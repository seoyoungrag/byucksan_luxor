
package com.sds.acube.luxor.ws.client.bbs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for bbsPostCommon complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bbsPostCommon">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.bbs.collaboration.luxor.acube.sds.com/}baseDomain">
 *       &lt;sequence>
 *         &lt;element name="accessids" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attachId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bbsUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="boardIds" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="boardName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="boardType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="boomupType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="categoryId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="categoryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="commentCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="creatorDeptId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creatorDeptName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creatorUid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="delPostId" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="doType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="editortype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="exColumn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="favoriteId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fileHisFid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="filePostAttachId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="filePostAttachName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradeFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradeTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hasReply" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="headerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="headerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://ws.bbs.collaboration.luxor.acube.sds.com/}bbsPostCommonId" minOccurs="0"/>
 *         &lt;element name="imageAttachId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imageThumbnail" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="isEmergency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isFilePost" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isNotice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isReply" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isReserve" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isSecretPost" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isSecurity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isTempSave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isView" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="newPostTerm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="parentPostId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postDepth" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="postIds" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="postLifecycleEnddate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preview" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rcmdCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="register" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="registerPwd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reportType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reserveDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reservedAccessInfos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourceBoardId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourcePostId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tableId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tableName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tagName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="targetBoardId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="targetPostId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tempReg" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="topPostId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trackBackBoardId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trackBackPostId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trackBackUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="updateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="updaterDeptId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="updaterDeptName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="updaterName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="updaterUid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="useAnonymous" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="useMime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="useNewPost" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usePostSecurity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="variables" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="viewCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="xid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="yesCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="ynType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bbsPostCommon", propOrder = {
    "accessids",
    "attachId",
    "attr",
    "bbsUrl",
    "boardIds",
    "boardName",
    "boardType",
    "boomupType",
    "categoryId",
    "categoryName",
    "commentCount",
    "content",
    "createDate",
    "creatorDeptId",
    "creatorDeptName",
    "creatorUid",
    "delPostId",
    "doType",
    "editortype",
    "exColumn",
    "favoriteId",
    "fileHisFid",
    "filePostAttachId",
    "filePostAttachName",
    "gradeFrom",
    "gradeTo",
    "hasReply",
    "headerId",
    "headerName",
    "id",
    "imageAttachId",
    "imageThumbnail",
    "isEmergency",
    "isFilePost",
    "isNotice",
    "isReply",
    "isReserve",
    "isSecretPost",
    "isSecurity",
    "isTempSave",
    "isView",
    "newPostTerm",
    "noCount",
    "parentPostId",
    "postDepth",
    "postIds",
    "postLifecycleEnddate",
    "postTitle",
    "postType",
    "preview",
    "rcmdCount",
    "register",
    "registerPwd",
    "reportType",
    "reserveDate",
    "reservedAccessInfos",
    "sourceBoardId",
    "sourcePostId",
    "tableId",
    "tableName",
    "tagName",
    "targetBoardId",
    "targetPostId",
    "tempReg",
    "topPostId",
    "trackBackBoardId",
    "trackBackPostId",
    "trackBackUrl",
    "updateDate",
    "updaterDeptId",
    "updaterDeptName",
    "updaterName",
    "updaterUid",
    "useAnonymous",
    "useMime",
    "useNewPost",
    "usePostSecurity",
    "userType",
    "variables",
    "viewCount",
    "xid",
    "yesCount",
    "ynType"
})
public class BbsPostCommon
    extends BaseDomain
{

    protected String accessids;
    protected String attachId;
    protected String attr;
    protected String bbsUrl;
    @XmlElement(nillable = true)
    protected List<String> boardIds;
    protected String boardName;
    protected String boardType;
    protected String boomupType;
    protected String categoryId;
    protected String categoryName;
    protected Long commentCount;
    protected String content;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createDate;
    protected String creatorDeptId;
    protected String creatorDeptName;
    protected String creatorUid;
    @XmlElement(nillable = true)
    protected List<String> delPostId;
    protected String doType;
    protected String editortype;
    protected String exColumn;
    protected String favoriteId;
    protected String fileHisFid;
    protected String filePostAttachId;
    protected String filePostAttachName;
    protected String gradeFrom;
    protected String gradeTo;
    protected String hasReply;
    protected String headerId;
    protected String headerName;
    protected BbsPostCommonId id;
    protected String imageAttachId;
    protected byte[] imageThumbnail;
    protected String isEmergency;
    protected String isFilePost;
    protected String isNotice;
    protected String isReply;
    protected String isReserve;
    protected String isSecretPost;
    protected String isSecurity;
    protected String isTempSave;
    protected int isView;
    protected String newPostTerm;
    protected Long noCount;
    protected String parentPostId;
    protected Long postDepth;
    @XmlElement(nillable = true)
    protected List<String> postIds;
    protected String postLifecycleEnddate;
    protected String postTitle;
    protected String postType;
    protected String preview;
    protected Long rcmdCount;
    protected String register;
    protected String registerPwd;
    protected String reportType;
    protected String reserveDate;
    protected String reservedAccessInfos;
    protected String sourceBoardId;
    protected String sourcePostId;
    protected String tableId;
    protected String tableName;
    protected String tagName;
    protected String targetBoardId;
    protected String targetPostId;
    protected int tempReg;
    protected String topPostId;
    protected String trackBackBoardId;
    protected String trackBackPostId;
    protected String trackBackUrl;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateDate;
    protected String updaterDeptId;
    protected String updaterDeptName;
    protected String updaterName;
    protected String updaterUid;
    protected String useAnonymous;
    protected String useMime;
    protected String useNewPost;
    protected String usePostSecurity;
    protected String userType;
    protected String variables;
    protected Long viewCount;
    protected String xid;
    protected Long yesCount;
    protected String ynType;

    /**
     * Gets the value of the accessids property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessids() {
        return accessids;
    }

    /**
     * Sets the value of the accessids property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessids(String value) {
        this.accessids = value;
    }

    /**
     * Gets the value of the attachId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttachId() {
        return attachId;
    }

    /**
     * Sets the value of the attachId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttachId(String value) {
        this.attachId = value;
    }

    /**
     * Gets the value of the attr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttr() {
        return attr;
    }

    /**
     * Sets the value of the attr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttr(String value) {
        this.attr = value;
    }

    /**
     * Gets the value of the bbsUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBbsUrl() {
        return bbsUrl;
    }

    /**
     * Sets the value of the bbsUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBbsUrl(String value) {
        this.bbsUrl = value;
    }

    /**
     * Gets the value of the boardIds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the boardIds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBoardIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getBoardIds() {
        if (boardIds == null) {
            boardIds = new ArrayList<String>();
        }
        return this.boardIds;
    }

    /**
     * Gets the value of the boardName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBoardName() {
        return boardName;
    }

    /**
     * Sets the value of the boardName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBoardName(String value) {
        this.boardName = value;
    }

    /**
     * Gets the value of the boardType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBoardType() {
        return boardType;
    }

    /**
     * Sets the value of the boardType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBoardType(String value) {
        this.boardType = value;
    }

    /**
     * Gets the value of the boomupType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBoomupType() {
        return boomupType;
    }

    /**
     * Sets the value of the boomupType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBoomupType(String value) {
        this.boomupType = value;
    }

    /**
     * Gets the value of the categoryId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the value of the categoryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryId(String value) {
        this.categoryId = value;
    }

    /**
     * Gets the value of the categoryName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets the value of the categoryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryName(String value) {
        this.categoryName = value;
    }

    /**
     * Gets the value of the commentCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCommentCount() {
        return commentCount;
    }

    /**
     * Sets the value of the commentCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCommentCount(Long value) {
        this.commentCount = value;
    }

    /**
     * Gets the value of the content property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * Gets the value of the createDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateDate() {
        return createDate;
    }

    /**
     * Sets the value of the createDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateDate(XMLGregorianCalendar value) {
        this.createDate = value;
    }

    /**
     * Gets the value of the creatorDeptId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatorDeptId() {
        return creatorDeptId;
    }

    /**
     * Sets the value of the creatorDeptId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatorDeptId(String value) {
        this.creatorDeptId = value;
    }

    /**
     * Gets the value of the creatorDeptName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatorDeptName() {
        return creatorDeptName;
    }

    /**
     * Sets the value of the creatorDeptName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatorDeptName(String value) {
        this.creatorDeptName = value;
    }

    /**
     * Gets the value of the creatorUid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatorUid() {
        return creatorUid;
    }

    /**
     * Sets the value of the creatorUid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatorUid(String value) {
        this.creatorUid = value;
    }

    /**
     * Gets the value of the delPostId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the delPostId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDelPostId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDelPostId() {
        if (delPostId == null) {
            delPostId = new ArrayList<String>();
        }
        return this.delPostId;
    }

    /**
     * Gets the value of the doType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDoType() {
        return doType;
    }

    /**
     * Sets the value of the doType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDoType(String value) {
        this.doType = value;
    }

    /**
     * Gets the value of the editortype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEditortype() {
        return editortype;
    }

    /**
     * Sets the value of the editortype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEditortype(String value) {
        this.editortype = value;
    }

    /**
     * Gets the value of the exColumn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExColumn() {
        return exColumn;
    }

    /**
     * Sets the value of the exColumn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExColumn(String value) {
        this.exColumn = value;
    }

    /**
     * Gets the value of the favoriteId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFavoriteId() {
        return favoriteId;
    }

    /**
     * Sets the value of the favoriteId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFavoriteId(String value) {
        this.favoriteId = value;
    }

    /**
     * Gets the value of the fileHisFid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileHisFid() {
        return fileHisFid;
    }

    /**
     * Sets the value of the fileHisFid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileHisFid(String value) {
        this.fileHisFid = value;
    }

    /**
     * Gets the value of the filePostAttachId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilePostAttachId() {
        return filePostAttachId;
    }

    /**
     * Sets the value of the filePostAttachId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilePostAttachId(String value) {
        this.filePostAttachId = value;
    }

    /**
     * Gets the value of the filePostAttachName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilePostAttachName() {
        return filePostAttachName;
    }

    /**
     * Sets the value of the filePostAttachName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilePostAttachName(String value) {
        this.filePostAttachName = value;
    }

    /**
     * Gets the value of the gradeFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGradeFrom() {
        return gradeFrom;
    }

    /**
     * Sets the value of the gradeFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGradeFrom(String value) {
        this.gradeFrom = value;
    }

    /**
     * Gets the value of the gradeTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGradeTo() {
        return gradeTo;
    }

    /**
     * Sets the value of the gradeTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGradeTo(String value) {
        this.gradeTo = value;
    }

    /**
     * Gets the value of the hasReply property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHasReply() {
        return hasReply;
    }

    /**
     * Sets the value of the hasReply property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHasReply(String value) {
        this.hasReply = value;
    }

    /**
     * Gets the value of the headerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHeaderId() {
        return headerId;
    }

    /**
     * Sets the value of the headerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHeaderId(String value) {
        this.headerId = value;
    }

    /**
     * Gets the value of the headerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHeaderName() {
        return headerName;
    }

    /**
     * Sets the value of the headerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHeaderName(String value) {
        this.headerName = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BbsPostCommonId }
     *     
     */
    public BbsPostCommonId getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BbsPostCommonId }
     *     
     */
    public void setId(BbsPostCommonId value) {
        this.id = value;
    }

    /**
     * Gets the value of the imageAttachId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageAttachId() {
        return imageAttachId;
    }

    /**
     * Sets the value of the imageAttachId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageAttachId(String value) {
        this.imageAttachId = value;
    }

    /**
     * Gets the value of the imageThumbnail property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getImageThumbnail() {
        return imageThumbnail;
    }

    /**
     * Sets the value of the imageThumbnail property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setImageThumbnail(byte[] value) {
        this.imageThumbnail = value;
    }

    /**
     * Gets the value of the isEmergency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsEmergency() {
        return isEmergency;
    }

    /**
     * Sets the value of the isEmergency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsEmergency(String value) {
        this.isEmergency = value;
    }

    /**
     * Gets the value of the isFilePost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsFilePost() {
        return isFilePost;
    }

    /**
     * Sets the value of the isFilePost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsFilePost(String value) {
        this.isFilePost = value;
    }

    /**
     * Gets the value of the isNotice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsNotice() {
        return isNotice;
    }

    /**
     * Sets the value of the isNotice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsNotice(String value) {
        this.isNotice = value;
    }

    /**
     * Gets the value of the isReply property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsReply() {
        return isReply;
    }

    /**
     * Sets the value of the isReply property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsReply(String value) {
        this.isReply = value;
    }

    /**
     * Gets the value of the isReserve property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsReserve() {
        return isReserve;
    }

    /**
     * Sets the value of the isReserve property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsReserve(String value) {
        this.isReserve = value;
    }

    /**
     * Gets the value of the isSecretPost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsSecretPost() {
        return isSecretPost;
    }

    /**
     * Sets the value of the isSecretPost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsSecretPost(String value) {
        this.isSecretPost = value;
    }

    /**
     * Gets the value of the isSecurity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsSecurity() {
        return isSecurity;
    }

    /**
     * Sets the value of the isSecurity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsSecurity(String value) {
        this.isSecurity = value;
    }

    /**
     * Gets the value of the isTempSave property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsTempSave() {
        return isTempSave;
    }

    /**
     * Sets the value of the isTempSave property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsTempSave(String value) {
        this.isTempSave = value;
    }

    /**
     * Gets the value of the isView property.
     * 
     */
    public int getIsView() {
        return isView;
    }

    /**
     * Sets the value of the isView property.
     * 
     */
    public void setIsView(int value) {
        this.isView = value;
    }

    /**
     * Gets the value of the newPostTerm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewPostTerm() {
        return newPostTerm;
    }

    /**
     * Sets the value of the newPostTerm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewPostTerm(String value) {
        this.newPostTerm = value;
    }

    /**
     * Gets the value of the noCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNoCount() {
        return noCount;
    }

    /**
     * Sets the value of the noCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNoCount(Long value) {
        this.noCount = value;
    }

    /**
     * Gets the value of the parentPostId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentPostId() {
        return parentPostId;
    }

    /**
     * Sets the value of the parentPostId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentPostId(String value) {
        this.parentPostId = value;
    }

    /**
     * Gets the value of the postDepth property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPostDepth() {
        return postDepth;
    }

    /**
     * Sets the value of the postDepth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPostDepth(Long value) {
        this.postDepth = value;
    }

    /**
     * Gets the value of the postIds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the postIds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPostIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPostIds() {
        if (postIds == null) {
            postIds = new ArrayList<String>();
        }
        return this.postIds;
    }

    /**
     * Gets the value of the postLifecycleEnddate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostLifecycleEnddate() {
        return postLifecycleEnddate;
    }

    /**
     * Sets the value of the postLifecycleEnddate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostLifecycleEnddate(String value) {
        this.postLifecycleEnddate = value;
    }

    /**
     * Gets the value of the postTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostTitle() {
        return postTitle;
    }

    /**
     * Sets the value of the postTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostTitle(String value) {
        this.postTitle = value;
    }

    /**
     * Gets the value of the postType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostType() {
        return postType;
    }

    /**
     * Sets the value of the postType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostType(String value) {
        this.postType = value;
    }

    /**
     * Gets the value of the preview property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreview() {
        return preview;
    }

    /**
     * Sets the value of the preview property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreview(String value) {
        this.preview = value;
    }

    /**
     * Gets the value of the rcmdCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRcmdCount() {
        return rcmdCount;
    }

    /**
     * Sets the value of the rcmdCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRcmdCount(Long value) {
        this.rcmdCount = value;
    }

    /**
     * Gets the value of the register property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegister() {
        return register;
    }

    /**
     * Sets the value of the register property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegister(String value) {
        this.register = value;
    }

    /**
     * Gets the value of the registerPwd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegisterPwd() {
        return registerPwd;
    }

    /**
     * Sets the value of the registerPwd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegisterPwd(String value) {
        this.registerPwd = value;
    }

    /**
     * Gets the value of the reportType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportType() {
        return reportType;
    }

    /**
     * Sets the value of the reportType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportType(String value) {
        this.reportType = value;
    }

    /**
     * Gets the value of the reserveDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserveDate() {
        return reserveDate;
    }

    /**
     * Sets the value of the reserveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserveDate(String value) {
        this.reserveDate = value;
    }

    /**
     * Gets the value of the reservedAccessInfos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReservedAccessInfos() {
        return reservedAccessInfos;
    }

    /**
     * Sets the value of the reservedAccessInfos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReservedAccessInfos(String value) {
        this.reservedAccessInfos = value;
    }

    /**
     * Gets the value of the sourceBoardId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceBoardId() {
        return sourceBoardId;
    }

    /**
     * Sets the value of the sourceBoardId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceBoardId(String value) {
        this.sourceBoardId = value;
    }

    /**
     * Gets the value of the sourcePostId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourcePostId() {
        return sourcePostId;
    }

    /**
     * Sets the value of the sourcePostId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourcePostId(String value) {
        this.sourcePostId = value;
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
     * Gets the value of the tableName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Sets the value of the tableName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTableName(String value) {
        this.tableName = value;
    }

    /**
     * Gets the value of the tagName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * Sets the value of the tagName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTagName(String value) {
        this.tagName = value;
    }

    /**
     * Gets the value of the targetBoardId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetBoardId() {
        return targetBoardId;
    }

    /**
     * Sets the value of the targetBoardId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetBoardId(String value) {
        this.targetBoardId = value;
    }

    /**
     * Gets the value of the targetPostId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetPostId() {
        return targetPostId;
    }

    /**
     * Sets the value of the targetPostId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetPostId(String value) {
        this.targetPostId = value;
    }

    /**
     * Gets the value of the tempReg property.
     * 
     */
    public int getTempReg() {
        return tempReg;
    }

    /**
     * Sets the value of the tempReg property.
     * 
     */
    public void setTempReg(int value) {
        this.tempReg = value;
    }

    /**
     * Gets the value of the topPostId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTopPostId() {
        return topPostId;
    }

    /**
     * Sets the value of the topPostId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTopPostId(String value) {
        this.topPostId = value;
    }

    /**
     * Gets the value of the trackBackBoardId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackBackBoardId() {
        return trackBackBoardId;
    }

    /**
     * Sets the value of the trackBackBoardId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackBackBoardId(String value) {
        this.trackBackBoardId = value;
    }

    /**
     * Gets the value of the trackBackPostId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackBackPostId() {
        return trackBackPostId;
    }

    /**
     * Sets the value of the trackBackPostId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackBackPostId(String value) {
        this.trackBackPostId = value;
    }

    /**
     * Gets the value of the trackBackUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackBackUrl() {
        return trackBackUrl;
    }

    /**
     * Sets the value of the trackBackUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackBackUrl(String value) {
        this.trackBackUrl = value;
    }

    /**
     * Gets the value of the updateDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateDate() {
        return updateDate;
    }

    /**
     * Sets the value of the updateDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdateDate(XMLGregorianCalendar value) {
        this.updateDate = value;
    }

    /**
     * Gets the value of the updaterDeptId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdaterDeptId() {
        return updaterDeptId;
    }

    /**
     * Sets the value of the updaterDeptId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdaterDeptId(String value) {
        this.updaterDeptId = value;
    }

    /**
     * Gets the value of the updaterDeptName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdaterDeptName() {
        return updaterDeptName;
    }

    /**
     * Sets the value of the updaterDeptName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdaterDeptName(String value) {
        this.updaterDeptName = value;
    }

    /**
     * Gets the value of the updaterName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdaterName() {
        return updaterName;
    }

    /**
     * Sets the value of the updaterName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdaterName(String value) {
        this.updaterName = value;
    }

    /**
     * Gets the value of the updaterUid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdaterUid() {
        return updaterUid;
    }

    /**
     * Sets the value of the updaterUid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdaterUid(String value) {
        this.updaterUid = value;
    }

    /**
     * Gets the value of the useAnonymous property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseAnonymous() {
        return useAnonymous;
    }

    /**
     * Sets the value of the useAnonymous property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseAnonymous(String value) {
        this.useAnonymous = value;
    }

    /**
     * Gets the value of the useMime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseMime() {
        return useMime;
    }

    /**
     * Sets the value of the useMime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseMime(String value) {
        this.useMime = value;
    }

    /**
     * Gets the value of the useNewPost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseNewPost() {
        return useNewPost;
    }

    /**
     * Sets the value of the useNewPost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseNewPost(String value) {
        this.useNewPost = value;
    }

    /**
     * Gets the value of the usePostSecurity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsePostSecurity() {
        return usePostSecurity;
    }

    /**
     * Sets the value of the usePostSecurity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsePostSecurity(String value) {
        this.usePostSecurity = value;
    }

    /**
     * Gets the value of the userType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Sets the value of the userType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserType(String value) {
        this.userType = value;
    }

    /**
     * Gets the value of the variables property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVariables() {
        return variables;
    }

    /**
     * Sets the value of the variables property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVariables(String value) {
        this.variables = value;
    }

    /**
     * Gets the value of the viewCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getViewCount() {
        return viewCount;
    }

    /**
     * Sets the value of the viewCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setViewCount(Long value) {
        this.viewCount = value;
    }

    /**
     * Gets the value of the xid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXid() {
        return xid;
    }

    /**
     * Sets the value of the xid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXid(String value) {
        this.xid = value;
    }

    /**
     * Gets the value of the yesCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getYesCount() {
        return yesCount;
    }

    /**
     * Sets the value of the yesCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setYesCount(Long value) {
        this.yesCount = value;
    }

    /**
     * Gets the value of the ynType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYnType() {
        return ynType;
    }

    /**
     * Sets the value of the ynType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYnType(String value) {
        this.ynType = value;
    }

}
