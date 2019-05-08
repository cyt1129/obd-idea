package com.obd.web;

import java.util.HashMap;
import java.util.Iterator;
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

import com.obd.dto.QueryAuthedExecution;
import com.obd.dto.QueryTypeExecution;
import com.obd.dto.RegisterExecution;
import com.obd.entity.VehicleModel;
import com.obd.exception.CannotFindException;
import com.obd.service.VehicleService;

@Controller // @Service @Component 放入spring容器当中
@RequestMapping("/registry") // url:/模块/资源/{id}/细分
public class RegistryController {

	// 日志
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private VehicleService vehicleService;

	/**
	 * 查询车辆是否已鉴权
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/auth/status", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public ResponseEntity<QueryAuthedExecution> queryAuthedVehicleByVIN(@RequestBody String json) throws Exception {
		logger.info("查询车辆是否鉴权");
		// 跨域请求的头
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Allow-Origin", "*");
		responseHeaders.set("Content-Type", "application/json;charset=utf-8");

		// 解析出vin码
		JSONTokener jsonTokener = new JSONTokener(json);
		JSONObject jsonData = new JSONObject(jsonTokener);
		String clsbhm = jsonData.getString("clsbhm");

		// 查询结果返回对象
		QueryAuthedExecution queryAuthedExecution;

		try {
			queryAuthedExecution = vehicleService.queryIfAuthedByVin(clsbhm);
			logger.info("sucecss", queryAuthedExecution);
			return new ResponseEntity<QueryAuthedExecution>(queryAuthedExecution, responseHeaders, HttpStatus.OK);
		} catch (CannotFindException e) {
			logger.error(e.getMessage());
			queryAuthedExecution = new QueryAuthedExecution(clsbhm, -1, "未找到");
			return new ResponseEntity<QueryAuthedExecution>(queryAuthedExecution, responseHeaders, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			queryAuthedExecution = new QueryAuthedExecution(clsbhm, -2, "内部错误");
			return new ResponseEntity<QueryAuthedExecution>(queryAuthedExecution, responseHeaders, HttpStatus.OK);
		}
	}

	/**
	 * 车辆鉴权 平台鉴权+加入本地数据库
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/auth", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<RegisterExecution> registerDevice(@RequestBody String json) throws Exception {

		logger.info("鉴权");
		// 头，解决跨域问题
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Allow-Origin", "*");
		responseHeaders.set("Content-Type", "application/json;charset=utf-8");

		// 上传数据包report
		Map<String, String> report = new HashMap<String, String>();
		RegisterExecution registerExecution;

		JSONTokener jsonTokener = new JSONTokener(json);
		JSONObject jsonData = new JSONObject(jsonTokener);

//		// 1设备编号
//		String SBBH = Constant.cnbh + new String(jsonData.getString("SBBH").getBytes("ISO-8859-1"), "UTF-8").trim();
//		report.put("SBBH", SBBH);
//		// 2号牌种类
//		report.put("HPZL", new String(jsonData.getString("HPZL").getBytes("ISO-8859-1"), "UTF-8").trim());
//		// 3号牌号码
//		report.put("HPHM", new String(jsonData.getString("HPHM").getBytes("ISO-8859-1"), "UTF-8").trim());
//		// 4车辆型号
//		report.put("CLXH", new String(jsonData.getString("CLXH").getBytes("ISO-8859-1"), "UTF-8").trim());
//		// 5车辆品牌
//		report.put("CLPP", new String(jsonData.getString("CLPP").getBytes("ISO-8859-1"), "UTF-8").trim());
//		// 6车架号
//		report.put("CLSBHM", new String(jsonData.getString("CLSBHM").getBytes("ISO-8859-1"), "UTF-8").trim());
//		// 7发动机号
//		report.put("FDJH", new String(jsonData.getString("FDJH").getBytes("ISO-8859-1"), "UTF-8").trim());
//		// 8初次登记日期
//		report.put("CCDJRQ", new String(jsonData.getString("CCDJRQ").getBytes("ISO-8859-1"), "UTF-8").trim());
//		// 9发动机型号
//		report.put("FDJXH", new String(jsonData.getString("FDJXH").getBytes("ISO-8859-1"), "UTF-8").trim());
//		// 10燃料
//		report.put("RLZL", new String(jsonData.getString("RLZL").getBytes("ISO-8859-1"), "UTF-8").trim());
//		// 11车辆类型
//		report.put("CLLX", new String(jsonData.getString("CLLX").getBytes("ISO-8859-1"), "UTF-8").trim());
//		// 12所有人
//		report.put("SYR", new String(jsonData.getString("SYR").getBytes("ISO-8859-1"), "UTF-8").trim());
//		// 13联系电话
//		report.put("LXDH", new String(jsonData.getString("LXDH").getBytes("ISO-8859-1"), "UTF-8").trim());
//		// 14销售单位
//		report.put("XSDW", new String(jsonData.getString("XSDW").getBytes("ISO-8859-1"), "UTF-8").trim());
//		// 15汽车分类
//		report.put("QCFL", new String(jsonData.getString("QCFL").getBytes("ISO-8859-1"), "UTF-8").trim());
//		// 16生产日期
//		report.put("SCRQ", new String(jsonData.getString("SCRQ").getBytes("ISO-8859-1"), "UTF-8").trim());
//		// 17排放阶段
//		report.put("PF", new String(jsonData.getString("PF").getBytes("ISO-8859-1"), "UTF-8").trim());
//		// 18车辆制造商名称
//		report.put("SCQYMC", new String(jsonData.getString("SCQYMC").getBytes("ISO-8859-1"), "UTF-8").trim());
//		// 19发动机生产厂
//		report.put("FDJSCC", new String(jsonData.getString("FDJSCC").getBytes("ISO-8859-1"), "UTF-8").trim());
//		// 20IUPR监测功能
//		report.put("IUPR", new String(jsonData.getString("IUPR").getBytes("ISO-8859-1"), "UTF-8").trim());

		Iterator<String> keys = jsonData.keys();
		while (keys.hasNext()) {
			String key = keys.next();
			if(key.equals("optional")) {
				continue;
			}
			report.put(key, new String(jsonData.getString(key).getBytes("ISO-8859-1"), "UTF-8").trim());
		}
		
		// 检查json数据里面有没有optional参数
		if (jsonData.has("optional")) {
			JSONObject optional = jsonData.getJSONObject("optional");
			Iterator<String> optionalKeys = optional.keys();
			while (optionalKeys.hasNext()) {
				String key = optionalKeys.next();
				report.put(key, new String(optional.getString(key).getBytes("ISO-8859-1"), "UTF-8").trim());
			}
		}
		registerExecution = vehicleService.registerVehicle(report);
		logger.info("registerExecution:" + registerExecution.toString());
		return new ResponseEntity<RegisterExecution>(registerExecution, responseHeaders, HttpStatus.OK);
	}

	/**
	 * 添加车辆型号至数据库
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/vehicleModel/insert", method = RequestMethod.POST) // ,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RegisterExecution> insertVehicleType(@RequestBody String json) throws Exception {

		logger.info("添加车辆型号");
		RegisterExecution registerExecution;
		// 头
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Allow-Origin", "*");
		responseHeaders.set("Content-Type", "application/json;charset=utf-8");

		Map<String, String> report = new HashMap<String, String>();

		JSONTokener jsonTokener = new JSONTokener(json);
		JSONObject jsonData = new JSONObject(jsonTokener);

//		// 车辆型号
//		report.put("CLXH", new String(jsonData.getString("CLXH").getBytes("ISO-8859-1"), "UTF-8").trim());
//		// 车辆品牌
//		report.put("CLPP", new String(jsonData.getString("CLPP").getBytes("ISO-8859-1"), "UTF-8").trim());
//		// 发动机型号
//		report.put("FDJXH", new String(jsonData.getString("FDJXH").getBytes("ISO-8859-1"), "UTF-8").trim());
//		// 燃料
//		report.put("RLZL", new String(jsonData.getString("RLZL").getBytes("ISO-8859-1"), "UTF-8").trim());
//		// 车辆类型
//		report.put("CLLX", new String(jsonData.getString("CLLX").getBytes("ISO-8859-1"), "UTF-8").trim());
//		// 汽车分类
//		report.put("QCFL", new String(jsonData.getString("QCFL").getBytes("ISO-8859-1"), "UTF-8").trim());
//		// 排放阶段
//		report.put("PF", new String(jsonData.getString("PF").getBytes("ISO-8859-1"), "UTF-8").trim());
//		// 车辆制造商名称
//		report.put("SCQYMC", new String(jsonData.getString("SCQYMC").getBytes("ISO-8859-1"), "UTF-8").trim());
//		// 发动机生产厂
//		report.put("FDJSCC", new String(jsonData.getString("FDJSCC").getBytes("ISO-8859-1"), "UTF-8").trim());
//		// IUPR监测功能
//		report.put("IUPR", new String(jsonData.getString("IUPR").getBytes("ISO-8859-1"), "UTF-8").trim());
		Iterator<String> keys = jsonData.keys();
		while (keys.hasNext()) {
			String key = keys.next();
			if(key.equals("optional")) {
				continue;
			}
			report.put(key, new String(jsonData.getString(key).getBytes("ISO-8859-1"), "UTF-8").trim());
		}
		
		// 处理optional
		Map<String, String> option = new HashMap<String, String>();
		JSONObject optional = jsonData.getJSONObject("optional");
		Iterator<String> optionalKeys = optional.keys();
		while (optionalKeys.hasNext()) {
			String key = optionalKeys.next();
			option.put(key, new String(optional.getString(key).getBytes("ISO-8859-1"), "UTF-8").trim());
		}
		report.put("optional", option.toString());
		try {
			VehicleModel v = new VehicleModel(report);
			int result = vehicleService.updateVehicleModelTable(v);
			if (result == 1) {
				registerExecution = new RegisterExecution(report.get("CLXH"), 1, "添加成功");
				return new ResponseEntity<RegisterExecution>(registerExecution, responseHeaders, HttpStatus.OK);
			}
			registerExecution = new RegisterExecution(report.get("CLXH"), -1, "添加失败");
			return new ResponseEntity<RegisterExecution>(registerExecution, responseHeaders, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			registerExecution = new RegisterExecution(report.get("CLXH"), -1, "添加失败");
			return new ResponseEntity<RegisterExecution>(registerExecution, responseHeaders, HttpStatus.OK);
		}
	}

	/**
	 * 查询车辆型号
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/vehicleModel", method = RequestMethod.POST) // ,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QueryTypeExecution> queryVehicleType(@RequestBody String json) throws Exception {

		System.out.println(json);
		logger.info("查询车辆型号");
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Allow-Origin", "*");
		responseHeaders.set("Content-Type", "application/json;charset=utf-8");

		JSONTokener jsonTokener = new JSONTokener(json);
		JSONObject jsonData = new JSONObject(jsonTokener);
		String clxh = jsonData.getString("type");

		VehicleModel vm;
		QueryTypeExecution queryTypeExecution;
		// 查询数据库是否有该车辆类型,有则返回数据
		try {
			vm = vehicleService.getVehicleModel(clxh);
			queryTypeExecution = new QueryTypeExecution(clxh, 1, "操作成功", vm);
			return new ResponseEntity<QueryTypeExecution>(queryTypeExecution, responseHeaders, HttpStatus.OK);
		} catch (CannotFindException e) {
			logger.error(e.getMessage());
			queryTypeExecution = new QueryTypeExecution(clxh, -1, "未找到");
			return new ResponseEntity<QueryTypeExecution>(queryTypeExecution, responseHeaders, HttpStatus.OK);
		}
	}
}
