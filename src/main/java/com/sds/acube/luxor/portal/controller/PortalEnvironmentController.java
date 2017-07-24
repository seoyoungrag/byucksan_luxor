package com.sds.acube.luxor.portal.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import org.anyframe.pagination.Page;
import com.sds.acube.luxor.common.vo.UserProfileVO;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.controller.BaseController;
import com.sds.acube.luxor.portal.service.PortalEnvironmentService;
import com.sds.acube.luxor.portal.vo.PortalEnvironmentVO;


public class PortalEnvironmentController extends BaseController {
	private PortalEnvironmentService portalEnvironmentService;
	private String envManager;
	
	public void setPortalEnvironmentService(PortalEnvironmentService portalEnvironmentService) {
    	this.portalEnvironmentService = portalEnvironmentService;
    }
	public void setEnvManager(String envManager) {
    	this.envManager = envManager;
    }


	public ModelAndView insertEnvironment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalEnvironmentVO paramVO = new PortalEnvironmentVO();
		bind(request, paramVO);
		boolean result = portalEnvironmentService.insertEnvironment(paramVO);
		
		LuxorConfig.loadEnvironment();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	
	
	public ModelAndView updateEnvironment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalEnvironmentVO paramVO = new PortalEnvironmentVO();
		bind(request, paramVO);

		// 수정자 UID 셋팅
		UserProfileVO userProfile = (UserProfileVO)WebUtils.getSessionAttribute(request, "userProfile");
		String userId = userProfile.getUserUid();
		paramVO.setModUserId(userId);
		
		boolean result = portalEnvironmentService.updateEnvironment(paramVO);
		
		LuxorConfig.loadEnvironment();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	

	public ModelAndView deleteEnvironment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalEnvironmentVO paramVO = new PortalEnvironmentVO();
		bind(request, paramVO);
		
		boolean result = portalEnvironmentService.deleteEnvironment(paramVO);
		
		LuxorConfig.loadEnvironment();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	

	public ModelAndView getEnvironment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalEnvironmentVO paramVO = new PortalEnvironmentVO();
		bind(request, paramVO);
		PortalEnvironmentVO menu = portalEnvironmentService.getEnvironment(paramVO);
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", menu);
		return mav;
	}

	
	public ModelAndView getEnvironments(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalEnvironmentVO paramVO = new PortalEnvironmentVO();
		bind(request, paramVO);
		
		paramVO.setPageSize(LuxorConfig.getEnvInt(request, "PAGE_LIST_COUNT"));
		Page page = portalEnvironmentService.getEnvironments(paramVO);
		
		if(page==null) {
			throw new Exception("Invalid Access - Can't retrieve environments!!");
		}
		
		int cPage = page.getCurrentPage();
		int totalCount = page.getTotalCount();
		
		List<PortalEnvironmentVO> list = (List<PortalEnvironmentVO>)page.getList();
		PortalEnvironmentVO[] envs = new PortalEnvironmentVO[list.size()];
		list.toArray(envs);
		
		ModelAndView mav = new ModelAndView(envManager);
		mav.addObject("cPage", cPage);
		mav.addObject("totalCount", totalCount);
		mav.addObject("envs", envs);
		return mav;
	}

	
}
