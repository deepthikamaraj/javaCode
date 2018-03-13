/**
 * 
 */
package com.cognizant.opserv.sp.exception;

import com.cognizant.opserv.sp.exception.InvalidInputParamException.InvalidInputParamExceptionCode;
import com.cognizant.peg.core.exception.BusinessException;

/**
 * ****************************************************************************.
 *
 * @class DashboardServiceException contains all the Dashboard services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 09/06/2016
 * ***************************************************************************
 */
public class DashboardServiceException extends BusinessException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8595217550263375571L;
	
	public enum DashboardServiceExceptionCode{
		
		DSHBD_SER_EX_CD_001("DSHBD_SER_EX_CD_001"),
		DSHBD_SER_EX_CD_002("DSHBD_SER_EX_CD_002"),
		DSHBD_SER_EX_CD_003("DSHBD_SER_EX_CD_003"),
		DSHBD_SER_EX_CD_004("DSHBD_SER_EX_CD_004"),
		DSHBD_SER_EX_CD_005("DSHBD_SER_EX_CD_005"),
		DSHBD_SER_EX_CD_006("DSHBD_SER_EX_CD_006"),
		DSHBD_SER_EX_CD_007("DSHBD_SER_EX_CD_007");
		/**
		 * @param code
		 */
		private DashboardServiceExceptionCode(String code) {
			this.code = code;
		}

		private String code;

		/**
		 * @return the code
		 */
		public String getCode() {
			return this.code;
		}
		
		
		
	}

	/**
	 * 
	 */
	public DashboardServiceException(final Object[] missingArgs) {
		super(InvalidInputParamExceptionCode.INV_VAD_EX_CD_001.getCode(), "Validation", missingArgs);
		
	}

	/**
	 * @param exceptionCode
	 * @param exceptionContext
	 * @param args
	 * @param cause
	 */
	public DashboardServiceException(DashboardServiceExceptionCode exceptionCode, final String exceptionContext, final Object[] args, final Throwable cause) {
		super(exceptionCode.getCode(), exceptionContext, args, cause);
		
	}

	/**
	 * @param exceptionCode
	 * @param exceptionContext
	 */
	public DashboardServiceException(String code, String message) {
		super(code, message);
		
	}


}
