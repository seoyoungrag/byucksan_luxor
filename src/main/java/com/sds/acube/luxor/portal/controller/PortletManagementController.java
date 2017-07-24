/*
 * @(#) PortletManagementController.java 2010. 5. 11.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.portal.controller;

import java.io.FileOutputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.anyframe.pagination.Page;

import com.sds.acube.luxor.common.service.TreeService;
import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.util.UtilRequest;
import com.sds.acube.luxor.common.vo.TreeVO;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.controller.BaseController;
import com.sds.acube.luxor.portal.context.PortletContextRegistry;
import com.sds.acube.luxor.portal.service.GroupPortalService;
import com.sds.acube.luxor.portal.service.PortletManagementService;
import com.sds.acube.luxor.portal.vo.GroupPortalVO;
import com.sds.acube.luxor.portal.vo.PortletCategoryVO;
import com.sds.acube.luxor.portal.vo.PortletContextVO;
import com.sds.acube.luxor.portal.vo.PortletManagementVO;


/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.3.2.8 $ $Date: 2011/11/04 06:08:38 $
 */
public class PortletManagementController extends BaseController {
//	private Log logger = LogFactory.getLog(PortletManagementController.class);
	private static String UPLOAD_DIR =  LuxorConfig.getString("Common", "PORTLET.UPLOAD_PATH");
	private PortletManagementService service;
	private TreeService treeService;
	private GroupPortalService groupPortalService;
	private String form;
	private String xmlForm;
	private String deployStatusForm;
	private String iframeLoadForm;
	private String helpPageLoadForm;
	private String categoryForm;
	private String exportForm;
	
	
	public void setTreeService(TreeService treeService) {
		this.treeService = treeService;
	}

	public void setGroupPortalService(GroupPortalService groupPortalService) {
		this.groupPortalService = groupPortalService;
	}
	
	public void setHelpPageLoadForm(String helpPageLoadForm) {
    	this.helpPageLoadForm = helpPageLoadForm;
    }

	/**
	 * @param exportForm The exportForm to set.
	 */
	public void setExportForm(String exportForm) {
		this.exportForm = exportForm;
	}

	/**
	 * @param categoryForm The categoryForm to set.
	 */
	public void setCategoryForm(String categoryForm) {
		this.categoryForm = categoryForm;
	}

	/**
	 * @param xmlForm The xmlForm to set.
	 */
	public void setXmlForm(String xmlForm) {
		this.xmlForm = xmlForm;
	}

	/**
	 * @param iframeLoadForm The iframeLoadForm to set.
	 */
	public void setIframeLoadForm(String iframeLoadForm) {
		this.iframeLoadForm = iframeLoadForm;
	}

	/**
	 * @param deployStatusForm The deployStatusForm to set.
	 */
	public void setDeployStatusForm(String deployStatusForm) {
		this.deployStatusForm = deployStatusForm;
	}

	public void setService(PortletManagementService service) {
		this.service = service;
	}

