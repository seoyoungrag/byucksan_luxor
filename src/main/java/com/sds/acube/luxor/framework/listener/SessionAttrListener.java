package com.sds.acube.luxor.framework.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 세션의 값이 추가되거나 제거될때 이벤트를 받아서 추가적인 작업 정의
 * @author ole2000
 *
 */
public class SessionAttrListener implements HttpSessionAttributeListener {
	Log logger = LogFactory.getLog(SessionAttrListener.class);

	public void attributeAdded(HttpSessionBindingEvent event) {
		String attributeName = event.getName();
		Object attributeValue = event.getValue();
		logger.debug("******** Session Attribute added : " + attributeName + " : " + attributeValue);		
	}

	public void attributeRemoved(HttpSessionBindingEvent event) {
		String attributeName = event.getName();
		Object attributeValue = event.getValue();
		logger.debug("******** Session Attribute removed : " + attributeName + " : " + attributeValue);
	}

	public void attributeReplaced(HttpSessionBindingEvent event) {
        String attributeName = event.getName();
        Object attributeValueFrom = event.getValue();
        Object attributeValueTo = event.getSession().getAttribute(attributeName);
        logger.debug("******** Session Attribute replaced : " + attributeName + " : " + attributeValueFrom + " --> " + attributeValueTo);
	}
	
}
