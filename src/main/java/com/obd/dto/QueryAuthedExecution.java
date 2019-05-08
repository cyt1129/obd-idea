package com.obd.dto;

import com.obd.entity.Vehicle;

public class QueryAuthedExecution {

	// 输入的车辆设备编号
	private String clsbhm;

	// 状态码 -1代表未鉴权 0代表有 -2代表其他问题
	private int status;

	// 状态码解释
	private String statusInfo;

	// 车辆鉴权信息
	private Vehicle vehicle;

	// 车辆已鉴权的构造函数
	public QueryAuthedExecution(String clsbhm, int status, String statusInfo, Vehicle vehicle) {
		this.clsbhm = clsbhm;
		this.status = status;
		this.statusInfo = statusInfo;
		this.vehicle = vehicle;
	}

	// 车辆未鉴权的构造函数
	public QueryAuthedExecution(String clsbhm, int status, String statusInfo) {
		this.clsbhm = clsbhm;
		this.status = status;
		this.statusInfo = statusInfo;
	}

	public String getClsbhm() {
		return clsbhm;
	}

	public void setClsbhm(String clsbhm) {
		this.clsbhm = clsbhm;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusInfo() {
		return statusInfo;
	}

	public void setStatusInfo(String statusInfo) {
		this.statusInfo = statusInfo;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public String toString() {
		return "QueryAuthedExecution [clsbhm=" + clsbhm + ", status=" + status + ", statusInfo=" + statusInfo
				+ ", vehicle=" + vehicle + "]";
	}

	
	

}
