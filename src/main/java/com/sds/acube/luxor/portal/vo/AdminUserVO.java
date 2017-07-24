/*
 * @(#) AdminUserVO.java 2010. 7. 21.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.portal.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.util.TimestampAdapter;
import com.sds.acube.luxor.common.vo.MessageVO;
import com.sds.acube.luxor.framework.config.LuxorConfig;


/**
 * 
 * @author Alex, Eum
 * @version $Revision: 1.2.2.2 $ $Timestamp: 2010/07/23 08:47:39 $
 */
public class AdminUserVO extends MessageVO implements Serializable {
    private static final long serialVersionUID = -1459720402543301929L;
	private String userId;
	private int adminType = 0;// 0: 일반 관리자 , 1:그룹포탈 관리자
	private Timestamp lastUpdateDate;
	private String userName;
	private String portalName;
	private String option;
	private String[] adminIds;
	private String[] adminNames;
	private String[] adminUids;
	private String userUid;
	private String isIgnoreAcl;

	/**
	 * @return Returns the adminNames.
	 */
	public String[] getAdminNames() {
		return adminNames;
	}


	/**
	 * @param adminNames The adminNames to set.
	 */
	public void setAdminNames(String[] adminNames) {
		this.adminNames = adminNames;
	}


	/**
	 * @return Returns the adminIds.
	 */
	public String[] getAdminIds() {
		return adminIds;
	}


	/**
	 * @param adminIds The adminIds to set.
	 */
	public void setAdminIds(String[] adminIds) {
		this.adminIds = adminIds;
	}


	/**
	 * @return Returns the option.
	 */
	public String getOption() {
		return option;
	}


	/**
	 * @param option The option to set.
	 */
	public void setOption(String option) {
		this.option = option;
	}


	/**
	 * @return Returns the portalName.
	 */
	public String getPortalName() {
		return portalName;
	}


	/**
	 * @param portalName The portalName to set.
	 */
	public void setPortalName(String portalName) {
		this.portalName = portalName;
	}


	/**
	 * @return Returns the userName.
	 */
	public String getUserName() {
		return userName;
	}


	/**
	 * @param userName The userName to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
		if (lastUpdateDate == null) {
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


	/**
	 * @return Returns the userId.
	 */
	public String getUserId() {
		return userId;
	}


	/**
	 * @param userId The userId to set.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}


	/**
	 * @return Returns the adminType.
	 */
	public int getAdminType() {
		return adminType;
	}


	/**
	 * @param adminType The adminType to set.
	 */
	public void setAdminType(int adminType) {
		this.adminType = adminType;
	}


	public String getUserUid() {
		return userUid;
	}


	public void setUserUid(String userUid) {
		this.userUid = userUid;
	}

	

	public String[] getAdminUids() {
		return adminUids;
	}


	public void setAdminUids(String[] adminUids) {
		this.adminUids = adminUids;
	}

	
	public String getIsIgnoreAcl() {
		return isIgnoreAcl;
	}


	public void setIsIgnoreAcl(String isIgnoreAcl) {
		this.isIgnoreAcl = isIgnoreAcl;
	}


	@Override
    public String toString() {
	    return super.toString() + ", AdminUserVO [adminIds=" + Arrays.toString(adminIds) + ", adminNames=" + Arrays.toString(adminNames) + ", adminType="
	        + adminType + ", lastUpdateDate=" + lastUpdateDate + ", option=" + option + ", portalName=" + portalName + ", userId=" + userId + ", userName=" + userName + "]";
    }
	
	
}
