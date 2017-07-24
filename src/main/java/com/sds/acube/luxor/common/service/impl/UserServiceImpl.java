package com.sds.acube.luxor.common.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.anyframe.pagination.Page;
import com.sds.acube.luxor.common.service.UserService;
import com.sds.acube.luxor.common.vo.UserBasicVO;
import com.sds.acube.luxor.common.vo.UserProfileVO;
import com.sds.acube.luxor.framework.cache.LuxorHashMap;
import com.sds.acube.luxor.framework.cache.MemoryCache;
import com.sds.acube.luxor.framework.cache.MemoryCacheCenter;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.service.BaseService;
import com.sds.acube.luxor.ws.client.aclservice.AclProvider4WS;
import com.sds.acube.luxor.ws.client.aclservice.GroupInfo;
import com.sds.acube.luxor.ws.client.aclservice.RoleInfo;
import com.sds.acube.luxor.ws.client.orgservice.IUser;
import com.sds.acube.luxor.ws.client.orgservice.IUsers;
import com.sds.acube.luxor.ws.client.orgservice.OrgProvider4WS;

public class UserServiceImpl extends BaseService implements UserService, MemoryCache {
	private static LuxorHashMap cache = new LuxorHashMap(Integer.parseInt(LuxorConfig.getString("Common", "CACHE.MAXIMUM_SIZE")));
	private OrgProvider4WS orgService;
	private AclProvider4WS aclService;
	private String serviceKey;
	
	
	public UserServiceImpl() {
		MemoryCacheCenter.getInstance().register("USER_SERVICE", this);
		serviceKey = LuxorConfig.getString("IAM.KEY");
	}
	
	public void setOrgService(OrgProvider4WS orgService) {
		this.orgService = orgService;
	}

	public void setAclService(AclProvider4WS aclService) {
		this.aclService = aclService;
	}
	
	/**
	 * User UID로 사용자 정보 조회
	 * 
	 * @param userUID
	 * @return
	 * @throws Exception
	 */
	public UserProfileVO getUserByUID(String userUID) throws Exception {
		UserProfileVO userVO = (UserProfileVO)cache.get(userUID);
		if(userVO==null) {
			IUser user = orgService.getUserByUID(serviceKey, userUID);
			if(user!=null) {
			userVO = new UserProfileVO();
			userVO.setCompId(user.getCompID());
			userVO.setCompName(user.getCompName());
			userVO.setCompOtherName(user.getCompOtherName()); // @add 2013.11.18 - other language
			userVO.setLoginId(user.getUserID());
			userVO.setUserUid(user.getUserUID());
			userVO.setUserName(user.getUserName());
			userVO.setUserOtherName(user.getUserOtherName()); // @add 2013.11.18 - other language
			userVO.setDeptId(user.getDeptID());
			userVO.setDeptName(user.getDeptName());
			userVO.setDeptOtherName(user.getDeptOtherName()); // @add 2013.11.18 - other language
			userVO.setSecurityLevel(user.getSecurityLevel());
			userVO.setGradeCode(user.getGradeCode());
			userVO.setGradeName(user.getGradeName());
			userVO.setGradeOtherName(user.getGradeOtherName()); // @add 2013.11.18 - other language
			userVO.setOfficeTel(user.getOfficeTel());
			userVO.setOfficeFax(user.getOfficeFax());
			userVO.setHomeTel(user.getHomeTel());
			userVO.setMobile(user.getMobile());
			userVO.setSysMail(user.getSysMail());
			userVO.seteMail(user.getEmail());
			userVO.setChangedPasswordDate(user.getChangedPWDDate());
			userVO.setRoleCodes(user.getRoleCodes());
			userVO.setPositionCode(user.getPositionCode());
			userVO.setPositionName(user.getPositionName());
			userVO.setTitleName(user.getTitleName());
			
			cache.put(userUID, userVO);
			}
		}
		return userVO;
	}
	
	
	public IUser getUser(IUser vo) throws Exception {
		IUser iUser = orgService.getUserByID(serviceKey, vo.getUserID());

		if (iUser == null) {
			// 객체를 얻어오지 못하는 경우
			logger.error("-iDir- IUser object is null. (" + vo.getUserID() + ")");
		}
		return iUser;
	}
	
