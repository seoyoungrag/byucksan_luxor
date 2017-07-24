<%--
/************************************************************************************************
*	@(#) EP_ClientRegOpen.jsp 2001/05/02														*
*	Copyright (c) 2000 삼성SDS(주), inc. All Rights Reserved										*
*																								*
*	Description :																				*
*																								*
*	경로 지정 방법																				*
*		ex) 싱글 메신저를 Open할 경우																*	
*		portal/asp/EP_CertClientOpen.asp?														*
*							hKey=HKEY_LOCAL_MACHINE&FirstSubKey=SOFTWARE\\SDS\\ACUBE\\im.exe&	*
*							FirstValueName=path&ClassName=Weppy&filename=im.exe					*
*							&url=<OMWEBSVR>/cgi-bin/imsrchuser.cgi								*
*	filename	:	Client 프로그램을 구동시킬 경우 실행파일 명										*
*						(ex. im.exe, im.exe 등)													*
*	url			:																				*
*	Param		:   D0:암호화방식,D1:암호화된Data,D2:암호화된대칭키,D3:Hash함수를 호출한 정보			*
*																								*
*																								*
*	수정내역     : 																				*
*																								*
*************************************************************************************************/
--%>

<%
String strFile				= "";
String strHKey				= "";
String strFirstSubKey		= "";
String strFirstValueName	= "";
String strClassName			= "";
String strParam				= "";
String strFlag="1";
String strArgument = "";

String strUserId = (String)request.getParameter("empId");

String ClientIP = request.getHeader("X-Forwarded-For");

if (ClientIP == null || ClientIP.length() == 0 || "unknown".equalsIgnoreCase(ClientIP)) {  

    ClientIP = request.getHeader("Proxy-Client-IP");  

}  

if (ClientIP == null || ClientIP.length() == 0 || "unknown".equalsIgnoreCase(ClientIP)) {  

    ClientIP = request.getHeader("WL-Proxy-Client-IP");  

}  

if (ClientIP == null || ClientIP.length() == 0 || "unknown".equalsIgnoreCase(ClientIP)) {  

    ClientIP = request.getHeader("HTTP_CLIENT_IP");  

}  

if (ClientIP == null || ClientIP.length() == 0 || "unknown".equalsIgnoreCase(ClientIP)) {  

    ClientIP = request.getHeader("HTTP_X_FORWARDED_FOR");  

}  

if (ClientIP == null || ClientIP.length() == 0 || "unknown".equalsIgnoreCase(ClientIP)) {  

    ClientIP = request.getRemoteAddr();  

}
if (ClientIP.equals("211.168.82.32") || ClientIP.equals("211.168.82.33") ||
   ClientIP.equals("211.168.82.34") || ClientIP.equals("211.168.82.35"))
{
    strUserId = "META" + " " + strUserId;
//      strUserId = "META";
} 

//strUserId = DecodeBySType(USERID);

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

		'URL실행    
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
		strParamX = "<%=strUserId%>"
		

		'msgbox strParamX
		
		ret = AxCon.AXID.ClientFullEXE("C:\Program Files (x86)\ITMS\SIMEIS\SIMEIS.exe",strParamX,1)	
	end if
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
