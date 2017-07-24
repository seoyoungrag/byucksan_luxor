package com.sds.acube.luxor.portal.dao;

import java.util.List;
import org.anyframe.pagination.Page;
import com.sds.acube.luxor.framework.dao.BaseDAO;
import com.sds.acube.luxor.portal.vo.LoginPopupVO;


public class LoginPopupDAO extends BaseDAO {
	
	public boolean insertPopup(LoginPopupVO vo) throws Exception {
		return insert("insertPopup", vo) > 0;
	}
	
	public boolean updatePopup(LoginPopupVO vo) throws Exception {
		return update("updatePopup", vo) > 0;
	}

	public boolean deletePopup(LoginPopupVO vo) throws Exception {
		return delete("deletePopup", vo) > 0;
	}
	
	public LoginPopupVO getPopup(LoginPopupVO vo) throws Exception {
		return (LoginPopupVO)select("getPopup", vo);
	}

	public List getPopupList(LoginPopupVO vo) throws Exception {
		return selectList("getPopupList", vo);
	}

	public Page getPopupListPage(LoginPopupVO vo) throws Exception {
		return selectListPage("getPopupList", vo);
	}
	
}
