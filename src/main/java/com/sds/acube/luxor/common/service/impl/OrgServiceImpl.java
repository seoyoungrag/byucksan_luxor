package com.sds.acube.luxor.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebParam;

import com.sds.acube.luxor.common.ConstantList;
import com.sds.acube.luxor.common.service.OrgService;
import com.sds.acube.luxor.common.vo.UserProfileVO;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.service.BaseService;
import com.sds.acube.luxor.ws.client.aclservice.AclProvider4WS;
import com.sds.acube.luxor.ws.client.orgservice.Departments;
import com.sds.acube.luxor.ws.client.orgservice.Duties;
import com.sds.acube.luxor.ws.client.orgservice.Duty;
import com.sds.acube.luxor.ws.client.orgservice.Grade;
import com.sds.acube.luxor.ws.client.orgservice.Grades;
import com.sds.acube.luxor.ws.client.orgservice.IUser;
import com.sds.acube.luxor.ws.client.orgservice.IUsers;
import com.sds.acube.luxor.ws.client.orgservice.OrgProvider4WS;
import com.sds.acube.luxor.ws.client.orgservice.Organization;
import com.sds.acube.luxor.ws.client.orgservice.Organizations;
import com.sds.acube.luxor.ws.client.orgservice.PolicyInfo;
import com.sds.acube.luxor.ws.client.orgservice.Position;
import com.sds.acube.luxor.ws.client.orgservice.Positions;
import com.sds.acube.luxor.ws.client.orgservice.ResultInfo;
import com.sds.acube.luxor.ws.client.orgservice.Title;
import com.sds.acube.luxor.ws.client.orgservice.Titles;

public class OrgServiceImpl extends BaseService implements OrgService {
	private OrgProvider4WS orgService;
	private AclProvider4WS aclService;
	private String serviceKey;
	
	public void setOrgService(OrgProvider4WS orgService) {
		this.orgService = orgService;
		serviceKey = LuxorConfig.getString("IAM.KEY");
	}
	
	public void setAclService(AclProvider4WS aclService) {
		this.aclService = aclService;
	}
	
	/**
	 * 조직도 Tree를 생성
	 * 
	 * @param 사용자 UID
	 * @param 트리 Type
	 * 		   0 : From User Dept Code to Root Code
	 *         1 : From User Dept Code to Institution Code 
	 *         2 : From User Dept Code to Company Code 
	 *         3 : From User Dept Code to Root Code (Exclude Other Company) 
	 */
	public Departments getOrgTree(String userUID, int treeType) throws Exception {
		Departments depts = null;
		depts = orgService.getDepartmentsTree(serviceKey, userUID, treeType); // userUID, 트리 Type	
		return depts;
	}
	
	public Organization getOrganization(String orgId) throws Exception {
		Organization org = null;
		org = orgService.getOrganization(serviceKey, orgId); 
		return org;
	}

	/**
	 * 하위 부서 List
	 * 
	 * @param strDeptID 부서 ID
	 * @param nType 하위 부서 List Type
	 * @return Departments nType = 0 ; 부서까지 추출 nType = 1 : 파트까지 추출
	 */
	public Departments getOrgSubTree(String orgId, int treeType) throws Exception {
		Departments dept = orgService.getSubDepartments(serviceKey, orgId, treeType); // userUID, 트리 Type
		return dept;
	}

	public Grades getSubGrades(Grade vo) throws Exception {
		Grades pst = orgService.getSubGrades(serviceKey, vo.getGradeID(), vo.getCompID()); // 직급 ID, 회사 ID
		if(pst==null) {
			pst = orgService.getSubGrades(serviceKey, vo.getGradeID(), "DEFAULT");
		}
		return pst;
	}

	public Positions getSubPositions(Position vo) throws Exception {
		Positions grd = orgService.getSubPositions(serviceKey, vo.getPositionID(), vo.getCompID()); // 직위 ID, 회사 ID
		if(grd==null) {
			grd = orgService.getSubPositions(serviceKey, vo.getPositionID(), "DEFAULT");
		}
		return grd;
	}
	
	public Titles getSubTitles(Title vo) throws Exception {
		Titles ttl = orgService.getSubTitles(serviceKey, vo.getTitleID(), vo.getCompID()); // 직책 ID, 회사 ID
		if(ttl==null) {
			ttl = orgService.getSubTitles(serviceKey, vo.getTitleID(), "DEFAULT");
		}
		return ttl;
	}
	
	public Duties getSubDuties(Duty vo) throws Exception {
		Duties dty = orgService.getSubDuties(serviceKey, vo.getDutyID(), vo.getCompID()); // 직무 ID, 회사 ID
		if(dty==null) {
			dty = orgService.getSubDuties(serviceKey, vo.getDutyID(), "DEFAULT");
		}
		return dty;
	}
	
	public List<PolicyInfo> getPolicyList(int policyType) throws Exception {
		List<PolicyInfo> policyInfo = orgService.getPolicyListByType(serviceKey, policyType);
		return policyInfo;
	}
	
	public IUsers getConcurrentUsersByID(String userId) {
		return orgService.getConcurrentUsersByID(serviceKey, userId);
	}
	
	public ResultInfo registerConcurrentUser(IUser user) {
		return orgService.registerConcurrentUser(serviceKey, user);
	}
	
	public UserProfileVO getUserProfile(String userUID) {
		IUser user = orgService.getUserByUID(serviceKey, userUID);
		UserProfileVO myProfile = new UserProfileVO();
		
		if(user != null) {
			myProfile.setLoginResult(ConstantList.LOGIN_SUCCESS);
			myProfile.setCompId(user.getCompID());
			myProfile.setCompName(user.getCompName());
			myProfile.setLoginId(user.getUserID());
			myProfile.setUserUid(user.getUserUID());
			myProfile.setUserName(user.getUserName());
			myProfile.setDeptId(user.getDeptID());
			myProfile.setDeptName(user.getDeptName());
			myProfile.setSecurityLevel(user.getSecurityLevel());
			myProfile.setGradeCode(user.getGradeCode());
			myProfile.setGradeName(user.getGradeName());
			myProfile.setOfficeTel(user.getOfficeTel());
			myProfile.setHomeTel(user.getHomeTel());
			myProfile.setMobile(user.getMobile());
			myProfile.setSysMail(user.getSysMail());
			myProfile.seteMail(user.getEmail());
			myProfile.setChangedPasswordDate(user.getChangedPWDDate());
			myProfile.setRoleCodes(user.getRoleCodes());
			myProfile.setPositionCode(user.getPositionCode());
			myProfile.setPositionName(user.getPositionName());
			myProfile.setTitleName(user.getTitleName());
			myProfile.setOfficeFax(user.getOfficeFax());
			
			Organizations organizations = orgService.getUserOrganizationsByOrgID(serviceKey, myProfile.getCompId(), myProfile.getDeptId());
			int orgCount = (organizations == null) ? 0 : organizations.getOrganizationList().size();
			ArrayList<String> list = new ArrayList<String>();
			
			for (int i = 0; i < orgCount; i++) {
				Organization organization = organizations.getOrganizationList().get(i);
				list.add(organization.getOrgID());
			}
			
			String[] arrDeptID = new String[list.size()];
			list.toArray(arrDeptID);
			myProfile.setDepartmentList(arrDeptID);
		} else {
			myProfile.setLoginResult(ConstantList.LOGIN_FAIL_CONCURRENT);
		}
		return myProfile;
	}
}
