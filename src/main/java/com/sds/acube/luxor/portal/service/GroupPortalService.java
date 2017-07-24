/*
 * @(#) GroupPortalService.java 2010. 7. 22.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.portal.service;

import java.util.List;

import org.anyframe.pagination.Page;
import com.sds.acube.luxor.portal.vo.GroupPortalVO;


/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.1.4.2 $ $Date: 2011/04/01 08:45:06 $
 */
public interface GroupPortalService {
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<GroupPortalVO> getList(GroupPortalVO param) throws Exception;
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Page getListPage(GroupPortalVO param) throws Exception;
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public GroupPortalVO get(GroupPortalVO param) throws Exception;
	
	
	/**
	 * @param param
	 * @throws Exception
	 */
	public int setDefault(GroupPortalVO param) throws Exception;
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public GroupPortalVO getDefault(GroupPortalVO param) throws Exception;
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int delete(GroupPortalVO param) throws Exception;

	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public boolean portalRegister(GroupPortalVO param) throws Exception;
	
	public GroupPortalVO getParentPortal(GroupPortalVO param) throws Exception;
	
	public int updateChildPortal(GroupPortalVO param) throws Exception;
	
	public GroupPortalVO checkDuplication(GroupPortalVO param) throws Exception;
}
