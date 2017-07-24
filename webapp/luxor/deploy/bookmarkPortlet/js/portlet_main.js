var param = "userId="+userUid;
var isInitUse = false;

function showBookmarkCategory() {
	$('#bookmarkCategoryTab').html('');
	isInitUse = false;
	callJson("generalPortletController", "getBookmarkCategory", param, function(category) {
		for(var i=0;i<category.length;i++) {
			$('#bookmarkCategoryTab').append('<li id="category_'+category[i].bookmarkId+'" style="padding-top:6px;padding-bottom:4px;">'
					+'<a style="display:inline;padding-bottom:4px;cursor:pointer;">'+category[i].bookmarkName+'</a>'
					+'<img id=edit_'+category[i].bookmarkId+' class="edit" title="edit" alt="edit" src="/ep/luxor/ref/image/portlet/bookmark/ico_edit.gif" style="height:10px;padding-right:5px;display:none;"/>'
					+'<img id=delete_'+category[i].bookmarkId+' class="delete" title="delete" alt="delete" src="/ep/luxor/ref/image/portlet/bookmark/ico_delete.gif" style="height:10px;padding-right:5px;display:none;"/></li>');
			if(category[i].bookmarkOption=="init") {
				$('#bookmarkCategoryTab #category_'+category[i].bookmarkId).attr('class','selected');
				showBookmark(category[i].bookmarkId);
				isInitUse = true;
			}
		}
		$("#bookmarkPortletEdit img").show();
	});
}

function selectBookmarkCategoryTab(bookmarkId) {
	$('#bookmarkCategoryTab li').each(function() {
		$(this).removeClass();
	});
	$('#bookmarkCategoryTab #category_'+bookmarkId).addClass('selected');
	showBookmark(bookmarkId);
}

function showBookmark(bookmarkId) {
	
	$('#bookmarkList').html('');
	param ='userId='+userUid+'&bookmarkId='+bookmarkId;
	callJson("generalPortletController", "getBookmarkListInCategory", param, function(bookmark) {
		for(var j=0;j<bookmark.length;j++) {
			var description ='',strUrl='';
			if(bookmark[j].description!=null) {
				description = '<li>'+bookmark[j].description+'</li>';
			}
			if(bookmark[j].bookmarkOption!='urlHide') {
				strUrl='<li style="color:#178727;cursor: pointer;"><a class='+bookmark[j].bookmarkUrl+'>'+bookmark[j].bookmarkUrl+'</a></li>';
			}
			$('#bookmarkList').append('<ul style="padding:5px;"><li style="font-weight:bold;cursor: pointer;"><a class='+bookmark[j].bookmarkUrl+'>'+bookmark[j].bookmarkName+'</a>'
					+'<img id=edit_'+bookmark[j].bookmarkId+' class="edit" title="edit" alt="edit" src="/ep/luxor/ref/image/portlet/bookmark/ico_edit.gif" style="height:10px;padding: 5px; 0px;display:none;">'
					+'<img id=delete_'+bookmark[j].bookmarkId+' class="delete" title="delete" alt="delete" src="/ep/luxor/ref/image/portlet/bookmark/ico_delete.gif" style="height:10px;display:none;">'
					+'</li>'+strUrl+description+'</ul>');
		}
		$("#bookmarkPortletEdit img").show();
	});
}

function openBookmarkWindow(link) {
	var winWidth = 800;
	var winheight = 600;
	luxor.popup(link,
			{width:winWidth,height:winheight,scrollbars:'yes',resizable:'yes',toolbar:'yes',status:'yes',location:'yes',menubar:'yes'});
}

