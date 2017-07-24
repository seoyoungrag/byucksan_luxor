//################# 공통 전역 변수 설정 시작
var alertDebug = false;//- 첨부파일 등록시에 메서드명 및 설명이 alert로 호출하면 변수 (true : 호출 , false : 없음 )
var selFileMaxSize = 0;//- 등록된 파일의 총용량을 확인.
var selCurrentFileMaxSize = 0;//- 상세보기, 수정시에 기존에 등록된 파일의 총량을 설정하는 변수
//*[중요1] 등록시에는 attachFileArray 선택된 파일을 ajax로 올려서 업로드 처리된 파일 정보를 받아서 VO 객체에 생성하고 담아서 서버 페이지에 보내준다.
//*[중요2] 수정시에 기존에 첨부파일정보를 attachFileArray 모두 담고 기존 첨부 파일이 삭제되면 attachFileArray 에서는 선택된 정보를 Vo객체를 삭제하고  
//deleteFileArray 에 fileStatus에 delete로 설정해서 삭제된 정보를 넣어서 수정시에 수정 페이지(JSP)에 넘겨준다.
var attachFileArray = [];//-첨부 및 삭제할 파일의 정보를 담는 배열 변수 ( 상세정보 & 수정 시에 등록된 파일에 정보를 담는다 .)
var deleteFileArray = [];//- 삭제한 첨부파일을 저장하는 배열 변수
var _multiSelect = false;//다중 선택이 가능한지를 확인한다.
var _allFileType = false;//업로드 파일 타입을 전체(*.*)으로 선택했는지 확인하는 변수
var _fileType = "";//익스플러러가 아닐경우 내부적으로 연산에서 사용할 변수
var _fileDescSwf = "*.*";//file upload flash에 설정한 파일 타입 설명 변수
var _fileTypeSwf = "*.*";//file upload flash에 설정한 파일 타입 변수
var _fileTypeArray = [];//파일 타입을 확인하기 위해서 사용자가 설정한 파일 확장자를 배열 현채로 보관하는 변수
if( navigator.appName.indexOf("Microsoft") > -1 ){ 
	//if( navigator.appVersion.indexOf("MSIE 6") > -1){ /*익스 플로러이면 버전 6인지 확인*/  }else if(navigator.appVersion.indexOf("MSIE 7") > -1){  /*익스 플로러이면 버전 7인지 확인*/ } 
	//falsh에 설정한 확장자 정보를 연산용 변수에 설정한다.
	_fileType = _fileTypeSwf;
	_multiSelect = true;
} else { 
	var agt = navigator.userAgent.toLowerCase();	
	if (agt.indexOf("chrome") != -1) {
		_fileType = _fileTypeSwf;
		_fileDescSwf = "*.*";
		_fileTypeSwf = "*.*";	
		_multiSelect = true;
	} else if (agt.indexOf("firefox") != -1) {
		_fileType = _fileTypeSwf;
		_multiSelect = false;
	} else {
		_fileType = _fileTypeSwf;
		_fileDescSwf = "*.*";
		_fileTypeSwf = "*.*";	
		_multiSelect = false;
	}
}
//################# 공통 전역 변수 설정 종료

