/**
 * 컨텐츠 렌더링 엔진 (2010.06.11 - 월드컵 개막식 날)
 * Created by MRJH
 */
var content = {
	/**
	 * 현재 content.js 파일을 호출하는 페이지
	 * CM : contentManager (컨텐츠 관리)
	 * PM : pageManager (페이지 관리)
	 * UM : userPageManager (사용자 페이지)
	 */
	refer: 'UM',
	
	/**
	 * 현재 컨텐츠를 로딩하고 있는 페이지 ID
	 */
	pageId: '',
	
	/**
	 * 최대화 여부
	 */
	isMax: false,
	
	/**
	 * 옵션을 통한 수정여부
	 */
	isModified : false,
	
	/**
	 * 리사이즈시 autoheight조절여부
	 */
	needResize : true,
	
	/**
	 * 자동높이 조절 여부
	 */
	isAutoHeight: false,
	
	
	/**
	 * isAutoHeight가 true인 경우 해당 컨텐츠 아이디값
	 */
	autoHeightContentId:'',
	
	/**
	 * 페이지에 포함된 전체 컨텐츠 수
	 */
	allContentCount: 0,
	
	/**
	 * 현재 로딩 완료된 컨텐츠 수 (최종적으로는 allContentCount와 같아짐)
	 */
	loadedContentCount: 0,

	/**
	 * 컨텐츠 로드 (content.load)가 완료되고 난 후 호출되는 콜백함수
	 * loadDone에서 호출됨
	 * 사용예) content.load(data, function() { ... });
	 */
	callBackFunc: null,
	
	/**
	 * initialize content object cache
	 * HashMap is defined at luxor.js
	 */
	cache: new HashMap(),

	/**
	 * Content options
	 */
	opt: {
		draggable:false,
		preview:false,
		showTitle:true,
		showBody:true,
		showMenuList:['go','max','min','help','edit','normal','reload','setup','del']
	},

	/**
	 * zone내에서 컨텐츠간의 순서조정(drag & drop)이 필요한 경우 true로 셋팅
	 */
	setDraggable: function(bool) {
		this.opt.draggable = bool;
	},

	/**
	 * preview로 셋팅시에는 각 버튼의 function 수행안함
	 */
	setPreview: function(bool) {
		this.opt.preview = bool;
	},

	/**
	 * 보여줄 메뉴목록(Array)을 정의
	 * contentId가 없는 경우 해당 페이지의 모든 컨텐츠들이 이 속성을 상속받음
	 */
	setMenuList: function(list, contentId, portalId) {
		this.opt.showMenuList = list;
		if(typeof(contentId)!='undefined') {
			$('#'+contentId+' ul > li').each(function() {
				if($.inArray($(this).attr('mode'), list) == -1) {
					$(this).hide();
				} 
				//상위포탈 페이지의 컨텐츠인 경우 설정아이콘 을 제거
				if(typeof(currentPortalId)!='undefined' && typeof(portalId)!='undefined' && ($(this).attr('mode') == 'setup' || $(this).attr('mode') == 'del')) {
					if(currentPortalId != portalId) {
						$(this).hide();
					}
				}
			});
		}
	},

	/**
	 * 타이틀바 보여줄지 여부
	 */
	setTitle: function(bool) {
		this.opt.showTitle = bool;
	},
	
	/**
	 * 타이틀바 셋팅
	 */
	setTitle: function(contentId, title) {
		if($.browser.msie==true && $.browser.version=="7.0" && $('#'+contentId).attr('class').indexOf("content-wrap")==-1) {
			$('#'+contentId).attr('id','');
		}
		$('#'+contentId+' div.content-title-bar').text(title);
	},
	
	/**
	 * 상위 포탈 페이지 내의 현재 포탈 컨텐츠의 경우 구분자를 같이 넣어준다.
	 */
	setTitleWithPortalName: function(contentId, title, currentPortalName) {
		$('#'+contentId+' div.content-title-bar').html(title+"<span style='color:red'>["+currentPortalName+"]</span>");
	},

	/**
	 * 타이틀 텍스트 가져옴
	 */
	getTitle: function(contentId) {
		return $('#'+contentId+' div.content-title-bar').text();
	},

	/**
	 * 바디 부분을 부여줄지 여부
	 */
	setBody: function(bool) {
		this.opt.showBody = bool;
	},
	
	/**
	 * 개인화 설정 페이지에서 관리자가 Fixed Y/N 셋팅
	 */
	setFixed: function(contentId, val) {
		var param = "pageId="+selectedNodeId+"&contentId="+contentId+"&isFixed="+val;
		callJson("portalPageZoneController", "updateContentFixedFlag", param, function(data) {
			$('#'+selectedNodeId+' a:first').click();
		});
	},
	
	/**
	 * 포틀릿쪽에 정의된 상태 함수를 호출해 줌
	 */
	callPorltetFunc: function(contentId, action) {
		try {
			var portletId = this.getPortletId(contentId);
			eval(portletId+".on"+action+"()");
		} catch(e) {}
	},

	/**
	 * 포틀릿에서 제공하는 Javascript 로딩
	 */
	loadPortletJs: function(portletId) {
		var url = "/ep/luxor/common/jsProxy/getPortletResource.jsp?portletId="+portletId+"&type=js&cacheTime="+new Date();
		$.get(url, function(resource) {
			var arr = eval(resource);
			for(var i=0; i < arr.length; i++) {
				$.getScript(arr[i]);
			}
		});
	},

	/**
	 * 포틀릿에서 제공하는 CSS 로딩
	 */
	loadPortletCss: function(portletId) {
		var url = "/ep/luxor/common/jsProxy/getPortletResource.jsp?portletId="+portletId+"&type=css&cacheTime="+new Date();
		$.get(url, function(resource) {
			var arr = eval(resource);
			var linkStr = "";
			for(var i=0; i < arr.length; i++) {
				if($('link[href="'+arr[i]+'"]').is('link')) {
					$('link[href="'+arr[i]+'"]').attr('href',arr[i]);
				} else {
					linkStr += "<link name='portletCSS' rel='stylesheet' type='text/css' href='"+arr[i]+"'>";
				}
			}
			//$('body').prepend(linkStr);
			$('head').append(linkStr);
		});
	},

	/**
	 * 포틀릿 보기(View) 페이지 URL
	 */
	getPortletViewUrl: function(portletId) {
		// 미리보기인 경우에는 Preview 페이지로
		if(content.opt.preview) {
			return content.getPortletPreviewUrl(portletId);
		}
		var dls = document.location.search;
		var query = dls.substring(1, dls.length);
		return "/ep/luxor/common/jsProxy/getPortlet.jsp?portletId="+portletId+"&themeImageUrl="+themeImageUrl+"&"+query+"&cacheTime="+new Date();
	},

	/**
	 * 미리보기 페이지 (preview.html로 고정되어있음)
	 */
	getPortletPreviewUrl: function(portletId) {
		return "/ep/luxor/deploy/"+portletId+"/html/preview.html";
	},

	/**
	 * 포틀릿 수정(Edit) 페이지 URL
	 */
	getPortletEditUrl: function(portletId) {
		var dls = document.location.search;
		var query = dls.substring(1, dls.length);
		return "/ep/luxor/common/jsProxy/getPortlet.jsp?portletId="+portletId+"&mode=edit&themeImageUrl="+themeImageUrl+"&"+query+"&cacheTime="+new Date();
	},

	/**
	 * 포틀릿 도움말(Help) 페이지 URL
	 */
	getPortletHelpUrl: function(portletId) {
		var dls = document.location.search;
		var query = dls.substring(1, dls.length);
		return "/ep/luxor/common/jsProxy/getPortlet.jsp?portletId="+portletId+"&mode=help&themeImageUrl="+themeImageUrl+"&"+query+"&cacheTime="+new Date();
	},
	
	/**
	 * 포틀릿 Go 페이지 URL
	 */
	getPortletGoUrl: function(portletId) {
		var dls = document.location.search;
		var query = dls.substring(1, dls.length);
		return "/ep/luxor/common/jsProxy/getPortlet.jsp?portletId="+portletId+"&mode=go&themeImageUrl="+themeImageUrl+"&"+query+"&cacheTime="+new Date();
	},

	/**
	 * content에 맵핑된 portlet ID값 구함
	 */
	getPortletId: function(contentId) {
		if(contentId=='NO_PERMISSION') {
			return 'NO_PERMISSION';
		}
		var _portletId = this.cache.get(contentId);
		return _portletId;
	},
	
	/**
	 * 현재 content가 소속된 zone ID값 구함
	 */
	getZoneId: function(contentId) {
		return $('#'+contentId).closest('div[class^=luxor]').attr('id');
	},
	
	/**
	 * 컨텐츠가 채워지기전 zone의 초기 height값
	 */
	getInitialZoneHeight: function(zoneId) {
		return $('#'+zoneId).height();
	},
	
	/**
	 * zone의 height값을 구함
	 */
	getZoneHeight: function(zoneId) {
		var _h = 0;
		$('#'+zoneId+" .content-wrap").each(function() {
			_h += $(this).outerHeight(true);
		});
		$('#'+zoneId+" .content-wrap-hide").each(function() {
			_h += $(this).outerHeight(true);
		});
		//h가 0일경우 iframe으로 들어가있는것인지 확인하여 추가한다.
		if(targetFrame && _h == 0) { 
			_h = $('#'+zoneId+" > iframe").outerHeight(true);
		}
		return _h;
	},
	
	/**
	 * zone에 컨텐츠가 다 채워지고 난 후 zone의 최종 height값을 셋팅
	 */
	adjustZoneHeight: function(zoneId) {
		var _ih = this.getInitialZoneHeight(zoneId);
		var _zh = this.getZoneHeight(zoneId);
		if(_zh > _ih) {
			$('#'+zoneId).height(_zh);
		}
	}, 

	/**
	 * 포틀릿에서 높이를 조절하고 싶은 경우 이 함수를 호출해준다 
	 */
	adjustContentHeight: function(contentId, height) {
		$('#'+contentId+' .content-body').height(height);
	},
	
	getMinHeight: function(contentStyle) {
		var checkValue = '.content-body{min-height:';
		var startPoint = contentStyle.indexOf(checkValue)+checkValue.length;
		var minHeight = contentStyle.substring(startPoint);
		minHeight = minHeight.substring(0,minHeight.indexOf('px;'));
		return minHeight;
	},
	
	/**
	 * isAutoHeight값이 Y로 선택된 컨텐츠가 있는 경우 모든 컨텐츠 로딩 완료후
	 * 해당 컨텐츠의 높이를 자동으로 윈도우에 맞춰줌 
	 */
	adjustBodyHeight: function(margin, isIframeAutoHeight) {
		if(!isIframeAutoHeight) {
			isIframeAutoHeight = false;
		}
		
		if((this.opt.preview || this.isAutoHeight==false) && isIframeAutoHeight==false) {
			return;
		}
		
		try {
			if(!margin) {
				margin = 0;
			}

			content.needResize=false;
			//컨텐츠의 높이를 변경하는  margin, padding값을 받아 조절해준다.
			var contentPaddings = parseInt($('#'+this.autoHeightContentId+' .content-body').css('padding-top').replace('px','')) +
			parseInt($('#'+this.autoHeightContentId+' .content-body').css('padding-bottom').replace('px','')) +
			parseInt($('#'+this.autoHeightContentId+' .portlet-wrap').css('padding-top').replace('px','')) +
			parseInt($('#'+this.autoHeightContentId+' .portlet-wrap').css('padding-bottom').replace('px','')) ;
			
			var bodyHeight = $(document).height() - $('#header').outerHeight(true) - $('#footer').outerHeight(true) - $('#'+this.autoHeightContentId+' .content-top').outerHeight(true) - margin - contentPaddings;
			var tempHeight = bodyHeight + $('#header').outerHeight(true) + $('#footer').outerHeight(true) + $('#'+this.autoHeightContentId+' .content-top').outerHeight(true) + contentPaddings;
			
			$('#'+this.autoHeightContentId+' .content-body').css('min-height','0');
			$('#'+this.autoHeightContentId+' .content-body').height(bodyHeight);
			$('#'+this.autoHeightContentId+' .content-body').attr('class','content-body autoheight-content');
			$('#'+this.autoHeightContentId+' iframe').css('min-height','0');
			$('#'+this.autoHeightContentId+' iframe').height(bodyHeight);
			$('#'+this.autoHeightContentId+' iframe').attr('class','autoheight-content');
			$('div.luxor-left').height(bodyHeight);
			$('div.luxor-content').height(bodyHeight);
			$('div.luxor-right').height(bodyHeight);
			$('div.leftbox').height(bodyHeight-40);
			$('.luxor-content iframe').height(bodyHeight-10);
			if((tempHeight - $(window).height()) > 0 && bodyHeight>0) {
				margin += tempHeight - $(window).height();
				this.adjustBodyHeight(margin);
			}
			if($.browser.msie==true) {
				content.needResize=true;
			} else {
				content.needResize=true;
			}
		} catch(e) {}
	},

	/**
	 * 컨텐츠가 로딩이 완료될때마다 이 함수를 호출하고
	 * 모든 컨텐츠가 로딩이 완료되면 body의 height조정이 일어남
	 */
	loadDone: function() {
		this.loadedContentCount++;
		if(this.allContentCount==this.loadedContentCount) {
			this.adjustBodyHeight();
			
			try {
				//다른 domain의 페이지를 호출하는 포틀릿이 있을 경우 모두 불러와지기 전까지는 interval을 돌면서 높이를 체크
				var count = 0;
				var firstZoneHeight = Math.max(content.getZoneHeight('left'), content.getZoneHeight('content'), content.getZoneHeight('right'));
				var isAutoHeight = this.isAutoHeight;
				var innerHtmlHeight = firstZoneHeight+$('.luxor-top').outerHeight(true) + $('.luxor-footer').outerHeight(true);
				
				var intervalId = setInterval(function() {
					var maxZoneHeight = Math.max(content.getZoneHeight('left'), content.getZoneHeight('content-left'), content.getZoneHeight('content'), content.getZoneHeight('content-right'), content.getZoneHeight('right'));
					innerHtmlHeight = maxZoneHeight+$('.luxor-top').outerHeight(true) + $('.luxor-footer').outerHeight(true);
					$('div.luxor-left').height(maxZoneHeight);
					$('div.luxor-content').height(maxZoneHeight);
					$('div.luxor-right').height(maxZoneHeight);
					$('div.luxor-left > iframe').height(maxZoneHeight);
					$('div.luxor-content > iframe').height(maxZoneHeight);
					$('div.luxor-right > iframe').height(maxZoneHeight);
					
					
					//5초 내로는 페이지가 다 뿌려지겠지..
					if(firstZoneHeight != maxZoneHeight || parent.targetFrame !=null || count == 15) {
						clearInterval(intervalId);
						//부분 리프레시로 로드된 페이지라면 호출한 페이지에 자신의 높이를 전달해줍니다.
						//자동높이로 설정된 페이지라면, window.height에 맞게 iframe 높이를 변경해줍니다.
						if(!isAutoHeight && parent.targetFrame != null && innerHtmlHeight!=0) {
							//parent.setTargetFrameHeight(innerHtmlHeight,isAutoHeight);
							parent.setTargetFrameHeight(0,isAutoHeight);
						} else if(isAutoHeight && parent.targetFrame != null) {
							parent.setTargetFrameHeight(0,isAutoHeight);
						}
					} else {
						if(!isAutoHeight && parent.targetFrame != null) {
							parent.setTargetFrameHeight(innerHtmlHeight,isAutoHeight);
						} else if(isAutoHeight && parent.targetFrame != null) {
							parent.setTargetFrameHeight(0,isAutoHeight);
						}
						count++;
					}
				}, 500);
				this.callBackFunc();
			} catch(e) { }
		}
	},

	/** 
	 * zone의 갯수를 구함
	 */
	loadedContentCountInEachZone: function(zoneId) {
		var count =0;
		$('#'+zoneId+" .content-wrap").each(function() {
			count++;
		});
		$('#'+zoneId+" .content-wrap-hide").each(function() {
			count++;
		});
		return count;
	},
	
	/**
	 *  타이틀 칸에 내용이 없을때 타이틀칸 hide
	 */
	autoCollapseTitleArea: function(contentId) {
		if(content.opt.preview==false
			&& $('#'+contentId+' li[mode=max]').css('display')=='none'
			&& $('#'+contentId+' li[mode=min]').css('display')=='none'
			&& $('#'+contentId+' li[mode=normal]').css('display')=='none'
			&& $('#'+contentId+' li[mode=help]').css('display')=='none'
			&& $('#'+contentId+' li[mode=go]').css('display')=='none'
			&& $('#'+contentId+' li[mode=reload]').css('display')=='none'
			&& $('#'+contentId+' li[mode=edit]').css('display')=='none'
			&& $('#'+contentId+' .content-title-bar').css('display')=='none') {
			$('#'+contentId+' .content-top-div').removeClass('content-top');
			if($.browser.msie==true && $.browser.version!="8.0") {
				$('#'+contentId+' .content-top').css('display','none');
			} 
		}
	},
	
	/**
	 * 컨텐츠 렌더링의 핵심 부분임
	 * 
	 * 렌더링순서
	 * 
	 * 1. contentLayout.jsp 라는 껍데기 HTML을 가져옴
	 * 2. 페이지에 등록된 모든 컨텐츠 수만큼 루프를 돌기시작함
	 * 3. 자기 자신이 등록된 zone에다가 1번에 가져온 빈 껍데기 HTML을 넣음
	 * 4. DIV에 id 속성 부여
	 * 5. id속성을 부여했으므로 $('#id')로 탐색이 가능함
	 * 6. 컨텐츠명을 title bar에 셋팅
	 * 7. 포틀릿을 Ajax로 가져와서 content-body부분에 셋팅
	 * 8. Drag & Drop을 사용하는 곳에서는 Drag & Drop 셋팅
	 * 9. 컨텐츠가 로딩이 완료되면 각 zone의 height를 셋팅 (reload함수에 있음)
	 *  
	 */
	load: function(contents, cb) {
		this.callBackFunc = cb;
		$.get('/ep/luxor/portal/content/contentLayout.jsp?themeImageUrl='+themeImageUrl+'&cacheTime='+new Date(), function(data) { // Step 1
			content.allContentCount = contents.length;
			var strContentStyle = "";
			var strContentStyleForEach = "";

			//IE경우 header에 style이 많이 들어가는 경우 인식을 못하기 때문에 한번에 넣도록 한다.
			for(var i=0; i < contents.length; i++) { // Step 2
				if(typeof(contents[i].contentStyle)=='undefined') {
					contents[i].contentStyle = '';
				}
				if(typeof(contents[i].contentStyleForEach)=='undefined') {
					contents[i].contentStyleForEach = '';
				}
				var contentId = contents[i].contentId;
				var contentStyle = contents[i].contentStyle.replace(/\[contentId\]/g,'#'+contentId);
				
				var contentStyleForEach = contents[i].contentStyleForEach.replace(/\[contentId\]/g,'#'+contentId);
				if(!luxor.isNullOrEmpty(contentStyle)) {
					strContentStyle += " "+contentStyle;
				}
				if(!luxor.isNullOrEmpty(contentStyleForEach)) {
					strContentStyleForEach += " "+contentStyleForEach;
				}
			}
			//Personalization check
			var contentIds = "";
			var personalizedContents = "";
			for(var i=0; i < contents.length; i++) {
				if(contentIds == "") { 
					contentIds += contents[i].contentId+'|';
				} else if(contentIds.indexOf(contents[i].contentId)>-1) {
					personalizedContents += contents[i].contentId+"|";
				} else {
					contentIds += contents[i].contentId+'|';
				}
			}
			
			if($.browser.msie == true && (content.refer=='UM' || content.refer=='PM')) {
				if(!luxor.isNullOrEmpty(strContentStyle)) {

					
					if($('head style[scope=all]').html() != null) {
						$('head style[scope=all]').remove();
						$('head').append("<style type='text/css' scope='all'>"+strContentStyle+"</style>");
					} else {
						$('head').append("<style type='text/css' scope='all'>"+strContentStyle+"</style>");
					}
				}
				if(!luxor.isNullOrEmpty(strContentStyleForEach)) {
					if($('head style[scope=one]').html() != null) {
						$('head style[scope=one]').remove();
						$('head').append("<style type='text/css' scope='one'>"+strContentStyleForEach+"</style>");
					} else {
						$('head').append("<style type='text/css' scope='one'>"+strContentStyleForEach+"</style>");
					}
					
				}
			} 
			
			//자동높이 설정ID 초기화
			content.autoHeightContentId = '';
			for(var i=0; i < contents.length; i++) { // Step 2
				if(contents[i].isDeleted =='Y') {
					content.loadDone();
					continue;
				}
				if((content.refer != "PM" && content.refer != "CM") && contents[i].regUserId == 'ADMIN' && (contents[i].zoneId!='header' && contents[i].zoneId!='footer')) {
					if(personalizedContents.indexOf(contents[i].contentId) > -1) {
						content.loadDone();
						continue;
					}
				}
				if(typeof(contents[i].contentStyle)=='undefined') {
					contents[i].contentStyle = '';
				}
				if(typeof(contents[i].contentStyleForEach)=='undefined') {
					contents[i].contentStyleForEach = '';
				}
				
				var zoneId = contents[i].zoneId;
				var contentId = contents[i].contentId;
				var contentName = contents[i].messageName;
				if(contentName.indexOf('&amp;') > -1) contentName = contentName.replace('&amp;','&');	
				var contentStyle = contents[i].contentStyle.replace(/\[contentId\]/g,'#'+contentId);
				var contentStyleForEach = contents[i].contentStyleForEach.replace(/\[contentId\]/g,'#'+contentId);
				var contentRegUserId = contents[i].regUserId;
				var portletId = contents[i].portletId;
				var portalId = contents[i].portalId;
				
				// 자동높이 여부 체크
				if(contents[i].isAutoHeight=='Y') {
					content.autoHeightContentId = contentId;
					content.isAutoHeight = true;
				}
				
				content.cache.put(contentId, portletId);
				if(contents[i].isFixed=="Y") {
					$('#'+zoneId+' > ul[type=fixed]').append(data); // Step 3
					$('#'+zoneId+' > ul[type=fixed] > li').last().attr('id', contentId); // Step 4
					$('#'+zoneId+' > ul[type=fixed] > li').last().attr('portalId', portalId);
					$('#'+zoneId+' > ul[type=fixed] > li .content-top').last().attr('portalId', portalId);
				} else {
					$('#'+zoneId+' > ul[type=unfixed]').append(data); // Step 3
					$('#'+zoneId+' > ul[type=unfixed] > li').last().attr('id', contentId); // Step 4
					$('#'+zoneId+' > ul[type=unfixed] > li').last().attr('portalId', portalId);
					$('#'+zoneId+' > ul[type=unfixed] > li .content-top').last().attr('portalId', portalId);
				}
				if(content.refer=='PM' && portalId == currentPortalId && selectedPortalId != currentPortalId) {
					content.setTitleWithPortalName(contentId, contentName, currentPortalName); // Step 6
				} else {
					content.setTitle(contentId, contentName); // Step 6
				}
				
				if($.browser.msie != true || (content.refer!='UM' && content.refer!='PM')) {
					if(!luxor.isNullOrEmpty(contentStyle)) {
						$('head').append("<style type='text/css' scope='all'>"+contentStyle+"</style>");
					}
					if(!luxor.isNullOrEmpty(contentStyleForEach)) {
						$('head').append("<style type='text/css' scope='one'>"+contentStyleForEach+"</style>");
					}
				} 
				
				//1.컨텐츠 관리에서 컨텐츠의 테두리를 지웠을 경우 class를 바꾼다
				//2.페이지 관리에서 컨텐츠의 테두리를 지웠을 경우 class를 바꾼다
				if(contentStyle.indexOf('content-wrap-hide')>-1) {
					$('#'+contentId).removeClass('content-wrap').addClass('content-wrap-hide');
					if(content.refer=='CM') {
						$('ul > #'+contentId).removeClass('content-wrap').addClass('content-wrap-hide');
						$('#'+contentId).attr('style','');
					}
				}
				if(content.refer!='CM') {
					if(contentStyleForEach.indexOf('content-wrap-hide')>-1) {
						$('#'+contentId).removeClass('content-wrap').addClass('content-wrap-hide');
					} else if(contentStyleForEach.indexOf('content-wrap')>-1) {
						$('#'+contentId).removeClass('content-wrap-hide').addClass('content-wrap');
					}
				}
				if(parent.targetFrame) {
					$('#'+contentId+'.content-wrap-hide').attr('style','');
				}
				
				// title및 menu button 체크
				content.autoCollapseTitleArea(contentId);
				
				if(content.opt.draggable) { // Step 8
					if(currentPortalId != selectedPortalId) {
						//$('#'+contentId+' .content-top-div[portalId='+currentPortalId+']').css('cursor','move');
					} else {
						//$('#'+contentId+' .content-top-div').css('cursor','move');
					}
					
					if(content.refer=='UM') {
						// 사용자화면에서 Drag & Drop에서 관리자가 Fix한 컨텐츠는 위치 고정
						if(contents[i].isFixed=="Y") {
							$('#'+contentId).attr("isFixed","Y");
							$('#'+contentId+' .content-top-div').css('cursor','default');
						}
					}
				}
				
				// 컨텐츠 Body (포틀릿 호출)
				if(content.opt.showBody) content.reload(contents[i], contentId); // Step 7
				if(!content.opt.showTitle) $('#'+contentId+" .content-title-bar").hide();
				
				// 버튼 셋팅 (정의된 버튼만 보여줌)
				content.setMenuList(content.opt.showMenuList, contentId, contents[i].portalId);

				// 개인화 설정된 페이지 관리에서는 관리자가 위치 고정(Fix) 여부를 선택할 수 있는 압정 아이콘 추가
				if(content.refer=='PM' && $('#isPersonal').val()=='Y') {
					//현재 포탈ID와 선택된 페이지/컨텐츠의 포탈 ID를 비교, 다른 포탈 페이지라면 fix사용금지
					if(currentPortalId == selectedPortalId) {
						var __icon = contents[i].isFixed=="Y" ? "ico_tack_r.gif" : "ico_tack2_r.gif";
						var __tooltip = contents[i].isFixed=="Y" ? "Fixed" : "Non-fixed";
						var __setValue = contents[i].isFixed=="Y" ? "N" : "Y";
						$('#body #'+contentId+" .content-menu-list").prepend(
								"<li onclick=\"content.setFixed('"+contentId+"','"+__setValue+"');return false;\">"
							   +"<img src='/ep/luxor/ref/image/admin/"+__icon+"' alt='"+__tooltip+"' title='"+__tooltip+"'/></li>");
					}
				}
				
				// 포틀릿쪽 함수 호출
				content.callPorltetFunc(contentId, "load");
			} // END FOR


			// preview==true인 경우, 페이지 관리에서는 메뉴바를 숨김으로 하더라도
			// 설정, 삭제 버튼은 기본적으로 보여줘야하기 때문에 다시 보이게 셋팅
			// 타이틀바도 보여줌
			if(content.opt.preview==true || content.refer == "CM") {
				$('.content-title-bar').each(function() {
					if($(this).css('display')=='none') {
						$(this).css('color','#b2b2b3').show();
					}
				});
				$('.content-menu-bar').show();
			}
			
			// 컨텐츠 드래그하여 컨텐츠간 순서조정 셋팅
			if(content.opt.draggable) { // Step 8
				// 컨텐츠 테두리가 없는 경우에는 Drag할 포인트가 없기때문에
				// 마우스 오버 되었을때 임의로 테두리를 보이게해서 Drag할수 있게 해줌
				// 마우스 아웃되면 다시 테두리 제거함
				if(content.refer=='UM') {
					$('#body li.content-wrap-hide').live('mouseover', function(e) {
						// 컨텐츠 추가 버튼 옆의 edit 모드로 변경시에만 활성화 함
						if(content.refer=='DD' && $(this).attr('isfixed') != 'Y') {
							var hoveredContentId = $(this).attr('id');
							if($('#'+hoveredContentId+' .content-title-bar').css('display')=='none') {
								$(this).removeClass('content-wrap-hide');
								$(this).addClass('content-wrap');
								$('#'+hoveredContentId+' .content-top-div').addClass('content-top');
								$('#'+hoveredContentId+' .content-top-div').css('cursor','move');
								
								$('#'+hoveredContentId).live('mouseout', function(e) {
									$(this).removeClass('content-wrap');
									$(this).addClass('content-wrap-hide');
									$('#'+hoveredContentId+' .content-top-div').removeClass('content-top');
								});
							}
						}
					});
					
					$('#body li.content-wrap').live('mouseover', function(e) {
						// 컨텐츠 추가 버튼 옆의 edit 모드로 변경시에만 활성화 함
						if(content.refer=='DD' && $(this).attr('isfixed') != 'Y') {
							var hoveredContentId = $(this).attr('id');
							if($('#'+hoveredContentId+' .content-title-bar').css('display')=='none') {
								$('#'+hoveredContentId+' .content-top-div').addClass('content-top');
								$('#'+hoveredContentId+' .content-top-div').css('cursor','move');
								
								$('#'+hoveredContentId).live('mouseout', function(e) {
									$('#'+hoveredContentId+' .content-top-div').removeClass('content-top');
								});
							}
						}
					});
				}
				if(content.refer != 'DD' && content.refer != 'UM') {
					doSort();
				} 
			} // END of if(content.opt.draggable)
			
			data = null;
		}); // END $.get.. Step 1
	},

	maximized: new Array(), 
	minimized: new Array(),
	
	/**
	 * 최대화 버튼 클릭시 호출
	 */
	max: function(obj, contentId) {
		if(this.opt.preview==true) {
			return false;
		}
		
		$('.luxor-body').attr('style','margin-left:0px;margin-right:0px');
		
		// 최대화는 body영역에서만 됨 (header, footer에서는 최대화 기능 동작안함)
		if(typeof($(obj).closest('#body').attr('class'))=='undefined') {
			alert('Maximize only allowed on body');
			return false;
		}
		
		$('div#wrap').append($('div#body').clone().attr('id','body_cloned').hide());
		$('div#body').html($('#'+contentId));
		var _h = $(window).height() - ($('#header').outerHeight(true) 
				+ $('#footer').outerHeight(true) + $('#'+contentId+' div .content-top-div').outerHeight(true));
	
		$('div #body #'+contentId+' .content-body').height(_h-55);
		$(obj).siblings('[mode=normal]').css('display','inline');
		
		this.cache.put(contentId+'max', 'inline');
		if($(obj).siblings('[mode=min]').css('display')=='none') {
			this.cache.put(contentId+'min', 'none');
		} else {
			$(obj).siblings('[mode=min]').hide();
			this.cache.put(contentId+'min', 'inline');
		}
		
		$(obj).hide();
		this.maximized.push(contentId);
		
		// 포틀릿쪽 함수 호출
		this.callPorltetFunc(contentId, "maximize");
		
		this.isMax = true;
		
		this.reload(obj, contentId);
	},

	/**
	 * 최소화 버튼 클릭시 호출
	 */
	min: function(obj, contentId) {
		if(this.opt.preview==true) {
			return false;
		}
		
		$('#'+contentId+' div.content-body').slideUp('fast');
		$(obj).siblings('[mode=normal]').css('display','inline');
		
		this.cache.put(contentId+'min', 'inline');
		if($(obj).siblings('[mode=max]').css('display')=='none') {
			this.cache.put(contentId+'max', 'none');
		} else {
			$(obj).siblings('[mode=max]').hide();
			this.cache.put(contentId+'max', 'inline');
		}
		
		$(obj).hide();
		this.minimized.push(contentId);
		
		// 포틀릿쪽 함수 호출
		this.callPorltetFunc(contentId, "minimize");
		
	},

	/**
	 * 최대화/최소화 상태에서 원래대로 버튼 클릭시 호출됨
	 */
	normal: function(obj, contentId) {
		if(this.opt.preview==true) {
			return false;
		}
		
		$('.luxor-body').attr('style','');
		
		$('div #body #'+contentId+' .content-body').slideDown('fast');
		if(this.maximized.indexOf(contentId) > -1) {
			$('div #body').html($('div #body_cloned').html());
			$('div #body_cloned').remove();
			this.maximized.remove(contentId);
		}
		if(this.minimized.indexOf(contentId) > -1) {
			this.minimized.remove(contentId);
		}
		
		$(obj).siblings('[mode=min]').css('display', this.cache.get(contentId+'min'));
		$(obj).siblings('[mode=max]').css('display', this.cache.get(contentId+'max'));
		$(obj).hide();
		
		// 포틀릿쪽 함수 호출
		this.callPorltetFunc(contentId, "normalize");
		
		if(this.isMax==true) {
			this.isMax = false;		
			this.reload(obj, contentId);
		}
	},
	
	go: function(obj, contentId) {
		if(this.opt.preview==true) {
			return false;
		}

		$(obj).siblings('[mode=edit]').css('display','inline');
		$(obj).hide();

		var url = this.getPortletGoUrl(this.getPortletId(contentId));
		this.reload(obj, contentId, url);

		// 포틀릿쪽 함수 호출
		this.callPorltetFunc(contentId, "go");
	},

	edit: function(obj, contentId) {
		if(this.opt.preview==true) {
			return false;
		}

		$(obj).siblings('[mode=go]').css('display','inline');
		$(obj).hide();

		var url = this.getPortletEditUrl(this.getPortletId(contentId));
		this.reload(obj, contentId, url);
		
		// 포틀릿쪽 함수 호출
		this.callPorltetFunc(contentId, "edit");
	},

	/**
	 * 새로고침 버튼 클릭시 포틀릿 재로딩
	 */
	reload: function(obj, contentId, url) {
		var portletId = this.getPortletId(contentId);
		
		// show loading
		luxor.loading($('#'+contentId+' div.content-body'), {type:'circle', size:'m', caption:"<div style='margin-top:10px;'>Loading...</div>"});
		
		// Portlet Ajax call URL setting
		var callUrl = this.getPortletViewUrl(portletId);
		
		if(!luxor.isNullOrEmpty(url)) {
			callUrl = url;
		}
		
		var additionalParam = "contentId="+contentId;
		if(obj !=null && obj.contentStyle != null){
			minHeight = content.getMinHeight(obj.contentStyle.replace(/\[contentId\]/g,'#'+contentId));
			additionalParam+="&minHeight="+minHeight;
		}

		//getPortlet.jsp에 contentId를 전달하기 위한 추가사항
		callUrl = callUrl.indexOf("?") > 0 ? callUrl+"&"+additionalParam : callUrl+"?"+additionalParam;

		// Ajax call
		$.ajax({
			type: 'post',
			url: callUrl,
			success: function(data, textStatus, XMLHttpRequest) {
				// close loading
				luxor.loadingClose($('#'+contentId+' div.content-body'));
				$('#'+contentId+' div.content-body').html(data);

				// 페이지를 찾을수 없는 경우 (404 Not Found)
				if( $('#RESPONSE_CODE').val()=='404' ) {
					var _message = content.opt.preview ? portal_alert_msg_14 : portal_alert_msg_15;
					if(content.opt.preview) {
						$('#'+contentId+' div.content-body').html(_message);
					} else {
						$('#'+contentId+' div.content-body').html(_message);
					}
				}
				// 오류가 발생한 경우 (500 Internal Error)
				if( $('#RESPONSE_CODE').val()=='500' ) {
					$('#'+contentId+' div.content-body').html(portal_alert_msg_9);
				}
				
				// load함수의 Step 9 (각 zone의 height를 셋팅함)
				// 로딩이 완료된 후라도 CSS가 적용되는데 미세한 시간차이가 날수있으므로
				// 0.5초의 간격을 두고 CSS가 다 적용된 후 최종적인 Height값을 셋팅함
				if(content.isMax==false) {
					setTimeout(function() {
							content.adjustZoneHeight('left');
							content.adjustZoneHeight('content');
							content.adjustZoneHeight('right');
					}, 500);
				}
				
				// 포틀릿쪽 함수 호출
				content.callPorltetFunc(contentId, "refresh");
				
				// Javascript & CSS load
				if(content.opt.preview==false) {
					content.loadPortletJs(portletId);
				}
				
				content.loadPortletCss(portletId);
				content.loadDone();
				
				data = null;
			},
			error: function (xhr, ajaxOptions, thrownError) {
				luxor.loadingClose($('#'+contentId+' div.content-body'));
				var _message = content.opt.preview ? portal_alert_msg_14 : portal_alert_msg_15;
				$('#'+contentId+' div.content-body').html(_message);
				
				content.loadDone();
			}
		});
	},

	/**
	 * 삭제 버튼 클릭시
	 */
	del: function(obj, contentId) {
		if(confirm(portal_alert_msg_12)) {
			$('#'+contentId).remove();
			callJson("portalPageZoneController", "deleteContentOnZone", "pageId="+selectedNodeId+"&contentId="+contentId);
		}
	},

	/**
	 * 설정 버튼 클릭시 스타일 매니저 오픈
	 */
	setup: function(obj, contentId) {
		// 이미 열려진 경우에는 닫기
		if($('#styleManager').css('display')=='block') {
			//return false;
		}
		
		var pos = $(obj).offset();
		
		// 새로 열기
		$('#styleManager').attr('contentId',contentId);
		$('#styleManager').dialog({position:[pos.left-230, pos.top+10]});
		$('#styleManager').dialog('open');
		
		// 페이지관리에서는 제목 수정할 수 없음 // 자동 높이조절 셋팅
		if(content.refer=='PM') {
			$('#styleManager #contentName').hide();
			$('#csmButtonGroup #initButton').show();
			$('#csmButtonGroup #cancelButton').hide();
			
			var zoneId = content.getZoneId(contentId);
			//자동 높이조절은 body레이어에 존에 하나의 컨텐츠만 존재할경우 세팅 가능하도록 제약
			if((luxor.isNullOrEmpty(content.autoHeightContentId) || contentId==content.autoHeightContentId) 
					&& content.loadedContentCountInEachZone(zoneId)==1
					&& zoneId!="header" && zoneId!="footer") {
				$('#styleManager #autoheight_alert').hide();
				$('#styleManager #autoheight').show();
			} else if((luxor.isNullOrEmpty(content.autoHeightContentId) || contentId!=content.autoHeightContentId) 
					&& content.loadedContentCountInEachZone(zoneId)==1
					&& zoneId!="header" && zoneId!="footer") { 
				$('#styleManager #autoheight_alert').show();
				$('#styleManager #autoheight_alert td').html(content.getTitle(content.autoHeightContentId)+portal_label_205);
				$('#styleManager #autoheight').hide();
			} else {
				$('#styleManager #autoheight').hide();
			}
			
		} else {
			$('#csmButtonGroup #initButton').hide();
			$('#csmButtonGroup #cancelButton').show();
		}
		
		// initialize
		content.style.init(contentId);
		
		// refresh button set
		$("#autoheight_radio").buttonset();
		$("#titlebar_radio").buttonset();
		$("#border_radio").buttonset();
		$("#max_radio").buttonset();
		$("#min_radio").buttonset();
		$("#help_radio").buttonset();
		$("#edit_radio").buttonset();
		$("#reload_radio").buttonset();
	},

	help: function(obj, contentId) {
		//포틀릿 edit메뉴 클릭시 팝업으로 띄울경우
		if(this.opt.preview==true) {
			return false;
		}
		var url = this.getPortletHelpUrl(this.getPortletId(contentId));
		
		if(helpButtonPopup == 'Y') {
			this.popup(obj, contentId, url);
		} else {
			this.reload(obj, contentId, url);
		}
		// 포틀릿쪽 함수 호출
		this.callPorltetFunc(contentId, "help");
	},
	/**
	 * 포틀릿 메뉴 클릭시 팝업으로 띄우는 경우
	 */
	popup: function(obj, contentId, url) {
		var portletId = this.getPortletId(contentId);
		var callUrl = this.getPortletViewUrl(portletId);
		if(!luxor.isNullOrEmpty(url)) {
			callUrl = url;
		}
		var options = helpPopupOption.split(',');
		var screenW = screen.availWidth;
  		var screenH = screen.availHeight;
  		var windowW = '600';
  		var windowH = '500';
  		
		for(var i = 0 ; i<options.length ; i++ ) {
			if(options[i].indexOf('width') > -1) {
				windowW = options[i].replace('width','');
				windowW = windowW.replace('px','');
				windowW = windowW.replace('=','');
			} 
			if(options[i].indexOf('height') > -1) {
				windowH = options[i].replace('height','');
				windowH = windowH.replace('px','');
				windowH = windowH.replace('=','');
			}
		}
		
  		var left = (screenW - windowW)/2;
  		var top = (screenH - windowH)/2;
  		
		var openedWinObj = window.open(callUrl,"help_"+portletId,"left="+left+",top="+top+","+helpPopupOption);
  		openedWinObj.focus();
	},
	
	
	/**
	 * 컨텐츠 박스의 스타일부분
	 * 호출예 : content.style.func()
	 */
	style: {
		wyswyg: false,
		minHeight:70,
		
		/**
		 * 스타일 설정 화면이 로딩될때 호출되어서 
		 * 기존에 정의된 스타일을 미리 셋팅함
		 */
		init: function(contentId) {
			// 높이
			$('#content_height').val($('#'+contentId+' .content-body').height());
			// 자동높이
			if(contentId==content.autoHeightContentId) {
				$('#autoheight_r1').attr('checked','checked');
				$('#autoheight_r2').removeAttr('checked');
				$('#isAutoHeight').val('Y');
			} else {
				$('#autoheight_r1').removeAttr('checked');
				$('#autoheight_r2').attr('checked','checked');
				$('#isAutoHeight').val('N');
			}
			// 제목
			if( $('#'+contentId+' .content-title-bar').css('color')=='#b2b2b3' || $('#'+contentId+' .content-title-bar').css('color')=='rgb(178, 178, 179)') {
				$('#titlebar_r1').removeAttr('checked');
				$('#titlebar_r2').attr('checked','checked');
			} else {
				$('#titlebar_r1').attr('checked','checked');
				$('#titlebar_r2').removeAttr('checked');
			}
			// 메뉴
			if( $('#'+contentId+' .content-menu-bar').css('display')=='none' ) {
				$('#menu_r1').removeAttr('checked');
				$('#menu_r2').attr('checked','checked');
			} else {
				$('#menu_r1').attr('checked','checked');
				$('#menu_r2').removeAttr('checked');
			}
			// 테두리
			if( $('ul #'+contentId).attr('class').indexOf('content-wrap-hide')>-1) {
				$('#border_r1').removeAttr('checked');
				$('#border_r2').attr('checked','checked');
			} else {
				$('#border_r1').attr('checked','checked');
				$('#border_r2').removeAttr('checked');
			}
			// 최대화
			if( $('#'+contentId+' .menu-btn-max').css('display')=='none' ) {
				$('#max_r1').removeAttr('checked');
				$('#max_r2').attr('checked','checked');
			} else {
				$('#max_r1').attr('checked','checked');
				$('#max_r2').removeAttr('checked');
			}
			// 최소화
			if( $('#'+contentId+' .menu-btn-min').css('display')=='none' ) {
				$('#min_r1').removeAttr('checked');
				$('#min_r2').attr('checked','checked');
			} else {
				$('#min_r1').attr('checked','checked');
				$('#min_r2').removeAttr('checked');
			}
			// 새로고침
			if( $('#'+contentId+' .menu-btn-reload').css('display')=='none' ) {
				$('#reload_r1').removeAttr('checked');
				$('#reload_r2').attr('checked','checked');
			} else {
				$('#reload_r1').attr('checked','checked');
				$('#reload_r2').removeAttr('checked');
			}
			// 수정
			if( $('#'+contentId+' .menu-btn-edit').css('display')=='none' ) {
				$('#edit_r1').removeAttr('checked');
				$('#edit_r2').attr('checked','checked');
			} else {
				$('#edit_r1').attr('checked','checked');
				$('#edit_r2').removeAttr('checked');
			}
			// 도움말
			if( $('#'+contentId+' .menu-btn-help').css('display')=='none' ) {
				$('#help_r1').removeAttr('checked');
				$('#help_r2').attr('checked','checked');
			} else {
				$('#help_r1').attr('checked','checked');
				$('#help_r2').removeAttr('checked');
			}
		},
		
		/**
		 * 스타일 편집시 저장버튼 클릭시 바로바로 DB에 저장할지 
		 * 아니면 임시로 저장하고 페이지 전체가 submit될때 저장할지 여부
		 * wyswyg가 true이면 바로바로 저장하고, false이면 submit 될때 저장함
		 */
		setWyswyg: function(bool) {
			this.wyswyg = bool;
		},

		title: function(obj, contentId) {
			if($(obj).val()=='off') {
				$('#'+contentId+' .content-title-bar').css('color','#b2b2b3');
			} else {
				$('#'+contentId+' .content-title-bar').css('color','');
			}
		},

		setHeight: function(obj, contentId) {
			if($(obj).val()!='' && isNaN($(obj).val())) {
				alert('Number Only.');
				$(obj).val(content.style.minHeight);
			}
			$('#'+contentId+' .content-body').height($(obj).val()+'px');
			content.style.minHeight = $(obj).val();
		},

		autoheight: function(obj, contentId) {
			if($(obj).val()=='off') {
				$('#isAutoHeight').val('N');
			} else {
				$('#isAutoHeight').val('Y');
			}
		},

		border: function(obj, contentId) {
			if($(obj).val()=='off') {
				$('ul #'+contentId).removeClass('content-wrap');
				$('ul #'+contentId).addClass('content-wrap-hide');
			} else {
				$('ul #'+contentId).removeClass('content-wrap-hide');
				$('ul #'+contentId).addClass('content-wrap');
			}
		},
		
		menuMax: function(obj, contentId) {
			if($(obj).val()=='off') {
				$('#'+contentId+' .menu-btn-max').hide();
			} else {
				$('#'+contentId+' .menu-btn-max').css('display','inline');
			}
		},

		menuMin: function(obj, contentId) {
			if($(obj).val()=='off') {
				$('#'+contentId+' .menu-btn-min').hide();
			} else {
				$('#'+contentId+' .menu-btn-min').css('display','inline');
			}
		},

		menuReload: function(obj, contentId) {
			if($(obj).val()=='off') {
				$('#'+contentId+' .menu-btn-reload').hide();
			} else {
				$('#'+contentId+' .menu-btn-reload').css('display','inline');
			}
		},

		menuEdit: function(obj, contentId) {
			if($(obj).val()=='off') {
				$('#'+contentId+' .menu-btn-edit').hide();
			} else {
				$('#'+contentId+' .menu-btn-edit').css('display','inline');
			}
		},

		menuHelp: function(obj, contentId) {
			if($(obj).val()=='off') {
				$('#'+contentId+' .menu-btn-help').hide();
			} else {
				$('#'+contentId+' .menu-btn-help').css('display','inline');
			}
		}, 
		
		/**
		 * 스타일 에디터에서 저장버튼 클릭시 선택된 스타일 정보를 contentStyle이라는 id를 가지는 input에 저장
		 * contentStyle을 사용하는 페이지에서는 <form></form>안에 다음의 input 태그가 있어야함
		 * <input type="hidden" id="contentStyle" name="contentStyle" />
		 */
		save: function(obj, contentId) {
			var css = '';
			// 테두리 스타일
			if($('ul #'+contentId).attr('class').indexOf('content-wrap-hide')>-1) {
				$('ul #'+contentId).removeClass('content-wrap').addClass('content-wrap-hide');
				css += '[contentId] .'+$('ul #'+contentId).attr('class')+'{}';
			} else {
				$('ul #'+contentId).removeClass('content-wrap-hide').addClass('content-wrap');
				css += '[contentId] .'+$('ul #'+contentId).attr('class')+'{}';
			}
			
			// 각 버튼(max,min,reload,etc)에 대한 스타일
			$('#'+contentId+' li[mode]').each(function() {
				if($(this).attr('mode')=='del' || $(this).attr('mode')=='setup') {
					return true; // continue;
				}
				css += '[contentId] .'+$(this).attr('class')+'{display:'+$(this).css('display')+';} ';
			});
			// 각 영역(타이틀바, 메뉴바)에 대한 스타일
			$('#'+contentId+' .content-title-bar').each(function() {
				if($('#'+contentId+' .content-title-bar').css('color')=='#b2b2b3' || $('#'+contentId+' .content-title-bar').css('color')=='rgb(178, 178, 179)'){
					css += '[contentId] .'+$(this).attr('class')+'{display:none;} ';
				}else{
					css += '[contentId] .'+$(this).attr('class')+'{display:block;} ';
				}
			});
			// 각 영역(타이틀바, 메뉴바)에 대한 스타일
			$('#'+contentId+' .content-menu-bar').each(function() {
				css += '[contentId] .'+$(this).attr('class')+'{display:'+$(this).css('display')+';} ';
			});
			// 높이값
			if($('#content_height').val() > 0) {
				css += '[contentId] .content-body{min-height:'+$('#content_height').val()+'px;} ';
			}
			
			$('#contentStyle').val(css);
			if(this.wyswyg) {
				var zoneId = content.getZoneId(contentId);
				var param = 'pageId='+selectedNodeId+'&zoneId='+zoneId+'&contentId='+contentId
							+'&contentStyleForEach='+css+'&isAutoHeight='+$('#isAutoHeight').val();
				callJson('portalPageZoneController','updateContentStyle', param, function(data) {
					//다른 포탈 페이지에서 컨텐츠 스타일 설정이 일어난 경우는 포탈ID전송
					if(content.refer=='PM') {
						document.location.href = '/ep/page/manager.do?nodeId='+selectedNodeId+'&selectedPortalId='+selectedPortalId;
					} else {
						document.location.href = '/ep/page/manager.do?nodeId='+selectedNodeId;
					}
				});
			} else {
				$("#styleManager").dialog('close');
			}
		},
		
		cancel: function() {
			$("#styleManager").dialog('close');
		},

		/**
		 * 설정된 스타일 초기화
		 * wyswyg가 true인 경우(페이지관리)는 각 컨텐츠 자체의 스타일
		 * false인 경우(컨텐츠관리)는 컨텐츠 스타일을 초기화함
		 * 
		 * 페이지관리에서 초기화하면 페이지관리에서 따로 설정된 스타일만 초기화되며
		 * 컨텐츠관리에서 초기화하면 모든 페이지에서 해당 컨텐츠의 스타일이 초기화됨 
		 */
		reset: function(obj, contentId) {
			var scope = this.wyswyg ? 'one' : 'all';
			var resetStyle = "";
			$('style[scope=all]').each(function() {
				if($(this).text().indexOf(contentId) > -1) {
					resetStyle = $(this).text();
				}
			});
			
			if(resetStyle.indexOf(".content-title-bar{display:none;}")>-1) {
				$('#'+contentId+' .content-title-bar').css('color','#b2b2b3');
			} else {
				$('#'+contentId+' .content-title-bar').css('color','');
			}
			
			if(resetStyle.indexOf(".content-wrap-hide{}")>-1) {
				$('ul #'+contentId).removeClass('content-wrap').addClass('content-wrap-hide');
			} else {
				$('ul #'+contentId).removeClass('content-wrap-hide').addClass('content-wrap');
			}
			
			if(resetStyle.indexOf(".menu-btn-max{display:none;}")>-1) {
				$('#'+contentId+' .menu-btn-max').hide();
			} else {
				$('#'+contentId+' .menu-btn-max').css('display','inline');
			}
			
			if(resetStyle.indexOf(".menu-btn-min{display:none;}")>-1) {
				$('#'+contentId+' .menu-btn-min').hide();
			} else {
				$('#'+contentId+' .menu-btn-min').css('display','inline');
			}
			
			if(resetStyle.indexOf(".menu-btn-reload{display:none;}")>-1) {
				$('#'+contentId+' .menu-btn-reload').hide();
			} else {
				$('#'+contentId+' .menu-btn-reload').css('display','inline');
			}
			
			if(resetStyle.indexOf(".menu-btn-edit{display:none;}")>-1) {
				$('#'+contentId+' .menu-btn-edit').hide();
			} else {
				$('#'+contentId+' .menu-btn-edit').css('display','inline');
			}
			
			if(resetStyle.indexOf(".menu-btn-help{display:none;}")>-1) {
				$('#'+contentId+' .menu-btn-help').hide();
			} else {
				$('#'+contentId+' .menu-btn-help').css('display','inline');
			}
			
			this.init(contentId);
			
			$("#titlebar_radio").buttonset();
			$("#border_radio").buttonset();
			$("#max_radio").buttonset();
			$("#min_radio").buttonset();
			$("#help_radio").buttonset();
			$("#edit_radio").buttonset();
			$("#reload_radio").buttonset();
			
			content.isModified=true;
		}
	}
};

