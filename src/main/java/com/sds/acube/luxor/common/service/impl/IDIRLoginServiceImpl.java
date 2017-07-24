/*
 * @(#) IDIRLoginServiceImpl.java 2010. 5. 25.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.common.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import com.sds.acube.luxor.common.ConstantList;
import com.sds.acube.luxor.common.dao.LoginDAO;
import com.sds.acube.luxor.common.service.LoginService;
import com.sds.acube.luxor.common.vo.LoginHistoryVO;
import com.sds.acube.luxor.common.vo.LoginVO;
import com.sds.acube.luxor.common.vo.UserProfileVO;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.service.BaseService;
import com.sds.acube.luxor.security.EnDecode;
import com.sds.acube.luxor.ws.client.orgservice.IUser;
import com.sds.acube.luxor.ws.client.orgservice.OrgProvider4WS;
import com.sds.acube.luxor.ws.client.orgservice.Organization;
import com.sds.acube.luxor.ws.client.orgservice.Organizations;


/**
 * @author Alex, Eum
 * @version $Revision: 1.2.2.1 $ $Date: 2011/02/10 06:05:53 $
 */
public class IDIRLoginServiceImpl extends BaseService implements LoginService {
	private OrgProvider4WS orgService;
	private LoginDAO loginDAO;
	private String serviceKey;