$(document).ready(function() {
	_fileTypeSwf = (attachInfo.uploadifyAttachType == null || attachInfo.uploadifyAttachType == '') ? '*.*' : attachInfo.uploadifyAttachType;
    var _type = "UserVar";
    var _age = 20;
	_fileTypeArray = _fileType.split(";");//파일 타입 체크를 위해서 확장자 배열을 생성.(업로드 가능한 파일정보를 배열에 보관.)
	for(var x=0; x < _fileTypeArray.length; x++) {//업로드 가능한 확장자중에 모든파일(*.*)이 있는지를 확인하고 _allFileType 값(true, false)를 설정.
		if(_fileTypeArray[x] == '*.*' ){
			_allFileType = true;
			if(alertDebug){ alert('[onload] 확장자 구분없이 *.* 파일을 업로드 할 수 있도록 설정'); }
		}
	}	
	
	//버튼을 설정한다.
	$('.fileuploadify-fileupload-button').button({		
		icons: { primary: 'ui-icon-plus' },
		disabled: false
	});	
		
	$('.fileuploadify-fileupload-select-all').button({
		icons: { primary: 'ui-icon-triangle-1-s' },
		disabled: false
	}).click(function() {
		cmdAllSel();
	});
	$('.fileuploadify-fileupload-select-del').button({
		icons: { primary: 'ui-icon-minus' },
		disabled: false
	}).click(function() {
		cmdSelFileDel();
	});
	//버튼을 설정한다 End.
	
	//** 버튼을 설정한 후에 flash 설정을 처리해아한다.
	
	$("#custom_file_upload").uploadify({
		auto: false,						//파일을 올리자 마자 등록처리.
		//buttonClass: "main_btn", 	//버튼에 대한 스타일 정보를 설정.		
		//hideButton : true,                                
		wmode     : 'transparent',
		swf: attachInfo.basePath + '/collaboration/ref/jquery/jquery/attach/jquery.uploadify.v2.1.4.swf', 	//Swf 파리에 대정의를 설정.
		buttonCursor: 'arrow',	
		buttonImg: " ",
		buttonText: lxr.att.attach_btn,//버튼의 텍스트정보를 설정.	
		debug: false,							//debug 처리하면 화면에 textArear가 생기고 log정보를 출력.
		fileObjName: 'fileData',				//하단의 input type=file에 대한 이름을 정의.
		fileSizeLimit: attachInfo.uploadAttachmaxKB,	//한파일이 Upload할수 있는 용량을 설정. ( 총 업로드 용량도 같이 설정. )
		fileTypeDesc: _fileDescSwf,							//파일업로드 할수 있는 형태를 정의.	
		//fileTypeExts: "*.txt;*.xls;*.xlsx;*.docx;*.*", 	//파일 업로드 할수 있는 형태에 대한 설명을 정의.
		fileTypeExts: _fileTypeSwf, 							//파일 업로드 할수 있는 형태에 대한 설명을 정의.
		formData: { 'type': _type, 'age': _age },			//파일 업로드시에 JSP 페이지에 넘기는 이름, 값을 선언.
		queueID:'queueDiv',   								//파일 큐가 생성될 div의 이름을 정의.
		/* 첨부파일에 대한 디자인 정보를 설정한다.
		itemTemplate: '<div style="background-color:#aaa;color:red;width:350px;" id="${fileID}" class="uploadify-queue-item">\
	                <div class="cancel">\
	                    <a href="javascript:$(\'#${instanceID}\').uploadify(\'cancel\', \'${fileID}\')">X</a>\
	                </div>\
	                <span class="fileName">${fileName} (${fileSize})</span><span class="data"></span>\
	            </div>', */
		multi: _multiSelect,			//파일 선택시에 다중선택 가능 여부
		method: 'post',			//파일 업로드시에 Method 형태 (get, post)
		//overrideEvents: ["onSelectError","onDialogClose"],
		uploader: attachInfo.basePath + '/servlet/upload/',		//업로드될 Jsp 페이지 Url 정보
		preventCaching: true,
		progressData: 'speed',
		queueSizeLimit: 10,		//등록할수 queue 있는 최대파일수
		removeComplete: true,	//Set to false to keep files that have completed uploading in the queue
		removeTimeout: 10,		//The delay in seconds before a completed upload is removed from the queue
		requeueErrors: true,	//If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.        
		successTimeOut: 5,
		uploadLimit:10,			//등록 할 수 있는 최대 파일수
		width: 65,				//등록 버튼의 width
		height: 20,				//등록 버턴의 height
		onCancel: function (file) {
			if(alertDebug) { alert(' [onCancel 1/1 ] 파일을 삭제시에 호출돼는 매서드  : '+  file.name +' / '+ file.size +' byte '); }
			//삭제된 파일의 정보의 사이즈 만큰 등록된 총용량의 사이지를 계산하고 빼준다.
			var currentFileSize = 0;
			if(selFileMaxSize > 0){								//만일 현재 등록된 파일의 용량이 0보다 크다면 (파일이 대기돼어 있다면)
				selFileMaxSize  = selFileMaxSize - file.size;	//현재의 파일 용량에서 선택된 파일의 용량만큼 빼준다.	
			}  
			currentFileSize = byteConvert(selFileMaxSize);		//바이트 정보를 kbyte로 설정.
			currentFileSize = currentFileSize +' / '+ byteConvert(attachInfo.uploadAttachmax);
			$('#status-upload-size').text(currentFileSize); 	//하단에 총용량 정보의 정보를 변경.
		},
		onClearQueue: function (queueItemCount) {
			if(alertDebug) { 
				alert("[onClearQueue 1/2 ] 파일 업로드 대기 큐의 정보를 삭제하고 삭제된 카운트 정보를 반환한다. : Del Queue Cnt" + queueItemCount+" . "); 
				alert("[onClearQueue 2/2 ] 큐는 삭제돼지만 화면에 파일정보는 삭제가 돼지 않는다. ps: 이럼 어따 쓰라는거지 ?"); 
			}
		},
		onDestroy: function () {
			if(alertDebug) { alert("[onDestroy 1/1 ] uploadify Swf를 삭제한다. "); }
		},
		onSelect: function (file) {
			if(alertDebug) { 
				alert("[onSelect 1/4 ] 선택된 파일의 정보를 보여주는 매서드 : Select File " + file.name +" . "); 
				alert("[onSelect 2/4 ] 선택된 파일에 대한 확장자를 검색해서 맞지 않으면 메시지 출력후에 파일을 삭제한다.(cancel) ");
				alert("[onSelect 3/4 ] 선택된 파일에 대한 용량을 계산해서 총 업로드 용량이 넘는지 체크한다. 용량이 넘은면 메시지 출력후에 파일을 삭제한다.(cancel) "); 
				alert("[onSelect 4/4 ] 선택된 파일에 대한 용량은 총 업로드 대기 파일 용량(selFileMaxSize)에 합산한다. ( onDialogClose 매서드에서 합산된 용량을 화면에출력한다. ) ");
			}
			//파일을 업로드 목록에 넣고 파일용량 및 확장자를 체크해서 사용이 불가 하면 alert 메시지 후에 삭제.
			//selFileFlag 가 false 값을 가지면 첨부된 파일을 삭제. (cancel)
			var selFileFlag = true;
			//첨부파일 타입이 전체가 아니라면
			if(!_allFileType){
				var selFileArry = file.name.split(".");
				if(	selFileArry.length < 2){//*.* 파일이 아닌데 확장자가 없다면
					alert(lxr.att.attach_04.format(_fileDescSwf));
					//파일 등록여부 false 설정한다.
					selFileFlag = false;
				}//end if(selFileArry.length)	
				if(selFileFlag){ //확장자가 존재한다면
					//등록 여부를 false로 설정한다.
					selFileFlag = false;
					//등록 가능한 확장자정보를 찾아서(for) 현재 업로드 하는 파일 확장자와 동일한지 확인하고
					//동일한 정보가 없다면 파일등록 가능 변수(selFileFlag)에 false를 설정한다.
					for(var x=0; x < _fileTypeArray.length; x++) {
						var _fileTypeDetailArray = _fileTypeArray[x].split(".")
						if(_fileTypeDetailArray[1] == selFileArry[1]){
							selFileFlag = true;	
							break;
						} 				
					}//end for	
					if(!selFileFlag){
						alert(lxr.att.attach_04.format(file.name, _fileType));
					}//파일등록 여부가 false 라면
				}//end if(selFileFlag)
			}//첨부파일 타입이 전체가 아니라면 end
			//확장자 검사를 통과했다면 (실패라면 용량체크를 할필요도 없음)
			if(selFileFlag){ //현재등록된 파일의 총용량을 확인해서 초과하면 메시지를 출력하고 파일 등록여부 변수에 false를 설정
				if( (selFileMaxSize + file.size) > attachInfo.uploadAttachmax ){	//파일 용량이 총용량을 넘는지 체크한다.
					alert(lxr.att.attach_05.format(file.name, '' + byteConvert(selFileMaxSize + file.size)));
					selFileFlag = false;
				}
			}
			//파일등록여부변수에 값을 가지고 파일을 파일들 설정하거나 삭제.
			if(selFileFlag){ 
				selFileMaxSize += file.size;	//넘지 않는다면 용량을 합산.
			} else {
				$('#custom_file_upload').uploadify('cancel',file.id); //현재 파일을 삭제.
				selFileMaxSize += file.size;	//추후에 삭제 로직이 처리되면 용량을 삭제를 체크함으로 삭제는 태우고 선택된 파일에 용량을 합산.
			}
		},
		onSelectError: function (file, errorcode, errormsg) {
			if(alertDebug) { alert("[onSelectError 1/1 ] 파일선택시에 오류가 발생 했을경우 호출 돼는 매서드. "); }
			/*
			QUEUE_LIMIT_EXCEEDED : -100, QUEUE_LIMIT_EXCEEDED : -100, FILE_EXCEEDS_SIZE_LIMIT : -110, FILE_EXCEEDS_SIZE_LIMIT : -110, 
			ZERO_BYTE_FILE : -120, ZERO_BYTE_FILE : -120, INVALID_FILETYPE : -130 INVALID_FILETYPE : -130 
	        */
		},
		onDialogClose: function (queueData) {
			if(alertDebug) { 
				alert("[onDialogClose 1/2 ] 파일 선택창이 닫히면서 호출 돼는 매서드 "); 
			}
			//var file = queueData.files; //현재 큐에서 for문 돌면서 체크해도 될듯함..!
			var currentFileSize = byteConvert(selFileMaxSize);	//바이트 정보를 kbyte로 설정한다.
			currentFileSize = currentFileSize +' / '+ byteConvert(attachInfo.uploadAttachmax);
			$('#status-upload-size').text(currentFileSize); //하단에 총용량 정보의 정보를 변경해준다.
		},
		onDialogOpen: function () {
			if(alertDebug) { 
				alert("[onDialogOpen 1/1 ] 파일선택창이 뜨면서 호출되는 매서드. "); 
			}          	
		},
		onDisable: function () {
			if(alertDebug) { 
				alert("[onDisable 1/1 ] uploadify Swf를 사용중지 시킨다. "); 
			}       
		},
		onEnable: function () {
			if(alertDebug) { 
				alert("[onDisable 1/1 ] uploadify Swf를 사용중으로 변경한다. "); 
			} 
		},  
		onUploadSuccess: function (file, data, response) {
			if(alertDebug) { 
				alert("[onUploadSuccess 1/1 ] 업로드 할려는  파일이 업로드가 완료될때마다 호출된다. File : "+ file.name +" Upload Success ");
				alert("[onUploadSuccess 1/1 ] 호출된 정보의 서버 페이지의 Response정보를  data 에 설정되어 있다 ( json 형태로 업로된 파일 정보가 담겨있다 ");           		
			} 
			var responseStr = data.replace('[','');	 //받아온 정보가 완벽한 json 형태가 아니라 [,]가 붙어 있음 그래서 replace 했음 
			responseStr = responseStr.substring(0, (responseStr.length)-1);
			var obj = JSON.parse(responseStr);	//json으로 파싱처리해서 obj변수에 설정
			//attachVO 객체에 정보를 생성하고 attachFileArray 배열에 등록한다.
			//attachFileArray 배열에는 등록할 파일에 정보가 담겨져있다. ( 하단의 attachVO 정보 형태로 들어가 있음 )
			attachVO = {
				"tenantId" : attachInfo.tenantId,
				"portalId" : attachInfo.portalId,
				"fileFid" : attachInfo.fileFid,
				"fileSeq" : (attachFileArray.length+1),
				"fileSfid" : "",
				"fileLocalName" : obj.name,
				"fileServerName" : obj.name_temp,
				"fileServerPath" : attachInfo.attachTempUploadPath + "/" + obj.name_temp,
				"fileSize" : obj.size,
				"fileType" : obj.type,
				"fileStatus" : "add",
				"moduleType" : attachInfo.moduleType
			};		
			attachFileArray.push(attachVO);
		},  
		onQueueComplete: function (queueData) { 
			if(alertDebug) { 
				alert('[onQueueComplete] '+ queueData.uploadsSuccessful + ' 파일업로드가 완료되고 최종으로 큐에서 성공한 정보를 확인한다.');
			}
			filesUploadsSuccessful();            
		},
		onFallback: function () {
		},
		onInit: function (instance) {
		},
		onSWFReady: function () {
		},       
		onUploadError: function (file, errorCode, errorMsg, errorString) {
			/*
       		HTTP_ERROR : -200, HTTP_ERROR : -200, MISSING_UPLOAD_URL : -210, MISSING_UPLOAD_URL : -210, IO_ERROR : -220, IO_ERROR : -220, 
	        SECURITY_ERROR : -230, SECURITY_ERROR : -230, UPLOAD_LIMIT_EXCEEDED : -240, UPLOAD_LIMIT_EXCEEDED : -240, UPLOAD_FAILED : -250, UPLOAD_FAILED : -250, 
	        SPECIFIED_FILE_ID_NOT_FOUND : -260, SPECIFIED_FILE_ID_NOT_FOUND : -260, FILE_VALIDATION_FAILED : -270, FILE_VALIDATION_FAILED : -270, 
	        FILE_CANCELLED : -280, FILE_CANCELLED : -280, UPLOAD_STOPPED : -290 UPLOAD_STOPPED : -290 
	        */
		},
		onUploadProgress: function (file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
		},
		onUploadStart: function (file) {
		},
		onUploadComplete: function (file) {
			//업로드 할려는  파일이 완료시에  호출된다. ( 다중 파일 등록시 여러번 호출 )
		} 
	 });
	
	 if (attachInfo.mode == 'view' || attachInfo.mode == 'mod') { 
		 displayAttachFile();
	 }
});