$(document).ready(function() {
	// 메뉴바의 버튼 클릭시 각 액션 핸들러 호출
	$('div.content-menu-bar li').live('click', function() {
		var contentId = $(this).closest('.content-wrap').attr('id');
		if(contentId==null || contentId=="") {
			contentId = $(this).closest('.content-wrap-hide').attr('id');
		}
		if($(this).attr('mode')!=null) {
			eval("content."+$(this).attr('mode')+"($(this), '"+contentId+"')");
		}
		return false;
	});
	
	// 스타일 관리에서 각 액션 핸들러 호출
	$('#styleManager :radio').live('click', function() {
		var contentId = $('#styleManager').attr('contentId');
		eval("content.style."+$(this).attr('name')+"($(this), '"+contentId+"')");
		content.isModified=true;
		return false;
	});
	
	// 스타일 관리에서 초기화, 저장, 취소 클릭시
	$('#csmButtonGroup a').live('click', function() {
		var contentId = $('#styleManager').attr('contentId');
		eval("content.style."+$(this).attr('mode')+"($(this), '"+contentId+"')");
		return false;
	});
	
	// 스타일 에디터에서 높이값 입력시 핸들러 호출
	$('#content_height').live('keyup', function() {
		var contentId = $('#styleManager').attr('contentId');
		content.style.setHeight($(this), contentId);
		content.isModified=true;
	});
});

