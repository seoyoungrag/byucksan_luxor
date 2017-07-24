package com.sds.acube.luxor.common.service.impl;

import java.util.List;
import com.sds.acube.luxor.common.dao.UserSettingDAO;
import com.sds.acube.luxor.common.service.UserSettingService;
import com.sds.acube.luxor.common.vo.UserSettingVO;
import com.sds.acube.luxor.framework.cache.LuxorHashMap;
import com.sds.acube.luxor.framework.cache.MemoryCache;
import com.sds.acube.luxor.framework.cache.MemoryCacheCenter;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.service.BaseService;


public class UserSettingServiceImpl extends BaseService implements UserSettingService, MemoryCache {
	private static LuxorHashMap cache = new LuxorHashMap(Integer.parseInt(LuxorConfig.getString("Common", "CACHE.MAXIMUM_SIZE")));
	private UserSettingDAO userSettingDAO;
	
	public UserSettingServiceImpl() {
		MemoryCacheCenter.getInstance().register("USER_SETTING_SERVICE", this);
	}

	public void setUserSettingDAO(UserSettingDAO userSettingDAO) {
    	this.userSettingDAO = userSettingDAO;
    }

	public boolean insertUserSetting(UserSettingVO vo) throws Exception {
		return userSettingDAO.insertUserSetting(vo);
	}
	
	public boolean updateUserSetting(UserSettingVO vo) throws Exception {
		return userSettingDAO.updateUserSetting(vo);
	}
	
	public boolean deleteUserSetting(UserSettingVO vo) throws Exception {
		return userSettingDAO.deleteUserSetting(vo);
	}

	public boolean deleteUserAllSetting(UserSettingVO vo) throws Exception {
		logger.info("User("+vo.getUserId()+")'s setting has been cleared!!");
		return userSettingDAO.deleteUserAllSetting(vo);
	}

	public boolean deleteAllSetting(UserSettingVO vo) throws Exception {
		logger.info("All User's setting has been cleared!!");
		return userSettingDAO.deleteAllSetting(vo);
	}

	public UserSettingVO getUserSetting(UserSettingVO vo) throws Exception {
		return userSettingDAO.getUserSetting(vo);
	}
	
	public List getUserSettingList(UserSettingVO vo) throws Exception {
		return userSettingDAO.getUserSettingList(vo);
	}

	
	public void clear() {
		logger.debug("Cache - UserSettingService.clear() called..!!"); 
		cache.clear();
	}
	
	public void remove(Object key) {
		logger.debug("Cache - UserSettingService.remove("+key+") called..!!");
		cache.remove(key);
	}
	
	public int size() {
		return cache.size();
    }
}
