package com.sds.acube.luxor.portal.service.impl;

import java.util.List;
import org.anyframe.pagination.Page;
import com.sds.acube.luxor.framework.service.BaseService;
import com.sds.acube.luxor.portal.dao.LoginPopupDAO;
import com.sds.acube.luxor.portal.service.LoginPopupService;
import com.sds.acube.luxor.portal.vo.LoginPopupVO;

public class LoginPopupServiceImpl extends BaseService implements LoginPopupService {
	private LoginPopupDAO loginPopupDAO;
	
	
	public void setLoginPopupDAO(LoginPopupDAO loginPopupDAO) {
    	this.loginPopupDAO = loginPopupDAO;
    }


	public boolean insertPopup(LoginPopupVO vo) throws Exception {
		return loginPopupDAO.insertPopup(vo);
	}
	
	
	public boolean updatePopup(LoginPopupVO vo) throws Exception {
		return loginPopupDAO.updatePopup(vo);
	}

	
	public boolean deletePopup(LoginPopupVO vo) throws Exception {
		return loginPopupDAO.deletePopup(vo);
	}
	
	
	public LoginPopupVO getPopup(LoginPopupVO vo) throws Exception {
		return loginPopupDAO.getPopup(vo);
	}

	
	public List getPopupList(LoginPopupVO vo) throws Exception {
		return loginPopupDAO.getPopupList(vo);
	}

	public Page getPopupListPage(LoginPopupVO vo) throws Exception {
		return loginPopupDAO.getPopupListPage(vo);
	}
	
	
}
