/*
 * @(#) BaseHandle.java 2010. 7. 14.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.framework.listener;


/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.1.6.1 $ $Date: 2011/02/10 06:06:07 $
 */
public interface BaseHandle extends Runnable {

	public void start();
	public void stop();
}
