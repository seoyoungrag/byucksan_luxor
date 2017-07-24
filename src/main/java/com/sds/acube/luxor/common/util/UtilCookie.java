package com.sds.acube.luxor.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UtilCookie {
	public static String getValue(HttpServletRequest request, String name) {
		String retValue = "";		
		Cookie[] cookie = request.getCookies();		
		if(cookie==null) {
			return null;
		}		
		for(int i=0; i < cookie.length; i++) {
			if(cookie[i].getName().equals(name)) {
				retValue = cookie[i].getValue();
				break;
			}
		}
		return retValue;
	}


	public static void setValue(HttpServletRequest request, HttpServletResponse response, String name, String value) {
		setValue(request, response, name, value, 365);
	}

	
	public static void setValue(HttpServletRequest request, HttpServletResponse response, String name, String value, int maxAge) {
		boolean isExist = false;
		Cookie[] cookie = request.getCookies();
		
		// 같은 쿠키가 있는지 체크해서 있는 경우에는 값만 바꿔줌
		if(cookie!=null) {
			for(int i=0; i < cookie.length; i++) {
				if(cookie[i].getName().equals(name)) {
					cookie[i].setValue(value);
					response.addCookie(cookie[i]);
					isExist = true;
				}
			}
		}
		// 같은 쿠키가 없는 경우에는 새로 생성
		if(cookie==null || !isExist) {
			Cookie newcookie = new Cookie(name, value);
			newcookie.setPath("/");
			newcookie.setMaxAge(maxAge);
			response.addCookie(newcookie);
		}
	}
	
	
	public static void delete(HttpServletRequest request, HttpServletResponse response, String name) {
		Cookie[] cookie =request.getCookies();
		if(cookie!=null) {   
			for(int i=0; i < cookie.length; i++) {
				if(cookie[i].getName().equals(name)) {
					cookie[i].setMaxAge(0);
					cookie[i].setPath("/");
					response.addCookie(cookie[i]);
				}
			}
		}
	}
	
}
