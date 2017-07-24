package com.sds.acube.luxor.common.vo;

import java.sql.Timestamp;
import java.util.List;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.util.TimestampAdapter;
import com.sds.acube.luxor.framework.config.LuxorConfig;

public class CommonVO implements Cloneable {
	protected String tenantId = "";
	protected String portalId = "";
	private String compId = "";
	private List accessIdList;
	
	private Timestamp regDate;
	private Timestamp modDate;
	
	private String refer = "";
	private String regUserId = "";
	private String modUserId = "";
	private String searchKey = "";
	private String searchWay = "";
    private int totalCnt = 0;
    private int cPage = 1;
    private int pageSize = LuxorConfig.getEnvInt(tenantId, portalId, "PAGE_LIST_COUNT");
    private Object request;
    
    public String getRefer() {
    	return refer;
    }
	public void setRefer(String refer) {
    	this.refer = refer;
    }
	public String getSearchKey() {
    	return searchKey;
    }
	public void setSearchKey(String searchKey) {
    	this.searchKey = searchKey;
    }
	public String getSearchWay() {
    	return searchWay;
    }
	public void setSearchWay(String searchWay) {
    	this.searchWay = searchWay;
    }
	public String getCompId() {
    	return compId;
    }
	public void setCompId(String compId) {
    	this.compId = compId;
    }
	public List getAccessIdList() {
    	return accessIdList;
    }
	public void setAccessIdList(List accessIdList) {
    	this.accessIdList = accessIdList;
    }
	public Object getRequest() {
    	return request;
    }
	public void setRequest(Object request) {
    	this.request = request;
    }
	
	@XmlJavaTypeAdapter(TimestampAdapter.class)
    public Timestamp getRegDate() {
    	return regDate;
    }
    public String getRegDate2String() {
		if(this.regDate==null) {
			return "";
		}
		String datePattern = LuxorConfig.getEnvString(tenantId, portalId, "DATE_PATTERN");
    	return CommonUtil.formatDate(regDate, datePattern);
    }
    
	public void setRegDate(Timestamp regDate) {
    	this.regDate = regDate;
    }
    
    @XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getModDate() {
		return this.modDate;
	}
	public String getModDate2String() {
		if(this.modDate==null) {
			return "";
		}
		String datePattern = LuxorConfig.getEnvString(tenantId, portalId, "DATE_PATTERN");
    	return CommonUtil.formatDate(this.modDate, datePattern);
    }
	
	public void setModDate(Timestamp modDate) {
    	this.modDate = modDate;
    }
	public String getTenantId() {
    	return tenantId;
    }
	public void setTenantId(String tenantId) {
    	this.tenantId = tenantId;
    }
	public String getPortalId() {
    	return portalId;
    }
	public void setPortalId(String portalId) {
    	this.portalId = portalId;
    }
	public int getTotalCnt() {
    	return totalCnt;
    }
	public void setTotalCnt(int totalCnt) {
    	this.totalCnt = totalCnt;
    }
	public int getcPage() {
    	return cPage;
    }
	public void setcPage(int cPage) {
    	this.cPage = cPage;
    }
	public int getPageSize() {
    	return pageSize;
    }
	public void setPageSize(int pageSize) {
    	this.pageSize = pageSize;
    }
	public String getRegUserId() {
    	return regUserId;
    }
	public void setRegUserId(String regUserId) {
    	this.regUserId = regUserId;
    }
	public String getModUserId() {
    	return modUserId;
    }
	public void setModUserId(String modUserId) {
    	this.modUserId = modUserId;
    }
	
	@Override
    public String toString() {
	    return "CommonVO [accessIdList=" + accessIdList + ", cPage=" + cPage + ", compId=" + compId + ", modDate=" + modDate
	        + ", modUserId=" + modUserId + ", pageSize=" + pageSize + ", portalId=" + portalId + ", regDate=" + regDate + ", regUserId="
	        + regUserId + ", request=" + request + ", searchKey=" + searchKey + ", searchWay=" + searchWay + ", tenantId=" + tenantId
	        + ", totalCnt=" + totalCnt + "]";
    }
	
	public Object clone() throws CloneNotSupportedException {
		CommonVO a = (CommonVO)super.clone();
		return a;
	}
	
}


