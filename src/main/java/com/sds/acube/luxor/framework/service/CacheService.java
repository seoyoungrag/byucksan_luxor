package com.sds.acube.luxor.framework.service;

import java.util.List;

public interface CacheService {
	public List getCacheList() throws Exception;
	
	public int updateSeq(String moduleId, String moduleKey) throws Exception;
}
