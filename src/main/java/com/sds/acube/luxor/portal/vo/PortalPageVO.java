package com.sds.acube.luxor.portal.vo;

import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.vo.MessageVO;

public class PortalPageVO extends MessageVO {
	private String pageId = "";
	private String pageLayoutId = "";
	private String pageThemeId = "";
	private String pageDesc = "";
	private String isHome = "";
	private String isPersonal = "";
	private String isActive = "";
	private String isTemplate = "";
	private String pageIdPath = "";
	private String pageNamePath="";
	
	public String getIsTemplate() {
    	return isTemplate;
    }
	public void setIsTemplate(String isTemplate) {
    	this.isTemplate = isTemplate;
    }
	public String getPageId() {
    	return pageId;
    }
	public void setPageId(String pageId) {
    	this.pageId = pageId;
    }
	public String getPageLayoutId() {
    	return pageLayoutId;
    }
	public void setPageLayoutId(String pageLayoutId) {
    	this.pageLayoutId = pageLayoutId;
    }
	public String getPageThemeId() {
    	return pageThemeId;
    }
	public void setPageThemeId(String pageThemeId) {
    	this.pageThemeId = pageThemeId;
    }
	public String getPageDesc() {
    	return CommonUtil.escapeSpecialChar(pageDesc);
    }
	public void setPageDesc(String pageDesc) {
    	this.pageDesc = pageDesc;
    }
	public String getIsHome() {
    	return isHome;
    }
	public void setIsHome(String isHome) {
    	this.isHome = isHome;
    }
	public String getIsPersonal() {
    	return isPersonal;
    }
	public void setIsPersonal(String isPersonal) {
    	this.isPersonal = isPersonal;
    }
	public String getIsActive() {
    	return isActive;
    }
	public void setIsActive(String isActive) {
    	this.isActive = isActive;
    }
	
	public String getPageIdPath() {
    	return pageIdPath;
    }
	public void setPageIdPath(String pageIdPath) {
    	this.pageIdPath = pageIdPath;
    }
	
	public String getPageNamePath() {
    	return pageNamePath;
    }
	public void setPageNamePath(String pageNamePath) {
    	this.pageNamePath = pageNamePath;
    }	
	
	@Override
    public String toString() {
	    return super.toString() + ", PortalPageVO [pageId=" + pageId + ", pageLayoutId=" + pageLayoutId + ", pageThemeId=" + pageThemeId + ", pageDesc="
	        + pageDesc + ", isHome=" + isHome + ", isPersonal=" + isPersonal + ", isActive=" + isActive + ", isTemplate=" + isTemplate
	        + "]";
    }
}
