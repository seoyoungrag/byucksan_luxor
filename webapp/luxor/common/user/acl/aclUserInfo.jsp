<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.sds.acube.luxor.ws.client.orgservice.*" %>

<%
	IUser vo = (IUser) request.getAttribute("aclUser");          
%>

<html lang="ko">
<head>
<title></title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript">
	$(document).ready(function() {

		// 확인 버튼 클릭시
		$('#btn_ok').click(function() {
			self.close();
		});
		
		
	});
</script>
</head>
<body>
	<div class="admin-content-width w580 ml10">
		<!-- subtitle -->
		<div class="title-sub">
			<span class="title-sub-text"><spring:message code="user.label.39" text="사용자정보" /></span>
		</div>
<%
		if(vo==null){
%>		
			<table class="table-write">
				<tr>
					<td colspan="7">
						<spring:message code="portal.alert.msg.19" text="데이터가 없습니다."/>
					</td>
				</tr>
			</table>
<%		
		}else{
%>
			<!-- //subtitle -->
			<table class="table-write">
			<thead>
				<tr>
					<th width="110">
						<spring:message code="user.label.28" text="성명(한글)" />
					</th>
					 <td width="140">
						<%=vo.getUserName()%>
					</td>
					<th width="110">
						<spring:message code="user.label.29" text="성명(영어)" />
					</th>
					 <td width="140">
						<%=vo.getUserOtherName()%>
					</td>
				</tr>
				</thead>
		        <tbody>
				<tr>
					<th>
						<spring:message code="user.label.9" text="ID" />
					</th>
					<td>
						<%=vo.getUserID()%>
					</td>
					<th>
						<spring:message code="user.label.30" text="사번" />
					</th>
					<td>
						<%=vo.getEmployeeID()%>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="user.label.10" text="회사" />
					</th>
					<td>
						<%=vo.getCompName()%>
					</td>
					<th>
						<spring:message code="user.label.11" text="부서" />
					</th>
					<td>
						<%=vo.getDeptName()%>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="user.label.15" text="직위" />
					</th>
					<td>
						<%=vo.getPositionName()%>
					</td>
					<th>
						<spring:message code="user.label.12" text="직급" />
					</th>
					<td>
						<%=vo.getGradeName()%>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="user.label.16" text="회사전화" />
					</th>
					<td>
						<%=vo.getOfficeTel()%>
					</td>
					<th>
						<spring:message code="user.label.20" text="회사FAX" />
					</th>
					<td>
						<%=vo.getOfficeFax()%>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="user.label.31" text="자택전화" />
					</th>
					<td>
						<%=vo.getHomeTel()%>
					</td>
					<th>
						<spring:message code="user.label.32" text="자택FAX" />
					</th>
					<td>
						<%=vo.getHomeFax()%>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="user.label.17" text="휴대폰" />
					</th>
					<td colspan="3">
						<%=vo.getMobile()%>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="user.label.19" text="회사주소" />
					</th>
					<td colspan="3">
						<%=vo.getOfficeAddr()%>
						<%=vo.getOfficeDetailAddr()%>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="user.label.33" text="자택주소" />
						
					</th>
					<td colspan="3">
						<%=vo.getHomeAddr()%>
						<%=vo.getHomeDetailAddr()%>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="user.label.13" text="직무" />
					</th>
					<td colspan="3">
						<%=vo.getDuty()%>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="user.label.18" text="email" />
					</th>
					<td colspan="3">
						<%=vo.getEmail()%>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="user.label.34" text="시스템mail" />
					</th>
					<td colspan="3">
						<%=vo.getSysMail()%>
					</td>
				</tr>
			</table>
<%		
		}
%>
		<div class="admin-right-bottom-btn-group">
			<span class="main-btn"><a href="#" id="btn_ok"><spring:message code="portal.btn.label.1" text="확인" /></a></span>
		</div>
	</div>
</body>
</html>	