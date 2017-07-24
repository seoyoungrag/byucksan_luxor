package com.sds.acube.luxor.framework.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.sds.acube.luxor.common.util.UtilRequest;
import com.sds.acube.luxor.common.vo.CommonVO;
import com.sds.acube.luxor.framework.config.LuxorConfig;

/**
 * JSP에서 callJson/postJson을 이용한 모든 Ajax호출은 이 Controller를 거치게 됨
 * 이 Controller에서 각 Target Controller를 호출해주고 결과값을 JSON으로 맵핑해줌
 */
public class JsonController extends BaseController {
	private final ObjectMapper mapper = new ObjectMapper();
	private Log logger = LogFactory.getLog(this.getClass().getName());
	private boolean isDebug = "Y".equals(LuxorConfig.getString("BASIC.DEBUG"));
	private boolean isInstalled = "Y".equals(LuxorConfig.getString("BASIC.INSTALLED"));

	protected ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mnv = null;
		
		try {
			long _start = System.currentTimeMillis();
			
			String mvcBeanId = UtilRequest.getString(request,"mvcBeanId");
			String methodName = UtilRequest.getString(request,"methodName");
			ApplicationContext context = getWebApplicationContext();
			Object mvcBean = context.getBean(mvcBeanId);
			mnv = (ModelAndView) mvcBean.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class).invoke(mvcBean, request, response);
			
			// 관리자 체크
			Boolean checkAdmin = (Boolean)mnv.getModelMap().get("ADMIN_CHECK");
			if(isInstalled && checkAdmin!=null && checkAdmin==true) {
				logger.info("Check Admin Permission from ["+mvcBeanId+"."+methodName+"]");
				
				List accessList = (List)WebUtils.getSessionAttribute(request, "ACCESS_ID_LIST");
				if(accessList.indexOf("ADMIN") == -1) {
					throw new Exception("Invalid Access - Need admin permission!!");
				}
			}
			
			Object jsonResult = mnv.getModelMap().get("jsonResult");
			if(jsonResult instanceof CommonVO) {
				((CommonVO)jsonResult).setRequest(new Object());
			} 
			mapper.writeValue(response.getOutputStream(), jsonResult);
			
			long _end = System.currentTimeMillis();
			logger.info("Call "+mvcBeanId+"."+methodName+" ["+(_end-_start)+"ms]");
			
		} catch (Exception e) {
			if(logger.isDebugEnabled() || isDebug) {
				e.printStackTrace();
			}

			mnv = new ModelAndView();
			logger.error("Exception[" + e.toString() + "]");
			mnv.addObject("_errorCode", "-9999");
			mnv.addObject("_errorMsg", "Unexpected Error. (" + e.getMessage() + ")");
			mapper.writeValue(response.getOutputStream(), mnv.getModelMap());
		} finally {
		}
		return null;
	}
	
}