function displayAttachFile() {
	if(alertDebug) { 
   		alert("[displayAttachFile 1/1 ] 상세보기나 수정 페이지라면 기존에 등록 된 페이지를 화면에 출력해준다. ");
   		alert(" tenantId, portalId, fileFid 정보로 ajax를 통해서 등록된 파일 정보를 Json 형태로 반환받는다. ");
   	} 
	var fileVO = null;
	var fileStyle = '';
	
	var baseVO = { 
		"method" : "displayAttachFile",	
		"tenantId" : attachInfo.tenantId,
		"portalId" : attachInfo.portalId,
		"fileFid" : attachInfo.fileFid
	}; 			
	
	var paramData = decodeURIComponent($.param(baseVO)); 
	var actionURL = context_root + "/common/ComFileAttach.do";	

	$.ajax({			//ajax를 호출해서 등록되 파일 정보를 받는다.
		type:'POST',
		url:actionURL,
		data:paramData,
		dataType:'text',
		success:function(data) {
			if(alertDebug) { 
		   		alert("[displayAttachFile 1/1 ] ajax를 통해서 가져온 첨부파일 정보 ( NoDBData는 첨부 없는경우 ) \r\n "+ data);
		   	} 	   	
			if(data == "NoDBData") {
				//- 현재 첨부파일 정보가 없습니다.	
				//alert('No File Data ');
			} 
			else {
				var dataArray = data.split("$&$");//FF에서 object로 인식되어 split불가하여 post방식에서 ajax로 변경(dataType지정)
				var dataArrayCount = dataArray.length - 1;
				//받아온 정보를 Json 파서를 통해서 정보를 obj에 설정한다.
				//obj의 속성값 ( obj.fileFid, obj.fileSize, obj.fileLocalName, obj.fileServerName.. )
				for(var x=0; x < dataArrayCount; x++) {
					var obj = JSON.parse(dataArray[x]);					
					obj.fileSeq = x;
					obj.fileServerPath = attachInfo.attachTempUploadPath + "/" + obj.fileServerName;	

					//attachVO 객체에 저장하고 	attachFileArray 배열에 등록한다. 수정시에도 기존에 등록된 파일 정보를 넘겨야하며 fileStatus가 none이어야 한다. (아니면 삭제됨 )
					//*[중요] 추후에 수정시에 기존에 첨부파일이 삭제되면 attachFileArray 에서는 선택된 정보를 Vo객체를 삭제하고  deleteFileArray 에	fileStatus에 delete로 설정해서
					//삭제된 정보를 넣어서 수정시에 수정 페이지(JSP)에 넘겨준다.		
					attachVO = {
							"tenantId" : attachInfo.tenantId,
							"portalId" : attachInfo.portalId,
							"fileFid" : obj.fileFid,
							"fileSeq" : obj.fileSeq,
							"fileSfid" : obj.fileSfid,
							"fileLocalName" : obj.fileLocalName,
							"fileServerName" : obj.fileServerName,
							"fileServerPath" : attachInfo.attachTempUploadPath + "/" + obj.fileLocalName,
							"fileSize" : obj.fileSize,
							"fileType" : obj.fileType,
							"fileStatus" : "none",
							"moduleType" : attachInfo.moduleType
						};		
					//attachFileArray 배열에 기존에 등록된 파일 정보를 넣는다.
					attachFileArray.push(attachVO);
					//기존에 등록된 파일 사이즈를 합산처리한다. (나중에 기존에 가지고 있던 용량만 확인하기 위해서 selCurrentFileMaxSize 변수에 담는다 )
					//selFileMaxSize에는 추가하거나 삭제할때 용량의 가감 처리. 
					selFileMaxSize += parseInt(obj.fileSize);
					selCurrentFileMaxSize += parseInt(obj.fileSize);
					if (attachInfo.mode == 'view') {//조회일경우 삭제가 없다.
						fileStyle += '<div id="fdiv_'+ obj.fileSfid +'" class="uploadifyQueueItem">';
						fileStyle += '<div class="fileName"> <img src="'+ AttachmentUtil.getIcon( obj.fileLocalName ) +'" border=0 > &nbsp; '+ obj.fileLocalName +' ( '+ byteConvert(obj.fileSize) +' )';
						fileStyle += '&nbsp;<a href="' + attachInfo.basePath + '/servlet/upload/file/serverPath/'+ obj.fileServerName +'/'+ obj.fileLocalName +'"><img src="'+ AttachmentUtil.getDown() +'" border=0 ></a></div>';
						fileStyle += '</div>';
					} else if (attachInfo.mode == 'mod') {//수정일경우 삭제 버튼을 추가.
						fileStyle += '<div id="fdiv_'+ obj.fileSfid +'" class="uploadifyQueueItem">';						
						fileStyle += '<div class="cancel"><a href=\'javascript:fileDelete("'+ obj.fileSfid +'", '+ obj.fileSize +')\'><img src="'+ AttachmentUtil.getDel() +'" border=0 ></a></div>';
						fileStyle += '<div class="fileName"> <input type="checkbox" name="uploadAttachModId" id="uploadAttachModId" value="'+ obj.fileSfid +':'+ obj.fileSize +'" > &nbsp; <img src="'+ AttachmentUtil.getIcon( obj.fileLocalName ) +'" border=0 > &nbsp; '+ obj.fileLocalName +' ( '+ byteConvert(obj.fileSize)  +' )';
						fileStyle += '&nbsp;<a href="' + attachInfo.basePath + '/servlet/upload/file/serverPath/'+ obj.fileServerName +'/'+ obj.fileLocalName +'"><img src="'+ AttachmentUtil.getDown() +'" border=0 ></a></div>';
						fileStyle += '</div>';
					}
				}//end for

				//기존의 등록된 파일 정보를 div에 붙여 넣는다.
				$('#queueDiv').append(fileStyle); 
				
				//기존의 등록된 파일용량정보를 화면에 출력.
				var currentFileSize = byteConvert(selFileMaxSize);
				currentFileSize = currentFileSize +' / '+ byteConvert(attachInfo.uploadAttachmax);
			  	$('#status-upload-size').text(currentFileSize); 		    
			}
		}	
	});
}

