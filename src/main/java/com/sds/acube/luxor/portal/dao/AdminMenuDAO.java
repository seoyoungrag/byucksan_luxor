package com.sds.acube.luxor.portal.dao;

import org.anyframe.pagination.Page;
import com.sds.acube.luxor.framework.dao.BaseDAO;
import com.sds.acube.luxor.portal.vo.AdminMenuVO;

public class AdminMenuDAO extends BaseDAO {
	public Page getAdminMenuCategoryList(AdminMenuVO vo) throws Exception {
		return selectListPage("getAdminMenuCategoryList", vo);
	}

	public Page getAdminMenuList(AdminMenuVO vo) throws Exception {
		return selectListPage("getAdminMenuList", vo);
	}

	public Page getAdminMenuActive(AdminMenuVO vo) throws Exception {
		return selectListPage("getAdminMenuActive", vo);
	}

	public boolean insertAdminMenu(AdminMenuVO vo) throws Exception {
		return insert("insertAdminMenu", vo) > 0;
	}
	
	public boolean updateAdminMenu(AdminMenuVO vo) throws Exception {
		return update("updateAdminMenu", vo) > 0;
	}

	public boolean unsetHome(AdminMenuVO vo) throws Exception {
		return update("com.sds.acube.luxor.common.dao.unsetHome", vo) > 0;
	}

	public boolean setHome(AdminMenuVO vo) throws Exception {
		return update("com.sds.acube.luxor.common.dao.setHome", vo) > 0;
	}
	
	public boolean deleteAdminMenu(AdminMenuVO vo) throws Exception {
		return delete("deleteAdminMenu", vo) > 0;
	}
	
	public int getChildAdminMenuCount(AdminMenuVO vo) throws Exception {
		return getCount("getChildAdminMenuCount", "cnt", vo);
	}
	
	public AdminMenuVO getAdminMenu(AdminMenuVO vo) throws Exception {
		return (AdminMenuVO)select("getAdminMenu", vo);
	}
}
