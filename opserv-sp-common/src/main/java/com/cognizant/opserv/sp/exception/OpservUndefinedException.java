package com.cognizant.opserv.sp.exception;

import com.cognizant.opserv.sp.exception.InvalidInputParamException.InvalidInputParamExceptionCode;
import com.cognizant.peg.core.exception.BusinessException;
/**
 * ****************************************************************************.
 *
 * @class OpservUndefinedException , exception class for BusinessReasonService
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 10/06/2016
 * ***************************************************************************
 */
public class OpservUndefinedException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7496452397541401108L;
	
	public enum OpservUndefinedExceptionCode {
		
		OPSERV_UNDFND_EX_CD_001("OPSERV_UNDFND_EX_CD_001"),
		OPSERV_UNDFND_EX_CD_002("OPSERV_UNDFND_EX_CD_002"),
		OPSERV_UNDFND_EX_CD_003("OPSERV_UNDFND_EX_CD_003"),
		OPSERV_UNDFND_EX_CD_004("OPSERV_UNDFND_EX_CD_004"),
		OPSERV_UNDFND_EX_CD_005("OPSERV_UNDFND_EX_CD_005");

		private final String code;
		
		private OpservUndefinedExceptionCode(String code) {
			this.code = code;
		}
		public String getCode() {
		    return this.code;
		}		
	}		
	
	/**
	 * Instantiates a new Opserv Undefined Exception.
	 *
	 * @param code the code
	 * @param message the message
	 */
	public OpservUndefinedException(String code, String message) {
		super(code, message);
	}

	/**
	 * Instantiates a new Opserv Undefined Exception.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionContext the exception context
	 * @param args the args
	 * @param cause the cause
	 */
	public OpservUndefinedException(OpservUndefinedExceptionCode exceptionCode, final String exceptionContext, final Object[] args, final Throwable cause) {
		super (exceptionCode.getCode(), exceptionContext, args, cause);
	}
	
	/**
	 * Instantiates a new Opserv Undefined Exception.
	 *
	 * @param missingArgs the missing args
	 */
	public OpservUndefinedException(final Object[] missingArgs) {
		super (InvalidInputParamExceptionCode.INV_VAD_EX_CD_001.getCode(), "Validation", missingArgs);
	}

}