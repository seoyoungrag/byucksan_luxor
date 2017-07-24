package com.sds.acube.luxor.common.service;

import java.util.List;

import com.sds.acube.luxor.common.vo.SsoToken;
import com.sds.acube.luxor.ws.client.aclservice.AccessList;
import com.sds.acube.luxor.ws.client.aclservice.Permission;
import com.sds.acube.luxor.ws.client.aclservice.SsoKey;

public interface AccessControllService {
	@SuppressWarnings("unchecked")
	public boolean canAccess(String resourceId, List accessIdList) throws Exception;
	
	@SuppressWarnings("unchecked")
	public Permission getPermission(String resourceId, List accessIdList) throws Exception;
	
	public String getAccess(String resourceId) throws Exception;

	@SuppressWarnings("unchecked")
	public List getGroupsByUid(String userId) throws Exception;
	
	@SuppressWarnings("unchecked")
	public List getAclGroupsByUid(String userId) throws Exception;

	public AccessList makeAccessList(String accessInfos) throws Exception;
	
	public boolean aclSave(String resourceId, String accessInfos) throws Exception;
	
	/**
	 * SSO TOKEN 정보 얻어오기
	 * 
	 * @since 2014.02.25
	 * @param ssoData
	 * @param timeOutMinute
	 * @return
	 * @throws Exception
	 */
	public SsoToken getSsoToken(String ssoData, int timeOutMinute) throws Exception;
	
	/**
	 * 얻어온 TOKEN 정보가 올바른지 체크
	 * 
	 * @since 2014.02.25
	 * @param ssoKey
	 * @return
	 * @throws Exception
	 */
	public boolean validSsoToken(SsoKey ssoKey) throws Exception;
}
