package com.obd.service;

import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import com.obd.dto.OBDResponse;

public interface WebService {
	
	/**
	 * 发送鉴权信息
	 * @param packagedBody
	 * @return
	 */
	OBDResponse sendAuthMsg2OBD(Map<String, String> packagedBody);
	
	/**
	 * 将写入数据发送至OBD中心平台
	 * @param packagedBody
	 * @param token
	 * @return
	 */
	OBDResponse sendUpdateMsg2OBD(Map<String, String> packagedBody, String sbbh);
	
	/**
	 * 将写入数据多条打包发送至OBD中心平台
	 * @param packagedBody
	 * @param token
	 * @return
	 */
	OBDResponse sendUpdateMsg2OBDWithPack(List<Map<String, String>> list);
	
	/**
	 * 发送心跳包
	 * @return
	 */
	OBDResponse sendBeatMsg2OBD();
	
	/**
	 * 发送故障类数据
	 * @param packagedBody
	 * @param sbbh
	 * @return
	 */
	OBDResponse sendFaultMsg2OBD(Map<String, String> packagedBody, String sbbh);
	
	/**
	 * 将打包好的信息用http请求发送至OBD中心平台
	 * @param formData
	 * @param url
	 * @return
	 */
	OBDResponse OBDHttpPost(List<NameValuePair> formData, String url);

	/**
	 * 发gps信息至thingsboard
	 * @param jsonParam
	 * @param url
	 * @return
	 */
	boolean httpPostWithJson(JSONObject jsonParam, String url);
	
	/**
	 * 查询杭州中心平台收到的设备运行情况
	 * @param packagedBody
	 * @param token
	 * @return
	 */
	String hzStatusInfo(Map<String, String> packagedBody);
	

}
