<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page import="java.util.List" %>
<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.portal.PortletConstant" %>
<%@ page import="com.sds.acube.luxor.common.vo.UserProfileVO" %>
<%@ page import="com.sds.acube.luxor.common.util.CommonUtil" %>

<%
UserProfileVO userProfile = (UserProfileVO)session.getAttribute("userProfile");
if(userProfile == null || (userProfile.getLoginResult() != 0)) {
%>
<spring:message code="portal.portlet.label.110" text="로그인이 필요합니다."/>
<script type="text/javascript">
var userUid = "";
</script>
<%
} else {
%>
<script type="text/javascript">
var userUid = "<%=userProfile.getUserUid()%>";
var strInitCategory= "<spring:message code="portal.portlet.label.178" text="기본 카테고리"/>";
var strUrlHide = "<spring:message code="portal.portlet.label.179" text="url 숨기기"/>";
var strInsertCategory = "<spring:message code="portal.portlet.label.172" text="카테고리 등록"/>";
var strInsertBookmark = "<spring:message code="portal.portlet.label.173" text="북마크 등록"/>";
var strEditBookmark = "<spring:message code="portal.portlet.label.180" text="북마크 수정"/>";
var strIsDeleteBookmark = "<spring:message code="portal.portlet.alert.1" text="북마크를 삭제하시겠습니까?"/>";
var strIsDeleteCategory = "<spring:message code="portal.portlet.alert.2" text="카테고리 삭제시 하위의 북마크가 모두 삭제됩니다.삭제하시겠습니까?"/>";
var strEditCategory = "<spring:message code="portal.portlet.label.181" text="카테고리 수정"/>";
var strError = "<spring:message code="portal.portlet.alert.5" text="에러가 발생하였습니다."/>";
var strSuccess = "<spring:message code="portal.portlet.alert.6" text="정상적으로 처리되었습니다."/>";
</script>
<div id="bookmarkPortlet">
<div class="built-in-portlet-tab">
    <ul id="bookmarkCategoryTab">
    </ul>
</div>
<div id="bookmarkList" style="height:150px;overflow:auto;">
</div>
<div class="clear"></div>
</div>
<%
}
%>