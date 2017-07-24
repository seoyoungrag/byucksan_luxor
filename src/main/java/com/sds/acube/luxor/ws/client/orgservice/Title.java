
package com.sds.acube.luxor.ws.client.orgservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for title complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="title">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="compID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titleID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titleOrder" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="titleOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titleParentID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "title", propOrder = {
    "compID",
    "description",
    "titleID",
    "titleName",
    "titleOrder",
    "titleOtherName",
    "titleParentID"
})
public class Title {

    protected String compID;
    protected String description;
    protected String titleID;
    protected String titleName;
    protected int titleOrder;
    protected String titleOtherName;
    protected String titleParentID;

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
     * Gets the value of the titleID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitleID() {
        return titleID;
    }

    /**
     * Sets the value of the titleID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitleID(String value) {
        this.titleID = value;
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
     * Gets the value of the titleParentID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitleParentID() {
        return titleParentID;
    }

    /**
     * Sets the value of the titleParentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitleParentID(String value) {
        this.titleParentID = value;
    }

}
