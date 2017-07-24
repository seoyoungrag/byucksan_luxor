
package com.sds.acube.luxor.ws.client.rsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for rscResource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rscResource">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.rsc.collaboration.luxor.acube.sds.com/}baseDomain">
 *       &lt;sequence>
 *         &lt;element name="attachId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="creatorUid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deliberatorId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deliberatorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://ws.rsc.collaboration.luxor.acube.sds.com/}rscResourceId" minOccurs="0"/>
 *         &lt;element name="isDeliberation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="placeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="placeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remarks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reserveCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rscEndTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rscName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rscStartTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rscTimeUnit" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="rscTypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="updateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="updaterName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="updaterUid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rscResource", propOrder = {
    "attachId",
    "createDate",
    "creatorUid",
    "deliberatorId",
    "deliberatorName",
    "id",
    "isDeliberation",
    "placeId",
    "placeName",
    "remarks",
    "reserveCount",
    "rscEndTime",
    "rscName",
    "rscStartTime",
    "rscTimeUnit",
    "rscTypeName",
    "updateDate",
    "updaterName",
    "updaterUid"
})
public class RscResource
    extends BaseDomain
{

    protected String attachId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createDate;
    protected String creatorUid;
    protected String deliberatorId;
    protected String deliberatorName;
    protected RscResourceId id;
    protected String isDeliberation;
    protected String placeId;
    protected String placeName;
    protected String remarks;
    protected String reserveCount;
    protected String rscEndTime;
    protected String rscName;
    protected String rscStartTime;
    protected Long rscTimeUnit;
    protected String rscTypeName;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateDate;
    protected String updaterName;
    protected String updaterUid;

    /**
     * Gets the value of the attachId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttachId() {
        return attachId;
    }

    /**
     * Sets the value of the attachId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttachId(String value) {
        this.attachId = value;
    }

    /**
     * Gets the value of the createDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateDate() {
        return createDate;
    }

    /**
     * Sets the value of the createDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateDate(XMLGregorianCalendar value) {
        this.createDate = value;
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
     * Gets the value of the deliberatorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliberatorName() {
        return deliberatorName;
    }

    /**
     * Sets the value of the deliberatorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliberatorName(String value) {
        this.deliberatorName = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link RscResourceId }
     *     
     */
    public RscResourceId getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link RscResourceId }
     *     
     */
    public void setId(RscResourceId value) {
        this.id = value;
    }

    /**
     * Gets the value of the isDeliberation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsDeliberation() {
        return isDeliberation;
    }

    /**
     * Sets the value of the isDeliberation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsDeliberation(String value) {
        this.isDeliberation = value;
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
     * Gets the value of the remarks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * Sets the value of the remarks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemarks(String value) {
        this.remarks = value;
    }

    /**
     * Gets the value of the reserveCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserveCount() {
        return reserveCount;
    }

    /**
     * Sets the value of the reserveCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserveCount(String value) {
        this.reserveCount = value;
    }

    /**
     * Gets the value of the rscEndTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRscEndTime() {
        return rscEndTime;
    }

    /**
     * Sets the value of the rscEndTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRscEndTime(String value) {
        this.rscEndTime = value;
    }

    /**
     * Gets the value of the rscName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRscName() {
        return rscName;
    }

    /**
     * Sets the value of the rscName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRscName(String value) {
        this.rscName = value;
    }

    /**
     * Gets the value of the rscStartTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRscStartTime() {
        return rscStartTime;
    }

    /**
     * Sets the value of the rscStartTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRscStartTime(String value) {
        this.rscStartTime = value;
    }

    /**
     * Gets the value of the rscTimeUnit property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRscTimeUnit() {
        return rscTimeUnit;
    }

    /**
     * Sets the value of the rscTimeUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRscTimeUnit(Long value) {
        this.rscTimeUnit = value;
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
     * Gets the value of the updateDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateDate() {
        return updateDate;
    }

    /**
     * Sets the value of the updateDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdateDate(XMLGregorianCalendar value) {
        this.updateDate = value;
    }

    /**
     * Gets the value of the updaterName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdaterName() {
        return updaterName;
    }

    /**
     * Sets the value of the updaterName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdaterName(String value) {
        this.updaterName = value;
    }

    /**
     * Gets the value of the updaterUid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdaterUid() {
        return updaterUid;
    }

    /**
     * Sets the value of the updaterUid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdaterUid(String value) {
        this.updaterUid = value;
    }

}
