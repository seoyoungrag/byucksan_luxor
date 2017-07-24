package com.sds.acube.luxor.portal.service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.anyframe.pagination.Page;
import com.sds.acube.luxor.common.vo.TreeVO;
import com.sds.acube.luxor.portal.vo.PortalContentVO;

@WebService
public interface PortalContentService {
	@WebMethod(exclude=true)
	public boolean insertContent(PortalContentVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean updateContent(PortalContentVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean updateContentParentId(PortalContentVO vo) throws Exception;

	@WebMethod(exclude=true)
	public boolean deleteContent(PortalContentVO vo) throws Exception;
	
	public PortalContentVO getContent(PortalContentVO vo) throws Exception;
	
	public List getContentList(PortalContentVO vo) throws Exception;
	
	public Page getContentListPage(PortalContentVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean setCategoryUsePersonal(TreeVO vo) throws Exception;
	
	public List<TreeVO> getPersonalCatalog(TreeVO vo) throws Exception;
	
	@WebMethod(exclude=true)
	public boolean setContentUsePersonal(PortalContentVO vo) throws Exception;
	
}
