package com.sds.acube.luxor.common.util;

import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;
import com.sds.acube.luxor.common.util.namo.NamoMime;
import com.sds.acube.luxor.framework.config.LuxorConfig;

public class UtilEditor {
	/**
	 * XSS(Cross Site Scripting)요소 제거
	 * 
	 * @param content
	 * @return
	 */
	public static String escapeXSS(String content) {
		StringBuffer sb = new StringBuffer();
		sb.append("(");
		sb.append("onabort|");
		sb.append("onblur|");
		sb.append("onchange|");
		sb.append("onclick|");
		sb.append("ondblclick|");
		sb.append("onerror|");
		sb.append("onfocus|");
		sb.append("onkeydown|");
		sb.append("onkeypress|");
		sb.append("onkeyup|");
		sb.append("onload|");
		sb.append("onmousedown|");
		sb.append("onmousemove|");
		sb.append("onmouseout|");
		sb.append("onmouseover|");
		sb.append("onmouseup|");
		sb.append("onreset|");
		sb.append("onresize|");
		sb.append("onselect|");
		sb.append("onsubmit|");
		sb.append("onunload");
		sb.append(")=");
		
		Pattern HTML_SCRIPT1 = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
		Pattern HTML_SCRIPT2 = Pattern.compile("\\<script[^>]*?>.*?\\<\\/script\\>", Pattern.CASE_INSENSITIVE);
		Pattern HTML_SCRIPT3 = Pattern.compile(sb.toString(), Pattern.CASE_INSENSITIVE);		
		content = HTML_SCRIPT1.matcher(content).replaceAll("");
		content = HTML_SCRIPT2.matcher(content).replaceAll("");
		content = HTML_SCRIPT3.matcher(content).replaceAll("");
		return content;
	}


	public static String getContent(HttpSession session, String data) {
		if(CommonUtil.isNullOrEmpty(data)) {
			return "";
		}
		
		String editorType = "";
		if(data.startsWith("MIME-Version") && data.indexOf("base64") > -1 && data.indexOf("Namo") > -1) {
			editorType = "NAMO";
		} else {
			editorType = "XINHA";
		}
		
		if("NAMO".equals(editorType)) {
			return escapeXSS(parseMime(data));
		} else {
			return escapeXSS(Xinha.decode(data, Xinha.getPrefixID(session)));
		}
	}


	/**
	 * @deprecated
	 */
	public static String treatHtml(String htmlData) {
		String[] arrTarget = { "<script", "</script>" };
		String[] arrReplace = { "<!--script", "</script-->" };

		String lowerContent = "";
		int pos;
		for(int i = 0; i < arrTarget.length; i++) {
			lowerContent = htmlData.toLowerCase();
			pos = lowerContent.indexOf(arrTarget[i]);

			while(pos >= 0) {
				htmlData = htmlData.substring(0, pos)
				           + arrReplace[i]
				           + htmlData.substring(pos + arrTarget[i].length(), htmlData.length());

				lowerContent = htmlData.toLowerCase();
				pos = lowerContent.indexOf(arrTarget[i]);
			}
		}

		return htmlData;

	}


	public static String parseMime(String mimeData) {
		String parsedData = "";
		try {
			if(mimeData == null || mimeData.trim().length() == 0) {
				return "";
			}

			String dirKey = "mime/" + CommonUtil.generateId("S");
			String fileSavePath = LuxorConfig.getString("ATTACH.UPLOAD_TEMP") + "/" + dirKey;
			String fileViewUrl = "/ep/luxor/image/editor/FileView.jsp?kid=" + dirKey;

			NamoMime mime = new NamoMime();
			mime.setSaveURL(fileViewUrl);
			mime.setSavePath(fileSavePath);
			mime.decode(mimeData);
			mime.saveFile();
			parsedData = mime.getBodyContent().replaceAll("'", "\'");
		} catch(Exception e) {}

		return parsedData;
	}
}
