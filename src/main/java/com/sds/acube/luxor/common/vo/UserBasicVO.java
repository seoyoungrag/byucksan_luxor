package com.sds.acube.luxor.common.vo;

import java.io.Serializable;
import com.sds.acube.luxor.common.vo.CommonVO;


public class UserBasicVO extends CommonVO implements Serializable {
	private static final long serialVersionUID = 3821624345462678583L;
	public static final int LOGIN_SUCCESS = 0; // Login Success
	public static final int INVALID_LOGIN = 1; // Invalid Login Id
	public static final int INVALID_PWD = 2; // Invalid PassWord
	public static final int DB_CONNECT_FAIL = 3; // Fail Database connection
	public static final int DB_SELECT_FAIL = 4; // Fail Database select
	private String list_type = "";
	private String m_strUserID = "";
	private String m_strUserIDs = "";
	private String m_strUserName = "";
	private String m_strUserOtherName = "";
	private String m_strUserUID = "";
	private String m_strGroupID = "";
	private String m_strGroupName = "";
	private String m_strGroupOtherName = "";
	private String m_strCompID = "";
	private String m_strCompName = "";
	private String m_strCompOtherName = "";
	private int m_nOrgType = 0;
	private int m_nType = 0;
	private String m_strDeptID = "";
	private String m_strDeptName = "";
	private String m_strDeptOtherName = "";
	private String m_strPartID = "";
	private String m_strPartName = "";
	private String m_strPartOtherName = "";
	private String m_strOrgDisplayName = "";
	private String m_strOrgDisplayOtherName = "";
	private int m_nUserOrder = 0;
	private int m_nSecurityLevel = 0;
	private String m_strRoleCodes = "";
	private String m_strResidentNo = "";
	private String m_strEmployeeID = "";
	private String m_strSysMail = "";
	private boolean m_bConcurrent = false;
	private boolean m_bProxy = false;
	private boolean m_bDelegate = false;
	private boolean m_bExistence = false;
	private String m_strUserRID = "";
	private boolean m_bDeleted = false;
	private String m_strDescription = "";
	private String m_strReserved1 = "";
	private String m_strReserved2 = "";
	private String m_strReserved3 = "";
	private String m_strGradeCode = "";
	private String m_strGradeName = "";
	private String m_strGradeOtherName = "";
	private String m_strGradeAbbrName = "";
	private int m_nGradeOrder = 0;
	private String m_strPositionCode = "";
	private String m_strPositionName = "";
	private String m_strPositionOtherName = "";
	private String m_strPositionAbbrName = "";
	private int m_nPositionOrder = 0;
	private String m_strTitleCode = "";
	private String m_strTitleName = "";
	private String m_strTitleOtherName = "";
	private int m_nTitleOrder = 0;
	private String m_strEmail = "";
	private String m_strDuty = "";
	private String m_strPCOnlineID = "";
	private String m_strHomePage = "";
	private String m_strOfficeTel = "";
	private String m_strOfficeTel2 = "";
	private String m_strOfficeAddr = "";
	private String m_strOfficeDetailAddr = "";
	private String m_strOfficeZipCode = "";
	private String m_strOfficeFax = "";
	private String m_strMobile = "";
	private String m_strMobile2 = "";
	private String m_strPager = "";
	private String m_strHomeAddr = "";
	private String m_strHomeDetailAddr = "";
	private String m_strHomeZipCode = "";
	private String m_strHomeTel = "";
	private String m_strHomeTel2 = "";
	private String m_strHomeFax = "";
	private String m_strUserStatus = "";
	private String m_strChangedPWDDate = "";
	private String m_strMailServer = "";
	private String m_strCertificationID = "";
	private String m_strDutyCode = "";
	private String m_strDutyName = "";
	private String m_strDutyOtherName = "";
	private int m_nDutyOrder = 0;
	private String m_strOptionalGTPName = "";
	private int m_nLoginResult = -1;
	private boolean m_bCaseSensitive = false;
	private int m_nScope = 0;


