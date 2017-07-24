package com.sds.acube.luxor.portal.vo;

import com.sds.acube.luxor.common.vo.CommonVO;

public class LoginPlugVO extends CommonVO {
	private String plugId;
	private String plugTitle;
	private String plugUrl;
	private String plugType;
	private String startDate;
	private String endDate;
	private String isActive;
	private String isExternal;
	
	public String getStartDate() {
    	return startDate;
    }
	public void setStartDate(String startDate) {
    	this.startDate = startDate;
    }
	public String getEndDate() {
    	return endDate;
    }
	public void setEndDate(String endDate) {
    	this.endDate = endDate;
    }
	public String getPlugId() {
    	return plugId;
    }
	public void setPlugId(String plugId) {
    	this.plugId = plugId;
    }
	public String getPlugTitle() {
    	return plugTitle;
    }
	public void setPlugTitle(String plugTitle) {
    	this.plugTitle = plugTitle;
    }
	public String getPlugUrl() {
    	return plugUrl;
    }
	public void setPlugUrl(String plugUrl) {
    	this.plugUrl = plugUrl;
    }
	public String getPlugType() {
    	return plugType;
    }
	public void setPlugType(String plugType) {
    	this.plugType = plugType;
    }
	public String getIsActive() {
    	return isActive;
    }
	public void setIsActive(String isActive) {
    	this.isActive = isActive;
    }
	public String getIsExternal() {
		return isExternal;
	}
	public void setIsExternal(String isExternal) {
		this.isExternal = isExternal;
	}
	@Override
	public String toString() {
		return "LoginPlugVO [endDate=" + endDate + ", isActive=" + isActive
				+ ", plugId=" + plugId + ", plugTitle=" + plugTitle
				+ ", plugType=" + plugType + ", plugUrl=" + plugUrl
				+ ", startDate=" + startDate + "]";
	}
	
}