	/**
	 * @param orgService The orgService to set.
	 */
	public void setOrgService(OrgProvider4WS orgService) {
		this.orgService = orgService;
		serviceKey = LuxorConfig.getString("IAM.KEY");
	}


	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sds.acube.luxor.common.service.LoginService#loginProcess(com.sds.
	 * acube.luxor.common.vo.LoginVO)
	 */
	public UserProfileVO loginProcess(LoginVO param) throws Exception {
		UserProfileVO myProfile = new UserProfileVO();
		IUser user = null;
		
		logger.info("Checking authentication for login ID : " + param.getLoginId());
		
		String tenantId = param.getTenantId();
		String portalId = param.getPortalId();
		
		// 동시로그인 제한
		String limitConcurrentLogin = LuxorConfig.getEnvString(tenantId, portalId, "CONCURRENT_LOGIN_LIMIT");
		String limitMethod = LuxorConfig.getEnvString(tenantId, portalId, "CONCURRENT_LOGIN_METHOD");
		
		boolean chkLogin = false;
		LoginHistoryVO lhVo = new LoginHistoryVO();
		
		if ("Y".equals(limitConcurrentLogin) && "FLA".equals(limitMethod)) {
			// 로그인 가능 : Y, 다른 IP에서 로그인중 : IP
			lhVo.setTenantId(param.getTenantId());
			lhVo.setPortalId(param.getPortalId());
			lhVo.setLoginId(param.getLoginId());
			lhVo.setLoginIp(param.getLoginIp());
			chkLogin = checkLoginFLA(lhVo);
		}
		
		if (chkLogin) {
		} else {
			myProfile.setLoginResult(ConstantList.LOGIN_FAIL_CONCURRENT);
			user = orgService.getUserByIDWithPWD(serviceKey, param.getLoginId(), EnDecode.EncodeBySType(param.getPassword()), 0);
			if (user == null) {
				// 객체를 얻어오지 못하는 경우
				myProfile.setLoginResult(ConstantList.LOGIN_OBJECT_NULL);
				logger.error("IUser object is null. (" + param.getLoginId() + ")");
				return myProfile;
			}
			
			logger.debug("user.getLoginResult()=" + user.getLoginResult());
			
			myProfile.setLoginResult(user.getLoginResult());
			if (user.getLoginResult() == ConstantList.LOGIN_SUCCESS || user.getLoginResult() == ConstantList.LOGIN_FAIL_EXPIRED_PASSWORD) {
				myProfile.setCompId(user.getCompID());
				myProfile.setCompName(user.getCompName());
				myProfile.setCompOtherName(user.getCompOtherName()); // @add 2013.11.18 - other language
				myProfile.setLoginId(user.getUserID());
				myProfile.setUserUid(user.getUserUID());
				myProfile.setUserName(user.getUserName());
				myProfile.setUserOtherName(user.getUserOtherName()); // @add 2013.11.18 - other language
				myProfile.setPassword(EnDecode.EncodeBySType(param.getPassword()));
				myProfile.setDeptId(user.getDeptID());
				myProfile.setDeptName(user.getDeptName());
				myProfile.setDeptOtherName(user.getDeptOtherName()); // @add 2013.11.18 - other language
				myProfile.setSecurityLevel(user.getSecurityLevel());
				myProfile.setGradeCode(user.getGradeCode());
				myProfile.setGradeName(user.getGradeName());
				myProfile.setGradeOtherName(user.getGradeOtherName()); // @add 2013.11.18 - other language
				myProfile.setOfficeTel(user.getOfficeTel());
				myProfile.setHomeTel(user.getHomeTel());
				myProfile.setMobile(user.getMobile());
				myProfile.setSysMail(user.getSysMail());
				myProfile.seteMail(user.getEmail());
				myProfile.setChangedPasswordDate(user.getChangedPWDDate());
				myProfile.setRoleCodes(user.getRoleCodes());
				myProfile.setPositionCode(user.getPositionCode());
				myProfile.setPositionName(user.getPositionName());
				myProfile.setTitleName(user.getTitleName());
				myProfile.setTenantId(param.getTenantId());
				myProfile.setPortalId(param.getPortalId());
				myProfile.setOfficeFax(user.getOfficeFax());
				myProfile.setEmployeeId(user.getEmployeeID());
				Organizations organizations = orgService.getUserOrganizationsByOrgID(serviceKey, myProfile.getCompId(), myProfile.getDeptId());
				int orgCount = (organizations == null) ? 0 : organizations.getOrganizationList().size();
				ArrayList<String> list = new ArrayList<String>();
				
				for (int i = 0; i < orgCount; i++) {
					Organization organization = organizations.getOrganizationList().get(i);
					list.add(organization.getOrgID());
				}
				
				String[] arrDeptID = new String[list.size()];
				list.toArray(arrDeptID);
				myProfile.setDepartmentList(arrDeptID);
				
				logger.info("Login Success for login ID : " + param.getLoginId());
			}
		}
		
		return myProfile;
	}


	   // 로그인 시간 정보를 가져온다.
    public LoginHistoryVO getLoginTime(LoginHistoryVO vo) throws Exception {
      return  (LoginHistoryVO)loginDAO.getLoginTime(vo);  
    }
	
	
	// 로그인 통계 정보 insert
	public boolean insertLoginHistory(LoginHistoryVO vo) throws Exception {
		boolean result = loginDAO.insertLoginHistory(vo);
		if (result == false) {
			throw new Exception("Fail to insert!!");
		}
		return result;
	}


	// 로그아웃 시 로그인 통계 정보 수정
	public boolean updateLoginHistory(LoginHistoryVO vo) throws Exception {
		boolean result = loginDAO.updateLoginHistory(vo);
		if (result == false) {
			throw new Exception("Fail to update!!");
		}
		return result;
	}


	// 중복 로그인 체크 - FLA 방식
	public boolean checkLoginFLA(LoginHistoryVO vo) throws Exception {
		boolean result = false;
		LoginHistoryVO lhVo = loginDAO.getLastLoginInfo(vo);
		String sessionId = "";
		String loginIp = "";
		if (lhVo != null) {
			sessionId = lhVo.getSessionId();
			loginIp = lhVo.getLoginIp();
		}
		
		// 다른 IP에서 내 login id로 로그인하여 로그아웃 하지 않은 상태일 경우
		if (!loginIp.equals(vo.getLoginIp()) && !sessionId.equals(vo.getSessionId())) {
			result = true;
		}
		return result;
	}


	// 중복 로그인 체크 - LLE 방식
	public boolean checkLoginLLE(LoginHistoryVO vo) throws Exception {
		boolean result = false;
		vo.setLoginTime(null);
		LoginHistoryVO lhVo = loginDAO.checkLoginLLE(vo);
		Timestamp myLoginTime = null;
		if (lhVo != null) myLoginTime = lhVo.getLoginTime();
		vo.setLoginTime(myLoginTime);
		vo.setSessionId("");
		// 내 login id로 나보다 이후에 로그인하고 로그아웃 기록이 없을경우
		LoginHistoryVO resultVo = loginDAO.checkLoginLLE(vo);
		String loginIp = "";
		if (resultVo != null) {
			loginIp = resultVo.getLoginIp();
			if (resultVo.getLogoutTime() == null) {
				if (loginIp != null) {
					// 내 login id로 나보다 이후에 로그인한 ip가 내 ip와 다를 경우
					if (!loginIp.equals("") && !loginIp.equals(vo.getLoginIp())) {
						result = true;
					}
				}
			}
		}
		return result;
	}
}
