var leftMenuPortlet = {
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
	}
};

// 폴더 열기
var mzVertical_open = function(obj) {
	obj.parent('li').children('ul').removeClass('hidden');
	obj.attr("mode","close_child");
	obj.html("<img src='"+themeImageUrl+"/"+portletContext+"/bu_left_minus.gif' alt='"+portal_btn_label_58+"' title='"+portal_btn_label_58+"'>");
};

// 폴더 닫기
var mzVertical_close = function(obj) {
	obj.parent('li').children('ul').addClass('hidden');
	obj.attr("mode","open_child");
	obj.html("<img src='"+themeImageUrl+"/"+portletContext+"/bu_left_plus.gif' alt='"+portal_btn_label_57+"' title='"+portal_btn_label_57+"'>");
};

// ROOT부터 특정 폴더까지 전부 열어줌 
var mzVertical_openAll = function(menuId) {
	mzVertical_open($('#mzv_'+menuId+' > a[mode=open_child]'));
	
	var parentMenuId = $('#mzv_'+menuId).parent().parent().attr('id');
	
	if(typeof(parentMenuId)=='undefined' || parentMenuId=='mzVertical') {
		return false;
	}

	parentMenuId = parentMenuId.substring(4); 
	
	// 최상위까지 계속 올라감 recursively
	mzVertical_openAll(parentMenuId);
};


$(document).ready(function() {
	var contentId = $('#mzVertical').closest('li').attr('id');
	var mzVertical_param = "pageId="+currentPageId+"&contentId="+contentId+"&zoneId=mzVertical";
	
	// 메뉴 로딩
	callJson("portalMenuController", "getMenusOnContent", mzVertical_param, function(data) {
		for(var i=0; i < data.length; i++) {
			var _nodeId = data[i].menuId;
			var _parentId = data[i].parentId;
			var _menuName = data[i].messageName;
			var _hasChild = data[i].hasChild;
			var _script = data[i].script;
			var _depth = data[i].depth;
			var _targetZone = data[i].targetZone;
			var _displayName = data[i].displayName;
			//따로 지정한 메뉴명이 있다면, 페이지명을 쓰지 않고 메뉴명을 씀
			if(_displayName != "" && _displayName != null) {
				_menuName = _displayName;
			}
			
			luxor.log("_menuName : "+_menuName);

			// 기본 페이지 링크
			var _href = "/ep/page/index.do?pageId="+_nodeId;
			
			// 임의의 Script 정의가 있는 경우에는 페이지 링크 대신 onclick으로 대체
			if(!luxor.isNullOrEmpty(_targetZone)) {
				_href = "javascript:targetFrameLoad('"+_href+"','"+_nodeId+"','"+_targetZone+"','side');";
			} else if(!luxor.isNullOrEmpty(_script)) {
				_href = "#";
			} 
			
			// 임의의 script 정의가 있는 경우 스크립트 HEAD에 삽입
			$('head').append("<script type='text/javascript'>function "+contentId+"_"+_nodeId+"(obj) { "+_script+" }<\/script>");
			
			var _targetDIV = _parentId=='ROOT' ? '#mzVertical' : '#mzv_'+_parentId;

			var plus = _hasChild=='Y' ? "<a href='#' mode='open_child'><img src='bu_left_plus.gif' alt='"+portal_btn_label_57+"' title='"+portal_btn_label_57+"'></a>" : "<img src='bu_left.gif'>";
			var css = _targetDIV=='#mzVertical' ? "mzv_row" : "mzv_row hidden";
			
			var paddingLeft = 26 * _depth;
			
			// 서브메뉴가 존재하는 경우 hidden으로 넣어놓음
			if($(_targetDIV+' > ul > li').size()==0) {
				$(_targetDIV).append("<ul class='"+css+"'></ul>");
			}
			
			luxor.log("_targetDIV : "+_targetDIV);
			
			$(_targetDIV+' > ul').append("<li id='mzv_"+_nodeId+"' class='left-menu0"+(_depth+1)+"'><span style='padding-left:"+paddingLeft+"px'></span>"+plus+" <a href="+_href+" onclick=\""+contentId+"_"+_nodeId+"( $('#mzv_"+_nodeId+" > a[mode]') )\">"+_menuName+"</a></li>");
		  $('#leftMenuPortlet img').each(function() {
  			if($(this).attr('src').indexOf(themeImageUrl) == -1) {
  				if(($.browser.version=="6.0" || $.browser.version=="7.0") && $.browser.msie==true) {
  					$(this).attr('src', themeImageUrl + "/"+portletContext+$(this).attr('src').substring($(this).attr('src').lastIndexOf("/")));
  				} else {
  					$(this).attr('src', themeImageUrl + "/"+portletContext+"/" + $(this).attr('src'));
  				}
  			}
		  });
		}

		//적용되는 테마 이미지  경로로 변경
		//테마를 사용하지 않을 포틀릿의 경우 해당 로직을 삭제하세요
		/*$('#leftMenuPortlet img').each(function() {
			if($(this).attr('src').indexOf(themeImageUrl) == -1) {
				if(($.browser.version=="6.0" || $.browser.version=="7.0") && $.browser.msie==true) {
					$(this).attr('src', themeImageUrl + "/"+portletContext+$(this).attr('src').substring($(this).attr('src').lastIndexOf("/")));
				} else {
					$(this).attr('src', themeImageUrl + "/"+portletContext+"/" + $(this).attr('src'));
				}
			}
		});*/
		
		// 파라미터로 넘겨진 menuId가 있는 경우 해당 메뉴를 열어줌
		// currentMenuId값은 portal/page/pageLayout.jsp에 정의되어있음
		// URL끝에 menuId=xxxxx 파라미터를 붙여주면 해당 메뉴가 자동으로 열림
		if(!luxor.isNullOrEmpty(currentMenuId)) {
			mzVertical_openAll(currentMenuId);
		} else {
			// 따로 파라미터로 menuId가 넘어오지 않은 경우에는 현재 페이지의 메뉴를 자동으로 열어줌
			if(!luxor.isNullOrEmpty(currentPageId)) {
				mzVertical_openAll(currentPageId);
			}
		}
	});

	// 폴더 열기
	$('#'+contentId+' a[mode=open_child]').live('click', function() {
		mzVertical_open($(this));
        var h = document.body.scrollHeight;
        parent.setIfrHeight(h+100);
	});
	
    // 처음 로딩 시.
    var h = document.body.scrollHeight;
    parent.setIfrHeight(h+100); 

	// 폴더 닫기
	$('#'+contentId+' a[mode=close_child]').live('click', function() {
		mzVertical_close($(this));
	});

	// 제목 셋팅
	$('div.left-top').text(content.getTitle(contentId));
	
});
