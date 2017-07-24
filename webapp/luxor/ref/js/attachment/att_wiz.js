function getLen( str ){
	var tmpC, tmpN;
	var cnt = 0;

	for (var i = 1; i< str.length; i++)	{
		tmpC = str.substr(i,1);
		tmpN = tmpC.charCodeAt(0);
		if (0 <= tmpN && tmpN <= 255) {
			cnt = cnt + 1;
		}else {
			cnt = cnt + 2;
		}
	}

	return cnt;
}

function getChr( asc ){
	return String.fromCharCode(asc);
}

var o_time;

function wait4complete(){
	o_time = setTimeout("isReady()",50);
}

function isReady(){
	if (document.readystate == "complete") 
	{
		clearinterval( o_time );
		init();
	}
}
// end of portal method
//--------------------------------------------------------

//------------------------------------------------------
// Version 1,0,1,112 (2001.09.30)
//------------------------------------------------------
// 서버의 ip와 port를 setting한다.
function SetServerInfo(sSvrIp, sSvrPort){
	document.getElementById('FileWizard').SetServerInfo(sSvrIp, sSvrPort);
}

//파일을 upload할 디렉토리를 설정한다.
function SetTempDir(sTempDir){
	document.getElementById('FileWizard').SetTempDir(sTempDir);
}

// 파일을 선택하는 즉시 upload할 경우 즉, 커뮤니케이션에서는 1을
// 파일만 선택하고 나중에 upload할 경우에는 0을 준다.
function SetUploadMode(nMode){
	document.getElementById('FileWizard').SetUploadMode(nMode);
}

// nMaxFileSize : 첨부파일의 최대 크기 제한
// nEncOption : 암호와 여부 1은 암호화 , 0 은 비 암호화
// nAttSize : 현재까지 첨부된 파일의 크기
// 파일의 크기는 모두 byte단위로 통일해서 넘겨준다.
function SetServerOption(nAttSize, nMaxFileSize, nEncOption){
	document.getElementById('FileWizard').SetAttachedFileSize(nAttSize);
	document.getElementById('FileWizard').SetMaxFileSize(nMaxFileSize);
	document.getElementById('FileWizard').SetEncOption(nEncOption);
}

// ocx에 남아있는 정보를 모두 clear한다.
function ClearFileInfos(){
	document.getElementById('FileWizard').ClearFileInfos();
}

//파일을 첨부한다. Mode는 일반적으로 NEW이다.
//파일정보는 아래처럼 파일개수파일정보(한 파일이 끝날 때마다 (31)로 구분하고 파일의 내용상 구분은 (29)로 한다.
//22001070201.gulc:\temp\2001070201.gul/users1/dmsadm/lejoo/A97A0DF544074A38996A2A5529214F3C.gul1036608020010713094106N0NNFLY0YI2001070201.MGR/users1/dmsadm/lejoo/7823AF0085C14137B866D0D1EDD7BB51.MGR101319320010609111930N1NNFFY0A97A0DF544074A38996A2A5529214F3C.gulNI
//파일개수 : 2
//첫번째 파일정보 : 2001070201.gulc:\temp\2001070201.gul/users1/dmsadm/lejoo/A97A0DF544074A38996A2A5529214F3C.gul1036608020010713094106N0NNFLY0YI
//두번째 파일정보 : 2001070201.MGRc:\temp\2001070201.MGR/users1/dmsadm/lejoo/7823AF0085C14137B866D0D1EDD7BB51.MGR101319320010609111930N1NNFFY0A97A0DF544074A38996A2A5529214F3C.gulNI
//첫번째 파일정보에 대한 상세설명
//   파일이름 : 2001070201.gul
//   파일의 로컬쪽 path : c:\temp\2001070201.gul
//   파일의 서버쪽 path : /users1/dmsadm/lejoo/A97A0DF544074A38996A2A5529214F3C.gul
//   파일의 주버전 : 1
//   파일의 부버전 : 0
//   파일의 크기   : 366080
//   파일의 작성날짜 : 20010713094106 (YYYYMMDDHHMMSS)
//   그 이후 값은 문서관리에서만 사용하는 정보입니다. 그 중에서 끝에서 두번째 Y값은 ShowBool값으로 화면에 보여주라는 뜻입니다.
//두번째 파일정보에 대한 상세설명
//   파일이름 : 2001070201.gul
//   파일의 로컬쪽 path : c:\temp\2001070201.MGR
//   파일의 서버쪽 path : /users1/dmsadm/lejoo/7823AF0085C14137B866D0D1EDD7BB51.MGR
//   파일의 주버전 : 1
//   파일의 부버전 : 0
//   파일의 크기   : 13193
//   파일의 작성날짜 : 20010609111930 (YYYYMMDDHHMMSS)
//   그 이후 값은 문서관리에서만 사용하는 정보입니다. 그 중에서 끝에서 두번째 N값은 ShowBool값으로 화면에 보여주지 말라는 뜻입니다.
//   또한 훈민정음과 mgr파일을 연계하기 위한 relatioin정보가 A97A0DF544074A38996A2A5529214F3C.gul 와 같이 들어가 있습니다.
//   이 정보는 파일조회때에도 똑같이 주시면 됩니다.

function AttachFile(Mode){
	var i;
	var sFiles;
	SetAttachMode(Mode);
	sFiles = document.getElementById('FileWizard').AttachFileJs();

	var splitStr = sFiles.split(String.fromCharCode(31));
	return splitStr.length + String.fromCharCode(31) + sFiles;
}

//파일을 조회 또는 다운로드한다.
//파일조회일 경우 Mode는 "F" , 다운로드일 경우에는 "W"라고 한다.
//sFileInfo는 파일첨부시에 받은 정보와 동일한 형태로 주면 된다.

