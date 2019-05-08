package com.obd.timer;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.obd.mq.MqReciever;

public class AccountTimerListener implements ServletContextListener {

	private Timer timer = null;

//	private MqReciever mqReciever = null;

	public void contextInitialized(ServletContextEvent event) {
		timer = new Timer(true);
//		mqReciever = new MqReciever();
		event.getServletContext().log("定时器已启动");
		// 服务器启动后，延迟10秒启动，25秒执行一次
		timer.scheduleAtFixedRate(new AccountTask(), 5 * 1000, 25 * 1000);
		// 延迟15秒启动rabbitmq comsumer端
		//timer.schedule(mqReciever, 8 * 1000);
//		mqReciever.run();
	}

	public void contextDestroyed(ServletContextEvent event) {
//		if (mqReciever != null) {
//			mqReciever.stop();
//		}
		if (timer != null) {
			timer.cancel();
			event.getServletContext().log("定时器销毁");
		}
		
	}
}
