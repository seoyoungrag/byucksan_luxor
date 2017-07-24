package com.sds.acube.luxor.framework.cache;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.service.BaseService;


public class MemoryCacheCenter {
	private static MemoryCacheCenter instance = new MemoryCacheCenter();
	private Log logger = LogFactory.getLog(BaseService.class);
	private Map cacheTree = new Hashtable();

	private MemoryCacheCenter() {}

	public static MemoryCacheCenter getInstance() {
		return instance;
	}

	/**
	 * return cache keys
	 * 
	 * @param module
	 * @param memoryCache
	 */
	public Iterator getCacheKeys() {
		Iterator it = cacheTree.keySet().iterator();
		return it;
	}
	
	/**
	 * return cache keys
	 * 
	 * @param module
	 * @param memoryCache
	 */
	public int getCacheSize() {
		Iterator it = cacheTree.keySet().iterator();
		return cacheTree.size();
	}
	
	/**
	 * 캐쉬 적용할 모듈을 메모리 캐쉬에 등록한다
	 * 
	 * @param module
	 * @param memoryCache
	 */
	public void register(Object module, MemoryCache memoryCache) {
		List listenerList = (List) cacheTree.get(module);
		if (listenerList == null) {
			listenerList = new ArrayList();
			cacheTree.put(module, listenerList);
		}
		if (listenerList.contains(memoryCache) == false) {
			listenerList.add(memoryCache);
			if (logger.isDebugEnabled()) {
				logger.debug("Memory cache for [" + module.toString() + "] registered");
			}
		}
	}


	/**
	 * 메모리 캐쉬에서 모듈을 등록 해제한다
	 * 
	 * @param module
	 * @param memoryCache
	 */
	public void deregister(Object module, MemoryCache memoryCache) {
		List listenerList = (List) cacheTree.get(module);
		if (listenerList == null) {
			return;
		}
		listenerList.remove(memoryCache);
	}


	/**
	 * clear memory cache
	 * 
	 * @param module
	 * @return
	 */
	public int clear(Object module) {
		boolean callMonitor = "Y".equals(LuxorConfig.getString("CACHE.IS_MULTI_CONTAINER"));
		return clear(module, callMonitor);
	}


	/**
	 * clear memory cache
	 * 
	 * @param module
	 * @param callMonitor
	 * @return
	 */
	public int clear(Object module, boolean callMonitor) {
		List listenerList = (List) cacheTree.get(module);
		if (listenerList == null) {
			return 0;
		}
		int cleared;
		for (cleared = 0; cleared < listenerList.size(); cleared++) {
			MemoryCache memoryCache = (MemoryCache) listenerList.get(cleared);
			memoryCache.clear();
			logger.info("Memory cache - clear [" + module.toString() + "]");
		}
		if (callMonitor) {
			MemoryCacheMonitor.getInstance().updateSequence(module.toString(), "all");
		}
		return cleared;
	}

	
	/**
	 * Clear All cache
	 * @return
	 */
	public void clearAll() {
		Iterator it = cacheTree.keySet().iterator();
		while(it.hasNext()) {
			clear(it.next());
		}
	}

	
	/**
	 * 메모리 캐쉬에서 모듈의 key로 설정된 정보를 remove
	 * 
	 * @param module
	 * @param key
	 * @return
	 */
	public int remove(Object module, Object key) {
		boolean callMonitor = "Y".equals(LuxorConfig.getString("CACHE.IS_MULTI_CONTAINER"));
		return remove(module, key, callMonitor);
	}


	/**
	 * 메모리 캐쉬에서 모듈의 key로 설정된 정보를 remove
	 * 
	 * @param module
	 * @param key
	 * @param callMonitor
	 * @return
	 */
	public int remove(Object module, Object key, boolean callMonitor) {
		List list = (List) cacheTree.get(module);
		if (key == null) {
			return 0;
		}
		int listCount = list == null ? 0 : list.size();
		int cleared;
		for (cleared = 0; cleared < listCount; cleared++) {
			MemoryCache memoryCache = (MemoryCache) list.get(cleared);
			logger.info("Memory cache - remove key [" + module.toString() + "," + key + "]");
			memoryCache.remove(key);
		}
		if (callMonitor) {
			MemoryCacheMonitor.getInstance().updateSequence(module.toString(), key.toString());
		}
		return cleared;
	}
	
	public int size(Object module) {
		List list = (List) cacheTree.get(module);
		int listCount = list == null ? 0 : list.size();
		int size = 0;
		int cleared;
		for (cleared = 0; cleared < listCount; cleared++) {
			MemoryCache memoryCache = (MemoryCache) list.get(cleared);
			size += memoryCache.size();
		}
		return size;
	}
	
}
