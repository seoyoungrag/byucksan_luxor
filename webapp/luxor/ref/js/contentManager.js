/* 컨텐츠와 관련된 공통 스크립트 */
var _treeId = "PORTAL_CONTENT";
var selectedZoneId = "";
var initData = null;
var deletedAdminContentId = "";
//X버튼또는 변경취소버튼을 중복으로 마구누를때 버그 처리
var isClosed = false;
$(document).ready(function() {
	tree_draggable = false;
	
	// 컨텐츠관리 팝업창 초기화
	$('#content_manager').dialog({
		autoOpen:false,
		resizable:false,
		modal:true,
		width:800,
		height:550,
		position:['center',50],
		resize: function() {
			$('#resize_bar2').height($(this).height()-10);
		},
		close:function(event, ui) { // 팝업창 닫을때 초기화
			if(content.refer=='PM') {
				loadContents(selectedNodeId);
			} else {
				document.location.reload();
			}
		}
	});
	
	//dialog title에 id부여
	$("#content_manager").siblings(".ui-dialog-titlebar").attr("id","content_manager_title");
	//dialog X버튼 이벤트 가로채기 (사용자 화면에서만.. 관리자는 NO)
	if(content.refer=='DD' || content.refer=='UM') {
		var temp = $("#content_manager_title").html();
		$("#content_manager_title").html(temp);
		$('#content_manager_title a').live('click mouseover mouseout', function(event) {
			if(event.type=='click') {
				$('#btn_close_content_manager').click();
			} else if(event.type=='mouseout') {
				$(this).removeClass('ui-state-hover');
			} else {
				$(this).addClass('ui-state-hover');
			}
		});
	}
	

	// 컨텐츠관리 창 닫기 버튼 (cancel btn, replace to initData)
	$('#btn_close_content_manager').live('click', function() {
		//최초 접근만 허용 - 비동기성이라 중복으로 마구 누르면 로직 깨짐
		if(isClosed == true) {
			return false;
		}
		isClosed = true;
		if(layoutId != null) {
			var param = "layoutId="+layoutId+"&pageId="+currentPageId;
			callJson("userController", "setLayout", param, function(data) {
		        if(data._errorCode=='-9999') {
		        	alert(portal_alert_msg_9);
		        }
			});
		}
		
		$('#selected_content_list div').each(function() {
			if($(this).attr('content_status') == 'fixed') {
		
			} else if($(this).attr('content_status') != 'admin') {
				var param = "pageId="+selectedNodeId+"&contentId="+$(this).attr('sid')+"&isDeleted=Y";
				callJson("portalPageZoneController", "updateContentDeletedFlag", param);
			} 
		});
		var count = 0;
		for(var i=0; i < initData.length; i++) {
			if(initData[i].zoneId!='header' && initData[i].zoneId!='footer') {
				count++;
			}
		}
		for(var i=0; i < initData.length; i++) {
			if(initData[i].zoneId!='header' && initData[i].zoneId!='footer') {
				if(initData[i].regUserId=='ADMIN' && initData[i].isFixed=='Y') {
				} else if(initData[i].regUserId!='ADMIN' && initData[i].isDeleted!='Y') {
					var param = "pageId="+selectedNodeId+"&contentId="+initData[i].contentId+"&isDeleted=N";
					callJson("portalPageZoneController", "updateContentDeletedFlag", param, function(data) {
					});
				} else if(initData[i].regUserId=='ADMIN') {
					if(deletedAdminContentId.indexOf(initData[i].contentId)>-1) {
						var param = "pageId="+selectedNodeId+"&contentId="+initData[i].contentId+"&isDeleted=N";
						callJson("portalPageZoneController", "updateContentDeletedFlag", param);
					}
				}
				count--;
			}
		}
		var pageId = "pageId="+selectedNodeId;
		callJson("portalPageZoneController", "cleanDeletedContent", pageId, function(data) {
			if(count == 0) {
				$('#content_manager').dialog('close');
			}
		});
	});
	
	// 컨텐츠관리 창 닫기 버튼
	$('#btn_save_content_manager').live('click', function() {
		var pageId = "pageId="+selectedNodeId;
		callJson("portalPageZoneController", "cleanDeletedContent", pageId);
		$('#content_manager').dialog('close');
		return false;
	});
	
	var validation = false;
	// 컨텐츠 목록에서 선택시 우측 선택된 목록 영역으로 추가
	$('#content_list > div').live('click mouseover mouseout dblclick', function(event) {
		
		//실제 더블클릭하여 들어온 경우에는 validation 이 false여서 click이벤트로 강제 이동
		//click이벤트에서 validation 처리 후 dbclick에서 선택목록으로 추가 이벤트 수행 (비동기성 ajax호출을 동기적으로 contrall하기 위함)
		if(event.type == 'dblclick'){
			var selectedContentId = $(this).attr('id');
			if(validation == false) {
				$('#content_list > div[id='+$(this).attr('id')+']').click();
			} else {
				var token = false;
				for(var i=0; i < initData.length; i++) {
					if(initData[i].zoneId == 'header' || initData[i].zoneId == 'footer') {
					}
					else if(initData[i].regUserId!='ADMIN' && selectedContentId == initData[i].contentId) {
						selectedZoneId = initData[i].zoneId;
						var zoneIdLabel = "<span style='color:#990000;'>["+luxor.getMessage(selectedZoneId,'')+"]</span><img did='"+selectedContentId+"' class='delete' title='delete' alt='delete' src='/ep/luxor/ref/image/portlet/bookmark/ico_delete.gif' style='height: 10px;padding-right: 5px;padding-top: 5px;float: right;'/>";
						$('#selected_content_list').append($(this).clone().removeAttr('id').attr('sid',selectedContentId).removeClass('hover').append(zoneIdLabel));
						
						var param = "pageId="+selectedNodeId+"&contentId="+initData[i].contentId+"&isDeleted=N";
						callJson("portalPageZoneController", "updateContentDeletedFlag", param);
						token = true;
					} 
				}
				if(token == false) {
					// Ajax 호출해서 DB저장
					if(luxor.isNullOrEmpty(selectedZoneId)) {
						selectedZoneId = 'content';
					}
					// 추가
					var zoneIdLabel = "<span style='color:#990000;'>["+luxor.getMessage(selectedZoneId,'')+"]</span><img did='"+selectedContentId+"' class='delete' title='delete' alt='delete' src='/ep/luxor/ref/image/portlet/bookmark/ico_delete.gif' style='height: 10px;padding-right: 5px;padding-top: 5px;float: right;'/>";
					if(content.refer!="PM") {
						zoneIdLabel = "<img did='"+selectedContentId+"' class='delete' title='delete' alt='delete' src='/ep/luxor/ref/image/portlet/bookmark/ico_delete.gif' style='height: 10px;padding-right: 5px;padding-top: 5px;float: right;'/>";
					}
					$('#selected_content_list').append($(this).clone().removeAttr('id').attr('sid',selectedContentId).removeClass('hover').append(zoneIdLabel));
					
					var updateParam = "pageId="+selectedNodeId+"&contentId="+selectedContentId+"&isDeleted=N";
					if(content.refer=="PM") {
						var insertParam = "pageId="+selectedNodeId+"&zoneId="+selectedZoneId+"&contentId="+selectedContentId+"&refer="+content.refer;
						callJson("portalPageZoneController", "insertContentOnZone", insertParam);
					}else {
						callJson("portalPageZoneController", "updateContentDeletedFlag", updateParam, function(data) {
							//업데이트가 성공했다는 것은 이미 등록이 되어있는 상태라는것 (컨텐츠관리에서 같은 컨텐츠를 넣다 뺐다 이럴때)
							if(data == false) {
								var insertParam = "pageId="+selectedNodeId+"&zoneId="+selectedZoneId+"&contentId="+selectedContentId+"&refer="+content.refer;
								callJson("portalPageZoneController", "insertContentOnZone", insertParam);
							}
						});
					}
				}
				validation = false;
				//deletedAdminContentId = deletedAdminContentId.replace(selectedContentId+"|","");
			}
		}
		if (event.type == 'click') {
			if($('#selected_content_list div').size() >= contentMaximum) {
				alert(contentMaximum+portal_alert_msg_185);
				return false;
			}
			
			var divId = $(this).attr('id');
			$(this).attr('style','position:relative;');
			$(this).closest('#content_list').css('overflow-x','hidden');
			$(this).animate( { left: '+=100', opacity: 0}, 500, function() {
				$(this).attr('style','');
				$(this).closest('#content_list').css('overflow-x','auto');
				$('#content_list > div[id='+divId+'] > a').attr('style' , 'color:red;');
				$(this).attr('style','position:relative');
	        });
			
			var selectedContentId = $(this).attr('id');
			// 중복체크
			if($("div[sid="+selectedContentId+"]").is('div')) {
				alert(portal_alert_msg_10);
				return false;
			} 
			validation = true;
			$('#content_list > div[id='+selectedContentId+']').dblclick();
		} else if (event.type == 'mouseover') {
			$(this).addClass('hover');
		} else {
			$(this).removeClass('hover');
		}
	});

	// 우측 선택된 목록 클릭시 제거
	$('#selected_content_list div img').live('click', function(event) {
		var object = $(this).closest("div");
		if (event.type == 'click') {
			if(object.attr('content_status') == 'fixed') {
				alert(portal_alert_msg_32);
				return false;
			} else if(object.attr('content_status') == 'admin' && content.refer!="PM") {
				deletedAdminContentId += object.attr('sid')+"|";
				callJson("portalPageZoneController", "setPersonalizedPage", "contentId="+object.attr('sid')+"&pageId="+selectedNodeId+"&setDeleteFlag=Y", function(data) {
				});
			} else if(content.refer!="PM"){
				var param = "pageId="+selectedNodeId+"&contentId="+object.attr('sid')+"&isDeleted=Y";
				callJson("portalPageZoneController", "updateContentDeletedFlag", param);
			} else {
				var param = "pageId="+selectedNodeId+"&contentId="+object.attr('sid')+"&refer="+content.refer;
				callJson("portalPageZoneController", "deleteContentOnZone", param);
			}
			
			$('#content_list > div[id='+object.attr('sid')+'] > a').attr('style' , 'color:black;');
			// 화면에서 제거
			object.remove();
		}
	});
	
	// 우측 선택된 목록 선택시
	$('#selected_content_list div a').live('click', function(event) {
		//preview accordian 창 열기
		if($('#content_preview').attr('src')=="") {
			$('.preview-layout').closest('h3').show();
		} else {
			$('.preview-layout').click();
		}
		$('#content_preview').attr('src','/ep/page/getPreviewPage.do?contentId='+$(this).closest('div').attr('sid')+'&pageId='+selectedNodeId+'&themeImageUrl='+themeImageUrl);
	});
	
	$('#selected_content_list div').live('mouseover mouseout', function(event) {
		if (event.type == 'mouseover') {
			$(this).addClass('hover');
		} else {
			$(this).removeClass('hover');
		}
	});
	
	// 각각 섹션에서 컨텐츠관리 클릭시
	$('#wrap h6[name=contentManager]').live('click', function() {
		selectedZoneId = $(this).parent().attr('id');
		$('#content_manager').dialog({title:luxor.getMessage($(this).closest('div[type=zone]').attr('id'),'')+' '+portal_btn_label_22});
		
		// 컨텐츠 선택 팝업 로딩
		$('#content_manager').load('/ep/content/manager.do?type=contentSelector', function() {
			$('#layoutList').remove();
			
			// 트리초기화
			$("#PORTAL_CONTENT").tree({
		    	ui: {
					theme_name : "classic"
				},
				callback: {
					onselect: function(node, tree_obj) {
						$('#content_list').load('/ep/content/listSimple.do?parentId='+$(node).attr('id') , function() {
							$('#selected_content_list div').each(function() {
								var id = $(this).attr('sid');
								$('#content_list #'+id+' > a').attr('style' , 'color:red;');
							});
						});
					}
				},
				types: {
					'default': {
						draggable : false
					}
				}
			});
			
			// 좌우 크기조정 드래그 바
			$('#resize_bar2').draggable({
				axis: 'x',
				drag: function(e, ui) {
					var pos = $(this).position();
					$('#cs_tree_wrap').width(pos.left);
					$('#cs_admin_wrap').width($('#cs_tree_wrap').width()+550);
					$(this).attr('style','position:absolute;top:55px;');
				},
				stop: function(e, ui) {
					var pos = $(this).position();
					$(this).attr('style','position:relative;height:450px;float:left;margin-left:-213px;');
					$('#cs_admin_wrap').width($('#cs_tree_wrap').width()+$('#container').width()+20);
				}
			});
			$('#resize_bar2').attr('style','top:55px;');		
			$('#content_manager').dialog('open');
			
			// 기존에 선택된 목록 로딩
			callJson("portalPageZoneController", "getContentsOnPage", "pageId="+selectedNodeId, function(data) {
				initData = data;
				for(var i=0; i < data.length; i++) {
					var zoneIdLabel = "<span style='color:#990000;'>["+luxor.getMessage(data[i].zoneId,'')+"]</span>";
					//다른 포탈에서 설정한 컨텐츠는 지울수 없도록 설정
					if(currentPortalId != data[i].portalId && content.refer == 'PM') {
						$('#selected_content_list').append("<div sid='"+data[i].contentId+"' class='link pop_content_div'><a href='#'> - "+data[i].messageName+" "+zoneIdLabel+"</a></div>");
					} else {
						$('#selected_content_list').append("<div sid='"+data[i].contentId+"' class='link pop_content_div'><a href='#'> - "+data[i].messageName+" "+zoneIdLabel+"</a><img did='"+data[i].contentId+"' class='delete' title='delete' alt='delete' src='/ep/luxor/ref/image/portlet/bookmark/ico_delete.gif' style='height: 10px;padding-right: 5px;padding-top: 5px;float: right;'/></div>");
					}
				}
				$.tree.focused().select_branch("#PORTAL_CONTENT #ROOT");
			});
		});
	});
	
	// 마이 컨텐츠 추가 클릭
	$('#mycontent_anchor').live('click', function() {
		
		// 컨텐츠 선택 팝업 로딩
		$('#content_manager').load('/ep/content/manager.do?type=contentSelector', function() {
			// 트리초기화
			$("#PORTAL_CONTENT").tree({
		    	ui: {
					theme_name : "classic"
				},
				callback: {
					onselect: function(node, tree_obj) {
						$('#content_list').load('/ep/content/listSimple.do?parentId='+$(node).attr('id')+'&usePersonal=Y', function() {
							$('#selected_content_list div').each(function() {
								var id = $(this).attr('sid');
								$('#content_list #'+id+' > a').attr('style' , 'color:red;');
							});
						});
					}
				},
				types: {
					'default': {
						draggable : false
					}
				}
			});

			// 좌우 크기조정 드래그 바
			$('#resize_bar2').draggable({
				axis: 'x',
				drag: function(e, ui) {
					var pos = $(this).position();
					$('#cs_tree_wrap').width(pos.left);
					$('#cs_admin_wrap').width($('#cs_tree_wrap').width()+550);
					$(this).attr('style','position:absolute;top:55px;');
				},
				stop: function(e, ui) {
					var pos = $(this).position();
					$(this).attr('style','position:relative;height:450px;float:left;margin-left:-213px;');
					$('#cs_admin_wrap').width($('#cs_tree_wrap').width()+$('#container').width()+20);
				}
			});
			$('#resize_bar2').attr('style','top:55px;');	
			$('#content_manager').dialog('open');
			// 기존에 선택된 목록 로딩
			callJson("portalPageZoneController", "getContentsOnPage", "pageId="+selectedNodeId+"&refer="+content.refer, function(data) {
				initData = data;
				//Personalization check
				var contentIds = "";
				var personalizedContents = "";
				for(var i=0; i < data.length; i++) {
					if(contentIds == "") { 
						contentIds += data[i].contentId+'|';
					} else if(contentIds.indexOf(data[i].contentId)>-1) {
						personalizedContents += data[i].contentId+"|";
					} else {
						contentIds += data[i].contentId+'|';
					}
				}
				for(var i=0; i < data.length; i++) {
					var zoneIdLabel = "";
					if(data[i].isDeleted =='Y') {
						continue;
					}
					if((content.refer != "PM" && content.refer != "CM") && data[i].regUserId == 'ADMIN' && (data[i].zoneId!='header' && data[i].zoneId!='footer')) {
						if(personalizedContents.indexOf(data[i].contentId) > -1) {
							continue;
						}
					}
					
					if(data[i].zoneId=='header' || data[i].zoneId=='footer') {
						continue;
					}
					
					var fixedLabel = "";
					
					if(data[i].regUserId=='ADMIN') {
						if(data[i].isFixed=='Y') {
							fixedLabel = "<span style='color:#990000;' >"+portal_content_label_14+"</span>";
							$('#selected_content_list').append("<div content_status='fixed' sid='"+data[i].contentId+"' class='link pop_content_div'><a href='#'> - "+data[i].messageName+" "+fixedLabel+"</a></div>");
						} else {
							zoneIdLabel = "";//"<span style='color:#990000;' content_status='admin'>[ADMIN]</span>";
							$('#selected_content_list').append("<div content_status='admin' sid='"+data[i].contentId+"' class='link pop_content_div'><a href='#'> - "+data[i].messageName+" "+zoneIdLabel+"</a><img did='"+data[i].contentId+"' class='delete' title='delete' alt='delete' src='/ep/luxor/ref/image/portlet/bookmark/ico_delete.gif' style='height: 10px;padding-right: 5px;padding-top: 5px;float: right;'/></div>");
						}
					} else {
						$('#selected_content_list').append("<div sid='"+data[i].contentId+"' class='link pop_content_div'><a href='#'> - "+data[i].messageName+"</a><img did='"+data[i].contentId+"' class='delete' title='delete' alt='delete' src='/ep/luxor/ref/image/portlet/bookmark/ico_delete.gif' style='height: 10px;padding-right: 5px;padding-top: 5px;float: right;'/></div>");
					}
				}
				$.tree.focused().select_branch("#PORTAL_CONTENT #ROOT");
			});

			// 템플릿 선택
			$('#select_template').change(function() {
				if($(this).val() != "") {
					if(confirm(portal_alert_msg_75)) {
						var param = "currentPageId="+selectedNodeId+"&templatePageId="+$(this).val()+"&pageId="+$(this).val();
						// 레이아웃 셋팅
						callJson("portalPageController", "getPage", param, function(data) {
							if(luxor.isNullOrEmpty(data.pageLayoutId)==false) {
								param += "&layoutId="+data.pageLayoutId;
							}
							callJson("portalPageZoneController", "setTemplatePage", param, function(data) {
								document.location.reload();
							});
						});
					}
				}
			});
			
			// 기존 레이아웃 셋팅
			$('#select_layout').val(layoutId);
			$('#select_layout').change(function() {
				if(confirm(portal_alert_msg_77)) {
					var param = "layoutId="+$(this).val()+"&pageId="+currentPageId;
					callJson("userController", "setLayout", param, function(data) {
						if(data == "1") {
							alert(portal_alert_msg_79);
						} else if(data == "-1") {
							alert(portal_alert_msg_9);
						} else if(data == "0") {
							document.location.reload();
						}
					});
				}
			});
		});
	});
	
	// 마이 컨텐츠 Drag & Drop 모드로 변경
	$('#mycontent_editmode').live('click', function() {
		if(content.refer=='DD') {
			content.refer = 'UM';
			destroySort();
			$(this).text(portal_content_label_15);
		} else {
			content.refer = 'DD';
			doSort();
			$(this).text(portal_content_label_16);
		}
	});
});