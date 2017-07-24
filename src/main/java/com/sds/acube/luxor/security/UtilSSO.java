package com.sds.acube.luxor.security;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sds.acube.common.security.hash.HashEncrypt;
import com.sds.acube.esso.security.EnDecoder;
import com.sds.acube.esso.security.SecurityFactory;
import com.sds.acube.luxor.common.service.AccessControllService;
import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.util.UtilRequest;
import com.sds.acube.luxor.common.vo.SsoToken;
import com.sds.acube.luxor.common.vo.UserProfileVO;
import com.sds.acube.luxor.framework.config.LuxorConfig;


public class UtilSSO {
	public static String encodeSession(HttpServletRequest request) throws Exception {
		SecurityFactory fac = SecurityFactory.getInstance();
		String algorithm = LuxorConfig.getString("SSO.ENCODE_ALGORITHM");
		EnDecoder enDecoder = fac.getEnDecoder(algorithm);
		return enDecoder.encode(getSSOData(request));
	}


	public static String encodeSession(HttpServletRequest request, String encMethod) {
		String result = "";
		try {
			SecurityFactory fac = SecurityFactory.getInstance();
			EnDecoder enDecoder = fac.getEnDecoder(encMethod);
			result = enDecoder.encode(getSSOData(request));
		} catch (Exception e) {	}
		return result;
	}


