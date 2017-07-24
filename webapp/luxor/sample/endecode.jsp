<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.security.*" %>
<%@ page import="com.sds.acube.luxor.security.seed.*" %>

<%
	/* DES(64비트) - ACUBE 내부 암복호화할때 사용 */
	String s1 = "2041";
	String encodedS1 = EnDecode.EncodeBySType(s1);
	String decodedS1 = EnDecode.DecodeBySType(encodedS1);
	
	out.println("## MRJH DEBUG ##  s1 : "+s1+"<br />");
	out.println("## MRJH DEBUG ##  encodedS1 : "+encodedS1+"<br />");
	out.println("## MRJH DEBUG ##  decodedS1 : "+decodedS1+"<br />");
	out.println("<br /><br />");
	
	/* SEED(128비트) - 외부 연계시 사용 RoundKey없이 사용하는 예제 */
	String s2 = "abcde123";
	
	String encodedS2 = SeedBean.doEncrypt(s2);
	String decodedS2 = SeedBean.doDecode(encodedS2);

	out.println("## MRJH DEBUG ##  s2 : "+s2+"<br />");
	out.println("## MRJH DEBUG ##  encodedS2 : "+encodedS2+"<br />");
	out.println("## MRJH DEBUG ##  decodedS2 : "+decodedS2+"<br />");
	out.println("<br /><br />");
	
	/* 
	 * SEED - RoundKey가 필요한 경우 
	 * 로그인시 SEED 플러그인에서 암호화해서 넘길경우 RoundKey(개인키)를 같이 넘겨줌 
	 * RoundKey가 따로 넘어올때는 아래와 같이 복호화해줘야 함
	 * 위의 RoundKey없이 복호화방법으로는 복호화 안됨
	 */	 
	//SeedBean.setRoundKey(request);
	//String decodedS2 = SeedBean.doDecode(request, encodedS2);
	
%>