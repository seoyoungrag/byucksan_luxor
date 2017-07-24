package com.sds.acube.luxor.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import com.sds.acube.luxor.common.service.AccessControllService;
import com.sds.acube.luxor.common.util.UtilRequest;
import com.sds.acube.luxor.framework.controller.BaseController;
import com.sds.acube.luxor.ws.client.aclservice.AccessList;


public class AccessControlController extends BaseController {
	private AccessControllService accessControllService;
	private String aclSetupUser;
	private String aclView;

	public void setAccessControllService(AccessControllService accessControllService) {
		this.accessControllService = accessControllService;
	}

	public void setAclSetupUser(String aclSetupUser) {
		this.aclSetupUser = aclSetupUser;
	}

	public void setAclView(String aclView) {
		this.aclView = aclView;
	}
	
	/**
	 * 권한설정 화면 호출
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView aclSetupUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String resourceId = UtilRequest.getString(request, "resourceID");
		String callback = UtilRequest.getString(request, "callback");
		
		AccessList accessList = new AccessList(); 
			
		// 리소스에 대한 Access 정보
		String accessInfo = accessControllService.getAccess(resourceId);
		
		if(accessInfo != null) {
			// 액세스 정보 문자를 AccessList 형태로 변환한다 
			accessList = accessControllService.makeAccessList(accessInfo);
		}
		
		ModelAndView mav = new ModelAndView(aclSetupUser);
		mav.addObject("accessInfos", accessList);
		mav.addObject("callback", callback);
		return mav;
	}
	

	/**
	 * 권한정보 복사
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView aclCopy(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String resourceIdFrom = UtilRequest.getString(request, "resourceIdFrom");
		String resourceIdTo = UtilRequest.getString(request, "resourceIdTo");
		
		// 복사할 리소스의 권한 조회
		String accessInfo = accessControllService.getAccess(resourceIdFrom);

		// 권한 복사전 기존에 내용은 제거
		accessControllService.aclSave(resourceIdTo, "");
		
		// 복사
		boolean result = accessControllService.aclSave(resourceIdTo, accessInfo);
	    
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}

	
	/**
	 * 리소스에 대한 ACL 정보 추가
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView aclSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String resourceId = UtilRequest.getString(request, "resourceID");
		String accessInfos = UtilRequest.getString(request, "accessInfos");
		
		// 리소스에 대한 Access 정보
		boolean result = accessControllService.aclSave(resourceId, accessInfos);
	    
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}

	
	/**
	 * 리소스에 대한 ACL 정보 조회
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView aclView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String resourceId = UtilRequest.getString(request, "resourceID");
		AccessList accessList = new AccessList(); 

		// 리소스에 대한 Access 정보
		String accessInfo = accessControllService.getAccess(resourceId);

		if(accessInfo != null){
			// 액세스 정보 문자를 AccessList 형태로 변환한다 
			accessList = accessControllService.makeAccessList(accessInfo);
	    }
		
		ModelAndView mav = new ModelAndView(aclView);
		mav.addObject("accessInfos", accessList);
		return mav;
	}
	
}
