
package com.sds.acube.luxor.ws.client.orgservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for duties complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="duties">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dutyList" type="{http://service.org.cn.acube.sds.com/}duty" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "duties", propOrder = {
    "dutyList"
})
public class Duties {

    @XmlElement(nillable = true)
    protected List<Duty> dutyList;

    /**
     * Gets the value of the dutyList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dutyList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDutyList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Duty }
     * 
     * 
     */
    public List<Duty> getDutyList() {
        if (dutyList == null) {
            dutyList = new ArrayList<Duty>();
        }
        return this.dutyList;
    }

}
