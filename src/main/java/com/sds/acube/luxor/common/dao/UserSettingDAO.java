package com.sds.acube.luxor.common.dao;

import java.util.List;
import com.sds.acube.luxor.common.vo.UserSettingVO;
import com.sds.acube.luxor.framework.dao.BaseDAO;

public class UserSettingDAO extends BaseDAO {
	public boolean insertUserSetting(UserSettingVO vo) throws Exception {
		return insert("insertUserSetting", vo) > 0;
	}

	public boolean updateUserSetting(UserSettingVO vo) throws Exception {
		return update("updateUserSetting", vo) > 0;
	}

	public boolean deleteUserSetting(UserSettingVO vo) throws Exception {
		return delete("deleteUserSetting", vo) > 0;
	}

	public boolean deleteUserAllSetting(UserSettingVO vo) throws Exception {
		return delete("deleteUserAllSetting", vo) > 0;
	}

	public boolean deleteAllSetting(UserSettingVO vo) throws Exception {
		return delete("deleteAllSetting", vo) > 0;
	}

	public UserSettingVO getUserSetting(UserSettingVO vo) throws Exception {
		return (UserSettingVO)select("getUserSetting", vo);
	}

	public List getUserSettingList(UserSettingVO vo) throws Exception {
		return selectList("getUserSettingList", vo);
	}
}
