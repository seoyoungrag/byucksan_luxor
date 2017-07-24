<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.sds.acube.luxor.common.util.CommonUtil"%>
<%
	String guid = CommonUtil.generateId();
%>
<script type="text/javascript">
	$(function() {
		$( ".<%=guid%>datepicker_portlet" ).datepicker({
				//changeMonth: true,
				//changeYear: true,
				//yearRange: 'c-5:c+5', 옵션 사용
				showOtherMonths: true,
				numberOfMonths: 1,
				showMonthAfterYear:true,
				dayNamesMin:['<spring:message code="portal.portlet.label.8" text="일"/>', 
								'<spring:message code="portal.portlet.label.9" text="월"/>', 
								'<spring:message code="portal.portlet.label.10" text="화"/>', 
								'<spring:message code="portal.portlet.label.11" text="수"/>', 
								'<spring:message code="portal.portlet.label.12" text="목"/>', 
								'<spring:message code="portal.portlet.label.13" text="금"/>', 
							    '<spring:message code="portal.portlet.label.14" text="토"/>'],
				dayNamesShort:['<spring:message code="portal.portlet.label.8" text="일"/>', 
								'<spring:message code="portal.portlet.label.9" text="월"/>', 
								'<spring:message code="portal.portlet.label.10" text="화"/>', 
								'<spring:message code="portal.portlet.label.11" text="수"/>', 
								'<spring:message code="portal.portlet.label.12" text="목"/>', 
								'<spring:message code="portal.portlet.label.13" text="금"/>', 
								'<spring:message code="portal.portlet.label.14" text="토"/>'],
				yearSuffix:'<spring:message code="portal.portlet.label.0" text="년"/>',
				monthNames:['<spring:message code="portal.portlet.label.21" text="1월"/>', 
							'<spring:message code="portal.portlet.label.22" text="2월"/>', 
							'<spring:message code="portal.portlet.label.23" text="3월"/>', 
							'<spring:message code="portal.portlet.label.24" text="4월"/>', 
							'<spring:message code="portal.portlet.label.25" text="5월"/>', 
							'<spring:message code="portal.portlet.label.26" text="6월"/>', 
							'<spring:message code="portal.portlet.label.27" text="7월"/>', 
							'<spring:message code="portal.portlet.label.28" text="8월"/>', 
							'<spring:message code="portal.portlet.label.29" text="9월"/>', 
							'<spring:message code="portal.portlet.label.30" text="10월"/>', 
							'<spring:message code="portal.portlet.label.31" text="11월"/>', 
							'<spring:message code="portal.portlet.label.32" text="12월"/>'],
				monthNamesShort:['<spring:message code="portal.portlet.label.21" text="1월"/>', 
									'<spring:message code="portal.portlet.label.22" text="2월"/>', 
									'<spring:message code="portal.portlet.label.23" text="3월"/>', 
									'<spring:message code="portal.portlet.label.24" text="4월"/>', 
									'<spring:message code="portal.portlet.label.25" text="5월"/>', 
									'<spring:message code="portal.portlet.label.26" text="6월"/>', 
									'<spring:message code="portal.portlet.label.27" text="7월"/>', 
									'<spring:message code="portal.portlet.label.28" text="8월"/>', 
									'<spring:message code="portal.portlet.label.29" text="9월"/>', 
									'<spring:message code="portal.portlet.label.30" text="10월"/>', 
									'<spring:message code="portal.portlet.label.31" text="11월"/>', 
									'<spring:message code="portal.portlet.label.32" text="12월"/>'],
				dateFormat:'yy-mm-dd',
				onSelect: function(dateText, inst) {
					//TODO 클릭 이벤트시 이곳에서 처리
				}
				});
		$( ".<%=guid%>datepicker_portlet #format" ).change(function() {
			$( ".<%=guid%>datepicker_portlet" ).datepicker( "option", "dateFormat", $( this ).val() );
		});
		$(".<%=guid%>datepicker_portlet .ui-priority-secondary").attr("style","font-weight:normal;opacity:0.3;");
		$(".<%=guid%>datepicker_portlet > .ui-widget-content").attr("style","border:0px solid #AAAAAA;");
		$(".<%=guid%>datepicker_portlet .ui-state-highlight").attr("style","max-height:14px");
		$(".<%=guid%>datepicker_portlet .ui-datepicker").show();
		$(".<%=guid%>datepicker_portlet .ui-datepicker").addClass('w100percent');
	});
</script>
<div class="<%=guid%>datepicker_portlet"></div>