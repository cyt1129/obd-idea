package com.obd.service;

import com.obd.entity.CarIotInfo;

public interface CarIotInfoService {
	
	/**
	 * 
	 * 通过车架号查实时数据
	 * 带limit参数
	 * 
	 */
	
    CarIotInfo[] queryLimitedDataInfo(String clsbhm, int limit);
    /**
	 * 
	 * 通过车架号查实时数据
	 * 默认10条
	 */
    CarIotInfo[] queryDataInfo(String clsbhm);

}
