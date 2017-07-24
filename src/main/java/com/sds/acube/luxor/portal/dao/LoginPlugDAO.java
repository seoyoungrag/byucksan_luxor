package com.sds.acube.luxor.portal.dao;

import java.util.List;
import org.anyframe.pagination.Page;
import com.sds.acube.luxor.framework.dao.BaseDAO;
import com.sds.acube.luxor.portal.vo.LoginPlugVO;


public class LoginPlugDAO extends BaseDAO {
	
	public boolean insertPlug(LoginPlugVO vo) throws Exception {
		return insert("insertPlug", vo) > 0;
	}
	
	public boolean updatePlug(LoginPlugVO vo) throws Exception {
		return update("updatePlug", vo) > 0;
	}

	public boolean deletePlug(LoginPlugVO vo) throws Exception {
		return delete("deletePlug", vo) > 0;
	}
	
	public boolean updatePlugActiveInfo(LoginPlugVO vo) throws Exception {
		return delete("updatePlugActiveInfo", vo) > 0;
	}
	
	public LoginPlugVO getPlug(LoginPlugVO vo) throws Exception {
		return (LoginPlugVO)select("getPlug", vo);
	}

	public List getPlugList(LoginPlugVO vo) throws Exception {
		return selectList("getPlugList", vo);
	}

	public Page getPlugListPage(LoginPlugVO vo) throws Exception {
		return selectListPage("getPlugListAll", vo);
	}
	
}
