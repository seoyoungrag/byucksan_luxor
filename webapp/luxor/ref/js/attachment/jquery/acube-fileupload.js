/*
 * jQuery File Upload Plugin JS Example 5.0.2
 * https://github.com/blueimp/jQuery-File-Upload
 *
 * Copyright 2010, Sebastian Tschan
 * https://blueimp.net
 *
 * Licensed under the MIT license:
 * http://creativecommons.org/licenses/MIT/
 */

/*jslint nomen: true */
/*global $j */
var $j = jQuery.noConflict();
var uploadedFiles = new Array();
var savedFiles = new Array();
var filesToSave = new Array();
var filesToDelete = new Array();
var totalFiles = new Array();
var currentTotalSize = 0;

jQuery(document).ready(function () {
	if(attachmentId == '') {
		attachmentId = luxor.generateId('F');
	}
    'use strict';
    $j('#fileupload').show();
    var _acceptFileTypes = /.+$/i;
    try {
      _acceptFileTypes = upload_acceptFileTypes;
    } catch(e) {}
    // Initialize the jQuery File Upload widget:
    $j('#fileupload').fileupload({
		  url:'/ep/servlet/upload', 
		  singleFileUploads:'true', 
		  sequentialUploads:'true'
		  //acceptFileTypes: _acceptFileTypes,
		  //refuseFileTypes: upload_limitFileTypes
	})
	.bind('fileuploaddone', function(e, data){
		uploadedFiles = uploadedFiles.concat(data.result);
	}).bind('fileuploadstop', function (e) {
		saveFiles();
	}).bind('fileuploaddestroy', function (e, data) {
		var deletedStorFid = data.context.data("tmplItem").data.id;
		if(deletedStorFid == null) { deletedStorFid = attachmentId; }
		var deletedNameTemp = data.context.data("tmplItem").data.name_temp;
		var deletedName = data.context.data("tmplItem").data.name;
		var storeFileId = data.context.data("tmplItem").data.storeFileId;
		if(deletedStorFid) {
			//var delim = filesToDelete.length > 1 ? ",": "";
			//filesToDelete = filesToDelete + delim + deletedStorFid+'/'+deletedNameTemp;
			filesToDelete.push(data.context.data("tmplItem").data);
			
			for(var i=(filesToSave.length-1); i >= 0; i--) {
			  var tempfileinfo = filesToSave[i][0];
				if(tempfileinfo.name_temp == deletedNameTemp) {
					filesToSave.splice(i, 1);
				}
			}
			for(var i=(uploadedFiles.length-1); i >= 0; i--) {
			  var tempfileinfo = uploadedFiles[i];
				if(tempfileinfo.name_temp == deletedNameTemp) {
					uploadedFiles.splice(i, 1);
				}
			}
			for(var i=(savedFiles.length-1); i >= 0; i--) {
			  var tempfileinfo = savedFiles[i];
				if(tempfileinfo.name_temp == deletedNameTemp) {
					uploadedFiles.splice(i, 1);
				}
			}
		} 
		
	}).bind('fileuploadadd', function (e, data) {
		//reloadIframe();
		filesToSave.push(data.files);
	});
    
    
    $('.fileupload-buttonbar .cancel').live('click.' , function(e) {
    	uploadedFiles = new Array();
		savedFiles =  new Array();
		filesToSave =  new Array();
		filesToDelete = new Array();
    	fileRendering(false);
    });
    $('.fileupload-buttonbar .start').live('click.' , function(e) {
    	var count = 0;
    	$('.files .cancel').each(function() {
    		count ++;
    	});
    	if(filesToDelete.length > 0 && count < 1) {
    		fileDelete();
    		uploadedFiles = new Array();
			savedFiles =  new Array();
			filesToSave =  new Array();
			filesToDelete = new Array();
    	}
    });
    
    $('.download').live('click.' , function(e) {
    	location.href = $(this).closest('.template-download').find('.name a').attr('href');
    });
    
    fileRendering(true);
    fileDownloading();

    // Open download dialogs via iframes,
    // to prevent aborting current uploads:
    $j('#fileupload .files a:not([target^=_blank])').live('click', function (e) {
        e.preventDefault();
        $j('<iframe style="display:none;"></iframe>')
            .prop('src', this.href)
            .appendTo('body');
    });
    
   // 세시판 등록과 같이 따로 업로드 기능이 필요 없으면 아래를 활성화
   // $j('#fileupload button[type="submit"]').hide();
});

