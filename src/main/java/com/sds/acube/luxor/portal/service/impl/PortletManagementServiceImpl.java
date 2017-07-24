/*
 * @(#) PortletManagementServiceImpl.java 2010. 5. 11.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.portal.service.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import javax.jws.WebMethod;

//import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.anyframe.pagination.Page;
import org.springframework.util.StringUtils;

import com.sds.acube.luxor.common.service.MessageService;
import com.sds.acube.luxor.common.util.Tree;
import com.sds.acube.luxor.common.vo.MessageVO;
import com.sds.acube.luxor.common.vo.TreeVO;
import com.sds.acube.luxor.framework.cache.MemoryCacheCenter;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.service.BaseService;
import com.sds.acube.luxor.portal.PortletConstant;
import com.sds.acube.luxor.portal.context.PortletContextRegistry;
import com.sds.acube.luxor.portal.context.PortletXMLLoader;
import com.sds.acube.luxor.portal.dao.PortletCategoryDAO;
import com.sds.acube.luxor.portal.dao.PortletContextDAO;
import com.sds.acube.luxor.portal.service.PortletManagementService;
import com.sds.acube.luxor.portal.vo.PortletCategoryVO;
import com.sds.acube.luxor.portal.vo.PortletContextVO;
import com.sds.acube.luxor.portal.vo.PortletManagementVO;


/**
 * 
 * @author Alex, Eum
 * @version $Revision: 1.2.2.2 $ $Date: 2011/03/17 07:47:26 $
 */
public class PortletManagementServiceImpl extends BaseService implements PortletManagementService {
	private PortletContextDAO contextDAO;
	private PortletCategoryDAO categoryDAO;
	private MessageService messageService;
	private Log logger = LogFactory.getLog(PortletManagementServiceImpl.class);


