package com.sds.acube.luxor.common.vo;


public class TreeVO extends MessageVO {
	private String treeId;
	private String nodeId;
	private String nodeName;
	private String nodeNameId;
	private String nodeType;
	private String parentId;
	private String hasChild;
	private String isActive;
	private String usePersonal;
	private int depth;
	private int seq;

	
	public String getUsePersonal() {
    	return usePersonal;
    }
	public void setUsePersonal(String usePersonal) {
    	this.usePersonal = usePersonal;
    }
	public String getIsActive() {
    	return isActive;
    }
	public void setIsActive(String isActive) {
    	this.isActive = isActive;
    }
	public String getNodeName() {
    	return nodeName;
    }
	public void setNodeName(String nodeName) {
    	this.nodeName = nodeName;
    }
	public String getHasChild() {
    	return hasChild;
    }
	public void setHasChild(String hasChild) {
    	this.hasChild = hasChild;
    }
	public String getTreeId() {
    	return treeId;
    }
	public void setTreeId(String treeId) {
    	this.treeId = treeId;
    }
	public String getNodeId() {
    	return nodeId;
    }
	public void setNodeId(String nodeId) {
    	this.nodeId = nodeId;
    }
	public String getNodeNameId() {
    	return nodeNameId;
    }
	public void setNodeNameId(String nodeNameId) {
    	this.nodeNameId = nodeNameId;
    }
	public String getParentId() {
    	return parentId;
    }
	public void setParentId(String parentId) {
    	this.parentId = parentId;
    }
	public int getDepth() {
    	return depth;
    }
	public void setDepth(int depth) {
    	this.depth = depth;
    }
	public int getSeq() {
    	return seq;
    }
	public void setSeq(int seq) {
    	this.seq = seq;
    }
	public String getNodeType() {
    	return nodeType;
    }
	public void setNodeType(String nodeType) {
    	this.nodeType = nodeType;
    }
	
	@Override
    public String toString() {
	    return /*super.toString() +*/ ", TreeVO [depth=" + depth + ", hasChild=" + hasChild + ", nodeId=" + nodeId + ", nodeName=" + nodeName + ", nodeNameId="
	        + nodeNameId + ", nodeType=" + nodeType + ", parentId=" + parentId + ", seq=" + seq + ", treeId=" + treeId + "]";
    }
	
}
