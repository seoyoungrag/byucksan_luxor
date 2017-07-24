package com.sds.acube.luxor.webservice;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebService;

import org.springframework.stereotype.Service;
import com.sds.acube.luxor.portal.vo.PortalContentVO;
import com.sds.acube.luxor.portal.vo.PortletManagementVO;
import com.sds.acube.luxor.portal.vo.PortletContextVO;
import com.sds.acube.luxor.portal.service.PortletManagementService;
import com.sds.acube.luxor.portal.service.PortalContentService;

@WebService(targetNamespace = "http://webservice.luxor.acube.sds.com/", endpointInterface = "com.sds.acube.luxor.webservice.LuxorWebService", portName = "LuxorWebServiceImplPort", serviceName = "LuxorWebServiceImplService")
@Service("cxfJaxWsLuxorWebService")
public class LuxorWebServiceImpl implements LuxorWebService {
	
	@Inject
	@Named("portalContentService")
	private PortalContentService portalContentService;	

	@Inject
	@Named("portletManagementService")
	private PortletManagementService portletManagementService;	
	

	/*
	 * 포틀릿 생성 
	 *  String tenantId
	 *  String portalId 
	 *  String contextName
	 *  int statusCode
	 *  int typeOfPortlet 
	 *	String title
	 *  String viewUrl 
	 *  String ssoInfo
	 *  String categoryId
	 * */
	public boolean createPortlet(PortletManagementVO Portlet) throws Exception{
		return portletManagementService.portletRegister(Portlet);
	}
    
	public boolean updatePortlet(PortletManagementVO Portlet) throws Exception{
		return portletManagementService.portletRegister(Portlet);
	}
	
	public boolean removePortlet(PortletManagementVO Portlet) throws Exception{
		return portletManagementService.delete(Portlet);
	}
	
	public PortletContextVO getPortelt( PortletManagementVO Portlet) throws Exception{
		return portletManagementService.getPortletContextInfo(Portlet);
	}
	
	public List<PortletManagementVO> getPortletList(PortletManagementVO Portlet) throws Exception{
		return portletManagementService.deployStatusInfo(Portlet);
	}
	
	/*
	 * 컨텐츠 생성  
	 *  String tenantId
	 *  String portalId 
	 *  String parentId(컨텐츠 카테고리id) 
	 *  String contentId
	 *  String portletId
	 *  String messageName
	 * */	
	public boolean createContent(PortalContentVO content) throws Exception{
		return portalContentService.insertContent(content);
	}
	
	public boolean updateContent(PortalContentVO content) throws Exception{
		return portalContentService.updateContent(content);
	}
	
	public boolean removeContent(PortalContentVO content) throws Exception{
		return portalContentService.deleteContent(content);
	}
	
	public PortalContentVO getContent(PortalContentVO content) throws Exception{
		return portalContentService.getContent(content);
	}
	
	public List<PortalContentVO> getContentList(PortalContentVO content) throws Exception{
		return portalContentService.getContentList(content);
	}
}
