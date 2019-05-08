package com.obd.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public long streamCode =  00000001;
	/***
	 * 将电线平台的时间转为Unix时间戳（非北京时间）
	 * @param dateStr
	 * @return Unix Timestamp 13
	 */
	public static String Date2TimeStamp(String dateStr) {
        try {
        		String format = "yyyyMMdd'T'HHmmss'Z'";
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(dateStr).getTime()+28800000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
	
	public static String generateRequestTime() {
		
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
		//System.out.println(dateformat.format(date));
		return dateformat.format(date);
		
	}
	
	public static String generateExchangeCode() {
		//10位接口用户名+14位时间编码+8位流水码
		return Constant.jkYhm + generateRequestTime() + "00000001";
	}

}