	/**
	 * @return the list_type
	 */
	public String getList_type() {
		return list_type;
	}


	/**
	 * @param listType the list_type to set
	 */
	public void setList_type(String listType) {
		list_type = listType;
	}


	public void setOptionalGTPName(String strOptionalGTPName) {
		m_strOptionalGTPName = strOptionalGTPName;
	}


	public void setGroupOtherName(String strGroupOtherName) {
		m_strGroupOtherName = strGroupOtherName;
	}


	public void setCompOtherName(String strCompOtherName) {
		m_strCompOtherName = strCompOtherName;
	}


	public void setDeptOtherName(String strDeptOtherName) {
		m_strDeptOtherName = strDeptOtherName;
	}


	public void setPartOtherName(String strPartOtherName) {
		m_strPartOtherName = strPartOtherName;
	}


	public void setOrgDisplayOtherName(String strOrgDisplayOtherName) {
		m_strOrgDisplayOtherName = strOrgDisplayOtherName;
	}


	public void setDutyOtherName(String strDutyOtherName) {
		m_strDutyOtherName = strDutyOtherName;
	}


	public void setCertificationID(String strCertificationID) {
		m_strCertificationID = strCertificationID;
	}


	public void setMailServer(String strMailServer) {
		m_strMailServer = strMailServer;
	}


	public void setChangedPWDDate(String strChangedPWDDate) {
		m_strChangedPWDDate = strChangedPWDDate;
	}


	public void setLoginResult(int nLoginResult) {
		m_nLoginResult = nLoginResult;
	}


	public void setCaseSensitive(boolean bCaseSensitive) {
		m_bCaseSensitive = bCaseSensitive;
	}


	public void setNScope(int nScope) {
		m_nScope = nScope;
	}


	public void setConcurrent(boolean bConcurrent) {
		m_bConcurrent = bConcurrent;
	}


	public void setDelegate(boolean bDelegate) {
		m_bDelegate = bDelegate;
	}


	public void setDeleted(boolean bDeleted) {
		m_bDeleted = bDeleted;
	}


	public void setExistence(boolean bExistence) {
		m_bExistence = bExistence;
	}


	public void setProxy(boolean bProxy) {
		m_bProxy = bProxy;
	}


	public void setGradeOrder(int nGradeOrder) {
		m_nGradeOrder = nGradeOrder;
	}


	public void setPositionOrder(int nPositionOrder) {
		m_nPositionOrder = nPositionOrder;
	}


	public void setSecurityLevel(int nSecurityLevel) {
		m_nSecurityLevel = nSecurityLevel;
	}


	public void setTitleOrder(int nTitleOrder) {
		m_nTitleOrder = nTitleOrder;
	}


	public void setUserOrder(int nUserOrder) {
		m_nUserOrder = nUserOrder;
	}


	public void setCompID(String strCompID) {
		m_strCompID = strCompID;
	}


	public void setCompName(String strCompName) {
		m_strCompName = strCompName;
	}


	/**
	 * @param nOrgType
	 */
	public void setOrgType(int nOrgType) {
		m_nOrgType = nOrgType;
	}


	/**
	 * @param nType
	 */
	public void setNType(int nType) {
		m_nType = nType;
	}
	public void setnType(int nType) {
		m_nType = nType;
	}


	public void setDeptID(String strDeptID) {
		m_strDeptID = strDeptID;
	}


	public void setDeptName(String strDeptName) {
		m_strDeptName = strDeptName;
	}


	public void setDescription(String strDescription) {
		m_strDescription = strDescription;
	}


	public void setDuty(String strDuty) {
		m_strDuty = strDuty;
	}


	public void setEmail(String strEmail) {
		m_strEmail = strEmail;
	}


	public void setEmployeeID(String strEmployeeID) {
		m_strEmployeeID = strEmployeeID;
	}


