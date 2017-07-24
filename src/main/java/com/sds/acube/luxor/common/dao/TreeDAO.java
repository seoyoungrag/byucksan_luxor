package com.sds.acube.luxor.common.dao;

import java.util.ArrayList;

import com.sds.acube.luxor.common.vo.TreeVO;
import com.sds.acube.luxor.framework.dao.BaseDAO;

public class TreeDAO extends BaseDAO {

	public TreeVO[] getTree(TreeVO vo) throws Exception {
		ArrayList resultList = (ArrayList)selectList("getTree", vo);
		TreeVO[] treeVO = new TreeVO[resultList.size()];
		resultList.toArray(treeVO);
		return treeVO;
	}
	
	public TreeVO[] getAclMapTree(TreeVO vo) throws Exception {
		ArrayList resultList = (ArrayList)selectList("getAclMapTree", vo);
		TreeVO[] treeVO = new TreeVO[resultList.size()];
		resultList.toArray(treeVO);
		return treeVO;
	}

	public TreeVO[] getTreeChild(TreeVO vo) throws Exception {
		ArrayList resultList = (ArrayList)selectList("getTreeChild", vo);
		TreeVO[] treeVO = new TreeVO[resultList.size()];
		resultList.toArray(treeVO);
		return treeVO;
	}

	public TreeVO getTreeNode(TreeVO vo) throws Exception {
		return (TreeVO)select("getTreeNode", vo);
	}

	public TreeVO getParentNode(TreeVO vo) throws Exception {
		return (TreeVO)select("getParentNode", vo);
	}

	public int getMaxSeq(TreeVO vo) throws Exception {
		return getCount("getMaxSeq", "maxseq", vo);
	}

	public boolean insertTreeNode(TreeVO vo) throws Exception {
		logger.debug("PortalTreeVO : "+vo.toString());
		return insert("insertTreeNode", vo) > 0;
	}

	public boolean updateTreeNode(TreeVO vo) throws Exception {
		return update("updateTreeNode", vo) > 0;
	}

	public boolean updateTreeNodeSeq1(TreeVO vo) throws Exception {
		return update("updateTreeNodeSeq1", vo) > 0;
	}

	public boolean updateTreeNodeSeq2(TreeVO vo) throws Exception {
		return update("updateTreeNodeSeq2", vo) > 0;
	}

	public boolean updateTreeNodeDepth(TreeVO vo) throws Exception {
		return update("updateTreeNodeDepth", vo) > 0;
	}
	
	public boolean updateTreeActiveStatus(TreeVO vo) throws Exception {
		return update("updateTreeActiveStatus", vo) > 0;
	}

	public boolean deleteTreeNode(TreeVO vo) throws Exception {
		return delete("deleteTreeNode", vo) > 0;
	}

	public int getMaxDepth(TreeVO vo) throws Exception {
		return getCount("getMaxDepth", "maxdepth", vo);
	}
	
	public TreeVO[] getHasChildNodeList(TreeVO vo) throws Exception {
		ArrayList resultList = (ArrayList)selectList("getHasChildNodeList", vo);
		TreeVO[] codes = new TreeVO[resultList.size()];
		resultList.toArray(codes);
		return codes;
	}
	
	public TreeVO[] getSiblingNodeList(TreeVO vo) throws Exception {
		ArrayList resultList = (ArrayList)selectList("getSiblingNodeList", vo);
		TreeVO[] codes = new TreeVO[resultList.size()];
		resultList.toArray(codes);
		return codes;
	}
}
