
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

@XmlRootElement(name = "createPortlet", namespace = "http://webservice.luxor.acube.sds.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createPortlet", namespace = "http://webservice.luxor.acube.sds.com/")

public class CreatePortlet {

    @XmlElement(name = "arg0")
    private com.sds.acube.luxor.portal.vo.PortletManagementVO arg0;

    public com.sds.acube.luxor.portal.vo.PortletManagementVO getArg0() {
        return this.arg0;
    }

    public void setArg0(com.sds.acube.luxor.portal.vo.PortletManagementVO newArg0)  {
        this.arg0 = newArg0;
    }

}

