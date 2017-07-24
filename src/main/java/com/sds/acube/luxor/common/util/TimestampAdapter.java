/*
 * @(#) TimestampAdapter.java 2010. 6. 11.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.common.util;

import java.util.Date;
import java.sql.Timestamp;

import javax.xml.bind.annotation.adapters.XmlAdapter;


/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.1.6.1 $ $Date: 2011/02/10 06:05:31 $
 */
public class TimestampAdapter extends XmlAdapter<Date, Timestamp> {
	
	public Date marshal(Timestamp v) {
        return new Date(v.getTime());
    }
	
    public Timestamp unmarshal(Date v) {
        return new Timestamp(v.getTime());
    }
}
