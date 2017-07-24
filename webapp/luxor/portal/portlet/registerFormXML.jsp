<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.sds.acube.luxor.portal.vo.PortletCategoryVO" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	PortletCategoryVO[] categories = (PortletCategoryVO[])request.getAttribute("categories");
%>
<form id="registerXmlForm" name="registerXmlForm" action="/ep/portlet/portletRegisterByXML.do" method="post" enctype="multipart/form-data">
<div class="tb-write"> 
	<table class="table-write">
		<tr>
			<th><spring:message code="portal.label.109" text="카테고리"/></th>
			<td>
				<select id="selectCategoryXML" name="categoryId">
					<option value="" selected> = <spring:message code="portal.label.129" text="카테고리 선택"/> = </option>
<%
	if(categories != null) {
		for(int i=0;i<categories.length;i++){
%>
						<option value="<%=categories[i].getCategoryId()%>"><%=categories[i].getCategoryName()%></option>
<%
		}
	}
%>
					<option value="add"> = <spring:message code="portal.label.130" text="카테고리 추가"/> = </option>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="portal.label.110" text="구분"/></th>
			<td>
				<input type="radio" name="typeOfPortlet" value="0" id="genericRXML" checked /><label for="genericRXML"><spring:message code="portal.label.132" text="일반 포틀릿" /></label>
				<input type="radio" name="typeOfPortlet" value="1" id="iframeRXML" /><label for="iframeRXML"><spring:message code="portal.label.133" text="IFRAME 포틀릿" /></label>
				<input type="radio" name="typeOfPortlet" value="2" id="menuRXML" /><label for="menuRXML"><spring:message code="portal.label.134" text="MENU 포틀릿" /></label>
			</td>
		</tr>
		<tr>
			<th width="100"><spring:message code="portal.label.137" text="등록 XML"/></th>
			<td><input type="file" id="upfile" name="upfile" onkeydown="return false" /></td>
		</tr>
	</table>
</div>
<div class="aright">
	<span class="main-btn"><a href="#" id="btnApplyXML" onclick="applyXML();return false;" title="<spring:message code="portal.btn.label.3" text="저장"/>"><spring:message code="portal.btn.label.3" text="저장"/></a></span> <!-- 저장버튼 -->
	<span class="main-btn"><a href="#" id="btnCancelXML" onclick="$('#popupDiv3').dialog('close');return false;"  title="<spring:message code="portal.btn.label.2" text="취소"/>"><spring:message code="portal.btn.label.2" text="취소"/></a></span><!-- 취소 -->
</div>
</form>
<spring:message code="portal.content.alert.9" text="* XML은 영문으로만 입력되어야 합니다."/>