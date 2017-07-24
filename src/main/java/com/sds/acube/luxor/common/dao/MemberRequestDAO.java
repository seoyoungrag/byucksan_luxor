package com.sds.acube.luxor.common.dao;

import java.util.List;

import org.anyframe.pagination.Page;

import com.sds.acube.luxor.common.vo.MemberRequestKeyVO;
import com.sds.acube.luxor.common.vo.MemberRequestVO;
import com.sds.acube.luxor.framework.dao.BaseDAO;

public class MemberRequestDAO extends BaseDAO {
	public boolean insertMemberRequest(MemberRequestVO vo) throws Exception {
		return insert("insertMemberRequest", vo) > 0;
	}

	public boolean updateMemberRequestStatus(MemberRequestVO vo) throws Exception {
		return update("updateMemberRequestStatus", vo) > 0;
	}
	
	public Page getMemberRequestPage(MemberRequestVO vo) throws Exception {
		return selectListPage("getMemberRequestList", vo);
	}
	
	public List<MemberRequestVO> getMemberRequestList(MemberRequestVO vo) throws Exception {
		return (List<MemberRequestVO>)selectList("getMemberRequestList", vo);
	}
	
	public MemberRequestVO getMemberRequestByPk(MemberRequestVO vo) throws Exception {
		return (MemberRequestVO)select("getMemberRequest", vo);
	}
	
	public MemberRequestKeyVO getMemberRequestKey(MemberRequestKeyVO vo) throws Exception {
		return (MemberRequestKeyVO)select("getMemberRequestKey", vo);
	}
	
	public Page getMemberRequestKeyPage(MemberRequestKeyVO vo) throws Exception {
		return super.selectListPage("getMemberRequestKey", vo);
	}
	
	public void insertMemberRequestkey(MemberRequestKeyVO vo) throws Exception {
		insert("insertMemberRequestKey", vo);
	}
	
	public boolean updateMemberRequestKey(MemberRequestKeyVO vo) throws Exception {
		return update("updateMemberRequestKey", vo) > 0;
	}
	
	public boolean deleteMemberRequestKey(MemberRequestKeyVO vo) throws Exception {
		return update("deleteMemberRequestKey", vo) > 0;
	}
}
