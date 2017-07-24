package com.sds.acube.luxor.common.vo;


public class MemberRequestKeyVO extends CommonVO {
	private String requestKey;
	private String[] requestKeys;
	private String compId;
	private String deptId;
	private String gradeId;
	private String compName;
	private String deptName;
	private String gradeName;
	private int useCount;
	private int countLimit;
	public String getRequestKey() {
		return requestKey;
	}
	public void setRequestKey(String requestKey) {
		this.requestKey = requestKey;
	}
	public String[] getRequestKeys() {
		return requestKeys;
	}
	public void setRequestKeys(String[] requestKeys) {
		this.requestKeys = requestKeys;
	}
	public String getCompId() {
		return compId;
	}
	public void setCompId(String compId) {
		this.compId = compId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public int getUseCount() {
		return useCount;
	}
	public void setUseCount(int useCount) {
		this.useCount = useCount;
	}
	public int getCountLimit() {
		return countLimit;
	}
	public void setCountLimit(int countLimit) {
		this.countLimit = countLimit;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
	
}
