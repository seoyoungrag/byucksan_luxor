package com.sds.acube.luxor.portal.vo;

import com.sds.acube.luxor.common.vo.MessageVO;

public class PortalEnvironmentVO extends MessageVO {
	private String envId;
	private String envValue;
	private String envDesc;
	private String envValueSet;
	
	public String getEnvValueSet() {
		return envValueSet;
	}
	public void setEnvValueSet(String envValueSet) {
		this.envValueSet = envValueSet;
	}
	public String getEnvDesc() {
    	return envDesc;
    }
	public void setEnvDesc(String envDesc) {
    	this.envDesc = envDesc;
    }
	public String getEnvId() {
    	return envId;
    }
	public void setEnvId(String envId) {
    	this.envId = envId;
    }
	public String getEnvValue() {
    	return envValue;
    }
	public void setEnvValue(String envValue) {
    	this.envValue = envValue;
    }
	
	@Override
    public String toString() {
	    return super.toString() + ", PortalEnvironmentVO [envId=" + envId + ", envValue=" + envValue + ", envDesc=" + envDesc + "]";
    }
	
	
}