	public void setGradeAbbrName(String strGradeAbbrName) {
		m_strGradeAbbrName = strGradeAbbrName;
	}


	public void setGradeCode(String strGradeCode) {
		m_strGradeCode = strGradeCode;
	}


	public void setGradeName(String strGradeName) {
		m_strGradeName = strGradeName;
	}


	public void setGradeOtherName(String strGradeOtherName) {
		m_strGradeOtherName = strGradeOtherName;
	}


	public void setGroupID(String strGroupID) {
		m_strGroupID = strGroupID;
	}


	public void setGroupName(String strGroupName) {
		m_strGroupName = strGroupName;
	}


	public void setHomeAddr(String strHomeAddr) {
		m_strHomeAddr = strHomeAddr;
	}


	public void setHomeDetailAddr(String strHomeDetailAddr) {
		m_strHomeDetailAddr = strHomeDetailAddr;
	}


	public void setHomeFax(String strHomeFax) {
		m_strHomeFax = strHomeFax;
	}


	public void setHomePage(String strHomePage) {
		m_strHomePage = strHomePage;
	}


	public void setHomeTel(String strHomeTel) {
		m_strHomeTel = strHomeTel;
	}


	public void setHomeTel2(String strHomeTel2) {
		m_strHomeTel2 = strHomeTel2;
	}


	public void setHomeZipCode(String strHomeZipCode) {
		m_strHomeZipCode = strHomeZipCode;
	}


	public void setMobile(String strMobile) {
		m_strMobile = strMobile;
	}


	public void setMobile2(String strMobile2) {
		m_strMobile2 = strMobile2;
	}


	public void setOfficeAddr(String strOfficeAddr) {
		m_strOfficeAddr = strOfficeAddr;
	}


	public void setOfficeDetailAddr(String strOfficeDetailAddr) {
		m_strOfficeDetailAddr = strOfficeDetailAddr;
	}


	public void setOfficeFax(String strOfficeFax) {
		m_strOfficeFax = strOfficeFax;
	}


	public void setOfficeTel(String strOfficeTel) {
		m_strOfficeTel = strOfficeTel;
	}


	/**
	 */
	public void setOfficeTel2(String strOfficeTel2) {
		m_strOfficeTel2 = strOfficeTel2;
	}


	/**
	 */
	public void setOfficeZipCode(String strOfficeZipCode) {
		m_strOfficeZipCode = strOfficeZipCode;
	}


	/**
	 */
	public void setOrgDisplayName(String strOrgDisplayName) {
		m_strOrgDisplayName = strOrgDisplayName;
	}


	/**
	 */
	public void setPager(String strPager) {
		m_strPager = strPager;
	}


	/**
	 */
	public void setPartID(String strPartID) {
		m_strPartID = strPartID;
	}


	/**
	 */
	public void setPartName(String strPartName) {
		m_strPartName = strPartName;
	}


	/**
	 */
	public void setPCOnlineID(String strPCOnlineID) {
		m_strPCOnlineID = strPCOnlineID;
	}


	/**
	 */
	public void setPositionAbbrName(String strPositionAbbrName) {
		m_strPositionAbbrName = strPositionAbbrName;
	}


	/**
	 */
	public void setPositionCode(String strPositionCode) {
		m_strPositionCode = strPositionCode;
	}


	/**
	 */
	public void setPositionName(String strPositionName) {
		m_strPositionName = strPositionName;
	}


	/**
	 */
	public void setPositionOtherName(String strPositionOtherName) {
		m_strPositionOtherName = strPositionOtherName;
	}


	/**
	 */
	public void setReserved1(String strReserved1) {
		m_strReserved1 = strReserved1;
	}


	/**
	 */
	public void setReserved2(String strReserved2) {
		m_strReserved2 = strReserved2;
	}


	/**
	 */
	public void setReserved3(String strReserved3) {
		m_strReserved3 = strReserved3;
	}


	/**
	 */
	public void setResidentNo(String strResidentNo) {
		m_strResidentNo = strResidentNo;
	}


