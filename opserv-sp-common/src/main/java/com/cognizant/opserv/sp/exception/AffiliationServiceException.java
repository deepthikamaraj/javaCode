package com.cognizant.opserv.sp.exception;

import com.cognizant.opserv.sp.exception.InvalidInputParamException.InvalidInputParamExceptionCode;
import com.cognizant.peg.core.exception.BusinessException;
/**
 * ****************************************************************************.
 *
 * @class AffiliationServiceException , exception class for AffiliationService
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 30/04/2016
 * ***************************************************************************
 */
public class AffiliationServiceException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7417929491267073739L;
	
	/**
	 * Exception Code
	 * @author 426111
	 *
	 */
	public enum AffiliationServiceExceptionCode {
		
		AFF_SER_EX_CD_001("AFF_SER_EX_CD_001"),
		AFF_SER_EX_CD_002("AFF_SER_EX_CD_002"),
		AFF_SER_EX_CD_003("AFF_SER_EX_CD_003"),
		AFF_SER_EX_CD_004("AFF_SER_EX_CD_004");

		private final String code;
		
		private AffiliationServiceExceptionCode(String code) {
			this.code = code;
		}
		public String getCode() {
		    return this.code;
		}		
	}

	/**
	 * Instantiates a new affiliation service exception.
	 *
	 * @param code the code
	 * @param message the message
	 */
	public AffiliationServiceException(String code, String message) {
		super(code, message);
	}
	
	/**
	 * Instantiates a new affiliation service exception.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionContext the exception context
	 * @param args the args
	 * @param cause the cause
	 */
	public AffiliationServiceException(AffiliationServiceExceptionCode exceptionCode, final String exceptionContext, final Object[] args, final Throwable cause) {
		super (exceptionCode.getCode(), exceptionContext, args, cause);
	}	
	
	/**
	 * Instantiates a new affiliation service exception.
	 *
	 * @param missingArgs the missing args
	 */
	public AffiliationServiceException(final Object[] missingArgs) {
		super (InvalidInputParamExceptionCode.INV_VAD_EX_CD_001.getCode(), "Validation", missingArgs);
	}

}