function showCategoryEditDialog(bookmarkId,btn,title) {
	var pos = $('#bookmarkCategoryTab').closest('.content-body').offset();
	var width = $('#bookmarkCategoryTab').closest('.content-body').outerWidth();
	var height = $('#bookmarkCategoryTab').closest('.content-body').outerHeight();
	if(width<300)width=300;
	var bookmarkName ='',bookmarkOption='';
	if(bookmarkId!="") {
		var param ='userId='+userUid+'&bookmarkId='+bookmarkId;
		callJson("generalPortletController", "getBookmark", param, function(bookmark) {
			bookmarkName = bookmark.bookmarkName;
			if(bookmark.bookmarkOption=='init') {
				bookmarkOption = 'disabled="true" checked';
			}
			$('#editCategoryDialog #bookmarkId').val(bookmarkId);
			$('#editCategoryDialog #bookmarkName').val(bookmarkName);
			$('#editCategoryDialog #bookmarkInitOption').html('<span class="portlet-black-bold" style="margin-right:5px;">'+strInitCategory+'</span><input id="isInitCheck" type="checkbox" '+bookmarkOption+'/>');
		});
	}else{
		$('#editCategoryDialog #bookmarkId').val('');
		$('#editCategoryDialog #bookmarkName').val('');
		$('#editCategoryDialog #bookmarkInitOption').html('');
	}
	$('#editCategoryDialog').dialog({
		resizable:false,
		position:[pos.left, pos.top],
		title:title,
		width:width,
		height:height
	});
	$('#editCategoryDialog').dialog('open');
}

function showBookmarkEditDialog(bookmarkId,btn,title) {
	var pos = $('#bookmarkCategoryTab').closest('.content-body').offset();
	var width = $('#bookmarkCategoryTab').closest('.content-body').outerWidth();
	if(width<300)width=300;
	var height = $('#bookmarkCategoryTab').closest('.content-body').outerHeight();
	var bookmarkName='',bookmarkUrl='http://',description='',bookmarkOption='' ,parentId = '';
	if(bookmarkId!="") {
		param ='userId='+userUid+'&bookmarkId='+bookmarkId;
		callJson("generalPortletController", "getBookmark", param, function(bookmark) {
			bookmarkUrl = bookmark.bookmarkUrl;
			bookmarkName = bookmark.bookmarkName;
			description = bookmark.description;
			parentId = bookmark.parentId;
			if(bookmark.bookmarkOption=='urlHide') {
				bookmarkOption = "checked";
			}
		});
	}
	callJson("generalPortletController", "getBookmarkCategory", param, function(category) {
		$('#categorySelect').html('');
		for(var i=0;i<category.length;i++) {
			var strSelected = '';
			if(parentId==category[i].bookmarkId) {
				strSelected = 'selected';
			}
			$('#categorySelect').append('<option value="'+category[i].bookmarkId+'" '+strSelected+'>'+category[i].bookmarkName+'</option>')
		}
		setTimeout(function() {
			$('#editBookmarkDialog #bookmarkId').val(bookmarkId);
			$('#editBookmarkDialog #bookmarkUrl').val(bookmarkUrl);
			$('#editBookmarkDialog #bookmarkName').val(bookmarkName);
			$('#editBookmarkDialog #description').val(description);
			$('#editBookmarkDialog #bookmarkOption').html(strUrlHide+'<input id="isUrlHideCheck" type="checkbox"'+bookmarkOption+'/>');
		},200);
	});
	$('#editBookmarkDialog').dialog({resizable:false,position:[pos.left, pos.top],title:title,width:width,height:height+50});
	$('#editBookmarkDialog').dialog('open');
}

function editCategory () {
	var option = $('#isInitCheck').attr('checked')==true ? "init" : "";
	var bookmarkName = encodeURIComponent($("#editCategoryDialog #bookmarkName").val());
	param ='userId='+userUid+'&bookmarkName='+bookmarkName+'&parentId=CATEGORY'+
	'&bookmarkOption='+option+'&bookmarkUrl=&description=';
	if(option == 'init' && isInitUse == true) {
		callJson("generalPortletController", "updateCategoryInit",param, function(result) {
		});
	}
	if($("#editCategoryDialog #bookmarkId").val()=="") {
		param +="&bookmarkId="+luxor.generateId();
		callJson("generalPortletController", "insertBookmarkData", param, function(result) {
			if(result==false) {
				alert(strError);
			} else {
				alert(strSuccess);
			}
		});
	} else {
		param +="&bookmarkId="+$("#editCategoryDialog #bookmarkId").val();
		callJson("generalPortletController", "updateBookmarkData", param, function(result) {
			if(result==false) {
				alert(strError);
			} else {
				alert(strSuccess);
			}
		});
	}
	$("#editCategoryDialog").dialog('close');
	
	setTimeout(function() {
		showBookmarkCategory();
	},200);
}

