package com.sds.acube.luxor.common.service;

import java.util.List;
import javax.jws.WebService;
import org.anyframe.pagination.Page;

import com.sds.acube.luxor.common.vo.ContentsStatisticsVO;
import com.sds.acube.luxor.common.vo.LoginHistoryVO;
import com.sds.acube.luxor.common.vo.PageStatisticsVO;

@WebService
public interface StatisticsService {
	
	public Page getLoginHistoryList(LoginHistoryVO vo) throws Exception;
	public boolean deleteLoginHistory(LoginHistoryVO vo) throws Exception;
	public boolean deleteLoginHistoryAll(LoginHistoryVO vo) throws Exception;
	public Page getPageStatisticsList(PageStatisticsVO vo) throws Exception;
	public Page getContentsStatisticsList(ContentsStatisticsVO vo) throws Exception;
	public boolean insertPageStatistics(PageStatisticsVO vo) throws Exception;
	public boolean insertContentsStatistics(ContentsStatisticsVO vo) throws Exception;
	public boolean deletePageStatistics(PageStatisticsVO vo) throws Exception;
	public boolean deletePageStatisticsAll(PageStatisticsVO vo) throws Exception;
	public List<PageStatisticsVO> getPageStatisticsListWithoutPaging(PageStatisticsVO vo) throws Exception;
	public List<LoginHistoryVO> getLoginHistoryListWithoutPaging(LoginHistoryVO vo) throws Exception;
	
	
}
