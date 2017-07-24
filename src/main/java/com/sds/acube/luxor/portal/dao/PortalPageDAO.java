package com.sds.acube.luxor.portal.dao;

import java.util.List;
import org.anyframe.pagination.Page;

import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.vo.CommonVO;
import com.sds.acube.luxor.framework.dao.BaseDAO;
import com.sds.acube.luxor.portal.vo.PortalPageVO;

public class PortalPageDAO extends BaseDAO {
	
	public boolean insertPage(PortalPageVO vo) throws Exception {
		return insert("insertPage", vo) > 0;
	}
	
	public boolean updatePage(PortalPageVO vo) throws Exception {
		return update("updatePage", vo) > 0;
	}

	public boolean setHome(PortalPageVO vo) throws Exception {
		update("_setHome", vo); // 기존에 is_home이 Y인것을 N로 변경
		return update("setHome", vo) > 0;
	}

	public boolean setPersonalize(PortalPageVO vo) throws Exception {
		return update("setPersonalize", vo) > 0;
	}

	public boolean setTemplate(PortalPageVO vo) throws Exception {
		return update("setTemplate", vo) > 0;
	}

	public List<PortalPageVO> getHome(PortalPageVO vo) throws Exception {
		return selectList("getHome", vo);
	}
	
	public List getPersonalPages(PortalPageVO vo) throws Exception {
		return selectList("getPersonalPages", vo);
	}

	public boolean deletePage(PortalPageVO vo) throws Exception {
		return delete("deletePage", vo) > 0;
	}
	
	public PortalPageVO getPage(PortalPageVO vo) throws Exception {
		vo.setPortalId(CommonUtil.getEscapeString(vo.getPortalId(),dbType));
		PortalPageVO result = (PortalPageVO)select("getPage", vo);
		vo.setPortalId(CommonUtil.getResetEscapeString(vo.getPortalId()));
		
		return result;
	}
	
	public PortalPageVO getPagePathInfo(PortalPageVO vo) throws Exception{
		return (PortalPageVO)select("getPagePathInfo", vo);
	}

	public List getTemplatePages(PortalPageVO vo) throws Exception {
		return selectList("getTemplatePages", vo);
	}

	@Deprecated
	public Page getPageList(PortalPageVO vo) throws Exception {
		return selectListPage("getPageList", vo);
	}
	
	public int getTotalPageCount(PortalPageVO vo) throws Exception {
		return getCount("getTotalPageCount", "cnt", vo);
	}
	
	public CommonVO getGlobalServiceIds(PortalPageVO vo) throws Exception {
		return (CommonVO)select("getGlobalServiceIds", vo);
	}
	
	public boolean clearPersonalizedPage(PortalPageVO vo) throws Exception {
		try {
			vo.setPageId(CommonUtil.getEscapeString(vo.getPageId(), dbType));
			delete("clearPersonalizedUserSetting", vo);
			vo.setPageId(CommonUtil.getResetEscapeString(vo.getPageId()));
			
			delete("clearPersonalizedPage", vo);
		} catch (Exception e) {
			throw(e);
		}
		return true;
	}
	
}
