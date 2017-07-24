package com.sds.acube.luxor.portal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import com.sds.acube.luxor.common.service.AccessControllService;
import com.sds.acube.luxor.common.service.TreeService;
import com.sds.acube.luxor.common.vo.TreeVO;
import com.sds.acube.luxor.framework.controller.BaseController;
import com.sds.acube.luxor.portal.service.PortalContentService;
import com.sds.acube.luxor.portal.vo.PortalContentVO;
import com.sds.acube.luxor.ws.client.aclservice.AccessList;


public class PortalAclMapController extends BaseController {
	private TreeService treeService;
	private PortalContentService portalContentService;
	private AccessControllService accessControllService;
	private String mapPage;
	
	public void setTreeService(TreeService treeService) {
    	this.treeService = treeService;
    }

	public void setPortalContentService(PortalContentService portalContentService) {
		this.portalContentService = portalContentService;
	}
	
	public void setAccessControllService(AccessControllService accessControllService) {
		this.accessControllService = accessControllService;
	}

	public void setMapPage(String mapPage) {
    	this.mapPage = mapPage;
    }

	/**
	 * 페이지 트리와 페이지 각각의 권한정보를 검색
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getMap(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Page
		TreeVO treeVO = new TreeVO();
		bind(request, treeVO);

		treeVO.setTreeId("PORTAL_PAGE");
		
		TreeVO[] tree = treeService.getAclMapTree(treeVO);

		Map aclMap = new HashMap();
		
		for(int i=0; i < tree.length; i++) {
			String resourceId = tree[i].getNodeId();

			String accessInfo = accessControllService.getAccess(resourceId);
			
			AccessList accessList = new AccessList();
			if(accessInfo != null) {
				// 액세스 정보 문자를 AccessList 형태로 변환한다 
				accessList = accessControllService.makeAccessList(accessInfo);
			}

			aclMap.put(resourceId, accessList);
		}
		
		// Content
		PortalContentVO paramVO = new PortalContentVO();
		bind(request, paramVO);
		paramVO.setParentId("ROOT");
		paramVO.setRefer("name");
		List<PortalContentVO> list = portalContentService.getContentList(paramVO);

		for(int i=0; i < list.size(); i++) {
			PortalContentVO content = list.get(i);
			String resourceId = content.getContentId();
			
			String accessInfo = accessControllService.getAccess(resourceId);
			
			AccessList accessList = new AccessList();
			if(accessInfo != null) {
				// 액세스 정보 문자를 AccessList 형태로 변환한다 
				accessList = accessControllService.makeAccessList(accessInfo);
			}

			aclMap.put(resourceId, accessList);
		}

		PortalContentVO[] contentList = new PortalContentVO[list.size()];
		list.toArray(contentList);
		
		ModelAndView mav = new ModelAndView(mapPage);
		mav.addObject("pageList", tree);
		mav.addObject("contentList", contentList);
		mav.addObject("aclMap", aclMap);
		return mav;
	}

}
