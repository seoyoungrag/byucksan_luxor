package com.sds.acube.luxor.portal.service;

import java.util.List;
import org.anyframe.pagination.Page;
import com.sds.acube.luxor.portal.vo.LoginPopupVO;

public interface LoginPopupService {
	public boolean insertPopup(LoginPopupVO vo) throws Exception;
	
	public boolean updatePopup(LoginPopupVO vo) throws Exception;
	
	public boolean deletePopup(LoginPopupVO vo) throws Exception;
	
	public LoginPopupVO getPopup(LoginPopupVO vo) throws Exception;
	
	public List getPopupList(LoginPopupVO vo) throws Exception;
	
	public Page getPopupListPage(LoginPopupVO vo) throws Exception;
}
