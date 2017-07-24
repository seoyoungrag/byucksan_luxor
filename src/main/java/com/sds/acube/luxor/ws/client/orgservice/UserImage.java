
package com.sds.acube.luxor.ws.client.orgservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for userImage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="userImage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pictureImage" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="pictureType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="signImage" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="signType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stampImage" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="stampOrSign" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="stampType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userUID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userImage", propOrder = {
    "pictureImage",
    "pictureType",
    "signImage",
    "signType",
    "stampImage",
    "stampOrSign",
    "stampType",
    "userUID"
})
public class UserImage {

    protected byte[] pictureImage;
    protected String pictureType;
    protected byte[] signImage;
    protected String signType;
    protected byte[] stampImage;
    protected int stampOrSign;
    protected String stampType;
    protected String userUID;

    /**
     * Gets the value of the pictureImage property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getPictureImage() {
        return pictureImage;
    }

    /**
     * Sets the value of the pictureImage property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setPictureImage(byte[] value) {
        this.pictureImage = value;
    }

    /**
     * Gets the value of the pictureType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPictureType() {
        return pictureType;
    }

    /**
     * Sets the value of the pictureType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPictureType(String value) {
        this.pictureType = value;
    }

    /**
     * Gets the value of the signImage property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getSignImage() {
        return signImage;
    }

    /**
     * Sets the value of the signImage property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setSignImage(byte[] value) {
        this.signImage = value;
    }

    /**
     * Gets the value of the signType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignType() {
        return signType;
    }

    /**
     * Sets the value of the signType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignType(String value) {
        this.signType = value;
    }

    /**
     * Gets the value of the stampImage property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getStampImage() {
        return stampImage;
    }

    /**
     * Sets the value of the stampImage property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setStampImage(byte[] value) {
        this.stampImage = value;
    }

    /**
     * Gets the value of the stampOrSign property.
     * 
     */
    public int getStampOrSign() {
        return stampOrSign;
    }

    /**
     * Sets the value of the stampOrSign property.
     * 
     */
    public void setStampOrSign(int value) {
        this.stampOrSign = value;
    }

    /**
     * Gets the value of the stampType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStampType() {
        return stampType;
    }

    /**
     * Sets the value of the stampType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStampType(String value) {
        this.stampType = value;
    }

    /**
     * Gets the value of the userUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserUID() {
        return userUID;
    }

    /**
     * Sets the value of the userUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserUID(String value) {
        this.userUID = value;
    }

}
