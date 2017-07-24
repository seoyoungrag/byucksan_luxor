
package com.sds.acube.luxor.ws.client.aclservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>groupMemberInfo complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="groupMemberInfo">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.org.cn.acube.sds.com/}baseDMO">
 *       &lt;sequence>
 *         &lt;element name="additionalInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="memberId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="memberName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="memberType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="userInfoBasic" type="{http://service.org.cn.acube.sds.com/}userInfoBasic" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "groupMemberInfo", propOrder = {
    "additionalInfo",
    "groupId",
    "memberId",
    "memberName",
    "memberType",
    "userInfoBasic"
})
public class GroupMemberInfo
    extends BaseDMO
{

    protected String additionalInfo;
    protected String groupId;
    protected String memberId;
    protected String memberName;
    protected Integer memberType;
    protected UserInfoBasic userInfoBasic;

    /**
     * additionalInfo 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    /**
     * additionalInfo 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdditionalInfo(String value) {
        this.additionalInfo = value;
    }

    /**
     * groupId 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * groupId 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupId(String value) {
        this.groupId = value;
    }

    /**
     * memberId 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * memberId 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemberId(String value) {
        this.memberId = value;
    }

    /**
     * memberName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * memberName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemberName(String value) {
        this.memberName = value;
    }

    /**
     * memberType 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMemberType() {
        return memberType;
    }

    /**
     * memberType 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMemberType(Integer value) {
        this.memberType = value;
    }

    /**
     * userInfoBasic 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link UserInfoBasic }
     *     
     */
    public UserInfoBasic getUserInfoBasic() {
        return userInfoBasic;
    }

    /**
     * userInfoBasic 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link UserInfoBasic }
     *     
     */
    public void setUserInfoBasic(UserInfoBasic value) {
        this.userInfoBasic = value;
    }

}
