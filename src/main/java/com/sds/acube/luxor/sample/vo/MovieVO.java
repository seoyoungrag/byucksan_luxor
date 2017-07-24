package com.sds.acube.luxor.sample.vo;

import java.sql.Timestamp;
import com.sds.acube.luxor.common.vo.CommonVO;

public class MovieVO extends CommonVO {
	private String code = "";
	private String title = "";
	private String content = "";
	private String attachmentId = "";
	private Timestamp releaseDate;
	
	
	public String getCode() {
    	return code;
    }
	public void setCode(String code) {
    	this.code = code;
    }
	public String getTitle() {
    	return title;
    }
	public void setTitle(String title) {
    	this.title = title;
    }
	public String getContent() {
    	return content;
    }
	public void setContent(String content) {
    	this.content = content;
    }
	public Timestamp getReleaseDate() {
    	return releaseDate;
    }
	public void setReleaseDate(Timestamp releaseDate) {
    	this.releaseDate = releaseDate;
    }
	public String getAttachmentId() {
    	return attachmentId;
    }
	public void setAttachmentId(String attachmentId) {
    	this.attachmentId = attachmentId;
    }
	
	
	@Override
    public String toString() {
	    return "MovieVO [code=" + code + ", title=" + title + ", content=" + content + ", attachmentId=" + attachmentId
	        + ", releaseDate=" + releaseDate + "]";
    }
	
}
