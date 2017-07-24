
package com.sds.acube.luxor.ws.client.bbs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for baseUserProfile complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="baseUserProfile">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.bbs.collaboration.luxor.acube.sds.com/}baseDomain">
 *       &lt;sequence>
 *         &lt;element name="changedPasswordDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="compId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="compName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="compOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departmentList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="deptId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deptName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deptOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dutyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dutyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dutyOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="empId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradeOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homeTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isAdmin" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="loginEncResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loginId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loginResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="mobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="officeFax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="officeTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="positionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="positionName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="positionOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roleCodes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="securityLevel" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sysMail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titleCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titleOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tranferId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="xid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="xpw" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="xtm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eMail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "baseUserProfile", propOrder = {
    "changedPasswordDate",
    "compId",
    "compName",
    "compOtherName",
    "departmentList",
    "deptId",
    "deptName",
    "deptOtherName",
    "dutyCode",
    "dutyName",
    "dutyOtherName",
    "empId",
    "gradeCode",
    "gradeName",
    "gradeOtherName",
    "groupId",
    "groupName",
    "homeTel",
    "isAdmin",
    "loginEncResult",
    "loginId",
    "loginResult",
    "mobile",
    "officeFax",
    "officeTel",
    "partId",
    "partName",
    "password",
    "positionCode",
    "positionName",
    "positionOtherName",
    "roleCodes",
    "securityLevel",
    "sysMail",
    "titleCode",
    "titleName",
    "titleOtherName",
    "tranferId",
    "userOtherName",
    "userStatus",
    "userType",
    "xid",
    "xpw",
    "xtm",
    "eMail"
})
public class BaseUserProfile
    extends BaseDomain
{

    protected String changedPasswordDate;
    protected String compId;
    protected String compName;
    protected String compOtherName;
    @XmlElement(nillable = true)
    protected List<String> departmentList;
    protected String deptId;
    protected String deptName;
    protected String deptOtherName;
    protected String dutyCode;
    protected String dutyName;
    protected String dutyOtherName;
    protected String empId;
    protected String gradeCode;
    protected String gradeName;
    protected String gradeOtherName;
    protected String groupId;
    protected String groupName;
    protected String homeTel;
    protected boolean isAdmin;
    protected String loginEncResult;
    protected String loginId;
    protected int loginResult;
    protected String mobile;
    protected String officeFax;
    protected String officeTel;
    protected String partId;
    protected String partName;
    protected String password;
    protected String positionCode;
    protected String positionName;
    protected String positionOtherName;
    protected String roleCodes;
    protected int securityLevel;
    protected String sysMail;
    protected String titleCode;
    protected String titleName;
    protected String titleOtherName;
    protected String tranferId;
    protected String userOtherName;
    protected String userStatus;
    protected String userType;
    protected String xid;
    protected String xpw;
    protected String xtm;
    protected String eMail;

    /**
     * Gets the value of the changedPasswordDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChangedPasswordDate() {
        return changedPasswordDate;
    }

    /**
     * Sets the value of the changedPasswordDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChangedPasswordDate(String value) {
        this.changedPasswordDate = value;
    }

    /**
     * Gets the value of the compId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompId() {
        return compId;
    }

    /**
     * Sets the value of the compId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompId(String value) {
        this.compId = value;
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
     * Gets the value of the departmentList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the departmentList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDepartmentList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDepartmentList() {
        if (departmentList == null) {
            departmentList = new ArrayList<String>();
        }
        return this.departmentList;
    }

    /**
     * Gets the value of the deptId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeptId() {
        return deptId;
    }

    /**
     * Sets the value of the deptId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeptId(String value) {
        this.deptId = value;
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
     * Gets the value of the empId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmpId() {
        return empId;
    }

    /**
     * Sets the value of the empId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmpId(String value) {
        this.empId = value;
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
     * Gets the value of the groupId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * Sets the value of the groupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupId(String value) {
        this.groupId = value;
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
     * Gets the value of the isAdmin property.
     * 
     */
    public boolean isIsAdmin() {
        return isAdmin;
    }

    /**
     * Sets the value of the isAdmin property.
     * 
     */
    public void setIsAdmin(boolean value) {
        this.isAdmin = value;
    }

    /**
     * Gets the value of the loginEncResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginEncResult() {
        return loginEncResult;
    }

    /**
     * Sets the value of the loginEncResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginEncResult(String value) {
        this.loginEncResult = value;
    }

    /**
     * Gets the value of the loginId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * Sets the value of the loginId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginId(String value) {
        this.loginId = value;
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
     * Gets the value of the partId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartId() {
        return partId;
    }

    /**
     * Sets the value of the partId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartId(String value) {
        this.partId = value;
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
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
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
     * Gets the value of the tranferId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTranferId() {
        return tranferId;
    }

    /**
     * Sets the value of the tranferId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTranferId(String value) {
        this.tranferId = value;
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
     * Gets the value of the userType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Sets the value of the userType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserType(String value) {
        this.userType = value;
    }

    /**
     * Gets the value of the xid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXid() {
        return xid;
    }

    /**
     * Sets the value of the xid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXid(String value) {
        this.xid = value;
    }

    /**
     * Gets the value of the xpw property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXpw() {
        return xpw;
    }

    /**
     * Sets the value of the xpw property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXpw(String value) {
        this.xpw = value;
    }

    /**
     * Gets the value of the xtm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXtm() {
        return xtm;
    }

    /**
     * Sets the value of the xtm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXtm(String value) {
        this.xtm = value;
    }

    /**
     * Gets the value of the eMail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEMail() {
        return eMail;
    }

    /**
     * Sets the value of the eMail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEMail(String value) {
        this.eMail = value;
    }

}
