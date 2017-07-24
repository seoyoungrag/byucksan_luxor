package com.sds.acube.luxor.sample.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.anyframe.pagination.Page;
import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.util.Xinha;
import com.sds.acube.luxor.framework.controller.BaseController;
import com.sds.acube.luxor.sample.service.SampleService;
import com.sds.acube.luxor.sample.vo.SampleVO;


public class SampleController extends BaseController {
	private SampleService sampleService;
	private String listJSP;
	private String viewJSP;
	private String modifyViewJSP;
	
	public void setSampleService(SampleService sampleService) {
		this.sampleService = sampleService;
	}
	public void setListJSP(String listJSP) {
    	this.listJSP = listJSP;
    }
	public void setViewJSP(String viewJSP) {
    	this.viewJSP = viewJSP;
    }
	public void setModifyViewJSP(String modifyViewJSP) {
    	this.modifyViewJSP = modifyViewJSP;
    }
	
	
	public ModelAndView sampleTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SampleVO resultVO = new SampleVO();
		resultVO.setSampleId("12345");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", resultVO);
		return mav;
	}
	
	public ModelAndView insertSample(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SampleVO paramVO = new SampleVO();
		bind(request, paramVO);
		
		paramVO.setSampleId(CommonUtil.generateId("S"));
		paramVO.setContent(Xinha.encode(paramVO.getContent()));
		
		boolean result = sampleService.insertSample(paramVO);

		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}
	
	
	public ModelAndView updateSample(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SampleVO paramVO = new SampleVO();
		bind(request, paramVO);
		
		boolean result = sampleService.updateSample(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}
	

	public ModelAndView deleteSample(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SampleVO paramVO = new SampleVO();
		bind(request, paramVO);
		
		boolean result = sampleService.deleteSample(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}

	public ModelAndView modifyView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SampleVO paramVO = new SampleVO();
		bind(request, paramVO);
		
		SampleVO resultVO = sampleService.getSample(paramVO);
		
		if(resultVO==null) {
			throw new Exception("내용 조회 실패");
		}
		
		ModelAndView mav = new ModelAndView(modifyViewJSP);
		mav.addObject("vo", resultVO);
		return mav;
	}

	public ModelAndView getSample(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SampleVO paramVO = new SampleVO();
		bind(request, paramVO);
		
		SampleVO resultVO = sampleService.getSample(paramVO);
		
		if(resultVO==null) {
			throw new Exception("내용 조회 실패");
		}
		
		ModelAndView mav = new ModelAndView(viewJSP);
		mav.addObject("vo", resultVO);
		return mav;
	}

	
	public ModelAndView getSampleListPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SampleVO paramVO = new SampleVO();
		bind(request, paramVO);
		
		paramVO.setcPage(1);
		paramVO.setPageSize(10);
		
		Page page = sampleService.getSampleListPage(paramVO);
		
		if(page==null) {
			throw new Exception("목록 조회 실패");
		}
		
		int totalCnt = page.getTotalCount();
		
		List<SampleVO> list = (List<SampleVO>)page.getList();

		ModelAndView mav = new ModelAndView(listJSP);
		mav.addObject("totalCnt", totalCnt);
		mav.addObject("list", list);
		return mav;
	}

}
