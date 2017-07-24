package com.sds.acube.luxor.portal.service.impl;

import java.util.List;
import javax.jws.WebMethod;
import javax.servlet.http.HttpSession;

import org.anyframe.pagination.Page;
import com.sds.acube.luxor.common.service.MessageService;
import com.sds.acube.luxor.common.service.TreeService;
import com.sds.acube.luxor.common.vo.CommonVO;
import com.sds.acube.luxor.common.vo.MessageVO;
import com.sds.acube.luxor.common.vo.TreeVO;
import com.sds.acube.luxor.common.vo.UserProfileVO;
import com.sds.acube.luxor.framework.cache.MemoryCacheCenter;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.service.BaseService;
import com.sds.acube.luxor.portal.dao.PortalPageDAO;
import com.sds.acube.luxor.portal.service.GroupPortalService;
import com.sds.acube.luxor.portal.service.PortalMenuService;
import com.sds.acube.luxor.portal.service.PortalPageService;
import com.sds.acube.luxor.portal.service.PortalPageZoneService;
import com.sds.acube.luxor.portal.vo.GroupPortalVO;
import com.sds.acube.luxor.portal.vo.PortalMenuVO;
import com.sds.acube.luxor.portal.vo.PortalPageVO;
import com.sds.acube.luxor.portal.vo.PortalPageZoneVO;
import com.sds.acube.luxor.ws.client.aclservice.AclProvider4WS;

public class PortalPageServiceImpl extends BaseService implements PortalPageService {
	private TreeService treeService;
	private MessageService messageService;
	private PortalMenuService portalMenuService;
	private PortalPageDAO portalPageDAO;
	private PortalPageZoneService portalPageZoneService;
	private GroupPortalService groupPortalService;
	private AclProvider4WS wsClientAclProviderService;
	private String serviceKey;
	
	public void setTreeService(TreeService treeService) {
    	this.treeService = treeService;
    }
	public void setMessageService(MessageService messageService) {
    	this.messageService = messageService;
    }
	public void setPortalMenuService(PortalMenuService portalMenuService) {
    	this.portalMenuService = portalMenuService;
    }
	public void setPortalPageDAO(PortalPageDAO portalPageDAO) {
    	this.portalPageDAO = portalPageDAO;
    }
	public void setPortalPageZoneService(PortalPageZoneService portalPageZoneService) {
    	this.portalPageZoneService = portalPageZoneService;
    }
	public void setGroupPortalService(GroupPortalService groupPortalService) {
		this.groupPortalService = groupPortalService;
	}
	public void setWsClientAclProviderService(
			AclProvider4WS wsClientAclProviderService) {
		serviceKey = LuxorConfig.getString("IAM.KEY");
		this.wsClientAclProviderService = wsClientAclProviderService;
	}
	
	@WebMethod(exclude=true)
	public boolean insertPage(PortalPageVO vo) throws Exception {
		return insertPageWithDefaultAcl(vo,"B2C",null);
	}
	
