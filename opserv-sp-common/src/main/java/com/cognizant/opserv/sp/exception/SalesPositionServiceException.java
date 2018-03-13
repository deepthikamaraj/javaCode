package com.cognizant.opserv.sp.exception;

import com.cognizant.opserv.sp.exception.InvalidInputParamException.InvalidInputParamExceptionCode;
import com.cognizant.peg.core.exception.BusinessException;
/**
 * ****************************************************************************.
 *
 * @class SalesPositionServiceException , exception class for SalesPositionService
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 30/04/2016
 * ***************************************************************************
 */
public class SalesPositionServiceException extends BusinessException {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -3773933663297623112L;

	/**
	 * Exception Code
	 * @author 426111
	 *
	 */
	public enum SalesPositionServiceExceptionCode {
		
		SP_SER_EX_CD_001("SP_SER_EX_CD_001"),
		SP_SER_EX_CD_002("SP_SER_EX_CD_002"),
		SP_SER_EX_CD_003("SP_SER_EX_CD_003"),
		SP_SER_EX_CD_004("SP_SER_EX_CD_004"),
		SP_SER_EX_CD_005("SP_SER_EX_CD_005"),
		SP_SER_EX_CD_006("SP_SER_EX_CD_006"),
		SP_SER_EX_CD_007("SP_SER_EX_CD_007");
		
		/** The code. */
		private final String code;
		
		/**
		 * Instantiates a new sales position service exception code.
		 *
		 * @param code the code
		 */
		private SalesPositionServiceExceptionCode(String code) {
			this.code = code;
		}
		
		/**
		 * Gets the code.
		 *
		 * @return the code
		 */
		public String getCode() {
		    return this.code;
		}		
	}	
	
	/**
	 * Instantiates a new sales position service exception.
	 *
	 * @param code the code
	 * @param message the message
	 */
	public SalesPositionServiceException(String code, String message) {
		super(code, message);
	}

	/**
	 * Instantiates a new sales position service exception.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionContext the exception context
	 * @param args the args
	 * @param cause the cause
	 */
	public SalesPositionServiceException(SalesPositionServiceExceptionCode exceptionCode, final String exceptionContext, final Object[] args, final Throwable cause) {
		super (exceptionCode.getCode(), exceptionContext, args, cause);
	}
	
	/**
	 * Instantiates a new sales position service exception.
	 *
	 * @param missingArgs the missing args
	 */
	public SalesPositionServiceException(final Object[] missingArgs) {
		super (InvalidInputParamExceptionCode.INV_VAD_EX_CD_001.getCode(), "Validation", missingArgs);
	}
}

