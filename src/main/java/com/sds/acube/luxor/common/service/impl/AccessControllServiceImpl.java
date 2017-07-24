package com.sds.acube.luxor.common.service.impl;

import java.util.List;
import com.sds.acube.luxor.common.service.AccessControllService;
import com.sds.acube.luxor.common.service.TreeService;
import com.sds.acube.luxor.common.vo.SsoToken;
import com.sds.acube.luxor.common.vo.TreeVO;
import com.sds.acube.luxor.framework.cache.LuxorHashMap;
import com.sds.acube.luxor.framework.cache.MemoryCache;
import com.sds.acube.luxor.framework.cache.MemoryCacheCenter;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.service.BaseService;
import com.sds.acube.luxor.ws.client.aclservice.AccessList;
import com.sds.acube.luxor.ws.client.aclservice.AclProvider4WS;
import com.sds.acube.luxor.ws.client.aclservice.Permission;
import com.sds.acube.luxor.ws.client.aclservice.SsoKey;


public class AccessControllServiceImpl extends BaseService implements AccessControllService, MemoryCache {
	private static LuxorHashMap cache = new LuxorHashMap(Integer.parseInt(LuxorConfig.getString("Common", "CACHE.MAXIMUM_SIZE")));
	private AclProvider4WS wsClientAclProviderService;
	private TreeService treeService;
	private String serviceKey;
	
	public void setTreeService(TreeService treeService) {
    	this.treeService = treeService;
    }

	public void setWsClientAclProviderService(AclProvider4WS wsClientAclProviderService) {
    	this.wsClientAclProviderService = wsClientAclProviderService;
    }

	
	public AccessControllServiceImpl() {
		MemoryCacheCenter.getInstance().register("ACCESS_CONTROLL_SERVICE", this);
		serviceKey = LuxorConfig.getString("IAM.KEY");
	}
	

	/**
	 * 접근권한 체크
	 */
	@SuppressWarnings("unchecked")
	public boolean canAccess(String resourceId, List accessIdList) throws Exception {
		boolean result = true;
		
		TreeVO treeVO = new TreeVO();
		treeVO.setNodeId(resourceId);
		treeVO.setPortalId("A10000");
		treeVO.setTenantId("ESIS000");
		treeVO = treeService.getTreeNode(treeVO);
		
		if(treeVO==null) {
			// Permission 정보 가져옴
			Permission p = getPermission(resourceId, accessIdList);
			result = p.isReadable();
		} else {
			TreeVO[] parents = treeService.getParents(treeVO);
	
			// 상위에서부터 내려오면서 권한검사
			for(int i=parents.length-1; i >= 0; i--) {
				// Permission 정보 가져옴
				Permission p = getPermission(parents[i].getNodeId(), accessIdList);
				result = p.isReadable();
				
				// 권한 체크
				if(result==false) {
					break;
				}
			}
		}
		
		return result;
	}
	
	
	/**
	 * IAM ACL WebSerivce를 통해 권한정보 받아옴
	 */
	@SuppressWarnings("unchecked")
	public Permission getPermission(String resourceId, List accessIdList) throws Exception {
		String cacheKey = "getPermission."+resourceId+"."+accessIdList.toString();
		Permission p = (Permission)cache.get(cacheKey);
		if(p==null) {
			p = wsClientAclProviderService.getPermissionByAccess(serviceKey, resourceId, accessIdList);
			if(p==null) {
				p = new Permission();
				p.setReadable(false);
			}
			cache.put(cacheKey, p);
		} 

		return p;
	}

	
	/**
	 * IAM ACL WebSerivce를 통해 리소스에 대한 Access 정보 받아옴
	 */
	public String getAccess(String resourceId) throws Exception {
		String cacheKey = "getAccess."+resourceId;
		String ac = (String)cache.get(cacheKey);
		if(ac==null) {
			ac = wsClientAclProviderService.getAccessString(serviceKey, resourceId);
			cache.put(cacheKey, ac);
		}
		return ac;
	}


	/**
	 * 사용자가 속한 가상 그룹 정보를 받아옴
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getGroupsByUid(String userId) throws Exception {
		String cacheKey = "getGroupsByUid."+userId;
		List list = (List)cache.get(cacheKey);
		if(list==null) {
			list = wsClientAclProviderService.getAccessIdListByUid(serviceKey, userId);
			cache.put(cacheKey, list);
		}
		return list;
	}
	

	/**
	 * 사용자가 속한 권한 그룹 정보를 받아옴
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getAclGroupsByUid(String userId) throws Exception {
		String cacheKey = "getAclGroupsByUid."+userId;
		List list = (List)cache.get(cacheKey);
		if(list==null) {
			list = wsClientAclProviderService.getAccessIdListByUid(serviceKey, userId);
			cache.put(cacheKey, list);
		}
		return list;
	}

	
	/**
	 * IAM ACL WebSerivce를 통해 액세스 정보 문자를 AccessList 형태로 변환한다
	 */
	public AccessList makeAccessList(String accessInfos) throws Exception {
		String cacheKey = "makeAccessList."+accessInfos;
		AccessList al = (AccessList)cache.get(cacheKey);
		if(al==null) {
			al = wsClientAclProviderService.makeAccessList(serviceKey, accessInfos);
			cache.put(cacheKey, al);
		}
		return al;
	}
	

	/**
	 * IAM ACL WebSerivce를 통해 리소스에 대한 ACL 정보 추가
	 */
	public boolean aclSave(String resourceId, String accessInfos) throws Exception {
		// Cache Clear
		MemoryCacheCenter.getInstance().clearAll();
		return wsClientAclProviderService.save(serviceKey, resourceId, accessInfos);
	}

	
    /**
     * Cache 전체 Clear
     */
    public void clear() {
        cache.clear();
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

    /**
     * @since 2014.02.25
     */
	public SsoToken getSsoToken(String ssoData, int timeOutMinute) throws Exception {
		// SSO Token 발급할지 여부 체크
		boolean token_publishing = LuxorConfig.getString("Common", "SSO.TOKEN_PUBLISHING").equalsIgnoreCase("Y");
		SsoToken token = new SsoToken();
		if (token_publishing) {
			SsoKey item = this.wsClientAclProviderService.getSsoToken(ssoData, timeOutMinute);
			if (item == null) {
				return null;
			}
			token.setSTM(item.getPublishingTimestamp());
			token.setSHM(item.getHmacKey());
		} else {
			token.setSTM("NOT-USE-SSO-TOKEN-PUBLISHING-ON-LUXOR-PORTAL");
			token.setSHM("NOT-USE-SSO-TOKEN-PUBLISHING-ON-LUXOR-PORTAL");
		}
		return token;
	}

	/**
	 * @since 2014.02.25
	 */
	public boolean validSsoToken(SsoKey ssoKey) throws Exception {
		// SSO Token 발급할지 여부 체크
		boolean token_publishing = LuxorConfig.getString("Common", "SSO.TOKEN_PUBLISHING").equalsIgnoreCase("Y");
		if (token_publishing) {
			return this.wsClientAclProviderService.validSsoToken(ssoKey);
		} else {
			return true; // 토큰을 사용하지 않을 경우 validation을 통과하도록 함.
		}
	}


}