	@WebMethod(exclude=true)
	public boolean insertPageWithDefaultAcl(PortalPageVO vo, String defaultAcl, HttpSession session) throws Exception {
		boolean result = false;
		try {
			// 최초로 등록되는 페이지가 홈페이지가 됨
			if(getTotalPageCount(vo)==0) {
				logger.info("Initial page registered!! (This will be registered as home)");
				result = portalPageDAO.insertPage(vo) && setHome(vo);
			} else {
				result = portalPageDAO.insertPage(vo);
			}
			
			if(defaultAcl.equals("B2B")) {
				MemoryCacheCenter.getInstance().clearAll();
				result = wsClientAclProviderService.save(serviceKey, vo.getPageId(), "GUEST|GUEST|3||");
				
			} else if(defaultAcl.equals("B2B_STRICT")) {
				String accessInfos = "";
				MemoryCacheCenter.getInstance().clearAll();
				accessInfos += "GUEST|GUEST|3||^COMMON|COMMON|3||";
				GroupPortalVO portalVO = new GroupPortalVO();
				portalVO.setTenantId(vo.getTenantId());
				
				List<GroupPortalVO> portalList = groupPortalService.getList(portalVO);
				UserProfileVO userProfile = (UserProfileVO)session.getAttribute("userProfile");
				
				//연결조직 아이디가 부서로 매핑되어있는 포탈을 찾아서 세팅
	        	for(int j=0; j < portalList.size(); j++) {
	        		if(userProfile.getDeptId().equals(portalList.get(j).getRelatedCompid())) {
	        			accessInfos = portalList.get(j).getPortalId()+"|"+portalList.get(j).getPortalName()+"|2|RW|^"+accessInfos;
	        			result = wsClientAclProviderService.save(serviceKey, vo.getPageId(), accessInfos);
			        	return result;
	        		}
		      	}
	        	
				//연결조직 아이디가 회사로 매핑되있는 포탈을 찾아서 세팅
	        	for(int i=0; i < portalList.size(); i++) {
	        		if(userProfile.getCompId().equals(portalList.get(i).getRelatedCompid())) {
	        			accessInfos = portalList.get(i).getPortalId()+"|"+portalList.get(i).getPortalName()+"|2|RW|^"+accessInfos;
	        			result = wsClientAclProviderService.save(serviceKey, vo.getPageId(), accessInfos);
	        			return result;
	        		}
		      	}
			}
		} catch(Exception e) {
			throw e;
		}
		return result;
	}
	
	@WebMethod(exclude=true)
	public boolean updatePage(PortalPageVO vo) throws Exception {
		return portalPageDAO.updatePage(vo);
	}

	
	@WebMethod(exclude=true)
	public boolean setHome(PortalPageVO vo) throws Exception {
		return portalPageDAO.setHome(vo);
	}

	
	@WebMethod(exclude=true)
	public boolean setPersonalize(PortalPageVO vo) throws Exception {
		return portalPageDAO.setPersonalize(vo);
	}

	@WebMethod(exclude=true)
	public boolean setTemplate(PortalPageVO vo) throws Exception {
		return portalPageDAO.setTemplate(vo);
	}
	
	
	/**
	 * 페이지 복사
	 * fromPageId의 모든 정보 (테마, 레이아웃, 컨텐츠)를 toPageId로 복사
	 */
	@WebMethod(exclude=true)
	public boolean copyPage(PortalPageVO vo, String fromPageId, String toPageId) throws Exception {
		PortalPageVO fromPageVO = new PortalPageVO();
		PortalPageVO toPageVO = new PortalPageVO();

		fromPageVO.setTenantId(vo.getTenantId());
		fromPageVO.setPortalId(vo.getPortalId());
		fromPageVO.setCompId(vo.getCompId());
		fromPageVO.setAccessIdList(vo.getAccessIdList());
		fromPageVO.setPageId(fromPageId);
		fromPageVO.setLangType(vo.getLangType());

		toPageVO.setTenantId(vo.getTenantId());
		toPageVO.setPortalId(vo.getPortalId());
		toPageVO.setLangType(vo.getLangType());
		toPageVO.setPageId(toPageId);
		
		// 복사할 페이지 정보 가져옴
		fromPageVO = portalPageDAO.getPage(fromPageVO);
		
		// 테마, 레이아웃정보 셋팅
		toPageVO.setPageLayoutId(fromPageVO.getPageLayoutId());
		toPageVO.setPageThemeId(fromPageVO.getPageThemeId());
		
		// 복사
		boolean result = portalPageDAO.updatePage(toPageVO);

		//-----------------------------------------------------
		// 컨텐츠 복사 - START
		//-----------------------------------------------------
		// 복사할 페이지에 포함된 컨텐츠 정보 가져옴
		PortalPageZoneVO ppzVO = new PortalPageZoneVO();
		ppzVO.setTenantId(vo.getTenantId());
		ppzVO.setPortalId(vo.getPortalId());
		ppzVO.setCompId(vo.getCompId());
		ppzVO.setAccessIdList(vo.getAccessIdList());
		ppzVO.setPageId(fromPageId);
		ppzVO.setLangType(vo.getLangType());
		
		List tempList = portalPageZoneService.getContentsOnPage(ppzVO);
		
		PortalPageZoneVO[] contents = new PortalPageZoneVO[tempList.size()];
		tempList.toArray(contents);

		// 기존에 있는건 먼저 삭제
		PortalPageZoneVO delPZVO = new PortalPageZoneVO();
		delPZVO.setTenantId(vo.getTenantId());
		delPZVO.setPortalId(vo.getPortalId());
		delPZVO.setPageId(toPageId);
		delPZVO.setLangType(vo.getLangType());
		portalPageZoneService.deleteAllContentOnPage(delPZVO);
		
		// 복사
		for(PortalPageZoneVO content : contents) {
			content.setPageId(toPageId);
			portalPageZoneService.insertContentOnZone(content);
		}
		//-----------------------------------------------------
		// 컨텐츠 복사 - END
		//-----------------------------------------------------
		
		//-----------------------------------------------------
		// 메뉴 복사 - START
		//-----------------------------------------------------
		// 기존에 등록된 메뉴는 삭제
		PortalMenuVO portalMenuVO = new PortalMenuVO();
		portalMenuVO.setTenantId(vo.getTenantId());
		portalMenuVO.setPortalId(vo.getPortalId());
		portalMenuVO.setPageId(toPageId);
		
		portalMenuService.deleteMenusOnPage(portalMenuVO);
		
		// 가져올 페이지에 등록된 메뉴 조회
		portalMenuVO.setPageId(fromPageId);
		portalMenuVO.setAccessIdList(vo.getAccessIdList());
		List srcMenuList = portalMenuService.getMenusOnPage(portalMenuVO);
		
		PortalMenuVO[] srcMenus = new PortalMenuVO[srcMenuList.size()];
		srcMenuList.toArray(srcMenus);

		// 복사
		for(PortalMenuVO srcMenu : srcMenus) {
			srcMenu.setPageId(toPageId);
			portalMenuService.insertMenu(srcMenu);
		}
		//-----------------------------------------------------
		// 메뉴 복사 - END
		//-----------------------------------------------------
		
		return result;
	}


