
package com.sds.acube.luxor.ws.client.bbs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bbsBoardCodes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bbsBoardCodes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="depth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nodeList" type="{http://ws.bbs.collaboration.luxor.acube.sds.com/}comCode" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="parentNodeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="selectedNodeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bbsBoardCodes", propOrder = {
    "depth",
    "nodeList",
    "parentNodeId",
    "selectedNodeId"
})
public class BbsBoardCodes {

    protected int depth;
    @XmlElement(nillable = true)
    protected List<ComCode> nodeList;
    protected String parentNodeId;
    protected String selectedNodeId;

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
     * Gets the value of the nodeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nodeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNodeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComCode }
     * 
     * 
     */
    public List<ComCode> getNodeList() {
        if (nodeList == null) {
            nodeList = new ArrayList<ComCode>();
        }
        return this.nodeList;
    }

    /**
     * Gets the value of the parentNodeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentNodeId() {
        return parentNodeId;
    }

    /**
     * Sets the value of the parentNodeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentNodeId(String value) {
        this.parentNodeId = value;
    }

    /**
     * Gets the value of the selectedNodeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectedNodeId() {
        return selectedNodeId;
    }

    /**
     * Sets the value of the selectedNodeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectedNodeId(String value) {
        this.selectedNodeId = value;
    }

}
