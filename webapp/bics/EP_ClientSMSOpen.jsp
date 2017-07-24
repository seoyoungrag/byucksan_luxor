<%--
/************************************************************************************************
*	@(#) EP_ClientRegOpen.jsp 2001/05/02														*
*	Copyright (c) 2000 »ï¼ºSDS(ÁÖ), inc. All Rights Reserved										*
*																								*
*	Description :																				*
*																								*
*	°æ·Î ÁöÁ¤ ¹æ¹ý																				*
*		ex) ½Ì±Û ¸Þ½ÅÀú¸¦ OpenÇÒ °æ¿ì																*	
*		portal/asp/EP_CertClientOpen.asp?														*
*							hKey=HKEY_LOCAL_MACHINE&FirstSubKey=SOFTWARE\\SDS\\ACUBE\\im.exe&	*
*							FirstValueName=path&ClassName=Weppy&filename=im.exe					*
*							&url=<OMWEBSVR>/cgi-bin/imsrchuser.cgi								*
*	filename	:	Client ÇÁ·Î±×·¥À» ±¸µ¿½ÃÅ³ °æ¿ì ½ÇÇàÆÄÀÏ ¸í										*
*						(ex. im.exe, im.exe µî)													*
*	url			:																				*
*	Param		:   D0:¾ÏÈ£È­¹æ½Ä,D1:¾ÏÈ£È­µÈData,D2:¾ÏÈ£È­µÈ´ëÄªÅ°,D3:HashÇÔ¼ö¸¦ È£ÃâÇÑ Á¤º¸			*
*																								*
*																								*
*	¼öÁ¤³»¿ª     : 																				*
*																								*
*************************************************************************************************/
--%>
<%@page import="com.sds.acube.luxor.security.UtilSSO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*, java.net.*" %>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.sds.acube.luxor.framework.config.LuxorConfig" %>
<%@ page import="org.springframework.web.util.WebUtils" %>
<%@ page import="com.sds.acube.luxor.common.vo.UserProfileVO" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %> 
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.sds.acube.luxor.portal.service.AdminService" %>
<%@ page import="com.sds.acube.luxor.portal.vo.AdminUserVO" %>
<%@ page import="com.sds.acube.luxor.common.util.CommonUtil" %>
<%


UserProfileVO userProfile = (UserProfileVO)session.getAttribute("userProfile");

String strFile				= "";
String strHKey				= "";
String strFirstSubKey		= "";
String strFirstValueName	= "";
String strClassName			= "";
String strParam				= "";
String strFlag="1";
String strArgument = "";


//String strUserId = (String)session.getValue("LOGINID");
String strUserId = userProfile.getLoginId();
String ppwd = (String)session.getValue("PASSWORD");

//strUserId = DecodeBySType(USERID);
//ppwd = DecodeBySType(ppwd);

%>
<html>
<head>
<title></title>
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Pragma" content="no-cache">

<script language="vbscript">
Dim o_time

sub openClient()
	o_time=window.setinterval("fntimedelay()",500)
end sub

function fntimedelay()
	if AxCon.document.readystate="complete" then
	
		window.clearinterval(o_time)

		'URL½ÇÇà    
		Dim url
		Dim hKey
		Dim FirstSubKey
		Dim SecondSubKey
	
		Dim FirstValueName
		Dim SecondValueName
    Dim strParamX

		hKey="<%=strHKey%>"							'		"HKEY_LOCAL_MACHINE"	
		FirstSubKey="<%=strFirstSubKey%>"			'		"SOFTWARE\SDS\ACUBE\im.exe"
		SecondSubKey="<%=strFirstSubKey%>"			'		"SOFTWARE\SDS\ACUBE\im.exe"
		FirstValueName="<%=strFirstValueName%>"		'		"path"
		SecondValueName="<%=strFirstValueName%>"	'		"path"
		strParamX = "/USER=<%=strUserId%>"
		

		'msgbox strParamX
		
		ret = AxCon.AXID.ClientFullEXE("C:\Program Files\SAMSUNG SDS\SMS\Smslogin.exe",strParamX,1)	
	end if
	history.go(-1)
end function

</script>
</head>

<body onload="javascript:openClient();">
<form name="AxCon">
<OBJECT WIDTH=0 HEIGHT=0 ID=AXID CLASSID="CLSID:08BCD971-A13B-4D6E-A2A5-E9B2324FC00D">
</OBJECT>
</form>

</body>
</html>
