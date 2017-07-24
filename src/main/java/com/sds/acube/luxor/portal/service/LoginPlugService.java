package com.sds.acube.luxor.portal.service;

import java.util.List;
import org.anyframe.pagination.Page;
import com.sds.acube.luxor.portal.vo.LoginPlugVO;

public interface LoginPlugService {
	public boolean insertPlug(LoginPlugVO vo) throws Exception;
	
	public boolean updatePlug(LoginPlugVO vo) throws Exception;
	
	public boolean deletePlug(LoginPlugVO vo) throws Exception;
	
	public boolean updatePlugActiveInfo(LoginPlugVO vo) throws Exception;
	
	public LoginPlugVO getPlug(LoginPlugVO vo) throws Exception;
	
	public List getPlugList(LoginPlugVO vo) throws Exception;
	
	public Page getPlugListPage(LoginPlugVO vo) throws Exception;
	
	
}
