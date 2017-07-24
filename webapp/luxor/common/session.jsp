<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="java.util.*" %>

<html lang="ko">
<head><title>EP Session</title></head>
<body style='margin:0;overflow:auto;'>

<%
	int ssCount = 0;

	out.println("luxor session values<BR><BR>");

	String sessitem="",appItem="";
	out.print("SessionId: " + session.getId() + "<P>");
	String strSesList[]=session.getValueNames();

	out.print("List of " + strSesList.length + " items in Session   contents collection:<HR>");

	for(Enumeration enum_ses=session.getAttributeNames();enum_ses.hasMoreElements();)
	{
    	sessitem = enum_ses.nextElement().toString();
     	if (!sessitem.equals ("PASSWORD") )
     	{
    		out.print(sessitem + " : " + session.getAttribute(sessitem).toString()+"<BR>");
    	}
    	ssCount = ssCount + 1;
	}

%>

</body>
</html>