	/**
	 * @param strRoleCodes Role Code
	 */
	public void setRoleCodes(String strRoleCodes) {
		m_strRoleCodes = strRoleCodes;
	}


	/**
	 */
	public void setSysMail(String strSysMail) {
		m_strSysMail = strSysMail;
	}


	/**
	 */
	public void setTitleCode(String strTitleCode) {
		m_strTitleCode = strTitleCode;
	}


	/**
	 */
	public void setTitleName(String strTitleName) {
		m_strTitleName = strTitleName;
	}


	/**
	 */
	public void setTitleOtherName(String strTitleOtherName) {
		m_strTitleOtherName = strTitleOtherName;
	}


	/**
	 */
	public void setUserID(String strUserID) {
		m_strUserID = strUserID;
	}


	/**
	 */
	public void setUserIDs(String strUserIDs) {
		m_strUserIDs = strUserIDs;
	}


	/**
	 */
	public void setUserName(String strUserName) {
		m_strUserName = strUserName;
	}


	/**
	 */
	public void setUserOtherName(String strUserOtherName) {
		m_strUserOtherName = strUserOtherName;
	}


	/**
	 */
	public void setUserRID(String strUserRID) {
		m_strUserRID = strUserRID;
	}


	/**
	 */
	public void setUserStatus(String strUserStatus) {
		m_strUserStatus = strUserStatus;
	}


	/**
	 * @param strUserUID The m_strUserUID to set
	 */
	public void setUserUID(String strUserUID) {
		m_strUserUID = strUserUID;
	}


	/**
	 */
	public void setDutyOrder(int nDutyOrder) {
		m_nDutyOrder = nDutyOrder;
	}


	/**
	 */
	public void setDutyCode(String strDutyCode) {
		m_strDutyCode = strDutyCode;
	}


	/**
	 */
	public void setDutyName(String strDutyName) {
		m_strDutyName = strDutyName;
	}


	/**
	 * @return String
	 */
	public String getChangedPWDDate() {
		return m_strChangedPWDDate;
	}


	/**
	 * @return boolean
	 */
	public boolean isConcurrent() {
		return m_bConcurrent;
	}


	/**
	 * @return boolean
	 */
	public boolean isDelegate() {
		return m_bDelegate;
	}


	/**
	 * @return boolean
	 */
	public boolean isDeleted() {
		return m_bDeleted;
	}


	/**
	 * @return boolean
	 */
	public boolean isExistence() {
		return m_bExistence;
	}


	/**
	 * @return boolean
	 */
	public boolean isProxy() {
		return m_bProxy;
	}


	/**
	 * @return int
	 */
	public int getGradeOrder() {
		return m_nGradeOrder;
	}


	/**
	 * @return int
	 */
	public int getPositionOrder() {
		return m_nPositionOrder;
	}


	/**
	 * @return int
	 */
	public int getSecurityLevel() {
		return m_nSecurityLevel;
	}


	/**
	 * @return int
	 */
	public int getTitleOrder() {
		return m_nTitleOrder;
	}


	/**
	 * @return int
	 */
	public int getUserOrder() {
		return m_nUserOrder;
	}


	/**
	 * @return String
	 */
	public String getCompID() {
		return m_strCompID;
	}


	/**
	 * @return String
	 */
	public String getCompName() {
		return m_strCompName;
	}


	/**
	 * @return int
	 */
	public int getOrgType() {
		return m_nOrgType;
	}


	/**
	 * @return int
	 */
	public int getNType() {
		return m_nType;
	}
	public int getnType() {
		return m_nType;
	}


	/**
	 * @return String
	 */
	public String getDeptID() {
		return m_strDeptID;
	}


	/**
	 * @return String
	 */
	public String getDeptName() {
		return m_strDeptName;
	}


	/**
	 * @return String
	 */
	public String getDescription() {
		return m_strDescription;
	}


