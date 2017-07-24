<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.sds.acube.luxor.common.ConstantList"%>
<%@ page import="java.util.*" %>
<%@ page import="org.anyframe.pagination.Page" %>
<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.common.service.*" %>

<%

	Page _page = (Page)request.getAttribute("memberRequestPage");
	int listSize = 0;
	int cPage = 0;
	int totalCount = 0;

	List list = null;
	if(_page==null) {
		listSize=0;
	} else {
		list = (List)_page.getList();	
		listSize = list.size();
		cPage = _page.getCurrentPage();
		totalCount = _page.getTotalCount();
	}
%>	
	
<table class="table-body">
	<thead>
		<tr trId='chkAll'>
			<th width="40"><input name="checkbox" type="checkbox" id="checkAll" /></th>
			<th><spring:message code="portal.memberRequest.msg.11" text="로그인ID" /></th>
			<th><spring:message code="portal.memberRequest.msg.12" text="사용자명" /></th>
			<th><spring:message code="portal.memberRequest.msg.13" text="회사" /></th>
			<th><spring:message code="portal.memberRequest.msg.14" text="부서" /></th>
			<th><spring:message code="portal.memberRequest.msg.15" text="직급" /></th>
			<th><spring:message code="portal.memberRequest.msg.16" text="신청시 접근IP" /></th>
			<th><spring:message code="portal.memberRequest.msg.17" text="계정 신청키" /></th>
			<th><spring:message code="portal.memberRequest.msg.18" text="상태" /></th>
		</tr>
	</thead>
	<tbody>
<%
		String chkName = "";
		String chkId = "";
		int i = 0; 

		if(listSize>0) {
			MemberRequestVO[] memberRequests = new MemberRequestVO[list.size()];
			list.toArray(memberRequests);	
			for(MemberRequestVO vo : memberRequests) {
				chkId = "indexChk" + i;
				String requestId = CommonUtil.nullTrim(vo.getRequestId());
				String requestKey = CommonUtil.nullTrim(vo.getRequestKey());
				String compName =  CommonUtil.nullTrim(vo.getCompName());
				String deptName =  CommonUtil.nullTrim(vo.getDeptName());
				String gradeName =  CommonUtil.nullTrim(vo.getGradeName());
				String loginId =  CommonUtil.nullTrim(vo.getLoginId());
				String userName =  CommonUtil.nullTrim(vo.getUserName());
				String loginIp =  CommonUtil.nullTrim(vo.getLoginIp());
				String requestStatus = ConstantList.MEMBER_REQUEST_STATUS[vo.getRequestStatus()];
				//승인되었거나, 사용확정되었을 경우는 승인/반려 제외
				if(vo.getRequestStatus() == 1 || vo.getRequestStatus() == 3) {
					requestId = "approved";
				}
%>			
				<tr>
					<td ><input name='indexChk' type='checkbox' id='<%=chkId %>' value='<%=requestId %>' class='box' /></td>
					<td><%=loginId%></td>
					<td><%=userName%></td>
					<td><%=compName%></td>
					<td><%=deptName %></td>
					<td><%=gradeName %></td>
					<td><%=loginIp%></td>
					<td><%=requestKey%></td>
					<td><spring:message code="<%=requestStatus%>" text=""/></td>
				</tr>
<%	
			}
		} else {
%>
				<tr>
					<td colspan="7">
						<spring:message code="portal.alert.msg.19" text="데이터가 없습니다."/>
					</td>
				</tr>
<%						
		}
%>
	</tbody>
	</table>
	<!-- 페이징 -->
	<jsp:include page="/luxor/common/paging.jsp">
		<jsp:param name="cPage" value="<%=cPage %>" />
		<jsp:param name="totalCount" value="<%=totalCount %>" />
		<jsp:param name="listCount" value="20" />
	</jsp:include>
	<!-- //페이징 -->
