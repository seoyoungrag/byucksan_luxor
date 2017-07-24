package com.sds.acube.luxor.portal.dao;

import java.util.List;
import org.anyframe.pagination.Page;
import com.sds.acube.luxor.framework.dao.BaseDAO;
import com.sds.acube.luxor.portal.vo.PortalEnvironmentVO;

public class PortalEnvironmentDAO extends BaseDAO {
	
	public boolean insertEnvironment(PortalEnvironmentVO vo) throws Exception {
		return insert("insertEnvironment", vo) > 0;
	}

	public boolean updateEnvironment(PortalEnvironmentVO vo) throws Exception {
		return update("updateEnvironment", vo) > 0;
	}

	public boolean deleteEnvironment(PortalEnvironmentVO vo) throws Exception {
		return delete("deleteEnvironment", vo) > 0;
	}
	
	public PortalEnvironmentVO getEnvironment(PortalEnvironmentVO vo) throws Exception {
		return (PortalEnvironmentVO)select("getEnvironment", vo);
	}

	public Page getEnvironments(PortalEnvironmentVO vo) throws Exception {
		return selectListPage("getEnvironments", vo);
	}

	public PortalEnvironmentVO[] getAllEnvironments() throws Exception {
		List envList = selectList("getAllEnvironments", null);
		PortalEnvironmentVO[] result = new PortalEnvironmentVO[envList.size()];
		envList.toArray(result);
		return result;
	}

}
