package com.sds.acube.luxor.common.vo;

import java.sql.Timestamp;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.util.TimestampAdapter;
import com.sds.acube.luxor.common.vo.MessageVO;
import com.sds.acube.luxor.framework.config.LuxorConfig;

public class PageStatisticsVO extends MessageVO {

	private String statisticsId = "";
	private String pageId = "";
	private String pageName = "";
	private Timestamp accessTime;
	private String accessUserId = "";
	private String accessUserName = "";
	private String fromDate = "";
	private String toDate = "";
	private String searchTxt = "";
	private String userAgent = "";		// user Connect 정보
	private String[] statisticsIds;
	private String loginIp = "";
	private String searchType = "";
	private String userStatus = "";

	public String getAccessUserName() {
    	return accessUserName;
    }
	public void setAccessUserName(String accessUserName) {
    	this.accessUserName = accessUserName;
    }
	public String getPageName() {
    	return pageName;
    }
	public void setPageName(String pageName) {
    	this.pageName = pageName;
    }
	public String getStatisticsId() {
		return statisticsId;
	}
	public void setStatisticsId(String statisticsId) {
		this.statisticsId = statisticsId;
	}
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getAccessTime() {
		return accessTime;
	}
    public String getAccessTime2String() {
		if(this.accessTime==null) {
			return "";
		}
		String datePattern = LuxorConfig.getEnvString(tenantId, portalId, "DATE_PATTERN");;
    	return CommonUtil.formatDate(accessTime, datePattern);
    }
	public void setAccessTime(Timestamp accessTime) {
		this.accessTime = accessTime;
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
	public String getSearchTxt() {
		return searchTxt;
	}
	public void setSearchTxt(String searchTxt) {
		this.searchTxt = searchTxt;
	}
	public String getAccessUserId() {
		return accessUserId;
	}
	public void setAccessUserId(String accessUserId) {
		this.accessUserId = accessUserId;
	}
	
	public String[] getStatisticsIds() {
    	return statisticsIds;
    }
	public void setStatisticsIds(String[] statisticsIds) {
    	this.statisticsIds = statisticsIds;
    }
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
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
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	@Override
	public String toString() {
		return super.toString() + ", PageStatisticsVO [accessTime=" + accessTime + ", fromDate="
				+ fromDate + ", pageId=" + pageId
				+ ", accessUserId=" + accessUserId + ", searchTxt=" + searchTxt
				+ ", statisticsId=" + statisticsId + ", toDate=" + toDate + "]";
	}
	
}