function editBookmark () {
	var option = $('#isUrlHideCheck').attr('checked')==true ? "urlHide" : "";
	var bookmarkName = encodeURIComponent($("#editBookmarkDialog #bookmarkName").val());
	var description = encodeURIComponent($("#editBookmarkDialog #description").val());
	param ='userId='+userUid+'&bookmarkName='+bookmarkName+'&parentId='+$("#editBookmarkDialog #categorySelect").val()+
	'&bookmarkOption='+option+'&bookmarkUrl='+$("#editBookmarkDialog #bookmarkUrl").val()+'&description='+description;
	
	if($("#editBookmarkDialog #bookmarkId").val()=="") {
		param +="&bookmarkId="+luxor.generateId();
		callJson("generalPortletController", "insertBookmarkData", param, function(result) {
			if(result==false) {
				alert(strError);
			} else {
				alert(strSuccess);
			}
		});
	} else {
		param +="&bookmarkId="+$("#editBookmarkDialog #bookmarkId").val();
		callJson("generalPortletController", "updateBookmarkData", param, function(result) {
			if(result==false) {
				alert(strError);
			} else {
				alert(strSuccess);
			}
		});
	}
	$("#editBookmarkDialog").dialog('close');
	setTimeout(function() {
		selectBookmarkCategoryTab($("#editBookmarkDialog #categorySelect").val());
	},200);
}

function deleteCategory(bookmarkId) {
	var param ='userId='+userUid+'&bookmarkId='+bookmarkId;
	callJson("generalPortletController", "deleteBookmarkCategory", param, function(result) {
		if(result==false) {
			if(result==false) {
				alert(strError);
			} else {
				alert(strSuccess);
			}
		}
	});
	setTimeout(function() {
		showBookmarkCategory();
	},200);
}

function deleteBookmark(bookmarkId) {
	var param ='userId='+userUid+'&bookmarkId='+bookmarkId;
	callJson("generalPortletController", "deleteBookmark", param, function(result) {
		if(result==false) {
			if(result==false) {
				alert(strError);
			} else {
				alert(strSuccess);
			}
		}
	});
	setTimeout(function() {
		showBookmarkCategory();
	},200);
}

$(document).ready(function() {
	
	$("#bookmarkList li a").die();
	$("#bookmarkList img.edit").die();
	$("#bookmarkList img.delete").die();
	$("#bookmarkCategoryTab img.delete").die();
	$("#bookmarkCategoryTab img.edit").die();
	$("#bookmarkCategoryTab li").die();
	$('#EditCategory').die();
	$('#EditBookmark').die();
	$('#bookmarkEditcancel').die();
	
	
	if(userUid != '') {
		showBookmarkCategory();
	}
	
	$('#EditCategory').click(function() {
		editCategory();
	});
	
	$('#EditBookmark').click(function() {
		editBookmark();
	});
	
	$('#bookmarkAddCategory').click(function() {
		showCategoryEditDialog("",'bookmarkAddCategory',strInsertCategory);
	});
	
	$('#bookmarkAdd').click(function() {
		showBookmarkEditDialog("",'bookmarkAdd',strInsertBookmark);
	});
	
	$('#bookmarkEditcancel').live('click', function(e) {
		$("#editCategoryDialog").dialog('close');
		$("#editBookmarkDialog").dialog('close');
	});
	
	$('#bookmarkList li a').live('click', function(e) {
		openBookmarkWindow($(this).attr('class'));
	});

	$('#bookmarkList img.edit').live('click', function(e) {
		showBookmarkEditDialog($(this).attr('id').replace("edit_",""),'bookmarkAdd',strEditBookmark);
	});

	$('#bookmarkList img.delete').live('click', function(e) {
		if(confirm(strIsDeleteBookmark)) {
			deleteBookmark($(this).attr('id').replace("delete_",""));
		}
	});

	$('#bookmarkCategoryTab img.delete').live('click', function(e) {
		if(confirm(strIsDeleteCategory)) {
		deleteCategory($(this).attr('id').replace("delete_",""));
		}
	});
	
	$('#bookmarkCategoryTab img.edit').live('click', function(e) {
		showCategoryEditDialog($(this).attr('id').replace("edit_",""),'bookmarkAddCategory',strEditCategory);
	});
	
	$('#bookmarkCategoryTab li').live('click', function(e) {
		selectBookmarkCategoryTab($(this).attr('id').replace("category_",""));
	});
});