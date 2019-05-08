package com.obd.web;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.obd.dto.InvokeSuccess;
import com.obd.entity.CarIotInfo;
import com.obd.service.CarIotInfoService;
import com.obd.service.WebService;

@Controller
@RequestMapping("/statusInfo")
public class StatusInfoController {

	// 日志
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WebService webService;

	@Autowired
	private CarIotInfoService carIotInfoService;

	@RequestMapping(value = "/hz", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<InvokeSuccess> queryStatusHz(@RequestBody String json) throws Exception {

		InvokeSuccess invokeSuccess;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Allow-Origin", "*");
		responseHeaders.set("Content-Type", "application/json;charset=utf-8");

		// 解析出vin码
		JSONTokener jsonTokener = new JSONTokener(json);
		JSONObject jsonData = new JSONObject(jsonTokener);
		String clsbhm = jsonData.getString("clsbhm");

		Map<String, String> body = new HashMap<String, String>();
		body.put("clsbhm", clsbhm);
		String result = webService.hzStatusInfo(body);
		JSONObject execute = new JSONObject(new JSONTokener(result));
		String code = execute.getString("code");// 取code
		String requestTime = execute.getString("requestTime");
		int version = execute.getInt("version");
		if (code.equals("1")) {
			// 已鉴权状态
			JSONObject abody = execute.getJSONArray("body").getJSONObject(0);
			String obdzt = abody.getString("obdzt");
			String clsbhmBack = abody.getString("clsbhm");
			String deviceSn = abody.getString("deviceSn");
			String jqsj = abody.getString("jqsj");
			invokeSuccess = new InvokeSuccess(requestTime, code, version, obdzt, clsbhmBack, deviceSn, jqsj);
		} else {
			// 未鉴权用OBDResponse就行
			// 取是否成功和message
			String message = execute.getString("message");
			invokeSuccess = new InvokeSuccess(requestTime, code, version, message);
		}
		logger.info("invokeResult:" + invokeSuccess.toString());
		return new ResponseEntity<InvokeSuccess>(invokeSuccess, responseHeaders, HttpStatus.OK);
	}

	/**
	 * 查秒连平台数据情况
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ml", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<CarIotInfo[]> queryStatusMl(@RequestBody String json) throws Exception {

		CarIotInfo[] carIotInfo = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Allow-Origin", "*");
		responseHeaders.set("Content-Type", "application/json;charset=utf-8");

		// 解析出vin码
		JSONTokener jsonTokener = new JSONTokener(json);
		JSONObject jsonData = new JSONObject(jsonTokener);
		String clsbhm = jsonData.getString("clsbhm");
		if (jsonData.has("limit")) {
			int limit = Integer.parseInt(jsonData.getString("limit"));
			carIotInfo = carIotInfoService.queryLimitedDataInfo(clsbhm, limit);
			return new ResponseEntity<CarIotInfo[]>(carIotInfo, responseHeaders, HttpStatus.OK);
		} else {
			carIotInfo = carIotInfoService.queryDataInfo(clsbhm);
			return new ResponseEntity<CarIotInfo[]>(carIotInfo, responseHeaders, HttpStatus.OK);
		}

	}

}
