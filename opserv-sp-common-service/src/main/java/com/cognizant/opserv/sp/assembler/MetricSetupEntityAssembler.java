package com.cognizant.opserv.sp.assembler;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.MetricCategoryType;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesHier;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeamId;
import com.cognizant.opserv.sp.core.entity.TMtr;
import com.cognizant.opserv.sp.core.entity.TMtrConfig;
import com.cognizant.opserv.sp.core.entity.TMtrConfigId;
import com.cognizant.opserv.sp.core.entity.TMtrExecConfig;
import com.cognizant.opserv.sp.core.entity.TMtrExpr;
import com.cognizant.opserv.sp.core.entity.TMtrTrigger;
import com.cognizant.opserv.sp.core.entity.TMtrValMsg;
import com.cognizant.opserv.sp.core.entity.TMtrValMsgId;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.metric.Metric;
import com.cognizant.opserv.sp.model.metric.MetricConfig;


/**
 * The Class MetricSetupEntityAssembler.
 */
public class MetricSetupEntityAssembler {
	

	/**
	 * Instantiates a new metric setup entity assembler.
	 */
	private MetricSetupEntityAssembler(){
	}
	
	/**
	 * convertMetricModelToEntity.
	 *
	 * @param metric the metric
	 * @param userDetails the user details
	 * @return tMtr
	 */
	public static TMtr convertMetricModelToEntity(Metric metric, UserDetails userDetails){
		TMtr tMtr = new TMtr();
		TMtrValMsg tMtrValMsg =new TMtrValMsg();
		TAlgmntSalesTeam tAlgmntSalesTeam = new TAlgmntSalesTeam();
		TAlgmntSalesTeamId tAlgmntSalesTeamId = new TAlgmntSalesTeamId();
		tMtr.setMtrName(metric.getName());
		if( null!= metric.getCategory().getId() ){
			tMtr.setMtrCategoryId(metric.getCategory().getId());	
		}
		tMtr.setMaxValue(metric.getMaxValue());
		tMtr.setMinValue(metric.getMinValue());
		tMtr.setPrec(metric.getPrecision());
		tMtr.setTenantId(metric.getTenantId());
		tMtrValMsg.setValMsg(metric.getValmessage());
		
		tAlgmntSalesTeamId.setAlgmntId(metric.getAlignment().getId());
		tAlgmntSalesTeamId.setBussUnitId(metric.getAlignment().getSalesTeam()
				.getBusinessUnit().getId());
		tAlgmntSalesTeamId.setSalesTeamId(metric.getAlignment().getSalesTeam()
				.getId());
		tAlgmntSalesTeam.setTAlgmntSalesTeamId(tAlgmntSalesTeamId);

		tMtr.setTAlgmntSalesTeam(tAlgmntSalesTeam);
		if(metric.isVisible())
		{
			tMtr.setVisibleFlag(CommonConstants.ACTIVE_Y);
		}
		else
		{
			tMtr.setVisibleFlag(CommonConstants.ACTIVE_N);	
		}
		if(metric.isEnforced())
		{
			tMtr.setEnforceFlag(CommonConstants.ACTIVE_Y);
		}
		else
		{
			tMtr.setEnforceFlag(CommonConstants.ACTIVE_N);	
		}
		
		tMtr.setCreatedBy(metric.getCreatedBy());
		tMtr.setActiveFlag(CommonConstants.ACTIVE_Y);
		tMtr.setCreateDt(DateUtil.getCurrentDate());
		tMtr.setEffStartDt(DateUtil.getCurrentDate());
		if(null!=  metric.getEndDate() ){
			tMtr.setEffEndDt(metric.getEndDate());
		}
		
		return tMtr;
	}
	
