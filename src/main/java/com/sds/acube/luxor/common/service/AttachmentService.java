/*
 * @(#) AttachmentService.java 2010. 8. 9.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.common.service;

import java.util.List;

import com.sds.acube.luxor.common.vo.AttachmentVO;
import com.sds.acube.luxor.common.vo.AttachmentVOs;


/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.2.2.4 $ $Date: 2011/12/13 02:02:56 $
 */
public interface AttachmentService {

	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public boolean apply(AttachmentVOs param) throws Exception;
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public boolean download(AttachmentVOs param) throws Exception;

	
	/**
	 * 저장서버에서 파일을 삭제한다(첨부모듈에서의 삭제)
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public boolean delete(AttachmentVOs param) throws Exception;
	
	/**
	 * 저장서버에서 파일을 삭제한다(게시물삭제시 함께 삭제)
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public boolean delete(AttachmentVO param) throws Exception;
	
	/**
	 * 파일정보를 가져온다
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public AttachmentVO getFileInfo(AttachmentVO param) throws Exception;
	
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<AttachmentVO> getList(AttachmentVOs param) throws Exception;

	
}
