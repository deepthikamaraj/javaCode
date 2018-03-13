/******************************************************************************
 *  
 * @class ListenerErrorHandler provides error info while processing message.
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 10/29/2014
 *
 *****************************************************************************/
package com.cognizant.opserv.sp.batch.util;

import org.springframework.util.ErrorHandler;

import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

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
