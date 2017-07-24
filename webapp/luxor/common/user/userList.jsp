<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="org.anyframe.pagination.Page" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.ws.client.orgservice.*" %>

<%
	String selectType = CommonUtil.nullTrim(UtilRequest.getString(request, "selectType"));	
	String isAcl = CommonUtil.nullTrim(UtilRequest.getString(request, "isAcl"));	
	
	boolean isRadio = false;
	boolean displayCheckbox = true;
	
	if (UtilRequest.getString(request, "displayCheckbox") != null && UtilRequest.getString(request, "displayCheckbox").toString().equals("true")) {
		displayCheckbox = true;
		if (UtilRequest.getString(request, "selectType") != null && UtilRequest.getString(request, "selectType").toString().equals("single")) {
			isRadio = true;
		}else{
			isRadio = false;
		}
	}else{
		displayCheckbox = false;
	}
	List list = null;
	int listSize = 0;
	int cPage = 0;
	int totalCount = 0;
	
	Page _page = (Page)request.getAttribute("page");
	if(_page==null){
		listSize=0;
	}else{
		list = (List)_page.getList();	
		listSize = list.size();
		cPage = _page.getCurrentPage();
		totalCount = _page.getTotalCount();
	}

	
%>

<script type="text/javascript">
	
	$(document).ready(function() {
		var isAcl = '<%=isAcl%>';
		var listSize = '<%=listSize%>';
		if(isAcl == 'Y'){					// 권한설정 페이지일 경우 칼럼설정(ID/성명/직급)
			 $("#compNameTt").hide();
			 $("#deptNameTt").hide();
			 $("#officeTelTt").hide();
			 for(var i=0;i<listSize;i++){
				 var compNameTd = i+'compNameTd';
				 var deptNameTd = i+'deptNameTd';
				 var officeTelTd = i+'officeTelTd';
				 $("#"+compNameTd).hide();
				 $("#"+deptNameTd).hide();
				 $("#"+officeTelTd).hide();
			 }
		}
		if(listSize>10 && isAcl != 'Y'){
			$("#scroll_wrap").addClass("admin-user-list-wrap");
		}else{
			$("#scroll_wrap").removeClass("admin-user-list-wrap");
		}
	});
</script>
<div id="scroll_wrap">
<table class="table-body02" id="userList_table">
	<thead>
	<tr trId='chkAll'>
<%
		if( displayCheckbox ){
			if( isRadio ){
%>
				<th class="w30"></th>
<%			}else{
%>				<th class="w30"><input type='checkbox' id='chkAll' name='chkAll'></th>
<%			}
		}
%>		<th id="thUserId"><spring:message code="user.label.9" text="ID" /></th>
		<th><spring:message code="user.label.8" text="성명" /></th>
		<th id="compNameTt"><spring:message code="user.label.10" text="회사" /></th>
		<th id="deptNameTt"><spring:message code="user.label.11" text="부서" /></th>
		<th id="gradeTt"><spring:message code="user.label.12" text="직급" /></th>
		<th id="officeTelTt"><spring:message code="user.label.16" text="회사전화" /></th>
		<th id="emailTt" style="display: none;"><spring:message code="user.label.18" text="email" /></th>
		<th id="mobileTt" style="display: none;"><spring:message code="user.label.17" text="휴대폰" /></th>
		<th id="officeAddrTt" style="display: none;"><spring:message code="user.label.19" text="회사주소" /></th>
		<th id="officeFaxTt" style="display: none;"><spring:message code="user.label.20" text="회사FAX" /></th>
	</tr>
	</thead>
<%
		String chkName = "";
		String chkId = "";
		String chkValue = "";
		String tdId = "";
		int i = 0;
		if(listSize>0){
			IUser[] users = new IUser[list.size()];
			list.toArray(users);	
			for(IUser vo : users) {
				int count = 0;
				chkName = "indexChk";
				chkId = "indexChk" + i;
				chkValue = vo.getUserID() + "^" + vo.getUserName() + "^" + vo.getCompName() + "^" + vo.getDeptName() + "^" + vo.getGradeName() + "^" + vo.getOfficeTel() + "^" + vo.getUserUID();
%>
  		<tbody>
		<tr trId='<%=chkId%>'>
<%
			if( displayCheckbox ){
				if( isRadio ){
%>					<td class="w30"><input type='radio' name='<%= chkName%>' id='<%= chkId%>' value='<%=chkValue%>' checked="checked" class='box' /></td>
<%				}else{
%>					<td class="w30"><input type='checkbox' name='<%= chkName%>' id='<%= chkId%>' value='<%=chkValue%>' class='box' /></td>
<%				}
			}
%>	
			<td id="<%=i%>tdUserId"  style="cursor:pointer;"><%=vo.getUserID()%></td>
			<td class="tblink">
				<a href="#" id="<%=vo.getUserID()%>">
					<%=vo.getUserName()%>
				</a>
			</td>
			<td id="<%=i%>compNameTd"><%=vo.getCompName()%></td>
			<td id="<%=i%>deptNameTd"><%=vo.getDeptName()%></td>
			<td id="<%=i%>gradeTd"><%=vo.getGradeName()%></td>
			<td id="<%=i%>officeTelTd"><%=vo.getOfficeTel()%></td>
			
			<td id="<%=i%>emailTd" style="display: none;"><%=vo.getEmail()%></td>
			<td id="<%=i%>mobileTd" style="display: none;"><%=vo.getMobile()%></td>
			<td id="<%=i%>officeAddrTd" style="display: none;"><%=vo.getOfficeAddr()%></td>
			<td id="<%=i%>officeFaxTd" style="display: none;"><%=vo.getOfficeFax()%></td>
		</tr>
<%				i++;
			}
		}else{
%>				
			<tr>
				<td colspan="7">
					<spring:message code="portal.alert.msg.19" text="데이터가 없습니다."/>
				</td>
			</tr>
<%		}
%>		
</tbody>
</table>
</div>

<%		if(! isAcl.equals("Y")){
%>			<!-- 페이징 -->
			<jsp:include page="/luxor/common/paging.jsp">
				<jsp:param name="cPage" value="<%=cPage%>" />
				<jsp:param name="totalCount" value="<%=totalCount%>" />
			</jsp:include>
			<!-- //페이징 -->
<%		}
%>

<form id="MainForm" name="MainForm" target="" method="post">
</form>

<input type="hidden" name="userListSize" id="userListSize" value="<%= listSize%>" />
<input type="hidden" name="cPage" id="cPage" value=<%= cPage%> />