function fileDelete(fileSfid, fileSize) {
	if(alertDebug) { 
   		alert("[fileDelete 1/1 ] 수정화면에서 기존에 등록된 첨부파일 정보를 삭제한다. fileSfid : "+ fileSfid);
   		alert("attachFileArray에 들어있는 fileSfid 통해서 몇번째 배열에 있는지 찾고 정보를 가져와서 fileStatus값을 delete로 변경하고 deleteFileArray 등록한다. ");
   		alert("deleteFileArray에서 삭제된 파일 정보는 관리하고 기존의 attachFileArray에 들어있던 정보는 삭제처리한다. 후에 화면에 파일정보를 삭제하고 총용량 재계산 한다.");   		
   	}    
   		
	var attachCount = attachFileArray.length;
	var deleteIndex = -1;	
	//배렬을 반복하면서 선택된 파일이 몇번째에 위치하는지 확인.
	for(var x=0; x < attachCount; x++) {
		var attachData = attachFileArray[x];		
		if(fileSfid == attachData.fileSfid) {			
			deleteIndex = x;
			break;
		}
	}
	if(alertDebug){ alert('삭제한 파일 정보가 '+ deleteIndex +' 번째 배열에서 발견 ( -1이면 존재하지 않음 )'); }	
	if(deleteIndex != -1) {
		//1. 첨부 파일 배열에서 정보를 가져와서 delete 표시.
		var deleteObj = attachFileArray[deleteIndex];
		deleteObj.fileStatus = "delete";
		
		//2.삭제된 첨부 파일 배열(deleteFileArray)에 저장하고 첨부 파일 배열에서(attachFileArray) 삭제.
		deleteFileArray.push(deleteObj);
		arrayRemoveByIndex(attachFileArray, deleteIndex);	

		//3.수정시에 화면에 기존에 등록된 파일 정보 Div 삭제.
		var delDiv = '#fdiv_'+ fileSfid;
		$(delDiv).remove(); 

		//4.선택된 파일의 용량을 삭제하고 화면에 총용량을 변경해서 출력.
		var currentFileSize = 0;		  
	  	if(selFileMaxSize > 0){
		  	//currentFileSize = selFileMaxSize - fileSize;	  
		  	selFileMaxSize  = selFileMaxSize - fileSize;	
	  	}
	  	currentFileSize = byteConvert(selFileMaxSize);
	  	currentFileSize = currentFileSize +' / '+ byteConvert(attachInfo.uploadAttachmax);
	  	$('#status-upload-size').text(currentFileSize);						
	}	
}


