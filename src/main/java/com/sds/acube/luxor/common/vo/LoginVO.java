/*
 * @(#) LoginVO.java 2010. 5. 25.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.common.vo;


/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.1.6.1 $ $Date: 2011/02/10 06:05:46 $
 */
public class LoginVO extends CommonVO {
	private String loginId;
	private String password;
	private String language;
	private String loginIp;
	private String xId;
	private String xPw;
	private String xTm;
	
	/**
	 * @return Returns the language.
	 */
	public String getLanguage() {
		return language;
	}

	
	/**
	 * @param language The language to set.
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return Returns the loginId.
	 */
	public String getLoginId() {
		return loginId;
	}
	
	/**
	 * @param loginId The loginId to set.
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	
	public String getXid(){
		return xId;
	}
	
	public void setXid(String xId){
		this.xId = xId;
	}
	
	public String getXpw(){
		return xPw;
	}
	
	public void setXpw(String xPw){
		this.xPw = xPw;
	}
	
	public String getXtm(){
		return xTm;
	}
	
	public void setXtm(String xTm){
		this.xTm = xTm;
	}	
}
