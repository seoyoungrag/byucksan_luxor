<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.framework.config.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>

<%
	String ns = CommonUtil.nullTrim(UtilRequest.getString(request, "namespace"));
	String inputCssClass = CommonUtil.nullTrim(UtilRequest.getString(request, "input_css_class"));
	String inputCssStyle = CommonUtil.nullTrim(UtilRequest.getString(request, "input_css_style"));
	String messageType = UtilRequest.getString(request, "message_type");
	String checkDuplicate = UtilRequest.getString(request, "check_duplicate");
	
	if(CommonUtil.isNullOrEmpty(messageType)) {
		messageType = "PORTAL.COMMON";
	}
	if(CommonUtil.isNullOrEmpty(checkDuplicate)) {
		checkDuplicate = "N";
	}
	
	String[] localeLang = LuxorConfig.getStringArray("LOCALE.LANG");
	String[] localeName = LuxorConfig.getStringArray("LOCALE.DISPLAY-NAME");
	
	boolean isSingleLang = false;
	if(localeLang.length <= 1) {
		isSingleLang = true;
	}
	
	Locale sessLangType = (Locale)session.getAttribute("LANG_TYPE");
	String langType = sessLangType == null ? "ko" : sessLangType.toString();
%>

<script type="text/javascript">
var <%=ns%>Message = {
	dupilcationCheck: false,
	/**
	 * input 필드의 값을 초기화 시킴
	 * 글 등록 후 이 함수를 호출하여 값들을 초기화 시킴
	 */
	reset: function() {
		$('#<%=ns%>__message_multi_input input:text').each(function() {
			$(this).val('');
		});
	},
	
	/**
	 * 이 함수는 수정모드일때 호출됨
	 * message를 사용하는 모듈에서 이 함수를 호출하면서 messageId를 넘겨주면
	 * form에 자동으로 해당 값들을 미리 셋팅해놓음
	 */
	setMessageId: function(messageId, callBack) {
		var param = "messageId="+messageId;
		callJson("messageController", "getMessageById", param, function(data) {
			$('#<%=ns%>__messageId').val(messageId);
			<%	if(isSingleLang) { %>
			if(data.messageName.indexOf("&") > -1) {
				data.messageName = data.messageName.replace(/amp;/gi,"");
			}
			$('#<%=ns%>__'+data.langType).val(data.messageName);
			$('#<%=ns%>__message_input_all').val(<%=ns%>Message._concatMessage());
			<%	} else { %>
			for(var i in data) {
				if(typeof(data[i].langType)=='undefined') {
					continue;
				}
				if(data[i].messageName.indexOf("&") > -1) {
					data[i].messageName = data[i].messageName.replace(/amp;/gi,"");
				}
				$('#<%=ns%>__'+data[i].langType).val(data[i].messageName);
				// 현재 선택된 언어로 셋팅
				if(data[i].langType=='<%=langType%>') {
					try {
						$('#<%=ns%>__message_input').val(data[i].messageName);
					} catch(e) {}
				}
			}
			$('#<%=ns%>__message_input_all').val(<%=ns%>Message._concatMessage());
			<%	} %>

			try {
				callBack();
			} catch(e) {}
		});
	},
	/**
	 * 메세지 타입 조회
	 */
	getLangType: function() {
		return "<%=langType%>";
	},
	
	/**
	 * 메세지 타입 조회
	 */
	getMessageType: function() {
		return $('#<%=ns%>__messageType').val();
	},

	/**
	 * 기본 언어에 입력된 값 조회
	 */
	getDefaultMessageName: function() {
		return $('#<%=ns%>__message_multi_input input:text').first().val();
	},

	/**
	 * 입력된 다국어 언어코드 + 값 조회
	 * return ko^한글|en^영어
	 */
	getMessageNameAll: function() {
		return $('#<%=ns%>__message_input_all').val();
	},

	/**
	 * Ajax로 다국어 정보 조회시 호출
	 * ex) var koMsg = Message.getMessage('messageId','ko');
	 */
	getMessage: function(messageId, langType) {
		var param = "messageId="+messageId+"&langType="+langType;
		callJson("messageController", "getMessageByIdLang", param, function(data) {
			try {
				if(data.messageName.indexOf("&") > -1) {
					data.messageName = data.messageName.replace(/amp;/gi,"");
				}
				return data.messageName;
			} catch(e) {}
		});
	},

	/**
	 * private
	 */
	_concatMessage: function() {
		var param = "";
		$('#<%=ns%>__message_multi_input input:text').each(function() {
<%	if(isSingleLang==false) { %>			
			if(luxor.trim($(this).val())=="") {
				alert("<spring:message code="portal.alert.msg.3" text="이름 입력 후 진행해주세요." />");
				param = "";
				return false;
			}
<%	} %>			
			var lang = $(this).attr('id').replace("<%=ns%>__","");
			param += lang+"^"+$(this).val()+"|";
		});
		param = param.substring(0,param.length-1);
		return param;
	},

	/**
	 * 중복체크
	 * alertOnlyDup이 true이면 중복된다라는 메세지만 뿌려줌
	 */
	checkDup: function(alertOnlyDup) {
		if(!alertOnlyDup) {
			alertOnlyDup = false;
		}
		var result = false;
		var param = this._concatMessage();
		if(param!="") {
			$.ajax({
				url: "/ep/message/isDuplicate.do?messageNameAll="+encodeURIComponent(param)+"&messageType=<%=messageType%>",
				async: false,
				global: false,
				success: function(data) {
					if(eval(data)) {
						alert("<spring:message code="portal.alert.msg.1" text="중복됩니다." />");
						this.dupilcationCheck = false;
						result = false;
					} else {
						if(alertOnlyDup==false) {
							alert("<spring:message code="portal.alert.msg.2" text="사용가능합니다." />");
							this.dupilcationCheck = true;
						}
						result = true;
					}
				}
			});
		}
		return result;
	},

	/**
	 * 저장
	 */
	save: function() {
		var concatedMessage = this._concatMessage();
		if(concatedMessage==false) {
			$('#<%=ns%>__message_multi_input input:text').first().focus();
			return false;
		}
		$('#<%=ns%>__message_input').val($('#<%=ns%>__message_multi_input #<%=ns%>__<%=langType%>').val());

		<%	if("Y".equals(checkDuplicate)) { %>
			if(this.checkDup(true)==false) {
				$('#<%=ns%>__message_multi_input input:text').first().focus();
				this.dupilcationCheck = false;
				return false;
			} else {
				this.dupilcationCheck = true;
			}
		<%	} else { %>
				this.dupilcationCheck = true;
		<%  } %>
		
		$('#<%=ns%>__message_input_all').val(concatedMessage);
		
		try {
			$('#<%=ns%>__message_multi_input').dialog('close');
			_setMessageCallBack($('#<%=ns%>__message_input').val(), $('#<%=ns%>__message_input_all').val());
		} catch(e) {}
	}
}