	/**
	 * @return String
	 */
	public String getDuty() {
		return m_strDuty;
	}


	/**
	 * @return String
	 */
	public String getEmail() {
		return m_strEmail;
	}


	/**
	 * @return String
	 */
	public String getEmployeeID() {
		return m_strEmployeeID;
	}


	/**
	 * @return String
	 */
	public String getGradeAbbrName() {
		return m_strGradeAbbrName;
	}


	/**
	 * @return String
	 */
	public String getGradeCode() {
		return m_strGradeCode;
	}


	/**
	 * @return String
	 */
	public String getGradeName() {
		return m_strGradeName;
	}


	/**
	 * @return String
	 */
	public String getGradeOtherName() {
		return m_strGradeOtherName;
	}


	/**
	 * @return String
	 */
	public String getGroupID() {
		return m_strGroupID;
	}


	/**
	 * @return String
	 */
	public String getGroupName() {
		return m_strGroupName;
	}


	/**
	 * @return String
	 */
	public String getHomeAddr() {
		return m_strHomeAddr;
	}


	/**
	 * @return String
	 */
	public String getHomeDetailAddr() {
		return m_strHomeDetailAddr;
	}


	/**
	 * @return String
	 */
	public String getHomeFax() {
		return m_strHomeFax;
	}


	/**
	 * @return String
	 */
	public String getHomePage() {
		return m_strHomePage;
	}


	/**
	 * @return String
	 */
	public String getHomeTel() {
		return m_strHomeTel;
	}


	/**
	 * @return String
	 */
	public String getHomeTel2() {
		return m_strHomeTel2;
	}


	/**
	 * @return String
	 */
	public String getHomeZipCode() {
		return m_strHomeZipCode;
	}


	/**
	 * @return String
	 */
	public String getMobile() {
		return m_strMobile;
	}


	/**
	 * @return String
	 */
	public String getMobile2() {
		return m_strMobile2;
	}


	/**
	 * @return String
	 */
	public String getOfficeAddr() {
		return m_strOfficeAddr;
	}


	/**
	 * @return String
	 */
	public String getOfficeDetailAddr() {
		return m_strOfficeDetailAddr;
	}


	/**
	 * @return String
	 */
	public String getOfficeFax() {
		return m_strOfficeFax;
	}


	/**
	 * @return String
	 */
	public String getOfficeTel() {
		return m_strOfficeTel;
	}


	/**
	 * @return String
	 */
	public String getOfficeTel2() {
		return m_strOfficeTel2;
	}


	/**
	 * @return String
	 */
	public String getOfficeZipCode() {
		return m_strOfficeZipCode;
	}


	/**
	 * @return String
	 */
	public String getOrgDisplayName() {
		return m_strOrgDisplayName;
	}


	/**
	 * @return String
	 */
	public String getPager() {
		return m_strPager;
	}


	/**
	 * @return String
	 */
	public String getPartID() {
		return m_strPartID;
	}


	/**
	 * @return String
	 */
	public String getPartName() {
		return m_strPartName;
	}


	/**
	 * @return String
	 */
	public String getPCOnlineID() {
		return m_strPCOnlineID;
	}


	/**
	 * @return String
	 */
	public String getPositionAbbrName() {
		return m_strPositionAbbrName;
	}


	/**
	 * @return String
	 */
	public String getPositionCode() {
		return m_strPositionCode;
	}


	/**
	 * @return String
	 */
	public String getPositionName() {
		return m_strPositionName;
	}


	/**
	 * @return String
	 */
	public String getPositionOtherName() {
		return m_strPositionOtherName;
	}


	/**
	 * @return String
	 */
	public String getReserved1() {
		return m_strReserved1;
	}


	/**
	 * @return String
	 */
	public String getReserved2() {
		return m_strReserved2;
	}


	/**
	 * @return String
	 */
	public String getReserved3() {
		return m_strReserved3;
	}


	/**
	 * @return String
	 */
	public String getResidentNo() {
		return m_strResidentNo;
	}


