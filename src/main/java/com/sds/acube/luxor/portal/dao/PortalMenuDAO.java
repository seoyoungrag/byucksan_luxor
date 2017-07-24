package com.sds.acube.luxor.portal.dao;

import java.util.List;

import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.framework.dao.BaseDAO;
import com.sds.acube.luxor.portal.vo.PortalMenuVO;

public class PortalMenuDAO extends BaseDAO {
	
	public boolean insertMenu(PortalMenuVO vo) throws Exception {
		return insert("insertMenu", vo) > 0;
	}

	public boolean updateMenu(PortalMenuVO vo) throws Exception {
		return update("updateMenu", vo) > 0;
	}
	
	public boolean updateMenuDisplayName(PortalMenuVO vo) throws Exception {
		return update("updateMenuDisplayName", vo) > 0;
	}

	public boolean updateMenuSeq(PortalMenuVO vo) throws Exception {
		return update("updateMenuSeq", vo) > 0;
	}

	public boolean updateMenuToAll(PortalMenuVO vo) throws Exception {
		return update("updateMenuToAll", vo) > 0;
	}

	public boolean deleteMenu(PortalMenuVO vo) throws Exception {
		return delete("deleteMenu", vo) > 0;
	}
	
	public boolean deleteMenusOnZone(PortalMenuVO vo) throws Exception {
		return delete("deleteMenusOnZone", vo) > 0;
	}

	public boolean deleteMenusOnContent(PortalMenuVO vo) throws Exception {
		return delete("deleteMenusOnContent", vo) > 0;
	}

	public boolean deleteMenusOnPage(PortalMenuVO vo) throws Exception {
		return delete("deleteMenusOnPage", vo) > 0;
	}

	public int getMaxSeq(PortalMenuVO vo) throws Exception {
		vo.setPortalId(CommonUtil.getEscapeString(vo.getPortalId(), dbType));
		int count = getCount("getMaxMenuSeq", "maxseq", vo);
		vo.setPortalId(CommonUtil.getResetEscapeString(vo.getPortalId()));
		
		return count;
	}
	
	public PortalMenuVO[] getAddedMenu(PortalMenuVO vo) throws Exception {
		vo.setPortalId(CommonUtil.getEscapeString(vo.getPortalId(), dbType));
		List menuList = selectList("getAddedMenu", vo);
		vo.setPortalId(CommonUtil.getResetEscapeString(vo.getPortalId()));

		PortalMenuVO[] result = new PortalMenuVO[menuList.size()];
		menuList.toArray(result);
		return result;
	}
	
	public PortalMenuVO getMenu(PortalMenuVO vo) throws Exception {
		vo.setPortalId(CommonUtil.getEscapeString(vo.getPortalId(),dbType));
		PortalMenuVO result = (PortalMenuVO)select("getMenu", vo);
		vo.setPortalId(CommonUtil.getResetEscapeString(vo.getPortalId()));
		return result;
	}

	public PortalMenuVO getParentMenu(PortalMenuVO vo) throws Exception {
		vo.setPortalId(CommonUtil.getEscapeString(vo.getPortalId(), dbType));
		PortalMenuVO result = (PortalMenuVO)select("getParentMenu", vo);
		vo.setPortalId(CommonUtil.getResetEscapeString(vo.getPortalId()));
		
		return result;
	}

	public PortalMenuVO[] getMenusOnParent(PortalMenuVO vo) throws Exception {
		vo.setPortalId(CommonUtil.getEscapeString(vo.getPortalId(), dbType));
		List menuList = selectList("getMenusOnParent", vo);
		vo.setPortalId(CommonUtil.getResetEscapeString(vo.getPortalId()));
		
		PortalMenuVO[] result = new PortalMenuVO[menuList.size()];
		menuList.toArray(result);
		return result;
	}

	public List getMenusOnZone(PortalMenuVO vo) throws Exception {
		return selectList("getMenusOnZone", vo);
	}

	public List getMenusOnPage(PortalMenuVO vo) throws Exception {
		vo.setPortalId(CommonUtil.getEscapeString(vo.getPortalId(), dbType));
		List result = selectList("getMenusOnPage", vo);
		vo.setPortalId(CommonUtil.getResetEscapeString(vo.getPortalId()));
		
		return result;
	}

	public List getMenusOnContent(PortalMenuVO vo) throws Exception {
		vo.setPortalId(CommonUtil.getEscapeString(vo.getPortalId(), dbType));
		vo.setLangType("ko");
		List result = selectList("getMenusOnContent", vo);
		vo.setPortalId(CommonUtil.getResetEscapeString(vo.getPortalId()));
		return result;
	}
	
	public List getMyMenus(PortalMenuVO vo) throws Exception {
		vo.setPortalId(CommonUtil.getEscapeString(vo.getPortalId(), dbType));		
		List result = selectList("getMyMenus", vo);
		vo.setPortalId(CommonUtil.getResetEscapeString(vo.getPortalId()));

		return result;
	}

	public List getMenus(PortalMenuVO vo) throws Exception {
		vo.setPortalId(CommonUtil.getEscapeString(vo.getPortalId(), dbType));		
		vo.setSearchKey(CommonUtil.getEscapeString(vo.getSearchKey(), dbType));				
		List result = selectList("getMenus", vo);
		vo.setPortalId(CommonUtil.getResetEscapeString(vo.getPortalId()));		
		vo.setSearchKey(CommonUtil.getResetEscapeString(vo.getSearchKey()));	
		
		return result;
	}
	
	public List getTargetedPages(PortalMenuVO vo) throws Exception {
		vo.setPortalId(CommonUtil.getEscapeString(vo.getPortalId(), dbType));
		List result = selectList("getTargetedPages", vo);
		vo.setPortalId(CommonUtil.getResetEscapeString(vo.getPortalId()));		
		
		return result;
	}

}
