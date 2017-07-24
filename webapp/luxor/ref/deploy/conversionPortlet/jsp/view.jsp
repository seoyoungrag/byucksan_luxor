<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript">
function convert(value){
	var fromvalue = $('#'+value+'Content .fromunits').val();
	var tovalue = $('#'+value+'Content .tounits').val();
	$('#'+value+'Content .tovalue').val(($('#'+value+'Content .fromvalue').val() * fromvalue) / tovalue);
 	return true;
}

function clearfield(form){
    form.fromvalue.value = "";
    return true;
}

function selectConversionCalculatorTab(value){
	$('#conversionCalculatorTab li').each(function() {
		$(this).removeClass();
	});
	$('#'+value).addClass('selected');
	$('#conversionCalculatorTab li').each(function() {
		if($(this).attr('class')=='selected'){
			var showId = $(this).attr('id');
			$('.portlet-main').hide();
			$('#'+showId+'Content').show();
		}
	});
}

$(document).ready(function() {
	$('#conversionCalculatorTab li').each(function() {
		if($(this).attr('class')=='selected'){
			var showId = $(this).attr('id');
			$('.portlet-main').hide();
			$('#'+showId+'Content').show();
		}
	});
});
</script>
<div class="built-in-portlet-tab">
	<ul id="conversionCalculatorTab">
		<li id="length" class="selected"><a href="javascript:selectConversionCalculatorTab('length')"><spring:message code="portal.portlet.label.43" text="길이"/></a></li>
        <li id="velocity"><a href="javascript:selectConversionCalculatorTab('velocity')"><spring:message code="portal.portlet.label.44" text="속도"/></a></li>
        <li id="area"><a href="javascript:selectConversionCalculatorTab('area')"><spring:message code="portal.portlet.label.45" text="영역"/></a></li>
        <li id="mass"><a href="javascript:selectConversionCalculatorTab('mass')"><spring:message code="portal.portlet.label.46" text="질량"/></a></li>
      	<li id="volume"><a href="javascript:selectConversionCalculatorTab('volume')"><spring:message code="portal.portlet.label.47" text="부피"/></a></li>
        <li id="presure"><a href="javascript:selectConversionCalculatorTab('presure')"><spring:message code="portal.portlet.label.48" text="압력"/></a></li>
	</ul>
</div>
<div id="lengthContent" class="portlet-main mt5 clear" style="width:100%;">
    <span class="portlet-black-bold"><spring:message code="portal.portlet.label.40" text="입력값"/></span>
    <input type="text" value="1" class="input-d fromvalue" style="width:30%;" />
    <select class="select-d fromunits" style="width:30%;">
        <option value="1" selected="selected"><spring:message code="portal.portlet.label.50" text="미터"/></option>
		<option value="0.001"><spring:message code="portal.portlet.label.51" text="밀리미터"/></option>
		<option value="0.01"><spring:message code="portal.portlet.label.52" text="센티미터"/></option>
		<option value="1000"><spring:message code="portal.portlet.label.53" text="킬로미터"/></option>
		<option value="0.0254"><spring:message code="portal.portlet.label.54" text="인치"/></option>
		<option value="0.3048"><spring:message code="portal.portlet.label.55" text="피트"/></option>
		<option value="0.9144"><spring:message code="portal.portlet.label.56" text="야드"/></option>
		<option value="1609.344"><spring:message code="portal.portlet.label.57" text="마일"/></option>
		<option value="1852"><spring:message code="portal.portlet.label.58" text="해리"/></option>
		<option value="9460000000000000"><spring:message code="portal.portlet.label.59" text="광년"/></option>
    </select>
	<div class="clear mt5"></div>
    <span class="portlet-black-bold"><spring:message code="portal.portlet.label.41" text="변환값"/></span>
    <input type="text" class="input-d tovalue" readonly="readonly" style="width:30%;" />
    <select class="select-d tounits" style="width:30%;">
        <option value="1"><spring:message code="portal.portlet.label.50" text="미터"/></option>
		<option value="0.001" selected="selected"><spring:message code="portal.portlet.label.51" text="밀리미터"/></option>
		<option value="0.01"><spring:message code="portal.portlet.label.52" text="센티미터"/></option>
		<option value="1000"><spring:message code="portal.portlet.label.53" text="킬로미터"/></option>
		<option value="0.0254"><spring:message code="portal.portlet.label.54" text="인치"/></option>
		<option value="0.3048"><spring:message code="portal.portlet.label.55" text="피트"/></option>
		<option value="0.9144"><spring:message code="portal.portlet.label.56" text="야드"/></option>
		<option value="1609.344"><spring:message code="portal.portlet.label.57" text="마일"/></option>
		<option value="1852"><spring:message code="portal.portlet.label.58" text="해리"/></option>
		<option value="9460000000000000"><spring:message code="portal.portlet.label.59" text="광년"/></option>
    </select>
    <span class="smain-btn" onclick="convert('length');"><a><spring:message code="portal.portlet.label.42" text="변환하기"/></a></span>
