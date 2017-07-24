package com.sds.acube.luxor.common.service.impl;

import java.util.List;

import org.anyframe.pagination.Page;

import com.sds.acube.common.security.hash.HashEncrypt;
import com.sds.acube.luxor.common.dao.MemberRequestDAO;
import com.sds.acube.luxor.common.service.MemberRequestService;
import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.vo.MemberRequestKeyVO;
import com.sds.acube.luxor.common.vo.MemberRequestVO;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.service.BaseService;
import com.sds.acube.luxor.ws.client.orgservice.IUser;
import com.sds.acube.luxor.ws.client.orgservice.OrgProvider4WS;
import com.sds.acube.luxor.ws.client.orgservice.ResultInfo;

public class MemberRequestServiceImpl extends BaseService implements MemberRequestService{
	private MemberRequestDAO memberRequestDao;
	private OrgProvider4WS orgService;
	private String serviceKey;
	
	public void setMemberRequestDao(MemberRequestDAO memberRequestDao) {
    	this.memberRequestDao = memberRequestDao;
    }
	
	public void setOrgService(OrgProvider4WS orgService) {
		serviceKey = LuxorConfig.getString("IAM.KEY");
		this.orgService = orgService;
	}

	public MemberRequestVO getMemberRequestByPk(MemberRequestVO vo) throws Exception {
		return memberRequestDao.getMemberRequestByPk(vo);
	}

	public void insertMemberRequest(MemberRequestVO vo) throws Exception {
		MemberRequestKeyVO memberRequestKey = new MemberRequestKeyVO();
		vo.setRequestId(CommonUtil.generateId("R"));
		String passwordEncodingAlgorithm = LuxorConfig.getString("SSO.PASSWORD_ENCODE_ALGORITHM");
		String passwd = "";
		if(!passwordEncodingAlgorithm.equals("N")) {
			passwd = HashEncrypt.getHashEncryptData(vo.getLoginPassword(), passwordEncodingAlgorithm);
		} 
		vo.setLoginPassword(passwd);		
		memberRequestKey = memberRequestDao.getMemberRequestKey(vo);
		memberRequestKey.setUseCount(memberRequestKey.getUseCount()+1);
		memberRequestDao.updateMemberRequestKey(memberRequestKey);
		memberRequestDao.insertMemberRequest(vo);
	}

	public boolean updateMemberRequestStatus(MemberRequestVO vo)
			throws Exception {
		return memberRequestDao.updateMemberRequestStatus(vo);
	}

	public ResultInfo approveRequest(MemberRequestVO vo) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		
		IUser iUser = new IUser();
		iUser.setUserID(vo.getLoginId());
		iUser.setUserUID(CommonUtil.generateId());
		iUser.setUserName(vo.getUserName());
		iUser.setDeptID(vo.getDeptId());
		iUser.setCompID(vo.getCompId());
		iUser.setGradeCode(vo.getGradeId());
		//SHA-256으로 Encoding 된 password를 넘겨줌 -> Encode type = 0
		resultInfo = orgService.register(serviceKey, iUser, vo.getLoginPassword(), 0);
		return resultInfo;
	}

	public ResultInfo validateRequestId(MemberRequestVO vo) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		MemberRequestVO returnVo = new MemberRequestVO();
		resultInfo = orgService.checkIdForInsert(serviceKey, vo.getLoginId());
		//조직에서 사용 가능한 경우
		if(resultInfo.getResultCode() == 1) {
			//가입신청중인 ID도 체크
			returnVo = memberRequestDao.getMemberRequestByPk(vo);
			if(returnVo != null) {
				resultInfo.setResultCode(-1);
			}
		}
		return resultInfo;
	}

	//신청한 계정인지 체크하여 계정을 리턴해줌
	public MemberRequestVO getRequestedAccount(MemberRequestVO vo)
			throws Exception {
		MemberRequestVO returnVO = new MemberRequestVO();
		String passwordEncodingAlgorithm = LuxorConfig.getString("SSO.PASSWORD_ENCODE_ALGORITHM");
		String passwd = "";
		if(!passwordEncodingAlgorithm.equals("N")) {
			passwd = HashEncrypt.getHashEncryptData(vo.getLoginPassword(), passwordEncodingAlgorithm);
		} 
		List<MemberRequestVO> list = memberRequestDao.getMemberRequestList(vo);
		for(MemberRequestVO indexVO : list) {
			if(indexVO.getLoginPassword().equals(passwd) && indexVO.getLoginId().equals(vo.getLoginId())) {
				returnVO = indexVO;
				break;
			}
		}
		return returnVO;
	}

	public Page getMemberRequestPage(MemberRequestVO vo) throws Exception {
		return memberRequestDao.getMemberRequestPage(vo);
	}

	public MemberRequestKeyVO getMemberRequestKey(MemberRequestKeyVO vo)
			throws Exception {
		return memberRequestDao.getMemberRequestKey(vo);
	}

	public Page getMemberRequestKeyPage(MemberRequestKeyVO vo) throws Exception {
		return memberRequestDao.getMemberRequestKeyPage(vo);
	}

	public boolean deleteMemberRequestKey(MemberRequestKeyVO vo)
			throws Exception {
		return memberRequestDao.deleteMemberRequestKey(vo);
	}

	public boolean updateMemberRequestKey(MemberRequestKeyVO vo)
			throws Exception {
		return memberRequestDao.updateMemberRequestKey(vo);
	}

	public void insertMemberRequestKey(MemberRequestKeyVO vo) throws Exception {
		memberRequestDao.insertMemberRequestkey(vo);
	}
}
