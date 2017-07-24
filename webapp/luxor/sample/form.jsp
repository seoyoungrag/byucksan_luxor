<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.sample.vo.*" %>

<%
	SampleVO vo = (SampleVO) request.getAttribute("vo");
	if(vo==null) {
		vo = new SampleVO();
	}
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Sample 등록</title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript">
$(document).ready(function() {
	//luxor.editor.setSimpleToolbar(false);
	//luxor.editor.setHeight('600px');
	$('#btn_save').click(function() {
		luxor.editor.setContent();
		var param = $('#sample_form').serialize();
		postJson("sampleController", "insertSample", param, function(data) {
			document.location.href = "/ep/sample/list.do";
		});
	});
});
</script>
</head>

<body>

<form id="sample_form" name="sample_form" action="/ep/sample/insert.do" method="post">

<table border="1" width="100%">
	<colgroup><col width="180" align="right"></col><col width="*"></col></colgroup>
	<tr>
		<td>다국어</td>
		<td><jsp:include page="/luxor/common/message.jsp" /></td>
	</tr>
	<tr>
		<td>name</td>
		<td><input type="text" id="name" name="name" /></td>
	</tr>
	<tr>
		<td>content</td>
		<td>
			<jsp:include page="/luxor/common/editor.jsp">
				<jsp:param name="CONTENT" value="<%=vo.getContent()%>" />
			</jsp:include>
		</td>
	</tr>
	<tr>
		<td colspan="2"><input type="button" id="btn_save" value="등록" /></td>
	</tr>
</table>
</form>

</body>
</html>