function arrayRemoveByIndex(arrayName, arrayIndex){ 
	if(alertDebug){ alert('[arrayRemoveByIndex] ]'+ arrayIndex +' 배열에 정보를 삭제한다.'); }
	arrayName.splice(arrayIndex, 1);	
	if(alertDebug){ 
		alert('[arrayRemoveByIndex] 삭제 후에 attachFileArray 들어있는 정보를 확인한다. ');
		for(x=0 ; x< attachFileArray.length ; x++) {                 
			var attchVoTmp = attachFileArray[x];
			alert('[arrayRemoveByIndex] attachFileArra ['+ x +'] 정보 -> attchVoTmp.fileSeq '+ attchVoTmp.fileSfid +', fileStatus : '+ attchVoTmp.fileStatus +',  attchVoTmp.tenantId '+ attchVoTmp.tenantId +" , attchVoTmp.fileLocalName  "+ attchVoTmp.fileLocalName  );	
		} 
		alert('[arrayRemoveByIndex] 삭제 후에 deleteFileArray 들어있는 정보를 확인한다. ');
		for(x=0 ; x< deleteFileArray.length ; x++) {                 
			var attchVoTmp = deleteFileArray[x];
			alert('[arrayRemoveByIndex] deleteFileArray ['+ x +'] 정보 -> attchVoTmp.fileSeq '+ attchVoTmp.fileSfid +', fileStatus : '+ attchVoTmp.fileStatus +',  attchVoTmp.tenantId '+ attchVoTmp.tenantId +" , attchVoTmp.fileLocalName  "+ attchVoTmp.fileLocalName  );	
		} 
	}
}	
//################# 상세보기/수정 화면 기존 등록된 첨부파일 정보 출력 & 기존 등록된 첨부파일 정보 삭제 종료
function filesUploadsSuccessful(){
	if(alertDebug) { 
   		alert("[filesUploadsSuccessful 1/1 ] 기존에 첨부파일 정보와 새로 첨부된 파일 정보를 담고있는 attachFileArray 배열에서 attachArray 배열에 복사한다.");
   		alert("[filesUploadsSuccessful 2/1 ] deleteFileArray에서 삭제된 파일 정보를 attachArray 배열에 등록한다.(for문처리)");
   		alert("[filesUploadsSuccessful 2/1 ] tenantId,portalId,fileFid 정보를 담고있는 객체를 attachArray 붙여넣는다.");
   		alert("attachFileArray에 들어있는 fileSfid 통해서 몇번째 배열에 있는지 찾고 정보를 가져와서 fileStatus값을 delete로 변경하고 deleteFileArray 등록한다. ");
   		alert("deleteFileArray에서 삭제된 파일 정보는 관리하고 기존의 attachFileArray에 들어있던 정보는 삭제처리한다. 후에 화면에 파일정보를 삭제하고 총용량 재계산 한다.");   		
   	}    	
	var attachArray = attachFileArray.slice();//기존에 첨부파일 정보와 새로 첨부된 파일 정보를 attachFileArray 에서 복사.  
		
	var dataCount = deleteFileArray.length;
	for(var x=0; x < dataCount; x++) {//- 삭제된 첨부파일 정보가 있으면 복사
		attachArray.push(deleteFileArray[x]);
	}	
		
	var baseVO = { 
		"tenantId" : attachInfo.tenantId,
		"portalId" : attachInfo.portalId,
		"fileFid" : attachInfo.fileFid,
		"attachType" : attachInfo.attachType, //@add 업로드 형식 2013.11.27 - 확장자 및 사이즈 체크
		"maxFileSize" : attachInfo.uploadAttachmax, //@add 전체 파일 사이즈 2013.11.27 - 확장자 및 사이즈 체크
		"attachFileTypes" : '' + attachInfo.attachTypeType, //@add 허용확장자 형식 2013.11.27 - 확장자 및 사이즈 체크
		"unAcceptUse" : attachInfo.unAcceptUse //@add 지정확장자를 허용할지 거부할지 여부 2013.11.27 - 확장자 및 사이즈 체크		
	}; 		
	attachArray.unshift(baseVO);
	var paramData = { "attachFileList" : attachArray };
	if(alertDebug) {
		alert('[filesUploadsSuccessful ] attachArray 정보를 확인한다. 새로운 첨부는 fileStatus가 add로 삭제는 delete 기존첨부 유지는 none형태로 모든 파일 정보가 배열에 담아있어야 한다.');
		for(x=0 ; x< attachArray.length ; x++) {                 
			var attchVoTmp = attachArray[x];
			alert('[filesUploadsSuccessful ] attachArray ['+ x +'] -> attchVoTmp.fileSeq '+ attchVoTmp.fileSfid +', fileStatus : '+ attchVoTmp.fileStatus +', attchVoTmp.fileLocalName '+ attchVoTmp.fileLocalName  );	
		} 
	}
	var actionURL = context_root + "/common/ComFileAttach.do?method=apply";
	$.ajax({
		url: actionURL, 
	    type: "POST",
	    dataType: "text",
	    data: JSON.stringify(paramData),
	    contentType: "application/text; charset=utf-8",
	    success: function(data) {
		    //성공처리되면 fid값을 등록/수정 페이지에 반환한다.-> callback함수로 변경해야함
		    if(alertDebug) { alert('[filesUploadsSuccessful ] 완료  : fid ( error 이면 오류 )'+ data); }
		    //fashUpAfter(data);
		    callBackFunction(data);
		},
	    error: function(xhr, txt, err) {
			if(alertDebug) { alert('[filesUploadsSuccessful ] 오류  : error'); }
	    	fashUpAfter('false');				
	    }   			
	});	
}

