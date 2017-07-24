<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>

<%
	String themeImageUrl = UtilRequest.getString(request, "themeImageUrl");
	if(themeImageUrl.equals("") || themeImageUrl == null) {
		themeImageUrl = "/ep/luxor/ref/image/default_theme";
	}
%>

<li class="content-wrap" style="min-height:20px;">
	<div class="round-top">
	<div class="round-right">
	<div class="round-bottom">
	<div class="round-left">
	<div class="round-top-left">
	<div class="round-top-right">
	<div class="round-bottom-left">
	<div class="round-bottom-right">
		<div class="portlet-wrap">
			<div class="content-top content-top-div">
				<div class="content-title-bar" contentId=""></div>
				<div class="content-menu-bar">
					<ul class="content-menu-list">
						<li mode="max" class="menu-btn-max"><a href='#'><img src="<%=themeImageUrl%>/portlet/max.gif" alt="Max" title="Max"/></a></li>
						<li mode="min" class="menu-btn-min"><a href='#'><img src="<%=themeImageUrl%>/portlet/min.gif" alt="Min" title="Min"/></a></li>
						<li mode="normal" class="menu-btn-normal"><a href='#'><img src="<%=themeImageUrl%>/portlet/normal.gif" alt="Normal" title="Normal"/></a></li>
						<li mode="help" class="menu-btn-help"><a href='#'><img src="<%=themeImageUrl%>/portlet/help.gif" alt="Help" title="Help"/></a></li>
						<li mode="edit" class="menu-btn-edit"><a href='#'><img src="<%=themeImageUrl%>/portlet/edit.gif" alt="Edit" title="Edit"/></a></li>
						<li mode="go" class="menu-btn-go"><a href='#'><img src="<%=themeImageUrl%>/portlet/go.gif" alt="Go" title="Go"/></a></li>
						<li mode="reload" class="menu-btn-reload"><a href='#'><img src="<%=themeImageUrl%>/portlet/reload.gif" alt="Reload" title="Reload"/></a></li>
						<li mode="setup" class="menu-btn-setup"><a href='#'><img src="<%=themeImageUrl%>/portlet/setup.gif" alt="Setup" title="Setup"/></a></li>
						<li mode="del" class="menu-btn-delete"><a href='#'><img src="<%=themeImageUrl%>/portlet/delete.gif" alt="Delete" title="Delete"/></a></li>
					</ul>
				</div>
			</div>
			<div class="content-body"></div>
		</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
</li>
