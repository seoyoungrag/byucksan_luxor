package com.sds.acube.luxor.portal.dao;

import java.util.List;
import org.anyframe.pagination.Page;

import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.vo.TreeVO;
import com.sds.acube.luxor.framework.dao.BaseDAO;
import com.sds.acube.luxor.portal.vo.PortalContentVO;


public class PortalContentDAO extends BaseDAO {
	
	public boolean insertContent(PortalContentVO vo) throws Exception {
		return insert("insertContent", vo) > 0;
	}
	
	public boolean updateContent(PortalContentVO vo) throws Exception {
		return update("updateContent", vo) > 0;
	}

	public boolean updateContentParentId(PortalContentVO vo) throws Exception {
		return update("updateContentParentId", vo) > 0;
	}
	
	public boolean deleteContent(PortalContentVO vo) throws Exception {
		return delete("deleteContent", vo) > 0;
	}
	
	public PortalContentVO getContent(PortalContentVO vo) throws Exception {
		
		vo.setPortalId(CommonUtil.getEscapeString(vo.getPortalId(), dbType));
		PortalContentVO portalContent =  (PortalContentVO)select("getContent", vo);
		vo.setPortalId(CommonUtil.getResetEscapeString(vo.getPortalId()));

		return portalContent;
	}
	
	public List getContentList(PortalContentVO vo) throws Exception {
		
		List result = null;
		
		vo.setSearchKey(CommonUtil.getEscapeString(vo.getSearchKey(),dbType));
		if(vo.getUsePersonal().equals("Y")) {
			result =  selectList("getContentListUsingPersonal", vo);
		} else {
			result =  selectList("getContentList", vo);
		}
		vo.setSearchKey(CommonUtil.getResetEscapeString(vo.getSearchKey()));
		
		return result;
	}

	public Page getContentListPage(PortalContentVO vo) throws Exception {
		
		vo.setSearchKey(CommonUtil.getEscapeString(vo.getSearchKey(),dbType));
		Page result =  selectListPage("getContentList", vo);
		vo.setSearchKey(CommonUtil.getResetEscapeString(vo.getSearchKey()));

		return result;
	}

	public boolean setCategoryUsePersonal(TreeVO vo) throws Exception {
		return update("setCategoryUsePersonal", vo) > 0;
	}
	
	public boolean setContentUsePersonal(PortalContentVO vo) throws Exception {
		return update("setContentUsePersonal", vo) > 0;
	}
	
	public List<TreeVO> getPersonalCatalog(TreeVO vo) throws Exception {
		return selectList("getPersonalCatalog", vo);
	}
	
}
