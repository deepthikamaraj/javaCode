package com.cognizant.opserv.sp.exception;

import com.cognizant.opserv.sp.exception.InvalidInputParamException.InvalidInputParamExceptionCode;
import com.cognizant.peg.core.exception.BusinessException;

/**
 * ****************************************************************************.
 *
 * @class AlignmentServiceException , exception class for AlignmentService
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 30/04/2016
 * ***************************************************************************
 */
public class AlignmentServiceException extends BusinessException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3396082105363841801L;
	
	/**
	 * Exception Code
	 * @author 426111
	 *
	 */
	public enum AlignmentServiceExceptionCode {
		
		ALGN_SER_EX_CD_001("ALGN_SER_EX_CD_001"),
		ALGN_SER_EX_CD_002("ALGN_SER_EX_CD_002"),
		ALGN_SER_EX_CD_003("ALGN_SER_EX_CD_003"),
		ALGN_SER_EX_CD_004("ALGN_SER_EX_CD_004"),
		ALGN_SER_EX_CD_005("ALGN_SER_EX_CD_005"),
		ALGN_SER_EX_CD_006("ALGN_SER_EX_CD_006"),
		ALGN_SER_EX_CD_007("ALGN_SER_EX_CD_007"),
		ALGN_SER_EX_CD_008("ALGN_SER_EX_CD_008"),
		ALGN_SER_EX_CD_009("ALGN_SER_EX_CD_009"),
		ALGN_SER_EX_CD_010("ALGN_SER_EX_CD_010"),
		ALGN_SER_EX_CD_011("ALGN_SER_EX_CD_011"),
		ALGN_SER_EX_CD_012("ALGN_SER_EX_CD_012"),
		ALGN_SER_EX_CD_100("ALGN_SER_EX_CD_100"),
		ALGN_SER_EX_CD_101("ALGN_SER_EX_CD_101"),
		ALGN_SER_EX_CD_102("ALGN_SER_EX_CD_102"),
		ALGN_SER_EX_CD_103("ALGN_SER_EX_CD_103"),
		ALGN_SER_EX_CD_200("ALGN_SER_EX_CD_200"),
		ALGN_SER_EX_CD_201("ALGN_SER_EX_CD_200"),
		ALGN_SER_EX_CD_202("ALGN_SER_EX_CD_202"),
		ALGN_SER_EX_CD_203("ALGN_SER_EX_CD_203"),
		ALGN_SER_EX_CD_204("ALGN_SER_EX_CD_204"),
			
		/**
		 *  Start of SP Store Exception Code
		 */
		
		ALGN_SER_EX_CD_205("ALGN_SER_EX_CD_205"),
		ALGN_SER_EX_CD_206("ALGN_SER_EX_CD_206"),
		ALGN_SER_EX_CD_207("ALGN_SER_EX_CD_207"),
		ALGN_SER_EX_CD_208("ALGN_SER_EX_CD_208"),
		ALGN_SER_EX_CD_209("ALGN_SER_EX_CD_209"),
		ALGN_SER_EX_CD_210("ALGN_SER_EX_CD_210"),
		ALGN_SER_EX_CD_211("ALGN_SER_EX_CD_211"),
		
		/**
		 *  End of SP Store Exception Code
		 */
		
		ALGN_SER_EX_CD_212("ALGN_SER_EX_CD_212"),
		ALGN_SER_EX_CD_213("ALGN_SER_EX_CD_213"),
		ALGN_SER_EX_CD_214("ALGN_SER_EX_CD_214"),
		ALGN_SER_EX_CD_215("ALGN_SER_EX_CD_215"),
		ALGN_SER_EX_CD_216("ALGN_SER_EX_CD_216"),
		
		ALGN_SER_EX_CD_217("ALGN_SER_EX_CD_217"),
		ALGN_SER_EX_CD_218("ALGN_SER_EX_CD_218"),
		ALGN_SER_EX_CD_219("ALGN_SER_EX_CD_219"),
		ALGN_SER_EX_CD_220("ALGN_SER_EX_CD_220"),
		ALGN_SER_EX_CD_221("ALGN_SER_EX_CD_221"),
		ALGN_SER_EX_CD_222("ALGN_SER_EX_CD_222"),
		ALGN_SER_EX_CD_223("ALGN_SER_EX_CD_223"),
		ALGN_SER_EX_CD_224("ALGN_SER_EX_CD_224"),
		ALGN_SER_EX_CD_225("ALGN_SER_EX_CD_225"),
		
		ALGN_SER_EX_CD_226("ALGN_SER_EX_CD_226"),
		ALGN_SER_EX_CD_227("ALGN_SER_EX_CD_227"),
		ALGN_SER_EX_CD_228("ALGN_SER_EX_CD_228"),
		ALGN_SER_EX_CD_229("ALGN_SER_EX_CD_229"),
		ALGN_SER_EX_CD_230("ALGN_SER_EX_CD_230"),
		ALGN_SER_EX_CD_231("ALGN_SER_EX_CD_231"),
		ALGN_SER_EX_CD_232("ALGN_SER_EX_CD_232"),
		ALGN_SER_EX_CD_233("ALGN_SER_EX_CD_233"),
		ALGN_SER_EX_CD_234("ALGN_SER_EX_CD_234"),
		ALGN_SER_EX_CD_235("ALGN_SER_EX_CD_235"),
		ALGN_SER_EX_CD_236("ALGN_SER_EX_CD_236"),
		ALGN_SER_EX_CD_237("ALGN_SER_EX_CD_237"),
		ALGN_SER_EX_CD_238("ALGN_SER_EX_CD_238"),
		ALGN_SER_EX_CD_239("ALGN_SER_EX_CD_239"),
		ALGN_SER_EX_CD_240("ALGN_SER_EX_CD_240"),
		ALGN_SER_EX_CD_241("ALGN_SER_EX_CD_241"),
		ALGN_SER_EX_CD_242("ALGN_SER_EX_CD_242"),
		ALGN_SER_EX_CD_243("ALGN_SER_EX_CD_243"),
		ALGN_SER_EX_CD_244("ALGN_SER_EX_CD_244"),
		ALGN_SER_EX_CD_245("ALGN_SER_EX_CD_245"),
        ALGN_SER_EX_CD_246("ALGN_SER_EX_CD_246"),
		ALGN_SER_EX_CD_247("ALGN_SER_EX_CD_247"),
		ALGN_SER_EX_CD_248("ALGN_SER_EX_CD_248"),
		
		ALGN_SER_EX_CD_249("ALGN_SER_EX_CD_249"),
		ALGN_SER_EX_CD_250("ALGN_SER_EX_CD_250"),
		ALGN_SER_EX_CD_251("ALGN_SER_EX_CD_251"),
		ALGN_SER_EX_CD_252("ALGN_SER_EX_CD_252"),
		ALGN_SER_EX_CD_253("ALGN_SER_EX_CD_253"),
		ALGN_SER_EX_CD_254("ALGN_SER_EX_CD_254"),
		ALGN_SER_EX_CD_255("ALGN_SER_EX_CD_255"),
		ALGN_SER_EX_CD_256("ALGN_SER_EX_CD_256"),
		ALGN_SER_EX_CD_257("ALGN_SER_EX_CD_257"),
		ALGN_SER_EX_CD_258("ALGN_SER_EX_CD_258"),
		ALGN_SER_EX_CD_259("ALGN_SER_EX_CD_259"),

		/**
		 *  Start of Alignment Setup Exception Code
		 */
		
		ALGN_SER_EX_CD_300("ALGN_SER_EX_CD_300"),
		ALGN_SER_EX_CD_301("ALGN_SER_EX_CD_301"),
		ALGN_SER_EX_CD_302("ALGN_SER_EX_CD_302"),
		ALGN_SER_EX_CD_303("ALGN_SER_EX_CD_303"),
		ALGN_SER_EX_CD_304("ALGN_SER_EX_CD_304"),
		
		/**
		 *  Start of CustAlignment Ext Attribute Exception Code
		 */
		
		ALGN_SER_EX_CD_306("ALGN_SER_EX_CD_306"),
		ALGN_SER_EX_CD_307("ALGN_SER_EX_CD_307"),
		ALGN_SER_EX_CD_308("ALGN_SER_EX_CD_308"),
		ALGN_SER_EX_CD_309("ALGN_SER_EX_CD_309"),
		ALGN_SER_EX_CD_310("ALGN_SER_EX_CD_310"),
		ALGN_SER_EX_CD_311("ALGN_SER_EX_CD_311"),
		ALGN_SER_EX_CD_312("ALGN_SER_EX_CD_312"),
		ALGN_SER_EX_CD_313("ALGN_SER_EX_CD_313"),
		ALGN_SER_EX_CD_314("ALGN_SER_EX_CD_314"),
		ALGN_SER_EX_CD_315("ALGN_SER_EX_CD_315"),
		ALGN_SER_EX_CD_316("ALGN_SER_EX_CD_316"),
		ALGN_SER_EX_CD_317("ALGN_SER_EX_CD_317"),
		ALGN_SER_EX_CD_318("ALGN_SER_EX_CD_318"),
		ALGN_SER_EX_CD_319("ALGN_SER_EX_CD_319"),
		ALGN_SER_EX_CD_320("ALGN_SER_EX_CD_320"),
		ALGN_SER_EX_CD_321("ALGN_SER_EX_CD_321"),
		ALGN_SER_EX_CD_322("ALGN_SER_EX_CD_322"),
		ALGN_SER_EX_CD_323("ALGN_SER_EX_CD_323"),
		ALGN_SER_EX_CD_324("ALGN_SER_EX_CD_324"),
		ALGN_SER_EX_CD_325("ALGN_SER_EX_CD_325"),
		ALGN_SER_EX_CD_326("ALGN_SER_EX_CD_326"),
		ALGN_SER_EX_CD_327("ALGN_SER_EX_CD_327"),
		ALGN_SER_EX_CD_328("ALGN_SER_EX_CD_328"),
		ALGN_SER_EX_CD_329("ALGN_SER_EX_CD_329"),
		ALGN_SER_EX_CD_330("ALGN_SER_EX_CD_330"),
		ALGN_SER_EX_CD_331("ALGN_SER_EX_CD_331"),
		ALGN_SER_EX_CD_332("ALGN_SER_EX_CD_332"),
		ALGN_SER_EX_CD_333("ALGN_SER_EX_CD_333"),
		/** Zip movement exception code**/
		ALGN_SER_EX_CD_401("ALGN_SER_EX_CD_401"),
		ALGN_SER_EX_CD_402("ALGN_SER_EX_CD_402"),
		ALGN_SER_EX_CD_403("ALGN_SER_EX_CD_403"),
		ALGN_SER_EX_CD_404("ALGN_SER_EX_CD_404"),
		ALGN_SER_EX_CD_405("ALGN_SER_EX_CD_405"),
		ALGN_SER_EX_CD_406("ALGN_SER_EX_CD_406"),
		ALGN_SER_EX_CD_407("ALGN_SER_EX_CD_407");
		
		
		/**
		 * The code
		 */
		private final String code;
		
		/**
		 * @param code
		 */
		private AlignmentServiceExceptionCode(String code) {
			this.code = code;
		}
		
		/**
		 * @return 
		 */
		public String getCode() {
		    return this.code;
		}		
	}	
	
	/**
	 * Instantiates a new alignment service exception.
	 *
	 * @param code the code
	 * @param message the message
	 */
	public AlignmentServiceException(String code, String message) {
		super(code, message);
	}
	
	/**
	 * Instantiates a new alignment service exception.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionContext the exception context
	 * @param args the args
	 * @param cause the cause
	 */
	public AlignmentServiceException(AlignmentServiceExceptionCode exceptionCode, final String exceptionContext, final Object[] args, final Throwable cause) {
		super (exceptionCode.getCode(), exceptionContext, args, cause);
	}
	
	/**
	 * Instantiates a new alignment service exception.
	 *
	 * @param missingArgs the missing args
	 */
	public AlignmentServiceException(final Object[] missingArgs) {
		super (InvalidInputParamExceptionCode.INV_VAD_EX_CD_001.getCode(), "Validation", missingArgs);
	}	
 

}