	public void setForm(String form) {
		this.form = form;
	}
	

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView loadRegisterForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("loadRegisterForm called...");
		ModelAndView mav = new ModelAndView(form);
		PortletManagementVO param = new PortletManagementVO();
		bind(request, param);
		PortletCategoryVO[] list = service.getPortletCategoryList(param);
		mav.addObject("param", param);
		mav.addObject("categories", list);
		return mav;
	}
	
	/**
	 * 포틀릿 등록(XML)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView loadRegisterFormXML(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("loadRegisterForm called...");
		ModelAndView mav = new ModelAndView(xmlForm);
		PortletManagementVO param = new PortletManagementVO();
		bind(request, param);
		PortletCategoryVO[] list = service.getPortletCategoryList(param);
		mav.addObject("param", param);
		mav.addObject("categories", list);
		return mav;
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView portletRegister(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("portletRegister called...");
		PortletManagementVO param = new PortletManagementVO();
		try{
			bind(request, param);
	        if(!service.portletRegister(param)) {
	        	throw new Exception("등록이 실패했습니다. \n 로그를 확인하시기 바랍니다.");
	        }
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
        	throw e;
        }
		
		CommonUtil.redirect(response, "/ep/portlet/deployStatusInfo.do?directRegisterPortletId="+param.getContextName()+"&categoryId=" + param.getCategoryId());
		return null;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView portletRegisterByXML(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("portletRegisterByXML called...");
//		ModelAndView mav = new ModelAndView();
		List<PortletContextVO> list = null;
		PortletManagementVO param = new PortletManagementVO();
		try{
			bind(request, param);
	    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	        MultipartFile file = multipartRequest.getFile("upfile");
	        if(file == null) {
	        	throw new Exception("multipart file is null.");
	        }else{
	        	logger.debug("upload file name is " + UPLOAD_DIR + file.getOriginalFilename());
	        	param.setFileName(file.getOriginalFilename());
	        	param.setFilePath(UPLOAD_DIR + file.getOriginalFilename());
	        	FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(param.getFilePath()));
	        	list = service.loadXmlInfo(param);
	        }
	        //mav.addObject("param", param);
			//mav.addObject("jsonResult", context);

	        ObjectMapper mapper = new ObjectMapper();
	        mapper.writeValue(response.getOutputStream(), list);
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
        	throw e;
        }
		return null;
	}
	
	/**
	 * 포틀릿 관리 목록
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deployStatusInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("deployStatusInfo called...");
		ModelAndView mav = new ModelAndView(deployStatusForm);
		PortletManagementVO param = new PortletManagementVO();
		PortletManagementVO parentSearchParam = new PortletManagementVO();
		GroupPortalVO parentPortalVO = new GroupPortalVO();
		bind(request, param);
		bind(request, parentSearchParam);
		bind(request, parentPortalVO);
		
		String searchKey = "%" + UtilRequest.getString(request,"searchKey") + "%";
		String searchWay = UtilRequest.getString(request,"searchWay");
		param.setSearchKey(searchKey);
		param.setSearchWay(searchWay);
		
		param.setPageSize(LuxorConfig.getEnvInt(request, "PAGE_LIST_COUNT"));
		//부모포탈의 카테고리를 선택하였을 경우
		String parentPortalId = UtilRequest.getString(request, "parentPortalId");
		Page _page = service.deployStatusInfoPage(param);
		if(!parentPortalId.equals("")) {
			parentSearchParam.setPortalId(parentPortalId);
			_page = service.deployStatusInfoPage(parentSearchParam);
			mav.addObject("isParentPortal", parentPortalId);
		} 
		
        TreeVO[] trees = service.getPortletCategoryTree(param);
        
        searchKey = UtilRequest.getString(request,"searchKey");
        searchWay = UtilRequest.getString(request,"searchWay");
		param.setSearchKey(searchKey);
		param.setSearchWay(searchWay);
		
		mav.addObject("CategoryTree", trees);
		mav.addObject("treeId", "CategoryTree");
		
		parentPortalVO = groupPortalService.get(parentPortalVO);
		mav.addObject("currentPortalName", parentPortalVO.getPortalName());
		//부모그룹이 있는지 체크하여 있으면, 부모 페이지 트리도 함께 전달
		for(int i = 0; true ; i++ ) {
			parentPortalVO = groupPortalService.getParentPortal(parentPortalVO);
			if(parentPortalVO == null) {
				mav.addObject("parentPortalCount",0);
				break;
			} else {
				parentSearchParam.setPortalId(parentPortalVO.getPortalId());
				trees = service.getPortletCategoryTree(parentSearchParam);
				mav.addObject("parentTree_"+i, trees);
				mav.addObject("parentTreeId_"+i, "CategoryTree");
				mav.addObject("groupPortal_"+i, parentPortalVO);
				if(parentPortalVO.getParentId().equals("_ROOT_")) {
					mav.addObject("parentPortalCount",i+1);
					break;
				}
			}
		}
		
		TreeVO contentTreeVO = new TreeVO();
		TreeVO pageTreeVO = new TreeVO();
		bind(request, contentTreeVO);
		bind(request, pageTreeVO);
		
		pageTreeVO.setTreeId("PORTAL_PAGE");
		contentTreeVO.setTreeId("PORTAL_CONTENT");
		TreeVO[] contentTree = treeService.getTree(contentTreeVO);
		TreeVO[] pageTree = treeService.getTree(pageTreeVO);
		mav.addObject("contentTree", contentTree);
		mav.addObject("pageTree", pageTree);
		
		mav.addObject("param", param);
		mav.addObject("_page", _page);
		return mav;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getPortletContextInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("getPortletContextInfo called...");
		ModelAndView mav = new ModelAndView();
		PortletManagementVO param = new PortletManagementVO();
		bind(request, param);
        PortletContextVO context = service.getPortletContextInfo(param);
		
        if(context == null) {
        	logger.error("context is null.(" + param.getContextName() + ")");
        	throw new Exception("context is null.(" + param.getContextName() + ")");
        }
        else logger.debug("context.getDescription()=" + context.getDescription());
        
		mav.addObject("param", param);
		mav.addObject("jsonResult", context);
		return mav;
	}
	
	/**
	 * 포틀릿 카테고리 삭제
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deletePortletCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("deletePortletCategory called...");
		ModelAndView mav = new ModelAndView();
		PortletManagementVO param = new PortletManagementVO();
		bind(request, param);
        int r = service.deletePortletCategory(param);
		
        if(r == 0) {
        	logger.error("category delete id failed(" + param.getCategoryId() + ").");
        	throw new Exception("category delete id failed(" + param.getCategoryId() + ").");
        }else{
        	param.setcPage(r);
        }
        
		mav.addObject("param", param);
		mav.addObject("jsonResult", param);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	
	/**
	 * 등록되어 있는 포틀릿을  redeploy
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView redeploy(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("redeploy called...");
		ModelAndView mav = new ModelAndView();
		PortletManagementVO param = new PortletManagementVO();
		bind(request, param);
		service.redeploy(param);
		mav.addObject("param", param);
		return mav;
	}
	
	/**
	 * 등록되어 있는 포틀릿 전부를 redeploy
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView redeployAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("redeployAll called...");
		ModelAndView mav = new ModelAndView();
		PortletManagementVO param = new PortletManagementVO();
		bind(request, param);
		if(!service.redeployAll(param)) {
			param.setException("some portlet redeploy is failed.\n check log file.");
		}
		mav.addObject("param", param);
		mav.addObject("jsonResult", param);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	
	/**
	 * 등록된 포틀릿을 사용 불가 상태로 전환
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView undeploy(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("undeploy called...");
		ModelAndView mav = new ModelAndView();
		PortletManagementVO param = new PortletManagementVO();
		bind(request, param);
		service.undeploy(param);
		mav.addObject("param", param);
		return mav;
	}
	
	/**
	 * 포틀릿을 삭제
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("delete called...");
		ModelAndView mav = new ModelAndView();
		PortletManagementVO param = new PortletManagementVO();
		bind(request, param);
		service.delete(param);
		mav.addObject("param", param);
		return mav;
	}
	
	/**
	 * 카테고리 등록 폼 호출
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView loadCategoryForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("loadCategoryForm called...");
		ModelAndView mav = new ModelAndView(categoryForm);
		PortletManagementVO param = new PortletManagementVO();
		bind(request, param);
		PortletCategoryVO category = null;
		if(!"".equals(param.getCategoryId())){
			category = service.getPortletCategoryInfo(param);
			mav.addObject("category", category);
		}
		mav.addObject("param", param);
		return mav;
	}
	
	/**
	 * 카테고리 등록/수정
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView saveCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("saveCategory called...");
		ModelAndView mav = new ModelAndView();
		PortletCategoryVO param = new PortletCategoryVO();
		bind(request, param);
		
		PortletCategoryVO r = null;
		if("".equals(param.getMessageId())) { // 등록
			r = service.addCategory(param);
			
		}else{ // 수정
			r = service.updateCategory(param);
		}
		
		mav.addObject("param", param);
		mav.addObject("jsonResult", r);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView xmlEditForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("xmlEditForm called...");
		ModelAndView mav = new ModelAndView();
		PortletManagementVO param = new PortletManagementVO();
		bind(request, param);
		
		PortletManagementVO r = service.getDeployDescriptionXML(param);
		
		mav.addObject("param", param);
		mav.addObject("jsonResult", r);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView xmlEditApply(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("xmlEditApply called...");
		ModelAndView mav = new ModelAndView();
		PortletManagementVO param = new PortletManagementVO();
		bind(request, param);
		
		if(!service.updateDeployDescriptionXML(param)) {
			throw new Exception("XML update is failed.");
		}
		
		mav.addObject("param", param);
		mav.addObject("jsonResult", param);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView iframeLoad(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("iframeLoad called...");
		
		PortletManagementVO param = new PortletManagementVO();
		String mode = UtilRequest.getString(request,"mode","view");
		bind(request, param);
		
		PortletContextVO portlet = PortletContextRegistry.getPortlet(param.getPortalId(), param.getTenantId() ,param.getContextName());
		
		ModelAndView mav = new ModelAndView(iframeLoadForm);
		mav.addObject("mode",mode);
		mav.addObject("param", param);
		mav.addObject("portlet", portlet);
		
		return mav;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return helpPageLoadForm
	 * @throws Exception
	 */
	public ModelAndView helpPageLoad(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("helpPageLoad called...");
		PortletManagementVO param = new PortletManagementVO();
		String mode = UtilRequest.getString(request,"mode","view");
		bind(request, param);
		
		PortletContextVO portlet = PortletContextRegistry.getPortlet(param.getPortalId(), param.getTenantId() , param.getContextName());
		
		ModelAndView mav = new ModelAndView(helpPageLoadForm);
		mav.addObject("mode",mode);
		mav.addObject("param", param);
		mav.addObject("portlet", portlet);
		
		return mav;
	}


	/**
	 * 일반 포틀릿 JSP를 컨트롤러를 태워서 호출해 줌
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView genericPortletLoad(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = UtilRequest.getString(request,"url");
		return new ModelAndView(url);
	}

	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView exportXML(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("exportXML called...");
		ModelAndView mav = new ModelAndView(exportForm);
		PortletManagementVO param = new PortletManagementVO();
		bind(request, param);
		String xml = service.exportXML(param);
		param.setOutline(xml);
		mav.addObject("param", param);
		mav.addObject("xml", param);
		return mav;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView portletRegisterXMLConfirm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("registerPortletXMLConfirm called...");
		PortletManagementVO param = new PortletManagementVO();
		bind(request, param);
		
        try{
	        if(!service.portletRegisterXMLConfirm(param)) {
	        	throw new Exception("등록이 실패했습니다. \n 로그를 확인하시기 바랍니다.");
	        }
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
        	throw e;
        }
		
		CommonUtil.redirect(response, "/ep/portlet/deployStatusInfo.do?categoryId=" + param.getCategoryId());
		return null;
	}
	
	/**
	 * 기존에 저장된 정보가 있는지 체크
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView checkDup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("checkDup called...");
		ModelAndView mav = new ModelAndView();
		PortletManagementVO param = new PortletManagementVO();
		bind(request, param);
		String result = service.checkDup(param);
		if(result.equals("modify")) { // 중복
			param.setMessageName("modify");
		} else if(result.equals("success")) { //상위포탈 contextName과 중복
			param.setMessageName("success");
		} else { 
			param.setMessageName(result);
		}
		
		mav.addObject("param", param);
		mav.addObject("jsonResult", param);
		return mav;
	}
	
}
