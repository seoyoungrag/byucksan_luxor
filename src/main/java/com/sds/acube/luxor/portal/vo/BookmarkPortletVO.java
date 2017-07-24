package com.sds.acube.luxor.portal.vo;

import com.sds.acube.luxor.common.vo.CommonVO;


public class BookmarkPortletVO extends CommonVO {
	private String userId;
	private String parentId;
	private String bookmarkId;
	private String bookmarkName;
	private String bookmarkUrl;
	private String description;
	private String bookmarkOption;


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getParentId() {
		return parentId;
	}


	public void setParentId(String parentId) {
		this.parentId = parentId;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getBookmarkId() {
		return bookmarkId;
	}


	public void setBookmarkId(String bookmarkId) {
		this.bookmarkId = bookmarkId;
	}


	public String getBookmarkName() {
		return bookmarkName;
	}


	public void setBookmarkName(String bookmarkName) {
		this.bookmarkName = bookmarkName;
	}


	public String getBookmarkUrl() {
		return bookmarkUrl;
	}


	public void setBookmarkUrl(String bookmarkUrl) {
		this.bookmarkUrl = bookmarkUrl;
	}


	public String getBookmarkOption() {
		return bookmarkOption;
	}


	public void setBookmarkOption(String bookmarkOption) {
		this.bookmarkOption = bookmarkOption;
	}


	@Override
	public String toString() {
		return "BookmarkPortletVO [bookmarkId=" + bookmarkId + ", bookmarkName=" + bookmarkName + ", bookmarkUrl=" + bookmarkUrl
		    + ", description=" + description + ", bookmarkOption=" + bookmarkOption + ", parentId=" + parentId + ", userId=" + userId + "]";
	}
}
