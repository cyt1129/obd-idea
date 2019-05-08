package com.obd.timer;

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.obd.dto.OBDResponse;
import com.obd.exception.WebException;
import com.obd.service.impl.WebServiceImpl;

/**
 * 每隔25秒向中心平台发送一个心跳包 Sending a heartbeat package to OBD center platform every 25
 * seconds
 * 
 * @author chenyitian
 * 
 */
public class AccountTask extends TimerTask {
	// 日志
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private WebServiceImpl webService = new WebServiceImpl();

	@Override
	public void run() {
		try {
			//OBDResponse obdResposne = 
			webService.sendBeatMsg2OBD();
		} catch (WebException e) {
			logger.error(e.getMessage(), "发送心跳包失败");
		}
	}

	public static void main(String[] args) {
		AccountTask at = new AccountTask();
		at.run();
	}
}
