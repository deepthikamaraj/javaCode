package com.cognizant.opserv.sp.exception;

import com.cognizant.opserv.sp.exception.InvalidInputParamException.InvalidInputParamExceptionCode;
import com.cognizant.peg.core.exception.BusinessException;

/**
 * ****************************************************************************.
 *
 * @class AdvanceSearchServiceException , exception class for AdvanceSearchServiceException
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class AdvanceSearchServiceException extends BusinessException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3507780829570061677L;

	/**
	 * Exception Code
	 * @author 397318
	 *
	 */
	public enum AdvanceSearchServiceExceptionCode {
		
		ADV_SRCH_SER_EX_CD_001("ADV_SRCH_SER_EX_CD_001");
		
		private final String code;
		
		private AdvanceSearchServiceExceptionCode(String code) {
			this.code = code;
		}
		public String getCode() {
		    return this.code;
		}		
	}
	
	/**
	 * Instantiates a new View Service Exception.
	 *
	 * @param code the code
	 * @param message the message
	 */
	public AdvanceSearchServiceException(String code, String message) {
		super(code, message);
	}
	
	/**
	 * Instantiates a new sales position service exception.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionContext the exception context
	 * @param args the arguments
	 * @param cause the cause
	 */
	public AdvanceSearchServiceException(AdvanceSearchServiceExceptionCode exceptionCode, final String exceptionContext, final Object[] args, final Throwable cause) {
		super (exceptionCode.getCode(), exceptionContext, args, cause);
	}
	
	/**
	 * Instantiates a new sales position service exception.
	 * 
	 * @param missingArgs the missing arguments
	 */
	public AdvanceSearchServiceException(final Object[] missingArgs) {
		super (InvalidInputParamExceptionCode.INV_VAD_EX_CD_001.getCode(), "Validation", missingArgs);
	}

}
