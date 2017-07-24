
package com.sds.acube.luxor.ws.client.rsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for iUser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="iUser">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="certificationID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="changedPWDDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="compID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="compName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="compOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="concurrent" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="delegate" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="deleted" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="deptID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deptName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deptOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="duty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dutyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dutyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dutyOrder" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dutyOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="employeeID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="existence" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="gradeAbbrName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradeOrder" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="gradeOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homeAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homeDetailAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homeFax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homePage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homeTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homeTel2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homeZipCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loginResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="mailServer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobile2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="officeAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="officeDetailAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="officeFax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="officeTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="officeTel2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="officeZipCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="optionalGTPName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgDisplayName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgDisplayOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PCOnlineID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pager" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="personInfoAgreeType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="positionAbbrName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="positionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="positionName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="positionOrder" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="positionOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proxy" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="reserved1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reserved2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reserved3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="residentNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roleCodes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="securityLevel" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sysMail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titleCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titleOrder" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="titleOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userOrder" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="userOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userRID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "iUser", propOrder = {
    "certificationID",
    "changedPWDDate",
    "compID",
    "compName",
    "compOtherName",
    "concurrent",
    "delegate",
    "deleted",
    "deptID",
    "deptName",
    "deptOtherName",
    "description",
    "duty",
    "dutyCode",
    "dutyName",
    "dutyOrder",
    "dutyOtherName",
    "email",
    "employeeID",
    "existence",
    "gradeAbbrName",
    "gradeCode",
    "gradeName",
    "gradeOrder",
    "gradeOtherName",
    "groupID",
    "groupName",
    "groupOtherName",
    "homeAddr",
    "homeDetailAddr",
    "homeFax",
    "homePage",
    "homeTel",
    "homeTel2",
    "homeZipCode",
    "loginResult",
    "mailServer",
    "mobile",
    "mobile2",
    "officeAddr",
    "officeDetailAddr",
    "officeFax",
    "officeTel",
    "officeTel2",
    "officeZipCode",
    "optionalGTPName",
    "orgDisplayName",
    "orgDisplayOtherName",
    "pcOnlineID",
    "pager",
    "partID",
    "partName",
    "partOtherName",
    "personInfoAgreeType",
    "positionAbbrName",
    "positionCode",
    "positionName",
    "positionOrder",
    "positionOtherName",
    "proxy",
    "reserved1",
    "reserved2",
    "reserved3",
    "residentNo",
    "roleCodes",
    "securityLevel",
    "sysMail",
    "titleCode",
    "titleName",
    "titleOrder",
    "titleOtherName",
    "userID",
    "userName",
    "userOrder",
    "userOtherName",
    "userRID",
    "userStatus",
    "userUID"
})
public class IUser {

    protected String certificationID;
    protected String changedPWDDate;
    protected String compID;
    protected String compName;
    protected String compOtherName;
    protected boolean concurrent;
    protected boolean delegate;
    protected boolean deleted;
    protected String deptID;
    protected String deptName;
    protected String deptOtherName;
    protected String description;
    protected String duty;
    protected String dutyCode;
    protected String dutyName;
    protected int dutyOrder;
    protected String dutyOtherName;
    protected String email;
    protected String employeeID;
    protected boolean existence;
    protected String gradeAbbrName;
    protected String gradeCode;
    protected String gradeName;
    protected int gradeOrder;
    protected String gradeOtherName;
    protected String groupID;
    protected String groupName;
    protected String groupOtherName;
    protected String homeAddr;
    protected String homeDetailAddr;
    protected String homeFax;
    protected String homePage;
    protected String homeTel;
    protected String homeTel2;
    protected String homeZipCode;
    protected int loginResult;
    protected String mailServer;
    protected String mobile;
    protected String mobile2;
    protected String officeAddr;
    protected String officeDetailAddr;
    protected String officeFax;
    protected String officeTel;
    protected String officeTel2;
    protected String officeZipCode;
    protected String optionalGTPName;
    protected String orgDisplayName;
    protected String orgDisplayOtherName;
    @XmlElement(name = "PCOnlineID")
    protected String pcOnlineID;
    protected String pager;
    protected String partID;
    protected String partName;
    protected String partOtherName;
    protected int personInfoAgreeType;
    protected String positionAbbrName;
    protected String positionCode;
    protected String positionName;
    protected int positionOrder;
    protected String positionOtherName;
    protected boolean proxy;
    protected String reserved1;
    protected String reserved2;
    protected String reserved3;
    protected String residentNo;
    protected String roleCodes;
    protected int securityLevel;
    protected String sysMail;
    protected String titleCode;
    protected String titleName;
    protected int titleOrder;
    protected String titleOtherName;
    protected String userID;
    protected String userName;
    protected int userOrder;
    protected String userOtherName;
    protected String userRID;
    protected String userStatus;
    protected String userUID;

