package com.sds.acube.luxor.portal.service.impl;

import java.util.List;
import com.sds.acube.luxor.framework.service.BaseService;
import com.sds.acube.luxor.portal.context.PortletXMLLoader;
import com.sds.acube.luxor.portal.dao.GeneralPortletDAO;
import com.sds.acube.luxor.portal.service.GeneralPortletService;
import com.sds.acube.luxor.portal.vo.BookmarkPortletVO;
import com.sds.acube.luxor.portal.vo.MemoPortletVO;
import com.sds.acube.luxor.portal.vo.PortletContextVO;
import com.sds.acube.luxor.portal.vo.TabPortletVO;
import com.sds.acube.luxor.portal.vo.ImgPortletVO;


public class GeneralPortletServiceImpl extends BaseService implements GeneralPortletService{
	private GeneralPortletDAO generalPortletDAO;
	
	public void setGeneralPortletDAO(GeneralPortletDAO generalPortletDAO) {
    	this.generalPortletDAO = generalPortletDAO;
    }

	public MemoPortletVO getMemo(MemoPortletVO vo) throws Exception {
		MemoPortletVO result = generalPortletDAO.getMemo(vo);
	    return result;
    }
	
	public List<MemoPortletVO> getMemoList(MemoPortletVO vo) throws Exception {
		List<MemoPortletVO> result = generalPortletDAO.getMemoList(vo);
	    return result;
    }
	
	public boolean deleteMemo(MemoPortletVO vo) throws Exception {
		boolean result = generalPortletDAO.deleteMemo(vo);
		if(result==false) {
			throw new Exception("Fail to deleteMemo!!");
		}
	    return result;
    }
	
	public boolean insertMemo(MemoPortletVO vo) throws Exception {
		boolean result = generalPortletDAO.insertMemo(vo);
		if(result==false) {
			throw new Exception("Fail to insertMemo!!");
		}
	    return result;
    }
	
	public boolean updateMemo(MemoPortletVO vo) throws Exception {
		boolean result = generalPortletDAO.updateMemo(vo);
		if(result==false) {
			throw new Exception("Fail to updateMemo!!");
		}
	    return result;
    }
	
	public BookmarkPortletVO getBookmark(BookmarkPortletVO vo) throws Exception {
		BookmarkPortletVO result = generalPortletDAO.getBookmark(vo);
	    return result;
	}

	public List<BookmarkPortletVO> getBookmarkCategory(BookmarkPortletVO vo) throws Exception {
		List<BookmarkPortletVO> result = generalPortletDAO.getBookmarkCategory(vo);
	    return result;
	}

	public List<BookmarkPortletVO> getBookmarkListInCategory(BookmarkPortletVO vo) throws Exception {
		List<BookmarkPortletVO> result = generalPortletDAO.getBookmarkListInCategory(vo);
	    return result;
    }

	public boolean insertBookmarkData(BookmarkPortletVO vo) throws Exception {
		boolean result = generalPortletDAO.insertBookmarkData(vo);
		if(result==false) {
			throw new Exception("Fail to insertBookmarkData!!");
		}
	    return result;
    }

	public boolean updateBookmarkData(BookmarkPortletVO vo) throws Exception {
		boolean result = generalPortletDAO.updateBookmarkData(vo);
		if(result==false) {
			throw new Exception("Fail to updateBookmarkData!!");
		}
	    return result;
    }
	public boolean deleteBookmark(BookmarkPortletVO vo) throws Exception {
		boolean result = generalPortletDAO.deleteBookmark(vo);
		if(result==false) {
			throw new Exception("Fail to deleteBookmark!!");
		}
	    return result;
    }

	public boolean deleteBookmarkCategory(BookmarkPortletVO vo) throws Exception {
		boolean result = generalPortletDAO.deleteBookmarkCategory(vo);
		if(result==false) {
			throw new Exception("Fail to deleteBookmarkCategory!!");
		}
	    return result;
    }

	public boolean updateCategoryInit(BookmarkPortletVO vo) throws Exception {
		boolean result = generalPortletDAO.updateCategoryInit(vo);
		if(result==false) {
			throw new Exception("Fail to updateCategoryInit!!");
		}
	    return false;
    }
	
	public boolean deleteTabContents(TabPortletVO vo) throws Exception {
		boolean result = generalPortletDAO.deleteTabContents(vo);
		if(result==false) {
			throw new Exception("Fail to delete contents!!");
		}
	    return result;
    }
	
	public boolean insertTabContents(TabPortletVO vo) throws Exception {

		int seq = generalPortletDAO.getMaxSequence(vo);		
		vo.setSeq(seq+1);			
		boolean result = generalPortletDAO.insertTabContents(vo);
		if(result==false) {
			throw new Exception("Fail to insert contents!!");
		}
	    return result;
    }	
	
	public boolean updateTabContents(TabPortletVO vo) throws Exception {
		boolean result = generalPortletDAO.updateTabContents(vo);
		if(result==false) {
			throw new Exception("Fail to update contents!!");
		}
	    return false;
    }
	
	public List<TabPortletVO> getTabContentsList(TabPortletVO vo) throws Exception {
		List<TabPortletVO> result = generalPortletDAO.getTabContentsList(vo);
	    return result;
    }	
	

	public TabPortletVO getTabContents(TabPortletVO vo) throws Exception {
	
		TabPortletVO result = generalPortletDAO.getTabContents(vo);
	    return result;
    }	
	
	public List<ImgPortletVO> getImgContentsList(ImgPortletVO vo) throws Exception {
		List<ImgPortletVO> result = generalPortletDAO.getImgContentsList(vo);
		
		for(int i=0; result != null && i<result.size();i++){
			PortletContextVO context = null;
			context = (PortletContextVO)PortletXMLLoader.load(result.get(i).getDeployDescriptionXml()).get(0);
		
			result.get(i).setPortletContextVO(context);
		}

	    return result;
    }	
		
}