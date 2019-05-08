package com.obd.service;

import java.util.Map;

import com.obd.dto.QueryAuthedExecution;
import com.obd.dto.RegisterExecution;
import com.obd.entity.CarIotInfo;
import com.obd.entity.Vehicle;
import com.obd.entity.VehicleModel;

/**
 * 1.添加鉴权成功设备 2、查询鉴权成功设备 3、添加设备类型 4、查询设备类型
 */
public interface VehicleService {

	/**
	 * 传入数据进行鉴权
	 * 
	 * @param report
	 * @return 鉴权结果（包括写入数据库结果）
	 */
	RegisterExecution registerVehicle(Map<String, String> report);

	/**
	 * 查询车辆是否鉴权与鉴权信息
	 * 
	 * @return
	 */
	QueryAuthedExecution queryIfAuthedByVin(String clsbhm);

	/**
	 * 添加数据至vehicle表
	 * 
	 * @param report
	 * @return
	 */
	int updateVehicleList(Vehicle report);

	/**
	 * 添加车辆型号clxh
	 * 
	 * @param vehicleModel
	 * @return
	 */
	int updateVehicleModelTable(VehicleModel vehicleModel);

	/**
	 * 通过车辆型号查找车辆类型
	 * 
	 * @param clxh
	 * @return
	 */
	VehicleModel getVehicleModel(String clxh);

	/**
	 * 插入iccid
	 * @param iccid
	 * @param sbbh
	 */
	void updateICCID(String iccid,String clsbhm);
}
