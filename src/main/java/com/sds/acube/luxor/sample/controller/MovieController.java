package com.sds.acube.luxor.sample.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.anyframe.pagination.Page;

import com.sds.acube.luxor.common.util.UtilRequest;
import com.sds.acube.luxor.common.util.Xinha;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.controller.BaseController;
import com.sds.acube.luxor.sample.service.MovieService;
import com.sds.acube.luxor.sample.vo.MovieVO;


public class MovieController extends BaseController {
	private MovieService movieService;
	private String listJSP;
	private String formJSP;
	private String viewJSP;
	
	public void setMovieService(MovieService movieService) {
		this.movieService = movieService;
	}
	public void setListJSP(String listJSP) {
    	this.listJSP = listJSP;
    }
	public void setFormJSP(String formJSP) {
    	this.formJSP = formJSP;
    }
	public void setViewJSP(String viewJSP) {
    	this.viewJSP = viewJSP;
    }
	
	
	public ModelAndView insertMovie(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MovieVO paramVO = new MovieVO();
		bind(request, paramVO);
		
		String releaseDate = UtilRequest.getString(request,"releaseDateString");
		SimpleDateFormat sdf = new SimpleDateFormat(LuxorConfig.getEnvString(request, "DATE_PATTERN_SHORT"));
		Date date = sdf.parse(releaseDate);
		Timestamp timestamp = new Timestamp(date.getTime());
		paramVO.setReleaseDate(timestamp);
		paramVO.setContent(Xinha.encode(paramVO.getContent()));
		
		boolean result = movieService.insertMovie(paramVO);

		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}
	
	
	public ModelAndView updateMovie(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MovieVO paramVO = new MovieVO();
		bind(request, paramVO);
		String releaseDate = UtilRequest.getString(request,"releaseDateString");
		SimpleDateFormat sdf = new SimpleDateFormat(LuxorConfig.getEnvString(request, "DATE_PATTERN_SHORT"));
		Date date = sdf.parse(releaseDate);
		Timestamp timestamp = new Timestamp(date.getTime());
		paramVO.setReleaseDate(timestamp);
		paramVO.setContent(Xinha.encode(paramVO.getContent()));
		
		boolean result = movieService.updateMovie(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}
	

	public ModelAndView deleteMovie(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MovieVO paramVO = new MovieVO();
		bind(request, paramVO);
		
		boolean result = movieService.deleteMovie(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}


	public ModelAndView deleteMovies(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MovieVO paramVO = new MovieVO();
		bind(request, paramVO);
		
		boolean result = false;
		String[] codes = UtilRequest.getStringArray(request,"codes");
		
		for(String code : codes) {
			paramVO.setCode(code);
			result = movieService.deleteMovie(paramVO);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}

	
	public ModelAndView getMovieRegView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView(formJSP);
	}

	
	public ModelAndView getMovieModView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MovieVO paramVO = new MovieVO();
		bind(request, paramVO);
		
		MovieVO resultVO = movieService.getMovie(paramVO);
		
		ModelAndView mav = new ModelAndView(formJSP);
		mav.addObject("movie", resultVO);
		return mav;
	}

	
	public ModelAndView getMovie(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MovieVO paramVO = new MovieVO();
		bind(request, paramVO);
		
		MovieVO resultVO = movieService.getMovie(paramVO);
		
		if(resultVO==null) {
			throw new Exception("내용 조회 실패");
		}
		
		ModelAndView mav = new ModelAndView(viewJSP);
		mav.addObject("movie", resultVO);
		mav.addObject("jsonResult", resultVO);
		return mav;
	}


	public ModelAndView getMovieListPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MovieVO paramVO = new MovieVO();
		bind(request, paramVO);

		paramVO.setPageSize(LuxorConfig.getEnvInt(request, "PAGE_LIST_COUNT"));

		Page page = movieService.getMovieListPage(paramVO);
		
		if(page==null) {
			throw new Exception("목록 조회 실패");
		}
		
		ModelAndView mav = new ModelAndView(listJSP);
		mav.addObject("_page", page);
		return mav;
	}
	

}
