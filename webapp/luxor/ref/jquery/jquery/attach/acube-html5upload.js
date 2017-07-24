$(document).ready( function(){
	h5Uploader.init(
		{
			attachId:'lxrFiles',
			attachListId:'lxrFileList'
		}		
	);		
});

var h5Uploader = {
		attachFileArray:[],//- 삭제를 제외한 추가 및 기존에 저장된 첨부파일을 저장하는 배열 변수
		deleteFileArray:[],//- 삭제한 첨부파일을 저장하는 배열 변수
		attachFileMap : null,
		attachFileCount : 0,
		attachFileSize : 0,
		attachFileStatus : null,
		attachCallback : null,
		attachId:null,
		attachListId:null,
		attachExtType:null,

		init : function(options) {
			var that = this;
			var defaultoptions = {
				attachCallback : function() {},
				attachId:'lxrFiles',
				attachListId:'lxrFileList',
				attachExtType:'bak|bmp|doc|exe|gif|gul|hlp|htm|html|hun|hwp|ini|jar|jpg|jpeg|mpg|pdf|png|ppt|pptx|psd|swf|text|tif|ttf|txt|wav|wmf|word|xls|xlsx|xml|zip|',
			};
			options = $.extend(defaultoptions, options);
			
			$('.html5-fileupload-button').button({
				icons: { primary: 'ui-icon-plus' },
				disabled: false
			});
			$('.html5-fileupload-select-all').button({
				icons: { primary: 'ui-icon-triangle-1-s' },
				disabled: false
			}).click(function() {
				that.selectAll();
			});
			$('.html5-fileupload-select-del').button({
				icons: { primary: 'ui-icon-minus' },
				disabled: false
			}).click(function() {
				that.selectFileDelete();
			});
			
			this.attachCallback = options.attachCallback;
			this.attachId = options.attachId;
			this.attachListId = options.attachListId;
			this.attachExtType = options.attachExtType; 
			var attachlist = document.getElementById(this.attachListId);
			if (attachInfo.mode == 'add' || attachInfo.mode == 'mod') {	
				attachlist.addEventListener('dragenter', function(evt) {
					evt.preventDefault();
					evt.stopPropagation();
					evt.dataTransfer.dropEffect = 'copy';
				}, false);
				attachlist.addEventListener('dragleave', function(evt) {
					evt.preventDefault();
					evt.stopPropagation();
				}, false);
				attachlist.addEventListener('dragover', function(evt) {
					evt.preventDefault();
					evt.stopPropagation();
					evt.dataTransfer.dropEffect = 'copy';
				}, false);
				attachlist.addEventListener('drop', h5Uploader.mouseDrop, false);
			}
			this.attachFileMap = new HashMap();
			this.attachFileStatus = 'start';
			$('#' + this.attachId).bind('change', function() {
				h5Uploader.addFile();
			});
			var tab = document.getElementById('filesListTable');
			if (tab == null) { 
				tab = document.createElement('table');
				tab.setAttribute('id', 'filesListTable');
				tab.setAttribute('width', '100%');
				tab.setAttribute('border-collapse', 'collapse');
				var tbo = document.createElement('tbody');
				tbo.setAttribute('id', 'filesListTable_body');
				tab.appendChild(tbo);
				attachlist.appendChild(tab);
			} 
			this.showFile();
		},
		showFile : function() {
			var that = this;
			var params = { 
				tenantId : attachInfo.tenantId,
				portalId : attachInfo.portalId,
				fileFid : attachInfo.fileFid
			}; 		
			$.ajax({
				type:'post',
				url:attachInfo.basePath + '/common/ComFileAttach.do?method=showFile',
				data:params,
				dataType:'json',
				success:function(data) {
					$.each(data.files, function(key, val) {
						var options = {
							flname : val.fileLocalName,
							fsname : val.fileServerName
						}
						that.drawFileList(val.fileSfid, val.fileSize, val.fileLocalName, options);
						that.attachFileArray.push({
							tenantId : attachInfo.tenantId,
							portalId : attachInfo.portalId,
							fileFid : val.fileFid,
							fileSeq : val.fileSeq,
							fileSfid : val.fileSfid,
							fileLocalName : val.fileLocalName,
							fileServerName : val.fileServerName,
							fileServerPath : attachInfo.attachTempUploadPath + '/' + val.fileServerName,
							fileSize :val.fileSize,
							fileType : val.fileType,
							fileStatus : 'view',
							moduleType : attachInfo.moduleType
						});
					});
					that.adjustFileInfoView();
					that.showFileInfo();
				}	
			});				
		},
		
		addFile : function() {
			var files = document.getElementById(this.attachId).files;
			if (this.checkMaxFileSize(files)) {
				for (var i=0;i<files.length;i++) {
					if (this.checkUnAcceptFileType(files[i])) {
						this.addFileInfo(files[i]);
					}
				}
				this.adjustFileInfo();
				this.showFileInfo();
			}
		},
		addFileInfo : function(fileInfo) {
			var rowId = fileInfo.name + '_' + fileInfo.size;
			if (this.attachFileMap.containsKey(rowId) == false) {// 동일한 파일은 다시 첨부할 수 없게.
				this.attachFileMap.put(rowId, fileInfo);
				this.drawFileList(rowId, fileInfo.size, fileInfo.name);
			}
		},
		drawFileList : function(rowId, filesize, filename, options) {
			var opt = options || {};
			var tbo, row, cell;
			tbo = document.getElementById('filesListTable_body');
			row = document.createElement('tr');
			row.setAttribute('id', rowId);
			row.setAttribute('class', 'html5-fileupload-filerow');
			var loopidx = (attachInfo.mode == 'view') ? 3 : 5; 
			// 하나의 행에 첨부파일 정보를 보여준다.
			// 0 : 체크박스, 1 : 파일 이미지, 2 : 파일 이름, 3 : 파일 크기, 4 : 업로드시 퍼센트 표시, 5 : 삭제 버튼
			for (var x = 0; x <= loopidx; x++) {
				cell = document.createElement('td');
				switch (x) {
				case 0 :
					var chk = document.createElement('input');
					chk.setAttribute('type', 'checkbox');
					chk.setAttribute('id', rowId);
					chk.setAttribute('fs', filesize);
					chk.setAttribute('name', 'chk');
					chk.setAttribute('value', rowId);
					if (attachInfo.mode == 'view') {
						chk.setAttribute('disabled', 'disabled');
					}					
					cell.setAttribute('width', '20px');
					cell.appendChild(chk);
					row.appendChild(cell);
					break;
				case 1 :// 첨부파일 이미지  
					var img = document.createElement('img');
					img.setAttribute('id', rowId + '_img');
					img.setAttribute('width', '16px');
					img.setAttribute('height', '16px');
					img.setAttribute('alt', 'upload_new');
					img.src = attachInfo.basePath + '/collaboration/ref/images/attachment/attach_' + this.getFileIcon(filename);
					cell.setAttribute('width', '18px');
					cell.appendChild(img);
					row.appendChild(cell);
					break;
				case 2 :// 첨부파일 이름 
					cell.setAttribute('width', '*');//50%
					if (attachInfo.mode == 'view') {
						cell.setAttribute("class", "html5-filename");
						cell.innerHTML = "<a target='_blank' href='" + attachInfo.basePath + "/servlet/upload/file/serverPath/" + opt.fsname + "/" + opt.flname + "'>" + filename + "</a>";
					} else {
						cell.appendChild(document.createTextNode(filename));
					}
					row.appendChild(cell);
					break;
				case 3 :// 첨부파일 크기 
					cell.setAttribute('width', '25%');
					cell.setAttribute('nowrap', 'nowrap');
					cell.setAttribute('align', 'right');
					cell.appendChild(document.createTextNode(this.formatFileSize(filesize)));
					row.appendChild(cell);
					break;
				case 4 :// 첨부파일 업로드시 퍼센트 표시
					cell.setAttribute('id', rowId + '_progress');
					cell.setAttribute('nowrap', 'nowrap');
					cell.setAttribute('width', '15%');
					cell.setAttribute('align', 'right');
					cell.appendChild(document.createTextNode(''));
					row.appendChild(cell);
					break;
				case 5 :// 첨부파일 삭제 버튼
					cell.setAttribute('width', '25px');
					if (attachInfo.mode == 'add' || attachInfo.mode == 'mod') {
						cell.innerHTML = "<a href='javascript:h5Uploader.removeFile(\"" + rowId + "\", " + filesize + ");'><img src='" + attachInfo.basePath + "/collaboration/ref/images/attachment/icon_file_delete.gif' alt='delete file' width='20px' height='20px' border='0'></a>";
					} else {
						cell.appendChild(document.createTextNode(''));
					}
					row.appendChild(cell);
					break;
				}
			}
			tbo.appendChild(row);
		},
		adjustFileInfoView : function() {
			this.attachFileSize = 0;
			var attchArr = this.attachFileArray.slice();
			for (var i = 0; i < attchArr.length; i++) {
				this.attachFileSize += attchArr[i].fileSize;
			}
		},
		adjustFileInfo : function() {
			var that = this;
			that.attachFileSize = 0;
			$('input[name=chk]:checkbox').each(function() {
				that.attachFileSize += parseFloat($(this).attr('fs'));
			});
			/*
			var keys = this.attachFileMap.keys();
			this.attachFileCount = keys.length;
			this.attachFileSize = 0;
			for (var x = 0; x < this.attachFileCount; x++) {
				var file = this.attachFileMap.get(keys[x]);
				this.attachFileSize += file.size; 
			}
			*/
		},
		showFileInfo : function() {
			var totalattachcount = $('#filesListTable >tbody >tr').length;
			var fileinfo = $('#file-info');
			if (totalattachcount > 0) {
				fileinfo.html(this.formatFileSize(this.attachFileSize) + ' / ' + this.formatFileSize(attachInfo.uploadAttachmax));
			} else {
				fileinfo.html('');
			}
			totalattachcount = (totalattachcount == 0) ? 1 : totalattachcount;
			$('#' + this.attachListId).css({'height':39 * totalattachcount});// file resize
		},
		attach : function(callback) {
			var that = this;
			if (typeof callback == 'function') {
				this.attachCallback = callback;
			}
			var keys = this.attachFileMap.keys();
			this.attachFileCount = keys.length;
			if (this.attachFileCount == 0) {
				this.attachFileStatus = 'complete';
				this.uploadResult();
			} else {
				for (var i = 0; i < this.attachFileCount; i++) {
					var f = this.attachFileMap.get(keys[i]);
					
					// -- jstor(uploadResult함수 attachJstor)에서 처리위해 배열에 저장 START
					var guid = $.getGuid(22, false, true);
					var sname = this.getServerFileName(f.name, guid); // json 결과를 받지 않고 client에서 미리 생성.
					this.attachFileArray.push({
						tenantId : attachInfo.tenantId,
						portalId : attachInfo.portalId,
						fileFid : '',
						fileSeq : i + 1,
						fileSfid : '',
						fileLocalName : f.name,
						fileServerName : sname,
						fileServerPath : attachInfo.attachTempUploadPath + '/' + sname,
						fileSize : f.size,
						fileType : f.type, // jstor(ComFileAttachController#attachJstor) 저장시 가져오도록.
						fileStatus : 'add',
						moduleType :attachInfo.moduleType
					});
					// -- jstor(uploadResult함수 attachJstor)에서 처리위해 배열에 저장 END
					
					// -- upload json callback 이 최종적으로 한번 발생하므로
					// -- 이 경우 attachFileArray에 json값을 넣을 수 없으므로 위에서 배열에 입력
					// -- 동기방식으로 처리할 경우는 가능함.
					var xhr = new XMLHttpRequest();
					xhr.upload.rowId = f.name + '_' + f.size;
					xhr.upload.addEventListener('loadstart', this.uploadStrart, false);  
					xhr.upload.addEventListener('progress', this.uploadProgress, false);  
					xhr.upload.addEventListener('load', this.uploadEnd, false);  
					xhr.upload.addEventListener('error', this.uploadError, false);  
					xhr.onreadystatechange = function() {
						if (xhr.readyState == 4 && xhr.status == 200) {
							var results = JSON.parse(xhr.responseText);
							if (results.result == 'true') {
								that.attachFileStatus = 'start';
								that.uploadResult();
							} else {
								that.attachFileStatus = 'start';
							}
						} else {
							that.attachFileStatus = 'error';
						}
					};
					xhr.open('post', attachInfo.basePath + '/common/ComFileAttach.do?method=attachFile', true);	
					xhr.setRequestHeader('Cache-Control', 'no-cache'); 
					xhr.setRequestHeader('X-File-Name', encodeURIComponent(f.name));
					xhr.setRequestHeader('X-File-SName', sname);
					xhr.setRequestHeader('X-File-Size', f.size);
					xhr.setRequestHeader('X-File-Seq', i);
					xhr.setRequestHeader('X-File-Guid', guid);
					if (i == this.attachFileCount - 1) {
						xhr.setRequestHeader('X-File-Count', 'X');
					}
					xhr.send(f);
				}
			}
		},
		uploadResult : function() {
			var that = this;
			if (this.attachFileCount > 0 && this.attachFileStatus == 'start') {
				this.uploadCompleted();
				//this.attachFileStatus = 'complete'; // 동기 방식으로 처리 시
			}
			if (this.attachFileStatus == 'complete') {
				this.attachFileStatus = 'end';
				var attachArray = this.attachFileArray.slice();
				if (attachArray.length > 0) {
					attachArray.unshift({
						tenantId : attachInfo.tenantId,
						portalId : attachInfo.portalId,
						fileFid : attachInfo.fileFid,
						attachType : attachInfo.attachType, //@add 업로드 형식 2013.11.27 - 확장자 및 사이즈 체크
						maxFileSize : attachInfo.uploadAttachmax, //@add 전체 파일 사이즈 2013.11.27 - 확장자 및 사이즈 체크
						attachFileTypes : '' + attachInfo.attachTypeType, //@add 허용확장자 형식 2013.11.27 - 확장자 및 사이즈 체크
						unAcceptUse : attachInfo.unAcceptUse //@add 지정확장자를 허용할지 거부할지 여부 2013.11.27 - 확장자 및 사이즈 체크 								
					});
					var deleleArray = this.deleteFileArray.slice();
					var paramData = { 'attachFileList' : attachArray, 'deleteFileList' :  deleleArray};
					$.ajax({
						url: attachInfo.basePath + '/common/ComFileAttach.do?method=attachJstor', 
					    type: 'post',
					    dataType: 'json',
					    data: JSON.stringify(paramData),
					    contentType: 'application/json; charset=utf-8',
					    success: function(data) {
							if (typeof that.attachCallback == 'function') {
								that.attachCallback(data.fid);
							}
						},
					    error: function(request,status,error) {
							//alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
							if (typeof that.attachCallback == 'function') {
								that.attachCallback('');
							}						
					    }   			
					});
				} else {
					if (typeof that.attachCallback == 'function') {
						that.attachCallback('');
					}
				}		
			} else if (this.attachFileStatus == 'error') {
				this.attachFileStatus = 'end';
				if (typeof this.attachCallback == 'function') {
					this.attachCallback('');
				}
			}
		},
		uploadStrart : function(evt) {
		},
		uploadProgress : function (evt) {
			if(evt.lengthComputable) {
				var percentComplete = Math.round(evt.loaded * 100 / evt.total);
				var uploadProgressElement = document.getElementById(this.rowId + "_progress");
				if (uploadProgressElement != null) {
					uploadProgressElement.innerHTML = percentComplete.toString() + "%";
				}
			} 
		},
		uploadEnd : function(evt) {
			var uploadProgressElement = document.getElementById(this.rowId + "_progress");
		    if (uploadProgressElement != null) {
		    	uploadProgressElement.innerHTML = "100%";
			}
		},
		uploadError : function(evt) {
			this.attachFileStatus = 'error';
			var uploadProgressElement = document.getElementById(this.rowId + "_progress");
	        if (uploadProgressElement != null) {
	        	uploadProgressElement.innerHTML = "Error";
			}
		},
		uploadCompleted : function() {
			var uploadSuccessFileCounts = 0;
			var keys = this.attachFileMap.keys();
			for (var x = 0; x < keys.length; x++) {
				var file = this.attachFileMap.get(keys[x]);
				var rowId = file.name + "_" + file.size;
				var uploadProgressElement = document.getElementById(rowId + "_progress");
				if (uploadProgressElement != null) {
					if (uploadProgressElement.innerHTML == '100%') {
						uploadProgressElement.innerHTML = "Complete";
					}
					if (uploadProgressElement.innerHTML == 'Complete') {
						uploadSuccessFileCounts++;
					}
				}
			}
			if (this.attachFileCount == uploadSuccessFileCounts) {
				this.attachFileStatus = "complete";
			}				
		},
		removeFile : function(rowId, fileSize) {
			// 등록, 수정시 새로 추가한 것에 대한 삭제-START
			var deleteTR = document.getElementById(rowId);
			deleteTR.parentNode.removeChild(deleteTR);
			this.attachFileMap.remove(rowId);
			this.attachFileCount--;
			this.attachFileSize -= fileSize;
			this.showFileInfo();
			
			//this.attachFileArray에서 빠진거는 빼야함.
			
			
			// 등록, 수정시 새로 추가한 것에 대한 삭제-END
			// 수정화면시 미리 로드된 것에 대한 삭제(저장서버에서 삭제해야함)-START
			var attachArray = this.attachFileArray.slice();
			for (var i = 0; i < attachArray.length; i++) {
				if (attachArray[i].fileStatus == 'view' && rowId == attachArray[i].fileSfid) {
					attachArray[i].fileStatus = 'delete';
					this.deleteFileArray.push(attachArray[i]);
				}
			}
			// 수정화면시 미리 로드된 것에 대한 삭제(저장서버에서 삭제해야함)-END
		},
        formatFileSize: function (fileSize) {
            if (fileSize >= 1073741824) {
            	return (fileSize / 1073741824).toFixed(2) + ' GB';
            }
            if (fileSize >= 1048576) {
            	return (fileSize / 1048576).toFixed(2) + ' MB';
            }
            if (fileSize >= 1024) {
            	return (fileSize / 1024).toFixed(2) + ' KB';
            }            
            return (fileSize).toFixed(2) + ' B';
        },	
        getServerFileName : function(fileName, guid) {
			var ext = fileName.substring(fileName.lastIndexOf('.'));
			return guid + ext;
        },
		getFileIcon : function(fileName) {
			var ext = fileName.substr(fileName.lastIndexOf('.') + 1);
			ext = ext.toLowerCase();
			if(this.attachExtType.indexOf(ext) == -1) {// 확장자가 지정되지 않은 파일 아이콘 처리
				ext = 'etc';
			}
			if(ext.indexOf('xlsx') > -1) {
				ext = 'xls';
			} else if(ext.indexOf('docx') > -1) {
				ext = 'doc';
			} else if(ext.indexOf('pptx') > -1) {
				ext = 'ppt';
			} else if (ext.indexOf('jpeg') > -1) {
				ext = 'jpg';
			} else if(ext.indexOf('class') > -1 || ext.indexOf('java') > -1) {
				ext = 'etc';
			} else if(ext.indexOf('mp3') > -1 || ext.indexOf('mp4') > -1) {
				ext = 'wav';
			} 
			return ext.replace(/(^\s*)|(\s*$)/g, '') + '.gif';
		},			
		selectAll : function() {
			$('input[name=chk]:checkbox').each(function() {
				$(this).attr('checked', 'checked');
			});
		},
		selectFileDelete : function() {
			var that = this;
			var cnt = $('input[name=chk]:checkbox:checked').length;
			if (cnt < 1) {
				//alert('선택하세요.');
				return;
			}
			$('input[name=chk]:checkbox:checked').each(function() {
				that.removeFile($(this).val(), $(this).attr('fs'));
			});
		},
		checkMaxFileSize : function(files) {
			var msz = 0;
			$('input[name=chk]:checkbox').each(function() {
				msz += parseFloat($(this).attr('fs'));
			});
			for (var i=0;i<files.length;i++) {
				msz += files[i].size;
			}
			if (msz > attachInfo.uploadAttachmax) {
				alert(attachInfo.uploadAttachmaxMsg);
				return false;
			}
			return true;
		},
		checkUnAcceptFileType : function(file) {
			var filename = file.name;
			filename = filename.substring(filename.lastIndexOf("\\")+1);
			var filetype = filename.substring(filename.lastIndexOf(".")+1).toLowerCase();
			var un_accept_type = attachInfo.unAcceptedFileTypes;
			un_accept_type = (un_accept_type == 'undefined' || un_accept_type == null) ? '' : un_accept_type.toLowerCase();
			if (un_accept_type == '') {
				return true;
			}
			var un_accept_type_check = false;
			if (un_accept_type != '') {
				var un_accept_types;
				if (un_accept_type.indexOf('|') > 0) {
					un_accept_types = un_accept_type.split('\|');
				} else {
					un_accept_types = un_accept_type.split(',');
				}
				for (var i = 0; i < un_accept_types.length; i++){
					if (un_accept_types[i].indexOf(filetype) >= 0) { 
						un_accept_type_check = true;
						break;
					}
				}
				if (un_accept_type_check) {
					alert(lxr.att.attach_03.format(un_accept_types.join(', ')));
					return false;
				}
			}
			return true;
		},		
		mouseDrop : function (evt) {
			var that = this;
			evt.stopPropagation();
	    	evt.preventDefault();
			var files = evt.dataTransfer.files;
			if (that.checkMaxFileSize(files)) {
				for (var i=0;i<files.length;i++) {
					if (!files[i]) {
						continue;
					}
					if (that.checkUnAcceptFileType(files[i])) {
						that.addFileInfo(files[i]);
					}
				}
				that.adjustFileInfo();
				that.showFileInfo();
			}
		}
	};

	function startFileUpload(callback) {
		h5Uploader.attach(callback);		
	};