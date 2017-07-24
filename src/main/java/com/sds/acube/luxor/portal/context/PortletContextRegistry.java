/*
 * @(#) PortletContextRegistry.java 2010. 5. 17.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.portal.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.portal.PortletConstant;
import com.sds.acube.luxor.portal.service.GroupPortalService;
import com.sds.acube.luxor.portal.service.PortletContextService;
import com.sds.acube.luxor.portal.vo.GroupPortalVO;
import com.sds.acube.luxor.portal.vo.PortletContextVO;
import com.sds.acube.luxor.portal.vo.PortletManagementVO;
import com.sun.portal.portletcontainer.context.registry.PortletRegistryContext;
import com.sun.portal.portletcontainer.driver.DriverUtil;


/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.3.2.1 $ $Date: 2011/02/10 06:06:06 $
 */
public class PortletContextRegistry {
	private static HashMap<String, PortletContextVO> contextRegistry;
    
    private static PortletContextService portletContextService = null;
    private static GroupPortalService groupPortalservice = null;
    
    private static Log logger = LogFactory.getLog(PortletContextRegistry.class);
    
    public PortletContextRegistry() {
        super();
    }
    
    
    public static void init(Object wac) {
         init((WebApplicationContext)wac);
    }
    
    public static void init(WebApplicationContext wac) {
    	try{
            logger.info("PortletContextRegistry loading ...");
            
            if(wac != null) {
            	portletContextService = (PortletContextService)wac.getBean("portletContextService");
                groupPortalservice = (GroupPortalService)wac.getBean("groupPortalService");
            } else {
                logger.warn("WebApplicationContext is null");
                return;
            }
            
            contextRegistry = new HashMap<String, PortletContextVO>();
            PortletManagementVO vo = new PortletManagementVO();
            
            if(portletContextService != null) {
                logger.debug("service is not null");
                List<PortletContextVO> r = portletContextService.selectList(vo);
                if (r != null) {
                	for(int index=0;index<r.size();index++) {
                		PortletContextVO portlet = (PortletContextVO)r.get(index);
                		contextRegistry.put(portlet.getPortalId()+portlet.getPortletContextName(), portlet);
                	}
                }
                
                // Loading JSR168 Portlets
                boolean useJsr168 = "Y".equals(LuxorConfig.getString("Common", "PORTLET.USE_JSR168"));
                if(useJsr168) {
                	logger.info("JSR168 portlet loading...");
                	
	                PortletRegistryContext portletRegistryContext = DriverUtil.getPortletRegistryContext();
	                List portlets = portletRegistryContext.getAvailablePortlets();
	    	        for(int i=0; i < portlets.size(); i++) {
	    	        	String portletContextName = (String)portlets.get(i);
	    	        	String portletDisplayName = portletRegistryContext.getPortletWindowTitle(portletContextName);
	    	        	
	    	        	logger.info("Loading portlet "+portletDisplayName+" [JSR168]");
	    	        	
	    	        	// 포탈별로 포틀릿 등록
	    	        	GroupPortalVO groupPortalVO = new GroupPortalVO();
	    	        	groupPortalVO.setTenantId(LuxorConfig.getString("Common", "BASIC.TENANT_ID"));
	    	        	List<GroupPortalVO> portalList = groupPortalservice.getList(groupPortalVO);
	    	        	
	    	        	for(int j=0; j < portalList.size(); j++) {
	    	        		groupPortalVO = portalList.get(j);
	    	        		
	        	        	PortletContextVO jsr168portlet = new PortletContextVO();
	        	        	jsr168portlet.setTenantId(groupPortalVO.getTenantId());
	        	        	jsr168portlet.setPortalId(groupPortalVO.getPortalId());
	        	        	jsr168portlet.setPortletContextName(portletContextName);
	        	        	jsr168portlet.setDisplayName(portletDisplayName);
	        	        	jsr168portlet.setTypeOfPortlet(PortletConstant.JSR168_PORTLET);
	        	        	jsr168portlet.setViewUrl("view.jsp");
	        	        	
	        	        	contextRegistry.put(groupPortalVO.getPortalId()+jsr168portlet.getPortletContextName(), jsr168portlet);
	    	        	}
	    	        }
                }
            }
            
            logger.info("PortletContextRegistry loaded");
        } catch(Exception e) {
            logger.error("PortletContextRegistry loading is failed", e);
        }
    }
    
