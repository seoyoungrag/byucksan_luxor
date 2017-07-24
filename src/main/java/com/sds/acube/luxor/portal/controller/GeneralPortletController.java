package com.sds.acube.luxor.portal.controller;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import com.sds.acube.luxor.common.service.AccessControllService;
import com.sds.acube.luxor.common.util.UtilRequest;
import com.sds.acube.luxor.framework.controller.BaseController;
import com.sds.acube.luxor.portal.context.PortletContextRegistry;
import com.sds.acube.luxor.portal.service.GeneralPortletService;
import com.sds.acube.luxor.portal.vo.BookmarkPortletVO;
import com.sds.acube.luxor.portal.vo.MemoPortletVO;
import com.sds.acube.luxor.portal.vo.TabPortletVO;
import com.sds.acube.luxor.portal.vo.PortletContextVO;
import com.sds.acube.luxor.portal.vo.ImgPortletVO;


/**
 * @author JSSUH
 *
 */
public class GeneralPortletController extends BaseController {
	private GeneralPortletService generalPortletService;
	private AccessControllService accessControllService;


	/**
	 * @param generalPortletService
	 */
	public void setGeneralPortletService(GeneralPortletService generalPortletService) {
		this.generalPortletService = generalPortletService;
	}

	public void setAccessControllService(AccessControllService accessControllService) {
		this.accessControllService = accessControllService;
	}
	
