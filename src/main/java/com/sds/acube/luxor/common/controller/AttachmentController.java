/*
 * @(#) AttachmentController.java 2010. 8. 9.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.common.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import com.sds.acube.luxor.common.service.AttachmentService;
import com.sds.acube.luxor.common.util.Xinha;
import com.sds.acube.luxor.common.vo.AttachmentVO;
import com.sds.acube.luxor.common.vo.AttachmentVOs;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.controller.BaseController;


/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.2.2.2 $ $Date: 2011/11/29 23:24:51 $
 */
public class AttachmentController extends BaseController {
	
	private AttachmentService service;

	
	/**
	 * @param service The service to set.
	 */
	public void setService(AttachmentService service) {
		this.service = service;
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView apply(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AttachmentVOs param = new AttachmentVOs();
		bind(request, param);
		
		param.setSuccess(service.apply(param));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("param", param);
		mav.addObject("jsonResult", param);
		return mav;
	}
	
	/**
	 * 저장서버에서 파일(들)을 가져와 서버의 Temp경로에 다운로드 함
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView download(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AttachmentVOs param = new AttachmentVOs();
		bind(request, param);
		
		param.setSuccess(service.download(param));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", param);
		return mav;
	}

	/**
	 * 데이터 지움
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AttachmentVOs param = new AttachmentVOs();
		bind(request, param);
		
		param.setSuccess(service.delete(param));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", param);
		return mav;
	}
	
	/**
	 * 저장서버에서 이미지 파일을 가져온 후 스트림으로 뿌려줌
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView imageAsStream(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AttachmentVO param = new AttachmentVO();
		AttachmentVOs params = new AttachmentVOs();
		bind(request, param);
		bind(request, params);
		
		AttachmentVO fileInfo = service.getFileInfo(param);
		boolean result = service.download(params);

		if(result) {
			String tempPath = LuxorConfig.getString("Common", "ATTACH.UPLOAD_TEMP");
			String filePath = tempPath + "/" + fileInfo.getServerFilename();
			
			String contentType = Xinha.getContentType(fileInfo.getClientFilename());

			response.reset();
			response.setHeader("Content-Type", contentType);

		    BufferedInputStream bis = null;
		    BufferedOutputStream bos = null;

			File tf = new File(filePath);
			byte b[] = new byte[1024];

		    try {
				if(tf.isFile()) {
					bis = new BufferedInputStream(new FileInputStream(tf));
					bos = new BufferedOutputStream(response.getOutputStream());
			
					int read = 0;
					while ((read = bis.read(b)) != -1) {
						bos.write(b,0,read);
					}
					bos.flush();
			    }
		    } catch(Exception e) {
		    	e.printStackTrace();
		    } finally {
		    	if(bis!=null) bis.close();
				if(bos!=null) bos.close();		
		    }
		}
		
		return new ModelAndView();
	}
	
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		AttachmentVOs param = new AttachmentVOs();
		bind(request, param);
		
		List<AttachmentVO> list = service.getList(param);
		
		mav.addObject("param", param);
		mav.addObject("jsonResult", list);
		return mav;
	}
}
