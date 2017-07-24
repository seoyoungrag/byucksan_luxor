/*
 * @(#) DateUtil.java 2010. 5. 7.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.common.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.sds.acube.luxor.common.ConstantList;
import com.sds.acube.luxor.framework.config.LuxorConfig;


/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.1.6.1 $ $Date: 2011/02/10 06:05:31 $
 */
public class DateUtil {

	/**
     * 17자리 현재 시간을 return 해줌
     * 
     * @return String : 현재 시간 "yyyyMMddhhmmssSSS"
     */
    public static String getCurrentTime17() {
        Calendar cal = Calendar.getInstance();
        String currentTime = null;
        currentTime = return0(cal.get(Calendar.YEAR)) + return0(cal.get(Calendar.MONTH) + 1) + return0(cal.get(Calendar.DAY_OF_MONTH)) 
             + return0(cal.get(Calendar.HOUR_OF_DAY)) + return0(cal.get(Calendar.MINUTE)) + return0(cal.get(Calendar.SECOND))
             + return00(cal.get(Calendar.MILLISECOND));

        return currentTime; // HHMMSS
    }
    
    /**
     * 주로 날짜 관련 1~9사이의 날짜 혹은 월 앞에 0을 붙여준다.
     * 
     * @param String
     * @return String : 현재 날짜 String "20000910"
     */
    public static String return0(String str) {
        if (str.length() == 1)
            str = "0" + str;
        return str;
    }
    
    /**
     * convert 1-9 to "001"~"009"
     * convert 11-99 to "011"~"099"
     * @param int
     * @return String
     */
    public static String return00(int num) {
        String str;
        if (num < 10) {
            str = "00" + num;
        } else if (num < 100) {
            str = "0" + num;
        } else {
            str = Integer.toString(num);
        }
        return str;
    }

    /**
     * convert 1-9 to "01"~"09"
     * 
     * @param int
     * @return String if param = 1 then return "01"
     */
    public static String return0(int num) {
        String str;
        if (num < 10)
            str = "0" + num;
        else
            str = Integer.toString(num);
        return str;
    }
	
    /**
     * @param strFromDate
     * @param strToDate
     * @return
     */
    public static String getTimeBetweenPeriod(String strFromDate, String strToDate) {
        StringBuffer strTime = new StringBuffer();
        int betweenHour = Integer.parseInt(strToDate.substring(0, 2)) - Integer.parseInt(strFromDate.substring(0, 2));
        int betweenMinute = Integer.parseInt(strToDate.substring(2, 4)) - Integer.parseInt(strFromDate.substring(2, 4));
        int betweenSecond = Integer.parseInt(strToDate.substring(4, 6)) - Integer.parseInt(strFromDate.substring(4, 6));
        int betweenMilliSecond = Integer.parseInt(strToDate.substring(6, 9)) - Integer.parseInt(strFromDate.substring(6, 9));
        
        if (betweenMilliSecond < 0) {
            betweenSecond = betweenSecond - 1;
            betweenMilliSecond = betweenMilliSecond + 1000;
        }
        if (betweenSecond < 0) {
            betweenMinute = betweenMinute - 1;
            betweenSecond = betweenSecond + 60;
        }
        if (betweenMinute < 0) {
            betweenHour = betweenHour - 1;
            betweenMinute = betweenMinute + 60;
        }
        
        strTime.append(return0(betweenHour)).append(return0(betweenMinute)).append(
                return0(betweenSecond)).append(return00(betweenMilliSecond));
        return strTime.toString();
    }
    
	public static String getCurrentDate() {
		String timezone = LuxorConfig.getString(ConstantList.DEFAULT_TIMEZONE);
		return getCurrentDate(timezone);
	}

	public static String getCurrentDate(String timezone) {
		String datetimeformat = "yyyyMMddHHmmss.SSS";
		Date currentdt = new Date();
		Timestamp ts = new Timestamp(currentdt.getTime());
		return getDateTime(timezone, datetimeformat, ts);
	}
	
	public static String getDateTime(String timezone, String datetimeformat, Timestamp dt) {
		SimpleDateFormat sdf = new SimpleDateFormat(datetimeformat);
		TimeZone tz = TimeZone.getTimeZone(timezone);
		sdf.setTimeZone(tz);
		return sdf.format(dt);
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
