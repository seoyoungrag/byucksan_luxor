<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.ws.client.aclservice.*" %>
<%@ page import="java.util.*" %>
<%
	
	List<GroupInfo> groupList = (List)request.getAttribute("virtualGroupList");
	String compId = "";

	UserProfileVO userProfile = (UserProfileVO)session.getAttribute("userProfile");
	
	if(userProfile == null) {
		out.println("<script type=\"text/javascript\">alert('조회 권한이 없습니다.');window.close();</script>");
		return;
	}else{
		compId = userProfile.getCompId();
	}

	int listSize = 0;

	if(groupList==null){
		listSize = 0;
	}else{
		listSize = groupList.size();
	}
	
%>


<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript">
	function addGroupToRight(){
	    parent.addAccessID();
	}
</script>

		<select name="virtual_group_list" id="virtual_group_list" class="w99percent" style="height:420px;" onDblClick="addGroupToRight();" multiple>
<%  		for (int i=0; i < listSize; i++) { %>
            	<option value="<%= compId + "," + groupList.get(i).getGroupId() %>"><%= groupList.get(i).getGroupName() %></option>
<%  		} %>
		</select>
	
	
        
        