	@WebMethod(exclude=true)
	public boolean deletePage(PortalPageVO vo) throws Exception {
		// 메세지 삭제
		MessageVO messageVO = new MessageVO();
		messageVO.setTenantId(vo.getTenantId());
		messageVO.setPortalId(vo.getPortalId());
		messageVO.setMessageId(vo.getMessageId());
		messageService.deleteMessage(messageVO);
		
		// 페이지에 추가되어있는 컨텐츠들은 삭제
		PortalPageZoneVO delPZVO = new PortalPageZoneVO();
		delPZVO.setTenantId(vo.getTenantId());
		delPZVO.setPortalId(vo.getPortalId());
		delPZVO.setPageId(vo.getPageId());
		portalPageZoneService.deleteAllContentOnPage(delPZVO);
		
		return portalPageDAO.deletePage(vo);
	}
	
	
	@WebMethod(exclude=true)
	public boolean updatePageActiveStatus(PortalPageVO vo) throws Exception {
		TreeVO treeVO = new TreeVO();
		treeVO.setTenantId(vo.getTenantId());
		treeVO.setPortalId(vo.getPortalId());
		treeVO.setNodeId(vo.getPageId());
		treeVO.setIsActive(vo.getIsActive());
		
		MemoryCacheCenter.getInstance().clear("TREE_SERVICE");
		MemoryCacheCenter.getInstance().clear("MENU_SERVICE");

		return treeService.updateTreeActiveStatus(treeVO) && portalPageDAO.updatePage(vo);
	}
	
	
	public List<PortalPageVO> getHome(PortalPageVO vo) throws Exception {
		return portalPageDAO.getHome(vo);
	}
	
