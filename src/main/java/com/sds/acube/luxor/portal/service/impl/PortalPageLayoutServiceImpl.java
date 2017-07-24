package com.sds.acube.luxor.portal.service.impl;

import javax.jws.WebMethod;

import org.anyframe.pagination.Page;

import com.sds.acube.luxor.common.service.MessageService;
import com.sds.acube.luxor.framework.service.BaseService;
import com.sds.acube.luxor.portal.dao.PortalPageLayoutDAO;
import com.sds.acube.luxor.portal.service.GroupPortalService;
import com.sds.acube.luxor.portal.service.PortalPageLayoutService;
import com.sds.acube.luxor.portal.vo.GroupPortalVO;
import com.sds.acube.luxor.portal.vo.PortalPageLayoutVO;

public class PortalPageLayoutServiceImpl extends BaseService implements PortalPageLayoutService {
	private MessageService messageService;
	private GroupPortalService groupPortalService;
	private PortalPageLayoutDAO portalPageLayoutDAO;
	
	public void setGroupPortalService(GroupPortalService groupPortalService) {
		this.groupPortalService = groupPortalService;
	}
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	public void setPortalPageLayoutDAO(PortalPageLayoutDAO portalPageLayoutDAO) {
    	this.portalPageLayoutDAO = portalPageLayoutDAO;
    }

	
	@WebMethod(exclude=true)
	public boolean insertPageLayout(PortalPageLayoutVO vo) throws Exception {
		// 다국어 셋팅
		String layoutNameId = messageService.insertMessage(vo);
		vo.setLayoutNameId(layoutNameId);
		
		return portalPageLayoutDAO.insertPageLayout(vo);
	}

	
	@WebMethod(exclude=true)
	public boolean updatePageLayout(PortalPageLayoutVO vo) throws Exception {
		return messageService.updateMessage(vo) && portalPageLayoutDAO.updatePageLayout(vo);
	}

	
	/**
	 * 기존에 기본으로 선택된 레이아웃을 리셋시키고 새로 기본으로 설정함 
	 */
	@WebMethod(exclude=true)
	public boolean setDefaultPageLayout(PortalPageLayoutVO vo) throws Exception {
		portalPageLayoutDAO.resetDefaultPageLayout(vo);
		return portalPageLayoutDAO.updatePageLayout(vo);
	}

	
	@WebMethod(exclude=true)
	public boolean deletePageLayout(PortalPageLayoutVO vo) throws Exception {
		return messageService.deleteMessage(vo) && portalPageLayoutDAO.deletePageLayout(vo);
	}
	
	public PortalPageLayoutVO getPageLayout(PortalPageLayoutVO vo) throws Exception {
		GroupPortalVO parentPortalVO = new GroupPortalVO();
		parentPortalVO.setTenantId(vo.getTenantId());
		parentPortalVO.setPortalId(vo.getPortalId());
		
		PortalPageLayoutVO resultVO = new PortalPageLayoutVO();
		resultVO = portalPageLayoutDAO.getPageLayout(vo);
		if(resultVO == null) {
			//부모그룹이 있는지 체크하여 있으면, 무모포탈의 레이아웃도 찾아봄
			for(int i = 0; true ; i++ ) {
				parentPortalVO = groupPortalService.getParentPortal(parentPortalVO);
				if(parentPortalVO == null) {
					break;
				} else {
					if(parentPortalVO.getParentId().equals("_ROOT_")) {
						vo.setTenantId(parentPortalVO.getTenantId());
						vo.setPortalId(parentPortalVO.getPortalId());
						resultVO = portalPageLayoutDAO.getPageLayout(vo);
						break;
					} else {
						vo.setTenantId(parentPortalVO.getTenantId());
						vo.setPortalId(parentPortalVO.getPortalId());
						resultVO = portalPageLayoutDAO.getPageLayout(vo);
						if(resultVO != null) {
							break;
						}
					}
				}
			}
		}
		return resultVO;
	}
	
	public Page getPageLayoutList(PortalPageLayoutVO vo) throws Exception {
		return portalPageLayoutDAO.getPageLayoutList(vo);
	}

}
