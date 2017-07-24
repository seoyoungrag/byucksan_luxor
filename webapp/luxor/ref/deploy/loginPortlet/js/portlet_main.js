var LoginPortlet = {
	
	onload : function() {
	},
	onunload : function() {
	},
	onexception : function() {
	},
	onmaximize : function() {
	},
	onminimize : function() {
	},
	onnormalize : function() {
	},
	onedit : function() {
	},
	onview : function() {
	},
	onhelp : function() {
	},
	onrefresh : function() {
	},
	alertTitle : function() {
	},
	validateLogin : function () {
		//validation
		if(luxor.isNullOrEmpty($('#id').val())) {
			alert(login_label_171);
		} else if(luxor.isNullOrEmpty($('#pw').val())) {
			alert(login_label_172);
		} else {
			if(useAES==true){
				$.getLogin();
			}
			LoginPortlet.loginProcess();
		}
	},
	loginProcess : function() {
		if(useHttps==false) {
			if(useAES==false){
				// Seed Encrypt
				$('#loginId').val(document.getElementById("seedOCX").SeedEncryptData($('#id').val()));
				$('#password').val(document.getElementById("seedOCX").SeedEncryptData($('#pw').val()));
			}

		} else {
			$('#loginId').val($('#id').val());
			$('#password').val($('#pw').val());
		}
		
		var param = $('#adminLoginform').serialize();
		callJson("loginController", "loginProcessByPortlet", param, function(json){
			if (useAES) { $.getLoginResult(json);}			
			if(json.loginResult == 0) {
				//아이디 저장
				if ($('#saveid').attr("checked")==true) {
					luxor.setCookie("saveid", $('#id').val());
				} else {
					luxor.setCookie("saveid", null);
				}
				// 로그인 팝업
				var loginPopupLength = luxor.isNullOrEmpty(json.loginPopup) ? 0 : json.loginPopup.length;
				for(var p=0; p < loginPopupLength; p++) {
					var popupId = json.loginPopup[p].popupId;
					var popupType = json.loginPopup[p].popupType;
					var popupTitle = json.loginPopup[p].popupTitle;
					var popupUrl = json.loginPopup[p].popupUrl;
					
					// 팝업이 하루에 한번이 띄우는 형식이고 이미 한번 띄웠다면 건너뜀
					if(popupType!='E' && luxor.trim(luxor.getCookie(popupId))=='on') {
						continue;
					}
					
					var url = popupType=='E' 
							? json.loginPopup[p].popupUrl 
							: '/ep/luxor/portal/loginpopup/popupWindow.jsp?popupId='+popupId
							  +'&popupUrl='+encodeURIComponent(popupUrl)+'&popupTitle='+encodeURIComponent(popupTitle)+'&cacheTime='+new Date();
			  		var opt = "toolbar=no,scrollbars=no,resizable=no,status=no,location=no,menubar=no,"
			  			+"width="+json.loginPopup[p].windowWidth+"px,height="+json.loginPopup[p].windowHeight+"px,"
			  			+"left="+json.loginPopup[p].positionLeft+"px,top="+json.loginPopup[p].positionTop+"px";
			  		window.open(url,"",opt);
				}
				
				// 초기화면(플러그 적용)
				var loginPlugLength = luxor.isNullOrEmpty(json.loginPlug) ? 0 : json.loginPlug.length;

				if(loginPlugLength > 0) {
					var plugId = json.loginPlug[0].plugId;
					var plugType = json.loginPlug[0].plugType;
					var plugTitle = json.loginPlug[0].plugTitle;
					var plugUrl = json.loginPlug[0].plugUrl;
					var isExternal = json.loginPlug[0].isExternal;
					if(plugType!='E' && luxor.trim(luxor.getCookie(plugId))=='on') {
						if(luxor.isNullOrEmpty(json.initialPage) && loginRedirectScript != '') {
							eval("parent."+loginRedirectScript);
						} else if(luxor.isNullOrEmpty(json.initialPage) && loginRedirectScript == '') { 
							parent.document.location.href = "/ep";
						} else {
							parent.document.location.href = "/ep/page/index.do?pageId="+json.initialPage;
						}
					} else {
						luxor.setCookie(plugId,'on',1);
						if(isExternal == 'Y') {
							parent.document.location.href = plugUrl;
						} else {
							parent.document.location.href = "/ep/"+plugUrl;
						}
					}
				} else if(luxor.isNullOrEmpty(json.initialPage) && loginRedirectScript != '') {
					eval("parent."+loginRedirectScript);
				} else if(luxor.isNullOrEmpty(json.initialPage) && loginRedirectScript == '') { 
					parent.document.location = "/ep";
				} else {
					parent.document.location.href = "/ep/page/index.do?pageId="+json.initialPage;
				}
			} else if(json.loginResult == 1) {
				if(isUsingMemeberRequest == "Y") {
					callJson("memberRequestController", "getMemberRequest", "loginId="+$('#id').val()+"&loginPassword="+$('#pw').val(), function(data) {	
						if(data == null) { 
							alert(login_label_173);
						} else if(data.requestStatus == -1){
							alert(login_label_174);
						} else if(data.requestStatus == 0){
							alert(portal_member_request_msg_34);
						} else if(data.requestStatus == 2){
							alert(portal_member_request_msg_35);
						} else {
							alert(login_label_173);
						}
					});
				} else {
					alert(login_label_173);
				}
			} else if(json.loginResult == 2) {
				alert(login_label_174);
			} else if(json.loginResult == 3) {
				alert(login_label_175);
			} else if(json.loginResult == 4) {
				alert(login_label_176);
			} else if(json.loginResult == 5) {
				alert(login_label_181);
			} else if(json.loginResult == 6) {
				//만료
				luxor.popup('/ep/user/getChangePasswordView.do?isExpired=Y', {
					width:500,
					height:300,
					scrollbars:'1',
					resizable:'1'
				});
			} else if(json.loginResult == 7) {
				alert(login_label_181);
			} else if(json.loginResult == 8) {
				alert(login_label_177);
			} else if(json.loginResult == 100) {
				alert(login_label_178);
			} else if(json.loginResult == 101) {
				luxor.popup('/ep/user/getChangePasswordView.do?isNewAccount=Y', {
					width:500,
					height:300,
					scrollbars:'1',
					resizable:'1'
				});
			} else {
				alert(login_label_179);
			}
		});
	},
	logoutProcess : function() {
		callJson("logoutSysController", "getList", "", function(data) {
			for(var i=0; i < data.length; i++) {
				$('body').append("<iframe id='logoutHelpIframe"+i+"' class='dialog'></iframe>");
				$('#logoutHelpIframe'+i).attr('src',data[i].logoutUrl);
			}
			
			callJson("loginController", "logoutProcessByPortlet", "", function(data) {
				setTimeout(function() {
					if(isUsingLoginPage == "Y") {
						top.location.href="/ep";
					} else {
						top.location.reload();
					}
				}, 300);
			});
		});
	},
	prepareSeed: function() {
	    var currRoundKey = document.getElementById("seedOCX").GetCurrentRoundKey();
	    if (currRoundKey == "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0") {
	        var userkey = document.getElementById("seedOCX").GetUserKey();
	        currRoundKey = document.getElementById("seedOCX").GetRoundKey(userkey);
	    }
	    var roundkey_c = document.getElementById("seedOCX").SeedEncryptRoundKey(currRoundKey);
	    $('#roundkey').val(roundkey_c);
	}
};

