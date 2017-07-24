
package com.sds.acube.luxor.ws.client.rsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rscRepeatInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rscRepeatInfo">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.rsc.collaboration.luxor.acube.sds.com/}baseDomain">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://ws.rsc.collaboration.luxor.acube.sds.com/}rscRepeatInfoId" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rscRepeatInfo", propOrder = {
    "id"
})
public class RscRepeatInfo
    extends BaseDomain
{

    protected RscRepeatInfoId id;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link RscRepeatInfoId }
     *     
     */
    public RscRepeatInfoId getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link RscRepeatInfoId }
     *     
     */
    public void setId(RscRepeatInfoId value) {
        this.id = value;
    }

}
