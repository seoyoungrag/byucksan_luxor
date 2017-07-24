/*
 * @(#) PortletContextServiceImpl.java 2010. 5. 18.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sds.acube.luxor.framework.cache.MemoryCache;
import com.sds.acube.luxor.framework.cache.MemoryCacheCenter;
import com.sds.acube.luxor.portal.PortletConstant;
import com.sds.acube.luxor.portal.context.PortletContextRegistry;
import com.sds.acube.luxor.portal.context.PortletXMLLoader;
import com.sds.acube.luxor.portal.dao.PortletContextDAO;
import com.sds.acube.luxor.portal.service.PortletContextService;
import com.sds.acube.luxor.portal.vo.PortletContextVO;
import com.sds.acube.luxor.portal.vo.PortletManagementVO;


/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.2.2.2 $ $Date: 2011/12/06 02:28:35 $
 */
public class PortletContextServiceImpl implements PortletContextService, MemoryCache {
	private static Log logger = LogFactory.getLog(PortletContextServiceImpl.class);
	
	private PortletContextDAO contextDAO;
	
	public PortletContextServiceImpl() {
		super();
		// MessageServiceImpl을 Cache에 등록함
        // 등록시 고유의 KEY를 문자열로 입력함
        MemoryCacheCenter.getInstance().register("PORTLET_SERVICE", this);
	}
	
	/**
	 * @param contextDAO The contextDAO to set.
	 */
	public void setContextDAO(PortletContextDAO contextDAO) {
		this.contextDAO = contextDAO;
	}


	/* (non-Javadoc)
	 * @see com.sds.acube.luxor.portal.service.PortletContextService#selectList()
	 */
	@SuppressWarnings("unchecked")
	public List<PortletContextVO> selectList(PortletManagementVO vo) throws Exception {
		
		List l = contextDAO.getList4Deploy(vo);
		List<PortletContextVO> list = new ArrayList<PortletContextVO>();
		PortletContextVO context = null;
		for(int i=0;i<l.size();i++) {
			PortletManagementVO item = (PortletManagementVO)l.get(i);
			logger.debug("item.getContextName()=" + item.getContextName());
			try{
				// 주어진 conext name을 가지고 portlet.xml을 읽어들여 PortletContextVO를 셋팅
				context = (PortletContextVO)PortletXMLLoader.load(item.getDeployDescriptionXml()).get(0);
				context.setTenantId(item.getTenantId());
				context.setPortalId(item.getPortalId());
				context.setCategoryId(item.getCategoryId());
				context.setTypeOfPortlet(item.getTypeOfPortlet());
				item.setStatusCode(PortletConstant.DEPLOYED);
				contextDAO.update4undeploy(item);
			}catch(Exception e){
				item.setStatusCode(PortletConstant.UNDEPLOYED_BY_FAULT);
				item.setException(e.getMessage());
				contextDAO.update4undeploy(item);
				logger.warn(e.getMessage(), e);
				continue;
			}
			// TODO: context 의 버전과 DB를 비교 version up 된 경우는 등록절차를 다시 수행
			
			// 만들어진 PortletContextVO를 ArrayList에 담아서 return;
			list.add(context);
		}
		return list;
	}


	public void clear()  {
		try{
			PortletManagementVO param = new PortletManagementVO();
			PortletContextRegistry.reload(this.selectList(param));
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}
	}


	public void remove(Object key) {
	}
	
	public int size() {
		return 0;
    }

}
