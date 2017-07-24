
package com.sds.acube.luxor.ws.client.rsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for rscReserve complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rscReserve">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.rsc.collaboration.luxor.acube.sds.com/}baseDomain">
 *       &lt;sequence>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="creatorEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creatorUid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deliberateContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deliberateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="endTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://ws.rsc.collaboration.luxor.acube.sds.com/}rscReserveId" minOccurs="0"/>
 *         &lt;element name="isRepeat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="placeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="repeatCondition" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="repeatCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="repeatEndDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="repeatFrequency" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="repeatWay" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reserveContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rscEndTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rscId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rscName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rscStartTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rscTimeUnit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rscType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rscTypeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rscTypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rscReserve", propOrder = {
    "createDate",
    "creatorEmail",
    "creatorUid",
    "deliberateContent",
    "deliberateDate",
    "endTime",
    "id",
    "isRepeat",
    "placeId",
    "repeatCondition",
    "repeatCount",
    "repeatEndDate",
    "repeatFrequency",
    "repeatWay",
    "resDate",
    "reserveContent",
    "rscEndTime",
    "rscId",
    "rscName",
    "rscStartTime",
    "rscTimeUnit",
    "rscType",
    "rscTypeId",
    "rscTypeName",
    "startTime",
    "status",
    "title"
})
public class RscReserve
    extends BaseDomain
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createDate;
    protected String creatorEmail;
    protected String creatorUid;
    protected String deliberateContent;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar deliberateDate;
    protected String endTime;
    protected RscReserveId id;
    protected String isRepeat;
    protected String placeId;
    protected String repeatCondition;
    protected Long repeatCount;
    protected String repeatEndDate;
    protected Long repeatFrequency;
    protected String repeatWay;
    protected String resDate;
    protected String reserveContent;
    protected String rscEndTime;
    protected String rscId;
    protected String rscName;
    protected String rscStartTime;
    protected String rscTimeUnit;
    protected String rscType;
    protected String rscTypeId;
    protected String rscTypeName;
    protected String startTime;
    protected String status;
    protected String title;

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
     * Gets the value of the creatorEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatorEmail() {
        return creatorEmail;
    }

    /**
     * Sets the value of the creatorEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatorEmail(String value) {
        this.creatorEmail = value;
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
     * Gets the value of the deliberateContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliberateContent() {
        return deliberateContent;
    }

    /**
     * Sets the value of the deliberateContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliberateContent(String value) {
        this.deliberateContent = value;
    }

    /**
     * Gets the value of the deliberateDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDeliberateDate() {
        return deliberateDate;
    }

    /**
     * Sets the value of the deliberateDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDeliberateDate(XMLGregorianCalendar value) {
        this.deliberateDate = value;
    }

    /**
     * Gets the value of the endTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Sets the value of the endTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndTime(String value) {
        this.endTime = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link RscReserveId }
     *     
     */
    public RscReserveId getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link RscReserveId }
     *     
     */
    public void setId(RscReserveId value) {
        this.id = value;
    }

    /**
     * Gets the value of the isRepeat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsRepeat() {
        return isRepeat;
    }

    /**
     * Sets the value of the isRepeat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsRepeat(String value) {
        this.isRepeat = value;
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
     * Gets the value of the repeatCondition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepeatCondition() {
        return repeatCondition;
    }

    /**
     * Sets the value of the repeatCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepeatCondition(String value) {
        this.repeatCondition = value;
    }

    /**
     * Gets the value of the repeatCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRepeatCount() {
        return repeatCount;
    }

    /**
     * Sets the value of the repeatCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRepeatCount(Long value) {
        this.repeatCount = value;
    }

    /**
     * Gets the value of the repeatEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepeatEndDate() {
        return repeatEndDate;
    }

    /**
     * Sets the value of the repeatEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepeatEndDate(String value) {
        this.repeatEndDate = value;
    }

    /**
     * Gets the value of the repeatFrequency property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRepeatFrequency() {
        return repeatFrequency;
    }

    /**
     * Sets the value of the repeatFrequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRepeatFrequency(Long value) {
        this.repeatFrequency = value;
    }

    /**
     * Gets the value of the repeatWay property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepeatWay() {
        return repeatWay;
    }

    /**
     * Sets the value of the repeatWay property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepeatWay(String value) {
        this.repeatWay = value;
    }

    /**
     * Gets the value of the resDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResDate() {
        return resDate;
    }

    /**
     * Sets the value of the resDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResDate(String value) {
        this.resDate = value;
    }

    /**
     * Gets the value of the reserveContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserveContent() {
        return reserveContent;
    }

    /**
     * Sets the value of the reserveContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserveContent(String value) {
        this.reserveContent = value;
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
     *     {@link String }
     *     
     */
    public String getRscTimeUnit() {
        return rscTimeUnit;
    }

    /**
     * Sets the value of the rscTimeUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRscTimeUnit(String value) {
        this.rscTimeUnit = value;
    }

    /**
     * Gets the value of the rscType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRscType() {
        return rscType;
    }

    /**
     * Sets the value of the rscType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRscType(String value) {
        this.rscType = value;
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
     * Gets the value of the startTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartTime(String value) {
        this.startTime = value;
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
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

}