	/**
	 * @return String
	 */
	public String getRoleCodes() {
		return m_strRoleCodes;
	}


	/**
	 * @return String
	 */
	public String getSysMail() {
		return m_strSysMail;
	}


	/**
	 * @return String
	 */
	public String getTitleCode() {
		return m_strTitleCode;
	}


	/**
	 * @return String
	 */
	public String getTitleName() {
		return m_strTitleName;
	}


	/**
	 * @return String
	 */
	public String getTitleOtherName() {
		return m_strTitleOtherName;
	}


	/**
	 * @return String
	 */
	public String getUserID() {
		return m_strUserID;
	}


	/**
	 * @return String
	 */
	public String getUserIDs() {
		return m_strUserIDs;
	}


	/**
	 * @return String
	 */
	public String getUserName() {
		return m_strUserName;
	}


	/**
	 * @return String
	 */
	public String getUserOtherName() {
		return m_strUserOtherName;
	}


	/**
	 * @return String
	 */
	public String getUserRID() {
		return m_strUserRID;
	}


	/**
	 * @return String
	 */
	public String getUserStatus() {
		return m_strUserStatus;
	}


	/**
	 * @return String
	 */
	public String getUserUID() {
		return m_strUserUID;
	}


	/**
	 * @return String
	 */
	public String getMailServer() {
		return m_strMailServer;
	}


	/**
	 * @return int
	 */
	public int getLoginResult() {
		return m_nLoginResult;
	}


	/**
	 * @return boolean
	 */
	public boolean getCaseSensitive() {
		return m_bCaseSensitive;
	}


	/**
	 * @return int
	 */
	public int getNScope() {
		return m_nScope;
	}


	/**
	 * @return String
	 */
	public String getCertificationID() {
		return m_strCertificationID;
	}


	/**
	 * @return int
	 */
	public int getDutyOrder() {
		return m_nDutyOrder;
	}


	/**
	 * @return String
	 */
	public String getDutyCode() {
		return m_strDutyCode;
	}


	/**
	 * @return String
	 */
	public String getDutyName() {
		return m_strDutyName;
	}


	/**
	 * @return String
	 */
	public String getGroupOtherName() {
		return m_strGroupOtherName;
	}


	/**
	 * @return String
	 */
	public String getCompOtherName() {
		return m_strCompOtherName;
	}


	/**
	 * @return String
	 */
	public String getDeptOtherName() {
		return m_strDeptOtherName;
	}


	/**
	 * @return String
	 */
	public String getPartOtherName() {
		return m_strPartOtherName;
	}


	/**
	 * @return String
	 */
	public String getOrgDisplayOtherName() {
		return m_strOrgDisplayOtherName;
	}


	/**
	 * @return String
	 */
	public String getDutyOtherName() {
		return m_strDutyOtherName;
	}


