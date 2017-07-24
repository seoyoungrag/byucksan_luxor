
package com.sds.acube.luxor.ws.client.orgservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for address complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="address">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BUNGI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DONG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GUGUN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SIDO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="zipCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "address", propOrder = {
    "bungi",
    "dong",
    "gugun",
    "sido",
    "zipCode"
})
public class Address {

    @XmlElement(name = "BUNGI")
    protected String bungi;
    @XmlElement(name = "DONG")
    protected String dong;
    @XmlElement(name = "GUGUN")
    protected String gugun;
    @XmlElement(name = "SIDO")
    protected String sido;
    protected String zipCode;

    /**
     * Gets the value of the bungi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBUNGI() {
        return bungi;
    }

    /**
     * Sets the value of the bungi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBUNGI(String value) {
        this.bungi = value;
    }

    /**
     * Gets the value of the dong property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDONG() {
        return dong;
    }

    /**
     * Sets the value of the dong property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDONG(String value) {
        this.dong = value;
    }

    /**
     * Gets the value of the gugun property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGUGUN() {
        return gugun;
    }

    /**
     * Sets the value of the gugun property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGUGUN(String value) {
        this.gugun = value;
    }

    /**
     * Gets the value of the sido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSIDO() {
        return sido;
    }

    /**
     * Sets the value of the sido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSIDO(String value) {
        this.sido = value;
    }

    /**
     * Gets the value of the zipCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the value of the zipCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZipCode(String value) {
        this.zipCode = value;
    }

}
