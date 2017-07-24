// Event Handler
var topMenuPortlet = {
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
	userSearch : function() {
		var url = "";
		var txtSrch = $('#searchKey').val();
		var viewType = "1";		// 0:tree/userList/selectBox  1:tree/userList  2:userList/selectBox  3:userList  4:tree
		var returnMethod = "no";
		var treeType = "0";	//트리 Type  *0 : From User Dept Code to Root Code  *1 : From User Dept Code to Institution Code *2 : From User Dept Code to Company Code *3 : From User Dept Code to Root Code (Exclude Other Company) 

		url = "/ep/user/userMng.do?returnMethod="+returnMethod+"&treeType="+treeType+"&nType=0&searchType=byName&searchName="+txtSrch+"&viewType="+viewType+"&isSearch=Y";
				window.open(url,"userSearchForm","height=574 ,width=880");
		//luxor.popup(url,{width:880,height:580});
	}
};

$(document).ready(function() {
	var contentId = $('#mzTop').closest('li').attr('id');
	var mzTop_param = 'pageId=' + currentPageId + '&contentId=' + contentId;
	console.log('mzTop_param=' + mzTop_param);
	
	// 상단 메뉴 가져오기.
	callJson('portalMenuController', 'getMenusOnContent', mzTop_param, function(data) {
		console.log('getMenusOnContent result count='+data.length);
		//var menus = getMenuData(data);
		// setMenuData(getMenuData(data), 0);
		for (var i = 0, n = data.length; i < n; i++) { //var i = 0 -> var i = 1 로 조건변경
			var _nodeId = data[i].menuId;
			var _parentId = data[i].parentId;
			var _menuName = data[i].messageName;
			var _hasChild = data[i].hasChild;
			var _script = data[i].script == null ? '' : data[i].script;
			var _zoneId = data[i].zoneId;
			var _targetZone = data[i].targetZone;
			var _displayName = data[i].displayName;
			// console.log('_menuName='+_menuName);

			// 기본 페이지 링크.
			var _href = '/ep/page/index.do?pageId=' + _nodeId;

			// 임의의 script 정의가 있는 경우 스크립트 HEAD에 삽입
			var scriptVal = '<script>';
			scriptVal += 'function '+contentId + '_' + _nodeId + '()' ;
			scriptVal += '{ try { ' + _script + ' } catch(e) {} }' ;
			scriptVal += '</script>';
			// console.log('scriptVal=' + scriptVal);
			$('head').append(scriptVal);

			// 임의의 Script 정의가 있는 경우에는 페이지 링크 대신 onclick으로 대체
			if (!luxor.isNullOrEmpty(_targetZone)) {
				_href = 'javascript:targetFrameLoad("'+_href+'","'+_nodeId+'","'+_targetZone+'","header");';
			} else if (!luxor.isNullOrEmpty(_script)) {
				_href = '#';
			}

			var _targetDIV = _parentId == 'ROOT' ? _zoneId : 'sp_' + _zoneId + _parentId;
			var _targetVal = '';
			console.log('_targetDIV=' + _targetDIV);
			
			//$('#'+_targetDIV).append(getTopMenus(_targetDIV, _zoneId, _nodeId, _href, contentId, _nodeId, _menuName));

			switch(_targetDIV) {
			case 'mzTop' :		// 상단 메뉴 설정.
				_targetVal += '<li id="sp_' +_zoneId + _nodeId + '">';
				_targetVal += '<a href="' + _href + '" onclick="' + contentId + _nodeId + '()' + '">';
				_targetVal += _menuName;
				_targetVal += '</a></li>';
				console.log('mzTop, _targetVal=' + _targetVal);
				$('#'+_targetDIV).append(_targetVal);
				break;
			case 'mzMiddle' :	// 중단 메뉴 설정.
				_targetVal += '<li id="sp_'+_zoneId + _nodeId + '" style="z-index:1000;">';
				_targetVal += '<a href="' + _href + '" onclick="' + contentId + '_' + _nodeId + '()' + '">';
				_targetVal += _menuName;
				_targetVal += '</a>';
				_targetVal += '</li>';
				// console.log('mzMiddle, _targetVal=' + _targetVal);
				$('#'+_targetDIV).append(_targetVal);
				break;
			case 'mzBottom' :	// 하단 메뉴 설정.
				_targetVal += '<li id="sp_' +_zoneId + _nodeId + '">';
				_targetVal += '<a href="' + _href + '" onclick="' + contentId + _nodeId + '()' + '">';
				_targetVal += _menuName;
				_targetVal += '</a></li>';
				console.log('mzBottom, _targetVal=' + _targetVal);
				$('#'+_targetDIV).append(_targetVal);
				break;
			default : 
				// 서브 메뉴 넣기.
				if ($('#'+_targetDIV+' > ul > li').size() == 0) {
					_targetVal += '<br/>';
					_targetVal += '<ul class="top-sub-menu"';
					
					if(_zoneId == 'mzTop') {
						_targetVal += ' style="z-index:100000;margin-top:0px"></ul>';
					} else if(_zoneId == 'mzMiddle') {
						_targetVal += ' style="z-index:100000; top:7px"></ul>';
					} else {
						_targetVal += ' style="z-index:100000;margin-top:0px"></ul>';
					}
					console.log('li size == 0, _targetVal='+_targetVal);
					$('#'+_targetDIV).append(_targetVal);
				}
				_targetVal = '';
				_targetVal += '<li id="' +_zoneId + _nodeId + '">';
				_targetVal += '<a href="' + _href + '" onclick="' + contentId + '_' + _nodeId + '()">';
				_targetVal += _menuName + '</a></li>';
				console.log('서브 메뉴 넣기, _targetVal=' + _targetVal);
				$('#'+_targetDIV+' > ul').append(_targetVal);
			}
		}
		
		// 마이 메뉴 설정.
		
		var myMenuVal = '';
		myMenuVal += '<li id="sp_mymenu">';
		myMenuVal += '<a id="mymenu_anchor" href="#">';
		myMenuVal += '<spring:message code="portal.label.76" />';	// '마이메뉴'
		myMenuVal += '</a>';
		myMenuVal += '</li>';
		//console.log('myMenuVal='+myMenuVal);
		//$('#mzMiddle').append(myMenuVal); //마이메뉴 표시 제거 2015.06.15 dy.kim
		/*
		if (luxor.usePersonalMenu && luxor.isLoggedIn) {
			console.log('마이메뉴 설정!');
			//홈설정
			callJson('portalPageController', 'getHome', '', function(data) {
				console.log('getHome result count=' + data.length);
				if(data != null) {
		        	if(data.pageId != currentPageId || (data.pageId == currentPageId && initHome != "")) {
		        		if(initHome == currentPageId) {
		        			$('#mzBottom').append('<li class="isHomePageOn" id="setPageHome" title="'+portal_tooltip_msg_03+'"><a></a></li>');
		        		} else {
		        			$('#mzBottom').append('<li class="isHomePageOff" id="setPageHome" title="'+portal_tooltip_msg_04+'"><a></a></li>');
		        		}
		        	} 
				} else {
					$('#mzBottom').append('<li class="isHomePageOff" id="setPageHome" title="'+portal_tooltip_msg_04+'"><a></a></li>');
				}
	        	return false;
			});
			
			var isMyMenuPage = false;
			callJson('portalMenuController', 'getMyMenus', '', function(mymenu) {
				console.log('getMyMenus result count=' + mymenu.length);
				if(mymenu.length > 0) {
					$('#sp_mymenu').append('<br /><ul class="top-sub-menu" style="z-index:1000; top:7px;"></ul>');
				}
				
				for(var j=0; j < mymenu.length; j++) {
					var myMenuId = mymenu[j].menuId;
					var myMenuName = mymenu[j].messageName;
					var myHref = '/ep/page/index.do?pageId='+myMenuId;
					if(mymenu[j].displayNameId != 'display_none') {
						var param = 'messageId='+mymenu[j].displayNameId+'&langType='+langType;
						callJson('messageController', 'getMessageByIdLang', param, function(data) {
							if(data.messageName.indexOf("&") > -1) {
								data.messageName = data.messageName.replace(/amp;/gi,"");
							}
							if(currentPageId == myMenuId) {
								$('#mzBottom').append('<li class="myMenuOn" id="setMyMenuPage" title="'+portal_tooltip_msg_01+'"><a></a></li>');
								isMyMenuPage = true;
							} 
							$('#sp_mymenu > ul').append('<li id="sp_'+myMenuId+'"><a href="'+myHref+'">'+data.messageName+'</a></li>');
						});
					} else {
						if(currentPageId == myMenuId) {
							$('#mzBottom').append('<li class="myMenuOn" id="setMyMenuPage" title="'+portal_tooltip_msg_01+'"><a></a></li>');
							isMyMenuPage = true;
						} 
						$('#sp_mymenu > ul').append('<li id="sp_'+myMenuId+'"><a href="'+myHref+'">'+myMenuName+'</a></li>');
					}
				}
				
				setTimeout(function(){
					if(mymenu.length == 0 || isMyMenuPage == false) {
						$('#mzBottom').append('<li class="myMenuOff" id="setMyMenuPage" title="'+portal_tooltip_msg_02+'"><a></a></li>');
					}
				},300);
			});
		} else {
			callJson('portalPageController', 'getHome', '', function(data) {
				if(data != null) {
		        	if(data.pageId != currentPageId || (data.pageId == currentPageId && initHome != "")) {
		        		if(initHome == currentPageId) {
		        			$('#mzBottom').append('<li class="isHomePageOn" id="setPageHome" title="'+portal_tooltip_msg_03+'"><a></a></li>');
		        		} else {
		        			$('#mzBottom').append('<li class="isHomePageOff" id="setPageHome" title="'+portal_tooltip_msg_04+'"><a></a></li>');
		        		}
		        	} 
				} else {
					$('#mzBottom').append('<li class="isHomePageOff" id="setPageHome" title="'+portal_tooltip_msg_04+'"><a></a></li>');
				}
	        	return false;
			});
		}
		*/
		if(typeof(isPersonal) != 'undefined' && luxor.isLoggedIn) {
			// 마이 컨텐츠 추가를 클릭했을때 처리 핸들러는 contentManager.js에 정의되어있음
			if(isPersonal == true) {
				/*$('#mzMiddle').append('<li id="sp_mycontent_from_top" style="float:right;"><a id="mycontent_anchor" href="#"><strong>'+portal_label_77+'</strong></a><a id="mycontent_editmode" href="#">'+portal_content_label_15+'</a></li>');*/
			}
		}
	});
	
	
	/**
	 * 이벤트
	 */
	// 마이메뉴 클릭.
	$('#mymenu_anchor').live('click', function() {
		var _contentId = $(this).closest('li[class^=content-wrap]').attr('id');
		luxor.popup('/ep/page/personalPageSelector.do?contentId='+contentId, {
			width:650,
			height:500,
			scrollbars:'1',
			resizable:'1'
		});
	});
	
	//현재 페이지를 홈으로 설정하는 icon클릭.
	$('#setPageHome').live('click', function() {
		//홈으로 설정시
		if($(this).attr('class') == 'isHomePageOff') {
			if(confirm(user_alert_msg_9)) {
				var param = 'pageId='+currentPageId;
				callJson('userController', 'setHome', param, function(data) {
			        if(data._errorCode=='-9999') {
			        	alert(portal_alert_msg_9);
			        } else {
			            alert(portal_alert_msg_13);
			            document.location.href ='/ep';
			        }
				});
			}
		} 
	});
	
	//마이메뉴  페이지 설정 클릭.
	$('#setMyMenuPage').live('click', function() {
		//해제시
		if($(this).attr('class') == 'myMenuOn') {
			var param = 'pageId=MYMENU_'+userUid+'&contentId='+$(this).closest('li[class^=content-wrap]').attr('id')+'&zoneId=mzMiddle&menuId='+currentPageId;
			$(this).attr('class','myMenuOff');
			callJson('portalMenuController', 'deleteMenu', param, function() {
				$('#sp_mymenu ul').html('');
				callJson('portalMenuController', 'getMyMenus', '', function(mymenu) {
					for(var j=0; j < mymenu.length; j++) {
						var myMenuId = mymenu[j].menuId;
						var myMenuName = mymenu[j].messageName;
						var myHref = '/ep/page/index.do?pageId='+myMenuId;
						$('#sp_mymenu > ul').append('<li id="sp_'+myMenuId+'"><a href="'+myHref+'">'+myMenuName+'</a></li>');
					}
				});
			});
		} else { //등록시
			var param = 'pageId=MYMENU_'+userUid+'&contentId='+$(this).closest('li[class^=content-wrap]').attr('id')+'&zoneId=mzMiddle&parentId=ROOT&menuId='+currentPageId;
			$(this).attr('class','myMenuOn');
			callJson('portalMenuController', 'insertMenu', param, function() {
				var myHref = '/ep/page/index.do?pageId='+currentPageId;
				callJson('portalMenuController', 'getMyMenus', '', function(mymenu) {
					if(mymenu.length > 0 && $('#sp_mymenu ul').attr('class') != 'top-sub-menu') {
						$('#sp_mymenu').append('<br /><ul class="top-sub-menu" style="z-index:1000; top:7px;"></ul>');
					}
					$('#sp_mymenu ul').append('<li id="sp_'+currentPageId+'"><a href="'+myHref+'">'+currentPageName+'</a></li>');
				});
			});
		}
	});
	
	// 사용자 & 메뉴 검색 클릭.
	$('#doSearch').live('click', function() {
		if($('#searchWay').val()=="user") {
			// 사용자 검색
			topMenuPortlet.userSearch();
		}
		else {
			// 메뉴 검색
			$('#search_result').html('');
			callJson('portalMenuController', 'getMenus', 'searchKey='+$('#searchKey').val(), function(data) {
				for(var j=0; j < data.length; j++) {
					var pageId = data[j].pageId;
					var menuName = "";
					if(data[j].displayName == null || data[j].displayName == '') {
						
						menuName = data[j].messageName.replace($('#searchKey').val(),'<span class="bold red">'+$("#searchKey").val()+'</span>');
					} else {
						menuName = data[j].displayName.replace($('#searchKey').val(),'<span class="bold red">'+$("#searchKey").val()+'</span>');
					}
					var menuHref = '/ep/page/index.do?pageId='+pageId;
					
					$('#search_result').append('<div class="menu-search-result"><a href="'+menuHref+'">'+menuName+'</a></div>');
				}
				
				if(data.length==0) {
					$('#search_result').append('<div style="padding:3px;text-align:center;">'+portal_alert_msg_56+'</div>');
				}
				
				var pos = $('#searchKey').position();
				$('#search_result_dialog').css('top', pos.top+19);
				$('#search_result_dialog').css('left', pos.left);
				$('#search_result_dialog').show();
			});
		}
	});
	
	//검색 엔터
	$('#searchKey').keyup(function(e) {
		if(e.keyCode==13) {
			$('#doSearch').click();
		}
	});
	
	/**
	 * 함수
	 */
	// 블로그, 나의 동호회, 마이페이지 이동.
	(function(contentId) {
		var goPageObj = $('#'+contentId + ' [name^=goPageByTop] li a');
		goPageObj.click(function() {
			console.log('이동할 페이지=' + $(this).attr('gopage'));
			goPage($(this).attr('gopage'));
		});

		function goPage(url) {
			alert(url);
		}
	})(contentId);
	
	
	// 상 중 하단 메뉴 불러오기.
	function getMenuData(data) {
		var maxSize = data.length;
		var menus = data;
		return function (i){
			if (maxSize > i) {
				return menus[i];
			}
			return null;
		};
	}
	
	function setMenuData(func, index) {
		var data = func(index);
		if (data != null) {
			var _nodeId = data.menuId;
			var _parentId = data.parentId;
			var _menuName = data.messageName;
			var _hasChild = data.hasChild;
			var _script = data.script == null ? '' : data.script;
			var _zoneId = data.zoneId;
			var _targetZone = data.targetZone;
			var _displayName = data.displayName;
			
			// 기본 페이지 링크.
			var _href = '/ep/page/index.do?pageId=' + _nodeId;
			
			// 임의의 Script 정의가 있는 경우에는 페이지 링크 대신 onclick으로 대체
			if (!luxor.isNullOrEmpty(_targetZone)) {
				_href = 'javascript:targetFrameLoad("'+_href+'","'+_nodeId+'","'+_targetZone+'","header");';
			} else if (!luxor.isNullOrEmpty(_script)) {
				_href = '#';
			}
			
			var _targetDIV = _parentId == 'ROOT' ? _zoneId : 'sp_' + _zoneId + _parentId;
			$('#'+_targetDIV).append(getTopMenus(_targetDIV, _zoneId, _nodeId, _href, contentId, _nodeId, _menuName));
			
			return setMenuData(func, (index + 1));
		} else {
			return;
		}
	}
	
	function getTopMenus(_targetDIV, _zoneId, _nodeId, _href, contentId, _nodeId, _menuName) {
		var result = '';
		
		if ('mzTop' || 'mzMiddle' || 'mzBottom') {
			if ('mzMiddle' == _targetDIV) {
				result += '<li id="sp_' +_zoneId + _nodeId + '">';
			} else {
				result += '<li id="sp_' +_zoneId + _nodeId + '" style="z-index:1000;">';
			}
			
			result += '<a href="' + _href + '" onclick="' + contentId + _nodeId + '()' + '">';
			result += _menuName;
			result += '</a></li>';
			
			console.log('getTopMenus, result=' + result);
		} else {
			if ($('#'+_targetDIV+' > ul > li').size() == 0) {
				result += '<br/>';
				result += '<ul class="top-sub-menu"';
				
				if(_zoneId == 'mzTop') {
					result += ' style="z-index:100000;margin-top:0px"></ul>';
				} else if(_zoneId == 'mzMiddle') {
					result += ' style="z-index:100000;"></ul>';
				} else {
					result += ' style="z-index:100000;margin-top:0px"></ul>';
				}
			}
			result += '<li id="' +_zoneId + _nodeId + '">';
			result += '<a href="' + _href + '" onclick="' + contentId + '_' + _nodeId + '()">';
			result += _menuName + '</a></li>';
		}
		console.log('서브 메뉴 넣기, result=' + result);
		return result;
	}

});
