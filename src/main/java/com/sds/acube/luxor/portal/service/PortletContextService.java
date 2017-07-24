/*
 * @(#) PortletManagementService.java 2010. 5. 11.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.portal.service;

import java.util.List;

import com.sds.acube.luxor.portal.vo.PortletContextVO;
import com.sds.acube.luxor.portal.vo.PortletManagementVO;



/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.1.6.1 $ $Date: 2011/02/10 06:05:53 $
 */
public interface PortletContextService {
	
	
	
	/**
	 * @return
	 * @throws Exception
	 */
	public List<PortletContextVO> selectList(PortletManagementVO vo) throws Exception;
	
}
