package com.obd.entity;

import java.sql.Timestamp;
import java.util.Date;

import com.obd.dto.OBDResponse;

public class CarIotInfo {

	private long id;

	private String vin;

	private Timestamp gmtCreate;

	private String deviceStatus;

	private String content;

	private long obdResponseId;

	private CarIotResponse carIotResponse;
	
	public CarIotInfo() {
		
	}

	//正真有用的构造函数
	public CarIotInfo(long id, String vin, Timestamp gmtCreate, String deviceStatus, String content, long obdResponseId,
			String requestTtime,String code,String message,int version) {
		this.id = id;
		this.vin = vin;
		this.gmtCreate = gmtCreate;
		this.deviceStatus = deviceStatus;
		this.content = content;
		this.obdResponseId = obdResponseId;
		this.carIotResponse = new CarIotResponse(requestTtime,code,message,version);
	}

	public CarIotInfo(long id, String vin, Timestamp gmtCreate, String deviceStatus, String content, long obdResponseId,
			CarIotResponse carIotResponse) {
		super();
		this.id = id;
		this.vin = vin;
		this.gmtCreate = gmtCreate;
		this.deviceStatus = deviceStatus;
		this.content = content;
		this.obdResponseId = obdResponseId;
		this.carIotResponse = carIotResponse;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public CarIotResponse getObdResponse() {
		return carIotResponse;
	}

	public void setObdResponse(CarIotResponse obdResponse) {
		this.carIotResponse = obdResponse;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Timestamp gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public String getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public long getObdResponseId() {
		return obdResponseId;
	}

	public void setObdResponseId(long obdResponseId) {
		this.obdResponseId = obdResponseId;
	}

	@Override
	public String toString() {
		return "CarIotInfo [vin=" + vin + ", gmtCreate=" + gmtCreate + ", deviceStatus=" + deviceStatus + ", content="
				+ content + ", obdResponseId=" + obdResponseId + "]";
	}

}
