package com.cognizant.opserv.sp.common;

/**
 * ****************************************************************************.
 *
 * @class MetricConstants
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public class MetricConstants {

	/** The Constant MTR_TRIGGER_QUERY. */
	public static final String MTR_TRIGGER_QUERY = "select myTMtrTrigger.trigger_id, myTMtrTrigger.feature_id, myTMtrTrigger.type_id from t_mtr_trigger myTMtrTrigger " +
				" where myTMtrTrigger.trigger_id = ? AND  myTMtrTrigger.tenant_id = ? AND myTMtrTrigger.active_flag = 'Y'";
	
	/** The Constant MTR_DATA_QUERY. */
	public static final String MTR_DATA_QUERY = "select mtr.mtr_id,mtr.mtr_name,mtr.min_value,mtr.max_value,expr.mtr_expr,confg.child_rollup_flag " +
				" from t_mtr mtr inner join t_algmnt_sales_team alst on alst.algmnt_id = mtr.algmnt_id AND alst.buss_unit_id = mtr.buss_unit_id AND alst.sales_team_id = mtr.sales_team_id " +
				" inner join t_mtr_config confg on mtr.mtr_id = confg.mtr_id inner join t_mtr_expr expr on mtr.mtr_id = expr.mtr_id " +
				"	inner join t_mtr_exec_config execConfg on mtr.mtr_id = execConfg.mtr_id " +
				" where alst.algmnt_id = :algId and alst.buss_unit_id = :buId and alst.sales_team_id = :stId and confg.sales_hier_id = :shId " +
				" and expr.expr_type IN (:predCal) and execConfg.trigger_id = :triggerId and mtr.tenant_id = :tenantId " +
				" and mtr.active_flag=execConfg.active_flag and mtr.active_flag = confg.active_flag and mtr.active_flag = 'Y' " +
				" group by mtr.mtr_id order by mtr.mtr_name";
	
	/** The Constant SP_COUNT_QUERY. */
	public static final String SP_COUNT_QUERY = "select count(*) from t_sales_pos myTSalesPos where myTSalesPos.active_flag = 'Y'" +
				" and myTSalesPos.sales_pos_id = ? and myTSalesPos.tenant_id =?";

	/** The Constant PREDICTIVE. */
	public static final Character PREDICTIVE = 'P';

	/** The Constant CALCULATIVE. */
	public static final Character CALCULATIVE = 'C';
	
	/** The Constant PRNT_SP_ID. */
	public static final String PRNT_SP_ID = "prntSpId";
	
	/** The Constant SP_IDS. */
	public static final String SP_IDS = "spIds";
	
	/** The Constant MTR_ID. */
	public static final String MTR_ID = "mtrId";
	
	/** The Constant FEATURE_ID. */
	public static final String TRIGGER_ID = "triggerId";
	
	/** The Constant FEATURE_ID. */
	public static final String FEATURE_ID = "featureId";
	
	/** The Constant TYPE_ID. */
	public static final String TYPE_ID = "typeId";
	
	/** The Constant TENANT_ID. */
	public static final String TENANT_ID = "tenantId";
	
	/** The Constant TENANT_ID. */
	public static final String SUBSCRIBER_ID = "subscriberId";
	
	/** The Constant POSTAL_CD. */
	public static final String POSTAL_CD = "postalCd";
	
	/** The Constant ALG_ID. */
	public static final String ALG_ID = "algId";
	
	/** The Constant BU_ID. */
	public static final String BU_ID = "buId";

	/** The Constant ST_ID. */
	public static final String ST_ID = "stId";
	
	/** The Constant SH_ID. */
	public static final String SH_ID = "shId";
	
	/** The Constant SP_ID. */
	public static final String SP_ID = "spId";
	
	/** The Constant EXEC_TYPE. */
	public static final String EXEC_TYPE = "execType";
	
	/** The Constant USER_ID. */
	public static final String USER_ID = "userId";
	
	/** The Constant VALUES. */
	public static final String VALUES = "values";
	
	/** The Constant COUNT. */
	public static final String COUNT = "count(*)";
	
	/** The Constant PREDCAL. */
	public static final String PREDCAL = "predCal";
	
	/** The Constant COLUMN_TRIGGER_ID. */
	public static final String COLUMN_TRIGGER_ID = "trigger_id";
	
	/** The Constant COLUMN_FEATURE_ID. */
	public static final String COLUMN_FEATURE_ID = "feature_id";
	
	/** The Constant COLUMN_TYPE_ID. */
	public static final String COLUMN_TYPE_ID = "type_id";

	/** The Constant ERROR. */
	public static final String ERROR = "Error";

	/** The Constant STORE_PROC. */
	public static final String STORE_PROC = "storeProc";
	
	/** The Constant TENANT_KEY. */
	public static final String TENANT_KEY = "tenantKey";
	
}
