
package com.sds.acube.luxor.ws.client.orgservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for resultInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="resultInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="alreadyExist" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="checkType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="failCnt" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="notFound" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="resultCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="resultValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="successCnt" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalCnt" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resultInfo", propOrder = {
    "alreadyExist",
    "checkType",
    "description",
    "failCnt",
    "notFound",
    "resultCode",
    "resultValue",
    "successCnt",
    "totalCnt"
})
public class ResultInfo {

    protected int alreadyExist;
    protected String checkType;
    protected String description;
    protected int failCnt;
    protected int notFound;
    protected int resultCode;
    protected String resultValue;
    protected int successCnt;
    protected int totalCnt;

    /**
     * Gets the value of the alreadyExist property.
     * 
     */
    public int getAlreadyExist() {
        return alreadyExist;
    }

    /**
     * Sets the value of the alreadyExist property.
     * 
     */
    public void setAlreadyExist(int value) {
        this.alreadyExist = value;
    }

    /**
     * Gets the value of the checkType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckType() {
        return checkType;
    }

    /**
     * Sets the value of the checkType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckType(String value) {
        this.checkType = value;
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
     * Gets the value of the failCnt property.
     * 
     */
    public int getFailCnt() {
        return failCnt;
    }

    /**
     * Sets the value of the failCnt property.
     * 
     */
    public void setFailCnt(int value) {
        this.failCnt = value;
    }

    /**
     * Gets the value of the notFound property.
     * 
     */
    public int getNotFound() {
        return notFound;
    }

    /**
     * Sets the value of the notFound property.
     * 
     */
    public void setNotFound(int value) {
        this.notFound = value;
    }

    /**
     * Gets the value of the resultCode property.
     * 
     */
    public int getResultCode() {
        return resultCode;
    }

    /**
     * Sets the value of the resultCode property.
     * 
     */
    public void setResultCode(int value) {
        this.resultCode = value;
    }

    /**
     * Gets the value of the resultValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultValue() {
        return resultValue;
    }

    /**
     * Sets the value of the resultValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultValue(String value) {
        this.resultValue = value;
    }

    /**
     * Gets the value of the successCnt property.
     * 
     */
    public int getSuccessCnt() {
        return successCnt;
    }

    /**
     * Sets the value of the successCnt property.
     * 
     */
    public void setSuccessCnt(int value) {
        this.successCnt = value;
    }

    /**
     * Gets the value of the totalCnt property.
     * 
     */
    public int getTotalCnt() {
        return totalCnt;
    }

    /**
     * Sets the value of the totalCnt property.
     * 
     */
    public void setTotalCnt(int value) {
        this.totalCnt = value;
    }

}
