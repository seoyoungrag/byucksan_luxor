
package com.sds.acube.luxor.ws.client.bbs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bbsBoardUiCru complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bbsBoardUiCru">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://ws.bbs.collaboration.luxor.acube.sds.com/}bbsBoardUiCruId" minOccurs="0"/>
 *         &lt;element name="isTemplate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="leftCodeList" type="{http://ws.bbs.collaboration.luxor.acube.sds.com/}comCode" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="leftColumnAttribute" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="leftColumnCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="leftColumnEssential" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="leftColumnId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="leftColumnName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="leftColumnSize" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="leftResourceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pageHtml" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rightCodeList" type="{http://ws.bbs.collaboration.luxor.acube.sds.com/}comCode" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="rightColumnAttribute" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rightColumnCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rightColumnEssential" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rightColumnId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rightColumnName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rightColumnSize" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rightResourceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="templateName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="useLeftColumnLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="useRightColumnLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bbsBoardUiCru", propOrder = {
    "id",
    "isTemplate",
    "leftCodeList",
    "leftColumnAttribute",
    "leftColumnCode",
    "leftColumnEssential",
    "leftColumnId",
    "leftColumnName",
    "leftColumnSize",
    "leftResourceName",
    "pageHtml",
    "rightCodeList",
    "rightColumnAttribute",
    "rightColumnCode",
    "rightColumnEssential",
    "rightColumnId",
    "rightColumnName",
    "rightColumnSize",
    "rightResourceName",
    "templateName",
    "useLeftColumnLabel",
    "useRightColumnLabel"
})
public class BbsBoardUiCru {

