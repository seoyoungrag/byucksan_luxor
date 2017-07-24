package com.sds.acube.luxor.portal.vo;

import com.sds.acube.luxor.common.vo.MessageVO;

public class PortalPageLayoutVO extends MessageVO {
	private String layoutId = "";
	private String layoutNameId = "";
	private String layoutCss = "";
	private String isDefault = "";
	
	public String getLayoutId() {
    	return layoutId;
    }
	public void setLayoutId(String layoutId) {
    	this.layoutId = layoutId;
    }
	public String getLayoutNameId() {
    	return layoutNameId;
    }
	public void setLayoutNameId(String layoutNameId) {
    	this.layoutNameId = layoutNameId;
    }
	public String getLayoutCss() {
    	return layoutCss;
    }
	public void setLayoutCss(String layoutCss) {
    	this.layoutCss = layoutCss;
    }
	public String getIsDefault() {
    	return isDefault;
    }
	public void setIsDefault(String isDefault) {
    	this.isDefault = isDefault;
    }
	
}
