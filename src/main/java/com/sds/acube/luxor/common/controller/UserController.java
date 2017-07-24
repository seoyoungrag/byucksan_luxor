package com.sds.acube.luxor.common.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.anyframe.pagination.Page;
import com.sds.acube.luxor.common.ConstantList;
import com.sds.acube.luxor.common.service.OrgService;
import com.sds.acube.luxor.common.service.UserService;
import com.sds.acube.luxor.common.service.UserSettingService;
import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.util.UtilRequest;
import com.sds.acube.luxor.common.vo.UserBasicVO;
import com.sds.acube.luxor.common.vo.UserProfileVO;
import com.sds.acube.luxor.common.vo.UserSettingVO;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.controller.BaseController;
import com.sds.acube.luxor.portal.service.PortalMenuService;
import com.sds.acube.luxor.portal.service.PortalPageLayoutService;
import com.sds.acube.luxor.portal.service.PortalPageThemeService;
import com.sds.acube.luxor.portal.service.PortalPageZoneService;
import com.sds.acube.luxor.portal.vo.PortalMenuVO;
import com.sds.acube.luxor.portal.vo.PortalPageLayoutVO;
import com.sds.acube.luxor.portal.vo.PortalPageThemeVO;
import com.sds.acube.luxor.portal.vo.PortalPageZoneVO;
import com.sds.acube.luxor.security.EnDecode;
import com.sds.acube.luxor.security.seed.SeedBean;
import com.sds.acube.luxor.ws.client.aclservice.GroupInfo;
import com.sds.acube.luxor.ws.client.aclservice.RoleInfo;
import com.sds.acube.luxor.ws.client.orgservice.Department;
import com.sds.acube.luxor.ws.client.orgservice.Departments;
import com.sds.acube.luxor.ws.client.orgservice.IUser;
import com.sds.acube.luxor.ws.client.orgservice.OrgProvider4WS;
import com.sds.acube.luxor.ws.client.orgservice.PolicyInfo;

public class UserController extends BaseController {
	private UserService userService;
	private OrgService orgService;
	private PortalMenuService portalMenuService;
	private UserSettingService userSettingService;
	private PortalPageThemeService portalPageThemeService;
	private PortalPageLayoutService portalPageLayoutService;
	private PortalPageZoneService portalPageZoneService;
	private String treeView;
	private String userView;
	private String userOne;
	private String userList;
	private String userTree;
	private String aclUser;
	private String roleList;
	private String virtualGroupList;
	private String aclGroupList;
	private String changeUserInformationView;
	private String changePasswordView;
	private String setHomeView;
	private String setTimezoneView;
	private String setThemeView;
	private OrgProvider4WS orgProvider4WS;
	private String serviceKey;
	