	/**
	 * convertMetricEntityToModel.
	 *
	 * @param metric the metric
	 * @param tMtr the t mtr
	 * @param userDetails the user details
	 * @return metric
	 */
	public static Metric convertMetricEntityToModel(Metric metric ,TMtr tMtr, UserDetails userDetails){
		
		
		Alignment alignment= new Alignment();
		alignment.setId(tMtr.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getAlgmntId());
		SalesTeam salesTeam = new SalesTeam();
		salesTeam.setId(tMtr.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getSalesTeamId());
		BusinessUnit businessUnit = new BusinessUnit();
		businessUnit.setId(tMtr.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getBussUnitId());
		salesTeam.setBusinessUnit(businessUnit);
		alignment.setSalesTeam(salesTeam);
		if(null!=  tMtr.getMtrId() ){
			metric.setId((long)tMtr.getMtrId());
		}
		metric.setName(tMtr.getMtrName());
		
		metric.setActive(tMtr.getActiveFlag()=='Y'?true:false);
		metric.setCategory(MetricCategoryType.getMetricCategoryType(tMtr.getMtrCategoryId()));
		metric.setAlignment(alignment);
		metric.setCreatedBy(tMtr.getCreatedBy());
		metric.setCreatedDate(tMtr.getCreateDt());
		if(null!=  tMtr.getEffEndDt() ){
			metric.setEndDate(tMtr.getEffEndDt());	
		}
		metric.setStartDate(tMtr.getEffStartDt());
		metric.setTenantId(tMtr.getTenantId());
		
		
//		if(tMtr.getDataDotFlag()!='Y'||tMtr.getDataDotFlag()==null){
//			metric.setDataDotActive(false);
//		}else{
//			metric.setDataDotActive(true);
//		}
//		if(tMtr.getEnforceFlag()!='Y'|| tMtr.getEnforceFlag()==null){
//			metric.setEnforced(false);
//		}else{
//			metric.setEnforced(true);
//		}
		
		metric.setMaxValue(tMtr.getMaxValue());
		metric.setMinValue(tMtr.getMinValue());
		metric.setPrecision(tMtr.getPrec());
//		if(tMtr.getVisibleFlag()!='Y'||tMtr.getVisibleFlag()==null){
//			metric.setVisible(false);	
//		}else{
//			metric.setVisible(true);
//		}
		
		return metric;
	}
	
	/**
	 * convertMetricEntityToModel.
	 *
	 * @param tMtr the t mtr
	 * @param userDetails the user details
	 * @return metric
	 */
	public static Metric convertMetricEntityToModel(TMtr tMtr, UserDetails userDetails)
	{
		Metric metric=new Metric();
		Alignment alignment= new Alignment();
		alignment.setId(tMtr.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getAlgmntId());
		SalesTeam salesTeam = new SalesTeam();
		salesTeam.setId(tMtr.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getSalesTeamId());
		BusinessUnit businessUnit = new BusinessUnit();
		businessUnit.setId(tMtr.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getBussUnitId());
		salesTeam.setBusinessUnit(businessUnit);
		alignment.setSalesTeam(salesTeam);
		if(null!=  tMtr.getMtrId()){
			metric.setId((long)tMtr.getMtrId());
		}
		metric.setName(tMtr.getMtrName());
		metric.setActive(tMtr.getActiveFlag()=='Y'?true:false);
		metric.setCategory(MetricCategoryType.getMetricCategoryType(tMtr.getMtrCategoryId()));
		metric.setAlignment(alignment);
		metric.setCreatedBy(tMtr.getCreatedBy());
		metric.setCreatedDate(tMtr.getCreateDt());
		if(null!=  tMtr.getEffEndDt()){
			metric.setEndDate(tMtr.getEffEndDt());	
		}
		metric.setStartDate(tMtr.getEffStartDt());
		metric.setTenantId(tMtr.getTenantId());
		
		
//		if(tMtr.getDataDotFlag()!='Y'||tMtr.getDataDotFlag()==null){
//			metric.setDataDotActive(false);
//		}else{
//			metric.setDataDotActive(true);
//		}
//		if(tMtr.getEnforceFlag()!='Y'|| tMtr.getEnforceFlag()==null){
//			metric.setEnforced(false);
//		}else{
//			metric.setEnforced(true);
//		}
		
		metric.setMaxValue(tMtr.getMaxValue());
		metric.setMinValue(tMtr.getMinValue());
		metric.setPrecision(tMtr.getPrec());
//		if(tMtr.getVisibleFlag()!='Y'||tMtr.getVisibleFlag()==null){
//			metric.setVisible(false);	
//		}else{
//			metric.setVisible(true);
//		}
		
		return metric;
	}
	
	
	/**
	 * convertMetricModeltoTMtrValMsgEntity.
	 *
	 * @param metric the metric
	 * @param userDetails the user details
	 * @return tMtrValMsg
	 */
	public static TMtrValMsg convertMetricModeltoTMtrValMsgEntity(Metric metric,UserDetails userDetails){
       TMtrValMsg tMtrValMsg = new TMtrValMsg();
		
		
		
		//tMtrValMsg.setTMtr(tMtr);
		TMtrValMsgId tMtrValMsgId = new TMtrValMsgId();
		tMtrValMsgId.setMtrId(metric.getId().intValue());
		tMtrValMsgId.setLocaleId("en_US");
		//tMtrValMsgId.setLocaleId(userDetails.getLocaleId());
		tMtrValMsg.setTMtrValMsgId(tMtrValMsgId);
		tMtrValMsg.setValMsg(metric.getValmessage());
		tMtrValMsg.setCreateDt(DateUtil.getCurrentDate());
		tMtrValMsg.setCreatedBy(metric.getCreatedBy());
		tMtrValMsg.setTenantId(userDetails.getTenantId());
		//tMtrValMsg.setTMtr(tMtr);
		return tMtrValMsg;
		
	}