	public PortalPageVO getPage(PortalPageVO vo) throws Exception {
		PortalPageVO resultVO = new PortalPageVO();
		resultVO = portalPageDAO.getPage(vo);
		
		GroupPortalVO parentPortalVO = new GroupPortalVO();
		parentPortalVO.setTenantId(vo.getTenantId());
		parentPortalVO.setPortalId(vo.getPortalId());
		
		if(resultVO == null) {
			//부모그룹이 있는지 체크하여 있으면, 부모 포탈 페이지도 찾아봄
			for(int i = 0; true ; i++ ) {
				parentPortalVO = groupPortalService.getParentPortal(parentPortalVO);
				if(parentPortalVO == null) {
					break;
				} else {
					if(parentPortalVO.getParentId().equals("_ROOT_")) {
						vo.setTenantId(parentPortalVO.getTenantId());
						vo.setPortalId(parentPortalVO.getPortalId());
						resultVO = portalPageDAO.getPage(vo);
						
						/*각 페이지의 path 정보를 찾아온다*/
						PortalPageVO pathVO = portalPageDAO.getPagePathInfo(vo);
						resultVO.setPageIdPath(pathVO.getPageIdPath());
						resultVO.setPageNamePath(pathVO.getPageNamePath());
						
						break;
					} else {
						vo.setTenantId(parentPortalVO.getTenantId());
						vo.setPortalId(parentPortalVO.getPortalId());
						resultVO = portalPageDAO.getPage(vo);
						if(resultVO != null) {
							/*각 페이지의 path 정보를 찾아온다*/
							PortalPageVO pathVO = portalPageDAO.getPagePathInfo(vo);
							resultVO.setPageIdPath(pathVO.getPageIdPath());
							resultVO.setPageNamePath(pathVO.getPageNamePath());
							break;
						}
					}
				}
			}
		}else{
			/*각 페이지의 path 정보를 찾아온다*/
			PortalPageVO pathVO = portalPageDAO.getPagePathInfo(vo);
			resultVO.setPageIdPath(pathVO.getPageIdPath());
			resultVO.setPageNamePath(pathVO.getPageNamePath());
		}
		
		return resultVO;
	}
	
	//정상적인 페이지 접근이 아닌 페이지데이터참고용으로만 호출시 다음의 메서드 사용
	//getPage 메서드는 정상적으로 페이지를 호출하는 경우에만 사용 -> AOP로 접근권한이나 페이지 히스토리가 쌓임
	public PortalPageVO getPageForAdmin(PortalPageVO vo) throws Exception {
		PortalPageVO resultVO = new PortalPageVO();
		resultVO = portalPageDAO.getPage(vo);
		
		GroupPortalVO parentPortalVO = new GroupPortalVO();
		parentPortalVO.setTenantId(vo.getTenantId());
		parentPortalVO.setPortalId(vo.getPortalId());
		
		if(resultVO == null) {
			//부모그룹이 있는지 체크하여 있으면, 부모 포탈 페이지도 찾아봄
			for(int i = 0; true ; i++ ) {
				parentPortalVO = groupPortalService.getParentPortal(parentPortalVO);
				if(parentPortalVO == null) {
					break;
				} else {
					if(parentPortalVO.getParentId().equals("_ROOT_")) {
						vo.setTenantId(parentPortalVO.getTenantId());
						vo.setPortalId(parentPortalVO.getPortalId());
						resultVO = portalPageDAO.getPage(vo);
						break;
					} else {
						vo.setTenantId(parentPortalVO.getTenantId());
						vo.setPortalId(parentPortalVO.getPortalId());
						resultVO = portalPageDAO.getPage(vo);
						if(resultVO != null) {
							break;
						}
					}
				}
			}
		}
		
		return resultVO;
	}
	
	public List getPersonalPages(PortalPageVO vo) throws Exception {
		return portalPageDAO.getPersonalPages(vo);
	}
	

	public List getTemplatePages(PortalPageVO vo) throws Exception {
		return portalPageDAO.getTemplatePages(vo);
	}

	
	@Deprecated
	public Page getPageList(PortalPageVO vo) throws Exception {
		return portalPageDAO.getPageList(vo);
	}

	
	public int getTotalPageCount(PortalPageVO vo) throws Exception {
		return portalPageDAO.getTotalPageCount(vo);
	}
	
	public boolean clearPersonalizedPage(PortalPageVO vo) throws Exception {
		return portalPageDAO.clearPersonalizedPage(vo);
	}
	/**
	 * 
	 */
	public CommonVO getGlobalServiceIds(PortalPageVO page) throws Exception {
		return portalPageDAO.getGlobalServiceIds(page);
	}
}
