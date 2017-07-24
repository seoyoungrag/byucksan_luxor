package com.sds.acube.luxor.sample.service.impl;

import java.util.List;
import org.anyframe.pagination.Page;
import com.sds.acube.luxor.common.service.AttachmentService;
import com.sds.acube.luxor.common.vo.AttachmentVO;
import com.sds.acube.luxor.framework.service.BaseService;
import com.sds.acube.luxor.sample.dao.MovieDAO;
import com.sds.acube.luxor.sample.service.MovieService;
import com.sds.acube.luxor.sample.vo.MovieVO;

public class MovieServiceImpl extends BaseService implements MovieService {
	private MovieDAO movieDAO;
	private AttachmentService attachmentService;
	
	public void setMovieDAO(MovieDAO MovieDAO) {
    	this.movieDAO = MovieDAO;
    }
	
	public void setAttachmentService(AttachmentService attachmentService) {
    	this.attachmentService = attachmentService;
    }

	public boolean insertMovie(MovieVO vo) throws Exception {
		return movieDAO.insertMovie(vo);
	}
	
	public boolean updateMovie(MovieVO vo) throws Exception {
		return movieDAO.updateMovie(vo);
	}
	
	public boolean deleteMovie(MovieVO vo) throws Exception {
		AttachmentVO attachVO = new AttachmentVO();
		attachVO.setTenantId(vo.getTenantId());
		attachVO.setPortalId(vo.getPortalId());
		attachVO.setAttachmentId(vo.getAttachmentId());
		return attachmentService.delete(attachVO) && movieDAO.deleteMovie(vo);
	}
	
	public MovieVO getMovie(MovieVO vo) throws Exception {
		return movieDAO.getMovie(vo);
	}

	public int getMovieCount(MovieVO vo) throws Exception {
		return movieDAO.getMovieCount(vo);
	}
	
	public List<MovieVO> getMovieList(MovieVO vo) throws Exception {
		return movieDAO.getMovieList(vo);
	}

	public Page getMovieListPage(MovieVO vo) throws Exception {
		return movieDAO.getMovieListPage(vo);
	}
	
}
