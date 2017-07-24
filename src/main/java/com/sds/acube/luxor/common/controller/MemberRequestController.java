package com.sds.acube.luxor.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.anyframe.pagination.Page;
import org.springframework.web.servlet.ModelAndView;

import com.sds.acube.common.security.hash.HashEncrypt;
import com.sds.acube.luxor.common.service.MemberRequestService;
import com.sds.acube.luxor.common.vo.MemberRequestKeyVO;
import com.sds.acube.luxor.common.vo.MemberRequestVO;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.controller.BaseController;
import com.sds.acube.luxor.ws.client.orgservice.ResultInfo;

public class MemberRequestController extends BaseController {
	private MemberRequestService memberRequestService;
	private String requestView;
	private String termsView;
	private String approvalView;
	private String memberRequestKeyView;
	private String approvalListView;
	private String memberRequestKeyListView;
	
	public void setMemberRequestService(MemberRequestService memberRequestService) {
    	this.memberRequestService = memberRequestService;
    }
	
	public void setRequestView(String requestView) {
		this.requestView = requestView;
	}
	
	public void setTermsView(String termsView) {
		this.termsView = termsView;
	}
	
	public void setApprovalView(String approvalView) {
		this.approvalView = approvalView;
	}
	
	public void setApprovalListView(String approvalListView) {
		this.approvalListView = approvalListView;
	}
	
	public void setMemberRequestKeyView(String memberRequestKeyView) {
		this.memberRequestKeyView = memberRequestKeyView;
	}
	
	public void setMemberRequestKeyListView(String memberRequestKeyListView) {
		this.memberRequestKeyListView = memberRequestKeyListView;
	}

