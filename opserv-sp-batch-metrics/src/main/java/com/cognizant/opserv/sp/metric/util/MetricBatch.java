package com.cognizant.opserv.sp.metric.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/******************************************************************************
 *  
 * @class MetricBatch executes as Quartz Server Main Class with default behavior.
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 *****************************************************************************/
public final class MetricBatch {

	/** The Constant QUARTZ_METRIC_CONFIG_XML. */
	private static final String QUARTZ_METRIC_CONFIG_XML = "metric-batch-config.xml";
	
	/**
	 * @Method MetricBatch - Instantiates a new metric batch.
	 * @param
	 * @return void
	 */
	private MetricBatch(){

	}	

	/**
	 * @Method main - main method of java thread
	 * @return void
	 */
	public static void main(String[] args) {		
		//String serverType = args[0];
		//THIS PROPERTY WE ARE REFFERING AT HEART BEAT STATUS CHECK
		System.setProperty("quartzServerType", "metric");
		initiateMetricServer();
	}

	/**
	 * @Method initiateMetricServer - Initiate metric server.
	 * @param
	 * @return void
	 */
	@SuppressWarnings({ "unused", "resource" })
	private static void initiateMetricServer(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext(QUARTZ_METRIC_CONFIG_XML);
	}





}
