package com.sds.acube.luxor.portal.vo;


public class TabPortletVO extends PortalContentVO {
	private String pageId = "";
	private String tabPortletId = "";
	private String contentId = "";
	private String portletId = "";	
	private int seq = 0;
	

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
	public String getTabPortletId() {
    	return tabPortletId;
    }
	public void setTabPortletId(String tabPortletId) {
    	this.tabPortletId = tabPortletId;
    }
	public String getContentId() {
    	return contentId;
    }
	public void setContentId(String contentId) {
    	this.contentId = contentId;
    }
	
	public String getPortletId() {
    	return portletId;
    }
	public void setPortletId(String portletId) {
    	this.portletId = portletId;
    }

	
	@Override
    public String toString() {
	    return super.toString() + ", PortalPageZoneVO [contentId=" + contentId +  ", pageId=" + pageId
	        + ", seq=" + seq + ", tabPortletId=" + tabPortletId+ ", portletId=" + portletId + "]";
    }
	
	
}
