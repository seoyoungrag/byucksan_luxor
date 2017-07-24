
package com.sds.acube.luxor.ws.client.rsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rscResourceId complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rscResourceId">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.rsc.collaboration.luxor.acube.sds.com/}baseDomain">
 *       &lt;sequence>
 *         &lt;element name="rscId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rscTypeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rscResourceId", propOrder = {
    "rscId",
    "rscTypeId"
})
public class RscResourceId
    extends BaseDomain
{

    protected String rscId;
    protected String rscTypeId;

    /**
     * Gets the value of the rscId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRscId() {
        return rscId;
    }

    /**
     * Sets the value of the rscId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRscId(String value) {
        this.rscId = value;
    }

    /**
     * Gets the value of the rscTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRscTypeId() {
        return rscTypeId;
    }

    /**
     * Sets the value of the rscTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRscTypeId(String value) {
        this.rscTypeId = value;
    }

}
