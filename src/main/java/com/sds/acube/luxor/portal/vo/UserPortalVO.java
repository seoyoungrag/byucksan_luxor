/*
 * @(#) GroupPortalVO.java 2010. 7. 21.
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
 * @version $Revision: 1.2.2.2 $ $Date: 2011/04/11 00:29:49 $
 */
public class UserPortalVO implements Cloneable {
	protected String tenantId = "";
	protected String portalId = "";
	private String compId = "";
	private String portalName;
	private String roleId = "";
	private String deptId = "";
	private String conUserUID = "";
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getPortalId() {
		return portalId;
	}
	public void setPortalId(String portalId) {
		this.portalId = portalId;
	}
	public String getCompId() {
		return compId;
	}
	public void setCompId(String compId) {
		this.compId = compId;
	}
	public String getPortalName() {
		return portalName;
	}
	public void setPortalName(String portalName) {
		this.portalName = portalName;
	}
	public String getConUserUID() {
		return conUserUID;
	}
	public void setConUserUID(String conUserUID) {
		this.conUserUID = conUserUID;
	}
	
}