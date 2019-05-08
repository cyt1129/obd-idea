package com.obd.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.obd.dao.CarIotInfoDao;
import com.obd.entity.CarIotInfo;
import com.obd.entity.VehicleModel;
import com.obd.exception.CannotFindException;
import com.obd.service.CarIotInfoService;

@Service
public class CarIotInfoServiceImpl implements CarIotInfoService {

	// 日志
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private CarIotInfoDao carIotInfoDao;

	/**
	 * 
	 * 通过车架号查实时数据 带limit参数
	 * 
	 */

	public CarIotInfo[] queryLimitedDataInfo(String clsbhm, int limit) {
		CarIotInfo[] cii = null;
		try {
			cii = carIotInfoDao.queryLimitedDataInfo(clsbhm,limit);
			return cii;
		} catch (Exception e) {
			logger.error("inner error");
			throw new CannotFindException("cannot find");
		}

	}

	/**
	 * 
	 * 通过车架号查实时数据 默认10条
	 */
	public CarIotInfo[] queryDataInfo(String clsbhm) throws CannotFindException {
		CarIotInfo[] cii = null;
		try {
			cii = carIotInfoDao.queryDataInfo(clsbhm);
			return cii;
		} catch (Exception e) {
			logger.error("inner error");
			throw new CannotFindException("cannot find");
		}
	}

}
