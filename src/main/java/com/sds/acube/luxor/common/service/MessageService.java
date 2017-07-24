package com.sds.acube.luxor.common.service;

import javax.jws.WebService;
import com.sds.acube.luxor.common.vo.MessageVO;

@WebService
public interface MessageService {
	
	public String insertMessage(MessageVO vo) throws Exception;
	
	public boolean updateMessage(MessageVO vo) throws Exception;
	
	public boolean deleteMessage(MessageVO vo) throws Exception;
	
	/**
	 * Message ID 기반으로 메세지 조회
	 * 셋팅된 language set (한,영,등등) 만큼 배열로 리턴
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public MessageVO[] getMessageById(MessageVO vo) throws Exception;
	
	
	/**
	 * Message ID + LangType 기반으로 메세지 조회
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public MessageVO getMessageByIdLang(MessageVO vo) throws Exception;
	
	
	/**
	 * 중복된 데이터가 있는 경우 true를 리턴 otherwise return false
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean isDuplicate(MessageVO vo) throws Exception;
	
	
	public String toString();
	
}
