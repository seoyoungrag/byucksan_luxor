/*
 * @(#) LogoutSysService.java 2010. 8. 17.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.portal.service;

import java.util.List;

import com.sds.acube.luxor.portal.vo.LogoutSysVO;


/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.1.4.1 $ $Date: 2011/02/10 06:05:53 $
 */
public interface LogoutSysService {

	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int register(LogoutSysVO param) throws Exception;
	
	/**
	 * 수정
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int update(LogoutSysVO param) throws Exception;
	
	/**
	 * 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int delete(LogoutSysVO param) throws Exception;
	
	/**
	 * 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public LogoutSysVO get(LogoutSysVO param) throws Exception;
	
	/**
	 * 목록
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<LogoutSysVO> getList(LogoutSysVO param) throws Exception;
	
	/**
	 * 목록 전체
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<LogoutSysVO> getListAll(LogoutSysVO param) throws Exception;

	/**
	 * 등록/수정
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int apply(LogoutSysVO param) throws Exception;

	/**
	 * 사용으로 설정
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int toEnable(LogoutSysVO param) throws Exception;

	/**
	 * 비사용으로 설정
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int toUnable(LogoutSysVO param) throws Exception;

	/**
	 * 시스템에 등록된 '사용' 상태의 세션종료 url을 호출
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public boolean logoutAll(LogoutSysVO param) throws Exception;
}
