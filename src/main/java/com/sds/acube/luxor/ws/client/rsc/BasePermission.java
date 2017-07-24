
package com.sds.acube.luxor.ws.client.rsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for basePermission complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="basePermission">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="delete" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="deleteable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="modifiable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="modify" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="permission" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="priority" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="read" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="readable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="write" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
@XmlType(name = "basePermission", propOrder = {
    "delete",
    "deleteable",
    "modifiable",
    "modify",
    "permission",
    "priority",
    "read",
    "readable",
    "write",
    "writeable"
})
public class BasePermission {

    protected boolean delete;
    protected boolean deleteable;
    protected boolean modifiable;
    protected boolean modify;
    protected int permission;
    protected int priority;
    protected boolean read;
    protected boolean readable;
    protected boolean write;
    protected boolean writeable;

    /**
     * Gets the value of the delete property.
     * 
     */
    public boolean isDelete() {
        return delete;
    }

    /**
     * Sets the value of the delete property.
     * 
     */
    public void setDelete(boolean value) {
        this.delete = value;
    }

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
     * Gets the value of the modify property.
     * 
     */
    public boolean isModify() {
        return modify;
    }

    /**
     * Sets the value of the modify property.
     * 
     */
    public void setModify(boolean value) {
        this.modify = value;
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
     * Gets the value of the read property.
     * 
     */
    public boolean isRead() {
        return read;
    }

    /**
     * Sets the value of the read property.
     * 
     */
    public void setRead(boolean value) {
        this.read = value;
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
     * Gets the value of the write property.
     * 
     */
    public boolean isWrite() {
        return write;
    }

    /**
     * Sets the value of the write property.
     * 
     */
    public void setWrite(boolean value) {
        this.write = value;
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
