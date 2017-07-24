
package com.sds.acube.luxor.ws.client.aclservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for permission complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="permission">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deleteable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="modifiable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="permission" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="priority" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="readable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="strPermission" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="writeable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "permission", propOrder = {
    "deleteable",
    "modifiable",
    "permission",
    "priority",
    "readable",
    "strPermission",
    "writeable"
})
public class Permission {

    protected boolean deleteable;
    protected boolean modifiable;
    protected int permission;
    protected int priority;
    protected boolean readable;
    protected String strPermission;
    protected boolean writeable;

    /**
     * Gets the value of the deleteable property.
     * 
     */
    public boolean isDeleteable() {
        return deleteable;
    }

    /**
     * Sets the value of the deleteable property.
     * 
     */
    public void setDeleteable(boolean value) {
        this.deleteable = value;
    }

    /**
     * Gets the value of the modifiable property.
     * 
     */
    public boolean isModifiable() {
        return modifiable;
    }

    /**
     * Sets the value of the modifiable property.
     * 
     */
    public void setModifiable(boolean value) {
        this.modifiable = value;
    }

    /**
     * Gets the value of the permission property.
     * 
     */
    public int getPermission() {
        return permission;
    }

    /**
     * Sets the value of the permission property.
     * 
     */
    public void setPermission(int value) {
        this.permission = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     */
    public void setPriority(int value) {
        this.priority = value;
    }

    /**
     * Gets the value of the readable property.
     * 
     */
    public boolean isReadable() {
        return readable;
    }

    /**
     * Sets the value of the readable property.
     * 
     */
    public void setReadable(boolean value) {
        this.readable = value;
    }

    /**
     * Gets the value of the strPermission property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrPermission() {
        return strPermission;
    }

    /**
     * Sets the value of the strPermission property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrPermission(String value) {
        this.strPermission = value;
    }

    /**
     * Gets the value of the writeable property.
     * 
     */
    public boolean isWriteable() {
        return writeable;
    }

    /**
     * Sets the value of the writeable property.
     * 
     */
    public void setWriteable(boolean value) {
        this.writeable = value;
    }

}
