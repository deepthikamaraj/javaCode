/**
 * 
 */
package com.cognizant.opserv.sp.exception;

import com.cognizant.opserv.sp.exception.InvalidInputParamException.InvalidInputParamExceptionCode;
import com.cognizant.peg.core.exception.BusinessException;

/**
 * ****************************************************************************.
 *
 * @class ViewServiceException , exception class for ViewServiceException
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class ViewServiceException extends BusinessException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3507780829570061677L;

	/**
	 * Exception Code
	 * @author 397318
	 *
	 */
	public enum ViewServiceExceptionCode {
		
		VIEW_SER_EX_CD_001("VIEW_SER_EX_CD_001"),

		VIEW_SER_EX_CD_002("VIEW_SER_EX_CD_002");
		private final String code;
		
		private ViewServiceExceptionCode(String code) {
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
	public ViewServiceException(String code, String message) {
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
	public ViewServiceException(ViewServiceExceptionCode exceptionCode, final String exceptionContext, final Object[] args, final Throwable cause) {
		super (exceptionCode.getCode(), exceptionContext, args, cause);
	}
	
	/**
	 * Instantiates a new sales position service exception.
	 * 
	 * @param missingArgs the missing arguments
	 */
	public ViewServiceException(final Object[] missingArgs) {
		super (InvalidInputParamExceptionCode.INV_VAD_EX_CD_001.getCode(), "Validation", missingArgs);
	}

}
