
	var searchDivId = "tree_search";
	var messageDivId = "tree_message_form_div";
	
	var cmode = 'add';
	var tree_draggable = true;

	// 선택된 노드명을 가져옴
	function _getNodeName(nodeId) {
		return $('#'+nodeId+' a').first().text();
	}
	
 	// 풀더 생성 버튼 클릭시 호출
 	// 다국어 입력창을 보여줌
	function _showCreateForm() {
		Message.dupilcationCheck = false;
    	cmode = 'add';
    	var t = $.tree.reference($('#'+_treeId));
    	if(t.selected) {
    		var pos = $("#"+_treeId).offset();
    		$('#'+messageDivId).dialog({
    			position:[pos.left,pos.top]
    		});
    		$('#'+messageDivId).dialog('open');
    	} else {
        	alert(portal_alert_msg_7);
    	}
    	
    	$('#'+messageDivId+' input[id~="__message_input"]').val('');
    	$('div[id~="__message_multi_input"] input:text').each(function() {
			$(this).val('');
		});
	}
	
    // 다국어 입력창에서 확인 버튼을 클릭시 호출
    function _treesave() {
    	if(!Message.dupilcationCheck) {
    		alert(portal_page_alert_4);
    		return false;
    	}
    	var t = $.tree.reference($('#'+_treeId));
    	if(cmode=='add') {
    		//js 스크립트 내에서는 다른 js에서 ajax쓴걸 사용하면 안됨 캐시갱신이 안되어서 최초 받은 값을 브라우저가 가지고 있음
    		//(낡디낡은IE가그렇다..)
    		var _newNodeId = luxor.generateId('N'); 
    		t.create( { attributes:{'id':_newNodeId}, data:{title:Message.getDefaultMessageName()} } );
    		$('#'+_newNodeId+' a').first().attr('href','#');
    		$('#'+_newNodeId+' a').first().attr('class','clicked');
    		$('#'+_newNodeId+' a').first().removeAttr('style');
    		$('#'+_newNodeId+' ins').first().text('');
    	} else {
    		if(luxor.isNullOrEmpty(Message.getMessageNameAll())) {
    			alert("Error");
    			return false;
    		}
    		_renameNode(t.selected.attr("id"));
    	}
    }

    // 풀더 수정 버튼 클릭시 호출
    function _showModifyForm() {
    	Message.dupilcationCheck = false;
    	cmode = 'mod';
    	var t = $.tree.reference($('#'+_treeId));
    	if(t.selected) {
        	if(t.selected.attr("id")=="ROOT") {
            	alert(portal_alert_msg_6);
            	return;
        	}
        	Message.setMessageId(t.selected.attr("nodeNameId"));
    		var pos = $("#"+_treeId).offset();
    		$('#'+messageDivId).dialog({position:[pos.left,pos.top]});
    		$('#'+messageDivId).dialog('open');
    	} else {
        	alert(portal_alert_msg_7);
    	}
    }

    function openNode(nodeId) {
    	$.tree.focused().open_all("#"+nodeId);
    }
    function openAll() {
    	$.tree.focused().open_all("#ROOT");
    	$.tree.focused().open_branch("#ROOT");
    }
    function closeAll() {
    	$.tree.focused().close_all("#ROOT");
    }

    
    /**
     * 노드 선택
     * @param nodeId
     * @return
     */
    function _selectNode(nodeId) {
    	$.tree.reference($('#'+_treeId)).select_branch("#"+nodeId);
    }
    
    /**
     * 노드 삭제 버튼 클릭시 호출
     * 
     * @param nodeId
     * @param askConfirm
     * @return
     */
    function _deleteFolder(nodeId, askConfirm) {
    	if(typeof(nodeId)=='undefined') {
    		var t = $.tree.focused();
        	if(t.selected) {
        		nodeId = t.selected.attr("id");
        	} else {
            	alert(portal_alert_msg_7);
            	return;
        	}
    	}
    	
    	if(typeof(askConfirm)=='undefined') {
    		askConfirm = true;
    	}
    	
    	if(nodeId==("ROOT")) {
        	alert(portal_alert_msg_5);
        	return;
    	}
    	if(askConfirm) {
    		if(confirm(portal_alert_msg_4)) {
        		_selectNode(nodeId);
        		$.tree.focused().remove();
    		}
    	} else {
    		_selectNode(nodeId);
    		$.tree.focused().remove();
    	}
    }
    
    // 검색 버튼 클릭시
    function _searchFolder(obj) {
    	$.tree.focused().search($(obj).prev().val());
    }
    
    // 엔터 쳤을때 검색 버튼 클릭 효과
    function _checkEnter(e,obj) {
        if(e.keyCode==13) {
        	_searchFolder();
        }
    }
	
	// 폴더명 수정시 호출
	// Ajax로 폴더명 수정
	function _renameNode(nodeId) {
		var nodeNameId = $('#'+nodeId).attr('nodeNameId');
		var params = 'treeId='+_treeId+'&nodeId='+nodeId+'&messageId='+nodeNameId+'&messageNameAll='+encodeURIComponent(Message.getMessageNameAll());
		callJson("treeController", "updateTreeNode", params, function(data) {
			var langArray = Message.getMessageNameAll().split("|");
			var message = "", messageArray = null;
			for(var i =0 ;i<langArray.length;i++) {
				if(langArray[i].indexOf(Message.getLangType())>-1) {
					messageArray = langArray[i].split("^");
					message = messageArray[1];
				}
			}
			$('#'+messageDivId).dialog('close');
			$('#'+nodeId+' a:first').html("<ins></ins>"+message);
			try {
				updateCallBack(nodeId);
			} catch(e) {}
		});
	}

	/**
	 * Ajax로 하위 트리 가져올때 가져온 트리노드 Object를 이 함수에 전달해주면 됨
	 * 
	 * @param nodeId 이 nodeId 밑으로 하위 트리가 그려짐
	 * @param nodeObj 그려질 트리 데이터
	 * @return
	 */
	function drawSubTree(nodeId, nodeObj) {
		var t = $.tree.focused();
		for(var i=0; i < nodeObj.length; i++) {
			if(typeof($('#'+nodeObj[i].nodeId).attr('parentId'))!='undefined') {
				continue;
			}
			try {
				t.create({attributes:{'id':nodeObj[i].nodeId, 'parentId':nodeObj[i].parentId, 'depth':nodeObj[i].hasChild, 'orgType':nodeObj[i].nodeType, 'treeNodeName':nodeObj[i].nodeName},
					data:{title:nodeObj[i].nodeName}}, '#'+nodeObj[i].parentId);
			} catch(e) {}
		}
	}

	/**
	 * 실제 트리를 그리는 부분
	 * 
	 * @param treeId
	 * @return
	 */
	function drawTree(treeId) {
		if(treeId) {
			_treeId = treeId;
		}
        $("#"+_treeId).tree({
    		ui: {
				theme_name : "classic"
			},
			types: {
				'default': {
					draggable : tree_draggable
				}
			},
			// 트리 Action 콜백함수들
			callback: {
				// 트리 선택되었을때 호출
				onselect: function(node, tree_obj) {
					try {
						var parentId = tree_obj.parent(node).attr('id');
						selectCallback($(node).attr('id'), parentId);
					} catch(e) {
						selectCallback($(node).attr('id'));
					}
				},
				onopen: function(node, tree_obj) {
					try {
						openCallback($(node).attr('id'));
					} catch(e) {
					}
				},
				// 트리 검색시 호출
				onsearch : function (n,t) {
					t.container.find('a.search').removeClass('search');
					n.addClass('search');
				},
				// 트리 생성시 호출
				// Ajax로 트리 생성
				oncreate : function(node, parent, type, tree_obj, rollback) {
					if(luxor.isNullOrEmpty(Message.getMessageNameAll())) {
						alert("Error");
						return false;
					}
					
					var params = 'treeId='+_treeId+'&nodeId='+$(node).attr('id')+'&parentId='+tree_obj.parent(node).attr('id')
								+'&messageNameAll='+encodeURIComponent(Message.getMessageNameAll())+'&messageType='+Message.getMessageType();

					callJson("treeController", "insertTreeNode", params, function(data) {
						$('#'+messageDivId).dialog('close');
						try {
					        if(data._errorCode=='-9999') {
					        	alert(portal_alert_msg_9);
					        	$.tree.rollback(rollback);
					        	return false;
					        }
					        
					        $(node).attr('parentid', tree_obj.parent(node).attr('id'));
					        $(node).attr('nodenameid', data);
							insertCallBack($(node).attr('id'), data);
						} catch(e) {}
					});
				},
				// 트리 삭제시 호출
				// Ajax로 삭제처리
				ondelete : function(node, tree_obj, rollback) {
					var nodeId = $(node).attr('id');
					var nodeNameId = $(node).attr('nodeNameId');
					var parentId = $(node).attr('parentId');
					var params = "treeId="+_treeId+"&nodeId="+nodeId+"&messageId="+nodeNameId+"&parentId="+parentId;
					callJson("treeController", "deleteTreeNodeAll", params, function(data) {
						try {
					        if(data._errorCode=='-9999') {
					        	alert(portal_alert_msg_9);
					        	$.tree.rollback(rollback);
					        	return false;
					        }
							deleteCallBack(nodeId);
						} catch(e) {}
					});
				},
				// 트리를 드래그하여 옮길때 호출
				// Ajax로 폴더 이동 처리
				onmove : function (NODE,REF_NODE,TYPE,TREE_OBJ,RB) {
					if($(REF_NODE).attr('id')=='ROOT') {
						alert(portal_alert_msg_42);
						$.tree.rollback(RB);
						return false;
					}
					
					var params = 'srcNodeId='+$(NODE).attr('id')+'&targetNodeId='+$(REF_NODE).attr('id')+'&type='+TYPE;
					callJson("treeController", "moveTreeNode", params, function(data) {
						try {
					        if(data._errorCode=='-9999') {
					        	alert(portal_alert_msg_9);
					        	$.tree.rollback(RB);
					        	return false;
					        }
							moveCallBack($(NODE).attr('id'),$(REF_NODE).attr('id'),TYPE);
						} catch(e) {}
					});
				},
				ondrop : function (NODE,REF_NODE,TYPE,TREE_OBJ) {
				}
			}
        });
	}
	
    $(document).ready(function() {
    	try {
    		$('#'+messageDivId).dialog({autoOpen:false,width:270,height:90,modal:true});
    	} catch(e) {}
    	
    	// 검색 처리
        $('#'+searchDivId+' a').click(function(e) {
        	$.tree.reference($('#'+_treeId)).search($('#'+searchDivId+' :text').val());
        	if($('#'+searchDivId+' :text').val() == '') {
        		alert(portal_alert_msg_26);
        	} else if($('#tree_wrap li a.search').length == 0) {
        		alert(portal_alert_msg_56);
        	}
        	
        	return false;
        });
        
        $('#'+searchDivId+' > :text').keypress(function(e) {
            if(e.keyCode==13) {
            	$('#'+searchDivId+' a').click();
            }
        });
    	
     	// 드래그가 끝났을때 호출
    	$.tree.drag_end = function () {
    	};

    	drawTree();
    });
