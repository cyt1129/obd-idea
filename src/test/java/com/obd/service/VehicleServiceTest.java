package com.obd.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.obd.dto.QueryAuthedExecution;
import com.obd.dto.RegisterExecution;
import com.obd.util.Constant;

@RunWith(SpringJUnit4ClassRunner.class) // spring和junit整合
@ContextConfiguration({ "classpath:spring/spring-service.xml", "classpath:spring/spring-dao.xml" })
public class VehicleServiceTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private VehicleService vehicleService;

	@Test
	public void testRegisterVehicleWithOption() {
		Map<String, String> report = new HashMap<String, String>();
		String SBBH = "01234567890123456";
		report.put("CLXH", "JIMA2019TEST001");
		report.put("CLPP", "极码");
		report.put("CLSBHM", "JIMA2019CLSBHM");
		report.put("FDJH", "JIMA2019FDJH");
		report.put("FDJXH", "JIMA2019FDJXH001");
		report.put("RLZL", "A");
		report.put("PF", "5");
		report.put("SCQYMC", "极码科技");
		report.put("FDJSCC", "极码科技");
		report.put("SCRQ", "2018-12-10");
		report.put("DOCXH", "1");
		// scr

		// 发送至OBD中心平台，更新sbbh加上33010007，多传id好像没关系的
		report.put("SBBH",SBBH);
//		report.put("SCRXH", "aaaa");
		RegisterExecution re = vehicleService.registerVehicle(report);
		logger.info("RegisterExecution:"+re.toString());
	}

	@Test
	public void testQueryIfAuthedByVin() {
		String clsbhm = "LGAX2B13XK1005245";
		QueryAuthedExecution queryAuthedExecution = vehicleService.queryIfAuthedByVin(clsbhm);
		System.out.println(queryAuthedExecution);
	}

	@Test
	public void testUpdateVehicleList() {
		vehicleService.updateICCID("1234567", "0123456");
	}

}