	public void setOrgProvider4WS(OrgProvider4WS orgProvider4WS) {
		this.orgProvider4WS = orgProvider4WS;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService; 
	}

	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
		serviceKey = LuxorConfig.getString("IAM.KEY");
	}

	public void setPortalMenuService(PortalMenuService portalMenuService) {
		this.portalMenuService = portalMenuService;
	}
	
	public void setUserSettingService(UserSettingService userSettingService) {
    	this.userSettingService = userSettingService;
    }

	public void setPortalPageThemeService(PortalPageThemeService portalPageThemeService) {
    	this.portalPageThemeService = portalPageThemeService;
    }
	
	public void setPortalPageLayoutService(
			PortalPageLayoutService portalPageLayoutService) {
		this.portalPageLayoutService = portalPageLayoutService;
	}

	public void setPortalPageZoneService(PortalPageZoneService portalPageZoneService) {
		this.portalPageZoneService = portalPageZoneService;
	}

	public void setAclUser(String aclUser) {
		this.aclUser = aclUser;
	}

	public void setUserView(String userView) {
		this.userView = userView;
	}

	public void setUserTree(String userTree) {
		this.userTree = userTree;
	}

	public void setUserOne(String userOne) {
		this.userOne = userOne;
	}
	
	public void setUserList(String userList) {
    	this.userList = userList;
    }

	public void setRoleList(String roleList) {
    	this.roleList = roleList;
    }
	
	public void setVirtualGroupList(String virtualGroupList) {
    	this.virtualGroupList = virtualGroupList;
    }
	
	public void setAclGroupList(String aclGroupList) {
    	this.aclGroupList = aclGroupList;
    }
	
	public void setTreeView(String treeView) {
		this.treeView = treeView;
	}

	public void setSetTimezoneView(String setTimezoneView) {
    	this.setTimezoneView = setTimezoneView;
    }

	public void setChangeUserInformationView(String changeUserInformationView) {
    	this.changeUserInformationView = changeUserInformationView;
    }

	public void setChangePasswordView(String changePasswordView) {
    	this.changePasswordView = changePasswordView;
    }
	
	public void setSetHomeView(String setHomeView) {
    	this.setHomeView = setHomeView;
    }

	public void setSetThemeView(String setThemeView) {
    	this.setThemeView = setThemeView;
    }

	
	// 회원정보 조회
	public ModelAndView getUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		IUser paramVO = new IUser();
		bind(request, paramVO);
		
		IUser resultVO = userService.getUser(paramVO);
		
		ModelAndView mav = new ModelAndView(userView);
		mav.addObject("userView", resultVO);
		return mav;
	}

	// ID로 검색하여 검색결과가 한개만 나올경우의 리스트
	public ModelAndView getOneUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		IUser paramVO = new IUser();
		bind(request, paramVO);
		
		IUser resultVO = userService.getUser(paramVO);
		
		ModelAndView mav = new ModelAndView(userOne);
		mav.addObject("userOne", resultVO);
		return mav;
	}

	public ModelAndView getIUserAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		IUser paramVO = new IUser();
		bind(request, paramVO);
		
		IUser resultVO = userService.getUser(paramVO);
				
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", resultVO);
		return mav;
	}

	public ModelAndView getIUsersAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		IUser paramVO = new IUser();

		ArrayList tempList = new ArrayList();
		String[] userID = UtilRequest.getStringArray(request, "userID");
		for(int i=0; i < userID.length; i++) {
			paramVO.setUserID(userID[i]);
			IUser resultVO = userService.getUser(paramVO);
			tempList.add(resultVO);
		}
		
		IUser[] iusers = new IUser[tempList.size()];
		tempList.toArray(iusers);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", iusers);
		return mav;
	}

	// 회원 목록 조회
	public ModelAndView getUserList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		IUser paramVO = new IUser();
		UserBasicVO userBasicVO = new UserBasicVO();
		
		bind(request, paramVO);
		bind(request, userBasicVO);

		userBasicVO.setPageSize(LuxorConfig.getEnvInt(request, "PAGE_LIST_COUNT"));
		userBasicVO.setSearchWay(UtilRequest.getString(request, "list_type"));
		
		Page page = userService.getUserList(paramVO, userBasicVO);
		
		ModelAndView mav = new ModelAndView(userList);
		mav.addObject("page", page);
		return mav;
	}

	// 사용자/조직 메인 화면 
	public ModelAndView userMng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		IUser paramVO = new IUser();
		bind(request, paramVO);

		int treeType = 0;
		if(UtilRequest.getString(request,"treeType")!=null && !"".equals(UtilRequest.getString(request,"treeType"))){
			treeType = Integer.parseInt(UtilRequest.getString(request,"treeType"));
		}
		
		String userUid = "";		
		Department dept = null;
		UserProfileVO userProfileVO = (UserProfileVO) request.getSession().getAttribute("userProfile");
		
		if(userProfileVO!=null) {
			if(userProfileVO.getUserUid()!=null) {
				userUid = userProfileVO.getUserUid();
			}else {		// 포탈 관리자 설정에서 관리자 추가 시 사용
				userUid = "ADMIN";
				userProfileVO.setUserUid(userUid);
				dept = orgProvider4WS.getRootDepartment(serviceKey);
				treeType = 0;
			}
		}
		paramVO.setUserUID(userUid);
		Departments depts = orgService.getOrgTree(userUid, treeType);
		ModelAndView mav;
		mav = new ModelAndView(treeView);
		mav.addObject("tree", depts);
		mav.addObject("adminTree", dept);	// 포탈 관리자 설정에서 관리자 추가 시 사용
		return mav;
	}

	// 권한설정 조직트리+사용자목록 화면
	public ModelAndView getUserTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		IUser paramVO = new IUser();
		bind(request, paramVO);

		int treeType = 0;
		if(UtilRequest.getString(request,"treeType")!=null && !"".equals(UtilRequest.getString(request,"treeType"))){
			treeType = Integer.parseInt(UtilRequest.getString(request,"treeType"));
		}
		String userUid = "";
		userUid = UtilRequest.getString(request,"userUid");
		paramVO.setUserUID(userUid);
		Departments resultVO = orgService.getOrgTree(userUid, treeType);
		
		ModelAndView mav = new ModelAndView(userTree);
		mav.addObject("userTree", resultVO);
		return mav;
	}

	// 권한설정 사용자정보 조회 화면
	public ModelAndView getAclUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		IUser paramVO = new IUser();
		bind(request, paramVO);
		
		IUser resultVO = userService.getUser(paramVO);
				
		ModelAndView mav = new ModelAndView(aclUser);
		mav.addObject("aclUser", resultVO);
		return mav;
	}

	// 역할 리스트
	public ModelAndView getRoleList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RoleInfo roleInfo = new RoleInfo();
		bind(request, roleInfo);
		
		List rList = userService.getRoleList(roleInfo);
		//해당 회사에서 역할준게 없으면 기본역할을 참조함
		if(rList == null || rList.size() == 0) {
			roleInfo.setCompId("DEFAULT");
			rList = userService.getRoleList(roleInfo);
		}
		
		ModelAndView mav = new ModelAndView(roleList);
		mav.addObject("roleList", rList);
		return mav;
	}

	// 가상그룹 리스트
	public ModelAndView getVirtualGroupList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String searchKey = UtilRequest.getString(request,"searchKey");
		ModelAndView mav = new ModelAndView(virtualGroupList);
		GroupInfo groupInfo = new GroupInfo();
		bind(request,groupInfo);
		List virtualGroup = userService.getVirtualGroupList(groupInfo);
		if(virtualGroup == null || virtualGroup.size() == 2 ) {
			String orgId = orgService.getOrganization(groupInfo.getOrgId()).getOrgParentID();
			groupInfo.setOrgId(orgId);
			virtualGroup = userService.getVirtualGroupList(groupInfo);
		}
		List<GroupInfo> searchedVirtualGroup = new ArrayList<GroupInfo>();
		//검색어에 해당하는 가상그룹 List'
		if(!searchKey.equals("")) {
			for(int i = 0; i < virtualGroup.size() ; i++ ) {
				GroupInfo info = (GroupInfo)virtualGroup.get(i);
				if(info.getGroupName().contains(searchKey)) {
					searchedVirtualGroup.add((GroupInfo)virtualGroup.get(i));
				}
			}
			mav.addObject("virtualGroupList", searchedVirtualGroup);
		} else {
			mav.addObject("virtualGroupList", virtualGroup);
		}
		
		return mav;
	}

	// 권한그룹 리스트
	@Deprecated
	public ModelAndView getAclGroupList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		IUser paramVO = new IUser();
		bind(request, paramVO);

		String compId = "";		
		UserProfileVO userProfileVO = (UserProfileVO) request.getSession().getAttribute("userProfile");
		if(userProfileVO!=null) {
			compId = userProfileVO.getCompId();
		}
		
		List groupList = userService.getAclGroupList(compId);
		
		ModelAndView mav = new ModelAndView(aclGroupList);
		mav.addObject("groupList", groupList);
		return mav;
	}
	

	/**
	 * 사용자 정보수정화면 로딩
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getChangeUserInformationView(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserProfileVO userProfile = (UserProfileVO)request.getSession().getAttribute("userProfile");
		if(userProfile==null) {
			throw new Exception("Invalid Access - You need to login to access this page!!");
		}

		userProfile = userService.getUserByUID(userProfile.getUserUid());
		
		ModelAndView mav = new ModelAndView(changeUserInformationView);
		mav.addObject("userProfile", userProfile);
		return mav;
	}

	
	/**
	 * 사용자 정보수정
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView changeUserInformation(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserProfileVO userProfile = (UserProfileVO)request.getSession().getAttribute("userProfile");
		if(userProfile==null) {
			throw new Exception("Invalid Access - You need to login to access this page!!");
		}
		
        IUser userVO = new IUser();
        bind(request, userVO);
        
		boolean result = userService.changeUserInformation(userVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}

	
	/**
	 * 사용자 비밀번호 변경화면 로딩
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getChangePasswordView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<PolicyInfo> policyInfoList = orgService.getPolicyList(0);
		String isExpired = UtilRequest.getString(request, "isExpired");
		String isNewAccount = UtilRequest.getString(request, "isNewAccount");
		
		ModelAndView mav = new ModelAndView(changePasswordView);
		mav.addObject("policyInfoList", policyInfoList);
		mav.addObject("isExpired", isExpired);
		mav.addObject("isNewAccount", isNewAccount);
		return mav;
	}

	
	/**
	 * 사용자 비밀번호 변경
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView changePassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserProfileVO userProfile = (UserProfileVO)request.getSession().getAttribute("userProfile");
        String userUid = "";
        
        if(userProfile==null) {
        	userUid = UtilRequest.getString(request, "loginId");
		} else {
			userUid = userProfile.getUserUid();
		}
		
        if(userUid.equals("")) {
			throw new Exception("Invalid Access - You need to login to access this page!!");
		}
        
		String oldPassword = UtilRequest.getString(request, "oldPassword");
		String newPassword = UtilRequest.getString(request, "newPassword");
		
		if("N".equals(LuxorConfig.getString("BASIC.USE_HTTPS"))) {
			// Seed Decode
			SeedBean.setRoundKey(request);
			oldPassword = SeedBean.doDecode(request, oldPassword);
			newPassword = SeedBean.doDecode(request, newPassword);
		}

		oldPassword = EnDecode.EncodeBySType(oldPassword);
		newPassword = EnDecode.EncodeBySType(newPassword);
		
		int result = userService.changePassword(userUid, oldPassword, newPassword);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}

	
	/**
	 * 초기화면 설정 화면 로딩
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView setHomeView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserProfileVO userProfile = (UserProfileVO)request.getSession().getAttribute("userProfile");
		if(userProfile==null) {
			throw new Exception("Invalid Access - You need to login to access this page!!");
		}
		
		PortalMenuVO portalMenuVO = new PortalMenuVO();
		bind(request, portalMenuVO);
		
		List list = portalMenuService.getMenus(portalMenuVO);
		
		UserSettingVO userSettingVO = new UserSettingVO();
		bind(request, userSettingVO);
		
		userSettingVO.setUserId(userProfile.getUserUid());
		userSettingVO.setSettingCode(ConstantList.US_CODE_INITIAL_PAGE);
		userSettingVO = userSettingService.getUserSetting(userSettingVO);
		
		PortalMenuVO[] menus = new PortalMenuVO[list.size()];
		list.toArray(menus);

		ModelAndView mav = new ModelAndView(setHomeView);
		mav.addObject("menus", menus);
		mav.addObject("userSetting", userSettingVO);
		return mav;
	}

	
	/**
	 * 초기화면 설정
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView setHome(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserProfileVO userProfile = (UserProfileVO)request.getSession().getAttribute("userProfile");
		if(userProfile==null) {
			throw new Exception("Invalid Access - You need to login to access this page!!");
		}
		
		String pageId = CommonUtil.nullTrim(UtilRequest.getString(request,"pageId"));
		
		UserSettingVO userSettingVO = new UserSettingVO();
		bind(request, userSettingVO);
		
		userSettingVO.setUserId(userProfile.getUserUid());
		userSettingVO.setSettingCode(ConstantList.US_CODE_INITIAL_PAGE);
		userSettingVO.setSettingValue(pageId);
		
		boolean result = false;
		if(userSettingService.getUserSetting(userSettingVO)==null) {
			result = userSettingService.insertUserSetting(userSettingVO);
		} else {
			result = userSettingService.updateUserSetting(userSettingVO);
		}
		
		if(result == true) {
			request.getSession().setAttribute(ConstantList.US_CODE_INITIAL_PAGE, pageId);
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}

	
	/**
	 * 타임존 설정 화면 로딩
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView setTimezoneView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserProfileVO userProfile = (UserProfileVO)request.getSession().getAttribute("userProfile");
		if(userProfile==null) {
			throw new Exception("Invalid Access - You need to login to access this page!!");
		}
		
		UserSettingVO userSettingVO = new UserSettingVO();
		bind(request, userSettingVO);
		
		userSettingVO.setUserId(userProfile.getUserUid());
		userSettingVO.setSettingCode(ConstantList.US_CODE_TIMEZONE);
		userSettingVO = userSettingService.getUserSetting(userSettingVO);
		
		ModelAndView mav = new ModelAndView(setTimezoneView);
		mav.addObject("userSetting", userSettingVO);
		return mav;
	}

	
	/**
	 * 시간대 설정
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView setTimezone(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserProfileVO userProfile = (UserProfileVO)request.getSession().getAttribute("userProfile");
		if(userProfile==null) {
			throw new Exception("Invalid Access - You need to login to access this page!!");
		}
		
		String timezone = CommonUtil.nullTrim(UtilRequest.getString(request,"timezone"));
		
		UserSettingVO userSettingVO = new UserSettingVO();
		bind(request, userSettingVO);
		
		userSettingVO.setUserId(userProfile.getUserUid());
		userSettingVO.setSettingCode(ConstantList.US_CODE_TIMEZONE);
		userSettingVO.setSettingValue(timezone);
		
		boolean result = false;
		if(userSettingService.getUserSetting(userSettingVO)==null) {
			result = userSettingService.insertUserSetting(userSettingVO);
		} else {
			result = userSettingService.updateUserSetting(userSettingVO);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}

	
	/**
	 * 테마 설정 화면 로딩
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView setThemeView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserProfileVO userProfile = (UserProfileVO)request.getSession().getAttribute("userProfile");
		if(userProfile==null) {
			throw new Exception("Invalid Access - You need to login to access this page!!");
		}
		
		UserSettingVO userSettingVO = new UserSettingVO();
		bind(request, userSettingVO);
		
		userSettingVO.setUserId(userProfile.getUserUid());
		userSettingVO.setSettingCode(ConstantList.US_CODE_THEME);
		userSettingVO = userSettingService.getUserSetting(userSettingVO);
		
		// 테마목록
		PortalPageThemeVO themeVO = new PortalPageThemeVO();
		bind(request, themeVO);
		Page page = portalPageThemeService.getPageThemeList(themeVO);
		List _list = (List)page.getList();
		PortalPageThemeVO[] themeList = new PortalPageThemeVO[_list.size()];
		_list.toArray(themeList);
		
		ModelAndView mav = new ModelAndView(setThemeView);
		mav.addObject("userSetting", userSettingVO);
		mav.addObject("themeList", themeList);
		return mav;
	}
	
	
	/**
	 * 개인 테마 설정
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView setTheme(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserProfileVO userProfile = (UserProfileVO)request.getSession().getAttribute("userProfile");
		if(userProfile==null) {
			throw new Exception("Invalid Access - You need to login to access this page!!");
		}
		
		String themeId = CommonUtil.nullTrim(UtilRequest.getString(request,"themeId"));
		
		UserSettingVO userSettingVO = new UserSettingVO();
		bind(request, userSettingVO);
		
		userSettingVO.setUserId(userProfile.getUserUid());
		userSettingVO.setSettingCode(ConstantList.US_CODE_THEME);
		userSettingVO.setSettingValue(themeId);
		
		boolean result = false;
		if(userSettingService.getUserSetting(userSettingVO)==null) {
			result = userSettingService.insertUserSetting(userSettingVO);
		} else {
			result = userSettingService.updateUserSetting(userSettingVO);
		}
		
		// 새로운 테마 세션에 저장
		if(result) {
			request.getSession().setAttribute(ConstantList.US_CODE_THEME, themeId);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}	
	
	
	/**
	 * 개인 레이아웃 설정
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView setLayout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserProfileVO userProfile = (UserProfileVO)request.getSession().getAttribute("userProfile");
		PortalPageZoneVO paramVO = new PortalPageZoneVO();
		PortalPageZoneVO tempVO = new PortalPageZoneVO();
		PortalPageLayoutVO layoutVO = new PortalPageLayoutVO();
		bind(request,layoutVO);
		bind(request,paramVO);
		bind(request,tempVO);
		if(userProfile==null) {
			throw new Exception("Invalid Access - You need to login to access this page!!");
		}
		
		String pageId = CommonUtil.nullTrim(UtilRequest.getString(request,"pageId"));
		String layoutId = CommonUtil.nullTrim(UtilRequest.getString(request,"layoutId"));
		
		layoutVO.setLayoutId(layoutId);
		layoutVO = portalPageLayoutService.getPageLayout(layoutVO);
		paramVO.setPageId(pageId);
		paramVO.setRefer("UM");
		List list = portalPageZoneService.getUnpersonalizedAdminContents(paramVO);
		List<PortalPageZoneVO> currentPageContentlist = portalPageZoneService.getContentsOnPage(paramVO);
		String strLayoutCss = layoutVO.getLayoutCss();
		String[] arrayLayout = strLayoutCss.split(".luxor");
		int returnInteger = 0;
		ModelAndView mav = new ModelAndView();
		
		for(int h = 0 ; h < arrayLayout.length ; h++ ) {
			if((arrayLayout[h].contains("left") || arrayLayout[h].contains("right")) && arrayLayout[h].contains("display") && arrayLayout[h].contains("none")) {
				PortalPageZoneVO[] contents = new PortalPageZoneVO[list.size()];
				list.toArray(contents);
				for(int i=0 ; i < currentPageContentlist.size() ; i++ ) {
					if(currentPageContentlist.get(i).getIsFixed().equals("Y") && arrayLayout[h].contains(currentPageContentlist.get(i).getZoneId())) {
						returnInteger = 1;
						mav.addObject("jsonResult", returnInteger);
						return mav;
					}
				}
			}
		}
		
		
		UserSettingVO userSettingVO = new UserSettingVO();
		bind(request, userSettingVO);
		
		String key = ConstantList.US_CODE_LAYOUT + "_" + pageId;
		
		userSettingVO.setUserId(userProfile.getUserUid());
		userSettingVO.setSettingCode(key);
		userSettingVO.setSettingValue(layoutId);
		
		boolean result = false;
		if(userSettingService.getUserSetting(userSettingVO)==null) {
			result = userSettingService.insertUserSetting(userSettingVO);
		} else {
			result = userSettingService.updateUserSetting(userSettingVO);
		}
		
		if(result == true) {
			returnInteger = 0;
		} else {
			returnInteger = -1;
		}
		
		// 새로운 레이아웃 세션에 저장
		if(result) {
			request.getSession().setAttribute(key, layoutId);
		}
		
		//레이아웃변경이 일어났을때 없어지는 zone이 있는지 체크하여 해당 zone의 컨텐츠를 content영역으로 이동
		if(layoutVO != null) {
			for(int h = 0 ; h < arrayLayout.length ; h++ ) {
				if((arrayLayout[h].contains("left") || arrayLayout[h].contains("right")) && arrayLayout[h].contains("display") && arrayLayout[h].contains("none")) {
					PortalPageZoneVO[] contents = new PortalPageZoneVO[list.size()];
					list.toArray(contents);
					for(int i=0 ; i < currentPageContentlist.size() ; i++ ) {
						paramVO = currentPageContentlist.get(i);
						if(!paramVO.getRegUserId().equals("ADMIN") && arrayLayout[h].contains(paramVO.getZoneId())) {
							portalPageZoneService.deleteContentOnZone(paramVO);
							paramVO.setZoneId("content");
							portalPageZoneService.insertContentOnZone(paramVO);
						}
					}
					for(int j=0 ; j< contents.length ; j++) {
						if(arrayLayout[h].contains(contents[j].getZoneId())) {
							contents[j].setZoneId("content");
							if(!contents[j].getZoneId().equals("header") && !contents[j].getZoneId().equals("footer")) {
								portalPageZoneService.insertContentOnZone(contents[j]);
							}
						} 
					}
				} 
			}
		}
		
		mav.addObject("jsonResult", returnInteger);
		return mav;
	}		
}
