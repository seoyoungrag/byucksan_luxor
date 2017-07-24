/*
 * @(#) PortletXMLLoader.java 2010. 5. 19.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.portal.context;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.sds.acube.luxor.portal.PortletConstant;
import com.sds.acube.luxor.portal.vo.PortletContextVO;


/**
 * 
 * @author Alex, Eum
 * @version $Revision: 1.1.6.2 $ $Date: 2011/04/21 00:13:39 $
 */
public class PortletXMLLoader {
	private static Log logger = LogFactory.getLog(PortletXMLLoader.class);


	/**
	 * @param xmlFile
	 * @return
	 * @throws Exception
	 */
	public static List<PortletContextVO> loadFile(String xmlFile) throws Exception {
		FileInputStream fis = null;
		List<PortletContextVO> r = null;
		try {
			fis = new FileInputStream(xmlFile);
			r = PortletXMLLoader.parse(fis);
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(fis!=null) fis.close();
			} catch (Exception e) {}
		}
		return r;
	}


	/**
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public static List<PortletContextVO> load(String xml) throws Exception {
		ByteArrayInputStream bais = null;
		List<PortletContextVO> r = null;
		try {
			bais = new ByteArrayInputStream(xml.getBytes("utf-8"));
			r = PortletXMLLoader.parse(bais);
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(bais!=null) bais.close();
			} catch (Exception ignore) {}
		}
		return r;
	}


	/**
	 * @param is
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private static List<PortletContextVO> parse(InputStream is) throws Exception {
		PortletContextVO portletContext = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<PortletContextVO> list = new ArrayList<PortletContextVO>();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(is);
			Element root = document.getDocumentElement();
			// logger.debug("root=" + root.getNodeName());
			NodeList portlets = root.getChildNodes();
			for (int i = 0; i < portlets.getLength(); i++) {
				Node portlet = portlets.item(i);
				if ("portlet".equals(portlet.getNodeName())) {
					// logger.debug("i=" + portlet.getNodeName());
					NodeList portletInfos = portlet.getChildNodes();
					for (int j = 0; j < portletInfos.getLength(); j++) {
						Node item = portletInfos.item(j);
						if (item.getNodeType() == 1) {
							// logger.debug("j=" + item.getNodeName() + "|" + item.getNodeType() + "|" + (item.getFirstChild() == null ? ""
							// : item.getFirstChild().getNodeValue()));
							if (item.getFirstChild() != null) {
								if (!"".equals(item.getFirstChild().getNodeValue().trim())) {
									// logger.debug(item.getNodeName() + "=" + item.getFirstChild().getNodeValue());
									map.put(item.getNodeName(), item.getFirstChild().getNodeValue());
								} else {
									if ("supports".equals(item.getNodeName())) {
										// logger.debug("put " + item.getNodeName());
										map.put(item.getNodeName(), getSupports(item.getChildNodes()));
									} else if ("portlet-info".equals(item.getNodeName())) {
										// logger.debug("put " + item.getNodeName());
										map.put(item.getNodeName(), getPortletInfo(item.getChildNodes()));
									}
								}
							}
						}
					}
					portletContext = new PortletContextVO();
					HashMap<String, Object> portletInfo = (HashMap<String, Object>) map.get("portlet-info");
					HashMap<String, Object> urlInfo = (HashMap<String, Object>) portletInfo.get("url-info");
					HashMap<String, Object> resourceInfo = (HashMap<String, Object>) portletInfo.get("resource-info");
					HashMap<String, String> supports = (HashMap<String, String>) map.get("supports");
					portletContext.setDisplayName((String) map.get("display-name"));
					portletContext.setEditUrl((String) urlInfo.get("edit"));
					portletContext.setGoUrl((String) urlInfo.get("go"));
					portletContext.setHelpUrl((String) urlInfo.get("help"));
					portletContext.setViewUrl((String) urlInfo.get("view"));
					portletContext.setImgUrl((String) urlInfo.get("img"));					
					portletContext.setPortletContextName((String) map.get("portlet-context-name"));
					portletContext.setScripts((List<String>) resourceInfo.get("javascript"));
					portletContext.setStyles((List<String>) resourceInfo.get("style"));
					portletContext.setTitle((String) portletInfo.get("title"));
					portletContext.setDescription((String) map.get("description"));
					portletContext.setSupports(supports);
					
					try {
						portletContext.setLoginFlag((Integer) portletInfo.get("login-flag"));
					} catch (Exception e) {
						portletContext.setLoginFlag(PortletConstant.LOGIN_NEED);
					}
					
					try {
						portletContext.setSsoInfo((String) portletInfo.get("sso-info"));
					} catch (Exception e) {
						portletContext.setSsoInfo("N/A");
					}
					
					logger.info("Loading portlet "+portletContext.getTitle());
					list.add(portletContext);
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				is.close();
			} catch (Exception ignore) {}
		}
		return list;
	}


	/**
	 * @param supports
	 * @return
	 * @throws Exception
	 */
	private static HashMap<String, Object> getSupports(NodeList supports) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < supports.getLength(); i++) {
			Node support = supports.item(i);
			if (support.getNodeType() == 1) {
				logger.debug("supports:" + support.getNodeName() + "=" + support.getFirstChild().getNodeValue());
				map.put(support.getNodeName(), support.getFirstChild().getNodeValue());
			}
		}
		return map;
	}


	/**
	 * @param portletInfos
	 * @return
	 * @throws Exception
	 */
	private static HashMap<String, Object> getPortletInfo(NodeList portletInfos) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < portletInfos.getLength(); i++) {
			Node portletInfo = portletInfos.item(i);
			if (portletInfo.getNodeType() == 1) {
				if ("title".equals(portletInfo.getNodeName())) {
					logger.debug("portletInfos:" + portletInfo.getNodeName() + "=" + portletInfo.getFirstChild().getNodeValue());
					map.put(portletInfo.getNodeName(), portletInfo.getFirstChild().getNodeValue());
				} else if ("login-flag".equals(portletInfo.getNodeName())) {
					logger.debug("portletInfos:" + portletInfo.getNodeName() + "=" + portletInfo.getFirstChild().getNodeValue());
					map.put(portletInfo.getNodeName(), portletInfo.getFirstChild().getNodeValue());
				} else if ("sso-info".equals(portletInfo.getNodeName())) {
					logger.debug("portletInfos:" + portletInfo.getNodeName() + "=" + portletInfo.getFirstChild().getNodeValue());
					map.put(portletInfo.getNodeName(), portletInfo.getFirstChild().getNodeValue());
				} else if ("url-info".equals(portletInfo.getNodeName())) {
					logger.debug("porteltInfos:put " + portletInfo.getNodeName());
					map.put(portletInfo.getNodeName(), getUrlInfo(portletInfo.getChildNodes()));
				} else if ("resource-info".equals(portletInfo.getNodeName())) {
					logger.debug("porteltInfos:put " + portletInfo.getNodeName());
					map.put(portletInfo.getNodeName(), getResourceInfo(portletInfo.getChildNodes()));
				} else if ("spring-service".equals(portletInfo.getNodeName())) {
					logger.debug("porteltInfos:put " + portletInfo.getNodeName());
					map.put(portletInfo.getNodeName(), getSpringService(portletInfo.getChildNodes()));
				} else if ("library-info".equals(portletInfo.getNodeName())) {
					logger.debug("porteltInfos:put " + portletInfo.getNodeName());
					map.put(portletInfo.getNodeName(), getLibraryInfo(portletInfo.getChildNodes()));
				}
			}
		}
		return map;
	}


	/**
	 * @param services
	 * @return
	 * @throws Exception
	 */
	private static List<String> getSpringService(NodeList services) throws Exception {
		List<String> list = new ArrayList<String>();
		String servicesString = null;
		for (int i = 0; i < services.getLength(); i++) {
			Node service = services.item(i);
			logger.debug("portletInfos.springServices:" + service.getNodeValue().trim());
			servicesString = service.getNodeValue() == null ? "" : service.getNodeValue().trim();
			StringTokenizer st = new StringTokenizer(servicesString, ",");
			while (st.hasMoreTokens()) {
				String serviceString = st.nextToken();
				logger.debug(serviceString);
				list.add(serviceString.trim());
			}
		}
		return list;
	}


	/**
	 * @param libs
	 * @return
	 * @throws Exception
	 */
	private static List<String> getLibraryInfo(NodeList libs) throws Exception {
		List<String> list = new ArrayList<String>();
		String libsString = null;
		for (int i = 0; i < libs.getLength(); i++) {
			Node lib = libs.item(i);
			logger.debug("portletInfos.libraryInfos:" + lib.getNodeValue().trim());
			libsString = lib.getNodeValue() == null ? "" : lib.getNodeValue().trim();
			StringTokenizer st = new StringTokenizer(libsString, ",");
			while (st.hasMoreTokens()) {
				String libString = st.nextToken();
				logger.debug(libString);
				list.add(libString.trim());
			}
		}
		return list;
	}


	/**
	 * @param urlInfos
	 * @return
	 * @throws Exception
	 */
	private static HashMap<String, Object> getUrlInfo(NodeList urlInfos) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < urlInfos.getLength(); i++) {
			Node url = urlInfos.item(i);
			if (url.getNodeType() == 1) {
				logger.debug("portletInfos.urlInfos:" + url.getNodeName() + "="
				    + (url.getFirstChild() == null ? "" : url.getFirstChild().getNodeValue()));
				map.put(url.getNodeName(), url.getFirstChild() == null ? "" : url.getFirstChild().getNodeValue());
			}
		}
		return map;
	}


	/**
	 * @param resourceInfos
	 * @return
	 * @throws Exception
	 */
	private static HashMap<String, Object> getResourceInfo(NodeList resourceInfos) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<String> javascripts = new ArrayList<String>();
		List<String> styles = new ArrayList<String>();
		for (int i = 0; i < resourceInfos.getLength(); i++) {
			Node resourceInfo = resourceInfos.item(i);
			if (resourceInfo.getNodeType() == 1) {
				if ("javascript".equals(resourceInfo.getNodeName())) {
					if (resourceInfo.getFirstChild() != null) {
						logger.debug("portletInfos.resourceInfos:javascript=" + resourceInfo.getFirstChild().getNodeValue());
						String s = resourceInfo.getFirstChild().getNodeValue();
						StringTokenizer st = new StringTokenizer(s, ",");
						while (st.hasMoreTokens()) {
							String s2 = st.nextToken();
							javascripts.add(s2.trim());
						}
					}
				} else if ("style".equals(resourceInfo.getNodeName())) {
					if (resourceInfo.getFirstChild() != null) {
						logger.debug("portletInfos.resourceInfos:style=" + resourceInfo.getFirstChild().getNodeValue());
						String s = resourceInfo.getFirstChild().getNodeValue();
						StringTokenizer st = new StringTokenizer(s, ",");
						while (st.hasMoreTokens()) {
							String s2 = st.nextToken();
							styles.add(s2.trim());
						}
					}
				}
			}
		}
		map.put("javascript", javascripts);
		map.put("style", styles);
		return map;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PortletXMLLoader.loadFile("D:/Temp/portlet.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
