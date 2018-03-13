package com.cognizant.opserv.sp.exception;

import com.cognizant.opserv.sp.exception.InvalidInputParamException.InvalidInputParamExceptionCode;
import com.cognizant.peg.core.exception.BusinessException;
/**
 * ****************************************************************************.
 *
 * @class ReportServiceException , exception class for ReportService
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 30/04/2016
 * ***************************************************************************
 */
public class ReportServiceException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5996591529605100742L;
	
	/**
	 * Exception Code
	 * @author 426111
	 *
	 */
	public enum ReportServiceExceptionCode {
		
		RPT_SER_EX_CD_001("RPT_SER_EX_CD_001");

		private final String code;
		
		private ReportServiceExceptionCode(String code) {
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
	public ReportServiceException(String code, String message) {
		super(code, message);
	}

	/**
	 * Instantiates a new report service exception.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionContext the exception context
	 * @param args the args
	 * @param cause the cause
	 */
	public ReportServiceException(ReportServiceExceptionCode exceptionCode, final String exceptionContext, final Object[] args, final Throwable cause) {
		super (exceptionCode.getCode(), exceptionContext, args, cause);
	}
	
	/**
	 * Instantiates a new report service exception.
	 *
	 * @param missingArgs the missing args
	 */
	public ReportServiceException(final Object[] missingArgs) {
		super (InvalidInputParamExceptionCode.INV_VAD_EX_CD_001.getCode(), "Validation", missingArgs);
	}
}

