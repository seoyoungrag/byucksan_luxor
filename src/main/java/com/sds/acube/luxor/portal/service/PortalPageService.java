package com.sds.acube.luxor.portal.service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.servlet.http.HttpSession;

import org.anyframe.pagination.Page;
import com.sds.acube.luxor.common.vo.CommonVO;
import com.sds.acube.luxor.portal.vo.PortalPageVO;

@WebService
public interface PortalPageService {
	@WebMethod(exclude=true)
	public boolean insertPage(PortalPageVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean insertPageWithDefaultAcl(PortalPageVO vo, String defaultAcl, HttpSession session) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean updatePage(PortalPageVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean setHome(PortalPageVO vo) throws Exception;

	@WebMethod(exclude=true)
	public boolean setPersonalize(PortalPageVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean setTemplate(PortalPageVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean copyPage(PortalPageVO vo, String fromPageId, String toPageId) throws Exception;
		
	@WebMethod(exclude=true)
	public boolean deletePage(PortalPageVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean updatePageActiveStatus(PortalPageVO vo) throws Exception;

	public List<PortalPageVO> getHome(PortalPageVO vo) throws Exception;
	
	public PortalPageVO getPage(PortalPageVO vo) throws Exception;
	
	public PortalPageVO getPageForAdmin(PortalPageVO vo) throws Exception;
	
	public List getPersonalPages(PortalPageVO vo) throws Exception;
	
	public List getTemplatePages(PortalPageVO vo) throws Exception;
	
	@Deprecated
	public Page getPageList(PortalPageVO vo) throws Exception;
	
	public int getTotalPageCount(PortalPageVO vo) throws Exception;
	
	public boolean clearPersonalizedPage(PortalPageVO vo) throws Exception;

	public CommonVO getGlobalServiceIds(PortalPageVO page) throws Exception;
}