//################# file attach swf 에서 Sample 구성한 button 시작
var attachCallBackFunction = null;

function cmdCancel(){ $('#custom_file_upload').uploadify('cancel','*'); }
function cmdUpload(){ $('#custom_file_upload').uploadify('upload','*'); }
function cmdDestroy(){ $('#custom_file_upload').uploadify('destroy'); }
function cmdDisable(){ $('#custom_file_upload').uploadify('disable',true); }
function cmdEnable(){ $('#custom_file_upload').uploadify('disable',false); }
function cmdStop(){ $('#custom_file_upload').uploadify('stop'); }
function cmdQueueDataCnt(){ var cnt = $('#custom_file_upload').uploadify('queueDataCnt'); }

function cmdAllSel(){ 
	//alert('cmdAllSel');
	$("input:checkbox[id='uploadAttachId']").attr("checked", true);
	$("input:checkbox[id='uploadAttachModId']").attr("checked", true);
}

function cmdSelFileDel(mode){ 
	//alert('cmdAllSel');	
	$("input:checkbox[id='uploadAttachModId']").each(function (index) {  
		if($(this).is(":checked")){
			//alert(index + ' : '+ $(this).val());
			var fileInfo = $(this).val().split(":"); 
			fileDelete(fileInfo[0], fileInfo[1]);
		}
	}); 
	
	$("input:checkbox[id='uploadAttachId']").each(function (index) {  
		if($(this).is(":checked")){
			//alert(index + ' : '+ $(this).val());
			var inputData = $(this).val();
			$('#custom_file_upload').uploadify('cancel', inputData);
		}
	});  
}

