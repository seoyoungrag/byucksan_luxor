
package com.sds.acube.luxor.ws.client.orgservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for iUsers complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="iUsers">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="searchTotalCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="userList" type="{http://service.org.cn.acube.sds.com/}iUser" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "iUsers", propOrder = {
    "searchTotalCount",
    "userList"
})
public class IUsers {

    protected int searchTotalCount;
    @XmlElement(nillable = true)
    protected List<IUser> userList;

    /**
     * Gets the value of the searchTotalCount property.
     * 
     */
    public int getSearchTotalCount() {
        return searchTotalCount;
    }

    /**
     * Sets the value of the searchTotalCount property.
     * 
     */
    public void setSearchTotalCount(int value) {
        this.searchTotalCount = value;
    }

    /**
     * Gets the value of the userList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IUser }
     * 
     * 
     */
    public List<IUser> getUserList() {
        if (userList == null) {
            userList = new ArrayList<IUser>();
        }
        return this.userList;
    }

}
