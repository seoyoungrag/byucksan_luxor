
package com.sds.acube.luxor.ws.client.rsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rscSearch complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rscSearch">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.rsc.collaboration.luxor.acube.sds.com/}baseDomain">
 *       &lt;sequence>
 *         &lt;element name="accessInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="admin" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="cellIndex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creatorUid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deliberatorId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isReserve" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isSearch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isSearchDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isSearchList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="placeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="placeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="privilege" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reserveId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rscId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rscSearchName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rscTypeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rscTypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="searchDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="searchTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rscSearch", propOrder = {
    "accessInfo",
    "admin",
    "cellIndex",
    "creatorUid",
    "deliberatorId",
    "isReserve",
    "isSearch",
    "isSearchDate",
    "isSearchList",
    "placeId",
    "placeName",
    "privilege",
    "reserveId",
    "rscId",
    "rscSearchName",
    "rscTypeId",
    "rscTypeName",
    "searchDate",
    "searchTime",
    "status",
    "type"
})
public class RscSearch
    extends BaseDomain
{

    protected String accessInfo;
    protected boolean admin;
    protected String cellIndex;
    protected String creatorUid;
    protected String deliberatorId;
    protected String isReserve;
    protected String isSearch;
    protected String isSearchDate;
    protected String isSearchList;
    protected String placeId;
    protected String placeName;
    protected String privilege;
    protected String reserveId;
    protected String rscId;
    protected String rscSearchName;
    protected String rscTypeId;
    protected String rscTypeName;
    protected String searchDate;
    protected String searchTime;
    protected String status;
    protected String type;

    /**
     * Gets the value of the accessInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessInfo() {
        return accessInfo;
    }

    /**
     * Sets the value of the accessInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessInfo(String value) {
        this.accessInfo = value;
    }

    /**
     * Gets the value of the admin property.
     * 
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Sets the value of the admin property.
     * 
     */
    public void setAdmin(boolean value) {
        this.admin = value;
    }

    /**
     * Gets the value of the cellIndex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCellIndex() {
        return cellIndex;
    }

    /**
     * Sets the value of the cellIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCellIndex(String value) {
        this.cellIndex = value;
    }

    /**
     * Gets the value of the creatorUid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatorUid() {
        return creatorUid;
    }

    /**
     * Sets the value of the creatorUid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatorUid(String value) {
        this.creatorUid = value;
    }

    /**
     * Gets the value of the deliberatorId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliberatorId() {
        return deliberatorId;
    }

    /**
     * Sets the value of the deliberatorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliberatorId(String value) {
        this.deliberatorId = value;
    }

    /**
     * Gets the value of the isReserve property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsReserve() {
        return isReserve;
    }

    /**
     * Sets the value of the isReserve property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsReserve(String value) {
        this.isReserve = value;
    }

    /**
     * Gets the value of the isSearch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsSearch() {
        return isSearch;
    }

    /**
     * Sets the value of the isSearch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsSearch(String value) {
        this.isSearch = value;
    }

    /**
     * Gets the value of the isSearchDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsSearchDate() {
        return isSearchDate;
    }

    /**
     * Sets the value of the isSearchDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsSearchDate(String value) {
        this.isSearchDate = value;
    }

    /**
     * Gets the value of the isSearchList property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsSearchList() {
        return isSearchList;
    }

    /**
     * Sets the value of the isSearchList property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsSearchList(String value) {
        this.isSearchList = value;
    }

    /**
     * Gets the value of the placeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlaceId() {
        return placeId;
    }

    /**
     * Sets the value of the placeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlaceId(String value) {
        this.placeId = value;
    }

    /**
     * Gets the value of the placeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlaceName() {
        return placeName;
    }

    /**
     * Sets the value of the placeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlaceName(String value) {
        this.placeName = value;
    }

    /**
     * Gets the value of the privilege property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrivilege() {
        return privilege;
    }

    /**
     * Sets the value of the privilege property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrivilege(String value) {
        this.privilege = value;
    }

    /**
     * Gets the value of the reserveId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserveId() {
        return reserveId;
    }

    /**
     * Sets the value of the reserveId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserveId(String value) {
        this.reserveId = value;
    }

    /**
     * Gets the value of the rscId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRscId() {
        return rscId;
    }

    /**
     * Sets the value of the rscId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRscId(String value) {
        this.rscId = value;
    }

    /**
     * Gets the value of the rscSearchName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRscSearchName() {
        return rscSearchName;
    }

    /**
     * Sets the value of the rscSearchName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRscSearchName(String value) {
        this.rscSearchName = value;
    }

    /**
     * Gets the value of the rscTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRscTypeId() {
        return rscTypeId;
    }

    /**
     * Sets the value of the rscTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRscTypeId(String value) {
        this.rscTypeId = value;
    }

    /**
     * Gets the value of the rscTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRscTypeName() {
        return rscTypeName;
    }

    /**
     * Sets the value of the rscTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRscTypeName(String value) {
        this.rscTypeName = value;
    }

    /**
     * Gets the value of the searchDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchDate() {
        return searchDate;
    }

    /**
     * Sets the value of the searchDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchDate(String value) {
        this.searchDate = value;
    }

    /**
     * Gets the value of the searchTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchTime() {
        return searchTime;
    }

    /**
     * Sets the value of the searchTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchTime(String value) {
        this.searchTime = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
