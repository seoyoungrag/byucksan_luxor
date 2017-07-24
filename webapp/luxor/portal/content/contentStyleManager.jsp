<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<script type="text/javascript">
	$(document).ready(function() {
		$("#autoheight_radio").buttonset();
		$("#titlebar_radio").buttonset();
		$("#border_radio").buttonset();
		$("#max_radio").buttonset();
		$("#min_radio").buttonset();
		$("#help_radio").buttonset();
		$("#edit_radio").buttonset();
		$("#reload_radio").buttonset();
		$("#styleManager").dialog({
			resizable:false,
			autoOpen:false,
			modal:true,
			width:260,
			open:function(e, ui) {
			},
			close:function(e, ui) {
				if(content.refer=='PM' && content.isModified==true) {
					document.location.href = '/ep/page/manager.do?nodeId='+selectedNodeId;
				}
			}
		});
	});
</script>

<div id='styleManager' title="<spring:message code="portal.btn.label.27" text="스타일 관리" />" style="z-index:99;">
	<table class="table-write">
		<tr id="contentName">
			<th width="80px">
				<spring:message code="portal.btn.label.26" text="컨텐츠명" />
			</th>
			<td>
				<jsp:include page="/luxor/common/message.jsp">
					<jsp:param name="namespace" value="c" />
					<jsp:param name="message_type" value="PORTAL.CONTENT" />
				</jsp:include>
			</td>
		</tr>
		<tr>
			<th width="80px"><spring:message code="portal.btn.label.15" text="높이" /></th>
			<td><input type='text' id="content_height" size='5' /> px</td>
		</tr>
		<tr id="autoheight" style="display: none;">
			<th width="80px"><spring:message code="portal.btn.label.42" text="자동높이" /></th>
			<td>
				<div id="autoheight_radio">
					<input type="radio" id="autoheight_r1" name="autoheight" value="on" /><label for="autoheight_r1">On</label>
					<input type="radio" id="autoheight_r2" name="autoheight" value="off" checked="checked" /><label for="autoheight_r2">Off</label>
					<input type="hidden" id="isAutoHeight" />
				</div>
			</td>
		</tr>
		<tr id="autoheight_alert" style="display: none;">
			<th width="80px"><spring:message code="portal.btn.label.42" text="자동높이" /></th>
			<td>
			<!-- content.js에서 동적으로 message 생성 -->
			</td>
		</tr>
		<tr>
			<th width="80px"><spring:message code="portal.btn.label.12" text="제목" /></th>
			<td>
				<div id="titlebar_radio">
					<input type="radio" id="titlebar_r1" name="title" value="on" checked="checked" /><label for="titlebar_r1">On</label>
					<input type="radio" id="titlebar_r2" name="title" value="off" /><label for="titlebar_r2">Off</label>
				</div>
			</td>
		</tr>
		<tr>
			<th width="80px"><spring:message code="portal.btn.label.14" text="테두리" /></th>
			<td>
				<div id="border_radio">
					<input type="radio" id="border_r1" name="border" value="on" checked="checked" /><label for="border_r1">On</label>
					<input type="radio" id="border_r2" name="border" value="off" /><label for="border_r2">Off</label>
				</div>
			</td>
		</tr>
		<tr>
			<th width="80px"><spring:message code="portal.btn.label.16" text="최대화" /></th>
			<td>
				<div id="max_radio">
					<input type="radio" id="max_r1" name="menuMax" value="on" checked="checked" /><label for="max_r1">On</label>
					<input type="radio" id="max_r2" name="menuMax" value="off" /><label for="max_r2">Off</label>
				</div>
			</td>
		</tr>
		<tr>
			<th width="80px"><spring:message code="portal.btn.label.17" text="최소화" /></th>
			<td>
				<div id="min_radio">
					<input type="radio" id="min_r1" name="menuMin" value="on" checked="checked" /><label for="min_r1">On</label>
					<input type="radio" id="min_r2" name="menuMin" value="off" /><label for="min_r2">Off</label>
				</div>
			</td>
		</tr>
		<tr>
			<th width="80px"><spring:message code="portal.btn.label.20" text="도움말" /></th>
			<td>
				<div id="help_radio">
					<input type="radio" id="help_r1" name="menuHelp" value="on" checked="checked" /><label for="help_r1">On</label>
					<input type="radio" id="help_r2" name="menuHelp" value="off" /><label for="help_r2">Off</label>
				</div>
			</td>
		</tr>
		<tr>
			<th width="80px"><spring:message code="portal.btn.label.19" text="수정" /></th>
			<td>
				<div id="edit_radio">
					<input type="radio" id="edit_r1" name="menuEdit" value="on" /><label for="edit_r1">On</label>
					<input type="radio" id="edit_r2" name="menuEdit" value="off" /><label for="edit_r2">Off</label>
				</div>
			</td>
		</tr>
		<tr>
			<th width="80px"><spring:message code="portal.btn.label.18" text="새로고침" /></th>
			<td>
				<div id="reload_radio">
					<input type="radio" id="reload_r1" name="menuReload" value="on" checked="checked" /><label for="reload_r1">On</label>
					<input type="radio" id="reload_r2" name="menuReload" value="off" /><label for="reload_r2">Off</label>
				</div>
			</td>
		</tr>
	</table>
	
	<div id="csmButtonGroup" class="admin-right-bottom-btn-group">
		<span class="main-btn" id="initButton" style="display:none;"><a href="#" mode='reset'><spring:message code="portal.btn.label.21" text="초기화" /></a></span>
		<span class="main-btn"><a href="#" mode='save'><spring:message code="portal.btn.label.3" text="저장" /></a></span>
		<span class="main-btn" id="cancelButton" style="display:none;"><a href="#" mode='cancel'><spring:message code="portal.btn.label.2" text="취소" /></a></span>
	</div>
</div>
