package com.obd.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.obd.dto.OBDResponse;
import com.obd.exception.WebException;
import com.obd.service.WebService;
import com.obd.util.Constant;
import com.obd.util.DateUtil;
import com.obd.util.JsonUtil;
import com.obd.util.MsgCheckUtil;

/**
 * 与OBD中心平台进行网络通信的服务
 * 
 * @author mac
 *
 */
@Service
public class WebServiceImpl implements WebService {

	// 日志
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 发送鉴权包
	 * 
	 * @param packagedBody
	 * @throws IOException
	 */
	public OBDResponse sendAuthMsg2OBD(Map<String, String> packagedBody) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		list.add(packagedBody);
		OBDResponse obdResponse;

		// 打包writeJsonDoc
		Map<String, Object> JsonDoc = new HashMap<String, Object>();
		JsonDoc.put("exchangeType", Constant.EXTYPE_AUTH);
		JsonDoc.put("exchangeCode", DateUtil.generateExchangeCode());
		JsonDoc.put("requestTime", DateUtil.generateRequestTime());
		JsonDoc.put("body", list);
		JsonDoc.put("version", "1.0");
		String WriteJsonDoc = JsonUtil.jsonObj2Sting(JsonDoc);
		logger.info("auth-WriteJsonDoc:" + WriteJsonDoc);

