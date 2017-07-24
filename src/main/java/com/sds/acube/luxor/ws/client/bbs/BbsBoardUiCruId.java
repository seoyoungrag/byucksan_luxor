
package com.sds.acube.luxor.ws.client.bbs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bbsBoardUiCruId complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bbsBoardUiCruId">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="boardId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="columnOrder" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="portalId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="screenType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tableId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tenantId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bbsBoardUiCruId", propOrder = {
    "boardId",
    "columnOrder",
    "portalId",
    "screenType",
    "tableId",
    "tenantId"
})
public class BbsBoardUiCruId {

    protected String boardId;
    protected Long columnOrder;
    protected String portalId;
    protected String screenType;
    protected String tableId;
    protected String tenantId;

    /**
     * Gets the value of the boardId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBoardId() {
        return boardId;
    }

    /**
     * Sets the value of the boardId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBoardId(String value) {
        this.boardId = value;
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
     * Gets the value of the portalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortalId() {
        return portalId;
    }

    /**
     * Sets the value of the portalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortalId(String value) {
        this.portalId = value;
    }

    /**
     * Gets the value of the screenType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScreenType() {
        return screenType;
    }

    /**
     * Sets the value of the screenType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScreenType(String value) {
        this.screenType = value;
    }

    /**
     * Gets the value of the tableId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTableId() {
        return tableId;
    }

    /**
     * Sets the value of the tableId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTableId(String value) {
        this.tableId = value;
    }

    /**
     * Gets the value of the tenantId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * Sets the value of the tenantId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTenantId(String value) {
        this.tenantId = value;
    }

}
