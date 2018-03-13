package com.cognizant.opserv.sp.exception;

import com.cognizant.opserv.sp.exception.InvalidInputParamException.InvalidInputParamExceptionCode;
import com.cognizant.peg.core.exception.BusinessException;

/**
 * ****************************************************************************.
 *
 * @class AlignmentServiceException , exception class for AlignmentService
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 30/04/2016
 * ***************************************************************************
 */
public class CommunicationServiceException extends BusinessException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5288433171565096594L;

	
	/**
	 * The Enum CommunicationServiceExceptionCode.
	 */
	public enum CommunicationServiceExceptionCode {
		
		COMM_SER_EX_CD_001("COMM_SER_EX_CD_001"),
		COMM_SER_EX_CD_002("COMM_SER_EX_CD_002"),
		COMM_SER_EX_CD_003("COMM_SER_EX_CD_003"),
		COMM_SER_EX_CD_004("COMM_SER_EX_CD_004"),
		COMM_SER_EX_CD_005("COMM_SER_EX_CD_005");
		
		private final String code;
		
		private CommunicationServiceExceptionCode(String code) {
			this.code = code;
		}
		public String getCode() {
		    return this.code;
		}		
	}	
	
	/**
	 * Instantiates a new alignment service exception.
	 *
	 * @param code the code
	 * @param message the message
	 */
	public CommunicationServiceException(String code, String message) {
		super(code, message);
	}
	
	/**
	 * Instantiates a new alignment service exception.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionContext the exception context
	 * @param args the args
	 * @param cause the cause
	 */
	public CommunicationServiceException(CommunicationServiceExceptionCode exceptionCode, final String exceptionContext, final Object[] args, final Throwable cause) {
		super (exceptionCode.getCode(), exceptionContext, args, cause);
	}
	
	/**
	 * Instantiates a new alignment service exception.
	 *
	 * @param missingArgs the missing args
	 */
	public CommunicationServiceException(final Object[] missingArgs) {
		super (InvalidInputParamExceptionCode.INV_VAD_EX_CD_001.getCode(), "Validation", missingArgs);
	}	
 

}
