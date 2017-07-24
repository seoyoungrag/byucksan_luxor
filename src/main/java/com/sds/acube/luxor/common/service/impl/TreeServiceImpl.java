package com.sds.acube.luxor.common.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.sds.acube.luxor.common.dao.TreeDAO;
import com.sds.acube.luxor.common.service.MessageService;
import com.sds.acube.luxor.common.service.TreeService;
import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.util.Tree;
import com.sds.acube.luxor.common.vo.MessageVO;
import com.sds.acube.luxor.common.vo.TreeVO;
import com.sds.acube.luxor.framework.cache.LuxorHashMap;
import com.sds.acube.luxor.framework.cache.MemoryCache;
import com.sds.acube.luxor.framework.cache.MemoryCacheCenter;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.service.BaseService;

public class TreeServiceImpl extends BaseService implements TreeService, MemoryCache {
	private static LuxorHashMap cache = new LuxorHashMap(Integer.parseInt(LuxorConfig.getString("Common", "CACHE.MAXIMUM_SIZE")));
	private MessageService messageService;
	private TreeDAO treeDAO;
	
	public TreeServiceImpl() {
		MemoryCacheCenter.getInstance().register("TREE_SERVICE", this);
	}
	
	public void setTreeDAO(TreeDAO treeDAO) {
    	this.treeDAO = treeDAO;
    }
	
	public void setMessageService(MessageService messageService) {
    	this.messageService = messageService;
    }

	
	public List getTreeList(TreeVO treeVO) throws Exception {
		TreeVO[] trees = getTree(treeVO);
		return new ArrayList(Arrays.asList(trees));
	}
	
	
	public TreeVO[] getTree(TreeVO treeVO) throws Exception {
		String key = "getTree." + treeVO.getTenantId() + "." + treeVO.getPortalId() + "." + treeVO.getTreeId() + "." + treeVO.getLangType();
		
		// Cache
		TreeVO[] resultVO = (TreeVO[])cache.get(key);
		if(resultVO==null) {
			resultVO = treeDAO.getTree(treeVO);
			cache.put(key, resultVO);
		}
		
		Tree tree = new Tree();
		for(int i=0; i < resultVO.length; i++) {
			String folderId = resultVO[i].getNodeId();
			String parentId = resultVO[i].getParentId();
			
			tree.add(folderId, parentId, resultVO[i]);
		}
		
		tree.toArray(resultVO);
        return resultVO;
	}
	
	public TreeVO[] getAclMapTree(TreeVO treeVO) throws Exception {
		String key = "getAclMapTree." + treeVO.getTenantId() + "." + treeVO.getPortalId() + "." + treeVO.getTreeId() + "." + treeVO.getLangType();
		
		// Cache
		TreeVO[] resultVO = (TreeVO[])cache.get(key);
		if(resultVO==null) {
			resultVO = treeDAO.getAclMapTree(treeVO);
			cache.put(key, resultVO);
		}
		
		Tree tree = new Tree();
		for(int i=0; i < resultVO.length; i++) {
			String folderId = resultVO[i].getNodeId();
			String parentId = resultVO[i].getParentId();
			
			tree.add(folderId, parentId, resultVO[i]);
		}
		
		tree.toArray(resultVO);
        return resultVO;
	}
	

