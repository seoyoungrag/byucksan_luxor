
package com.sds.acube.luxor.ws.client.bbs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getPostPaging complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getPostPaging">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://ws.bbs.collaboration.luxor.acube.sds.com/}bbsPostCommon" minOccurs="0"/>
 *         &lt;element name="arg1" type="{http://ws.bbs.collaboration.luxor.acube.sds.com/}bbsBoardMaster" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPostPaging", propOrder = {
    "arg0",
    "arg1"
})
public class GetPostPaging {

    protected BbsPostCommon arg0;
    protected BbsBoardMaster arg1;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link BbsPostCommon }
     *     
     */
    public BbsPostCommon getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BbsPostCommon }
     *     
     */
    public void setArg0(BbsPostCommon value) {
        this.arg0 = value;
    }

    /**
     * Gets the value of the arg1 property.
     * 
     * @return
     *     possible object is
     *     {@link BbsBoardMaster }
     *     
     */
    public BbsBoardMaster getArg1() {
        return arg1;
    }

    /**
     * Sets the value of the arg1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BbsBoardMaster }
     *     
     */
    public void setArg1(BbsBoardMaster value) {
        this.arg1 = value;
    }

}
