package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @class CommonConstants contains constants 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 05/04/2016
 * ***************************************************************************
 */
public class CommonConstants {
	
	/** The Constant ACTIVE_FLAG. */
	public static final String ACTIVE_FLAG = "Y";
	
	/** The Constant ACTIVE_Y. */
	public static final Character ACTIVE_Y = 'Y';
	
	/** The Constant ACTIVE_N. */
	public static final Character ACTIVE_N = 'N';
	
	/** The Constant TRUE. */
	public static final boolean TRUE = true;
	
	/** The Constant FALSE. */
	public static final  boolean FALSE = false;
	
	/** The Constant SPASSIGNMENTS. */
	public static final String SPASSIGNMENTS="SalesPositionAssignments";
	
	/** The Constant SPASSIGNMENTS. */
	public static final String SPVIEW="SalesPositionView";
	
	/** The Constant SPSTORE. */
	public static final String SPSTORE="SalesPositionStore";
	
	/** The Constant CUSTOMERALIGNMENT. */
	public static final String CUSTOMERALIGNMENT="CustomerAlignment";
	
	/** The Constant PRODUCTALIGNMENT. */
	public static final String PRODUCTALIGNMENT="ProductAlignment";
	
	/** The Constant EMPLOYEEALIGNMENT. */
	public static final String EMPLOYEEALIGNMENT="EmployeeAlignment";
	
	/** The Constant CUSTPRODUCTALIGNMENT. */
	public static final String CUSTPRODUCTALIGNMENT="CustomerProductAlignment";
	
	/** The Constant GEOGRAPHYALIGNMENT. */
	public static final String GEOGRAPHYALIGNMENT="GeographyAlignment";
	
	/** The Constant CUSTOMERCALLPLAN. */
	public static final String CUSTOMERCALLPLAN="CustomerCallPlan";
	
	/** The Constant NONPOACUSTOMERALIGNMENT. */
	public static final String NONPOACUSTOMERALIGNMENT="NonPoaCustomerAlignment";
	
	/** The Constant TARGET_Y. */
	public static final Character TARGET_Y = 'Y';
	
	/** The Constant TARGET_N. */
	public static final Character TARGET_N = 'N';
	
	/** The Constant EMPTY. */
    public static final String EMPTY = "";
    
    /** The Constant NULL. */
	public static final String NUL = "{null}";
	
	/** The Constant NA. */
	public static final String NA = "NA";
	
	/** The Constant VALUE_1. */
	public static final String VALUE_1="1";
	
	/** The Constant VALUE_2. */
	public static final String VALUE_2="2";
	
	/** The Constant VALUE_3. */
	public static final String VALUE_3="3";
	
	/** The Constant VALUE_4. */
	public static final String VALUE_4="4";
	
	/** The Constant zero. */
	public static final  Integer zeroCount = 0;
	
	/** The Constant SOURCE. */
	public static final Character SOURCE = 'S';
	
	/** The Constant TARGET. */
	public static final  Character TARGET = 'T';
	
	/** The Constant Push. */
	public static final String PUSH="Push";
	
	/** The Constant Pull. */
	public static final String PULL="pull";

	/** The Constant Push. *//*
	public static final String PUSH_CUSTOMER="PUSH_CUSTOMER";
	
	*//** The Constant Pull. *//*
	public static final String PULL_CUSTOMER="PULL_CUSTOMER";*/
	
	/** The Constant SALES_POS_ID. */
	public static final String SALES_POS_ID = "salesPosId";
	
	/** The Constant SALES_HIER_ID. */
	public static final String SALES_HIER_ID = "salesHierId";
	
	/** The Constant for template alignment enity alignment cache. */
	public static final String TEMPLATEALINMENT_ENTITY_CACHE="templateAlignment.entityTemplate.cache";
	
	/** The Constant SALESPOSITION_SERVICE_CACHE. */
	public static final String SALESPOSITION_SERVICE_CACHE="salesPosition.service.cache";
	
	/** The Constant PRODUCT_SERVICE_CACHE. */
	public static final String PRODUCT_SERVICE_CACHE="product.service.cache";
	
	/** The Constant CUST_PROD_ALGMNT_SERVICE_CACHE. */
	public static final String CUST_PROD_ALGMNT_SERVICE_CACHE="custPrdAlignment.service.cache";

	/** The Constant POSTAL_CD. */
	public static final String POSTAL_CD = "postalCd";
	
	/** The Constant CUST_NAME. */
	public static final String CUST_NAME = "custName";
	
	/** The Constant CUST_CD. */
	public static final String CUST_CD = "custCd";

	/** The count Ten. */
	public static final  Integer Count = 10;
	
