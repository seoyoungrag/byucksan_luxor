/*
 * @(#) LoginController.java 2010. 5. 25.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.common.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import org.apache.commons.lang.StringUtils;

import com.sds.acube.luxor.security.AESUtil;
import com.sds.acube.luxor.security.EnDecode;
import com.sds.acube.luxor.security.UtilSSO;
import com.sds.acube.luxor.common.util.DateUtil;
import com.sds.acube.luxor.common.ConstantList;
import com.sds.acube.luxor.common.service.AccessControllService;
import com.sds.acube.luxor.common.service.LoginService;
import com.sds.acube.luxor.common.service.MemberRequestService;
import com.sds.acube.luxor.common.service.OrgService;
import com.sds.acube.luxor.common.service.UserSettingService;
import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.util.UtilCookie;
import com.sds.acube.luxor.common.util.UtilRequest;
import com.sds.acube.luxor.common.vo.LoginHistoryVO;
import com.sds.acube.luxor.common.vo.LoginVO;
import com.sds.acube.luxor.common.vo.MemberRequestVO;
import com.sds.acube.luxor.common.vo.UserProfileVO;
import com.sds.acube.luxor.common.vo.UserSettingVO;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.controller.BaseController;
import com.sds.acube.luxor.portal.service.AdminService;
import com.sds.acube.luxor.portal.service.GroupPortalService;
import com.sds.acube.luxor.portal.service.LoginPlugService;
import com.sds.acube.luxor.portal.service.LoginPopupService;
import com.sds.acube.luxor.portal.service.LogoutSysService;
import com.sds.acube.luxor.portal.vo.AdminUserVO;
import com.sds.acube.luxor.portal.vo.GroupPortalVO;
import com.sds.acube.luxor.portal.vo.LoginPlugVO;
import com.sds.acube.luxor.portal.vo.LoginPopupVO;
import com.sds.acube.luxor.portal.vo.LogoutSysVO;
import com.sds.acube.luxor.portal.vo.UserPortalVO;
import com.sds.acube.luxor.security.seed.SeedBean;
import com.sds.acube.luxor.ws.client.orgservice.IUser;
import com.sds.acube.luxor.ws.client.orgservice.IUsers;

/**
 * @author Alex, Eum
 * @version $Revision: 1.3.2.3 $ $Date: 2011/06/03 01:34:18 $
 */
