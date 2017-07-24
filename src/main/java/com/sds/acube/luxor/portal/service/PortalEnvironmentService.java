package com.sds.acube.luxor.portal.service;

import org.anyframe.pagination.Page;
import com.sds.acube.luxor.portal.vo.PortalEnvironmentVO;


public interface PortalEnvironmentService {
	
	public boolean insertEnvironment(PortalEnvironmentVO vo) throws Exception;

	public boolean updateEnvironment(PortalEnvironmentVO vo) throws Exception;

	public boolean deleteEnvironment(PortalEnvironmentVO vo) throws Exception;
	
	public PortalEnvironmentVO getEnvironment(PortalEnvironmentVO vo) throws Exception;
	
	public Page getEnvironments(PortalEnvironmentVO vo) throws Exception;

	public PortalEnvironmentVO[] getAllEnvironments() throws Exception;

}