		// 打包整个鉴权数据包成namepair形式
		List<NameValuePair> authMsg = packUrlParameters(WriteJsonDoc);
		obdResponse = OBDHttpPost(authMsg, Constant.OBD_AUTH);
		return obdResponse;
	}

	/**
	 * 发送写入数据
	 * 
	 * @param packagedBody
	 * @param token
	 * @throws IOException
	 */
	public OBDResponse sendUpdateMsg2OBD(Map<String, String> packagedBody, String sbbh) {
		// 在body中添加设备编号SBBH
		packagedBody.put("SBBH", Constant.cnbh + sbbh);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		list.add(packagedBody);
		OBDResponse obdResponse;
		// WriteJsonDoc原始数据
		Map<String, Object> WriteJsonDoc = new HashMap<String, Object>();
		WriteJsonDoc.put("exchangeType", Constant.EXTYPE_WRITEIN);
		WriteJsonDoc.put("exchangeCode", DateUtil.generateExchangeCode());
		WriteJsonDoc.put("requestTime", DateUtil.generateRequestTime());
		WriteJsonDoc.put("body", list);
		WriteJsonDoc.put("version", "1.0");
		// 转成json字符串
		String JsonDoc = JsonUtil.jsonObj2Sting(WriteJsonDoc);
		// 打包成最终要表单发送
		try {
			obdResponse = OBDHttpPost(packUrlParameters(JsonDoc), Constant.OBD_WRITE_OBJECT);
			return obdResponse;
		} catch (WebException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	/**
	 * 发送写入数据包！(好多条一起发的)
	 * 
	 * @param packagedBody
	 * @param token
	 * @throws IOException
	 */
	public OBDResponse sendUpdateMsg2OBDWithPack(List<Map<String, String>> list) {
		// 在body中添加设备编号SBBH
		// packagedBody.put("SBBH", Constant.cnbh + sbbh);
		// List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		// list.add(packagedBody);
		OBDResponse obdResponse;
		// WriteJsonDoc原始数据
		Map<String, Object> WriteJsonDoc = new HashMap<String, Object>();
		WriteJsonDoc.put("exchangeType", Constant.EXTYPE_WRITEIN);
		WriteJsonDoc.put("exchangeCode", DateUtil.generateExchangeCode());
		WriteJsonDoc.put("requestTime", DateUtil.generateRequestTime());
		WriteJsonDoc.put("body", list);
		WriteJsonDoc.put("version", "1.0");
		// 转成json字符串
		String JsonDoc = JsonUtil.jsonObj2Sting(WriteJsonDoc);
		// 打包成最终要表单发送
		try {
			obdResponse = OBDHttpPost(packUrlParameters(JsonDoc), Constant.OBD_WRITE_OBJECT);
			return obdResponse;
		} catch (WebException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	/**
	 * 发送心跳包
	 * 
	 * @throws IOException
	 * 
	 */
	public OBDResponse sendBeatMsg2OBD() throws WebException {
		// WriteJsonDoc
		Map<String, String> JsonDoc = new HashMap<String, String>();
		JsonDoc.put("exchangeType", Constant.EXTYPE_HEARTBEAT);
		JsonDoc.put("exchangeCode", DateUtil.generateExchangeCode());
		JsonDoc.put("requestTime", DateUtil.generateRequestTime());
		JsonDoc.put("version", "1.0");
		// 转成json字符串
		String WriteJsonDoc = JsonUtil.jsonObj2Sting(JsonDoc);
		// System.out.println("WriteJsonDoc:"+WriteJsonDoc);
		// 发送心跳包
		return OBDHttpPost(packUrlParameters(WriteJsonDoc), Constant.OBD_BEAT_OBJECT);
	}

	/**
	 * 发送故障写入类数据
	 * 
	 * @param packagedBody
	 * @throws IOException
	 */
	public OBDResponse sendFaultMsg2OBD(Map<String, String> packagedBody, String sbbh) {

		packagedBody.put("SBBH", Constant.cnbh + sbbh);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		list.add(packagedBody);
		// WriteJsonDoc
		Map<String, Object> JsonDoc = new HashMap<String, Object>();
		JsonDoc.put("exchangeType", Constant.EXTYPE_FAULT);
		JsonDoc.put("exchangeCode", DateUtil.generateExchangeCode());
		JsonDoc.put("requestTime", DateUtil.generateRequestTime());
		JsonDoc.put("body", list);
		JsonDoc.put("version", "1.0");
		// 转成json字符串
		String WriteJsonDoc = JsonUtil.jsonObj2Sting(JsonDoc);
		// 发送心跳包
		return OBDHttpPost(packUrlParameters(WriteJsonDoc), Constant.OBD_WRITE_FAULT);
	}

	/**
	 * 调用杭州平台查询接口
	 */
	public String hzStatusInfo(Map<String, String> packagedBody) {
		// packagedBody.put("clsbhm", clsbhm);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		list.add(packagedBody);

		Map<String, Object> JsonDoc = new HashMap<String, Object>();
		JsonDoc.put("exchangeType", Constant.INVOKE);
		JsonDoc.put("exchangeCode", DateUtil.generateExchangeCode());
		JsonDoc.put("requestTime", DateUtil.generateRequestTime());
		JsonDoc.put("body", list);
		JsonDoc.put("version", "1.0");
		// 转成json字符串
		String WriteJsonDoc = JsonUtil.jsonObj2Sting(JsonDoc);
		String res = post(packUrlParameters(WriteJsonDoc), Constant.OBD_INVOKE_OBJECT);
		//JSONObject jsonData = new JSONObject(new JSONTokener(res));
		System.out.println(res);
		return res;
	}
	
	/**
	 * 最底层服务：发送http请求 return string
	 */
	public String post(List<NameValuePair> formData, String url) throws WebException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		// 配制请求
		HttpPost post = new HttpPost(url);
		try {
			post.setEntity(new UrlEncodedFormEntity(formData, HTTP.UTF_8));
			try {
				response = httpclient.execute(post);
				// 判断网络连接状态码是否正常(0--200都数正常)
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					String content = EntityUtils.toString(response.getEntity(), "UTF-8");
					EntityUtils.consume(response.getEntity());// 完全消耗
					return content;
				} else {
					EntityUtils.consume(response.getEntity());// 完全消耗
					throw new WebException("网络请求错误非200");
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new WebException("网络请求错误，执行execute错误");
			} finally {
				try {
					if (null != response)
						response.close();
				} catch (IOException e) {
					// e.printStackTrace();
					throw new WebException("网络请求错误");
				}
			}
		} catch (UnsupportedEncodingException e) {
			throw new WebException("网络请求错误");
		} finally {
			// 释放链接
			try {
				httpclient.close();
			} catch (IOException e) {
				// e.printStackTrace();
				throw new WebException("网络请求错误");
			}
		}

	}

	/**
	 * 最底层服务：发送http请求 return OBDResponse
	 */
	public OBDResponse OBDHttpPost(List<NameValuePair> formData, String url) throws WebException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String message = "无message";//防止没有message的情况
		String error = "未找到error字段,车辆鉴权异常,请及时联系环保局处理";
		// 配制请求
		HttpPost post = new HttpPost(url);
		try {
			post.setEntity(new UrlEncodedFormEntity(formData, HTTP.UTF_8));
			try {
				response = httpclient.execute(post);
				// 判断网络连接状态码是否正常(0--200都数正常)
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					String content = EntityUtils.toString(response.getEntity(), "UTF-8");
					
					// 对返回的数据进行处理，日志打印原始数据
					JSONObject jsonData = new JSONObject(new JSONTokener(content));
					
					// 取是否成功和message
					String requestTime = jsonData.getString("requestTime");
					String code = jsonData.getString("code");
					String exchangeType = jsonData.getString("exchangeType");
					int version = jsonData.getInt("version");
					String exchangeCode = jsonData.getString("exchangeCode");
					String responseTime = jsonData.getString("responseTime");
					//01是心跳包回复不打印
					if (!exchangeType.equals("01")) {
						logger.info("环保局平台回复:" + jsonData.toString());
					}
					//一般都是有message的，意外情况会出现没有message
					if(jsonData.has("message")) {
						message = jsonData.getString("message");
					}
					
					if (jsonData.has("body")) {
						JSONObject obj = jsonData.getJSONArray("body").getJSONObject(0);
						if(obj.has("error")) {
							error = obj.getString("error");
						}
						EntityUtils.consume(response.getEntity());// 完全消耗
						return new OBDResponse(requestTime, code, exchangeType, responseTime, message, version,
								exchangeCode, error);
					}
					EntityUtils.consume(response.getEntity());// 完全消耗
					return new OBDResponse(requestTime, code, exchangeType, responseTime, message, version,
							exchangeCode);
				} else {
					EntityUtils.consume(response.getEntity());// 完全消耗
					throw new WebException("网络请求错误非200");
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new WebException("网络请求错误，执行execute错误");
			} finally {
				try {
					if (null != response)
						response.close();
				} catch (IOException e) {
					// e.printStackTrace();
					throw new WebException("网络请求错误");
				}
			}
		} catch (UnsupportedEncodingException e) {
			throw new WebException("网络请求错误");
		} finally {
			// 释放链接
			try {
				httpclient.close();
			} catch (IOException e) {
				// e.printStackTrace();
				throw new WebException("网络请求错误");
			}
		}

	}

	/**
	 * 将WriteJsonDoc、jkYhm、jkSqm、crcCode打包成最终要发送的表单
	 * 
	 * @param JsonDoc
	 * @return
	 */
	public List<NameValuePair> packUrlParameters(String WriteJsonDoc) {

		// 生成crc校验码
		String crcCode = MsgCheckUtil.checkCRC(WriteJsonDoc);

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();

		try {
			// JsonDoc字符串加密
			String encryptWriteJsonDoc = MsgCheckUtil.aesEncrypt(WriteJsonDoc, Constant.AES_KEY);
			// System.out.println(encryptWriteJsonDoc);

			urlParameters.add(new BasicNameValuePair("jkYhm", Constant.jkYhm));
			urlParameters.add(new BasicNameValuePair("jkSqm", Constant.jkSqm));
			urlParameters.add(new BasicNameValuePair("crcCode", crcCode));
			urlParameters.add(new BasicNameValuePair("WriteJsonDoc", encryptWriteJsonDoc));
			/* 这里只是显示一下打出来为了看的 */
			//String jsonRequest = JsonUtil.jsonObj2Sting(urlParameters);
			//logger.info("发送的数据：" + jsonRequest);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return urlParameters;
	}

	public boolean httpPostWithJson(JSONObject jsonObj, String url) {
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = null;

		String respContent = null;
		// JSONObject jsonParam = new JSONObject();
		// jsonParam.put("name", "admin");
		// jsonParam.put("pass", "123456");
		StringEntity entity = new StringEntity(jsonObj.toString(), "utf-8");// 解决中文乱码问题
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);

		try {
			response = client.execute(httpPost);
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage(), "发送至thingsboard失败");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), "发送至thingsboard失败");
		}
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity he = response.getEntity();
			try {
				respContent = EntityUtils.toString(he, "UTF-8");
			} catch (ParseException e) {
				logger.error(e.getMessage());
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		WebServiceImpl ws = new WebServiceImpl();
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("name", "admin");
		String url = "http://129.211.96.209:8080/api/v1/01234567890123456/telemetry";
		boolean r = ws.httpPostWithJson(jsonParam, url);
		System.out.println(r);
	}

}
