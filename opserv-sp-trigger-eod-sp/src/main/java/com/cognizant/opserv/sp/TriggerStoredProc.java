package com.cognizant.opserv.sp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cognizant.opserv.sp.persistence.dao.service.impl.TriggerSPStoredProcDAOImpl;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

public class TriggerStoredProc {

	@Autowired
	private TriggerSPStoredProcDAOImpl triggerSPStoredProcDAOImpl;
	
	/** The context has all the services of Abstract Application Context */
	private static AbstractApplicationContext context = null;
	
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TriggerStoredProc.class);
	
	public static void main(String[] args) {
		System.setProperty("opserv.config.file", "C:\\opserv\\config\\opserv-config.properties");
		
		LOGGER.info("Execute stored proc batch start");
		
		try {
//			Runtime.getRuntime().addShutdownHook(TriggerStoredProc.getShutdownThread());
			context = new ClassPathXmlApplicationContext("applicationContext-trigger-sp.xml");
			LOGGER.info("Execute stored proc batch started successfully");
			TriggerStoredProc triggerStoredProc = (TriggerStoredProc)context.getBean("triggerStoredProc");
			triggerStoredProc.trigger();
			context.close();
			LOGGER.info("Execute stored proc batch closed");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	private void trigger(){
		try{
			triggerSPStoredProcDAOImpl.triggerStoredProc();
		}catch(OpservDataAccessException opDataAccessEx){
			LOGGER.error("Error while executing Stored Proc : "+opDataAccessEx.getMessage());
		}
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
