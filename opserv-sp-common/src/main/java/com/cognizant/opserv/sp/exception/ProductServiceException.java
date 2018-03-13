package com.cognizant.opserv.sp.exception;

import com.cognizant.opserv.sp.exception.InvalidInputParamException.InvalidInputParamExceptionCode;
import com.cognizant.peg.core.exception.BusinessException;
/**
 * ****************************************************************************.
 *
 * @class ProductServiceException , exception class for ProductService
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 30/04/2016
 * ***************************************************************************
 */
public class ProductServiceException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7152732613036752019L;

	/**
	 * Exception Code
	 * @author 426111
	 *
	 */
	public enum ProductServiceExceptionExceptionCode {
		
		PROD_SER_EX_CD_001("PROD_SER_EX_CD_001"),
		PROD_SER_EX_CD_002("PROD_SER_EX_CD_002"),
		PROD_SER_EX_CD_003("PROD_SER_EX_CD_003");

		private final String code;
		
		private ProductServiceExceptionExceptionCode(String code) {
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
	public ProductServiceException(String code, String message) {
		super(code, message);
	}

	/**
	 * Instantiates a new product service exception.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionContext the exception context
	 * @param args the args
	 * @param cause the cause
	 */
	public ProductServiceException(ProductServiceExceptionExceptionCode exceptionCode, final String exceptionContext, final Object[] args, final Throwable cause) {
		super (exceptionCode.getCode(), exceptionContext, args, cause);
	}	
	
	/**
	 * Instantiates a new product service exception.
	 *
	 * @param missingArgs the missing args
	 */
	public ProductServiceException(final Object[] missingArgs) {
		super (InvalidInputParamExceptionCode.INV_VAD_EX_CD_001.getCode(), "Validation", missingArgs);
	}
}
