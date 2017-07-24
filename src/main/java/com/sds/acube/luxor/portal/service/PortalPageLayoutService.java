package com.sds.acube.luxor.portal.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import org.anyframe.pagination.Page;
import com.sds.acube.luxor.portal.vo.PortalPageLayoutVO;

@WebService
public interface PortalPageLayoutService {
	@WebMethod(exclude=true)
	public boolean insertPageLayout(PortalPageLayoutVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean updatePageLayout(PortalPageLayoutVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean setDefaultPageLayout(PortalPageLayoutVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean deletePageLayout(PortalPageLayoutVO vo) throws Exception;
	
	public PortalPageLayoutVO getPageLayout(PortalPageLayoutVO vo) throws Exception;
	
	public Page getPageLayoutList(PortalPageLayoutVO vo) throws Exception;
	
}