//target zone으로 iframe을 설정하여 포탈 페이지를 호출하는 경우 높이 컨트롤하기
//iframe 내부에 포탈 페이지가 호출되면, 아래 함수들이 계층구조로 stack에 쌓이게 됨
//1. 자동높이가 설정되면, 최초 window 높이를 기억하고 있다가, 자동높이설정시 자신의 window높이 말고 부모의 window 높이를 태워야함. (initWindowHeight)
//1-1. 브라우저 창크기 조절 이벤트가 발생하면 최초 window높이값을 변경해주고, 이미 내부 iframe이 생성되어있다면, 그 iframe에 설정되어있는 window높이값도 변경
//1-2. 호출된 페이지가 자동높이 설정이 되어있다면, 자신을 호출한 상위페이지의 자동높이설정을 on으로 바꿔줌
var isIframeAutoHeight = false;
$(window).resize(function() {
	//브라우저 창 크기가 변경되었을때는 initWindowHeight의 높이를 다시 세팅
	var parentTargetId = parent.$('.luxor-body iframe[id*=body_]').attr('id');
	if(parentTargetId) {
		initWindowHeight = $(window).height() - $('.luxor-top').outerHeight(true) - $('.luxor-footer').outerHeight(true);
	}
	
	//호출된 IFRAME 내부의 content.js만 반응하기 위한 검증
	//parent에서 호출한 현재 페이지가 autoheight가 걸려있는지 여부 확인해서 걸려있으면, window height를 먹여줌
	if($('#'+parent.targetFrame).html() != null && isIframeAutoHeight) {
		//상위 페이지에게도 autoheight on 작업 수행시켜줌
		parent.setTargetFrameHeight(0, isIframeAutoHeight);
	}
	if(content.needResize==true) {
		//현재 컨텐츠 리사이징작업 -> 부분 zone iframe리프레시 기능을 쓰지 않으면 이 아래 로직만 사용됨
		content.adjustBodyHeight();
		var maxZoneHeight = Math.max(content.getZoneHeight('left'), content.getZoneHeight('content'), content.getZoneHeight('right'));
		$('div.luxor-left > ul[type=unfixed]').height(maxZoneHeight);
		$('div.luxor-content > ul[type=unfixed]').height(maxZoneHeight);
		$('div.luxor-right > ul[type=unfixed]').height(maxZoneHeight);
	}
});

