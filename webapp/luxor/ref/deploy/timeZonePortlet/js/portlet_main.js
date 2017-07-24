function initClock (targetId,gmt) {
	$('#digiclock').jdigiclock({
    	clockImagesPath:"/ep/luxor/ref/image/portlet/timezone/clock/",
    	targetId:targetId,
    	gmt:gmt,
    	monthNames:monthNames,
    	dayNames:dayNames,
    	yearSuffix:yearSuffix,
    	dateSuffix:dateSuffix
    });
}

function showOption (count) {
	var timeZoneLocale = $('#timeZoneLocale').html();
	
	$('#digiClockEditView #digiClockOptionView').html('');
	for(var i=0 ; i<count ; i++) {
		var countView = i+1;
		$('#digiClockEditView #digiClockOptionView').append('<div id="digiClock_'+i+'">'+
				'<span class="portlet-black-bold mr5"><a>세계시계 '+countView+'</a></span>'
				+timeZoneLocale+'</div>');
	}
}

$(document).ready(function() {
	var isUserSettingOption = '';
	var clock = '';
	var strClockTemplate = '';
	
	$('#digiClockEditConfirm').die();
	$('#digiClockEditInitialize').die();
	
	$('#timePortlet #gmt option').each(function() {
		if($(this).attr('class').indexOf(locale)!=-1) {
			$(this).attr('selected','selected');
			initClock('digiClock', $(this).val());
		}
	});
	
	$('#timePortlet #gmt').change(function() {
		var gmt = $(this).val();
		var targetId = $(this).closest('div[id^=digiClock]').attr('id');
		initClock(targetId,gmt);
	});
	
	$('#digiClockEditView #digiClockCount').change(function() {
		$('#digiClockEditView #digiClockCount option:selected').each(function() {
			showOption($(this).val());
		});
	});
	
	$('#digiClockEditConfirm').live('click',function(e) {
		var param = 'settingCode=TIMEZONEPORTLET&settingValue=';
		$('#digiClockOptionView #gmt option:selected').each(function() {
			param+=$(this).attr('class')+'/';
		});
		param = param.substring(0,param.lastIndexOf('/'));
		if(isUserSettingOption==null) {
			callJson("userSettingController", "insertUserSetting", param, function(result) {
				if(result==false) {
					alert(strError);
				} else {
					alert(strSuccess);
				}
			});
		} else {
			callJson("userSettingController", "updateUserSetting", param, function(result) {
				if(result==false) {
					alert(strError);
				} else {
					alert(strSuccess);
				}
			});
		}
	});
	
	$('#digiClockEditInitialize').live('click',function(e) {
		if(isUserSettingOption!=null) {
			var param = 'settingCode=TIMEZONEPORTLET';
			callJson("userSettingController", "deleteUserSetting", param, function(result) {
				if(result==false) {
					alert(strError);
				} else {
					alert(strSuccess);
				}
			});
		} else {
			alert(strOptionIsNull);
		}
	});
});