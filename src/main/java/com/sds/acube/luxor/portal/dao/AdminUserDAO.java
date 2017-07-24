/*
 * @(#) AdminUserDAO.java 2010. 7. 21.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.portal.dao;

import org.anyframe.pagination.Page;
import com.sds.acube.luxor.framework.dao.BaseDAO;
import com.sds.acube.luxor.portal.vo.AdminUserVO;
import java.util.List;
/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.1.4.3 $ $Date: 2011/04/14 00:00:01 $
 */
@SuppressWarnings("unchecked")
public class AdminUserDAO extends BaseDAO {
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Page getList(AdminUserVO param) throws Exception {
		return selectListPage("com.sds.acube.luxor.common.admin.user.getList", param);
	}
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List getListAll(AdminUserVO param) throws Exception {
		return selectList("com.sds.acube.luxor.common.admin.user.getList", param);
	}
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public AdminUserVO get(AdminUserVO param) throws Exception {
		return (AdminUserVO)select("com.sds.acube.luxor.common.admin.user.get", param);
	}
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int insert(AdminUserVO param) throws Exception {
		return insert("com.sds.acube.luxor.common.admin.user.insert", param);
	}
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int delete(AdminUserVO param) throws Exception {
		return delete("com.sds.acube.luxor.common.admin.user.delete", param);
	}
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
		
	public int setAclIgnoreType(AdminUserVO param) throws Exception {
		return update("com.sds.acube.luxor.common.admin.user.setAclIgnoreType", param);
	}
}