	@SuppressWarnings("unchecked")
	public static String getSSOData(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(true);
		UserProfileVO userProfile = (UserProfileVO) session.getAttribute("userProfile");
		
		String accessIdString = "";
		List<String> accessIdList = (List<String>)session.getAttribute("ACCESS_ID_LIST");

		//Guest인 경우(비로그인사용자)
		if (userProfile == null) {
			userProfile = new UserProfileVO();
			userProfile.setLoginId(LuxorConfig.getString("GUEST.USER_ID"));
			userProfile.setUserName(LuxorConfig.getString("GUEST.USER_NAME"));
			userProfile.setCompId(LuxorConfig.getString("GUEST.PORTAL_ID"));
			userProfile.setUserUid(CommonUtil.generateId(LuxorConfig.getString("GUEST.USER_ID")));	
			accessIdString = LuxorConfig.getString("GUEST.GROUP_ID");
			session.setAttribute("userProfile", userProfile);	
			session.setAttribute("ACCESS_ID_LIST", Arrays.asList(accessIdString));
			
		}else{ //로그인사용자의 경우
			for(int i=0; i < accessIdList.size(); i++) {
				accessIdString += accessIdList.get(i) + ",";	
			}
			
			if(accessIdString.length() > 0) {
				accessIdString = accessIdString.substring(0, accessIdString.length()-1);
			}	
		}
		
		
		SecurityFactory fac = SecurityFactory.getInstance();
		String algorithm = LuxorConfig.getString("SSO.ENCODE_ALGORITHM");
		EnDecoder enDecoder = fac.getEnDecoder(algorithm);
		
		String passwordEncodingAlgorithm = LuxorConfig.getString("SSO.PASSWORD_ENCODE_ALGORITHM");
		String passwd = "";
		if(!passwordEncodingAlgorithm.equals("N")) {
			passwd = HashEncrypt.getHashEncryptData(enDecoder.decode(userProfile.getPassword()), passwordEncodingAlgorithm);
		} else {
			passwd = userProfile.getPassword();
		}
		
		String loginID = userProfile.getLoginId();
		String strName = userProfile.getUserName();
		String strOtherName = userProfile.getUserOtherName(); // @add 2013.11.18 - other language
		String strCompID = userProfile.getCompId();
		String strCompName = userProfile.getCompName();
		String strCompOtherName = userProfile.getCompOtherName(); // @add 2013.11.18 - other language
		String strCompTel = "";
		String strDeptID = userProfile.getDeptId();
		String strDeptName = userProfile.getDeptName();
		String strDeptOtherName = userProfile.getDeptOtherName(); // @add 2013.11.18 - other language
		String strGrdID = userProfile.getGradeCode();
		String strGrdName = userProfile.getGradeName();
		String strGrdOtherName = userProfile.getGradeOtherName(); // @add 2013.11.18 - other language
		String strSecID = CommonUtil.nullTrim(session.getAttribute("SECID")+"");
		String strSGLSvr = (String) session.getAttribute("SGLSVR");
		String strSGLOMAddr = (String) session.getAttribute("SYSMAIL");
		String strSabun = (String) session.getAttribute("SABUN");
		String strLang = (String) session.getAttribute("LANG");
		if (CommonUtil.isNullOrEmpty(strLang)) {//2013.04.09
			strLang = ((Locale) session.getAttribute("LANG_TYPE")).getLanguage();
		}
		String strOmWebIP = (String) session.getAttribute("OMWEBIP");
		String strMailServer = "";
		String strUserID = userProfile.getUserUid();
		String strSocialID = (String) session.getAttribute("SOCIALID");
		//String strPrivLst = (String) session.getAttribute("PRIVLST");
		String strEmail = (String) session.getAttribute("USEREMAIL");
		String strPortalWebLoc = "";
		String strPortalSvr = "";
		String strStartPage = "";
		//String strGrpLst = (String) session.getAttribute("GRPLST");
		String strSTimeZone = "";
		String strCTimeZone = (String) session.getAttribute("USERTIMEZONE");
		String strRoleCodes = (String) session.getAttribute("ROLECODES");
		String strDSTFlag = "";
		String strDateFormat = "";
		String strTimeFormat = "";
		String GmtFlag = "";
		String DayligntFlag = "";
		String strDN = "";
		String strJikCheck = "";
		String strAddJobCode = "";
		String strAddTel = "";
		String strAddDeptCode = (String) session.getAttribute("ADDITIONAL_DEPTCODE");
		String strAddDeptName = (String) session.getAttribute("ADDITIONAL_DEPTNAME");
		String strAddSecLevel = (String) session.getAttribute("ADDITIONAL_SECLEVEL");
		String strAddLogin = (String) session.getAttribute("ADDITIONAL_LOGIN");
		String strPisGrp = (String) session.getAttribute("PISGRP");
		String strPositionName = (String) session.getAttribute("POSITIONNAME");
		String strPositionID = (String) session.getAttribute("POSITIONID");
		String strIntranet = (String) session.getAttribute("INTRAFLAG");
		String selLang = "ko";
		String strDutyCode = (String) session.getAttribute("DUTYCODE");
		String strReserved1 = (String) session.getAttribute("RESERVED1");
		String strReserved2 = (String) session.getAttribute("RESERVED2");
		String strReserved3 = (String) session.getAttribute("RESERVED3");
		String tenantId = (String) session.getAttribute("TENANT_ID");
		String portalId = (String) session.getAttribute("PORTAL_ID");
		String relatedPortalId = portalId;
		String relatedPortalinfo = (String) session.getAttribute("RELATED_PORTAL_INFO");
		if("P".equals(relatedPortalinfo)){
			relatedPortalId = (String) session.getAttribute("PARENT_ID");
		}
		String vtime = "";
		StringBuilder buff = new StringBuilder();
		buff.append("F1=").append(loginID).append(";");
		buff.append("F2=").append(strName).append(";");
		buff.append("F3=").append(strCompID).append(";");
		buff.append("F4=").append(strCompName).append(";");
		buff.append("F5=").append(strCompTel).append(";");
		buff.append("F6=").append(strDeptID).append(";");
		buff.append("F7=").append(strDeptName).append(";");
		buff.append("F8=").append(strGrdID).append(";");
		buff.append("F9=").append(strGrdName).append(";");
		buff.append("F10=").append(strSecID).append(";");
		buff.append("F11=").append(strSGLSvr).append(";");
		buff.append("F12=").append(passwd).append(";");
		buff.append("F13=").append(strSGLOMAddr).append(";");
		buff.append("F14=").append(strSabun).append(";");
		buff.append("F15=").append(strLang).append(";");
		buff.append("F16=").append(strOmWebIP).append(";");
		buff.append("F17=").append(strMailServer).append(";");
		buff.append("F18=").append(strUserID).append(";");
		buff.append("F19=").append(strSocialID).append(";");
		buff.append("F20=").append(accessIdString).append(";");
		buff.append("F21=").append(strEmail).append(";");
		buff.append("F22=").append(strPortalSvr).append(";");
		buff.append("F23=").append(strPortalWebLoc).append(";");
		buff.append("F24=").append(strStartPage).append(";");
		//buff.append("F25=").append(accessIdString).append(";"); D1의 길이가 너무 길어져서 오류가 발생하는 케이스가 존재한다. 여기저기 살펴보니 accessIDs는 F20을 사용하고 있는 것 같아 F25의 데이터는 빼기로 했다.
		buff.append("F25=").append("").append(";");
		buff.append("F26=").append(strSTimeZone).append(";");
		buff.append("F27=").append(strCTimeZone).append(";");
		buff.append("F28=").append(strDateFormat).append(";");
		buff.append("F29=").append(strTimeFormat).append(";");
		buff.append("F30=").append(GmtFlag).append(";");
		buff.append("F31=").append(DayligntFlag).append(";");
		buff.append("F32=").append(strDN).append(";");
		buff.append("F33=").append(strDSTFlag).append(";");
		buff.append("F34=").append(strRoleCodes).append(";");
		buff.append("F35=").append("").append(";");
		buff.append("F36=").append(CommonUtil.nullTrim((String) session.getAttribute("SEL_EDITOR"))).append(";");
		buff.append("F37=").append(strJikCheck).append(";");
		buff.append("F38=").append(strAddJobCode).append(";");
		buff.append("F39=").append(strAddTel).append(";");
		buff.append("F40=").append(strAddDeptCode).append(";");
		buff.append("F41=").append(strAddDeptName).append(";");
		buff.append("F42=").append(strAddSecLevel).append(";");
		buff.append("F43=").append(strAddLogin).append(";");
		buff.append("F44=").append(strPisGrp).append(";");
		buff.append("F45=").append(strPositionName).append(";");
		buff.append("F46=").append(strPositionID).append(";");
		buff.append("F47=").append(strIntranet).append(";");
		buff.append("F48=").append(selLang).append(";");
		buff.append("F49=").append(";");
		buff.append("F50=").append(strDutyCode).append(";");
		buff.append("F51=").append(strReserved1).append(";");
		buff.append("F52=").append(strReserved2).append(";");
		buff.append("F53=").append(strReserved3).append(";");
		buff.append("F54=").append(vtime).append(";"); // Valid Time
		buff.append("F55=").append("").append(";"); // Valid Check Second
		buff.append("F56=").append(strOtherName).append(";"); // @add 2013.11.18 - other language
		buff.append("F57=").append(strCompOtherName).append(";"); // @add 2013.11.18 - other language
		buff.append("F58=").append(strDeptOtherName).append(";"); // @add 2013.11.18 - other language
		buff.append("F59=").append(strGrdOtherName).append(";"); // @add 2013.11.18 - other language
		buff.append("F101=").append(tenantId).append(";"); 
		buff.append("F102=").append(portalId).append(";"); 
		buff.append("F103=").append(relatedPortalId).append(";"); 
		
		return buff.toString();
	}
	
	/**
	 * SSO TOKEN 얻어오기
	 * 
	 * @since 2014.02.25
	 * @param request
	 * @param ssoData
	 * @return
	 * @throws Exception
	 */
	public static SsoToken getSsoToken(HttpServletRequest request, String ssoData) throws Exception {
		// HttpSession 객체 가져오기
		HttpSession session = request.getSession();
		// ServletContext 객체 가져오기
		ServletContext conext = session.getServletContext();
		// Spring Context 가져오기
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		
		AccessControllService accessControlService = (AccessControllService) wContext.getBean("accessControllService");
		
		int session_timeout = session.getMaxInactiveInterval() / 60;
		int timeOutMinute = (session_timeout == 0) ? LuxorConfig.getInt("Common", "SESSION.TIMEOUT") / 60 : session_timeout;
		
		return accessControlService.getSsoToken(ssoData, timeOutMinute);
	}
	
	/**
	 * SSO TOKEN 얻어오기
	 * 
	 * @since 2014.02.25
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static SsoToken getSsoToken(HttpServletRequest request) throws Exception {
		String ssoData = UtilRequest.getString(request, "D1");
		return getSsoToken(request, ssoData);
	}
}
