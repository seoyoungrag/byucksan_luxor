<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE HTML>
<html lang="ko">
<head>
<title>jqGrid Sample</title>
<jsp:include page="/luxor/common/header.jsp" />
<link rel="stylesheet" type="text/css" href="/ep/luxor/ref/css/jquery-ui-default.css" />
<link rel="stylesheet" type="text/css" href="/ep/luxor/ref/css/ui.jqgrid.css" />
<script type="text/javascript" src="/ep/luxor/ref/js/grid.locale-en.js" charset="utf-8"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/jquery.jqGrid.min.js" charset="utf-8"></script>
<script type="text/javascript">
	$(document).ready(function() {
		callJson("portalPageLayoutController","getPageLayoutListAjax","",function(gridData) {
			jQuery("#list47").jqGrid({ 
				data: gridData, 
				datatype: "local", 
				height: 600, 
				rowNum: 20, 
				rowList: [10,20,30], 
				colNames:['layoutId','layoutNameId', 'layoutCss', 'isDefault'], 
				colModel:[ 
							{name:'layoutId',index:'layoutId', width:120}, 
							{name:'layoutNameId',index:'layoutNameId', width:120}, 
							{name:'layoutCss',index:'layoutCss', width:300}, 
							{name:'isDefault',index:'isDefault', width:80} 
				], 
				pager: "#plist47", 
				viewrecords: false, 
				caption: "Manipulating Array Data" 
			}); 			
		});
	});
</script>
</head>

<body>
	<table id="list47"></table>
	<div id="plist47"></div> 
</body>
</html>