
package com.sds.acube.luxor.ws.client.bbs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for selectPostPreNextListResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="selectPostPreNextListResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://ws.bbs.collaboration.luxor.acube.sds.com/}bbsPostPreNext" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "selectPostPreNextListResponse", propOrder = {
    "_return"
})
public class SelectPostPreNextListResponse {

    @XmlElement(name = "return")
    protected BbsPostPreNext _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link BbsPostPreNext }
     *     
     */
    public BbsPostPreNext getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link BbsPostPreNext }
     *     
     */
    public void setReturn(BbsPostPreNext value) {
        this._return = value;
    }

}
