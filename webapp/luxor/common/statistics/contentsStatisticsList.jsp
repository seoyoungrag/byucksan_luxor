<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="org.anyframe.pagination.Page" %>
<%@ page import="java.util.*" %>
<%
	Page _page = (Page)request.getAttribute("page");
	int cPage = 0;
	int totalCount = 0;

	List list = null;
	int listSize = 0;
	
	if(_page!=null){
		list = (List)_page.getList();	
		listSize = list.size();
		cPage = _page.getCurrentPage();
		totalCount = _page.getTotalCount();
	}

%>	

	<table class="table-body">
	<thead>
		<tr>
			<th><spring:message code="statistics.label.11" text="컨텐츠명" /></th>
			<th><spring:message code="statistics.label.12" text="호출UserID" /></th>
			<th><spring:message code="statistics.label.13" text="호출일시" /></th>
		</tr>
	</thead>
	<tbody>
<%
		String chkName = "";
		String chkId = "";
		int i = 0; 

		if(listSize>0){
			ContentsStatisticsVO[] contStatistics = new ContentsStatisticsVO[list.size()];
			list.toArray(contStatistics);	
			for(ContentsStatisticsVO vo : contStatistics) {
	
				String statisticsId = vo.getStatisticsId();
				String contNm = vo.getMessageName();
				String accessTime = vo.getAccessTime2String();
				String accessUserId = vo.getAccessUserId();
%>				
				<tr onMouseOver=this.style.backgroundColor="#f3f3f3"  onMouseOut=this.style.backgroundColor=''>
					<td><%=contNm %></td>
					<td><%=accessUserId.equals("") ? "GUEST" : accessUserId %></td>
					<td><%=accessTime %></td>
				</tr>
<%				i++;
			}
		}else{
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
