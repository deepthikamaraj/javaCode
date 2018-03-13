package com.cognizant.opserv.sp.exception;

import com.cognizant.opserv.sp.exception.InvalidInputParamException.InvalidInputParamExceptionCode;
import com.cognizant.peg.core.exception.BusinessException;
/**
 * ****************************************************************************.
 *
 * @class MetricServiceException , exception class for MetricService
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 30/04/2016
 * ***************************************************************************
 */
public class MetricServiceException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2551152185103473557L;
	
	/**
	 * Exception Code
	 * @author 426111
	 *
	 */
	public enum MetricServiceExceptionCode {
		
		MTR_SER_EX_CD_001("MTR_SER_EX_CD_001"),	MTR_SER_EX_CD_002("MTR_SER_EX_CD_002"),	MTR_SER_EX_CD_003("MTR_SER_EX_CD_003"),
		MTR_SER_EX_CD_004("MTR_SER_EX_CD_004"), MTR_SER_EX_CD_005("MTR_SER_EX_CD_005"),	MTR_SER_EX_CD_006("MTR_SER_EX_CD_006"),
		MTR_SER_EX_CD_007("MTR_SER_EX_CD_007"), MTR_SER_EX_CD_008("MTR_SER_EX_CD_008"),	MTR_SER_EX_CD_009("MTR_SER_EX_CD_009"),
		MTR_SER_EX_CD_0010("MTR_SER_EX_CD_0010"), MTR_SER_EX_CD_0011("MTR_SER_EX_CD_0011"),	MTR_SER_EX_CD_0012("MTR_SER_EX_CD_0012"),
		MTR_SER_EX_CD_0013("MTR_SER_EX_CD_0013"),MTR_SER_EX_CD_0014("MTR_SER_EX_CD_0014"),MTR_SER_EX_CD_0015("MTR_SER_EX_CD_0015"),
		MTR_SER_EX_CD_0016("MTR_SER_EX_CD_0016"),MTR_SER_EX_CD_0017("MTR_SER_EX_CD_0017"),MTR_SER_EX_CD_0018("MTR_SER_EX_CD_0018"),
		MTR_SER_EX_CD_0019("MTR_SER_EX_CD_0019");
		
		private final String code;
		
		private MetricServiceExceptionCode(String code) {
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
	public MetricServiceException(String code, String message) {
		super(code, message);
	}
	
	/**
	 * Instantiates a new metric service exception.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionContext the exception context
	 * @param args the args
	 * @param cause the cause
	 */
	public MetricServiceException(MetricServiceExceptionCode exceptionCode, final String exceptionContext, final Object[] args, final Throwable cause) {
		super (exceptionCode.getCode(), exceptionContext, args, cause);
	}		
		
	/**
	 * Instantiates a new metric service exception.
	 *
	 * @param missingArgs the missing args
	 */
	public MetricServiceException(final Object[] missingArgs) {
		super (InvalidInputParamExceptionCode.INV_VAD_EX_CD_001.getCode(), "Validation", missingArgs);
	}
}
