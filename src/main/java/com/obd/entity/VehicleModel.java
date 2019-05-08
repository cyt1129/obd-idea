package com.obd.entity;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VehicleModel {

	private String CLXH;// 车辆型号（必填）

	private String CLPP;// 车辆品牌（必填)

	private String FDJXH;// 发动机型号（必填）

	private String RLZL;// 燃料种类（必填）

	private String CLLX;// 车辆类型

	private String QCFL; // 汽车分类

	private String PF;// 排放阶段（必填）

	private String SCQYMC;// 车辆制造商名称（必填）

	private String FDJSCC;// 发动机生产厂（必填）

	private String optional;// 所有剩下的参数

	private String IUPR;// iupr

	public VehicleModel(String cLXH, String cLPP, String fDJXH, String rLZL, String cLLX, String qCFL, String pF,
			String sCQYMC, String fDJSCC, String optional, String iUPR) {
		super();
		CLXH = cLXH;
		CLPP = cLPP;
		FDJXH = fDJXH;
		RLZL = rLZL;
		CLLX = cLLX;
		QCFL = qCFL;
		PF = pF;
		SCQYMC = sCQYMC;
		FDJSCC = fDJSCC;
		this.optional = optional;
		IUPR = iUPR;
	}

	public VehicleModel(Map<String, String> m) {
		CLXH = m.get("CLXH");
		CLPP = m.get("CLPP");
		FDJXH = m.get("FDJXH");
		RLZL = m.get("RLZL");
		CLLX = m.get("CLLX");
		QCFL = m.get("QCFL");
		PF = m.get("PF");
		SCQYMC = m.get("SCQYMC");
		FDJSCC = m.get("FDJSCC");
		this.optional = m.get("optional");
		IUPR = m.get("IUPR");
	}

	@JsonProperty("CLXH")
	public String getCLXH() {
		return CLXH;
	}

	public void setCLXH(String cLXH) {
		CLXH = cLXH;
	}

	@JsonProperty("CLPP")
	public String getCLPP() {
		return CLPP;
	}

	public void setCLPP(String cLPP) {
		CLPP = cLPP;
	}

	@JsonProperty("FDJXH")
	public String getFDJXH() {
		return FDJXH;
	}

	public void setFDJXH(String fDJXH) {
		FDJXH = fDJXH;
	}

	@JsonProperty("RLZL")
	public String getRLZL() {
		return RLZL;
	}

	public void setRLZL(String rLZL) {
		RLZL = rLZL;
	}

	@JsonProperty("CLLX")
	public String getCLLX() {
		return CLLX;
	}

	public void setCLLX(String cLLX) {
		CLLX = cLLX;
	}

	@JsonProperty("QCFL")
	public String getQCFL() {
		return QCFL;
	}

	public void setQCFL(String qCFL) {
		QCFL = qCFL;
	}

	@JsonProperty("PF")
	public String getPF() {
		return PF;
	}

	public void setPF(String pF) {
		PF = pF;
	}

	@JsonProperty("SCQYMC")
	public String getSCQYMC() {
		return SCQYMC;
	}

	public void setSCQYMC(String sCQYMC) {
		SCQYMC = sCQYMC;
	}

	@JsonProperty("FDJSCC")
	public String getFDJSCC() {
		return FDJSCC;
	}

	public void setFDJSCC(String fDJSCC) {
		FDJSCC = fDJSCC;
	}

	public String getOptional() {
		return optional;
	}

	public void setOptional(String optional) {
		this.optional = optional;
	}

	@JsonProperty("IUPR")
	public String getIUPR() {
		return IUPR;
	}

	public void setIUPR(String iUPR) {
		IUPR = iUPR;
	}

}