    /**
     * Gets the value of the certificationID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertificationID() {
        return certificationID;
    }

    /**
     * Sets the value of the certificationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertificationID(String value) {
        this.certificationID = value;
    }

    /**
     * Gets the value of the changedPWDDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChangedPWDDate() {
        return changedPWDDate;
    }

    /**
     * Sets the value of the changedPWDDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChangedPWDDate(String value) {
        this.changedPWDDate = value;
    }

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
     * Gets the value of the concurrent property.
     * 
     */
    public boolean isConcurrent() {
        return concurrent;
    }

    /**
     * Sets the value of the concurrent property.
     * 
     */
    public void setConcurrent(boolean value) {
        this.concurrent = value;
    }

    /**
     * Gets the value of the delegate property.
     * 
     */
    public boolean isDelegate() {
        return delegate;
    }

    /**
     * Sets the value of the delegate property.
     * 
     */
    public void setDelegate(boolean value) {
        this.delegate = value;
    }

    /**
     * Gets the value of the deleted property.
     * 
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Sets the value of the deleted property.
     * 
     */
    public void setDeleted(boolean value) {
        this.deleted = value;
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
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the duty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDuty() {
        return duty;
    }

    /**
     * Sets the value of the duty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDuty(String value) {
        this.duty = value;
    }

    /**
     * Gets the value of the dutyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDutyCode() {
        return dutyCode;
    }

    /**
     * Sets the value of the dutyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDutyCode(String value) {
        this.dutyCode = value;
    }

    /**
     * Gets the value of the dutyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDutyName() {
        return dutyName;
    }

    /**
     * Sets the value of the dutyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDutyName(String value) {
        this.dutyName = value;
    }

    /**
     * Gets the value of the dutyOrder property.
     * 
     */
    public int getDutyOrder() {
        return dutyOrder;
    }

    /**
     * Sets the value of the dutyOrder property.
     * 
     */
    public void setDutyOrder(int value) {
        this.dutyOrder = value;
    }

    /**
     * Gets the value of the dutyOtherName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDutyOtherName() {
        return dutyOtherName;
    }

    /**
     * Sets the value of the dutyOtherName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDutyOtherName(String value) {
        this.dutyOtherName = value;
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
     * Gets the value of the employeeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * Sets the value of the employeeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployeeID(String value) {
        this.employeeID = value;
    }

    /**
     * Gets the value of the existence property.
     * 
     */
    public boolean isExistence() {
        return existence;
    }

    /**
     * Sets the value of the existence property.
     * 
     */
    public void setExistence(boolean value) {
        this.existence = value;
    }

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
     * Gets the value of the homeAddr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeAddr() {
        return homeAddr;
    }

    /**
     * Sets the value of the homeAddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeAddr(String value) {
        this.homeAddr = value;
    }

    /**
     * Gets the value of the homeDetailAddr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeDetailAddr() {
        return homeDetailAddr;
    }

    /**
     * Sets the value of the homeDetailAddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeDetailAddr(String value) {
        this.homeDetailAddr = value;
    }

    /**
     * Gets the value of the homeFax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeFax() {
        return homeFax;
    }

    /**
     * Sets the value of the homeFax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeFax(String value) {
        this.homeFax = value;
    }

    /**
     * Gets the value of the homePage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomePage() {
        return homePage;
    }

    /**
     * Sets the value of the homePage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomePage(String value) {
        this.homePage = value;
    }

    /**
     * Gets the value of the homeTel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeTel() {
        return homeTel;
    }

    /**
     * Sets the value of the homeTel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeTel(String value) {
        this.homeTel = value;
    }

    /**
     * Gets the value of the homeTel2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeTel2() {
        return homeTel2;
    }

    /**
     * Sets the value of the homeTel2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeTel2(String value) {
        this.homeTel2 = value;
    }

    /**
     * Gets the value of the homeZipCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeZipCode() {
        return homeZipCode;
    }

    /**
     * Sets the value of the homeZipCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeZipCode(String value) {
        this.homeZipCode = value;
    }

    /**
     * Gets the value of the loginResult property.
     * 
     */
    public int getLoginResult() {
        return loginResult;
    }

    /**
     * Sets the value of the loginResult property.
     * 
     */
    public void setLoginResult(int value) {
        this.loginResult = value;
    }

    /**
     * Gets the value of the mailServer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailServer() {
        return mailServer;
    }

    /**
     * Sets the value of the mailServer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailServer(String value) {
        this.mailServer = value;
    }

    /**
     * Gets the value of the mobile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Sets the value of the mobile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobile(String value) {
        this.mobile = value;
    }

    /**
     * Gets the value of the mobile2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobile2() {
        return mobile2;
    }

    /**
     * Sets the value of the mobile2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobile2(String value) {
        this.mobile2 = value;
    }

    /**
     * Gets the value of the officeAddr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficeAddr() {
        return officeAddr;
    }

    /**
     * Sets the value of the officeAddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficeAddr(String value) {
        this.officeAddr = value;
    }

    /**
     * Gets the value of the officeDetailAddr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficeDetailAddr() {
        return officeDetailAddr;
    }

    /**
     * Sets the value of the officeDetailAddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficeDetailAddr(String value) {
        this.officeDetailAddr = value;
    }

    /**
     * Gets the value of the officeFax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficeFax() {
        return officeFax;
    }

    /**
     * Sets the value of the officeFax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficeFax(String value) {
        this.officeFax = value;
    }

    /**
     * Gets the value of the officeTel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficeTel() {
        return officeTel;
    }

    /**
     * Sets the value of the officeTel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficeTel(String value) {
        this.officeTel = value;
    }

    /**
     * Gets the value of the officeTel2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficeTel2() {
        return officeTel2;
    }

    /**
     * Sets the value of the officeTel2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficeTel2(String value) {
        this.officeTel2 = value;
    }

    /**
     * Gets the value of the officeZipCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficeZipCode() {
        return officeZipCode;
    }

    /**
     * Sets the value of the officeZipCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficeZipCode(String value) {
        this.officeZipCode = value;
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
     * Gets the value of the pcOnlineID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPCOnlineID() {
        return pcOnlineID;
    }

    /**
     * Sets the value of the pcOnlineID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPCOnlineID(String value) {
        this.pcOnlineID = value;
    }

    /**
     * Gets the value of the pager property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPager() {
        return pager;
    }

    /**
     * Sets the value of the pager property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPager(String value) {
        this.pager = value;
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
     * Gets the value of the personInfoAgreeType property.
     * 
     */
    public int getPersonInfoAgreeType() {
        return personInfoAgreeType;
    }

    /**
     * Sets the value of the personInfoAgreeType property.
     * 
     */
    public void setPersonInfoAgreeType(int value) {
        this.personInfoAgreeType = value;
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
     * Gets the value of the proxy property.
     * 
     */
    public boolean isProxy() {
        return proxy;
    }

    /**
     * Sets the value of the proxy property.
     * 
     */
    public void setProxy(boolean value) {
        this.proxy = value;
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
     * Gets the value of the residentNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResidentNo() {
        return residentNo;
    }

    /**
     * Sets the value of the residentNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResidentNo(String value) {
        this.residentNo = value;
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
     * Gets the value of the securityLevel property.
     * 
     */
    public int getSecurityLevel() {
        return securityLevel;
    }

    /**
     * Sets the value of the securityLevel property.
     * 
     */
    public void setSecurityLevel(int value) {
        this.securityLevel = value;
    }

    /**
     * Gets the value of the sysMail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSysMail() {
        return sysMail;
    }

    /**
     * Sets the value of the sysMail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSysMail(String value) {
        this.sysMail = value;
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

    /**
     * Gets the value of the userStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserStatus() {
        return userStatus;
    }

    /**
     * Sets the value of the userStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserStatus(String value) {
        this.userStatus = value;
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