	public Page getUserList(IUser vo, UserBasicVO userBasicVO) throws Exception {

		IUsers iUsers = null;

		if (userBasicVO.getSearchWay().equals("byDeptIDWithPaging")){
			iUsers = orgService.getUsersByDeptIDWithPaging(serviceKey, vo.getDeptID(), userBasicVO.getOrgType(), userBasicVO.getPageSize(), userBasicVO.getcPage()-1);
		}else if(userBasicVO.getSearchWay().equals("byDeptID")) {
			iUsers = orgService.getUsersByDeptID(serviceKey, vo.getDeptID(), userBasicVO.getOrgType());
		}else if (userBasicVO.getSearchWay().equals("ByName")){
			iUsers = orgService.getUsersByName(serviceKey, vo.getUserName());
		}else if (userBasicVO.getSearchWay().equals("ByNameByOrgId")){
			iUsers = orgService.getUsersByNameByOrgIdWithPaging(serviceKey, vo.getUserName(), vo.getDeptID(), userBasicVO.getnType(), userBasicVO.getPageSize(), userBasicVO.getcPage()-1);
		}else if (userBasicVO.getSearchWay().equals("byGradeName")){
			iUsers = orgService.getUsersByGradeNameWithPaging(serviceKey, vo.getGradeName(), vo.getDeptID(), userBasicVO.getnType(), userBasicVO.getPageSize(), userBasicVO.getcPage()-1);      //nType 검색 type ( 0 : 전체 검색(Group), 1 : 회사내 검색, 2 : 부서내 검색)
		}

		ArrayList userList = new ArrayList();
		Page page;
		
		if(iUsers != null) {
			for(int i=0; i < iUsers.getUserList().size(); i++) {
				userList.add(iUsers.getUserList().get(i));
			}
			page = new Page(userList, userBasicVO.getcPage(), iUsers.getSearchTotalCount());  // 리스트, 현재페이지, 총페이지수
		} else {
			// 객체를 얻어오지 못하는 경우
			page = null;
		}
		
		return page;
	}
	
	
	public void clear() {
		logger.debug("Cache - UserService.clear() called..!!"); 
		cache.clear();
	}
	
	
	public void remove(Object key) {
		logger.debug("Cache - UserService.remove("+key+") called..!!");
		cache.remove(key);
	}
	
	public int size() {
		return cache.size();
    }

	
	/**
	 * 역할 리스트
	 */
	public List getRoleList(RoleInfo roleInfo) throws Exception{
		return aclService.getRoleInfoListByCondition(serviceKey, roleInfo);
	}

	
	/**
	 * 가상그룹 리스트
	 */
	public List getVirtualGroupList(GroupInfo groupInfo) throws Exception{
		return aclService.getModuleVirtualGroupList(serviceKey, groupInfo);
	}

	
	/**
	 * 권한그룹 리스트
	 */
	@Deprecated
	public List getAclGroupList(String compId) throws Exception{
		return aclService.getAccessIdListByUid(serviceKey, "");
	}

	
	/**
	 * 사용자 비밀번호 변경
	 */
	public int changePassword(String userUid, String oldPassword, String newPassword) throws Exception {
		return orgService.registerLogonPassword(serviceKey, userUid, oldPassword, newPassword);
	}
	
	
	/**
	 * 사용자 개인정보 변경
	 */
	public boolean changeUserInformation(IUser vo) throws Exception {
		clear();
		return orgService.registerIUserAddressInfo(serviceKey, vo);
	}
	
}
