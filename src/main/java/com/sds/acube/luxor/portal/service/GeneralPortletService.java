package com.sds.acube.luxor.portal.service;

import java.util.List;
import com.sds.acube.luxor.portal.vo.BookmarkPortletVO;
import com.sds.acube.luxor.portal.vo.MemoPortletVO;
import com.sds.acube.luxor.portal.vo.TabPortletVO;
import com.sds.acube.luxor.portal.vo.ImgPortletVO;

public interface GeneralPortletService {
	public MemoPortletVO getMemo(MemoPortletVO vo) throws Exception;
	public boolean updateMemo(MemoPortletVO vo) throws Exception;
	public boolean insertMemo(MemoPortletVO vo) throws Exception;
	public boolean deleteMemo(MemoPortletVO vo) throws Exception;
	public List<MemoPortletVO> getMemoList(MemoPortletVO vo) throws Exception;
	
	public List<BookmarkPortletVO> getBookmarkListInCategory(BookmarkPortletVO vo) throws Exception;
	public List<BookmarkPortletVO> getBookmarkCategory(BookmarkPortletVO vo) throws Exception;
	public BookmarkPortletVO getBookmark(BookmarkPortletVO vo) throws Exception;
	public boolean insertBookmarkData(BookmarkPortletVO vo) throws Exception;
	public boolean updateBookmarkData(BookmarkPortletVO vo) throws Exception;
	public boolean deleteBookmarkCategory(BookmarkPortletVO vo) throws Exception;
	public boolean deleteBookmark(BookmarkPortletVO vo) throws Exception;
	public boolean updateCategoryInit(BookmarkPortletVO vo) throws Exception;
	
	public boolean insertTabContents(TabPortletVO vo) throws Exception;
	public boolean deleteTabContents(TabPortletVO vo) throws Exception;	
	public boolean updateTabContents(TabPortletVO vo) throws Exception;
	public List<TabPortletVO> getTabContentsList(TabPortletVO vo) throws Exception;
	public TabPortletVO getTabContents(TabPortletVO vo) throws Exception;
	
	public List<ImgPortletVO> getImgContentsList(ImgPortletVO vo) throws Exception;

	
}