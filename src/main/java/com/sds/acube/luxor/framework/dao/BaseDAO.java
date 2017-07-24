package com.sds.acube.luxor.framework.dao;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.anyframe.pagination.Page;
import org.anyframe.query.QueryService;
import org.anyframe.query.QueryServiceException;

import com.sds.acube.luxor.common.vo.CommonVO;
import com.sds.acube.luxor.framework.config.LuxorConfig;

/**
 * 모든 DAO가 상속받는 기본 DAO임 
 * @param <T>
 */
public class BaseDAO<T> {
	protected Log logger = LogFactory.getLog(BaseDAO.class);
	
	protected QueryService queryService;
	
	public int dbType = Integer.parseInt(LuxorConfig.getString("BASIC.DATABASE"));
	
	public void setQueryService(QueryService queryService) {
    	this.queryService = queryService;
    }

	
	/**
	 * insert state query시 사용
	 * 
	 * @param queryId
	 * @param vo
	 * @return 수행된 Row count
	 * @throws QueryServiceException
	 */
	protected int insert(String queryId, Object vo) throws QueryServiceException {
		try {
	        Date date = new Date();
	        Object[] param = new Object[]{new java.sql.Timestamp(date.getTime())};
	        Method method = vo.getClass().getMethod("setRegDate", java.sql.Timestamp.class);
	        method.invoke(vo, param);
        } catch (Exception e) {}

		return queryService.create(queryId, new Object[]{new Object[]{"vo", vo}});
	}

	
	/**
	 * update state query시 사용
	 * 
	 * @param queryId
	 * @param vo
	 * @return 수행된 Row count
	 * @throws QueryServiceException
	 */
	protected int update(String queryId, Object vo) throws QueryServiceException {
		try {
			// Set Mod Date
			Date date = new Date();
			Object[] param = new Object[]{new java.sql.Timestamp(date.getTime())};
			Method method = vo.getClass().getMethod("setModDate", java.sql.Timestamp.class);
			method.invoke(vo, param);

			// Set Mod User Id
			Method method2 = vo.getClass().getMethod("getRegUserId");
			String userId = (String)method2.invoke(vo);
			
			Object[] param2 = new Object[]{userId};
			Method method3 = vo.getClass().getMethod("setModUserId", String.class);
			method3.invoke(vo, param2);
		} catch (Exception e) {}

		return queryService.update(queryId, new Object[]{new Object[]{"vo", vo}});
	}

	
	/**
	 * delete state query시 사용
	 * 
	 * @param queryId
	 * @param vo
	 * @return 수행된 Row count
	 * @throws QueryServiceException
	 */
	protected int delete(String queryId, Object vo) throws QueryServiceException {
		return queryService.remove(queryId, new Object[]{new Object[]{"vo", vo}});
	}

	
	/**
	 * select state query시 결과 row 가 1인 경우 (하나의 결과값)
	 * 
	 * @param queryId
	 * @param vo
	 * @return Object (사용하는 곳에서 Casting 하여 사용)
	 * @throws QueryServiceException
	 */
	protected T select(String queryId, Object vo) throws QueryServiceException {
		ArrayList result = (ArrayList)queryService.find(queryId, new Object[]{new Object[]{"vo", vo}});
		if(result.size() > 0) {
			return (T)result.get(0);
		} else {
			return null;
		}
	}

	
	/**
	 * select state query시 결과 row 가 1 이상인 경우 (다수의 결과값)
	 * 
	 * @param queryId
	 * @param vo
	 * @return List (사용하는 곳에서 toArray로 Casting 하여 사용)
	 * @throws QueryServiceException
	 */
	protected List selectList(String queryId, Object vo) throws QueryServiceException {
		return (List)queryService.find(queryId, new Object[]{new Object[]{"vo", vo}});
	}
	
	
	/**
	 * 페이지 리스팅을 할때 사용
	 * 이 메소드를 호출할때 사용되는 VO는 반드시 CommonVO를 상속하고 있어야 함
	 * 
	 * @param queryId
	 * @param CommonVO vo
	 * @return Page
	 * @throws QueryServiceException
	 */
	protected Page selectListPage(String queryId, CommonVO vo) throws QueryServiceException {
        Map map = queryService.findWithRowCount(queryId, new Object[]{new Object[]{"vo",vo}}, vo.getcPage(), vo.getPageSize());
        Page page = new Page((ArrayList)map.get(QueryService.LIST), vo.getcPage(), ((Long)map.get(QueryService.COUNT)).intValue());
        
        // Anyframe Bug 같은데? 응? 응?
        // 위에서 Page 생성자에 Current Page 정보 넘겨주는데 셋팅이 안됨
        // 아래 setCurrentPage를 한번 더 호출 해줘야 함
        page.setCurrentPage(vo.getcPage());
        
        return page;
	}

	
	/**
	 * 단순 카운트 조회시 사용 ex) select count(*) as cnt from user
	 * 
	 * @param queryId
	 * @param column count의 alias값에 해당
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	protected int getCount(String queryId, String column, Object vo) throws Exception {
		ArrayList result = (ArrayList)queryService.find(queryId, new Object[]{new Object[]{"vo", vo}});
		Map map = (Map)result.get(0);
		if(map.get(column)==null) {
			return 0;
		} else {
			if (map.get(column) instanceof BigDecimal) {
				return ((BigDecimal) map.get(column)).intValue();				
			} else {
				return ((Integer) map.get(column)).intValue();	
			}
		}
	}
	
}
