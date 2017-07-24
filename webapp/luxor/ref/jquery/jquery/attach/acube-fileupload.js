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

	//- 삭제를 제외한 추가 및 기존에 저장된 첨부파일을 저장하는 배열 변수
	var attachFileArray = [];
	
	//- 삭제한 첨부파일을 저장하는 배열 변수
	var deleteFileArray = [];
	
	//- 첨부한 전체 파일 크기
	var attachFileSize = 0;
	
	//- 첨부파일 정보 
	var attachVO = null;
	
	//- 파일 저장후 호출되는 callback 메소드를 저장하는 변수
	var attachCallBackFunction = null;
	
	//- 첨부한 파일 갯수
	var attachFileCount = 0;
	
	var attachTypeType = /.+$/i;
	
	//onChange시 사용할 callback 메소드를 저장하는 변수
	var callBackMethod = null;
	
	//---------------------------------------------------------------
	//- 기능 : 처음 시작할때 첨부파일에 대한 이벤트를 설정한다. 
	//---------------------------------------------------------------
	jQuery(document).ready(function () {
		
		attachFileCount = 0;
		attachFileArray = [];
		deleteFileArray = [];
	
	    'use strict';
	    $('#fileupload').show();
	    //- 첨부파일 초기화 및 이벤트 메소드 설정
	    $('#fileupload').fileupload({	    
	    	url: context_root + '/servlet/upload', 
			singleFileUploads: 'true', 
			sequentialUploads: 'true',
			acceptFileTypes: attachTypeType,
			unacceptUse: att_cfg.unAcceptUse
		}).bind('fileuploaddone', function(e, data) {
			//- files submit할때 첨부파일의 업로드가 끝나고 첨부된 파일 정보만 파라미터로 넘어온다. 
			attachVO = {
				"tenantId" : tenantId,
				"portalId" : portalId,
				"fileFid" : fileFid,
				"fileSeq" : attachFileArray.length,
				"fileSfid" : "",
				"fileLocalName" : data.result[0].name,
				"fileServerName" : data.result[0].name_temp,
				"fileServerPath" : attachTempUploadPath + "/" + data.result[0].name_temp,
				"fileSize" : data.result[0].size,
				"fileType" : data.result[0].type,
				"fileStatus" : "add",
				"moduleType" : moduleType
			};			
			
			attachFileArray.push(attachVO);

	    }).bind('fileuploadstop', function (e) {
			//- submit을 수행해서 첨부파일이 업로드가 모두 끝나면 호출되는 메소드.
	    	saveAttachFile();
	    }).bind('fileuploaddestroy', function (e, data) {
			//- 파일 저장서버에 저장된 첨부파일을 삭제할때 호출되는 메소드.
			attachFileDelete(data.context.data("tmplItem").data.storeFileId);
		}).bind('fileuploadadd', function (e, data) {
			//- 파일을 첨부할때마다 호출되는 메소드
			attachFileCount++;
			$('.fileupload-content').show('fast'); //add 2012.06.06
			
			if(callBackMethod != null) {
				attachVO = { "fileInput" : data.fileInput };
				
				callBackMethod();
			}
		});
		
	    //- 첨부된 파일에서 '취소' 클릭하면 추가한 파일 갯수를 감소한다. 
	    $(".fileupload-content").delegate("#attachFileCancel", "click", function(e) {
	    	attachFileCount--;
	    	if (attachFileCount <= 0) {
	    		$('.fileupload-content').hide('normal'); //add 2012.06.06
	    	}
	    });
	    
	    //- '다운로드' 클릭하면 파일을 다운로드한다. 
	    $(".fileupload-content").delegate("#attachFileDownload", "click", function(e) {
	    	// location.href = $(this).closest('.template-download').find('.name a').attr('href');
	    	// 페이지에 바로 쏘기 때문에 페이지를 먹어버리므로 아래와 같이 변경... 이벤트 막기 위해 return false
	    	util.popup($(this).closest('.template-download').find('.name a').attr('href'),{width:10,height:10});
	    	return false;
	    });
	    
	    displayAttachFile();
	});
	
	//---------------------------------------------------------------
	//- 기능 : 변경된 첨부파일 정보를 등록한다.
	//---------------------------------------------------------------
	function saveAttachFile() {
		//- 현재 첨부파일 정보를 복사한다. 
		var attachArray = attachFileArray.slice();

		//- 삭제된 첨부파일 정보가 있으면 복사한다.
		var dataCount = deleteFileArray.length;
		for(var x=0; x < dataCount; x++) {
			attachArray.push(deleteFileArray[x]);
		}	
		
		var baseVO = { 
			"tenantId" : tenantId,
			"portalId" : portalId,
			"fileFid" : fileFid,
			"attachType" : attachInfo.attachType, //@add 업로드 형식 2013.11.27 - 확장자 및 사이즈 체크
			"maxFileSize" : attachInfo.uploadAttachmax, //@add 전체 파일 사이즈 2013.11.27 - 확장자 및 사이즈 체크
			"attachFileTypes" : '' + attachInfo.attachTypeType, //@add 허용확장자 형식 2013.11.27 - 확장자 및 사이즈 체크
			"unAcceptUse" : attachInfo.unAcceptUse //@add 지정확장자를 허용할지 거부할지 여부 2013.11.27 - 확장자 및 사이즈 체크
		}; 		
		
		attachArray.unshift(baseVO);
		var paramData = { "attachFileList" : attachArray };
		
		//- 업로딩중 표시 
		var $loadingIndicator = $("<img/>").attr({ 
			"src": context_root + "/collaboration/ref/images/attachment/attach_loading.gif",
			"alt": "Uploading. Please wait..."
		})
		.css({
			position: "relative",
			left: "130px"
		})
		.appendTo(".fileupload-content");
		var actionURL = context_root + "/common/ComFileAttach.do?method=apply";
		$.ajax({
			url: actionURL, 
		    type: "POST",
		    dataType: "text",
		    data: JSON.stringify(paramData),
		    contentType: "application/text; charset=utf-8",
		    success: function(data) {
				//- 업로딩중 표시 삭제
				$loadingIndicator.remove();
				if(attachFileArray.length == 0) {
					callBackFunction('');
				} else {
					callBackFunction(data);
				}

			},
		    error: function(xhr, txt, err) {
				//- 업로딩중 표시 삭제
				$loadingIndicator.remove();
				
				callBackFunction("false");				
		    }   			
		});		
	}
	
	//---------------------------------------------------------------
	//- 기능 : 첨부된 파일을 보여준다.
	//---------------------------------------------------------------
	function displayAttachFile() {
		var fileVO = null;
		
		//- 화면에 보이는 첨부화면 템플릿을 적용하기 위해서 필요한 배열 변수.
		var tmplFileArray = [];
		attachFileSize = 0;
		
		var baseVO = { 
			"method" : "displayAttachFile",	
			"tenantId" : tenantId,
			"portalId" : portalId,
			"fileFid" : fileFid
		}; 			
		
		var paramData = decodeURIComponent($.param(baseVO)); 
		var actionURL = context_root + "/common/ComFileAttach.do";
		
		//- 로딩중 표시 
		var $loadingIndicator = $("<img/>").attr({ 
			"src": context_root + "/collaboration/ref/images/attachment/attach_loading.gif",
			"alt": "Loading. Please wait..."
		})
		.css({
			position: "relative",
			left: "130px"
		})
		.appendTo(".fileupload-content");
		$.ajax({
			type:'POST',
			url:actionURL,
			data:paramData,
			dataType:'text',
			success:function(data) {
				//- 로딩중 표시 삭제
				$loadingIndicator.remove();
				if(data == "NoDBData") {
					//- 현재 첨부파일 정보가 없습니다.	
					$('.fileupload-content').hide('fast'); //add 2012.06.06
				} else {
					var dataArray = data.split("$&$");//FF에서 object로 인식되어 split불가하여 post방식에서 ajax로 변경(dataType지정)
					var dataArrayCount = dataArray.length - 1;
					
					for(var x=0; x < dataArrayCount; x++) {
						var obj = JSON.parse(dataArray[x]);
						
						obj.fileSeq = x;
						obj.fileServerPath = attachTempUploadPath + "/" + obj.fileServerName;
						attachFileArray.push(obj);
						
						//- 화면에 보이는 템플릿 첨부 화면에 필요한 정보 설정
						fileVO = new Object();
						
						fileVO.id   = obj.fileFid;
						fileVO.size = obj.fileSize;
						fileVO.name = obj.fileLocalName;
						fileVO.name_temp = obj.fileServerName;
						fileVO.isEdit = true;
						fileVO.isNew  = false;
						fileVO.index  = x;
						fileVO.storeFileId = obj.fileSfid;
						fileVO.thumbnail_url = context_root + "/collaboration/ref/images/attachment" + AttachmentUtil.getIcon(obj.fileLocalName);
						fileVO.sizef = AttachmentUtil.getKB(obj.fileSize); // @mod 사이즈 체크 부분 수정 2013.10.17
						tmplFileArray.push(fileVO);
						attachFileSize += parseInt(obj.fileSize);
					}
					
			        var fu = $('#fileupload').data('fileupload');
			        fu._adjustMaxNumberOfFiles(-tmplFileArray.length);
			        
			        $('#fileupload .files').html("");
			        fu._renderDownload(tmplFileArray).appendTo($('#fileupload .files')).fadeIn(function () {
			        	// Fix for IE7 and lower:
			            $(this).show();
			            
			        	$('.template-download').each(function() {
			        		//- href 속성 -> http://dit2-pc:8080/luxor_collaboration/servlet/upload/file/serverPath/ceb95c74-75f6-4146-abe7-b59b0ad25886.jpg/1.jpg
			        		$(this).find('.name a').attr('href', basePath + '/servlet/upload/file/serverPath/' + $(this).find('.name').attr('id') + '/' + $(this).find('.name a').html());
			        	});
			        });	
				}
			}	
		});
	}
	
	//---------------------------------------------------------------
	//- 기능 : 파일 이미지 및 파일 사이트를 구한다.
	//---------------------------------------------------------------
	var ext_type = 'bak|bmp|doc|exe|gif|gul|hlp|htm|html|hun|hwp|ini|jar|jpg|jpeg|mpg|pdf|png|ppt|pptx|psd|swf|text|tif|ttf|txt|wav|wmf|word|xls|xlsx|xml|zip|';
	var AttachmentUtil = {
		getIcon : function(fileName) {
		//- 파일 확장자를 구하여 첨부파일 이미지를 리턴한다.
			var ext = fileName.substr(fileName.lastIndexOf(".") + 1);
			ext = ext.toLowerCase();
			
			// 확장자가 지정되지 않은 파일 아이콘 처리	
			if(ext_type.indexOf(ext) == -1){
				ext = 'etc';
			}

			if(ext.indexOf("xlsx") > -1) {
				ext = 'xls';
			} else if(ext.indexOf("docx") > -1) {
				ext = 'doc';
			} else if(ext.indexOf("pptx") > -1) {
				ext = 'ppt';
			} else if (ext.indexOf('jpeg') > -1) {
				ext = 'jpg';
			} else if(ext.indexOf("class") > -1 || ext.indexOf("java") > -1) {
				ext = "etc";
			} else if(ext.indexOf("mp3") > -1 || ext.indexOf("mp4") > -1) {
				ext = "wav";
			} 
			return "/attach_" + ext.replace(/(^\s*)|(\s*$)/g, "") + ".gif";
		},
		getKB : function(fileSize) {
		//- 파일 사이지를 구한다.
			if(fileSize < 1024) {
				return fileSize + ' B';
			} else {
				try {
					return Math.round(parseInt(fileSize) / 1024) + ' KB';
				}catch(e) {
					return fileSize;
				}
			}
		},
		escapeQuote : function(str) {
			return str.replace(/\'/g,"\\'");
		}
	};	
	
   	//===============================================================
   	//- 기능 : 배열의 특정 인덱스의 값 삭제
   	//===============================================================
	function arrayRemoveByIndex(arrayName, arrayIndex){ 
		arrayName.splice(arrayIndex, 1); 
	}	
	
	//---------------------------------------------------------------
	//- 기능 : 첨부파일을 업로드할때 호출하는 메소드
	//- 파라미터 : callback - 콜백 함수
	//---------------------------------------------------------------
	function startFileUpload(callback) {
		try {
			if(callback != undefined) {
				attachCallBackFunction = callback;
			} else {
				attachCallBackFunction = null;
			}

			//- 첨부 파일을 업로드한다.
			if(attachFileCount == 0) {
				if(deleteFileArray.length > 0) {
					//- 첨부파일을 삭제만 했을경우 호출한다.
					saveAttachFile();
				} else {
					//- 2012.06.14 수정시 첨부수정없을 경우처리
					if (fileFid != '') {
						callBackFunction(fileFid);
					} else {
						callBackFunction('');
					}
				}
			} else {
				//- 첨부파일을 추가 및 삭제 등의 행동을 했을경우 호출.
				//- fileuploaddone 이벤트 -> fileuploadstop 이벤트 순으로 호출한다.
				$(".fileupload-buttonbar .start").click();
			}
		}catch(e) {
			alert(e.description);
		}finally {
		}
	}
		
	//---------------------------------------------------------------
	//- 기능 : 콜백 함수를 첨부파일을 업로드할때 호출하는 메소드
	//- 파라미터 : attachResult - true, false 결과 문자열
	//- 파라미터 : fileFid - 파일 Fid
	//---------------------------------------------------------------
	function callBackFunction(attachResult, fileFid) {
		try {
			if(attachCallBackFunction != null) {
				attachCallBackFunction(attachResult, fileFid);
			}
		} catch(e) { }
	}
	
	//---------------------------------------------------------------
	//- 기능 : 첨부된 파일을 삭제하면 호출하는 메소드
	//---------------------------------------------------------------	
	function attachFileDelete(fileSfid) {
		var attachCount = attachFileArray.length;
		var deleteIndex = -1;
		
		for(var x=0; x < attachCount; x++) {
			var attachData = attachFileArray[x];
			
			if(fileSfid == attachData.fileSfid) {
				deleteIndex = x;
				break;
			}
		}

		if(deleteIndex != -1) {
			//- 첨부 파일 배열에서 정보를 가져와서 delete 표시를 한다.
			var deleteObj = attachFileArray[deleteIndex];
			deleteObj.fileStatus = "delete";
			
			//- 삭제된 첨부 파일 배열에 저장하고 첨부 파일 배열에서 삭제한다.
			deleteFileArray.push(deleteObj);
			arrayRemoveByIndex(attachFileArray, deleteIndex);					
		}
	}
	
	//---------------------------------------------------------------
	//- 기능 : 업로드할 파일 갯수를 제한한다.
	//---------------------------------------------------------------	
	function setUploadFileCount(uploadFileCount) {
		$('#fileupload').fileupload('option', 'maxNumberOfFiles', uploadFileCount);
	}
	
	//---------------------------------------------------------------
	//- 기능 : 업로드할 파일 확장자를 지정한다.
	//- acceptFileType : regular expression 
	//---------------------------------------------------------------	
	function setAcceptFileType(acceptFileType) {
		//var acceptFileType = /(\.|\/)(gif|jpe?g|png)$/i;
		attachTypeType = acceptFileType;
		$('#fileupload').fileupload('option', 'acceptFileTypes', acceptFileType);
	}
	
	//---------------------------------------------------------------
	//- 기능 : 콜백function을 지정한다.(onChange시 사용)
	//---------------------------------------------------------------
	function setCallBack(callBackName) {
		callBackMethod = callBackName;
	}
	