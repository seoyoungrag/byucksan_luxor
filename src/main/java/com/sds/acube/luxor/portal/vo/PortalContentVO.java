package com.sds.acube.luxor.portal.vo;

import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.vo.MessageVO;

public class PortalContentVO extends MessageVO {
	private String parentId = ""; 
	private String contentId = "";
	private String contentNameId = "";
	private String portletId = "";
	private String contentStyle = "";
	private String contentDesc = "";
	private String isActive = "Y";
	private String usePersonal = "N";
	private int typeOfPortlet=0;
	
	
	public String getUsePersonal() {
    	return usePersonal;
    }
	public void setUsePersonal(String usePersonal) {
    	this.usePersonal = usePersonal;
    }
	private int seq = 0;
	
	public String getContentStyle() {
    	return contentStyle;
    }
	public void setContentStyle(String contentStyle) {
    	this.contentStyle = contentStyle;
    }
	public String getParentId() {
    	return parentId;
    }
	public void setParentId(String parentId) {
    	this.parentId = parentId;
    }
	public String getContentId() {
    	return contentId;
    }
	public void setContentId(String contentId) {
    	this.contentId = contentId;
    }
	public String getContentNameId() {
    	return contentNameId;
    }
	public void setContentNameId(String contentNameId) {
    	this.contentNameId = contentNameId;
    }
	public String getPortletId() {
    	return portletId;
    }
	public void setPortletId(String portletId) {
    	this.portletId = portletId;
    }
	public String getContentDesc() {
    	return CommonUtil.escapeSpecialChar(contentDesc);
    }
	public void setContentDesc(String contentDesc) {
    	this.contentDesc = contentDesc;
    }
	public String getIsActive() {
    	return isActive;
    }
	public void setIsActive(String isActive) {
    	this.isActive = isActive;
    }
	public int getSeq() {
    	return seq;
    }
	public void setSeq(int seq) {
    	this.seq = seq;
    }
	
	public int getTypeOfPortlet() {
    	return typeOfPortlet;
    }
	public void setTypeOfPortlet(int typeOfPortlet) {
    	this.typeOfPortlet = typeOfPortlet;
    }
		
	
	@Override
    public String toString() {
	    return super.toString() + ", PortalContentVO [contentDesc=" + contentDesc + ", contentId=" + contentId + ", contentNameId=" + contentNameId
	        + ", contentStyle=" + contentStyle + ", isActive=" + isActive + ", parentId=" + parentId + ", portletId=" + portletId
	        + ", seq=" + seq + "]";
    }
	
}
