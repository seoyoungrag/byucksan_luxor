package com.sds.acube.luxor.common.dao;

import java.lang.reflect.Method;
import java.util.Date;

import com.sds.acube.luxor.common.vo.LoginHistoryVO;
import com.sds.acube.luxor.framework.dao.BaseDAO;

public class LoginDAO extends BaseDAO {

	public boolean insertLoginHistory(LoginHistoryVO vo) throws Exception {

        Date date = new Date();
        Object[] param = new Object[]{new java.sql.Timestamp(date.getTime())};
        Method method = vo.getClass().getMethod("setLoginTime", java.sql.Timestamp.class);
        method.invoke(vo, param);
		return insert("insertLoginHistory", vo) > 0;
	}
	
	public boolean updateLoginHistory(LoginHistoryVO vo) throws Exception {
        Date date = new Date();
        Object[] param = new Object[]{new java.sql.Timestamp(date.getTime())};
        Method method = vo.getClass().getMethod("setLogoutTime", java.sql.Timestamp.class);
        method.invoke(vo, param);
		return update("updateLoginHistory", vo) > 0;
	}

	public LoginHistoryVO getLastLoginInfo(LoginHistoryVO vo) throws Exception {
		return (LoginHistoryVO)select("getLastLoginInfo", vo);
	}

	public LoginHistoryVO checkLoginLLE(LoginHistoryVO vo) throws Exception {
		return (LoginHistoryVO)select("checkLoginLLE", vo);
	}
	
	public LoginHistoryVO getLoginTime(LoginHistoryVO vo) throws Exception {
      return (LoginHistoryVO)select("getLoginTimeInfo", vo);
  }
	
	
}
