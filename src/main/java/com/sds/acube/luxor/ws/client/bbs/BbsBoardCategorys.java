
package com.sds.acube.luxor.ws.client.bbs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bbsBoardCategorys complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bbsBoardCategorys">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="categoryList" type="{http://ws.bbs.collaboration.luxor.acube.sds.com/}bbsBoardCategory" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="depth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="parentCategoryId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="selectedCategoryId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bbsBoardCategorys", propOrder = {
    "categoryList",
    "depth",
    "parentCategoryId",
    "selectedCategoryId"
})
public class BbsBoardCategorys {

    @XmlElement(nillable = true)
    protected List<BbsBoardCategory> categoryList;
    protected int depth;
    protected String parentCategoryId;
    protected String selectedCategoryId;

    /**
     * Gets the value of the categoryList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the categoryList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategoryList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BbsBoardCategory }
     * 
     * 
     */
    public List<BbsBoardCategory> getCategoryList() {
        if (categoryList == null) {
            categoryList = new ArrayList<BbsBoardCategory>();
        }
        return this.categoryList;
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

    /**
     * Gets the value of the selectedCategoryId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectedCategoryId() {
        return selectedCategoryId;
    }

    /**
     * Sets the value of the selectedCategoryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectedCategoryId(String value) {
        this.selectedCategoryId = value;
    }

}
