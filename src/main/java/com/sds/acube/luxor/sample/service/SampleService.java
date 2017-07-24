package com.sds.acube.luxor.sample.service;

import org.anyframe.pagination.Page;
import com.sds.acube.luxor.sample.vo.SampleVO;

public interface SampleService {
	
	public boolean insertSample(SampleVO vo) throws Exception;
	
	public boolean updateSample(SampleVO vo) throws Exception;
	
	public boolean deleteSample(SampleVO vo) throws Exception;
	
	public SampleVO getSample(SampleVO vo) throws Exception;
	
	public Page getSampleListPage(SampleVO vo) throws Exception;
	
}
