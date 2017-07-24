package com.sds.acube.luxor.common.aspect;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import com.sds.acube.luxor.common.service.AccessControllService;
import com.sds.acube.luxor.common.vo.CommonVO;
import com.sds.acube.luxor.common.vo.TreeVO;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.portal.vo.AdminUserVO;
import com.sds.acube.luxor.portal.vo.LoginPopupVO;
import com.sds.acube.luxor.portal.vo.PortalContentVO;
import com.sds.acube.luxor.portal.vo.PortalMenuVO;
import com.sds.acube.luxor.portal.vo.PortalPageVO;
import com.sds.acube.luxor.portal.vo.PortalPageZoneVO;


public class LuxorAccessControllAspect {
	private AccessControllService accessControllService;
	Log logger = LogFactory.getLog(LuxorLogAspect.class);
	
	public void setAccessControllService(AccessControllService accessControllService) {
    	this.accessControllService = accessControllService;
    }

	public void beforeCreate() {
	}
	

	/**
	 * 트리 조회시 권한체크
	 * 
	 * @param thisJoinPoint
	 * @param returnVO
	 */
	public void accessControll4PageTree(final JoinPoint thisJoinPoint, List returnVO) throws Exception {
		if(returnVO==null) {
			return;
		}
		
		CommonVO commonVO = getCommonVO(thisJoinPoint);
		List accessIdList = commonVO.getAccessIdList();
		
		// DB에서 Select한 모든 트리 권한 검사
    	for(int i=0; i < returnVO.size(); i++) {
			String nodeId = ((TreeVO) returnVO.get(i)).getNodeId();
			String nodeName = ((TreeVO) returnVO.get(i)).getNodeName();
			
			logger.debug("Loading node ["+nodeName+"::"+nodeId+"]");
			
			// 관리자인 경우 권한체크 패스
			boolean canAccess = false;
			if(accessIdList.indexOf("ADMIN") > -1) {
				AdminUserVO[] admins = LuxorConfig.getAdminList();
				for(int j = 0 ; j < admins.length ; j++) {
					//그룹포탈관리자는 모든 포탈에 권한이 풀림
					//일반포탈관리자는 자기 포탈일때만 권한이 풀린다.
					if(admins[j].getAdminType() == 0 && admins[j].getIsIgnoreAcl().equals("Y")) {
						if(accessIdList.indexOf(admins[j].getUserUid()) > -1) {
							canAccess = true;
						}
					} else if(admins[j].getAdminType() == 1 && admins[j].getIsIgnoreAcl().equals("Y")) { 
						if(accessIdList.indexOf(admins[j].getUserUid()) > -1 && commonVO.getPortalId().equals(admins[j].getPortalId())) {
							canAccess = true;
						}
					} 
				}
			} 

			if(canAccess == false) {
				canAccess = accessControllService.canAccess(nodeId, accessIdList);
			}

			// 권한이 없는 경우
			if(canAccess==false) {
				logger.info("Node ["+nodeName+"::"+nodeId+"] has been filtered by ACL");
				returnVO.remove(i--);
			}
    	}
    }

	
	/**
	 * 페이지 조회시 권한체크
	 * 
	 * @param thisJoinPoint
	 * @param returnVO
	 */
	public void accessControll4Page(final JoinPoint thisJoinPoint, CommonVO returnVO) throws Exception {
		if(returnVO==null) {
			return;
		}
		
		CommonVO commonVO = getCommonVO(thisJoinPoint);
		List accessIdList = commonVO.getAccessIdList();
		
		String pageId = ((PortalPageVO) returnVO).getPageId();
		String pageName = ((PortalPageVO) returnVO).getMessageName();

		logger.info("Loading page ["+pageName+"::"+pageId+"]");
		
		// 관리자인 경우 권한체크 패스
		boolean canAccess = false;
		if(accessIdList.indexOf("ADMIN") > -1) {
			AdminUserVO[] admins = LuxorConfig.getAdminList();
			for(int j = 0 ; j < admins.length ; j++) {
				//그룹포탈관리자는 모든 포탈에 권한이 풀림
				//일반포탈관리자는 자기 포탈일때만 권한이 풀린다.
				if(admins[j].getAdminType() == 0 && admins[j].getIsIgnoreAcl().equals("Y")) {
					if(accessIdList.indexOf(admins[j].getUserUid()) > -1) {
						canAccess = true;
					}
				} else if(admins[j].getAdminType() == 1 && admins[j].getIsIgnoreAcl().equals("Y")) { 
					if(accessIdList.indexOf(admins[j].getUserUid()) > -1 && commonVO.getPortalId().equals(admins[j].getPortalId())) {
						canAccess = true;
					}
				} 
			}
		} 
		if(accessIdList.indexOf("Ua67c9c6aa7ac4031ff9")>-1){
		  canAccess = true;
		  System.out.println("----------------0426------------");
		  System.out.println(((PortalPageVO) returnVO).getPageId());
		  System.out.println(((PortalPageVO) returnVO).getPageNamePath());
		  System.out.println("----------------0426------------");
		}
		if(canAccess == false) {
			canAccess = accessControllService.canAccess(pageId, accessIdList);
		}
		
		if(canAccess==false) {
			logger.info("Page ["+pageName+"::"+pageId+"] has been filtered by ACL");
			((PortalPageVO) returnVO).setPageId("NO_PERMISSION");
		}
    }
	
