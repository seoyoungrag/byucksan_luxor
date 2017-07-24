
package com.sds.acube.luxor.ws.client.aclservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for baseDMO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="baseDMO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="currentPage" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="orderValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ROW_COUNT" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="rowPerPage" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="useNavigation" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "baseDMO", propOrder = {
    "currentPage",
    "orderValue",
    "rowcount",
    "rowPerPage",
    "useNavigation"
})
@XmlSeeAlso({
    GroupInfo.class,
    RoleInfo.class,
    AccessList.class,
    GroupMemberInfo.class
})
public class BaseDMO {

    protected int currentPage;
    protected String orderValue;
    @XmlElement(name = "ROW_COUNT")
    protected int rowcount;
    protected int rowPerPage;
    protected boolean useNavigation;

    /**
     * Gets the value of the currentPage property.
     * 
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * Sets the value of the currentPage property.
     * 
     */
    public void setCurrentPage(int value) {
        this.currentPage = value;
    }

    /**
     * Gets the value of the orderValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderValue() {
        return orderValue;
    }

    /**
     * Sets the value of the orderValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderValue(String value) {
        this.orderValue = value;
    }

    /**
     * Gets the value of the rowcount property.
     * 
     */
    public int getROWCOUNT() {
        return rowcount;
    }

    /**
     * Sets the value of the rowcount property.
     * 
     */
    public void setROWCOUNT(int value) {
        this.rowcount = value;
    }

    /**
     * Gets the value of the rowPerPage property.
     * 
     */
    public int getRowPerPage() {
        return rowPerPage;
    }

    /**
     * Sets the value of the rowPerPage property.
     * 
     */
    public void setRowPerPage(int value) {
        this.rowPerPage = value;
    }

    /**
     * Gets the value of the useNavigation property.
     * 
     */
    public boolean isUseNavigation() {
        return useNavigation;
    }

    /**
     * Sets the value of the useNavigation property.
     * 
     */
    public void setUseNavigation(boolean value) {
        this.useNavigation = value;
    }

}
