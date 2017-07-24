package com.sds.acube.luxor.common.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import org.anyframe.pagination.Page;

import com.sds.acube.luxor.common.service.StatisticsService;
import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.util.UtilRequest;
import com.sds.acube.luxor.common.vo.ContentsStatisticsVO;
import com.sds.acube.luxor.common.vo.LoginHistoryVO;
import com.sds.acube.luxor.common.vo.PageStatisticsVO;
import com.sds.acube.luxor.framework.controller.BaseController;

public class StatisticsController extends BaseController {
	private StatisticsService statisticsService;
	private String loginHistoryList;
	private String loginHistory;
	private String pageStatistics;
	private String contentsStatistics;
	private String pageStatisticsList;
	private String contentsStatisticsList;
	
	private String userLoginHistoryMkFile;
	private String pageStatisticsMkFile;
	
	public StatisticsService getStatisticsService() {
		return statisticsService;
	}
	public void setStatisticsService(StatisticsService statisticsService) {
		this.statisticsService = statisticsService;
	}
	public void setUserLoginHistoryMkFile(String userLoginHistoryMkFile) {
    	this.userLoginHistoryMkFile = userLoginHistoryMkFile;
    }
	public void setPageStatisticsMkFile(String pageStatisticsMkFile) {
    	this.pageStatisticsMkFile = pageStatisticsMkFile;
    }
	public void setLoginHistoryList(String loginHistoryList) {
    	this.loginHistoryList = loginHistoryList;
    }
	public void setLoginHistory(String loginHistory) {
    	this.loginHistory = loginHistory;
    }
	public void setPageStatisticsList(String pageStatisticsList) {
    	this.pageStatisticsList = pageStatisticsList;
    }
	public void setContentsStatisticsList(String contentsStatisticsList) {
    	this.contentsStatisticsList = contentsStatisticsList;
    }
	public void setPageStatistics(String pageStatistics) {
    	this.pageStatistics = pageStatistics;
    }
	public void setContentsStatistics(String contentsStatistics) {
    	this.contentsStatistics = contentsStatistics;
    }
	
	/**
	 * 개인별 접속 정보 메인 화면
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getLoginHistory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(loginHistory);
		return mav;
	}

	/**
	 * 개인별 접속 정보 리스트 가져옴
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getLoginHistoryList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LoginHistoryVO paramVO = new LoginHistoryVO();
		bind(request, paramVO);

		Page page = statisticsService.getLoginHistoryList(paramVO);
		
		ModelAndView mav = new ModelAndView(loginHistoryList);
		mav.addObject("page", page);
		return mav;
	}

	// 개인별 접속 정보 리스트 삭제
	public ModelAndView deleteLoginHistory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginHistoryVO paramVO = new LoginHistoryVO();
		bind(request, paramVO);
		
		boolean result = false;
		String[] ids = paramVO.getStatisticsIds();
		for(String id : ids) {
			paramVO.setStatisticsId(id);
			result = statisticsService.deleteLoginHistory(paramVO);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}

	// 개인별 접속 정보 리스트 전체삭제
	public ModelAndView deleteLoginHistoryAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginHistoryVO paramVO = new LoginHistoryVO();
		bind(request, paramVO);
		boolean result = statisticsService.deleteLoginHistoryAll(paramVO);
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}

	/**
	 * 페이지 사용 통계 메인 화면
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getPageStatistics(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(pageStatistics);
		return mav;
	}
	
	// 개인별 page 정보 리스트 삭제
	public ModelAndView deletePageStatistics(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PageStatisticsVO paramVO = new PageStatisticsVO();
		bind(request, paramVO);
		
		boolean result = false;
		String[] ids = paramVO.getStatisticsIds();
		for(String id : ids) {
			paramVO.setStatisticsId(id);
			result = statisticsService.deletePageStatistics(paramVO);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}

	// 개인별 page 정보 리스트 전체삭제
	public ModelAndView deletePageStatisticsAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PageStatisticsVO paramVO = new PageStatisticsVO();
		bind(request, paramVO);
		boolean result = statisticsService.deletePageStatisticsAll(paramVO);
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}
	
	/**
	 * 페이지 사용 통계 목록
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getPageStatisticsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PageStatisticsVO paramVO = new PageStatisticsVO();
		bind(request, paramVO);

		Page page = statisticsService.getPageStatisticsList(paramVO);
		
		ModelAndView mav = new ModelAndView(pageStatisticsList);
		mav.addObject("page", page);
		return mav;
	}

	/**
	 * 컨텐츠 사용 통계 메인 화면
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getContentsStatistics(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(contentsStatistics);
		return mav;
	}

	/**
	 * 컨텐츠 사용 통계 목록
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getContentsStatisticsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ContentsStatisticsVO paramVO = new ContentsStatisticsVO();
		bind(request, paramVO);

		Page page = statisticsService.getContentsStatisticsList(paramVO);
		
		ModelAndView mav = new ModelAndView(contentsStatisticsList);
		mav.addObject("page", page);
		return mav;
	}
	
	// mkFile
	public ModelAndView getPageStatisticsMkFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PageStatisticsVO paramVO = new PageStatisticsVO();
		bind(request, paramVO);
		
		List<PageStatisticsVO> list = statisticsService.getPageStatisticsListWithoutPaging(paramVO);
		
		ModelAndView mav = new ModelAndView(pageStatisticsMkFile);
		mav.addObject("arg1", UtilRequest.getString(request, "arg1"));
		mav.addObject("arg2", UtilRequest.getString(request, "arg2"));
		mav.addObject("arg3", UtilRequest.getString(request, "arg3"));
		mav.addObject("arg4", UtilRequest.getString(request, "arg4"));
		mav.addObject("arg5", UtilRequest.getString(request, "arg5"));
		mav.addObject("arg6", UtilRequest.getString(request, "arg6"));
		mav.addObject("list", list);
		return mav;
		
	}
	
	public ModelAndView getLoginHistoryMkFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginHistoryVO paramVO = new LoginHistoryVO();
		bind(request, paramVO);
		
		List<LoginHistoryVO> list = statisticsService.getLoginHistoryListWithoutPaging(paramVO);
		
		ModelAndView mav = new ModelAndView(userLoginHistoryMkFile);
		mav.addObject("arg1", UtilRequest.getString(request, "arg1"));
		mav.addObject("arg2", UtilRequest.getString(request, "arg2"));
		mav.addObject("arg3", UtilRequest.getString(request, "arg3"));
		mav.addObject("arg4", UtilRequest.getString(request, "arg4"));
		mav.addObject("arg5", UtilRequest.getString(request, "arg5"));
		mav.addObject("arg6", UtilRequest.getString(request, "arg6"));
		mav.addObject("list", list);
		return mav;
	}
	
}
