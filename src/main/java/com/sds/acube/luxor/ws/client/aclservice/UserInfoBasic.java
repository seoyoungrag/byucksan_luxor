
package com.sds.acube.luxor.ws.client.aclservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>userInfoBasic complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="userInfoBasic">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.org.cn.acube.sds.com/}baseDMO">
 *       &lt;sequence>
 *         &lt;element name="certificationId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="compId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="compName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="compOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="defaultUser" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="deptId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deptName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deptOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="displayOrder" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="dutyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dutyInfo" type="{http://service.org.cn.acube.sds.com/}dutyInfo" minOccurs="0"/>
 *         &lt;element name="employeeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradeInfo" type="{http://service.org.cn.acube.sds.com/}gradeInfo" minOccurs="0"/>
 *         &lt;element name="groupCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupList" type="{http://service.org.cn.acube.sds.com/}groupInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="groupName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupNames" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isConcurrent" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="isDelegate" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="isDeleted" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="isExistence" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="isProxy" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="optionalGtpName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgDisplayName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgDisplayOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="personInfoAgreeType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="positionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="positionInfo" type="{http://service.org.cn.acube.sds.com/}positionInfo" minOccurs="0"/>
 *         &lt;element name="reserved1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reserved2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reserved3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="residentNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roleCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roleList" type="{http://service.org.cn.acube.sds.com/}roleInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="roleNames" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="securityLevel" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="servers" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sysmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titleCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titleInfo" type="{http://service.org.cn.acube.sds.com/}titleInfo" minOccurs="0"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userOrder" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="userOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userRid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userUid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userInfoBasic", propOrder = {
    "certificationId",
    "compId",
    "compName",
    "compOtherName",
    "defaultUser",
    "deptId",
    "deptName",
    "deptOtherName",
    "description",
    "displayOrder",
    "dutyCode",
    "dutyInfo",
    "employeeId",
    "gradeCode",
    "gradeInfo",
    "groupCode",
    "groupId",
    "groupList",
    "groupName",
    "groupNames",
    "groupOtherName",
    "isConcurrent",
    "isDelegate",
    "isDeleted",
    "isExistence",
    "isProxy",
    "optionalGtpName",
    "orgDisplayName",
    "orgDisplayOtherName",
    "partId",
    "partName",
    "partOtherName",
    "personInfoAgreeType",
    "positionCode",
    "positionInfo",
    "reserved1",
    "reserved2",
    "reserved3",
    "residentNo",
    "roleCode",
    "roleList",
    "roleNames",
    "securityLevel",
    "servers",
    "sysmail",
    "titleCode",
    "titleInfo",
    "userId",
    "userName",
    "userOrder",
    "userOtherName",
    "userRid",
    "userUid"
})
public class UserInfoBasic
    extends BaseDMO
{

    protected String certificationId;
    protected String compId;
    protected String compName;
    protected String compOtherName;
    protected Integer defaultUser;
    protected String deptId;
    protected String deptName;
    protected String deptOtherName;
    protected String description;
    protected Integer displayOrder;
    protected String dutyCode;
    protected DutyInfo dutyInfo;
    protected String employeeId;
    protected String gradeCode;
    protected GradeInfo gradeInfo;
    protected String groupCode;
    protected String groupId;
    @XmlElement(nillable = true)
    protected List<GroupInfo> groupList;
    protected String groupName;
    protected String groupNames;
    protected String groupOtherName;
    protected Integer isConcurrent;
    protected Integer isDelegate;
    protected Integer isDeleted;
    protected Integer isExistence;
    protected Integer isProxy;
    protected String optionalGtpName;
    protected String orgDisplayName;
    protected String orgDisplayOtherName;
    protected String partId;
    protected String partName;
    protected String partOtherName;
    protected Integer personInfoAgreeType;
    protected String positionCode;
    protected PositionInfo positionInfo;
    protected String reserved1;
    protected String reserved2;
    protected String reserved3;
    protected String residentNo;
    protected String roleCode;
    @XmlElement(nillable = true)
    protected List<RoleInfo> roleList;
    protected String roleNames;
    protected Integer securityLevel;
    protected String servers;
    protected String sysmail;
    protected String titleCode;
    protected TitleInfo titleInfo;
    protected String userId;
    protected String userName;
    protected Integer userOrder;
    protected String userOtherName;
    protected String userRid;
    protected String userUid;

    /**
     * certificationId 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertificationId() {
        return certificationId;
    }

    /**
     * certificationId 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertificationId(String value) {
        this.certificationId = value;
    }

    /**
     * compId 속성의 값을 가져옵니다.
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
     * compId 속성의 값을 설정합니다.
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
     * compName 속성의 값을 가져옵니다.
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
     * compName 속성의 값을 설정합니다.
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
     * compOtherName 속성의 값을 가져옵니다.
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
     * compOtherName 속성의 값을 설정합니다.
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
     * defaultUser 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDefaultUser() {
        return defaultUser;
    }

    /**
     * defaultUser 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDefaultUser(Integer value) {
        this.defaultUser = value;
    }

    /**
     * deptId 속성의 값을 가져옵니다.
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
     * deptId 속성의 값을 설정합니다.
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
     * deptName 속성의 값을 가져옵니다.
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
     * deptName 속성의 값을 설정합니다.
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
     * deptOtherName 속성의 값을 가져옵니다.
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
     * deptOtherName 속성의 값을 설정합니다.
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
     * description 속성의 값을 가져옵니다.
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
     * description 속성의 값을 설정합니다.
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
     * displayOrder 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    /**
     * displayOrder 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDisplayOrder(Integer value) {
        this.displayOrder = value;
    }

    /**
     * dutyCode 속성의 값을 가져옵니다.
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
     * dutyCode 속성의 값을 설정합니다.
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
     * dutyInfo 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link DutyInfo }
     *     
     */
    public DutyInfo getDutyInfo() {
        return dutyInfo;
    }

    /**
     * dutyInfo 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link DutyInfo }
     *     
     */
    public void setDutyInfo(DutyInfo value) {
        this.dutyInfo = value;
    }

    /**
     * employeeId 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * employeeId 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployeeId(String value) {
        this.employeeId = value;
    }

    /**
     * gradeCode 속성의 값을 가져옵니다.
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
     * gradeCode 속성의 값을 설정합니다.
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
     * gradeInfo 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link GradeInfo }
     *     
     */
    public GradeInfo getGradeInfo() {
        return gradeInfo;
    }

    /**
     * gradeInfo 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link GradeInfo }
     *     
     */
    public void setGradeInfo(GradeInfo value) {
        this.gradeInfo = value;
    }

    /**
     * groupCode 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupCode() {
        return groupCode;
    }

    /**
     * groupCode 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupCode(String value) {
        this.groupCode = value;
    }

    /**
     * groupId 속성의 값을 가져옵니다.
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
     * groupId 속성의 값을 설정합니다.
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
     * Gets the value of the groupList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the groupList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGroupList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GroupInfo }
     * 
     * 
     */
    public List<GroupInfo> getGroupList() {
        if (groupList == null) {
            groupList = new ArrayList<GroupInfo>();
        }
        return this.groupList;
    }

    /**
     * groupName 속성의 값을 가져옵니다.
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
     * groupName 속성의 값을 설정합니다.
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
     * groupNames 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupNames() {
        return groupNames;
    }

    /**
     * groupNames 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupNames(String value) {
        this.groupNames = value;
    }

    /**
     * groupOtherName 속성의 값을 가져옵니다.
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
     * groupOtherName 속성의 값을 설정합니다.
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
     * isConcurrent 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIsConcurrent() {
        return isConcurrent;
    }

    /**
     * isConcurrent 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIsConcurrent(Integer value) {
        this.isConcurrent = value;
    }

    /**
     * isDelegate 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIsDelegate() {
        return isDelegate;
    }

    /**
     * isDelegate 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIsDelegate(Integer value) {
        this.isDelegate = value;
    }

    /**
     * isDeleted 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * isDeleted 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIsDeleted(Integer value) {
        this.isDeleted = value;
    }

    /**
     * isExistence 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIsExistence() {
        return isExistence;
    }

    /**
     * isExistence 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIsExistence(Integer value) {
        this.isExistence = value;
    }

    /**
     * isProxy 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIsProxy() {
        return isProxy;
    }

    /**
     * isProxy 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIsProxy(Integer value) {
        this.isProxy = value;
    }

    /**
     * optionalGtpName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOptionalGtpName() {
        return optionalGtpName;
    }

    /**
     * optionalGtpName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOptionalGtpName(String value) {
        this.optionalGtpName = value;
    }

    /**
     * orgDisplayName 속성의 값을 가져옵니다.
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
     * orgDisplayName 속성의 값을 설정합니다.
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
     * orgDisplayOtherName 속성의 값을 가져옵니다.
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
     * orgDisplayOtherName 속성의 값을 설정합니다.
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
     * partId 속성의 값을 가져옵니다.
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
     * partId 속성의 값을 설정합니다.
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
     * partName 속성의 값을 가져옵니다.
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
     * partName 속성의 값을 설정합니다.
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
     * partOtherName 속성의 값을 가져옵니다.
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
     * partOtherName 속성의 값을 설정합니다.
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
     * personInfoAgreeType 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPersonInfoAgreeType() {
        return personInfoAgreeType;
    }

    /**
     * personInfoAgreeType 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPersonInfoAgreeType(Integer value) {
        this.personInfoAgreeType = value;
    }

    /**
     * positionCode 속성의 값을 가져옵니다.
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
     * positionCode 속성의 값을 설정합니다.
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
     * positionInfo 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link PositionInfo }
     *     
     */
    public PositionInfo getPositionInfo() {
        return positionInfo;
    }

    /**
     * positionInfo 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link PositionInfo }
     *     
     */
    public void setPositionInfo(PositionInfo value) {
        this.positionInfo = value;
    }

    /**
     * reserved1 속성의 값을 가져옵니다.
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
     * reserved1 속성의 값을 설정합니다.
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
     * reserved2 속성의 값을 가져옵니다.
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
     * reserved2 속성의 값을 설정합니다.
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
     * reserved3 속성의 값을 가져옵니다.
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
     * reserved3 속성의 값을 설정합니다.
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
     * residentNo 속성의 값을 가져옵니다.
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
     * residentNo 속성의 값을 설정합니다.
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
     * roleCode 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * roleCode 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoleCode(String value) {
        this.roleCode = value;
    }

    /**
     * Gets the value of the roleList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roleList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoleList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RoleInfo }
     * 
     * 
     */
    public List<RoleInfo> getRoleList() {
        if (roleList == null) {
            roleList = new ArrayList<RoleInfo>();
        }
        return this.roleList;
    }

    /**
     * roleNames 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoleNames() {
        return roleNames;
    }

    /**
     * roleNames 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoleNames(String value) {
        this.roleNames = value;
    }

    /**
     * securityLevel 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSecurityLevel() {
        return securityLevel;
    }

    /**
     * securityLevel 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSecurityLevel(Integer value) {
        this.securityLevel = value;
    }

    /**
     * servers 속성의 값을 가져옵니다.
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
     * servers 속성의 값을 설정합니다.
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
     * sysmail 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSysmail() {
        return sysmail;
    }

    /**
     * sysmail 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSysmail(String value) {
        this.sysmail = value;
    }

    /**
     * titleCode 속성의 값을 가져옵니다.
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
     * titleCode 속성의 값을 설정합니다.
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
     * titleInfo 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link TitleInfo }
     *     
     */
    public TitleInfo getTitleInfo() {
        return titleInfo;
    }

    /**
     * titleInfo 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link TitleInfo }
     *     
     */
    public void setTitleInfo(TitleInfo value) {
        this.titleInfo = value;
    }

    /**
     * userId 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserId() {
        return userId;
    }

    /**
     * userId 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserId(String value) {
        this.userId = value;
    }

    /**
     * userName 속성의 값을 가져옵니다.
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
     * userName 속성의 값을 설정합니다.
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
     * userOrder 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUserOrder() {
        return userOrder;
    }

    /**
     * userOrder 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUserOrder(Integer value) {
        this.userOrder = value;
    }

    /**
     * userOtherName 속성의 값을 가져옵니다.
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
     * userOtherName 속성의 값을 설정합니다.
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
     * userRid 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserRid() {
        return userRid;
    }

    /**
     * userRid 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserRid(String value) {
        this.userRid = value;
    }

    /**
     * userUid 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserUid() {
        return userUid;
    }

    /**
     * userUid 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserUid(String value) {
        this.userUid = value;
    }

}