$(document).ready(function() {
	$('#id').focus();
	
	if(useHttps==false && useAES==false) {
		LoginPortlet.prepareSeed();
	}
	
	$('#adminLoginform').submit(function() {
		return false;
	});
	
	// 일반 포틀릿 인 경우
	$('#submit_img').click(function(e) {
		LoginPortlet.validateLogin();
	});
	
	// 일반 포틀릿 인 경우
	$('#pw').keypress(function(e) {
		if(e.keyCode==13) {
			$('#submit_img').click();
		}
	});
	
	$('#btnLOGOUT').live('click', function(e) {
		 if(confirm(login_label_180)) {
			 LoginPortlet.logoutProcess();
		 }
	});
	
	// 다국어가 2개 미만일 경우
	if(localeLanguage_length==1) {
		 $("#language_wrap").hide();
	}
	
	// 저장아이디 불러오기
	if(!luxor.isNullOrEmpty(luxor.getCookie("saveid"))) {
		$('#id').val(luxor.getCookie("saveid"));
		$('#saveid').attr("checked","checked");
		$('#id').focus();
		$('#pw').focus();
	}
	
	// 비밀번호 변경
	$('#btn_change_password').click(function() {
		luxor.popup('/ep/user/getChangePasswordView.do', {
			width:500,
			height:300,
			scrollbars:'1',
			resizable:'1'
		});
	});

	// 개인정보 수정
	$('#btn_change_personalinfo').click(function() {
		luxor.popup('/ep/user/getChangeUserInformationView.do', {
			width:650,
			height:420,
			scrollbars:'1',
			resizable:'1'
		});
	});

	// 초기화면 설정
	$('#btn_set_home').click(function() {
		luxor.popup('/ep/user/setHomeView.do', {
			width:250,
			height:500,
			scrollbars:'1',
			resizable:'1'
		});
	});

	// 시간대 설정
	$('#btn_set_timezone').click(function() {
		luxor.popup('/ep/user/setTimezoneView.do', {
			width:420,
			height:500,
			scrollbars:'1',
			resizable:'1'
		});
	});

	// 테마 설정
	$('#btn_set_theme').click(function() {
		luxor.popup('/ep/user/setThemeView.do', {
			width:250,
			height:500,
			scrollbars:'1',
			resizable:'1'
		});
	});
	
	// 회원가입
	$('#btn_member_request').click(function() {
		luxor.popup('/ep/memberRequest/getTermsView.do', {
			width:800,
			height:650,
			scrollbars:'1',
			resizable:'1'
		});
	});
});

function confirmChangedPageword(password) {
	if(isUsingMemeberRequest == "Y") {
		postJson("memberRequestController", "confirmMemberRequest", "loginId="+$('#id').val(), function(data) {		
			$('#pw').val(password);
			$('#password').val(password);
			LoginPortlet.validateLogin();
		});
	} else {
		$('#pw').val(password);
		$('#password').val(password);
		LoginPortlet.validateLogin();
	}
}
