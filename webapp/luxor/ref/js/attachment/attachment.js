
var del29 = String.fromCharCode(29);
var del31 = String.fromCharCode(31);

var attachments = {};

(function($) {
	
	$.fn.attachment = function(options) {
		
		var id = this.attr('id');
		var attachmentId = '';
		$('body').append(eval(AttachmentUtil.pluginLoad(id)));
		
		if(typeof(options)== 'undefined') options = {
			'attachId' : '',
			'totalSize' : 0,
			'maxSize' : 10485760,
			'lang' : 'K',
			'serverUrl' : 'localhost',
			'serverPort' : '7775',
			'uploadMode' : 0,
			'tempDir' : 'D:/Temp/sftp_temp',
			'attachmentMode' : 'sftp'
		};
		
		Logger.debug('attachment():called!! id=' + id);
		if(typeof(options.attachId) == 'undefined') {
			options.attachId = '';
			attachmentId = '';
		}else{
			attachmentId = options.attachId;
		}
		Logger.debug('attachment():attachmentId=' + attachmentId);
		
		if(typeof(options.lang) == 'undefined') option.lang = 'K'; 
		if(typeof(options.serverUrl) == 'undefined') options.serverUrl = 'localhost';
		if(typeof(options.serverPort) == 'undefined') options.serverPort = '7775';
		if(typeof(options.totalSize) == 'undefined') options.totalSize = 0;
		if(typeof(options.maxSize) == 'undefined') options.maxSize = 10485760;
		if(typeof(options.uploadMode) == 'undefined') options.uploadMode = 0;
		if(typeof(options.tempDir) == 'undefined') options.tempDir = 'D:/Temp/sftp_temp';
		if(typeof(options.attachmentMode) == 'undefined') options.attachmentMode = 'sftp';
		if(typeof(options.moduleDiv) == 'undefined') options.moduleDiv = 'COMMON.TEST';
		
		document.getElementById(id + '_FileWizard').SetLanguage(options.lang);//'lang' : 'K',
		document.getElementById(id + '_FileWizard').SetServerInfo(options.serverUrl, options.serverPort);
		document.getElementById(id + '_FileWizard').SetAttachedFileSize(options.totalSize);
		document.getElementById(id + '_FileWizard').SetMaxFileSize(options.maxSize);
		document.getElementById(id + '_FileWizard').SetEncOption(0);
		document.getElementById(id + '_FileWizard').SetUploadMode(options.uploadMode);//'uploadMode' : 0,
		document.getElementById(id + '_FileWizard').SetTempDir(options.tempDir);//'tempDir' : 'D:/Temp/sftp_temp',
		document.getElementById(id + '_FileWizard').SetTransferMode(options.attachmentMode == 'sftp' ? 0 : 1);
		
		if(typeof(options.serverAutoDelete) != 'undefined') document.getElementById(id + '_FileWizard').SetServerAutoDelete(options.serverAutoDelete);
		if(typeof(options.multiSelectMode) != 'undefined') document.getElementById(id + '_FileWizard').SetMultiSelectMode(options.multiSelectMode);
		
		if(options.attachmentMode == 'shttp') {
			document.getElementById(id + '_FileWizard').SetSHTTPMode(options.shttpInfo.id, options.shttpInfo.passwd, options.shttpInfo.uploadServletPath, '', options.shttpInfo.downloadServletPath);
		}
		Logger.info('----------------- file attach configulation info ------------------');
		Logger.info('language:' + options.lang);
		Logger.info('sftp server info:' + options.serverUrl + ':' + options.serverPort);
		Logger.info('maximum attach size:' + options.maxSize);
		Logger.info('file upload mode:' + options.uploadMode);
		Logger.info('server temp directory:' + options.tempDir);
		Logger.info('file transfer protocol:' + options.attachmentMode);
		Logger.info('serverAutoDelete:' + options.serverAutoDelete);
		Logger.info('multiSelectMode:' + options.multiSelectMode);
		if(options.attachmentMode == 'shttp') {
			Logger.info('shttpInfo.id:' + options.shttpInfo_id);
			Logger.info('shttpInfo.passwd:' + options.shttpInfo_passwd);
			Logger.info('shttpInfo.uploadServletPath:' + options.shttpInfo_uploadServletPath);
			Logger.info('shttpInfo.downloadServletPath:' + options.shttpInfo_downloadServletPath);
			document.getElementById(id + '_FileWizard').SetSHTTPMode(options.shttpInfo_id, options.shttpInfo_passwd, options.shttpInfo_uploadServletPath, '', options.shttpInfo_downloadServletPath);
		}
		Logger.info('-------------------------------------------------------------');
		
		if(attachmentId) {
			var params = 'attachmentId=' + attachmentId;
			var files = new Array();
			var totalSize = 0;
			callJson('attachmentController', 'getList', params, function(json){
				if(json.length) {
					for(var i=0;i<json.length;i++) {
						var file = new File();
						file.id = attachmentId;
						file.fileSize = json[i].fileSize;
						file.clientFileName = json[i].clientFilename;
						file.serverFileName = json[i].serverFilename;
						if(options.flag == 'edit'){
							file.isEdit = true;
						}
						file.isNew = false;
						file.index = json[i].seq;
						file.storeFileId = json[i].storeFileId;
						files.push(file);
						totalSize += parseInt(file.fileSize);
					}
					options.totalSize = totalSize;
					attachments[id] = {
						'options' : options,
						'files' : files,
						'deleted' : new Array()
					};
					drawList(id, attachments[id].files);
					document.getElementById(id + '_FileWizard').SetAttachedFileSize(options.totalSize);
					Logger.info('current attached size=' + options.totalSize);
				}
			});
			
		}
		
		// 첨부파일 추가 이벤트 bind
		$('#' + id + ' a.attach-addBtn').click(function(e){
			document.getElementById(id + '_FileWizard').SetMode('NEW');
			var attString = document.getElementById(id + '_FileWizard').AttachFileJs();
			var files = AttachmentUtil.parseAttachString(attString);
			for(var i=0;i<files.length;i++) {
				options.totalSize += parseInt(files[i].fileSize);
			}
			
			var deleted = null;
			if(typeof(attachments[id]) == 'undefined') deleted = new Array();
			else if(typeof(attachments[id].deleted) == 'undefined') deleted = new Array();
			else deleted = attachments[id].deleted;
			
			attachments[id] = {
				'options' : options,
				'files' : AttachmentUtil.getServerFiles(id).concat(files),
				'deleted' : deleted
			};
			document.getElementById(id + '_FileWizard').SetAttachedFileSize(options.totalSize);
			Logger.info('current attached size=' + options.totalSize);
			drawList(id, attachments[id].files);return false;
		});
		
		// 첨부파일 다운로드
		$('#' + id + ' a.attach-downBtn').click(function(e) {
			Logger.debug('file download called...');
			if(typeof(attachments[id]) == 'undefined'){
				Logger.info('첨부된 파일이 없습니다.');
				return;
			}
			
			//Logger.debug($('#' + id + ' input:checkbox:checked').length);
			
			if($('#' + id + ' input:checkbox:checked').length == 0) {
				Logger.info('선택된 첨부파일이 없습니다.');
			}else{
				var a = $('#' + id + ' input:checkbox:checked');
				var sInfo = '';
				var params = 'attachmentId=' + attachments[id].options.attachId;
				sInfo += a.length + del31;
				for(var i=0;i<a.length;i++) {
					var index = $(a[i]).val()-1;
					Logger.debug('download:index=' + index);
					var file = attachments[id].files[index];
					if(!file.isNew) { // 새로 추가된 파일은 제외
						sInfo += del29 + file.clientFileName;
						sInfo += del29 + del29; // 로컬 패스는 생략
						sInfo += attachments[id].options.tempDir + '/' + file.serverFileName;
						sInfo += del31;
						params += '&seq=' + (index);
					}
				}
				
				callJson('attachmentController', 'download', params, function(json){
					document.getElementById(id + '_FileWizard').ViewFile("W", sInfo);	
				});
			}
			
		});
	};
	
	// 첨부파일 저장
	$.fn.upload = function(callBack) {
		var id = this.attr('id');
		var params = '';
		var sFiles = null;
		
		// 첨부파일 자체가 없는 경우
		if(typeof(attachments[id]) == 'undefined' || attachments[id].files.length <= 0) {
			if(attachments[id].deleted.length == 0) {
				alert('upload():첨부할 파일이 없습니다.');
				if(callBack) {
					callBack({success : 'true', 'attachmentId' : attachments[id].options.attachId});
				}
				return;
			}
		}
			
		// 새로 추가된 파일이 없는 경우
		var count = 0;
		for(var i=0;i<attachments[id].files.length;i++) {
			var file = attachments[id].files[i];
			if(file.isNew) count++;
		}
		
		if(count == 0 && attachments[id].deleted.length == 0) {
			alert('upload():새로 첨부되거나 삭제된 파일이 없습니다.');
			if(callBack) {
				callBack({success : 'true', 'attachmentId' : attachments[id].options.attachId});
			}
			return;
		}
		
		if(count != 0) {
			sFiles = document.getElementById(id + '_FileWizard').GetFileRegInfoJs("NEW");
			// 신규 저장된 file의 store id를 기존 파일 목록 정보에 저장
			var splitStr = sFiles.split(del31);
			for(var i=0;i<splitStr.length;i++){
				var arr = splitStr[i].split(del29);
				for(var j=0;j<attachments[id].files.length;j++) {
					var file = attachments[id].files[j];
					if(luxor.trim(file.clientFileName) == luxor.trim(arr[1]) && file.isNew) {
						file.serverPath = arr[3].replace("//","/");
						file.serverFileName = arr[3].substring(arr[3].lastIndexOf('/') + 1);
					}
				}
			}
		}
		
		// 저장용 param 을 생성
		for(var i=0;i<attachments[id].files.length;i++) {
			var file = attachments[id].files[i];
			params += '&localFileName=' + encodeURIComponent(file.clientFileName);
			params += '&serverPath=' + file.serverPath;
			params += '&serverFileName=' + file.serverFileName;
			params += '&fileSize=' + file.fileSize;
			params += '&isNew=' + file.isNew;
			params += '&storeFileId=' + file.storeFileId;
			params += '&seq=' + file.index;
		}
		
		// 삭제용 param 을 생성
		for(var i=0;i<attachments[id].deleted.length;i++) {
			
			var deleted = attachments[id].deleted[i];
			params += '&deleted=' + deleted;
		}
		var attachmentId = null;
		if(attachments[id].options.attachId == '') attachmentId = luxor.generateId('F');
		else attachmentId = attachments[id].options.attachId;
		
		params = 'attachmentId=' + attachmentId + '&moduleDiv=' + attachments[id].options.moduleDiv + params;
		//Logger.debug('params=' + params);
		callJson('attachmentController', 'apply', params, function(json) {
			Logger.debug('json.success=' + json.success);
			if(json.success) {
				Logger.info('upload is success.');
				if(callBack) {
					callBack(json);
				}
			}else{
				Logger.error('upload is failed. check server log.');
			}
		});
	};
	
})(jQuery);


