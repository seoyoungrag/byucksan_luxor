
package com.sds.acube.luxor.ws.client.bbs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for bbsTag complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bbsTag">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.bbs.collaboration.luxor.acube.sds.com/}baseDomain">
 *       &lt;sequence>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="creatorUid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://ws.bbs.collaboration.luxor.acube.sds.com/}bbsTagId" minOccurs="0"/>
 *         &lt;element name="isRepresentTag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="representTagId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tagCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tagName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bbsTag", propOrder = {
    "createDate",
    "creatorUid",
    "id",
    "isRepresentTag",
    "representTagId",
    "tagCount",
    "tagName"
})
public class BbsTag
    extends BaseDomain
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createDate;
    protected String creatorUid;
    protected BbsTagId id;
    protected String isRepresentTag;
    protected String representTagId;
    protected int tagCount;
    protected String tagName;

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
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BbsTagId }
     *     
     */
    public BbsTagId getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BbsTagId }
     *     
     */
    public void setId(BbsTagId value) {
        this.id = value;
    }

    /**
     * Gets the value of the isRepresentTag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsRepresentTag() {
        return isRepresentTag;
    }

    /**
     * Sets the value of the isRepresentTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsRepresentTag(String value) {
        this.isRepresentTag = value;
    }

    /**
     * Gets the value of the representTagId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepresentTagId() {
        return representTagId;
    }

    /**
     * Sets the value of the representTagId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepresentTagId(String value) {
        this.representTagId = value;
    }

    /**
     * Gets the value of the tagCount property.
     * 
     */
    public int getTagCount() {
        return tagCount;
    }

    /**
     * Sets the value of the tagCount property.
     * 
     */
    public void setTagCount(int value) {
        this.tagCount = value;
    }

    /**
     * Gets the value of the tagName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * Sets the value of the tagName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTagName(String value) {
        this.tagName = value;
    }

}
