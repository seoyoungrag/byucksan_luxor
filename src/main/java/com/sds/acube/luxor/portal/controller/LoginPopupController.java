package com.sds.acube.luxor.portal.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.anyframe.pagination.Page;
import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.controller.BaseController;
import com.sds.acube.luxor.portal.service.LoginPopupService;
import com.sds.acube.luxor.portal.vo.LoginPopupVO;


public class LoginPopupController extends BaseController {
	private LoginPopupService loginPopupService;
	private String popupList;
	
	
	public void setLoginPopupService(LoginPopupService loginPopupService) {
    	this.loginPopupService = loginPopupService;
    }

	public void setPopupList(String popupList) {
    	this.popupList = popupList;
    }


	public ModelAndView insertPopup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginPopupVO paramVO = new LoginPopupVO();
		bind(request, paramVO);
		
		paramVO.setPopupId(CommonUtil.generateId("P"));
		
		boolean result = false;
		String shortDatePattern = LuxorConfig.getEnvString(request, "DATE_PATTERN_SHORT");
		
		try {
			paramVO.setStartDate(CommonUtil.formatDate(CommonUtil.toDate(paramVO.getStartDate(), shortDatePattern), "yyyyMMdd"));
			paramVO.setEndDate(CommonUtil.formatDate(CommonUtil.toDate(paramVO.getEndDate(), shortDatePattern), "yyyyMMdd"));
			
			result = loginPopupService.insertPopup(paramVO);
		} catch (Exception e) {
			result = false;
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	

	public ModelAndView updatePopup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginPopupVO paramVO = new LoginPopupVO();
		bind(request, paramVO);

		boolean result = false;
		String shortDatePattern = LuxorConfig.getEnvString(request, "DATE_PATTERN_SHORT");

		try {
			paramVO.setStartDate(CommonUtil.formatDate(CommonUtil.toDate(paramVO.getStartDate(), shortDatePattern), "yyyyMMdd"));
			paramVO.setEndDate(CommonUtil.formatDate(CommonUtil.toDate(paramVO.getEndDate(), shortDatePattern), "yyyyMMdd"));
			
			result = loginPopupService.updatePopup(paramVO);
		} catch (Exception e) {
			result = false;
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	
	
	public ModelAndView deletePopup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginPopupVO paramVO = new LoginPopupVO();
		bind(request, paramVO);
		
		boolean result = loginPopupService.deletePopup(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}


	public ModelAndView getPopup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginPopupVO paramVO = new LoginPopupVO();
		bind(request, paramVO);
		
		LoginPopupVO resultVO = loginPopupService.getPopup(paramVO);
		
		if(resultVO==null) {
			throw new Exception("No login popup found!!");
		}
		
		String shortDatePattern = LuxorConfig.getEnvString(request, "DATE_PATTERN_SHORT");
		resultVO.setStartDate(CommonUtil.formatDate(CommonUtil.toDate(resultVO.getStartDate(), "yyyyMMdd"), shortDatePattern));
		resultVO.setEndDate(CommonUtil.formatDate(CommonUtil.toDate(resultVO.getEndDate(), "yyyyMMdd"), shortDatePattern));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", resultVO);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}

	
	public ModelAndView getPopupList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginPopupVO paramVO = new LoginPopupVO();
		bind(request, paramVO);
		
		paramVO.setPageSize(LuxorConfig.getEnvInt(request, "PAGE_LIST_COUNT"));
		
		Page page = loginPopupService.getPopupListPage(paramVO);
		
		if(page==null) {
			throw new Exception("Fail to retreive popup list");
		}
		
		int totalCnt = page.getTotalCount();
		List<LoginPopupVO> list = (List<LoginPopupVO>)page.getList();
		
		LoginPopupVO[] popups = new LoginPopupVO[list.size()];
		list.toArray(popups);
		
		ModelAndView mav = new ModelAndView(popupList);
		mav.addObject("totalCnt", totalCnt);
		mav.addObject("popups", popups);
		mav.addObject("page", page);
		return mav;
	}

}
