<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.Locale" %>
<%@ page import="com.sds.acube.luxor.common.vo.UserProfileVO" %>
<%
Locale locale = (Locale)session.getAttribute("LANG_TYPE");
UserProfileVO userProfile = (UserProfileVO)session.getAttribute("userProfile");
%>
<script type="text/javascript">
var userUid = '';
<%if(userProfile!=null) {%>
userUid = "<%=userProfile.getUserUid()%>";
<%}%>
var locale = "<%=locale%>";
var monthNames = ['<spring:message code="portal.portlet.label.21" text="1월"/>', 
      			'<spring:message code="portal.portlet.label.22" text="2월"/>', 
    			'<spring:message code="portal.portlet.label.23" text="3월"/>', 
    			'<spring:message code="portal.portlet.label.24" text="4월"/>', 
    			'<spring:message code="portal.portlet.label.25" text="5월"/>', 
    			'<spring:message code="portal.portlet.label.26" text="6월"/>', 
    			'<spring:message code="portal.portlet.label.27" text="7월"/>', 
    			'<spring:message code="portal.portlet.label.28" text="8월"/>', 
    			'<spring:message code="portal.portlet.label.29" text="9월"/>', 
    			'<spring:message code="portal.portlet.label.30" text="10월"/>', 
    			'<spring:message code="portal.portlet.label.31" text="11월"/>', 
    			'<spring:message code="portal.portlet.label.32" text="12월"/>'];
var dayNames =['<spring:message code="portal.portlet.label.8" text="일"/>', 
				'<spring:message code="portal.portlet.label.9" text="월"/>', 
				'<spring:message code="portal.portlet.label.10" text="화"/>', 
				'<spring:message code="portal.portlet.label.11" text="수"/>', 
				'<spring:message code="portal.portlet.label.12" text="목"/>', 
				'<spring:message code="portal.portlet.label.13" text="금"/>', 
			    '<spring:message code="portal.portlet.label.14" text="토"/>'];
var yearSuffix = '<spring:message code="portal.portlet.label.0" text="년"/>';
var dateSuffix = '<spring:message code="portal.portlet.label.33" text="일"/>';
</script>
<script type="text/javascript" src="/ep/luxor/ref/js/timezone.js" charset="utf-8"></script>