//등록 버튼을 선택한 경우 수행한다.
function startFileUpload(callback) {
	try {
		//CallBack Method를 정의한다.
		if(callback != undefined) {
			attachCallBackFunction = callback;
		} 
		else {
			attachCallBackFunction = null;
		}
		if (attachInfo.mode == 'add') {
			if( $('#custom_file_upload').uploadify('queueDataCnt') > 0   ){ // 새로운 파일이 등록이 발생한 경우
				cmdUpload();
			} else{ // 파일 첨부가 없는경우
				callBackFunction('');
			}
		} else {	
			if( $('#custom_file_upload').uploadify('queueDataCnt') > 0    ){ // 새로운 파일이 등록이 발생한 경우[ 기존 파일도 삭제를 여뷰는 상관업음]
				cmdUpload();
			} else if( $('#custom_file_upload').uploadify('queueDataCnt') == 0 &&  deleteFileArray.length > 0) { //첨부파일 변경이 있는 경우(새로운 파일이 등록이 하지 않고 기존 파일을 삭제함)
				filesUploadsSuccessful();
			} else { // 파일에 대한 변경없이 내용만 수정
				filesUploadsSuccessful();
			}
		}
	} catch(e) {
		alert(e.description);
	} finally {
	}
}

//################# 등록/수정 페이지의 Callback 함수 호출
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
//################# 등록/수정 페이지의 Callback 함수 호출 종료
	
