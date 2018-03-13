package com.cognizant.opserv.sp.storeproc;

import java.util.concurrent.CountDownLatch;

import org.springframework.jdbc.core.JdbcTemplate;

import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

public class ExecuteStoreProc implements Runnable {

	private JdbcTemplate spConfigJdbcTemplate;
	
	private String storedProcName;
	
	private final CountDownLatch latch;
	
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(ExecuteStoreProc.class);
	
	/**
	 * @param spConfigJdbcTemplate
	 * @param storedProcName
	 * @param latch
	 */
	public ExecuteStoreProc(JdbcTemplate spConfigJdbcTemplate, String storedProcName, CountDownLatch latch){
		this.spConfigJdbcTemplate = spConfigJdbcTemplate;
		this.storedProcName = storedProcName;
		this.latch = latch;
	}
	
	@Override
	public void run(){
		
		try{
			LOGGER.info("call stored proc - start");
			spConfigJdbcTemplate.execute("CALL " + storedProcName + "()");
			LOGGER.info("call stored proc - end");
		} catch (Exception e) {
			LOGGER.error("Exception while running stored Proc in ExecuteStoreProc : "+e.getMessage());
			throw new OpservDataAccessException("Exception while running stored Proc in ExecuteStoreProc", e);
		}finally{
			latch.countDown();
			LOGGER.info("call stored proc - end finally");
		}
	}
	

}
