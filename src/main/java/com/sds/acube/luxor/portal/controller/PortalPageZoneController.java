package com.sds.acube.luxor.portal.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import com.sds.acube.luxor.common.ConstantList;
import com.sds.acube.luxor.common.service.UserSettingService;
import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.util.UtilRequest;
import com.sds.acube.luxor.common.vo.UserProfileVO;
import com.sds.acube.luxor.common.vo.UserSettingVO;
import com.sds.acube.luxor.framework.controller.BaseController;
import com.sds.acube.luxor.portal.service.PortalPageZoneService;
import com.sds.acube.luxor.portal.vo.PortalPageZoneVO;


public class PortalPageZoneController extends BaseController {
	private PortalPageZoneService portalPageZoneService;
	private UserSettingService userSettingService;
	
	public void setPortalPageZoneService(PortalPageZoneService portalPageZoneService) {
    	this.portalPageZoneService = portalPageZoneService;
    }
	
	public void setUserSettingService(UserSettingService userSettingService) {
    	this.userSettingService = userSettingService;
    }
	
	public ModelAndView insertContentOnZone(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageZoneVO paramVO = new PortalPageZoneVO();
		bind(request, paramVO);

		if(!"UM".equals(paramVO.getRefer()) && !"DD".equals(paramVO.getRefer())) {
			// 관리자가 등록하는 컨텐츠인 경우에는 등록자에 ADMIN이라고 넣음
			List accessIdList = (List)WebUtils.getSessionAttribute(request, "ACCESS_ID_LIST");
			if(accessIdList.contains("ADMIN")) {
				paramVO.setRegUserId("ADMIN");
			}
		}
		
		boolean result = portalPageZoneService.insertContentOnZone(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}
	

	public ModelAndView deleteContentOnZone(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageZoneVO paramVO = new PortalPageZoneVO();
		bind(request, paramVO);
		
		if(!"UM".equals(paramVO.getRefer()) && !"DD".equals(paramVO.getRefer())) {
			// 관리자가 등록하는 컨텐츠인 경우에는 등록자에 ADMIN이라고 넣음
			List accessIdList = (List)WebUtils.getSessionAttribute(request, "ACCESS_ID_LIST");
			if(accessIdList.contains("ADMIN")) {
				paramVO.setRegUserId("ADMIN");
			}
		}

		boolean result = portalPageZoneService.deleteContentOnZone(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}


	public ModelAndView deleteAllContentOnZone(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageZoneVO paramVO = new PortalPageZoneVO();
		bind(request, paramVO);

		if(!"UM".equals(paramVO.getRefer()) && !"DD".equals(paramVO.getRefer())) {
			// 관리자가 등록하는 컨텐츠인 경우에는 등록자에 ADMIN이라고 넣음
			List accessIdList = (List)WebUtils.getSessionAttribute(request, "ACCESS_ID_LIST");
			if(accessIdList.contains("ADMIN")) {
				paramVO.setRegUserId("ADMIN");
			}
		}

		boolean result = portalPageZoneService.deleteAllContentOnZone(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}


	public ModelAndView deleteAllContentOnPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageZoneVO paramVO = new PortalPageZoneVO();
		bind(request, paramVO);
		
		if(!"UM".equals(paramVO.getRefer()) && !"DD".equals(paramVO.getRefer())) {
			// 관리자가 등록하는 컨텐츠인 경우에는 등록자에 ADMIN이라고 넣음
			List accessIdList = (List)WebUtils.getSessionAttribute(request, "ACCESS_ID_LIST");
			if(accessIdList.contains("ADMIN")) {
				paramVO.setRegUserId("ADMIN");
			}
		}

		boolean result = portalPageZoneService.deleteAllContentOnPage(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}

	public ModelAndView updateSeq(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageZoneVO paramVO = new PortalPageZoneVO();
		bind(request, paramVO);
		
		if(!"UM".equals(paramVO.getRefer()) && !"DD".equals(paramVO.getRefer())) {
			// 관리자가 등록하는 컨텐츠인 경우에는 등록자에 ADMIN이라고 넣음
			List accessIdList = (List)WebUtils.getSessionAttribute(request, "ACCESS_ID_LIST");
			if(accessIdList.contains("ADMIN")) {
				paramVO.setRegUserId("ADMIN");
			}
		}
		
		boolean result = portalPageZoneService.updateSeq(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}
	
	public ModelAndView updateContentStyle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageZoneVO paramVO = new PortalPageZoneVO();
		bind(request, paramVO);
		
		boolean result = portalPageZoneService.updateContentStyle(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}

	
	public ModelAndView updateContentFixedFlag(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageZoneVO paramVO = new PortalPageZoneVO();
		bind(request, paramVO);
		
		adminCheck(request);
		boolean result = portalPageZoneService.updateContentFixedFlag(paramVO);
		if( result == true ) { 
			result = portalPageZoneService.clearContentsToSetTheFixedContent(paramVO);
		} 
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}

	
	public ModelAndView updateContentDeletedFlag(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageZoneVO paramVO = new PortalPageZoneVO();
		bind(request, paramVO);
		
		boolean result = portalPageZoneService.updateContentDeletedFlag(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}

	
	public ModelAndView getContentOnZone(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageZoneVO paramVO = new PortalPageZoneVO();
		bind(request, paramVO);
		
		PortalPageZoneVO resultVO = portalPageZoneService.getContentOnZone(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", resultVO);
		return mav;
	}

	public ModelAndView setPersonalizedPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageZoneVO paramVO = new PortalPageZoneVO();
		PortalPageZoneVO insertVO = new PortalPageZoneVO();
		bind(request, paramVO);
		
		List list = portalPageZoneService.getUnpersonalizedAdminContents(paramVO);
		
		PortalPageZoneVO[] contents = new PortalPageZoneVO[list.size()];
		list.toArray(contents);
		
		for(int i=0 ; i< contents.length ; i++) {
			if(paramVO.getContentId()!=null && paramVO.getContentId().equals(contents[i].getContentId())) {
				insertVO.setIsDeleted("Y");
			} else {
				insertVO.setIsDeleted("N");
			}
			bind(request, insertVO);
			insertVO.setPageId(contents[i].getPageId());
			insertVO.setZoneId(contents[i].getZoneId());
			insertVO.setSeq(contents[i].getSeq());
			insertVO.setContentId(contents[i].getContentId());
			if(!contents[i].getZoneId().equals("header") && !contents[i].getZoneId().equals("footer")) {
				portalPageZoneService.insertPersonalizedContentOnZone(insertVO);
			}
		}
		
		ModelAndView mav = new ModelAndView();
		if(UtilRequest.getString(request,"setDeleteFlag").equals("Y")) {
			paramVO.setIsDeleted("Y");
			boolean result = portalPageZoneService.updateContentDeletedFlag(paramVO);
			mav.addObject("jsonResult", result);
		} else {
			mav.addObject("jsonResult", paramVO.getContentId());
		}
		return mav;
	}

	public ModelAndView getContentsOnZone(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageZoneVO paramVO = new PortalPageZoneVO();
		bind(request, paramVO);
		
		List list = portalPageZoneService.getContentsOnZone(paramVO);
		
		PortalPageZoneVO[] contents = new PortalPageZoneVO[list.size()];
		list.toArray(contents);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", contents);
		return mav;
	}

	
	public ModelAndView getContentsOnPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageZoneVO paramVO = new PortalPageZoneVO();
		bind(request, paramVO);
		
		List list = portalPageZoneService.getContentsOnPage(paramVO);
		
		PortalPageZoneVO[] contents = new PortalPageZoneVO[list.size()];
		list.toArray(contents);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", contents);
		return mav;
	}

	public ModelAndView getPagesHasContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageZoneVO paramVO = new PortalPageZoneVO();
		bind(request, paramVO);
		
		List list = portalPageZoneService.getPagesHasContent(paramVO);
		
		PortalPageZoneVO[] contents = new PortalPageZoneVO[list.size()];
		list.toArray(contents);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", contents);
		return mav;
	}
	
	//해당 개인화 페이지 사용자 설정 초기화 세팅
	public ModelAndView deleteAllContentPersonalizedPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageZoneVO paramVO = new PortalPageZoneVO();
		bind(request, paramVO);

		boolean result = portalPageZoneService.deleteAllContentPersonalizedPage(paramVO);
		
		UserProfileVO userProfile = (UserProfileVO)request.getSession().getAttribute("userProfile");
		if(userProfile==null) {
			throw new Exception("Invalid Access - You need to login to access this page!!");
		}
		
		String pageId = CommonUtil.nullTrim(UtilRequest.getString(request,"pageId"));
		String layoutId = CommonUtil.nullTrim(UtilRequest.getString(request,"layoutId"));
		
		UserSettingVO userSettingVO = new UserSettingVO();
		bind(request, userSettingVO);
		
		String key = ConstantList.US_CODE_LAYOUT + "_" + pageId;
		
		userSettingVO.setUserId(userProfile.getUserUid());
		userSettingVO.setSettingCode(key);
		userSettingVO.setSettingValue(layoutId);
		
		if(userSettingService.getUserSetting(userSettingVO)==null) {
		} else {
			result = userSettingService.deleteUserSetting(userSettingVO);
		}
		
		// 새로운 레이아웃 세션에 저장
		if(result) {
			request.getSession().removeAttribute(key);
			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}
	
	public ModelAndView cleanDeletedContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageZoneVO paramVO = new PortalPageZoneVO();
		bind(request, paramVO);

		boolean result = portalPageZoneService.cleanDeletedContent(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}
	
	//특정 컨텐츠가 포함되어있는 페이지들을 리턴함
	public ModelAndView getContentIncludingPages(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageZoneVO paramVO = new PortalPageZoneVO();
		bind(request, paramVO);
		
		List list = portalPageZoneService.getContentIncludingPages(paramVO);
		
		PortalPageZoneVO[] pages = new PortalPageZoneVO[list.size()];
		list.toArray(pages);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", pages);
		return mav;
	}
	
	//개인화 기능중 템플릿 선택된 페이지 세팅
	public ModelAndView setTemplatePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageZoneVO paramVO = new PortalPageZoneVO();
		PortalPageZoneVO tempVO = new PortalPageZoneVO();
		PortalPageZoneVO insertVO = new PortalPageZoneVO();
		bind(request, paramVO);
		bind(request, tempVO);
		String userUid = paramVO.getRegUserId();
		String currentPageId = UtilRequest.getString(request, "currentPageId");
		String templatePageId = UtilRequest.getString(request, "templatePageId");
		String layoutId = UtilRequest.getString(request, "layoutId");
		String result = "true";
		ModelAndView mav = new ModelAndView();
		
		try {
			paramVO.setRefer("UM");
			paramVO.setPageId(currentPageId);
			List<PortalPageZoneVO> currentPageContentList = portalPageZoneService.getContentsOnPage(paramVO);
			List list = portalPageZoneService.getUnpersonalizedAdminContents(paramVO);
			PortalPageZoneVO[] contents = new PortalPageZoneVO[list.size()];
			list.toArray(contents);
			
			for(int i=0 ; i < currentPageContentList.size() ; i++ ) {
				tempVO = currentPageContentList.get(i);
				
					for(int j=0 ; j< contents.length ; j++) {
						if(paramVO.getContentId()!=null && tempVO.getContentId().equals(contents[j].getContentId())) {
							insertVO.setIsDeleted("Y");
							bind(request, insertVO);
							insertVO.setPageId(contents[j].getPageId());
							insertVO.setZoneId(contents[j].getZoneId());
							insertVO.setSeq(contents[j].getSeq());
							insertVO.setContentId(contents[j].getContentId());
							if(!contents[j].getZoneId().equals("header") && !contents[j].getZoneId().equals("footer")) {
								portalPageZoneService.insertPersonalizedContentOnZone(insertVO);
							}
						} 
					}
					
					if(!tempVO.getRegUserId().equals("ADMIN")) {
					tempVO.setIsDeleted("Y");
					portalPageZoneService.updateContentDeletedFlag(tempVO);
				}
			}
			
			paramVO.setPageId(templatePageId);
			List<PortalPageZoneVO> templatePageContentList = portalPageZoneService.getContentsOnPage(paramVO);
			String regUserId = paramVO.getRegUserId();
			boolean token = false;
			for(int i=0 ; i < templatePageContentList.size() ; i++ ) {
				tempVO = templatePageContentList.get(i);
				tempVO.setPageId(currentPageId);
				tempVO.setRegUserId(regUserId);
				token = false;
				for(int j=0 ; j < currentPageContentList.size() ; j++ ) {
					paramVO = currentPageContentList.get(j);
					if(!paramVO.getIsFixed().equals("Y")) {
						if(paramVO.getContentId().equals(tempVO.getContentId())) {
							tempVO.setIsDeleted("N");
							portalPageZoneService.deleteContentOnZone(tempVO);
							portalPageZoneService.insertContentOnZone(tempVO);
							token = true;
						} 
					}
				}
				if(token == false) {
					portalPageZoneService.insertContentOnZone(tempVO);
				}
			}
			
			//레이아웃 변경시
			if(!layoutId.equals("")) {
				
				UserSettingVO userSettingVO = new UserSettingVO();
				bind(request, userSettingVO);
				
				String key = ConstantList.US_CODE_LAYOUT + "_" + currentPageId;
				
				userSettingVO.setUserId(userUid);
				userSettingVO.setSettingCode(key);
				userSettingVO.setSettingValue(layoutId);
				
				boolean lsResult = false;
				if(userSettingService.getUserSetting(userSettingVO)==null) {
					lsResult = userSettingService.insertUserSetting(userSettingVO);
				} else {
					lsResult = userSettingService.updateUserSetting(userSettingVO);
				}
				
				// 새로운 레이아웃 세션에 저장
				if(lsResult) {
					request.getSession().setAttribute(key, layoutId);
				}
			}
			
		} catch(Exception e) {
			result = e.toString();
			throw e;
		} 
		mav.addObject("jsonResult", result);
		return mav;
	}
}
