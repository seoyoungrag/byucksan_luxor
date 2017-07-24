
package com.sds.acube.luxor.ws.client.aclservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for accessList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="accessList">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.org.cn.acube.sds.com/}baseDMO">
 *       &lt;sequence>
 *         &lt;element name="accessList" type="{http://service.org.cn.acube.sds.com/}access" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="resourceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accessList", propOrder = {
    "accessList",
    "resourceId"
})
public class AccessList
    extends BaseDMO
{

    @XmlElement(nillable = true)
    protected List<Access> accessList;
    protected String resourceId;

    /**
     * Gets the value of the accessList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accessList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccessList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Access }
     * 
     * 
     */
    public List<Access> getAccessList() {
        if (accessList == null) {
            accessList = new ArrayList<Access>();
        }
        return this.accessList;
    }

    /**
     * Gets the value of the resourceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * Sets the value of the resourceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResourceId(String value) {
        this.resourceId = value;
    }

}
