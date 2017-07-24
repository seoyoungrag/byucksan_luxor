/*
 * @(#) UserProfileVO.java 2010. 5. 28.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.common.vo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;

import com.sds.acube.luxor.portal.vo.LoginPlugVO;
import com.sds.acube.luxor.portal.vo.LoginPopupVO;


/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.3.2.4 $ $Date: 2011/04/21 00:13:39 $
 */
public class UserProfileVO extends CommonVO implements Serializable {
    private static final long serialVersionUID = 3722772531323311116L;
	protected String compId; // 회사코드
    protected String compName; // 회사명
    protected String compOtherName; // @add 2013.11.18 - other language
    protected String deptId; // 부서코드
    protected String deptName; // 부서명
    protected String deptOtherName; // @add 2013.11.18 - other language
    protected String dutyCode; // 직무코드
    protected String dutyName; // 직무명
    protected String gradeCode; // 직급코드
    protected String gradeName; // 직급명
    protected String gradeOtherName; // @add 2013.11.18 - other language
    protected String groupId; // 
    protected String groupName;
    protected int loginResult; // 로그인 결과
    protected String loginEncResult; //암호화된 로그인결과
    protected String partId; 
    protected String partName;
    protected String positionCode; // 직위 코드
    protected String positionName; // 직위 명
    protected boolean proxy; 
    protected String roleCodes;
    protected int securityLevel; // 보안 등급
    protected String titleCode; // 직책코드
    protected String titleName; // 직책명
    protected String loginId; // 로그인 아이디 
    protected String userName; // 사용자명
    protected String userOtherName; // @add 2013.11.18 - other language
    protected String userStatus; // 
    protected String userUid; // 유져 내부 ID
    protected String password;
    
    protected String officeTel;
    protected String officeFax;
    protected String homeTel;
    protected String mobile;
    protected String sysMail;
    protected String eMail;
    protected String changedPasswordDate;
    
    protected String[] departmentList;
    protected HashMap<String, Object> attrs;
    
    protected String defaultPortalId;
    protected String initialPage; // 개인화 초기화면
    protected String themeId; // 개인화 테마ID
    protected LoginPopupVO[] loginPopup; // 개인 로그인 팝업
    protected LoginPlugVO[] loginPlug; // 로그인 플러그 팝업
    protected String d1;
    
    protected String employeeId;
   
    public String getEmployeeId() {
      return employeeId;
    }

    public void setEmployeeId(String employeeId) {
      this.employeeId = employeeId;
    }

    public String getD1() {
      return d1;
    }

    public void setD1(String d1) {
      this.d1 = d1;
    }

    /**
     * 
     */
    public UserProfileVO() {
		super();
		this.compId = "";
		this.compName = "";
		this.compOtherName = ""; // @add 2013.11.18 - other language
		this.deptId = "";
		this.deptName = "";
		this.deptOtherName = ""; // @add 2013.11.18 - other language
		this.dutyCode = "";
		this.dutyName = "";
		this.gradeCode = "";
		this.gradeName = "";
		this.gradeOtherName = ""; // @add 2013.11.18 - other language
		this.groupId = "";
		this.groupName = "";
		this.loginResult = -1;
		this.partId = "";
		this.partName = "";
		this.positionCode = "";
		this.positionName = "";
		this.proxy = false;
		this.roleCodes = "";
		this.securityLevel = -1;
		this.titleCode = "";
		this.titleName = "";
		this.loginId = "";
		this.userName = "";
		this.userOtherName = ""; // @add 2013.11.18 - other language
		this.userStatus = "";
		this.userUid = "";
		this.password = "";
		this.departmentList = null;
		this.attrs = new HashMap<String, Object>();
		
		this.officeTel = "";
		this.officeFax = "";
	    this.homeTel = "";
	    this.mobile = "";
	    this.sysMail = "";
	    this.eMail = "";
	    this.changedPasswordDate = "";
	    this.initialPage = "";
	    this.employeeId = "";
	}
    
