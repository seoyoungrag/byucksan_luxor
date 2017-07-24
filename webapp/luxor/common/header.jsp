<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page import="java.util.*" %>
<%@ page import="com.sds.acube.luxor.common.*" %>
<%@ page import="com.sds.acube.luxor.framework.config.*" %>
<%@page import="com.sds.acube.luxor.common.util.CommonUtil"%>
<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "No-cache");
	String luxorContextPath = request.getContextPath();
%>

<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="<%=luxorContextPath %>/luxor/ref/css/jquery-ui-default.css" />
<link rel="stylesheet" type="text/css" href="<%=luxorContextPath %>/luxor/ref/css/jquery-ui-theme.css" />
<link rel="stylesheet" type="text/css" href="<%=luxorContextPath %>/luxor/ref/css/default.css" />
<link rel="stylesheet" href="<%=luxorContextPath %>/luxor/ref/js/attachment/jquery/jquery.fileupload-ui.css">
<script type="text/javascript">
	var portal_label_205 = "<spring:message code="portal.label.205" text="에 이미 설정되어있습니다." />";
	var portal_alert_msg_4 = "<spring:message code="portal.alert.msg.4" text="삭제 시 하위 폴더까지 삭제됩니다. 정말 삭제하시겠습니까?" />";
	var portal_alert_msg_5 = "<spring:message code="portal.alert.msg.5" text="최상위 폴더는 삭제할 수 없습니다." />";
	var portal_alert_msg_6 = "<spring:message code="portal.alert.msg.6" text="최상위 폴더는 수정할 수 없습니다." />";
	var portal_alert_msg_7 = "<spring:message code="portal.alert.msg.7" text="폴더를 먼저 선택하세요." />";
	var portal_alert_msg_9 = "<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />";
	var portal_alert_msg_10 = "<spring:message code="portal.alert.msg.10" text="이미 추가되어있습니다. 한 페이지에 같은 컨텐츠를 두개 등록할 수 없습니다." />";
	var portal_alert_msg_12 = "<spring:message code="portal.alert.msg.12" text="삭제하시겠습니까?" />";
	var portal_alert_msg_14 = "<spring:message code="portal.alert.msg.14" text="미리보기가 없습니다." />";
	var portal_alert_msg_32 = "<spring:message code="portal.alert.msg.32" text="관리자가 추가한 컨텐츠는 삭제할 수 없습니다." />";
	var portal_alert_msg_40 = "<spring:message code="portal.alert.msg.40" text="특수문자는 허용되지 않습니다." />";
	var portal_alert_msg_40_1 = "<spring:message code="portal.alert.msg.40.1" text="숫자만 입력할 수 있습니다." />";
	var portal_alert_msg_41 = "<spring:message code="portal.alert.msg.41" text="알파벳, 숫자, 언더바(_)만 입력할 수 있습니다." />";
	var portal_alert_msg_42 = "<spring:message code="portal.alert.msg.42" text="최상위 폴더 위로 이동할 수 없습니다." />";
	var portal_page_alert_4 = "<spring:message code="portal.page.alert.4" text="중복체크가 이루어지지 않았습니다." />";
	var portal_alert_msg_15 = "<spring:message code="portal.alert.msg.15" text="페이지를 찾을수 없습니다." />";
	var portal_alert_msg_26 = "<spring:message code="portal.alert.msg.26" text="검색어를 입력하세요" />";
	var portal_alert_msg_56 = "<spring:message code="portal.alert.msg.56" text="검색 결과가 없습니다." />";
	var portal_btn_label_22 = "<spring:message code="portal.btn.label.22" text="컨텐츠 추가" />";
	var portal_content_label_14 = "<spring:message code="portal.content.label.14" text="[고정]" />";
	var portal_content_label_15 = "<spring:message code="portal.content.label.15" text="[수정모드]" />";
	var portal_content_label_16 = "<spring:message code="portal.content.label.16" text="[고정모드]" />";
	var context_root = "${pageContext.request.contextPath}";
</script>
<script type="text/javascript" src="<%=luxorContextPath %>/luxor/ref/js/jquery-1.4.3.min.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=luxorContextPath %>/luxor/ref/js/jquery-ui-1.8.16.custom.min.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=luxorContextPath %>/luxor/ref/js/luxor.js" charset="utf-8"></script>