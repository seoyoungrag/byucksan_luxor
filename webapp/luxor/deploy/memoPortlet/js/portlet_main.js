$(document).ready(function() {
	var memoId ="";
	var memoSkin ="default";
	var param = "userId="+userUid;
	
	$('#memoText').height($('#memoText').closest('.content-body').height());
	
	if(userUid != '') {
		callJson("generalPortletController", "getMemo", param, function(data) {
			if(data.memoId==null){
				memoId ="not memorized";
				$('#memoText').val(msgInitMemo);
			}else{
				memoId = data.memoId;
				memoSkin = data.memoSkin;
				$('#memoText').attr('class','memo-content '+data.memoSkin);
				$('#memoText').val(data.memoValue);
			}
		});
	}
	
	$('#memoText').live('blur', function(e) {
		var param = "";
		var value = $('#memoText').val();
		value = value.replace(/\n/gi,"<br>");
		if(memoId=="on memorizing" && $('#memoText').val()=="") {
			memoId="not memorized";
			$('#memoText').val(msgInitMemo);
		} else if(memoId=="not memorized" || memoId=="on memorizing") {
			value = encodeURIComponent(value);
			param = "userId="+userUid+"&memoSkin=default&memoId="+luxor.generateId()+"&memoValue="+value;
			callJson("generalPortletController", "insertMemo", param, function(data) {
				if(data==false){
					alert(msgError);
				}
			});
		} else {
			value = encodeURIComponent(value);
			param = "userId="+userUid+"&memoSkin="+memoSkin+"&memoId="+memoId+"&memoValue="+value;
			callJson("generalPortletController", "updateMemo", param, function(data) {
				if(data==false){
					alert(msgError);
				}
			});
		}
	});
	
	$('#memoText').live('click', function(e) {
		if(memoId=="not memorized") {
			$('#memoText').val("");
			memoId="on memorizing";
		} 
	});
});