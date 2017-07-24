package com.sds.acube.luxor.sample.service;

import java.util.List;
import org.anyframe.pagination.Page;
import com.sds.acube.luxor.sample.vo.MovieVO;

public interface MovieService {
	
	public boolean insertMovie(MovieVO vo) throws Exception;
	
	public boolean updateMovie(MovieVO vo) throws Exception;
	
	public boolean deleteMovie(MovieVO vo) throws Exception;
	
	public MovieVO getMovie(MovieVO vo) throws Exception;
	
	public int getMovieCount(MovieVO vo) throws Exception;
	
	public List<MovieVO> getMovieList(MovieVO vo) throws Exception;
	
	public Page getMovieListPage(MovieVO vo) throws Exception;
	
}
