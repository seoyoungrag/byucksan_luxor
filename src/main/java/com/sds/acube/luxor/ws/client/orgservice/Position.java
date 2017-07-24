
package com.sds.acube.luxor.ws.client.orgservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for position complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="position">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="compID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="positionAbbrName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="positionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="positionName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="positionOrder" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="positionOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="positionParentID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "position", propOrder = {
    "compID",
    "description",
    "positionAbbrName",
    "positionID",
    "positionName",
    "positionOrder",
    "positionOtherName",
    "positionParentID"
})
public class Position {

    protected String compID;
    protected String description;
    protected String positionAbbrName;
    protected String positionID;
    protected String positionName;
    protected int positionOrder;
    protected String positionOtherName;
    protected String positionParentID;

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
     * Gets the value of the positionAbbrName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPositionAbbrName() {
        return positionAbbrName;
    }

    /**
     * Sets the value of the positionAbbrName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPositionAbbrName(String value) {
        this.positionAbbrName = value;
    }

    /**
     * Gets the value of the positionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPositionID() {
        return positionID;
    }

    /**
     * Sets the value of the positionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPositionID(String value) {
        this.positionID = value;
    }

    /**
     * Gets the value of the positionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPositionName() {
        return positionName;
    }

    /**
     * Sets the value of the positionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPositionName(String value) {
        this.positionName = value;
    }

    /**
     * Gets the value of the positionOrder property.
     * 
     */
    public int getPositionOrder() {
        return positionOrder;
    }

    /**
     * Sets the value of the positionOrder property.
     * 
     */
    public void setPositionOrder(int value) {
        this.positionOrder = value;
    }

    /**
     * Gets the value of the positionOtherName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPositionOtherName() {
        return positionOtherName;
    }

    /**
     * Sets the value of the positionOtherName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPositionOtherName(String value) {
        this.positionOtherName = value;
    }

    /**
     * Gets the value of the positionParentID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPositionParentID() {
        return positionParentID;
    }

    /**
     * Sets the value of the positionParentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPositionParentID(String value) {
        this.positionParentID = value;
    }

}
