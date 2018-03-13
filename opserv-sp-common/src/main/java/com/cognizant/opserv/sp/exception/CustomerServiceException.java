package com.cognizant.opserv.sp.exception;

import com.cognizant.opserv.sp.exception.InvalidInputParamException.InvalidInputParamExceptionCode;
import com.cognizant.peg.core.exception.BusinessException;
/**
 * ****************************************************************************.
 *
 * @class CustomerServiceException , exception class for AlignmentService
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 30/04/2016
 * ***************************************************************************
 */
public class CustomerServiceException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5237692652215779605L;

	/**
	 * Exception Code
	 * @author 426111
	 *
	 */
	public enum CustomerServiceExceptionCode {
		
		CUST_SER_EX_CD_001("CUST_SER_EX_CD_001"),
		CUST_SER_EX_CD_002("CUST_SER_EX_CD_002"),
		CUST_SER_EX_CD_003("CUST_SER_EX_CD_003"),
		CUST_SER_EX_CD_004("CUST_SER_EX_CD_004"),
		CUST_SER_EX_CD_005("CUST_SER_EX_CD_005"),
		CUST_SER_EX_CD_006("CUST_SER_EX_CD_006"),
		CUST_SER_EX_CD_007("CUST_SER_EX_CD_007"),
		CUST_SER_EX_CD_008("CUST_SER_EX_CD_008"),
		CUST_SER_EX_CD_010("CUST_SER_EX_CD_010");
		

		private final String code;
		
		private CustomerServiceExceptionCode(String code) {
			this.code = code;
		}
		public String getCode() {
		    return this.code;
		}		
	}
	
	/**
	 * 
	 * @param code
	 * @param message
	 */
	public CustomerServiceException(String code, String message) {
		super(code, message);
	}
	
	/**
	 * Instantiates a new customer service exception.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionContext the exception context
	 * @param args the args
	 * @param cause the cause
	 */
	public CustomerServiceException(CustomerServiceExceptionCode exceptionCode, final String exceptionContext, final Object[] args, final Throwable cause) {
		super (exceptionCode.getCode(), exceptionContext, args, cause);
	}		

	/**
	 * Instantiates a new customer service exception.
	 *
	 * @param missingArgs the missing args
	 */
	public CustomerServiceException(final Object[] missingArgs) {
		super (InvalidInputParamExceptionCode.INV_VAD_EX_CD_001.getCode(), "Validation", missingArgs);
	}
}
