package com.sds.acube.luxor.common.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.util.UtilRequest;
import com.sds.acube.luxor.common.util.Xinha;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.controller.BaseController;

public class XinhaImageController extends BaseController {
	
	/**
	 * Xinha 에디터에서 이미지 업로드 처리
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView upload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xPrefixID = UtilRequest.getString(request,"prefixid");
		String tempDir = Xinha.generateID("X");
		String tempDir2 = xPrefixID + "/" + tempDir;
		String prefixFolder = LuxorConfig.getString("ATTACH.UPLOAD_TEMP") + "/" + xPrefixID;
		String uploadPath = prefixFolder + "/" + tempDir;
		
		File dir1 = new File(prefixFolder);
		File dir2 = new File(uploadPath);
		
		// Create new prefix directory
		if(!dir1.exists()) {
			dir1.mkdirs();
		}
		if(!dir2.exists()) {
			dir2.mkdirs();
		}

    	File destinationDir = new File(uploadPath);
    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
    	MultipartFile file = multipartRequest.getFile("f_url");
    	String orgFileName =  file.getOriginalFilename();
    	FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(destinationDir + File.separator + orgFileName));
    	
    	// Set View Image URL
    	String imageViewUrl = "/ep/image/xinha/view.do?image_dir="+tempDir2+"&image_file="+orgFileName;
    	CommonUtil.script(response, "parent.uploadDone('"+imageViewUrl+"');");
    	return null;
	}
	
	
	/**
	 * 이미지 보기
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String imageDir = UtilRequest.getString(request,"image_dir");
		String imageFile = UtilRequest.getString(request,"image_file"); 
		String imagePath = LuxorConfig.getString("ATTACH.UPLOAD_TEMP") + "/" + imageDir + "/" + imageFile;
		
		if(imagePath.indexOf("..") > -1) {
			throw new Exception("Invalid path included!!");
		}

		String contentType = Xinha.getContentType(imageFile);

		response.reset();
		response.setHeader("Content-Type", contentType);

	    BufferedInputStream bis = null;
	    BufferedOutputStream bos = null;

		File tf = new File(imagePath);
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

	    return null;
	}
	
	
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String imageDir = UtilRequest.getString(request,"image_dir");

		if(imageDir==null) {
			throw new Exception("image_dir cannot be null!");
		}
		if(imageDir.indexOf("..") > -1) {
			throw new Exception("Invalid path included!!");
		}
		
		Xinha.deleteAll(LuxorConfig.getString("ATTACH.UPLOAD_TEMP") + "/" +imageDir);
		return null;
	}
	
	
}
