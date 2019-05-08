package com.obd.util;

public class Constant {
	public static final String miaolian = "111.111.111.111";
	public static final String localhost = "localhost";
	
	public static final String thingsboard_tele = "http://localhost:8080/api/v1/";

 /***********************************************OBD中心平台******************************************************/
//    IP:port for OBD center platform 正式80，测试8080
//    public static String OBD_URL = "http://222.222.222.222:8080";
    public static String OBD_URL = "http://222.222.222.222";
    /*
     * API for OBD
     */
    public static final String OBD_AUTH = OBD_URL + "/obd_exg/services/authObjectOut.do";
    public static final String OBD_WRITE_OBJECT = OBD_URL + "/obd_exg/services/writeObjectOut.do";
    public static final String OBD_WRITE_FAULT = OBD_URL + "/obd_exg/services/writeFaultObjectOut.do";
    public static final String OBD_BEAT_OBJECT = OBD_URL + "/obd_exg/services/beatObjectOut.do";
    public static final String OBD_INVOKE_OBJECT = OBD_URL + "/obd_exg/services/invokObjectOut.do";
    
    /*
     * for OBD center platform
     */
    public static final String jkYhm = "******";
    public static final String jkSqm = "****";
    public static final String cnbh = "*******";
    
    /*
     * key for AES encode
     */
    public static final String AES_KEY = "***********";
   
    /*
     * OBD交换码，exchangeType
     * 00 心跳包
     */
    public static final String EXTYPE_HEARTBEAT = "00";
    public static final String EXTYPE_WRITEIN = "10";
    public static final String EXTYPE_FAULT = "20";
    public static final String EXTYPE_AUTH = "30";
    public static final String INVOKE = "40";
    
    /*
     * 写入类参数集合 SCR——尾气处理使用scr的需要上报的数据  TB——需要发到thingsboard的数据  自定义——杭州平台对接协议中未规定数据
     * 0、车速  CS
     * 1、大气压力 DQYL
     * 2、实际扭矩百分比 SJNJBFB
     * 3、摩擦扭矩百分比 MCNJBFB
     * 4、发动机转速 FDJZS
     * 5、发动机燃料流量 FDJRLLL
     * 6、后处理上游氮氧浓度(SCR) JQDYCLZ
     * 7、后处理下游氮氧浓度(SCR) HCLXYDYND
     * 8、尿素箱液位(SCR) NSXYW
     * 9、进气流量 JQLL
     * 10、后处理上游排气温度 HCLSYPQWD
     * 11、后处理下游排气温度(SCR) HCLXYPQWD
     * 12、载体压差（DPF/POC）ZTYC
     * 13、冷却液温度 LQYWD
     * 14、油箱液位 YXYW
     * 15、定位状态(TB) DWZT      TB
     * 16、经度(TB) JD           TB
     * 17、纬度(TB) WD            TB
     * 18、总行驶里程 ZXSLC
     * 19、发送机净输出扭矩(自定义) fdjjscnj
     * 20、发动机扭矩模式 FDJNJMS
     * 21、油门踏板 YMTB
     * 22、总油耗 ZYH
     * 23、尿素箱温度(SCR) NSXWD
     * 24、实际尿素喷射量 scr SJNSPSL
     * 25、总尿素消耗 scr ZNSXH
     * 26、DPF排气温度 dpf PQWD
     * ========以上为终端对接要求数据=========
     * 27、DPF排气背压 dpf 默认NA PQBY
     * 28、环境温度 HJWD
     * 29、地面航向(自定义 TB) dmhx        TB
     * 30、参考扭矩 必填项 CKNJ
     * 31、MIL灯状态 MDZT
     * 32、发动机总运行时间 FDJZYXSJ
     * 33、瞬时油耗 国五要求 SSYH
     * 34、进气氧含量 doc国四 JQYHL
     * 35、排气氧含量 doc国四 PQYHL
     * 36、进气温度 JQWD
     * 37、基于PEMS的Nox排放值 JYPEMSDNOXPFZ
     * 38、ECU数据篡改监测 ECUSJCGJC
     */ 
    public static final String[] writeData = new String[] {"CS","DQYL","SJNJBFB","MCNJBFB","FDJZS",
    		                                                   "FDJRLLL","JQDYCLZ","HCLXYDYND","NSXYW","JQLL",
    		                                                   "HCLSYPQWD","HCLXYPQWD","ZTYC","LQYWD","YXYW",
    		                                                   "DWZT","JD","WD","ZXSLC","fdjjscnj",
    		                                                   "FDJNJMS","YMTB","ZYH","NSXWD","SJNSPSL",
    		                                                   "ZNSXH","PQWD","PQBY","HJWD","dmhx",
    		                                                   "CKNJ","MDZT","SSYH","FDJZYXSJ","JQYHL",
    		                                                   "PQYHL","JQWD","JYPEMSDNOXPFZ","ECUSJCGJC"};

	public static String getOBD_URL() {
		return OBD_URL;
	}

	public static void setOBD_URL(String oBD_URL) {
		OBD_URL = oBD_URL;
	}
	
	/**
	 * 记录实时数据条数
	 */
	public static int count;
	
	/**
	 * 返回目前package条数
	 * @return
	 */
	public static int getCount() {
		return count;
	}
	
	public static void updateCount() {
		count++;
	}
    
   

}


