package com.sds.acube.luxor.sample.service.impl;

import org.anyframe.pagination.Page;
import com.sds.acube.luxor.common.service.MessageService;
import com.sds.acube.luxor.framework.service.BaseService;
import com.sds.acube.luxor.sample.dao.SampleDAO;
import com.sds.acube.luxor.sample.service.SampleService;
import com.sds.acube.luxor.sample.vo.SampleVO;

public class SampleServiceImpl extends BaseService implements SampleService {
	private MessageService messageService;
	private SampleDAO sampleDAO;
	
	public void setSampleDAO(SampleDAO sampleDAO) {
    	this.sampleDAO = sampleDAO;
    }
	
	public void setMessageService(MessageService messageService) {
    	this.messageService = messageService;
    }

	public boolean insertSample(SampleVO vo) throws Exception {
		String messageId = messageService.insertMessage(vo);
		vo.setNameId(messageId);
		return sampleDAO.insertSample(vo);
	}
	
	public boolean updateSample(SampleVO vo) throws Exception {
		return sampleDAO.updateSample(vo);
	}
	
	public boolean deleteSample(SampleVO vo) throws Exception {
		return sampleDAO.deleteSample(vo);
	}
	
	public SampleVO getSample(SampleVO vo) throws Exception {
		return sampleDAO.getSample(vo);
	}
	
	public Page getSampleListPage(SampleVO vo) throws Exception {
		return sampleDAO.getSampleListPage(vo);
	}
	
}
