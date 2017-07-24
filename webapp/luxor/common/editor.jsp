<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.framework.config.*" %>

<%
	// 수정모드일때는 원본글을 작성한 에디터에서 수정을 해야하므로
	// request로 에디터 타입을 먼저 받고 없을 경우에는 세션을 봄
	// 개인화에서 에디터 선택할 경우 세션에 저장되기때문에
	// 개인화된 에디터가 있는지 확인 후 없을 경우 
	// 시스템 설정에서 에디터 정보 가져옴
	// 에디터는 XINHA, NAMO 둘중 하나임
	String editorType = UtilRequest.getString(request, "EDITOR_TYPE");
	if(editorType.equals("")) {
		editorType = (String)session.getAttribute("EDITOR_TYPE");
		if(editorType==null) {
			editorType = LuxorConfig.getString("EDITOR.TYPE");
		}
	}
	
	// IE가 아닌 타브라우져는 무조건 XINHA로 셋팅
	String userAgent = request.getHeader("USER-AGENT");
	if(!userAgent.contains("MSIE")) {
		editorType = "XINHA";
	}
	
	// 수정모드일 경우 기존에 등록된 컨텐츠 가져옴
	String content = CommonUtil.decodeEscapedSpecialChar(CommonUtil.nullTrim(UtilRequest.getString(request, "CONTENT")));
	
	// 지나 에디터인 경우 컨텐츠 디코딩 필요
    if("XINHA".equals(editorType)) {
		content = Xinha.decode(content, Xinha.getPrefixID(session));
    }
%>


<%	if("XINHA".equals(editorType)) { %>

	<script type="text/javascript">
		var _editor_url  = "/ep/luxor/ref/js/xinha/";
		var _editor_lang = "ko";
		var _prefixID = "<%=Xinha.getPrefixID(session)%>";
	</script>
	<script type="text/javascript" src="/ep/luxor/ref/js/xinha/XinhaCore.js"></script>
	<script type="text/javascript" src="/ep/luxor/ref/js/xinha/XinhaConfig.js"></script>
	<script type="text/javascript">
		luxor.editor = {
			isSimpleToolbar: true,
			init: function() {
				if(!Xinha.loadPlugins(xinha_plugins, luxor.editor.init)) {
					return;
				}
			
				var xinha_config = getConfig(luxor.editor.isSimpleToolbar);
				
			  	xinha_editors = Xinha.makeEditors(xinha_editors, xinha_config, xinha_plugins);
			  	Xinha.startEditors(xinha_editors);
			},

			getContent: function() {
				return unescape(xinha_editors['wec'].getEditorContent());
			},

			setContent: function() {
				$('#content').val(luxor.editor.getContent());
			},

			setHeight: function(h) {
				luxor.$('#wec').style.height = h;
			},

			setSimpleToolbar: function(bool) {
				this.isSimpleToolbar = bool;
			}
		};
		
		Xinha.addOnloadHandler(luxor.editor.init);
			
	</script>
	<textarea id="wec" name="wec" style="width:100%;height:400px;"><%=CommonUtil.escapeSpecialChar(content)%></textarea>

<%	} else { %>

	<script type="text/javascript" for="wec" event="OnInitCompleted()">
	    DoCommand("mark_tag");
		// 수정일때 본문 셋팅
		luxor.$('#wec').MIMEValue = $('#content').val();
	</script>

	<script type="text/javascript">
		var contentMaxSize="<%=LuxorConfig.getString("EDITOR.CONTENT_MAX_SIZE")%>";
		function isOverDocSize(limit) {
		    var object1 = luxor.$('#wec').GetDocumentSize();
		    if (object1 > limit*1024 ) return true;
		    else return false;
		}
		
		luxor.editor = {
			setContent: function() {
			    if(luxor.trim(luxor.$('#wec').TextValue).length == 0) {
			        alert('fill out');
			        return;
			    }

				if(isOverDocSize(contentMaxSize)) {
		            alert("over doc size ("+(contentMaxSize*1024)+" Byte)");
			        return;
			    }
			    
			    $('#content').val(luxor.editor.getContent());
			},

			getContent: function() {
				return luxor.$('#wec').MIMEValue;
			},

			setHeight: function(h) {
				luxor.$('#wec').style.height = h;
			}
		};
	</script>

	<object width="0" height="0" classid="clsid:<%=LuxorConfig.getString("EDITOR.NAMO_LPK_CLASSID")%>">
		<param name="LPKPath" value="/ep/luxor/ref/plugins/namo/NamoWec6_acube_dev.lpk">
	</object>
	<object id="wec" width="100%" height="350px" classid="CLSID:<%=LuxorConfig.getString("EDITOR.NAMO_CAB_CLASSID")%>" 
			codebase="/ep/luxor/ref/plugins/namo/NamoWec.cab#version=<%=LuxorConfig.getString("EDITOR.NAMO_CAB_VERSION")%>">
		<param name="UseNamoNet" value="0" />
		<param name="InitFileURL" value="/ep/luxor/ref/plugins/namo/namowec.env" />
		<param name="InstallSourceURL" value="http://www.namo.co.kr/activesquare/products/update" />
	</object>
		
<%	} %>

<input type="hidden" id="content" name="content" value="<%=CommonUtil.escapeSpecialChar(content)%>" />

