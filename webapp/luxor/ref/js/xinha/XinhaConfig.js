// skins폴더 하위 폴더 이름중 선택
var _editor_skin = "blue-look";

// Xinha Editor의 textarea ID값
// 다중 Editor를 사용할 경우에는 ['ID1','ID2','ID3'..] 식으로 추가해줌
var xinha_editors =	['wec'];

// Xinha Editor에서 사용될 플러그인
// 추후 플러그인 추가시 ['plugin1','plugin2','plugin3'..] 식으로 추가해줌
var xinha_plugins = ['ImageManager'];

// 본문에 추가 가능한 이미지 확장자
// 여기에 명시되지 않은 확장자는 업로드 불가능
var imageExt = "jpg,jpeg,gif,png,JPG,JPEG,GIF,PNG";

// 에디터 툴바 및 기타 설정
// Config에 대한 자세한 설명은 다음 링크를 참고 (회사에서 접속 안됨)
// http://xinha.webfactional.com/wiki/Documentation/ConfigVariablesList
function getConfig(simple_toolbar) {
	var xinha_config = new Xinha.Config();
	if(simple_toolbar) {
		xinha_config.toolbar =
		 [
		   ["popupeditor"],
		   ["separator","fontname","fontsize","bold","italic","underline","strikethrough"],
		   ["separator","forecolor","hilitecolor"],
		   ["separator","justifyleft","justifycenter","justifyright","justifyfull"],
		   ["separator","createlink","insertimage","inserttable"]
		 ];
	} else {
		xinha_config.toolbar =
		 [
		   ["popupeditor"],
		   ["separator","formatblock","fontname","fontsize","bold","italic","underline","strikethrough"],
		   ["separator","forecolor","hilitecolor","textindicator"],
		   ["separator","subscript","superscript"],
		   ["linebreak","separator","justifyleft","justifycenter","justifyright","justifyfull"],
		   ["separator","insertorderedlist","insertunorderedlist","outdent","indent"],
		   ["separator","inserthorizontalrule","createlink","insertimage","inserttable"],
		   ["linebreak","separator","undo","redo","selectall","print"], (Xinha.is_gecko ? [] : ["cut","copy","paste","overwrite","saveas"]),
		   ["separator","killword","clearfonts","removeformat","toggleborders","splitblock","lefttoright", "righttoleft"],
		   ["separator","htmlmode","showhelp","about"]
		 ];
	}

	// 폰트추가
//	xinha_config.fontname.굴림 = '굴림,굴림체';
//	xinha_config.fontname.돋움 = '돋움,돋움체';

	xinha_config.statusBar = false;
	xinha_config.showLoading = false;
	xinha_config.pageStyle = 'body {font-family:굴림,verdana,arial,sans-serif;font-size:10pt;}';
	return xinha_config;
}