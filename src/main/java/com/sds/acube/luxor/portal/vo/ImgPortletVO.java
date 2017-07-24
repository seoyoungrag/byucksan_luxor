package com.sds.acube.luxor.portal.vo;

import com.sds.acube.luxor.common.vo.MessageVO;

public class ImgPortletVO extends MessageVO {
	private String tabPortletId = "";
	private String contentId = "";
	private String portletId = "";	
	private int seq = 0;
	private String deployDescriptionXml = "";
	private PortletContextVO portletContext = new PortletContextVO();
	private int typeOfPortlet = 0;
	
	public int getSeq() {
    	return seq;
    }
	public void setSeq(int seq) {
    	this.seq = seq;
    }
	public String getDeployDescriptionXml() {
    	return deployDescriptionXml;
    }
	public void setDeployDescriptionXml(String deployDescriptionXml) {
    	this.deployDescriptionXml = deployDescriptionXml;
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

	public void setPortletContextVO(PortletContextVO portletContext){
		this.portletContext = portletContext;
	}
	
	public PortletContextVO getPortletContext(){
		return portletContext;
	}
	
	public int getTypeOfPortlet() {
    	return typeOfPortlet;
    }
	public void setTypeOfPortlet(int typeOfPortlet) {
    	this.typeOfPortlet = typeOfPortlet;
    }
	
	@Override
    public String toString() {
	    return super.toString() + ", PortalPageZoneVO [contentId=" + contentId +  ", deployDescriptionXml=" + deployDescriptionXml
	        + ", seq=" + seq + ", tabPortletId=" + tabPortletId+ ", portletId=" + portletId + "]";
    }
	
	
}
