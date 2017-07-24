/*
 * @(#) PortletManagementService.java 2010. 5. 11.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.portal.service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.anyframe.pagination.Page;
import com.sds.acube.luxor.common.vo.TreeVO;
import com.sds.acube.luxor.portal.vo.PortletCategoryVO;
import com.sds.acube.luxor.portal.vo.PortletContextVO;
import com.sds.acube.luxor.portal.vo.PortletManagementVO;


/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.2.2.2 $ $Date: 2011/03/17 07:47:26 $
 */
@WebService
public interface PortletManagementService {
	
	/**
	 * 포틀릿 등록
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@WebMethod(exclude=true)
	public boolean portletRegister(PortletManagementVO param) throws Exception;
	
	
	/**
	 * 등록된 포틀릿 목록조회
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@WebMethod(exclude=true)
	public List<PortletManagementVO> deployStatusInfo(PortletManagementVO param) throws Exception;
	
	
	/**
	 * 등록된 포틀릿 목록조회 (페이징 처리)
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Page deployStatusInfoPage(PortletManagementVO param) throws Exception;
	
	
	/**
	 * 포틀릿 조회
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public PortletContextVO getPortletContextInfo(PortletManagementVO param) throws Exception;
	
	/**
	 * 포틀릿 redeploy
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@WebMethod(exclude=true)
	public boolean redeploy(PortletManagementVO param) throws Exception;
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@WebMethod(exclude=true)
	public boolean redeployAll(PortletManagementVO param) throws Exception;
	
	/**
	 * 포틀릿 undeploy
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@WebMethod(exclude=true)
	public boolean undeploy(PortletManagementVO param) throws Exception;
	
	/**
	 * 포틀릿 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@WebMethod(exclude=true)
	public boolean delete(PortletManagementVO param) throws Exception;
	
	/**
	 * @param param
	 * @return
	 */
	public PortletCategoryVO[] getPortletCategoryList(PortletManagementVO param) throws Exception;

	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public TreeVO[] getPortletCategoryTree(PortletManagementVO param) throws Exception;
	
	/**
	 * @param param
	 * @return
	 */
	@WebMethod(exclude=true)
	public PortletCategoryVO addCategory(PortletCategoryVO param) throws Exception;


	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@WebMethod(exclude=true)
	public List<PortletContextVO> loadXmlInfo(PortletManagementVO param) throws Exception;


	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@WebMethod(exclude=true)
	public boolean portletRegisterXMLConfirm(PortletManagementVO param) throws Exception;
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public String exportXML(PortletManagementVO param) throws Exception;


	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public PortletManagementVO getDeployDescriptionXML(PortletManagementVO param) throws Exception;


	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@WebMethod(exclude=true)
	public boolean updateDeployDescriptionXML(PortletManagementVO param) throws Exception;


	/**
	 * 포틀릿 카테고리 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@WebMethod(exclude=true)
	public int deletePortletCategory(PortletManagementVO param) throws Exception;
	
	/**
	 * 포틀릿 카테고리 정보
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public PortletCategoryVO getPortletCategoryInfo(PortletManagementVO param) throws Exception;
	
	/**
	 * 포틀릿 카테고리 업데이트
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@WebMethod(exclude=true)
	public PortletCategoryVO updateCategory(PortletCategoryVO param) throws Exception;


	/**
	 * 중복 체크
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public String checkDup(PortletManagementVO param) throws Exception;
}
