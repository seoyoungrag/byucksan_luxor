
package com.sds.acube.luxor.ws.client.orgservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for policyInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="policyInfo">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.org.cn.acube.sds.com/}baseDMO">
 *       &lt;sequence>
 *         &lt;element name="compId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="policyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="policyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="policyType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="policyValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reserved1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reserved2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reserved3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "policyInfo", propOrder = {
    "compId",
    "description",
    "policyId",
    "policyName",
    "policyType",
    "policyValue",
    "reserved1",
    "reserved2",
    "reserved3"
})
public class PolicyInfo extends BaseDMO
{

    protected String compId;
    protected String description;
    protected String policyId;
    protected String policyName;
    protected Integer policyType;
    protected String policyValue;
    protected String reserved1;
    protected String reserved2;
    protected String reserved3;

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
     * Gets the value of the policyId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolicyId() {
        return policyId;
    }

    /**
     * Sets the value of the policyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolicyId(String value) {
        this.policyId = value;
    }

    /**
     * Gets the value of the policyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolicyName() {
        return policyName;
    }

    /**
     * Sets the value of the policyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolicyName(String value) {
        this.policyName = value;
    }

    /**
     * Gets the value of the policyType property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPolicyType() {
        return policyType;
    }

    /**
     * Sets the value of the policyType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPolicyType(Integer value) {
        this.policyType = value;
    }

    /**
     * Gets the value of the policyValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolicyValue() {
        return policyValue;
    }

    /**
     * Sets the value of the policyValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolicyValue(String value) {
        this.policyValue = value;
    }

    /**
     * Gets the value of the reserved1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserved1() {
        return reserved1;
    }

    /**
     * Sets the value of the reserved1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserved1(String value) {
        this.reserved1 = value;
    }

    /**
     * Gets the value of the reserved2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserved2() {
        return reserved2;
    }

    /**
     * Sets the value of the reserved2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserved2(String value) {
        this.reserved2 = value;
    }

    /**
     * Gets the value of the reserved3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserved3() {
        return reserved3;
    }

    /**
     * Sets the value of the reserved3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserved3(String value) {
        this.reserved3 = value;
    }

}
