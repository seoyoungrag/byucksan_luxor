package com.sds.acube.luxor.portal.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.anyframe.pagination.Page;
import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.controller.BaseController;
import com.sds.acube.luxor.portal.service.LoginPlugService;
import com.sds.acube.luxor.portal.vo.LoginPlugVO;


public class LoginPlugController extends BaseController {
	private LoginPlugService loginPlugService;
	private String plugList;
	
	
	public void setLoginPlugService(LoginPlugService loginPlugService) {
    	this.loginPlugService = loginPlugService;
    }

	public void setPlugList(String plugList) {
    	this.plugList = plugList;
    }


	public ModelAndView insertPlug(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginPlugVO paramVO = new LoginPlugVO();
		bind(request, paramVO);
		
		paramVO.setPlugId(CommonUtil.generateId("P"));
		
		boolean result = false;
		String shortDatePattern = LuxorConfig.getEnvString(request, "DATE_PATTERN_SHORT");
		
		try {
			paramVO.setStartDate(CommonUtil.formatDate(CommonUtil.toDate(paramVO.getStartDate(), shortDatePattern), "yyyyMMdd"));
			paramVO.setEndDate(CommonUtil.formatDate(CommonUtil.toDate(paramVO.getEndDate(), shortDatePattern), "yyyyMMdd"));
			
			result = loginPlugService.insertPlug(paramVO);
		} catch (Exception e) {
			result = false;
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	

	public ModelAndView updatePlug(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginPlugVO paramVO = new LoginPlugVO();
		bind(request, paramVO);

		boolean result = false;
		String shortDatePattern = LuxorConfig.getEnvString(request, "DATE_PATTERN_SHORT");

		try {
			paramVO.setStartDate(CommonUtil.formatDate(CommonUtil.toDate(paramVO.getStartDate(), shortDatePattern), "yyyyMMdd"));
			paramVO.setEndDate(CommonUtil.formatDate(CommonUtil.toDate(paramVO.getEndDate(), shortDatePattern), "yyyyMMdd"));
			
			result = loginPlugService.updatePlug(paramVO);
		} catch (Exception e) {
			result = false;
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	
	
	public ModelAndView deletePlug(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginPlugVO paramVO = new LoginPlugVO();
		bind(request, paramVO);
		
		boolean result = loginPlugService.deletePlug(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}

	public ModelAndView updateActiveInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginPlugVO paramVO = new LoginPlugVO();
		bind(request, paramVO);
		
		boolean result = loginPlugService.updatePlugActiveInfo(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}

	public ModelAndView getPlug(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginPlugVO paramVO = new LoginPlugVO();
		bind(request, paramVO);
		
		LoginPlugVO resultVO = loginPlugService.getPlug(paramVO);
		
		if(resultVO==null) {
			throw new Exception("No login plug found!!");
		}
		
		String shortDatePattern = LuxorConfig.getEnvString(request, "DATE_PATTERN_SHORT");
		resultVO.setStartDate(CommonUtil.formatDate(CommonUtil.toDate(resultVO.getStartDate(), "yyyyMMdd"), shortDatePattern));
		resultVO.setEndDate(CommonUtil.formatDate(CommonUtil.toDate(resultVO.getEndDate(), "yyyyMMdd"), shortDatePattern));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", resultVO);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}

	
	public ModelAndView getPlugList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginPlugVO paramVO = new LoginPlugVO();
		bind(request, paramVO);
		
		paramVO.setPageSize(LuxorConfig.getEnvInt(request, "PAGE_LIST_COUNT"));
		
		Page page = loginPlugService.getPlugListPage(paramVO);
		
		if(page==null) {
			throw new Exception("Fail to retreive plug list");
		}
		
		int totalCnt = page.getTotalCount();
		List<LoginPlugVO> list = (List<LoginPlugVO>)page.getList();
		
		LoginPlugVO[] plugs = new LoginPlugVO[list.size()];
		list.toArray(plugs);
		
		ModelAndView mav = new ModelAndView(plugList);
		mav.addObject("totalCnt", totalCnt);
		mav.addObject("plugs", plugs);
		mav.addObject("page", page);
		return mav;
	}

}
