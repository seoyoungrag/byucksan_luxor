/*
 * @(#) LogoutSysVO.java 2010. 8. 17.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.portal.vo;

import com.sds.acube.luxor.common.vo.MessageVO;


/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.1.4.1 $ $Date: 2011/02/10 06:05:44 $
 */
public class LogoutSysVO extends MessageVO {
	private String systemId;
	private String systemName;
	private String logoutUrl;
	private String sessionId;
	private int status;
	
	
	
	public String getSessionId() {
    	return sessionId;
    }

	public void setSessionId(String sessionId) {
    	this.sessionId = sessionId;
    }

	/**
	 * @return Returns the status.
	 */
	public int getStatus() {
		return status;
	}

	
	/**
	 * @param status The status to set.
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return Returns the systemId.
	 */
	public String getSystemId() {
		return systemId;
	}
	
	/**
	 * @param systemId The systemId to set.
	 */
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	
	/**
	 * @return Returns the systemName.
	 */
	public String getSystemName() {
		return systemName;
	}
	
	/**
	 * @param systemName The systemName to set.
	 */
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	
	/**
	 * @return Returns the logoutUrl.
	 */
	public String getLogoutUrl() {
		return logoutUrl;
	}
	
	/**
	 * @param logoutUrl The logoutUrl to set.
	 */
	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}
	
	
}