	/**
	 * getMemo 
	 * 메모 불러오기
	 * @param request : (key parameter = userId, memoId)
	 * @param response
	 * @return : MemoPortletVO
	 * @throws Exception
	 */
	public ModelAndView getMemo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("getMemo called...");
		ModelAndView mav = new ModelAndView();
		MemoPortletVO param = new MemoPortletVO();
		bind(request, param);
		//TODO controller단에서 <br> -> /n 제거해야함
		param = generalPortletService.getMemo(param) == null ? new MemoPortletVO() : generalPortletService.getMemo(param);
		if(param.getMemoValue()!=null) {
			param.setMemoValue(param.getMemoValue().replaceAll("<br>", "\n"));
		}
		mav.addObject("jsonResult", param);
		return mav;
	}


	/**
	 * getMemoList
	 * 해당사용자  메모list 불러오기
	 * @param request : (key parameter = userId)
	 * @param response 
	 * @return : List(MemoportletVO)
	 * @throws Exception
	 */
	public ModelAndView getMemoList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("getMemoList called...");
		ModelAndView mav = new ModelAndView();
		MemoPortletVO param = new MemoPortletVO();
		bind(request, param);
		param = generalPortletService.getMemo(param);
		mav.addObject("jsonResult", param);
		return mav;
	}


	/**
	 * updateMemo
	 * 메모 업데이트
	 * @param request : (key parameter = memoId, userId)
	 * @param response
	 * @return : boolean
	 * @throws Exception
	 */
	public ModelAndView updateMemo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("updateMemo called...");
		ModelAndView mav = new ModelAndView();
		MemoPortletVO param = new MemoPortletVO();
		bind(request, param);
		boolean result = generalPortletService.updateMemo(param);
		mav.addObject("jsonResult", result);
		return mav;
	}


	/**
	 * deleteMemo
	 * 메모 삭제 
	 * @param request : (key parameter = memoId, userId)
	 * @param response
	 * @return : boolean
	 * @throws Exception
	 */
	public ModelAndView deleteMemo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("deleteMemo called...");
		ModelAndView mav = new ModelAndView();
		MemoPortletVO param = new MemoPortletVO();
		bind(request, param);
		boolean result = generalPortletService.deleteMemo(param);
		mav.addObject("jsonResult", result);
		return mav;
	}


	/**
	 * insertMemo
	 * 메모 입력
	 * @param request : (key parameter = memoId, userId)
	 * @param response
	 * @return : boolean
	 * @throws Exception
	 */
	public ModelAndView insertMemo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("insertMemo called...");
		ModelAndView mav = new ModelAndView();
		MemoPortletVO param = new MemoPortletVO();
		bind(request, param);
		boolean result = generalPortletService.insertMemo(param);
		mav.addObject("jsonResult", result);
		return mav;
	}


	/**
	 * getBookmarkCategory
	 * 북마크 카테고리  불러오기
	 * @param request : (key parameter = userId, parentId="CATEGORY")
	 * @param response
	 * @return : BookmarkPortletVO
	 * @throws Exception
	 */
	public ModelAndView getBookmarkCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("getBookmarkCategory called...");
		ModelAndView mav = new ModelAndView();
		BookmarkPortletVO param = new BookmarkPortletVO();
		bind(request, param);
		List<BookmarkPortletVO> list = generalPortletService.getBookmarkCategory(param);
		mav.addObject("jsonResult", list);
		return mav;
	}


	/**
	 * getBookmarkListInCategory
	 * 카테고리클릭시 카태고리 내의 북마크 불러오기
	 * @param request : (key parameter = userId, parentId)
	 * @param response
	 * @return : BookmarkPortletVO
	 * @throws Exception
	 */
	public ModelAndView getBookmarkListInCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("getBookmarkListInCategory called...");
		ModelAndView mav = new ModelAndView();
		BookmarkPortletVO param = new BookmarkPortletVO();
		bind(request, param);
		List<BookmarkPortletVO> list = generalPortletService.getBookmarkListInCategory(param);
		mav.addObject("jsonResult", list);
		return mav;
	}

	
	/**
	 * getBookmark
	 * 북마크 정보 불러오기
	 * @param request : (key parameter = userId, bookmarkId)
	 * @param response
	 * @return : BookmarkPortletVO
	 * @throws Exception
	 */
	public ModelAndView getBookmark(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("getBookmark called...");
		ModelAndView mav = new ModelAndView();
		BookmarkPortletVO param = new BookmarkPortletVO();
		bind(request, param);
		BookmarkPortletVO result = generalPortletService.getBookmark(param);
		mav.addObject("jsonResult", result);
		return mav;
	}
	
	
	/**
	 * insertBookmarkData
	 * 묵마크 데이터 컬럼 생성(카테고리+북마크)
	 * @param request : (key parameter = userId, bookmarkId)
	 * @param response
	 * @return : boolean
	 * @throws Exception
	 */
	public ModelAndView insertBookmarkData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("insertMemo called...");
		ModelAndView mav = new ModelAndView();
		BookmarkPortletVO param = new BookmarkPortletVO();
		bind(request, param);
		boolean result = generalPortletService.insertBookmarkData(param);
		mav.addObject("jsonResult", result);
		return mav;
	}


	/**
	 * updateBookmarkData
	 * 북마크 데이터 컬럼 업데이트(카테고리+북마크)
	 * @param request : (key parameter = userId, bookmarkId)
	 * @param response
	 * @return : boolean
	 * @throws Exception
	 */
	public ModelAndView updateBookmarkData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("insertMemo called...");
		ModelAndView mav = new ModelAndView();
		BookmarkPortletVO param = new BookmarkPortletVO();
		bind(request, param);
		boolean result = generalPortletService.updateBookmarkData(param);
		mav.addObject("jsonResult", result);
		return mav;
	}


	/**
	 * deleteBookmarkCategory
	 * 북마크 카테고리 삭제
	 * @param request : (key parameter = userId, bookmarkId, parentId)
	 * @param response
	 * @return : boolean
	 * @throws Exception
	 */
	public ModelAndView deleteBookmarkCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("insertMemo called...");
		ModelAndView mav = new ModelAndView();
		BookmarkPortletVO param = new BookmarkPortletVO();
		bind(request, param);
		boolean result = generalPortletService.deleteBookmarkCategory(param);
		mav.addObject("jsonResult", result);
		return mav;
	}


	/**
	 * deleteBookmark
	 * 북마크 삭제
	 * @param request : (key parameter = userId, bookmarkId)
	 * @param response
	 * @return : boolean
	 * @throws Exception
	 */
	public ModelAndView deleteBookmark(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("insertMemo called...");
		ModelAndView mav = new ModelAndView();
		BookmarkPortletVO param = new BookmarkPortletVO();
		bind(request, param);
		boolean result = generalPortletService.deleteBookmark(param);
		mav.addObject("jsonResult", result);
		return mav;
	}
	
	/**
	 * updateCategoryInit
	 * 초기화면 카테고리 설정
	 * @param request : (key parameter = userId, bookmarkId)
	 * @param response
	 * @return : boolean
	 * @throws Exception
	 */
	public ModelAndView updateCategoryInit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("updateCategoryInit called...");
		ModelAndView mav = new ModelAndView();
		BookmarkPortletVO param = new BookmarkPortletVO();
		bind(request, param);
		boolean result = generalPortletService.updateCategoryInit(param);
		mav.addObject("jsonResult", result);
		return mav;
	}
	
	/**
	 * deleteTabContents
	 * 탭포틀릿에서 컨텐츠 삭제 
	 * @param request : (key parameter = memoId, userId)
	 * @param response
	 * @return : boolean
	 * @throws Exception
	 */
	public ModelAndView deleteTabContents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("deleteTabContents called...");
		ModelAndView mav = new ModelAndView();
		TabPortletVO param = new TabPortletVO();
		bind(request, param);
		boolean result = generalPortletService.deleteTabContents(param);
		mav.addObject("jsonResult", result);
		return mav;
	}


	/**
	 * insertTabContents
	 * 탭포틀릿에서 컨텐츠 등록 
	 * @param request : (key parameter = memoId, userId)
	 * @param response
	 * @return : boolean
	 * @throws Exception
	 */
	public ModelAndView insertTabContents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("insertTabContents called...");
		ModelAndView mav = new ModelAndView();
		TabPortletVO param = new TabPortletVO();
		bind(request, param);
		boolean result = generalPortletService.insertTabContents(param);
		mav.addObject("jsonResult", result);
		return mav;
	}
	
	/**
	 * updateTabContents
	 * 탭포틀릿에서 컨텐츠 수정 
	 * @param request : (key parameter = memoId, userId)
	 * @param response
	 * @return : boolean
	 * @throws Exception
	 */
	public ModelAndView updateTabContents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("insertTabContents called...");
		ModelAndView mav = new ModelAndView();
		TabPortletVO param = new TabPortletVO();
		bind(request, param);
		boolean result = generalPortletService.updateTabContents(param);
		mav.addObject("jsonResult", result);
		return mav;
	}	
	
	/**
	 * getTabContentsList
	 * 탭포틀릿에서 컨텐츠 목록조회
	 * @param request : (key parameter = tabPortletId)
	 * @param response
	 * @return : boolean
	 * @throws Exception
	 */
	public ModelAndView getTabContentsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("getTabContentsList called...");
		ModelAndView mav = new ModelAndView();
		TabPortletVO param = new TabPortletVO();
		bind(request, param);
		String isSetting = UtilRequest.getString(request,"isSetting","Y");
		List<TabPortletVO> result = generalPortletService.getTabContentsList(param);
		List<TabPortletVO> tabList = new ArrayList<TabPortletVO>();
		
		List accessIdList = (List)request.getSession().getAttribute("ACCESS_ID_LIST");
		
		//권한이 있는 컨텐츠만 조회되도록 한다.
		if("N".equals(isSetting)  && !accessIdList.contains("ADMIN")){
			for(int i=0; i<result.size();i++){
				if(accessControllService.getPermission(result.get(i).getContentId(), (List)request.getSession().getAttribute("ACCESS_ID_LIST")).isReadable()){
					tabList.add(result.get(i));
				}
			}
		}else{
			tabList = result;
		}
		
		mav.addObject("jsonResult", tabList);
		return mav;
	}	
	
	/**
	 * getTabContentsList
	 * 탭포틀릿에서 컨텐츠 목록조회
	 * @param request : (key parameter = tabPortletId)
	 * @param response
	 * @return : boolean
	 * @throws Exception
	 */
	public ModelAndView getTabContents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("getTabContents called...");
		ModelAndView mav = new ModelAndView();
		TabPortletVO param = new TabPortletVO();
		bind(request, param);
		
		TabPortletVO result = generalPortletService.getTabContents(param);		

		mav.addObject("jsonResult", result);
		return mav;
	}		
	
	/**
	 * getTabContentsUrl
	 * 탭포틀릿에서 컨텐츠 url조회
	 * @param request : (key parameter = tabPortletId)
	 * @param response
	 * @return : boolean
	 * @throws Exception
	 */
	public ModelAndView getTabContentsUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("getTabContentsUrl called...");
		ModelAndView mav = new ModelAndView();
		TabPortletVO param = new TabPortletVO();
		bind(request, param);

		TabPortletVO result = generalPortletService.getTabContents(param);

		String portletId = result.getPortletId();
		PortletContextVO portlet = PortletContextRegistry.getPortlet(param.getPortalId(), param.getTenantId(),portletId );

		int portletType = portlet.getTypeOfPortlet();

		portletId = portletType==5 ? "jsr168Portlet" : portletId;
		String url = portlet.getViewUrl();

		url = portlet.getGoUrl();
		if( url.equals("") || url == null ) {
			url = portlet.getViewUrl();
		}

		// URL앞에 슬러쉬(/)가 있으면 제거
		url = url.startsWith("/") ? url.substring(1) : url;
		
		// 서블릿인지 JSP인지 구별
		url = (url.lastIndexOf(".do") > -1) ? url : "/luxor/deploy/"+portletId+"/jsp/"+url;
		
		// Iframe URL 셋팅
		String iframePortletUrl = "/portlet/iframeLoad.do?contextName="+portletId+"&mode=go";		

		// 최종 URL 결정
		url = portletType==1 ? iframePortletUrl : url;
		
		String[] portletInfo = new String[2];
		portletInfo[0] = url;
		portletInfo[1] = portletId;
		mav.addObject("jsonResult", portletInfo);
		return mav;
	}		
	
	/**
	 * getImgContentsList
	 * 탭포틀릿에서 컨텐츠 목록조회
	 * @param request : (key parameter = tabPortletId)
	 * @param response
	 * @return : boolean
	 * @throws Exception
	 */
	public ModelAndView getImgContentsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("getImgContentsList called...");
		ModelAndView mav = new ModelAndView();
		ImgPortletVO param = new ImgPortletVO();
		bind(request, param);
		
		List<ImgPortletVO> result = generalPortletService.getImgContentsList(param);
		List<ImgPortletVO> imgList = new ArrayList<ImgPortletVO>();
		
		String isSetting = UtilRequest.getString(request,"isSetting","Y");
		List accessIdList = (List)request.getSession().getAttribute("ACCESS_ID_LIST");
		

		if("N".equals(isSetting) && !accessIdList.contains("ADMIN")){
			//권한이 있는 컨텐츠만 조회되도록 한다.		
			for(int i=0; i<result.size();i++){
				
				if(accessControllService.getPermission(result.get(i).getContentId(), accessIdList).isReadable()){
					imgList.add(result.get(i));
				}
			}
		}else{
			imgList = result;
		}
		mav.addObject("jsonResult", imgList);
		return mav;
	}		
}