//현재 페이지에 설정된 iframe 변수id
var targetFrame = null;
//현재 페이지에 설정된 자동높이이 브라우저 높이를 저장해높은 변수
var initWindowHeight = null;
//부분 zone iframe리프레시 기능수행
function targetFrameLoad(url, nodeId, targetZone, portletZone) {
	//side에서 body를 호출한경우 자신이 부분호출된 페이지에 있다면 자기를 덮고있는 body iframe을 변경하고,
	//body iframe없는 상태라면 바로 iframe만들어 호출
	var parentTargetId = parent.$('.luxor-body iframe[id*=body_]').attr('id');
	if(parentTargetId == null) {
		initWindowHeight = $(window).height() - $('.luxor-top').outerHeight(true) - $('.luxor-footer').outerHeight(true);
	}
	//side에서 body를 타겟으로 링크를 태우면 통 프레임 호출과 같은 기능 수행(자기zone도 바뀌어버리므로)
	if(portletZone == 'side' && parentTargetId && targetZone  == 'body') {
		parent.targetFrameLoad(url, nodeId, targetZone, 'header');
	} 
	targetFrame = targetZone+"_"+nodeId;
	var targetId = $('.luxor-body iframe[id*=body_]').attr('id');
	
	//body속에서 다시 body를 호출하는 경우에는 통리프레시한다.
	if(targetId != null && targetZone != 'body') {
		document.getElementById(targetId).contentWindow.targetFrameLoad(url, nodeId, targetZone, 'header');
	} else {
		document.getElementById(targetZone).innerHTML = "<iframe id='"+targetFrame+"' src='' class="+nodeId+" style='overflow:hidden;width:100%;' frameborder='0'></iframe>";
		if($.browser.msie==true) {
			setTimeout(function() {
				document.getElementById(targetFrame).src = url;
			},500);  
		} else {
			document.getElementById(targetFrame).src = url;
		}
		if($.browser.msie==true) {
			var bodyHeight = $(window).height() - $('.luxor-top').outerHeight(true) - $('.luxor-footer').outerHeight(true);
			$('#'+targetFrame).height(bodyHeight);
		}
		//IE는 파싱엔진이 jquery핸들링을 못따라감..
		if($.browser.msie==true) {
		} else {
			$('.luxor-footer').addClass('visibility');
			$('.luxor-'+targetZone).addClass('visibility');
			if(parentTargetId) {
				parent.$('.luxor-footer').addClass('visibility');
				parent.$('.luxor-'+targetZone).addClass('visibility');
			}
		}
	}
}

