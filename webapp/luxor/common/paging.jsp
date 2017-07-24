<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.framework.config.*" %>

<%
	// 현재 페이지
	int cPage = UtilRequest.getInt(request, "cPage", 1); 
	
	// 목록에 보여지는 갯수 (한페이지 목록에 10개씩인지 20개씩인지) 
	int listCount = UtilRequest.getInt(request, "listCount", LuxorConfig.getEnvInt(request, "PAGE_LIST_COUNT"));
	if(listCount <= 0) { listCount = 20; }
	
	// 하단에 보여지는 페이지 단위 갯수 
	// 5로 설정하면 << < 1 2 3 4 5 > >> 로 보여짐
	// 10이 기본이고 10일경우 << < 1 2 3 4 5 6 7 8 9 10 > >> 로 보여짐
	int pageUnit = UtilRequest.getInt(request, "pageUnit", LuxorConfig.getEnvInt(request, "PAGE_PAGE_COUNT"));
	if(pageUnit <=0 ) { pageUnit = 10; }
	
	// 전체 게시물 수
	int totalCount = UtilRequest.getInt(request, "totalCount");
	
	// pageUnit이 전부 몇개인지
	int totalPageCount = (int)Math.ceil((float)totalCount/(float)listCount);
	
	int _start = (cPage-1) / pageUnit * pageUnit;
	if(_start==0 || _start % 10==0) {
		_start += 1;
	}
	
	int _end = _start + pageUnit - 1;
	if(_end > totalPageCount) {
		_end = totalPageCount;
	}
%>

<script type="text/javascript">
var paging = {
	checkEnter: function(e) {
		if(e.keyCode==13 || e.which==13) {
			this.changePage($('#changePageTo').val());
		}
	},
	
	changePage: function(p) {
		var rex = /[0-9]/;
		if(rex.test(p)==false) {
			return;
		}
		
		if(typeof(p)=='undefined' || p < 1 || p > <%=totalPageCount%>) {
			return;
		}
		try {
			changePage(p);
		} catch(e) {};
	}
};
</script>

<div class="paging-wrap">
	<% if(_end > 1) { %>
		<div class="paging-left-side">Page:<%=cPage%>/<%=totalPageCount%>
	   	<input id="changePageTo" type="text" class="paging-search" style="width:30px;" onkeyup="paging.checkEnter(event)" />
	   	<a href="#" onclick="paging.changePage(document.getElementById('changePageTo').value);return false;">
	   		<img src="/ep/luxor/ref/image/admin/btn_go.gif" alt="<spring:message code="portal.btn.label.55" text="이동" />" title="<spring:message code="portal.btn.label.55" text="이동" />" align="absmiddle"/>
	   	</a>
	   	</div>
   	<% } %>
   	<div class="paging-right-side"><spring:message code="portal.btn.label.54" text="총" /> <%=totalCount%><spring:message code="portal.btn.label.55" text="건" /></div>
  	<div class="paging">
  		<% if(_end > 1) { %>
			<a href="#" onclick="paging.changePage(1);return false;" class="first-page-btn">
				<img src="/ep/luxor/ref/image/admin/btn_pg_first.gif" alt="<spring:message code="portal.btn.label.50" text="처음" />" title="<spring:message code="portal.btn.label.50" text="처음" />" />
			</a>
			<a href="#" onclick="paging.changePage(<%=(cPage-1)%>);return false;" class="pre-page-btn">
				<img src="/ep/luxor/ref/image/admin/btn_pg_pre.gif" alt="<spring:message code="portal.btn.label.51" text="이전" />" title="<spring:message code="portal.btn.label.51" text="이전" />" />
			</a>
		<%	
			for(int i=_start; i <= _end; i++) {
				if(cPage==i) {
					out.println("<strong>"+i+"</strong>");
				} else {
					out.println("<a href='#' onclick=\"paging.changePage("+i+");return false;\">"+i+"</a>");
				}
			}
			
		%>
			<a href="#" onclick="paging.changePage(<%=(cPage+1)%>);return false;" class="next-page-btn">
				<img src="/ep/luxor/ref/image/admin/btn_pg_next.gif" alt="<spring:message code="portal.btn.label.52" text="다음" />" title="<spring:message code="portal.btn.label.52" text="다음" />" />
			</a>
			<a href="#" onclick="paging.changePage(<%=totalPageCount%>);return false;" class="end-page-btn">
				<img src="/ep/luxor/ref/image/admin/btn_pg_end.gif" alt="<spring:message code="portal.btn.label.53" text="끝" />" title="<spring:message code="portal.btn.label.53" text="끝" />" />
			</a>
		<% } %>
  	</div>
</div>
