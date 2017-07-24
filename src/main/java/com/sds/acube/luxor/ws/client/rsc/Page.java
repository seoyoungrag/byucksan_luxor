
package com.sds.acube.luxor.ws.client.rsc;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for page complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="page">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="beginUnitPage" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="condition" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currentPage" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="currentPageStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emptyPage" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="endListPage" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="endUnitPage" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="list" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="maxPage" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nextPage" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="objects" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="pagesize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pageunit" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="previousPage" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="search" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="total" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "page", propOrder = {
    "beginUnitPage",
    "condition",
    "currentPage",
    "currentPageStr",
    "emptyPage",
    "endListPage",
    "endUnitPage",
    "list",
    "maxPage",
    "nextPage",
    "objects",
    "pagesize",
    "pageunit",
    "previousPage",
    "search",
    "total",
    "totalCount"
})
public class Page {

    protected int beginUnitPage;
    protected String condition;
    protected int currentPage;
    protected String currentPageStr;
    protected boolean emptyPage;
    protected int endListPage;
    protected int endUnitPage;
    @XmlElement(nillable = true)
    protected List<Object> list;
    protected int maxPage;
    protected int nextPage;
    @XmlElement(nillable = true)
    protected List<Object> objects;
    protected int pagesize;
    protected int pageunit;
    protected int previousPage;
    protected String search;
    protected int total;
    protected int totalCount;

    /**
     * Gets the value of the beginUnitPage property.
     * 
     */
    public int getBeginUnitPage() {
        return beginUnitPage;
    }

    /**
     * Sets the value of the beginUnitPage property.
     * 
     */
    public void setBeginUnitPage(int value) {
        this.beginUnitPage = value;
    }

    /**
     * Gets the value of the condition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Sets the value of the condition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCondition(String value) {
        this.condition = value;
    }

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
     * Gets the value of the currentPageStr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentPageStr() {
        return currentPageStr;
    }

    /**
     * Sets the value of the currentPageStr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentPageStr(String value) {
        this.currentPageStr = value;
    }

    /**
     * Gets the value of the emptyPage property.
     * 
     */
    public boolean isEmptyPage() {
        return emptyPage;
    }

    /**
     * Sets the value of the emptyPage property.
     * 
     */
    public void setEmptyPage(boolean value) {
        this.emptyPage = value;
    }

    /**
     * Gets the value of the endListPage property.
     * 
     */
    public int getEndListPage() {
        return endListPage;
    }

    /**
     * Sets the value of the endListPage property.
     * 
     */
    public void setEndListPage(int value) {
        this.endListPage = value;
    }

    /**
     * Gets the value of the endUnitPage property.
     * 
     */
    public int getEndUnitPage() {
        return endUnitPage;
    }

    /**
     * Sets the value of the endUnitPage property.
     * 
     */
    public void setEndUnitPage(int value) {
        this.endUnitPage = value;
    }

    /**
     * Gets the value of the list property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the list property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getList() {
        if (list == null) {
            list = new ArrayList<Object>();
        }
        return this.list;
    }

    /**
     * Gets the value of the maxPage property.
     * 
     */
    public int getMaxPage() {
        return maxPage;
    }

    /**
     * Sets the value of the maxPage property.
     * 
     */
    public void setMaxPage(int value) {
        this.maxPage = value;
    }

    /**
     * Gets the value of the nextPage property.
     * 
     */
    public int getNextPage() {
        return nextPage;
    }

    /**
     * Sets the value of the nextPage property.
     * 
     */
    public void setNextPage(int value) {
        this.nextPage = value;
    }

    /**
     * Gets the value of the objects property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the objects property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObjects().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getObjects() {
        if (objects == null) {
            objects = new ArrayList<Object>();
        }
        return this.objects;
    }

    /**
     * Gets the value of the pagesize property.
     * 
     */
    public int getPagesize() {
        return pagesize;
    }

    /**
     * Sets the value of the pagesize property.
     * 
     */
    public void setPagesize(int value) {
        this.pagesize = value;
    }

    /**
     * Gets the value of the pageunit property.
     * 
     */
    public int getPageunit() {
        return pageunit;
    }

    /**
     * Sets the value of the pageunit property.
     * 
     */
    public void setPageunit(int value) {
        this.pageunit = value;
    }

    /**
     * Gets the value of the previousPage property.
     * 
     */
    public int getPreviousPage() {
        return previousPage;
    }

    /**
     * Sets the value of the previousPage property.
     * 
     */
    public void setPreviousPage(int value) {
        this.previousPage = value;
    }

    /**
     * Gets the value of the search property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearch() {
        return search;
    }

    /**
     * Sets the value of the search property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearch(String value) {
        this.search = value;
    }

    /**
     * Gets the value of the total property.
     * 
     */
    public int getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     */
    public void setTotal(int value) {
        this.total = value;
    }

    /**
     * Gets the value of the totalCount property.
     * 
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * Sets the value of the totalCount property.
     * 
     */
    public void setTotalCount(int value) {
        this.totalCount = value;
    }

}