</div>

<div id="velocityContent" class="portlet-main mt5 clear" style="width:100%;">
    <span class="portlet-black-bold"><spring:message code="portal.portlet.label.40" text="입력값"/></span>
    <input type="text" value="1" class="input-d fromvalue" style="width:30%;" />
    <select class="select-d fromunits" style="width:30%;">
	    <option value="1"><spring:message code="portal.portlet.label.60" text="m/s"/></option>
        <option value="1000"><spring:message code="portal.portlet.label.61" text="km/s"/></option>
        <option value="0.000277777777777777777777777777777777"><spring:message code="portal.portlet.label.62" text="m/h"/></option>
		<option value="0.277777777777777777777777777777777777"><spring:message code="portal.portlet.label.63" text="km/h"/></option>
		<option value="0.3048"><spring:message code="portal.portlet.label.64" text="ft/s"/></option>
		<option value="0.00508"><spring:message code="portal.portlet.label.65" text="ft/m"/></option>
		<option value="0.514444"><spring:message code="portal.portlet.label.66" text="노트"/></option>
		<option value="340"><spring:message code="portal.portlet.label.67" text="마하"/></option>
    </select>
	<div class="clear mt5"></div>
    <span class="portlet-black-bold"><spring:message code="portal.portlet.label.41" text="변환값"/></span>
    <input type="text" class="input-d tovalue" readonly="readonly" style="width:30%;" />
    <select class="select-d tounits" style="width:30%;">
        <option value="1"><spring:message code="portal.portlet.label.60" text="m/s"/></option>
        <option value="1000" selected="selected"><spring:message code="portal.portlet.label.61" text="km/s"/></option>
        <option value="0.000277777777777777777777777777777777"><spring:message code="portal.portlet.label.62" text="m/h"/></option>
		<option value="0.277777777777777777777777777777777777"><spring:message code="portal.portlet.label.63" text="km/h"/></option>
		<option value="0.3048"><spring:message code="portal.portlet.label.64" text="ft/s"/></option>
		<option value="0.00508"><spring:message code="portal.portlet.label.65" text="ft/m"/></option>
		<option value="0.514444"><spring:message code="portal.portlet.label.66" text="노트"/></option>
		<option value="340"><spring:message code="portal.portlet.label.67" text="마하"/></option>
    </select>
    <span class="smain-btn" onclick="convert('velocity');"><a><spring:message code="portal.portlet.label.42" text="변환하기"/></a></span>
</div>

<div id="areaContent" class="portlet-main mt5 clear" style="width:100%;">
    <span class="portlet-black-bold"><spring:message code="portal.portlet.label.40" text="입력값"/></span>
    <input type="text" value="1" class="input-d fromvalue" style="width:30%;" />
    <select class="select-d fromunits" style="width:30%;">
       	<option value="1"><spring:message code="portal.portlet.label.70" text="제곱미터"/></option>
		<option value="0.0001"><spring:message code="portal.portlet.label.71" text="제곱센티미터"/></option>
		<option value="1000000"><spring:message code="portal.portlet.label.72" text="제곱킬로미터"/></option>
		<option value="1000"><spring:message code="portal.portlet.label.73" text="헥타르(ha)"/></option>
		<option value="0.00064516"><spring:message code="portal.portlet.label.74" text="제곱인치"/></option>
		<option value="0.09290304"><spring:message code="portal.portlet.label.75" text="제곱피트"/></option>
		<option value="0.83612736"><spring:message code="portal.portlet.label.76" text="제곱야드"/></option>
		<option value="4046.856422"><spring:message code="portal.portlet.label.77" text="에이커(ac)"/></option>
		<option value="3.305785"><spring:message code="portal.portlet.label.78" text="평"/></option>
    </select>
	<div class="clear mt5"></div>
    <span class="portlet-black-bold"><spring:message code="portal.portlet.label.41" text="변환값"/></span>
    <input type="text" class="input-d tovalue" readonly="readonly" style="width:30%;" />
    <select class="select-d tounits" style="width:30%;">
       	<option value="1"><spring:message code="portal.portlet.label.70" text="제곱미터"/></option>
		<option value="0.0001" selected="selected"><spring:message code="portal.portlet.label.71" text="제곱센티미터"/></option>
		<option value="1000000"><spring:message code="portal.portlet.label.72" text="제곱킬로미터"/></option>
		<option value="1000"><spring:message code="portal.portlet.label.73" text="헥타르(ha)"/></option>
		<option value="0.00064516"><spring:message code="portal.portlet.label.74" text="제곱인치"/></option>
		<option value="0.09290304"><spring:message code="portal.portlet.label.75" text="제곱피트"/></option>
		<option value="0.83612736"><spring:message code="portal.portlet.label.76" text="제곱야드"/></option>
		<option value="4046.856422"><spring:message code="portal.portlet.label.77" text="에이커(ac)"/></option>
		<option value="3.305785"><spring:message code="portal.portlet.label.78" text="평"/></option>
    </select>
   <span class="smain-btn" onclick="convert('area');"><a><spring:message code="portal.portlet.label.42" text="변환하기"/></a></span>
