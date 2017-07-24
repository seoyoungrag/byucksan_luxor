package com.sds.acube.luxor.portal.service.impl;

import java.util.List;
import org.anyframe.pagination.Page;
import com.sds.acube.luxor.framework.service.BaseService;
import com.sds.acube.luxor.portal.dao.LoginPlugDAO;
import com.sds.acube.luxor.portal.service.LoginPlugService;
import com.sds.acube.luxor.portal.vo.LoginPlugVO;

public class LoginPlugServiceImpl extends BaseService implements LoginPlugService {
	private LoginPlugDAO loginPlugDAO;
	
	
	public void setLoginPlugDAO(LoginPlugDAO loginPlugDAO) {
    	this.loginPlugDAO = loginPlugDAO;
    }


	public boolean insertPlug(LoginPlugVO vo) throws Exception {
		return loginPlugDAO.insertPlug(vo);
	}
	
	
	public boolean updatePlug(LoginPlugVO vo) throws Exception {
		return loginPlugDAO.updatePlug(vo);
	}

	
	public boolean deletePlug(LoginPlugVO vo) throws Exception {
		return loginPlugDAO.deletePlug(vo);
	}
	
	public boolean updatePlugActiveInfo(LoginPlugVO vo) throws Exception {
		return loginPlugDAO.updatePlugActiveInfo(vo);
	}
	
	public LoginPlugVO getPlug(LoginPlugVO vo) throws Exception {
		return loginPlugDAO.getPlug(vo);
	}

	public List getPlugList(LoginPlugVO vo) throws Exception {
		return loginPlugDAO.getPlugList(vo);
	}
	
	public Page getPlugListPage(LoginPlugVO vo) throws Exception {
		return loginPlugDAO.getPlugListPage(vo);
	}
	
	
}
