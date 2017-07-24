<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<script type="text/javascript">
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
</script>