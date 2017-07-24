package com.sds.acube.luxor.portal.vo;

import com.sds.acube.luxor.common.vo.MessageVO;

public class PortalMenuVO extends MessageVO {
	private String pageId = "";
	private String contentId = "";
	private String zoneId = "";
	private String parentId = "";
	private String menuId = "";
	private String script = "";
	private String isActive = "";
	private String hasChild = "";
	private int depth = 0;
	private int seq = 0;
	private String targetZone = "";
	private String displayNameId = "";
	private String displayName = "";
	private String portalName = "";
	private int childCnt = 0;
	private String childId = "";
		
	
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getPortalName() {
		return portalName;
	}
	public void setPortalName(String portalName) {
		this.portalName = portalName;
	}
	public String getHasChild() {
    	return hasChild;
    }
	public void setHasChild(String hasChild) {
    	this.hasChild = hasChild;
    }
	public String getParentId() {
    	return parentId;
    }
	public void setParentId(String parentId) {
    	this.parentId = parentId;
    }
	public int getDepth() {
    	return depth;
    }
	public void setDepth(int depth) {
    	this.depth = depth;
    }
	public String getPageId() {
    	return pageId;
    }
	public void setPageId(String pageId) {
    	this.pageId = pageId;
    }
	public String getContentId() {
    	return contentId;
    }
	public void setContentId(String contentId) {
    	this.contentId = contentId;
    }
	public String getZoneId() {
    	return zoneId;
    }
	public void setZoneId(String zoneId) {
    	this.zoneId = zoneId;
    }
	public String getMenuId() {
    	return menuId;
    }
	public void setMenuId(String menuId) {
    	this.menuId = menuId;
    }
	public String getScript() {
    	return script;
    }
	public void setScript(String script) {
    	this.script = script;
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
	
	public String getTargetZone() {
		return targetZone;
	}
	public void setTargetZone(String targetZone) {
		this.targetZone = targetZone;
	}
	
	public String getDisplayNameId() {
		return displayNameId;
	}
	public void setDisplayNameId(String displayNameId) {
		this.displayNameId = displayNameId;
	}
	
	public void setChildCnt(int childCnt){
		this.childCnt = childCnt;
	}
	
	public int getChildCnt(){
		return childCnt;
	}
	
	public void setChildId(String childId){
		this.childId = childId;
	}
	
	public String getChildId(){
		return childId;
	}
	
	@Override
	public String toString() {
		return "PortalMenuVO [contentId=" + contentId + ", depth=" + depth
				+ ", hasChild=" + hasChild + ", isActive=" + isActive
				+ ", menuId=" + menuId + ", pageId=" + pageId + ", parentId="
				+ parentId + ", script=" + script + ", seq=" + seq
				+ ", targetZone=" + targetZone + ", zoneId=" + zoneId + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contentId == null) ? 0 : contentId.hashCode());
		result = prime * result + depth;
		result = prime * result
				+ ((hasChild == null) ? 0 : hasChild.hashCode());
		result = prime * result
				+ ((isActive == null) ? 0 : isActive.hashCode());
		result = prime * result + ((menuId == null) ? 0 : menuId.hashCode());
		result = prime * result + ((pageId == null) ? 0 : pageId.hashCode());
		result = prime * result
				+ ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result + ((script == null) ? 0 : script.hashCode());
		result = prime * result + seq;
		result = prime * result
				+ ((targetZone == null) ? 0 : targetZone.hashCode());
		result = prime * result + ((zoneId == null) ? 0 : zoneId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PortalMenuVO other = (PortalMenuVO) obj;
		if (contentId == null) {
			if (other.contentId != null)
				return false;
		} else if (!contentId.equals(other.contentId))
			return false;
		if (depth != other.depth)
			return false;
		if (hasChild == null) {
			if (other.hasChild != null)
				return false;
		} else if (!hasChild.equals(other.hasChild))
			return false;
		if (isActive == null) {
			if (other.isActive != null)
				return false;
		} else if (!isActive.equals(other.isActive))
			return false;
		if (menuId == null) {
			if (other.menuId != null)
				return false;
		} else if (!menuId.equals(other.menuId))
			return false;
		if (pageId == null) {
			if (other.pageId != null)
				return false;
		} else if (!pageId.equals(other.pageId))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (script == null) {
			if (other.script != null)
				return false;
		} else if (!script.equals(other.script))
			return false;
		if (seq != other.seq)
			return false;
		if (targetZone == null) {
			if (other.targetZone != null)
				return false;
		} else if (!targetZone.equals(other.targetZone))
			return false;
		if (zoneId == null) {
			if (other.zoneId != null)
				return false;
		} else if (!zoneId.equals(other.zoneId))
			return false;
		return true;
	}
	
	
}
