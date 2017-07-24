package com.sds.acube.luxor.portal.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.sds.acube.luxor.common.service.MessageService;
import com.sds.acube.luxor.common.util.UtilRequest;
import com.sds.acube.luxor.common.vo.UserProfileVO;
import com.sds.acube.luxor.framework.controller.BaseController;
import com.sds.acube.luxor.portal.service.PortalMenuService;
import com.sds.acube.luxor.portal.vo.PortalMenuVO;


public class PortalMenuController extends BaseController {
	private PortalMenuService portalMenuService;
	private MessageService messageService;
	
	public void setPortalMenuService(PortalMenuService portalMenuService) {
    	this.portalMenuService = portalMenuService;
    }

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}


	public ModelAndView insertMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalMenuVO paramVO = new PortalMenuVO();
		bind(request, paramVO);
		boolean result = portalMenuService.insertMenu(paramVO);
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}
	
	
	public ModelAndView updateMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalMenuVO paramVO = new PortalMenuVO();
		bind(request, paramVO);

		// 수정자 UID 셋팅
		UserProfileVO userProfile = (UserProfileVO)WebUtils.getSessionAttribute(request, "userProfile");
		String userId = userProfile.getUserUid();
		paramVO.setModUserId(userId);
		
		boolean result = portalMenuService.updateMenu(paramVO);
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}
	
	public ModelAndView setMenuDisplayName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalMenuVO paramVO = new PortalMenuVO();
		bind(request, paramVO);
		String mode = UtilRequest.getString(request, "mode");
		String displayNameId = UtilRequest.getString(request, "messageId");
		boolean result = true;
		if(mode.equals("insert")) {
			displayNameId = messageService.insertMessage(paramVO);
		} else  if(mode.equals("delete")) {
			result = messageService.deleteMessage(paramVO);
			displayNameId = "display_none";
		} else {
			result = messageService.updateMessage(paramVO);
		}
		
		if(result != false) {
			paramVO.setDisplayNameId(displayNameId);
			// 수정자 UID 셋팅
			UserProfileVO userProfile = (UserProfileVO)WebUtils.getSessionAttribute(request, "userProfile");
			String userId = userProfile.getUserUid();
			paramVO.setModUserId(userId);
			
			result = portalMenuService.updateMenuDisplayName(paramVO);
		} 
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}
	
	public ModelAndView updateMenuSeq(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalMenuVO paramVO = new PortalMenuVO();
		bind(request, paramVO);
		boolean result = portalMenuService.updateMenuSeq(paramVO);
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}

	
	public ModelAndView updateMenuToAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalMenuVO paramVO = new PortalMenuVO();
		bind(request, paramVO);
		boolean result = portalMenuService.updateMenuToAll(paramVO);
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}

	
	public ModelAndView deleteMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalMenuVO paramVO = new PortalMenuVO();
		bind(request, paramVO);
		boolean result = portalMenuService.deleteMenu(paramVO);
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}
	
	public ModelAndView deleteMenuAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalMenuVO paramVO = new PortalMenuVO();
		bind(request, paramVO);
		portalMenuService.deleteMenuAll(paramVO);
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", true);
		return mav;
	}

	public ModelAndView getMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalMenuVO paramVO = new PortalMenuVO();
		bind(request, paramVO);
		PortalMenuVO menu = portalMenuService.getMenu(paramVO);
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", menu);
		return mav;
	}

	public ModelAndView getMenusOnZone(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalMenuVO paramVO = new PortalMenuVO();
		bind(request, paramVO);
		List list = portalMenuService.getMenusOnZone(paramVO);
		PortalMenuVO[] menus = new PortalMenuVO[list.size()];
		list.toArray(menus);
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", menus);
		return mav;
	}

	
	/**
	 * Ajax로 메뉴포틀릿 가져갈때
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getMenusOnContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalMenuVO paramVO = new PortalMenuVO();
		bind(request, paramVO);
		List list = portalMenuService.getMenusOnContent(paramVO);
		PortalMenuVO[] menus = new PortalMenuVO[list.size()];
		list.toArray(menus);
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", menus);
		return mav;
	}
	
	
	/**
	 * 나의메뉴
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getMyMenus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalMenuVO paramVO = new PortalMenuVO();
		bind(request, paramVO);
		
		List list = portalMenuService.getMyMenus(paramVO);
		PortalMenuVO[] menus = new PortalMenuVO[list.size()];
		list.toArray(menus);
		for(int i=0; i < menus.length; i++) {
			menus[i].setLangType(paramVO.getLangType());
			PortalMenuVO tempMenu = portalMenuService.getMenu((PortalMenuVO) menus[i]);
			if(!tempMenu.getDisplayNameId().equals("display_none")) {
				menus[i].setDisplayNameId(tempMenu.getDisplayNameId());
			}
		}
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", menus);
		return mav;
	}

	
	/**
	 * 메뉴검색
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getMenus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalMenuVO paramVO = new PortalMenuVO();
		bind(request, paramVO);
		
		List list = portalMenuService.getMenus(paramVO);
		PortalMenuVO[] menus = new PortalMenuVO[list.size()];
		list.toArray(menus);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", menus);
		return mav;
	}
}
