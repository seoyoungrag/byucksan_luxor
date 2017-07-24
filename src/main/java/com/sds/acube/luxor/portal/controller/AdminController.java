package com.sds.acube.luxor.portal.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import org.anyframe.pagination.Page;
import com.sds.acube.luxor.common.ConstantList;
import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.vo.UserProfileVO;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.controller.BaseController;
import com.sds.acube.luxor.portal.service.AdminService;
import com.sds.acube.luxor.portal.vo.AdminMenuVO;
import com.sds.acube.luxor.portal.vo.AdminUserVO;


public class AdminController extends BaseController {
	private AdminService adminService;
	private String adminIndex;
	private String adminMenuManager;
	private String adminMenuList;
	private String adminMenuCategoryList;
	private String adminUserList;
	private String cacheClear;
	
	/**
	 * @param adminUserList The adminUserList to set.
	 */
	public void setAdminUserList(String adminUserList) {
		this.adminUserList = adminUserList;
	}

	/**
	 * @param adminIndex
	 */
	public void setAdminIndex(String adminIndex) {
    	this.adminIndex = adminIndex;
    }

	/**
	 * @param adminService
	 */
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	/**
	 * @param adminMenuList
	 */
	public void setAdminMenuList(String adminMenuList) {
		this.adminMenuList = adminMenuList;
	}

	/**
	 * @param adminMenuCategoryList
	 */
	public void setAdminMenuCategoryList(String adminMenuCategoryList) {
    	this.adminMenuCategoryList = adminMenuCategoryList;
    }
	
	/**
	 * @param adminMenuManager
	 */
	public void setAdminMenuManager(String adminMenuManager) {
    	this.adminMenuManager = adminMenuManager;
    }
	
