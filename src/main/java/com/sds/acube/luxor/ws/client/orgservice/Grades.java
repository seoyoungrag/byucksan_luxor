
package com.sds.acube.luxor.ws.client.orgservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for grades complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="grades">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="gradeList" type="{http://service.org.cn.acube.sds.com/}grade" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "grades", propOrder = {
    "gradeList"
})
public class Grades {

    @XmlElement(nillable = true)
    protected List<Grade> gradeList;

    /**
     * Gets the value of the gradeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gradeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGradeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Grade }
     * 
     * 
     */
    public List<Grade> getGradeList() {
        if (gradeList == null) {
            gradeList = new ArrayList<Grade>();
        }
        return this.gradeList;
    }

}
