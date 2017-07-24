package com.sds.acube.luxor.portal.service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import com.sds.acube.luxor.portal.vo.PortalPageZoneVO;

@WebService
public interface PortalPageZoneService {
	@WebMethod(exclude=true)
	public boolean insertContentOnZone(PortalPageZoneVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean insertPersonalizedContentOnZone(PortalPageZoneVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean updateSeq(PortalPageZoneVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean updateContentStyle(PortalPageZoneVO vo) throws Exception;
	
	public boolean updateContentFixedFlag(PortalPageZoneVO vo) throws Exception;
	
	public boolean updateContentDeletedFlag(PortalPageZoneVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean deleteContentOnZone(PortalPageZoneVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean deleteAllContentOnZone(PortalPageZoneVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean deleteAllContentOnPage(PortalPageZoneVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean deleteAllContentPersonalizedPage(PortalPageZoneVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean clearContentsToSetTheFixedContent(PortalPageZoneVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean cleanDeletedContent(PortalPageZoneVO vo) throws Exception;
	
	public PortalPageZoneVO getContentOnZone(PortalPageZoneVO vo) throws Exception;
	
	public List getUnpersonalizedAdminContents(PortalPageZoneVO vo) throws Exception;
	
	public List getContentsOnZone(PortalPageZoneVO vo) throws Exception;
	
	public List getContentsOnPage(PortalPageZoneVO vo) throws Exception;
	
	public List getPagesHasContent(PortalPageZoneVO vo) throws Exception;
	
	public List getContentIncludingPages(PortalPageZoneVO vo) throws Exception;
}