	/** The Constant Dirty Flag. */
	public static final  Character DIRTY = 'Y';
	
	/** The Constant UPDATED. */
	public static final String UPDATED = "U";
	
	/** The Constant CUSTOMER. */
	public static final String CUSTOMER = "Customer";
	
	/** The Constant ZIP. */
	public static final String ZIP = "Zip";
	
	/** The Constant NONTARGETEDCUSTPRODUCTALIGNMENT. */
	public static final String NONTARGETEDCUSTPRODUCTALIGNMENT="NonTargetedCustomerProductAlignment";
	
	/** The Constant PUSH_PULL_QUEUE_NAME. */
	public static final String ONLINE_UPDATE_QUEUE = "onlineUpdateQueue";

	/** The Constant SINGLE_VALUED. */
	public static final Character SINGLE_VALUED = 'S';
	
	/** The Constant MULTI_VALUED. */
	public static final Character MULTI_VALUED = 'M';

	/** The Constant EMPTY_STR. */
	public static final String EMPTY_STR = "";
	
	/** The Constant ALIGN_NAME. */
	public static final String ALIGN_NAME = "algmntName";
	
	/** The Constant ALIGN_STATUS_ID. */
	public static final String ALIGN_STATUS_ID = "algmntStatusId";
	
	/** The Constant SALES_TEAM_NAME. */
	public static final String SALES_TEAM_NAME = "salesTeamName";
	
	/** The Constant BU_NAME. */
	public static final String BU_NAME = "bussUnitName";

	/** The Constant CUSTOMER_MOVEMENT. */
	public static final String CUSTOMER_MOVEMENT = "Customer Movement";

	/** The Constant CONTIGUITY_CHECK_ENABLED. */
	public static final String CONTIGUITY_CHECK_ENABLED = "Enable Contiguity Check";

	/** The Constant SP_NAME. */
	public static final String SP_NAME = "Sales Position Name";
	
	/** The Constant SP_CODE. */
	public static final String SP_CODE = "Sales Position Code";

	/** The Constant GEO_ASSIGNED_TO_SP. */
	public static final String GEO_ASSIGNED_TO_SP = "Geo Assigned to SP";
	
	/** The Constant ZIP_ASSIGNED_TO_SP_GEO. */
	public static final String ZIP_ASSIGNED_TO_SP_GEO = "Zips assigned to the SP-GEO";
	
	/** The Constant SP_HIERARCHY. */
	public static final String SP_HIERARCHY = "SP Hierarchy From Top Level";
	
	/** The Constant PRN_SP_CODE. */
	public static final String PRN_SP_CODE = "Parent SP Code";

    public static final Integer PRIMARY_ALLOC_ID = 1;
	/** The constant PLANNED_CALLS. */
	public static short TWO = 2;
	/** The Constant Online. */
	public static final String ONLINE = "online";
	
	/** The Constant Offline. */
	public static final String OFFLINE = "offline";
	
	/** The Constant PUSH_PULL_QUEUE_NAME. */
	public static final String OFFLINE_UPDATE_QUEUE = "offlineUpdateQueue";
	
	/** The Constant ONLINE_CHNAGE_REQUEST_SUBMIT_QUEUE. */
	public static final String ONLINE_CHNAGE_REQUEST_SUBMIT_QUEUE = "changeRequestSubmitQueue";
	
	/** The Constant ONLINE_CHNAGE_REQUEST_APPROVE_QUEUE. */
	public static final String ONLINE_CHNAGE_REQUEST_APPROVE_QUEUE = "changeRequestApproveQueue";
	
	/** The Constant ONLINE_CHNAGE_REQUEST_REJECT_QUEUE. */
	public static final String ONLINE_CHNAGE_REQUEST_REJECT_QUEUE = "changeRequestRejectQueue";
	
	/**  The Constant ONLINE_CHNAGE_REQUEST_WITHDRAWL_QUEUE */
	public static final String ONLINE_CHNAGE_REQUEST_WITHDRAWL_QUEUE = "changeRequestWithdrawlQueue";
	
//	public static final String CUSTOMER_ALIGNMENT_VIEW = "Customer Alignment view";
//	public static final String ZIP_SALESPOS_VIEW = "Zip SalesPos View";
//	public static final String CHANGE_REQUEST_VIEW = "Change Request view";
	public static final String MATERIALIZED_VIEW = "MAT_VIEW";
	
	public static final String CUSTOMER_MAT_VIEW = "CUST_M_VIEW";
	
	public static final String ZIP_MAT_VIEW = "ZIP_M_VIEW";
	
