
package com.sds.acube.luxor.ws.client.orgservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for department complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="department">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="addrSymbol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chiefPosition" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="depth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hasChild" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homepage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="institutionDisplayName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isInstitution" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ODCDCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgOrder" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="orgOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgParentID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="outgoingName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proxyDocHandleDeptCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telephone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="zipCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "department", propOrder = {
    "addrSymbol",
    "address",
    "addressDetail",
    "chiefPosition",
    "depth",
    "email",
    "fax",
    "hasChild",
    "homepage",
    "institutionDisplayName",
    "isInstitution",
    "odcdCode",
    "orgID",
    "orgName",
    "orgOrder",
    "orgOtherName",
    "orgParentID",
    "orgType",
    "outgoingName",
    "proxyDocHandleDeptCode",
    "telephone",
    "zipCode"
})
public class Department {

    protected String addrSymbol;
    protected String address;
    protected String addressDetail;
    protected String chiefPosition;
    protected int depth;
    protected String email;
    protected String fax;
    protected String hasChild;
    protected String homepage;
    protected String institutionDisplayName;
    protected boolean isInstitution;
    @XmlElement(name = "ODCDCode")
    protected String odcdCode;
    protected String orgID;
    protected String orgName;
    protected int orgOrder;
    protected String orgOtherName;
    protected String orgParentID;
    protected int orgType;
    protected String outgoingName;
    protected String proxyDocHandleDeptCode;
    protected String telephone;
    protected String zipCode;

    /**
     * Gets the value of the addrSymbol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddrSymbol() {
        return addrSymbol;
    }

    /**
     * Sets the value of the addrSymbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddrSymbol(String value) {
        this.addrSymbol = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the addressDetail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressDetail() {
        return addressDetail;
    }

    /**
     * Sets the value of the addressDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressDetail(String value) {
        this.addressDetail = value;
    }

    /**
     * Gets the value of the chiefPosition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChiefPosition() {
        return chiefPosition;
    }

    /**
     * Sets the value of the chiefPosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChiefPosition(String value) {
        this.chiefPosition = value;
    }

    /**
     * Gets the value of the depth property.
     * 
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Sets the value of the depth property.
     * 
     */
    public void setDepth(int value) {
        this.depth = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the fax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFax() {
        return fax;
    }

    /**
     * Sets the value of the fax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFax(String value) {
        this.fax = value;
    }

    /**
     * Gets the value of the hasChild property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHasChild() {
        return hasChild;
    }

    /**
     * Sets the value of the hasChild property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHasChild(String value) {
        this.hasChild = value;
    }

    /**
     * Gets the value of the homepage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomepage() {
        return homepage;
    }

    /**
     * Sets the value of the homepage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomepage(String value) {
        this.homepage = value;
    }

    /**
     * Gets the value of the institutionDisplayName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstitutionDisplayName() {
        return institutionDisplayName;
    }

    /**
     * Sets the value of the institutionDisplayName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstitutionDisplayName(String value) {
        this.institutionDisplayName = value;
    }

    /**
     * Gets the value of the isInstitution property.
     * 
     */
    public boolean isIsInstitution() {
        return isInstitution;
    }

    /**
     * Sets the value of the isInstitution property.
     * 
     */
    public void setIsInstitution(boolean value) {
        this.isInstitution = value;
    }

    /**
     * Gets the value of the odcdCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getODCDCode() {
        return odcdCode;
    }

    /**
     * Sets the value of the odcdCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setODCDCode(String value) {
        this.odcdCode = value;
    }

    /**
     * Gets the value of the orgID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgID() {
        return orgID;
    }

    /**
     * Sets the value of the orgID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgID(String value) {
        this.orgID = value;
    }

    /**
     * Gets the value of the orgName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * Sets the value of the orgName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgName(String value) {
        this.orgName = value;
    }

    /**
     * Gets the value of the orgOrder property.
     * 
     */
    public int getOrgOrder() {
        return orgOrder;
    }

    /**
     * Sets the value of the orgOrder property.
     * 
     */
    public void setOrgOrder(int value) {
        this.orgOrder = value;
    }

    /**
     * Gets the value of the orgOtherName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgOtherName() {
        return orgOtherName;
    }

    /**
     * Sets the value of the orgOtherName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgOtherName(String value) {
        this.orgOtherName = value;
    }

    /**
     * Gets the value of the orgParentID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgParentID() {
        return orgParentID;
    }

    /**
     * Sets the value of the orgParentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgParentID(String value) {
        this.orgParentID = value;
    }

    /**
     * Gets the value of the orgType property.
     * 
     */
    public int getOrgType() {
        return orgType;
    }

    /**
     * Sets the value of the orgType property.
     * 
     */
    public void setOrgType(int value) {
        this.orgType = value;
    }

    /**
     * Gets the value of the outgoingName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutgoingName() {
        return outgoingName;
    }

    /**
     * Sets the value of the outgoingName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutgoingName(String value) {
        this.outgoingName = value;
    }

    /**
     * Gets the value of the proxyDocHandleDeptCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProxyDocHandleDeptCode() {
        return proxyDocHandleDeptCode;
    }

    /**
     * Sets the value of the proxyDocHandleDeptCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProxyDocHandleDeptCode(String value) {
        this.proxyDocHandleDeptCode = value;
    }

    /**
     * Gets the value of the telephone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Sets the value of the telephone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelephone(String value) {
        this.telephone = value;
    }

    /**
     * Gets the value of the zipCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the value of the zipCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZipCode(String value) {
        this.zipCode = value;
    }

}
