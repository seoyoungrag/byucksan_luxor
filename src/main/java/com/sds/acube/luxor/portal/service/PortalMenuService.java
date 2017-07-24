package com.sds.acube.luxor.portal.service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import com.sds.acube.luxor.portal.vo.PortalMenuVO;

@WebService
public interface PortalMenuService {
	@WebMethod(exclude=true)
	public boolean insertMenu(PortalMenuVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean updateMenu(PortalMenuVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean updateMenuDisplayName(PortalMenuVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean updateMenuSeq(PortalMenuVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean updateMenuToAll(PortalMenuVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean deleteMenu(PortalMenuVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public void deleteMenuAll(PortalMenuVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean deleteMenusOnZone(PortalMenuVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean deleteMenusOnContent(PortalMenuVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean deleteMenusOnPage(PortalMenuVO vo) throws Exception;
	
	public int getMaxSeq(PortalMenuVO vo) throws Exception;
	
	public PortalMenuVO getMenu(PortalMenuVO vo) throws Exception;
	
	public List getMenusOnZone(PortalMenuVO vo) throws Exception;
	
	public List getMenusOnContent(PortalMenuVO vo) throws Exception;
	
	/**
	 * 페이지 가져오기(복사)할때 PortalPageService.copyPage에서 사용됨
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List getMenusOnPage(PortalMenuVO vo) throws Exception;
	
	public List getMyMenus(PortalMenuVO vo) throws Exception;
	
	public List getMenus(PortalMenuVO vo) throws Exception;
	
	public List getTargetedPages(PortalMenuVO vo) throws Exception;
	
}