	public TreeVO[] getTreeChild(TreeVO treeVO) throws Exception {
		String key = "getTreeChild." + treeVO.getTenantId() + "." + treeVO.getPortalId() + "." + treeVO.getTreeId() + "." + treeVO.getParentId() + "." + treeVO.getLangType();
		
		// Cache
		TreeVO[] resultVO = (TreeVO[])cache.get(key);
		if(resultVO==null) {
			resultVO = treeDAO.getTreeChild(treeVO);
			cache.put(key, resultVO);
		}

		return resultVO;
	}

	
	/**
	 * 현재 노드에서 최상위 노드(ROOT)까지 구함
	 * 
	 * @param treeVO
	 * @return
	 * @throws Exception
	 */
	public TreeVO[] getParents(TreeVO treeVO) throws Exception {
		String key = "getParents." + treeVO.getTenantId() + "." + treeVO.getPortalId() + "." + treeVO.getTreeId() + "." + treeVO.getNodeId() + "." + treeVO.getLangType();
		TreeVO[] resultVO = (TreeVO[])cache.get(key);
		if(resultVO==null) {
			ArrayList parents = new ArrayList();
			parents.add(treeVO);
			
			int count = 0;
			while(true) {
				count++;
				TreeVO parentVO = treeDAO.getParentNode(treeVO);
				
				// 최상위까지 올라가면 끝냄
				if(parentVO==null) {
					break;
				}
	
				parents.add(parentVO);
				treeVO = parentVO;
				
				// 혹시 무한루프 돌아도 빠져나가게
				// 설마 트리 Depth가 100보다 크겠냐
				if(count > 100) {
					break;
				}
			}
			
			resultVO = new TreeVO[parents.size()];
			parents.toArray(resultVO);
			
			cache.put(key, resultVO);
		}

		return resultVO;
	}
	
	
	/**
	 * 트리 노드 정보 구함
	 */
	public TreeVO getTreeNode(TreeVO treeVO) throws Exception {
		String key = "getTreeNode." + treeVO.getTenantId() + "." + treeVO.getPortalId() + "." + treeVO.getNodeId() + "." + treeVO.getLangType();
		TreeVO resultVO = (TreeVO)cache.get(key);
		if(resultVO==null) {
			resultVO = treeDAO.getTreeNode(treeVO);
			cache.put(key, resultVO);
		}
		return resultVO;
	}
	
	
	public int getMaxSeq(TreeVO treeVO) throws Exception {
		return treeDAO.getMaxSeq(treeVO);
	}
	
	
	public String insertTreeNode(TreeVO treeVO) throws Exception {
		String nodeNameId = messageService.insertMessage(treeVO);
		treeVO.setNodeNameId(nodeNameId);
		
		// 상위 노드의 depth 구해옴
		TreeVO parentVO = treeDAO.getParentNode(treeVO);
		int parentDepth = parentVO==null ? 0 : parentVO.getDepth();
		
		// 형재 노드들중 가장 큰 seq 값을 가져옴
		int maxSeq = treeDAO.getMaxSeq(treeVO);
		
		logger.debug("maxSeq : "+maxSeq);
		logger.debug("parentDepth : "+parentDepth);
		
		// depth, seq 1씩증가
		treeVO.setDepth(++parentDepth);
		treeVO.setSeq(++maxSeq);
		
		if(!CommonUtil.nullTrim(treeVO.getIsActive()).equals("N")) {
			treeVO.setIsActive("Y");
		}
		
		boolean result = treeDAO.insertTreeNode(treeVO);

		// 상위 폴더가 ROOT가 아닌 경우 상위 폴더 haschild를 Y로 업데이트
		if(!treeVO.getParentId().equals("ROOT")) {
			TreeVO treeVO2 = new TreeVO();
			treeVO2.setHasChild("Y");
			treeVO2.setTenantId(treeVO.getTenantId());
			treeVO2.setPortalId(treeVO.getPortalId());
			treeVO2.setNodeId(treeVO.getParentId());
			result = treeDAO.updateTreeNode(treeVO2);
		}

		if(result==false) {
			throw new Exception("Fail to insert..");
		}

		MemoryCacheCenter.getInstance().clear("TREE_SERVICE");
		
	    return nodeNameId;
    }


	public boolean updateTreeNode(TreeVO treeVO) throws Exception {
		boolean result = messageService.updateMessage(treeVO) && treeDAO.updateTreeNode(treeVO);
		if(result==false) {
			throw new Exception("Fail to update!!");
		}
		
		MemoryCacheCenter.getInstance().clear("TREE_SERVICE");
		
		return result;
    }
	

