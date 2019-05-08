package com.obd.entity;

public class CarIotResponse {

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

	// 报错后的错误信息对象
	private String error;
	
	public CarIotResponse() {}

	// 添加一个构造函数
	public CarIotResponse(String requestTime, String code, String message, int version) {
		this.requestTime = requestTime;
		this.code = code;
		this.message = message;
		this.version = version;
	}
	//?????怎么处理sql返回null，进入构造函数报错的问题
	public CarIotResponse(Object requestTime, Object code, Object message, Object version) {
		this.requestTime = requestTime == null?null:requestTime.toString();
		this.code = code == null?null:code.toString();
		this.message = message == null?null:message.toString();
		this.version = version == null?null:Integer.parseInt((String)version);
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

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "CarIotResponse [requestTime=" + requestTime + ", code=" + code + ", exchangeType=" + exchangeType
				+ ", responseTime=" + responseTime + ", message=" + message + ", version=" + version + ", error=" + error + "]";
	}

}
