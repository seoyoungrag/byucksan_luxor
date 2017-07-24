package com.sds.acube.luxor.framework.config;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sds.acube.luxor.common.ConstantList;
import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.framework.cache.MemoryCache;
import com.sds.acube.luxor.framework.cache.MemoryCacheCenter;
import com.sds.acube.luxor.portal.service.AdminService;
import com.sds.acube.luxor.portal.service.PortalEnvironmentService;
import com.sds.acube.luxor.portal.vo.AdminUserVO;
import com.sds.acube.luxor.portal.vo.PortalEnvironmentVO;

/**
 * 포탈에서 사용하는 환경설정을 담당함
 */
public class LuxorConfig {
	public static final String ApplicationConfigFilePath = "/config/application/Common.xml";
    public static final String ProgramListFilePath = "/config/programlist/Common.xml";
    
    private static final String DEFAULT_RESOURCE = "Common";
    private static final String DEFAULT_DELIMITER = ",";
    
    private static HashMap<String, String> applicationConfig;
    private static Log logger = LogFactory.getLog(LuxorConfig.class);
    private static String contextRootRealPath; //contextRoot
    private static PortalEnvironmentService portalEnvironmentService;
    private static AdminService adminService;
    private static AdminUserVO[] admins;
    private static MemoryCache adminUser;
    
