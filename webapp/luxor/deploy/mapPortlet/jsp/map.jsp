<html lang="ko">
<head>
<script type="text/javascript" src="http://kr.open.gugi.yahoo.com/Client/AjaxMap.php?v=3.7&appid=CkV8VejV34H0tRGptHJu6Ia4qPsRVT8wxuH18tx.RoKpz81NRDcfdHwx8zWzyUqsGg--"></script>

<script type="text/javascript">
<!--
var map;
function StartYMap()
{
	// ���� ������Ʈ�� ���� �մϴ�.
	map = new YMap(document.getElementById('map'));

	// ���� ���� ��Ʈ���� �߰��մϴ�.
	map.addTypeControl();
	// ���� Ȯ��/��� ��Ʈ���� �߰��մϴ�.
	map.addZoomLong();
	// ���� �̵� ��Ʈ���� �߰��մϴ�.
	map.addPanControl();

	// �������� ������ �����մϴ�.
	// YAHOO_MAP_SAT: ��������
	// YAHOO_MAP_HYB: ���̺긮�� ����
	// YAHOO_MAP_REG: �Ϲ�����
	map.setMapType(YAHOO_MAP_REG);


	// WGS84 ��ǥ���� ������ ��ǥ ������Ʈ�� �����Ͽ� ��ġ�� ����
	// var center_point = new YGeoPoint(37.37160610616,127.10718565157);
	// map.drawZoomAndCenter(center_point,3);

	var center_point = new YGeoPoint(37.511411132213,127.05925359288);
	map.drawZoomAndCenter(center_point,3);
}


window.onload = StartYMap;

//-->
</script>

</head>

<body style ="margin:0px; padding:0px; overflow: hidden;">
<div id="map" style="width:100%; height:100%;"></div>
</body>
</html>
