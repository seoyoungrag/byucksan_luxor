package com.sds.acube.luxor.framework.dao;

import java.util.List;
import com.sds.acube.luxor.framework.vo.CacheVO;

public class CacheDAO extends BaseDAO {
	public List getCacheList() throws Exception {
		return selectList("getCacheList", new CacheVO());
	}

	public CacheVO getCache(CacheVO vo) throws Exception {
		return (CacheVO)select("getCache", vo);
	}
	
	public boolean insertCache(CacheVO vo) throws Exception {
		return insert("insertCache", vo) > 0;
	}

	public boolean updateCache(CacheVO vo) throws Exception {
		return update("updateCache", vo) > 0;
	}
	
}
