package com.sds.acube.luxor.sample.dao;

import java.util.List;
import org.anyframe.pagination.Page;
import com.sds.acube.luxor.framework.dao.BaseDAO;
import com.sds.acube.luxor.sample.vo.MovieVO;


public class MovieDAO extends BaseDAO {
	
	public boolean insertMovie(MovieVO vo) throws Exception {
		return insert("insertMovie", vo) > 0;
	}
	
	public boolean updateMovie(MovieVO vo) throws Exception {
		return update("updateMovie", vo) > 0;
	}

	public boolean deleteMovie(MovieVO vo) throws Exception {
		return delete("deleteMovie", vo) > 0;
	}
	
	public MovieVO getMovie(MovieVO vo) throws Exception {
		return (MovieVO)select("getMovie", vo);
	}

	public int getMovieCount(MovieVO vo) throws Exception {
		return getCount("getMovieCount", "cnt", vo);
	}

	public List<MovieVO> getMovieList(MovieVO vo) throws Exception {
		return selectList("getMovieList", vo);
	}

	public Page getMovieListPage(MovieVO vo) throws Exception {
		return selectListPage("getMovieList", vo);
	}

}
