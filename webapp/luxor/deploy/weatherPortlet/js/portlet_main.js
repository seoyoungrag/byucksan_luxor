$(document).ready(function(){
	var Weather = function(){
		this._tm ="";	// 발표시각:yyyymmddhhMM
		this._ts = "";	// 시간 step: 동네예보 중 4번째 경우인 오늘오후/내일오전/내일오후/모레오전
		this._x = "";	// x좌표
		this._y = "";		// y좌표
	};
	
	Weather.prototype = {getTm: function(){return this._tm;},setTm: function(tm){this._tm = tm;},getTs: function(){return this._ts;},setTs: function(ts){this._ts = ts;},getX: function(){return this._x;},setX: function(x){this._x = x;},getY: function(){return this._y;},setY: function(y){this._y = y;}}
	
	var Data = function(){
		this._hour = 0;		// 동네예보 3시간단위
		this._day = 0;		// 1번째날(오늘/내일/모레 중 오늘)
		this._temp = 0;		// 현재시간온도
		this._tmx = 0;		// 최고온도 missing(값이 없을 경우)
		this._tmn = 0;		// 최저온도 missing(값이 없을 경우)
		this._sky = 0;			// 하늘상태코드 :  1 : 맑음,  2 : 구름조금,  3 : 구름많음,  4 : 흐림
		this._pth = 0;			// 강수상태코드 :  0 : 없음,  1 : 비,  2 : 비/눈,  3 : 눈/비,  4 : 눈
		this._wfKor = "";	// 날씨한국어 : 1 맑음, 2 구름 조금, 3 구름 많음, 4 흐림, 5 비, 6  눈/비, 7 눈
		this._wfEn = "";		// 날씨영어 : 1 Clear, 2 Partly Cloudy, 3 Mostly Cloudy, 4 Cloudy, 5 Rain, 6 Snow/Rain, 7 Snow
		this._pop = 0;		// 강수확률%
		this._r12 = 0.0;			// 12시간 예상강수량
		this._s12 = 0.0;		// 12시간 예상적설량
		this._ws = 0.0;			// 풍속 : 반올림처리 값 이용(정수)
		this._wd = 0;			// 풍향(8방): 국문8방위/영문8방위 풍향 0~7 (북, 북동, 동, 남동, 남, 남서, 서, 북서)
		this._wdKor = "";	// 풍향한국어
		this._wdEn = "";	// 풍향영어
		this._reh = 0;			// 습도
	};
	
	Data.prototype = {getHour: function(){ return this._hour; }, setHour: function(hour){ this._hour = hour; },getDay: function(){ return this._day; }, setDay: function(day){ this._day = day; },getTemp: function(){ return this._temp; }, setTemp: function(temp){ this._temp = temp; },getTmx: function(){ return this._tmx; }, setTmx: function(tmx){ this._tmx = tmx; },getTmn: function(){ return this._tmn; }, setTmn: function(tmn){ this._tmn = tmn; },getSky: function(){ return this._sky; }, setSky: function(sky){ this._sky = sky; },getPth: function(){ return this._pth; }, setPth: function(pth){ this._pth = pth; },getWfKor: function(){ return this._wfKor; }, setWfKor: function(wfKor){ this._wfKor = wfKor; },getWfEn: function(){ return this._wfEn; }, setWfEn: function(wfEn){ this._wfEn = wfEn; },getPop: function(){ return this._pop; }, setPop: function(pop){ this._pop = pop; },getR12: function(){ return this._r12; }, setR12: function(r12){ this._r12 = r12; },getS12: function(){ return this._s12; }, setS12: function(s12){ this._s12 = s12; },getWs: function(){ return this._ws; }, setWs: function(ws){ this._ws = ws; },getWd: function(){ return this._wd; }, setWd: function(wd){ this._wd = wd; },getWdKor: function(){ return this._wdKor; }, setWdKor: function(wdKor){ this._wdKor = wdKor; },getWdEn: function(){ return this._wdEn; }, setWdEn: function(wdEn){ this._wdEn = wdEn; },getReh: function(){ return this._reh; }, setReh: function(reh){ this._reh = reh; }}
	
	var myWeather = new Weather();
	var myData;
	
	function xmlPars(xml) {
		if ($(xml).find("wid").find("data").length > 0) {
	
			myWeather.setTm($(xml).find("tm").text());
			myWeather.setTs($(xml).find("ts").text());
			myWeather.setX($(xml).find("x").text());
			myWeather.setY($(xml).find("y").text());
	
			myData = new Array($(xml).find("wid").find("data").length);
			$(xml).find("wid").find("body").find("data").each(function(idx) {
				myData[idx] = new Data();
				myData[idx].setHour($(this).find("hour").text());
				myData[idx].setDay($(this).find("day").text());
				myData[idx].setTemp($(this).find("temp").text());
				myData[idx].setTmx($(this).find("tmx").text());
				myData[idx].setTmn($(this).find("tmn").text());
				myData[idx].setSky($(this).find("sky").text());
				myData[idx].setPth($(this).find("pth").text());
				myData[idx].setWfKor($(this).find("wfKor").text());		
				myData[idx].setWfEn($(this).find("wfEn").text().replace(/^\s+|\s+$/g,""));
				myData[idx].setPop($(this).find("pop").text());
				myData[idx].setR12($(this).find("r12").text());
				myData[idx].setS12($(this).find("s12").text());
				myData[idx].setWs($(this).find("ws").text());
				myData[idx].setWd($(this).find("wd").text());
				myData[idx].setWdKor($(this).find("wdKor").text());
				myData[idx].setWdEn($(this).find("wdEn").text());
				myData[idx].setReh($(this).find("reh").text());
			});
			var contentWidth = $('#weather_portlet').closest('.content-body').width();
			printWeather($('#weather_portlet'),myWeather,myData,parseInt(contentWidth/105)+2);
		}
	}
	
	function printWeather(obj, weatherHeader,arr,len){
		if (len==null) { var len = arr.length; }
		var str = "";
		var printRefrshDate = "";
		printRefrshDate += weatherHeader.getTm().slice(0,4)+portal_portlet_label_151+" "+weatherHeader.getTm().slice(4,6)+portal_portlet_label_152+" "+weatherHeader.getTm().slice(6,8)+portal_portlet_label_153+" ("+weatherHeader.getTm().slice(8,10)+":"+weatherHeader.getTm().slice(10,12)+") "+portal_portlet_label_154;
		$('#weather_title').append("&nbsp;&nbsp;["+printRefrshDate+"]");
		str += '<table class="weather-table">';
		str += '<caption>'+printRefrshDate+'</caption>';
		str += '<thead>';
	
		str += '<tr><th align="center">'+portal_portlet_label_155+'</th>';
		var col0 = 0;
		var col1 = 0;
		var col2 = 0;
		for (var i=0; i<len; i++) {
			if ( arr[i].getDay()==0 ) col0 += 1;
			else if ( arr[i].getDay()==1 ) col1 += 1;
			else if ( arr[i].getDay()==2 ) col2 += 1;
		}
		var day_bak = 9;
		for (var i=0; i<len; i++) {
			if (day_bak!=arr[i].getDay()) {
				if ( arr[i].getDay()==0 ) str += '<th colspan='+col0+'>'+portal_portlet_label_156+'</th>';
				else if ( arr[i].getDay()==1 ) str += '<th colspan='+col1+'>'+portal_portlet_label_157+'</th>';
				else if ( arr[i].getDay()==2 ) str += '<th colspan='+col2+'>'+portal_portlet_label_158+'</th>';
			}
			day_bak = arr[i].getDay();
		}
		str += '</tr>';
	
		str += '<tr><th>'+portal_portlet_label_159+'</th>';
		for (var i=0; i<len; i++) str += '<th>'+arr[i].getHour()+'</th>';
		str += '</tr>';
		str += '</thead>';
	
		str += '<tbody>';
	
		str += '<tr><th>'+portal_portlet_label_160+'</th>';
		for (var i=0; i<len; i++) {
			if (arr[i].getHour()>18 || arr[i].getHour()<6) {
				var imgPath = '/ep/luxor/ref/image/portlet/weather/'+arr[i].getWfEn()+'_night'+'.png';
			}else {
				var imgPath = '/ep/luxor/ref/image/portlet/weather/'+arr[i].getWfEn()+'.png';
			}
			
			if(langType == 'ko'){
				str += '<td><img src="'+imgPath+'" alt='+arr[i].getWfKor()+' width="50" height="50"><br/>'+arr[i].getWfKor()+'</td>';
			}else{
				str += '<td><img src="'+imgPath+'" alt='+arr[i].getWfEn()+' width="50" height="50"><br/>'+arr[i].getWfEn()+'</td>';
			}
		}
		str += '</tr>';
	
		str += '<tr><th>'+portal_portlet_label_161+'</th>';
		for (var i=0; i<len; i++) str += '<td>'+arr[i].getTemp()+'</td>';
		str += '</tr>';
	
		str += '<tr><th>'+portal_portlet_label_162+'</th>';
		for (var i=0; i<len; i++) {
			if (arr[i].getR12()==0.0) str += '<td>-</td>';
			else str += '<td>'+arr[i].getR12()+'</td>';
		}
		str += '</tr>';
	
		str += '<tr><th>'+portal_portlet_label_163+'</th>';
		for (var i=0; i<len; i++) str += '<td>'+arr[i].getPop()+'</td>';
		str += '</tr>';
	
		str += '<tr><th>'+portal_portlet_label_164+'</th>';
		for (var i=0; i<len; i++) str += '<td>'+arr[i].getReh()+'</td>';
		str += '</tr>';
	
		str += '<tr><th>'+portal_portlet_label_165+'</th>';
		for (var i=0; i<len; i++){
			if(langType == 'ko'){
				str += '<td>'+arr[i].getWdKor()+'('+arr[i].getWs().substring(0,3)+')</td>';
			}else{
				str += '<td>'+arr[i].getWdEn()+'('+arr[i].getWs().substring(0,3)+')</td>';
			}
		}
		str += '</tr>';
	
		str += '<tbody>';
		str += '</table>';
		obj.html(str);
	}
	$.ajax({
		type:'get',
		url: '/ep/luxor/common/jsProxy/getWeatherRSS.jsp?cacheTime='+new Date(), 
		dataType:'xml',
		success: function(data) {	
			xmlPars(data);
		}, 
		error: function(xhr, status, error) {$('#weatherPortlet').html(portal_portlet_alert_8); }
	});	 
});