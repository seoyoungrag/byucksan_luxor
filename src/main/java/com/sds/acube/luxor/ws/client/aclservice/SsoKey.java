
package com.sds.acube.luxor.ws.client.aclservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ssoKey complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ssoKey">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.org.cn.acube.sds.com/}baseDMO">
 *       &lt;sequence>
 *         &lt;element name="compId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hmacKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="publishingDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="publishingIp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="publishingKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="publishingTimestamp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ssoData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="timeOutMinute" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="userUid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ssoKey", propOrder = {
    "compId",
    "hmacKey",
    "publishingDate",
    "publishingIp",
    "publishingKey",
    "publishingTimestamp",
    "ssoData",
    "timeOutMinute",
    "userUid"
})
public class SsoKey
    extends BaseDMO
{

    protected String compId;
    protected String hmacKey;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar publishingDate;
    protected String publishingIp;
    protected String publishingKey;
    protected String publishingTimestamp;
    protected String ssoData;
    protected int timeOutMinute;
    protected String userUid;

    /**
     * Gets the value of the compId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompId() {
        return compId;
    }

    /**
     * Sets the value of the compId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompId(String value) {
        this.compId = value;
    }

    /**
     * Gets the value of the hmacKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHmacKey() {
        return hmacKey;
    }

    /**
     * Sets the value of the hmacKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHmacKey(String value) {
        this.hmacKey = value;
    }

    /**
     * Gets the value of the publishingDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPublishingDate() {
        return publishingDate;
    }

    /**
     * Sets the value of the publishingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPublishingDate(XMLGregorianCalendar value) {
        this.publishingDate = value;
    }

    /**
     * Gets the value of the publishingIp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublishingIp() {
        return publishingIp;
    }

    /**
     * Sets the value of the publishingIp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublishingIp(String value) {
        this.publishingIp = value;
    }

    /**
     * Gets the value of the publishingKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublishingKey() {
        return publishingKey;
    }

    /**
     * Sets the value of the publishingKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublishingKey(String value) {
        this.publishingKey = value;
    }

    /**
     * Gets the value of the publishingTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublishingTimestamp() {
        return publishingTimestamp;
    }

    /**
     * Sets the value of the publishingTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublishingTimestamp(String value) {
        this.publishingTimestamp = value;
    }

    /**
     * Gets the value of the ssoData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSsoData() {
        return ssoData;
    }

    /**
     * Sets the value of the ssoData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSsoData(String value) {
        this.ssoData = value;
    }

    /**
     * Gets the value of the timeOutMinute property.
     * 
     */
    public int getTimeOutMinute() {
        return timeOutMinute;
    }

    /**
     * Sets the value of the timeOutMinute property.
     * 
     */
    public void setTimeOutMinute(int value) {
        this.timeOutMinute = value;
    }

    /**
     * Gets the value of the userUid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserUid() {
        return userUid;
    }

    /**
     * Sets the value of the userUid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserUid(String value) {
        this.userUid = value;
    }

}
