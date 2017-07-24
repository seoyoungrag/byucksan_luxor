/*
 * @(#) ObjectUtil.java 2001-11-22
 * Copyright (c) 2002 Samsung SDS, Co., Ltd. All Rights Reserved.
 */

package com.sds.acube.luxor.common.util;

/**
 * @author  MyungHee Jung
 * @version $Revision: 1.1.6.1 $
 * Last Revised $Date: 2011/02/10 06:05:31 $
 */
public class ObjectUtil {

	/////////////////////////////////////////////////////////////////////////
		
	public static Object instantiateClass(String className) throws Exception {
		Object obj = null;

		try {
			obj = Class.forName(className).newInstance();
		} catch (ClassNotFoundException cnfex) {
			throw cnfex;
		} catch (IllegalAccessException iaex) {
			throw iaex;
		} catch (InstantiationException iex) {
			throw iex;
		} 
		return obj;
	}
}