	public boolean moveTreeNode(TreeVO srcTreeVO, TreeVO targetTreeVO, String type) throws Exception {
		boolean result = false;
		TreeVO srcNodeVO = treeDAO.getTreeNode(srcTreeVO);
		TreeVO targetNodeVO = treeDAO.getTreeNode(targetTreeVO);
		
		if(CommonUtil.isNullOrEmpty(type)) {
			throw new Exception("Unknown move type");
		}

		// 노드를 다른 노드의 하위로 이동하는 경우
		if(type.equals("inside")) {
			// 이동된 노드의 부모노드, SEQ를 업데이트
			srcNodeVO.setSeq(1);
			srcNodeVO.setParentId(targetNodeVO.getNodeId());
			result = treeDAO.updateTreeNode(srcNodeVO);
			
			// 이동된 노드의 하위 모든 노드들의 depth 업데이트 
			srcNodeVO.setDepth(targetNodeVO.getDepth()+1);
			updateTreeNodeDepthAll(srcNodeVO);
			
			// 이동된 노드의 자식여부를 Y로 업데이트
			targetNodeVO.setHasChild("Y");
			result = treeDAO.updateTreeNode(targetNodeVO);
		} else if(type.equals("after")) {
			// 이동된 노드의 SEQ보다 큰 SEQ를 1씩 증가시킴
			result = treeDAO.updateTreeNodeSeq1(targetNodeVO);

			// 이동된 노드의 부모노드, SEQ, Depth를 업데이트
			srcNodeVO.setSeq(targetNodeVO.getSeq()+1);
			srcNodeVO.setDepth(targetNodeVO.getDepth());
			srcNodeVO.setParentId(targetNodeVO.getParentId());
			
			// 이동된 노드의 하위 모든 노드들의 depth 업데이트 
			updateTreeNodeDepthAll(srcNodeVO);
			
			result = treeDAO.updateTreeNode(srcNodeVO);
		} else if(type.equals("before")) {
			// 이동된 노드의 SEQ보다 큰 SEQ를 1씩 증가시킴
			result = treeDAO.updateTreeNodeSeq2(targetNodeVO);

			// 이동된 노드의 부모노드, SEQ, Depth를 업데이트
			srcNodeVO.setSeq(targetNodeVO.getSeq()-1);
			srcNodeVO.setDepth(targetNodeVO.getDepth());
			srcNodeVO.setParentId(targetNodeVO.getParentId());
			
			// 이동된 노드의 하위 모든 노드들의 depth 업데이트 
			updateTreeNodeDepthAll(srcNodeVO);

			result = treeDAO.updateTreeNode(srcNodeVO);
		}
		
		//하위노드 존재 여부를 다시 세팅함
		TreeVO[] hasChildNodes = treeDAO.getHasChildNodeList(srcNodeVO);
		TreeVO[] Nodes = treeDAO.getTree(srcNodeVO);
		HashMap<Integer,TreeVO> tempMap = new HashMap<Integer,TreeVO>();
		for (int i = 0; i < Nodes.length; i ++) {
			tempMap.put(i,Nodes[i]);
			for (int j = 0; j < hasChildNodes.length; j++) {
				if(hasChildNodes[j].getNodeId().equals(Nodes[i].getNodeId())) {
					tempMap.remove(i);
				}
			}
		}
		
		for (int i = 0; i < tempMap.size(); i ++) {
			if(tempMap.get(i) != null) {
				tempMap.get(i).setHasChild("N");
				result = treeDAO.updateTreeNode(tempMap.get(i));
			}
		}
		
		
		//하위노드가 있는지 여부를 쿼리로 불러와서
		int maxDept = treeDAO.getMaxDepth(srcNodeVO);
		for (int i = 1; i < maxDept + 1; i ++) {
			srcNodeVO.setDepth(i);
			TreeVO[] siblingNods = treeDAO.getSiblingNodeList(srcNodeVO);
			//시퀀스를 다시 배열함
			for (int j = 0; j < siblingNods.length; j++) {
				siblingNods[j].setSeq(j+1);
				result = treeDAO.updateTreeNode(siblingNods[j]);
			}
		}
		
		if(result==false) {
			throw new Exception("Fail to move node!!");
		}

		MemoryCacheCenter.getInstance().clear("TREE_SERVICE");
		MemoryCacheCenter.getInstance().clear("MENU_SERVICE");
		
		return result;
    }

	
	/**
	 * 노드 삭제시 노드 하위의 모든 노드를 재귀적으로 삭제
	 *  
	 * @param treeVO
	 * @throws Exception
	 */
	public void deleteTreeNodeAll(TreeVO treeVO) throws Exception {
		// Get children
		TreeVO paramVO = new TreeVO();
		paramVO.setTenantId(treeVO.getTenantId());
		paramVO.setPortalId(treeVO.getPortalId());
		paramVO.setTreeId(treeVO.getTreeId());
		paramVO.setParentId(treeVO.getNodeId());
		TreeVO[] childVO = treeDAO.getTreeChild(paramVO);

		// Base case
		if(childVO.length==0) {
			deleteTreeNode(treeVO);
			return;
		}
		
		for(TreeVO tempVO : childVO) {
			deleteTreeNode(tempVO); // Delete each node
			deleteTreeNodeAll(tempVO); // Recursive call
		}
		
		deleteTreeNode(treeVO);
	}
	
	
	/**
	 * 선택된 노드만 삭제함 
	 * 
	 * @param treeVO
	 * @throws Exception
	 */
	public boolean deleteTreeNode(TreeVO treeVO) throws Exception {
		boolean result = false;
		
        // Delete Message
        MessageVO messageVO = new MessageVO();
        if(CommonUtil.isNullOrEmpty(treeVO.getNodeNameId())) {
        	messageVO.setMessageId(treeVO.getMessageId());
        } else {
        	messageVO.setMessageId(treeVO.getNodeNameId());
        }
        messageVO.setTenantId(treeVO.getTenantId());
        messageVO.setPortalId(treeVO.getPortalId());
        result = messageService.deleteMessage(messageVO);
        
        result = treeDAO.deleteTreeNode(treeVO);
        
		MemoryCacheCenter.getInstance().clear("TREE_SERVICE");
		MemoryCacheCenter.getInstance().clear("MENU_SERVICE");
		
		return result;
	}

	
	public boolean updateTreeActiveStatus(TreeVO treeVO) throws Exception {
		MemoryCacheCenter.getInstance().clear("TREE_SERVICE");
		return treeDAO.updateTreeActiveStatus(treeVO);
	}
	
	
	/**
	 * 노드의 depth를 업데이트
	 * @param treeVO
	 * @return
	 * @throws Exception
	 */
	public boolean updateTreeNodeDepth(TreeVO treeVO) throws Exception {
		MemoryCacheCenter.getInstance().clear("TREE_SERVICE");
		return treeDAO.updateTreeNodeDepth(treeVO);
	}
	
	
	public void updateTreeNodeDepthAll(TreeVO treeVO) throws Exception {
		// Get children
		TreeVO paramVO = new TreeVO();
		paramVO.setTenantId(treeVO.getTenantId());
		paramVO.setPortalId(treeVO.getPortalId());
		paramVO.setTreeId(treeVO.getTreeId());
		paramVO.setParentId(treeVO.getNodeId());
		TreeVO[] childVO = treeDAO.getTreeChild(paramVO);

		// Base case
		if(childVO.length==0) {
			updateTreeNodeDepth(treeVO);
			return;
		}
		
		for(TreeVO tempVO : childVO) {
			// depth 1 증가
			tempVO.setDepth(treeVO.getDepth()+1);
			
			updateTreeNodeDepth(tempVO); // Delete each node
			updateTreeNodeDepthAll(tempVO); // Recursive call
		}
		
		updateTreeNodeDepth(treeVO);
	}

	
	
	public void clear() {
		cache.clear();
	}
	
	public void remove(Object key) {
		cache.remove(key);
	}
	
	public int size() {
		return cache.size();
    }

}
