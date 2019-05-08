package com.obd.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.obd.dto.QueryAuthedExecution;
import com.obd.service.VehicleService;

@RunWith(SpringJUnit4ClassRunner.class) // spring和junit整合
@ContextConfiguration({ "classpath:spring/spring-service.xml", "classpath:spring/spring-dao.xml" })
public class VehicleServiceImplTest {
	
	@Autowired
	private VehicleService vehicleService;

	@Test
	public void testQueryIfAuthedByVin() {
		String clsbhm = "00003";
		QueryAuthedExecution queryAuthedExecution = vehicleService.queryIfAuthedByVin(clsbhm);
	}

}
