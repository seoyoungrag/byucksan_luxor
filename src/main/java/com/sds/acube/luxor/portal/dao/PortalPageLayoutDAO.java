package com.sds.acube.luxor.portal.dao;

import org.anyframe.pagination.Page;

import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.framework.dao.BaseDAO;
import com.sds.acube.luxor.portal.vo.PortalPageLayoutVO;

public class PortalPageLayoutDAO extends BaseDAO {
	
	public boolean insertPageLayout(PortalPageLayoutVO vo) throws Exception {
		return insert("insertPageLayout", vo) > 0;
	}
	
	public boolean updatePageLayout(PortalPageLayoutVO vo) throws Exception {
		return update("updatePageLayout", vo) > 0;
	}

	public boolean resetDefaultPageLayout(PortalPageLayoutVO vo) throws Exception {
		return update("resetDefaultPageLayout", vo) > 0;
	}

	public boolean deletePageLayout(PortalPageLayoutVO vo) throws Exception {
		return delete("deletePageLayout", vo) > 0;
	}
	
	public PortalPageLayoutVO getPageLayout(PortalPageLayoutVO vo) throws Exception {
		vo.setPortalId(CommonUtil.getEscapeString(vo.getPortalId(), dbType));
		PortalPageLayoutVO result = (PortalPageLayoutVO)select("getPageLayout", vo);
		vo.setPortalId(CommonUtil.getResetEscapeString(vo.getPortalId()));
		return result;
	}
	
	public Page getPageLayoutList(PortalPageLayoutVO vo) throws Exception {
		return selectListPage("getPageLayoutList", vo);
	}
	
}