	public static final String CR_MAT_VIEW = "CR_M_VIEW";
	
	public static final String CHANGE_REQUEST_APPROVER_VIEW = "Change Request Approver view";
	
	public static final String CUSTOMER_UNIVERSE_VIEW = "Customer Universe view";

	/** The Constant Edit. */
	public static final String EDIT = "edit";

	public static final String BASIC = "Basic ";
	public static final String AUTHORIZATION = "Authorization";
	public static final String UTF8 = "UTF-8";
	public static final String CHANGE_REQUEST = "changeRequest";
	public static final String KERMIT = "kermit";
	public static final String APPROVE = "approve";
	public static final String COMPLETE = "complete";
	public static final String CREATE_TIME = "createTime";
	public static final String DESC = "desc";
	public static final String TRIGGER_TYPE = "triggerType";
	public static final String EQUALS_IGNORE_CASE = "equalsIgnoreCase";
	public static final String STRING = "string";
	public static final String PENDING_APPROVAL = "pendingApproval";
	public static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	/** The Constant ACTIVE. */
	public final static Integer ACTIVE = 1;
   /** The Constant ACTIVE_INT. */
	public static final Integer ACTIVE_INT = 1;
	/** The Constant COMMA. */
	public final static String COMMA = ", ";
	
	/** The Constant EMPLOYEE. */
	public final static String EMPLOYEE = "Employee";
	
	/**
	 * DEFAULT_LOCALE_ID
	 */
	public final static String DEFAULT_LOCALE_ID = "en_US";
	
	public final static String USER_ID = "UserId";
	
	public final static Character IMP_HEIGH = 'H';
	
	public final static Character IMP_LOW = 'L';
	
	public final static Character CHAR_YES = 'Y';
	
	public final static Character CHAR_NO = 'N';
	
	public final static String TENANT_CODE = "tenantCode";
	
	public final static String NOT_INTIATED = "Status of CR Offline Id is not Initiated, hence could not Push/Pull a customer";

	
	/** The Constant ONLINE_CHNAGE_REQUEST_SUBMIT_QUEUE. */
	public static final String NOTFICATION_SUBMIT_QUEUE = "NotificationMessageQueue";
	
	/** The Constant custSTExtAttrCount. */
	public final static Integer custSTExtAttrCount = 20;
	
	/** The Constant custExtAttrCount. */
	public final static Integer custExtAttrCount = 50;
	
	/** The Constant ZIP_PROC_NAME. */
	public final static String ZIP_PROC_NAME = "SP_ZIP_UPDATE";
	
	/** The Constant CUST_PROC_NAME. */
	public final static String CUST_PROC_NAME = "SP_CUST_UPDATE";
	
	/** The Constant CR_PROC_NAME. */
	public final static String CR_PROC_NAME = "SP_CR_UPDATE";

	/** The Constant MULTI_DOC. */
	public final static Integer MULTI_DOC = 1;
	
	/** The Constant NON_MULTI_DOC. */
	public final static Integer NON_MULTI_DOC = 2;
	
	public static final String CR_TYPE_CUSTOMER="customer";
	
	public static final String CR_TYPE_ZIP="zip";
	
	public static final String CR_TYPE_CUSTOMER_EDIT="customeredit";
	
	public static final String CR_NOTFICATION_SUBMIT_QUEUE = "CRNotificationMessageQueue";
	
	public final static String TENANT_ID= "tenantId";
	
	public final static String CHNG_REQ_ID= "chngReqId";
	
	public final static String OFFLINE_REQ_ID = "offlineReqId";	
	
	public final static String CR_TYPE= "chngReqType";
	
	public final static String CR_OFF_LINE_FULL_SUBMIT= "fullcrsubmit";
	
	public final static String CR_OFF_LINE_PARTIAL_SUBMIT= "partialcrsubmit";
	
	public final static String CR_OFF_LINE_CANCELLED= "fullcrreject";
	
	public final static String CR_OFF_LINE_INITIATED= "crinitiated";
	
	public final static String CR_OFF_LINE_PROCESSING= "crprocessing";
	
	public final static String CR_OFF_LINE_COMPLETED= "crcompleted";
	
	public final static String CR_ACTION_SUBMIT= "submit";
	
	public final static String CR_ACTION_APPROVE= "approve";
	
	public final static String CR_ACTION_REJECT= "reject";
	
	public final static String CR_PROCESSING_LOG = "CR_PROCESSING_LOG";
	
	public final static String CR_WORK_LOG_ID = "CR_WORK_LOG_ID";
	
	public final static String CR_OFFLINE_WORK_LOG_ID = "CR_OFFLINE_WORK_LOG_ID";
	
}