public class LoginController extends BaseController {
	private LoginService service;
	private AdminService adminService;
	private GroupPortalService groupPortalService;
	private LogoutSysService logoutSysService;
	private AccessControllService accessControllService;
	private UserSettingService userSettingService;
	private LoginPopupService loginPopupService;
	private LoginPlugService loginPlugService;
	private MemberRequestService memberRequestService;
	private OrgService orgService;
	private String commonLogin; 

	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}
	
	public void setLogoutSysService(LogoutSysService logoutSysService) {
		this.logoutSysService = logoutSysService;
	}

	public void setGroupPortalService(GroupPortalService groupPortalService) {
		this.groupPortalService = groupPortalService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public void setAccessControllService(AccessControllService accessControllService) {
		this.accessControllService = accessControllService;
	}

	public void setUserSettingService(UserSettingService userSettingService) {
    	this.userSettingService = userSettingService;
    }

	public void setLoginPopupService(LoginPopupService loginPopupService) {
    	this.loginPopupService = loginPopupService;
    }

	public void setLoginPlugService(LoginPlugService loginPlugService) {
		this.loginPlugService = loginPlugService;
	}

	public void setMemberRequestService(MemberRequestService memberRequestService) {
		this.memberRequestService = memberRequestService;
	}

	public void setCommonLogin(String commonLogin) {
		this.commonLogin = commonLogin;
	}

	/**
	 * @param service The service to set.
	 */
	public void setService(LoginService service) {
		this.service = service;
	}
	
	/**
	 * 로그인 이전 프로퍼티 불러오기 (다국어 지원 세팅)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView prepareLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("prepare Login called...");
		
		List<String> locale = LuxorConfig.getList("Common", "LOCALE");
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", locale);
		return mav;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView adminLoginProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("adminLoginProcess called...");
		
		LoginVO param = new LoginVO();
		bind(request, param);
		param.setLoginIp(request.getRemoteAddr());

		
		if("N".equals(LuxorConfig.getString("BASIC.USE_HTTPS"))) {
			if("N".equals(LuxorConfig.getString("BASIC.USE_AES"))){
				// Seed Decode
				SeedBean.setRoundKey(request);
				param.setLoginId(SeedBean.doDecode(request, param.getLoginId()));
				param.setPassword(SeedBean.doDecode(request, param.getPassword()));
			}else{
				
				//AES Decode
				String decid = AESUtil.decrypt(request, param.getXid());// GUID + login id + TIMESTAMP
				String decpw = AESUtil.decrypt(request, param.getXpw());// GUID + login password + TIMESTAMP
				String dectm = AESUtil.decrypt(request, param.getXtm());// GUID + TIMESTAMP
				String decidarr[] = StringUtils.split(decid, "-");
				String decpwarr[] = StringUtils.split(decpw, "-");
				String dectmarr[] = StringUtils.split(dectm, "-");				

                decid = "";
                decpw ="";
                for(int i=1;i<decidarr.length-1;i++){
                  decid += decidarr[i] + "-";
                }
                decid = decid.substring(0, decid.length()-1);
                for(int i=1;i<decpwarr.length-1;i++){
                  decpw += decpwarr[i];
                  if(i!=decpwarr.length-2){
                    decpw += "-";
                  }
                }
                //decid = decidarr[1]; 
                //decpw = decpwarr[1]; 
                String chktm = decidarr[decidarr.length-1];
				dectm = dectmarr[1];
				boolean scc = false;				
				
				if (org.springframework.util.StringUtils.hasText(decid) && org.springframework.util.StringUtils.hasText(decpw)) { // 아이디, 비밀번호 있을때
					if (org.springframework.util.StringUtils.hasText(dectm) && org.springframework.util.StringUtils.hasText(chktm)) { // 타임스탬프 파트와 비교 타임스탬프 있을때
						if (dectm.equals(chktm)) {
							scc = true;
						} else {
							logger.error("*** Compare Login Timestamp({}) and Check Timestamp({}) failed. !!!");
						}
					} else {
						logger.error("*** Fail Login Info decryption!!!");					
					}
					if (scc) {
						param.setLoginId(decid);	
						//param.setPassword(decpw);
						param.setPassword(param.getPassword());
					} else {
						param.setPassword("");
					}
				}				
				
			}
		}

		UserProfileVO userProfile = null;
		String nextURI = UtilRequest.getString(request, "nextURI");		
		if("".equals(param.getPassword())){
			userProfile = new UserProfileVO();
			userProfile.setLoginResult(ConstantList.LOGIN_FAIL_DECRYPT_ERROR);// 로그인 정보 복호화 실패에 따른 로그인 실패(현재는 "로그인중 오류가 발생했습니다" 에 묶어서 처리)
		}else{
		
			userProfile = service.loginProcess(param);
			
			AdminUserVO adminUserParam = new AdminUserVO();
			adminUserParam.setUserId(param.getLoginId());
			adminUserParam.setTenantId(param.getTenantId());
			AdminUserVO adminUser = adminService.getAdminUser(adminUserParam);
			
			// 로그인 실패시
			if (userProfile.getLoginResult()!=ConstantList.LOGIN_SUCCESS && userProfile.getLoginResult()!=ConstantList.LOGIN_FAIL_EXPIRED_PASSWORD) {
				logger.info("login failed!!!");
			} else if (adminUser == null) {	//로그인 성공했으나 admin이 아닐 경우
				logger.info("Permission Denied! " + param.getLoginId() + " is not administrator!");
				userProfile.setLoginResult(ConstantList.LOGIN_FAIL_AUTHORIZATION);
			} else {
				if (ConstantList.ROOT_PORTAL_ID.equals(adminUser.getPortalId())) {
					adminUser.setAdminType(ConstantList.GROUP_ADMIN);
				}
				
				GroupPortalVO portalVO = new GroupPortalVO();
				portalVO.setTenantId(adminUser.getTenantId());
				portalVO.setPortalId(adminUser.getPortalId());
				
				GroupPortalVO r = null;
				if (ConstantList.ROOT_PORTAL_ID.equals(adminUser.getPortalId())) {
					r = groupPortalService.getDefault(portalVO); // default portal
				} else {
					r = groupPortalService.get(portalVO);
				}
		
				// 다국어 설정
				if (CommonUtil.isNullOrEmpty(param.getLanguage())) {
					param.setLanguage("ko");
				}
				
				HttpSession session = request.getSession();
				session.setAttribute("userProfile", userProfile);
				session.setAttribute("adminProfile", adminUser);
				session.setAttribute("COMP_ID", userProfile.getCompId()); // 사용자 소속 회사 아이디
				session.setAttribute("RELATED_COMP_ID", r.getRelatedCompid()); // 포탈에 연결된 회사 아이디
				session.setAttribute("RELATED_PORTAL_INFO", r.getRelatedPortalinfo()); // 타시스템과 연결할 포털정보
				session.setAttribute("PARENT_ID", r.getParentId()); //상위포탈id
				session.setAttribute("PORTAL_ID", r.getPortalId());
				session.setAttribute("PORTAL_NAME", r.getPortalName());
				session.setAttribute("ACCESS_ID_LIST", getAccessIdList(request));
				session.setAttribute("LANG_TYPE", new Locale(param.getLanguage()));
				session.setAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE", new Locale(param.getLanguage()));
			}
			
		}
		
		if("N".equals(LuxorConfig.getString("BASIC.USE_HTTPS")) && "Y".equals(LuxorConfig.getString("BASIC.USE_AES"))){
			String loginEncResult = AESUtil.encrypt(request, Integer.toString(userProfile.getLoginResult()) + "-" + DateUtil.getCurrentDate());
			userProfile.setLoginEncResult(loginEncResult);
		}
		
		ModelAndView mav = new ModelAndView(nextURI);
		mav.addObject("jsonResult", userProfile);
		mav.addObject("userProfile", userProfile);
		return mav;
	}


	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView loginProcessByPortlet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("loginProcessByPortlet called...");
		
		LoginVO param = new LoginVO();
		bind(request, param);
 		param.setLoginIp(request.getRemoteAddr());
 	    
		if("N".equals(LuxorConfig.getString("BASIC.USE_HTTPS"))) {
			if("N".equals(LuxorConfig.getString("BASIC.USE_AES"))){
				// Seed Decode
				SeedBean.setRoundKey(request);
				param.setLoginId(SeedBean.doDecode(request, param.getLoginId()));
				param.setPassword(SeedBean.doDecode(request, param.getPassword()));
			}else{
				
				//AES Decode
				String decid = AESUtil.decrypt(request, param.getXid());// GUID + login id + TIMESTAMP
				String decpw = AESUtil.decrypt(request, param.getXpw());// GUID + login password + TIMESTAMP
				String dectm = AESUtil.decrypt(request, param.getXtm());// GUID + TIMESTAMP
				String decidarr[] = StringUtils.split(decid, "-");
				String decpwarr[] = StringUtils.split(decpw, "-");
				String dectmarr[] = StringUtils.split(dectm, "-");				
				
				decid = "";
                decpw ="";
				for(int i=1;i<decidarr.length-1;i++){
				  decid += decidarr[i] + "-";
				}
                decid = decid.substring(0, decid.length()-1);
				for(int i=1;i<decpwarr.length-1;i++){
				  decpw += decpwarr[i];
                  if(i!=decpwarr.length-2){
                    decpw += "-";
                  }
                }
				//decid = decidarr[1]; 
				//decpw = decpwarr[1]; 
				String chktm = decidarr[decidarr.length-1];
				dectm = dectmarr[1];
				boolean scc = false;				
				
				if (org.springframework.util.StringUtils.hasText(decid) && org.springframework.util.StringUtils.hasText(decpw)) { // 아이디, 비밀번호 있을때
					if (org.springframework.util.StringUtils.hasText(dectm) && org.springframework.util.StringUtils.hasText(chktm)) { // 타임스탬프 파트와 비교 타임스탬프 있을때
						if (dectm.equals(chktm)) {
							scc = true;
						} else {
							logger.error("*** Compare Login Timestamp({}) and Check Timestamp({}) failed. !!!");
						}
					} else {
						logger.error("*** Fail Login Info decryption!!!");					
					}
					if (scc) {
						param.setLoginId(decid);	
						param.setPassword(decpw);
					} else {
						param.setPassword("");
					}
				}				
				
			}
		}

		UserProfileVO userProfile = null;
		String nextURI = UtilRequest.getString(request, "nextURI");		

		if("".equals(param.getPassword())){
			userProfile = new UserProfileVO();
			userProfile.setLoginResult(ConstantList.LOGIN_FAIL_DECRYPT_ERROR);// 로그인 정보 복호화 실패에 따른 로그인 실패(현재는 "로그인중 오류가 발생했습니다" 에 묶어서 처리)
		}else{
			// 비밀번호에 대한 내부 암호화는 service에서 웹서비스 호출 직전에 함 
		    param.setRequest(request);
			userProfile = service.loginProcess(param);		
			
			HttpSession session = request.getSession();
			if (userProfile.getLoginResult() == ConstantList.LOGIN_SUCCESS || userProfile.getLoginResult() == ConstantList.LOGIN_FAIL_EXPIRED_PASSWORD) {
				session.setAttribute("userProfile", userProfile);
				
				GroupPortalVO portalVO = new GroupPortalVO();
				
				portalVO.setTenantId(param.getTenantId());
				
				List<GroupPortalVO> portalList = groupPortalService.getList(portalVO);
				//원직자 소속인 포탈을 제외한 나머지 그룹포탈 -> 겸직자가 해당 포탈에 소속인지 재차검증하기 위함 
				List<GroupPortalVO> extraPortalList = new ArrayList<GroupPortalVO>();
				List<UserPortalVO> userPortalList = new ArrayList<UserPortalVO>();
				UserPortalVO userPortalVO = new UserPortalVO();
				
				//포탈아이디 초기화
	        	session.setAttribute("PORTAL_ID",null);
	
	        	//연결조직 아이디가 회사,부서,역할로 매핑되있는 포탈을 찾아서 세팅 
				//주의! role, dept, compId 가 unique하게 IAM에서 설정되어있어야 side effect 나지 않음.
				List AccessIdList = getAccessIdList(request);
	        	for(int j=0; j < portalList.size(); j++) {
	        		userPortalVO = new UserPortalVO();
	    			userPortalVO.setPortalId(portalList.get(j).getPortalId());
	    			userPortalVO.setTenantId(portalList.get(j).getTenantId());
	    			userPortalVO.setPortalName(portalList.get(j).getPortalName());
	    			userPortalVO.setConUserUID(userProfile.getUserUid());
	    			String portalId = portalList.get(j).getPortalId();
	
	    			if(accessControllService.getPermission(portalId, AccessIdList).isReadable()){
	    				userPortalList.add(userPortalVO);
	    				if(portalList.get(j).getDefaultFlag() == 1){
	    					//session.setAttribute("PORTAL_ID", userPortalList.get(0).getPortalId()); 이게 무슨 구문인지는 잘 모르겠지만 portal_Id는 사용자의 comp_id와 맞춰준다.
	    				}
	    			}else{
	        			extraPortalList.add(portalList.get(j));
	    			}
	    			
	    			/* 
	        		if(userProfile.getCompId().equals(portalList.get(j).getRelatedCompid())) {
	        			userPortalVO.setCompId(portalList.get(j).getRelatedCompid());
	        			userPortalList.add(userPortalVO);
	        		}else if(userProfile.getDeptId().equals(portalList.get(j).getRelatedCompid())) {
	        			userPortalVO.setDeptId(portalList.get(j).getRelatedCompid());
	        			userPortalList.add(userPortalVO);
	        		} else if(userProfile.getRoleCodes().contains(portalList.get(j).getRelatedCompid())) {
	        			userPortalVO.setRoleId(portalList.get(j).getRelatedCompid());
	        			userPortalList.add(userPortalVO);
	        		} else {
	           			extraPortalList.add(portalList.get(j));
	        		}*/
		      	}
	        	
	        	session.setAttribute("PORTAL_ID", userProfile.getPortalId());  
	        	session.setAttribute("TENANT_ID", userProfile.getTenantId());  
	
	        	//겸직자 정보를 받아와서 포탈에 속해있는지 검사
	        	IUsers users = orgService.getConcurrentUsersByID(userProfile.getLoginId());
	        	List<IUser> userList = users.getUserList();
	        	for(int j=0; j < extraPortalList.size(); j++) {
	        		for(int i=0; i < userList.size(); i++) {
	        			userPortalVO = new UserPortalVO();
		    			userPortalVO.setPortalId(extraPortalList.get(j).getPortalId());
		    			userPortalVO.setTenantId(extraPortalList.get(j).getTenantId());
		    			userPortalVO.setPortalName(extraPortalList.get(j).getPortalName());
		    			//겸직자 정보를 세션에 넣기 위해 세팅
		    			userPortalVO.setConUserUID(userList.get(i).getUserUID());
		    			
		    			if(accessControllService.getPermission(extraPortalList.get(j).getPortalId(), AccessIdList).isReadable()){
		    				userPortalList.add(userPortalVO);
		    				if(CommonUtil.isNullOrEmpty((String)session.getAttribute("PORTAL_ID")) && extraPortalList.get(j).getDefaultFlag() == 1){
		    					session.setAttribute("PORTAL_ID", userPortalList.get(0).getPortalId());
	
		    				}
		    			}
		    			
		    			/*	    			
		        		if(userList.get(i).getCompID().equals(extraPortalList.get(j).getRelatedCompid())) {
		        			userPortalVO.setCompId(extraPortalList.get(j).getRelatedCompid());
		        			userPortalList.add(userPortalVO);
		        		} else if(userList.get(i).getDeptID().equals(extraPortalList.get(j).getRelatedCompid())) {
		        			userPortalVO.setDeptId(extraPortalList.get(j).getRelatedCompid());
		        			userPortalList.add(userPortalVO);
		        		} else if(userList.get(i).getRoleCodes().contains(extraPortalList.get(j).getRelatedCompid())) {
		        			userPortalVO.setRoleId(extraPortalList.get(j).getRelatedCompid());
		        			userPortalList.add(userPortalVO);
		        		}*/
	        		}
		      	}
	
	
	        	session.setAttribute("JOIN_PORTAL_IDS", userPortalList);
	        	if(CommonUtil.isNullOrEmpty((String)session.getAttribute("PORTAL_ID")) && userPortalList.size() > 0){
					session.setAttribute("PORTAL_ID", userPortalList.get(0).getPortalId());
	        	}
	
				// 관리자인지 체크
				AdminUserVO adminUserParam = new AdminUserVO();
				adminUserParam.setUserId(param.getLoginId());
				adminUserParam.setTenantId(param.getTenantId());
				AdminUserVO adminUser = adminService.getAdminUser(adminUserParam);
				if(adminUser!=null) {
					if (ConstantList.ROOT_PORTAL_ID.equals(adminUser.getPortalId())) {
						adminUser.setAdminType(ConstantList.GROUP_ADMIN);
						GroupPortalVO adminPortalVO = new GroupPortalVO();
						adminPortalVO.setTenantId(adminUser.getTenantId());
						adminPortalVO.setPortalId(adminUser.getPortalId());
						
						GroupPortalVO r = null;
						if (ConstantList.ROOT_PORTAL_ID.equals(adminUser.getPortalId())) {
							r = groupPortalService.getDefault(portalVO); // default portal
						} else {
							r = groupPortalService.get(portalVO);
						}
						session.setAttribute("COMP_ID", userProfile.getCompId()); // 사용자 소속 회사 아이디
						session.setAttribute("RELATED_COMP_ID", r.getRelatedCompid()); // 포탈에 연결된 회사 아이디
						session.setAttribute("PORTAL_NAME", r.getPortalName());
					}
					session.setAttribute("adminProfile", adminUser);
				}
			}
			
			if (CommonUtil.isNullOrEmpty(param.getLanguage())) {
				param.setLanguage("ko");
			}
			
			// 로그인 성공인 경우에 세션 저장
			if(userProfile.getLoginResult() == ConstantList.LOGIN_SUCCESS || userProfile.getLoginResult() == ConstantList.LOGIN_FAIL_EXPIRED_PASSWORD) {
				// 개인화 설정 가져와서 세션에 저장
				UserSettingVO userSettingVO = new UserSettingVO();
				bind(request, userSettingVO);
				userSettingVO.setUserId(userProfile.getUserUid());
				
				LoginHistoryVO loginHistory = new LoginHistoryVO();
				loginHistory.setUserUid(userProfile.getUserUid());
				loginHistory = service.getLoginTime(loginHistory);
				
				session.setAttribute("LOGIN_TIME", CommonUtil.formatDate(loginHistory.getLoginTime(), "yyyy.MM.dd HH:mm:ss"));
				
				List<UserSettingVO> userSettings = userSettingService.getUserSettingList(userSettingVO);
				
				for(int i=0; i < userSettings.size(); i++) {
					UserSettingVO userSetting = userSettings.get(i);
					// 세션에 저장
					session.setAttribute(userSetting.getSettingCode(), userSetting.getSettingValue());
					if(ConstantList.US_CODE_INITIAL_PAGE.equals(userSetting.getSettingCode())) {
						userProfile.setInitialPage(userSetting.getSettingValue());
					}
				}
				
				session.setAttribute("ACCESS_ID_LIST", getAccessIdList(request));
				session.setAttribute("LANG_TYPE", new Locale(param.getLanguage()));
				session.setAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE", new Locale(param.getLanguage()));
				
				// 로그인 팝업창 셋팅
				LoginPopupVO popupVO = new LoginPopupVO();
				bind(request, popupVO);
				popupVO.setSearchKey(CommonUtil.getToday());
				
				List<LoginPopupVO> popupList = loginPopupService.getPopupList(popupVO);
				
				if(popupList.size() > 0) {
					LoginPopupVO[] popups = new LoginPopupVO[popupList.size()];
					popupList.toArray(popups);
					userProfile.setLoginPopup(popups);
				}
				
				// 로그인 플러그 셋팅
				LoginPlugVO plugVO = new LoginPlugVO();
				bind(request, plugVO);
				plugVO.setSearchKey(CommonUtil.getToday());
				
				List<LoginPlugVO> plugList = loginPlugService.getPlugList(plugVO);
				
				if(plugList.size() > 0) {
					LoginPlugVO[] plugs = new LoginPlugVO[plugList.size()];
					plugList.toArray(plugs);
					userProfile.setLoginPlug(plugs);
				}
			}
			
			//계정등록 후 최초 로그인시 계정 암호 재설정
			if(LuxorConfig.getEnvString(request, "USE_MEMBER_REQUEST").equals("Y") && userProfile.getLoginResult() == ConstantList.LOGIN_SUCCESS) {
				MemberRequestVO memberRequestVO = new MemberRequestVO();
				bind(request, memberRequestVO);
				memberRequestVO.setRequestStatus(1);
				memberRequestVO = memberRequestService.getMemberRequestByPk(memberRequestVO);
				if(memberRequestVO != null) {
					userProfile.setLoginResult(101);
				}
			}
			
		}
		
		if("N".equals(LuxorConfig.getString("BASIC.USE_HTTPS")) && "Y".equals(LuxorConfig.getString("BASIC.USE_AES"))){
			String loginEncResult = AESUtil.encrypt(request, Integer.toString(userProfile.getLoginResult()) + "-" + DateUtil.getCurrentDate());
			userProfile.setLoginEncResult(loginEncResult);
		}
		
		ModelAndView mav = new ModelAndView(nextURI);
		
		// 모바일에서 로그인 하기 위해 D1값을 추가한다.
		userProfile.setD1(UtilSSO.encodeSession(request, LuxorConfig.getString("SSO.ENCODE_ALGORITHM")));
		mav.addObject("jsonResult", userProfile);
		mav.addObject("userProfile", userProfile);
		return mav;
	}


	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginVO param = new LoginVO();
		bind(request, param);
		
		HttpSession session = request.getSession();
		UserProfileVO userProfile = (UserProfileVO) session.getAttribute("userProfile");
		
		if(userProfile!=null) {
			logger.info(userProfile.getLoginId()+" logout..");
		}
		
		session.invalidate();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", param);
		return mav;
	}


	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView logoutProcessByPortlet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LogoutSysVO param = new LogoutSysVO();
		bind(request, param);
		
		HttpSession session = request.getSession();
		UserProfileVO userProfile = (UserProfileVO) session.getAttribute("userProfile");
		
		logger.info(userProfile.getLoginId()+" logout from portlet..");
		
		session.invalidate();
		
		List<LogoutSysVO> list = logoutSysService.getList(param);
		LogoutSysVO[] logoutSys = new LogoutSysVO[list.size()];
		list.toArray(logoutSys);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", logoutSys);
		return mav;
	}
	
	
	/**
	 * 접속자의 Access ID 값들을 List로 뽑아냄
	 * 
	 * @param request
	 * @return
	 */
	private List getAccessIdList(HttpServletRequest request) throws Exception {
		AdminUserVO admin = (AdminUserVO)WebUtils.getSessionAttribute(request, "adminProfile");
		UserProfileVO user = (UserProfileVO)WebUtils.getSessionAttribute(request, "userProfile");
		
		List accessList = new ArrayList();

        if(user==null) {
        	accessList.add(ConstantList.GUEST);
        	return accessList;
        }

        if(admin!=null) {
        	accessList.add(ConstantList.ADMIN);
        }
        accessList.add("COMMON");
        accessList.add(user.getCompId());
        accessList.add(user.getDeptId());
        accessList.add(user.getGroupId());
        accessList.add(user.getUserUid());
        //직급,직위,직책,직무
        accessList.add(user.getGradeCode());
        accessList.add(user.getPositionCode());
        accessList.add(user.getTitleCode());
        accessList.add(user.getDutyCode());
        
        for(String department : user.getDepartmentList()) {
        	accessList.add(department);
        }

        /*
        List virtualGroupList = accessControllService.getGroupsByUid(user.getUserUid());
   		if(virtualGroupList!=null) accessList.addAll(virtualGroupList);
         */
        List aclGroupList = accessControllService.getAclGroupsByUid(user.getUserUid());
        if(aclGroupList!=null) accessList.addAll(aclGroupList);
        return accessList;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView goCommonLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(commonLogin);
		return mav;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView switchPortal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String userUID = UtilRequest.getString(request, "userUID");
		UserProfileVO myProfile = new UserProfileVO();
		
		if(!userUID.equals("")) {
			myProfile = orgService.getUserProfile(userUID);
			if(myProfile.getLoginResult() == ConstantList.LOGIN_SUCCESS) {
				myProfile.setTenantId((String)session.getAttribute("TENANT_ID"));
				myProfile.setPortalId(UtilRequest.getString(request, "portalId"));
				session.setAttribute("userProfile", myProfile);
				session.setAttribute("ACCESS_ID_LIST", getAccessIdList(request));
			}
		}
		
		session.setAttribute("PORTAL_ID", UtilRequest.getString(request, "portalId"));
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", true);
		return mav;
	}
	
}