//################# 확장자 이미지, 다운, 삭제 등의 아이콘 정보 가져오는 Util 함수 및 파일 byte 계산함수 시작
//파일사이즈를 MB, KB로 처리한다.
function byteConvert( argByteSize ){
	var rtnVal = 0;
	var rtnValTmp = '';
	var byteSize = Math.round(argByteSize / 1024 * 100) * .01;
		
	try{
		 //단위를 mega와 byte로 구분한다.
		 if (byteSize > 1000) {
			 rtnVal = Math.round(byteSize *.001 * 100) * .01;
			 rtnValTmp = ''+rtnVal;
			 var fileSizeParts = rtnValTmp.toString().split('.');
			 rtnValTmp = fileSizeParts[0];
			 if (fileSizeParts.length > 1) {
				 rtnValTmp += '.' + fileSizeParts[1].substr(0,2);
			 }
			 rtnVal = rtnValTmp +' MB';
		  }
		  else{
			  //rtnVal = byteSize +" KB"; 
			 rtnValTmp = ''+byteSize;
			 var fileSizeParts = rtnValTmp.toString().split('.');
			 rtnValTmp = fileSizeParts[0];
			 if (fileSizeParts.length > 1) {
				 rtnValTmp += '.' + fileSizeParts[1].substr(0,2);
			 }
			 rtnVal = rtnValTmp +" KB";  
		  }		  
	} catch(e){
		rtnVal = "0.00 KB";
	}
	return rtnVal;
}
var ext_type = 'bak|bmp|doc|exe|gif|gul|hlp|htm|html|hun|hwp|ini|jar|jpg|jpeg|mpg|pdf|png|ppt|pptx|psd|swf|text|tif|ttf|txt|wav|wmf|word|xls|xlsx|xml|zip|';
var AttachmentUtil = {
	getDel  :  function() {
		return attachInfo.basePath + "/collaboration/ref/images/attachment/blog_icon03.gif";
	},	
	getDown  :  function() {
		return attachInfo.basePath + "/collaboration/ref/images/attachment/attach2.gif";
	},
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
		return attachInfo.basePath + "/collaboration/ref/images/attachment/attach_" + ext.replace(/(^\s*)|(\s*$)/g, "") + ".gif";
	},
	escapeQuote : function(str) {
		return str.replace(/\'/g,"\\'");
	}
};	