<div id="timePortlet">
	<div style="width:70%; margin: auto;">
		<select id="gmt" title="Time Zone 목록" class="time-font-locale" style="width:100%;">
			<option class="ko" value="9"><spring:message code="portal.portlet.label.200" text="Seoul"/></option>
			<option value="1"><spring:message code="portal.portlet.label.201" text="Algiers"/></option>
			<option value="2"><spring:message code="portal.portlet.label.202" text="Cairo"/></option>
			<option value="2"><spring:message code="portal.portlet.label.203" text="Johannesburg"/></option>
			<option value="-8"><spring:message code="portal.portlet.label.204" text="Anchorage"/></option>
			<option value="-3"><spring:message code="portal.portlet.label.205" text="Buenos Aires"/></option>
			<option value="-6"><spring:message code="portal.portlet.label.206" text="Chicago"/></option>
			<option value="-7"><spring:message code="portal.portlet.label.207" text="Denver"/></option>
			<option value="-8"><spring:message code="portal.portlet.label.208" text="Los Angeles"/></option>
			<option class="en" value="-5"><spring:message code="portal.portlet.label.209" text="New York"/></option>
			<option value="-3"><spring:message code="portal.portlet.label.210" text="Greenland"/></option>
			<option value="-6"><spring:message code="portal.portlet.label.211" text="Guatemala"/></option>
			<option value="-4"><spring:message code="portal.portlet.label.212" text="Halifax"/></option>
			<option value="-5"><spring:message code="portal.portlet.label.213" text="Jamaica"/></option>
			<option value="-6"><spring:message code="portal.portlet.label.214" text="Mexico City"/></option>
			<option value="-5"><spring:message code="portal.portlet.label.215" text="Montreal"/></option>
			<option value="-7"><spring:message code="portal.portlet.label.216" text="Arizona"/></option>
			<option value="-4"><spring:message code="portal.portlet.label.217" text="Puerto Rico"/></option>
			<option value="-3"><spring:message code="portal.portlet.label.218" text="Sao Paulo"/></option>
			<option value="-8"><spring:message code="portal.portlet.label.219" text="Vancouver"/></option>
			<option value="3"><spring:message code="portal.portlet.label.220" text="Baghdad"/></option>
			<option value="7"><spring:message code="portal.portlet.label.221" text="Bangkok"/></option>
			<option value="4"><spring:message code="portal.portlet.label.222" text="Dubai"/></option>
			<option value="8"><spring:message code="portal.portlet.label.223" text="Hong Kong"/></option>
			<option value="7"><spring:message code="portal.portlet.label.224" text="Jakarta"/></option>
			<option value="2"><spring:message code="portal.portlet.label.225" text="Jerusalem"/></option>
			<option value="3"><spring:message code="portal.portlet.label.226" text="Kuwait"/></option>
			<option value="8"><spring:message code="portal.portlet.label.227" text="Macao"/></option>
			<option value="3"><spring:message code="portal.portlet.label.228" text="Qatar"/></option>
			<option value="8"><spring:message code="portal.portlet.label.229" text="Shanghai"/></option>
			<option value="8"><spring:message code="portal.portlet.label.230" text="Singapore"/></option>
			<option value="8"><spring:message code="portal.portlet.label.231" text="Taipei"/></option>
			<option value="9"><spring:message code="portal.portlet.label.232" text="Tokyo"/></option>
			<option value="10"><spring:message code="portal.portlet.label.233" text="Vladivostok"/></option>
			<option value="10"><spring:message code="portal.portlet.label.235" text="Brisbane"/></option>
			<option value="10"><spring:message code="portal.portlet.label.238" text="Sydney"/></option>
			<option value="1"><spring:message code="portal.portlet.label.239" text="Amsterdam"/></option>
			<option value="1"><spring:message code="portal.portlet.label.240" text="Andorra"/></option>
			<option value="2"><spring:message code="portal.portlet.label.241" text="Athens"/></option>
			<option value="1"><spring:message code="portal.portlet.label.243" text="Berlin"/></option>
			<option value="1"><spring:message code="portal.portlet.label.244" text="Brussels"/></option>
			<option value="2"><spring:message code="portal.portlet.label.245" text="Bucharest"/></option>
			<option value="1"><spring:message code="portal.portlet.label.246" text="Budapest"/></option>
			<option value="1"><spring:message code="portal.portlet.label.247" text="Copenhagen"/></option>
			<option value="0"><spring:message code="portal.portlet.label.248" text="Dublin"/></option>
			<option value="2"><spring:message code="portal.portlet.label.249" text="Helsinki"/></option>
			<option value="2"><spring:message code="portal.portlet.label.250" text="Istanbul"/></option>
			<option value="2"><spring:message code="portal.portlet.label.251" text="Kiev"/></option>
			<option value="0"><spring:message code="portal.portlet.label.252" text="Lisbon"/></option>
			<option value="0"><spring:message code="portal.portlet.label.253" text="London"/></option>
			<option value="1"><spring:message code="portal.portlet.label.254" text="Luxembourg"/></option>
			<option value="1"><spring:message code="portal.portlet.label.255" text="Madrid"/></option>
			<option value="2"><spring:message code="portal.portlet.label.256" text="Minsk"/></option>
			<option value="1"><spring:message code="portal.portlet.label.257" text="Monaco"/></option>
			<option value="3"><spring:message code="portal.portlet.label.258" text="Moscow"/></option>
			<option value="1"><spring:message code="portal.portlet.label.259" text="Oslo"/></option>
			<option value="1"><spring:message code="portal.portlet.label.260" text="Paris"/></option>
			<option value="1"><spring:message code="portal.portlet.label.261" text="Prague"/></option>
			<option value="2"><spring:message code="portal.portlet.label.262" text="Riga"/></option>
			<option value="1"><spring:message code="portal.portlet.label.263" text="Rome"/></option>
			<option value="2"><spring:message code="portal.portlet.label.264" text="Sofia"/></option>
			<option value="1"><spring:message code="portal.portlet.label.265" text="Stockholm"/></option>
			<option value="1"><spring:message code="portal.portlet.label.266" text="Vienna"/></option>
			<option value="1"><spring:message code="portal.portlet.label.267" text="Zurich"/></option>
			<option value="12"><spring:message code="portal.portlet.label.268" text="Auckland"/></option>
			<option value="12"><spring:message code="portal.portlet.label.269" text="Fiji"/></option>
			<option value="10"><spring:message code="portal.portlet.label.270" text="Guam"/></option>
			<option value="-10"><spring:message code="portal.portlet.label.271" text="Honolulu"/></option>
			<option value="-9"><spring:message code="portal.portlet.label.272" text="Tahiti"/></option>
		</select>
	</div>
	<p id="digiclock"></p>
	<p class="dateview time-font-gray02 mb10"></p>
</div>
<div class="clear"></div>