//부분 zone iframe리프레시 기능 수행하여 loaddone 이벤트 발생시 아래 함수가 호출됨
function setTargetFrameHeight(height, isAutoHeight) {
	//부분 리프레시할경우 개인화제거함
	$('#sp_mycontent_from_top').hide();
	
	isIframeAutoHeight = isAutoHeight;
	var targetZone = targetFrame.split("_");
	var parentTargetId = parent.$('.luxor-body iframe[id*=body_]').attr('id');
	if(height == 0) {
		//안에있는 iframe의 높이가 변경되었다면, 그를 감싸고 있는 iframe도 높이 변경 이벤트가 일어나야함
		if(parentTargetId) {
			if(targetZone[0] != 'body') {
				parent.$('.luxor-body iframe[id*=body_]').height(parent.initWindowHeight);
				initWindowHeight = parent.initWindowHeight;
				parent.isIframeAutoHeight = isIframeAutoHeight;
			} 
		}
		
		//자동높이 설정된 iframe을 호출했다면, window사이즈에 맞춰 변경되어야함
		var bodyHeight = $(window).height() - $('.luxor-top').outerHeight(true) - $('.luxor-footer').outerHeight(true);
		$('#'+targetFrame).height(bodyHeight-8);
		height = bodyHeight;
		document.getElementById(targetFrame).contentWindow.$('#content > ul[type=unfixed]').height(bodyHeight-8);
		document.getElementById(targetFrame).contentWindow.$('#left > ul[type=unfixed]').height(bodyHeight-8);
		document.getElementById(targetFrame).contentWindow.$('#right > ul[type=unfixed]').height(bodyHeight-8);
		document.getElementById(targetFrame).contentWindow.$('#left > iframe').height(bodyHeight-8);
		document.getElementById(targetFrame).contentWindow.$('#right > iframe').height(bodyHeight-8);
		document.getElementById(targetFrame).contentWindow.$('#content > iframe').height(bodyHeight-8);
		
	} else {
		//iframe[body]안에있는 iframe의 높이가 변경되었다면, 그를 감싸고 있는 iframe도 높이 변경 이벤트가 일어나야함
		if(parentTargetId && targetZone[0] != 'body' && !isAutoHeight) {
			parent.$('.luxor-body iframe[id*=body_]').height(height);
			parent.isIframeAutoHeight = isIframeAutoHeight;
		} 
		
		if(targetZone[0] != 'body' && !parentTargetId){
			if(parent.$('.luxor-'+targetZone[0]+' iframe[id*='+targetZone[0]+'_]').attr('id')) {
				parent.$('.luxor-'+targetZone[0]+' iframe[id*='+targetZone[0]+'_]').height(height+34);
			}
			$('#'+targetFrame).height(height+17);
		} else {
			$('#'+targetFrame).height(height);
		}
	}
	
	//IE는 파싱엔진이 jquery핸들링을 못따라감..
	if($.browser.msie==true) {
	} else {
		$('.luxor-'+targetZone[0]).removeClass('visibility-hidden');
		$('.luxor-footer').removeClass('visibility-hidden');
		if(parentTargetId) {
			parent.$('.luxor-footer').removeClass('visibility-hidden');
			parent.$('.luxor-'+targetZone[0]).removeClass('visibility-hidden');
		}
	}
	
	var maxZoneHeight = Math.max(content.getZoneHeight('left'), content.getZoneHeight('content'), content.getZoneHeight('right'));
	if(maxZoneHeight != 0 ) {
		$('div.luxor-left > ul[type=unfixed]').height(maxZoneHeight);
		$('div.luxor-content > ul[type=unfixed]').height(maxZoneHeight);
		$('div.luxor-right > ul[type=unfixed]').height(maxZoneHeight);
		$('div.luxor-left > iframe').height(maxZoneHeight);
		$('div.luxor-content > iframe').height(maxZoneHeight);
		$('div.luxor-right > iframe').height(maxZoneHeight);
	}
}
function destroySort() {
	$('#body div[type=zone] > ul[type=unfixed]').sortable('destroy');
	$('#body div[type=zone] > ul[type=unfixed] .content-top').attr('style','cursor:default;');
}
function doSort() {
	$('#body div[type=zone] > ul[type=unfixed] .content-top').attr('style','cursor:move;');
	var dragDone = false;
	var handlingClass = ".content-top";
	if(currentPortalId != selectedPortalId) {
		handlingClass = ".content-top[portalId="+currentPortalId+"]";
	}
	$('#body div[type=zone] > ul[type=unfixed]').sortable('destroy');
	$('#body div[type=zone] > ul[type=unfixed]').sortable({
		items: 'li.content-wrap, li.content-wrap-hide',
		handle: handlingClass,  //핸들링되는 타겟 클래스
		cancel: 'li[isFixed=Y]',
		dropOnEmpty: true,
		connectWith: 'div[type=zone] > ul[type=unfixed]',
		placeholder: 'ui-state-highlight2',
		tolerance: 'pointer',
		scroll: false,
		
		// 드래그가 시작되면 백그라운드 그림자의 높이 조절
		start: function(event, ui) {
		
			dragDone = false;
			var contentId = ui.item.closest('li').attr('id');
			var portalId = ui.item.closest('li').attr('portalId');
			//다른 포탈에서 지정한 컨텐츠는 drag불가
			if(portalId == currentPortalId) {
				//content.cache.put(contentId, ui.item.find('.content-body').html());
				ui.item.find('.content-body').html('');
				ui.item.closest('li').css('background-color','#FFFFFF');
				
				var _zoneId = ui.item.closest('div[class^=luxor]').attr('id');
				$('#'+_zoneId+' .ui-state-highlight2').height(ui.item.height());
			} 
		},
		stop: function(event, ui) {
			var contentId = ui.item.closest('li').attr('id');
			$('#'+contentId+" li[mode='reload']").click();
			// Zone의 높이를 가장 높은 Zone의 높이로 전부 맞춤
			var maxZoneHeight = Math.max(content.getZoneHeight('left'), content.getZoneHeight('content'), content.getZoneHeight('right'));
			
			$('div.luxor-left > ul[type=unfixed]').height(maxZoneHeight);
			$('div.luxor-content > ul[type=unfixed]').height(maxZoneHeight);
			$('div.luxor-right > ul[type=unfixed]').height(maxZoneHeight);
			
			ui.item.closest('li').css('background-color','');
			//캐시로 했더니 iframe이 reload가 잘 안됨
			//ui.item.find('.content-body').html(content.cache.get(contentId));
		},
		// 드래그하여 순서조정을 한 경우 Ajax 호출해서 DB저장
		update: function(event, ui) {
			var contentId = ui.item.closest('li').attr('id');
			
			//개인화 이벤트 발생시 개인화 페이지로 세팅하는 json call
			if (content.refer == "PM" || content.refer == "CM") {
				$('#body div[type=zone]').each(function() {
					
					var tempZoneId = $(this).attr('id');
					var arr = $('#'+tempZoneId+' ul[type=unfixed]').sortable('toArray');
					for(var j=0; j < arr.length; j++) {
						if(luxor.isNullOrEmpty(arr[j])) {
							continue;
						}
						var _tzId = content.getZoneId(arr[j]);
						var portalId = $('#'+arr[j]).attr('portalId');
						var param = "pageId="+selectedNodeId+"&zoneId="+_tzId+"&contentId="+arr[j]+"&seq="+j+"&refer="+content.refer+"&portalId="+portalId;									
						callJson("portalPageZoneController", "updateSeq", param);
					}
				});
			} else {
				callJson("portalPageZoneController", "setPersonalizedPage", "pageId="+selectedNodeId+"&isDeleted=N", function(data) {
					
					$('#body div[type=zone]').each(function() {
				
						var tempZoneId = $(this).attr('id');
						var arr = $('#'+tempZoneId+' ul').sortable('toArray');
						for(var j=0; j < arr.length; j++) {
							if(luxor.isNullOrEmpty(arr[j])) {
								continue;
							}
							var _tzId = content.getZoneId(arr[j]);
							//alert(tempZoneId+":"+arr.length+":"+_tzId);
							var param = "pageId="+selectedNodeId+"&zoneId="+_tzId+"&contentId="+arr[j]+"&seq="+j+"&refer="+content.refer;
							callJson("portalPageZoneController", "updateSeq", param);
						}
					});
				});
			}
		}
	});
}
