package com.cognizant.opserv.sp.exception;

import com.cognizant.opserv.sp.exception.InvalidInputParamException.InvalidInputParamExceptionCode;
import com.cognizant.peg.core.exception.BusinessException;
/**
 * ****************************************************************************.
 *
 * @class EmployeeServiceException , exception class for EmployeeService
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 30/04/2016
 * ***************************************************************************
 */
public class EmployeeServiceException extends BusinessException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2423903243832719711L;

	/**
	 * Exception Code
	 * @author 426111
	 *
	 */
	public enum EmployeeServiceExceptionCode {
		
		EMP_SER_EX_CD_001("EMP_SER_EX_CD_001"),
		EMP_SER_EX_CD_002("EMP_SER_EX_CD_002"),
		EMP_SER_EX_CD_003("EMP_SER_EX_CD_003"),
		EMP_SER_EX_CD_004("EMP_SER_EX_CD_004"),
		EMP_SER_EX_CD_005("EMP_SER_EX_CD_005");
		private final String code;
		
		private EmployeeServiceExceptionCode(String code) {
			this.code = code;
		}
		public String getCode() {
		    return this.code;
		}		
	}		
	
	/**
	 * Instantiates a new employee service exception.
	 *
	 * @param code the code
	 * @param message the message
	 */
	public EmployeeServiceException(String code, String message) {
		super(code, message);
	}
	
	/**
	 * Instantiates a new employee service exception.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionContext the exception context
	 * @param args the args
	 * @param cause the cause
	 */
	public EmployeeServiceException(EmployeeServiceExceptionCode exceptionCode, final String exceptionContext, final Object[] args, final Throwable cause) {
		super (exceptionCode.getCode(), exceptionContext, args, cause);
	}		
	
	/**
	 * Instantiates a new employee service exception.
	 *
	 * @param missingArgs the missing args
	 */
	public EmployeeServiceException(final Object[] missingArgs) {
		super (InvalidInputParamExceptionCode.INV_VAD_EX_CD_001.getCode(), "Validation", missingArgs);
	}
}
