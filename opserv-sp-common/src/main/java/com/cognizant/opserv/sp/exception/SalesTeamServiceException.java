package com.cognizant.opserv.sp.exception;

import com.cognizant.opserv.sp.exception.InvalidInputParamException.InvalidInputParamExceptionCode;
import com.cognizant.peg.core.exception.BusinessException;
/**
 * ****************************************************************************.
 *
 * @class SalesTeamServiceException , exception class for SalesTeamService
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 24/05/2016
 * ***************************************************************************
 */
public class SalesTeamServiceException extends BusinessException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2358660708148346955L;

	/**
	 * Exception Code.
	 *
	 * @author 397948
	 */
	public enum SalesTeamServiceExceptionCode {
		
		/** The SALESTEA m_ se r_ e x_ c d_001. */
		SALESTEAM_SER_EX_CD_001("SALESTEAM_SER_EX_CD_001"),
		SALESTEAM_SER_EX_CD_002("SALESTEAM_SER_EX_CD_002"),
		SALESTEAM_SER_EX_CD_003("SALESTEAM_SER_EX_CD_003"),
		SALESTEAM_SER_EX_CD_004("SALESTEAM_SER_EX_CD_004"),
		SALESTEAM_SER_EX_CD_005("SALESTEAM_SER_EX_CD_005");
		/** The code. */
		private final String code;
		
		/**
		 * Instantiates a new sales team service exception code.
		 *
		 * @param code the code
		 */
		private SalesTeamServiceExceptionCode(String code) {
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
	 * Instantiates a new sales team service exception.
	 *
	 * @param code the code
	 * @param message the message
	 */
	public SalesTeamServiceException(String code, String message) {
		super(code, message);
	}


	/**
	 * Instantiates a new sales team service exception.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionContext the exception context
	 * @param args the args
	 * @param cause the cause
	 */
	public SalesTeamServiceException(SalesTeamServiceExceptionCode exceptionCode, final String exceptionContext, final Object[] args, final Throwable cause) {
		super (exceptionCode.getCode(), exceptionContext, args, cause);
	}
	

	/**
	 * Instantiates a new sales team service exception.
	 *
	 * @param missingArgs the missing args
	 */
	public SalesTeamServiceException(final Object[] missingArgs) {
		super (InvalidInputParamExceptionCode.INV_VAD_EX_CD_001.getCode(), "Validation", missingArgs);
	}
}

