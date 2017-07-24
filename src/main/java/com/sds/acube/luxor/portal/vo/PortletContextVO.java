/*
 * @(#) PortletContextVO.java 2010. 5. 17.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.portal.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.vo.MessageVO;
import com.sds.acube.luxor.portal.PortletConstant;


/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.2.2.1 $ $Date: 2011/02/10 06:05:44 $
 */
public class PortletContextVO extends MessageVO {
	private String portletContextName;
	private String displayName;
//	private int version;
	private HashMap<String, String> supports;
	private String title;
	private String viewUrl;
	private String editUrl;
	private String helpUrl;
	private String goUrl;
	private String imgUrl;//이미지 포틀릿을 위한 추가사항
	private List<String> scripts;
	private List<String> styles;
//	private List<String> springServices;
//	private List<String> librarys;
	
	private String description;
//	private String versionDescription;
	
	private int typeOfPortlet;
	private String categoryId;
	private String categoryName = null;
	private int loginFlag = PortletConstant.LOGIN_NEED;  
	private String ssoInfo = "N/A";
	
	public PortletContextVO() {
		super();
		this.portletContextName = null;
		this.displayName = null;
//		this.version = -1;
		this.supports = new HashMap<String, String>();
		this.title = null;
		this.viewUrl = null;
		this.editUrl = null;
		this.helpUrl = null;
		this.goUrl = null;
		this.imgUrl = null; //이미지 포틀릿을 위한 추가사항
		this.scripts = new ArrayList<String>();
		this.styles = new ArrayList<String>();
//		this.springServices = new ArrayList<String>();
//		this.librarys = new ArrayList<String>();
		this.description = null;
//		this.versionDescription = null;
		this.typeOfPortlet = PortletConstant.GENERIC_PORTLET;
		this.categoryId = null;
	}

	/**
	 * @param portletContextName
	 * @param displayName
	 * @param version
	 * @param supports
	 * @param title
	 * @param viewUrl
	 * @param editUrl
	 * @param helpUrl
	 * @param goUrl
	 * @param scripts
	 * @param styles
	 * @param springServices
	 */
	public PortletContextVO(String portletContextName, String displayName, int version, HashMap<String, String> supports, String title, String viewUrl, String editUrl, String helpUrl, String goUrl, String imgUrl, List<String> scripts,
			List<String> styles, int typeOfPortlet, String categoryId) {
		super();
		this.portletContextName = portletContextName;
		this.displayName = displayName;
//		this.version = version;
		this.supports = supports;
		this.title = title;
		this.viewUrl = viewUrl;
		this.editUrl = editUrl;
		this.helpUrl = helpUrl;
		this.goUrl = goUrl;
		this.imgUrl = imgUrl;
		this.scripts = scripts;
		this.styles = styles;
//		this.springServices = springServices;
//		this.librarys = librarys;
		
		this.description = null;
//		this.versionDescription = null;
		this.typeOfPortlet = typeOfPortlet;
		this.categoryId = categoryId;
		
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
	 * @return Returns the portletContextName.
	 */
	public String getPortletContextName() {
		return portletContextName;
	}

	
	/**
	 * @param portletContextName The portletContextName to set.
	 */
	public void setPortletContextName(String portletContextName) {
		this.portletContextName = portletContextName;
	}

	
	/**
	 * @return Returns the displayName.
	 */
	public String getDisplayName() {
		return displayName;
	}

	
	/**
	 * @param displayName The displayName to set.
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	

	
	/**
	 * @return Returns the supports.
	 */
	public HashMap<String, String> getSupports() {
		return supports;
	}

	
	/**
	 * @param supports The supports to set.
	 */
	public void setSupports(HashMap<String, String> supports) {
		this.supports = supports;
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
	 * @param helpUrl The helpUrl to set.
	 */
	public void setHelpUrl(String helpUrl) {
		this.helpUrl = helpUrl;
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
	 * @return Returns the imgUrl.
	 */	
	public String getImgUrl(){
		return imgUrl;
	}
	
	/**
	 * @param imgUrl The imgUrl to set.
	 */	
	public void setImgUrl(String imgUrl){
		this.imgUrl = imgUrl;
	}
	
	/**
	 * @return Returns the scripts.
	 */
	public List<String> getScripts() {
		return scripts;
	}

	public void setScriptsArray(String[] scripts) {
		if(scripts == null) return;
		this.scripts = new ArrayList<String>();
		for(int i=0;i<scripts.length;i++){
			this.scripts.add(scripts[i]);
		}
	}
	
	
	/**
	 * @param scripts The scripts to set.
	 */
	public void setScripts(List<String> scripts) {
		this.scripts = scripts;
	}

	
	/**
	 * @return Returns the styles.
	 */
	public List<String> getStyles() {
		return styles;
	}

	public void setStylesArray(String[] styles) {
		if(styles == null) return;
		this.styles = new ArrayList<String>();
		for(int i=0;i<styles.length;i++){
			this.styles.add(styles[i]);
		}
	}
	
	/**
	 * @param styles The styles to set.
	 */
	public void setStyles(List<String> styles) {
		this.styles = styles;
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

	
	@SuppressWarnings("unchecked")
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("portlet-context-name=" + getPortletContextName()).append("\n");
		sb.append("description=" + getDescription()).append("\n");
		sb.append("display-name=" + getDisplayName()).append("\n");
//		sb.append("build-version=" + getVersion()).append("\n");
//		sb.append("version-description=" + getVersionDescription()).append("\n");
		sb.append("supports=").append("\n");
		Iterator i = getSupports().keySet().iterator();
		while(i.hasNext()) {
			String key = (String)i.next();
			sb.append(key + "=" + (String)getSupports().get(key)).append("\n");
		}
		sb.append("title=" + getTitle()).append("\n");
		sb.append("view=" + getViewUrl()).append("\n");
		sb.append("edit=" + getEditUrl()).append("\n");
		sb.append("help=" + getHelpUrl()).append("\n");
		sb.append("go=" + getGoUrl()).append("\n");
		sb.append("img=" + getImgUrl()).append("\n");

		sb.append("scripts=").append("\n");
		for(int j=0;j<getScripts().size();j++) {
			sb.append(j + "=" + (String)getScripts().get(j)).append("\n");
		}
		
		sb.append("styles=").append("\n");
		for(int j=0;j<getStyles().size();j++) {
			sb.append(j + "=" + (String)getStyles().get(j)).append("\n");
		}
		
//		sb.append("spring services=").append("\n");
//		for(int j=0;j<getSpringServices().size();j++) {
//			sb.append(j + "=" + (String)getSpringServices().get(j)).append("\n");
//		}
//		
//		sb.append("library infos=").append("\n");
//		for(int j=0;j<getLibrarys().size();j++) {
//			sb.append(j + "=" + (String)getLibrarys().get(j)).append("\n");
//		}
		return sb.toString();
	}
	
	
}