	/**
	 * @param compId
	 * @param compName
	 * @param deptId
	 * @param deptName
	 * @param dutyCode
	 * @param dutyName
	 * @param gradeCode
	 * @param gradeName
	 * @param groupId
	 * @param groupName
	 * @param loginResult
	 * @param partId
	 * @param partName
	 * @param positionCode
	 * @param positionName
	 * @param proxy
	 * @param roleCodes
	 * @param securityLevel
	 * @param titleCode
	 * @param titleName
	 * @param loginId
	 * @param userName
	 * @param userStatus
	 * @param userUid
	 * @param departmentList
	 * @param attrs
	 */
	public UserProfileVO(String compId, String compName, String deptId, String deptName, String dutyCode, String dutyName, String gradeCode, String gradeName, String groupId, String groupName,
			int loginResult, String partId, String partName, String positionCode, String positionName, boolean proxy, String roleCodes, int securityLevel, String titleCode, String titleName,
			String loginId, String userName, String userStatus, String userUid, String[] departmentList, HashMap<String, Object> attrs, String officeTel, String homeTel, String mobile, String sysMail,
			String eMail, String changedPasswordDate) {
		super();
		this.compId = compId;
		this.compName = compName;
		this.deptId = deptId;
		this.deptName = deptName;
		this.dutyCode = dutyCode;
		this.dutyName = dutyName;
		this.gradeCode = gradeCode;
		this.gradeName = gradeName;
		this.groupId = groupId;
		this.groupName = groupName;
		this.loginResult = loginResult;
		this.partId = partId;
		this.partName = partName;
		this.positionCode = positionCode;
		this.positionName = positionName;
		this.proxy = proxy;
		this.roleCodes = roleCodes;
		this.securityLevel = securityLevel;
		this.titleCode = titleCode;
		this.titleName = titleName;
		this.loginId = loginId;
		this.userName = userName;
		this.userStatus = userStatus;
		this.userUid = userUid;
		this.departmentList = departmentList;
		this.attrs = attrs;
		
		this.officeTel = officeTel;
	    this.homeTel = homeTel;
	    this.mobile = mobile;
	    this.sysMail = sysMail;
	    this.eMail = eMail;
	    this.changedPasswordDate = changedPasswordDate;
	}
	
	
	public String getThemeId() {
    	return themeId;
    }

	public void setThemeId(String themeId) {
    	this.themeId = themeId;
    }

	public LoginPopupVO[] getLoginPopup() {
    	return loginPopup;
    }

	public void setLoginPopup(LoginPopupVO[] loginPopup) {
    	this.loginPopup = loginPopup;
    }

	
	public LoginPlugVO[] getLoginPlug() {
		return loginPlug;
	}

	public void setLoginPlug(LoginPlugVO[] loginPlug) {
		this.loginPlug = loginPlug;
	}

	/**
	 * @return Returns the defaultPortalId.
	 */
	public String getDefaultPortalId() {
		return defaultPortalId;
	}

	
	/**
	 * @param defaultPortalId The defaultPortalId to set.
	 */
	public void setDefaultPortalId(String defaultPortalId) {
		this.defaultPortalId = defaultPortalId;
	}

	public String getPassword() {
    	return password;
    }

	public void setPassword(String password) {
    	this.password = password;
    }

	/**
	 * @return Returns the compId.
	 */
	public String getCompId() {
		return compId;
	}
	
	/**
	 * @param compId The compId to set.
	 */
	public void setCompId(String compId) {
		this.compId = compId;
	}
	
	/**
	 * @return Returns the compName.
	 */
	public String getCompName() {
		return compName;
	}
	
	/**
	 * @param compName The compName to set.
	 */
	public void setCompName(String compName) {
		this.compName = compName;
	}
	
	// @add 2013.11.18 - other language
	public String getCompOtherName() {
		return compOtherName;
	}
	
	// @add 2013.11.18 - other language
	public void setCompOtherName(String compOtherName) {
		this.compOtherName = compOtherName;
	}
	
	/**
	 * @return Returns the deptId.
	 */
	public String getDeptId() {
		return deptId;
	}
	
	/**
	 * @param deptId The deptId to set.
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	/**
	 * @return Returns the deptName.
	 */
	public String getDeptName() {
		return deptName;
	}
	
	/**
	 * @param deptName The deptName to set.
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	// @add 2013.11.18 - other language
	public String getDeptOtherName() {
		return deptOtherName;
	}
	// @add 2013.11.18 - other language
	public void setDeptOtherName(String deptOtherName) {
		this.deptOtherName = deptOtherName;
	}
	
	/**
	 * @return Returns the dutyCode.
	 */
	public String getDutyCode() {
		return dutyCode;
	}
	
