<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-loose.dtd">
<%@ page import="com.sds.acube.luxor.common.util.CommonUtil"%>
<%@page import="java.util.Locale"%>
<%
	Locale langType = (Locale)session.getAttribute("LANG_TYPE");
%>
<script type="text/javascript">
var portal_portlet_label_151 = "<spring:message code="portal.portlet.label.151" text="년"/>";
var portal_portlet_label_152 = "<spring:message code="portal.portlet.label.152" text="월"/>";
var portal_portlet_label_153 = "<spring:message code="portal.portlet.label.153" text="일"/>";
var portal_portlet_label_154 = "<spring:message code="portal.portlet.label.154" text="발표"/>";
var portal_portlet_label_155 = "<spring:message code="portal.portlet.label.155" text="날짜"/>";
var portal_portlet_label_156 = "<spring:message code="portal.portlet.label.156" text="오늘"/>";
var portal_portlet_label_157 = "<spring:message code="portal.portlet.label.157" text="내일"/>";
var portal_portlet_label_158 = "<spring:message code="portal.portlet.label.158" text="모레"/>";
var portal_portlet_label_159 = "<spring:message code="portal.portlet.label.159" text="시간"/>";
var portal_portlet_label_160 = "<spring:message code="portal.portlet.label.160" text="날씨"/>";
var portal_portlet_label_161 = "<spring:message code="portal.portlet.label.161" text="기온(℃)"/>";
var portal_portlet_label_162 = "<spring:message code="portal.portlet.label.162" text="강수(mm)"/>";
var portal_portlet_label_163 = "<spring:message code="portal.portlet.label.163" text="강수확률(%)"/>";
var portal_portlet_label_164 = "<spring:message code="portal.portlet.label.164" text="습도(%)"/>";
var portal_portlet_label_165 = "<spring:message code="portal.portlet.label.165" text="바람(m/s)"/>";
var portal_portlet_alert_8 ="<spring:message code="portal.portlet.alert.8" text="일시적인 시스템 오류로 데이터를 불러오지 못 하였습니다."/>";

var langType = "<%=langType%>";

</script>
<p id="weather_title" class="weather-title"><spring:message code="portal.portlet.label.150" text="오늘날씨"/></p>
<div id="weather_portlet" align="center">
</div>