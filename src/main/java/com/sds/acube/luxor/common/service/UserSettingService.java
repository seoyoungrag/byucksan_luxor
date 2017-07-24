package com.sds.acube.luxor.common.service;

import java.util.List;
import com.sds.acube.luxor.common.vo.UserSettingVO;

public interface UserSettingService {
	public boolean insertUserSetting(UserSettingVO vo) throws Exception;
	
	public boolean updateUserSetting(UserSettingVO vo) throws Exception;
	
	/**
	 * 사용자의 특정 셋팅을 삭제
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean deleteUserSetting(UserSettingVO vo) throws Exception;
	
	/**
	 * 사용자의 모든 셋팅을 삭제
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean deleteUserAllSetting(UserSettingVO vo) throws Exception;
	
	/**
	 * 모든 사용자의 모든 셋팅을 삭제
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean deleteAllSetting(UserSettingVO vo) throws Exception;
	
	public UserSettingVO getUserSetting(UserSettingVO vo) throws Exception;
	
	public List getUserSettingList(UserSettingVO vo) throws Exception;
}
