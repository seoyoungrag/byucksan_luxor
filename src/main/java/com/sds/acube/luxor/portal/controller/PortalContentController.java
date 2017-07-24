package com.sds.acube.luxor.portal.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import org.anyframe.pagination.Page;
import com.sds.acube.luxor.common.service.TreeService;
import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.util.UtilRequest;
import com.sds.acube.luxor.common.vo.TreeVO;
import com.sds.acube.luxor.common.vo.UserProfileVO;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.controller.BaseController;
import com.sds.acube.luxor.portal.service.GroupPortalService;
import com.sds.acube.luxor.portal.service.PortalContentService;
import com.sds.acube.luxor.portal.service.PortalPageLayoutService;
import com.sds.acube.luxor.portal.service.PortalPageService;
import com.sds.acube.luxor.portal.service.PortletManagementService;
import com.sds.acube.luxor.portal.vo.GroupPortalVO;
import com.sds.acube.luxor.portal.vo.PortalContentVO;
import com.sds.acube.luxor.portal.vo.PortalPageLayoutVO;
import com.sds.acube.luxor.portal.vo.PortalPageVO;
import com.sds.acube.luxor.portal.vo.PortletCategoryVO;
import com.sds.acube.luxor.portal.vo.PortletManagementVO;


public class PortalContentController extends BaseController {
	private PortalContentService portalContentService;
	private PortletManagementService portletManagementService;
	private PortalPageLayoutService portalPageLayoutService;
	private GroupPortalService groupPortalService;
	private PortalPageService portalPageService;
	private TreeService treeService;
	private String contentList;
	private String contentManager;
	private String contentSelector;
	private String contentListSimple;
	private String contentsSelector;

	public void setGroupPortalService(GroupPortalService groupPortalService) {
		this.groupPortalService = groupPortalService;
	}
	public void setPortalPageService(PortalPageService portalPageService) {
    	this.portalPageService = portalPageService;
    }
	public void setPortletManagementService(PortletManagementService portletManagementService) {
    	this.portletManagementService = portletManagementService;
    }
	public void setContentListSimple(String contentListSimple) {
    	this.contentListSimple = contentListSimple;
    }
	public void setContentManager(String contentManager) {
    	this.contentManager = contentManager;
    }
	public void setContentSelector(String contentSelector) {
    	this.contentSelector = contentSelector;
    }
	
	public void setContentsSelector(String contentsSelector){
		this.contentsSelector = contentsSelector;
	}
	
	public void setPortalContentService(PortalContentService portalContentService) {
		this.portalContentService = portalContentService;
	}
	public void setTreeService(TreeService treeService) {
		this.treeService = treeService;
	}
	public void setContentList(String contentList) {
    	this.contentList = contentList;
    }
	public void setPortalPageLayoutService(PortalPageLayoutService portalPageLayoutService) {
    	this.portalPageLayoutService = portalPageLayoutService;
    }
	
	
	public ModelAndView insertContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalContentVO paramVO = new PortalContentVO();
		bind(request, paramVO);
		
		paramVO.setContentId(CommonUtil.generateId("C"));
		
		adminCheck(request);
		
		boolean result = portalContentService.insertContent(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}
	
	
	public ModelAndView updateContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalContentVO paramVO = new PortalContentVO();
		bind(request, paramVO);

		// 수정자 UID 셋팅
		UserProfileVO userProfile = (UserProfileVO)WebUtils.getSessionAttribute(request, "userProfile");
		String userId = userProfile.getUserUid();
		paramVO.setModUserId(userId);
		