function saveFiles() {
	if(uploadedFiles.length > 0 || filesToDelete.length > 1) {
		$j.ajax({
			url: '/ep/servlet/upload/save',
			dataType: "json",
			type: "POST",
			data: {
				"filesInfo":JSON.stringify(uploadedFiles),
				"boardid":moduleDiv,
				"jndiname":"",
				"fid":attachmentId,
				"delfiles":""
			},
			success: function(result) {
				if(result.error && !result.fid) {
					alert("error: "+result.error);
				} 
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("error("+textStatus+"): "+errorThrown);
			}
		});
	} 
	
	var params = '';
	var sFiles = null;
	// 저장용 param 을 생성
	for(var i=0;i<uploadedFiles.length;i++) {
		var file = uploadedFiles[i];
		params += '&localFileName=' + encodeURIComponent(file.name);
		params += '&serverPath=' + uploadPath + "/" +file.name_temp;
		params += '&serverFileName=' + file.name_temp;
		params += '&fileSize=' + file.size;
		params += '&isNew=true';
		params += '&storeFileId=';
		params += '&seq=' + i+1;
	}
	
	for(var i=0;i<savedFiles.length;i++) {
		var file = savedFiles[i];
		params += '&localFileName=' + encodeURIComponent(file.name);
		params += '&serverPath=' + uploadPath + "/" +file.name_temp;
		params += '&serverFileName=' + file.name_temp;
		params += '&fileSize=' + file.size;
		params += '&isNew=false';
		params += '&storeFileId=' + file.storeFileId;
		params += '&seq=' + uploadedFiles.length+ i +1;
	}
	params = 'tenantId='+tenantId+'&portalId='+portalId+'&attachmentId=' + attachmentId + '&moduleDiv=' + moduleDiv + params;
	callJson('attachmentController', 'apply', params, function(json) {
		
		if(json.success) {
			var flag = fileDelete();
			if(flag == false) {
				//alert('upload is success.');
				fileRendering(true);
			}
			uploadedFiles = new Array();
			savedFiles =  new Array();
			filesToSave =  new Array();
			filesToDelete = new Array();
		}else{
			alert('upload is failed. check server log.');
			uploadDone = true;
		}
	});
};
function fileDelete() {
	var params = "";
	if(filesToDelete.length > 0) {
		for(var i=0;i<filesToDelete.length;i++) {
			var file = filesToDelete[i];
			
			params += '&localFileName=' + encodeURIComponent(file.name);
			params += '&serverPath=' + uploadPath + "/" +file.name_temp;
			params += '&serverFileName=' + file.name_temp;
			params += '&fileSize=' + file.size;
			params += '&isNew=false';
			params += '&storeFileId=' + file.storeFileId;
		}
		
		params = 'tenantId='+tenantId+'&portalId='+portalId+'&attachmentId=' + attachmentId + '&moduleDiv=' + moduleDiv + params;
		callJson('attachmentController', 'delete', params, function(json) {
			
			if(json.success) {
				//alert('upload is success.');
				uploadDone = true;
				fileRendering(false);
			}else{
				alert('upload is failed. check server log.');
				uploadDone = true;
			}
		});
	} else {
		uploadDone = true;
		return false;
	}
}

function fileRendering(flag) {
    if(attachmentId) {
		var params = 'tenantId='+tenantId+'&portalId='+portalId+'&attachmentId=' + attachmentId;
		totalFiles = new Array();
		callJson('attachmentController', 'getList', params, function(json){
			if(json.length) {
				for(var i=0;i<json.length;i++) {
					var file = new Object();
					file.id = attachmentId;
					file.size = json[i].fileSize;
					file.name = json[i].clientFilename;
					file.name_temp = json[i].serverFilename;
					file.isEdit = true;
					file.isNew = false;
					file.index = json[i].seq;
					file.storeFileId = json[i].storeFileId;
					file.thumbnail_url = '/ep/luxor/ref/image/attachment' + AttachmentUtil.getIcon(json[i].clientFilename);
					totalFiles.push(file);
				}
				savedFiles = totalFiles;
		        var fu = $j('#fileupload').data('fileupload');
		        fu._adjustMaxNumberOfFiles(-totalFiles.length);
		        $j('#fileupload .files').html("");
		        fu._renderDownload(totalFiles)
		            .appendTo($j('#fileupload .files'))
		            .fadeIn(function () {
		                // Fix for IE7 and lower:
		                $j(this).show();
		        		$('.template-download').each(function() {
		        			$(this).find('.name a').attr('href',basePath+'/servlet/upload/file/serverPath/'+$(this).find('.name').attr('id')+'/'+$(this).find('.name a').html());
		        		});
		            });
		        if(flag == true) {
		        	fileDownloading();
        		}
			}
		});
	}
}

function fileDownloading() {
	var totalSize = 0;
	var params = "";
	for(var i=0;i<totalFiles.length;i++) {
		params += '&localFileName=' + encodeURIComponent(totalFiles[i].name);
		params += '&serverPath=' + uploadPath + "/" +totalFiles[i].name_temp;
		params += '&serverFileName=' + totalFiles[i].name_temp;
		params += '&fileSize=' + totalFiles[i].size;
		params += '&isNew=false';
		params += '&storeFileId=' + totalFiles[i].storeFileId;
		params += '&seq=' + totalFiles[i].index;
	}
    if(totalFiles.length > 0) {
		params = 'tenantId='+tenantId+'&portalId='+portalId+'&attachmentId=' + attachmentId + params;
		callJson('attachmentController', 'download', params, function(json){
		});
	}
}
var AttachmentUtil = {
		getIcon : function(fileName) {
			var ext = fileName.substr(fileName.lastIndexOf(".") + 1);
			ext = ext.toLowerCase();
			if(ext.indexOf("xlsx") > -1) ext = 'xls';
			else if(ext.indexOf("docx") > -1) ext = 'doc';
			else if(ext.indexOf("pptx") > -1) ext = 'ppt';
			if(
				ext.indexOf("class") > -1  ||
				ext.indexOf("java") > -1  ||
				ext == '') {
					ext = "etc";
			} 
			if(
					ext.indexOf("mp3") > -1  ||
					ext.indexOf("mp4") > -1  ||
					ext == '') {
						ext = "wav";
				} 
			return "/attach_" + ext.replace(/(^\s*)|(\s*$)/g, "") + ".gif";
		},
		getKB : function (fileSize) {
			if (fileSize < 1024) {
				return fileSize + ' B';
			} else {
				try{
					return Math.round(parseInt(fileSize)/1024) + ' KB';
				}catch(e){
					return fileSize;
				}
				
			}
		},
		escapeQuote : function(str) {
			return str.replace(/\'/g,"\\'");
		}
	};


//Start Upload 대신에 게시판 등록 시 이 스크립트를 사용하면 됨.
function startFileUpload(callback) {
	$('.fileupload-buttonbar .start').click();
	parent.setAttachInfo(attachmentId,$('#fileupload .files tr').size());
	callback(attachmentId);
	return;
}
