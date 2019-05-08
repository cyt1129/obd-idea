package com.obd.dao;

import org.apache.ibatis.annotations.Param;

import com.obd.entity.VehicleModel;

public interface VehicleModelDao {
	
	/**
	 * 插入车辆型号
	 * @param CLXH
	 * @param CLPP
	 * @param FDJXH
	 * @param RLZL
	 * @param CLLX
	 * @param QCFL
	 * @param PF
	 * @param SCQYMC
	 * @param FDJSCC
	 * @param optional
	 * @param IUPR
	 * @return 插入成功返回1，else报错
	 */
	int insertVehicleType(@Param("CLXH") String CLXH,@Param("CLPP") String CLPP,@Param("FDJXH") String FDJXH,
			@Param("RLZL") String RLZL,@Param("CLLX") String CLLX,@Param("QCFL") String QCFL,
			@Param("PF") String PF,@Param("SCQYMC") String SCQYMC,@Param("FDJSCC") String FDJSCC,
			@Param("optional") String optional,@Param("IUPR") String IUPR);
	/**
	 * 根据车辆型号查询某一车型信息
	 * @param clxh
	 * @return VehicleModel
	 */
	VehicleModel queryVehicleModelByClxh(String clxh);
	
	/**
	 * 通过车辆类型添加型号
	 * @param vehicleModel
	 * @return
	 */
	int insertVehicleTypeWithEntity(VehicleModel vehicleModel);

}
