package com.cognizant.opserv.sp.notification.listener;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************.
 *
 * @class TriggerCRProcess
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 01/04/2016
 * 
 * ***************************************************************************
 */
public class TestListener {
	
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TestListener.class);
	
	/** The context has all the services of Abstract Application Context */
	private static AbstractApplicationContext context = null;
	
	
	public static void main(String args[]){
		System.setProperty("opserv.config.file", "C:\\opserv\\config\\opserv-config.properties");
		System.setProperty("notificationCenter.activeMQ.brokerURL", "tcp://10.240.151.45:61616");
		System.setProperty("opserv.activeMQ.brokerURL", "tcp://10.240.151.45:61616");
//		System.setProperty("opserv.activeMQ.brokerURL", "tcp://10.232.199.132:61616");
		System.setProperty("distributed", "true");
		System.setProperty("appType", "standalone");
		System.setProperty("targetDataSource", "app");
		System.setProperty("appName", "NotificationBatch");
		
		try{
			LOGGER.info("Start of SP notification Batch");
			Runtime.getRuntime().addShutdownHook(TestListener.getShutdownThread());
			context = new ClassPathXmlApplicationContext("notification-batch-config.xml");
			System.out.println("start");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		//context.close();
	}
	
	
    /**
     * @method getShutdownThread-Gets the shutdown thread.
     *
     * @return the shutdown thread
     */
    private static Thread getShutdownThread() {
    	return new CleanShutdownHook();
    }
    
    /**
     * @method terminate-Terminate the application Context.
     */
    private static void terminate() {
    	try {
    		if(context != null) {
    			LOGGER.info("Shutting down the Application Context now ....");
    			context.close();
    			context.registerShutdownHook();
    			context = null;
    		}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
    }
    
	/**
	 * The Class CleanShutdownHook.
	 *@author Cognizant Technology Solutions
	 */
	static class CleanShutdownHook extends Thread {
    	
	   /** 
	     * @method run-run method
	     */
	    public void run(){
    		LOGGER.info("Shutting down initiated....");
    		terminate();
    		LOGGER.info("**** SHUTDOWN COMPLETED ****");
    		LOGGER.info("********* End of SP Document Store Batch ************");
        }
    }

}
