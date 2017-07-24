
package com.sds.acube.luxor.ws.client.bbs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for bbsHeading complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bbsHeading">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.bbs.collaboration.luxor.acube.sds.com/}baseDomain">
 *       &lt;sequence>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="creatorUid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="folderId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="headerImage" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="headerImageFile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="headerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="headerOrder" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="headerType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://ws.bbs.collaboration.luxor.acube.sds.com/}bbsHeadingId" minOccurs="0"/>
 *         &lt;element name="parentHeaderId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bbsHeading", propOrder = {
    "createDate",
    "creatorUid",
    "folderId",
    "headerImage",
    "headerImageFile",
    "headerName",
    "headerOrder",
    "headerType",
    "id",
    "parentHeaderId"
})
public class BbsHeading
    extends BaseDomain
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createDate;
    protected String creatorUid;
    protected String folderId;
    protected byte[] headerImage;
    protected String headerImageFile;
    protected String headerName;
    protected int headerOrder;
    protected String headerType;
    protected BbsHeadingId id;
    protected String parentHeaderId;

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
     * Gets the value of the folderId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolderId() {
        return folderId;
    }

    /**
     * Sets the value of the folderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolderId(String value) {
        this.folderId = value;
    }

    /**
     * Gets the value of the headerImage property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getHeaderImage() {
        return headerImage;
    }

    /**
     * Sets the value of the headerImage property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setHeaderImage(byte[] value) {
        this.headerImage = value;
    }

    /**
     * Gets the value of the headerImageFile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHeaderImageFile() {
        return headerImageFile;
    }

    /**
     * Sets the value of the headerImageFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHeaderImageFile(String value) {
        this.headerImageFile = value;
    }

    /**
     * Gets the value of the headerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHeaderName() {
        return headerName;
    }

    /**
     * Sets the value of the headerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHeaderName(String value) {
        this.headerName = value;
    }

    /**
     * Gets the value of the headerOrder property.
     * 
     */
    public int getHeaderOrder() {
        return headerOrder;
    }

    /**
     * Sets the value of the headerOrder property.
     * 
     */
    public void setHeaderOrder(int value) {
        this.headerOrder = value;
    }

    /**
     * Gets the value of the headerType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHeaderType() {
        return headerType;
    }

    /**
     * Sets the value of the headerType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHeaderType(String value) {
        this.headerType = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BbsHeadingId }
     *     
     */
    public BbsHeadingId getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BbsHeadingId }
     *     
     */
    public void setId(BbsHeadingId value) {
        this.id = value;
    }

    /**
     * Gets the value of the parentHeaderId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentHeaderId() {
        return parentHeaderId;
    }

    /**
     * Sets the value of the parentHeaderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentHeaderId(String value) {
        this.parentHeaderId = value;
    }

}
