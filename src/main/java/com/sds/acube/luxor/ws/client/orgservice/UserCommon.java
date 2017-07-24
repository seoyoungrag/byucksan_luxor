
package com.sds.acube.luxor.ws.client.orgservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for userCommon complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="userCommon">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="compID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="compName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="compOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deptID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deptName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deptOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgDisplayName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgDisplayOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userUID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userCommon", propOrder = {
    "compID",
    "compName",
    "compOtherName",
    "deptID",
    "deptName",
    "deptOtherName",
    "groupID",
    "groupName",
    "groupOtherName",
    "orgDisplayName",
    "orgDisplayOtherName",
    "partID",
    "partName",
    "partOtherName",
    "userID",
    "userName",
    "userOtherName",
    "userUID"
})
@XmlSeeAlso({
    Employee.class
})
public class UserCommon {

    protected String compID;
    protected String compName;
    protected String compOtherName;
    protected String deptID;
    protected String deptName;
    protected String deptOtherName;
    protected String groupID;
    protected String groupName;
    protected String groupOtherName;
    protected String orgDisplayName;
    protected String orgDisplayOtherName;
    protected String partID;
    protected String partName;
    protected String partOtherName;
    protected String userID;
    protected String userName;
    protected String userOtherName;
    protected String userUID;

    /**
     * Gets the value of the compID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompID() {
        return compID;
    }

    /**
     * Sets the value of the compID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompID(String value) {
        this.compID = value;
    }

    /**
     * Gets the value of the compName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompName() {
        return compName;
    }

    /**
     * Sets the value of the compName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompName(String value) {
        this.compName = value;
    }

    /**
     * Gets the value of the compOtherName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompOtherName() {
        return compOtherName;
    }

    /**
     * Sets the value of the compOtherName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompOtherName(String value) {
        this.compOtherName = value;
    }

    /**
     * Gets the value of the deptID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeptID() {
        return deptID;
    }

    /**
     * Sets the value of the deptID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeptID(String value) {
        this.deptID = value;
    }

    /**
     * Gets the value of the deptName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * Sets the value of the deptName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeptName(String value) {
        this.deptName = value;
    }

    /**
     * Gets the value of the deptOtherName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeptOtherName() {
        return deptOtherName;
    }

    /**
     * Sets the value of the deptOtherName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeptOtherName(String value) {
        this.deptOtherName = value;
    }

    /**
     * Gets the value of the groupID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupID() {
        return groupID;
    }

    /**
     * Sets the value of the groupID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupID(String value) {
        this.groupID = value;
    }

    /**
     * Gets the value of the groupName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Sets the value of the groupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupName(String value) {
        this.groupName = value;
    }

    /**
     * Gets the value of the groupOtherName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupOtherName() {
        return groupOtherName;
    }

    /**
     * Sets the value of the groupOtherName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupOtherName(String value) {
        this.groupOtherName = value;
    }

    /**
     * Gets the value of the orgDisplayName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgDisplayName() {
        return orgDisplayName;
    }

    /**
     * Sets the value of the orgDisplayName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgDisplayName(String value) {
        this.orgDisplayName = value;
    }

    /**
     * Gets the value of the orgDisplayOtherName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgDisplayOtherName() {
        return orgDisplayOtherName;
    }

    /**
     * Sets the value of the orgDisplayOtherName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgDisplayOtherName(String value) {
        this.orgDisplayOtherName = value;
    }

    /**
     * Gets the value of the partID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartID() {
        return partID;
    }

    /**
     * Sets the value of the partID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartID(String value) {
        this.partID = value;
    }

    /**
     * Gets the value of the partName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartName() {
        return partName;
    }

    /**
     * Sets the value of the partName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartName(String value) {
        this.partName = value;
    }

    /**
     * Gets the value of the partOtherName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartOtherName() {
        return partOtherName;
    }

    /**
     * Sets the value of the partOtherName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartOtherName(String value) {
        this.partOtherName = value;
    }

    /**
     * Gets the value of the userID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the value of the userID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserID(String value) {
        this.userID = value;
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the userOtherName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserOtherName() {
        return userOtherName;
    }

    /**
     * Sets the value of the userOtherName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserOtherName(String value) {
        this.userOtherName = value;
    }

    /**
     * Gets the value of the userUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserUID() {
        return userUID;
    }

    /**
     * Sets the value of the userUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserUID(String value) {
        this.userUID = value;
    }

}
