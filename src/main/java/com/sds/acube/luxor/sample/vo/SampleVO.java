package com.sds.acube.luxor.sample.vo;

import com.sds.acube.luxor.common.vo.MessageVO;

public class SampleVO extends MessageVO {
	private String sampleId;
	private String name;
	private String nameId;
	private String content;
	private byte[] contentLob;
	
	public String getSampleId() {
    	return sampleId;
    }
	public void setSampleId(String sampleId) {
    	this.sampleId = sampleId;
    }
	public String getName() {
    	return name;
    }
	public void setName(String name) {
    	this.name = name;
    }
	public String getContent() {
    	return content;
    }
	public void setContent(String content) {
    	this.content = content;
    }
	public String getNameId() {
    	return nameId;
    }
	public void setNameId(String nameId) {
    	this.nameId = nameId;
    }
	public byte[] getContentLob() {
    	return contentLob;
    }
	public void setContentLob(byte[] contentLob) {
    	this.contentLob = contentLob;
    }
	
	@Override
    public String toString() {
	    return super.toString() + ", SampleVO [content=" + content + ", name=" + name + ", sampleId=" + sampleId + "]";
    }
	
	
}
