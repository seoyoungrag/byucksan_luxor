package com.sds.acube.luxor.common.service.impl;

import java.util.Iterator;
import java.util.Set;
import com.sds.acube.luxor.common.dao.MessageDAO;
import com.sds.acube.luxor.common.service.MessageService;
import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.vo.MessageVO;
import com.sds.acube.luxor.framework.cache.LuxorHashMap;
import com.sds.acube.luxor.framework.cache.MemoryCache;
import com.sds.acube.luxor.framework.cache.MemoryCacheCenter;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.service.BaseService;

public class MessageServiceImpl extends BaseService implements MessageService, MemoryCache {
	private static LuxorHashMap cache = new LuxorHashMap(Integer.parseInt(LuxorConfig.getString("Common", "CACHE.MAXIMUM_SIZE")));
	private MessageDAO messageDAO;
	
	public MessageServiceImpl() {
		MemoryCacheCenter.getInstance().register("MESSAGE_SERVICE", this);
	}
	
	public void setMessageDAO(MessageDAO messageDAO) {
    	this.messageDAO = messageDAO;
    }
	
	
	public String insertMessage(MessageVO vo) throws Exception {
		MessageVO tempVO = (MessageVO)vo.clone();
		String nameAll = vo.getMessageNameAll();
		if(CommonUtil.isNullOrEmpty(nameAll)) {
			throw new Exception("");
		}
		
		String messageId = CommonUtil.generateId("M");
		tempVO.setMessageId(messageId);
		
		String[] token = nameAll.split("\\|");
		for(String part:token) {
			if(CommonUtil.isNullOrEmpty(part)) {
				continue;
			}
			
			tempVO.setLangType(part.split("\\^")[0]);
			tempVO.setMessageName(part.split("\\^")[1]);
			
			messageDAO.insertMessage(tempVO);
		}

		return messageId;
	}
	
	
	public boolean updateMessage(MessageVO vo) throws Exception {
		MessageVO tempVO = (MessageVO)vo.clone();
		boolean result = false;
		String nameAll = tempVO.getMessageNameAll();
		if(CommonUtil.isNullOrEmpty(nameAll)) {
			throw new Exception("");
		}
		
		String[] token = nameAll.split("\\|");
		for(String part:token) {
			if(CommonUtil.isNullOrEmpty(part)) {
				continue;
			}
			
			tempVO.setLangType(part.split("\\^")[0]);
			tempVO.setMessageName(part.split("\\^")[1]);
			
			result = messageDAO.updateMessage(tempVO);
			
			// 이 경우는 기존에는 다국어 적용하지 않고 한국어로만 운영하다가
			// 운영중에 영문이 추가된 경우 기존에 한국어만 있는 다국어를 수정할때
			// 영문은 존재를 하지 않기때문에 신규로 추가해주는 부분임
			if(result==false) {
				tempVO.setMessageType("PORTAL.COMMON");
				result = messageDAO.insertMessage(tempVO);
			}
		}
		
		MemoryCacheCenter.getInstance().clear("MESSAGE_SERVICE");
		MemoryCacheCenter.getInstance().clear("MENU_SERVICE");
		
		return result;
	}
	
	
	public boolean deleteMessage(MessageVO vo) throws Exception {
		MemoryCacheCenter.getInstance().clear("MESSAGE_SERVICE");
		MemoryCacheCenter.getInstance().clear("MENU_SERVICE");
		
		return messageDAO.deleteMessage(vo);
	}

	public MessageVO[] getMessageById(MessageVO vo) throws Exception {
		String key = "getMessageById." + vo.getTenantId() + "." + vo.getPortalId() + "." + vo.getMessageId();
		MessageVO[] resultVO = (MessageVO[])cache.get(key);
		if(resultVO==null) {
			resultVO = messageDAO.getMessageById(vo);
			cache.put(key, resultVO);
		}
		return resultVO;
	}

	public MessageVO getMessageByIdLang(MessageVO vo) throws Exception {
		String key = "getMessageByIdLang." + vo.getTenantId() + "." + vo.getPortalId() + "." + vo.getMessageId() + "." + vo.getLangType();
		MessageVO resultVO = (MessageVO)cache.get(key);
		if(resultVO==null) {
			resultVO = messageDAO.getMessageByIdLang(vo);
			cache.put(key, resultVO);
		}
		return resultVO;
	}

	public boolean isDuplicate(MessageVO vo) throws Exception {
		return messageDAO.isDuplicate(vo) > 0;
	}

	
	public void clear() {
		logger.debug("Cache - MessageService.clear() called..!!"); 
		cache.clear();
	}
	
	public void remove(Object key) {
		logger.debug("Cache - MessageService.remove("+key+") called..!!");
		cache.remove(key);
	}
	
	public String toString() {
		String result = "";
		Set keySet = cache.keySet();
		Iterator it = keySet.iterator();
		while(it.hasNext()) {
			String key = it.next().toString();
			String value = cache.get(key).toString(); 
			
			result += key + " = " + value + "<br />";
		}
		return result;
	}

	public int size() {
	   return 0;
    }
}