	/**
	 * @param dutyCode The dutyCode to set.
	 */
	public void setDutyCode(String dutyCode) {
		this.dutyCode = dutyCode;
	}
	
	/**
	 * @return Returns the dutyName.
	 */
	public String getDutyName() {
		return dutyName;
	}
	
	/**
	 * @param dutyName The dutyName to set.
	 */
	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}
	
	/**
	 * @return Returns the gradeCode.
	 */
	public String getGradeCode() {
		return gradeCode;
	}
	
	/**
	 * @param gradeCode The gradeCode to set.
	 */
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
	
	/**
	 * @return Returns the gradeName.
	 */
	public String getGradeName() {
		return gradeName;
	}
	
	/**
	 * @param gradeName The gradeName to set.
	 */
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
	// @add 2013.11.18 - other language
	public String getGradeOtherName() {
		return gradeOtherName;
	}
	// @add 2013.11.18 - other language
	public void setGradeOtherName(String gradeOtherName) {
		this.gradeOtherName = gradeOtherName;
	}
	
	/**
	 * @return Returns the groupId.
	 */
	public String getGroupId() {
		return groupId;
	}
	
	/**
	 * @param groupId The groupId to set.
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	/**
	 * @return Returns the groupName.
	 */
	public String getGroupName() {
		return groupName;
	}
	
	/**
	 * @param groupName The groupName to set.
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	/**
	 * @return Returns the loginResult.
	 */
	public int getLoginResult() {
		return loginResult;
	}
	
	/**
	 * @param loginResult The loginResult to set.
	 */
	public void setLoginResult(int loginResult) {
		this.loginResult = loginResult;
	}
	
	/**
	 * @return Returns the partId.
	 */
	public String getPartId() {
		return partId;
	}
	
	/**
	 * @param partId The partId to set.
	 */
	public void setPartId(String partId) {
		this.partId = partId;
	}
	
	/**
	 * @return Returns the partName.
	 */
	public String getPartName() {
		return partName;
	}
	
	/**
	 * @param partName The partName to set.
	 */
	public void setPartName(String partName) {
		this.partName = partName;
	}
	
	/**
	 * @return Returns the positionCode.
	 */
	public String getPositionCode() {
		return positionCode;
	}
	
	/**
	 * @param positionCode The positionCode to set.
	 */
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}
	
	/**
	 * @return Returns the positionName.
	 */
	public String getPositionName() {
		return positionName;
	}
	
	/**
	 * @param positionName The positionName to set.
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	
	/**
	 * @return Returns the proxy.
	 */
	public boolean isProxy() {
		return proxy;
	}
	
	/**
	 * @param proxy The proxy to set.
	 */
	public void setProxy(boolean proxy) {
		this.proxy = proxy;
	}
	
	/**
	 * @return Returns the roleCodes.
	 */
	public String getRoleCodes() {
		return roleCodes;
	}
	
	/**
	 * @param roleCodes The roleCodes to set.
	 */
	public void setRoleCodes(String roleCodes) {
		this.roleCodes = roleCodes;
	}
	
	/**
	 * @return Returns the securityLevel.
	 */
	public int getSecurityLevel() {
		return securityLevel;
	}
	
	/**
	 * @param securityLevel The securityLevel to set.
	 */
	public void setSecurityLevel(int securityLevel) {
		this.securityLevel = securityLevel;
	}
	
	/**
	 * @return Returns the titleCode.
	 */
	public String getTitleCode() {
		return titleCode;
	}
	
	/**
	 * @param titleCode The titleCode to set.
	 */
	public void setTitleCode(String titleCode) {
		this.titleCode = titleCode;
	}
	
	/**
	 * @return Returns the titleName.
	 */
	public String getTitleName() {
		return titleName;
	}
	
	/**
	 * @param titleName The titleName to set.
	 */
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	
	/**
	 * @return Returns the loginId.
	 */
	public String getLoginId() {
		return loginId;
	}
	
	/**
	 * @param loginId The loginId to set.
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	/**
	 * @return Returns the userName.
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * @param userName The userName to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	// @add 2013.11.18 - other language
	public String getUserOtherName() {
		return userOtherName;
	}
	// @add 2013.11.18 - other language
	public void setUserOtherName(String userOtherName) {
		this.userOtherName = userOtherName;
	}
	
	/**
	 * @return Returns the userStatus.
	 */
	public String getUserStatus() {
		return userStatus;
	}
	
	/**
	 * @param userStatus The userStatus to set.
	 */
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	
	/**
	 * @return Returns the userUid.
	 */
	public String getUserUid() {
		return userUid;
	}
	
	/**
	 * @param userUid The userUid to set.
	 */
	public void setUserUid(String userUid) {
		this.userUid = userUid;
	}
	
	/**
	 * @return Returns the departmentList.
	 */
	public String[] getDepartmentList() {
		return departmentList;
	}
	
	/**
	 * @param departmentList The departmentList to set.
	 */
	public void setDepartmentList(String[] departmentList) {
		this.departmentList = departmentList;
	}
	
	/**
	 * @return Returns the attrs.
	 */
	public HashMap<String, Object> getAttrs() {
		return attrs;
	}
	
	/**
	 * @param attrs The attrs to set.
	 */
	public void setAttrs(HashMap<String, Object> attrs) {
		this.attrs = attrs;
	}

	
	/**
	 * @return Returns the officeTel.
	 */
	public String getOfficeTel() {
		return officeTel;
	}

	
	/**
	 * @param officeTel The officeTel to set.
	 */
	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	
	/**
	 * @return Returns the homeTel.
	 */
	public String getHomeTel() {
		return homeTel;
	}

	
	/**
	 * @param homeTel The homeTel to set.
	 */
	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}

	
	/**
	 * @return Returns the mobile.
	 */
	public String getMobile() {
		return mobile;
	}

	
	/**
	 * @param mobile The mobile to set.
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	
	/**
	 * @return Returns the sysMail.
	 */
	public String getSysMail() {
		return sysMail;
	}

	
	/**
	 * @param sysMail The sysMail to set.
	 */
	public void setSysMail(String sysMail) {
		this.sysMail = sysMail;
	}

	
	/**
	 * @return Returns the eMail.
	 */
	public String geteMail() {
		return eMail;
	}

	
	/**
	 * @param eMail The eMail to set.
	 */
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	
	/**
	 * @return Returns the changedPasswordDate.
	 */
	public String getChangedPasswordDate() {
		return changedPasswordDate;
	}
	
	/**
	 * @param changedPasswordDate The changedPasswordDate to set.
	 */
	public void setChangedPasswordDate(String changedPasswordDate) {
		this.changedPasswordDate = changedPasswordDate;
	}

	
	public String getOfficeFax() {
    	return officeFax;
    }

	public void setOfficeFax(String officeFax) {
    	this.officeFax = officeFax;
    }

	public String getInitialPage() {
    	return initialPage;
    }

	public void setInitialPage(String initialPage) {
    	this.initialPage = initialPage;
    }

	public void setLoginEncResult(String loginEncResult){
		this.loginEncResult = loginEncResult;
	}
	
	public String getLoginEncResult(){
		return loginEncResult;
	}
	
	@Override
    public String toString() {
	    return super.toString() + ", UserProfileVO [attrs=" + attrs + ", changedPasswordDate=" + changedPasswordDate + ", compId=" + compId + ", compName="
	        + compName + ", departmentList=" + Arrays.toString(departmentList) + ", deptId=" + deptId + ", deptName=" + deptName
	        + ", dutyCode=" + dutyCode + ", dutyName=" + dutyName + ", eMail=" + eMail + ", gradeCode=" + gradeCode + ", gradeName="
	        + gradeName + ", groupId=" + groupId + ", groupName=" + groupName + ", homeTel=" + homeTel + ", loginId=" + loginId
	        + ", loginResult=" + loginResult + ", mobile=" + mobile + ", officeTel=" + officeTel + ", partId=" + partId + ", partName="
	        + partName + ", positionCode=" + positionCode + ", positionName=" + positionName + ", proxy=" + proxy + ", roleCodes="
	        + roleCodes + ", securityLevel=" + securityLevel + ", sysMail=" + sysMail + ", titleCode=" + titleCode + ", titleName="
	        + titleName + ", userName=" + userName + ", userStatus=" + userStatus + ", userUid=" + userUid + "]";
    }
    
}
