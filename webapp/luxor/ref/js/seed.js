var SC_Browser_Version = navigator.userAgent;

/**
 * Seed Plugin 설치여부 체크
 * @returns {Boolean}
 */
function installedPlugin() {
	var ret = false;
	
	var mimetype1 = navigator.mimeTypes["application/seedclientx"]; // For Google Chrome
	var mimetype2 = navigator.mimeTypes["Application/SeedClientX"]; // For Firefox
	if (mimetype1) {
		var plugin = mimetype1.enabledPlugin;
		if (plugin) {
			ret = true;
		}
	}
	if (mimetype2) {
		var plugin = mimetype2.enabledPlugin;
		if (plugin) {
			ret = true;
		}
	}
	
	return ret;
}

if (new RegExp(/MSIE/).test(SC_Browser_Version)) {
	document.writeln("<object id='seedOCX' classid='clsid:A69C034C-75AB-49A0-A3F9-D0F8DCB76E43'");
	document.writeln("      codebase='/ep/luxor/ref/plugins/SeedClientX.cab#version=1.0.0.4'");
	document.writeln("      width='0' height='0'>");
	document.writeln("</object>");
} else if (new RegExp(/Firefox/).test(SC_Browser_Version)) {
	if(installedPlugin()==true) {
		document.write("<embed id='seedOCX' type='Application/SeedClientX' pluginspage='/ep/luxor/ref/plugins/npSeedClientX.xpi' width=0 height=0 style='float:left;'/>");
	} else {
		document.location.href = '/ep/luxor/ref/plugins/npSeedClientX.xpi';
	}
} else {
	if(installedPlugin()==true) {
		document.write("<embed id='seedOCX' type='Application/SeedClientX' pluginspage='/ep/luxor/ref/plugins/npSeedClientX.msi' width=0 height=0 style='float:left;'/>");
	} else {
		luxor.popup('/ep/luxor/common/pluginDownload.jsp', {width:450,height:150});
	}
}
