<%@page import="com.sds.acube.luxor.framework.config.LuxorConfig"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.sds.acube.luxor.common.util.UtilRequest"%>
<%
String userAgent = request.getHeader("USER-AGENT").toLowerCase();
String fileuploadYn = "";
if(userAgent.matches(".*(android.+mobile|avantgo|bada\\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od|ad)|iris|kindle|lge |maemo|midp|mmp|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|symbian|treo|up\\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino).*")||userAgent.substring(0,4).matches("1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|e\\-|e\\/|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(di|rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|xda(\\-|2|g)|yas\\-|your|zeto|zte\\-")){
	fileuploadYn = "none";
}
String contextPath = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath;
String authType = UtilRequest.getString(request,"mode");
String tenantId = (String)session.getAttribute("TENANT_ID") == null ? "T00001" :(String)session.getAttribute("TENANT_ID");
String portalId = (String)session.getAttribute("PORTAL_ID") == null ? "1310000" :(String)session.getAttribute("PORTAL_ID");
String messageType = UtilRequest.getString(request,"messageType", "1" );
String attachId = UtilRequest.getString(request,"attachmentId","test");
%>

<jsp:include page="/luxor/common/header.jsp" />
<link rel="stylesheet" href="<%=contextPath%>/luxor/ref/js/attachment/jquery/jquery.fileupload-ui.css">
<link rel="stylesheet" href="<%=contextPath%>/luxor/ref/js/attachment/jquery/css/smoothness/jquery-ui-1.8.14.custom.css">
<div id="fileupload" style="display:none;" class="<%=attachId%>">

	<form method="post" enctype="multipart/form-data">
        <div class="tb-write" id="attach01" style="width:100%">
		<table class="table-write attachment-dialog">
			<tr>
				<th width="250" id="messageType" style="text-align: left; padding-left:5px;"></th>
				<td>
					<% if(!authType.equals("view")){ %>
					<div class="fileupload-buttonbar">
			            <label class="fileinput-button">
			                <span>첨부</span>
			                <input type="file" name="files[]" multiple>
			            </label>
			            <button type="reset" class="cancel">작업취소</button>
			            <button type="button" class="delete">전체삭제</button>
			            <button type="submit" class="start">변경적용</button>
			        </div>
			        <% } %>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="file-list-div">
						<div class="fileupload-content" style="border: 0px solid;">
					        <table class="files"></table>
					        <div class="fileupload-progressbar"></div>
					    </div>
					</div>
				</td>
			</tr>
		</table>
	</div>
        
    </form>
    
</div>
<script id="template-upload" type="text/x-jquery-tmpl">
     <tr class="template-upload{{if error}} ui-state-error{{/if}}">
        <td class="preview">
            {{if thumbnail_url}}
                <a href=${"${url}"} target="_blank"><img src=${"${thumbnail_url}"}></a>
            {{/if}}
        </td>
        <td class="name">${"${name}"}</td>
        <td class="size">${"${sizef}"}</td>
		<td class="serverFileName">${"${name_temp}"}</td>
        {{if error}}
            <td class="error" colspan="2">Error:
                {{if error === 'maxFileSize'}}파일이 너무 큽니다
                {{else error === 'minFileSize'}}파일이 너무 작습니다
                {{else error === 'acceptFileTypes'}}허용하는 파일 종류가 아닙니다
                {{else error === 'maxNumberOfFiles'}}제한된 파일 개수를 초과했습니다
                {{else}}${error}
                {{/if}}
            </td>
        {{else}}
            <td class="progress"><div></div></td>
            <td class="start"><button>Start</button></td>
        {{/if}}
        <td class="cancel"><button>Cancel</button></td>
    </tr>
