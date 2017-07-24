package com.sds.acube.luxor.common.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;

public class LuxorLogAspect {
	Log logger = LogFactory.getLog(LuxorLogAspect.class);
	
	public void beforeCreate() {}

	public void log4Exception(JoinPoint thisJoinPoint, Exception exception) {
		logger.error(exception.getMessage(), exception);
	}
	
	public void log4BeforeService(final JoinPoint thisJoinPoint) {
		String opName = (thisJoinPoint.getSignature().getName());
    	Object[] args = thisJoinPoint.getArgs();
    	
    	String arguments = "";
    	for(Object arg : args) {
    		if(arg==null) {
    			continue;
    		}
    		arguments += ","+arg.toString();
    	}
    	
    	logger.debug(opName+"("+arguments+")");
    }
}
