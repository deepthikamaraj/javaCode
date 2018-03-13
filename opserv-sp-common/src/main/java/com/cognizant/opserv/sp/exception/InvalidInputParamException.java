package com.cognizant.opserv.sp.exception;

import com.cognizant.peg.core.exception.BusinessException;
/**
 * ****************************************************************************.
 *
 * @class InvalidInputParamException
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 30/04/2016
 * ***************************************************************************
 */
public class InvalidInputParamException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1772482320034489913L;

	/**
	 * Exception Code
	 * 
	 * @author 472731
	 * 
	 */
	public enum InvalidInputParamExceptionCode {

		INV_VAD_EX_CD_001("INV_VAD_EX_CD_001");

		private final String code;

		private InvalidInputParamExceptionCode(String code) {
			this.code = code;
		}

		public String getCode() {
			return this.code;
		}
	}

	/**
	 * Instantiates a new invalid input param exception.
	 *
	 * @param code the code
	 * @param message the message
	 */
	public InvalidInputParamException(String code, String message) {
		super(code, message);
	}

	/**
	 * Instantiates a new invalid input param exception.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionContext the exception context
	 * @param args the args
	 * @param cause the cause
	 */
	public InvalidInputParamException(InvalidInputParamExceptionCode exceptionCode, final String exceptionContext, final Object[] args, final Throwable cause) {
		super(exceptionCode.getCode(), exceptionContext, args, cause);
	}

}
