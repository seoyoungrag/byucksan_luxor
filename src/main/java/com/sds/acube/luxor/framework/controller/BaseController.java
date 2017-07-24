package com.sds.acube.luxor.framework.controller;

import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.util.WebUtils;
import org.anyframe.spring.controller.MultiActionSimpleFormController;
import com.sds.acube.luxor.common.vo.CommonVO;
import com.sds.acube.luxor.common.vo.UserProfileVO;

/**
 * 모든 Controller가 상속받는 기본 Controller임
 */
public class BaseController extends MultiActionSimpleFormController {
	protected Log logger = LogFactory.getLog(BaseController.class);
	protected String tenantId;
	protected String portalId;
	
	public BaseController() {}

	/**
	 * 세션에 있는 값을 VO로 맵핑하고 원래 spring의 bind 호출함
	 * 
	 * @param request
	 * @param obj
	 * @throws Exception
	 */
	@Override
	protected void bind(HttpServletRequest request, Object obj) throws Exception {
		HttpSession session = request.getSession();
		
		Enumeration enumSess = session.getAttributeNames();
		while(enumSess.hasMoreElements()) {
	    	String sessName = enumSess.nextElement().toString();
	    	
	    	try {
	    		Object sessValue;
	    		
	    		if("LANG_TYPE".equals(sessName)) {
	    			Locale locale = (Locale)session.getAttribute(sessName);
	    			sessValue = (String)locale.getLanguage();
	    		}
	    		else if("userProfile".equals(sessName)) {
	    			UserProfileVO userProfile = (UserProfileVO)session.getAttribute(sessName);
	    			sessValue = (String)userProfile.getUserUid();
	    			sessName = "reg_user_id";
	    		}
	    		else {
	    			sessValue = session.getAttribute(sessName);
	    		}
	    		
	    		sessName = convert2JavaConventionName("set_"+sessName.toLowerCase());
	    		
	    		if(sessValue instanceof String) {
		    		Object[] param = new Object[]{sessValue};
		    		Method method = obj.getClass().getMethod(sessName, String.class);
		    		method.invoke(obj, param);
	    		} else if(sessValue instanceof List) {
		    		Object[] param = new Object[]{sessValue};
		    		Method method = obj.getClass().getMethod(sessName, List.class);
		    		method.invoke(obj, param);
	    		} else {
	    		}
	    		
	    		if(obj instanceof CommonVO) {
	    			((CommonVO) obj).setRequest(request);
	    		}
	    	} catch(Exception e) {
	    	}
		}
		
		super.bind(request, obj);
	}
	
	
	/**
	 * 컨트롤러에서 사용자 로그인 여부를 체크해야하는 경우 호출
	 * 주로 callJson에 의해 호출되는 메소드에서 로그인 여부를 체크할때 호출 해주면 됨
	 */
	protected void loginCheck(HttpServletRequest request) throws Exception {
		UserProfileVO userProfile = (UserProfileVO)request.getSession().getAttribute("userProfile");
		if(userProfile==null) {
			throw new Exception("Invalid Access - Need to login!!");
		}
	}
	
	
	/**
	 * 컨트롤러에서 관리자여부 체크해야하는 경우 호출
	 */
	protected void adminCheck(HttpServletRequest request) throws Exception {
		List accessList = (List)WebUtils.getSessionAttribute(request, "ACCESS_ID_LIST");
		if(accessList==null || accessList.indexOf("ADMIN") == -1) {
			throw new Exception("Invalid Access - Need admin permission!!");
		}
	}
	
	
	/**
	 * 언더바로 이루어진 문자열을 자바 스타일의 문자열로 변경
	 * user_id --> userId
	 * set_user_name --> setUserName
	 * 
	 * @param name
	 * @return
	 */
	private String convert2JavaConventionName(String name) {
		if(name.indexOf("_") == -1) {
			return name;
		}
		String a = name.substring(0, name.indexOf("_"));
		String b = name.substring(name.indexOf("_")+1,name.length());
		String c = b.substring(0,1).toUpperCase() + b.substring(1);
		return convert2JavaConventionName(a+c);
	}
	
}