</script>
<script id="template-download" type="text/x-jquery-tmpl">
    <tr class="template-download{{if error}} ui-state-error{{/if}}">
        {{if error}}
            <td class="name">${"${name}"}</td>
            <td class="size">${"${sizef}"}</td>
            <td class="error" colspan="2">Error:
                {{if error === 1}}File exceeds upload_max_filesize (php.ini directive)
                {{else error === 2}}File exceeds MAX_FILE_SIZE (HTML form directive)
                {{else error === 3}}File was only partially uploaded
                {{else error === 4}}No File was uploaded
                {{else error === 5}}Missing a temporary folder
                {{else error === 6}}Failed to write file to disk
                {{else error === 7}}File upload stopped by extension
                {{else error === 'maxFileSize'}}File is too big
                {{else error === 'minFileSize'}}File is too small
                {{else error === 'acceptFileTypes'}}Filetype not allowed
                {{else error === 'maxNumberOfFiles'}}Max number of files exceeded
                {{else error === 'uploadedBytes'}}Uploaded bytes exceed file size
                {{else error === 'emptyResult'}}Empty file upload result
                {{else}}${error}
                {{/if}}
            </td>
        {{else}}
            <td class="preview">
                {{if thumbnail_url}}
                    <a href=${"${url}"} target="_blank"><img src=${"${thumbnail_url}"}></a>
                {{/if}}
            </td>
            <td class="name" id=${"${name_temp}"}>
                <a href=${"${url}"} onclick=javascript:alert('ddd'); {{if thumbnail_url}} target="_blank"{{/if}}>${"${name}"}</a>
            </td>
            <td class="size">${"${sizef}"}</td>
            <td colspan="2"></td>
        {{/if}}
<% if(!authType.equals("view")){ %>
		<td class="download">
            <button>download</button>
        </td>
        <td class="delete">
            <button data-type=${"{delete_type}"} data-url=${"${delete_url}"}>Delete</button>
        </td>
    </tr>
<% } %>
</script>
<script>
var attachmentId = "<%=attachId%>";
var moduleDiv = "<%=UtilRequest.getString(request,"moduleDiv","COMMON.TEST" );
var uploadPath = "<%=LuxorConfig.getString("Common", "ATTACH.UPLOAD_TEMP")%>";
var basePath = "<%=basePath%>";
var upload_attachmax = 25*1024*1024;
var bal_overLimitSize = "" +(upload_attachmax/1024/1024)+ "mb 이상의 첨부는 불가능합니다.";
var tenantId = "<%=tenantId%>";
var portalId = "<%=portalId%>";
var messageType = "<%=messageType%>";

</script>
 <script type="text/javascript">

$(document).ready(function() {
	
	if(messageType == 1) {
		$("#messageType").html('문의파일첨부');
	} else if (messageType == 2) {
		$("#messageType").html('중간답변파일첨부');
	} else if (messageType == 3) {
		$("#messageType").html('최종답변파일첨부');
	} else if (messageType == 4) {
		$("#messageType").html('지원내역파일첨부');
	} else if (messageType == 5) {
		$("#messageType").html('전달내역파일첨부');
	} else if (messageType == 6) {
		$("#messageType").html('답변파일첨부');
	} else {
		$("#messageType").html('파일첨부');
	}
	
	//var interval = setInterval(function(){
	//	reloadIframe();
	//	}, 1000);

	}
);

function reloadIframe() {
	var height = $('.<%=attachId%> .fileupload-content').height()+42;
	if($('.<%=attachId%> .fileupload-content').height() != 20) {
		parent.resizeIframe("<%=attachId%>",$('.<%=attachId%> .fileupload-content').height()+42);
		$('.<%=UtilRequest.getString(request,"attachmentId")%>').attr('style','overflow:hidden;height:'+height+'px;');
	} else {
		parent.resizeIframe("<%=attachId%>",$('.<%=attachId%> .fileupload-content').height()+42);
		$('.<%=attachId%>').attr('style','overflow:hidden;height:70px;');
	}
}
</script>
<script src="<%=contextPath%>/luxor/ref/js/attachment/jquery/jquery-1.6.1.js"></script>
<script src="<%=contextPath%>/luxor/ref/js/attachment/jquery/acube-fileupload.js"></script>
<script src="<%=contextPath%>/luxor/ref/js/attachment/jquery/jquery-ui-1.8.14.custom.min.js"></script>
<script src="<%=contextPath%>/luxor/ref/js/attachment/jquery/jquery.tmpl.min.js"></script>
<script src="<%=contextPath%>/luxor/ref/js/attachment/jquery/jquery.iframe-transport.js"></script>
<script src="<%=contextPath%>/luxor/ref/js/attachment/jquery/jquery.fileupload.js"></script>
<script src="<%=contextPath%>/luxor/ref/js/attachment/jquery/jquery.fileupload-ui.js"></script>
<script src="<%=contextPath%>/luxor/ref/js/attachment/json2.js"></script>