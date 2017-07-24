package com.sds.acube.luxor.portal.service.impl;

import java.util.List;
import javax.jws.WebMethod;
import org.anyframe.pagination.Page;
import com.sds.acube.luxor.common.service.MessageService;
import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.vo.MessageVO;
import com.sds.acube.luxor.common.vo.TreeVO;
import com.sds.acube.luxor.framework.service.BaseService;
import com.sds.acube.luxor.portal.dao.PortalContentDAO;
import com.sds.acube.luxor.portal.service.PortalContentService;
import com.sds.acube.luxor.portal.service.PortalPageZoneService;
import com.sds.acube.luxor.portal.vo.PortalContentVO;
import com.sds.acube.luxor.portal.vo.PortalPageZoneVO;

public class PortalContentServiceImpl extends BaseService implements PortalContentService {
	private MessageService messageService;
	private PortalPageZoneService portalPageZoneService;
	private PortalContentDAO portalContentDAO;
	
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	public void setPortalContentDAO(PortalContentDAO portalContentDAO) {
    	this.portalContentDAO = portalContentDAO;
    }
	public void setPortalPageZoneService(PortalPageZoneService portalPageZoneService) {
    	this.portalPageZoneService = portalPageZoneService;
    }

	
	@WebMethod(exclude=true)
	public boolean insertContent(PortalContentVO vo) throws Exception {
		// 다국어 셋팅
		String contentNameId = messageService.insertMessage(vo);
		if(vo.getContentId() == null){
			vo.setContentId(CommonUtil.generateId("C"));
		}
		vo.setContentNameId(contentNameId);
		
		return portalContentDAO.insertContent(vo);
	}
	
	
	@WebMethod(exclude=true)
	public boolean updateContent(PortalContentVO vo) throws Exception {
		return messageService.updateMessage(vo) && portalContentDAO.updateContent(vo);
	}

	
	@WebMethod(exclude=true)
	public boolean updateContentParentId(PortalContentVO vo) throws Exception {
		return portalContentDAO.updateContentParentId(vo);
	}

	
	/**
	 * 컨텐츠 삭제시 페이지에 추가되어 사용된는 컨텐츠도 같이 삭제
	 */
	@WebMethod(exclude=true)
	public boolean deleteContent(PortalContentVO vo) throws Exception {
		PortalPageZoneVO paramVO = new PortalPageZoneVO();
		paramVO.setContentId(vo.getContentId());
		portalPageZoneService.deleteContentOnZone(paramVO);
		return portalContentDAO.deleteContent(vo);
	}
	
	
	public PortalContentVO getContent(PortalContentVO vo) throws Exception {
		PortalContentVO content = portalContentDAO.getContent(vo);

		content.setMessageId(content.getContentNameId());
		content.setLangType(vo.getLangType());
		
		MessageVO messageVO = messageService.getMessageByIdLang(content);
		
		if(messageVO!=null) {
			content.setMessageName(messageVO.getMessageName());
		}
		return content;
	}
	
	public List getContentList(PortalContentVO vo) throws Exception {
		return portalContentDAO.getContentList(vo);
	}

	public Page getContentListPage(PortalContentVO vo) throws Exception {
		return portalContentDAO.getContentListPage(vo);
	}
	
	@WebMethod(exclude=true)
	public boolean setCategoryUsePersonal(TreeVO vo) throws Exception {
		return portalContentDAO.setCategoryUsePersonal(vo);
	}
	
	@WebMethod(exclude=true)
	public boolean setContentUsePersonal(PortalContentVO vo) throws Exception {
		return portalContentDAO.setContentUsePersonal(vo);
	}
	
	public List<TreeVO> getPersonalCatalog(TreeVO vo) throws Exception {
		return portalContentDAO.getPersonalCatalog(vo);
	}
}