	/**
	 * @return String
	 */
	public String getOptionalGTPName() {
		return m_strOptionalGTPName;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + ", UserBasicVO [list_type=" + list_type + ", m_bCaseSensitive=" + m_bCaseSensitive + ", m_bConcurrent=" + m_bConcurrent
		    + ", m_bDelegate=" + m_bDelegate + ", m_bDeleted=" + m_bDeleted + ", m_bExistence=" + m_bExistence + ", m_bProxy=" + m_bProxy
		    + ", m_nDutyOrder=" + m_nDutyOrder + ", m_nGradeOrder=" + m_nGradeOrder + ", m_nLoginResult=" + m_nLoginResult
		    + ", m_nOrgType=" + m_nOrgType + ", m_nPositionOrder=" + m_nPositionOrder + ", m_nScope=" + m_nScope + ", m_nSecurityLevel="
		    + m_nSecurityLevel + ", m_nTitleOrder=" + m_nTitleOrder + ", m_nType=" + m_nType + ", m_nUserOrder=" + m_nUserOrder
		    + ", m_strCertificationID=" + m_strCertificationID + ", m_strChangedPWDDate=" + m_strChangedPWDDate + ", m_strCompID="
		    + m_strCompID + ", m_strCompName=" + m_strCompName + ", m_strCompOtherName=" + m_strCompOtherName + ", m_strDeptID="
		    + m_strDeptID + ", m_strDeptName=" + m_strDeptName + ", m_strDeptOtherName=" + m_strDeptOtherName + ", m_strDescription="
		    + m_strDescription + ", m_strDuty=" + m_strDuty + ", m_strDutyCode=" + m_strDutyCode + ", m_strDutyName=" + m_strDutyName
		    + ", m_strDutyOtherName=" + m_strDutyOtherName + ", m_strEmail=" + m_strEmail + ", m_strEmployeeID=" + m_strEmployeeID
		    + ", m_strGradeAbbrName=" + m_strGradeAbbrName + ", m_strGradeCode=" + m_strGradeCode + ", m_strGradeName=" + m_strGradeName
		    + ", m_strGradeOtherName=" + m_strGradeOtherName + ", m_strGroupID=" + m_strGroupID + ", m_strGroupName=" + m_strGroupName
		    + ", m_strGroupOtherName=" + m_strGroupOtherName + ", m_strHomeAddr=" + m_strHomeAddr + ", m_strHomeDetailAddr="
		    + m_strHomeDetailAddr + ", m_strHomeFax=" + m_strHomeFax + ", m_strHomePage=" + m_strHomePage + ", m_strHomeTel="
		    + m_strHomeTel + ", m_strHomeTel2=" + m_strHomeTel2 + ", m_strHomeZipCode=" + m_strHomeZipCode + ", m_strMailServer="
		    + m_strMailServer + ", m_strMobile=" + m_strMobile + ", m_strMobile2=" + m_strMobile2 + ", m_strOfficeAddr=" + m_strOfficeAddr
		    + ", m_strOfficeDetailAddr=" + m_strOfficeDetailAddr + ", m_strOfficeFax=" + m_strOfficeFax + ", m_strOfficeTel="
		    + m_strOfficeTel + ", m_strOfficeTel2=" + m_strOfficeTel2 + ", m_strOfficeZipCode=" + m_strOfficeZipCode
		    + ", m_strOptionalGTPName=" + m_strOptionalGTPName + ", m_strOrgDisplayName=" + m_strOrgDisplayName
		    + ", m_strOrgDisplayOtherName=" + m_strOrgDisplayOtherName + ", m_strPCOnlineID=" + m_strPCOnlineID + ", m_strPager="
		    + m_strPager + ", m_strPartID=" + m_strPartID + ", m_strPartName=" + m_strPartName + ", m_strPartOtherName="
		    + m_strPartOtherName + ", m_strPositionAbbrName=" + m_strPositionAbbrName + ", m_strPositionCode=" + m_strPositionCode
		    + ", m_strPositionName=" + m_strPositionName + ", m_strPositionOtherName=" + m_strPositionOtherName + ", m_strReserved1="
		    + m_strReserved1 + ", m_strReserved2=" + m_strReserved2 + ", m_strReserved3=" + m_strReserved3 + ", m_strResidentNo="
		    + m_strResidentNo + ", m_strRoleCodes=" + m_strRoleCodes + ", m_strSysMail=" + m_strSysMail + ", m_strTitleCode="
		    + m_strTitleCode + ", m_strTitleName=" + m_strTitleName + ", m_strTitleOtherName=" + m_strTitleOtherName + ", m_strUserID="
		    + m_strUserID + ", m_strUserIDs=" + m_strUserIDs + ", m_strUserName=" + m_strUserName + ", m_strUserOtherName="
		    + m_strUserOtherName + ", m_strUserRID=" + m_strUserRID + ", m_strUserStatus=" + m_strUserStatus + ", m_strUserUID="
		    + m_strUserUID + "]";
	}
}
