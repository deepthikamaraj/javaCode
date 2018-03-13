package com.cognizant.opserv.sp.view;

import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************.
 *
 * @class TriggerView
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 *  @since 01/04/2016
 * ***************************************************************************
 */
public class TriggerView {
	
	/**
	 * The private empty constructor
	 */
	private TriggerView (){
		
	}
	
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TriggerView.class);
	
	public static void main(String args[]){
		System.setProperty("opserv.config.file", "C:\\opserv\\config\\opserv-config.properties");

		AbstractXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-view.xml");
		LOGGER.info("Context started with applicationContext-view.xml");
		//context.close();
	}

}
