package com.sds.acube.luxor.sample.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.anyframe.pagination.Page;
import org.anyframe.query.QueryService;
import com.sds.acube.luxor.framework.dao.BaseDAO;
import com.sds.acube.luxor.sample.vo.SampleVO;


public class SampleDAO extends BaseDAO {
	
	public boolean insertSample(SampleVO vo) throws Exception {
		vo.setContentLob(vo.getContent().getBytes());
		return insert("insertSample", vo) > 0;
	}
	
	public boolean updateSample(SampleVO vo) throws Exception {
		return update("updateSample", vo) > 0;
	}

	public boolean deleteSample(SampleVO vo) throws Exception {
		return delete("deleteSample", vo) > 0;
	}
	
	public SampleVO getSample(SampleVO vo) throws Exception {
		return (SampleVO)select("getSample", vo);
	}

	public int getSampleCount(SampleVO vo) throws Exception {
		return getCount("getSampleCount", "cnt", vo);
	}

	public SampleVO[] getSampleList(SampleVO vo) throws Exception {
		List list = selectList("getSampleList2", vo);
		SampleVO[] sampleVO = new SampleVO[list.size()];
		list.toArray(sampleVO);
		return sampleVO;
	}

	public Page getSampleListPage(SampleVO vo) throws Exception {
		Object[] obj = new Object[1];
		obj[0] = "table="+vo.getName();
		Map map = queryService.findWithRowCount("getSampleList2", obj, 0, 100);
		Page page = new Page((ArrayList)map.get(QueryService.LIST), vo.getcPage(), ((Long)map.get(QueryService.COUNT)).intValue());
		
		List list = (List)page.getList();
		logger.debug("## MRJH DEBUG ##  list.size() : "+list.size());
		
		for(int i=0; i < list.size(); i++) {
			logger.debug("## MRJH DEBUG ##  list.get(i).toString() : "+list.get(i).toString());
		}
		
		return page;
	}

}
