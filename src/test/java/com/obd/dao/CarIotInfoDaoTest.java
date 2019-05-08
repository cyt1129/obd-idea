package com.obd.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.obd.entity.CarIotInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class CarIotInfoDaoTest {

	// 注入dao依赖
	@Resource
	private CarIotInfoDao carIotInfoDao;

	@Test
	public void testqueryDataInfo() {
//		String clsbhm = "LZ0B9PD3XK1064417";
//		String clsbhm = "LEZADICB8KF000280";
		String clsbhm = "LFNFVUNX1J1E35755";
		int i;
		CarIotInfo[] carIotInfoList = carIotInfoDao.queryDataInfo(clsbhm);
		for(i = 0;i < carIotInfoList.length;i++) {
			System.out.println(carIotInfoList[i]);
			System.out.println(carIotInfoList[i].getObdResponse());
		}
		System.out.println(i);
	}

	@Test
	public void testqueryLimitedDataInfo() {
		String clsbhm = "98765432100123456";
//		String clsbhm = "LGAX3B135J8038320";
		int i;
		CarIotInfo[] carIotInfoList = carIotInfoDao.queryLimitedDataInfo(clsbhm, 100);
		for(i = 0;i < carIotInfoList.length;i++) {
			System.out.println(carIotInfoList[i]);
			System.out.println(carIotInfoList[i].getObdResponse());
		}
		System.out.println(i);
	}
}
