package com.sds.acube.luxor.portal.vo;


public class PortalPageZoneVO extends PortalContentVO {
	private String pageId = "";
	private String zoneId = "";
	private String contentId = "";
	private String contentStyleForEach = "";
	private String isAutoHeight = "N";
	private String isFixed = "N";
	private String isDeleted = "N";
	private String refer = "";
	private int seq = 0;
	
	public String getIsDeleted() {
    	return isDeleted;
    }
	public void setIsDeleted(String isDeleted) {
    	this.isDeleted = isDeleted;
    }
	public String getIsFixed() {
    	return isFixed;
    }
	public void setIsFixed(String isFixed) {
    	this.isFixed = isFixed;
    }
	public String getRefer() {
    	return refer;
    }
	public void setRefer(String refer) {
    	this.refer = refer;
    }
	public String getIsAutoHeight() {
    	return isAutoHeight;
    }
	public void setIsAutoHeight(String isAutoHeight) {
    	this.isAutoHeight = isAutoHeight;
    }
	public int getSeq() {
    	return seq;
    }
	public void setSeq(int seq) {
    	this.seq = seq;
    }
	public String getPageId() {
    	return pageId;
    }
	public void setPageId(String pageId) {
    	this.pageId = pageId;
    }
	public String getZoneId() {
    	return zoneId;
    }
	public void setZoneId(String zoneId) {
    	this.zoneId = zoneId;
    }
	public String getContentId() {
    	return contentId;
    }
	public void setContentId(String contentId) {
    	this.contentId = contentId;
    }
	public String getContentStyleForEach() {
    	return contentStyleForEach;
    }
	public void setContentStyleForEach(String contentStyleForEach) {
    	this.contentStyleForEach = contentStyleForEach;
    }
	
	@Override
    public String toString() {
	    return super.toString() + ", PortalPageZoneVO [contentId=" + contentId + ", contentStyleForEach=" + contentStyleForEach + ", pageId=" + pageId
	        + ", seq=" + seq + ", zoneId=" + zoneId + "]";
    }
	
	
}
