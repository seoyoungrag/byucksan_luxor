package com.sds.acube.luxor.portal.service.impl;

import org.anyframe.pagination.Page;
import com.sds.acube.luxor.framework.cache.MemoryCacheCenter;
import com.sds.acube.luxor.framework.service.BaseService;
import com.sds.acube.luxor.portal.dao.PortalEnvironmentDAO;
import com.sds.acube.luxor.portal.service.PortalEnvironmentService;
import com.sds.acube.luxor.portal.vo.PortalEnvironmentVO;

public class PortalEnvironmentServiceImpl extends BaseService implements PortalEnvironmentService {
	private PortalEnvironmentDAO portalEnvironmentDAO;
	
	public void setPortalEnvironmentDAO(PortalEnvironmentDAO portalEnvironmentDAO) {
    	this.portalEnvironmentDAO = portalEnvironmentDAO;
    }

	
	public boolean insertEnvironment(PortalEnvironmentVO vo) throws Exception {
		MemoryCacheCenter.getInstance().clearAll();
		return portalEnvironmentDAO.insertEnvironment(vo);
    }

	public boolean updateEnvironment(PortalEnvironmentVO vo) throws Exception {
		MemoryCacheCenter.getInstance().clearAll();
		return portalEnvironmentDAO.updateEnvironment(vo);
    }

	public boolean deleteEnvironment(PortalEnvironmentVO vo) throws Exception {
		MemoryCacheCenter.getInstance().clearAll();
		return portalEnvironmentDAO.deleteEnvironment(vo);
    }

	public PortalEnvironmentVO getEnvironment(PortalEnvironmentVO vo) throws Exception {
		return portalEnvironmentDAO.getEnvironment(vo);
    }

	public Page getEnvironments(PortalEnvironmentVO vo) throws Exception {
		return portalEnvironmentDAO.getEnvironments(vo);
    }

	public PortalEnvironmentVO[] getAllEnvironments() throws Exception {
		return portalEnvironmentDAO.getAllEnvironments();
    }

}
