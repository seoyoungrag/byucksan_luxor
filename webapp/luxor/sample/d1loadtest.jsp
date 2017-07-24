<%@ page import="com.sds.acube.luxor.security.*" %>
<%@ page import="com.sds.acube.luxor.framework.config.*" %>
<%@ page import="com.sds.acube.esso.security.SecurityFactory" %>
<%@ page import="com.sds.acube.esso.security.EnDecoder" %>

<%
	//UtilSSO.encodeSession(request);
	SecurityFactory fac = SecurityFactory.getInstance();
	String algorithm = LuxorConfig.getString("SSO.ENCODE_ALGORITHM");
	algorithm = "RSA";
	EnDecoder enDecoder = fac.getEnDecoder(algorithm);
	String ssss = UtilSSO.getSSOData(request);
	
	long _start = System.currentTimeMillis();
	for(int i=0; i < 10000; i++) {
		String asdf = enDecoder.encode(ssss);
	}
	long _end = System.currentTimeMillis();
	//System.out.println("ellapsed time 1 : "+(_end-_start)+"ms");
%>
