package com.sds.acube.luxor.framework.service.impl;

import java.util.List;
import com.sds.acube.luxor.framework.dao.CacheDAO;
import com.sds.acube.luxor.framework.service.BaseService;
import com.sds.acube.luxor.framework.service.CacheService;
import com.sds.acube.luxor.framework.vo.CacheVO;

public class CacheServiceImpl extends BaseService implements CacheService {
	private CacheDAO cacheDAO;
	
	public void setCacheDAO(CacheDAO cacheDAO) {
    	this.cacheDAO = cacheDAO;
    }

	public List getCacheList() throws Exception {
		return cacheDAO.getCacheList();
	}
	
	public int updateSeq(String moduleId, String moduleKey) throws Exception {
		int newSeq = -1;
		boolean isExists = true;
		
		if (moduleKey == null || moduleKey.length() == 0) {
			moduleKey = "all";
		}

		CacheVO cacheVO = new CacheVO();
		cacheVO.setModuleId(moduleId);
		cacheVO.setModuleKey(moduleKey);
		
		// get exist cache
		CacheVO resultVO = cacheDAO.getCache(cacheVO);
		
		if(resultVO==null) {
			newSeq = 1;
			isExists = false;
		} else {
			newSeq = resultVO.getUpdatedTime() + 1;
			if (newSeq > 99999999) {
				newSeq = 1;
			}
		}
		
		// set new seq
		cacheVO.setUpdatedTime(newSeq);
		
		// Update
		if (isExists) {
			cacheDAO.updateCache(cacheVO);
		}
		// Insert
		else {
			cacheDAO.insertCache(cacheVO);
		}

		return newSeq;
	}
}
