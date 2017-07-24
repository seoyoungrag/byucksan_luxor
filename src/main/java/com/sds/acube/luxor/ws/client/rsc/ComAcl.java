
package com.sds.acube.luxor.ws.client.rsc;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for comAcl complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="comAcl">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.rsc.collaboration.luxor.acube.sds.com/}baseDomain">
 *       &lt;sequence>
 *         &lt;element name="accessName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accessType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aclAccessIdList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aclResourceIdList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="comAclList" type="{http://ws.rsc.collaboration.luxor.acube.sds.com/}comAclId" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="id" type="{http://ws.rsc.collaboration.luxor.acube.sds.com/}comAclId" minOccurs="0"/>
 *         &lt;element name="priority" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="privilege" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "comAcl", propOrder = {
    "accessName",
    "accessType",
    "aclAccessIdList",
    "aclResourceIdList",
    "comAclList",
    "id",
    "priority",
    "privilege"
})
public class ComAcl
    extends BaseDomain
{

    protected String accessName;
    protected String accessType;
    protected String aclAccessIdList;
    protected String aclResourceIdList;
    @XmlElement(nillable = true)
    protected List<ComAclId> comAclList;
    protected ComAclId id;
    protected Long priority;
    protected Long privilege;

    /**
     * Gets the value of the accessName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessName() {
        return accessName;
    }

    /**
     * Sets the value of the accessName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessName(String value) {
        this.accessName = value;
    }

    /**
     * Gets the value of the accessType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessType() {
        return accessType;
    }

    /**
     * Sets the value of the accessType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessType(String value) {
        this.accessType = value;
    }

    /**
     * Gets the value of the aclAccessIdList property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAclAccessIdList() {
        return aclAccessIdList;
    }

    /**
     * Sets the value of the aclAccessIdList property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAclAccessIdList(String value) {
        this.aclAccessIdList = value;
    }

    /**
     * Gets the value of the aclResourceIdList property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAclResourceIdList() {
        return aclResourceIdList;
    }

    /**
     * Sets the value of the aclResourceIdList property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAclResourceIdList(String value) {
        this.aclResourceIdList = value;
    }

    /**
     * Gets the value of the comAclList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comAclList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComAclList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComAclId }
     * 
     * 
     */
    public List<ComAclId> getComAclList() {
        if (comAclList == null) {
            comAclList = new ArrayList<ComAclId>();
        }
        return this.comAclList;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link ComAclId }
     *     
     */
    public ComAclId getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComAclId }
     *     
     */
    public void setId(ComAclId value) {
        this.id = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPriority(Long value) {
        this.priority = value;
    }

    /**
     * Gets the value of the privilege property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPrivilege() {
        return privilege;
    }

    /**
     * Sets the value of the privilege property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPrivilege(Long value) {
        this.privilege = value;
    }

}
