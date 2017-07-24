package com.sds.acube.luxor.portal.service;

import org.anyframe.pagination.Page;
import com.sds.acube.luxor.portal.vo.AdminMenuVO;
import com.sds.acube.luxor.portal.vo.AdminUserVO;


public interface AdminService {

	public Page getAdminMenuActive(AdminMenuVO vo) throws Exception;
	
	public Page getAdminMenuCategoryList(AdminMenuVO vo) throws Exception;
	
	public Page getAdminMenuList(AdminMenuVO vo) throws Exception;
	
	public boolean insertAdminMenu(AdminMenuVO vo) throws Exception;
	
	public boolean updateAdminMenu(AdminMenuVO vo) throws Exception;
	
	public boolean setHome(AdminMenuVO vo) throws Exception;
	
	public boolean deleteAdminMenu(AdminMenuVO vo) throws Exception;
	
	public int getChildAdminMenuCount(AdminMenuVO vo) throws Exception;
	
	public AdminMenuVO getAdminMenu(AdminMenuVO vo) throws Exception;
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int checkExistAdminUser(AdminUserVO param) throws Exception;
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Page getAdminUserList(AdminUserVO param) throws Exception;
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public AdminUserVO[] getAdminUserListAll(AdminUserVO param) throws Exception;
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int applyAdminUser(AdminUserVO param) throws Exception;
	
	/**
	 * @param adminUserParam
	 * @return
	 * @throws Exception
	 */
	public AdminUserVO getAdminUser(AdminUserVO param) throws Exception;

	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int deleteAdminUser(AdminUserVO param)throws Exception;
	
	public int setAclIgnoreType(AdminUserVO param)throws Exception;
	
}
