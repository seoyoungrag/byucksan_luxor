package com.sds.acube.luxor.portal.service.impl;

import java.util.List;

import javax.jws.WebMethod;

import com.sds.acube.luxor.framework.service.BaseService;
import com.sds.acube.luxor.portal.dao.PortalPageZoneDAO;
import com.sds.acube.luxor.portal.service.GroupPortalService;
import com.sds.acube.luxor.portal.service.PortalPageZoneService;
import com.sds.acube.luxor.portal.vo.PortalPageZoneVO;

public class PortalPageZoneServiceImpl extends BaseService implements PortalPageZoneService {
	private PortalPageZoneDAO portalPageZoneDAO;
	private GroupPortalService groupPortalService;
	
	@WebMethod(exclude=true)
	public void setGroupPortalService(GroupPortalService groupPortalService) {
		this.groupPortalService = groupPortalService;
	}

	@WebMethod(exclude=true)
	public void setPortalPageZoneDAO(PortalPageZoneDAO portalPageZoneDAO) {
    	this.portalPageZoneDAO = portalPageZoneDAO;
    }
	
	
	@WebMethod(exclude=true)
	public boolean insertContentOnZone(PortalPageZoneVO vo) throws Exception {
		int maxSeq = portalPageZoneDAO.getMaxSeqOnZone(vo);
		vo.setSeq(++maxSeq);
		vo.setIsDeleted("N");
		return portalPageZoneDAO.insertContentOnZone(vo);
	}
	
	@WebMethod(exclude=true)
	public boolean insertPersonalizedContentOnZone(PortalPageZoneVO vo) throws Exception {
		return portalPageZoneDAO.insertContentOnZone(vo);
	}

	
	@WebMethod(exclude=true)
	public boolean updateSeq(PortalPageZoneVO vo) throws Exception {
		return portalPageZoneDAO.updateSeq(vo);
	}

	
	@WebMethod(exclude=true)
	public boolean updateContentStyle(PortalPageZoneVO vo) throws Exception {
		return portalPageZoneDAO.updateContentStyle(vo);
	}
	
	
	public boolean updateContentFixedFlag(PortalPageZoneVO vo) throws Exception {
		// 관리자가 Non-fixed인 컨텐츠를 다시 Fixed로 변경할 경우
		// 개인이 임의로 삭제한 정보(is_deleted = 'Y')를 전부 리셋시킴
		if("Y".equals(vo.getIsFixed())) {
			vo.setIsDeleted("N");
			portalPageZoneDAO.updateContentDeletedFlagAll(vo);
		}
		
		return portalPageZoneDAO.updateContentFixedFlag(vo);
	}
	
	public boolean updateContentDeletedFlag(PortalPageZoneVO vo) throws Exception {
		return portalPageZoneDAO.updateContentDeletedFlag(vo);
	}
	
	@WebMethod(exclude=true)
	public boolean deleteContentOnZone(PortalPageZoneVO vo) throws Exception {
		return portalPageZoneDAO.deleteContentOnZone(vo);
	}

	
	@WebMethod(exclude=true)
	public boolean deleteAllContentOnZone(PortalPageZoneVO vo) throws Exception {
		return portalPageZoneDAO.deleteAllContentOnZone(vo);
	}

	
	@WebMethod(exclude=true)
	public boolean deleteAllContentOnPage(PortalPageZoneVO vo) throws Exception {
		return portalPageZoneDAO.deleteAllContentOnPage(vo);
	}

	@WebMethod(exclude=true)
	public boolean deleteAllContentPersonalizedPage(PortalPageZoneVO vo) throws Exception {
		return portalPageZoneDAO.deleteAllContentPersonalizedPage(vo);
	}
	
	@WebMethod(exclude=true)
	public boolean clearContentsToSetTheFixedContent(PortalPageZoneVO vo) throws Exception {
		return portalPageZoneDAO.clearContentsToSetTheFixedContent(vo);
	}
	
	@WebMethod(exclude=true)
	public boolean cleanDeletedContent(PortalPageZoneVO vo) throws Exception {
		return portalPageZoneDAO.cleanDeletedContent(vo);
	}
	
	public List getUnpersonalizedAdminContents(PortalPageZoneVO vo) throws Exception {
		return portalPageZoneDAO.getUnpersonalizedAdminContents(vo);
	}
	
	public PortalPageZoneVO getContentOnZone(PortalPageZoneVO vo) throws Exception {
		PortalPageZoneVO resultVO = new PortalPageZoneVO();
		resultVO = portalPageZoneDAO.getContentOnZone(vo);
		return resultVO;
	}
	
	
	public List getContentsOnZone(PortalPageZoneVO vo) throws Exception {
		List resultList = portalPageZoneDAO.getContentsOnZone(vo);
		return resultList;
	}

	
	public List getContentsOnPage(PortalPageZoneVO vo) throws Exception {
		List resultList = portalPageZoneDAO.getContentsOnPage(vo);
		return resultList;
	}

	
	public List getPagesHasContent(PortalPageZoneVO vo) throws Exception {
		return portalPageZoneDAO.getPagesHasContent(vo);
	}

	public List getContentIncludingPages(PortalPageZoneVO vo) throws Exception {
		return portalPageZoneDAO.getContentIncludingPages(vo);
	}
}
