//-- �׸�����
var cnt = 45;
//-- �׸�ǥ��
for(var i=1;i<cnt+1;i++){
	document.write("<img src='../images/imt" + i + ".gif' border='0' onclick='getimgname(this.src)' style='cursor:hand;'> ");
}
//-- ��Ƽ�꽺��� ������ �׸�����
function getimgname(iname){

	if(opener=="[object]"){
		var fRef = opener.document.testForm;
		fRef.Wec.InsertImage (iname,"");
		window.close();
		fRef.Wec.focus();
	}
	else{
		alert("���� â������ ����Ͻ� �� �����ϴ�.");
	}	
}
