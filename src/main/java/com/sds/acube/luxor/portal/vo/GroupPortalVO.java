/*
 * @(#) GroupPortalVO.java 2010. 7. 21.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.portal.vo;

import java.sql.Timestamp;
import java.util.List;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.util.TimestampAdapter;
import com.sds.acube.luxor.common.vo.MessageVO;
import com.sds.acube.luxor.framework.config.LuxorConfig;


/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.2.2.2 $ $Date: 2011/04/11 00:29:49 $
 */
public class GroupPortalVO extends MessageVO {
	private String portalName;
	private String description;
	private String parentId;
	private String relatedCompid;
	private String relatedCompname;	
	private String relatedPortalinfo;
	
	private String option;
	private String childPortal;
	private String oldTenantId;
	private String oldPortalId;
	private int defaultFlag = 0;
	private Timestamp lastUpdateDate;
	
	private List portalList;
	
	public String getChildPortal() {
		return childPortal;
	}

	public void setChildPortal(String childPortal) {
		this.childPortal = childPortal;
	}

	public String getOldTenantId() {
    	return oldTenantId;
    }

	public void setOldTenantId(String oldTenantId) {
    	this.oldTenantId = oldTenantId;
    }

	public String getOldPortalId() {
    	return oldPortalId;
    }

	public void setOldPortalId(String oldPortalId) {
    	this.oldPortalId = oldPortalId;
    }

	public String getOption() {
    	return option;
    }

	public void setOption(String option) {
    	this.option = option;
    }

	/**
	 * @return Returns the defaultFlag.
	 */
	public int getDefaultFlag() {
		return defaultFlag;
	}

	
	/**
	 * @param defaultFlag The defaultFlag to set.
	 */
	public void setDefaultFlag(int defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	/**
	 * @return Returns the portalName.
	 */
	public String getPortalName() {
		return CommonUtil.escapeSpecialChar(portalName);
	}
	
	/**
	 * @param portalName The portalName to set.
	 */
	public void setPortalName(String portalName) {
		this.portalName = portalName;
	}
	
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return CommonUtil.escapeSpecialChar(description);
	}
	
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return Returns the parentId.
	 */
	public String getParentId() {
		return parentId;
	}
	
	/**
	 * @param parentId The parentId to set.
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * @return Returns the relatedCompId.
	 */
	public String getRelatedCompid() {
		return relatedCompid;
	}
	
	/**
	 * @param relatedCompid The relatedCompid to set.
	 */
	public void setRelatedCompid(String relatedCompid) {
		this.relatedCompid = relatedCompid;
	}
	
	/**
	 * @return Returns the relatedCompId.
	 */
	public String getRelatedCompname() {
		return relatedCompname;
	}
	
	/**
	 * @param relatedCompid The relatedCompid to set.
	 */
	public void setRelatedCompname(String relatedCompname) {
		this.relatedCompname = relatedCompname;
	}
		
	
	/**
	 * @return Returns the relatedPortalid.
	 */
	public String getRelatedPortalinfo() {
		return relatedPortalinfo;
	}
	
	/**
	 * @param relatedCompid The relatedPortalid to set.
	 */
	public void setRelatedPortalinfo(String relatedPortalinfo) {
		this.relatedPortalinfo = relatedPortalinfo;
	}
		
	
	/**
	 * @return Returns the lastUpdateDate.
	 */
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getLastUpdateDate() {
		return lastUpdateDate;
	}
	
	/**
	 * @return
	 */
	public String getLastUpdateToString() {
		if(lastUpdateDate == null) {
			return "";
		}
		String datePattern = LuxorConfig.getEnvString(tenantId, portalId, "DATE_PATTERN");
    	return CommonUtil.formatDate(lastUpdateDate, datePattern);
	}
	
	/**
	 * @param lastUpdateDate The lastUpdateDate to set.
	 */
	public void setLastUpdateDate(Timestamp lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
	public void setPortalList(List portalList){
		this.portalList = portalList;
	}
	
	public List getPortalList(){
		return portalList;
	}	
	
	@Override
    public String toString() {
	    return super.toString() + ", GroupPortalVO [portalName=" + portalName + ", description=" + description + ", parentId=" + parentId + ", relatedCompid="
	        + relatedCompid + ", option=" + option + ", oldTenantId=" + oldTenantId + ", oldPortalId=" + oldPortalId + ", defaultFlag="
	        + defaultFlag + ", lastUpdateDate=" + lastUpdateDate + "]";
    }
}
