package com.sds.acube.luxor.common.vo;

import java.sql.Timestamp;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.util.TimestampAdapter;
import com.sds.acube.luxor.common.vo.MessageVO;
import com.sds.acube.luxor.framework.config.LuxorConfig;

public class ContentsStatisticsVO extends MessageVO {

	private String statisticsId = "";
	private String contentsId = "";
	private String contentsNameId = "";
	private Timestamp accessTime;
	private String accessUserId = "";
	private String fromDate = "";
	private String toDate = "";
	private String searchTxt = "";
	
	public String getStatisticsId() {
		return statisticsId;
	}
	public void setStatisticsId(String statisticsId) {
		this.statisticsId = statisticsId;
	}
	public String getContentsId() {
		return contentsId;
	}
	public void setContentsId(String contentsId) {
		this.contentsId = contentsId;
	}
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getAccessTime() {
		return accessTime;
	}
    public String getAccessTime2String() {
		if(this.accessTime==null) {
			return "";
		}
		String datePattern = LuxorConfig.getEnvString(tenantId, portalId, "DATE_PATTERN");;
    	return CommonUtil.formatDate(accessTime, datePattern);
    }
	public void setAccessTime(Timestamp accessTime) {
		this.accessTime = accessTime;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getContentsNameId() {
		return contentsNameId;
	}
	public void setContentsNameId(String contentsNameId) {
		this.contentsNameId = contentsNameId;
	}
	public String getSearchTxt() {
		return searchTxt;
	}
	public void setSearchTxt(String searchTxt) {
		this.searchTxt = searchTxt;
	}
	public String getAccessUserId() {
		return accessUserId;
	}
	public void setAccessUserId(String accessUserId) {
		this.accessUserId = accessUserId;
	}

	@Override
	public String toString() {
		return super.toString() + ", ContentsStatisticsVO [accessTime=" + accessTime
				+ ", accessUserId=" + accessUserId + ", contentsId="
				+ contentsId + ", contentsNameId=" + contentsNameId
				+ ", fromDate=" + fromDate + ", searchTxt=" + searchTxt
				+ ", statisticsId=" + statisticsId + ", toDate=" + toDate + "]";
	}
	
}
