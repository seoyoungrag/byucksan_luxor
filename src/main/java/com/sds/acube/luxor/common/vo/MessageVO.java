package com.sds.acube.luxor.common.vo;

import com.sds.acube.luxor.common.util.CommonUtil;

public class MessageVO extends CommonVO {
	private String messageId = "";
	private String messageName = "";
	private String messageNameAll = "";
	private String messageType = "";
	private String langType = "";
	
	public String getMessageId() {
    	return messageId;
    }
	public void setMessageId(String messageId) {
    	this.messageId = messageId;
    }
	public String getMessageName() {
    	return CommonUtil.escapeSpecialChar(messageName);
    }
	public void setMessageName(String messageName) {
    	this.messageName = messageName;
    }
	public String getMessageType() {
    	return messageType;
    }
	public void setMessageType(String messageType) {
    	this.messageType = messageType;
    }
	public String getLangType() {
    	return langType;
    }
	public void setLangType(String langType) {
    	this.langType = langType;
    }
	public String getMessageNameAll() {
    	return CommonUtil.escapeSpecialChar(messageNameAll);
    }
	public void setMessageNameAll(String messageNameAll) {
    	this.messageNameAll = messageNameAll;
    }
	
	@Override
    public String toString() {
	    return super.toString() + ", MessageVO [langType=" + langType + ", messageId=" + messageId + ", messageName=" + messageName + ", messageNameAll="
	        + messageNameAll + ", messageType=" + messageType + "]";
    }
	
}
