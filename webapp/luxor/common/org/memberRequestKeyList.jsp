<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="org.anyframe.pagination.Page" %>
<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.common.service.*" %>

<%

	Page _page = (Page)request.getAttribute("memberRequestKeyPage");
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
			<th><spring:message code="portal.memberRequest.msg.17" text="계정 신청키" /></th>
			<th><spring:message code="portal.memberRequest.msg.13" text="회사" /></th>
			<th><spring:message code="portal.memberRequest.msg.14" text="부서" /></th>
			<th><spring:message code="portal.memberRequest.msg.15" text="직급" /></th>
			<th width="80"><spring:message code="portal.memberRequest.msg.32" text="사용 횟수" /></th>
			<th width="80"><spring:message code="portal.memberRequest.msg.25" text="횟수 한계" /></th>
		</tr>
	</thead>
	<tbody>
<%
		String chkName = "";
		String chkId = "";
		int i = 0; 

		if(listSize>0) {
			MemberRequestKeyVO[] memberRequestKeys = new MemberRequestKeyVO[list.size()];
			list.toArray(memberRequestKeys);	
			for(MemberRequestKeyVO vo : memberRequestKeys) {
				chkId = "indexChk" + i;
				String requestKey = vo.getRequestKey();
				String compName = vo.getCompName();
				String deptName = vo.getDeptName();
				String gradeName = vo.getGradeName();
				int useCount = vo.getUseCount();
			 	int countLimit = vo.getCountLimit();
%>			
				<tr>
					<td ><input name='indexChk' type='checkbox' id='<%=chkId %>' value='<%=requestKey %>' class='box' /></td>
					<td><%=requestKey%></td>
					<td><%=compName%></td>
					<td><%=deptName %></td>
					<td><%=gradeName %></td>
					<td><%=useCount %></td>
					<td><%=countLimit %></td>
				</tr>
<%				i++;
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
