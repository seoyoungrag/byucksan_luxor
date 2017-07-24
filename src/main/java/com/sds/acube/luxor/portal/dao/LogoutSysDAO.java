/*
 * @(#) LogoutSysDAO.java 2010. 8. 17.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.portal.dao;

import java.util.List;

import com.sds.acube.luxor.framework.dao.BaseDAO;
import com.sds.acube.luxor.portal.vo.LogoutSysVO;


/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.1.4.1 $ $Date: 2011/02/10 06:05:30 $
 */
@SuppressWarnings("unchecked")
public class LogoutSysDAO extends BaseDAO {
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<LogoutSysVO> getList(LogoutSysVO param) throws Exception {
		return selectList("com.sds.acube.luxor.common.logoutsys.getList", param);
	}
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<LogoutSysVO> getListAll(LogoutSysVO param) throws Exception {
		return selectList("com.sds.acube.luxor.common.logoutsys.getListAll", param);
	}
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public LogoutSysVO get(LogoutSysVO param) throws Exception {
		return (LogoutSysVO)select("com.sds.acube.luxor.common.logoutsys.get", param);
	}
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int insert(LogoutSysVO param) throws Exception {
		return insert("com.sds.acube.luxor.common.logoutsys.insert", param);
	}
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int update(LogoutSysVO param) throws Exception {
		return update("com.sds.acube.luxor.common.logoutsys.update", param);
	}
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int updateStatus(LogoutSysVO param) throws Exception {
		return update("com.sds.acube.luxor.common.logoutsys.updateStatus", param);
	}
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int delete(LogoutSysVO param) throws Exception {
		return delete("com.sds.acube.luxor.common.logoutsys.delete", param);
	}
}