	/**
	 * @param messageService The messageService to set.
	 */
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}


	/**
	 * @param categoryDAO The categoryDAO to set.
	 */
	public void setCategoryDAO(PortletCategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}


	/**
	 * @param contextDAO The contextDAO to set.
	 */
	public void setContextDAO(PortletContextDAO contextDAO) {
		this.contextDAO = contextDAO;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sds.acube.luxor.portal.service.PortletManagementService#registerPortlet(com.sds.acube.luxor.portal.vo.PortletManagementVO)
	 */
	/*
	 * public PortletContextVO portletRegister(PortletManagementVO param) throws Exception {
	 * // step 1. 업로드된 포틀릿 압축파일을 지정된 위치에서 압축해제
	 * String ext = null;
	 * PortletContextVO context = null;
	 * 
	 * 
	 * String _target = LuxorConfig.getString("Common", "PORTLET.DEPLOY_PATH");
	 * ext = param.getFileName().substring(param.getFileName().lastIndexOf(".") + 1);
	 * logger.debug("ext=" + ext);
	 * if(ext.equalsIgnoreCase("zip")) {
	 * Extract4Zip.extract(param.getFilePath(), _target);
	 * }else if(ext.equalsIgnoreCase("jar")) {
	 * Extract4Jar.extract(param.getFilePath(), _target);
	 * }else {
	 * throw new Exception(param.getFileName() + " is invalid.");
	 * }
	 * // step 2. 압축 해제된 포틀릿 폴더에서 portlet.xml을 로드하여 유효성 체크
	 * context = PortletXMLLoader.load(_target + param.getFileName().substring(0, param.getFileName().lastIndexOf(".")) +
	 * ConstantList.PORTLET_XML_PATH);
	 * logger.debug(context.toString());
	 * // ==================================================================
	 * return context;
	 * }
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sds.acube.luxor.portal.service.PortletManagementService#portletRegister(com.sds.acube.luxor.portal.vo.PortletManagementVO)
	 */
	@WebMethod(exclude = true)
	public boolean portletRegister(PortletManagementVO param) throws Exception {
		PortletContextVO context = null;
		boolean r = false;
		try {
			// 기존에 등록한 데이타가 있는지 체크
			int cnt = contextDAO.checkExist(param);
			// 등록할 포틀릿 디스크립터 정보를 load
			// context = PortletXMLLoader.load(LuxorConfig.getString("Common", "PORTLET.DEPLOY_PATH") + param.getContextName() +
			// ConstantList.PORTLET_XML_PATH);
			context = new PortletContextVO();
			context.setDescription(param.getOutline());
			context.setDisplayName(param.getTitle());
			context.setEditUrl(param.getEditUrl());
			context.setGoUrl(param.getGoUrl());
			context.setHelpUrl(param.getHelpUrl());
			context.setPortletContextName(param.getContextName());
			context.setScriptsArray(param.getScriptRef());
			context.setStylesArray(param.getStyleRef());
			context.setTitle(param.getTitle());
			context.setTypeOfPortlet(param.getTypeOfPortlet());
			context.setViewUrl(param.getViewUrl());
			context.setImgUrl(param.getImgUrl());			
			context.setCategoryId(param.getCategoryId());
			context.setLoginFlag(param.getLoginFlag()); // 로그인 여부
			context.setSsoInfo(param.getSsoInfo());
			// portlet context를 메모리에 저장
			PortletContextRegistry.addPortlet(context);
			// portlet context의 DB 상태를 변경(등록완료)
			// param.setVersionInfo(context.getVersion());
			param.setStatusCode(PortletConstant.DEPLOYED); // 등록 성공
			// context 정보를 xml 형태로 변환
			param.setDeployDescriptionXml(setContextInfo2XML(context));
			// portlet context를 DB에 저장(등록대기)
			if (cnt == 0) { // 기존에 등록된 데이타가 없을 경우만 체크
			// param.setStatusCode(PortletConstant.STANDBY); // 등록대기 상태로 우선 저장
				contextDAO.insert(param);
			} else {
				contextDAO.update(param);
			}
			MemoryCacheCenter.getInstance().clear("PORTLET_SERVICE");
			r = true;
		} catch (Exception e) {
			// step 6. portlet context의 DB 상태를 변경( 오류)
			param.setStatusCode(PortletConstant.UNDEPLOYED_BY_FAULT); // 등록 실패
			logger.error(e.getMessage(), e);
			param.setException(e.getMessage());
			contextDAO.update(param);
		}
		return r;
	}


	/**
	 * @param context
	 * @return
	 */
	private String setContextInfo2XML(PortletContextVO context) throws Exception {
		InputStream is = (new PortletManagementServiceImpl()).getClass().getResourceAsStream("/template/portlet_template.xml");
		byte[] b = null;
		String xml = null;
		try {
			b = new byte[is.available()];
			is.read(b);
			xml = new String(b);
			xml = xml.replaceAll(PortletConstant.DESCRIPTION, context.getDescription());
			xml = xml.replaceAll(PortletConstant.PORTLET_CONTEXT_NAME, context.getPortletContextName());
			xml = xml.replaceAll(PortletConstant.DISPLAY_NAME, context.getDisplayName());
			xml = xml.replaceAll(PortletConstant.LOGIN_FLAG, Integer.toString(context.getLoginFlag()));
			xml = xml.replaceAll(PortletConstant.SSO_INFO, context.getSsoInfo());
			xml = xml.replaceAll(PortletConstant.TITLE, context.getTitle());
			xml = xml.replaceAll(PortletConstant.VIEW, context.getViewUrl());
			xml = xml.replaceAll(PortletConstant.EDIT, context.getEditUrl());
			xml = xml.replaceAll(PortletConstant.HELP, context.getHelpUrl());
			xml = xml.replaceAll(PortletConstant.GO, context.getGoUrl());
			xml = xml.replaceAll(PortletConstant.IMG, context.getImgUrl());
			
			StringBuffer sb = new StringBuffer();
			if (context.getScripts().size() > 0) {
				for (int i = 0; i < context.getScripts().size(); i++) {
					sb.append(",").append((String) context.getScripts().get(i));
				}
				xml = xml.replaceAll(PortletConstant.JAVASCRIPT, sb.toString().substring(1));
			} else {
				xml = xml.replaceAll(PortletConstant.JAVASCRIPT, "");
			}
			sb = new StringBuffer();
			if (context.getScripts().size() > 0) {
				for (int i = 0; i < context.getStyles().size(); i++) {
					sb.append(",").append((String) context.getStyles().get(i));
				}
				xml = xml.replaceAll(PortletConstant.STYLE, sb.toString().substring(1));
			} else {
				xml = xml.replaceAll(PortletConstant.STYLE, "");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (is != null) is.close();
			} catch (Exception e2) {}
		}
		return xml;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sds.acube.luxor.portal.service.PortletManagementService#deployStatusInfo(com.sds.acube.luxor.portal.vo.PortletManagementVO)
	 */
	public List<PortletManagementVO> deployStatusInfo(PortletManagementVO param) throws Exception {
		List<PortletManagementVO> list = null;
		List<PortletCategoryVO> categoryList = null;
		HashMap<String, String> map = new HashMap<String, String>();
		list = (List<PortletManagementVO>) contextDAO.getList(param);
		categoryList = (List<PortletCategoryVO>) categoryDAO.getList(param);
		for (int i = 0; i < categoryList.size(); i++) {
			PortletCategoryVO catg = (PortletCategoryVO) categoryList.get(i);
			map.put(catg.getCategoryId(), catg.getCategoryName());
		}
		for (int i = 0; i < list.size(); i++) {
			PortletManagementVO item = (PortletManagementVO) list.get(i);
			// param.setMessageId(item.getCategoryName());
			// vo.setCategoryName((messageService.getMessageByIdLang(param)).getMessageName());
			item.setCategoryName((String) map.get(item.getCategoryId()));
		}
		return list;
	}


	public Page deployStatusInfoPage(PortletManagementVO param) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		Page page = contextDAO.getListPage(param);
		List<PortletManagementVO> list = (List<PortletManagementVO>)page.getList();
		List<PortletCategoryVO> categoryList = (List<PortletCategoryVO>) categoryDAO.getList(param);
		
		for (int i = 0; i < categoryList.size(); i++) {
			PortletCategoryVO catg = (PortletCategoryVO) categoryList.get(i);
			map.put(catg.getCategoryId(), catg.getCategoryName());
		}
		
		for (int i = 0; i < list.size(); i++) {
			PortletManagementVO item = (PortletManagementVO) list.get(i);
			item.setCategoryName((String) map.get(item.getCategoryId()));
		}
		
		return page;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sds.acube.luxor.portal.service.PortletManagementService#getPortletContextInfo(com.sds.acube.luxor.portal.vo.PortletManagementVO)
	 */
	public PortletContextVO getPortletContextInfo(PortletManagementVO param) throws Exception {
		PortletManagementVO r = contextDAO.get(param);
		// 외부에서 웹서비스 호출 시 지정값이 없을 경우 에러 발생 회피. 2013.09.24
		if (r == null) { 
			return new PortletContextVO();
		}
		PortletContextVO portlet = (PortletContextVO) PortletXMLLoader.load(r.getDeployDescriptionXml()).get(0);
		// 외부에서 웹서비스 호출 시 지정값이 없을 경우 에러 발생 회피. 2013.09.24
		if (portlet == null) { 
			return new PortletContextVO();
		}
		param.setCategoryId(r.getCategoryId());
		portlet.setTypeOfPortlet(r.getTypeOfPortlet());
		portlet.setCategoryId(r.getCategoryId());
		// 외부에서 웹서비스 호출 시 지정값이 없을 경우 에러 발생 회피. 2013.09.24
		if (!StringUtils.hasText(param.getCategoryId())) {
			param.setCategoryId(r.getCategoryId());
		}
		portlet.setCategoryName(categoryDAO.getName(param).getCategoryName());
		return portlet;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sds.acube.luxor.portal.service.PortletManagementService#redeploy(com.sds.acube.luxor.portal.vo.PortletManagementVO)
	 */
	@WebMethod(exclude = true)
	public boolean redeploy(PortletManagementVO param) throws Exception {
		PortletContextVO context = null;
		PortletManagementVO portlet = contextDAO.get(param);
		logger.debug("redeploy():xml=" + portlet.getDeployDescriptionXml());
		context = (PortletContextVO) PortletXMLLoader.load(portlet.getDeployDescriptionXml()).get(0);
		context.setCategoryId(portlet.getCategoryId());
		context.setCategoryName(portlet.getCategoryName());
		PortletContextRegistry.addPortlet(context);
		param.setStatusCode(PortletConstant.DEPLOYED); // 등록 성공
		boolean r = contextDAO.update4undeploy(param) > 0 ? true : false;
		if (r) MemoryCacheCenter.getInstance().clear("PORTLET_SERVICE");
		return r;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sds.acube.luxor.portal.service.PortletManagementService#redeployAll(com.sds.acube.luxor.portal.vo.PortletManagementVO)
	 */
	@WebMethod(exclude = true)
	public boolean redeployAll(PortletManagementVO param) throws Exception {
		boolean r = true;
		List<PortletManagementVO> list = contextDAO.getList(param);
		for (int i = 0; i < list.size(); i++) {
			PortletManagementVO portlet = (PortletManagementVO) list.get(i);
			try {
				if (!redeploy(portlet)) throw new Exception("Redeploy " + portlet.getContextName() + " is failed.");
			} catch (Exception e) {
				r = false;
				logger.warn(e.getMessage(), e);
			}
		}
		MemoryCacheCenter.getInstance().clear("PORTLET_SERVICE");
		return r;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sds.acube.luxor.portal.service.PortletManagementService#undeploy(com.sds.acube.luxor.portal.vo.PortletManagementVO)
	 */
	@WebMethod(exclude = true)
	public boolean undeploy(PortletManagementVO param) throws Exception {
		PortletContextRegistry.deletePortlet(param.getPortalId(), param.getContextName());
		param.setStatusCode(PortletConstant.UNDEPLOYED_BY_USER);
		boolean r = contextDAO.update4undeploy(param) > 0 ? true : false;
		if (r) MemoryCacheCenter.getInstance().clear("PORTLET_SERVICE");
		return r;
	}


	@WebMethod(exclude = true)
	public boolean delete(PortletManagementVO param) throws Exception {
		PortletContextRegistry.deletePortlet(param.getPortalId(), param.getContextName());
		boolean r = contextDAO.delete(param) > 0 ? true : false;
		if (r) MemoryCacheCenter.getInstance().clear("PORTLET_SERVICE");
		return r;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sds.acube.luxor.portal.service.PortletManagementService#getPortletCategoryList(com.sds.acube.luxor.portal.vo.PortletCategoryVO)
	 */
	public PortletCategoryVO[] getPortletCategoryList(PortletManagementVO param) throws Exception {
		List<PortletCategoryVO> r = (List<PortletCategoryVO>) categoryDAO.getList(param);
		PortletCategoryVO[] a = new PortletCategoryVO[r.size()];
		r.toArray(a);
		// for(int i=0;i<a.length;i++) {
		// MessageVO m = new MessageVO();
		// m.setMessageId(a[i].getCategoryName());
		// m.setLangType(param.getLangType());
		// MessageVO message = messageService.getMessageByIdLang(m);
		// a[i].setCategoryName(message.getMessageName());
		// }
		return a;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sds.acube.luxor.portal.service.PortletManagementService#addCategory(com.sds.acube.luxor.portal.vo.PortletCategoryVO)
	 */
	@WebMethod(exclude = true)
	public PortletCategoryVO addCategory(PortletCategoryVO param) throws Exception {
		// 메세지 등록 후 messageId 넘겨받아 셋팅
		String categoryNameId = messageService.insertMessage(param);
		if (categoryNameId != null) {
			param.setCategoryName(categoryNameId);
			if (categoryDAO.insert(param) > 0) {
				param.setMessageId(categoryNameId);
				MessageVO r = messageService.getMessageByIdLang(param);
				param.setCategoryName(r.getMessageName());
			} else {
				throw new Exception("portlet category insert is failed(" + param.getCategoryId() + ").");
			}
		} else {
			throw new Exception("message update is failed(" + param.getMessageId() + ").");
		}
		return param;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sds.acube.luxor.portal.service.PortletManagementService#updateCategory(com.sds.acube.luxor.portal.vo.PortletCategoryVO)
	 */
	@WebMethod(exclude = true)
	public PortletCategoryVO updateCategory(PortletCategoryVO param) throws Exception {
		if (messageService.updateMessage(param)) {
			param.setCategoryName(param.getMessageId());
			if (categoryDAO.update(param) > 0) {
				MessageVO r = messageService.getMessageByIdLang(param);
				param.setCategoryName(r.getMessageName());
			} else {
				throw new Exception("portlet category update is failed(" + param.getCategoryId() + ").");
			}
		} else {
			throw new Exception("message update is failed(" + param.getMessageId() + ").");
		}
		return param;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sds.acube.luxor.portal.service.PortletManagementService#loadXmlInfo(com.sds.acube.luxor.portal.vo.PortletManagementVO)
	 */
	@WebMethod(exclude = true)
	public List<PortletContextVO> loadXmlInfo(PortletManagementVO param) throws Exception {
		String ext = null;
		List<PortletContextVO> list = null;
		ext = param.getFileName().substring(param.getFileName().lastIndexOf(".") + 1);
		logger.debug("ext=" + ext);
		if (!ext.equalsIgnoreCase("xml")) {
			throw new Exception(param.getFileName() + " is invalid xml file.");
		}
		list = (List<PortletContextVO>) PortletXMLLoader.loadFile(param.getFilePath());
		for (int i = 0; i < list.size(); i++) {
			PortletContextVO context = (PortletContextVO) list.get(i);
			context.setTypeOfPortlet(param.getTypeOfPortlet());
			context.setCategoryId(param.getCategoryId());
			context.setCategoryName(categoryDAO.getName(param).getCategoryName());
			PortletContextRegistry.addPortlet(context);
			logger.debug(context.toString());
		}
		PortletContextVO c = new PortletContextVO();
		c.setPortletContextName(param.getFileName());
		c.setTypeOfPortlet(param.getTypeOfPortlet());
		c.setCategoryId(param.getCategoryId());
		list.add(c);
		// ==================================================================
		return list;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sds.acube.luxor.portal.service.PortletManagementService#portletRegisterXMLConfirm(com.sds.acube.luxor.portal.vo.PortletManagementVO
	 * )
	 */
	@WebMethod(exclude = true)
	public boolean portletRegisterXMLConfirm(PortletManagementVO param) throws Exception {
		boolean r = false;
		try {
			logger.debug(LuxorConfig.getString("Common", "PORTLET.UPLOAD_PATH") + param.getFileName());
			// 등록할 포틀릿 디스크립터 정보를 load
			// context = PortletXMLLoader.load(LuxorConfig.getString("Common", "PORTLET.UPLOAD_PATH") + param.getContextName() + ".xml");
			List<PortletContextVO> list = PortletXMLLoader.loadFile(LuxorConfig.getString("Common", "PORTLET.UPLOAD_PATH")
			    + param.getFileName());
			for (int i = 0; i < list.size(); i++) {
				PortletContextVO context = null;
				context = (PortletContextVO) list.get(i);
				context.setTypeOfPortlet(param.getTypeOfPortlet());
				context.setCategoryId(param.getCategoryId());
				// portlet context를 메모리에 저장
				PortletContextRegistry.addPortlet(context);
				// portlet context의 DB 상태를 변경(등록완료)
				param.setStatusCode(PortletConstant.DEPLOYED); // 등록 성공
				// context 정보를 xml 형태로 변환
				param.setDeployDescriptionXml(setContextInfo2XML(context));
				param.setContextName(context.getPortletContextName());
				// 기존에 등록한 데이타가 있는지 체크
				int cnt = contextDAO.checkExist(param);
				// portlet context를 DB에 저장(등록대기)
				if (cnt == 0) { // 기존에 등록된 데이타가 없을 경우만 체크
				// param.setStatusCode(PortletConstant.STANDBY); // 등록대기 상태로 우선 저장
					contextDAO.insert(param);
				} else {
					contextDAO.update(param);
				}
			}
			MemoryCacheCenter.getInstance().clear("PORTLET_SERVICE");
			r = true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return r;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sds.acube.luxor.portal.service.PortletManagementService#exportXML(com.sds.acube.luxor.portal.vo.PortletManagementVO)
	 */
	public String exportXML(PortletManagementVO param) throws Exception {
		StringBuffer a = new StringBuffer();
		a.append(PortletConstant.PORTLET_APP_OPEN);
		for (int i = 0; i < param.getCheckbox().length; i++) {
			param.setContextName(param.getCheckbox()[i]);
			PortletManagementVO r = contextDAO.get(param);
			String xml = null;
			xml = r.getDeployDescriptionXml().substring(r.getDeployDescriptionXml().indexOf("<portlet>"), r.getDeployDescriptionXml().indexOf("</portlet-app>")).trim();
			logger.debug(xml);
			a.append("\n").append(xml).append("\n");
		}
		a.append(PortletConstant.PORTLET_APP_CLOSE);
		return a.toString().trim();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sds.acube.luxor.portal.service.PortletManagementService#getDeployDescriptionXML(com.sds.acube.luxor.portal.vo.PortletManagementVO
	 * )
	 */
	public PortletManagementVO getDeployDescriptionXML(PortletManagementVO param) throws Exception {
		return contextDAO.get(param);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sds.acube.luxor.portal.service.PortletManagementService#updateDeployDescriptionXML(com.sds.acube.luxor.portal.vo.PortletManagementVO
	 * )
	 */
	@WebMethod(exclude = true)
	public boolean updateDeployDescriptionXML(PortletManagementVO param) throws Exception {
		boolean r = false;
		if (contextDAO.updateDeployDescriptionXML(param) > 0 ? true : false) {
			// PortletContextVO context = null;
			// PortletManagementVO portlet = contextDAO.get(param);
			// logger.debug("updateDeployDescriptionXML():xml=" + param.getDeployDescriptionXml());
			// context = PortletXMLLoader.load(param.getDeployDescriptionXml());
			// context.setCategoryId(portlet.getCategoryId());
			// context.setCategoryName(portlet.getCategoryName());
			// PortletContextRegistry.addPortlet(context);
			MemoryCacheCenter.getInstance().clear("PORTLET_SERVICE");
			r = true;
		}
		return r;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sds.acube.luxor.portal.service.PortletManagementService#getPortletCategoryTree(com.sds.acube.luxor.portal.vo.PortletManagementVO)
	 */
	public TreeVO[] getPortletCategoryTree(PortletManagementVO param) throws Exception {
		List<PortletCategoryVO> categories = categoryDAO.getList(param);
		TreeVO[] trees = new TreeVO[categories.size()];
		Tree tree = new Tree();
		for (int i = 0; i < categories.size(); i++) {
			PortletCategoryVO item = (PortletCategoryVO) categories.get(i);
			TreeVO node = new TreeVO();
			node.setDepth(1);
			node.setNodeId(item.getCategoryId());
			node.setNodeName(item.getCategoryName());
			node.setHasChild("N");
			node.setSeq(i);
			node.setParentId("ROOT");
			tree.add(item.getCategoryId(), "ROOT", node);
		}
		tree.toArray(trees);
		return trees;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sds.acube.luxor.portal.service.PortletManagementService#getPortletCategoryInfo(com.sds.acube.luxor.portal.vo.PortletManagementVO)
	 */
	public PortletCategoryVO getPortletCategoryInfo(PortletManagementVO param) throws Exception {
		PortletCategoryVO category = categoryDAO.get(param);
		param.setMessageId(category.getCategoryName());
		MessageVO message = messageService.getMessageByIdLang(param);
		category.setMessageId(category.getCategoryName());
		category.setCategoryName(message.getMessageName());
		return category;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sds.acube.luxor.portal.service.PortletManagementService#deletePortletCategory(com.sds.acube.luxor.portal.vo.PortletManagementVO)
	 */
	@WebMethod(exclude = true)
	public int deletePortletCategory(PortletManagementVO param) throws Exception {
		param.setMessageId(param.getCategoryName());
		messageService.deleteMessage(param);
		return categoryDAO.delete(param);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sds.acube.luxor.portal.service.PortletManagementService#checkDup(com.sds.acube.luxor.portal.vo.PortletManagementVO)
	 */
	public String checkDup(PortletManagementVO param) throws Exception {
		String f = "modify";
		PortletManagementVO r = contextDAO.checkDup(param);
		if (r != null && !"".equals(r.getContextName())) {
			if(r.getPortalId().equals(param.getPortalId())) {
				f = "modify";
			} else {
				f = r.getPortalId();
			}
		} else {
			f = "success";
		}
		return f;
	}
}
