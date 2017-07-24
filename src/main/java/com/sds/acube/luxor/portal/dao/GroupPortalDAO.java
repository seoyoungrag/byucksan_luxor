/*
 * @(#) AdminUserDAO.java 2010. 7. 21.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.portal.dao;

import java.util.List;
import org.anyframe.pagination.Page;

import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.framework.dao.BaseDAO;
import com.sds.acube.luxor.portal.vo.GroupPortalVO;


/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.2.2.2 $ $Date: 2011/04/01 08:45:07 $
 */
@SuppressWarnings("unchecked")
public class GroupPortalDAO extends BaseDAO {
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<GroupPortalVO> getList(GroupPortalVO param) throws Exception {
		return selectList("com.sds.acube.luxor.common.admin.portalgroup.getList", param);
	}
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Page getListPage(GroupPortalVO param) throws Exception {
		return selectListPage("com.sds.acube.luxor.common.admin.portalgroup.getList", param);
	}
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int update(GroupPortalVO param) throws Exception {
		return update("com.sds.acube.luxor.common.admin.portalgroup.update", param);
	}
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int setDefault(GroupPortalVO param) throws Exception {
		return update("com.sds.acube.luxor.common.admin.portalgroup.setDefault", param);
	}
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int unsetDefault(GroupPortalVO param) throws Exception {
		return update("com.sds.acube.luxor.common.admin.portalgroup.unsetDefault", param);
	}
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public GroupPortalVO get(GroupPortalVO param) throws Exception {
		return (GroupPortalVO)select("com.sds.acube.luxor.common.admin.portalgroup.get", param);
	}
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public GroupPortalVO getDefault(GroupPortalVO param) throws Exception {
		return (GroupPortalVO)select("com.sds.acube.luxor.common.admin.portalgroup.getDefault", param);
	}
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int insert(GroupPortalVO param) throws Exception {
		return insert("com.sds.acube.luxor.common.admin.portalgroup.insert", param);
	}
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int delete(GroupPortalVO param) throws Exception {
		int result = 0;
		result += delete("delete_tlxr_admin_menu", param);
		result += delete("delete_tlxr_admin_user", param);
		result += delete("delete_tlxr_content", param);
		result += delete("delete_tlxr_environment", param);
		result += delete("delete_tlxr_login_history", param);
		result += delete("delete_tlxr_logoutsys", param);
		result += delete("delete_tlxr_menu", param);
		result += delete("delete_tlxr_message", param);
		result += delete("delete_tlxr_page", param);
		result += delete("delete_tlxr_page_layout", param);
		result += delete("delete_tlxr_page_statistics", param);
		result += delete("delete_tlxr_page_theme", param);
		result += delete("delete_tlxr_page_zone", param);
		result += delete("delete_tlxr_portlet_category", param);
		result += delete("delete_tlxr_portlet_context", param);
		result += delete("delete_tlxr_tree", param);
		result += delete("delete_tlxr_login_plug", param);
		result += delete("com.sds.acube.luxor.common.admin.portalgroup.delete", param); 
		return result;
	}
	
	public int deleteDBOnly(GroupPortalVO param) throws Exception {
		return delete("com.sds.acube.luxor.common.admin.portalgroup.delete", param);
	}
	
	
	/**
	 * 신규 포탈 생성시 필요한 기본 데이터 입력
	 * 
	 * @param param
	 * @throws Exception
	 */
	public void portalCopy(GroupPortalVO param) throws Exception {
		insert("insert_tlxr_message", param);
		insert("insert_tlxr_admin_menu", param);
		insert("insert_tlxr_page", param);
		insert("insert_tlxr_page_layout", param);
		insert("insert_tlxr_page_theme", param);
		insert("insert_tlxr_portlet_category", param);
		insert("insert_tlxr_portlet_context", param);
		insert("insert_tlxr_environment", param);
		insert("insert_tlxr_tree", param);
		insert("insert_tlxr_content", param);
		insert("insert_tlxr_login_plug", param);
	}
	
	public GroupPortalVO getParentPortal(GroupPortalVO param) throws Exception {
		return (GroupPortalVO)select("com.sds.acube.luxor.common.admin.portalgroup.getParentPortal", param);
	}
	
	public int updateChildPortal(GroupPortalVO param) throws Exception {
		return update("com.sds.acube.luxor.common.admin.portalgroup.updateChildPortal", param);
	}
	
	public GroupPortalVO checkDuplication(GroupPortalVO param) throws Exception {
		
		param.setPortalId(CommonUtil.getEscapeString(param.getPortalId(), dbType));
		GroupPortalVO result = (GroupPortalVO)select("com.sds.acube.luxor.common.admin.portalgroup.checkDuplication", param);
		param.setPortalId(CommonUtil.getResetEscapeString(param.getPortalId()));		
		return result;
	}
}
