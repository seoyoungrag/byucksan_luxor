
package com.sds.acube.luxor.ws.client.orgservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for grade complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="grade">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="compID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradeAbbrName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradeID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradeOrder" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="gradeOtherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradeParentID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "grade", propOrder = {
    "compID",
    "description",
    "gradeAbbrName",
    "gradeID",
    "gradeName",
    "gradeOrder",
    "gradeOtherName",
    "gradeParentID"
})
public class Grade {

    protected String compID;
    protected String description;
    protected String gradeAbbrName;
    protected String gradeID;
    protected String gradeName;
    protected int gradeOrder;
    protected String gradeOtherName;
    protected String gradeParentID;

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
     * Gets the value of the gradeAbbrName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGradeAbbrName() {
        return gradeAbbrName;
    }

    /**
     * Sets the value of the gradeAbbrName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGradeAbbrName(String value) {
        this.gradeAbbrName = value;
    }

    /**
     * Gets the value of the gradeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGradeID() {
        return gradeID;
    }

    /**
     * Sets the value of the gradeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGradeID(String value) {
        this.gradeID = value;
    }

    /**
     * Gets the value of the gradeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGradeName() {
        return gradeName;
    }

    /**
     * Sets the value of the gradeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGradeName(String value) {
        this.gradeName = value;
    }

    /**
     * Gets the value of the gradeOrder property.
     * 
     */
    public int getGradeOrder() {
        return gradeOrder;
    }

    /**
     * Sets the value of the gradeOrder property.
     * 
     */
    public void setGradeOrder(int value) {
        this.gradeOrder = value;
    }

    /**
     * Gets the value of the gradeOtherName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGradeOtherName() {
        return gradeOtherName;
    }

    /**
     * Sets the value of the gradeOtherName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGradeOtherName(String value) {
        this.gradeOtherName = value;
    }

    /**
     * Gets the value of the gradeParentID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGradeParentID() {
        return gradeParentID;
    }

    /**
     * Sets the value of the gradeParentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGradeParentID(String value) {
        this.gradeParentID = value;
    }

}
