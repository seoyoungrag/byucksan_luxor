package com.sds.acube.luxor.common.service;

import java.util.List;
import org.anyframe.pagination.Page;
import com.sds.acube.luxor.common.vo.UserBasicVO;
import com.sds.acube.luxor.common.vo.UserProfileVO;
import com.sds.acube.luxor.ws.client.aclservice.GroupInfo;
import com.sds.acube.luxor.ws.client.aclservice.RoleInfo;
import com.sds.acube.luxor.ws.client.orgservice.IUser;

public interface UserService {
	public UserProfileVO getUserByUID(String userUID) throws Exception;
	
	public IUser getUser(IUser vo) throws Exception;
	
	public Page getUserList(IUser paramVO, UserBasicVO userBasicVO) throws Exception;
	
	public List getRoleList(RoleInfo roleInfo) throws Exception;
	
	public List getVirtualGroupList(GroupInfo groupInfo) throws Exception;
	
	@Deprecated
	public List getAclGroupList(String compId) throws Exception;
	
	public int changePassword(String userUid, String oldPassword, String newPassword) throws Exception;
	
	public boolean changeUserInformation(IUser vo) throws Exception;
}
