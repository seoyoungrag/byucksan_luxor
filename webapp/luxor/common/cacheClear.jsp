<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.sds.acube.luxor.framework.cache.*" %>
<%@ page import="java.util.Iterator" %>
<jsp:include page="/luxor/common/header.jsp" />
<%
	MemoryCacheCenter center = MemoryCacheCenter.getInstance();
%>
<script type="text/javascript" src="/ep/luxor/ref/js/jquery.disable.text.select.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	$('table td a').live('click', function(e) {
		var keyValue = $(this).attr('id');
		var openURL = parent.document.location.href;
		luxor.popup("/ep/luxor/common/cacheClearExcute.jsp?keyValue="+keyValue+"&openURL="+openURL, {width:850,height:600});
	});
});
</script>
<div class="title-sub" style="margin-left:10px">
	<span class="title-sub-text">CACHE MANAGER</span>
</div>
<div class="admin-content-width w1000" style="margin-left:10px">
	<table class="table-body" id="cachecleartable">
		<thead>
			<tr>
				<th width="*">cacheKey</th>
				<th width="100">size</th>
				<th width="300px"><spring:message code="portal.label.31" text="기능" /></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>ACCESS_CONTROLL_SERVICE</td>
				<td><%=center.size("ACCESS_CONTROLL_SERVICE") %></td>
				<td>
					<span class="smain-btn">
						<a id="ACCESS_CONTROLL_SERVICE"><spring:message code="portal.btn.label.30" text="삭제" /></a>
					</span>
				</td>
			</tr>
			<tr>
				<td>MESSAGE_SERVICE</td>
				<td><%=center.size("MESSAGE_SERVICE") %></td>
				<td>
					<span class="smain-btn">
						<a id="MESSAGE_SERVICE"><spring:message code="portal.btn.label.30" text="삭제" /></a>
					</span>
				</td>
			</tr>
			<tr>
				<td>TREE_SERVICE</td>
				<td><%=center.size("TREE_SERVICE") %></td>
				<td>
					<span class="smain-btn">
						<a id="TREE_SERVICE"><spring:message code="portal.btn.label.30" text="삭제" /></a>
					</span>
				</td>
			</tr>
			<tr>
				<td>USER_SERVICE</td>
				<td><%=center.size("USER_SERVICE") %></td>
				<td>
					<span class="smain-btn">
						<a id="USER_SERVICE"><spring:message code="portal.btn.label.30" text="삭제" /></a>
					</span>
				</td>
			</tr>
			<tr>
				<td>USER_SETTING_SERVICE</td>
				<td><%=center.size("USER_SETTING_SERVICE") %></td>
				<td>
					<span class="smain-btn">
						<a id="USER_SETTING_SERVICE"><spring:message code="portal.btn.label.30" text="삭제" /></a>
					</span>
				</td>
			</tr>
			<tr>
				<td>MENU_SERVICE</td>
				<td><%=center.size("MENU_SERVICE") %></td>
				<td>
					<span class="smain-btn">
						<a id="MENU_SERVICE"><spring:message code="portal.btn.label.30" text="삭제" /></a>
					</span>
				</td>
			</tr>
			<tr>
				<td>MENU_ONLY_SERVICE</td>
				<td><%=center.size("MENU_ONLY_SERVICE") %></td>
				<td>Deleted with the 'MENU_SERVICE' Caches</td>
			</tr>
			<tr>
				<td>PORTLET_SERVICE</td>
				<td><%=center.size("PORTLET_SERVICE") %></td>
				<td>
					<span class="smain-btn">
						<a id="PORTLET_SERVICE"><spring:message code="portal.btn.label.30" text="삭제" /></a>
					</span>
				</td>
			</tr>
			<tr>
				<td>CLEAR_ALL</td>
				<td>&nbsp;</td>
				<td>
					<span class="smain-btn">
						<a id="CLEAR_ALL"><spring:message code="portal.btn.label.30" text="삭제" /></a>
					</span>
				</td>
			</tr>
			<tr>
				<td>DEBUG CACHE MONITORING ON</td>
				<td>&nbsp;</td>
				<td>
					<span class="smain-btn">
						<a id="MONITORING_ON">ON</a>
					</span>
				</td>
			</tr>
			<tr>
				<td>DEBUG CACHE MONITORING OFF</td>
				<td>&nbsp;</td>
				<td>
					<span class="smain-btn">
						<a id="MONITORING_OFF">OFF</a>
					</span>
				</td>
			</tr>
		</tbody>
	</table>
</div>