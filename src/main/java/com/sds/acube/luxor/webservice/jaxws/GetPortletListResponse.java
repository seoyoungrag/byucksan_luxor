
package com.sds.acube.luxor.webservice.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.3.0
 * Thu Jul 04 09:36:34 KST 2013
 * Generated source version: 2.3.0
 */

@XmlRootElement(name = "getPortletListResponse", namespace = "http://webservice.luxor.acube.sds.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPortletListResponse", namespace = "http://webservice.luxor.acube.sds.com/")

public class GetPortletListResponse {

    @XmlElement(name = "return")
    private java.util.List<com.sds.acube.luxor.portal.vo.PortletManagementVO> _return;

    public java.util.List<com.sds.acube.luxor.portal.vo.PortletManagementVO> getReturn() {
        return this._return;
    }

    public void setReturn(java.util.List<com.sds.acube.luxor.portal.vo.PortletManagementVO> new_return)  {
        this._return = new_return;
    }

}
