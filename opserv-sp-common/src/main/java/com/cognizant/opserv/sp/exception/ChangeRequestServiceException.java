package com.cognizant.opserv.sp.exception;

import com.cognizant.opserv.sp.exception.InvalidInputParamException.InvalidInputParamExceptionCode;
import com.cognizant.peg.core.exception.BusinessException;
/**
 * ****************************************************************************.
 *
 * @class ChangeRequestServiceException , exception class for ChangeRequestService
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 30/04/2016
 * ***************************************************************************
 */
public class ChangeRequestServiceException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4285646157440648866L;

	/**
	 * Exception Code
	 * @author 426111
	 *
	 */
	public enum ChangeRequestServiceExceptionCode {
		
		CR_SER_EX_CD_001("CR_SER_EX_CD_001"),
		CR_SER_EX_CD_002("CR_SER_EX_CD_002"), 
		CR_SER_EX_CD_003("CR_SER_EX_CD_003"), 
		CR_SER_EX_CD_004("CR_SER_EX_CD_004"),
		CR_SER_EX_CD_005("CR_SER_EX_CD_005"),
		CR_SER_EX_CD_006("CR_SER_EX_CD_006"),
		CR_SER_EX_CD_007("CR_SER_EX_CD_007"),
		CR_SER_EX_CD_008("CR_SER_EX_CD_008"),
		CR_SER_EX_CD_009("CR_SER_EX_CD_009"),
		CR_SER_EX_CD_010("CR_SER_EX_CD_010"),
		CR_SER_EX_CD_011("CR_SER_EX_CD_011"),
		CR_SER_EX_CD_012("CR_SER_EX_CD_012"),
		CR_SER_EX_CD_013("CR_SER_EX_CD_013"),
		CR_SER_EX_CD_014("CR_SER_EX_CD_014"),
		CR_SER_EX_CD_015("CR_SER_EX_CD_015"), 
		CR_SER_EX_CD_016("CR_SER_EX_CD_016"),
		CR_SER_EX_CD_017("CR_SER_EX_CD_017"),
		CR_SER_EX_CD_018("CR_SER_EX_CD_018"),
		CR_SER_EX_CD_019("CR_SER_EX_CD_019"),
		CR_SER_EX_CD_020("CR_SER_EX_CD_020"), // Parent CR cannot be null or empty
		CR_SER_EX_CD_021("CR_SER_EX_CD_021"); // ViewServiceException

		/** The code. */
		private final String code;
		
		/**
		 * Instantiates a new change request service exception code.
		 *
		 * @param code the code
		 */
		private ChangeRequestServiceExceptionCode(String code) {
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
	 * 
	 * @param code
	 * @param message
	 */
	public ChangeRequestServiceException(String code, String message) {
		super(code, message);
	}
	
	/**
	 * Instantiates a new change request service exception.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionContext the exception context
	 * @param args the args
	 * @param cause the cause
	 */
	public ChangeRequestServiceException(ChangeRequestServiceExceptionCode exceptionCode, final String exceptionContext, final Object[] args, final Throwable cause) {
		super (exceptionCode.getCode(), exceptionContext, args, cause);
	}	
	
	/**
	 * Instantiates a new change request service exception.
	 *
	 * @param missingArgs the missing args
	 */
	public ChangeRequestServiceException(final Object[] missingArgs) {
		super (InvalidInputParamExceptionCode.INV_VAD_EX_CD_001.getCode(), "Validation", missingArgs);
	}
}
