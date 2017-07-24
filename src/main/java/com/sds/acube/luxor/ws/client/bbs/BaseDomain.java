
package com.sds.acube.luxor.ws.client.bbs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for baseDomain complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="baseDomain">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.bbs.collaboration.luxor.acube.sds.com/}searchVO">
 *       &lt;sequence>
 *         &lt;element name="accessIdList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="accessInfos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aclId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aclList" type="{http://ws.bbs.collaboration.luxor.acube.sds.com/}comAcl" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="adminUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="callbackUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="comDbUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creatorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="guid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isAjx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="langType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loginIp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="memberAcl" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pageSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="portalId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resourceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resourceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resourceNameAll" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resourceType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="searchDateOption" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="searchEndDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="searchStartDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sessionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sortBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sortType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tenantId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userAgent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userDeptName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userDeptid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userPermission" type="{http://ws.bbs.collaboration.luxor.acube.sds.com/}basePermission" minOccurs="0"/>
 *         &lt;element name="userProfile" type="{http://ws.bbs.collaboration.luxor.acube.sds.com/}baseUserProfile" minOccurs="0"/>
 *         &lt;element name="userUid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cPage" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "baseDomain", propOrder = {
    "accessIdList",
    "accessInfos",
    "aclId",
    "aclList",
    "adminUser",
    "callbackUrl",
    "comDbUser",
    "creatorName",
    "endDate",
    "guid",
    "isAjx",
    "langType",
    "loginIp",
    "memberAcl",
    "pageSize",
    "portalId",
    "resourceId",
    "resourceName",
    "resourceNameAll",
    "resourceType",
    "searchDateOption",
    "searchEndDate",
    "searchStartDate",
    "sessionId",
    "sortBy",
    "sortType",
    "startDate",
    "tenantId",
    "userAgent",
    "userDeptName",
    "userDeptid",
    "userName",
    "userPermission",
    "userProfile",
    "userUid",
    "cPage"
})
@XmlSeeAlso({
    ComAcl.class,
    ComCode.class,
    BbsHeading.class,
    BbsTag.class,
    BbsColumnMaster.class,
    BbsPostCommon.class,
    BbsBoardMaster.class,
    BbsBoardCategory.class,
    BaseUserProfile.class
})
public class BaseDomain
    extends SearchVO
{

    @XmlElement(nillable = true)
    protected List<String> accessIdList;
    protected String accessInfos;
    protected String aclId;
    @XmlElement(nillable = true)
    protected List<ComAcl> aclList;
    protected String adminUser;
    protected String callbackUrl;
    protected String comDbUser;
    protected String creatorName;
    protected String endDate;
    protected String guid;
    protected String isAjx;
    protected String langType;
    protected String loginIp;
    protected int memberAcl;
    protected int pageSize;
    protected String portalId;
    protected String resourceId;
    protected String resourceName;
    protected String resourceNameAll;
    protected String resourceType;
    protected String searchDateOption;
    protected String searchEndDate;
    protected String searchStartDate;
    protected String sessionId;
    protected String sortBy;
    protected String sortType;
    protected String startDate;
    protected String tenantId;
    protected String userAgent;
    protected String userDeptName;
    protected String userDeptid;
    protected String userName;
    protected BasePermission userPermission;
    protected BaseUserProfile userProfile;
    protected String userUid;
    protected int cPage;

    /**
     * Gets the value of the accessIdList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accessIdList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccessIdList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAccessIdList() {
        if (accessIdList == null) {
            accessIdList = new ArrayList<String>();
        }
        return this.accessIdList;
    }

    /**
     * Gets the value of the accessInfos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessInfos() {
        return accessInfos;
    }

    /**
     * Sets the value of the accessInfos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessInfos(String value) {
        this.accessInfos = value;
    }

    /**
     * Gets the value of the aclId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAclId() {
        return aclId;
    }

    /**
     * Sets the value of the aclId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAclId(String value) {
        this.aclId = value;
    }

    /**
     * Gets the value of the aclList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aclList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAclList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComAcl }
     * 
     * 
     */
    public List<ComAcl> getAclList() {
        if (aclList == null) {
            aclList = new ArrayList<ComAcl>();
        }
        return this.aclList;
    }

    /**
     * Gets the value of the adminUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdminUser() {
        return adminUser;
    }

    /**
     * Sets the value of the adminUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdminUser(String value) {
        this.adminUser = value;
    }

    /**
     * Gets the value of the callbackUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallbackUrl() {
        return callbackUrl;
    }

    /**
     * Sets the value of the callbackUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallbackUrl(String value) {
        this.callbackUrl = value;
    }

    /**
     * Gets the value of the comDbUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComDbUser() {
        return comDbUser;
    }

    /**
     * Sets the value of the comDbUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComDbUser(String value) {
        this.comDbUser = value;
    }

    /**
     * Gets the value of the creatorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * Sets the value of the creatorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatorName(String value) {
        this.creatorName = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndDate(String value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the guid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuid() {
        return guid;
    }

    /**
     * Sets the value of the guid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuid(String value) {
        this.guid = value;
    }

    /**
     * Gets the value of the isAjx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsAjx() {
        return isAjx;
    }

    /**
     * Sets the value of the isAjx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsAjx(String value) {
        this.isAjx = value;
    }

    /**
     * Gets the value of the langType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLangType() {
        return langType;
    }

    /**
     * Sets the value of the langType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLangType(String value) {
        this.langType = value;
    }

    /**
     * Gets the value of the loginIp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * Sets the value of the loginIp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginIp(String value) {
        this.loginIp = value;
    }

    /**
     * Gets the value of the memberAcl property.
     * 
     */
    public int getMemberAcl() {
        return memberAcl;
    }

    /**
     * Sets the value of the memberAcl property.
     * 
     */
    public void setMemberAcl(int value) {
        this.memberAcl = value;
    }

    /**
     * Gets the value of the pageSize property.
     * 
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets the value of the pageSize property.
     * 
     */
    public void setPageSize(int value) {
        this.pageSize = value;
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
     * Gets the value of the resourceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * Sets the value of the resourceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResourceName(String value) {
        this.resourceName = value;
    }

    /**
     * Gets the value of the resourceNameAll property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResourceNameAll() {
        return resourceNameAll;
    }

    /**
     * Sets the value of the resourceNameAll property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResourceNameAll(String value) {
        this.resourceNameAll = value;
    }

    /**
     * Gets the value of the resourceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * Sets the value of the resourceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResourceType(String value) {
        this.resourceType = value;
    }

    /**
     * Gets the value of the searchDateOption property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchDateOption() {
        return searchDateOption;
    }

    /**
     * Sets the value of the searchDateOption property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchDateOption(String value) {
        this.searchDateOption = value;
    }

    /**
     * Gets the value of the searchEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchEndDate() {
        return searchEndDate;
    }

    /**
     * Sets the value of the searchEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchEndDate(String value) {
        this.searchEndDate = value;
    }

    /**
     * Gets the value of the searchStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchStartDate() {
        return searchStartDate;
    }

    /**
     * Sets the value of the searchStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchStartDate(String value) {
        this.searchStartDate = value;
    }

    /**
     * Gets the value of the sessionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Sets the value of the sessionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionId(String value) {
        this.sessionId = value;
    }

    /**
     * Gets the value of the sortBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSortBy() {
        return sortBy;
    }

    /**
     * Sets the value of the sortBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSortBy(String value) {
        this.sortBy = value;
    }

    /**
     * Gets the value of the sortType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSortType() {
        return sortType;
    }

    /**
     * Sets the value of the sortType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSortType(String value) {
        this.sortType = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartDate(String value) {
        this.startDate = value;
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

    /**
     * Gets the value of the userAgent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * Sets the value of the userAgent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserAgent(String value) {
        this.userAgent = value;
    }

    /**
     * Gets the value of the userDeptName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserDeptName() {
        return userDeptName;
    }

    /**
     * Sets the value of the userDeptName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserDeptName(String value) {
        this.userDeptName = value;
    }

    /**
     * Gets the value of the userDeptid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserDeptid() {
        return userDeptid;
    }

    /**
     * Sets the value of the userDeptid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserDeptid(String value) {
        this.userDeptid = value;
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

    /**
     * Gets the value of the userProfile property.
     * 
     * @return
     *     possible object is
     *     {@link BaseUserProfile }
     *     
     */
    public BaseUserProfile getUserProfile() {
        return userProfile;
    }

    /**
     * Sets the value of the userProfile property.
     * 
     * @param value
     *     allowed object is
     *     {@link BaseUserProfile }
     *     
     */
    public void setUserProfile(BaseUserProfile value) {
        this.userProfile = value;
    }

    /**
     * Gets the value of the userUid property.
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
     * Sets the value of the userUid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserUid(String value) {
        this.userUid = value;
    }

    /**
     * Gets the value of the cPage property.
     * 
     */
    public int getCPage() {
        return cPage;
    }

    /**
     * Sets the value of the cPage property.
     * 
     */
    public void setCPage(int value) {
        this.cPage = value;
    }

}
