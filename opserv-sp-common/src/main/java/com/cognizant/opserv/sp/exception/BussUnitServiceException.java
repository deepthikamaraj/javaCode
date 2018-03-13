package com.cognizant.opserv.sp.exception;

import com.cognizant.opserv.sp.exception.InvalidInputParamException.InvalidInputParamExceptionCode;
import com.cognizant.peg.core.exception.BusinessException;

/**
 * ****************************************************************************.
 *
 * @class BussUnitServiceException , exception class for BussunitService
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 24/05/2016
 * ***************************************************************************
 */
public class BussUnitServiceException extends BusinessException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4334304940138266695L;

	/**
	 * Exception Code.
	 *
	 * @author 397948
	 */
	public enum BussUnitServiceExceptionCode {
		
		/** The BUSSUNI t_ se r_ e x_ c d_001. */
		BUSSUNIT_SER_EX_CD_001("BUSSUNIT_SER_EX_CD_001"),
		BUSSUNIT_SER_EX_CD_002("BUSSUNIT_SER_EX_CD_002"),
		BUSSUNIT_SER_EX_CD_003("BUSSUNIT_SER_EX_CD_003"),
		BUSSUNIT_SER_EX_CD_004("BUSSUNIT_SER_EX_CD_004"),
		BUSSUNIT_SER_EX_CD_005("BUSSUNIT_SER_EX_CD_005");

		/** The code. */
		private final String code;
		
		/**
		 * Instantiates a new buss unit service exception code.
		 *
		 * @param code the code
		 */
		private BussUnitServiceExceptionCode(String code) {
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
	 * Instantiates a new buss unit service exception.
	 *
	 * @param code the code
	 * @param message the message
	 */
	public BussUnitServiceException(String code, String message) {
		super(code, message);
	}

	/**
	 * Instantiates a new buss unit service exception.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionContext the exception context
	 * @param args the args
	 * @param cause the cause
	 */
	public BussUnitServiceException(BussUnitServiceExceptionCode exceptionCode, final String exceptionContext, final Object[] args, final Throwable cause) {
		super (exceptionCode.getCode(), exceptionContext, args, cause);
	}
	
	/**
	 * Instantiates a new buss unit service exception.
	 *
	 * @param missingArgs the missing args
	 */
	public BussUnitServiceException(final Object[] missingArgs) {
		super (InvalidInputParamExceptionCode.INV_VAD_EX_CD_001.getCode(), "Validation", missingArgs);
	}
}

