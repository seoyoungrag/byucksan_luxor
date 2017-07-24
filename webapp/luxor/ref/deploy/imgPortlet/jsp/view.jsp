<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page import="java.util.*" %>
<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.portal.PortletConstant" %>
<%@ page import="com.sds.acube.luxor.common.vo.UserProfileVO" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.security.UtilSSO" %>
<%@ page import="com.sds.acube.luxor.framework.config.LuxorConfig" %>

<%
	String currentPath = (String)request.getAttribute("currentPath");
	String portletContext = (String)request.getAttribute("portletContext");
	String themeImageUrl = UtilRequest.getString(request, "themeImageUrl");
	String contentId = UtilRequest.getString(request, "contentId");	
	Locale locale = (Locale)session.getAttribute("LANG_TYPE");
	String D1 = "";
	String encodeAlgorithm = LuxorConfig.getString("SSO.ENCODE_ALGORITHM");
	D1 = UtilSSO.encodeSession(request, encodeAlgorithm);
	com.sds.acube.luxor.common.vo.SsoToken ssoToken = UtilSSO.getSsoToken(request, D1); //@add 2014.02.25;
	
	String additionalParam = "lang="+locale+"&D0=SDS&D1="+D1+"&D2=&D3="+encodeAlgorithm;
	additionalParam += "&STM=" + ssoToken.getSTM() + "&SHM=" + ssoToken.getSHM(); //@add 2014.02.25;
	
%>
<script type="text/javascript">
var imgPortletId = "<%=contentId%>";
var addParam = "<%=additionalParam%>";

function openPopup(strUrl) {
	luxor.popup(strUrl, {
		width:820,
		height:520,
		scrollbars:'yes'
	});
}


$(document).ready(function() {
	var selectParam = "tabPortletId="+imgPortletId+"&isSetting=N";
	var imgContents ="";
	$('#ImgPortletList_'+imgPortletId).html('');
	
	callJson("generalPortletController", "getImgContentsList", selectParam, function(data) {
		for(var i=0;i<data.length;i++) {
			var imgUrl = data[i].portletContext.imgUrl;
			var viewUrl = data[i].portletContext.viewUrl;
			var ssoInfo = data[i].portletContext.ssoInfo;
			if(ssoInfo == "D1" && viewUrl.indexOf("?") > 0){
				viewUrl+="&"+addParam;
			}else if(ssoInfo == "D1" && viewUrl.indexOf("?") < 0){
				viewUrl+="?"+addParam;
			}
			var contentId = data[i].contentId;
			if(imgUrl == null || imgUrl == '') imgUrl ="/ep/luxor/ref/image/common/no_image.gif";
			imgContents ='<span id="img_'+contentId+'" style="padding:5px;"><a href="javascript:openPopup(\''+viewUrl+'\');"><img id="img_'+ contentId+'" src="'+imgUrl+'"/></a></span>';
			$('#ImgPortletList_'+imgPortletId).append(imgContents);

		}
	});
});

</script>

<div id="ImgPortlet_<%=contentId %>"  >
	<div class="built-in-portlet-tab">
	    <div id="ImgPortletList_<%=contentId %>">    
	    </div>
	</div>
</div>

