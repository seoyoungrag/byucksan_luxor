<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%-- 20120925 open-source license 문제 없는지 확인할 것 --%>
<script language="Javascript">
function addChar(input, ch) {
	if (input.value == null || input.value == "0")
		input.value = ch;
	else
		input.value += ch;
}

function cos(form) {
	form.display.value = Math.cos(form.display.value);
}

function sin(form) {
	form.display.value = Math.sin(form.display.value);
}

function tan(form) {
	form.display.value = Math.tan(form.display.value);
}

function sqrt(form) {
	form.display.value = Math.sqrt(form.display.value);
}

function ln(form) {
	form.display.value = Math.log(form.display.value);
}

function exp(form) {
	form.display.value = Math.exp(form.display.value);
}

function deleteChar(input) {
	input.value = input.value.substring(0, input.value.length - 1);
}

function changeSign(input) {
	if (input.value.substring(0, 1) == "-")
		input.value = input.value.substring(1, input.value.length);
	else
		input.value = "-" + input.value;
}

function compute(form) {
	form.display.value = eval(form.display.value);
}

function square(form) {
	form.display.value = eval(form.display.value) * eval(form.display.value);
}

function validate(text) {
	for (var i = 0; i < text.length; i++) {
		var ch = text.substring(i, i+1);
		if (ch >= "0" && ch <= "9" || ch == "/" || ch == "*" || ch == "+" || ch == "-" || ch == "." || ch == "(" || ch == ")")
			continue;
		/*
		if (ch < "0" || ch > "9") {
			if (ch != "/" && ch != "*" && ch != "+" && ch != "-" && ch != "." && ch != "(" && ch!= ")") {
				alert('<spring:message code="portal.portlet.alert.0" text="input type is invalid. Please check again."/>');
				return false;
			}
		}
		*/
		else
			return false;
	}
	return true;
}

$(document).ready(function() {
	$('#calculator_display').keypress(function(e) {
		if(e.keyCode==13) {
			$('#computebtn').click();
			return false;
		}
	});
});
</script>
<form name="sci-calc" action="">
<table style="width:100%;" cellspacing="0" cellpadding="1">
<tr>
	<td colspan="5" style="padding-right:10px;" class="">
		<input class="ui-datepicker-header ui-widget-header ui-corner-all ui-helper-clearfix" style="padding-left:6px; width:100%;height:20px;font-size: 20px;" id="calculator_display" name="display" value="0" size="28" maxlength="25" />
	</td>
</tr>
<tr>
	<td >
		<input class="ui-state-default" type="button" style="width:100%;" value="exp" onclick="if (validate(this.form.display.value)) { exp(this.form) }">
	</td>
	<td >
		<input class="ui-state-default" type="button" style="width:100%;" value="7" onclick="addChar(this.form.display, '7')">
	</td>
	<td >
		<input class="ui-state-default" type="button" style="width:100%;" value="8" onclick="addChar(this.form.display, '8')">
	</td>
	<td >
		<input class="ui-state-default" type="button" style="width:100%;" value="9" onclick="addChar(this.form.display, '9')">
	</td>
	<td >
		<input class="ui-state-default" type="button" style="width:100%;" value="/" onclick="addChar(this.form.display, '/')">
	</td>
</tr>
<tr>
	<td >
		<input class="ui-state-default" type="button" style="width:100%;" value="ln" onclick="if (validate(this.form.display.value)) { ln(this.form) }">
	</td>
	<td >
		<input class="ui-state-default" type="button" style="width:100%;" value="4" onclick="addChar(this.form.display, '4')">
	</td>
	<td >
		<input class="ui-state-default" type="button" style="width:100%;" value="5" onclick="addChar(this.form.display, '5')">
	</td>
	<td >
		<input class="ui-state-default" type="button" style="width:100%;" value="6" onclick="addChar(this.form.display, '6')">
	</td>
	<td >
		<input class="ui-state-default" type="button" style="width:100%;" value="*" onclick="addChar(this.form.display, '*')">
	</td>
</tr>
<tr>
	<td ><input class="ui-state-default" type="button" style="width:100%;" value="sqrt" onclick="if (validate(this.form.display.value)) { sqrt(this.form) }">
	</td>
	<td ><input class="ui-state-default" type="button" style="width:100%;" value="1" onclick="addChar(this.form.display, '1')">
	</td>
	<td ><input class="ui-state-default" type="button" style="width:100%;" value="2" onclick="addChar(this.form.display, '2')">
	</td>
	<td ><input class="ui-state-default" type="button" style="width:100%;" value="3" onclick="addChar(this.form.display, '3')">
	</td>
	<td ><input class="ui-state-default" type="button" style="width:100%;" value="-" onclick="addChar(this.form.display, '-')">
	</td>
</tr>
<tr>
	<td >
		<input class="ui-state-default" type="button" style="width:100%;" value="sq" onclick="if (validate(this.form.display.value)) { square(this.form) }">
	</td>
	<td >
		<input class="ui-state-default" type="button" style="width:100%;" value="0" onclick="addChar(this.form.display, '0')">
	</td>
	<td >
		<input class="ui-state-default" type="button" style="width:100%;" value="." onclick="addChar(this.form.display, '.')">
	</td>
	<td >
		<input class="ui-state-default" type="button" style="width:100%;" value="+/-" onclick="changeSign(this.form.display)">
	</td>
	<td >
		<input class="ui-state-default" type="button" style="width:100%;" value="+" onclick="addChar(this.form.display, '+')">
	</td>
</tr>
<tr>
	<td >
		<input class="ui-state-default" type="button" style="width:100%;" value="(" onclick="addChar(this.form.display, '(')">
	</td>
	<td >
		<input class="ui-state-default" type="button" style="width:100%;" value="cos" onclick="if (validate(this.form.display.value)) { cos(this.form) }">
	</td>
	<td >
		<input class="ui-state-default" type="button" style="width:100%;" value="sin" onclick="if (validate(this.form.display.value)) { sin(this.form) }">
	</td>
	<td >
		<input class="ui-state-default" type="button" style="width:100%;" value="tan" onclick="if (validate(this.form.display.value)) { tan(this.form) }">
	</td>
	<td >
		<input class="ui-state-default" type="button" style="width:100%;" value=")" onclick="addChar(this.form.display, ')')">
	</td>
</tr>
<tr>
	<td >
		<input class="ui-state-default" type="button" style="width:100%;" value="clear" onclick="this.form.display.value = 0 ">
	</td>
	<td  colspan="3">
		<input class="ui-state-default" type="button" style="width:100%;" value="backspace" onclick="deleteChar(this.form.display)">
	</td>
	<td >
		<input class="ui-state-default" id="computebtn" type="button" style="width:100%;" value="enter" name="enter" onclick="if (validate(this.form.display.value)) { compute(this.form) }">
	</td>
</tr>
</table>
</form>