function DownloadFile(sFileInfo){
	return document.getElementById('FileWizard').ViewFile("W", sFileInfo);
}

function ViewFile(sFileInfo){
	return document.getElementById('FileWizard').ViewFile("F", sFileInfo);
}

//언어를 설정한다. K이면 한국어로 그 외에는 모두 영어로 출력된다.
function SetLanguage(sLang){
	document.getElementById('FileWizard').SetLanguage(sLang);
}

//파일첨부할 때 Mode, 일반적으로 'NEW'이다.
function SetAttachMode(prm){
	if (prm != "")
		document.getElementById('FileWizard').SetMode(prm);
}

//sftp로 파일을 받아오면서 서버쪽의 파일을 지우는 모드입니다.
//1일 때는 자동으로 지우는 것이고 0일때는 지우지 않습니다.
//기본값은 지우는 것으로 설정되어 있습니다.
function SetServerAutoDelete(nAuto){
	document.getElementById('FileWizard').SetServerAutoDelete(nAuto);
}

//tempfilename을 얻는다.
function GetTempFileName(sExt){
	return document.getElementById('FileWizard').GetTempFileName(sExt);
}

//첨부할 확장자를 정의한다.(구분자 #29)
//sExt = 'gultxt*'
function SetExtension(sExt){
	document.getElementById('FileWizard').SetExtension(sExt);
}

//ViewFile전에 설정하면 수정후 서버로 다시 전송
function SetModUpload(){
	document.getElementById('FileWizard').SetUploadAfterMod("Y");
}

//파일을 upload, download 할 때 UI를 보여줄 지 안보여줄 지.. 0이면 안보여준다. 기본값은 1, 2는 이어받기 가능
function SetFileUIMode(nMode){
	document.getElementById('FileWizard').SetFileUIMode(nMode);
}

//PC에 남아있는 파일을 지울 때
function DeleteLocalFile(sFileName){
	return document.getElementById('FileWizard').DeleteLocalFile(sFileName);
}

//upload 시에 압축을 하려면 sMode 를 "Y"로 준다.
function SetCompressMode(sMode){
	document.getElementById('FileWizard').SetCompressMode(sMode);
}

//뷰어로 열 지 말 지 결정하는 모드 1이면 뷰어로 기본적으로 열려고 한다.
function SetOpenUniView(nMode, sWord){
	//document.getElementById('FileWizard').SetOpenUniView(nMode, sWord);
	//사용안함
}

//나중올리기 모드에서 현재 저장되어있는 파일의 갯수
function GetFileListCount(){
	return document.getElementById('FileWizard').GetFileListCount();
}

//나중올리기 모드에서 현재 저장되어있는 파일의 정보
function GetFilelistInfos(){
	return document.getElementById('FileWizard').GetFilelistInfos();
}

// OCX가 저장하고 있는 파일의 정보를 이용하여 파일을 웹서버의 임시 디렉토리로 올린다.
function AttFileUpload(){
	var nCount, i;
	var sFiles;
	var VerUp;
	var strRes;

	VerUp  = "NEW";
	strRes = "";

	sFiles = document.getElementById('FileWizard').GetFileRegInfoJs(VerUp);
	
	var splitStr = sFiles.split(String.fromCharCode(31));
	nCount = splitStr.length;
	
	if (nCount == 0) {
		strRes = "";
	} else if (nCount == -2) {
		strRes = "CANCEL";
	} else {
		if(!isEmpty(splitStr[0])) {
			strRes = sFiles + String.fromCharCode(31);
		}
	}
	
	return strRes;
}

// OCX가 저장하고 있는 파일의 정보중 index에 해당하는 항목 삭제.
function DeleteAttFile(nIndex, title){
	return document.getElementById('FileWizard').DeleteAttFile(nIndex, title);
}

function DeleteAllFile(){
	return document.getElementById('FileWizard').DeleteAllFile();
}

//파일 첨부시 멀티 셀렉트 가능 여부를 처리
// 1이 Default, 0일 경우 싱글셀렉트
function SetMultiSelectMode(nMode){
	document.getElementById('FileWizard').SetMultiSelectMode(nMode);
}

//Execute File in Client PC
function ShellExecuteWeb(file){
	document.getElementById('FileWizard').ShellExecuteWeb(file);
}

function GetFilelistInfos_Ex(){
   return document.getElementById('FileWizard').GetFilelistInfos_Ex();
}


//''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
//2002-07-12 add by junhyuk for SHTTP Service
//nMode = 0 : SFTP(Default), nMode = 1 : SHTTP
function SetTransferMode(nMode){
	document.getElementById('FileWizard').SetTransferMode(nMode);
}

//2002-07-12 add by junhyuk for SHTTP Service
//in case of SHTTP
//ID : Web Server Login ID
//Passwd : WebServer Login Passwd
//UploadModule : Upload Sevlet URI
//TmpLocal : Save Target Temporary Direcory in Server System
//TmpWeb : Web URL for TmpLocal
function SetSHTTPMode(ID, Passwd, UploadModule, TmpLocal, TmpWeb){
	document.getElementById('FileWizard').SetSHTTPMode(ID, Passwd, UploadModule, TmpLocal, TmpWeb);
}

//''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
//2003-06-30 add by junhyuk
//DownLoad Content File
//When sOption = '1', Save File as Inputed File Name.
//When sOption = '', Save File as UUID File Name.
//When sOption = '2', Save File as Inputed File Path(Can Use Full Path).
//Return : Save FileInfo List(Seperator #31)
//2c:\temp\726537627362763.gulc:\temp\49849848734873843.jpg
//DownLoad Content File
function DownloadContents(sOption, sFileInfo){
	return document.getElementById('FileWizard').DownloadContents(sOption, sFileInfo);
}