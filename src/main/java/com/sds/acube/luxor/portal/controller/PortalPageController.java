package com.sds.acube.luxor.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import org.anyframe.pagination.Page;

import com.sds.acube.luxor.common.ConstantList;
import com.sds.acube.luxor.common.service.TreeService;
import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.util.UtilRequest;
import com.sds.acube.luxor.common.vo.TreeVO;
import com.sds.acube.luxor.common.vo.UserProfileVO;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.controller.BaseController;
import com.sds.acube.luxor.portal.service.GroupPortalService;
import com.sds.acube.luxor.portal.service.PortalContentService;
import com.sds.acube.luxor.portal.service.PortalMenuService;
import com.sds.acube.luxor.portal.service.PortalPageLayoutService;
import com.sds.acube.luxor.portal.service.PortalPageService;
import com.sds.acube.luxor.portal.service.PortalPageThemeService;
import com.sds.acube.luxor.portal.vo.GroupPortalVO;
import com.sds.acube.luxor.portal.vo.PortalContentVO;
import com.sds.acube.luxor.portal.vo.PortalMenuVO;
import com.sds.acube.luxor.portal.vo.PortalPageLayoutVO;
import com.sds.acube.luxor.portal.vo.PortalPageThemeVO;
import com.sds.acube.luxor.portal.vo.PortalPageVO;


public class PortalPageController extends BaseController {
	private PortalPageService portalPageService;
	private PortalPageLayoutService portalPageLayoutService;
	private PortalPageThemeService portalPageThemeService;
	private PortalMenuService portalMenuService;
	private GroupPortalService groupPortalService;
	private PortalContentService portalContentService;
	private TreeService treeService;
	private String previewIndex;
	private String index;
	private String pageList;
	private String pageSetup;
	private String pageManager;
	private String pageSelector;
	private String personalPageSelector;
	
	public void setGroupPortalService(GroupPortalService groupPortalService) {
		this.groupPortalService = groupPortalService;
	}
	public void setPortalPageLayoutService(PortalPageLayoutService portalPageLayoutService) {
    	this.portalPageLayoutService = portalPageLayoutService;
    }
	public void setPortalPageThemeService(PortalPageThemeService portalPageThemeService) {
    	this.portalPageThemeService = portalPageThemeService;
    }
	public void setPortalContentService(PortalContentService portalContentService) {
		this.portalContentService = portalContentService;
	}
	public void setIndex(String index) {
    	this.index = index;
    }
	public void setPortalPageService(PortalPageService portalPageService) {
		this.portalPageService = portalPageService;
	}
	public void setPortalMenuService(PortalMenuService portalMenuService) {
		this.portalMenuService = portalMenuService;
	}
	public void setTreeService(TreeService treeService) {
		this.treeService = treeService;
	}
	public void setPageList(String pageList) {
    	this.pageList = pageList;
    }
	public void setPageSetup(String pageSetup) {
    	this.pageSetup = pageSetup;
    }
	public void setPageManager(String pageManager) {
    	this.pageManager = pageManager;
    }
	public void setPageSelector(String pageSelector) {
    	this.pageSelector = pageSelector;
    }
	public void setPersonalPageSelector(String personalPageSelector) {
    	this.personalPageSelector = personalPageSelector;
    }
	public void setPreviewIndex(String previewIndex) {
		this.previewIndex = previewIndex;
	}
	
  public ModelAndView insertPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageVO paramVO = new PortalPageVO();
		bind(request, paramVO);
		
		boolean result;
        try {
        	String defaultAcl = LuxorConfig.getEnvString(request, "DEFAULT_PORTAL_TYPE");
	        result = portalPageService.insertPageWithDefaultAcl(paramVO, defaultAcl, request.getSession());
        } catch (Exception e) {
        	result = false;
        }
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult",result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	

