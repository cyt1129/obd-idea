package com.obd.dto;

public class RegisterExecution {

	// 鉴权车辆编号
	private String sbbh;

	// 执行鉴权结果状态码
	private int status;

	// 状态码解释
	private String statusInfo;

	// 写入数据库状态消息
	private String sqlInfo;

	// 鉴权成功结果构造函数
	public RegisterExecution(String sbbh, int status, String statusInfo) {
		this.sbbh = sbbh;
		this.status = status;
		this.statusInfo = statusInfo;
	}

	//写入数据库失败构造函数
	public RegisterExecution(String sbbh, int status, String statusInfo, String sqlMessage) {
		this.sbbh = sbbh;
		this.status = status;
		this.statusInfo = statusInfo;
		this.sqlInfo = sqlMessage;
	}


	public String getSbbh() {
		return sbbh;
	}

	public void setSbbh(String sbbh) {
		this.sbbh = sbbh;
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

	
	public String getSqlInfo() {
		return sqlInfo;
	}

	public void setSqlInfo(String sqlMessage) {
		this.sqlInfo = sqlMessage;
	}

	@Override
	public String toString() {
		return "RegisterExecution [sbbh=" + sbbh + ", status=" + status + ", statusInfo=" + statusInfo + ", sqlMessage="
				+ sqlInfo + "]";
	}
	
	

}
