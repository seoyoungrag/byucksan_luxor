/*
 * @(#) PortletContextDAO.java 2010. 5. 18.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.portal.dao;

import java.util.List;
import org.anyframe.pagination.Page;

import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.framework.dao.BaseDAO;
import com.sds.acube.luxor.portal.vo.PortletManagementVO;


/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.2.2.2 $ $Date: 2011/03/17 07:47:26 $
 */
@SuppressWarnings("unchecked")
public class PortletContextDAO extends BaseDAO {
	
	/**
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int insert(PortletManagementVO vo) throws Exception {
		return insert("com.sds.acube.luxor.portal.portlet.context.insert", vo);
	}
	
	/**
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int update(PortletManagementVO vo) throws Exception {
		return update("com.sds.acube.luxor.portal.portlet.context.update", vo);
	}
	
	/**
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int update4undeploy(PortletManagementVO vo) throws Exception {
		return update("com.sds.acube.luxor.portal.portlet.context.update4undeploy", vo);
	}
	
	/**
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int delete(PortletManagementVO vo) throws Exception {
		return delete("com.sds.acube.luxor.portal.portlet.context.delete", vo);
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	public List getList4Deploy(PortletManagementVO vo) throws Exception {
		return selectList("com.sds.acube.luxor.portal.portlet.context.getList4Deploy", vo);
	}
	
	/**
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<PortletManagementVO> getList(PortletManagementVO vo) throws Exception {
		vo.setSearchKey(CommonUtil.getEscapeString(vo.getSearchKey(), dbType));
		List<PortletManagementVO> result =  selectList("com.sds.acube.luxor.portal.portlet.context.getList", vo);
		vo.setSearchKey(CommonUtil.getResetEscapeString(vo.getSearchKey()));
		return result;
	}

	public Page getListPage(PortletManagementVO vo) throws Exception {
		vo.setSearchKey(CommonUtil.getEscapeString(vo.getSearchKey(), dbType));
		Page result = selectListPage("com.sds.acube.luxor.portal.portlet.context.getList", vo);
		vo.setSearchKey(CommonUtil.getResetEscapeString(vo.getSearchKey()));
		
		return result;
	}

	/**
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public PortletManagementVO get(PortletManagementVO vo) throws Exception {
		vo.setPortalId(CommonUtil.getEscapeString(vo.getPortalId(), dbType));
		PortletManagementVO result = (PortletManagementVO)select("com.sds.acube.luxor.portal.portlet.context.get", vo);
		vo.setPortalId(CommonUtil.getResetEscapeString(vo.getPortalId()));
		return result;
	}
	
	/**
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public PortletManagementVO checkDup(PortletManagementVO vo) throws Exception {
		return (PortletManagementVO)select("com.sds.acube.luxor.portal.portlet.context.checkDup", vo);
	}
	
	/**
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int checkExist(PortletManagementVO vo) throws Exception {
		return getCount("com.sds.acube.luxor.portal.portlet.context.checkExist", "CNT", vo);
	}

	/**
	 * @param param
	 * @return
	 */
	public int updateDeployDescriptionXML(PortletManagementVO vo) throws Exception {
		return update("com.sds.acube.luxor.portal.portlet.context.updateDeployDescriptionXML", vo);
	}
	
}
