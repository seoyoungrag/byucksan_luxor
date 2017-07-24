<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://portlet-container.dev.java.net" prefix="pcdriver" %>

<%
	String portletContext = (String)request.getAttribute("portletContext");
	String applicationName = portletContext.split("\\.")[0];
	String portletName = portletContext.split("\\.")[1];
%>

<pcdriver:portlet portletName="<%=portletName%>" applicationName="<%=applicationName%>" >
    <pcdriver:render/>
</pcdriver:portlet>

<%--
<pcdriver:portlet portletName="Catalog" applicationName="shoppingcart" >
	<h2 class="portlet-title"><pcdriver:title/></h2>
    <pcdriver:render/>
</pcdriver:portlet>

<pcdriver:portlet portletName="Cart" applicationName="shoppingcart" >
	<h2 class="portlet-title"><pcdriver:title/></h2>
    <pcdriver:render/>
</pcdriver:portlet>

<pcdriver:portlet portletName="<%=portletName%>" applicationName="samples-jsp-portlet" >
	<h2 class="portlet-title"><pcdriver:title/></h2>
    <pcdriver:render/>
</pcdriver:portlet>
--%>
