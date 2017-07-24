<html lang="ko">
<head>
<script type="text/javascript" src="http://kr.open.gugi.yahoo.com/Client/AjaxMap.php?v=3.7&appid=CkV8VejV34H0tRGptHJu6Ia4qPsRVT8wxuH18tx.RoKpz81NRDcfdHwx8zWzyUqsGg--"></script>

<script type="text/javascript">
<!--
var map;
function StartYMap()
{
	// 지도 오브젝트를 생성 합니다.
	map = new YMap(document.getElementById('map'));

	// 지도 유형 콘트롤을 추가합니다.
	map.addTypeControl();
	// 지도 확대/축소 콘트롤을 추가합니다.
	map.addZoomLong();
	// 지도 이동 콘트롤을 추가합니다.
	map.addPanControl();

	// 지도보기 유형을 선택합니다.
	// YAHOO_MAP_SAT: 위성지도
	// YAHOO_MAP_HYB: 하이브리드 지도
	// YAHOO_MAP_REG: 일반지도
	map.setMapType(YAHOO_MAP_REG);


	// WGS84 좌표계의 경위도 좌표 오브젝트를 전달하여 위치를 지정
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
