package com.sds.acube.luxor.portal.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import org.anyframe.pagination.Page;
import com.sds.acube.luxor.portal.vo.PortalPageThemeVO;

@WebService
public interface PortalPageThemeService {
	@WebMethod(exclude=true)
	public boolean insertPageTheme(PortalPageThemeVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean updatePageTheme(PortalPageThemeVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean setDefaultPageTheme(PortalPageThemeVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean deletePageTheme(PortalPageThemeVO vo) throws Exception;
	
	public PortalPageThemeVO getPageTheme(PortalPageThemeVO vo) throws Exception;
	
	public Page getPageThemeList(PortalPageThemeVO vo) throws Exception;
	
}
