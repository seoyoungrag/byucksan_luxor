<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="org.anyframe.pagination.Page" %>
<%@ page import="com.sds.acube.luxor.sample.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>


<%
	int totalCnt = (Integer)request.getAttribute("totalCnt");
	List list = (List)request.getAttribute("list");
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Sample 목록</title>
<%@ include file="/luxor/common/header.jsp" %>
<script type="text/javascript">
</script>
</head>

<body>

<a href="/ep/luxor/sample/form.jsp">등록</a>

<br /><br />

<form id="sample_form" name="sample_form" action="/ep/sample/insert.do" method="post">
<table border="1">
	<tr>
		<td>sample_id</td>
		<td>name</td>
		<td>content</td>
		<td>reg_date</td>
	</tr>
<%	
	for(int i=0; i < list.size(); i++) {
		SampleVO vo = (SampleVO)list.get(i);
%>	
	<tr>
		<td><a href="/ep/sample/modifyView.do?sampleId=<%=vo.getSampleId()%>"><%=vo.getSampleId()%></a></td>
		<td><%=vo.getName()%></td>
		<td><%=UtilEditor.getContent(session, vo.getContent())%></td>
		<td><%=vo.getRegDate2String()%></td>
	</tr>
<%	} %>	
</table>
</form>

</body>
</html>
