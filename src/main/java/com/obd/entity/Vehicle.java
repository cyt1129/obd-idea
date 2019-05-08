package com.obd.entity;

import java.util.Date;
import java.util.Map;
import java.sql.Timestamp;

public class Vehicle {

	private String SBBH;// 设备编号（必填）
	
	private String id;// deviceId不发给OBD
	
	private String node_id;// node_id
	
	private String name;// 号牌号码 name
	
	private String CLPP;// 车辆品牌（必填）
	
	private String CLXH;// 车辆型号（必填）
	
	private String CLSBHM;// 车架号（必填）
	
	private String FDJH;// 发动机号（必填）
	
	private String SCRQ;// 生产日期（必填）
	
	private String CCDJRQ;// 登记日期
	
	private String HPZL;// 号牌种类
	
	private String SYR;// 所有人
	
	private String LXDH;// 联系电话
	
	private String XSDW;// 销售单位
	
	private Date OBDauth;//鉴权时间
	
	private String ICCID;//iccid
	
	//private String optional;// 所有剩下的参数
	private VehicleModel vehicleModel;
	
	//insert数据库的构造函数
	public Vehicle(String sBBH, String id, String node_id, String name, String cLPP, String cLXH, String cLSBHM,
			String fDJH, String sCRQ, String cCDJRQ, String hPZL, String sYR, String lXDH, String xSDW, Date oBDauth) {
		this.SBBH = sBBH;
		this.id = id;
		this.node_id = node_id;
		this.name = name;
		this.CLPP = cLPP;
		this.CLXH = cLXH;
		this.CLSBHM = cLSBHM;
		this.FDJH = fDJH;
		this.SCRQ = sCRQ;
		this.CCDJRQ = cCDJRQ;
		this.HPZL = hPZL;
		this.SYR = sYR;
		this.LXDH = lXDH;
		this.XSDW = xSDW;
		this.OBDauth = oBDauth;
	}
	
	public Vehicle(String sBBH, String id, String node_id, String name, String cLPP, String cLXH, String cLSBHM,
			String fDJH, String sCRQ, String cCDJRQ, String hPZL, String sYR, String lXDH, String xSDW, Timestamp oBDauth,String iccid) {
		this.SBBH = sBBH;
		this.id = id;
		this.node_id = node_id;
		this.name = name;
		this.CLPP = cLPP;
		this.CLXH = cLXH;
		this.CLSBHM = cLSBHM;
		this.FDJH = fDJH;
		this.SCRQ = sCRQ;
		this.CCDJRQ = cCDJRQ;
		this.HPZL = hPZL;
		this.SYR = sYR;
		this.LXDH = lXDH;
		this.XSDW = xSDW;
		this.OBDauth = oBDauth;
		this.ICCID = iccid;
	}
	
	
	
	public Vehicle(Map<String,String> map,Date obdauth) {
	    if(map.containsKey("id")) {
	    	  this.id = map.get("id");
	    }
	    if(map.containsKey("node_id")) {
	    	  this.node_id = map.get("node_id");
	    }
	    this.name = map.get("HPHM");
	    this.SBBH = map.get("SBBH");
	    this.CLXH = map.get("CLXH");
	    this.CLPP = map.get("CLPP");
	    this.HPZL = map.get("HPZL");
	    this.CLSBHM = map.get("CLSBHM");
	    this.FDJH = map.get("FDJH");
	    this.CCDJRQ = map.get("CCDJRQ");
	    this.SYR = map.get("SYR");
	    this.LXDH = map.get("LXDH");
	    this.XSDW = map.get("XSDW");
	    this.SCRQ = map.get("SCRQ");//生产日期（必填）
	    this.OBDauth = obdauth;
	    
}

	public String getSBBH() {
		return SBBH;
	}

	public void setSBBH(String sBBH) {
		this.SBBH = sBBH;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNode_id() {
		return node_id;
	}

	public void setNode_id(String node_id) {
		this.node_id = node_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCLPP() {
		return CLPP;
	}

	public void setCLPP(String cLPP) {
		CLPP = cLPP;
	}

	public String getCLXH() {
		return CLXH;
	}

	public void setCLXH(String cLXH) {
		CLXH = cLXH;
	}

	public String getCLSBHM() {
		return CLSBHM;
	}

	public void setCLSBHM(String cLSBHM) {
		CLSBHM = cLSBHM;
	}

	public String getFDJH() {
		return FDJH;
	}

	public void setFDJH(String fDJH) {
		FDJH = fDJH;
	}

	public String getSCRQ() {
		return SCRQ;
	}

	public void setSCRQ(String sCRQ) {
		SCRQ = sCRQ;
	}

	public String getCCDJRQ() {
		return CCDJRQ;
	}

	public void setCCDJRQ(String cCDJRQ) {
		CCDJRQ = cCDJRQ;
	}

	public String getHPZL() {
		return HPZL;
	}

	public void setHPZL(String hPZL) {
		HPZL = hPZL;
	}

	public String getSYR() {
		return SYR;
	}

	public void setSYR(String sYR) {
		SYR = sYR;
	}

	public String getLXDH() {
		return LXDH;
	}

	public void setLXDH(String lXDH) {
		LXDH = lXDH;
	}

	public String getXSDW() {
		return XSDW;
	}

	public void setXSDW(String xSDW) {
		XSDW = xSDW;
	}

	public Date getOBDauth() {
		return OBDauth;
	}

	public void setOBDauth(Date oBDauth) {
		OBDauth = oBDauth;
	}

	public VehicleModel getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(VehicleModel vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public String getICCID() {
		return ICCID;
	}

	public void setICCID(String iCCID) {
		ICCID = iCCID;
	}

	@Override
	public String toString() {
		return "Vehicle [SBBH=" + SBBH + ", id=" + id + ", node_id=" + node_id + ", name=" + name + ", CLPP=" + CLPP
				+ ", CLXH=" + CLXH + ", CLSBHM=" + CLSBHM + ", FDJH=" + FDJH + ", SCRQ=" + SCRQ + ", CCDJRQ=" + CCDJRQ
				+ ", HPZL=" + HPZL + ", SYR=" + SYR + ", LXDH=" + LXDH + ", XSDW=" + XSDW + ", OBDauth=" + OBDauth
				+ ", vehicleModel=" + vehicleModel + "]";
	}
	
	
}
