package com.obd.web;

//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.obd.service.VehicleService;
//import com.obd.service.WebService;
//import com.obd.util.Constant;
//import com.obd.util.UrlUtil;

//@Controller
//@RequestMapping("/telemetry")
//public class TelemetryController {
//
//	// 普通数据上报例子
//	// 0jimatest0002:60,101,50,60,3000,76,0.6,0.3,30,2000,1234.96875,FF,FF,60,30,1,120.000000,30.000000,600000,54,9,20,150000000,100,34,3,23,23,30,30,1,1,110554060,FF,10,10,18,32,0
//	// 故障信息上报
//	// 1LV8174hTUGMM11076:故障码,SNSN,故障值,PNPN;......;......;
//	// 国三车 只上报 12载体压差和ZTYC 26排气温度PQWD PQBY：NA 加经纬度和定位状态
//
//	@Autowired
//	private WebService webService;
//
//	@Autowired
//	private VehicleService vehicleService;
//
//	private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//	@RequestMapping(value = "/up", method = RequestMethod.POST)
//	public String updateTelemetry(@RequestBody String str) throws IOException {
//		str = UrlUtil.getURLDecoderString(str);
//		int length = str.length();
//		if (str.substring(length - 1).equals("=")) {
//			str = str.substring(0, length - 1);
//		}
//		// System.out.println(str);
//		logger.info("原始数据:" + str);
//
//		String sjxx = str.substring(0, 14);// 取时间
//		String sbbh = str.split(":")[0].substring(15, 32);// 取车架号
//		if (str.substring(14, 15).equals("0")) {
//			// 普通数据,逐条发送，收到一条发一条
//			String[] report = str.split(":")[1].split(",");
//			// 如果发动机转速都是FF，就丢掉
//			if (!report[4].equals("FF")) {
//				Map<String, String> body = new HashMap<String, String>();
//				for (int i = 0; i < report.length; i++) {
//					body.put(Constant.writeData[i], report[i]);
//				}
//				body.put("SJXX", sjxx);
//
//				// 发至obd中心平台
//				try {
//					webService.sendUpdateMsg2OBD(body, sbbh);
//				} catch (Exception e) {
//					logger.error(e.getMessage(), "发送失败");
//				}
//				// 发至thingsboard
//				String gpsStr = "OBDGPS," + report[15] + "," + report[16] + "," + report[17] + "," + report[29];
//				JSONObject jsonParam = new JSONObject();
//				jsonParam.put("H0920", gpsStr);
//				String url = Constant.thingsboard_tele + sbbh + "/telemetry";
//				boolean r = webService.httpPostWithJson(jsonParam, url);
//				if (r) {
//					logger.info("[thingsboard] 发送成功");
//				} else {
//					logger.error("[thingsboard] 发送失败");
//				}
//			}
//		} else if(str.substring(14, 15).equals("1")) {
//			// 故障信息处理，只需要操作一下iccid号
//			String[] gzReport = str.split(":")[1].split(";");
//			String iccid = gzReport[0];
//			vehicleService.updateICCID(iccid, sbbh);
//			/**
//			 * 添加到数据库不知道怎么导入那个service String iccid = gzReport[0];
//			 * vehicleService.updateICCID(iccid, sbbh);
//			 *
//			 */
//			/**
//			 * String[] report = gzReport[1].split(","); Map<String, String> body = new
//			 * HashMap<String, String>(); body.put("GZSJ", sjxx); body.put("SBBH", sbbh);
//			 * body.put("SPN", report[0]); body.put("SPNNAME", report[1]); body.put("FMI",
//			 * report[2]); body.put("FMINAME", "未知故障"); webService.sendFaultMsg2OBD(body,
//			 * sbbh);
//			 */
//		}else if(str.substring(14, 15).equals("2")) {
//			
//			String[] report = str.split(":")[1].split(",");
//			Map<String, String> body = new HashMap<String, String>();
//			for (int i = 0; i < report.length; i++) {
//				body.put(Constant.writeData[i], report[i]);
//			}
//			body.put("SJXX", sjxx);
//
//			// 发至obd中心平台
//			try {
//				webService.sendUpdateMsg2OBD(body, sbbh);
//			} catch (Exception e) {
//				logger.error(e.getMessage(), "发送失败");
//			}
//			//国三车实时数据 只上报 
//			// 12、载体压差和ZTYC 
//			// 26、排气温度PQWD 
//			// 15、定位状态(TB) DWZT     
//		    // 16、经度(TB) JD           
//		    // 17、纬度(TB) WD
//			// PQBY：NA 
//			//String[] report = str.split(":")[1].split(",");
//			//Map<String, String> body = new HashMap<String, String>();
//			//body.put("ZTYC", report[12]);
//			//body.put("PQWD", report[26]);
//			//body.put("DWZT", report[15]);
//			//body.put("JD", report[16]);
//			//body.put("WD", report[17]);
//			//body.put("PQBY", "NA");
//		}
//		return "ok";
//	}
//}
