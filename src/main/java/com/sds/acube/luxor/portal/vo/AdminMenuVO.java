package com.sds.acube.luxor.portal.vo;

import com.sds.acube.luxor.common.vo.MessageVO;

public class AdminMenuVO extends MessageVO {
	private String parentId = ""; 
	private String menuId = "";
	private String menuNameId = "";
	private String menuUrl = "";
	private String isActive = "";
	private String isHome = "";
	private int seq = 0;
	
	public String getParentId() {
    	return parentId;
    }
	public void setParentId(String parentId) {
    	this.parentId = parentId;
    }
	
	public String getMenuId() {
		return menuId;
	}
	
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	public String getMenuNameId() {
		return menuNameId;
	}
	
	public void setMenuNameId(String menuNameId) {
		this.menuNameId = menuNameId;
	}
	
	public String getMenuUrl() {
		return menuUrl;
	}
	
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
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
	
	public String getIsHome() {
    	return isHome;
    }
	public void setIsHome(String isHome) {
    	this.isHome = isHome;
    }
	
	@Override
	public String toString() {
		return super.toString() + ", AdminMenuVO [isActive=" + isActive + ", menuId=" + menuId
				+ ", menuNameId=" + menuNameId + ", menuUrl=" + menuUrl
				+ ", parentId=" + parentId + ", seq=" + seq + "]";
	}
	
}
