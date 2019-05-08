package com.obd.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.obd.dto.InvokeSuccess;
import com.obd.dto.OBDResponse;
import com.obd.util.Constant;
import com.obd.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class) // spring和junit整合
@ContextConfiguration({ "classpath:spring/spring-service.xml", "classpath:spring/spring-dao.xml" })
public class WebServiceTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WebService webService;

	@Test
	public void testSendAuthMsg2OBD() {
		Map<String, String> report = new HashMap<String, String>();
		String SBBH = "LWU2PM1C3KKM04029";
//		String SBBH = "LFWSRXSJ6K1E10500";
//		String SBBH = "jimatest0002";
		report.put("CLXH", "KMC1041A28P5");
		report.put("CLPP", "凯马");
		report.put("CLSBHM", "LWU2PM1C3KKM04029");
		report.put("FDJH", "A3018010935");
		report.put("FDJXH", "D19TCIE1");
		report.put("RLZL", "B");
		report.put("PF", "5");
		report.put("SCQYMC", "山东凯马汽车制造有限公司");
		report.put("FDJSCC", "昆明云内动力股份有限公司");
		report.put("SCRQ", "2019-01-21");
		// scr
		report.put("SCRXH", "D20TCIE-17-2");
		report.put("SCRSCC", "无锡恒和环保科技有限公司");
		report.put("PYBXH", "CP1H");
		report.put("PYBSCC", "博世汽车柴油系统有限公司");
		report.put("PYQXH", "CRI");
		report.put("PYQSCC", "博世汽车柴油系统有限公司");
		report.put("ZYQXH", "HP48");
		report.put("ZYQSCC", "宁波威孚天力增压技术有限公司");
		report.put("ECUXH", "EDC");
		report.put("ECUBBH", "P1187/P924");
		report.put("ECUSCC", "博世汽车柴油系统有限公司");
		
		report.put("OBDXH", "OBD2-EDC");
		report.put("OBDSCC", "博世汽车柴油系统有限公司");
		report.put("ZLQXS", "空-空风冷中冷器");
		report.put("FDJGL", "70");
		report.put("FDJGLZS", "3600");
		report.put("ZDNJ", "235");
		report.put("ZDNJZS", "1600-2600");
		report.put("PQHCLXS", "deNOx系统");
		report.put("KQLQQXH", "1222");
		report.put("KQLQQSCC", "寿光市巨龙机械制造有限公司");
		report.put("PQXSQXH", "1222");
		report.put("PQXSQSCC", "寿光市巨龙机械制造有限公司");

		// 发送至OBD中心平台，更新sbbh加上33010007，多传id好像没关系的
		report.put("SBBH", Constant.cnbh + SBBH);
		OBDResponse obdResponse = webService.sendAuthMsg2OBD(report);
		logger.info(obdResponse.toString());
	}

	@Test
	public void testSendUpdateMsg2OBD() {
		System.out.println("********************写入数据***********************");
		long start = System.currentTimeMillis();
		String sbbh1 = "1G1JC5444R7252367";
		String sbbh2 = "1G1JC5444R7252367";
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> body = new HashMap<String, String>();
		Map<String, String> body1 = new HashMap<String, String>();
		Map<String, String> body3 = new HashMap<String, String>();
		list.add(body);
		list.add(body1);
		list.add(body3);
		body.put("SBBH", Constant.cnbh+sbbh1);
		body.put("sjxx", "20181225111415");// 时间信息
		body.put("cs", "60");// 0车速
		body.put("DQYL", "101");// 1大气压力
		body.put("SJNJBFB", "50");// 2实际扭矩百分比
		body.put("MCNJBFB", "60");// 3摩擦扭矩百分比
		body.put("FDJZS", "800");// 4发动机转速
		body.put("FDJRLLL", "76");// 5发动机燃料流量
//		//
		body.put("JQdYCLZ", "0.6");// 6后处理上游氮氧浓度 scr的
		body.put("HClXYDYND", "0.3"); // 7后处理下游氮氧浓度 scr
		body.put("NSXYW", "30");// 8尿素箱液位 scr
//		//
//		body.put("JQLL", "2000");// 9进气流量
//		//
		body.put("HCLSYPQWD", "1234.96875");// 10后处理上游排气温度 scr
//		body.put("HCLXYPQWD", "FF");// 11后处理下游排气温度 scr
//		//
//		body.put("ztyc", "FF"); // 12载体压差（DPF/POC）
//		//
//		body.put("LQYWD", "60");// 13冷却液温度
		body.put("YXYW", "30");// 14油箱液位
//		body.put("DWZT", "1");// 15定位状态
		body.put("JD", "120.000000");// 16经度
		body.put("WD", "30.000000");// 17纬度
		body.put("ZXSLC", "600000");// 18总行驶里程
//		//
//		body.put("fdjjscnj", "54");// 19发送机净输出扭矩（自定义）
//		//
		body.put("FDJNJMS", "9");// 20发动机扭矩模式
		body.put("YMTB", "20");// 21油门踏板
		body.put("ZYH", "150000000");// 22总油耗
//		//
//		body.put("NSXWD", "100");// 23尿素箱温度 scr
//		body.put("SJNSPSL", "34"); // 24实际尿素喷射量 scr
//		body.put("ZNSXH", "3");// 25总尿素消耗 scr
//		//
//		body.put("PQWD", "23");// 26DPF排气温度 dpf
//		//
//		body.put("PQBY", "23");// 27DPF排气温度 dpf 默认NA
//		body.put("HJWD", "30");// 28环境温度
//		body.put("dmhx", "30");// 29地面航向（ 自定义）
//		//
//		body.put("CKNJ", "1");// 30参考扭矩 必填项
//		body.put("MDZT", "1");// 31
//		body.put("FDJZYXSJ", "110554060");// 32发动机总运行时间
//		//
//		body.put("SSYH", "FF");// 33瞬时油耗 国五要求
//		//
//		body.put("JQYHL", "10");// 34进气氧含量 doc国四
//		body.put("PQYHL", "10");// 35排气氧含量 doc国四
//		body.put("JQWD", "18");// 36排气氧含量 doc国四进气温度 JQWD
//		body.put("JQWD", "32");// 37基于PEMS的Nox排放值 JYPEMSDNOXPFZ
//		body.put("JQWD", "0");// 38ECU数据篡改监测 ECUSJCGJC
		/**第二辆车*/
		body1.put("SBBH", Constant.cnbh+sbbh2);
		body1.put("sjxx", "20181225111415");// 时间信息
		body1.put("SBBH", Constant.cnbh+sbbh1);
		body1.put("sjxx", "20181225111415");// 时间信息
		body1.put("cs", "60");// 0车速
		body1.put("DQYL", "101");// 1大气压力
		body1.put("SJNJBFB", "50");// 2实际扭矩百分比
		body1.put("MCNJBFB", "60");// 3摩擦扭矩百分比
		body1.put("FDJZS", "800");// 4发动机转速
		body1.put("FDJRLLL", "76");// 5发动机燃料流量
//		//
		body1.put("JQdYCLZ", "0.6");// 6后处理上游氮氧浓度 scr的
		body1.put("HClXYDYND", "0.3"); // 7后处理下游氮氧浓度 scr
		body1.put("NSXYW", "30");// 8尿素箱液位 scr
//		//
//		body.put("JQLL", "2000");// 9进气流量
//		//
		body1.put("HCLSYPQWD", "1234.96875");// 10后处理上游排气温度 scr
//		body.put("HCLXYPQWD", "FF");// 11后处理下游排气温度 scr
//		//
//		body.put("ztyc", "FF"); // 12载体压差（DPF/POC）
//		//
//		body.put("LQYWD", "60");// 13冷却液温度
		body1.put("YXYW", "30");// 14油箱液位
//		body.put("DWZT", "1");// 15定位状态
		body1.put("JD", "120.000000");// 16经度
		body1.put("WD", "30.000000");// 17纬度
		body1.put("ZXSLC", "600000");// 18总行驶里程
//		//
//		body.put("fdjjscnj", "54");// 19发送机净输出扭矩（自定义）
//		//
		body1.put("FDJNJMS", "9");// 20发动机扭矩模式
		body1.put("YMTB", "20");// 21油门踏板
		body1.put("ZYH", "150000000");// 22总油耗
		/**国三车**/
		body3.put("SBBH", Constant.cnbh+sbbh2);
		body3.put("SBBH", Constant.cnbh+sbbh1);
		body3.put("sjxx", "20181225111415");// 时间信息
		body3.put("cs", "60");// 0车速
		body3.put("DQYL", "101");// 1大气压力
		body3.put("SJNJBFB", "50");// 2实际扭矩百分比
		body3.put("MCNJBFB", "60");// 3摩擦扭矩百分比
		body3.put("FDJZS", "800");// 4发动机转速
		body3.put("FDJRLLL", "76");// 5发动机燃料流量
//		//
		body3.put("JQdYCLZ", "0.6");// 6后处理上游氮氧浓度 scr的
		body3.put("HClXYDYND", "0.3"); // 7后处理下游氮氧浓度 scr
		body3.put("NSXYW", "30");// 8尿素箱液位 scr
//		//
//		body.put("JQLL", "2000");// 9进气流量
//		//
		body3.put("HCLSYPQWD", "1234.96875");// 10后处理上游排气温度 scr
//		body.put("HCLXYPQWD", "FF");// 11后处理下游排气温度 scr
//		//
//		body.put("ztyc", "FF"); // 12载体压差（DPF/POC）
//		//
//		body.put("LQYWD", "60");// 13冷却液温度
		body3.put("YXYW", "30");// 14油箱液位
//		body.put("DWZT", "1");// 15定位状态
		body3.put("JD", "120.000000");// 16经度
		body3.put("WD", "30.000000");// 17纬度
		body3.put("ZXSLC", "600000");// 18总行驶里程
//		//
//		body.put("fdjjscnj", "54");// 19发送机净输出扭矩（自定义）
//		//
		body3.put("FDJNJMS", "9");// 20发动机扭矩模式
		body3.put("YMTB", "20");// 21油门踏板
		body3.put("ZYH", "150000000");// 22总油耗
		OBDResponse obdResponse = webService.sendUpdateMsg2OBDWithPack(list);
		logger.info("OBDResponse:"+obdResponse);
	}

	@Test
	public void testSendBeat() {
		fail("Not yet implemented");
	}

	@Test
	public void testSendFault() {
		Map<String, String> report = new HashMap<String, String>();
		String sbbh = "jimatest0002";
		report.put("GZSJ", DateUtil.generateRequestTime());
		report.put("SPN", "JIMA2019TEST001");
		report.put("SPNNAME", "极码");
		report.put("FMI", "JIMA2019CLSBHM");
		report.put("FMINAME", "JIMA2019FDJH");
		

		// 发送至OBD中心平台，更新sbbh加上33010007，多传id好像没关系的
		report.put("SBBH", Constant.cnbh + sbbh);
		OBDResponse obdResponse = webService.sendFaultMsg2OBD(report, sbbh);
		logger.info(obdResponse.toString());
	}
	
	@Test
	public void tb() {
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("name", "admin");
		String url = "http://129.211.96.209:8080/api/v1/01234567890123456/telemetry";
		boolean r = webService.httpPostWithJson(jsonParam, url);
		System.out.println(r);
	}
	
	@Test
	public void hzstatusInfo() {
		String clsbhm = "LGDCWA1L6JH137697";//"LFNA4LJA9JAM19910";
		
		Map<String, String> report = new HashMap<String, String>();
		InvokeSuccess invokeSuccess;
		report.put("clsbhm", clsbhm);
		String result = webService.hzStatusInfo(report);
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
			invokeSuccess = new InvokeSuccess(requestTime, code, version, obdzt, clsbhmBack, deviceSn,jqsj);
		} else {
			// 未鉴权用OBDResponse就行
			// 取是否成功和message
			String message = execute.getString("message");
			invokeSuccess = new InvokeSuccess(requestTime, code, version, message);
		}
		logger.info("invokeResult:"+ invokeSuccess.toString());
		//System.out.println(obdResponse);
	}
	/**
	 * 1、车辆OBD状态正常
	 * {
	 *   "requestTime":"20190409165918",
	 *   "total":1,
	 *   "code":"1",
	 *   "responseTime":"20190409165907",
	 *   "exchangeType":"21",
	 *   "body":[{
	 *     "obdzt":"车辆OBD状态正常",
	 *     "clsbhm":"LFNA4LJA9JAM19910",
	 *     "deviceSn":"330107LFNA4LJA9JAM19910",
	 *     "jqsj":"20190402145316"
	 *   }],
	 *   "version":1,
	 *   "exchangeCode":"33010700002019040916591800000001"
	 * }
	 * 2、车辆OBD近期无数据
	 * {
	 *   "requestTime":"20190409165054",
	 *   "total":1,
	 *   "code":"1",
	 *   "responseTime":"20190409165043",
	 *   "exchangeType":"21",
	 *   "body":[{
	 *   	"obdzt":"车辆OBD近期无数据",
	 *      "clsbhm":"LGAG3DV21J3017927",
	 *      "deviceSn":"330107LGAG3DV21J3017927",
	 *      "jqsj":"20190408111904"
	 *      }],
	 *    "version":1,
	 *    "exchangeCode":"33010700002019040916505400000001"
	 *   }
	 * 3、未鉴权情况
	 * {
	 *   "requestTime":"20190409170414",
	 *   "total":0,
	 *   "code":"-1",
	 *   "responseTime":"20190409170403",
	 *   "exchangeType":"21",
	 *   "message":"[VIN:LPNA4LJA9JAM19910]请重新鉴权!",
	 *   "version":1,
	 *   "exchangeCode":"33010700002019040917041400000001"
	 *   }
	 *   
	 */

}
