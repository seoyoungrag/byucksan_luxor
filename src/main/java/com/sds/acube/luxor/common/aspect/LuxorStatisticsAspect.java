package com.sds.acube.luxor.common.aspect;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import com.sds.acube.luxor.common.ConstantList;
import com.sds.acube.luxor.common.service.LoginService;
import com.sds.acube.luxor.common.service.StatisticsService;
import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.vo.CommonVO;
import com.sds.acube.luxor.common.vo.ContentsStatisticsVO;
import com.sds.acube.luxor.common.vo.LoginHistoryVO;
import com.sds.acube.luxor.common.vo.PageStatisticsVO;
import com.sds.acube.luxor.common.vo.UserProfileVO;
import com.sds.acube.luxor.portal.service.AdminService;
import com.sds.acube.luxor.portal.service.GroupPortalService;
import com.sds.acube.luxor.portal.vo.AdminUserVO;
import com.sds.acube.luxor.portal.vo.GroupPortalVO;
import com.sds.acube.luxor.portal.vo.PortalPageVO;
import com.sds.acube.luxor.portal.vo.PortalPageZoneVO;


public class LuxorStatisticsAspect {
	Log logger = LogFactory.getLog(LuxorStatisticsAspect.class);
	private StatisticsService statisticsService;
	private LoginService loginService;
	private AdminService adminService;
	private GroupPortalService groupPortalService;


	public void setStatisticsService(StatisticsService statisticsService) {
		this.statisticsService = statisticsService;
	}


	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}


	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}


	public void setGroupPortalService(GroupPortalService groupPortalService) {
		this.groupPortalService = groupPortalService;
	}


	public void beforeCreate() {}


	public void insertLoginHistory(final JoinPoint thisJoinPoint, UserProfileVO retVal) {
		String loginIp = "";
		String userAgent = "";
		String sessionId = "";
		String portalId = "";
		GroupPortalVO r = null;
		GroupPortalVO portalVO = new GroupPortalVO();
		HttpServletRequest request = null;
		
		try {
			Object[] args = thisJoinPoint.getArgs();
			for (Object arg : args) {
				if (arg instanceof CommonVO) {
					request = (HttpServletRequest) ((CommonVO) arg).getRequest();
					if (request != null) {
						loginIp = request.getRemoteAddr();
						userAgent = request.getHeader("USER-AGENT");
						sessionId = request.getSession().getId();
					}
				}
			}
			
			if (retVal != null) {
				if (retVal.getLoginResult() == ConstantList.LOGIN_SUCCESS) { // login 성공일 경우
					LoginHistoryVO vo = new LoginHistoryVO();
					vo.setTenantId(retVal.getTenantId());
					vo.setStatisticsId(CommonUtil.generateId("LS")); // Log Statistics
					vo.setSessionId(sessionId); // Session Id
					vo.setUserUid(retVal.getUserUid());
					vo.setLoginId(retVal.getLoginId());
					vo.setUserName(retVal.getUserName());
					vo.setLoginIp(loginIp);
					vo.setCompName(retVal.getCompName());
					vo.setDptName(retVal.getDeptName());
					vo.setGradeName(retVal.getGradeName());
					vo.setUserAgent(userAgent);
					portalId = retVal.getPortalId();
					
					if (portalId == null || portalId.equals("")) { // admin일 경우
						AdminUserVO adminUserParam = new AdminUserVO();
						AdminUserVO adminUser = new AdminUserVO();
						adminUserParam.setUserId(retVal.getLoginId());
						adminUserParam.setTenantId(retVal.getTenantId());
						adminUser = adminService.getAdminUser(adminUserParam);
						
						if (adminUser == null) { // login id가 admin이 아닐 경우
							logger.debug("Permission Denied! " + retVal.getLoginId() + " is not administrator!");
						} else {
							portalVO.setTenantId(adminUser.getTenantId());
							portalVO.setPortalId(adminUser.getPortalId());
							if (ConstantList.ROOT_PORTAL_ID.equals(adminUser.getPortalId())) {
								r = groupPortalService.getDefault(portalVO); // default portal
							} else {
								r = groupPortalService.get(portalVO);
							}
							if (r != null) portalId = r.getPortalId();
							vo.setPortalId(portalId);
							loginService.insertLoginHistory(vo);
						}
					} else { // admin이 아닐 경우
						vo.setPortalId(portalId);
						loginService.insertLoginHistory(vo);
					}
				}
			}
		} catch (Exception e) {}
	}


	public void insertPageStatistics(final JoinPoint thisJoinPoint, PortalPageVO retVal) {
		String accessUserLoginId = "";
		String accessUserLoginName = "";
		String loginIp = "";
		Object[] args = thisJoinPoint.getArgs();
		for (Object arg : args) {
			if (arg instanceof CommonVO) {
				HttpServletRequest request = (HttpServletRequest) ((CommonVO) arg).getRequest();
				loginIp = request.getRemoteAddr();
				if (request != null) {
					// 관리자인 경우에도 통계 수집함
					AdminUserVO adminUserVO = (AdminUserVO) request.getSession().getAttribute("adminProfile");
					if(adminUserVO!=null) {
						//return;
					}
					
					UserProfileVO userProfileVO = (UserProfileVO) request.getSession().getAttribute("userProfile");
					if (userProfileVO != null) {
						accessUserLoginId = userProfileVO.getLoginId();
						accessUserLoginName = userProfileVO.getUserName();
					}
				}
			}
		}
		if (retVal != null) {
				
			PageStatisticsVO vo = new PageStatisticsVO();
			vo.setTenantId(retVal.getTenantId());
			vo.setPortalId(retVal.getPortalId());
			vo.setStatisticsId(CommonUtil.generateId("PS")); // Page Statistics
			vo.setPageId(retVal.getPageId());
			vo.setAccessUserName(accessUserLoginName);
			vo.setAccessUserId(accessUserLoginId);
			vo.setLoginIp(loginIp);
			try {
				statisticsService.insertPageStatistics(vo);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}


	public void insertContentStatistics(final JoinPoint thisJoinPoint, List retVal) {
		String accessUserLoginId = "";
		Object[] args = thisJoinPoint.getArgs();
		for (Object arg : args) {
			if (arg instanceof CommonVO) {
				HttpServletRequest request = (HttpServletRequest) ((CommonVO) arg).getRequest();
				if (request != null) {
					// 관리자인 경우에도 통계 수집함
					AdminUserVO adminUserVO = (AdminUserVO) request.getSession().getAttribute("adminProfile");
					if(adminUserVO!=null) {
						//return;
					}

					UserProfileVO userProfileVO = (UserProfileVO) request.getSession().getAttribute("userProfile");
					if (userProfileVO != null) {
						accessUserLoginId = userProfileVO.getLoginId();
					}
				}
			}
		}
		if (retVal != null) {
			ContentsStatisticsVO vo = new ContentsStatisticsVO();
			PortalPageZoneVO[] contents = new PortalPageZoneVO[retVal.size()];
			retVal.toArray(contents);
			for (PortalPageZoneVO content : contents) {
				vo.setTenantId(content.getTenantId());
				vo.setPortalId(content.getPortalId());
				vo.setStatisticsId(CommonUtil.generateId("CS")); // Contents Statistics
				vo.setContentsId(content.getContentId());
				vo.setContentsNameId(content.getContentNameId());
				vo.setAccessUserId(accessUserLoginId);
				try {
					statisticsService.insertContentsStatistics(vo);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}
}
