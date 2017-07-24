package com.sds.acube.luxor.framework.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 모든 Service가 상속받는 기본 Service임
 * 기본적으로 logger를 정의하고 있으므로 각 Service에서 여기에 정의된 logger를 사용하면 됨
 */
public class BaseService {
	protected Log logger = LogFactory.getLog(BaseService.class);
}
