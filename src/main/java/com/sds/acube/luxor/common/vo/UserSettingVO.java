package com.sds.acube.luxor.common.vo;

public class UserSettingVO extends CommonVO {
	private String userId;
	private String settingCode;
	private String settingValue;
	
	public String getUserId() {
    	return userId;
    }
	public void setUserId(String userId) {
    	this.userId = userId;
    }
	public String getSettingCode() {
    	return settingCode;
    }
	public void setSettingCode(String settingCode) {
    	this.settingCode = settingCode;
    }
	public String getSettingValue() {
    	return settingValue;
    }
	public void setSettingValue(String settingValue) {
    	this.settingValue = settingValue;
    }
	
	@Override
    public String toString() {
	    return super.toString() + ", UserSettingVO [userId=" + userId + ", settingCode=" + settingCode + ", settingValue=" + settingValue + "]";
    }
}
