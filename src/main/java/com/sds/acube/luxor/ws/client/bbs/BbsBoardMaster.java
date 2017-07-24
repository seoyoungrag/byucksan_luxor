
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
 * <p>Java class for bbsBoardMaster complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bbsBoardMaster">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.bbs.collaboration.luxor.acube.sds.com/}baseDomain">
 *       &lt;sequence>
 *         &lt;element name="accessIds" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attachRestrict" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attachSize" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="aviewMlg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="boardDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="boardIds" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="boardListCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="boardName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="boardTitleImage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="boardTitleImageBlob" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="boardTitleType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="boardTitleUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="boardType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="boomupRcmdCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="boomupViewCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="categoryId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="categoryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chargerUsers" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="commentMlg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contentSize" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="createMlg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creatorUid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deleteMlg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deliberationUsers" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="deptBoardUsers" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="favBoardIds" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="formId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="headerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="headerCodeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hitNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id" type="{http://ws.bbs.collaboration.luxor.acube.sds.com/}bbsBoardMasterId" minOccurs="0"/>
 *         &lt;element name="isUserSet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="menuId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newPostTerm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postLifecycleTerm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ptlCtxName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="replyMlg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="searchName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="searchTerm" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="searchType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="skinId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sortOrder" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tableName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="uiType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="updateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="updaterName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="updaterUid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="useAnonymous" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="useBoomup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="useCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="useCharger" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="useComment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="useDeptBoard" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="useFbdWord" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="useHeadings" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="useMileage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="useNewPost" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="useNoCert" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usePostDeliberation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usePostLifecycle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usePostReply" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usePostReport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usePostSecurity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usePostYesNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usePreNext" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="useSourceCopy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="useTag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="useTrackback" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="useWiki" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="viewMlg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="viewRestrictOption" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bbsBoardMaster", propOrder = {
    "accessIds",
    "attachRestrict",
    "attachSize",
    "aviewMlg",
    "boardDescription",
    "boardIds",
    "boardListCount",
    "boardName",
    "boardTitleImage",
    "boardTitleImageBlob",
    "boardTitleType",
    "boardTitleUrl",
    "boardType",
    "boomupRcmdCount",
    "boomupViewCount",
    "categoryId",
    "categoryName",
    "chargerUsers",
    "commentMlg",
    "contentSize",
    "createDate",
    "createMlg",
    "creatorUid",
    "deleteMlg",
    "deliberationUsers",
    "deptBoardUsers",
    "favBoardIds",
    "formId",
    "headerCode",
    "headerCodeName",
    "hitNo",
    "id",
    "isUserSet",
    "menuId",
    "newPostTerm",
    "postLifecycleTerm",
    "ptlCtxName",
    "replyMlg",
    "searchName",
    "searchTerm",
    "searchType",
    "skinId",
    "sortOrder",
    "tableName",
    "uiType",
    "updateDate",
    "updaterName",
    "updaterUid",
    "useAnonymous",
    "useBoomup",
    "useCategory",
    "useCharger",
    "useComment",
    "useDeptBoard",
    "useFbdWord",
    "useHeadings",
    "useMileage",
    "useNewPost",
    "useNoCert",
    "usePostDeliberation",
    "usePostLifecycle",
    "usePostReply",
    "usePostReport",
    "usePostSecurity",
    "usePostYesNo",
    "usePreNext",
    "useSourceCopy",
    "useTag",
    "useTrackback",
    "useWiki",
    "userType",
    "viewMlg",
    "viewRestrictOption"
})
public class BbsBoardMaster
    extends BaseDomain
{

    protected String accessIds;
    protected String attachRestrict;
    protected Long attachSize;
    protected String aviewMlg;
    protected String boardDescription;
    protected String boardIds;
    protected String boardListCount;
    protected String boardName;
    protected String boardTitleImage;
    protected byte[] boardTitleImageBlob;
    protected String boardTitleType;
    protected String boardTitleUrl;
    protected String boardType;
    protected String boomupRcmdCount;
    protected String boomupViewCount;
    protected String categoryId;
    protected String categoryName;
    @XmlElement(nillable = true)
    protected List<String> chargerUsers;
    protected String commentMlg;
    protected Long contentSize;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createDate;
    protected String createMlg;
    protected String creatorUid;
    protected String deleteMlg;
    @XmlElement(nillable = true)
    protected List<String> deliberationUsers;
    @XmlElement(nillable = true)
    protected List<String> deptBoardUsers;
    @XmlElement(nillable = true)
    protected List<String> favBoardIds;
    protected String formId;
    protected String headerCode;
    protected String headerCodeName;
    protected int hitNo;
    protected BbsBoardMasterId id;
    protected String isUserSet;
    protected String menuId;
    protected String newPostTerm;
    protected String postLifecycleTerm;
    protected String ptlCtxName;
    protected String replyMlg;
    protected String searchName;
    protected int searchTerm;
    protected String searchType;
    protected String skinId;
    protected int sortOrder;
    protected String tableName;
    protected String uiType;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateDate;
    protected String updaterName;
    protected String updaterUid;
    protected String useAnonymous;
    protected String useBoomup;
    protected String useCategory;
    protected String useCharger;
    protected String useComment;
    protected String useDeptBoard;
    protected String useFbdWord;
    protected String useHeadings;
    protected String useMileage;
    protected String useNewPost;
    protected String useNoCert;
    protected String usePostDeliberation;
    protected String usePostLifecycle;
    protected String usePostReply;
    protected String usePostReport;
    protected String usePostSecurity;
    protected String usePostYesNo;
    protected String usePreNext;
    protected String useSourceCopy;
    protected String useTag;
    protected String useTrackback;
    protected String useWiki;
    protected String userType;
    protected String viewMlg;
    protected String viewRestrictOption;

    /**
     * Gets the value of the accessIds property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessIds() {
        return accessIds;
    }

    /**
     * Sets the value of the accessIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessIds(String value) {
        this.accessIds = value;
    }

    /**
     * Gets the value of the attachRestrict property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttachRestrict() {
        return attachRestrict;
    }

    /**
     * Sets the value of the attachRestrict property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttachRestrict(String value) {
        this.attachRestrict = value;
    }

    /**
     * Gets the value of the attachSize property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAttachSize() {
        return attachSize;
    }

    /**
     * Sets the value of the attachSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAttachSize(Long value) {
        this.attachSize = value;
    }

    /**
     * Gets the value of the aviewMlg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAviewMlg() {
        return aviewMlg;
    }

    /**
     * Sets the value of the aviewMlg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAviewMlg(String value) {
        this.aviewMlg = value;
    }

    /**
     * Gets the value of the boardDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBoardDescription() {
        return boardDescription;
    }

    /**
     * Sets the value of the boardDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBoardDescription(String value) {
        this.boardDescription = value;
    }

    /**
     * Gets the value of the boardIds property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBoardIds() {
        return boardIds;
    }

    /**
     * Sets the value of the boardIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBoardIds(String value) {
        this.boardIds = value;
    }

    /**
     * Gets the value of the boardListCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBoardListCount() {
        return boardListCount;
    }

    /**
     * Sets the value of the boardListCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBoardListCount(String value) {
        this.boardListCount = value;
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
     * Gets the value of the boardTitleImage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBoardTitleImage() {
        return boardTitleImage;
    }

    /**
     * Sets the value of the boardTitleImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBoardTitleImage(String value) {
        this.boardTitleImage = value;
    }

    /**
     * Gets the value of the boardTitleImageBlob property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getBoardTitleImageBlob() {
        return boardTitleImageBlob;
    }

    /**
     * Sets the value of the boardTitleImageBlob property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setBoardTitleImageBlob(byte[] value) {
        this.boardTitleImageBlob = value;
    }

    /**
     * Gets the value of the boardTitleType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBoardTitleType() {
        return boardTitleType;
    }

    /**
     * Sets the value of the boardTitleType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBoardTitleType(String value) {
        this.boardTitleType = value;
    }

    /**
     * Gets the value of the boardTitleUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBoardTitleUrl() {
        return boardTitleUrl;
    }

    /**
     * Sets the value of the boardTitleUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBoardTitleUrl(String value) {
        this.boardTitleUrl = value;
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
     * Gets the value of the boomupRcmdCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBoomupRcmdCount() {
        return boomupRcmdCount;
    }

    /**
     * Sets the value of the boomupRcmdCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBoomupRcmdCount(String value) {
        this.boomupRcmdCount = value;
    }

    /**
     * Gets the value of the boomupViewCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBoomupViewCount() {
        return boomupViewCount;
    }

    /**
     * Sets the value of the boomupViewCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBoomupViewCount(String value) {
        this.boomupViewCount = value;
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
     * Gets the value of the chargerUsers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the chargerUsers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChargerUsers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getChargerUsers() {
        if (chargerUsers == null) {
            chargerUsers = new ArrayList<String>();
        }
        return this.chargerUsers;
    }

    /**
     * Gets the value of the commentMlg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommentMlg() {
        return commentMlg;
    }

    /**
     * Sets the value of the commentMlg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommentMlg(String value) {
        this.commentMlg = value;
    }

    /**
     * Gets the value of the contentSize property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getContentSize() {
        return contentSize;
    }

    /**
     * Sets the value of the contentSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setContentSize(Long value) {
        this.contentSize = value;
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
     * Gets the value of the createMlg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateMlg() {
        return createMlg;
    }

    /**
     * Sets the value of the createMlg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateMlg(String value) {
        this.createMlg = value;
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
     * Gets the value of the deleteMlg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeleteMlg() {
        return deleteMlg;
    }

    /**
     * Sets the value of the deleteMlg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeleteMlg(String value) {
        this.deleteMlg = value;
    }

    /**
     * Gets the value of the deliberationUsers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deliberationUsers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeliberationUsers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDeliberationUsers() {
        if (deliberationUsers == null) {
            deliberationUsers = new ArrayList<String>();
        }
        return this.deliberationUsers;
    }

    /**
     * Gets the value of the deptBoardUsers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deptBoardUsers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeptBoardUsers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDeptBoardUsers() {
        if (deptBoardUsers == null) {
            deptBoardUsers = new ArrayList<String>();
        }
        return this.deptBoardUsers;
    }

    /**
     * Gets the value of the favBoardIds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the favBoardIds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFavBoardIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFavBoardIds() {
        if (favBoardIds == null) {
            favBoardIds = new ArrayList<String>();
        }
        return this.favBoardIds;
    }

    /**
     * Gets the value of the formId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormId() {
        return formId;
    }

    /**
     * Sets the value of the formId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormId(String value) {
        this.formId = value;
    }

    /**
     * Gets the value of the headerCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHeaderCode() {
        return headerCode;
    }

    /**
     * Sets the value of the headerCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHeaderCode(String value) {
        this.headerCode = value;
    }

    /**
     * Gets the value of the headerCodeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHeaderCodeName() {
        return headerCodeName;
    }

    /**
     * Sets the value of the headerCodeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHeaderCodeName(String value) {
        this.headerCodeName = value;
    }

    /**
     * Gets the value of the hitNo property.
     * 
     */
    public int getHitNo() {
        return hitNo;
    }

    /**
     * Sets the value of the hitNo property.
     * 
     */
    public void setHitNo(int value) {
        this.hitNo = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BbsBoardMasterId }
     *     
     */
    public BbsBoardMasterId getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BbsBoardMasterId }
     *     
     */
    public void setId(BbsBoardMasterId value) {
        this.id = value;
    }

    /**
     * Gets the value of the isUserSet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsUserSet() {
        return isUserSet;
    }

    /**
     * Sets the value of the isUserSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsUserSet(String value) {
        this.isUserSet = value;
    }

    /**
     * Gets the value of the menuId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * Sets the value of the menuId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMenuId(String value) {
        this.menuId = value;
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
     * Gets the value of the postLifecycleTerm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostLifecycleTerm() {
        return postLifecycleTerm;
    }

    /**
     * Sets the value of the postLifecycleTerm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostLifecycleTerm(String value) {
        this.postLifecycleTerm = value;
    }

    /**
     * Gets the value of the ptlCtxName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPtlCtxName() {
        return ptlCtxName;
    }

    /**
     * Sets the value of the ptlCtxName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPtlCtxName(String value) {
        this.ptlCtxName = value;
    }

    /**
     * Gets the value of the replyMlg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReplyMlg() {
        return replyMlg;
    }

    /**
     * Sets the value of the replyMlg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReplyMlg(String value) {
        this.replyMlg = value;
    }

    /**
     * Gets the value of the searchName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchName() {
        return searchName;
    }

    /**
     * Sets the value of the searchName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchName(String value) {
        this.searchName = value;
    }

    /**
     * Gets the value of the searchTerm property.
     * 
     */
    public int getSearchTerm() {
        return searchTerm;
    }

    /**
     * Sets the value of the searchTerm property.
     * 
     */
    public void setSearchTerm(int value) {
        this.searchTerm = value;
    }

    /**
     * Gets the value of the searchType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchType() {
        return searchType;
    }

    /**
     * Sets the value of the searchType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchType(String value) {
        this.searchType = value;
    }

    /**
     * Gets the value of the skinId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSkinId() {
        return skinId;
    }

    /**
     * Sets the value of the skinId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSkinId(String value) {
        this.skinId = value;
    }

    /**
     * Gets the value of the sortOrder property.
     * 
     */
    public int getSortOrder() {
        return sortOrder;
    }

    /**
     * Sets the value of the sortOrder property.
     * 
     */
    public void setSortOrder(int value) {
        this.sortOrder = value;
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
     * Gets the value of the uiType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUiType() {
        return uiType;
    }

    /**
     * Sets the value of the uiType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUiType(String value) {
        this.uiType = value;
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
     * Gets the value of the useBoomup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseBoomup() {
        return useBoomup;
    }

    /**
     * Sets the value of the useBoomup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseBoomup(String value) {
        this.useBoomup = value;
    }

    /**
     * Gets the value of the useCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseCategory() {
        return useCategory;
    }

    /**
     * Sets the value of the useCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseCategory(String value) {
        this.useCategory = value;
    }

    /**
     * Gets the value of the useCharger property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseCharger() {
        return useCharger;
    }

    /**
     * Sets the value of the useCharger property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseCharger(String value) {
        this.useCharger = value;
    }

    /**
     * Gets the value of the useComment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseComment() {
        return useComment;
    }

    /**
     * Sets the value of the useComment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseComment(String value) {
        this.useComment = value;
    }

    /**
     * Gets the value of the useDeptBoard property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseDeptBoard() {
        return useDeptBoard;
    }

    /**
     * Sets the value of the useDeptBoard property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseDeptBoard(String value) {
        this.useDeptBoard = value;
    }

    /**
     * Gets the value of the useFbdWord property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseFbdWord() {
        return useFbdWord;
    }

    /**
     * Sets the value of the useFbdWord property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseFbdWord(String value) {
        this.useFbdWord = value;
    }

    /**
     * Gets the value of the useHeadings property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseHeadings() {
        return useHeadings;
    }

    /**
     * Sets the value of the useHeadings property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseHeadings(String value) {
        this.useHeadings = value;
    }

    /**
     * Gets the value of the useMileage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseMileage() {
        return useMileage;
    }

    /**
     * Sets the value of the useMileage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseMileage(String value) {
        this.useMileage = value;
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
     * Gets the value of the useNoCert property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseNoCert() {
        return useNoCert;
    }

    /**
     * Sets the value of the useNoCert property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseNoCert(String value) {
        this.useNoCert = value;
    }

    /**
     * Gets the value of the usePostDeliberation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsePostDeliberation() {
        return usePostDeliberation;
    }

    /**
     * Sets the value of the usePostDeliberation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsePostDeliberation(String value) {
        this.usePostDeliberation = value;
    }

    /**
     * Gets the value of the usePostLifecycle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsePostLifecycle() {
        return usePostLifecycle;
    }

    /**
     * Sets the value of the usePostLifecycle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsePostLifecycle(String value) {
        this.usePostLifecycle = value;
    }

    /**
     * Gets the value of the usePostReply property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsePostReply() {
        return usePostReply;
    }

    /**
     * Sets the value of the usePostReply property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsePostReply(String value) {
        this.usePostReply = value;
    }

    /**
     * Gets the value of the usePostReport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsePostReport() {
        return usePostReport;
    }

    /**
     * Sets the value of the usePostReport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsePostReport(String value) {
        this.usePostReport = value;
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
     * Gets the value of the usePostYesNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsePostYesNo() {
        return usePostYesNo;
    }

    /**
     * Sets the value of the usePostYesNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsePostYesNo(String value) {
        this.usePostYesNo = value;
    }

    /**
     * Gets the value of the usePreNext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsePreNext() {
        return usePreNext;
    }

    /**
     * Sets the value of the usePreNext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsePreNext(String value) {
        this.usePreNext = value;
    }

    /**
     * Gets the value of the useSourceCopy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseSourceCopy() {
        return useSourceCopy;
    }

    /**
     * Sets the value of the useSourceCopy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseSourceCopy(String value) {
        this.useSourceCopy = value;
    }

    /**
     * Gets the value of the useTag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseTag() {
        return useTag;
    }

    /**
     * Sets the value of the useTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseTag(String value) {
        this.useTag = value;
    }

    /**
     * Gets the value of the useTrackback property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseTrackback() {
        return useTrackback;
    }

    /**
     * Sets the value of the useTrackback property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseTrackback(String value) {
        this.useTrackback = value;
    }

    /**
     * Gets the value of the useWiki property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseWiki() {
        return useWiki;
    }

    /**
     * Sets the value of the useWiki property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseWiki(String value) {
        this.useWiki = value;
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
     * Gets the value of the viewMlg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getViewMlg() {
        return viewMlg;
    }

    /**
     * Sets the value of the viewMlg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setViewMlg(String value) {
        this.viewMlg = value;
    }

    /**
     * Gets the value of the viewRestrictOption property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getViewRestrictOption() {
        return viewRestrictOption;
    }

    /**
     * Sets the value of the viewRestrictOption property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setViewRestrictOption(String value) {
        this.viewRestrictOption = value;
    }

}
