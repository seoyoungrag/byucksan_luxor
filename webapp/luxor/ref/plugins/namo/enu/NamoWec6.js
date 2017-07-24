var cont = '<OBJECT WIDTH=0 HEIGHT=0 CLASSID="clsid:'+NAMO_LPK_CLASSID+'">'
+'<PARAM NAME="LPKPath" VALUE="/EP/htdocs/activex/cabfiles/namo/NamoWec6_acube_dev.lpk">'
+'</OBJECT>'
+'<OBJECT ID="wec" WIDTH="100%" HEIGHT="350" '
+'CLASSID="CLSID:'+NAMO_CAB_CLASSID+'" CODEBASE="/EP/htdocs/activex/cabfiles/namo/NamoWec.cab#version='+NAMO_CAB_VERSION+'">'
+'<param name="UseNamoNet" value="0">'
+'<param name="InitFileURL" value="/EP/htdocs/activex/cabfiles/namo/'+NAMO_ENV_FILE+'">'
+'<param name="InstallSourceURL" value="http://www.namo.co.kr/activesquare/products/update">'
+'</OBJECT>';
document.write(cont);
