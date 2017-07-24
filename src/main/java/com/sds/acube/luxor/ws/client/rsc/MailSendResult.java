
package com.sds.acube.luxor.ws.client.rsc;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for mailSendResult.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="mailSendResult">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SUCCESS"/>
 *     &lt;enumeration value="WARNING"/>
 *     &lt;enumeration value="ERROR"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "mailSendResult")
@XmlEnum
public enum MailSendResult {

    SUCCESS,
    WARNING,
    ERROR;

    public String value() {
        return name();
    }

    public static MailSendResult fromValue(String v) {
        return valueOf(v);
    }

}
