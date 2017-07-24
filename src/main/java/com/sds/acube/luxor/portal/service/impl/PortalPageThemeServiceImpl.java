package com.sds.acube.luxor.portal.service.impl;

import javax.jws.WebMethod;
import org.anyframe.pagination.Page;
import com.sds.acube.luxor.common.service.MessageService;
import com.sds.acube.luxor.framework.service.BaseService;
import com.sds.acube.luxor.portal.dao.PortalPageThemeDAO;
import com.sds.acube.luxor.portal.service.GroupPortalService;
import com.sds.acube.luxor.portal.service.PortalPageThemeService;
import com.sds.acube.luxor.portal.vo.GroupPortalVO;
import com.sds.acube.luxor.portal.vo.PortalPageThemeVO;

public class PortalPageThemeServiceImpl extends BaseService implements PortalPageThemeService {
	private MessageService messageService;
	private GroupPortalService groupPortalService;
	private PortalPageThemeDAO portalPageThemeDAO;
	
	
	public void setGroupPortalService(GroupPortalService groupPortalService) {
		this.groupPortalService = groupPortalService;
	}
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	public void setPortalPageThemeDAO(PortalPageThemeDAO portalPageThemeDAO) {
    	this.portalPageThemeDAO = portalPageThemeDAO;
    }

	
	@WebMethod(exclude=true)
	public boolean insertPageTheme(PortalPageThemeVO vo) throws Exception {
		// 다국어 셋팅
		String themeNameId = messageService.insertMessage(vo);
		vo.setThemeNameId(themeNameId);
		
		return portalPageThemeDAO.insertPageTheme(vo);
	}

	
	@WebMethod(exclude=true)
	public boolean updatePageTheme(PortalPageThemeVO vo) throws Exception {
		return messageService.updateMessage(vo) && portalPageThemeDAO.updatePageTheme(vo);
	}

	
	/**
	 * 기존에 기본으로 선택된 레이아웃을 리셋시키고 새로 기본으로 설정함 
	 */
	@WebMethod(exclude=true)
	public boolean setDefaultPageTheme(PortalPageThemeVO vo) throws Exception {
		return portalPageThemeDAO.resetDefaultPageTheme(vo) && portalPageThemeDAO.updatePageTheme(vo);
	}

	
	@WebMethod(exclude=true)
	public boolean deletePageTheme(PortalPageThemeVO vo) throws Exception {
		return messageService.deleteMessage(vo) && portalPageThemeDAO.deletePageTheme(vo);
	}
	
	
	public PortalPageThemeVO getPageTheme(PortalPageThemeVO vo) throws Exception {
		GroupPortalVO parentPortalVO = new GroupPortalVO();
		parentPortalVO.setTenantId(vo.getTenantId());
		parentPortalVO.setPortalId(vo.getPortalId());
		
		PortalPageThemeVO resultVO = new PortalPageThemeVO();
		resultVO = portalPageThemeDAO.getPageTheme(vo);
		if(resultVO == null) {
			//부모그룹이 있는지 체크하여 있으면, 부모 포탈 페이지 테마도 찾아봄
			for(int i = 0; true ; i++ ) {
				parentPortalVO = groupPortalService.getParentPortal(parentPortalVO);
				if(parentPortalVO == null) {
					break;
				} else {
					if(parentPortalVO.getParentId().equals("_ROOT_")) {
						vo.setTenantId(parentPortalVO.getTenantId());
						vo.setPortalId(parentPortalVO.getPortalId());
						resultVO = portalPageThemeDAO.getPageTheme(vo);
						break;
					} else {
						vo.setTenantId(parentPortalVO.getTenantId());
						vo.setPortalId(parentPortalVO.getPortalId());
						resultVO = portalPageThemeDAO.getPageTheme(vo);
						if(resultVO != null) {
							break;
						}
					}
				}
			}
		}
		return resultVO;
	}
	
	
	public Page getPageThemeList(PortalPageThemeVO vo) throws Exception {
		return portalPageThemeDAO.getPageThemeList(vo);
	}

}