function drawList(id, files) {
	var html = '';
	html += '<ul>';
	for(var i=0;i<files.length;i++) {
		files[i].index = i + 1; // 그리는 순서대로 index 재정렬
		html += '<li><div class="attach-text"><input type="checkbox" value="' + files[i].index + '">&nbsp;';
		if(files[i].isNew) {
			var filePath = AttachmentUtil.escapeQuote(files[i].localPath);
			html += '<img src="/ep/luxor/ref/image/attachment' + AttachmentUtil.getIcon(files[i].clientFileName) + '">&nbsp;<a href="#" onclick="AttachmentUtil.displayLocalFile(\'' + id + '\', \'' + filePath + '\')">' + files[i].clientFileName + '</a> (' + AttachmentUtil.getKB(files[i].fileSize) + ')';
			html += '&nbsp;<a href="#" class="attach-filedelete" index="' + files[i].index + '" isnew="' + files[i].isNew + '"><img src="/ep/luxor/ref/image/attachment/blog_icon03.gif"></a>';
		}else if(files[i].isEdit) {
			html += '<img src="/ep/luxor/ref/image/attachment' + AttachmentUtil.getIcon(files[i].clientFileName) + '">&nbsp;<a href="#" onclick="AttachmentUtil.viewFile(\'' + id + '\', \'' + (files[i].index-1) + '\')">' + files[i].clientFileName + '</a> (' + AttachmentUtil.getKB(files[i].fileSize) + ')';
			html += '&nbsp;<a href="#" class="attach-filedelete" index="' + files[i].index + '" isnew="' + files[i].isNew + '"><img src="/ep/luxor/ref/image/attachment/blog_icon03.gif"></a>';
		}else {
			html += '<img src="/ep/luxor/ref/image/attachment' + AttachmentUtil.getIcon(files[i].clientFileName) + '">&nbsp;<a href="#" onclick="AttachmentUtil.viewFile(\'' + id + '\', \'' + (files[i].index-1) + '\')">' + files[i].clientFileName + '</a> (' + AttachmentUtil.getKB(files[i].fileSize) + ')';
		}
		html += '</div></li>';
	}
	html += '</ul>';
	$('#' + id + ' div.file-list-div').html(html);
	
	// 첨부파일 삭제 이벤트 bind
	$('#' + id + ' a.attach-filedelete').click(function(e){
		//Logger.debug('index=' + $(this).attr('index'));
		if($(this).attr('isnew') == 'true') {
			document.getElementById(id + '_FileWizard').DeleteAttFile($(this).attr('index')-1, 'title');
		}
		Logger.debug(attachments[id].files[$(this).attr('index')-1].storeFileId);
		var file = attachments[id].files[$(this).attr('index')-1];
		
		if(!file.isNew) attachments[id].deleted.push(file.storeFileId);
		
		files = attachments[id].files.removeAt($(this).attr('index')-1);
		
		var totalSize = 0
		for(var i=0;i<files.length;i++) {
			totalSize += parseInt(files[i].fileSize);
		}
		attachments[id].options.totalSize = totalSize;
		
		attachments[id] = {
			'options' : attachments[id].options,
			'files' : files,
			'deleted' : attachments[id].deleted
		};
		drawList(id, attachments[id].files);
		document.getElementById(id + '_FileWizard').SetAttachedFileSize(attachments[id].options.totalSize);
		Logger.info('current attached size=' + attachments[id].options.totalSize);
	});
}

