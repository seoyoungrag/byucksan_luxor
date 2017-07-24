<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="org.anyframe.pagination.Page" %>
<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.common.service.*" %>

<%

	Page _page = (Page)request.getAttribute("page");
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
			<th><spring:message code="statistics.label.10" text="페이지ID" /></th>
			<th><spring:message code="portal.label.58" text="페이지명" /></th>
			<th><spring:message code="statistics.label.12" text="호출UserID" /></th>
			<th><spring:message code="user.label.8" text="성명" /></th>
			<th><spring:message code="statistics.label.5" text="로그인IP" /></th>
			<th><spring:message code="statistics.label.13" text="호출일시" /></th>
		</tr>
	</thead>
	<tbody>
<%
		String chkName = "";
		String chkId = "";
		int i = 0; 

		if(listSize>0) {
			PageStatisticsVO[] pageStatistics = new PageStatisticsVO[list.size()];
			list.toArray(pageStatistics);	
			for(PageStatisticsVO vo : pageStatistics) {
				chkId = "indexChk" + i;
				String statisticsId = vo.getStatisticsId();
				String pageId = vo.getPageId();
				String pageName = vo.getMessageName();
				String accessUserName = vo.getAccessUserName();
				String loginIp = vo.getLoginIp();
				String accessTime = vo.getAccessTime2String();
				String accessUserId = vo.getAccessUserId();
%>			
				<tr>
					<td ><input name='indexChk' type='checkbox' id='<%=chkId %>' value='<%=statisticsId %>' class='box' /></td>
					<td><%=pageId%></td>
					<td><%=pageName.equals("") ? "Deleted Page" : pageName%></td>
					<td><%=accessUserId.equals("") ? "GUEST" : accessUserId %></td>
					<td><%=accessUserName %></td>
					<td><%=loginIp %></td>
					<td><%=accessTime %></td>
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