    static {
		applicationConfig = new HashMap<String, String>();
		refresh();
    }
    
    
    public static void init(ApplicationContext wac) throws Exception {
    	portalEnvironmentService = (PortalEnvironmentService)wac.getBean("portalEnvironmentService");
    	adminService = (AdminService)wac.getBean("adminService");
    	adminUser= new MemoryCache() {
			public void clear() {
		        try {
					admins = adminService.getAdminUserListAll(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			public void remove(Object key) {}
			public int size() {
				return admins.length;
			}
		};
    	MemoryCacheCenter.getInstance().register("ADMIN_USER", adminUser);
    	loadEnvironment();
    	initAdminListCache();
    }

    
    public static void loadEnvironment() {
    	if(portalEnvironmentService==null) {
    		return;
    	}
    	
    	try {
	        PortalEnvironmentVO[] envs = portalEnvironmentService.getAllEnvironments();
	        logger.info("---------------------------- Environment Load ----------------------------");
	        for(PortalEnvironmentVO env : envs) {
	        	String key = env.getTenantId()+"."+env.getPortalId()+"."+env.getEnvId();
	        	applicationConfig.put(key, env.getEnvValue());
	        	logger.info(key+"="+env.getEnvValue());
	        }
	        logger.info("---------------------------- Environment Load ----------------------------");
	        
        } catch (Exception e) {
        }
    }
    
	public static String getString(String key) {
		return getString(DEFAULT_RESOURCE, key);
	}

	/**
	 * Environment에 정의된 값을 가져옴 (TenantId + PortalId + key)
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public static String getEnvString(HttpServletRequest request, String key) {
		String tenantId = (String)request.getSession().getAttribute("TENANT_ID");
		String portalId = (String)request.getSession().getAttribute("PORTAL_ID");
		if(tenantId==null) {
			tenantId = getString("Common", "BASIC.TENANT_ID");
		}
		if(portalId==null) {
			portalId = getString("Common", "BASIC.PORTAL_ID");
		}
		return getEnvString(tenantId, portalId, key);
	}

	public static String getEnvString(String tenantId, String portalId, String key) {
		String result = getString(tenantId+"."+portalId, key);
		if(CommonUtil.isNullOrEmpty(result)) {
			result = getString("%%TENANT_ID%%.%%PORTAL_ID%%", key);
		}
		return result;
	}

	public static String getString(String strResourceName, String key) {
		return getString(strResourceName, key, "");
	}
	
	public static String getString(String strResourceName, String key, String defaultValue) {
        String strRtn = applicationConfig.get(strResourceName + "." + key);
        if (strRtn == null) {
            return defaultValue;
        }
		return applicationConfig.get(strResourceName + "." + key);
	}

	public static String[] getStringArray(String key) {
		return getStringArray(DEFAULT_RESOURCE, key);
	}

	public static String[] getStringArray(String strResourceName, String key) {
		return getStringArray(strResourceName, key, DEFAULT_DELIMITER);
	}
	
	public static String[] getStringArray(String strResourceName, String key, String delimiter) {
		return getStringArray(strResourceName, key, delimiter, "");
	}
	
	/**
	 * value가 구분자(디폴트는 콤마임) 로 구별된 경우 split해서 배열에 담아서 리턴 
	 * 구분자로 구별되어있지 않은 단순 문자열인 경우에는 해당 문자열을 index 0에 담아 배열 리턴
	 * value가 없는 경우 length 0인 빈 배열 리턴
	 * 
	 * @param strResourceName
	 * @param key
	 * @param defaultValue
	 * @return String[] 배열 (null을 리턴하는 경우는 없음)
	 */
	public static String[] getStringArray(String strResourceName, String key, String delimiter, String defaultValue) {
		String[] result = null;
		String tempVal = getString(strResourceName, key, defaultValue);
		if(CommonUtil.isNullOrEmpty(tempVal)) {
			return new String[0];
		}
		if(tempVal.contains(delimiter)) {
			StringTokenizer st = new StringTokenizer(tempVal, delimiter);
			result = new String[st.countTokens()];
			int i=0;
			while(st.hasMoreTokens()) {
				result[i++] = st.nextToken();
			}
		} else {
			result = new String[]{tempVal};
		}
		return result;
	}

    public static List<String> getList(String strResourceName, String key) {
        List<String> list = new ArrayList<String>();
        Iterator<String> i = applicationConfig.keySet().iterator();
        while(i.hasNext()) {
            String serviceName = i.next();
            if(serviceName.indexOf(strResourceName + "." + key) != -1) {
                list.add(applicationConfig.get(serviceName));
            }
        }
        return list;
    }
    
	public static int getEnvInt(HttpServletRequest request, String key) {
		String tenantId = (String)request.getSession().getAttribute("TENANT_ID");
		String portalId = (String)request.getSession().getAttribute("PORTAL_ID");
		if(tenantId==null) {
			tenantId = getString("Common", "BASIC.TENANT_ID");
		}
		if(portalId==null) {
			portalId = getString("Common", "BASIC.PORTAL_ID");
		}
		return getEnvInt(tenantId, portalId, key);
	}

	public static int getEnvInt(String tenantId, String portalId, String key) {
		int result = getInt(tenantId+"."+portalId, key);
		if(result==-1) {
			result = getInt("%%TENANT_ID%%.%%PORTAL_ID%%", key);
		}
		return result;
	}
    
    public static int getInt(String strResourceName, String key) {
        String strRtn = applicationConfig.get(strResourceName + "." + key);
        if (strRtn == null) {
            return -1;
        }
        int nRet = 0;
        try {
            nRet = Integer.parseInt(strRtn);
        } catch (Exception e) {
        	nRet = -1;
        }
        return nRet;
    }

	public static void refresh() {
		try {
			if (applicationConfig != null) {
				applicationConfig.clear();
			}
			setApplicationConfig(ApplicationConfigFilePath);
			loadEnvironment();
			initAdminListCache();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
        	logger.info("Luxor Config Refresh OK!!");
		}
	}
	
	private static void setApplicationConfig(String strApplicationConfigFilePath) {
		InputStream fis = null;
		try {
			String strApplicationConfigName = "";
			String strCategoryName = "";
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			fis = (new ConstantList()).getClass().getResourceAsStream(strApplicationConfigFilePath);
			Document document = builder.parse(fis);
			Element root = document.getDocumentElement();
			strApplicationConfigName = root.getAttribute("name");
			NodeList categories = root.getChildNodes();
			
			logger.info("---------------------------- Config Reset ----------------------------");
			
			for (int i = 0; i < categories.getLength(); i++) {
				Node category = categories.item(i);
				if (category.hasAttributes()) {
					strCategoryName = category.getAttributes().getNamedItem("name").getNodeValue();
					NodeList variables = category.getChildNodes();
					for (int j = 0; j < variables.getLength(); j++) {
						Node variable = variables.item(j);
						if (variable.hasAttributes()) {
							String nodeValue = "";
							if (variable.hasChildNodes()) {
								nodeValue = variable.getFirstChild().getNodeValue();
							}
							
							String key = strApplicationConfigName + "." + strCategoryName + "." + variable.getAttributes().getNamedItem("name").getNodeValue();							
							applicationConfig.put(key, nodeValue);
							
							logger.info(key+"="+nodeValue);
							
							if (strCategoryName.equals("CONFIG_LIST")) {
								setApplicationConfig(variable.getFirstChild().getNodeValue());
							}
						}
					}
				}
			}
			logger.info("---------------------------- Config Reset ----------------------------");
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
			try {
			    if(fis!=null) fis.close();
		    } catch (Exception ignore) {}
		}
	}

	
	public static void setContextRootRealPath(String path){
	    contextRootRealPath = path.replace('\\', '/');
	}
	
	public static String getCotextRootRealPath(){
	    return contextRootRealPath;
	}
	
	public static void initAdminListCache() throws Exception{
		if(adminService==null) {
    		return;
    	}
		//서버이중화된 경우 ADMIN_USER 캐시를 지우라고 DB에 기록한다.
		MemoryCacheCenter.getInstance().clear("ADMIN_USER");
	}
	
	public static AdminUserVO[] getAdminList(){
	    return admins;
	}
	
}
