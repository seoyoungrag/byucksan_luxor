/*
 * @(#) PortletCategoryVO.java 2010. 7. 9.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.portal.vo;

import java.sql.Timestamp;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.util.TimestampAdapter;
import com.sds.acube.luxor.common.vo.MessageVO;
import com.sds.acube.luxor.framework.config.LuxorConfig;


/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.1.6.1 $ $Timestamp: 2010/08/04 00:20:09 $
 */
public class PortletCategoryVO extends MessageVO {
	
	private String categoryId;
	private String categoryName;
	private Timestamp lastUpdateDate;
	
	/**
	 * @return Returns the categoryId.
	 */
	public String getCategoryId() {
		return categoryId;
	}
	
	/**
	 * @param categoryId The categoryId to set.
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	/**
	 * @return Returns the categoryName.
	 */
	public String getCategoryName() {
		return categoryName;
	}
	
	/**
	 * @param categoryName The categoryName to set.
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
		String datePattern = LuxorConfig.getEnvString(tenantId, portalId, "DATE_PATTERN");;
    	return CommonUtil.formatDate(lastUpdateDate, datePattern);
	}
	
	/**
	 * @param lastUpdateDate The lastUpdateDate to set.
	 */
	public void setLastUpdateDate(Timestamp lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
	
}
