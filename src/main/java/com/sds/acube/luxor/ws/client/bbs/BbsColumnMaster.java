
package com.sds.acube.luxor.ws.client.bbs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bbsColumnMaster complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bbsColumnMaster">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.bbs.collaboration.luxor.acube.sds.com/}baseDomain">
 *       &lt;sequence>
 *         &lt;element name="columnAttribute" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="columnCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="columnCodeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="columnExpand" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="columnName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="columnOrder" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="columnSize" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="columnType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="columnWidth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="defaultValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://ws.bbs.collaboration.luxor.acube.sds.com/}bbsColumnMasterId" minOccurs="0"/>
 *         &lt;element name="isEssential" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="useCreate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="useList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="useScreen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="useUpdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="useView" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bbsColumnMaster", propOrder = {
    "columnAttribute",
    "columnCode",
    "columnCodeName",
    "columnExpand",
    "columnName",
    "columnOrder",
    "columnSize",
    "columnType",
    "columnWidth",
    "defaultValue",
    "id",
    "isEssential",
    "useCreate",
    "useList",
    "useScreen",
    "useUpdate",
    "useView"
})
public class BbsColumnMaster
    extends BaseDomain
{

    protected String columnAttribute;
    protected String columnCode;
    protected String columnCodeName;
    protected String columnExpand;
    protected String columnName;
    protected Long columnOrder;
    protected String columnSize;
    protected String columnType;
    protected String columnWidth;
    protected String defaultValue;
    protected BbsColumnMasterId id;
    protected String isEssential;
    protected String useCreate;
    protected String useList;
    protected String useScreen;
    protected String useUpdate;
    protected String useView;

    /**
     * Gets the value of the columnAttribute property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColumnAttribute() {
        return columnAttribute;
    }

    /**
     * Sets the value of the columnAttribute property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColumnAttribute(String value) {
        this.columnAttribute = value;
    }

    /**
     * Gets the value of the columnCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColumnCode() {
        return columnCode;
    }

    /**
     * Sets the value of the columnCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColumnCode(String value) {
        this.columnCode = value;
    }

    /**
     * Gets the value of the columnCodeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColumnCodeName() {
        return columnCodeName;
    }

    /**
     * Sets the value of the columnCodeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColumnCodeName(String value) {
        this.columnCodeName = value;
    }

    /**
     * Gets the value of the columnExpand property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColumnExpand() {
        return columnExpand;
    }

    /**
     * Sets the value of the columnExpand property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColumnExpand(String value) {
        this.columnExpand = value;
    }

    /**
     * Gets the value of the columnName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * Sets the value of the columnName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColumnName(String value) {
        this.columnName = value;
    }

    /**
     * Gets the value of the columnOrder property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getColumnOrder() {
        return columnOrder;
    }

    /**
     * Sets the value of the columnOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setColumnOrder(Long value) {
        this.columnOrder = value;
    }

    /**
     * Gets the value of the columnSize property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColumnSize() {
        return columnSize;
    }

    /**
     * Sets the value of the columnSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColumnSize(String value) {
        this.columnSize = value;
    }

    /**
     * Gets the value of the columnType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColumnType() {
        return columnType;
    }

    /**
     * Sets the value of the columnType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColumnType(String value) {
        this.columnType = value;
    }

    /**
     * Gets the value of the columnWidth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColumnWidth() {
        return columnWidth;
    }

    /**
     * Sets the value of the columnWidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColumnWidth(String value) {
        this.columnWidth = value;
    }

    /**
     * Gets the value of the defaultValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * Sets the value of the defaultValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultValue(String value) {
        this.defaultValue = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BbsColumnMasterId }
     *     
     */
    public BbsColumnMasterId getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BbsColumnMasterId }
     *     
     */
    public void setId(BbsColumnMasterId value) {
        this.id = value;
    }

    /**
     * Gets the value of the isEssential property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsEssential() {
        return isEssential;
    }

    /**
     * Sets the value of the isEssential property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsEssential(String value) {
        this.isEssential = value;
    }

    /**
     * Gets the value of the useCreate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseCreate() {
        return useCreate;
    }

    /**
     * Sets the value of the useCreate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseCreate(String value) {
        this.useCreate = value;
    }

    /**
     * Gets the value of the useList property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseList() {
        return useList;
    }

    /**
     * Sets the value of the useList property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseList(String value) {
        this.useList = value;
    }

    /**
     * Gets the value of the useScreen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseScreen() {
        return useScreen;
    }

    /**
     * Sets the value of the useScreen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseScreen(String value) {
        this.useScreen = value;
    }

    /**
     * Gets the value of the useUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseUpdate() {
        return useUpdate;
    }

    /**
     * Sets the value of the useUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseUpdate(String value) {
        this.useUpdate = value;
    }

    /**
     * Gets the value of the useView property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseView() {
        return useView;
    }

    /**
     * Sets the value of the useView property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseView(String value) {
        this.useView = value;
    }

}
