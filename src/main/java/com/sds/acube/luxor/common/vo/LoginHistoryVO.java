package com.sds.acube.luxor.common.vo;

import java.sql.Timestamp;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.util.TimestampAdapter;
import com.sds.acube.luxor.framework.config.LuxorConfig;

public class LoginHistoryVO extends MessageVO {
	
	private String statisticsId = "";
	private String[] statisticsIds;
	private String sessionId = "";
	private String userUid = "";
	private String loginId = "";
	private String userName = "";
	private String loginIp = "";
	private Timestamp loginTime;
	private Timestamp logoutTime;
	private String compName = "";
	private String dptName = "";
	private String gradeName = "";
	private String userAgent = "";		// user Connect 정보
	private String searchType = "";
	private String searchTxt = "";
	private String userStatus = "";
	private String fromDate;
	private String toDate;
    
	public String[] getStatisticsIds() {
    	return statisticsIds;
    }
	public void setStatisticsIds(String[] statisticsIds) {
    	this.statisticsIds = statisticsIds;
    }
	public String getStatisticsId() {
		return statisticsId;
	}
	public void setStatisticsId(String statisticsId) {
		this.statisticsId = statisticsId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getUserUid() {
		return userUid;
	}
	public void setUserUid(String userUid) {
		this.userUid =userUid;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getLoginTime() {
    	return loginTime;
    }
    public String getLoginTime2String() {
		if(this.loginTime==null) {
			return "";
		}
		String datePattern = LuxorConfig.getEnvString(tenantId, portalId, "DATE_PATTERN");;
    	return CommonUtil.formatDate(loginTime, datePattern);
    }
	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getLogoutTime() {
		return logoutTime;
	}
    public String getLogOutTime2String() {
		if(this.logoutTime==null) {
			return "";
		}
		String datePattern = LuxorConfig.getEnvString(tenantId, portalId, "DATE_PATTERN");;
    	return CommonUtil.formatDate(logoutTime, datePattern);
    }
	public void setLogoutTime(Timestamp logoutTime) {
		this.logoutTime = logoutTime;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getDptName() {
		return dptName;
	}
	public void setDptName(String dptName) {
		this.dptName = dptName;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchTxt() {
		return searchTxt;
	}
	public void setSearchTxt(String searchTxt) {
		this.searchTxt = searchTxt;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", LoginHistoryVO [compName=" + compName + ", dptName=" + dptName
				+ ", fromDate=" + fromDate + ", gradeName=" + gradeName
				+ ", loginId=" + loginId + ", loginIp=" + loginIp
				+ ", loginTime=" + loginTime + ", logoutTime=" + logoutTime
				+ ", searchTxt=" + searchTxt + ", searchType=" + searchType
				+ ", sessionId=" + sessionId + ", statisticsId=" + statisticsId
				+ ", toDate=" + toDate + ", userAgent=" + userAgent
				+ ", userName=" + userName + ", userStatus=" + userStatus
				+ ", userUid=" + userUid + "]";
	}
	
}
