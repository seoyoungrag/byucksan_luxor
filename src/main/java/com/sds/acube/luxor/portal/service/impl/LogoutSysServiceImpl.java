/*
 * @(#) LogoutSysServiceImpl.java 2010. 8. 17.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.portal.service.impl;

import java.util.List;
import com.sds.acube.luxor.common.ConstantList;
import com.sds.acube.luxor.common.service.MessageService;
import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.framework.service.BaseService;
import com.sds.acube.luxor.portal.dao.LogoutSysDAO;
import com.sds.acube.luxor.portal.service.LogoutSysService;
import com.sds.acube.luxor.portal.vo.LogoutSysVO;


/**
 * @author Alex, Eum
 * @version $Revision: 1.1.4.1 $ $Date: 2011/02/10 06:05:54 $
 */
public class LogoutSysServiceImpl extends BaseService implements LogoutSysService {
	private LogoutSysDAO logoutDAO;
	private MessageService messageService;


	/**
	 * @param logoutDAO The logoutDAO to set.
	 */
	public void setLogoutDAO(LogoutSysDAO logoutDAO) {
		this.logoutDAO = logoutDAO;
	}


	/**
	 * @param messageService The messageService to set.
	 */
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sds.acube.luxor.common.service.LogoutSysService#delete(com.sds.acube.luxor.common.vo.LogoutSysVO)
	 */
	public int delete(LogoutSysVO param) throws Exception {
		messageService.deleteMessage(logoutDAO.get(param));
		return logoutDAO.delete(param);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sds.acube.luxor.common.service.LogoutSysService#get(com.sds.acube.luxor.common.vo.LogoutSysVO)
	 */
	public LogoutSysVO get(LogoutSysVO param) throws Exception {
		return logoutDAO.get(param);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sds.acube.luxor.common.service.LogoutSysService#getList(com.sds.acube.luxor.common.vo.LogoutSysVO)
	 */
	public List<LogoutSysVO> getList(LogoutSysVO param) throws Exception {
		return logoutDAO.getList(param);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sds.acube.luxor.common.service.LogoutSysService#getListAll(com.sds.acube.luxor.common.vo.LogoutSysVO)
	 */
	public List<LogoutSysVO> getListAll(LogoutSysVO param) throws Exception {
		return logoutDAO.getListAll(param);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sds.acube.luxor.common.service.LogoutSysService#register(com.sds.acube.luxor.common.vo.LogoutSysVO)
	 */
	public int register(LogoutSysVO param) throws Exception {
		param.setSystemId(CommonUtil.generateId("SYS"));
		String systemNameId = messageService.insertMessage(param);
		param.setSystemName(systemNameId);
		return logoutDAO.insert(param);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sds.acube.luxor.common.service.LogoutSysService#update(com.sds.acube.luxor.common.vo.LogoutSysVO)
	 */
	public int update(LogoutSysVO param) throws Exception {
		// 메세지수정
		messageService.updateMessage(param);
		return logoutDAO.update(param);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sds.acube.luxor.common.service.LogoutSysService#apply(com.sds.acube.luxor.common.vo.LogoutSysVO)
	 */
	public int apply(LogoutSysVO param) throws Exception {
		int r = 0;
		if ("".equals(param.getSystemId())) {
			String systemNameId = messageService.insertMessage(param);
			param.setSystemName(systemNameId);
			param.setSystemId(CommonUtil.generateId("SYS"));
			r = logoutDAO.insert(param);
		} else {
			messageService.updateMessage(param);
			r = logoutDAO.update(param);
		}
		return r;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sds.acube.luxor.common.service.LogoutSysService#toEnable(com.sds.acube.luxor.common.vo.LogoutSysVO)
	 */
	public int toEnable(LogoutSysVO param) throws Exception {
		param.setStatus(ConstantList.ENABLE);
		return logoutDAO.updateStatus(param);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sds.acube.luxor.common.service.LogoutSysService#toUnable(com.sds.acube.luxor.common.vo.LogoutSysVO)
	 */
	public int toUnable(LogoutSysVO param) throws Exception {
		param.setStatus(ConstantList.UNABLE);
		return logoutDAO.updateStatus(param);
	}


	@Deprecated
	public boolean logoutAll(LogoutSysVO param) throws Exception {
		boolean r = false;
		List<LogoutSysVO> list = logoutDAO.getList(param);
		for (int i = 0; i < list.size(); i++) {
			/*
			 * LogoutSysVO item = (LogoutSysVO)list.get(i);
			 * CommonUtil.callURL(item.getLogoutUrl());
			 * logger.debug("logoutAll:" + item.getLogoutUrl() + "=\n" + html);
			 * if(html.indexOf(ConstantList.LOGOUTSYS_KEYWORD) != -1) r = true;
			 * else {
			 * r = false;
			 * throw new Exception("call fail.(url:" + item.getLogoutUrl() + ")");
			 * }
			 */
		}
		return r;
	}
}
