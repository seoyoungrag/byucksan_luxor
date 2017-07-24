package com.sds.acube.luxor.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.sds.acube.luxor.portal.vo.PortalContentVO;
import com.sds.acube.luxor.portal.vo.PortletContextVO;
import com.sds.acube.luxor.portal.vo.PortletManagementVO;

@WebService(name = "LuxorWebService", targetNamespace = "http://webservice.luxor.acube.sds.com/")
public interface LuxorWebService {

	@WebMethod(operationName = "createPortlet", action = "urn:CreatePortlet")
	@RequestWrapper(className = "com.sds.acube.luxor.webservice.jaxws.CreatePortlet", localName = "createPortlet", targetNamespace = "http://webservice.luxor.acube.sds.com/")
	@ResponseWrapper(className = "com.sds.acube.luxor.webservice.jaxws.CreatePortletResponse", localName = "createPortletResponse", targetNamespace = "http://webservice.luxor.acube.sds.com/")
	public boolean createPortlet(@WebParam(name = "arg0") PortletManagementVO Portlet) throws Exception;

	@WebMethod(operationName = "updatePortlet", action = "urn:UpdatePortlet")
	@RequestWrapper(className = "com.sds.acube.luxor.webservice.jaxws.UpdatePortlet", localName = "updatePortlet", targetNamespace = "http://webservice.luxor.acube.sds.com/")
	@ResponseWrapper(className = "com.sds.acube.luxor.webservice.jaxws.UpdatePortletResponse", localName = "updatePortletResponse", targetNamespace = "http://webservice.luxor.acube.sds.com/")
	public boolean updatePortlet(@WebParam(name = "arg0") PortletManagementVO Portlet) throws Exception;

	@WebMethod(operationName = "removePortlet", action = "urn:RemovePortlet")
	@RequestWrapper(className = "com.sds.acube.luxor.webservice.jaxws.RemovePortlet", localName = "removePortlet", targetNamespace = "http://webservice.luxor.acube.sds.com/")
	@ResponseWrapper(className = "com.sds.acube.luxor.webservice.jaxws.RemovePortletResponse", localName = "removePortletResponse", targetNamespace = "http://webservice.luxor.acube.sds.com/")
	public boolean removePortlet(@WebParam(name = "arg0") PortletManagementVO Portlet) throws Exception;

	@WebMethod(operationName = "getPortelt", action = "urn:GetPortelt")
	@RequestWrapper(className = "com.sds.acube.luxor.webservice.jaxws.GetPortelt", localName = "getPortelt", targetNamespace = "http://webservice.luxor.acube.sds.com/")
	@ResponseWrapper(className = "com.sds.acube.luxor.webservice.jaxws.GetPorteltResponse", localName = "getPorteltResponse", targetNamespace = "http://webservice.luxor.acube.sds.com/")
	public PortletContextVO getPortelt(@WebParam(name = "arg0") PortletManagementVO Portlet)
			throws Exception;

	@WebMethod(operationName = "getPortletList", action = "urn:GetPortletList")
	@RequestWrapper(className = "com.sds.acube.luxor.webservice.jaxws.GetPortletList", localName = "getPortletList", targetNamespace = "http://webservice.luxor.acube.sds.com/")
	@ResponseWrapper(className = "com.sds.acube.luxor.webservice.jaxws.GetPortletListResponse", localName = "getPortletListResponse", targetNamespace = "http://webservice.luxor.acube.sds.com/")
	public List<PortletManagementVO> getPortletList(@WebParam(name = "arg0") PortletManagementVO Portlet)
			throws Exception;

	@WebMethod(operationName = "createContent", action = "urn:CreateContent")
	@RequestWrapper(className = "com.sds.acube.luxor.webservice.jaxws.CreateContent", localName = "createContent", targetNamespace = "http://webservice.luxor.acube.sds.com/")
	@ResponseWrapper(className = "com.sds.acube.luxor.webservice.jaxws.CreateContentResponse", localName = "createContentResponse", targetNamespace = "http://webservice.luxor.acube.sds.com/")
	public boolean createContent(@WebParam(name = "arg0") PortalContentVO content) throws Exception;

	@WebMethod(operationName = "updateContent", action = "urn:UpdateContent")
	@RequestWrapper(className = "com.sds.acube.luxor.webservice.jaxws.UpdateContent", localName = "updateContent", targetNamespace = "http://webservice.luxor.acube.sds.com/")
	@ResponseWrapper(className = "com.sds.acube.luxor.webservice.jaxws.UpdateContentResponse", localName = "updateContentResponse", targetNamespace = "http://webservice.luxor.acube.sds.com/")
	public boolean updateContent(@WebParam(name = "arg0") PortalContentVO content) throws Exception;

	@WebMethod(operationName = "removeContent", action = "urn:RemoveContent")
	@RequestWrapper(className = "com.sds.acube.luxor.webservice.jaxws.RemoveContent", localName = "removeContent", targetNamespace = "http://webservice.luxor.acube.sds.com/")
	@ResponseWrapper(className = "com.sds.acube.luxor.webservice.jaxws.RemoveContentResponse", localName = "removeContentResponse", targetNamespace = "http://webservice.luxor.acube.sds.com/")
	public boolean removeContent(@WebParam(name = "arg0") PortalContentVO content) throws Exception;

	@WebMethod(operationName = "getContent", action = "urn:GetContent")
	@RequestWrapper(className = "com.sds.acube.luxor.webservice.jaxws.GetContent", localName = "getContent", targetNamespace = "http://webservice.luxor.acube.sds.com/")
	@ResponseWrapper(className = "com.sds.acube.luxor.webservice.jaxws.GetContentResponse", localName = "getContentResponse", targetNamespace = "http://webservice.luxor.acube.sds.com/")
	public PortalContentVO getContent(@WebParam(name = "arg0") PortalContentVO content) throws Exception;

	@WebMethod(operationName = "getContentList", action = "urn:GetContentList")
	@RequestWrapper(className = "com.sds.acube.luxor.webservice.jaxws.GetContentList", localName = "getContentList", targetNamespace = "http://webservice.luxor.acube.sds.com/")
	@ResponseWrapper(className = "com.sds.acube.luxor.webservice.jaxws.GetContentListResponse", localName = "getContentListResponse", targetNamespace = "http://webservice.luxor.acube.sds.com/")
	public List<PortalContentVO> getContentList(@WebParam(name = "arg0") PortalContentVO content)
			throws Exception;

}