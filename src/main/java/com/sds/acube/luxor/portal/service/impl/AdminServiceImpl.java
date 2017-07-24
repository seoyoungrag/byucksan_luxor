package com.sds.acube.luxor.portal.service.impl;

import org.anyframe.pagination.Page;
import com.sds.acube.luxor.common.service.MessageService;
import com.sds.acube.luxor.common.vo.MessageVO;
import com.sds.acube.luxor.framework.service.BaseService;
import com.sds.acube.luxor.portal.dao.AdminMenuDAO;
import com.sds.acube.luxor.portal.dao.AdminUserDAO;
import com.sds.acube.luxor.portal.service.AdminService;
import com.sds.acube.luxor.portal.vo.AdminMenuVO;
import com.sds.acube.luxor.portal.vo.AdminUserVO;
import java.util.List;
public class AdminServiceImpl extends BaseService implements AdminService {
	private AdminMenuDAO adminMenuDAO;
	private AdminUserDAO adminUserDAO;
	private MessageService messageService;

	/**
	 * @param adminUserDAO The adminUserDAO to set.
	 */
	public void setAdminUserDAO(AdminUserDAO adminUserDAO) {
		this.adminUserDAO = adminUserDAO;
	}

	public void setAdminMenuDAO(AdminMenuDAO adminMenuDAO) {
		this.adminMenuDAO = adminMenuDAO;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
	public Page getAdminMenuActive(AdminMenuVO vo) throws Exception {
		return adminMenuDAO.getAdminMenuActive(vo);
	}

	public Page getAdminMenuCategoryList(AdminMenuVO vo) throws Exception {
		return adminMenuDAO.getAdminMenuCategoryList(vo);
	}

	public Page getAdminMenuList(AdminMenuVO vo) throws Exception {
		return adminMenuDAO.getAdminMenuList(vo);
	}
	
	public boolean insertAdminMenu(AdminMenuVO vo) throws Exception {
		// 다국어 셋팅
		String menuNameId = messageService.insertMessage(vo);
		vo.setMenuNameId(menuNameId);
		
		return adminMenuDAO.insertAdminMenu(vo);
	}
	
	public boolean updateAdminMenu(AdminMenuVO vo) throws Exception {
		boolean result = messageService.updateMessage(vo) && adminMenuDAO.updateAdminMenu(vo);
		if(result==false) {
			throw new Exception("Fail to update!!");
		}
		return result;
	}

	public boolean setHome(AdminMenuVO vo) throws Exception {
		adminMenuDAO.unsetHome(vo);
		boolean result = adminMenuDAO.setHome(vo);
		if(result==false) {
			throw new Exception("Fail to update!!");
		}
		return result;
	}

	public boolean deleteAdminMenu(AdminMenuVO vo) throws Exception {
		boolean result = messageService.deleteMessage(vo) && adminMenuDAO.deleteAdminMenu(vo);
		if(result==false) {
			throw new Exception("Fail to delete!!");
		}
		return result;
	}
	
	public int getChildAdminMenuCount(AdminMenuVO vo) throws Exception {
		return adminMenuDAO.getChildAdminMenuCount(vo);
	}
	
	public AdminMenuVO getAdminMenu(AdminMenuVO vo) throws Exception {
		AdminMenuVO aMenu = adminMenuDAO.getAdminMenu(vo);

		aMenu.setMessageId(aMenu.getMenuNameId());
		aMenu.setLangType(vo.getLangType());
		
		MessageVO messageVO = messageService.getMessageByIdLang(aMenu);
		
		if(messageVO!=null) {
			aMenu.setMessageName(messageVO.getMessageName());
		}
		return aMenu;
	}

	/* (non-Javadoc)
	 * @see com.sds.acube.luxor.common.service.AdminService#checkExistAdminUser(com.sds.acube.luxor.common.vo.AdminUserVO)
	 */
	public int checkExistAdminUser(AdminUserVO param) throws Exception {
		return (adminUserDAO.getListAll(param) != null ? adminUserDAO.getListAll(param).size() : 0);
	}
	
	/* (non-Javadoc)
	 * @see com.sds.acube.luxor.common.service.AdminService#getAdminUserList(com.sds.acube.luxor.common.vo.AdminUserVO)
	 */
	public Page getAdminUserList(AdminUserVO param) throws Exception {
		return adminUserDAO.getList(param);
	}
	
	/* (non-Javadoc)
	 * @see com.sds.acube.luxor.common.service.AdminService#applyAdminUser(com.sds.acube.luxor.common.vo.AdminUserVO)
	 */
	public AdminUserVO[] getAdminUserListAll(AdminUserVO param) throws Exception {
			List list = adminUserDAO.getListAll(param);
			AdminUserVO[] userList = null;
		if(list != null) {
			userList = new AdminUserVO[list.size()];
			list.toArray(userList);
		} else {
			throw new Exception();
		}
		return userList;
	}

	/* (non-Javadoc)
	 * @see com.sds.acube.luxor.common.service.AdminService#applyAdminUser(com.sds.acube.luxor.common.vo.AdminUserVO)
	 */
	public int applyAdminUser(AdminUserVO param) throws Exception {
		int cnt = 0;
		for(int i=0;i<param.getAdminIds().length;i++) {
			param.setUserId(param.getAdminIds()[i]);
			param.setUserName(param.getAdminNames()[i]);
			param.setUserUid(param.getAdminUids()[i]);
			cnt += adminUserDAO.insert(param);
		}
		return cnt;
	}

	/* (non-Javadoc)
	 * @see com.sds.acube.luxor.common.service.AdminService#getAdminUser(com.sds.acube.luxor.common.vo.AdminUserVO)
	 */
	public AdminUserVO getAdminUser(AdminUserVO param) throws Exception {
		return adminUserDAO.get(param);
	}

	/* (non-Javadoc)
	 * @see com.sds.acube.luxor.common.service.AdminService#deleteAdminUser(com.sds.acube.luxor.common.vo.AdminUserVO)
	 */
	public int deleteAdminUser(AdminUserVO param) throws Exception {
		return adminUserDAO.delete(param);
	}
	
	public int setAclIgnoreType(AdminUserVO param)throws Exception {
		return adminUserDAO.setAclIgnoreType(param);
	}
}
