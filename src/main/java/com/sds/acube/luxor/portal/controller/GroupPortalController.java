/*
 * @(#) GroupPortalController.java 2010. 7. 22.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.portal.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.anyframe.pagination.Page;

import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.controller.BaseController;
import com.sds.acube.luxor.portal.service.GroupPortalService;
import com.sds.acube.luxor.portal.vo.GroupPortalVO;


/**
 * 
 * @author Alex, Eum
 * @version $Revision: 1.2.2.2 $ $Date: 2011/04/01 08:45:07 $
 */
public class GroupPortalController extends BaseController {
	private GroupPortalService groupPortalService;
	private String portalListForm;
	private String portalRegisterForm;


	/**
	 * @param portalRegisterForm The portalRegisterForm to set.
	 */
	public void setPortalRegisterForm(String portalRegisterForm) {
		this.portalRegisterForm = portalRegisterForm;
	}


	/**
	 * @param portalListForm The portalListForm to set.
	 */
	public void setPortalListForm(String portalListForm) {
		this.portalListForm = portalListForm;
	}


	/**
	 * @param groupPortalService The groupPortalService to set.
	 */
	public void setGroupPortalService(GroupPortalService groupPortalService) {
		this.groupPortalService = groupPortalService;
	}


	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getPortalList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(portalListForm);
		GroupPortalVO param = new GroupPortalVO();
		bind(request, param);
		
		param.setPageSize(LuxorConfig.getEnvInt(request, "PAGE_LIST_COUNT"));
		
		Page page = groupPortalService.getListPage(param);
		
		int cPage = page.getCurrentPage();
		int totalCount = page.getTotalCount();
		List<GroupPortalVO> list = (List<GroupPortalVO>)page.getList();
		
		GroupPortalVO[] portalList = new GroupPortalVO[list.size()];
		list.toArray(portalList);
		
		mav.addObject("cPage", cPage);
		mav.addObject("totalCount", totalCount);
		mav.addObject("param", param);
		mav.addObject("list", portalList);
		return mav;
	}


	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getPortalList4JSON(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		GroupPortalVO param = new GroupPortalVO();
		bind(request, param);
		List<GroupPortalVO> list = groupPortalService.getList(param);
		mav.addObject("param", param);
		mav.addObject("jsonResult", list);
		return mav;
	}


	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getPortalInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		GroupPortalVO param = new GroupPortalVO();
		bind(request, param);
		GroupPortalVO portal = groupPortalService.get(param);
		portal.setPortalList(groupPortalService.getList(param));
		
		mav.addObject("param", param);
		mav.addObject("jsonResult", portal);
		return mav;
	}


	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView changePortal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		GroupPortalVO param = new GroupPortalVO();
		bind(request, param);
		logger.debug("Requested Portal ID =" + param.getPortalId());
		GroupPortalVO r = groupPortalService.get(param);
		request.getSession().setAttribute("PORTAL_ID", r.getPortalId());
		request.getSession().setAttribute("RELATED_COMP_ID", r.getRelatedCompid());
		request.getSession().setAttribute("PORTAL_NAME", r.getPortalName());
		request.getSession().setAttribute("PARENT_ID", r.getParentId());
		request.getSession().setAttribute("RELATED_PORTAL_INFO", r.getRelatedPortalinfo());

		r.setMessageName("success");
		mav.addObject("param", param);
		mav.addObject("jsonResult", r);
		return mav;
	}


	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deletePortal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		GroupPortalVO param = new GroupPortalVO();
		bind(request, param);
		logger.debug("Requested Portal ID =" + param.getPortalId());
		int r = groupPortalService.delete(param);
		if (r > 0) param.setMessageName("success");
		else throw new Exception("deleted is failed");
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
	public ModelAndView setDefaultPortal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		GroupPortalVO param = new GroupPortalVO();
		bind(request, param);
		if (groupPortalService.setDefault(param) <= 0) {
			param.setMessageName("fail");
			throw new Exception("setDefault failed.");
		} else {
			param.setMessageName("success");
			mav.addObject("param", param);
			mav.addObject("jsonResult", param);
			mav.addObject("ADMIN_CHECK", true);
		}
		return mav;
	}


	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView loadRegisterForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(portalRegisterForm);
		GroupPortalVO param = new GroupPortalVO();
		bind(request, param);
		mav.addObject("param", param);
		return mav;
	}


	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView portalRegister(HttpServletRequest request, HttpServletResponse response) throws Exception {
		GroupPortalVO param = new GroupPortalVO();
		bind(request, param);
		boolean result = groupPortalService.portalRegister(param);
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}

	private String childPortalList = "";
	public ModelAndView getPortalHierarchy(HttpServletRequest request, HttpServletResponse response) throws Exception {
		GroupPortalVO param = new GroupPortalVO();
		bind(request, param);
		List<GroupPortalVO> portals = groupPortalService.getList(param);
		for(int i = 0; i < portals.size() ; i++ ) {
			childPortalList = "";
			findChildPortal(portals, portals.get(i).getPortalId());
			portals.get(i).setLastUpdateDate(param.getRegDate() == null ? param.getModDate() : param.getRegDate());
			childPortalList = CommonUtil.nullTrim(childPortalList);
			if(childPortalList.length() > 0) {
				portals.get(i).setChildPortal(childPortalList.substring(0, childPortalList.length()-1));
			} else {
				portals.get(i).setChildPortal(childPortalList);
			}
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", portals);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	
	public ModelAndView setPortalHierarchy(HttpServletRequest request, HttpServletResponse response) throws Exception {
		GroupPortalVO param = new GroupPortalVO();
		bind(request, param);
		groupPortalService.updateChildPortal(param);
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", true);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	
	public void findChildPortal(List<GroupPortalVO> portals, String portalId) {
		
		for(int i = 0; i < portals.size() ; i++ ) {
			if(portals.get(i).getParentId().equals(portalId)) {
				childPortalList += portals.get(i).getPortalId()+",";
				findChildPortal(portals, portals.get(i).getPortalId());
			}
		}
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView checkDuplication(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		GroupPortalVO param = new GroupPortalVO();
		bind(request, param);
		GroupPortalVO portal = groupPortalService.checkDuplication(param);
		mav.addObject("param", param);
		mav.addObject("jsonResult", portal);
		return mav;
	}
}