	/**
	 * 페이지 정보 업데이트 
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView updatePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageVO paramVO = new PortalPageVO();
		bind(request, paramVO);
		
		UserProfileVO userProfile = (UserProfileVO)request.getSession().getAttribute("userProfile");
		String userId = userProfile.getUserUid();
		
		paramVO.setModUserId(userId);

		boolean result = portalPageService.updatePage(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult",result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	

	/**
	 * 홈으로 설정 
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView setHome(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PortalPageVO paramVO = new PortalPageVO();
        bind(request, paramVO);
        
        UserProfileVO userProfile = (UserProfileVO)request.getSession().getAttribute("userProfile");
        String userId = userProfile.getUserUid();
        
        paramVO.setModUserId(userId);

        boolean result = portalPageService.setHome(paramVO);

        if(!result) {
        	throw new Exception("Fail to set home page");
        }
        
        ModelAndView mav = new ModelAndView();
        mav.addObject("jsonResult",result);
        mav.addObject("ADMIN_CHECK", true);
		return mav;
	}


	/**
	 * 개인화 설정 
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView setPersonalize(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PortalPageVO paramVO = new PortalPageVO();
        bind(request, paramVO);
        
        UserProfileVO userProfile = (UserProfileVO)request.getSession().getAttribute("userProfile");
        String userId = userProfile.getUserUid();
        
        paramVO.setModUserId(userId);

        boolean result = portalPageService.setPersonalize(paramVO);

        if(!result) {
        	throw new Exception("Fail to set personalize page");
        }
        
        if(paramVO.getIsPersonal().equals("N")) {
        	result = portalPageService.clearPersonalizedPage(paramVO);
        }

        if(!result) {
        	throw new Exception("Fail to clear personalized page");
        }
        
        ModelAndView mav = new ModelAndView();
        mav.addObject("jsonResult",result);
        mav.addObject("ADMIN_CHECK", true);
		return mav;
	}


	/**
	 * 개인페이지 템플릿 설정 
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView setTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PortalPageVO paramVO = new PortalPageVO();
        bind(request, paramVO);
        
        UserProfileVO userProfile = (UserProfileVO)request.getSession().getAttribute("userProfile");
        String userId = userProfile.getUserUid();
        
        paramVO.setModUserId(userId);

        boolean result = portalPageService.setTemplate(paramVO);

        if(!result) {
        	throw new Exception("Fail to set template");
        }
        
        ModelAndView mav = new ModelAndView();
        mav.addObject("jsonResult",result);
        mav.addObject("ADMIN_CHECK", true);
		return mav;
	}

	
	/**
	 * 사용/사용안함 설정 
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView updatePageActiveStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PortalPageVO paramVO = new PortalPageVO();
        bind(request, paramVO);
        
        UserProfileVO userProfile = (UserProfileVO)request.getSession().getAttribute("userProfile");
        String userId = userProfile.getUserUid();
        
        paramVO.setModUserId(userId);

        boolean result = portalPageService.updatePageActiveStatus(paramVO);

        if(!result) {
        	throw new Exception("Fail to set page active status");
        }
        
        ModelAndView mav = new ModelAndView();
        mav.addObject("jsonResult",result);
        mav.addObject("ADMIN_CHECK", true);
		return mav;
	}

	
	/**
	 * 홈으로 설정된 페이지를 가져옴
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getHome(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PortalPageVO paramVO = new PortalPageVO();
        bind(request, paramVO);
        
        List<PortalPageVO> pageList = portalPageService.getHome(paramVO);

        ModelAndView mav = new ModelAndView();
        mav.addObject("jsonResult",pageList);
		return mav;
	}
	

	/**
	 * 개인화  설정된 페이지를 가져옴
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getPersonalPages(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageVO paramVO = new PortalPageVO();
	    bind(request, paramVO);
        
        List<PortalPageVO> pageList = portalPageService.getPersonalPages(paramVO);

        ModelAndView mav = new ModelAndView();
        mav.addObject("jsonResult",pageList);
		return mav;
	}
	
	/**
	 * 템플릿  설정된 페이지를 가져옴
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getTemplatePages(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageVO paramVO = new PortalPageVO();
	    bind(request, paramVO);
        
	    List pageList = portalPageService.getTemplatePages(paramVO);

        ModelAndView mav = new ModelAndView();
        mav.addObject("jsonResult",pageList);
		return mav;
	}
	
	
	/**
	 * 페이지 복사 (페이지 가져오기 할때 호출됨) 
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView copyPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PortalPageVO paramVO = new PortalPageVO();
        bind(request, paramVO);
        
        UserProfileVO userProfile = (UserProfileVO)request.getSession().getAttribute("userProfile");
        String userId = userProfile.getUserUid();
        
        paramVO.setModUserId(userId);

        String fromPageId = UtilRequest.getString(request,"fromPageId");
        String toPageId = UtilRequest.getString(request,"toPageId");
        if(fromPageId==null || toPageId==null) {
        	throw new Exception("No fromPageId || toPageId");
        }
        
        boolean result = portalPageService.copyPage(paramVO, fromPageId, toPageId);

        if(!result) {
        	throw new Exception("Fail to copy page");
        }
        
        ModelAndView mav = new ModelAndView();
        mav.addObject("jsonResult",result);
        mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	
	
	public ModelAndView deletePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageVO paramVO = new PortalPageVO();
		bind(request, paramVO);
		
		boolean result = portalPageService.deletePage(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult",result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}


	/**
	 * 페이지 관리 메인 페이지 호출
	 * 초기 호출시에 트리만 보내주고 트리에서 노드 선택하여 리스트 정보를 Ajax로 가져옴
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getPageManager(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(pageManager);
		
		TreeVO treeVO = new TreeVO();
		GroupPortalVO parentPortalVO = new GroupPortalVO();
		bind(request, parentPortalVO);
		bind(request, treeVO);
		
		treeVO.setTreeId("PORTAL_PAGE");
		TreeVO[] tree = treeService.getTree(treeVO);
		mav.addObject("tree", tree);
		
		parentPortalVO = groupPortalService.get(parentPortalVO);
		mav.addObject("currentPortalName", parentPortalVO.getPortalName());
		//부모그룹이 있는지 체크하여 있으면, 부모 페이지 트리도 함께 전달
		for(int i = 0; true ; i++ ) {
			parentPortalVO = groupPortalService.getParentPortal(parentPortalVO);
			if(parentPortalVO == null) {
				mav.addObject("parentPortalCount",0);
				break;
			} else {
				treeVO.setPortalId(parentPortalVO.getPortalId());
				tree = treeService.getTree(treeVO);
				mav.addObject("parentTree_"+i, tree);
				mav.addObject("parentTreeId_"+i, treeVO.getTreeId());
				mav.addObject("groupPortal_"+i, parentPortalVO);
				if(parentPortalVO.getParentId().equals("_ROOT_")) {
					mav.addObject("parentPortalCount",i+1);
					break;
				}
			}
		}
		mav.addObject("selectedPortalId", UtilRequest.getString(request, "selectedPortalId"));
		mav.addObject("treeId", treeVO.getTreeId());
		mav.addObject("selectedNodeId", treeVO.getNodeId());
		return mav;
	}
	
	
	/**
	 * 메뉴 포틀릿의 메뉴관리에서 보여지는 페이지 트리 (메뉴선택)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getPageSelector(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(pageSelector);
		
		TreeVO treeVO = new TreeVO();
		GroupPortalVO parentPortalVO = new GroupPortalVO();
		bind(request, parentPortalVO);
		bind(request, treeVO);
		
		treeVO.setTreeId("PORTAL_PAGE");
		
		TreeVO[] tree = treeService.getTree(treeVO);
		mav.addObject("tree", tree);
		mav.addObject("treeId", treeVO.getTreeId());
		
		parentPortalVO = groupPortalService.get(parentPortalVO);
		mav.addObject("currentPortalName", parentPortalVO.getPortalName());
		//부모그룹이 있는지 체크하여 있으면, 부모 페이지 트리도 함께 전달
		for(int i = 0; true ; i++ ) {
			parentPortalVO = groupPortalService.getParentPortal(parentPortalVO);
			if(parentPortalVO == null) {
				mav.addObject("parentPortalCount",0);
				break;
			} else {
				treeVO.setPortalId(parentPortalVO.getPortalId());
				tree = treeService.getTree(treeVO);
				mav.addObject("parentTree_"+i, tree);
				mav.addObject("parentTreeId_"+i, treeVO.getTreeId());
				mav.addObject("groupPortal_"+i, parentPortalVO);
				if(parentPortalVO.getParentId().equals("_ROOT_")) {
					mav.addObject("parentPortalCount",i+1);
					break;
				}
			}
		}
		return mav;
	}

	
	/**
	 * 개인화 메뉴 선택화면
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getPersonalPageSelector(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserProfileVO userProfile = (UserProfileVO)request.getSession().getAttribute("userProfile");
		if(userProfile==null) {
			throw new Exception("Invalid Access - You need to login to access this page!!");
		}
		
		PortalMenuVO portalMenuVO = new PortalMenuVO();
		bind(request, portalMenuVO);
		
		List list = portalMenuService.getMenus(portalMenuVO);

		PortalMenuVO[] menus = new PortalMenuVO[list.size()];
		list.toArray(menus);

		ModelAndView mav = new ModelAndView(personalPageSelector);
		mav.addObject("menus", menus);
		return mav;
	}


	/**
	 * 페이지 관리에서 트리에서 노드 클릭해서 페이지 호출 
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getSetupPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageVO paramVO = new PortalPageVO();
		bind(request, paramVO);
		
		PortalPageVO resultVO = portalPageService.getPageForAdmin(paramVO);
		
		if(resultVO==null) {
			throw new Exception("Fail to get page!!");
		}
		
		ModelAndView mav = new ModelAndView(pageSetup);
		mav.addObject("page", resultVO);
		return mav;
	}


	public ModelAndView getPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageVO paramVO = new PortalPageVO();
		bind(request, paramVO);
		
		PortalPageVO resultVO = portalPageService.getPage(paramVO);
		
		if(resultVO==null) {
			throw new Exception("Fail to get page!!");
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", resultVO);
		return mav;
	}
	
	
	/**
	 * 포탈 페이지 로딩 (포탈에서 보여지는 모든 페이지는 이 메소드를 통해 호출)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getPortalPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageVO paramVO = new PortalPageVO();
		//iframe을 이욯안 sub페이지 호출시 사용
		PortalPageVO bodyPage = new PortalPageVO();
		PortalPageVO contentPage = new PortalPageVO();
		
		bind(request, paramVO);
		bind(request, bodyPage);
		bind(request, contentPage);
		
		bodyPage.setPageId(UtilRequest.getString(request, "bodyPage"));
		contentPage.setPageId(UtilRequest.getString(request, "contentPage"));
		
		PortalPageVO resultVO = portalPageService.getPage(paramVO);
		
		if(resultVO==null) {
			throw new Exception("No page found!!");
		}
		
		String userLayoutKey = ConstantList.US_CODE_LAYOUT+"_"+paramVO.getPageId();
		String userLayoutId = (String)request.getSession().getAttribute(userLayoutKey);
		String userThemeId = (String)request.getSession().getAttribute(ConstantList.US_CODE_THEME);
		
		// 레이아웃 설정
		PortalPageLayoutVO layoutVO = new PortalPageLayoutVO();
		layoutVO.setTenantId(resultVO.getTenantId());
		layoutVO.setPortalId(resultVO.getPortalId());

		// 사용자 설정 레이아웃이 있을 경우 사용자 설정 레이아웃으로 없을 경우에는 기본으로 셋팅
		if(CommonUtil.isNullOrEmpty(userLayoutId) || !resultVO.getPortalId().equals(paramVO.getPortalId())) {
			layoutVO.setLayoutId(resultVO.getPageLayoutId());
		} else {
			layoutVO.setLayoutId(userLayoutId);
		}
		layoutVO = portalPageLayoutService.getPageLayout(layoutVO);
		
		// 테마 설정
		PortalPageThemeVO themeVO = new PortalPageThemeVO();
		themeVO.setTenantId(resultVO.getTenantId());
		themeVO.setPortalId(resultVO.getPortalId());
		

		// 사용자 설정 테마가 있을 경우 사용자 설정 테마로 없을 경우에는 기본으로 셋팅
		if(userThemeId==null || !resultVO.getPortalId().equals(paramVO.getPortalId()) 
				|| userThemeId.indexOf(paramVO.getPortalId()) < 0) {
			themeVO.setThemeId(resultVO.getPageThemeId());
		} else {
			themeVO.setThemeId(userThemeId);
		}

		themeVO = portalPageThemeService.getPageTheme(themeVO);
		
		ModelAndView mav = new ModelAndView(index);
		
		if(!bodyPage.getPageId().equals("")) {
			bodyPage = portalPageService.getPage(bodyPage);
			if(bodyPage==null) {
				throw new Exception("No page found!!");
			}
			mav.addObject("bodyPage", bodyPage);
		} 
		if(!contentPage.getPageId().equals("")) {
			contentPage = portalPageService.getPage(contentPage);
			if(contentPage==null) {
				throw new Exception("No page found!!");
			}
			mav.addObject("contentPage",contentPage);
		}
	
		mav.addObject("page", resultVO);
		mav.addObject("layout", layoutVO);
		mav.addObject("theme", themeVO);
		return mav;
	}
	
	/**
     * 포탈 페이지 로딩 (포탈에서 보여지는 모든 페이지는 이 메소드를 통해 호출)
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView getMainPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
      ModelAndView mav = new ModelAndView("/bics_index.jsp");
      /*ModelAndView mav = new ModelAndView("/index.jsp");*/
        return mav;
    }
	
	/**
	 * preview pageloading (페이지관리에서 프리뷰 페이지 로딩)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getPreviewPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageVO paramVO = new PortalPageVO();
		bind(request, paramVO);
		String contentId = (String)UtilRequest.getString(request, "contentId");
		String themeImageUrl = (String)UtilRequest.getString(request, "themeImageUrl");
		
		PortalPageVO resultVO = portalPageService.getPage(paramVO);
		
		if(resultVO==null) {
			throw new Exception("No page found!!");
		}
		
		String userThemeId = (String)request.getSession().getAttribute(ConstantList.US_CODE_THEME);
		
		// 테마 설정
		PortalPageThemeVO themeVO = new PortalPageThemeVO();
		themeVO.setTenantId(resultVO.getTenantId());
		themeVO.setPortalId(resultVO.getPortalId());
		// 사용자 설정 테마가 있을 경우 사용자 설정 테마로 없을 경우에는 기본으로 셋팅
		if(userThemeId==null) {
			themeVO.setThemeId(resultVO.getPageThemeId());
		} else {
			themeVO.setThemeId(userThemeId);
		}
		themeVO = portalPageThemeService.getPageTheme(themeVO);
		
		
		ModelAndView mav = new ModelAndView(previewIndex);
		
		mav.addObject("page", resultVO);
		mav.addObject("theme", themeVO);
		mav.addObject("contentId", contentId);
		mav.addObject("themeImageUrl", themeImageUrl);
		return mav;
	}


	/**
	 * 메인 페이지의 트리에서 노드 선택시 Ajax로 호출되어 우측 리스트를 가져옴
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	public ModelAndView getPageList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(pageList);
		PortalPageVO paramVO = new PortalPageVO();
		bind(request, paramVO);
		
		paramVO.setcPage(1);
		paramVO.setPageSize(10);
		
		Page page = portalPageService.getPageList(paramVO);
		
		if(page==null) {
			throw new Exception("목록 조회 실패");
		}
		
		int totalCnt = page.getTotalCount();
		List<PortalPageVO> list = (List<PortalPageVO>)page.getList();
		
		PortalPageVO[] pages = new PortalPageVO[list.size()];
		list.toArray(pages);
		
		mav.addObject("totalCnt", totalCnt);
		mav.addObject("pages", pages);
		return mav;
	}
	
	/**
	 * 타겟설정된 페이지 리턴
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getTargetedPages(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalMenuVO paramVO = new PortalMenuVO();
		PortalPageVO pageVO = new PortalPageVO();
		PortalPageVO pageResultVO = new PortalPageVO();
		PortalContentVO contentVO = new PortalContentVO();
		bind(request, pageVO);
		bind(request, paramVO);
		bind(request, contentVO);
		
		List list = portalMenuService.getTargetedPages(paramVO);
		PortalMenuVO[] menus = new PortalMenuVO[list.size()];
		list.toArray(menus);
		for(int i=0; i < menus.length; i++) {
			contentVO.setContentId(menus[i].getContentId());
			contentVO.setPortalId(paramVO.getPortalId());
			contentVO = portalContentService.getContent(contentVO);
			menus[i].setMessageId(contentVO.getMessageName());
			if(!menus[i].getPageId().equals("ALL")) {
				pageVO.setPageId(menus[i].getPageId());
				pageResultVO = portalPageService.getPage(pageVO);
				menus[i].setMessageName(pageResultVO.getMessageName());
			} else {
				menus[i].setMessageName(menus[i].getPageId());
			}
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", menus);
		return mav;
	}
}
