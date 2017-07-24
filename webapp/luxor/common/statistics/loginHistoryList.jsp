<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="org.anyframe.pagination.Page" %>
<%@ page import="java.util.*" %>
<%
	Page _page = (Page)request.getAttribute("page");
	int listSize = 0;
	int cPage = 0;
	int totalCount = 0;
	
	List list = null;
	if(_page==null){
		listSize=0;
	}else{
		list = (List)_page.getList();	
		listSize = list.size();
		cPage = _page.getCurrentPage();
		totalCount = _page.getTotalCount();
	}
	
%>	

	<table class="table-body" id="loginList_table">
	<thead>
		<tr trId='chkAll'>
			<th width="40"><input name="checkbox" type="checkbox" id="checkAll" /></th>
			<th><spring:message code="statistics.label.4" text="로그인ID" /></th>
			<th><spring:message code="user.label.8" text="성명" /></th>
			<th><spring:message code="user.label.11" text="부서" /></th>
			<th><spring:message code="statistics.label.5" text="로그인IP" /></th>
			<th><spring:message code="statistics.label.6" text="로그인시간" /></th>
			<th><spring:message code="statistics.label.7" text="로그아웃시간" /></th>
		</tr>
	</thead>
	<tbody>
<%
		String chkName = "";
		String chkId = "";
		int i = 0;

		if(listSize>0){
			LoginHistoryVO[] logs = new LoginHistoryVO[list.size()];
			list.toArray(logs);	
			for(LoginHistoryVO vo : logs) {
				chkId = "indexChk" + i;
				String statisticsId = vo.getStatisticsId();
				String loginId = vo.getLoginId();
				String userNm = vo.getUserName();
				String deptNm = vo.getDptName();
				String loginIp = vo.getLoginIp();
				String loginTime = vo.getLoginTime2String();
				String logoutTime = vo.getLogOutTime2String();
%>			
				<tr onMouseOver=this.style.backgroundColor="#f3f3f3"  onMouseOut=this.style.backgroundColor=''>
					<td ><input name='indexChk' type='checkbox' id='<%=chkId %>' value='<%=statisticsId %>' class='box' /></td>
					<td><%=loginId %></td>
					<td><%=userNm %></td>
					<td><%=deptNm %></td>
					<td><%=loginIp %></td>
					<td><%=loginTime %></td>
					<td><%=logoutTime %></td>
				</tr>
<%				i++;
			}
		}else{
%>
				<tr>
					<td colspan="7">
						<spring:message code="portal.alert.msg.19" text="데이터가 없습니다." />
					</td>
				</tr>
<%		}
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
