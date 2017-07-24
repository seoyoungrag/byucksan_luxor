<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.portal.vo.*" %>

<%
	UserProfileVO userProfile = (UserProfileVO)request.getSession().getAttribute("userProfile");
	UserSettingVO userSetting = (UserSettingVO)request.getAttribute("userSetting");
	String timezone = userSetting==null ? "" : userSetting.getSettingValue();
%>

<!DOCTYPE HTML>
<html lang="ko">
<head>
<title><spring:message code="portal.label.147" text="시간대 설정" /></title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript">
$(document).ready(function() {
	$('#timezone option').each(function() {
		if($(this).val()=="<%=timezone%>") {
			$(this).attr("selected","selected");
		}
	});
	
	$('#btn_close').click(function() {
		self.close();
	});

	$('#btn_save').click(function() {
		if(confirm("<spring:message code="portal.alert.msg.18" text="저장하시겠습니까?" />")) {
			var param = $('#timezoneForm').serialize();
			callJson("userController", "setTimezone", param, function(data) {
		        if(data._errorCode=='-9999') {
		        	alert("<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />");
		        } else {
		            alert("<spring:message code="portal.alert.msg.13" text="저장되었습니다." />");
		            document.location.reload();
		        }
			});
		}
	});
});
</script>
</head>

