
package com.sds.acube.luxor.ws.client.rsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for meetingUpdate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="meetingUpdate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://ws.rsc.collaboration.luxor.acube.sds.com/}rscReserve" minOccurs="0"/>
 *         &lt;element name="arg1" type="{http://ws.rsc.collaboration.luxor.acube.sds.com/}rscRepeatInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "meetingUpdate", propOrder = {
    "arg0",
    "arg1"
})
public class MeetingUpdate {

    protected RscReserve arg0;
    protected RscRepeatInfo arg1;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link RscReserve }
     *     
     */
    public RscReserve getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link RscReserve }
     *     
     */
    public void setArg0(RscReserve value) {
        this.arg0 = value;
    }

    /**
     * Gets the value of the arg1 property.
     * 
     * @return
     *     possible object is
     *     {@link RscRepeatInfo }
     *     
     */
    public RscRepeatInfo getArg1() {
        return arg1;
    }

    /**
     * Sets the value of the arg1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link RscRepeatInfo }
     *     
     */
    public void setArg1(RscRepeatInfo value) {
        this.arg1 = value;
    }

}
