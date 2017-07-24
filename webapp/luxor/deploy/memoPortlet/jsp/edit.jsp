<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.sds.acube.luxor.common.vo.UserProfileVO" %>
<%@ page import="com.sds.acube.luxor.common.util.CommonUtil" %>
<%
UserProfileVO userProfile = (UserProfileVO)session.getAttribute("userProfile");
%>
<script type="text/javascript">

var msgModifyError = "<spring:message code="portal.portlet.alert.3" text="시스템 문제로 변경이 되지 않았습니다."/>";
var msgSuccess = "<spring:message code="portal.portlet.alert.4" text="변경이 완료되었습니다."/>";
var msgInitMemo = "<spring:message code="portal.portlet.label.111" text="메모를 입력하세요."/>";
var memoId ="";
var value = "";

function getMemo(userUid) {
	var param = "userId="+userUid;
	callJson("generalPortletController", "getMemo", param, function(data) {
		if(data.memoId==null){
			memoId ="not memorized";
		} else {
			memoId = data.memoId;
			value = data.memoValue;
		}
		
		setMemoSkin(data.memoSkin);
	});
}

function setMemoSkin(memoSkin){
	var memoFontSize = "";
	var memoSkinStyle = "default";
	var memoFontStyle = "gulim";	
	
	if(memoSkin != null){
		var memoSkinArray = memoSkin.split(" ");
		if(memoSkinArray.length > 1){
			memoFontStyle = memoSkinArray[1];
			var memoSkinFont = memoSkinArray[0];
			
			var memoSkinFontArray = memoSkinFont.split("_");
			if(memoSkinFontArray.length > 1){
				memoSkinStyle = memoSkinFontArray[0];
				memoFontSize = "_" + memoSkinFontArray[1];			
			}else{
				memoSkinStyle = memoSkinFontArray[0];
				memoFontSize = "";		
			}
		}
	}
	
	$('#memoFontSize option[value=' + memoFontSize + ']').attr('selected', 'selected');
	$('#memoSkinStyle option[value=' + memoSkinStyle + ']').attr('selected', 'selected');
	$('#memoFontStyle option[value=' + memoFontStyle + ']').attr('selected', 'selected');
}

$(document).ready(function() {
	if(userUid != '') {
		getMemo(userUid);
		value = value.replace(/\n/gi,"<br>");
		
		$('#memoStyleChangeBtn').click(function(e) {
			var memoSkin = $('#memoSkinStyle').val()+$('#memoFontSize').val()+' '+$('#memoFontStyle').val();
			var param = "";
			if(memoId=="not memorized") {	
				var encodeValue = encodeURIComponent(msgInitMemo);
				param = "userId="+userUid+"&memoSkin="+memoSkin+"&memoId="+luxor.generateId()+"&memoValue="+encodeValue;
				callJson("generalPortletController", "insertMemo", param, function(data) {
					if(data==false){
						alert(msgModifyError);
					} else {
						alert(msgSuccess);
					}
				});
			} else {
				var encodeValue = encodeURIComponent(value);
				param = "userId="+userUid+"&memoSkin="+memoSkin+"&memoId="+memoId+"&memoValue="+encodeValue;
				callJson("generalPortletController", "updateMemo", param, function(data) {
					if(data==false){
						alert(msgModifyError);
					} else {
						alert(msgSuccess);
					}
				});
			}
		});
	}
});
</script>
<%
if(userProfile==null) {
%>
	<spring:message code="portal.portlet.label.110" text="로그인이 필요합니다."/>
<%
} else {
%>
	<script type="text/javascript">
	var userUid = "<%=userProfile.getUserUid()%>";
	</script>
	<div class="title-pop">
		<span class="title-pop-text">
		<spring:message code="portal.portlet.label.112" text="메모장 관리"/>
		</span>
	</div>
	<div class="portlet-main mt5 clear" style="width:100%;">
	    <span class="portlet-black-bold" ><spring:message code="portal.portlet.label.113" text="폰트크기"/></span>
	    <select id="memoFontSize" class="select-d" style="width:70%;">
	       	<OPTION VALUE=""><spring:message code="portal.portlet.label.114" text="보통"/></OPTION>
	       	<OPTION VALUE="_big"><spring:message code="portal.portlet.label.115" text="크게"/></OPTION>
	    </select>
		<div class="clear mt5"></div>
	    <span class="portlet-black-bold" style="width:30%;"><spring:message code="portal.portlet.label.116" text="스킨선택"/></span>
	    <select id="memoSkinStyle" class="select-d tounits" style="width:70%;">
	       	<OPTION VALUE="default"><spring:message code="portal.portlet.label.117" text="기본"/></OPTION>
			<OPTION VALUE="blue" ><spring:message code="portal.portlet.label.118" text="하늘색노트"/></OPTION>
			<OPTION VALUE="orange"><spring:message code="portal.portlet.label.119" text="노을색노트"/></OPTION>
			<OPTION VALUE="feather"><spring:message code="portal.portlet.label.120" text="깃털노트"/></OPTION>
	    </select>
	    <div class="clear mt5"></div>
	    <span class="portlet-black-bold" style="width:30%;"><spring:message code="portal.portlet.label.121" text="폰트선택"/></span>
	    <select id="memoFontStyle" class="select-d tounits" style="width:70%;">
	       	<OPTION VALUE="gulim"><spring:message code="portal.portlet.label.122" text="굴림"/></OPTION>
			<OPTION VALUE="arial"><spring:message code="portal.portlet.label.123" text="Arial"/></OPTION>
			<OPTION VALUE="arialblack"><spring:message code="portal.portlet.label.124" text="Arial Black"/> </OPTION>
	    </select>
	</div>
	<div class="clear mt5"></div>
	<span id="memoStyleChangeBtn" class="smain-btn float-r"><a><spring:message code="portal.portlet.label.125" text="변경하기"/></a></span>
	<div class="clear"></div>
<%
}
%>

