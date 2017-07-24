//-- 그림갯수
var cnt = 45;
//-- 그림표시
for(var i=1;i<cnt+1;i++){
	document.write("<img src='../images/imt" + i + ".gif' border='0' onclick='getimgname(this.src)' style='cursor:hand;'> ");
}
//-- 액티브스퀘어에 선택한 그림삽입
function getimgname(iname){

	if(opener=="[object]"){
		var fRef = opener.document.testForm;
		fRef.Wec.InsertImage (iname,"");
		window.close();
		fRef.Wec.focus();
	}
	else{
		alert("현재 창에서는 사용하실 수 없습니다.");
	}	
}
