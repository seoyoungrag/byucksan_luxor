package com.sds.acube.luxor.common.dao;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Date;

import org.anyframe.pagination.Page;

import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.vo.ContentsStatisticsVO;
import com.sds.acube.luxor.common.vo.LoginHistoryVO;
import com.sds.acube.luxor.common.vo.PageStatisticsVO;
import com.sds.acube.luxor.framework.dao.BaseDAO;

public class StatisticsDAO extends BaseDAO {

	public Page getLoginHistoryList(LoginHistoryVO vo) throws Exception {
		return selectListPage("getLoginHistoryList", vo);
	}

	public boolean deleteLoginHistory(LoginHistoryVO vo) throws Exception {
		return delete("deleteLoginHistory", vo) > 0;
	}

	public boolean deleteLoginHistoryAll(LoginHistoryVO vo) throws Exception {
		vo.setSearchTxt(CommonUtil.getEscapeString(vo.getSearchTxt(), dbType));
		boolean result = delete("deleteLoginHistoryAll", vo) > 0;
		vo.setSearchTxt(CommonUtil.getResetEscapeString(vo.getSearchTxt()));
		return result;
	}

	public Page getPageStatisticsList(PageStatisticsVO vo) throws Exception {
		vo.setSearchTxt(CommonUtil.getEscapeString(vo.getSearchTxt(), dbType));
		Page result = selectListPage("getPageStatisticsList", vo);
		vo.setSearchTxt(CommonUtil.getResetEscapeString(vo.getSearchTxt()));
		return result;
	}
	
	public boolean deletePageStatistics(PageStatisticsVO vo) throws Exception {
		return delete("deletePageStatistics", vo) > 0;
	}

	public boolean deletePageStatisticsAll(PageStatisticsVO vo) throws Exception {
		vo.setSearchTxt(CommonUtil.getEscapeString(vo.getSearchTxt(), dbType));
		boolean result = delete("deletePageStatisticsAll", vo) > 0;
		vo.setSearchTxt(CommonUtil.getResetEscapeString(vo.getSearchTxt()));
		return result;
	}

	public Page getContentsStatisticsList(ContentsStatisticsVO vo) throws Exception {
		vo.setSearchTxt(CommonUtil.getEscapeString(vo.getSearchTxt(), dbType));
		Page result = selectListPage("getContentsStatisticsList", vo);
		vo.setSearchTxt(CommonUtil.getResetEscapeString(vo.getSearchTxt()));
		return result;
	}

	public boolean insertPageStatistics(PageStatisticsVO vo) throws Exception {
        Date date = new Date();
        Object[] param = new Object[]{new java.sql.Timestamp(date.getTime())};
        Method method = vo.getClass().getMethod("setAccessTime", java.sql.Timestamp.class);
        method.invoke(vo, param);
		return insert("insertPageStatistics", vo) > 0;
	}

	public boolean insertContentsStatistics(ContentsStatisticsVO vo) throws Exception {
        Date date = new Date();
        Object[] param = new Object[]{new java.sql.Timestamp(date.getTime())};
        Method method = vo.getClass().getMethod("setAccessTime", java.sql.Timestamp.class);
        method.invoke(vo, param);
		return insert("insertContentsStatistics", vo) > 0;
	}
	
	public List<LoginHistoryVO> getLoginHistoryListWithoutPaging(LoginHistoryVO vo) throws Exception {
		
		vo.setSearchTxt(CommonUtil.getEscapeString(vo.getSearchTxt(), dbType));
		List<LoginHistoryVO> result =  (List<LoginHistoryVO>)super.selectList("getLoginHistoryList", vo);
		vo.setSearchTxt(CommonUtil.getResetEscapeString(vo.getSearchTxt()));
		return result;
	}
	
	public List<PageStatisticsVO> getPageStatisticsWithoutPaging(PageStatisticsVO vo) throws Exception {
		return (List<PageStatisticsVO>)super.selectList("getPageStatisticsList", vo);
	}
	
}