File = function() {
	return {
		id : null,
		localPath : null,
		serverPath : null,
		fileSize : 0,
		clientFileName : null,
		serverFileName : null,
		isNew : true,
		isEdit: false,
		index : -1,
		sInfo : null,
		storeFileId : null,
		
		toString : function() {
			var str = '';
			str += 'id=' + this.id;
			str += 'localPath=' + this.localPath;
			str += ',serverPath=' + this.serverPath;
			str += ',fileSize=' + this.fileSize;
			str += ',clientFileName=' + this.clientFileName;
			str += ',serverFileName=' + this.serverFileName;
			str += ',isNew=' + this.isNew;
			str += ',isEdit=' + this.isEdit;
			str += ',index=' + this.index;
			str += ',sInfo=' + this.sInfo;
			str += ',storeFileId=' + this.storeFileId;
			return str;
		}
	};
	
};


var AttachmentUtil = {
	getIcon : function(fileName) {
		var ext = fileName.substr(fileName.lastIndexOf(".") + 1);
		ext = ext.toLowerCase();
		if(ext == 'xlsx') ext = 'xls';
		else if(ext == 'docx') ext = 'doc';
		else if(ext == 'pptx') ext = 'ppt';
		if(ext != "xls" &&
			ext != "doc"  &&
			ext != "ppt"  &&
			ext != "gul"  &&
			ext != "htm"  &&
			ext != "html" &&
			ext != "txt"  &&
			ext != "hwp"  &&
			ext != "bc"   &&
			ext != "bmp"  &&
			ext != "dl"   &&
			ext != "exe"  &&
			ext != "gif"  &&
			ext != "ini"  &&
			ext != "jpg"  &&
			ext != "mgr"  &&
			ext != "mpg"  &&
			ext != "pdf"  &&
			ext != "tif"  &&
			ext != "xml"  &&
			ext != "wav"  &&
			ext != "zip" ) {
				ext = "etc";
		}
		return "/attach_" + ext + ".gif";
	},
	getKB : function (fileSize) {
//		var sFileSize = fileSize.toString();
//		if (sFileSize.indexOf('(') > -1) {
//			return sFileSize.substring( sFileSize.indexOf('(') + 1, sFileSize.indexOf(')') );
//		}
		if (fileSize < 1024) {
			return fileSize + ' B';
		} else {
			try{
				return Math.round(parseInt(fileSize)/1024) + ' KB';
			}catch(e){
				Logger.error('AttachmentUtil.getKB():' + e.message);
				return fileSize;
			}
			
		}
	},
	getCurrentDate : function() {
		var d = null;
		try {
			d = (new Date()).format('yyyy-mm-dd hh:nn:ss a/p');
//			return new Date();
		}catch(e) {
			Logger.error('AttachmentUtil.getCurrentDate():' + e.message);
		}
		return d;
	},
	parseAttachString : function(attString) {
		//Logger.debug(attString);
		var aFiles = attString.split(del31);
		var files = new Array();
		for(var i=0;i<aFiles.length;i++) {
			//Logger.debug(i + '=' + aFiles[i]);
			var aFile = aFiles[i].split(del29);
			if(aFile.length>1){
				var file = new File();
				file.clientFileName = aFile[1];
				file.localPath = aFile[2].replace(/\\/g,'\\\\');
				file.fileSize = aFile[8];
				file.index = i;
				file.storeFileId = '';
				file.sInfo = aFiles[i];
				//for(var j=0;j<aFiles.length;j++) {
				//	Logger.debug('&nbsp;&nbsp;' + j + '=' + aFile[j]);
				//}
				//Logger.debug(file.toString());
				files.push(file);
			}
		}
		return files;
	},
	displayLocalFile : function(id, filePath) {
		document.getElementById(id + '_FileWizard').ShellExecuteWeb(filePath);
	    return;
	},
	escapeQuote : function(str) {
		return str.replace(/\'/g,"\\'");
	},
	getServerFiles : function(id) {
		var r = new Array();
		if(attachments[id]) {
			for(var i=0;i<attachments[id].files.length;i++) {
				if(!attachments[id].files[i].isNew) {
					r.push(attachments[id].files[i]);
				}		
			}
		}
		return r;
	},
	viewFile : function(id, index) {
		Logger.debug('viewFile():index=' + index);
		var file = attachments[id].files[index];
		var sInfo = '';
		sInfo += 1 + del31;
		sInfo += del29 + file.clientFileName;
		sInfo += del29 + del29; // 로컬 패스는 생략
		sInfo += attachments[id].options.tempDir + '/' +file.serverFileName;
		sInfo += del31;
		
		//Logger.debug('viewFile():' + attachments[id].options.tempDir + '/' +file.serverFileName);
		
		var params = 'attachmentId=' + attachments[id].options.attachId + '&seq=' + index;
		callJson('attachmentController', 'download', params, function(json){
			document.getElementById(id + '_FileWizard').ViewFile("F", sInfo);	
		});
		
	},
	pluginLoad : function(id){
		var FC_Browser_Version = navigator.userAgent;
		var FileWiz_Version = '2,1,9,33';
		id += '_FileWizard';
		var html = '';
		if (new RegExp(/MSIE/).test(FC_Browser_Version)) {
		    html += '<object id="' + id + '" name="' + id + '" style="width:0px;height:0px"';
		    html += '        classid="clsid:B84E12B0-B248-4371-A95A-EC943670DCFC"';
		    html += '        codeBase="/ep/luxor/ref/plugins/ACUBEFileCtrl(V'+FileWiz_Version+').cab#version='+ FileWiz_Version + '">';
		    html += '</object>';
		} else {
			var ret = false;
			var mimetype0 = navigator.mimeTypes["application/acubefilectrl"];
			var mimetype1 = navigator.mimeTypes["Application/AcubeFileCtrl"];
			if (mimetype0) {
				var plugin = mimetype0.enabledPlugin;
				if (plugin) {
					ret = true;
				}
			} else if (mimetype1) {
				var plugin = mimetype1.enabledPlugin;
				if (plugin) {
					ret = true;
				}
			}
			if (ret == false) {
				html += 'window.open("/ep/luxor/ref/plugins/ACUBEFileCtrl(V'+FileWiz_Version+').exe", "_self")';
			} else {
				html += '<embed id="'+id+'" type="Application/AcubeFileCtrl" style="width:0px;height:0px" />';
			}
		}
		
		//Logger.debug(html.escapeHTML());
		return html;
	}
};