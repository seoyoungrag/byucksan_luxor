/*
 * @(#) PortletManagementVO.java 2010. 5. 11.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.portal.vo;

import java.sql.Timestamp;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.util.TimestampAdapter;
import com.sds.acube.luxor.common.vo.MessageVO;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.portal.PortletConstant;


/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.2.2.1 $ $Timestamp: 2010/07/20 05:02:30 $
 */
public class PortletManagementVO extends MessageVO {
	
	private String filePath = "";
	private String fileName = "";
	private String contextName = "";
	private int statusCode = -1;
	private Timestamp lastUpdateDate = null;
	private String exception = "";
	private int typeOfPortlet = PortletConstant.GENERIC_PORTLET;
	
	// iframe portlet
	private String title = "";
	private String outline = "";
	private String viewUrl = "";
	private String goUrl = "";
	private String editUrl = "";
	private String helpUrl = "";
	private String imgUrl = "";
	private String activeCheck = "";
	private String deployDescriptionXml = "";
	
	private String[] styleRef = null;
	private String[] scriptRef = null;
	
	private String categoryId = "";
	private String categoryName = "";
	
	private int loginFlag = PortletConstant.LOGIN_NEED; // 로그인 필요
	private String ssoInfo = "N/A";
	
	private String[] checkbox;
	
	
	
	/**
	 * @return Returns the checkbox.
	 */
	public String[] getCheckbox() {
		return checkbox;
	}
	
	/**
	 * @param checkbox The checkbox to set.
	 */
	public void setCheckbox(String[] checkbox) {
		this.checkbox = checkbox;
	}



	/**
	 * @return Returns the ssoInfo.
	 */
	public String getSsoInfo() {
		return ssoInfo;
	}


	
	/**
	 * @param ssoInfo The ssoInfo to set.
	 */
	public void setSsoInfo(String ssoInfo) {
		this.ssoInfo = ssoInfo;
	}


	/**
	 * @return Returns the loginFlag.
	 */
	public int getLoginFlag() {
		return loginFlag;
	}

	
	/**
	 * @param loginFlag The loginFlag to set.
	 */
	public void setLoginFlag(int loginFlag) {
		this.loginFlag = loginFlag;
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
	 * @return Returns the styleRef.
	 */
	public String[] getStyleRef() {
		return styleRef;
	}
	
	/**
	 * @param styleRef The styleRef to set.
	 */
	public void setStyleRef(String[] styleRef) {
		this.styleRef = styleRef;
	}

	/**
	 * @return Returns the scriptRef.
	 */
	public String[] getScriptRef() {
		return scriptRef;
	}

	/**
	 * @param scriptRef The scriptRef to set.
	 */
	public void setScriptRef(String[] scriptRef) {
		this.scriptRef = scriptRef;
	}

	/**
	 * @return Returns the deployDescriptionXml.
	 */
	public String getDeployDescriptionXml() {
		return deployDescriptionXml;
	}
	
	/**
	 * @param deployDescriptionXml The deployDescriptionXml to set.
	 */
	public void setDeployDescriptionXml(String deployDescriptionXml) {
		this.deployDescriptionXml = deployDescriptionXml;
	}

	/**
	 * @return Returns the activeCheck.
	 */
	public String getActiveCheck() {
		return activeCheck;
	}

	/**
	 * @param activeCheck The activeCheck to set.
	 */
	public void setActiveCheck(String activeCheck) {
		this.activeCheck = activeCheck;
	}

	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}

	
	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	
	/**
	 * @return Returns the outline.
	 */
	public String getOutline() {
		return outline;
	}

	
	/**
	 * @param outline The outline to set.
	 */
	public void setOutline(String outline) {
		this.outline = outline;
	}

	
	/**
	 * @return Returns the viewUrl.
	 */
	public String getViewUrl() {
		return viewUrl;
	}

	
	/**
	 * @param viewUrl The viewUrl to set.
	 */
	public void setViewUrl(String viewUrl) {
		this.viewUrl = viewUrl;
	}

	
	/**
	 * @return Returns the goUrl.
	 */
	public String getGoUrl() {
		return goUrl;
	}

	
	/**
	 * @param goUrl The goUrl to set.
	 */
	public void setGoUrl(String goUrl) {
		this.goUrl = goUrl;
	}

	
	/**
	 * @return Returns the editUrl.
	 */
	public String getEditUrl() {
		return editUrl;
	}

	
	/**
	 * @param editUrl The editUrl to set.
	 */
	public void setEditUrl(String editUrl) {
		this.editUrl = editUrl;
	}

	
	/**
	 * @return Returns the helpUrl.
	 */
	public String getHelpUrl() {
		return helpUrl;
	}

	
	/**
	 * @param helpUrl The imgUrl to set.
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	/**
	 * @return Returns the imgUrl.
	 */
	public String getImgUrl() {
		return imgUrl;
	}

	
	/**
	 * @param helpUrl The helpUrl to set.
	 */
	public void setHelpUrl(String helpUrl) {
		this.helpUrl = helpUrl;
	}	

	/**
	 * @return Returns the typeOfPortlet.
	 */
	public int getTypeOfPortlet() {
		return typeOfPortlet;
	}
	
	/**
	 * @param typeOfPortlet The typeOfPortlet to set.
	 */
	public void setTypeOfPortlet(int typeOfPortlet) {
		this.typeOfPortlet = typeOfPortlet;
	}

	/**
	 * @return Returns the exception.
	 */
	public String getException() {
		return exception;
	}

	
	/**
	 * @param exception The exception to set.
	 */
	public void setException(String exception) {
		this.exception = exception;
	}

	/**
	 * @return Returns the contextName.
	 */
	public String getContextName() {
		return contextName;
	}
	
	/**
	 * @param contextName The contextName to set.
	 */
	public void setContextName(String contextName) {
		this.contextName = contextName;
	}
	
	/**
	 * @return Returns the statusCode.
	 */
	public int getStatusCode() {
		return statusCode;
	}
	
	/**
	 * @param statusCode The statusCode to set.
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	/**
	 * @return Returns the lastUpdateDate.
	 */
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getLastUpdateDate() {
		return lastUpdateDate;
	}
	
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
	
	
	/**
	 * @return Returns the filePath.
	 */
	public String getFilePath() {
		return filePath;
	}

	
	/**
	 * @param filePath The filePath to set.
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	
	/**
	 * @return Returns the fileName.
	 */
	public String getFileName() {
		return fileName;
	}


	
	/**
	 * @param fileName The fileName to set.
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
