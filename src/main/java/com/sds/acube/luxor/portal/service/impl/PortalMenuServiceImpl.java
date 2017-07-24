package com.sds.acube.luxor.portal.service.impl;

import java.util.Collections;
import java.util.List;
import javax.jws.WebMethod;
import com.sds.acube.luxor.framework.cache.LuxorHashMap;
import com.sds.acube.luxor.framework.cache.MemoryCache;
import com.sds.acube.luxor.framework.cache.MemoryCacheCenter;
import com.sds.acube.luxor.framework.service.BaseService;
import com.sds.acube.luxor.portal.dao.PortalMenuDAO;
import com.sds.acube.luxor.portal.service.PortalMenuService;
import com.sds.acube.luxor.portal.vo.PortalMenuVO;

public class PortalMenuServiceImpl extends BaseService implements PortalMenuService, MemoryCache {
	private static LuxorHashMap cache = new LuxorHashMap();
	private static LuxorHashMap mcache = new LuxorHashMap();
	private PortalMenuDAO portalMenuDAO;
	private MemoryCache menuOnly;
	
	public void setPortalMenuDAO(PortalMenuDAO portalMenuDAO) {
    	this.portalMenuDAO = portalMenuDAO;
    }

	public PortalMenuServiceImpl() {
		MemoryCacheCenter.getInstance().register("MENU_SERVICE", this);
		menuOnly= new MemoryCache() {
			public void clear() {}
			public void remove(Object key) {}
			public int size() {
				return mcache.size();
			}
		};
		MemoryCacheCenter.getInstance().register("MENU_ONLY_SERVICE", menuOnly);
	}

	@WebMethod(exclude=true)
	public boolean insertMenu(PortalMenuVO vo) throws Exception {
		MemoryCacheCenter.getInstance().clear("MENU_SERVICE");
		
		// Sequence
		int maxSeq = portalMenuDAO.getMaxSeq(vo);
		vo.setSeq(++maxSeq);
		
		// Depth 
		PortalMenuVO parentVO = portalMenuDAO.getParentMenu(vo);
		if(parentVO!=null) {
			int parentDepth = parentVO.getDepth();
			vo.setDepth(++parentDepth);
			
			// Has Child Flag
			parentVO.setHasChild("Y");
			
			portalMenuDAO.updateMenu(parentVO);
		}
		
		// Insert New Row
	    return portalMenuDAO.insertMenu(vo);
    }

	
	@WebMethod(exclude=true)
	public boolean updateMenu(PortalMenuVO vo) throws Exception {
		MemoryCacheCenter.getInstance().clear("MENU_SERVICE");
		return portalMenuDAO.updateMenu(vo);
    }

	@WebMethod(exclude=true)
	public boolean updateMenuDisplayName(PortalMenuVO vo) throws Exception {
		MemoryCacheCenter.getInstance().clear("MENU_SERVICE");
		return portalMenuDAO.updateMenuDisplayName(vo);
    }
	

	@WebMethod(exclude=true)
	public boolean updateMenuSeq(PortalMenuVO vo) throws Exception {
		MemoryCacheCenter.getInstance().clear("MENU_SERVICE");
		return portalMenuDAO.updateMenuSeq(vo);
    }

	
	/**
	 * 전체 페이지 적용시 기존에 한개 이상의 메뉴가 이미 등록되어있는 경우라면
	 * 한개만 남겨놓고 나머지는 다 삭제하고 남아있는 한개 메뉴에 대해서만 전체페이지 적용함 
	 */
	@WebMethod(exclude=true)
	public boolean updateMenuToAll(PortalMenuVO vo) throws Exception {
		MemoryCacheCenter.getInstance().clear("MENU_SERVICE");
		PortalMenuVO[] menus = portalMenuDAO.getAddedMenu(vo);
		if(menus.length > 1) {
			for(int i=1; i < menus.length; i++) {
				deleteMenu(menus[i]);
			}
		}
		return portalMenuDAO.updateMenuToAll(vo);
    }
	

	/**
	 * 선택된 메뉴만 삭제
	 */
	@WebMethod(exclude=true)
	public boolean deleteMenu(PortalMenuVO vo) throws Exception {
		MemoryCacheCenter.getInstance().clear("MENU_SERVICE");
		
		boolean result = false;
		
		PortalMenuVO[] sibling = portalMenuDAO.getMenusOnParent(vo);
		
		// 상위메뉴가 ROOT가 아니고 삭제 대상이 마지막 메뉴인 경우에는 
		// 삭제 후 Parent의 hasChild를 N로 변경해줌
		if(!vo.getParentId().equals("ROOT") && sibling.length==1) {
			PortalMenuVO parentMenu = portalMenuDAO.getParentMenu(vo);
			parentMenu.setHasChild("N");
			result = portalMenuDAO.deleteMenu(vo) && portalMenuDAO.updateMenu(parentMenu);
		} else {
			result = portalMenuDAO.deleteMenu(vo);
		}
		
		if(result==false) {
			throw new Exception("Fail to delete menu");
		}
		
	    return result;
    }
	
