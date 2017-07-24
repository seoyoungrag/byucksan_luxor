package com.sds.acube.luxor.common.service;

import com.sds.acube.luxor.common.vo.LoginHistoryVO;
import com.sds.acube.luxor.common.vo.LoginVO;
import com.sds.acube.luxor.common.vo.UserProfileVO;

public interface LoginService {
	public boolean insertLoginHistory(LoginHistoryVO vo) throws Exception;
	public UserProfileVO loginProcess(LoginVO param) throws Exception;
	public boolean updateLoginHistory(LoginHistoryVO vo) throws Exception;
	public boolean checkLoginFLA(LoginHistoryVO vo) throws Exception;
	public boolean checkLoginLLE(LoginHistoryVO vo) throws Exception;
	public LoginHistoryVO getLoginTime(LoginHistoryVO vo) throws Exception;
}
