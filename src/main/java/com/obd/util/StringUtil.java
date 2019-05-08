package com.obd.util;

public class StringUtil {

    public static boolean strIsNullOrEmpty(String s) {
        return (null == s || s.trim().length() < 1);
    }
    /**
     * 字符串左侧补0
     * @param code
     * @param num
     * @return
     */
    public static String paddingLeft(String code,int len ) {
    	    String result = "";
    	    int num = len-code.length();

        result = String.format("%0"+num+"d", 0)+code;

        return result;
    }
    
    public static void main(String[] args) {
    	    System.out.println(paddingLeft("cd",4));
    }

}

