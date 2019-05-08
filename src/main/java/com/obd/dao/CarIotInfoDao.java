package com.obd.dao;

import org.apache.ibatis.annotations.Param;

import com.obd.entity.CarIotInfo;

public interface CarIotInfoDao {

	/**
	 * 查车辆最新数据列表
	 * 默认最新10条
	 */
	CarIotInfo[] queryDataInfo(@Param("clsbhm")String clsbhm);
	
	/**
	 * 查车辆最新数据列表
	 * 有limit
	 */
	CarIotInfo[] queryLimitedDataInfo(@Param("clsbhm")String clsbhm,@Param("limit")int limit);
}