	public void setCacheClear(String cacheClear) {
		this.cacheClear = cacheClear;
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getAdminIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(adminIndex);
		return mav;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getAdminManager(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(adminMenuManager);
		return mav;
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView getAdminMenus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminMenuVO paramVO = new AdminMenuVO();
		bind(request, paramVO);
		
		paramVO.setPageSize(10000);
		Page page = adminService.getAdminMenuActive(paramVO);
		if(page==null) {
			throw new Exception("목록 조회 실패");
		}
		
		List<AdminMenuVO> list = (List<AdminMenuVO>)page.getList();
		AdminMenuVO[] menus = new AdminMenuVO[list.size()];
		list.toArray(menus);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", menus);
		return mav;
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView getAdminMenuList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminMenuVO paramVO = new AdminMenuVO();
		bind(request, paramVO);
		
		paramVO.setPageSize(LuxorConfig.getEnvInt(request, "PAGE_LIST_COUNT"));
		
		Page page = adminService.getAdminMenuList(paramVO);
		
		if(page==null) {
			throw new Exception("목록 조회 실패");
		}
		
		int totalCnt = page.getTotalCount();
		List<AdminMenuVO> list = (List<AdminMenuVO>)page.getList();
		
		AdminMenuVO[] menus = new AdminMenuVO[list.size()];
		list.toArray(menus);
		
		ModelAndView mav = new ModelAndView(adminMenuList);
		mav.addObject("totalCnt", totalCnt);
		mav.addObject("cPage", page.getCurrentPage());
		mav.addObject("menus", menus);
		return mav;
	}

	
	/**
	 * 카테고리 관리에서 카테고리 목록
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView getAdminMenuCategoryList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminMenuVO paramVO = new AdminMenuVO();
		bind(request, paramVO);

		paramVO.setPageSize(LuxorConfig.getEnvInt(request, "PAGE_LIST_COUNT"));
		
		Page page = adminService.getAdminMenuCategoryList(paramVO);
		
		if(page==null) {
			throw new Exception("목록 조회 실패");
		}
		
		int totalCnt = page.getTotalCount();
		List<AdminMenuVO> list = (List<AdminMenuVO>)page.getList();
		
		AdminMenuVO[] menus = new AdminMenuVO[list.size()];
		list.toArray(menus);
		
		ModelAndView mav = new ModelAndView(adminMenuCategoryList);
		mav.addObject("totalCnt", totalCnt);
		mav.addObject("menus", menus);
		return mav;
	}

	
	/**
	 * 카테고리 목록 전부 가져옴 (메뉴등록 화면에서 카테고리 선택하는 select 박스에 보여주기 위한 것)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView getAdminMenuCategories(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminMenuVO paramVO = new AdminMenuVO();
		bind(request, paramVO);

		paramVO.setPageSize(10000);
		
		Page page = adminService.getAdminMenuCategoryList(paramVO);
		
		if(page==null) {
			throw new Exception("목록 조회 실패");
		}
		
		List<AdminMenuVO> list = (List<AdminMenuVO>)page.getList();
		AdminMenuVO[] menus = new AdminMenuVO[list.size()];
		list.toArray(menus);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", menus);
		return mav;
	}

	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView insertAdminMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminMenuVO paramVO = new AdminMenuVO();
		bind(request, paramVO);
		
		paramVO.setMenuId(CommonUtil.generateId("A"));
		boolean result = adminService.insertAdminMenu(paramVO);
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView updateAdminMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminMenuVO paramVO = new AdminMenuVO();
		bind(request, paramVO);

		// 수정자 UID 셋팅
		UserProfileVO userProfile = (UserProfileVO)WebUtils.getSessionAttribute(request, "userProfile");
		String userId = userProfile.getUserUid();
		paramVO.setModUserId(userId);
		
		boolean result = adminService.updateAdminMenu(paramVO);
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView setHome(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminMenuVO paramVO = new AdminMenuVO();
		bind(request, paramVO);

		boolean result = adminService.setHome(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deleteAdminMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminMenuVO paramVO = new AdminMenuVO();
		bind(request, paramVO);
		
		ModelAndView mav = new ModelAndView();
		
		int child = adminService.getChildAdminMenuCount(paramVO);
		if(child > 0) {
			mav.addObject("jsonResult", "HAS_CHILD");
		} else {
			boolean result = adminService.deleteAdminMenu(paramVO);
			mav.addObject("jsonResult", result);
		}
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}

	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getAdminMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminMenuVO paramVO = new AdminMenuVO();
		bind(request, paramVO);
		
		AdminMenuVO result = adminService.getAdminMenu(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}

	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getAdminUserList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(adminUserList);
		AdminUserVO param = new AdminUserVO();
		bind(request, param);
		
		param.setPageSize(LuxorConfig.getEnvInt(request, "PAGE_LIST_COUNT"));
		
		Page page= adminService.getAdminUserList(param);
		if(!"init".equals(param.getOption()) && page.getTotalCount() > 0){ 
			AdminUserVO adminUser = (AdminUserVO)request.getSession().getAttribute("adminProfile");
			if(adminUser.getAdminType() != ConstantList.GROUP_ADMIN) {
				throw new Exception(adminUser.getUserName() + " is not group portal administrator.");
			}
		}
		
		int cPage = page.getCurrentPage();
		int totalCount = page.getTotalCount();
		List<AdminUserVO> list = (List<AdminUserVO>)page.getList();
		
		AdminUserVO[] userList = new AdminUserVO[list.size()];
		list.toArray(userList);
		
		mav.addObject("cPage", cPage);
		mav.addObject("totalCount", totalCount);
		mav.addObject("userList", userList);
		mav.addObject("param", param);
		
		return mav;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView applyAdminUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminUserVO param = new AdminUserVO();
		bind(request, param);
		
		int cnt = adminService.applyAdminUser(param);
		if(cnt > 0) {
			param.setMessageName("success");
			LuxorConfig.initAdminListCache();
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", param);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	
	public ModelAndView deleteAdminUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminUserVO param = new AdminUserVO();
		bind(request, param);
		
		int cnt = adminService.deleteAdminUser(param);
		if(cnt > 0) {
			param.setMessageName("success");
			LuxorConfig.initAdminListCache();
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", param);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	
	/**
	 * 관리자가 사용자 화면 호출시 권한관리를 무시하고 볼지여부 세팅
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView setAclIgnoreType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminUserVO param = new AdminUserVO();
		bind(request, param);
		
		int cnt = adminService.setAclIgnoreType(param);
		if(cnt > 0) {
			param.setMessageName("success");
			LuxorConfig.initAdminListCache();
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", param);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	
	public ModelAndView cacheClear(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(cacheClear);
		return mav;
	}
}