    protected BbsBoardUiCruId id;
    protected String isTemplate;
    @XmlElement(nillable = true)
    protected List<ComCode> leftCodeList;
    protected String leftColumnAttribute;
    protected String leftColumnCode;
    protected String leftColumnEssential;
    protected String leftColumnId;
    protected String leftColumnName;
    protected String leftColumnSize;
    protected String leftResourceName;
    protected String pageHtml;
    @XmlElement(nillable = true)
    protected List<ComCode> rightCodeList;
    protected String rightColumnAttribute;
    protected String rightColumnCode;
    protected String rightColumnEssential;
    protected String rightColumnId;
    protected String rightColumnName;
    protected String rightColumnSize;
    protected String rightResourceName;
    protected String templateName;
    protected String useLeftColumnLabel;
    protected String useRightColumnLabel;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BbsBoardUiCruId }
     *     
     */
    public BbsBoardUiCruId getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BbsBoardUiCruId }
     *     
     */
    public void setId(BbsBoardUiCruId value) {
        this.id = value;
    }

    /**
     * Gets the value of the isTemplate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsTemplate() {
        return isTemplate;
    }

    /**
     * Sets the value of the isTemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsTemplate(String value) {
        this.isTemplate = value;
    }

    /**
     * Gets the value of the leftCodeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the leftCodeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLeftCodeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComCode }
     * 
     * 
     */
    public List<ComCode> getLeftCodeList() {
        if (leftCodeList == null) {
            leftCodeList = new ArrayList<ComCode>();
        }
        return this.leftCodeList;
    }

    /**
     * Gets the value of the leftColumnAttribute property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeftColumnAttribute() {
        return leftColumnAttribute;
    }

    /**
     * Sets the value of the leftColumnAttribute property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeftColumnAttribute(String value) {
        this.leftColumnAttribute = value;
    }

    /**
     * Gets the value of the leftColumnCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeftColumnCode() {
        return leftColumnCode;
    }

    /**
     * Sets the value of the leftColumnCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeftColumnCode(String value) {
        this.leftColumnCode = value;
    }

    /**
     * Gets the value of the leftColumnEssential property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeftColumnEssential() {
        return leftColumnEssential;
    }

    /**
     * Sets the value of the leftColumnEssential property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeftColumnEssential(String value) {
        this.leftColumnEssential = value;
    }

    /**
     * Gets the value of the leftColumnId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeftColumnId() {
        return leftColumnId;
    }

    /**
     * Sets the value of the leftColumnId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeftColumnId(String value) {
        this.leftColumnId = value;
    }

    /**
     * Gets the value of the leftColumnName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeftColumnName() {
        return leftColumnName;
    }

    /**
     * Sets the value of the leftColumnName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeftColumnName(String value) {
        this.leftColumnName = value;
    }

    /**
     * Gets the value of the leftColumnSize property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeftColumnSize() {
        return leftColumnSize;
    }

    /**
     * Sets the value of the leftColumnSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeftColumnSize(String value) {
        this.leftColumnSize = value;
    }

    /**
     * Gets the value of the leftResourceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeftResourceName() {
        return leftResourceName;
    }

    /**
     * Sets the value of the leftResourceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeftResourceName(String value) {
        this.leftResourceName = value;
    }

    /**
     * Gets the value of the pageHtml property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPageHtml() {
        return pageHtml;
    }

    /**
     * Sets the value of the pageHtml property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPageHtml(String value) {
        this.pageHtml = value;
    }

    /**
     * Gets the value of the rightCodeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rightCodeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRightCodeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComCode }
     * 
     * 
     */
    public List<ComCode> getRightCodeList() {
        if (rightCodeList == null) {
            rightCodeList = new ArrayList<ComCode>();
        }
        return this.rightCodeList;
    }

    /**
     * Gets the value of the rightColumnAttribute property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRightColumnAttribute() {
        return rightColumnAttribute;
    }

    /**
     * Sets the value of the rightColumnAttribute property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRightColumnAttribute(String value) {
        this.rightColumnAttribute = value;
    }

    /**
     * Gets the value of the rightColumnCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRightColumnCode() {
        return rightColumnCode;
    }

    /**
     * Sets the value of the rightColumnCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRightColumnCode(String value) {
        this.rightColumnCode = value;
    }

    /**
     * Gets the value of the rightColumnEssential property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRightColumnEssential() {
        return rightColumnEssential;
    }

    /**
     * Sets the value of the rightColumnEssential property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRightColumnEssential(String value) {
        this.rightColumnEssential = value;
    }

    /**
     * Gets the value of the rightColumnId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRightColumnId() {
        return rightColumnId;
    }

    /**
     * Sets the value of the rightColumnId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRightColumnId(String value) {
        this.rightColumnId = value;
    }

    /**
     * Gets the value of the rightColumnName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRightColumnName() {
        return rightColumnName;
    }

    /**
     * Sets the value of the rightColumnName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRightColumnName(String value) {
        this.rightColumnName = value;
    }

    /**
     * Gets the value of the rightColumnSize property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRightColumnSize() {
        return rightColumnSize;
    }

    /**
     * Sets the value of the rightColumnSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRightColumnSize(String value) {
        this.rightColumnSize = value;
    }

    /**
     * Gets the value of the rightResourceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRightResourceName() {
        return rightResourceName;
    }

    /**
     * Sets the value of the rightResourceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRightResourceName(String value) {
        this.rightResourceName = value;
    }

    /**
     * Gets the value of the templateName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * Sets the value of the templateName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemplateName(String value) {
        this.templateName = value;
    }

    /**
     * Gets the value of the useLeftColumnLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseLeftColumnLabel() {
        return useLeftColumnLabel;
    }

    /**
     * Sets the value of the useLeftColumnLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseLeftColumnLabel(String value) {
        this.useLeftColumnLabel = value;
    }

    /**
     * Gets the value of the useRightColumnLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseRightColumnLabel() {
        return useRightColumnLabel;
    }

    /**
     * Sets the value of the useRightColumnLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseRightColumnLabel(String value) {
        this.useRightColumnLabel = value;
    }

}
