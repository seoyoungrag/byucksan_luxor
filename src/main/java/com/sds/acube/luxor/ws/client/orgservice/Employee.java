
package com.sds.acube.luxor.ws.client.orgservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for employee complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="employee">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.org.cn.acube.sds.com/}userCommon">
 *       &lt;sequence>
 *         &lt;element name="gradeAbbrName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradeOrder" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="gradeOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isConcurrent" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isDefaultUser" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isDelegate" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isExistence" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isProxy" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="optionalGTPName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="positionAbbrName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="positionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="positionName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="positionOrder" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="positionOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reserved1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reserved2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reserved3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roleCodes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="servers" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titleCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titleOrder" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="titleOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userOrder" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="userRID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "employee", propOrder = {
    "gradeAbbrName",
    "gradeCode",
    "gradeName",
    "gradeOrder",
    "gradeOtherName",
    "isConcurrent",
    "isDefaultUser",
    "isDelegate",
    "isExistence",
    "isProxy",
    "optionalGTPName",
    "positionAbbrName",
    "positionCode",
    "positionName",
    "positionOrder",
    "positionOtherName",
    "reserved1",
    "reserved2",
    "reserved3",
    "roleCodes",
    "servers",
    "titleCode",
    "titleName",
    "titleOrder",
    "titleOtherName",
    "userOrder",
    "userRID"
})
@XmlSeeAlso({
    Substitute.class,
    Signatory.class
})
public class Employee
    extends UserCommon
{

    protected String gradeAbbrName;
    protected String gradeCode;
    protected String gradeName;
    protected int gradeOrder;
    protected String gradeOtherName;
    protected boolean isConcurrent;
    protected boolean isDefaultUser;
    protected boolean isDelegate;
    protected boolean isExistence;
    protected boolean isProxy;
    protected String optionalGTPName;
    protected String positionAbbrName;
    protected String positionCode;
    protected String positionName;
    protected int positionOrder;
    protected String positionOtherName;
    protected String reserved1;
    protected String reserved2;
    protected String reserved3;
    protected String roleCodes;
    protected String servers;
    protected String titleCode;
    protected String titleName;
    protected int titleOrder;
    protected String titleOtherName;
    protected int userOrder;
    protected String userRID;

    /**
     * Gets the value of the gradeAbbrName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGradeAbbrName() {
        return gradeAbbrName;
    }

    /**
     * Sets the value of the gradeAbbrName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGradeAbbrName(String value) {
        this.gradeAbbrName = value;
    }

    /**
     * Gets the value of the gradeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGradeCode() {
        return gradeCode;
    }

    /**
     * Sets the value of the gradeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGradeCode(String value) {
        this.gradeCode = value;
    }

    /**
     * Gets the value of the gradeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGradeName() {
        return gradeName;
    }

    /**
     * Sets the value of the gradeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGradeName(String value) {
        this.gradeName = value;
    }

    /**
     * Gets the value of the gradeOrder property.
     * 
     */
    public int getGradeOrder() {
        return gradeOrder;
    }

    /**
     * Sets the value of the gradeOrder property.
     * 
     */
    public void setGradeOrder(int value) {
        this.gradeOrder = value;
    }

    /**
     * Gets the value of the gradeOtherName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGradeOtherName() {
        return gradeOtherName;
    }

    /**
     * Sets the value of the gradeOtherName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGradeOtherName(String value) {
        this.gradeOtherName = value;
    }

    /**
     * Gets the value of the isConcurrent property.
     * 
     */
    public boolean isIsConcurrent() {
        return isConcurrent;
    }

    /**
     * Sets the value of the isConcurrent property.
     * 
     */
    public void setIsConcurrent(boolean value) {
        this.isConcurrent = value;
    }

    /**
     * Gets the value of the isDefaultUser property.
     * 
     */
    public boolean isIsDefaultUser() {
        return isDefaultUser;
    }

    /**
     * Sets the value of the isDefaultUser property.
     * 
     */
    public void setIsDefaultUser(boolean value) {
        this.isDefaultUser = value;
    }

    /**
     * Gets the value of the isDelegate property.
     * 
     */
    public boolean isIsDelegate() {
        return isDelegate;
    }

    /**
     * Sets the value of the isDelegate property.
     * 
     */
    public void setIsDelegate(boolean value) {
        this.isDelegate = value;
    }

    /**
     * Gets the value of the isExistence property.
     * 
     */
    public boolean isIsExistence() {
        return isExistence;
    }

    /**
     * Sets the value of the isExistence property.
     * 
     */
    public void setIsExistence(boolean value) {
        this.isExistence = value;
    }

    /**
     * Gets the value of the isProxy property.
     * 
     */
    public boolean isIsProxy() {
        return isProxy;
    }

    /**
     * Sets the value of the isProxy property.
     * 
     */
    public void setIsProxy(boolean value) {
        this.isProxy = value;
    }

    /**
     * Gets the value of the optionalGTPName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOptionalGTPName() {
        return optionalGTPName;
    }

    /**
     * Sets the value of the optionalGTPName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOptionalGTPName(String value) {
        this.optionalGTPName = value;
    }

    /**
     * Gets the value of the positionAbbrName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPositionAbbrName() {
        return positionAbbrName;
    }

    /**
     * Sets the value of the positionAbbrName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPositionAbbrName(String value) {
        this.positionAbbrName = value;
    }

    /**
     * Gets the value of the positionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPositionCode() {
        return positionCode;
    }

    /**
     * Sets the value of the positionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPositionCode(String value) {
        this.positionCode = value;
    }

    /**
     * Gets the value of the positionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPositionName() {
        return positionName;
    }

    /**
     * Sets the value of the positionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPositionName(String value) {
        this.positionName = value;
    }

    /**
     * Gets the value of the positionOrder property.
     * 
     */
    public int getPositionOrder() {
        return positionOrder;
    }

    /**
     * Sets the value of the positionOrder property.
     * 
     */
    public void setPositionOrder(int value) {
        this.positionOrder = value;
    }

    /**
     * Gets the value of the positionOtherName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPositionOtherName() {
        return positionOtherName;
    }

    /**
     * Sets the value of the positionOtherName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPositionOtherName(String value) {
        this.positionOtherName = value;
    }

    /**
     * Gets the value of the reserved1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserved1() {
        return reserved1;
    }

    /**
     * Sets the value of the reserved1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserved1(String value) {
        this.reserved1 = value;
    }

    /**
     * Gets the value of the reserved2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserved2() {
        return reserved2;
    }

    /**
     * Sets the value of the reserved2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserved2(String value) {
        this.reserved2 = value;
    }

    /**
     * Gets the value of the reserved3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserved3() {
        return reserved3;
    }

    /**
     * Sets the value of the reserved3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserved3(String value) {
        this.reserved3 = value;
    }

    /**
     * Gets the value of the roleCodes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoleCodes() {
        return roleCodes;
    }

    /**
     * Sets the value of the roleCodes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoleCodes(String value) {
        this.roleCodes = value;
    }

    /**
     * Gets the value of the servers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServers() {
        return servers;
    }

    /**
     * Sets the value of the servers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServers(String value) {
        this.servers = value;
    }

    /**
     * Gets the value of the titleCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitleCode() {
        return titleCode;
    }

    /**
     * Sets the value of the titleCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitleCode(String value) {
        this.titleCode = value;
    }

    /**
     * Gets the value of the titleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitleName() {
        return titleName;
    }

    /**
     * Sets the value of the titleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitleName(String value) {
        this.titleName = value;
    }

    /**
     * Gets the value of the titleOrder property.
     * 
     */
    public int getTitleOrder() {
        return titleOrder;
    }

    /**
     * Sets the value of the titleOrder property.
     * 
     */
    public void setTitleOrder(int value) {
        this.titleOrder = value;
    }

    /**
     * Gets the value of the titleOtherName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitleOtherName() {
        return titleOtherName;
    }

    /**
     * Sets the value of the titleOtherName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitleOtherName(String value) {
        this.titleOtherName = value;
    }

    /**
     * Gets the value of the userOrder property.
     * 
     */
    public int getUserOrder() {
        return userOrder;
    }

    /**
     * Sets the value of the userOrder property.
     * 
     */
    public void setUserOrder(int value) {
        this.userOrder = value;
    }

    /**
     * Gets the value of the userRID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserRID() {
        return userRID;
    }

    /**
     * Sets the value of the userRID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserRID(String value) {
        this.userRID = value;
    }

}
