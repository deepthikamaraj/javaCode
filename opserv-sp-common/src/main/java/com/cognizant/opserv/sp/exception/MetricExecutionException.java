package com.cognizant.opserv.sp.exception;

import com.cognizant.opserv.sp.exception.InvalidInputParamException.InvalidInputParamExceptionCode;
import com.cognizant.peg.core.exception.BusinessException;
/**
 * ****************************************************************************.
 *
 * @class MetricExecutionException , exception class for MetricExecutionService
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/04/2016
 * ***************************************************************************
 */
public class MetricExecutionException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2576113234549237262L;

	/**
	 * Exception Code
	 * @author 426111
	 *
	 */
	public enum MetricExecutionExceptionCode {
		
		MTREXEC_SER_EX_CD_001("MTREXEC_SER_EX_CD_001"),MTREXEC_SER_EX_CD_002("MTREXEC_SER_EX_CD_002");

		private final String code;
		
		private MetricExecutionExceptionCode(String code) {
			this.code = code;
		}
		public String getCode() {
		    return this.code;
		}		
	}
	
	/**
	 * Instantiates a new metric violation exception.
	 *
	 * @param code the code
	 * @param message the message
	 */
	public MetricExecutionException(String code, String message) {
		super(code, message);
	}

	/**
	 * Instantiates a new metric violation exception.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionContext the exception context
	 * @param args the args
	 * @param cause the cause
	 */
	public MetricExecutionException(MetricExecutionExceptionCode exceptionCode, final String exceptionContext, final Object[] args, final Throwable cause) {
		super (exceptionCode.getCode(), exceptionContext, args, cause);
	}
	
	/**
	 * Instantiates a new metric violation exception.
	 *
	 * @param missingArgs the missing args
	 */
	public MetricExecutionException(final Object[] missingArgs) {
		super (InvalidInputParamExceptionCode.INV_VAD_EX_CD_001.getCode(), "Validation", missingArgs);
	}
}

