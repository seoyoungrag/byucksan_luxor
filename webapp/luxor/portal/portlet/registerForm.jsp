<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.sds.acube.luxor.portal.vo.PortletCategoryVO" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	PortletCategoryVO[] categories = (PortletCategoryVO[])request.getAttribute("categories");
%>
<form id="registerForm" name="registerForm" action="/ep/portlet/portletRegister.do" method="post">
<div id="tabs">
	<ul>
		<li><a href="#basicinfo" title="<spring:message code="portal.label.125" text="기본정보"/>"><spring:message code="portal.label.125" text="기본 정보"/></a></li>
		<li><a href="#urlinfo" title="<spring:message code="portal.label.126" text="URL 정보"/>"><spring:message code="portal.label.126" text="URL 정보"/></a></li>
		<li><a href="#etcinfo" title="<spring:message code="portal.label.127" text="추가 정보"/>"><spring:message code="portal.label.127" text="추가 정보"/></a></li>
	</ul>
	<div id="basicinfo">
		<table class="table-write">
			<tr>
				<th width="100"><spring:message code="portal.label.107" text="컨텍스트 명"/>*</th>
				<td><input type="text" name="contextName" id="contextName" class="input-d only-alphabet-number-underbar" style="width:100%;" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="portal.label.108" text="제목"/>*</th>
				<td><input type="text" name="title" id="title" class="input-d no-special-char" style="width:100%;" /></td>
			</tr>
			<tr>
				<th><spring:message code="portal.label.109" text="카테고리"/></th>
				<td colspan="3">
					<select id="selectCategory" name="categoryId">
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
					<input type="radio" name="typeOfPortlet" value="0" id="genericR" checked style="margin-bottom:4px;" /><label for="genericR"><spring:message code="portal.label.132" text="일반 포틀릿" /></label>
					<input type="radio" name="typeOfPortlet" value="1" id="iframeR" style="margin-bottom:4px;" /><label for="iframeR"><spring:message code="portal.label.133" text="IFRAME 포틀릿" /></label><br>
					<input type="radio" name="typeOfPortlet" value="2" id="menuR" style="margin-bottom:4px;" /><label for="menuR"><spring:message code="portal.label.134" text="MENU 포틀릿" /></label>
					<input type="radio" name="typeOfPortlet" value="3" id="tabR" style="margin-bottom:4px;" /><label for="tabR"><spring:message code="portal.label.500" text="TAB 포틀릿" /></label>
					<input type="radio" name="typeOfPortlet" value="4" id="imgR" style="margin-bottom:4px;" /><label for="imageR"><spring:message code="portal.label.501" text="IMG 포틀릿" /></label>
				</td>
			</tr>
			<tr>
				<th width="100"><spring:message code="portal.label.112" text="SSO 정보"/></th>
				<td>
					<input type="radio" name="ssoInfo" id="ssoInfo_na" value="N/A" checked style="margin-bottom:4px;" /><label for="ssoInfo_na">N/A</label>
					<input type="radio" name="ssoInfo" id="ssoInfo_d1" value="D1" style="margin-bottom:4px;" /><label for="ssoInfo_d1">D1</label>
				</td>
			</tr>
			<tr>
				<th><spring:message code="portal.label.131" text="설명"/></th>
	            <td><textarea name="outline" id="outline" class="textarea-d" style="width:100%; height:70px;"></textarea></td>
			</tr>
		</table>
	</div>
	<div id="urlinfo">
		<table class="table-write">
			<tr>
				<th width="100"><spring:message code="portal.label.121" text="View URL"/>*</th>
				<td><input type="text" name="viewUrl" id="viewUrl" class="input-d" style="width:100%;" /></td>
			</tr>
			<tr>
				<th><spring:message code="portal.label.122" text="Edit URL"/></th>
				<td><input type="text" name="editUrl" id="editUrl" class="input-d" style="width:100%;" /></td>
			</tr>
			<tr>
				<th><spring:message code="portal.label.123" text="Help URL"/></th>
				<td><input type="text" name="helpUrl" id="helpUrl" class="input-d" style="width:100%;" /></td>
			</tr>
			<tr>
				<th><spring:message code="portal.label.124" text="Go URL"/></th>
				<td><input type="text" name="goUrl" id="goUrl" class="input-d" style="width:100%;" /></td>
			</tr>
			<tr id="imgUrlLable">
				<th><spring:message code="portal.label.502" text="IMG URL"/></th>
				<td><input type="text" name="imgUrl" id="imgUrl" class="input-d" style="width:100%;" /></td>
			</tr>			
		</table>
	</div>
	<div id="etcinfo">
		<table class="table-write">
			<tr>
				<th width="100"><spring:message code="portal.label.135" text="Script Reference"/></th>
				<td colspan="2">
					<div id="scriptDiv"><input type="text" name="scriptRef" id="script0" class="input-d" style="width:100%;" /></div>
				</td>
				<td>
					<span class="smain-btn"><a href="#" id="btnAddScript" onclick="addScript();return false;" title="<spring:message code="portal.btn.label.34" text="추가"/>"><spring:message code="portal.btn.label.34" text="추가"/></a></span>
				</td>
			</tr>
			<tr>
				<th><spring:message code="portal.label.136" text="Style Reference"/></th>
				<td colspan="2">
					<div id="styleDiv"><input type="text" name="styleRef" id="style0" class="input-d" style="width:100%;" /></div>
				</td>
				<td>
					<span class="smain-btn"><a href="#" id="btnAddStyle" onclick="addStyle();return false;" title="<spring:message code="portal.btn.label.34" text="추가"/>"><spring:message code="portal.btn.label.34" text="추가"/></a></span>
				</td>
			</tr>
		</table>
	</div>
</div>
<div class="mb5"></div>
<div class="aright">
	<span class="main-btn"><a href="#" id="btnApply" onclick="apply();return false;"  title="<spring:message code="portal.btn.label.3" text="저장"/>"><spring:message code="portal.btn.label.3" text="저장"/></a></span><!-- 저장 -->
	<span class="main-btn"><a href="#" id="btnCancel" onclick="cancel();return false;"  title="<spring:message code="portal.btn.label.2" text="취소"/>"><spring:message code="portal.btn.label.2" text="취소"/></a></span><!-- 취소 -->
</div>
</form>
