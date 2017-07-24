/*
 * @(#) AttachmentDAO.java 2010. 8. 9.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.common.dao;

import java.util.List;

import com.sds.acube.luxor.common.vo.AttachmentVO;
import com.sds.acube.luxor.common.vo.AttachmentVOs;
import com.sds.acube.luxor.framework.dao.BaseDAO;


/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.1.6.1 $ $Date: 2011/02/10 06:05:42 $
 */
@SuppressWarnings("unchecked")
public class AttachmentDAO extends BaseDAO {
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int insert(AttachmentVO param) throws Exception {
		return super.insert("com.sds.acube.luxor.common.attachment.insert", param);
	}
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int delete(AttachmentVO param) throws Exception {
		return super.delete("com.sds.acube.luxor.common.attachment.delete", param);
	}
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int deleteGroup(AttachmentVOs param) throws Exception {
		return super.delete("com.sds.acube.luxor.common.attachment.deleteGroup", param);
	}
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public AttachmentVO get(AttachmentVO param) throws Exception {
		return (AttachmentVO)super.select("com.sds.acube.luxor.common.attachment.get", param);
	}
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<AttachmentVO> getList(AttachmentVOs param) throws Exception {
		return (List<AttachmentVO>)super.selectList("com.sds.acube.luxor.common.attachment.getList", param);
	}
	
}