</div>

<div id="massContent" class="portlet-main mt5 clear" style="width:100%;">
    <span class="portlet-black-bold"><spring:message code="portal.portlet.label.40" text="입력값"/></span>
    <input type="text" value="1" class="input-d fromvalue" style="width:30%;" />
    <select class="select-d fromunits" style="width:30%;">
        <option value="1"><spring:message code="portal.portlet.label.80" text="킬로그램(kg)"/></option>
		<option value="0.001"><spring:message code="portal.portlet.label.81" text="그램(g)"/></option>
		<option value="0.000001"><spring:message code="portal.portlet.label.82" text="밀리그램(mg)"/></option>
		<option value="0.028349523125"><spring:message code="portal.portlet.label.83" text="온스(oz)"/></option>
		<option value="0.45359237"><spring:message code="portal.portlet.label.84" text="파운드(lb)"/></option>
		<option value="1000"><spring:message code="portal.portlet.label.85" text="톤(t)"/></option>
		<option value="0.6"><spring:message code="portal.portlet.label.86" text="근"/></option>
    </select>
	<div class="clear mt5"></div>
    <span class="portlet-black-bold"><spring:message code="portal.portlet.label.41" text="변환값"/></span>
    <input type="text" class="input-d tovalue" readonly="readonly" style="width:30%;" />
    <select class="select-d tounits" style="width:30%;">
   		<option value="1"><spring:message code="portal.portlet.label.80" text="킬로그램(kg)"/></option>
		<option value="0.001" selected="selected"><spring:message code="portal.portlet.label.81" text="그램(g)"/></option>
		<option value="0.000001"><spring:message code="portal.portlet.label.82" text="밀리그램(mg)"/></option>
		<option value="0.028349523125"><spring:message code="portal.portlet.label.83" text="온스(oz)"/></option>
		<option value="0.45359237"><spring:message code="portal.portlet.label.84" text="파운드(lb)"/></option>
		<option value="1000"><spring:message code="portal.portlet.label.85" text="톤(t)"/></option>
		<option value="0.6"><spring:message code="portal.portlet.label.86" text="근"/></option>
    </select>
   <span class="smain-btn" onclick="convert('mass');"><a><spring:message code="portal.portlet.label.42" text="변환하기"/></a></span>
</div>

