/*
 * @(#) AttachmentVOs.java 2010. 8. 9.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.common.vo;



/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.1.6.1 $ $Date: 2011/02/10 06:05:46 $
 */
public class AttachmentVOs extends MessageVO {

	private String[] localFileName;
	private String[] serverFileName;
	private String[] serverPath;
	private long[] fileSize;
	private int[] seq;
	private String attachmentId;
	private String moduleDiv;
	
	private String[] deleted;
	private boolean[] isNew;
	
	private boolean isSuccess;
	
	
	
	private String[] storeFileId;
	


	
	/**
	 * @return Returns the storeFileId.
	 */
	public String[] getStoreFileId() {
		return storeFileId;
	}


	
	/**
	 * @param storeFileId The storeFileId to set.
	 */
	public void setStoreFileId(String[] storeFileId) {
		this.storeFileId = storeFileId;
	}



	/**
	 * @return Returns the isNew.
	 */
	public boolean[] getIsNew() {
		return isNew;
	}





	
	/**
	 * @param isNew The isNew to set.
	 */
	public void setIsNew(boolean[] isNew) {
		this.isNew = isNew;
	}





	/**
	 * @return Returns the serverPath.
	 */
	public String[] getServerPath() {
		return serverPath;
	}




	
	/**
	 * @param serverPath The serverPath to set.
	 */
	public void setServerPath(String[] serverPath) {
		this.serverPath = serverPath;
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
	 * @return Returns the isSuccess.
	 */
	public boolean isSuccess() {
		return isSuccess;
	}


	
	/**
	 * @param isSuccess The isSuccess to set.
	 */
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}


	/**
	 * @return Returns the localFileName.
	 */
	public String[] getLocalFileName() {
		return localFileName;
	}

	
	/**
	 * @param localFileName The localFileName to set.
	 */
	public void setLocalFileName(String[] localFileName) {
		this.localFileName = localFileName;
	}

	
	/**
	 * @return Returns the serverFileName.
	 */
	public String[] getServerFileName() {
		return serverFileName;
	}

	
	/**
	 * @param serverFileName The serverFileName to set.
	 */
	public void setServerFileName(String[] serverFileName) {
		this.serverFileName = serverFileName;
	}

	
	/**
	 * @return Returns the fileSize.
	 */
	public long[] getFileSize() {
		return fileSize;
	}

	
	/**
	 * @param fileSize The fileSize to set.
	 */
	public void setFileSize(long[] fileSize) {
		this.fileSize = fileSize;
	}

	
	/**
	 * @return Returns the seq.
	 */
	public int[] getSeq() {
		return seq;
	}

	
	/**
	 * @param seq The seq to set.
	 */
	public void setSeq(int[] seq) {
		this.seq = seq;
	}

	
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
	 * @return Returns the deleted.
	 */
	public String[] getDeleted() {
		return deleted;
	}



	
	/**
	 * @param deleted The deleted to set.
	 */
	public void setDeleted(String[] deleted) {
		this.deleted = deleted;
	}

	
	
}
