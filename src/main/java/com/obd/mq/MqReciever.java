package com.obd.mq;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.TimeoutException;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.obd.service.impl.WebServiceImpl;
import com.obd.util.Constant;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class MqReciever extends TimerTask {

	// 和producer的queue是同一个
	private final static String QUEUE_NAME = "hello";

	private ConnectionFactory connectionFactory;

	private WebServiceImpl webService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private Connection connection;
	private Channel channel;

	public MqReciever() {
		connectionFactory = new ConnectionFactory();
		connectionFactory.setHost(Constant.localhost);
		webService = new WebServiceImpl();
	}

	public void run() {
		try {
			// 这里不用try-with-resource是因为接收方要一直监听队列，一旦推出就收不到数据了
			connection = connectionFactory.newConnection();
			channel = connection.createChannel();

			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
			// 声明回调函数，当queue把message push过来就要调用这个函数
			DeliverCallback deliverCallback = (consumerTag, delivery) -> {
				String str = new String(delivery.getBody(), "UTF-8");
				logger.info("[mqReceived]" + str);
//				System.out.println(" [x] Received '" + str + "'");
				try {
					/** 处理数据发送给obd中心平台与thingsboard平台 **/
					String sjxx = str.substring(0, 14);
					String sbbh = str.split(":")[0].substring(15, 32);
					if (str.substring(14, 15).equals("0")) {
						// 普通数据,逐条发送，收到一条发一条
						String[] report = str.split(":")[1].split(",");
						Map<String, String> body = new HashMap<String, String>();
						for (int i = 0; i < report.length; i++) {
							body.put(Constant.writeData[i], report[i]);
						}
						body.put("SJXX", sjxx);

						// 发至obd中心平台
						try {
							webService.sendUpdateMsg2OBD(body, sbbh);
						} catch (Exception e) {
							logger.error(e.getMessage(), "发送失败");
						}
						// 发至thingsboard
						String gpsStr = "OBDGPS," + report[15] + "," + report[16] + "," + report[17] + "," + report[29];
						JSONObject jsonParam = new JSONObject();
						jsonParam.put("H0920", gpsStr);
						String url = Constant.thingsboard_tele + sbbh + "/telemetry";
						boolean r = webService.httpPostWithJson(jsonParam, url);
						if (r) {
							logger.info("[thingsboard] 发送成功");
						} else {
							logger.error("[thingsboard] 发送失败");
						}

					} else {
						// 故障信息处理
						String[] gzReport = str.split(":")[1].split(";");
						/**
						 * 添加到数据库不知道怎么导入那个service String iccid = gzReport[0];
						 * vehicleService.updateICCID(iccid, sbbh);
						 * 
						 */
						String[] report = gzReport[1].split(",");
						Map<String, String> body = new HashMap<String, String>();
						body.put("GZSJ", sjxx);
						body.put("SBBH", sbbh);
						body.put("SPN", report[0]);
						body.put("SPNNAME", report[1]);
						body.put("FMI", report[2]);
						body.put("FMINAME", "未知故障");
						webService.sendFaultMsg2OBD(body, sbbh);
					}
				} catch (Exception e) {
					logger.error(e.getMessage(),"reciever数据处理失败");
				}
			};

			// 设置
			channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
			});
		} catch (Exception e) {
			logger.error(e.getMessage(), "reforward msg failed");
		}

	}
	
	public void stop() {
		try {
			channel.close();
			connection.close();
			logger.info("mq连接已关闭");
		} catch (IOException e) {
			logger.error(e.getMessage(),"mq连接关闭失败");
		} catch (TimeoutException e) {
			logger.error(e.getMessage(),"mq连接关闭失败");
		}
		
	}

	public static void main(String[] args) {
		MqReciever mqReciever = new MqReciever();
		mqReciever.run();
	}

}