    /**
     * Method Desciption : 포틀릿 등록정보가 변경된 경우에 정보를 다시 가져온다.
     */
    public static void reload(List<PortletContextVO> param) {
        try{
            logger.info("PortletContextRegistry reloading ...");
            
            contextRegistry = new HashMap<String, PortletContextVO>();
            
            if (param != null) {
            	for(int index=0;index<param.size();index++) {
            		PortletContextVO portlet = (PortletContextVO)param.get(index);
            		contextRegistry.put(portlet.getPortalId()+portlet.getPortletContextName(), portlet);	
            	}
            }
            logger.info("PortletContextRegistry reloaded");
        } catch(Exception e) {
            logger.error("PortletContextRegistry reloading is failed", e);
        }
    }
    
    public static void deletePortlet(String portalId, String contextName) throws Exception {
    	if(contextRegistry != null) {
    		contextRegistry.remove(portalId+contextName);
    	}else {
    		throw new Exception("contextRegistry is null!");
    	}
    }
    
    /**
     * @param context
     * @throws Exception
     */
    public static void addPortlet(PortletContextVO context) throws Exception {
    	if(contextRegistry != null) {
    		contextRegistry.put(context.getPortalId()+context.getPortletContextName(), context);
    	}else {
    		throw new Exception("contextRegistry is null!");
    	}
    }
    
    /**
     * @param portletContextName
     * @return
     * @throws Exception
     */
    public static PortletContextVO getPortlet(String portalId, String tenantId, String portletContextName) throws Exception {
    	if(contextRegistry != null) {
    		GroupPortalVO parentPortalVO = new GroupPortalVO();
    		parentPortalVO.setPortalId(portalId);
    		parentPortalVO.setTenantId(tenantId);
    		PortletContextVO resultVO = (PortletContextVO)contextRegistry.get(portalId+portletContextName);
    		if(resultVO == null) {
    			//부모그룹이 있는지 체크하여 있으면, 부모 페이지 트리도 함께 전달
    			for(int i = 0; true ; i++ ) {
    				parentPortalVO = groupPortalservice.getParentPortal(parentPortalVO);
    				if(parentPortalVO == null) {
    					break;
    				} else {
    					if(parentPortalVO.getParentId().equals("_ROOT_")) {
    						resultVO = (PortletContextVO)contextRegistry.get(parentPortalVO.getPortalId()+portletContextName);
    						break;
    					} else {
    						resultVO = (PortletContextVO)contextRegistry.get(parentPortalVO.getPortalId()+portletContextName);
    						if(resultVO != null) {
    							break;
    						}
    					}
    				}
    			}
    		}
    		return resultVO;
    	}else {
    		logger.error("contextRegistry is null!");
    		return null;
    	}
    }

    /**
     * @param portletContextName
     * @return
     * @throws Exception
     */
    public static PortletContextVO[] getPortletList() throws Exception {
    	if(contextRegistry != null) {
    		ArrayList<PortletContextVO> list = new ArrayList<PortletContextVO>();
    		for (Iterator iter = contextRegistry.entrySet().iterator(); iter.hasNext();) {
    		    Map.Entry<String, PortletContextVO> entry = (Map.Entry<String, PortletContextVO>) iter.next();
    		    String key = (String)entry.getKey();
    		    PortletContextVO context = (PortletContextVO)contextRegistry.get(key);
    		    list.add(context);
    		}
    		PortletContextVO[] contexts = new PortletContextVO[list.size()];
    		list.toArray(contexts);
    		return contexts;
    	} else {
    		logger.error("contextRegistry is null!");
    		return new PortletContextVO[0];
    	}
    }

    /**
     * 
     */
    public static void debugInfo() {
        StringBuffer sb = new StringBuffer();
        Iterator<String> it = contextRegistry.keySet().iterator();
        Object key = null;
        while(it.hasNext()) {
            key = it.next();
            
            PortletContextVO portletContext = contextRegistry.get(key);
            sb.append((String)key).append("=").append(portletContext.getPortletContextName()).append("\n");
        }
        logger.debug("==================== current PortletContextRegistry info =========================");
        logger.debug(sb.toString());
        logger.debug("==================================================================================");
    }

}
