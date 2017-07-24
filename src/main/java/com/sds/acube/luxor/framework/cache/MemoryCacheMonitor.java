package com.sds.acube.luxor.framework.cache;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sds.acube.luxor.framework.service.BaseService;
import com.sds.acube.luxor.framework.service.CacheService;
import com.sds.acube.luxor.framework.vo.CacheVO;


public class MemoryCacheMonitor extends Thread {
	private Log logger = LogFactory.getLog(BaseService.class);
	private static MemoryCacheMonitor instance = new MemoryCacheMonitor();
	private int checkSec = 30;
	private Map seqPool = new Hashtable();
	private static boolean blinker = true;
	private CacheService cacheService; 

    
    public void stopThread() {
    	logger.info("MemoryCacheMonitor Stop Called!!!");
        blinker = false;
    }

	private MemoryCacheMonitor() {
	}

	public void init() {
		logger.info("MemoryCacheMonitor inititialize..");
		try {
	        List list = cacheService.getCacheList();
	        
	        for(int i=0; i < list.size(); i++) {
	        	CacheVO vo = (CacheVO) list.get(i);
	        	String poolKey = vo.getModuleId() + "," + vo.getModuleKey();
	        	Integer dbSeq = new Integer(vo.getUpdatedTime());
	        	seqPool.put(poolKey, dbSeq);
	        }
        } catch (Exception e) {
	        e.printStackTrace();
        }
	}
	
	public void setCacheService(CacheService cacheService) {
    	this.cacheService = cacheService;
    	init();
    }

	public static MemoryCacheMonitor getInstance() {
		return instance;
	}

	public void run() {
		logger.info("MemoryCacheMonitor Stop Called!!!");
		int interval = 0;
		while (blinker) {
			try {
				if(interval > checkSec*1000) {
					validateCache();
					interval = 0;
				}
				
				interval++;
				Thread.sleep(1);
			} catch (InterruptedException e) {
				stopThread();
			}
		}
	}


	private void validateCache() {
		logger.info("validating Cache!!!");
		try {
	        boolean callMonitor = false;
	        
	        List list = cacheService.getCacheList();
	        
	        for(int i=0; i < list.size(); i++) {
	        	CacheVO vo = (CacheVO) list.get(i);
	        	String poolKey = vo.getModuleId() + "," + vo.getModuleKey();
	        	Integer dbSeq = new Integer(vo.getUpdatedTime());
	        	Integer memorySeq = (Integer) seqPool.get(poolKey);
	        	
	        	logger.debug("Cache :: poolKey : " +poolKey+ " [memorySeq : "+memorySeq+", dbSeq : "+dbSeq+"]");
	        	
	        	if (memorySeq == null) {
	        		seqPool.put(poolKey, dbSeq);
	        		MemoryCacheCenter.getInstance().clear(vo.getModuleId(), callMonitor);
	        		continue;
	        	}
	        	if (memorySeq.intValue() == dbSeq.intValue()) {
	        		continue;
	        	}
	        	if ("all".equals(vo.getModuleKey())) {
	        		logger.debug(poolKey + " data updated. clearing all cache.");
	        		MemoryCacheCenter.getInstance().clear(vo.getModuleId(), callMonitor);
	        	} else {
	        		logger.debug(poolKey + " data updated. removing cache by key.");
	        		MemoryCacheCenter.getInstance().remove(vo.getModuleId(), vo.getModuleKey(), callMonitor);
	        	}
	        	seqPool.put(poolKey, dbSeq);
	        }
        } catch (Exception e) {
	        e.printStackTrace();
        }
	}


	/**
	 * 다른 WAS에서 Cache를 지울 수 있도록 기록한다
	 * 
	 * @param moduleID
	 * @param moduleKey
	 */
	public synchronized void updateSequence(String moduleID, String moduleKey) {
		try {
	        // 다른 WAS에서 CACHE를 지울 수 있도록 DB에 기록한다.
	        int newSeq = cacheService.updateSeq(moduleID, moduleKey);
	        String mapKey = moduleID + "," + moduleKey;
	        // MemoryCacheCenter에서 이미 CACHE가 삭제되었으므로 다시 삭제하지 않도록 저장한다.
	        seqPool.put(mapKey, new Integer(newSeq));
        } catch (Exception e) {
	        e.printStackTrace();
        }
	}


	public int getCheckSec() {
		return checkSec;
	}


	public void setCheckSec(int checkSec) {
		this.checkSec = checkSec;
	}
}
