package com.sds.acube.luxor.common.vo;

import java.io.Serializable;

public class SsoToken implements Serializable {

	private static final long serialVersionUID = 6911289166358760812L;
	
	private String SHM; // SSO HMAC KEY
	private String STM; // SSO TIMESTAMP KEY
	
	public void setSHM(String sHM) {
		SHM = sHM;
	}
	public String getSHM() {
		return SHM;
	}
	public void setSTM(String sTM) {
		STM = sTM;
	}
	public String getSTM() {
		return STM;
	}
}