$(function() {
<%	if(isSingleLang==false) { %>
	/* 다국어 입력창 팝업 */
	$('#<%=ns%>__message_multi_input').dialog({
		autoOpen:false,
		width:230,
		resizable:false,
		height:150,
		modal:true
	});
	
	$('#<%=ns%>__message_input').focus(function(e) {
		var pos = $(this).offset();
		$('#<%=ns%>__message_multi_input').dialog({position:[pos.left,pos.top]});
		$('#<%=ns%>__message_multi_input').dialog('open');
		$('#<%=ns%>__message_input').attr('readonly','readonly');
		$('#<%=ns%>__message_multi_input').attr('style','width: 90%; min-height: 0px;');
		$('#<%=ns%>__message_multi_input input:text').first().focus();
	});
<%	} %>

	$('#<%=ns%>__btn_cancel').click(function() {
		$('#<%=ns%>__message_multi_input').dialog('close');
		return false;
	});

<%	if(isSingleLang) { %>
	$('#<%=ns%>__<%=localeLang[0]%>').blur(function() {
		$('#<%=ns%>__message_input_all').val(<%=ns%>Message._concatMessage());
	});
<%	} %>

	$('input[mode=multi_lang_input]').last().keyup(function(e) {
		if(e.keyCode==13) {
			<%=ns%>Message.save();
		}
	});
});
</script>

<style type="text/css">
	/* 다국어 입력창 */
	#<%=ns%>__ko {ime-mode:active;}
	#<%=ns%>__en {ime-mode:inactive;}
</style>

<%	if(isSingleLang) { %>
<span id="<%=ns%>__message_multi_input" title="<spring:message code="portal.label.59" text="다국어 입력" />">
	<input type="text" id="<%=ns%>__<%=localeLang[0]%>" value="" class="input-d <%=inputCssClass%>" style="width:100%;<%=inputCssStyle%>" />
</span>
<%	} else { %>
<input type="text" id="<%=ns%>__message_input" value="" tabindex="-1" class="input-d no-special-char <%=inputCssClass%>" style="width:99%;<%=inputCssStyle%>" />
<div id="<%=ns%>__message_multi_input" class="tb-write" title="<spring:message code="portal.label.59" text="다국어 입력" />">
	<table class="table-write">
		<colgroup><col width="30%" /><col width="70%" /></colgroup>
		<%	for(int i=0; i < localeLang.length; i++) { %>
		<tr>
			<th><%=localeName[i]%></th>
			<td><input type="text" id="<%=ns%>__<%=localeLang[i]%>" mode="multi_lang_input" value="" class="input-d no-special-char" style="width:97%" /></td>
		</tr>
		<%	} %>
		<tr>
			<td colspan="2">
				<div class="aright mt5">
				<%	if("Y".equals(checkDuplicate)) { %>
					<span class="main-btn"><a href="#" onclick="<%=ns%>Message.checkDup();return false;"><spring:message code="portal.btn.label.4" text="중복확인" /></a></span>
				<%	} %>
					<span class="main-btn"><a href="#" onclick="<%=ns%>Message.save();return false;"><spring:message code="portal.btn.label.1" text="확인" /></a></span>
					<span class="main-btn"><a id="<%=ns%>__btn_cancel" href="#"><spring:message code="portal.btn.label.2" text="취소" /></a></span>
				</div>
			</td>
		</tr>
	</table>
</div>
<%	} %>

<input type="hidden" id="<%=ns%>__messageId" name="messageId" />
<input type="hidden" id="<%=ns%>__message_input_all" name="messageNameAll" />
<input type="hidden" id="<%=ns%>__messageType" name="messageType" value="<%=messageType%>" />
