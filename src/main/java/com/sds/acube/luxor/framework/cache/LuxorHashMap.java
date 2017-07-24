package com.sds.acube.luxor.framework.cache;

import org.apache.commons.collections.map.LRUMap;
import com.sds.acube.luxor.framework.config.LuxorConfig;

/**
 * 해쉬맵을 상속받아서 설정에서 캐시사용을 N로 했을 경우에는 null을 리턴하게 처리
 *  
 * @author ole2000
 * 
 */
public class LuxorHashMap extends LRUMap {
    private static final long serialVersionUID = -4577945333153360143L;
    private final String useCache = LuxorConfig.getString("CACHE.USE");
    
	public LuxorHashMap(int initialCapacity) {
	    super(initialCapacity);
    }
	
	public LuxorHashMap() {
	    super();
    }

	public Object get(Object key) {
		if("Y".equals(useCache)) {
			return super.get(key);
		} else {
			return null;
		}
	}
	
	public Object put(Object key, Object value) {
		if("Y".equals(useCache) && value != null) {
			return super.put(key, value);
		} else {
			return null;
		}
	}
}
