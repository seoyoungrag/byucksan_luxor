<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="org.anyframe.pagination.Page" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.ws.client.orgservice.*" %>

<%
	String selectType = CommonUtil.nullTrim(UtilRequest.getString(request, "selectType"));	
	boolean isRadio = false;
	boolean displayCheckbox = true;
	
	IUser vo = (IUser) request.getAttribute("userOne");

	if (UtilRequest.getString(request, "displayCheckbox") != null && UtilRequest.getString(request, "displayCheckbox").toString().equals("true")) {
		displayCheckbox = true;
		if (UtilRequest.getString(request, "selectType") != null && UtilRequest.getString(request, "selectType").toString().equals("single")) {
			isRadio = true;
		}else{
			isRadio = false;
		}
	}else{
		displayCheckbox = false;
	}
	
%>
<div id="scroll_wrap">
<table class="table-body" id="userList_table">
  <thead>
	<tr trId='chkAll'>
<%
		if( displayCheckbox ){
			if( isRadio ){
%>
				<th></th>
<%			}else{
%>				<th><input type='checkbox' id='chkAll' name='chkAll' /></th>
<%			}
		}
%>		<th><spring:message code="user.label.9" text="ID" /></th>
		<th><spring:message code="user.label.8" text="성명" /></th>
		<th id="compNameTt"><spring:message code="user.label.10" text="회사" /></th>
		<th id="deptNameTt"><spring:message code="user.label.11" text="부서" /></th>
		<th id="gradeTt"><spring:message code="user.label.12" text="직급" /></th>
		<th id="officeTelTt"><spring:message code="user.label.16" text="회사전화" /></th>
		<th id="emailTt" style="display: none;"><spring:message code="user.label.18" text="email" /></th>
		<th id="mobileTt" style="display: none;"><spring:message code="user.label.17" text="휴대폰" /></th>
		<th id="officeAddrTt" style="display: none;"><spring:message code="user.label.19" text="회사주소" /></th>
		<th id="officeFaxTt" style="display: none;"><spring:message code="user.label.20" text="회사FAX" /></th>
	</tr>
		</thead>

<%
		String chkName = "";
		String chkId = "";
		String chkValue = "";
		if(vo != null){
			chkName = "indexChk";
			chkId = "indexChk";
			chkValue = vo.getUserID() + " " + vo.getUserName() + " " + vo.getCompName() + " " + vo.getDeptName() + " " + vo.getGradeName() + " " + vo.getOfficeTel(); 
%>		
  		<tbody>		
		<tr trId='<%=chkId%>'>
<%
			if( displayCheckbox ){
				if( isRadio ){
%>
					<td><input type='radio' name='<%= chkName%>' id='<%= chkId%>' value='<%=chkValue%>' checked="checked"  class='box' /></td>
<%				}else{
%>
				<td><input type='checkbox' name='<%= chkName%>' id='<%= chkId%>' value='<%=chkValue%>' class='box' /></td>
<%				}
			}
%>	
			<td><%=vo.getUserID()%></td>
			<td>
				<a href="#" id="<%=vo.getUserID()%>">
					<%=vo.getUserName()%>
				</a>
			</td>
			<td id="0compNameTd"><%=vo.getCompName()%></td>
			<td id="0deptNameTd"><%=vo.getDeptName()%></td>
			<td id="0gradeTd"><%=vo.getGradeName()%></td>
			<td id="0officeTelTd"><%=vo.getOfficeTel()%></td>
			
			<td id="0emailTd" style="display: none;"><%=vo.getEmail()%></td>
			<td id="0mobileTd" style="display: none;"><%=vo.getMobile()%></td>
			<td id="0officeAddrTd" style="display: none;"><%=vo.getOfficeAddr()%></td>
			<td id="0officeFaxTd" style="display: none;"><%=vo.getOfficeFax()%></td>
		</tr>
<%		}else{
%>				
		<tr>
			<td colspan="7">
				<spring:message code="portal.alert.msg.19" text="데이터가 없습니다."/>
			</td>
		</tr>
<%		}
%>
</tbody>	
</table>
</div>
	<!-- 페이징 -->
	<jsp:include page="/luxor/common/paging.jsp">
		<jsp:param name="cPage" value="1" />
		<jsp:param name="totalCount" value="1" />
	</jsp:include>
	<!-- //페이징 -->


	<form id="MainForm" name="MainForm" target="" method="post">
	</form>

	<input type="hidden" name="userListSize" id="userListSize" value="1" />