<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE HTML>
<html lang="ko">
<head>
<title>jqGrid Sample</title>
<jsp:include page="/luxor/common/header.jsp" />
<link rel="stylesheet" type="text/css" href="/ep/luxor/ref/css/jquery-ui-1.8.2.custom.css" />
<link rel="stylesheet" type="text/css" href="/ep/luxor/ref/css/ui.jqgrid.css" />
<script type="text/javascript" src="/ep/luxor/ref/js/grid.locale-en.js" charset="utf-8"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/jquery.jqGrid.min.js" charset="utf-8"></script>
<script type="text/javascript">
	$(document).ready(function() {
		for(var i=400; i < 458; i++) {
			var param = "layoutCss=sdafasdfasdf&isDefault=N&messageNameAll=ko^테스트"+i+"|en^Test"+i;
			callJson("portalPageLayoutController","insertPageLayout",param);
		}
	});
</script>
</head>

<body>
	<table id="list47"></table>
	<div id="plist47"></div> 
</body>
</html>