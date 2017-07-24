
package com.sds.acube.luxor.ws.client.orgservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for duty complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="duty">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="compID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dutyID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dutyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dutyOrder" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dutyOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dutyParentID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "duty", propOrder = {
    "compID",
    "description",
    "dutyID",
    "dutyName",
    "dutyOrder",
    "dutyOtherName",
    "dutyParentID"
})
public class Duty {

    protected String compID;
    protected String description;
    protected String dutyID;
    protected String dutyName;
    protected int dutyOrder;
    protected String dutyOtherName;
    protected String dutyParentID;

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
     * Gets the value of the dutyID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDutyID() {
        return dutyID;
    }

    /**
     * Sets the value of the dutyID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDutyID(String value) {
        this.dutyID = value;
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
     * Gets the value of the dutyParentID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDutyParentID() {
        return dutyParentID;
    }

    /**
     * Sets the value of the dutyParentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDutyParentID(String value) {
        this.dutyParentID = value;
    }

}
