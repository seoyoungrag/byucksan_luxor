
package com.sds.acube.luxor.ws.client.bbs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for comCode complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="comCode">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.bbs.collaboration.luxor.acube.sds.com/}baseDomain">
 *       &lt;sequence>
 *         &lt;element name="depth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="formerParentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://ws.bbs.collaboration.luxor.acube.sds.com/}comCodeId" minOccurs="0"/>
 *         &lt;element name="isSubExist" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="linkUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="movePosition" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newIndex" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="newlyParentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nextSiblingNodeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nodeIds" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nodeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nodeNameId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oldIndex" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="parentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sortOrder" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "comCode", propOrder = {
    "depth",
    "description",
    "formerParentId",
    "id",
    "isSubExist",
    "linkUrl",
    "movePosition",
    "newIndex",
    "newlyParentId",
    "nextSiblingNodeId",
    "nodeIds",
    "nodeName",
    "nodeNameId",
    "oldIndex",
    "parentId",
    "sortOrder"
})
public class ComCode
    extends BaseDomain
{

    protected int depth;
    protected String description;
    protected String formerParentId;
    protected ComCodeId id;
    protected String isSubExist;
    protected String linkUrl;
    protected String movePosition;
    protected int newIndex;
    protected String newlyParentId;
    protected String nextSiblingNodeId;
    protected String nodeIds;
    protected String nodeName;
    protected String nodeNameId;
    protected int oldIndex;
    protected String parentId;
    protected int sortOrder;

    /**
     * Gets the value of the depth property.
     * 
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Sets the value of the depth property.
     * 
     */
    public void setDepth(int value) {
        this.depth = value;
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
     * Gets the value of the formerParentId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormerParentId() {
        return formerParentId;
    }

    /**
     * Sets the value of the formerParentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormerParentId(String value) {
        this.formerParentId = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link ComCodeId }
     *     
     */
    public ComCodeId getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComCodeId }
     *     
     */
    public void setId(ComCodeId value) {
        this.id = value;
    }

    /**
     * Gets the value of the isSubExist property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsSubExist() {
        return isSubExist;
    }

    /**
     * Sets the value of the isSubExist property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsSubExist(String value) {
        this.isSubExist = value;
    }

    /**
     * Gets the value of the linkUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkUrl() {
        return linkUrl;
    }

    /**
     * Sets the value of the linkUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinkUrl(String value) {
        this.linkUrl = value;
    }

    /**
     * Gets the value of the movePosition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMovePosition() {
        return movePosition;
    }

    /**
     * Sets the value of the movePosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMovePosition(String value) {
        this.movePosition = value;
    }

    /**
     * Gets the value of the newIndex property.
     * 
     */
    public int getNewIndex() {
        return newIndex;
    }

    /**
     * Sets the value of the newIndex property.
     * 
     */
    public void setNewIndex(int value) {
        this.newIndex = value;
    }

    /**
     * Gets the value of the newlyParentId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewlyParentId() {
        return newlyParentId;
    }

    /**
     * Sets the value of the newlyParentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewlyParentId(String value) {
        this.newlyParentId = value;
    }

    /**
     * Gets the value of the nextSiblingNodeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextSiblingNodeId() {
        return nextSiblingNodeId;
    }

    /**
     * Sets the value of the nextSiblingNodeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextSiblingNodeId(String value) {
        this.nextSiblingNodeId = value;
    }

    /**
     * Gets the value of the nodeIds property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNodeIds() {
        return nodeIds;
    }

    /**
     * Sets the value of the nodeIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNodeIds(String value) {
        this.nodeIds = value;
    }

    /**
     * Gets the value of the nodeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * Sets the value of the nodeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNodeName(String value) {
        this.nodeName = value;
    }

    /**
     * Gets the value of the nodeNameId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNodeNameId() {
        return nodeNameId;
    }

    /**
     * Sets the value of the nodeNameId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNodeNameId(String value) {
        this.nodeNameId = value;
    }

    /**
     * Gets the value of the oldIndex property.
     * 
     */
    public int getOldIndex() {
        return oldIndex;
    }

    /**
     * Sets the value of the oldIndex property.
     * 
     */
    public void setOldIndex(int value) {
        this.oldIndex = value;
    }

    /**
     * Gets the value of the parentId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * Sets the value of the parentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentId(String value) {
        this.parentId = value;
    }

    /**
     * Gets the value of the sortOrder property.
     * 
     */
    public int getSortOrder() {
        return sortOrder;
    }

    /**
     * Sets the value of the sortOrder property.
     * 
     */
    public void setSortOrder(int value) {
        this.sortOrder = value;
    }

}
