package com.sds.acube.luxor.portal.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.anyframe.pagination.Page;
import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.controller.BaseController;
import com.sds.acube.luxor.portal.service.PortalPageLayoutService;
import com.sds.acube.luxor.portal.service.PortalPageService;
import com.sds.acube.luxor.portal.vo.PortalPageLayoutVO;
import com.sds.acube.luxor.portal.vo.PortalPageVO;


public class PortalPageLayoutController extends BaseController {
	private PortalPageService portalPageService;
	private PortalPageLayoutService portalPageLayoutService;
	private String pageLayoutList;
	private String pageLayoutCollection;
	
	public void setPortalPageService(PortalPageService portalPageService) {
    	this.portalPageService = portalPageService;
    }
	public void setPortalPageLayoutService(PortalPageLayoutService portalPageLayoutService) {
		this.portalPageLayoutService = portalPageLayoutService;
	}
	public void setPageLayoutList(String pageLayoutList) {
    	this.pageLayoutList = pageLayoutList;
    }
	public void setPageLayoutCollection(String pageLayoutCollection) {
    	this.pageLayoutCollection = pageLayoutCollection;
    }
	
	
	public ModelAndView insertPageLayout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageLayoutVO paramVO = new PortalPageLayoutVO();
		bind(request, paramVO);
		
		paramVO.setLayoutId(CommonUtil.generateId("L"));
		
		boolean result = portalPageLayoutService.insertPageLayout(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	
	
	public ModelAndView updatePageLayout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageLayoutVO paramVO = new PortalPageLayoutVO();
		bind(request, paramVO);
		
		boolean result = portalPageLayoutService.updatePageLayout(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	
	public ModelAndView setDefaultPageLayout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageLayoutVO paramVO = new PortalPageLayoutVO();
		bind(request, paramVO);
		
		boolean result = portalPageLayoutService.setDefaultPageLayout(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}

	public ModelAndView deletePageLayout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageLayoutVO paramVO = new PortalPageLayoutVO();
		bind(request, paramVO);
		
		boolean result = portalPageLayoutService.deletePageLayout(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}


	public ModelAndView getPageLayout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageLayoutVO paramVO = new PortalPageLayoutVO();
		bind(request, paramVO);
		
		PortalPageLayoutVO resultVO = portalPageLayoutService.getPageLayout(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", resultVO);
		return mav;
	}

	/**
	 * callJson으로 호출
	 *  
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getPageLayoutAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageLayoutVO paramVO = new PortalPageLayoutVO();
		bind(request, paramVO);
		
		PortalPageLayoutVO resultVO = portalPageLayoutService.getPageLayout(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", resultVO);
		return mav;
	}
	
	/**
	 * 레이아웃 관리시 목록
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getPageLayoutList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageLayoutVO paramVO = new PortalPageLayoutVO();
		bind(request, paramVO);
		
		paramVO.setPageSize(LuxorConfig.getEnvInt(request, "PAGE_LIST_COUNT"));
		
		Page page = portalPageLayoutService.getPageLayoutList(paramVO);
		
		if(page==null) {
			throw new Exception("목록 조회 실패");
		}
		
		ModelAndView mav = new ModelAndView(pageLayoutList);
		mav.addObject("page", page);
		return mav;
	}


	/**
	 * 메인 페이지의 트리에서 노드 선택시 Ajax로 호출되어 우측 리스트를 가져옴
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getPageLayoutCollection(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageVO pageVO = new PortalPageVO();
		PortalPageLayoutVO paramVO = new PortalPageLayoutVO();
		
		bind(request, pageVO);
		bind(request, paramVO);
		
		paramVO.setPageSize(LuxorConfig.getEnvInt(request, "PAGE_LIST_COUNT"));
		
		pageVO = portalPageService.getPageForAdmin(pageVO);
		if(pageVO==null) {
			pageVO = new PortalPageVO();
		}
		
		Page page = portalPageLayoutService.getPageLayoutList(paramVO);
		
		List list = (List)page.getList();
		PortalPageLayoutVO[] layouts = new PortalPageLayoutVO[list.size()];
		list.toArray(layouts);

		ModelAndView mav = new ModelAndView(pageLayoutCollection);
		mav.addObject("layouts", layouts);
		mav.addObject("selectedLayoutId", pageVO.getPageLayoutId());
		return mav;
	}

}
