package com.obd.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.obd.entity.Vehicle;

public interface VehicleDao {

	/**
	 * 添加鉴权成功车辆
	 * 
	 * @param id
	 * @param name
	 * @param node_id
	 * @param CLXH
	 * @param CLPP
	 * @param HPZL
	 * @param CLSBHM
	 * @param FDJH
	 * @param CCDJRQ
	 * @param SYR
	 * @param LXDH
	 * @param SBBH
	 * @param XSDW
	 * @param SCRQ
	 * @param OBDauth
	 * @return
	 */
	int insertVehicle(@Param("id") String id, @Param("name") String name, @Param("node_id") String node_id,
			@Param("CLXH") String CLXH, @Param("CLPP") String CLPP, @Param("HPZL") String HPZL,
			@Param("CLSBHM") String CLSBHM, @Param("FDJH") String FDJH, @Param("CCDJRQ") String CCDJRQ,
			@Param("SYR") String SYR, @Param("LXDH") String LXDH, @Param("SBBH") String SBBH,
			@Param("XSDW") String XSDW, @Param("SCRQ") String SCRQ, @Param("OBDauth") Date OBDauth);

	/**
	 * 根据车辆vin号查询车辆鉴权信息
	 * 
	 * @param sbbh
	 * @return
	 */
	Vehicle queryVehicleByVIN(String sbbh);

	/**
	 * 传入vehcile对象添加 注意！和第一个方法传入参数的顺序不一样
	 * 
	 * @param device
	 * @return
	 */
	int insertDeviceWithEntity(Vehicle device);

	/**
	 * 修改iccid👌
	 * 
	 * @param iccid
	 */
	void updateVehicleICC(@Param("iccid") String iccid,@Param("clsbhm") String clsbhm);
}
