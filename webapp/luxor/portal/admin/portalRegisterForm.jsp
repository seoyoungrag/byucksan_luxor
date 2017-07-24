<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page import="com.sds.acube.luxor.common.ConstantList" %>

<form id="registerForm" name="registerForm" action="/ep/admin/groupportal/portalRegister.do" method="post">
<table class="table-write">
	<tr>
		<th><spring:message code="portal.label.168" text="상위포탈ID"/></th>
		<td colspan="3">
			<!--  <input type="text" name="parentId" id="parentId" class="input-d" style="width:100%;" value="<%=ConstantList.ROOT_PORTAL_ID%>"/>-->
			<select name="parentId" id="parentId">
			<option value="<%=ConstantList.ROOT_PORTAL_ID%>" selected><%=ConstantList.ROOT_PORTAL_ID%></option>
			</select>
		</td>
	</tr>
	<tr>
		<th width="100"><spring:message code="portal.label.163" text="포탈 아이디"/></th>
		<td colspan="3"><input type="text" name="portalId" id="portalId" class="input-d only-alphabet-number-underbar" style="width:75%;" />
			&nbsp;<a href="#" onclick="checkDup();return false;"><spring:message code="portal.label.128" text="중복확인"/></a>
		</td>
	</tr>
	<tr>
		<th><spring:message code="portal.label.164" text="포탈 명"/></th>
		<td colspan="3">
			<input type="text" name="portalName" id="portalName" class="input-d no-special-char" style="width:100%;" />
		</td>
	</tr>

	<tr>
		<th><spring:message code="portal.label.165" text="연결 조직 아이디"/></th>
		<td colspan="3">
			<input type="text" name="relatedCompname" id="relatedCompname" class="input-d only-alphabet-number-underbar" style="width:75%;" />
			&nbsp;<a href="#" onclick="orgPopup();return false;"><spring:message code="portal.btn.label.8" text="검색"/></a>
			<input type="hidden" name="relatedCompid" id="relatedCompid"   />
			<input type="hidden" name="deptinfo" id="deptinfo" />
		</td>
	</tr>
	<tr>
		<th><spring:message code="portal.label.169" text="연결포탈정보"/></th>
		<td colspan="3">
			<input type="radio" name="relatedPortalinfo" id="relatedPortalinfo"  value="S" checked /><spring:message code="portal.label.163" text="포탈 아이디"/>
			<input type="radio" name="relatedPortalinfo" id="relatedPortalinfo"  value="P"/><spring:message code="portal.label.168" text="상위포탈ID"/>
		</td>
	</tr>

</table>
<div class="mb5"></div>
<div class="aright">
	<span class="main-btn"><a href="#" id="btnApply" onclick="apply();return false;" title="<spring:message code="portal.btn.label.3" text="저장"/>"><spring:message code="portal.btn.label.3" text="저장"/></a></span><!-- 저장버튼 -->
	<span class="main-btn"><a href="#" id="btnCancel" onclick="cancel();return false;" <spring:message code="portal.btn.label.2" text="취소"/>"><spring:message code="portal.btn.label.2" text="취소"/></a></span><!-- 취소버튼 -->
</div>
</form>
