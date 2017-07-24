package com.sds.acube.luxor.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import com.sds.acube.luxor.common.service.TreeService;
import com.sds.acube.luxor.common.util.UtilRequest;
import com.sds.acube.luxor.common.vo.TreeVO;
import com.sds.acube.luxor.framework.controller.BaseController;


public class TreeController extends BaseController {
	private TreeService treeService;
	private String treeView;
	private String treeOrgView;
	
	public void setTreeService(TreeService treeService) {
		this.treeService = treeService;
	}
	public void setTreeView(String treeView) {
    	this.treeView = treeView;
    }
	public void setTreeOrgView(String treeOrgView) {
    	this.treeOrgView = treeOrgView;
    }

	public ModelAndView getOrgTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("getTree controller called...");
		
		TreeVO treeVO = new TreeVO();
		bind(request, treeVO);
		
		TreeVO[] tree = treeService.getTree(treeVO);
		
		ModelAndView mav = new ModelAndView(treeOrgView);
		mav.addObject("tree", tree);
		mav.addObject("treeId", treeVO.getTreeId());
		return mav;
	}
	
	public ModelAndView getTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("getTree controller called...");
		
		TreeVO treeVO = new TreeVO();
		bind(request, treeVO);
		
		TreeVO[] tree = treeService.getTree(treeVO);
		
		ModelAndView mav = new ModelAndView(treeView);
		mav.addObject("tree", tree);
		mav.addObject("treeId", treeVO.getTreeId());
		return mav;
	}

	public ModelAndView insertTreeNode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("insertTreeNode controller called...");
		
		TreeVO treeVO = new TreeVO();
		bind(request, treeVO);
		
		String nodeNameId = treeService.insertTreeNode(treeVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", nodeNameId);
		return mav;
	}

	public ModelAndView updateTreeNode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		TreeVO treeVO = new TreeVO();
		bind(request, treeVO);
		
		boolean result = treeService.updateTreeNode(treeVO);

		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult",result);
		return mav;
	}

	public ModelAndView moveTreeNode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		TreeVO srcTreeVO = new TreeVO();
		TreeVO targetTreeVO = new TreeVO();
		
		bind(request, srcTreeVO);
		bind(request, targetTreeVO);
		
		String type = UtilRequest.getString(request, "type");
		String srcNodeId = UtilRequest.getString(request, "srcNodeId");
		String targetNodeId = UtilRequest.getString(request, "targetNodeId");
		
		srcTreeVO.setNodeId(srcNodeId);
		targetTreeVO.setNodeId(targetNodeId);
		
		boolean result = treeService.moveTreeNode(srcTreeVO, targetTreeVO, type);

		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult",result);
		return mav;
	}

	public ModelAndView deleteTreeNodeAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		TreeVO treeVO = new TreeVO();
		bind(request, treeVO);
		
		treeService.deleteTreeNodeAll(treeVO);

		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult","");
		return mav;
	}

	public ModelAndView deleteTreeNode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		TreeVO treeVO = new TreeVO();
		bind(request, treeVO);
		
		boolean result = treeService.deleteTreeNode(treeVO);

		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult",result);
		return mav;
	}

}
