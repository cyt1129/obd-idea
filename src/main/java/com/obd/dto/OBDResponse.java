package com.obd.dto;

public class OBDResponse {

	// 请求时间
	private String requestTime;

	// 鉴权状态
	private String code;

	// 消息类型
	private String exchangeType;

	// 回复时间
	private String responseTime;

	// 消息
	private String message;

	// 版本号
	private int version;

	private String exchangeCode;

	// 报错后的错误信息对象
	private String error;

	// 添加一个构造函数

	public String getRequestTime() {
		return requestTime;
	}

	// 含有errorbody的构造函数
	public OBDResponse(String requestTime, String code, String exchangeType, String responseTime, String message,
			int version, String exchangeCode, String error) {
		this.requestTime = requestTime;
		this.code = code;
		this.exchangeType = exchangeType;
		this.responseTime = responseTime;
		this.message = message;
		this.version = version;
		this.exchangeCode = exchangeCode;
		this.error = error;
	}

	// 不含errorbody的构造函数
	public OBDResponse(String requestTime, String code, String exchangeType, String responseTime, String message,
			int version, String exchangeCode) {
		this.requestTime = requestTime;
		this.code = code;
		this.exchangeType = exchangeType;
		this.responseTime = responseTime;
		this.message = message;
		this.version = version;
		this.exchangeCode = exchangeCode;
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

	public String getExchangeType() {
		return exchangeType;
	}

	public void setExchangeType(String exchangeType) {
		this.exchangeType = exchangeType;
	}

	public String getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getExchangeCode() {
		return exchangeCode;
	}

	public void setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "OBDResponse [requestTime=" + requestTime + ", code=" + code + ", exchangeType=" + exchangeType
				+ ", responseTime=" + responseTime + ", message=" + message + ", version=" + version + ", exchangeCode="
				+ exchangeCode + ", error=" + error + "]";
	}

}