<body>
	<div class="popup-wrap">
		<div class="title-pop">
			<span class="title-pop-text"><spring:message code="portal.label.147" text="시간대 설정" /></span>
		</div>
	</div>
	<div id="tree_wrap" class="tree-wrap" style="width:100%;height:400px">
		<form id="timezoneForm">
		<select name="timezone" id="timezone" title="Time Zone 목록" style="margin-left:10px;height:400px;" size='13'>
			<option  value="-1" selected> <!-- Default:  -->기본값: <!-- Server time  -->서버시간
			<option value="Africa/Algiers"> [Africa/Algiers]&nbsp;&nbsp;(GMT+1:00) Algiers</option>
			<option value="Africa/Cairo"> [Africa/Cairo]&nbsp;&nbsp;(GMT+2:00) Cairo</option>
			<option value="Africa/Casablanca"> [Africa/Casablanca]&nbsp;&nbsp;(GMT) Casablanca</option>
			<option value="Africa/Harare"> [Africa/Harare]&nbsp;&nbsp;(GMT+2:00) Harare</option>
			<option value="Africa/Johannesburg"> [Africa/Johannesburg]&nbsp;&nbsp;(GMT+2:00) Johannesburg</option>
			<option value="Africa/Monrovia"> [Africa/Monrovia]&nbsp;&nbsp;(GMT) Monrovia</option>
			<option value="Africa/Nairobi"> [Africa/Nairobi]&nbsp;&nbsp;(GMT+3:00) Nairobi</option>
			<option value="America/Anchorage"> [America/Anchorage]&nbsp;&nbsp;(GMT-8:00) Anchorage</option>
			<option value="America/Bogota"> [America/Bogota]&nbsp;&nbsp;(GMT-5:00) Bogota</option>
			<option value="America/Buenos_Aires"> [America/Buenos_Aires]&nbsp;&nbsp;(GMT-3:00) Buenos Aires</option>
			<option value="America/Caracas"> [America/Caracas]&nbsp;&nbsp;(GMT-4:00) Caracas</option>
			<option value="America/Chicago"> [America/Chicago]&nbsp;&nbsp;(GMT-6:00) Central Time (US & Canada)</option>
			<option value="America/Costa_Rica"> [America/Costa_Rica]&nbsp;&nbsp;(GMT-6:00) Costa_Rica</option>
			<option value="America/Denver"> [America/Denver]&nbsp;&nbsp;(GMT-7:00) Mountain Time (US & Canada)</option>
			<option value="America/Edmonton"> [America/Edmonton]&nbsp;&nbsp;(GMT-7:00) Edmonton	</option>
			<option value="America/El_Salvador"> [America/El_Salvador]&nbsp;&nbsp;(GMT-6:00) El Salvador</option>
			<option value="America/Godthab"> [America/Godthab]&nbsp;&nbsp;(GMT-3:00) Greenland</option>
			<option value="America/Guatemala"> [America/Guatemala]&nbsp;&nbsp;(GMT-6:00) Guatemala</option>
			<option value="America/Halifax"> [America/Halifax]&nbsp;&nbsp;(GMT-4:00) Halifax</option>
			<option value="America/Indianapolis"> [America/Indianapolis]&nbsp;&nbsp;(GMT-5:00) Indiana (East)</option>
			<option value="America/Jamaica"> [America/Jamaica]&nbsp;&nbsp;(GMT-5:00) Jamaica</option>
			<option value="America/La_Paz"> [America/La_Paz]&nbsp;&nbsp;(GMT-4:00) La Paz</option>
			<option value="America/Lima"> [America/Lima]&nbsp;&nbsp;(GMT-5:00) Lima</option>
			<option value="America/Los_Angeles"> [America/Los_Angeles]&nbsp;&nbsp;(GMT-8:00) Pacific Time (US & Canada)</option>
			<option value="America/Mexico_City"> [America/Mexico_City]&nbsp;&nbsp;(GMT-6:00) Mexico City</option>
			<option value="America/Montreal"> [America/Montreal]&nbsp;&nbsp;(GMT-5:00) Montreal</option>
			<option value="America/New_York"> [America/New_York]&nbsp;&nbsp;(GMT-5:00) Eastern Time (US & Canada)</option>
			<option value="America/Phoenix"> [America/Phoenix]&nbsp;&nbsp;(GMT-7:00) Arizona</option>
			<option value="America/Puerto_Rico"> [America/Puerto_Rico]&nbsp;&nbsp;(GMT-4:00) Puerto Rico</option>
			<option value="America/Regina"> [America/Regina]&nbsp;&nbsp;(GMT-6:00) Regina</option>
			<option value="America/Santiago"> [America/Santiago]&nbsp;&nbsp;(GMT-4:00) Santiago</option>
			<option value="America/Sao_Paulo"> [America/Sao_Paulo]&nbsp;&nbsp;(GMT-3:00) Sao Paulo</option>
			<option value="America/St_Johns"> [America/St_Johns]&nbsp;&nbsp;(GMT-3:30) Newfoundland</option>
			<option value="America/Tijuana"> [America/Tijuana]&nbsp;&nbsp;(GMT-8:00) Tijuana</option>
			<option value="America/Vancouver"> [America/Vancouver]&nbsp;&nbsp;(GMT-8:00) Vancouver</option>
			<option value="Asia/Almaty"> [Asia/Almaty]&nbsp;&nbsp;(GMT+6:00) Almaty</option>
			<option value="Asia/Baghdad"> [Asia/Baghdad]&nbsp;&nbsp;(GMT+3:00) Baghdad</option>
			<option value="Asia/Bahrain"> [Asia/Bahrain]&nbsp;&nbsp;(GMT+3:00) Bahrain</option>
			<option value="Asia/Baku"> [Asia/Baku]&nbsp;&nbsp;(GMT+4:00) Baku</option>
			<option value="Asia/Bangkok"> [Asia/Bangkok]&nbsp;&nbsp;(GMT+7:00) Bangkok</option>
			<option value="Asia/Calcutta"> [Asia/Calcutta]&nbsp;&nbsp;(GMT+5:30) Calcutta</option>
			<option value="Asia/Damascus"> [Asia/Damascus]&nbsp;&nbsp;(GMT+2:00) Damascus</option>
			<option value="Asia/Dubai"> [Asia/Dubai]&nbsp;&nbsp;(GMT+4:00) Dubai</option>
			<option value="Asia/Hong_Kong"> [Asia/Hong_Kong]&nbsp;&nbsp;(GMT+8:00) Hong Kong</option>
			<option value="Asia/Irkutsk"> [Asia/Irkutsk]&nbsp;&nbsp;(GMT+8:00) Irkutsk</option>
			<option value="Asia/Jakarta"> [Asia/Jakarta]&nbsp;&nbsp;(GMT+7:00) Jakarta</option>
			<option value="Asia/Jerusalem"> [Asia/Jerusalem]&nbsp;&nbsp;(GMT+2:00) Jerusalem</option>
			<option value="Asia/Kabul"> [Asia/Kabul]&nbsp;&nbsp;(GMT+4:30) Kabul</option>
			<option value="Asia/Kamchatka"> [Asia/Kamchatka]&nbsp;&nbsp;(GMT+12:00) Kamchatka</option>
			<option value="Asia/Karachi"> [Asia/Karachi]&nbsp;&nbsp;(GMT+5:00) Karachi</option>
			<option value="Asia/Katmandu"> [Asia/Katmandu]&nbsp;&nbsp;(GMT+5:45) Katmandu</option>
			<option value="Asia/Krasnoyarsk"> [Asia/Krasnoyarsk]&nbsp;&nbsp;(GMT+7:00) Krasnoyarsk</option>
			<option value="Asia/Kuwait"> [Asia/Kuwait]&nbsp;&nbsp;(GMT+3:00) Kuwait</option>
			<option value="Asia/Macao"> [Asia/Macao]&nbsp;&nbsp;(GMT+8:00) Macao</option>
			<option value="Asia/Magadan"> [Asia/Magadan]&nbsp;&nbsp;(GMT+11:00) Magadan</option>
			<option value="Asia/Muscat"> [Asia/Muscat]&nbsp;&nbsp;(GMT+4:00) Muscat</option>
			<option value="Asia/Novosibirsk"> [Asia/Novosibirsk]&nbsp;&nbsp;(GMT+6:00) Novosibirsk</option>
			<option value="Asia/Qatar"> [Asia/Qatar]&nbsp;&nbsp;(GMT+3:00) Qatar</option>
			<option value="Asia/Rangoon"> [Asia/Rangoon]&nbsp;&nbsp;(GMT+6:30) Rangoon</option>
			<option value="Asia/Saigon"> [Asia/Saigon]&nbsp;&nbsp;(GMT+7:00) Saigon</option>
			<option value="Asia/Seoul"> [Asia/Seoul]&nbsp;&nbsp;(GMT+9:00) Seoul</option>
			<option value="Asia/Shanghai"> [Asia/Shanghai]&nbsp;&nbsp;(GMT+8:00) Shanghai</option>
			<option value="Asia/Singapore"> [Asia/Singapore]&nbsp;&nbsp;(GMT+8:00) Singapore</option>
			<option value="Asia/Taipei"> [Asia/Taipei]&nbsp;&nbsp;(GMT+8:00) Taipei</option>
			<option value="Asia/Tbilisi"> [Asia/Tbilisi]&nbsp;&nbsp;(GMT+4:00) Tbilisi</option>
			<option value="Asia/Tehran"> [Asia/Tehran]&nbsp;&nbsp;(GMT+3:30) Tehran</option>
			<option value="Asia/Tokyo"> [Asia/Tokyo]&nbsp;&nbsp;(GMT+9:00) Tokyo</option>
			<option value="Asia/Vladivostok"> [Asia/Vladivostok]&nbsp;&nbsp;(GMT+10:00) Vladivostok</option>
			<option value="Asia/Yakutsk"> [Asia/Yakutsk]&nbsp;&nbsp;(GMT+9:00) Yakutsk</option>
			<option value="Asia/Yekaterinburg"> [Asia/Yekaterinburg]&nbsp;&nbsp;(GMT+5:00) Yekaterinburg</option>
			<option value="Atlantic/Azores"> [Atlantic/Azores]&nbsp;&nbsp;(GMT-1:00) Azores</option>
			<option value="Atlantic/Canary"> [Atlantic/Canary]&nbsp;&nbsp;(GMT) Canary</option>
			<option value="Atlantic/Cape_Verde"> [Atlantic/Cape_Verde]&nbsp;&nbsp;(GMT-1:00) Cape Verde</option>
			<option value="Atlantic/Reykjavik"> [Atlantic/Reykjavik]&nbsp;&nbsp;(GMT) Reykjavik</option>
			<option value="Atlantic/South_Georgia"> [Atlantic/South_Georgia]&nbsp;&nbsp;(GMT-2:00) South Georgia</option>
			<option value="Australia/Adelaide"> [Australia/Adelaide]&nbsp;&nbsp;(GMT+9:30) Adelaide</option>
			<option value="Australia/Brisbane"> [Australia/Brisbane]&nbsp;&nbsp;(GMT+10:00) Brisbane</option>
			<option value="Australia/Darwin"> [Australia/Darwin]&nbsp;&nbsp;(GMT+9:30) Darwin</option>
			<option value="Australia/Hobart"> [Australia/Hobart]&nbsp;&nbsp;(GMT+10:00) Hobart</option>
			<option value="Australia/Perth"> [Australia/Perth]&nbsp;&nbsp;(GMT+8:00) Perth</option>
			<option value="Australia/Sydney"> [Australia/Sydney]&nbsp;&nbsp;(GMT+10:00) Sydney</option>
			<option value="Europe/Amsterdam"> [Europe/Amsterdam]&nbsp;&nbsp;(GMT+1:00) Amsterdam</option>
			<option value="Europe/Andorra"> [Europe/Andorra]&nbsp;&nbsp;(GMT+1:00) Andorra</option>
			<option value="Europe/Athens"> [Europe/Athens]&nbsp;&nbsp;(GMT+2:00) Athens</option>
			<option value="Europe/Belgrade"> [Europe/Belgrade]&nbsp;&nbsp;(GMT+1:00) Belgrade</option>
			<option value="Europe/Berlin"> [Europe/Berlin]&nbsp;&nbsp;(GMT+1:00) Berlin</option>
			<option value="Europe/Brussels"> [Europe/Brussels]&nbsp;&nbsp;(GMT+1:00) Brussels</option>
			<option value="Europe/Bucharest"> [Europe/Bucharest]&nbsp;&nbsp;(GMT+2:00) Bucharest</option>
			<option value="Europe/Budapest"> [Europe/Budapest]&nbsp;&nbsp;(GMT+1:00) Budapest</option>
			<option value="Europe/Copenhagen"> [Europe/Copenhagen]&nbsp;&nbsp;(GMT+1:00) Copenhagen</option>
			<option value="Europe/Dublin"> [Europe/Dublin]&nbsp;&nbsp;(GMT) Dublin</option>
			<option value="Europe/Helsinki"> [Europe/Helsinki]&nbsp;&nbsp;(GMT+2:00) Helsinki</option>
			<option value="Europe/Istanbul"> [Europe/Istanbul]&nbsp;&nbsp;(GMT+2:00) Istanbul</option>
			<option value="Europe/Kiev"> [Europe/Kiev]&nbsp;&nbsp;(GMT+2:00) Kiev</option>
			<option value="Europe/Lisbon"> [Europe/Lisbon]&nbsp;&nbsp;(GMT) Lisbon</option>
			<option value="Europe/London"> [Europe/London]&nbsp;&nbsp;(GMT) London</option>
			<option value="Europe/Luxembourg"> [Europe/Luxembourg]&nbsp;&nbsp;(GMT+1:00) Luxembourg</option>
			<option value="Europe/Madrid"> [Europe/Madrid]&nbsp;&nbsp;(GMT+1:00) Madrid</option>
			<option value="Europe/Minsk"> [Europe/Minsk]&nbsp;&nbsp;(GMT+2:00) Minsk</option>
			<option value="Europe/Monaco"> [Europe/Monaco]&nbsp;&nbsp;(GMT+1:00) Monaco</option>
			<option value="Europe/Moscow"> [Europe/Moscow]&nbsp;&nbsp;(GMT+3:00) Moscow</option>
			<option value="Europe/Oslo"> [Europe/Oslo]&nbsp;&nbsp;(GMT+1:00) Oslo</option>
			<option value="Europe/Paris"> [Europe/Paris]&nbsp;&nbsp;(GMT+1:00) Paris</option>
			<option value="Europe/Prague"> [Europe/Prague]&nbsp;&nbsp;(GMT+1:00) Prague</option>
			<option value="Europe/Riga"> [Europe/Riga]&nbsp;&nbsp;(GMT+2:00) Riga</option>
			<option value="Europe/Rome"> [Europe/Rome]&nbsp;&nbsp;(GMT+1:00) Rome</option>
			<option value="Europe/Sofia"> [Europe/Sofia]&nbsp;&nbsp;(GMT+2:00) Sofia</option>
			<option value="Europe/Stockholm"> [Europe/Stockholm]&nbsp;&nbsp;(GMT+1:00) Stockholm</option>
			<option value="Europe/Tallinn"> [Europe/Tallinn]&nbsp;&nbsp;(GMT+2:00) Tallinn</option>
			<option value="Europe/Vienna"> [Europe/Vienna]&nbsp;&nbsp;(GMT+1:00) Vienna</option>
			<option value="Europe/Vilnius"> [Europe/Vilnius]&nbsp;&nbsp;(GMT+2:00) Vilnius</option>
			<option value="Europe/Warsaw"> [Europe/Warsaw]&nbsp;&nbsp;(GMT+1:00) Warsaw</option>
			<option value="Europe/Zurich"> [Europe/Zurich]&nbsp;&nbsp;(GMT+1:00) Zurich</option>
			<option value="Indian/Cocos"> [Indian/Cocos]&nbsp;&nbsp;(GMT+6:30) Cocos</option>
			<option value="Pacific/Auckland"> [Pacific/Auckland]&nbsp;&nbsp;(GMT+12:00) Auckland</option>
			<option value="Pacific/Fiji"> [Pacific/Fiji]&nbsp;&nbsp;(GMT+12:00) Fiji</option>
			<option value="Pacific/Guam"> [Pacific/Guam]&nbsp;&nbsp;(GMT+10:00) Guam</option>
			<option value="Pacific/Honolulu"> [Pacific/Honolulu]&nbsp;&nbsp;(GMT-10:00) Honolulu</option>
			<option value="Pacific/Kiritimati"> [Pacific/Kiritimati]&nbsp;&nbsp;(GMT+14:00) Kiritimati</option>
			<option value="Pacific/Pago_Pago"> [Pacific/Pago_Pago]&nbsp;&nbsp;(GMT-11:00) Pago Pago</option>
			<option value="Pacific/Tahiti"> [Pacific/Tahiti]&nbsp;&nbsp;(GMT-9:00) Tahiti</option>
			<option value="Pacific/Tongatapu"> [Pacific/Tongatapu]&nbsp;&nbsp;(GMT+13:00) Tongatapu</option>
		</select>
		</form>
		<div class="mt10 text-center">
			<span class="main-btn"><a href="#" id="btn_save"><spring:message code="portal.btn.label.1" text="확인" /></a></span>
			<span class="main-btn"><a href="#" id="btn_close"><spring:message code="portal.btn.label.2" text="취소" /></a></span>
		</div>
	</div>
</body>
</html>
