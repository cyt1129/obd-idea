package com.obd.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.obd.entity.VehicleModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class VehicleModelTest {

	// 注入dao依赖
	@Resource
	private VehicleModelDao vehicleModelDao;

	@Test
	public void testInsert() {
		int result = vehicleModelDao.insertVehicleType("878264hef", "龙马牌", "878264hef", "5", "C31", "23",
				"6", "878264hef", "878264hef", "878264hef", "878264hef");
		System.out.println(result);
	}
	
	@Test
	public void testQuery() {
		String clxh = "878264hef";
		VehicleModel vehicleModel = vehicleModelDao.queryVehicleModelByClxh(clxh);
		System.out.println(vehicleModel.getCLLX());
	}
	

	@Test
	public void testInsert2() {
		int result = vehicleModelDao.insertVehicleType("0002912", "龙马牌", "878264hef", "5", "C31", "23",
				"6", "878264hef", "878264hef", "878264hef", "878264hef");
		System.out.println(result);
	}

}