	/**
	 * 하위 모든 메뉴를 삭제
	 * 
	 * @param menuVO
	 * @throws Exception
	 */
	@WebMethod(exclude=true)
	public void deleteMenuAll(PortalMenuVO menuVO) throws Exception {
		MemoryCacheCenter.getInstance().clear("MENU_SERVICE");
		
		// Get children
		menuVO.setParentId(menuVO.getMenuId());
		PortalMenuVO[] childVO = portalMenuDAO.getMenusOnParent(menuVO);
		
		// Base case
		if(childVO.length==0) {
			deleteMenu(menuVO); // delete my self
			return;
		}
		
		for(PortalMenuVO tempVO : childVO) {
			tempVO.setLangType(menuVO.getLangType());
			deleteMenu(tempVO); // Delete each node
			deleteMenuAll(tempVO); // Recursive call
		}
	}
	
	
	@WebMethod(exclude=true)
	public boolean deleteMenusOnZone(PortalMenuVO vo) throws Exception {
		MemoryCacheCenter.getInstance().clear("MENU_SERVICE");
		return portalMenuDAO.deleteMenusOnZone(vo);
	}

	
	@WebMethod(exclude=true)
	public boolean deleteMenusOnContent(PortalMenuVO vo) throws Exception {
		MemoryCacheCenter.getInstance().clear("MENU_SERVICE");
	    return portalMenuDAO.deleteMenusOnContent(vo);
    }

	
	@WebMethod(exclude=true)
	public boolean deleteMenusOnPage(PortalMenuVO vo) throws Exception {
		MemoryCacheCenter.getInstance().clear("MENU_SERVICE");
	    return portalMenuDAO.deleteMenusOnPage(vo);
    }

	public int getMaxSeq(PortalMenuVO vo) throws Exception {
		return portalMenuDAO.getMaxSeq(vo);
	}
	
	public PortalMenuVO getMenu(PortalMenuVO vo) throws Exception {
	    return portalMenuDAO.getMenu(vo);
    }
	
	public List getMenusOnZone(PortalMenuVO vo) throws Exception {
		String cacheKey = "getMenusOnZone."+vo.getPortalId()+"."+vo.getPageId()+"."+vo.getContentId()+"."+vo.getZoneId()+"."+vo.getAccessIdList().toString()+"."+vo.getLangType();
		List list = (List)cache.get(cacheKey);
		if(list==null || list.isEmpty()) {
			list = portalMenuDAO.getMenusOnZone(vo);
			list = putCache(cacheKey, list);
		}
		return list;
	}

	public List getMenusOnContent(PortalMenuVO vo) throws Exception {
		String cacheKey = "getMenusOnContent."+vo.getPortalId()+"."+vo.getPageId()+"."+vo.getContentId()+"."+vo.getAccessIdList().toString()+"."+vo.getLangType();
		List list = (List)cache.get(cacheKey);
		if(list==null || list.isEmpty()) {
			list = portalMenuDAO.getMenusOnContent(vo);
			list = putCache(cacheKey, list);
		}
		return list;
    }

	public List getMenusOnPage(PortalMenuVO vo) throws Exception {
		return portalMenuDAO.getMenusOnPage(vo);
    }
	
	public List getMyMenus(PortalMenuVO vo) throws Exception {
		String cacheKey = "getMyMenus."+vo.getPortalId()+"."+vo.getRegUserId()+"."+vo.getLangType();
		List list = (List)cache.get(cacheKey);
		if(list==null || list.isEmpty()) {
			list = portalMenuDAO.getMyMenus(vo);
			list = putCache(cacheKey, list);
		}
		return list;
    }

	public List getMenus(PortalMenuVO vo) throws Exception {
		List list = portalMenuDAO.getMenus(vo);
		return list;
    }
	
	public List getTargetedPages(PortalMenuVO vo) throws Exception {
		List list = portalMenuDAO.getTargetedPages(vo);
		return list;
    }
	/**
	 * cache 의 value가 되는 List들에는 몇 안되는 PortalMenuVO들이 중복되어 들어가므로 같은 PortalMenuVO 들이 하나의 인스턴스를 참조하도록 함
	 * @param cacheKey
	 * @param list
	 * @return
	 */
	private List putCache(String cacheKey, List list) {
		if(list == null) {
			list = Collections.EMPTY_LIST;
		}
		for(int i=0; i< list.size(); i++) {
			PortalMenuVO m = (PortalMenuVO) list.get(i);
			if(mcache.containsKey(m) ) {
				list.set(i, mcache.get(m));
			} else {
				mcache.put(m, m);
			}
		}
		cache.put(cacheKey, list);
		return list;
	}
	
    /**
     * Cache 전체 Clear
     */
    public void clear() {
        cache.clear();
        mcache.clear();
    }

    
    /**
     * Cache에서 특정 key만 삭제
     */
    public void remove(Object key) {
        cache.remove(key);
    }
	public int size() {
		return cache.size();
	}

}
