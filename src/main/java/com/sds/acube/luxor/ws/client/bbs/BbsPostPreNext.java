
package com.sds.acube.luxor.ws.client.bbs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bbsPostPreNext complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bbsPostPreNext">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nextPost" type="{http://ws.bbs.collaboration.luxor.acube.sds.com/}bbsPostCommon" minOccurs="0"/>
 *         &lt;element name="postIndex" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="prePost" type="{http://ws.bbs.collaboration.luxor.acube.sds.com/}bbsPostCommon" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bbsPostPreNext", propOrder = {
    "nextPost",
    "postIndex",
    "prePost"
})
public class BbsPostPreNext {

    protected BbsPostCommon nextPost;
    protected int postIndex;
    protected BbsPostCommon prePost;

    /**
     * Gets the value of the nextPost property.
     * 
     * @return
     *     possible object is
     *     {@link BbsPostCommon }
     *     
     */
    public BbsPostCommon getNextPost() {
        return nextPost;
    }

    /**
     * Sets the value of the nextPost property.
     * 
     * @param value
     *     allowed object is
     *     {@link BbsPostCommon }
     *     
     */
    public void setNextPost(BbsPostCommon value) {
        this.nextPost = value;
    }

    /**
     * Gets the value of the postIndex property.
     * 
     */
    public int getPostIndex() {
        return postIndex;
    }

    /**
     * Sets the value of the postIndex property.
     * 
     */
    public void setPostIndex(int value) {
        this.postIndex = value;
    }

    /**
     * Gets the value of the prePost property.
     * 
     * @return
     *     possible object is
     *     {@link BbsPostCommon }
     *     
     */
    public BbsPostCommon getPrePost() {
        return prePost;
    }

    /**
     * Sets the value of the prePost property.
     * 
     * @param value
     *     allowed object is
     *     {@link BbsPostCommon }
     *     
     */
    public void setPrePost(BbsPostCommon value) {
        this.prePost = value;
    }

}
