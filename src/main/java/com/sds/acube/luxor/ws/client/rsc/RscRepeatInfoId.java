
package com.sds.acube.luxor.ws.client.rsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rscRepeatInfoId complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rscRepeatInfoId">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.rsc.collaboration.luxor.acube.sds.com/}baseDomain">
 *       &lt;sequence>
 *         &lt;element name="reserveDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reserveId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rscRepeatInfoId", propOrder = {
    "reserveDate",
    "reserveId"
})
public class RscRepeatInfoId
    extends BaseDomain
{

    protected String reserveDate;
    protected String reserveId;

    /**
     * Gets the value of the reserveDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserveDate() {
        return reserveDate;
    }

    /**
     * Sets the value of the reserveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserveDate(String value) {
        this.reserveDate = value;
    }

    /**
     * Gets the value of the reserveId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserveId() {
        return reserveId;
    }

    /**
     * Sets the value of the reserveId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserveId(String value) {
        this.reserveId = value;
    }

}
