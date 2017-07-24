package com.sds.acube.luxor.common.service;

import javax.jws.WebService;

import org.anyframe.pagination.Page;

import com.sds.acube.luxor.common.vo.MemberRequestKeyVO;
import com.sds.acube.luxor.common.vo.MemberRequestVO;
import com.sds.acube.luxor.ws.client.orgservice.ResultInfo;

@WebService
public interface MemberRequestService{
	
	public void insertMemberRequest(MemberRequestVO vo) throws Exception;
	
	public boolean updateMemberRequestStatus(MemberRequestVO vo) throws Exception;
	
	public MemberRequestVO getMemberRequestByPk(MemberRequestVO vo) throws Exception;
	
	public ResultInfo validateRequestId(MemberRequestVO vo) throws Exception;
	
	public MemberRequestVO getRequestedAccount(MemberRequestVO vo) throws Exception;
	
	public ResultInfo approveRequest(MemberRequestVO vo) throws Exception;
	
	public Page getMemberRequestPage(MemberRequestVO vo) throws Exception;
	
	public Page getMemberRequestKeyPage(MemberRequestKeyVO vo) throws Exception;
	
	public MemberRequestKeyVO getMemberRequestKey(MemberRequestKeyVO vo) throws Exception;
	
	public void insertMemberRequestKey(MemberRequestKeyVO vo) throws Exception;
	
	public boolean updateMemberRequestKey(MemberRequestKeyVO vo) throws Exception;
	
	public boolean deleteMemberRequestKey(MemberRequestKeyVO vo) throws Exception;
}
