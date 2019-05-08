package com.obd.dao;

import static org.junit.Assert.*;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.obd.entity.Vehicle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class VehicleDaoTest {

	// 注入dao依赖
	@Resource
	private VehicleDao vehicleDao;

	@Test
	public void testInsertVehicle() {
		Date authTime = new Date();
		int insert = vehicleDao.insertVehicle("212", "13",null, "jfkwqe", "jfkwqe", 
				"2", "jfkwqe", "jfkwqe", "jfkwqe", "jfkwqe", 
				"jfkwqe", "00004", "jfkwqe", "jfkwqe", authTime);
		System.out.println(insert);
	}
	
	@Test
	public void queryVehicleByVIN() {
		Vehicle vehicle = vehicleDao.queryVehicleByVIN("330107LFNAHUKM8KFA05883");
		System.out.println(vehicle);
	}
	
	
	@Test
	public void testInsertVehicleByObject() {
		long now = System.currentTimeMillis();
		Date authTime = new Date(now);
		Vehicle vehicle = new Vehicle("1", "2","3", "4", "5", 
				"6", "7", "8", "9", "10", 
				"11", "12", "13", "14", authTime);
		int insert = vehicleDao.insertDeviceWithEntity(vehicle);
		System.out.println(insert);
	}
	
	@Test
	public void updateVehicleICC() {
		String sbbh = "212";
		String iccid ="abc";
		vehicleDao.updateVehicleICC(iccid,sbbh);
	}

}
