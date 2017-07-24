package com.sds.acube.luxor.portal.vo;

import com.sds.acube.luxor.common.vo.CommonVO;

public class LoginPopupVO extends CommonVO {
	private String popupId;
	private String popupTitle;
	private String popupUrl;
	private String popupType;
	private String startDate;
	private String endDate;
	private String isActive;
	private String windowWidth;
	private String windowHeight;
	private String positionTop;
	private String positionLeft;
	
	
	public String getWindowWidth() {
    	return windowWidth;
    }
	public void setWindowWidth(String windowWidth) {
    	this.windowWidth = windowWidth;
    }
	public String getWindowHeight() {
    	return windowHeight;
    }
	public void setWindowHeight(String windowHeight) {
    	this.windowHeight = windowHeight;
    }
	public String getPositionTop() {
    	return positionTop;
    }
	public void setPositionTop(String positionTop) {
    	this.positionTop = positionTop;
    }
	public String getPositionLeft() {
    	return positionLeft;
    }
	public void setPositionLeft(String positionLeft) {
    	this.positionLeft = positionLeft;
    }
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
	public String getPopupId() {
    	return popupId;
    }
	public void setPopupId(String popupId) {
    	this.popupId = popupId;
    }
	public String getPopupTitle() {
    	return popupTitle;
    }
	public void setPopupTitle(String popupTitle) {
    	this.popupTitle = popupTitle;
    }
	public String getPopupUrl() {
    	return popupUrl;
    }
	public void setPopupUrl(String popupUrl) {
    	this.popupUrl = popupUrl;
    }
	public String getPopupType() {
    	return popupType;
    }
	public void setPopupType(String popupType) {
    	this.popupType = popupType;
    }
	public String getIsActive() {
    	return isActive;
    }
	public void setIsActive(String isActive) {
    	this.isActive = isActive;
    }
	
	@Override
    public String toString() {
	    return super.toString() + ", LoginPopupVO [popupId=" + popupId + ", popupTitle=" + popupTitle + ", popupUrl=" + popupUrl + ", popupType=" + popupType
	        + ", startDate=" + startDate + ", endDate=" + endDate + ", isActive=" + isActive + ", windowWidth=" + windowWidth
	        + ", windowHeight=" + windowHeight + ", positionTop=" + positionTop + ", positionLeft=" + positionLeft + "]";
    }
}
