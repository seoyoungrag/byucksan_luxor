package com.sds.acube.luxor.portal.dao;

import java.util.List;
import com.sds.acube.luxor.framework.dao.BaseDAO;
import com.sds.acube.luxor.portal.vo.BookmarkPortletVO;
import com.sds.acube.luxor.portal.vo.MemoPortletVO;
import com.sds.acube.luxor.portal.vo.TabPortletVO;
import com.sds.acube.luxor.portal.vo.ImgPortletVO;


public class GeneralPortletDAO extends BaseDAO {
	public MemoPortletVO getMemo(MemoPortletVO vo) throws Exception {
		return (MemoPortletVO)select("getMemo", vo);
	}

	public boolean updateMemo(MemoPortletVO vo) throws Exception {
		return update("updateMemo", vo) > 0;
	}

	public boolean insertMemo(MemoPortletVO vo) throws Exception {
		return insert("insertMemo", vo) > 0;
	}

	public boolean deleteMemo(MemoPortletVO vo) throws Exception {
		return delete("deleteMemo", vo) > 0;
	}

	public List<MemoPortletVO> getMemoList(MemoPortletVO param) throws Exception {
		return selectList("getMemoList", param);
	}
	
	public List<BookmarkPortletVO> getBookmarkCategory(BookmarkPortletVO vo) throws Exception {
		return selectList("getBookmarkCategory", vo);
	}

	public List<BookmarkPortletVO> getBookmarkListInCategory(BookmarkPortletVO vo) throws Exception {
		return selectList("getBookmarkListInCategory", vo);
	}
	
	public BookmarkPortletVO getBookmark(BookmarkPortletVO vo) throws Exception {
		return (BookmarkPortletVO)select("getBookmark",vo);
	}
	
	public boolean updateBookmarkData(BookmarkPortletVO vo) throws Exception {
		return update("updateBookmarkData", vo) > 0;
	}

	public boolean insertBookmarkData(BookmarkPortletVO vo) throws Exception {
		return insert("insertBookmarkData", vo) > 0;
	}

	public boolean deleteBookmarkCategory(BookmarkPortletVO vo) throws Exception {
		return delete("deleteBookmarkCategory", vo) > 0;
	}
	
	public boolean deleteBookmark(BookmarkPortletVO vo) throws Exception {
		return delete("deleteBookmark", vo) > 0;
	}
	
	public boolean updateCategoryInit(BookmarkPortletVO vo) throws Exception {
		return update("updateCategoryInit", vo) > 0;
	}
	

	public boolean insertTabContents(TabPortletVO vo) throws Exception {
		return insert("insertTabContents", vo) > 0;
	}

	public boolean deleteTabContents(TabPortletVO vo) throws Exception {
		return delete("deleteTabContents", vo) > 0;
	}	
	
	public boolean updateTabContents(TabPortletVO vo) throws Exception {
		return update("updateTabContents", vo) > 0;
	}	
	
	public List<TabPortletVO> getTabContentsList(TabPortletVO param) throws Exception {
		return selectList("getTabContentsList", param);
	}
		
	public TabPortletVO getTabContents(TabPortletVO vo) throws Exception {
		return (TabPortletVO)select("getTabContents", vo);
	}	
	
	public int getMaxSequence(TabPortletVO vo) throws Exception {
		return getCount("getMaxSequence", "seq", vo);
	}		
	
	public List<ImgPortletVO> getImgContentsList(ImgPortletVO param) throws Exception {
		return selectList("getImgContentsList", param);
	}
}
