package com.obd.util;

import java.io.UnsupportedEncodingException;
import org.json.JSONObject;
import org.json.JSONTokener;
/**
 * url转码、解码
 *
 * @author lifq 
 * @date 2015-3-17 下午04:09:35
 */
public class UrlUtil {
    private final static String ENCODE = "GBK"; 
    /**
     * URL 解码
     *
     * @return String
     * @author lifq
     * @date 2015-3-17 下午04:09:51
     */
    public static String getURLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * URL 转码
     *
     * @return String
     * @author lifq
     * @date 2015-3-17 下午04:10:28
     */
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 
     * @return void
     * @author lifq
     * @date 2015-3-17 下午04:09:16
     */
    public static void main(String[] args) {
        String str = "%7B%22name%22:%22ann%22%7D";
        String str2 = "%7Bname:ann%7D";
//        String str3 = "{\"name\":\"ann\"}";
//        System.out.println(str3);
//        System.out.println(getURLEncoderString(str));
        System.out.println(getURLDecoderString(str2));
        JSONTokener json = new JSONTokener(getURLDecoderString(str2));
        JSONObject obj = new JSONObject(json);
        System.out.println(obj);     
    }

}
