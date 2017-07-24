package com.sds.acube.luxor.common.service;

import java.util.List;

import com.sds.acube.luxor.common.vo.UserProfileVO;
import com.sds.acube.luxor.ws.client.orgservice.Departments;
import com.sds.acube.luxor.ws.client.orgservice.Duties;
import com.sds.acube.luxor.ws.client.orgservice.Duty;
import com.sds.acube.luxor.ws.client.orgservice.Grade;
import com.sds.acube.luxor.ws.client.orgservice.Grades;
import com.sds.acube.luxor.ws.client.orgservice.IUser;
import com.sds.acube.luxor.ws.client.orgservice.IUsers;
import com.sds.acube.luxor.ws.client.orgservice.Organization;
import com.sds.acube.luxor.ws.client.orgservice.PolicyInfo;
import com.sds.acube.luxor.ws.client.orgservice.Position;
import com.sds.acube.luxor.ws.client.orgservice.Positions;
import com.sds.acube.luxor.ws.client.orgservice.ResultInfo;
import com.sds.acube.luxor.ws.client.orgservice.Title;
import com.sds.acube.luxor.ws.client.orgservice.Titles;

public interface OrgService {

	public Departments getOrgTree(String userUID, int treeType) throws Exception;
	public Organization getOrganization(String orgId) throws Exception;
	public Departments getOrgSubTree(String orgId, int treeType) throws Exception;
	public Grades getSubGrades(Grade vo) throws Exception;
	public Positions getSubPositions(Position vo) throws Exception;
	public Titles getSubTitles(Title vo) throws Exception;
	public Duties getSubDuties(Duty vo) throws Exception;
	public List<PolicyInfo> getPolicyList(int policyType) throws Exception;
	public IUsers getConcurrentUsersByID(String userId);
	public ResultInfo registerConcurrentUser(IUser user);
	public UserProfileVO getUserProfile(String userId);
}
