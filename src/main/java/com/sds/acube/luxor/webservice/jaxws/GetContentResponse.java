
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

@XmlRootElement(name = "getContentResponse", namespace = "http://webservice.luxor.acube.sds.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getContentResponse", namespace = "http://webservice.luxor.acube.sds.com/")

public class GetContentResponse {

    @XmlElement(name = "return")
    private com.sds.acube.luxor.portal.vo.PortalContentVO _return;

    public com.sds.acube.luxor.portal.vo.PortalContentVO getReturn() {
        return this._return;
    }

    public void setReturn(com.sds.acube.luxor.portal.vo.PortalContentVO new_return)  {
        this._return = new_return;
    }

}
