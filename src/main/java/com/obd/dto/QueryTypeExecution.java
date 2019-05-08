package com.obd.dto;

import com.obd.entity.VehicleModel;

public class QueryTypeExecution {

	private String clxh;

	private int status;

	private String statusInfo;

	private VehicleModel vehicleModel;

	public QueryTypeExecution(String clxh, int status, String statusInfo, VehicleModel vehicleModel) {
		this.clxh = clxh;
		this.status = status;
		this.statusInfo = statusInfo;
		this.vehicleModel = vehicleModel;
	}

	public QueryTypeExecution(String clxh, int status, String statusInfo) {
		super();
		this.clxh = clxh;
		this.status = status;
		this.statusInfo = statusInfo;
	}

	public String getClxh() {
		return clxh;
	}

	public void setClxh(String clxh) {
		this.clxh = clxh;
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

	public VehicleModel getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(VehicleModel vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

}
