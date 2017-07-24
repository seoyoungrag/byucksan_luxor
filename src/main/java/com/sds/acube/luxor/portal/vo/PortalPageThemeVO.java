package com.sds.acube.luxor.portal.vo;

import com.sds.acube.luxor.common.vo.MessageVO;

public class PortalPageThemeVO extends MessageVO {
	private String themeId = "";
	private String themeNameId = "";
	private String themeCssUrl = "";
	private String themeImageUrl = "";
	private String isDefault = "";
	
	public String getThemeId() {
    	return themeId;
    }
	public void setThemeId(String themeId) {
    	this.themeId = themeId;
    }
	public String getThemeNameId() {
    	return themeNameId;
    }
	public void setThemeNameId(String themeNameId) {
    	this.themeNameId = themeNameId;
    }
	public String getThemeCssUrl() {
    	return themeCssUrl;
    }
	public void setThemeCssUrl(String themeCssUrl) {
    	this.themeCssUrl = themeCssUrl;
    }
	public String getThemeImageUrl() {
		return themeImageUrl;
	}
	public void setThemeImageUrl(String themeImageUrl) {
		this.themeImageUrl = themeImageUrl;
	}
	public String getIsDefault() {
    	return isDefault;
    }
	public void setIsDefault(String isDefault) {
    	this.isDefault = isDefault;
    }
	
}
