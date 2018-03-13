package com.cognizant.opserv.sp.batch.util;

import org.springframework.util.ErrorHandler;

import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************.
 *
 * @class ListenerErrorHandler provides error info while processing message.
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public class ListenerErrorHandler implements ErrorHandler{
	
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(ListenerErrorHandler.class);
	
	/**
	 * @Method handleError - This method handles the error if we got any
	 * @param ex - holds error info
	 * @return void	
	 */
	@Override
	public void handleError(Throwable ex) {
		LOGGER.error("ERROR in LISTENING.......",ex);		
	}

}
