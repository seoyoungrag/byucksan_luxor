/*
 * @(#) GroupPortalServiceImpl.java 2010. 7. 22.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.portal.service.impl;

import java.util.List;
import org.anyframe.pagination.Page;
import com.sds.acube.luxor.framework.cache.MemoryCacheCenter;
import com.sds.acube.luxor.framework.service.BaseService;
import com.sds.acube.luxor.portal.dao.GroupPortalDAO;
import com.sds.acube.luxor.portal.service.GroupPortalService;
import com.sds.acube.luxor.portal.vo.GroupPortalVO;


/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.3.2.4 $ $Date: 2011/04/21 00:13:39 $
 */
public class GroupPortalServiceImpl extends BaseService implements GroupPortalService {

	private GroupPortalDAO groupPortalDAO;
	
	/**
	 * @param groupPortalDAO The groupPortalDAO to set.
	 */
	public void setGroupPortalDAO(GroupPortalDAO groupPortalDAO) {
		this.groupPortalDAO = groupPortalDAO;
	}

	/* (non-Javadoc)
	 * @see com.sds.acube.luxor.common.service.GroupPortalService#delete(com.sds.acube.luxor.common.vo.GroupPortalVO)
	 */
	public int delete(GroupPortalVO param) throws Exception {
		return groupPortalDAO.delete(param);
	}

	/* (non-Javadoc)
	 * @see com.sds.acube.luxor.common.service.GroupPortalService#get(com.sds.acube.luxor.common.vo.GroupPortalVO)
	 */
	public GroupPortalVO get(GroupPortalVO param) throws Exception {
		return groupPortalDAO.get(param);
	}

	/* (non-Javadoc)
	 * @see com.sds.acube.luxor.common.service.GroupPortalService#getList(com.sds.acube.luxor.common.vo.GroupPortalVO)
	 */
	public List<GroupPortalVO> getList(GroupPortalVO param) throws Exception {
		return groupPortalDAO.getList(param);
	}
	
	/* (non-Javadoc)
	 * @see com.sds.acube.luxor.common.service.GroupPortalService#getListPage(com.sds.acube.luxor.common.vo.GroupPortalVO)
	 */
	public Page getListPage(GroupPortalVO param) throws Exception {
		return groupPortalDAO.getListPage(param);
	}
	
	/* (non-Javadoc)
	 * @see com.sds.acube.luxor.common.service.GroupPortalService#portalRegister(com.sds.acube.luxor.common.vo.GroupPortalVO)
	 */
	public boolean portalRegister(GroupPortalVO param) throws Exception {
		if(groupPortalDAO.getList(param).size() == 0) param.setDefaultFlag(1);
		
		GroupPortalVO item = groupPortalDAO.get(param);
		
		if(item != null) {
			return groupPortalDAO.update(param) > 0 ? true : false;
		}
		else {
			logger.debug("item is null.");
			
			param.setOldTenantId("%%TENANT_ID%%");
			param.setOldPortalId("%%PORTAL_ID%%");
			
			groupPortalDAO.portalCopy(param);
			
			MemoryCacheCenter.getInstance().clearAll();
			
			return groupPortalDAO.insert(param) > 0 ? true : false;
		}
	}

	/* (non-Javadoc)
	 * @see com.sds.acube.luxor.common.service.GroupPortalService#setDefault(com.sds.acube.luxor.common.vo.GroupPortalVO)
	 */
	public int setDefault(GroupPortalVO param) throws Exception {
		groupPortalDAO.unsetDefault(param);
		return groupPortalDAO.setDefault(param);
	}

	/* (non-Javadoc)
	 * @see com.sds.acube.luxor.common.service.GroupPortalService#getDefault(com.sds.acube.luxor.common.vo.GroupPortalVO)
	 */
	public GroupPortalVO getDefault(GroupPortalVO param) throws Exception {
		return groupPortalDAO.getDefault(param);
	}
	
	public GroupPortalVO getParentPortal(GroupPortalVO param) throws Exception {
		return groupPortalDAO.getParentPortal(param);
	}
	
	public int updateChildPortal(GroupPortalVO param) throws Exception {
		return groupPortalDAO.updateChildPortal(param);
	}
	
	public GroupPortalVO checkDuplication(GroupPortalVO param) throws Exception{
		return groupPortalDAO.checkDuplication(param);
	}
}
