
var user = {
	getIUser: function(userID) {
		var result = null;
		$.ajaxSetup({async:false});
		callJson("userController","getIUserAjax","userID="+userID, function(data) {
			result = data;
		});
		return result;
	},
	
	getIUsers: function(userIDArr) {
		var result = null;
		$.ajaxSetup({async:false});
		var param = "";
		for(var i=0; i < userIDArr.length; i++) {
			param += "&userID="+userIDArr[i];
		}
		param = param.substring(1);
		callJson("userController", "getIUsersAjax", param, function(data) {
			result = data;
		});
		return result;
	}	
}