	/**
	 * 템플릿 페이지 조회시 권한체크
	 * 
	 * @param thisJoinPoint
	 * @param returnVO
	 */
	public void accessControll4TemplatePage(final JoinPoint thisJoinPoint, List returnVO) throws Exception {
		if(returnVO==null) {
			return;
		}
		
		CommonVO commonVO = getCommonVO(thisJoinPoint);
		List accessIdList = commonVO.getAccessIdList();
		
		for(int i=0; i < returnVO.size(); i++) {
			String pageId = ((PortalPageVO) returnVO.get(i)).getPageId();
			String pageName = ((PortalPageVO) returnVO.get(i)).getMessageName();
			
			logger.debug("Loading Page ["+pageId+"::"+pageName+"]");

			// 관리자인 경우 권한체크 패스
    		boolean canAccess = false;
    		if(accessIdList.indexOf("ADMIN") > -1) {
    			AdminUserVO[] admins = LuxorConfig.getAdminList();
    			for(int j = 0 ; j < admins.length ; j++) {
    				//그룹포탈관리자는 모든 포탈에 권한이 풀림
    				//일반포탈관리자는 자기 포탈일때만 권한이 풀린다.
    				if(admins[j].getAdminType() == 0 && admins[j].getIsIgnoreAcl().equals("Y")) {
    					if(accessIdList.indexOf(admins[j].getUserUid()) > -1) {
    						canAccess = true;
    					}
    				} else if(admins[j].getAdminType() == 1 && admins[j].getIsIgnoreAcl().equals("Y")) { 
    					if(accessIdList.indexOf(admins[j].getUserUid()) > -1 && commonVO.getPortalId().equals(admins[j].getPortalId())) {
    						canAccess = true;
    					}
    				} 
    			}
    		}
    		
    		if(canAccess == false) {
    			canAccess = accessControllService.canAccess(pageId, accessIdList);
    		}

			// 권한이 없는 경우
			if(canAccess==false) {
				String controllType = LuxorConfig.getEnvString(commonVO.getTenantId(), commonVO.getPortalId(), "NO_PERMISSION_MENU");
				
				// 메뉴 권한 처리타입이 hide인 경우 결과값에서 제거해서 JSP로 안넘겨줌
				if("hide".equals(controllType)) {
					logger.info("Menu ["+pageName+"::"+pageId+"] has been filtered by ACL");
					returnVO.remove(i--);
				}
			}
		}
    }

	
	/**
	 * 메뉴 조회시 권한체크 (결과가 List로 리턴)
	 * 
	 * @param thisJoinPoint
	 * @param returnVO
	 */
	public void accessControll4Menu(final JoinPoint thisJoinPoint, List returnVO) throws Exception {
		if(returnVO==null) {
			return;
		}
		
		CommonVO commonVO = getCommonVO(thisJoinPoint);
		List accessIdList = commonVO.getAccessIdList();
    	
		// DB에서 Select한 모든 메뉴 권한 검사
    	for(int i=0; i < returnVO.size(); i++) {
			String menuId = ((PortalMenuVO) returnVO.get(i)).getMenuId();
			String menuName = ((PortalMenuVO) returnVO.get(i)).getMessageName();
			
			logger.debug("Loading menu ["+menuName+"::"+menuId+"]");

			// 관리자인 경우 권한체크 패스
    		boolean canAccess = false;
    		if(accessIdList.indexOf("ADMIN") > -1) {
    			AdminUserVO[] admins = LuxorConfig.getAdminList();
    			for(int j = 0 ; j < admins.length ; j++) {
    				//그룹포탈관리자는 모든 포탈에 권한이 풀림
    				//일반포탈관리자는 자기 포탈일때만 권한이 풀린다.
    				if(admins[j].getAdminType() == 0 && admins[j].getIsIgnoreAcl().equals("Y")) {
    					if(accessIdList.indexOf(admins[j].getUserUid()) > -1) {
    						canAccess = true;
    					}
    				} else if(admins[j].getAdminType() == 1 && admins[j].getIsIgnoreAcl().equals("Y")) { 
    					if(accessIdList.indexOf(admins[j].getUserUid()) > -1 && commonVO.getPortalId().equals(admins[j].getPortalId())) {
    						canAccess = true;
    					}
    				} 
    			}
    		} 

    		if(canAccess == false) {
				canAccess = accessControllService.canAccess(menuId, accessIdList);
			}

    		
			// 권한이 없는 경우
			if(canAccess==false) {
				String controllType = LuxorConfig.getEnvString(commonVO.getTenantId(), commonVO.getPortalId(), "NO_PERMISSION_MENU");
				
				// 메뉴 권한 처리타입이 hide인 경우 결과값에서 제거해서 JSP로 안넘겨줌
				if("hide".equals(controllType)) {
					logger.info("Menu ["+menuName+"::"+menuId+"] has been filtered by ACL");
					returnVO.remove(i--);
				}
			}
    	}
    }


