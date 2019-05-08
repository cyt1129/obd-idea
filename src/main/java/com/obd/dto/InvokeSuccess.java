package com.obd.dto;

public class InvokeSuccess {
	
	private String requestTime;
	
	private String code;
	
	private int version;
	
	private String obdzt;
	
	private String clsbhm;
	
	private String deviceSn;
	
	private String jqsj;
	
	private String message;

	//已鉴权车辆有body没message
	public InvokeSuccess(String requestTime, String code, int version, String obdzt, String clsbhm, String deviceSn,
			String jqsj) {
		super();
		this.requestTime = requestTime;
		this.code = code;
		this.version = version;
		this.obdzt = obdzt;
		this.clsbhm = clsbhm;
		this.deviceSn = deviceSn;
		this.jqsj = jqsj;
	}
	
	//未鉴权车辆，只有message
	public InvokeSuccess(String requestTime, String code, int version, String message) {
		super();
		this.requestTime = requestTime;
		this.code = code;
		this.version = version;
		this.message = message;
	}




	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getObdzt() {
		return obdzt;
	}

	public void setObdzt(String obdzt) {
		this.obdzt = obdzt;
	}

	public String getClsbhm() {
		return clsbhm;
	}

	public void setClsbhm(String clsbhm) {
		this.clsbhm = clsbhm;
	}

	public String getDeviceSn() {
		return deviceSn;
	}

	public void setDeviceSn(String deviceSn) {
		this.deviceSn = deviceSn;
	}

	public String getJqsj() {
		return jqsj;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setJqsj(String jqsj) {
		this.jqsj = jqsj;
	}

	@Override
	public String toString() {
		return "InvokeSuccess [requestTime=" + requestTime + ", code=" + code + ", version=" + version + ", obdzt="
				+ obdzt + ", clsbhm=" + clsbhm + ", deviceSn=" + deviceSn + ", jqsj=" + jqsj + "]";
	}
	
	
	
}


