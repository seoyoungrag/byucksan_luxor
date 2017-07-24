<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="org.anyframe.pagination.Page" %>
<%@ page import="com.sds.acube.luxor.sample.vo.MovieVO" %>
<%@ page import="com.sds.acube.luxor.common.util.CommonUtil" %>
<%@ page import="com.sds.acube.luxor.common.vo.UserProfileVO" %>
<%@ page import="com.sds.acube.luxor.framework.config.LuxorConfig"%>

<%
	Page _page = (Page)request.getAttribute("_page");
	List list = (List)_page.getList();

	MovieVO[] movies = new MovieVO[list.size()];
	list.toArray(movies);
%>

<!DOCTYPE HTML>
<html lang="ko">
<head>
<title>Movie List</title>
<jsp:include page="/luxor/common/header.jsp" />
<link rel="stylesheet" href="/ep/luxor/ref/js/attachment/jquery/css/smoothness/jquery-ui-1.8.14.custom.css" id="theme">
<script type="text/javascript">
$(document).ready(function() {
	$('#btn_add').click(function() {
		luxor.popup('/ep/movie/add.do', {width:700,height:500});
	});
	
	$('#check_all').click(function() {
		if($(this).is(':checked')) {
			$('tbody :checkbox').each(function() {
				$(this).attr('checked','checked');
			});
		} else {
			$('tbody :checkbox').each(function() {
				$(this).removeAttr('checked');
			});
		}
	});
	
	$('#btn_del').click(function() {
		if(confirm("선택된 영화를 삭제하시겠습니까?")) {
			var param = $('#listForm').serialize();
			callJson("movieController", "deleteMovies", param, function(data) {
		        if(data._errorCode=='-9999') {
		        	alert("<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />");
		        } else {
		            alert("<spring:message code="portal.alert.msg.8" text="삭제되었습니다." />");
		        }
		        document.location.reload();
			});
		}
	});
	
	$('table a').click(function() {
		if($(this).attr('mode')=='mod') {
			luxor.popup('/ep/movie/mod.do?code='+$(this).attr('code'), {width:700,height:400});
			return false;
		}
		else if($(this).attr('mode')=='view') {
			luxor.popup('/ep/movie/view.do?code='+$(this).attr('code'), {width:700,height:400});
			return false;
		}
		else if($(this).attr('mode')=='del') {
			if(!confirm("<spring:message code="portal.alert.msg.110" text="삭제하시겠습니까?" />")) {
				return false;
			}

			var param = "code="+$(this).attr('code')+"&attachmentId="+$(this).attr('attachmentId');
			callJson("movieController", "deleteMovie", param, function(data) {
		        if(data._errorCode=='-9999') {
		        	alert("<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />");
		        } else {
		            alert("<spring:message code="portal.alert.msg.8" text="삭제되었습니다." />");
		        }
		        document.location.reload();
			});
		}
	});
});
</script>
</head>

<body style="padding:10px">

<div>
	<div class="admin-content-width w100percent" name="zone">
		<div class="title-sub" style="margin-top:0">
			<span class="title-sub-text">영화목록</span>
			<div class="aright">
				<span class="main-btn"><a href="#" id="btn_del">영화삭제</a></span>
				<span class="main-btn"><a href="#" id="btn_add">영화등록</a></span>
			</div>
		</div>
		<div class="table-body-wrap">
			<form id="listForm">
			<table class="table-body">
			<thead>
				<tr>
					<th width="40px"><input type="checkbox" id="check_all" /></th>
					<th width="100px">영화코드</th>
					<th width="*">제목</th>
					<th width="60px">첨부</th>
					<th width="100px">출시일</th>
					<th width="120px"><spring:message code="portal.label.39" text="기능" /></th>
				</tr>
			</thead>
			<tbody>
			<%
				for(MovieVO movie : movies) {
					Timestamp timestamp = movie.getReleaseDate();
					String releaseDate = CommonUtil.formatDate(timestamp, LuxorConfig.getEnvString(request, "DATE_PATTERN_SHORT"));
					String attachment = CommonUtil.isNullOrEmpty(movie.getAttachmentId()) ? "" : "<img src='/ep/luxor/ref/image/attachment/attach.gif' />";
			%>
				<tr>
					<td><input type="checkbox" name="codes" value="<%=movie.getCode()%>" /></td>
					<td><%=movie.getCode()%></td>
					<td class="body-left"><a href="#" mode="view" code="<%=movie.getCode()%>"><%=movie.getTitle()%></a></td>
					<td><%=attachment%></td>
					<td><%=releaseDate%></td>
					<td>
						<div class="acenter">
							<span class="smain-btn"><a href="#" mode="mod" code="<%=movie.getCode()%>"><spring:message code="portal.btn.label.19" text="수정" /></a></span>
							<span class="smain-btn"><a href="#" mode="del" code="<%=movie.getCode()%>" attachmentId="<%=movie.getAttachmentId()%>"><spring:message code="portal.btn.label.30" text="삭제" /></a></span>
						</div>
					</td>
				</tr>
			<%		
				}
			%>
			</tbody>
			</table>
			</form>
		</div>
		<jsp:include page="/luxor/common/paging.jsp">
			<jsp:param name="cPage" value="<%=_page.getCurrentPage()%>" />
			<jsp:param name="totalCount" value="<%=_page.getTotalCount()%>" />
		</jsp:include>
	</div>
</div>
		
</body>

</html>