		boolean result = portalContentService.updateContent(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	
	
	public ModelAndView updateContentParentId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalContentVO paramVO = new PortalContentVO();
		bind(request, paramVO);
		
		boolean result = portalContentService.updateContentParentId(paramVO);
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}

	
	public ModelAndView deleteContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalContentVO paramVO = new PortalContentVO();
		bind(request, paramVO);
		boolean result = portalContentService.deleteContent(paramVO);
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}


	/**
	 * 컨텐츠 관리 메인 페이지 호출
	 * 초기 호출시에 트리만 보내주고 트리에서 노드 선택하여 리스트 정보를 Ajax로 가져옴
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getIndexPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		TreeVO treeVO = new TreeVO();
		bind(request, treeVO);
		GroupPortalVO parentPortalVO = new GroupPortalVO();
		bind(request, parentPortalVO);
		
		// 포틀릿 카테고리 정보
		PortletManagementVO catgParam = new PortletManagementVO();
		catgParam.setTenantId(treeVO.getTenantId());
		catgParam.setPortalId(treeVO.getPortalId());
		catgParam.setLangType(treeVO.getLangType());
		PortletCategoryVO[] portletCategories = portletManagementService.getPortletCategoryList(catgParam);
		
		// 레이아웃 목록
		PortalPageLayoutVO layoutVO = new PortalPageLayoutVO();
		bind(request, layoutVO);
		
		Page page = portalPageLayoutService.getPageLayoutList(layoutVO);
		List list = (List)page.getList();
		PortalPageLayoutVO[] layouts = new PortalPageLayoutVO[list.size()];
		list.toArray(layouts);
		
		// 개인화 페이지 템플릿 목록
		PortalPageVO pageVO = new PortalPageVO();
		bind(request, pageVO);
		
		List templateList = portalPageService.getTemplatePages(pageVO);
		PortalPageVO[] templates = new PortalPageVO[templateList.size()];
		templateList.toArray(templates);
		
		ModelAndView mav = new ModelAndView(contentManager);
		if(UtilRequest.getString(request,"type")!=null && "contentSelector".equals(UtilRequest.getString(request,"type"))) {
			mav = new ModelAndView(contentSelector);
		}else if(UtilRequest.getString(request,"type")!=null && "contentsSelector".equals(UtilRequest.getString(request,"type"))) {
			mav = new ModelAndView(contentsSelector);
		}
		
		treeVO.setTreeId("PORTAL_CONTENT");
		TreeVO[] tree = treeService.getTree(treeVO);
		mav.addObject("tree", tree);
		mav.addObject("treeId", treeVO.getTreeId());
		mav.addObject("contentId", UtilRequest.getString(request,"contentId"));
		mav.addObject("typeOfPortlet", UtilRequest.getString(request,"typeOfPortlet"));

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
		
		TreeVO contentTreeVO = new TreeVO();
		TreeVO pageTreeVO = new TreeVO();
		bind(request, contentTreeVO);
		bind(request, pageTreeVO);
		
		pageTreeVO.setTreeId("PORTAL_PAGE");
		contentTreeVO.setTreeId("PORTAL_CONTENT");
		TreeVO[] contentTree = treeService.getTree(contentTreeVO);
		TreeVO[] pageTree = treeService.getTree(pageTreeVO);
		mav.addObject("contentTree", contentTree);
		mav.addObject("pageTree", pageTree);
		
		mav.addObject("templates", templates);
		mav.addObject("layouts", layouts);
	
		mav.addObject("portletCategories", portletCategories);
		return mav;
	}
	
	
	/**
	 * 컨텐츠 박스를 그릴때 호출됨
	 *  
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalContentVO paramVO = new PortalContentVO();
		bind(request, paramVO);
		
		PortalContentVO content = portalContentService.getContent(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", content);
		return mav;
	}

	
	/**
	 * 컨텐츠 관리 메인 페이지의 트리에서 노드 선택시 Ajax로 호출되어 우측 리스트를 가져옴
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getContentList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(contentList);
		
		PortalContentVO paramVO = new PortalContentVO();
		PortalContentVO parentSearchParamVO = new PortalContentVO();
		bind(request, paramVO);
		bind(request, parentSearchParamVO);
		
		//검색조건 설정
		String searchKey = paramVO.getSearchKey().equals("") ? "%%" : "%"+paramVO.getSearchKey()+"%";
		paramVO.setSearchKey(searchKey);
		
		paramVO.setPageSize(LuxorConfig.getEnvInt(request, "PAGE_LIST_COUNT"));
		
		
		//부모포탈의 카테고리를 선택하였을 경우
		String parentPortalId = UtilRequest.getString(request, "parentPortalId");
		Page page = portalContentService.getContentListPage(paramVO);
		if(!parentPortalId.equals("")) {
			parentSearchParamVO.setPortalId(parentPortalId);
			page = portalContentService.getContentListPage(parentSearchParamVO);
			mav.addObject("isParentPortal", parentPortalId);
		} 
		
		if(page==null) {
			throw new Exception("목록 조회 실패");
		}
		
		int cPage = page.getCurrentPage();
		int totalCount = page.getTotalCount();
		List<PortalContentVO> list = (List<PortalContentVO>)page.getList();
		
		PortalContentVO[] contents = new PortalContentVO[list.size()];
		list.toArray(contents);
		
		
		mav.addObject("cPage", cPage);
		mav.addObject("totalCount", totalCount);
		mav.addObject("contents", contents);
		return mav;
	}

	
	/**
	 * 페이지 관리에서 컨텐츠 추가시 보여지는 심플 컨텐츠 목록
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getContentListSimple(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalContentVO paramVO = new PortalContentVO();
		bind(request, paramVO);
		String usePersonal = UtilRequest.getString(request,"usePersonal","N");
		List<PortalContentVO> list = portalContentService.getContentList(paramVO);
		
		if(list==null) {
			throw new Exception("목록 조회 실패");
		}
		
		PortalContentVO[] contents = new PortalContentVO[list.size()];
		list.toArray(contents);
		
		ModelAndView mav = new ModelAndView(contentListSimple);
		mav.addObject("usePersonal", usePersonal);
		mav.addObject("contents", contents);
		mav.addObject("inputContents", paramVO);
		
		return mav;
	}
	
	/**
	 * 컨텐츠 카테고리중 퍼스널 카테고리로 사용할 것을 세팅
	 *  
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView setCategoryUsePersonal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		TreeVO paramVO = new TreeVO();
		bind(request, paramVO);
		boolean result = portalContentService.setCategoryUsePersonal(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}

	/**
	 * 컨텐츠 중 퍼스널 컨텐츠로 사용할 것을 세팅
	 *  
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView setContentUsePersonal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalContentVO paramVO = new PortalContentVO();
		bind(request, paramVO);
		
		boolean result = portalContentService.setContentUsePersonal(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}

	/**
	 * 컨텐츠 카테고리개인화 조회
	 *  
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getPersonalCatalog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		TreeVO treeVO = new TreeVO();
		bind(request, treeVO);
		treeVO.setTreeId("PORTAL_CONTENT");
		
		List<TreeVO> treeList = portalContentService.getPersonalCatalog(treeVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", treeList);
		return mav;
	}

}
