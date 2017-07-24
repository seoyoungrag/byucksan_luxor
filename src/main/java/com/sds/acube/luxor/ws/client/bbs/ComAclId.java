
package com.sds.acube.luxor.ws.client.bbs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for comAclId complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="comAclId">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accessId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aclType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="portalId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resourceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tenantId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "comAclId", propOrder = {
    "accessId",
    "aclType",
    "portalId",
    "resourceId",
    "tenantId"
})
public class ComAclId {

    protected String accessId;
    protected String aclType;
    protected String portalId;
    protected String resourceId;
    protected String tenantId;

    /**
     * Gets the value of the accessId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessId() {
        return accessId;
    }

    /**
     * Sets the value of the accessId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessId(String value) {
        this.accessId = value;
    }

    /**
     * Gets the value of the aclType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAclType() {
        return aclType;
    }

    /**
     * Sets the value of the aclType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAclType(String value) {
        this.aclType = value;
    }

    /**
     * Gets the value of the portalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortalId() {
        return portalId;
    }

    /**
     * Sets the value of the portalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortalId(String value) {
        this.portalId = value;
    }

    /**
     * Gets the value of the resourceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * Sets the value of the resourceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResourceId(String value) {
        this.resourceId = value;
    }

    /**
     * Gets the value of the tenantId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * Sets the value of the tenantId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTenantId(String value) {
        this.tenantId = value;
    }

}
