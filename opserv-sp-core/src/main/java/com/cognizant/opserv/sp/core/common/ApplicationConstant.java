/* COPYRIGHT (C) 2014 Cognizant, all rights reserved.                        */

package com.cognizant.opserv.sp.core.common;



// TODO: Auto-generated Javadoc

/******************************************************************************
 * 
 * @class <h1>Class Name: ApplicationConstant</h1>
 *        <p>
 *        This Class gives the ApplicationConstant Information
 *        </P>
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 01/11/2014
 * 
 *****************************************************************************/
public class ApplicationConstant {
   // public ApplicationConstant() {} 
    /** The Constant NULL. */
   public static final Object NULL = null;
    
    /** The Constant EMPTY. */
    public static final String EMPTY = "";
    
    /** The Constant SINGLE_SPACE. */
    public static final String SINGLE_SPACE = " ";
    
    /** The Constant ZERO. */
    public static final Integer ZERO = 0;
    
    /** The Constant SUCCESS. */
    public static final String SUCCESS = "SUCCESS";
    
    /** The Constant TRUE. */
    public static final String TRUE = "true";
    
    /** The Constant FAILURE. */
    public static final String FAILURE = "FAILURE";
    
    /** The Constant FALSE. */
    public static final String FALSE = "false";
    
    /** The Constant DATE_FORMAT. */
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    
    /** The Constant TIME_FORMAT. */
    public static final String TIME_FORMAT = "HH:mm:ss";
    
    /** The Constant JDATE_FORMAT. */
    public static final String JDATE_FORMAT = "d/m/yy";
    
    /** The Constant JTIME_FORMAT. */
    public static final String JTIME_FORMAT = "hh:mm:ss";
    
    /** The Constant APPLICATION_AUTHENTICATION_FAILURE. */
    public static final String APPLICATION_AUTHENTICATION_FAILURE = "Authentication Falied.Check your Login credentials";
    
    /** The Constant APPLICATION_AUTHORIZATION_DENIED. */
    public static final String APPLICATION_AUTHORIZATION_DENIED = "You are not authorized for this Target page.";
   
   /** The Constant INACTIVESTATUSID. */
   public static final Integer INACTIVESTATUSID= 2;
    
    /** The Constant FLAG_YES. */
    public static final Character FLAG_YES = 'Y';
    
    /** The Constant FLAG_NO. */
    public static final Character FLAG_NO = 'N';
    
    /** The Constant PHONE. */
    public static final String PHONE = "Phone";
    
    /** The Constant OFFICE. */
    public static final String OFFICE = "1";
    
    /** The Constant ACTIVE. */
    public static final String ACTIVE = "1";
    
    /** The Constant JSPDATEFORMAT. */
    private static final String JSPDATEFORMAT = JDATE_FORMAT;
    
    /** The Constant JSPTIMEFORMAT. */
    private static final String JSPTIMEFORMAT = JTIME_FORMAT;
    
    /** The Constant JDTIMEFORMAT. */
    private static final String JDTIMEFORMAT = "H:i:s";
    
    /** The Constant JSPTIMEDATEFORMAT. */
    private static final String JSPTIMEDATEFORMAT = JDATE_FORMAT + " " + JTIME_FORMAT;
    
    /** The Constant YES. */
    public static final String YES ="Yes";
    
    /** The Constant NO. */
    public static final String NO = "No";
    
    /** The Constant TABLE. */
    public static final String TABLE ="t_unique_key";
	
	/** The Constant VALUE_COL. */
	public static final String VALUE_COL = "sequence_next_hi_value";
	
	/** The Constant PRIM_KEY_COL. */
	public static final String PRIM_KEY_COL = "sequence_name";

	/** The Constant ROLE_TYPE. */
	public static final Integer ROLE_TYPE = 2;
	
	/** The Constant NA. */
	public static final String NA = "NA";
	
	/** The Constant NULL. */
	public static final String NUL = "{null}";
    /**
     * Gets the jsptimedate format.
     *
     * @return the jsptimedateFormat
     */
    public static String getJsptimedateFormat() {
        return JSPTIMEDATEFORMAT;
    }

    /**
     * Gets the jsp date format.
     *
     * @return the jspDateFormat
     */
    public static String getJspDateFormat() {
        return JSPDATEFORMAT;
    }

    /**
     * Gets the jsp time format.
     *
     * @return the jspTimeFormat
     */
    public static String getJspTimeFormat() {
        return JSPTIMEFORMAT;
    }

    /**
     * Gets the jd time format.
     *
     * @return the jdTimeFormat
     */
    public static String getJdTimeFormat() {
        return JDTIMEFORMAT;
    }
    
