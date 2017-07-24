<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.sample.vo.*" %>

<%
	SampleVO vo = (SampleVO) request.getAttribute("vo");
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Sample 조회</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="/ep/luxor/ref/js/jquery-1.4.3.min.js"></script>
<script type="text/javascript">
</script>
</head>

<body>

<a href="http://localhost:8080/ep/sample/list.do">목록으로</a>

<br /><br />

<form id="sample_form" name="sample_form" action="/ep/sample/insert.do" method="post">

<table border="1">
	<colgroup><col width="180" align="right"></col><col width="*"></col></colgroup>
	<tr>
		<td>sample_id</td>
		<td><%=vo.getSampleId()%></td>
	</tr>
	<tr>
		<td>name</td>
		<td><%=vo.getName()%></td>
	</tr>
	<tr>
		<td>content</td>
		<td><%=vo.getContent()%></td>
	</tr>
	<tr>
		<td>reg_date</td>
		<td><%=vo.getRegDate2String()%></td>
	</tr>
</table>
</form>

</body>
</html>
