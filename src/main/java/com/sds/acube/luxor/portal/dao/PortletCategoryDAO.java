/*
 * @(#) PortletCategoryDAO.java 2010. 7. 9.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.portal.dao;

import java.util.List;

import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.framework.dao.BaseDAO;
import com.sds.acube.luxor.portal.vo.PortletCategoryVO;
import com.sds.acube.luxor.portal.vo.PortletManagementVO;


/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.2.2.1 $ $Date: 2011/02/10 06:05:30 $
 */
@SuppressWarnings("unchecked")
public class PortletCategoryDAO extends BaseDAO {

	/**
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int insert(PortletCategoryVO vo) throws Exception {
		return insert("com.sds.acube.luxor.portal.portlet.category.insert", vo);
	}
	
	/**
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int update(PortletCategoryVO vo) throws Exception {
		return update("com.sds.acube.luxor.portal.portlet.category.update", vo);
	}
	
	
	/**
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int delete(PortletManagementVO vo) throws Exception {
		return delete("com.sds.acube.luxor.portal.portlet.category.delete", vo);
	}
	
	/**
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public PortletCategoryVO get(PortletManagementVO vo) throws Exception {
		return (PortletCategoryVO)select("com.sds.acube.luxor.portal.portlet.category.get", vo);
	}
	
	/**
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<PortletCategoryVO> getList(PortletManagementVO vo) throws Exception {
		return selectList("com.sds.acube.luxor.portal.portlet.category.getList", vo);
	}
	
	/**
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public PortletCategoryVO getName(PortletManagementVO vo) throws Exception {
		vo.setPortalId(CommonUtil.getEscapeString(vo.getPortalId(), dbType));
		PortletCategoryVO result = (PortletCategoryVO)select("com.sds.acube.luxor.portal.portlet.category.getName", vo);
		vo.setPortalId(CommonUtil.getResetEscapeString(vo.getPortalId()));
		return result;
	}
}