<div id="volumeContent" class="portlet-main mt5 clear" style="width:100%;">
    <span class="portlet-black-bold"><spring:message code="portal.portlet.label.40" text="입력값"/></span>
    <input type="text" value="1" class="input-d fromvalue" style="width:30%;" />
    <select class="select-d fromunits" style="width:30%;">
      	<option value="1"><spring:message code="portal.portlet.label.90" text="리터"/></option>
		<option value="0.001"><spring:message code="portal.portlet.label.91" text="밀리리터(ml)"/></option>
		<option value="1000"><spring:message code="portal.portlet.label.92" text="세제곱미터"/></option>
		<option value="0.016387064"><spring:message code="portal.portlet.label.93" text="세제곱인치"/></option>
		<option value="28.316846592"><spring:message code="portal.portlet.label.94" text="세제곱피트"/></option>
		<option value="764.554857984"><spring:message code="portal.portlet.label.95" text="세제곱야드"/></option>
		<option value="3.785412"><spring:message code="portal.portlet.label.96" text="갤런(gal)"/></option>
		<option value="158.987"><spring:message code="portal.portlet.label.97" text="압배럴(bbl)력"/></option>
		<option value="0.001"><spring:message code="portal.portlet.label.98" text="씨씨(cc)"/></option>
    </select>
	<div class="clear mt5"></div>
    <span class="portlet-black-bold"><spring:message code="portal.portlet.label.41" text="변환값"/></span>
    <input type="text" class="input-d tovalue" readonly="readonly" style="width:30%;" />
    <select class="select-d tounits" style="width:30%;">
     	<option value="1"><spring:message code="portal.portlet.label.90" text="리터"/></option>
		<option value="0.001" selected="selected"><spring:message code="portal.portlet.label.91" text="밀리리터(ml)"/></option>
		<option value="1000"><spring:message code="portal.portlet.label.92" text="세제곱미터"/></option>
		<option value="0.016387064"><spring:message code="portal.portlet.label.93" text="세제곱인치"/></option>
		<option value="28.316846592"><spring:message code="portal.portlet.label.94" text="세제곱피트"/></option>
		<option value="764.554857984"><spring:message code="portal.portlet.label.95" text="세제곱야드"/></option>
		<option value="3.785412"><spring:message code="portal.portlet.label.96" text="갤런(gal)"/></option>
		<option value="158.987"><spring:message code="portal.portlet.label.97" text="배럴(bbl)"/></option>
		<option value="0.001"><spring:message code="portal.portlet.label.98" text="씨씨(cc)"/></option>
    </select>
    <span class="smain-btn" onclick="convert('volume');"><a><spring:message code="portal.portlet.label.42" text="변환하기"/></a></span>
</div>

<div id="presureContent" class="portlet-main mt5 clear" style="width:100%;">
    <span class="portlet-black-bold"><spring:message code="portal.portlet.label.40" text="입력값"/></span>
    <input type="text" value="1" class="input-d fromvalue" style="width:30%;" />
    <select class="select-d fromunits" style="width:30%;">
       	<option value="1"><spring:message code="portal.portlet.label.100" text="밀리바(mb)"/></option>
		<option value="1000"><spring:message code="portal.portlet.label.101" text="바(bar)"/></option>
		<option value="0.01"><spring:message code="portal.portlet.label.102" text="파스칼(Pa)"/></option>
		<option value="10000"><spring:message code="portal.portlet.label.103" text="메가파스칼(mPa)"/></option>
		<option value="10"><spring:message code="portal.portlet.label.104" text="킬로파스칼(kPa)"/></option>
		<option value="1"><spring:message code="portal.portlet.label.105" text="헥토파스칼(hPa)"/></option>
		<option value="68.94757"><spring:message code="portal.portlet.label.106" text="프사이(psi)"/></option>
		<option value="1013.25"><spring:message code="portal.portlet.label.107" text="기압(atm)"/></option>
    </select>
	<div class="clear mt5"></div>
    <span class="portlet-black-bold"><spring:message code="portal.portlet.label.41" text="변환값"/></span>
    <input type="text" class="input-d tovalue" readonly="readonly" style="width:30%;" />
    <select class="select-d tounits" style="width:30%;">
       	<option value="1"><spring:message code="portal.portlet.label.100" text="밀리바(mb)"/></option>
		<option value="1000" selected="selected"><spring:message code="portal.portlet.label.101" text="바(bar)"/></option>
		<option value="0.01"><spring:message code="portal.portlet.label.102" text="파스칼(Pa)"/></option>
		<option value="10000"><spring:message code="portal.portlet.label.103" text="메가파스칼(mPa)"/></option>
		<option value="10"><spring:message code="portal.portlet.label.104" text="킬로파스칼(kPa)"/></option>
		<option value="1"><spring:message code="portal.portlet.label.105" text="헥토파스칼(hPa)"/></option>
		<option value="68.94757"><spring:message code="portal.portlet.label.106" text="프사이(psi)"/></option>
		<option value="1013.25"><spring:message code="portal.portlet.label.107" text="기압(atm)"/></option>
    </select>
    <span class="smain-btn" onclick="convert('presure');"><a><spring:message code="portal.portlet.label.42" text="변환하기"/></a></span>
</div>
