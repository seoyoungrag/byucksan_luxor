/*
 * @(#) PortletConstant.java 2010. 5. 24.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.portal;


/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.2.2.2 $ $Date: 2011/04/21 00:13:39 $
 */
public class PortletConstant {
	
	public final static int STANDBY = 0;
	public final static int DEPLOYED = 1;
	public final static int UNDEPLOYED_BY_USER = 2;
	public final static int UNDEPLOYED_BY_FAULT = 3;
	
	public final static int GENERIC_PORTLET = 0;
	public final static int IFRAME_PORTLET = 1;
	public final static int MENU_PORTLET = 2;
	public final static int TAB_PORTLET = 3;
	public final static int IMG_PORTLET = 4;
	public final static int JSR168_PORTLET = 5;

	public final static String IGNORE_SERVER_INSTANCE = "_ALL_";
	
	public final static String[] PORTLET_STATUS = {"Stand by", "deployed", "undeployed by user", "undeployed by fault"};
	public final static String[] SSO_STATUS = {"No", "Yes"};
	public final static String[] TYPE_OF_PORTLET = {"Generic", "Iframe", "Menu", "Tab", "Img"};

	
	public final static String DESCRIPTION = "%DESCRIPTION%";
	public final static String PORTLET_CONTEXT_NAME = "%PORTLET-CONTEXT-NAME%";
	public final static String DISPLAY_NAME = "%DISPLAY-NAME%";
	public final static String TITLE = "%TITLE%";
	public final static String VIEW = "%VIEW%";
	public final static String EDIT = "%EDIT%";
	public final static String HELP = "%HELP%";
	public final static String GO = "%GO%";
	public final static String IMG = "%IMG%";	
	public final static String JAVASCRIPT = "%JAVASCRIPT%";
	public final static String STYLE = "%STYLE%";
	public final static String LOGIN_FLAG = "%LOGIN-FLAG%";
	public final static String SSO_INFO = "%SSO-INFO%";
	
	public final static int LOGIN_NEED = 0;
	public final static int LOGIN_FREE = 1;
	
	public final static String PORTLET_APP_OPEN = "<portlet-app xmlns=\"http://acube.sds.co.kr/xml/ns/portlet/portlet-app_1_0.xsd\" version=\"1.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">";
	public final static String PORTLET_APP_CLOSE = "</portlet-app>";
	
	
	public static void main(String[] args){
	}
}