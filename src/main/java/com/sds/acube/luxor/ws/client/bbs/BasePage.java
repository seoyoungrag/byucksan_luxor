
package com.sds.acube.luxor.ws.client.bbs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for basePage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="basePage">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.bbs.collaboration.luxor.acube.sds.com/}page">
 *       &lt;sequence>
 *         &lt;element name="userPermission" type="{http://ws.bbs.collaboration.luxor.acube.sds.com/}basePermission" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "basePage", propOrder = {
    "userPermission"
})
public class BasePage
    extends Page
{

    protected BasePermission userPermission;

    /**
     * Gets the value of the userPermission property.
     * 
     * @return
     *     possible object is
     *     {@link BasePermission }
     *     
     */
    public BasePermission getUserPermission() {
        return userPermission;
    }

    /**
     * Sets the value of the userPermission property.
     * 
     * @param value
     *     allowed object is
     *     {@link BasePermission }
     *     
     */
    public void setUserPermission(BasePermission value) {
        this.userPermission = value;
    }

}
