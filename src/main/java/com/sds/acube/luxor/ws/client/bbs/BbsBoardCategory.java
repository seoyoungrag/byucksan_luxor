
package com.sds.acube.luxor.ws.client.bbs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for bbsBoardCategory complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bbsBoardCategory">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.bbs.collaboration.luxor.acube.sds.com/}baseDomain">
 *       &lt;sequence>
 *         &lt;element name="accessIds" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="categoryFrequency" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="categoryIds" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="categoryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="categoryOrder" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="creatorUid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="depth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="formerParentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://ws.bbs.collaboration.luxor.acube.sds.com/}bbsBoardCategoryId" minOccurs="0"/>
 *         &lt;element name="isSubExist" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="movePosition" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newIndex" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="newlyParentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nextSiblingNodeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oldIndex" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="parentCategoryId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bbsBoardCategory", propOrder = {
    "accessIds",
    "categoryFrequency",
    "categoryIds",
    "categoryName",
    "categoryOrder",
    "createDate",
    "creatorUid",
    "depth",
    "formerParentId",
    "id",
    "isSubExist",
    "movePosition",
    "newIndex",
    "newlyParentId",
    "nextSiblingNodeId",
    "oldIndex",
    "parentCategoryId"
})
public class BbsBoardCategory
    extends BaseDomain
{

    protected String accessIds;
    protected int categoryFrequency;
    protected String categoryIds;
    protected String categoryName;
    protected int categoryOrder;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createDate;
    protected String creatorUid;
    protected int depth;
    protected String formerParentId;
    protected BbsBoardCategoryId id;
    protected String isSubExist;
    protected String movePosition;
    protected int newIndex;
    protected String newlyParentId;
    protected String nextSiblingNodeId;
    protected int oldIndex;
    protected String parentCategoryId;

    /**
     * Gets the value of the accessIds property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessIds() {
        return accessIds;
    }

    /**
     * Sets the value of the accessIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessIds(String value) {
        this.accessIds = value;
    }

    /**
     * Gets the value of the categoryFrequency property.
     * 
     */
    public int getCategoryFrequency() {
        return categoryFrequency;
    }

    /**
     * Sets the value of the categoryFrequency property.
     * 
     */
    public void setCategoryFrequency(int value) {
        this.categoryFrequency = value;
    }

    /**
     * Gets the value of the categoryIds property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryIds() {
        return categoryIds;
    }

    /**
     * Sets the value of the categoryIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryIds(String value) {
        this.categoryIds = value;
    }

    /**
     * Gets the value of the categoryName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets the value of the categoryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryName(String value) {
        this.categoryName = value;
    }

    /**
     * Gets the value of the categoryOrder property.
     * 
     */
    public int getCategoryOrder() {
        return categoryOrder;
    }

    /**
     * Sets the value of the categoryOrder property.
     * 
     */
    public void setCategoryOrder(int value) {
        this.categoryOrder = value;
    }

    /**
     * Gets the value of the createDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateDate() {
        return createDate;
    }

    /**
     * Sets the value of the createDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateDate(XMLGregorianCalendar value) {
        this.createDate = value;
    }

    /**
     * Gets the value of the creatorUid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatorUid() {
        return creatorUid;
    }

    /**
     * Sets the value of the creatorUid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatorUid(String value) {
        this.creatorUid = value;
    }

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
     *     {@link BbsBoardCategoryId }
     *     
     */
    public BbsBoardCategoryId getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BbsBoardCategoryId }
     *     
     */
    public void setId(BbsBoardCategoryId value) {
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
     * Gets the value of the parentCategoryId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentCategoryId() {
        return parentCategoryId;
    }

    /**
     * Sets the value of the parentCategoryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentCategoryId(String value) {
        this.parentCategoryId = value;
    }

}