	/**
	 * convertMetricModeltoTMtrExprEntity.
	 *
	 * @param tMtr the t mtr
	 * @param userDetails the user details
	 * @return tMtrExpr
	 */
	public static TMtrExpr convertMetricModeltoTMtrExprEntity(TMtr tMtr,
			UserDetails userDetails) {

		TMtrExpr tMtrExpr = new TMtrExpr();
		tMtrExpr.setTMtr(tMtr);
		tMtrExpr.setCreatedBy(tMtr.getCreatedBy());
		tMtrExpr.setCreateDt(DateUtil.getCurrentDate());
		tMtrExpr.setExprType('C');
		
		String expr ="expression";
		tMtrExpr.setMtrExpr(expr);
		tMtrExpr.setTenantId(tMtr.getTenantId());
		if(null!= tMtr.getUpdatedBy() && null!= tMtr.getUpdateDt()){
			tMtrExpr.setUpdatedBy(userDetails.getStaffId());
			tMtrExpr.setUpdateDt(tMtr.getUpdateDt());
		}
		return tMtrExpr;

	}

	

	/**
	 * convertMtrConfigModeltoEntity.
	 *
	 * @param tMtr the t mtr
	 * @param tAlgmntSalesHier the t algmnt sales hier
	 * @param tMtrConfigId the t mtr config id
	 * @param userDetails the user details
	 * @param mConfig the m config
	 * @return tMtrConfig
	 */
	public static TMtrConfig convertMtrConfigModeltoEntity( TMtr tMtr,TAlgmntSalesHier tAlgmntSalesHier,TMtrConfigId tMtrConfigId,
			UserDetails userDetails, MetricConfig mConfig) {

		TMtrConfig tMtrConfig = new TMtrConfig();
		tMtrConfig.setTMtrConfigId(tMtrConfigId);
		tMtrConfig.setActiveFlag(CommonConstants.ACTIVE_Y);
		tMtrConfig.setCreatedBy(tMtr.getCreatedBy());
		tMtrConfig.setCreateDt(DateUtil.getCurrentDate());
		tMtrConfig.setTenantId(userDetails.getTenantId());
		if(null!= mConfig.getHierarchy() && null!= mConfig.getHierarchy().getId()){
			tMtrConfig.setTAlgmntSalesHier(tAlgmntSalesHier);
		}
		if(null!= mConfig.getMetric() && null!= mConfig.getMetric().getId()){
			tMtrConfig.setTMtr(tMtr);
		}
		
		tMtrConfig.setEffStartDt(DateUtil.getCurrentDate());
		if(null!= mConfig.getEndDate()){
			tMtrConfig.setEffEndDt(mConfig.getEndDate());
		}
		if (mConfig.isRollupActive()) {
			tMtrConfig.setChildRollupFlag(CommonConstants.ACTIVE_Y);
		} else
			tMtrConfig.setChildRollupFlag(CommonConstants.ACTIVE_N);
		return tMtrConfig;

	}

	/**
	 * convertMtrExecConfigModeltoEntity.
	 *
	 * @param tMtrConfig the t mtr config
	 * @param userDetails the user details
	 * @param tMtrTrigger the t mtr trigger
	 * @return tMtrExecConfig
	 */
	public static TMtrExecConfig convertMtrExecConfigModeltoEntity(
			TMtrConfig tMtrConfig, UserDetails userDetails,
			TMtrTrigger tMtrTrigger) {
		TMtrExecConfig tMtrExecConfig=new TMtrExecConfig();
		tMtrExecConfig.setActiveFlag(CommonConstants.ACTIVE_Y);
		tMtrExecConfig.setCreatedBy(tMtrConfig.getCreatedBy());
		tMtrExecConfig.setCreateDt(DateUtil.getCurrentDate());
		tMtrExecConfig.setTenantId(userDetails.getTenantId());
		tMtrExecConfig.setTMtrConfig(tMtrConfig);
		tMtrExecConfig.setTMtrTrigger(tMtrTrigger);
		tMtrExecConfig.setUpdatedBy(userDetails.getUserId());
		tMtrExecConfig.setUpdateDt(DateUtil.getCurrentDate());
		return tMtrExecConfig;
	}
}
