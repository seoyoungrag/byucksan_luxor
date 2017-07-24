package com.sds.acube.luxor.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sds.acube.luxor.common.ConstantList;

/**
 * 포탈에서 사용되는 공통 유틸 클래스
 */
public class CommonUtil {
	private static final String patter_now = "yyyyMMddHHmmss";
	private static final String patter_today = "yyyyMMdd";
	private static final long SEC = 1;
	private static final long MIN = SEC * 60;
	private static final long HOUR = MIN * 60;
	private static final long DAY = HOUR * 24;
	private static final long WEEK = DAY * 7;
	private static final long MONTH = DAY * 30;
	private static int iCount = (new Random()).nextInt();
	
    private synchronized static int getCount() {
        ++iCount;
        iCount = (iCount >= 10000 || iCount < 1000) ? 1000 : iCount;
        return iCount;
    }

    
    /**
     * Prefix 문자열 없는 고유 아이디 생성 (ex 20110120165752000)
     * @return
     */
	public static String generateId() {
		return generateId("");
	}

	
	/**
	 * Prefix 문자열을 가진 고유 아이디 생성 (ex P20110120165752000)
	 * @param prefix
	 * @return
	 */
	public static String generateId(String prefix) {
        String id = "";
        try {
            int count = getCount();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS", new Locale("ko","KO"));
            String formattedValue = formatter.format(new Date());
            id = prefix + formattedValue + count;
        } catch (Exception e) { 
        }
		return id;
	}

	
	/**
	 * Controller/Servlet/JSP에서 자바스크립트 실행
	 * @param response
	 * @param script 실행할 자바스크립트 (&lt;script&gt;&lt;/script&gt;는 제외하고 Javascript만)
	 */
	public static void scriptAlert(HttpServletResponse response, String script) {
		scriptAlert(response,script,"");
	}
	
	
	/**
	 * Controller/Servlet/JSP에서 자바스크립트 alert 메세지 보여주고 추가 스크립트 실행  
	 * @param response
	 * @param msg alert으로 보여줄 메세지
	 * @param script 추가 실행할 스크립트
	 */
	public static void scriptAlert(HttpServletResponse response, String msg, String script) {
		script(response,"alert(\""+msg+"\");"+script);
	}

	
	/**
	 * Controller/Servlet/JSP에서 자바스크립트 실행
	 * @param response
	 * @param script 실행할 자바스크립트 (&lt;script&gt;&lt;/script&gt;는 제외하고 Javascript만)
	 */
	public static void script(HttpServletResponse response, String script) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.write("<script type='text/javascript'>");
			out.write(script.replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
			out.write("</script>");
			out.flush();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Controller/Servlet/JSP에서 URL이동시 호출
	 * @param response
	 * @param url 이동할 URL주소
	 */
	public static void redirect(HttpServletResponse response, String url) {
		try {
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'>");
			out.println("document.location.href = '"+url+"';");
			out.println("</script>");
			out.flush();
		}
		catch(Exception e) {}
	}

	
	/**
	 * Controller/Servlet에서 out.println()이 필요한 경우 호출
	 * @param response
	 * @param str
	 */
	public static void println(HttpServletResponse response, String str) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.println(str);
			out.flush();
		}
		catch(Exception e) {}
	}
	
	
	/**
	 * 현재 날짜 + 시간을 구함
	 * @return
	 */
	public static String getNow() {
		SimpleDateFormat sdf = new SimpleDateFormat(patter_now, new Locale("ko")); 
		Date date = new Date();
		String now = sdf.format(date);
		return now;
	}

	
	/**
	 * 현재 날짜를 구함
	 * @return
	 */
	public static String getToday() {		
		SimpleDateFormat sdf = new SimpleDateFormat(patter_today, new Locale("ko")); 
		Date date = new Date();
		String today = sdf.format(date);
		return today;
	}

	
	/**
	 * n전일을 구함 (ex n이 3이면 3일전 날짜를 구하고 -3이면 3일후 날짜를 구함)
	 * @param n
	 * @return
	 */
	public static String getDayAgo(int n) {
		Date d = new Date();
		d.setTime(d.getTime() - DAY*1000*n);
		String yesterday = formatDate(d);
		return yesterday;
	}

	
	/**
	 * n전주를 구함 (ex n이 3이면 3주전 날짜를 구하고 -3이면 3주후 날짜를 구함)
	 * @param n
	 * @return
	 */
	public static String getWeekAgo(int n) {
		Date d = new Date();
		d.setTime(d.getTime() - WEEK*1000*n);
		String weekago = formatDate(d);
		return weekago;
	}

	
	/**
	 * n개월전을 구함 (ex n이 3이면 3개월전 날짜를 구하고 -3이면 3개월후 날짜를 구함)
	 * @param n
	 * @return
	 */
	public static String getMonthAgo(int n) {
		Date d = new Date();
		d.setTime(d.getTime() - MONTH*1000*n);
		String weekago = formatDate(d);
		return weekago;
	}

	
	/**
	 * 문자형식의 날짜(20110120165400)를 java.util.Date형으로 변환
	 * @param date
	 * @return
	 */
	public synchronized static Date toDate(String date) {
		return toDate(date, patter_now);
	}
	
	
	/**
	 * 특정 패턴의 문자형식의 날짜를 java.util.Date형으로 변환
	 * @param date
	 * @param pattern 날짜의 패턴
	 * @return
	 */
	public synchronized static Date toDate(String date, String pattern) {
		if(date==null || date.equals("")) {
			return new Date();
		}
		
 		Date retDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, new Locale("ko"));
		try {
			retDate = sdf.parse(date); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retDate;
	}
	
	
	/**
	 * java.util.Date를 문자형식의 날짜로 변환
	 * @param date 20110120165400 형식
	 * @return
	 */
	public static String formatDate(Date date) {
		return formatDate(date, patter_now);
	}
	
	
	/**
	 * java.util.Date를 특정 패턴의 문자형식의 날짜로 변환
	 * @param date
	 * @param pattern 변환할 패턴
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, new Locale("ko"));
		String strDate = "";
		try {
			TimeZone tz = TimeZone.getTimeZone("Asia/Seoul");
			sdf.setTimeZone(tz);
			strDate = sdf.format(date);			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return strDate;
	}
	
	
	/**
	 * 문자형식의 날짜(20110120165400)를 특정 패턴의 문자형식의 날짜로 변환
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(String date, String pattern) {
		Date tmpDate = toDate(date);
		return formatDate(tmpDate,pattern);
	}

	
	/**
	 * 문자열이 null인 경우 ""로 리턴하고 그 외의 경우에는 trim해서 리턴
	 * @param str
	 * @return
	 */
	public static String nullTrim(String str) {
		if(str==null || str.equals("null")) {
			return "";
		}
		return str.trim();
	}
	
	
	/**
	 * 문자열이 null이거나 빈문자열이면 true, 그렇지 않으면 false를 리턴
	 * @param s
	 * @return
	 */
	public static boolean isNullOrEmpty(String s) {
		return nullTrim(s).equals("");
	}

	
	/**
	 * 특정 URL의 컨텐츠(HTML/XML/Text)을 가져옴
	 * @param url 가져올 URL 문자열
	 * @return
	 * @throws Exception
	 */
	public static String getContent(String url) throws Exception {
        return getContent(new URL(url));
	}

	
	/**
	 * 특정 URL의 컨텐츠(HTML/XML/Text)을 가져옴
	 * @param url 가져올 URL
	 * @return
	 * @throws Exception
	 */
	public static String getContent(URL url) {
		return getContent(url,"");
	}
	
	
	/**
	 * 특정 URL의 컨텐츠(HTML/XML/Text)을 가져옴
	 * @param url 가져올 URL
	 * @param refer 해당 URL에서 referer 정보가 필요한 경우 HEADER의 REFERER에 셋팅해줌
	 * @return
	 */
	public static String getContent(URL url, String refer) {
		HashMap map = new HashMap();
		map.put("REFERER", refer);
		return getContent(url, map, false);
	}
	
	public static String getContent(URL url, HashMap map, boolean useCache) {
		int connectionTimeout = 10000;
		String userAgent = (String)map.get("USER_AGENT");
		String referer = (String)map.get("REFERER");
		String charset = (String)map.get("CHARSET");
		if(charset==null) {
			charset = "euc-kr";
		}

		String s = "";
		InputStream is = null;
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			URLConnection conn = url.openConnection();
			if(userAgent!=null) {
				conn.setRequestProperty("User-Agent", userAgent);
			}
			if(referer!=null) {
				conn.setRequestProperty("REFERER", referer);
			}
			
			if(map!=null) {
				for (Iterator iter = map.entrySet().iterator(); iter.hasNext();) {
				    Map.Entry entry = (Map.Entry) iter.next();
				    String key = (String)entry.getKey();
				    String value = (String)entry.getValue();
				    
				    conn.setRequestProperty(key, value);
				}
			}
			
			conn.setConnectTimeout(connectionTimeout);
			is = conn.getInputStream();

			br = new BufferedReader(new InputStreamReader(is,charset));
			while((s=br.readLine())!=null) {
				sb.append(s+"\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(is!=null) is.close();
				if(br!=null) br.close();
			} catch(Exception e) {}
		}
		
		return sb.toString();
	}
	
	
	/**
	 * 포탈에서 사용하는 세션정보 초기화
	 * @param session
	 * @throws Exception
	 */
	public static void sessionInit(HttpSession session) throws Exception {
		session.removeAttribute("TENANT_ID");
		session.removeAttribute("PORTAL_ID");
		session.removeAttribute("LANG_TYPE");
		session.removeAttribute("userProfile");
		session.removeAttribute("adminProfile");
	}
	
	
	/**
	 * 특수문자 처리
	 * @param str
	 * @return
	 */
	public static String escapeSpecialChar(String str) {
		if(str==null) return "";
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
    	str = str.replaceAll("'","\\\'");
		return str;
	}

	
	/**
	 * Escaped된 특수문자 Decode
	 * @param str
	 * @return
	 */
	public static String decodeEscapedSpecialChar(String str) {
		if(str==null) return "";
		str = str.replaceAll("&amp;", "&");
		str = str.replaceAll("&lt;", "<");
		str = str.replaceAll("&gt;", ">");
		str = str.replaceAll("&quot;", "\"");
    	str = str.replaceAll("\\\'", "'");
		return str;
	}
	
	
	/**
	 * 줄바꿈 Character을 HTML 줄바꿈 &lt;br /&gt;로 변경
	 * @param str
	 * @return
	 */
	public static String nl2br(String str) {
		if(str==null) return null;
		str = str.replaceAll("\r\n", "<br />");
		str = str.replaceAll("\n", "<br />");
		str = str.replaceAll("\r", "<br />");
		return str;
	}

	
	/**
	 * 문자열에 포함된 URL에 자동으로 링크를 걸어줌
	 * @param str
	 * @return
	 */
	public static String url2Link(String str) {
		String regex = "(https?://[^< \n\r\r\n]+)";
		return str.replaceAll(regex, "<a href='$1' target='_blank'>$1</a>");
	}
	
	
	/**
	 * 파일의 확장자를 구함
	 * @param file
	 * @return
	 */
	public static String getExt(String file) {
		return file.substring(file.lastIndexOf(".")+1).toLowerCase();
	}
	
	
	/**
	 * 문자열을 원하는 길이만큼 잘라줌
	 * @param str
	 * @param length
	 * @return
	 */
	public static String cutString(String str, int length) {
		if(str==null) {
			return "";
		}
		String token = "";
		if(length >= str.length()) {
			length = str.length();			
		} else {
			token = "..";
		}
		return str.substring(0,length)+token;
	}

	
	/**
	 * 바이트기반의 파일크기를 KB, MB, GB, TB로 변경 
	 * @param bytes
	 * @return
	 */
    public static String getFileSize(long bytes) {
        if (bytes >= (1099511627776L)) {
            float size = ((float)bytes / (float)(1099511627776L));
            return String.format("%.2f %s", size, "TB");
        } else if (bytes >= (1024 * 1024 * 1024)) {
            float size = ((float)bytes / (float)(1024 * 1024 * 1024));
            return String.format("%.2f %s", size, "GB");
        } else if (bytes >= (1024 * 1024)) {
            float size = ((float)bytes / (float)(1024 * 1024));
            return String.format("%.2f %s", size, "MB");
        } else if (bytes >= 1024) {
            float size = ((float)bytes / (float)1024);
            return String.format("%.0f %s", size, "KB");
        } else if (bytes > 0 & bytes < 1024) {
            float size = bytes;
            return String.format("%.0f %s", size, "B");
        } else {
            return "0 " + " B";
        }
    }

    
    /**
     * 특정 URL을 호출해줌
     * @param url
     * @return
     */
	public static String callURL(String url) {
		return callURL(url, null);
	}
	
	
	/**
	 * 특정 URL을 호출해줌
	 * @param url
	 * @param param
	 * @return
	 */
	public static String callURL(String url, String param) {
		return callURL(url, param, null, null);
	}

	
	/**
	 * 특정 URL을 호출해줌
	 * @param url
	 * @param param
	 * @param charset
	 * @return
	 */
	public static String callURL(String url, String param, String charset) {
		return callURL(url, param, null, charset);
	}
	
	
	/**
	 * 특정 URL을 호출해줌
	 * @param url
	 * @param param
	 * @param map
	 * @param charset
	 * @return
	 */
	public static String callURL(String url, String param, HashMap map, String charset) {
		String result = "";
		try {
			URL targetURL = new URL(url);
		    URLConnection urlcon = targetURL.openConnection();
		    urlcon.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; ko; rv:1.9.2.8) Gecko/20100722 Firefox/3.6.8");
			urlcon.setRequestProperty("REFERER", "http://sds.samsung.com");
			urlcon.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			urlcon.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
			urlcon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset="+charset);

			if(map!=null) {
				for (Iterator iter = map.entrySet().iterator(); iter.hasNext();) {
				    Map.Entry entry = (Map.Entry) iter.next();
				    String key = (String)entry.getKey();
				    String value = (String)entry.getValue();
				    urlcon.setRequestProperty(key, value);
				}
			}
			
			if(charset==null) {
				charset = "euc-kr";
			}
			
		    HttpURLConnection hurlc = (HttpURLConnection)urlcon;
		    
		    hurlc.setRequestMethod("GET");
		    hurlc.setDoOutput(true);
		    hurlc.setDoInput(true);
		    hurlc.setUseCaches(false);
		    hurlc.setDefaultUseCaches(false);
		
		    if(param!=null) {
			    PrintWriter out = new PrintWriter(hurlc.getOutputStream());
			    out.print(param);
			    out.close();
		    }
		    
	        BufferedReader br = new BufferedReader(new InputStreamReader(hurlc.getInputStream(), charset));
	        
	        String temp = "";
	        String strData = "";
	        while ((temp = br.readLine()) != null) {
	        	strData += temp;
	        }
	        
	        result = strData.trim();
		} catch(Exception e) {
			result = "Fail!!";
			e.printStackTrace();
		}
		return result;
	}
	
    /**
     * 문자열에서 변경하고자 하는 문자를 변환시켜 return 한다. (바꾸고자하는 기존의 문자의 길이가 2 이상일때)
     * 
     * @param text 문자열
     * @param repl 찾고자 하는 문자열
     * @param with 바꾸고자 하는 문자열
     * @return 변환된 문자열
     */
    public static String replacestr(String text, String repl, String with)
    {
        if (text == null || repl == null || with == null)
        {
            return text;
        }

        StringBuffer buf = new StringBuffer(text.length());
        int start = 0, end = 0;
        while ((end = text.indexOf(repl, start)) != -1)
        {
            buf.append(text.substring(start, end)).append(with);
            start = end + repl.length();
        }
        buf.append(text.substring(start));
        return buf.toString();
    }		

	/**
	 * 와일드카드 문자 ESCAPE 처리
	 * @param obj(실제로는 String 이나 Object로 넘겨주는 부분있어 Object argument 사용)
	 * @param dbType
	 * @return
	 */
	public static String getEscapeString(Object obj, int dbType) {
		String str = "";
		if (obj instanceof String) {
			str = (String) obj;
		} else {
			str = obj.toString();
		}
		if(!org.springframework.util.StringUtils.hasText(str)) {
			return str;
		}
		switch(dbType) {
		case ConstantList.ORA:
		case ConstantList.DB2:
		case ConstantList.MYSQL:
		case ConstantList.ALTIBASE:
			str = org.springframework.util.StringUtils.replace(str, "_", "__");
			str = org.springframework.util.StringUtils.replace(str, "%", "_%");
			break;
		case ConstantList.MS:
		case ConstantList.SYBASE:
			str = org.springframework.util.StringUtils.replace(str, "_", "__");
			str = org.springframework.util.StringUtils.replace(str, "%", "_%");
			str = org.springframework.util.StringUtils.replace(str, "[", "_[");
			str = org.springframework.util.StringUtils.replace(str, "]", "_]");
			str = org.springframework.util.StringUtils.replace(str, "^", "_^");
			break;
		}
		return str;
	}

	/**
	 * 와일드카드 문자 ESCAPE 처리 원복(화면에 ESCAPE된 결과 출력 방지)
	 * @param str
	 * @return
	 */
	public static String getResetEscapeString(String str) {
		if(!org.springframework.util.StringUtils.hasText(str)) {
			return str;
		}
		str = org.springframework.util.StringUtils.replace(str, "__", "_");
		str = org.springframework.util.StringUtils.replace(str, "_%", "%");
		str = org.springframework.util.StringUtils.replace(str, "_[", "[");
		str = org.springframework.util.StringUtils.replace(str, "_]", "]");
		str = org.springframework.util.StringUtils.replace(str, "_^", "^");
		return str;
	}
}
