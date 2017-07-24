package com.sds.acube.luxor.common.service;

import java.util.List;
import javax.jws.WebService;
import com.sds.acube.luxor.common.vo.TreeVO;

@WebService
public interface TreeService {
	public List getTreeList(TreeVO treeVO) throws Exception;
	
	public TreeVO[] getTree(TreeVO treeVO) throws Exception;
	
	public TreeVO[] getAclMapTree(TreeVO treeVO) throws Exception;
	
	public TreeVO[] getTreeChild(TreeVO treeVO) throws Exception;
	
	public TreeVO[] getParents(TreeVO treeVO) throws Exception;
	
	public TreeVO getTreeNode(TreeVO treeVO) throws Exception;
	
	public int getMaxSeq(TreeVO treeVO) throws Exception;
		
	public String insertTreeNode(TreeVO treeVO) throws Exception;
	
	public boolean updateTreeNode(TreeVO treeVO) throws Exception;
	
	public boolean moveTreeNode(TreeVO srcTreeVO, TreeVO targetTreeVO, String type) throws Exception;
	
	public boolean updateTreeActiveStatus(TreeVO treeVO) throws Exception;
	
	public void deleteTreeNodeAll(TreeVO treeVO) throws Exception;
	
	public boolean deleteTreeNode(TreeVO treeVO) throws Exception;
}
