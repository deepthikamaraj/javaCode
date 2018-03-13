package com.cognizant.opserv.sp.view;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************.
 *
 * @class SpViewBatch
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 *  @since 01/04/2016
 * ***************************************************************************
 */
public class SpViewBatch {
	
	/**
	 * The private empty constructor
	 */
	private SpViewBatch (){
		
	}

	/** The context has all the services of Abstract Application Context */
	private static AbstractApplicationContext context = null;
	
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(SpViewBatch.class);

	public static void main(String[] args) {
		try {
			LOGGER.info("Start of Sp View Batch...");
			Runtime.getRuntime().addShutdownHook(SpViewBatch.getShutdownThread());
			context = new ClassPathXmlApplicationContext("applicationContext-view.xml");
			LOGGER.info("Sp View Batch started successfully");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
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
    		LOGGER.info("********* End of SP View Batch ************");
        }
    }	
}