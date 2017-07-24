package com.sds.acube.luxor.portal.dao;

import org.anyframe.pagination.Page;

import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.framework.dao.BaseDAO;
import com.sds.acube.luxor.portal.vo.PortalPageThemeVO;

public class PortalPageThemeDAO extends BaseDAO {
	
	public boolean insertPageTheme(PortalPageThemeVO vo) throws Exception {
		return insert("insertPageTheme", vo) > 0;
	}
	
	public boolean updatePageTheme(PortalPageThemeVO vo) throws Exception {
		return update("updatePageTheme", vo) > 0;
	}

	public boolean resetDefaultPageTheme(PortalPageThemeVO vo) throws Exception {
		return update("resetDefaultPageTheme", vo) > 0;
	}

	public boolean deletePageTheme(PortalPageThemeVO vo) throws Exception {
		return delete("deletePageTheme", vo) > 0;
	}
	
	public PortalPageThemeVO getPageTheme(PortalPageThemeVO vo) throws Exception {
		vo.setPortalId(CommonUtil.getEscapeString(vo.getPortalId(), dbType));
		PortalPageThemeVO result = (PortalPageThemeVO)select("getPageTheme", vo);
		vo.setPortalId(CommonUtil.getResetEscapeString(vo.getPortalId()));
		return result;
	}
	
	public Page getPageThemeList(PortalPageThemeVO vo) throws Exception {
		return selectListPage("getPageThemeList", vo);
	}

	
}
