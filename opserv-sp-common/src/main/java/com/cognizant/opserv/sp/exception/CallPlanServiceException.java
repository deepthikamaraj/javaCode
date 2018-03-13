package com.cognizant.opserv.sp.exception;

import com.cognizant.opserv.sp.exception.InvalidInputParamException.InvalidInputParamExceptionCode;
import com.cognizant.peg.core.exception.BusinessException;
/**
 * ****************************************************************************.
 *
 * @class CallPlanServiceException , exception class for CallPlanService
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 30/04/2016
 * ***************************************************************************
 */
public class CallPlanServiceException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5692784860869271198L;
	
	
	/**
	 * Exception Code
	 * @author 426111
	 *
	 */
	public enum CallPlanServiceExceptionCode {
		
		CALLPLAN_SER_EX_CD_001("CALLPLAN_SER_EX_CD_001"),
		CALLPLAN_SER_EX_CD_002("CALLPLAN_SER_EX_CD_002"),
		CALLPLAN_SER_EX_CD_003("CALLPLAN_SER_EX_CD_003"),
		CALLPLAN_SER_EX_CD_004("CALLPLAN_SER_EX_CD_004"),
		CALLPLAN_SER_EX_CD_005("CALLPLAN_SER_EX_CD_005"), 
		CALLPLAN_SER_EX_CD_006("CALLPLAN_SER_EX_CD_006"),
		CALLPLAN_SER_EX_CD_007("CALLPLAN_SER_EX_CD_007"),
		CALLPLAN_SER_EX_CD_008("CALLPLAN_SER_EX_CD_008"),
		CALLPLAN_SER_EX_CD_009("CALLPLAN_SER_EX_CD_009"),
		CALLPLAN_SER_EX_CD_010("CALLPLAN_SER_EX_CD_010");
		
		


		private final String code;
		
		private CallPlanServiceExceptionCode(String code) {
			this.code = code;
		}
		public String getCode() {
		    return this.code;
		}		
	}		
	/**
	 * 
	 * 
	 * @param code
	 * @param message
	 */
	public CallPlanServiceException(String code,String message) {
		super(code,message);
	}

	/**
	 * Instantiates a new call plan service exception.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionContext the exception context
	 * @param args the args
	 * @param cause the cause
	 */
	public CallPlanServiceException(CallPlanServiceExceptionCode exceptionCode, final String exceptionContext, final Object[] args, final Throwable cause) {
		super (exceptionCode.getCode(), exceptionContext, args, cause);
	}		
	
	/**
	 * Instantiates a new call plan service exception.
	 *
	 * @param missingArgs the missing args
	 */
	public CallPlanServiceException(final Object[] missingArgs) {
		super (InvalidInputParamExceptionCode.INV_VAD_EX_CD_001.getCode(), "Validation", missingArgs);
	}
}
