package com.cognizant.opserv.sp.exception;

import com.cognizant.opserv.sp.exception.InvalidInputParamException.InvalidInputParamExceptionCode;
import com.cognizant.peg.core.exception.BusinessException;

public class AttributeServiceException extends BusinessException {

	private static final long serialVersionUID = 1300259041948903294L;

	public enum AttributeServiceExceptionCode {

		ATTR_SER_EX_CD_001("ATTR_SER_EX_CD_001"), ATTR_SER_EX_CD_002(
				"ATTR_SER_EX_CD_002"), ATTR_SER_EX_CD_003("ATTR_SER_EX_CD_003"),ATTR_SER_EX_CD_004("ATTR_SER_EX_CD_004"),ATTR_SER_EX_CD_005("ATTR_SER_EX_CD_005"),
				ATTR_SER_EX_CD_006("ATTR_SER_EX_CD_006"),ATTR_SER_EX_CD_007("ATTR_SER_EX_CD_007"),ATTR_SER_EX_CD_008("ATTR_SER_EX_CD_008"),
				ATTR_SER_EX_CD_009("ATTR_SER_EX_CD_009"),ATTR_SER_EX_CD_0010("ATTR_SER_EX_CD_0010"),ATTR_SER_EX_CD_0011("ATTR_SER_EX_CD_0011"),
				ATTR_SER_EX_CD_0012("ATTR_SER_EX_CD_0012");

		private final String code;

		private AttributeServiceExceptionCode(String code) {
			this.code = code;
		}

		public String getCode() {
			return this.code;
		}
	}

	/**
	 * Instantiates a new metric violation exception.
	 * 
	 * @param code
	 *            the code
	 * @param message
	 *            the message
	 */
	public AttributeServiceException(String code, String message) {
		super(code, message);
	}

	/**
	 * Instantiates a new metric violation exception.
	 * 
	 * @param exceptionCode
	 *            the exception code
	 * @param exceptionContext
	 *            the exception context
	 * @param args
	 *            the args
	 * @param cause
	 *            the cause
	 */
	public AttributeServiceException(
			AttributeServiceExceptionCode exceptionCode,
			final String exceptionContext, final Object[] args,
			final Throwable cause) {
		super(exceptionCode.getCode(), exceptionContext, args, cause);
	}

	/**
	 * Instantiates a new metric violation exception.
	 * 
	 * @param missingArgs
	 *            the missing args
	 */
	public AttributeServiceException(final Object[] missingArgs) {
		super(InvalidInputParamExceptionCode.INV_VAD_EX_CD_001.getCode(),
				"Validation", missingArgs);
	}

}