    /** The Constant CDATE_FORMAT. */
    public static final String CDATE_FORMAT = DATE_FORMAT + " " + TIME_FORMAT;
    
    /** The Constant JDTIME_FORMAT. */
    public static final String JDTIME_FORMAT = "H:i:s";
    
    /** The Constant EXP_MAP_KEY. */
    public static final String EXP_MAP_KEY = "ExceptionKey";
    
    /** The Constant STATUS_MAP_KEY. */
    public static final String STATUS_MAP_KEY = "StatusKey";

    /** SYSTEM_EXCEPTION_LOG_LEVEL indicates whether the system exception is logged at which level. */
    public static final String SYSTEM_EXCEPTION_LOG_LEVEL = "FATAL";

    /** DATA_EXCEPTION_LOG_LEVEL indicates whether the data exception is logged at which level. */
    public static final String DATA_EXCEPTION_LOG_LEVEL = "FATAL";

    /** BUSINESS_EXCEPTION_LOG_LEVEL indicates whether the business exception is logged at which level. */
    public static final String BUSINESS_EXCEPTION_LOG_LEVEL = "WARN";
    
    /**
     * The Enum Severity.
     */
    public enum Severity {
        
        /** The critical. */
        CRITICAL, 
 /** The warn. */
 WARN
    }

    	
    /** The Constant MANDATORY. */
    public static final String MANDATORY = "Mandatory";
    
    /** The Constant LENGTH. */
    public static final String LENGTH = "Length";
    
    /** The Constant REGEX. */
    public static final String REGEX = "Regex";
    
    /** The Constant EMAIL. */
    public static final String EMAIL = "Email";
    
    /** The Constant ISALPHABET. */
    public static final String ISALPHABET = "IsAlphabet";
    
    /** The Constant ISALPHANUMERIC. */
    public static final String ISALPHANUMERIC = "IsAlphaNumeric";
    
    /** The Constant RANGE. */
    public static final String RANGE = "Range";
    
    /** The Constant ISDATE. */
    public static final String ISDATE = "isDate";
    
    /** The Constant ISFUTUREDATE. */
    public static final String ISFUTUREDATE = "isFutureDate";
    
    /** The Constant ISPASTDATE. */
    public static final String ISPASTDATE = "isPastDate";
    
    /** The Constant BUS_EXP_FAULT. */
    public static final String BUS_EXP_FAULT = "BUS_EXP_FAULT";
    
    /** The Constant CLEANUP_FAILURE_DATAACCESS_EXCEPTION. */
    public static final String CLEANUP_FAILURE_DATAACCESS_EXCEPTION = "CLEANUP_FAILURE_DATAACCESS_EXCEPTION";
    
    /** The Constant CONCURRENCY_FAILURE_EXCEPTION. */
    public static final String CONCURRENCY_FAILURE_EXCEPTION = "CONCURRENCY_FAILURE_EXCEPTION";
    
    /** The Constant DATAACCESS_RESOURCE_FAILURE_EXCEPTION. */
    public static final String DATAACCESS_RESOURCE_FAILURE_EXCEPTION = "DATAACCESS_RESOURCE_FAILURE_EXCEPTION";
    
    /** The Constant DATAINTEGRITY_VIOLATION_EXCEPTION. */
    public static final String DATAINTEGRITY_VIOLATION_EXCEPTION = "DATAINTEGRITY_VIOLATION_EXCEPTION";
    
    /** The Constant DATARETRIEVAL_FAILURE_EXCEPTION. */
    public static final String DATARETRIEVAL_FAILURE_EXCEPTION = "DATARETRIEVAL_FAILURE_EXCEPTION";
    
    /** The Constant DATASOURCE_LOOKUP_FAILURE_EXCEPTION. */
    public static final String DATASOURCE_LOOKUP_FAILURE_EXCEPTION = "DATASOURCE_LOOKUP_FAILURE_EXCEPTION";
    
    /** The Constant INVALID_DATAACCESS_APIUSAGE_EXCEPTION. */
    public static final String INVALID_DATAACCESS_APIUSAGE_EXCEPTION = "INVALID_DATAACCESS_APIUSAGE_EXCEPTION";
    
    /** The Constant INVALID_DATAACCESS_RESOURCEUSAGE_EXCEPTION. */
    public static final String INVALID_DATAACCESS_RESOURCEUSAGE_EXCEPTION = "INVALID_DATAACCESS_RESOURCEUSAGE_EXCEPTION";
    
    /** The Constant PERMISSION_DENIED_DATAACCESS_EXCEPTION. */
    public static final String PERMISSION_DENIED_DATAACCESS_EXCEPTION = "PERMISSION_DENIED_DATAACCESS_EXCEPTION";
    
