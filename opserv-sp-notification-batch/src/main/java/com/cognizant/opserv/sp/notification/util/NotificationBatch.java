package com.cognizant.opserv.sp.notification.util;

/******************************************************************************
 *  
 * @class MetricBatch executes as Quartz Server Main Class with default behavior.
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 10/29/2014
 * COPYRIGHT (C) 2014 Cognizant, all rights reserved.
 *****************************************************************************/


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public final class NotificationBatch {

	/** The Constant QUARTZ_METRIC_CONFIG_XML. */
	private static final String NOTIFICATION_BATCH_CONFIG_XML = "notification-batch-config.xml";
	
	/**
	 * @Method MetricBatch - Instantiates a new metric batch.
	 * @param
	 * @return void
	 */
	private NotificationBatch(){

	}	

	/**
	 * @Method main - main method of java thread
	 * @return void
	 */
	public static void main(String[] args) {		
		
		initiateNotificationBatchServer();
	}

	/**
	 * @Method initiateMetricServer - Initiate metric server.
	 * @param
	 * @return void
	 */
	@SuppressWarnings({ "unused", "resource" })
	private static void initiateNotificationBatchServer(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext(NOTIFICATION_BATCH_CONFIG_XML);
	}

}
