package com.sds.acube.luxor.portal.dao;

import java.util.List;

import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.framework.dao.BaseDAO;
import com.sds.acube.luxor.portal.vo.PortalPageZoneVO;

public class PortalPageZoneDAO extends BaseDAO {
	
	public boolean insertContentOnZone(PortalPageZoneVO vo) throws Exception {
		return insert("insertContentOnZone", vo) > 0;
	}
	
	public boolean deleteContentOnZone(PortalPageZoneVO vo) throws Exception {
		return delete("deleteContentOnZone", vo) > 0;
	}

	public boolean deleteAllContentOnZone(PortalPageZoneVO vo) throws Exception {
		return delete("deleteAllContentOnZone", vo) > 0;
	}

	public boolean deleteAllContentOnPage(PortalPageZoneVO vo) throws Exception {
		return delete("deleteAllContentOnPage", vo) > 0;
	}
	
	public boolean deleteAllContentPersonalizedPage(PortalPageZoneVO vo) throws Exception {
		return delete("deleteAllContentPersonalizedPage", vo) > 0;
	}
	
	public boolean clearContentsToSetTheFixedContent(PortalPageZoneVO vo) throws Exception {
		return delete("clearContentsToSetTheFixedContent", vo) > 0;
	}

	public boolean cleanDeletedContent(PortalPageZoneVO vo) throws Exception {
		return delete("cleanDeletedContent", vo) > 0;
	}
	
	public boolean updateSeq(PortalPageZoneVO vo) throws Exception {
		return update("updateSeq", vo) > 0;
	}

	public boolean updateContentStyle(PortalPageZoneVO vo) throws Exception {
		return update("updateContentStyle", vo) > 0;
	}

	public boolean updateContentFixedFlag(PortalPageZoneVO vo) throws Exception {
		return update("updateContentFixedFlag", vo) > 0;
	}

	public boolean updateContentDeletedFlag(PortalPageZoneVO vo) throws Exception {
		return update("updateContentDeletedFlag", vo) > 0;
	}

	public boolean updateContentDeletedFlagAll(PortalPageZoneVO vo) throws Exception {
		return update("updateContentDeletedFlagAll", vo) > 0;
	}

	public int getMaxSeqOnZone(PortalPageZoneVO vo) throws Exception {
		return getCount("getMaxSeqOnZone", "maxseq", vo);
	}
	
	public PortalPageZoneVO getContentOnZone(PortalPageZoneVO vo) throws Exception {
		return (PortalPageZoneVO)select("getContentOnZone", vo);
	}
	
	public List getUnpersonalizedAdminContents(PortalPageZoneVO vo) throws Exception {
		return selectList("getUnpersonalizedAdminContents", vo);
	}
	
	public List getContentsOnZone(PortalPageZoneVO vo) throws Exception {
		vo.setPortalId(CommonUtil.getEscapeString(vo.getPortalId(), dbType));
		List result = selectList("getContentsOnZone", vo);
		vo.setPortalId(CommonUtil.getResetEscapeString(vo.getPortalId()));
		return result;
	}

	public List getContentsOnPage(PortalPageZoneVO vo) throws Exception {
		vo.setPortalId(CommonUtil.getEscapeString(vo.getPortalId(), dbType));
		List result =  selectList("getContentsOnPage", vo);
		vo.setPortalId(CommonUtil.getResetEscapeString(vo.getPortalId()));
		return selectList("getContentsOnPage", vo);
	}
	
	public List getPagesHasContent(PortalPageZoneVO vo) throws Exception {
		return selectList("getPagesHasContent", vo);
	}
	
	public List getContentIncludingPages(PortalPageZoneVO vo) throws Exception {
		return selectList("getContentIncludingPages", vo);
	}
}