    /** The Constant UNCATEGORIZED_DATAACCESS_EXCEPTION. */
    public static final String UNCATEGORIZED_DATAACCESS_EXCEPTION = "UNCATEGORIZED_DATAACCESS_EXCEPTION";
    
    /** The Constant ENTITY_EXISTS_EXCEPTION. */
    public static final String ENTITY_EXISTS_EXCEPTION = "ENTITY_EXISTS_EXCEPTION";
    
    /** The Constant ENTITY_NOT_FOUND_EXCEPTION. */
    public static final String ENTITY_NOT_FOUND_EXCEPTION = "ENTITY_NOT_FOUND_EXCEPTION";
    
    /** The Constant LOCK_TIMEOUT_EXCEPTION. */
    public static final String LOCK_TIMEOUT_EXCEPTION = "LOCK_TIMEOUT_EXCEPTION";
    
    /** The Constant NON_UNIQUE_RESULT_EXCEPTION. */
    public static final String NON_UNIQUE_RESULT_EXCEPTION = "NON_UNIQUE_RESULT_EXCEPTION";
    
    /** The Constant NO_RESULT_EXCEPTION. */
    public static final String NO_RESULT_EXCEPTION = "NO_RESULT_EXCEPTION";
    
    /** The Constant OPTIMISTIC_LOCK_EXCEPTION. */
    public static final String OPTIMISTIC_LOCK_EXCEPTION = "OPTIMISTIC_LOCK_EXCEPTION";
    
    /** The Constant PESSIMISTIC_LOCK_EXCEPTION. */
    public static final String PESSIMISTIC_LOCK_EXCEPTION = "PESSIMISTIC_LOCK_EXCEPTION";
    
    /** The Constant QUERY_TIMEOUT_EXCEPTION. */
    public static final String QUERY_TIMEOUT_EXCEPTION = "QUERY_TIMEOUT_EXCEPTION";
    
    /** The Constant ROLLBACK_EXCEPTION. */
    public static final String ROLLBACK_EXCEPTION = "ROLLBACK_EXCEPTION";
    
    /** The Constant TRANSACTION_REQUIRED_EXCEPTION. */
    public static final String TRANSACTION_REQUIRED_EXCEPTION = "TRANSACTION_REQUIRED_EXCEPTION";
    
    /** The Constant INVALID_DATA. */
    public static final String INVALID_DATA = "INVALID_DATA";
    
    /** The Constant DATA_EXP_FAULT. */
    public static final String DATA_EXP_FAULT = "DATA_EXP_FAULT";
    
    /** The Constant OPT_LOCK_DATA_EXP_FAULT. */
    public static final String OPT_LOCK_DATA_EXP_FAULT = "OPT_LOCK_DATA_EXP_FAULT";
    
    /** The Constant NO_ROW_FOUND_EXP. */
    public static final String NO_ROW_FOUND_EXP = "NO_ROW_FOUND_EXP";
    
    /** The Constant EXP_FAULT. */
    public static final String EXP_FAULT = "EXP_FAULT";
    
    /** The Constant APP_EXP_FAULT. */
    public static final String APP_EXP_FAULT = "APP_EXP_FAULT";
    
    /** The Constant EXP_MESSAGE. */
    public static final String EXP_MESSAGE = "EXP_MESSAGE";
    
    /** The Constant RUNTIME_EXP_MESSAGE. */
    public static final String RUNTIME_EXP_MESSAGE = "RUNTIME_EXP_MESSAGE";
    
    /** The Constant DOJO_DATE_FORMAT. */
    public static final String DOJO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    
    /** The Constant INTEGER_NOTNULL. */
    public static final int INTEGER_NOTNULL = -0;
    
    /** The Constant FLOAT_NOTNULL. */
    public static final float FLOAT_NOTNULL = (float) -0.0;
    
    /** The Constant DOUBLE_NOTNULL. */
    public static final double DOUBLE_NOTNULL = (double) -0.0;
    
    /** The Constant LONG_NOTNULL. */
    public static final long LONG_NOTNULL = (long) -0;
    
    /** The Constant CUSTOMER_CODE_EXIST. */
    public static final String CUSTOMER_CODE_EXIST = "CUSTOMER_CODE_EXIST";
    
    /** The Constant EMPLOYEE_ID_EXIST. */
    public static final String EMPLOYEE_ID_EXIST = "EMPLOYEE_ID_EXIST";
    
    /** The Constant IATTAIN_ID_EXIST. */
    public static final String IATTAIN_ID_EXIST = "IATTAIN_ID_EXIST";
    /** The Constant FLAG_y. */
    public static final Character FLAG_y = 'y';
}
