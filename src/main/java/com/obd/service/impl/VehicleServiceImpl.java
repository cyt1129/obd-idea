package com.obd.service.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obd.dao.VehicleDao;
import com.obd.dao.VehicleModelDao;
import com.obd.dto.OBDResponse;
import com.obd.dto.QueryAuthedExecution;
import com.obd.dto.RegisterExecution;
import com.obd.entity.CarIotInfo;
import com.obd.entity.Vehicle;
import com.obd.entity.VehicleModel;
import com.obd.exception.CannotFindException;
import com.obd.exception.InsertFailedExeption;
import com.obd.service.VehicleService;
import com.obd.service.WebService;
import com.obd.util.Constant;

@Service
public class VehicleServiceImpl implements VehicleService {

	// 日志
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private VehicleDao vehicleDao;
	
	@Resource
	private VehicleModelDao vehicleModelDao;

	@Autowired
	private WebService webService;

	/**
	 * 用车辆设备比编号查询鉴权情况 找到返回车辆信息，错误抛出CannotFindException
	 */
	public QueryAuthedExecution queryIfAuthedByVin(String sbbh) throws CannotFindException {
		// 查询是否鉴权，有则返回车辆信息
		String sb = Constant.cnbh + sbbh;
		Vehicle vehicle = vehicleDao.queryVehicleByVIN(sb);
		logger.info("找到了呦");
		if (vehicle == null) {
			throw new CannotFindException("cannot find this vehicle");
		}
		return new QueryAuthedExecution(sbbh, 0, "车辆已鉴权", vehicle);
	}

	/**
	 * 执行鉴权 带optional的信息体注册包括两部分 1.向obd中心平台发送部分 2.写入数据库部分 发送带有option体的信息
	 */
	public RegisterExecution registerVehicle(Map<String, String> report) {
		String sbbh = report.get("SBBH");
		report.put("SBBH", Constant.cnbh + sbbh);
		OBDResponse obdResponse;
		try {
			obdResponse = webService.sendAuthMsg2OBD(report);
			String code = obdResponse.getCode();
			String message = obdResponse.getMessage();
			if (code.equals("1")) {
				// 鉴权成功，写入数据库
				Date authTime = new Date();
				Vehicle vehicle = new Vehicle(report, authTime);
				try {
					// 写入数据库成功
					int insertResult = updateVehicleList(vehicle);
					if (insertResult == 1) {
						return new RegisterExecution(sbbh, 1, message, "写入数据库成功");
					}
					return new RegisterExecution(sbbh, -1, message, "写入数据库失败");
				} catch (InsertFailedExeption e) {
					// 写入数据库失败
					logger.error(e.getMessage());
					return new RegisterExecution(sbbh, -1, message, "写入数据库失败");
				}

			} else {
				// 鉴权失败，有error信息的
				logger.error(obdResponse.getExchangeCode() + ":鉴权失败");
				if (obdResponse.getError() != null) {
					return new RegisterExecution(sbbh, -1, obdResponse.getError(), "写入数据库失败");
				}
				// 鉴权失败 无error信息
				return new RegisterExecution(sbbh, -1, message, "写入数据库失败");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), "鉴权失败");
			return new RegisterExecution(sbbh, -1, "鉴权失败", "写入数据库失败");
		}
	}

	/**
	 * 添加数据至vehicle表
	 * 
	 * @param report
	 * @return
	 */
	public int updateVehicleList(Vehicle vehicle) {
		try {
			int result = vehicleDao.insertDeviceWithEntity(vehicle);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new InsertFailedExeption("insert failed");
		}
	}
	
	/**
	 * 添加车辆型号clxh
	 * @param vehicleModel
	 * @return
	 */
	public int updateVehicleModelTable(VehicleModel vehicleModel) {	
		try {
			int result = vehicleModelDao.insertVehicleTypeWithEntity(vehicleModel);
			return result;
		}catch(Exception e) {
			logger.error(e.getMessage());
			throw new InsertFailedExeption("insert failed");
		}
	}
	
	/**
	 * 查询车辆类型
	 */
	public VehicleModel getVehicleModel(String clxh) throws CannotFindException{
		try {
			VehicleModel vm = vehicleModelDao.queryVehicleModelByClxh(clxh);
			if(vm != null) {
				return vm;
			}
			throw new CannotFindException("cannot find");
		}catch(Exception e) {
			throw new CannotFindException("cannot find");
		}
		
	}
	
	/**
	 * 
	 * 更新iccid号
	 * 
	 */
	public void updateICCID(String iccid,String sbbh) {
		try {
			vehicleDao.updateVehicleICC(iccid, sbbh);
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
	}

}