	/**
	 * 계정신청 화면 로딩
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getRequestView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberRequestVO vo = new MemberRequestVO();
        bind(request,vo);
        if(!vo.getIsTermsAgree().equals("Y")) {
        	throw new Exception("Member Request Terms are not checked.");
        }
		return new ModelAndView(requestView);
	}
	
	/**
	 * 계정신청 서명 화면 로딩
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getTermsView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView(termsView);
	}

	
	/**
	 * 계정신청 승인요청
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView insertMemberRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MemberRequestVO vo = new MemberRequestVO();
        bind(request,vo);
        vo.setLoginIp(request.getRemoteAddr());
        memberRequestService.insertMemberRequest(vo);
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", true);
		return mav;
	}
	
	/**
	 * 계정 중복체크
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView validateRequestId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberRequestVO vo = new MemberRequestVO();
        bind(request,vo);
        ResultInfo result = memberRequestService.validateRequestId(vo);
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}
	
	/**
	 * 신청중인 계정이 있는지 체크
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getRequestedAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberRequestVO vo = new MemberRequestVO();
        bind(request,vo);
        vo = memberRequestService.getRequestedAccount(vo);
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", vo);
		return mav;
	}
	
	/**
	 * 신청 조회(PK)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getMemberRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberRequestVO vo = new MemberRequestVO();
		MemberRequestVO resultVO = new MemberRequestVO();
		bind(request,vo);
		String passwd = vo.getLoginPassword();
		String passwordEncodingAlgorithm = LuxorConfig.getString("SSO.PASSWORD_ENCODE_ALGORITHM");
		if(!passwordEncodingAlgorithm.equals("N")) {
			passwd = HashEncrypt.getHashEncryptData(passwd, passwordEncodingAlgorithm);
		} 
		vo.setLoginPassword("");
        resultVO = memberRequestService.getMemberRequestByPk(vo);
		vo.setLoginPassword(passwd);	
        if(!resultVO.getLoginPassword().equals(vo.getLoginPassword())) {
        	//패스워드 불일치
        	resultVO.setRequestStatus(-1);
        }
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", resultVO);
		return mav;
	}

	/**
	 * 신청 상태 업데이트(승인,반려)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView updateMemberRequestStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		adminCheck(request);
		MemberRequestVO vo = new MemberRequestVO();
		MemberRequestVO resultVO = new MemberRequestVO();
        bind(request,vo);
        String[] ids = vo.getRequestIds();
        boolean result = false;
		for(String id : ids) {
			vo.setRequestId(id);
			result = memberRequestService.updateMemberRequestStatus(vo);
			if(result == false) {
				throw new Exception("updateMemberRequestStatus failed.");
			} else if(vo.getRequestStatus() == 1) { //승인시에는 계정생성 서비스 호출
				vo.setRequestStatus(-1);
				resultVO = memberRequestService.getMemberRequestByPk(vo);
				memberRequestService.approveRequest(resultVO);
			}
		}
        
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}
	
	/**
	 * 계정 등록 최종 완료 상태 변경 (암호 재설정까지 끝났을때)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView confirmMemberRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberRequestVO vo = new MemberRequestVO();
        bind(request,vo);
        vo.setRequestStatus(3);
		boolean result = memberRequestService.updateMemberRequestStatus(vo);
		if(result == false) {
			throw new Exception("confirmMemberRequest failed.");
		} 
        
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}
	
	/**
	 * 신청 관리 페이지(리스트)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getApprovalList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		adminCheck(request);
		MemberRequestVO vo = new MemberRequestVO();
        bind(request,vo);
        vo.setRequestStatus(-1);
		Page memberRequestPage = memberRequestService.getMemberRequestPage(vo);
		ModelAndView mav = new ModelAndView(approvalListView);
		mav.addObject("memberRequestPage", memberRequestPage);
		return mav;
	}
	
	/**
	 * 신청 관리 페이지(메인)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getApprovalView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		adminCheck(request);
		MemberRequestVO vo = new MemberRequestVO();
        bind(request,vo);
		ModelAndView mav = new ModelAndView(approvalView);
		return mav;
	}
	
	/**
	 * 계정등록 키 관리 페이지(리스트)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getMemberRequestKeyList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		adminCheck(request);
		MemberRequestKeyVO vo = new MemberRequestKeyVO();
        bind(request,vo);
		Page memberRequestKeyPage = memberRequestService.getMemberRequestKeyPage(vo);
		ModelAndView mav = new ModelAndView(memberRequestKeyListView);
		mav.addObject("memberRequestKeyPage", memberRequestKeyPage);
		return mav;
	}
	
	/**
	 * 계정등록 키 관리 페이지(메인)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getMemberRequestKeyView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		adminCheck(request);
		MemberRequestKeyVO vo = new MemberRequestKeyVO();
        bind(request,vo);
		ModelAndView mav = new ModelAndView(memberRequestKeyView);
		return mav;
	}

	/**
	 * 계정등록 키 검증
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getMemberRequestKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberRequestKeyVO vo = new MemberRequestKeyVO();
        bind(request,vo);
        HttpSession session = request.getSession();
        String portalId = (String)session.getAttribute("PORTAL_ID");
        //로그인 페이지는 포탈 ID가 없을 수 있으므로..
        vo.setPortalId(portalId);
        vo = memberRequestService.getMemberRequestKey(vo);
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", vo);
		return mav;
	}
	
	/**
	 * 계정등록 키 추가
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView insertMemberRequestKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		adminCheck(request);
		MemberRequestKeyVO vo = new MemberRequestKeyVO();
        bind(request,vo);
		memberRequestService.insertMemberRequestKey(vo);
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", true);
		return mav;
	}
	
	/**
	 * 계정등록 키 수정
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView updateMemberRequestKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		adminCheck(request);
		MemberRequestKeyVO vo = new MemberRequestKeyVO();
        bind(request,vo);
		boolean result = memberRequestService.updateMemberRequestKey(vo);
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}
	
	/**
	 * 계정등록 키 삭제
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deleteMemberRequestKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		adminCheck(request);
		MemberRequestKeyVO vo = new MemberRequestKeyVO();
        bind(request,vo);
        String[] ids = vo.getRequestKeys();
        boolean result = false;
		for(String id : ids) {
			vo.setRequestKey(id);
			result = memberRequestService.deleteMemberRequestKey(vo);
			if(result == false) {
				throw new Exception("deleteMemberRequestKey failed.");
			}
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}
}
