package com.sds.acube.luxor.common.vo;


public class MemberRequestVO extends MemberRequestKeyVO {
	private String requestId;
	private String loginId;
	private String loginPassword;
	private String loginIp;
	private String userName;
	private int requestStatus = 0;
	private String isTermsAgree;
	private String[] requestIds;
	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String[] getRequestIds() {
		return requestIds;
	}
	public void setRequestIds(String[] requestIds) {
		this.requestIds = requestIds;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(int requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getIsTermsAgree() {
		return isTermsAgree;
	}
	public void setIsTermsAgree(String isTermsAgree) {
		this.isTermsAgree = isTermsAgree;
	}	
}
