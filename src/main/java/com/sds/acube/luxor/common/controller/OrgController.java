package com.sds.acube.luxor.common.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.sds.acube.luxor.common.service.OrgService;
import com.sds.acube.luxor.common.util.UtilRequest;
import com.sds.acube.luxor.common.vo.TreeVO;
import com.sds.acube.luxor.framework.controller.BaseController;
import com.sds.acube.luxor.ws.client.orgservice.Department;
import com.sds.acube.luxor.ws.client.orgservice.Departments;
import com.sds.acube.luxor.ws.client.orgservice.Duties;
import com.sds.acube.luxor.ws.client.orgservice.Duty;
import com.sds.acube.luxor.ws.client.orgservice.Grade;
import com.sds.acube.luxor.ws.client.orgservice.Grades;
import com.sds.acube.luxor.ws.client.orgservice.IUser;
import com.sds.acube.luxor.ws.client.orgservice.Position;
import com.sds.acube.luxor.ws.client.orgservice.Positions;
import com.sds.acube.luxor.ws.client.orgservice.Title;
import com.sds.acube.luxor.ws.client.orgservice.Titles;


public class OrgController extends BaseController {
	private OrgService orgService;
	private String gradeList;
	private String positionList;
	private String titleList;
	private String dutyList;
	private String gradeTree;
	private String titleTree;
	private String positionTree;
	private String dutyTree;
	private String orgTree;
	
	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}

	public void setGradeList(String gradeList) {
		this.gradeList = gradeList;
	}
	
	public void setPositionList(String positionList) {
		this.positionList = positionList;
	}

	public void setTitleList(String titleList) {
		this.titleList = titleList;
	}

	public void setDutyList(String dutyList) {
		this.dutyList = dutyList;
	}

	public void setGradeTree(String gradeTree) {
		this.gradeTree = gradeTree;
	}

	public void setOrgTree(String orgTree) {
		this.orgTree = orgTree;
	}

	public void setTitleTree(String titleTree) {
		this.titleTree = titleTree;
	}

	public void setPositionTree(String positionTree) {
		this.positionTree = positionTree;
	}

	public void setDutyTree(String dutyTree) {
		this.dutyTree = dutyTree;
	}

	// 트리 자식노드 오픈 시 AJAX로 로딩
	public ModelAndView getOrgSubTreeAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Department paramVO = new Department();
		bind(request, paramVO);

		int treeType = 0;
		if(UtilRequest.getString(request,"treeType")!=null && !"".equals(UtilRequest.getString(request,"treeType"))){
			treeType = Integer.parseInt(UtilRequest.getString(request,"treeType"));
		}
		
		Departments dept = orgService.getOrgSubTree(paramVO.getOrgID(), treeType);

		ArrayList list = new ArrayList();
		for(int i=0; i<dept.getDepartmentList().size();i++){
			TreeVO tree = new TreeVO();
			tree.setNodeId(dept.getDepartmentList().get(i).getOrgID());
			tree.setParentId(dept.getDepartmentList().get(i).getOrgParentID());
			tree.setNodeName(dept.getDepartmentList().get(i).getOrgName());
			tree.setDepth(dept.getDepartmentList().get(i).getDepth());		//tree.js에서 drawSubTree할때 depth를 depth attribute 아닌 hasChild attribute에다가 넘기게해놓음. depth가 int라서 형차이 문제
			tree.setNodeType(Integer.toString(dept.getDepartmentList().get(i).getOrgType()));
			list.add(tree);
		}
		
		TreeVO[] trees = new TreeVO[list.size()];
		list.toArray(trees);
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", trees);
		return mav;
	}

	public ModelAndView getGradeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Grade paramVO = new Grade();
		bind(request, paramVO);

		paramVO.setGradeID("ROOT");
		Grades resultVO = orgService.getSubGrades(paramVO);
		
		
		ModelAndView mav = new ModelAndView(gradeList);
		mav.addObject("gradeList", resultVO);
		return mav;
	}

	public ModelAndView getPositionList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Position paramVO = new Position();
		bind(request, paramVO);

		paramVO.setPositionID("ROOT");
		Positions resultVO = orgService.getSubPositions(paramVO);
		
		ModelAndView mav = new ModelAndView(positionList);
		mav.addObject("positionList", resultVO);
		return mav;
	}

	public ModelAndView getTitleList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Title paramVO = new Title();
		bind(request, paramVO);

		paramVO.setTitleID("ROOT");
		Titles resultVO = orgService.getSubTitles(paramVO);
		
		ModelAndView mav = new ModelAndView(titleList);
		mav.addObject("titleList", resultVO);
		return mav;
	}

	public ModelAndView getDutyList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Duty paramVO = new Duty();
		bind(request, paramVO);

		paramVO.setDutyID("ROOT");
		Duties resultVO = orgService.getSubDuties(paramVO);
		
		ModelAndView mav = new ModelAndView(dutyList);
		mav.addObject("dutyList", resultVO);
		return mav;
	}

	// 트리 자식노드 오픈 시 AJAX로 로딩
	public ModelAndView getGradeAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Grade paramVO = new Grade();
		bind(request, paramVO);

		int depth = UtilRequest.getInt(request, "depth");
		depth = depth+1;
		String strDepth = Integer.toString(depth);
		
		Grades resultVO = orgService.getSubGrades(paramVO);

		if(resultVO==null) {
			throw new Exception("내용 조회 실패");
		}
		
		ArrayList list = new ArrayList();
		for(int i=0; i<resultVO.getGradeList().size();i++){
			TreeVO tree = new TreeVO();
			tree.setNodeId(resultVO.getGradeList().get(i).getGradeID());
			tree.setParentId(resultVO.getGradeList().get(i).getGradeParentID());
			tree.setNodeName(resultVO.getGradeList().get(i).getGradeName());
			tree.setHasChild(strDepth);		//tree.setDepth에 넘기면 안넘어감;
			list.add(tree);
		}
		
		TreeVO[] trees = new TreeVO[list.size()];
		list.toArray(trees);

		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", trees);
		return mav;
	}

	// 트리 자식노드 오픈 시 AJAX로 로딩
	public ModelAndView getPositionAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Position paramVO = new Position();
		bind(request, paramVO);

		int depth = UtilRequest.getInt(request, "depth");
		
		Positions resultVO = orgService.getSubPositions(paramVO);
		
		if(resultVO==null) {
			throw new Exception("내용 조회 실패");
		}
		
		ArrayList list = new ArrayList();
		for(int i=0; i<resultVO.getPositionList().size();i++){
			TreeVO tree = new TreeVO();
			tree.setNodeId(resultVO.getPositionList().get(i).getPositionID());
			tree.setParentId(resultVO.getPositionList().get(i).getPositionParentID());
			tree.setNodeName(resultVO.getPositionList().get(i).getPositionName());
			tree.setDepth(depth+1);
			list.add(tree);
		}
		
		TreeVO[] trees = new TreeVO[list.size()];
		list.toArray(trees);
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", trees);
		return mav;
	}

	// 트리 자식노드 오픈 시 AJAX로 로딩
	public ModelAndView getDutyAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Duty paramVO = new Duty();
		bind(request, paramVO);

		int depth = UtilRequest.getInt(request, "depth");
		
		Duties resultVO = orgService.getSubDuties(paramVO);
		
		if(resultVO==null) {
			throw new Exception("내용 조회 실패");
		}
		
		ArrayList list = new ArrayList();
		for(int i=0; i<resultVO.getDutyList().size();i++){
			TreeVO tree = new TreeVO();
			tree.setNodeId(resultVO.getDutyList().get(i).getDutyID());
			tree.setParentId(resultVO.getDutyList().get(i).getDutyParentID());
			tree.setNodeName(resultVO.getDutyList().get(i).getDutyName());
			tree.setDepth(depth+1);
			list.add(tree);
		}
		
		TreeVO[] trees = new TreeVO[list.size()];
		list.toArray(trees);
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", trees);
		return mav;
	}

	// 트리 자식노드 오픈 시 AJAX로 로딩
	public ModelAndView getTitleAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Title paramVO = new Title();
		bind(request, paramVO);

		int depth = UtilRequest.getInt(request, "depth");
		
		Titles resultVO = orgService.getSubTitles(paramVO);
		
		if(resultVO==null) {
			throw new Exception("내용 조회 실패");
		}
		
		ArrayList list = new ArrayList();
		for(int i=0; i<resultVO.getTitleList().size();i++){
			TreeVO tree = new TreeVO();
			tree.setNodeId(resultVO.getTitleList().get(i).getTitleID());
			tree.setParentId(resultVO.getTitleList().get(i).getTitleParentID());
			tree.setNodeName(resultVO.getTitleList().get(i).getTitleName());
			tree.setDepth(depth+1);
			list.add(tree);
		}
		
		TreeVO[] trees = new TreeVO[list.size()];
		list.toArray(trees);
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", trees);
		return mav;
	}

	// 권한설정 조직트리 화면
	public ModelAndView getOrgTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		IUser paramVO = new IUser();
		bind(request, paramVO);
		
		int treeType = 0;
		if(UtilRequest.getString(request,"treeType")!=null && !"".equals(UtilRequest.getString(request,"treeType"))){
			treeType = Integer.parseInt(UtilRequest.getString(request,"treeType"));
		}
		String userUid = "";
		userUid = UtilRequest.getString(request,"userUid");
		paramVO.setUserUID(userUid);
		Departments resultVO = orgService.getOrgTree(userUid, treeType);
		
		ModelAndView mav = new ModelAndView(orgTree);
		mav.addObject("orgTree", resultVO);
		
		mav.addObject("returnMethod", UtilRequest.getString(request, "returnMethod",""));

		return mav;
	}

	// 권한설정 직급트리 화면
	public ModelAndView getGradeTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Grade paramVO = new Grade();
		bind(request, paramVO);
		
		Grades resultVO = orgService.getSubGrades(paramVO);
		if(resultVO.getGradeList().size() == 0 || resultVO == null) {
			paramVO.setCompID("DEFAULT");
			resultVO = orgService.getSubGrades(paramVO);
		}
		
		ModelAndView mav = new ModelAndView(gradeTree);
		mav.addObject("gradeTree", resultVO);
		return mav;
	}
	// 권한설정 직위 트리 화면
	public ModelAndView getPositionTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Position paramVO = new Position();
		bind(request, paramVO);
		
		Positions resultVO = orgService.getSubPositions(paramVO);
		if(resultVO.getPositionList().size() == 0 || resultVO == null) {
			paramVO.setCompID("DEFAULT");
			resultVO = orgService.getSubPositions(paramVO);
		}
		
		ModelAndView mav = new ModelAndView(positionTree);
		mav.addObject("positionTree", resultVO);
		return mav;
	}

	// 권한설정 직책트리 화면
	public ModelAndView getTitleTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Title paramVO = new Title();
		bind(request, paramVO);
		
		Titles resultVO = orgService.getSubTitles(paramVO);
		if(resultVO.getTitleList().size() == 0 || resultVO == null) {
			paramVO.setCompID("DEFAULT");
			resultVO = orgService.getSubTitles(paramVO);
		}
		
		ModelAndView mav = new ModelAndView(titleTree);
		mav.addObject("titleTree", resultVO);
		return mav;
	}
	
	// 권한설정 직무트리 화면
	public ModelAndView getDutyTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Duty paramVO = new Duty();
		bind(request, paramVO);
		
		Duties resultVO = orgService.getSubDuties(paramVO);
		if(resultVO.getDutyList().size() == 0 || resultVO == null) {
			paramVO.setCompID("DEFAULT");
			resultVO = orgService.getSubDuties(paramVO);
		}
		
		ModelAndView mav = new ModelAndView(dutyTree);
		mav.addObject("dutyTree", resultVO);
		return mav;
	}
	
	
	
}
