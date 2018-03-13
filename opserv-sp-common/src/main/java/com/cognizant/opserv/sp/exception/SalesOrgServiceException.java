package com.cognizant.opserv.sp.exception;

import com.cognizant.opserv.sp.exception.InvalidInputParamException.InvalidInputParamExceptionCode;
import com.cognizant.peg.core.exception.BusinessException;
/**
 * ****************************************************************************.
 *
 * @class SalesOrgServiceException , exception class for SalesOrgService
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 30/04/2016
 * ***************************************************************************
 */
public class SalesOrgServiceException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4196257826772274045L;
	
	/**
	 * Exception Code
	 * @author 426111
	 *
	 */
	public enum SalesOrgServiceExceptionCode {
		
		SALESORG_SER_EX_CD_001("SALESORG_SER_EX_CD_001"), SALESORG_SER_EX_CD_002("SALESORG_SER_EX_CD_002"),
		SALESORG_SER_EX_CD_003("SALESORG_SER_EX_CD_003"), SALESORG_SER_EX_CD_004("SALESORG_SER_EX_CD_004"),
		SALESORG_SER_EX_CD_005("SALESORG_SER_EX_CD_005"), SALESORG_SER_EX_CD_006("SALESORG_SER_EX_CD_006");

		private final String code;
		
		private SalesOrgServiceExceptionCode(String code) {
			this.code = code;
		}
		public String getCode() {
		    return this.code;
		}		
	}		
	
	/**
	 * Instantiates a new sales org service exception.
	 *
	 * @param code the code
	 * @param message the message
	 */
	public SalesOrgServiceException(String code, String message) {
		super(code, message);
	}

	/**
	 * Instantiates a new sales org service exception.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionContext the exception context
	 * @param args the args
	 * @param cause the cause
	 */
	public SalesOrgServiceException(SalesOrgServiceExceptionCode exceptionCode, final String exceptionContext, final Object[] args, final Throwable cause) {
		super (exceptionCode.getCode(), exceptionContext, args, cause);
	}
	
	/**
	 * Instantiates a new sales org service exception.
	 *
	 * @param missingArgs the missing args
	 */
	public SalesOrgServiceException(final Object[] missingArgs) {
		super (InvalidInputParamExceptionCode.INV_VAD_EX_CD_001.getCode(), "Validation", missingArgs);
	}
}

