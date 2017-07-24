package com.sds.acube.luxor.portal.vo;

import com.sds.acube.luxor.common.vo.CommonVO;

public class MemoPortletVO extends CommonVO {
	private String memoId;
	private String memoSkin;
	private String memoValue;
	private String userId;
	
	public String getMemoId() {
    	return memoId;
    }
	public void setMemoId(String memoId) {
    	this.memoId = memoId;
    }
	public String getMemoSkin() {
    	return memoSkin;
    }
	public void setMemoSkin(String memoSkin) {
    	this.memoSkin = memoSkin;
    }
	public String getMemoValue() {
    	return memoValue;
    }
	public void setMemoValue(String memoValue) {
    	this.memoValue = memoValue;
    }
	public String getUserId() {
    	return userId;
    }
	public void setUserId(String userId) {
    	this.userId = userId;
    }
	
	@Override
    public String toString() {
	    return "MemoPortletVO [memoId=" + memoId + ", memoSkin=" + memoSkin + ", memoValue=" + memoValue + ", userId=" + userId + "]";
    }
	
	
}
