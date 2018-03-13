package com.cognizant.opserv.sp.cr.process.batch;

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
public class TriggerCRProcess {
	
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TriggerCRProcess.class);
	
	/** The context has all the services of Abstract Application Context */
	private static AbstractApplicationContext context = null;
	
	public static void main(String args[]){
		//System.setProperty("opserv.config.file", "C:\\opserv\\config\\opserv-config.properties");
		//System.setProperty("appType", "standalone");
		//System.setProperty("targetDataSource", "app");
		//System.setProperty("distributed", "true");
		//System.setProperty("appName", "TriggerCRProcess");
		
		try{
			LOGGER.info("Start of SP TriggerCRProcess Batch");
			Runtime.getRuntime().addShutdownHook(TriggerCRProcess.getShutdownThread());
			String configlocations[] = null;
			if("true".equalsIgnoreCase(System.getProperty("distributed"))) {
				configlocations = new String[]{"applicationContext-request-process.xml","applicationContext-batch-persistence.xml"};
			} else {
				configlocations = new String[]{"applicationContext-request-process.xml","applicationContext-cr-persistence.xml"};
			}
			context = new ClassPathXmlApplicationContext(configlocations);
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		System.out.println("strtd");
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
