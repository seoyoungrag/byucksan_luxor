/*
 * @(#) AttachmentServiceImpl.java 2010. 8. 9.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.common.service.impl;

import java.util.List;
import com.sds.acube.luxor.common.dao.AttachmentDAO;
import com.sds.acube.luxor.common.dao.JstoreDAO;
import com.sds.acube.luxor.common.service.AttachmentService;
import com.sds.acube.luxor.common.vo.AttachmentVO;
import com.sds.acube.luxor.common.vo.AttachmentVOs;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.service.BaseService;


/**
 * 
 * @author Alex, Eum
 * @version $Revision: 1.2.2.6 $ $Date: 2011/12/13 02:02:59 $
 */
public class AttachmentServiceImpl extends BaseService implements AttachmentService {
	private static String tempPath = LuxorConfig.getString("Common", "ATTACH.UPLOAD_TEMP");
	private AttachmentDAO dao;


	/**
	 * @param dao The dao to set.
	 */
	public void setDao(AttachmentDAO dao) {
		this.dao = dao;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sds.acube.luxor.common.service.AttachmentService#apply(com.sds.acube.luxor.common.vo.AttachmentVOs)
	 */
	public boolean apply(AttachmentVOs param) throws Exception {
		boolean r = false;
		String[] localFileNames = param.getLocalFileName();
		String[] serverFileNames = param.getServerFileName();
		String[] serverPaths = param.getServerPath();
		String[] storeFileId = param.getStoreFileId();
		boolean[] isNew = param.getIsNew();
		long[] fileSizes = param.getFileSize();
		int[] seqs = param.getSeq();
		JstoreDAO jstoreDao = new JstoreDAO();
		try {
			// 기존 데이타 삭제
			dao.deleteGroup(param);
			if (serverPaths != null || param.getDeleted() != null) {
				jstoreDao.connect();
			}
			if (serverPaths != null) {
				for (int i = 0; i < serverPaths.length; i++) {
					String sFileId = null;
					if (isNew[i]) { // 새로 추가된 파일 저장서버 저장
						sFileId = jstoreDao.registerFile(serverPaths[i]);
					} else {
						sFileId = storeFileId[i];
					}
					AttachmentVO item = new AttachmentVO();
					item.setTenantId(param.getTenantId());
					item.setPortalId(param.getPortalId());
					item.setAttachmentId(param.getAttachmentId());
					item.setSeq(seqs[i] - 1);
					item.setModuleDiv(param.getModuleDiv());
					item.setClientFilename(localFileNames[i]);
					item.setServerFilename(serverFileNames[i]);
					item.setFileSize(fileSizes[i]);
					item.setStoreFileId(sFileId);
					// 파일정보 저장
					dao.insert(item);
				}
			}
			// 삭제된 파일들 저장 서버에서 삭제
			if (param.getDeleted() != null) {
				if (param.getDeleted().length > 0) {
					jstoreDao.deleteFiles(param.getDeleted());
				}
			}
			jstoreDao.commit();
			r = true;
		} catch (Exception e) {
			jstoreDao.rollback();
			throw e;
		} finally {
			jstoreDao.disconnect();
		}
		return r;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sds.acube.luxor.common.service.AttachmentService#download(com.sds.acube.luxor.common.vo.AttachmentVOs)
	 */
	public boolean download(AttachmentVOs param) throws Exception {
		boolean r = false;
		int[] seqs = param.getSeq();
		JstoreDAO jstoreDao = null;
		if (seqs.length > 0) {
			try {
				jstoreDao = new JstoreDAO();
				jstoreDao.connect();
				AttachmentVO item = new AttachmentVO();
				item.setTenantId(param.getTenantId());
				item.setPortalId(param.getPortalId());
				item.setAttachmentId(param.getAttachmentId());
				for (int i = 0; i < seqs.length; i++) {
					item.setSeq(seqs[i]);
					AttachmentVO file = dao.get(item);
					jstoreDao.getFile(tempPath + "/" + file.getServerFilename(), file.getStoreFileId());
				}
				jstoreDao.commit();
				r = true;
			} catch (Exception e) {
				jstoreDao.rollback();
				logger.warn(e.getMessage(), e);
			} finally {
				jstoreDao.disconnect();
			}
		}
		return r;
	}

	public boolean delete(AttachmentVO attachVO) throws Exception {
		boolean result = false;
		JstoreDAO jstoreDao = null;

		try {
			jstoreDao = new JstoreDAO();
			jstoreDao.connect();

	        AttachmentVOs attachVOs = new AttachmentVOs();
	        attachVOs.setTenantId(attachVO.getTenantId());
	        attachVOs.setPortalId(attachVO.getPortalId());
	        attachVOs.setAttachmentId(attachVO.getAttachmentId());
	        
	        List<AttachmentVO> attachList = getList(attachVOs);
	        
	        String[] storIDs = new String[attachList.size()];
	        for(int i=0; i < attachList.size(); i++) {
	        	AttachmentVO tempVO = attachList.get(i);
	        	
	        	// 저장서버 ID 셋팅
	        	storIDs[i] = tempVO.getStoreFileId();
	        	
	        	// tlxr_attachment 테이블에서 해당 정보 삭제
	        	dao.delete(tempVO);
	        }
	     // 저장서버에서 파일 삭제
			int flag = jstoreDao.deleteFiles(storIDs);
			if(flag >=0) {
				result = true;
				
				// Commit
		        jstoreDao.commit();
			}
		} catch (Exception e) {
			result = false;
			jstoreDao.rollback();
			logger.warn(e.getMessage(), e);
		} finally {
			jstoreDao.disconnect();
		}
		
		return result;
	}
	
	public boolean delete(AttachmentVOs param) throws Exception {
		boolean result = false;
		JstoreDAO jstoreDao = null;

		try {
			jstoreDao = new JstoreDAO();
			jstoreDao.connect();
	        
			String[] localFileNames = param.getLocalFileName();
			String[] serverFileNames = param.getServerFileName();
			String[] serverPaths = param.getServerPath();
			String[] storeFileId = param.getStoreFileId();
			boolean[] isNew = param.getIsNew();
			long[] fileSizes = param.getFileSize();
			int[] seqs = param.getSeq();
	        
	        String[] storIDs = new String[serverPaths.length];
	        for(int i=0; i < serverPaths.length; i++) {
	        	// 저장서버 ID 셋팅
	        	storIDs[i] = storeFileId[i];
	        	
	        	AttachmentVO item = new AttachmentVO();
				item.setTenantId(param.getTenantId());
				item.setPortalId(param.getPortalId());
				item.setAttachmentId(param.getAttachmentId());
				item.setModuleDiv(param.getModuleDiv());
				item.setClientFilename(localFileNames[i]);
				item.setServerFilename(serverFileNames[i]);
				item.setFileSize(fileSizes[i]);
				item.setStoreFileId(storeFileId[i]);
	        	// tlxr_attachment 테이블에서 해당 정보 삭제
	        	dao.delete(item);
	        }
	        
			// 저장서버에서 파일 삭제
			int flag = jstoreDao.deleteFiles(storIDs);
			if(flag >=0) {
				result = true;
				
				// Commit
		        jstoreDao.commit();
			}
		} catch (Exception e) {
			result = false;
			jstoreDao.rollback();
			logger.warn(e.getMessage(), e);
		} finally {
			jstoreDao.disconnect();
		}
		
		return result;
	}
	
	
	public AttachmentVO getFileInfo(AttachmentVO param) throws Exception {
		return dao.get(param);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sds.acube.luxor.common.service.AttachmentService#getList(com.sds.acube.luxor.common.vo.AttachmentVOs)
	 */
	public List<AttachmentVO> getList(AttachmentVOs param) throws Exception {
		return dao.getList(param);
	}
}
