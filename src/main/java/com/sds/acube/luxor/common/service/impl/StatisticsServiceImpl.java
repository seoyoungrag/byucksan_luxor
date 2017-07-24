package com.sds.acube.luxor.common.service.impl;

import java.util.List;
import org.anyframe.pagination.Page;

import com.sds.acube.luxor.common.dao.StatisticsDAO;
import com.sds.acube.luxor.common.service.StatisticsService;
import com.sds.acube.luxor.common.vo.ContentsStatisticsVO;
import com.sds.acube.luxor.common.vo.LoginHistoryVO;
import com.sds.acube.luxor.common.vo.PageStatisticsVO;
import com.sds.acube.luxor.framework.service.BaseService;

public class StatisticsServiceImpl extends BaseService implements StatisticsService {
	private StatisticsDAO statisticsDAO;

	public void setStatisticsDAO(StatisticsDAO statisticsDAO) {
    	this.statisticsDAO = statisticsDAO;
    }

	public Page getLoginHistoryList(LoginHistoryVO vo) throws Exception {
		return statisticsDAO.getLoginHistoryList(vo);
	}

	public boolean deleteLoginHistory(LoginHistoryVO vo) throws Exception {
		
		boolean result = statisticsDAO.deleteLoginHistory(vo);
		if(result==false) {
			throw new Exception("Fail to delete!!");
		}
		return result;
	}

	public boolean deleteLoginHistoryAll(LoginHistoryVO vo) throws Exception {
		
		boolean result = statisticsDAO.deleteLoginHistoryAll(vo);
		if(result==false) {
			throw new Exception("Fail to delete!!");
		}
		return result;
	}

	public Page getPageStatisticsList(PageStatisticsVO vo) throws Exception {
		return statisticsDAO.getPageStatisticsList(vo);
	}

	public Page getContentsStatisticsList(ContentsStatisticsVO vo) throws Exception {
		return statisticsDAO.getContentsStatisticsList(vo);
	}

	public boolean insertPageStatistics(PageStatisticsVO vo) throws Exception {
		
		String strUserAgent = vo.getUserAgent();
		if(strUserAgent!=null) {
			if(strUserAgent.getBytes("UTF-8").length > 1000) {
				vo.setUserAgent(new String(strUserAgent.getBytes("UTF-8"),0,1000,"UTF-8"));
			}
		}
		boolean result = statisticsDAO.insertPageStatistics(vo);
		if(result==false) {
			throw new Exception("Fail to insert!!");
		}
		return result;
	}

	public boolean insertContentsStatistics(ContentsStatisticsVO vo) throws Exception {
		
		boolean result = statisticsDAO.insertContentsStatistics(vo);
		if(result==false) {
			throw new Exception("Fail to insert!!");
		}
		return result;
	}

	public boolean deletePageStatistics(PageStatisticsVO vo) throws Exception {
		
		boolean result = statisticsDAO.deletePageStatistics(vo);
		if(result==false) {
			throw new Exception("Fail to delete!!");
		}
		return result;
	}

	public boolean deletePageStatisticsAll(PageStatisticsVO vo) throws Exception {
		
		boolean result = statisticsDAO.deletePageStatisticsAll(vo);
		if(result==false) {
			throw new Exception("Fail to delete!!");
		}
		return result;
	}

	public List<LoginHistoryVO> getLoginHistoryListWithoutPaging(LoginHistoryVO vo) throws Exception {
	    return statisticsDAO.getLoginHistoryListWithoutPaging(vo);
    }

	public List<PageStatisticsVO> getPageStatisticsListWithoutPaging(PageStatisticsVO vo) throws Exception {
		return statisticsDAO.getPageStatisticsWithoutPaging(vo);
    }
	

}
