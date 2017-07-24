package com.sds.acube.luxor.common.dao;

import java.util.ArrayList;

import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.vo.MessageVO;
import com.sds.acube.luxor.framework.dao.BaseDAO;

public class MessageDAO extends BaseDAO {
	
	public boolean insertMessage(MessageVO vo) throws Exception {
		if(vo.getMessageName().contains("&")) {
			vo.setMessageName(vo.getMessageName().replaceAll("amp;", ""));
		}
		logger.debug("MessageVO : "+vo.toString());
		return queryService.create("insertMessage", new Object[]{new Object[]{"vo", vo}}) > 0;
	}
	
	public boolean updateMessage(MessageVO vo) throws Exception {
		vo.setMessageName(vo.getMessageName().replaceAll("amp;", ""));
		logger.debug("MessageVO : "+vo.toString());
		return queryService.update("updateMessage", new Object[]{new Object[]{"vo", vo}}) > 0;
	}

	public boolean deleteMessage(MessageVO vo) throws Exception {
		logger.debug("MessageVO : "+vo.toString());
		return queryService.remove("deleteMessage", new Object[]{new Object[]{"vo", vo}}) > 0;
	}
	
	public MessageVO[] getMessageById(MessageVO vo) throws Exception {
		logger.debug("MessageVO : "+vo.toString());
		vo.setPortalId(CommonUtil.getEscapeString(vo.getPortalId(), dbType));
		ArrayList result = (ArrayList)queryService.find("getMessageById", new Object[] {new Object[] {"vo",vo}});
		MessageVO[] resultVO = new MessageVO[result.size()];
		result.toArray(resultVO);
		vo.setPortalId(CommonUtil.getResetEscapeString(vo.getPortalId()));

		return resultVO;
	}

	public MessageVO getMessageByIdLang(MessageVO vo) throws Exception {
		logger.debug("MessageVO : "+vo.toString());
		vo.setPortalId(CommonUtil.getEscapeString(vo.getPortalId(), dbType));
		ArrayList result = (ArrayList)queryService.find("getMessageByIdLang", new Object[] {new Object[] {"vo",vo}});
		vo.setPortalId(CommonUtil.getResetEscapeString(vo.getPortalId()));

		if(result.size() > 0) {
			return (MessageVO) result.get(0);
		} else {
			return null;
		}
	}

	public int isDuplicate(MessageVO vo) throws Exception {
		logger.debug("MessageVO : "+vo.toString());
		int count = 0;
		vo.setPortalId(CommonUtil.getEscapeString(vo.getPortalId(), dbType));
		count =getCount("isDuplicate", "cnt", vo);
		vo.setPortalId(CommonUtil.getResetEscapeString(vo.getPortalId()));
		
		return count;
	}
	
}