	/**
	 * 컨텐츠 조회시 권한체크 (결과가 List로 리턴)
	 * 
	 * @param thisJoinPoint
	 * @param returnVO
	 */
	public void accessControll4Content(final JoinPoint thisJoinPoint, List returnVO) throws Exception {
		if(returnVO==null) {
			return;
		}
		
		CommonVO commonVO = getCommonVO(thisJoinPoint);
		List accessIdList = commonVO.getAccessIdList();
    	
		// DB에서 Select한 모든 메뉴 권한 검사
    	for(int i=0; i < returnVO.size(); i++) {
    		String contentId = "";
    		String contentName = "";
    		if(returnVO.get(i) instanceof PortalPageZoneVO) {
    			contentId = ((PortalPageZoneVO) returnVO.get(i)).getContentId();
    			contentName = ((PortalPageZoneVO) returnVO.get(i)).getMessageName();
    		} else {
    			contentId = ((PortalContentVO) returnVO.get(i)).getContentId();
    			contentName = ((PortalContentVO) returnVO.get(i)).getMessageName();
    		}

    		logger.debug("Loading content ["+contentName+"::"+contentId+"]");
    		
    		// 관리자인 경우 권한체크 패스
    		boolean canAccess = false;
    		if(accessIdList.indexOf("ADMIN") > -1) {
    			AdminUserVO[] admins = LuxorConfig.getAdminList();
    			for(int j = 0 ; j < admins.length ; j++) {
    				//그룹포탈관리자는 모든 포탈에 권한이 풀림
    				//일반포탈관리자는 자기 포탈일때만 권한이 풀린다.
    				if(admins[j].getAdminType() == 0 && admins[j].getIsIgnoreAcl().equals("Y")) {
    					if(accessIdList.indexOf(admins[j].getUserUid()) > -1) {
    						canAccess = true;
    					}
    				} else if(admins[j].getAdminType() == 1 && admins[j].getIsIgnoreAcl().equals("Y")) { 
    					if(accessIdList.indexOf(admins[j].getUserUid()) > -1 && commonVO.getPortalId().equals(admins[j].getPortalId())) {
    						canAccess = true;
    					}
    				}
    			}
    		} 

    		if(canAccess == false) {
				canAccess = accessControllService.canAccess(contentId, accessIdList);
			}
    		
			// 권한이 없는 경우
			if(canAccess==false) {
				String controllType = LuxorConfig.getEnvString(commonVO.getTenantId(), commonVO.getPortalId(), "NO_PERMISSION_CONTENT");
				
				// 컨텐츠 권한 처리타입이 hide인 경우 결과값에서 제거해서 JSP로 안넘겨줌
				if("hide".equals(controllType)) {
					logger.info("Content ["+contentName+"::"+contentId+"] has been filtered by ACL");
					returnVO.remove(i--);
				}
				// 처리타입이 show인 경우에는 권한없음으로 넘겨줌
				else {
		    		if(returnVO.get(i) instanceof PortalPageZoneVO) {
		    			((PortalPageZoneVO) returnVO.get(i)).setPortletId("NO_PERMISSION");
		    		} else {
		    			returnVO.remove(i--);
		    		}
				}
			}
    	}
    }

	
	/**
	 * 로그인 팝업 권한 체크
	 * 
	 * @param thisJoinPoint
	 * @param returnVO
	 * @throws Exception
	 */
	public void accessControll4LoginPopup(final JoinPoint thisJoinPoint, List returnVO) throws Exception {
		if(returnVO==null) {
			return;
		}
		
		CommonVO commonVO = getCommonVO(thisJoinPoint);
		List accessIdList = commonVO.getAccessIdList();
    	
		// DB에서 Select한 모든 메뉴 권한 검사
    	for(int i=0; i < returnVO.size(); i++) {
			String popupId = ((LoginPopupVO) returnVO.get(i)).getPopupId();
			String popupTitle = ((LoginPopupVO) returnVO.get(i)).getPopupTitle();
			
			logger.debug("Loading login popup ["+popupTitle+"::"+popupId+"]");

   			boolean canAccess = accessControllService.canAccess(popupId, accessIdList);
    		
			// 권한이 없는 경우
			if(canAccess==false) {
				logger.info("Login popup ["+popupTitle+"::"+popupId+"] has been filtered by ACL");
				returnVO.remove(i--);
			}
    	}		
	}
	

	/**
	 * insert/update/delete 할때 호출
	 * 
	 * @param thisJoinPoint
	 * @throws Exception
	 */
	public void accessControll4Write(final JoinPoint thisJoinPoint) throws Exception {
		/*
		String methodName = (thisJoinPoint.getSignature().getName());
		CommonVO commonVO = getCommonVO(thisJoinPoint);
		
		String regUserId = commonVO.getRegUserId();
		List accessIdList = commonVO.getAccessIdList();
		*/
	}
	
	
	
	/**
	 * Request 객체가 넘어온 경우 추출해냄
	 * 
	 * @param thisJoinPoint
	 * @return
	 */
	private CommonVO getCommonVO(JoinPoint thisJoinPoint) {
		CommonVO commonVO = new CommonVO();
    	Object[] args = thisJoinPoint.getArgs();
    	for(Object arg : args) {
    		if(arg instanceof CommonVO) {
    			commonVO = (CommonVO) arg;
    			break;
    		}
    	}
    	return commonVO;
	}

}
