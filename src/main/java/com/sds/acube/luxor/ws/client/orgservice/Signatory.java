
package com.sds.acube.luxor.ws.client.orgservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for signatory complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="signatory">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.org.cn.acube.sds.com/}employee">
 *       &lt;sequence>
 *         &lt;element name="substituteCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="substitutes" type="{http://service.org.cn.acube.sds.com/}substitutes" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "signatory", propOrder = {
    "substituteCount",
    "substitutes"
})
public class Signatory
    extends Employee
{

    protected int substituteCount;
    protected Substitutes substitutes;

    /**
     * Gets the value of the substituteCount property.
     * 
     */
    public int getSubstituteCount() {
        return substituteCount;
    }

    /**
     * Sets the value of the substituteCount property.
     * 
     */
    public void setSubstituteCount(int value) {
        this.substituteCount = value;
    }

    /**
     * Gets the value of the substitutes property.
     * 
     * @return
     *     possible object is
     *     {@link Substitutes }
     *     
     */
    public Substitutes getSubstitutes() {
        return substitutes;
    }

    /**
     * Sets the value of the substitutes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Substitutes }
     *     
     */
    public void setSubstitutes(Substitutes value) {
        this.substitutes = value;
    }

}
