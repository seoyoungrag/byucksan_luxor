/*
 * @(#) AttachmentVO.java 2010. 8. 9.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.common.vo;

import java.util.Date;


/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.1.6.1 $ $Date: 2011/02/10 06:05:46 $
 */
public class AttachmentVO extends MessageVO {
	private String attachmentId;
	private int seq;
	private String moduleDiv;
	private String clientFilename;
	private String serverFilename;
	private long fileSize;
	private Date lastUpdateDate;
	private String storeFileId;
	
	/**
	 * @return Returns the attachmentId.
	 */
	public String getAttachmentId() {
		return attachmentId;
	}
	
	/**
	 * @param attachmentId The attachmentId to set.
	 */
	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}
	
	/**
	 * @return Returns the seq.
	 */
	public int getSeq() {
		return seq;
	}
	
	/**
	 * @param seq The seq to set.
	 */
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	/**
	 * @return Returns the moduleDiv.
	 */
	public String getModuleDiv() {
		return moduleDiv;
	}
	
	/**
	 * @param moduleDiv The moduleDiv to set.
	 */
	public void setModuleDiv(String moduleDiv) {
		this.moduleDiv = moduleDiv;
	}
	
	/**
	 * @return Returns the clientFilename.
	 */
	public String getClientFilename() {
		return clientFilename;
	}
	
	/**
	 * @param clientFilename The clientFilename to set.
	 */
	public void setClientFilename(String clientFilename) {
		this.clientFilename = clientFilename;
	}
	
	/**
	 * @return Returns the serverFilename.
	 */
	public String getServerFilename() {
		return serverFilename;
	}
	
	/**
	 * @param serverFilename The serverFilename to set.
	 */
	public void setServerFilename(String serverFilename) {
		this.serverFilename = serverFilename;
	}
	
	/**
	 * @return Returns the fileSize.
	 */
	public long getFileSize() {
		return fileSize;
	}
	
	/**
	 * @param fileSize The fileSize to set.
	 */
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	
	/**
	 * @return Returns the lastUpdateDate.
	 */
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	
	/**
	 * @param lastUpdateDate The lastUpdateDate to set.
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
	/**
	 * @return Returns the storeFileId.
	 */
	public String getStoreFileId() {
		return storeFileId;
	}
	
	/**
	 * @param storeFileId The storeFileId to set.
	 */
	public void setStoreFileId(String storeFileId) {
		this.storeFileId = storeFileId;
	}
	
	
}
