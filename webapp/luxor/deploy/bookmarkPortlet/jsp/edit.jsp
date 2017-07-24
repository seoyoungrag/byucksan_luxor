<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page import="java.util.List" %>
<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.portal.PortletConstant" %>
<%@ page import="com.sds.acube.luxor.common.vo.UserProfileVO" %>
<%@ page import="com.sds.acube.luxor.common.util.CommonUtil" %>

<%
UserProfileVO userProfile = (UserProfileVO)session.getAttribute("userProfile");
if(userProfile==null) {
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
var strIsDeleteCategory = "<spring:message code="portal.portlet.alert.2" text="카테고리 삭제시 하위의 북마크가 모두 삭제됩니다.\n삭제하시겠습니까?"/>";
var strEditCategory = "<spring:message code="portal.portlet.label.181" text="카테고리 수정"/>";
var strError = "<spring:message code="portal.portlet.alert.5" text="에러가 발생하였습니다."/>";
var strSuccess = "<spring:message code="portal.portlet.alert.6" text="정상적으로 처리되었습니다."/>";
</script>

<div id="bookmarkPortletEdit">
	<div class="built-in-portlet-tab">
	    <ul id="bookmarkCategoryTab">
	    </ul>
	</div>
	
	<div id="bookmarkList" style="height:150px;overflow:auto;">
	</div>
	<div class="clear mt5"></div>
	<span id="bookmarkAddCategory" class="smain-btn float-r"><a><spring:message code="portal.portlet.label.172" text="카테고리 등록"/></a></span>
	<span id="bookmarkAdd" class="smain-btn float-r mr5"><a><spring:message code="portal.portlet.label.173" text="북마크 등록"/></a></span>
	<div class="clear"></div>
	
	<div id="editCategoryDialog" class="bookmarkDialog" style="display: none">
		<span class="portlet-black-bold" style="margin-right:5px;"><spring:message code="portal.portlet.label.174" text="카테고리명"/></span>
		<input id="bookmarkName" class="input-d" type="text" style="width:70%;"/><div class="clear mt5"></div>
		<div id="bookmarkInitOption">
		</div>
		<div class="clear mt5"></div><span id="EditCategory" class="smain-btn float-r"><a><spring:message code="portal.portlet.label.170" text="적용"/></a></span>
		<span id="bookmarkEditcancel" class="smain-btn float-r mr5"><a><spring:message code="portal.portlet.label.171" text="취소"/></a></span>
		<input id="bookmarkId" type="hidden"></input>
	</div>
	
	<div id="editBookmarkDialog" class="bookmarkDialog" style="display: none">
		<span class="portlet-black-bold" style="margin-right:5px;"><spring:message code="portal.portlet.label.174" text="카테고리명"/></span>
		<select id="categorySelect">
		</select>
		<div class="clear mt5"></div>
		
		<span class="portlet-black-bold" style="margin-right:5px;"><spring:message code="portal.portlet.label.175" text="북마크제목"/></span>
		<input id="bookmarkName" class="input-d" type="text" style="width:70%;"/><div class="clear mt5"></div>
		
		<span class="portlet-black-bold" style="margin-right:5px;"><spring:message code="portal.portlet.label.176" text="북마크경로"/></span>
		<input id="bookmarkUrl" class="input-d" type="text" style="width:70%;"></input><div class="clear mt5"></div>
		
		<span class="portlet-black-bold" style="margin-right:5px;"><spring:message code="portal.portlet.label.177" text="북마크설명"/></span>
		<textarea id="description" class="input-d" style="width:70%;height:80px;"></textarea><div class="clear mt5"></div>
		
		<span id="bookmarkOption" class="portlet-black-bold" style="margin-right:5px;"></span>
		
		<div class="clear mt5"></div><span id="EditBookmark" class="smain-btn float-r"><a><spring:message code="portal.portlet.label.170" text="적용"/></a></span>
		<span id="bookmarkEditcancel" class="smain-btn float-r mr5"><a><spring:message code="portal.portlet.label.171" text="취소"/></a></span>
		
		<input id="bookmarkId" type="hidden"></input>
	</div>
</div>
<%
}
%>