package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.MetricTriggerType;
import com.cognizant.opserv.sp.common.MetricUnitType;
import com.cognizant.opserv.sp.common.MetricValidationType;
import com.cognizant.opserv.sp.common.MetricValueType;
import com.cognizant.opserv.sp.core.dao.TMtrDAO;
import com.cognizant.opserv.sp.core.dao.TMtrTriggerDAO;
import com.cognizant.opserv.sp.core.dao.TSalesPosMtrValueDAO;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.MetricDAOService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
/**
 * ****************************************************************************.
 *
 * @class MetricExecutionDAOServiceImpl contains all the DAO metric services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Component
public class MetricDAOServiceImpl implements  MetricDAOService {
	
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(MetricDAOServiceImpl.class);

	/** The Constant PREDICTIVE. */
	public static final Character PREDICTIVE = 'P';
	
	/** The Constant CALCULATIVE. */
	public static final Character CALCULATIVE = 'C';
	
	/*
	 * TSalesPosMtrValueDAO has methods on sales position metric value entity
	 */
	@Autowired
	private TSalesPosMtrValueDAO tSalesPosMtrValueDAO;
	
	/*
	 * TMtrDAO has methods on metric entity
	 */
	@Autowired
	private TMtrDAO tMtrDAO;
	
	/*
	 * TMtrTriggerDAO has methods on metric entity
	 */
	@Autowired
	private TMtrTriggerDAO tMtrTriggerDAO;

	/**
	 * @method getFeatureAndType - returns the feature id and type id
	 * @param mtrTriggerId - holds metric trigger id
	 * @param tenantId - holds tenant id
	 * @return Map<String,Integer> - holds the feature id and type id
	 */
	@Override
	public Map<String,Integer> getFeatureAndType(Integer mtrTriggerId, Short tenantId){
		try{
			Map<String,Integer> map = new HashMap<String,Integer>();
			
			List<Object[]> list = tMtrTriggerDAO.getAllTMtrTrigger(tenantId);
			if(list != null && !list.isEmpty()){
				for(Object[] obj : list){
					Integer trgId = (Integer) obj[0];
					if(trgId.intValue() == mtrTriggerId.intValue()){
						map.put("featureId", (Integer) obj[1]);
						map.put("typeId", (Integer) obj[2]);
						break;
					}
				}
			}
			return map;
		}catch (RuntimeException e) {
			LOGGER.error(" RuntimeException in getFeatureAndType - Error while getting feature and type id "+e.getMessage());	
			throw new OpservDataAccessException(
					"Error while getting feature and type id", e);
		}
	}
	
	
	/**
	 * @method getMetricValidationType - This method is to check metric validation type
	 * @param uomId - holds uom Id
	 * @param baseVal - holds base Value
	 * @param propVal - holds proposed Value
	 * @param minValue - holds minimum Value
	 * @param maxValue - holds maximum Value
	 * @param enforceFlag - holds enforce flag
	 * @return MetricValidationType - the metric validation type
	 */
	private MetricValidationType getMetricValidationType(Integer uomId, Float baseVal, Float propVal,
			Float minValue, Float maxValue, Character enforceFlag){
		MetricValidationType metricValidationType = MetricValidationType.NOT_APPILICABLE;
		if(uomId.intValue() == MetricUnitType.PERCENTAGE.getId().intValue()) {
			Float minRange =baseVal - (baseVal*minValue/100);
			Float maxRange =baseVal + (baseVal*maxValue/100);
			LOGGER.info(" UOM - PERCENTAGE");
			LOGGER.info(" minValue = " + minValue + " maxValue = " +maxValue);
			LOGGER.info(" minRange = " + minRange + " maxRange = " +maxRange);
			
			if(propVal >= minRange && propVal <= maxRange){
				metricValidationType = MetricValidationType.VALID;
			}else if (enforceFlag == null){
				metricValidationType = MetricValidationType.INVALID_AND_NON_ENFORCABLE;
			}else{
				if(enforceFlag.equals(CommonConstants.ACTIVE_Y)){
					metricValidationType = MetricValidationType.INVALID;
				}else{
					metricValidationType = MetricValidationType.INVALID_AND_NON_ENFORCABLE;
				}
			}
		}else if(uomId.intValue() == MetricUnitType.UNIT.getId().intValue()){
			LOGGER.info(" UOM - UNIT");
			LOGGER.info(" minValue = " + minValue + " maxValue = " +maxValue);
			
			if(propVal >= minValue && propVal <= maxValue){
				metricValidationType = MetricValidationType.VALID;
			}else if (enforceFlag == null){
				metricValidationType = MetricValidationType.INVALID_AND_NON_ENFORCABLE;
			}else{
				if(enforceFlag.equals(CommonConstants.ACTIVE_Y)){
					metricValidationType = MetricValidationType.INVALID;
				}else{
					metricValidationType = MetricValidationType.INVALID_AND_NON_ENFORCABLE;
				}
			}
		}
		LOGGER.info(metricValidationType.toString());
		return metricValidationType;
	}

	/**
	 * @method checkMetricViolation - This method is to check if metrics are violated or not
	 * @param metricTriggerType - the metric trigger types
	 * @param salesPosition - sales position information
	 * @param user - user details
	 * @return MetricValidationType - holds if metrics validation type
	 * @throws OpservDataAccessException - if we have violation or any issue.
	 */
	@Override
	public MetricValidationType checkMetricViolation(
			SalesPosition salesPosition, MetricTriggerType metricTriggerType,
			UserDetails user) throws OpservDataAccessException{
		try{
			MetricValidationType metricValidationType = null;
			List<MetricValidationType> mtrResulsList = new ArrayList<MetricValidationType>();
			if (null != salesPosition && null !=salesPosition.getAlignmment()) {
				Long alignmentId = salesPosition.getAlignmment().getId();
				Long bussUnitId = salesPosition.getAlignmment().getSalesTeam().getBusinessUnit().getId();
				Long salesTeamId = salesPosition.getAlignmment().getSalesTeam().getId();
				Long salesHierId = salesPosition.getHierarchy().getId();

				List<Character> predCal = new ArrayList<Character>();
				predCal.add(PREDICTIVE);
				predCal.add(CALCULATIVE);

				List<Object[]> metricRows = tMtrDAO.getMetricInfoByALBUSTExprType(alignmentId, bussUnitId, salesTeamId, salesHierId,predCal, metricTriggerType.getId(), user.getTenantId());
				if(metricRows != null && !metricRows.isEmpty()){
					List<Integer> mtrIds = new ArrayList<Integer>();
					for(Object[] obj :metricRows){
						mtrIds.add((Integer) obj[0]);
					}

					if(mtrIds != null && !mtrIds.isEmpty()){
						//ADD METRIC VALUE TYPES
						List<Integer> mtrValueTypeIds = new ArrayList<Integer>();
						mtrValueTypeIds.add(MetricValueType.INITIAL.getId());
						mtrValueTypeIds.add(MetricValueType.CURRENT.getId());
						mtrValueTypeIds.add(MetricValueType.PROPOSED.getId());
						List<Object[]> vals = tSalesPosMtrValueDAO.getMtrValueByMtrSpAndBaseFlag(mtrIds, salesPosition.getId(), mtrValueTypeIds, user.getTenantId());
						if(vals != null && !vals.isEmpty()){
							for(Object[] mtrObj  : metricRows){
								Integer mtrId = (Integer) mtrObj[0];
								Float minValue = (Float) mtrObj[2];
								Float maxValue = (Float) mtrObj[3];
								Integer uomId = null;
								if(mtrObj[7] != null){
									uomId = (Integer) mtrObj[7];
								}
								Character enforceFlag = CommonConstants.ACTIVE_N;
								if(mtrObj[6] != null){
									enforceFlag = (Character)mtrObj[6];
								}
								Float baseVal = null;
								Float propVal = null;
								LOGGER.info(" mtrId = " + mtrId + " uomId = "+ uomId);
								innerloop:	for(Object[] mtrValObj : vals){
									if(mtrId.intValue() == ((Integer) mtrValObj[0]).intValue()){
										Integer mtrValueTypeId = (Integer) mtrValObj[3];
										if(mtrValObj[1] != null){
											if(mtrValueTypeId.intValue() == MetricValueType.INITIAL.getId()){
												baseVal = (Float) mtrValObj[1];
											}else if(mtrValueTypeId.intValue() == MetricValueType.PROPOSED.getId()){
												propVal = (Float) mtrValObj[1];
											}
										}
										if(baseVal != null && propVal != null && minValue != null && maxValue != null && uomId != null){
											LOGGER.info( " baseVal = " + baseVal +" propVal = " +propVal) ;
											mtrResulsList.add(getMetricValidationType(uomId, baseVal, propVal, minValue, maxValue,enforceFlag));
											break innerloop;
										}
									}
								}
							}
						}
					}
				}
			}
			if(mtrResulsList != null && !mtrResulsList.isEmpty()){
				LOGGER.info(mtrResulsList.toString());
				if(mtrResulsList.contains(MetricValidationType.INVALID)){
					metricValidationType = MetricValidationType.INVALID;
				}else if(mtrResulsList.contains(MetricValidationType.INVALID_AND_NON_ENFORCABLE)){
					metricValidationType = MetricValidationType.INVALID_AND_NON_ENFORCABLE;
				}else{
					metricValidationType = MetricValidationType.VALID;
				}
			}
			if(metricValidationType == null){
				metricValidationType = MetricValidationType.NOT_APPILICABLE;
			}
			return metricValidationType;
		}catch (RuntimeException e) {
			LOGGER.error(" RuntimeException in checkMetricValidationType - Error while checking metric Validation Type "+e.getMessage());	
			throw new OpservDataAccessException(
					"Error while checking metric Validation Type", e);
		}
	}

	@Override
	public Map<Integer,String> getMtrData(Long alignmentId,Long bussUnitId,Long salesTeamId,Long salesHierId,Long salesPosId,Integer mtrTrgTypeId,Short tenantId){
		try{
			Map<Integer,String> map = null;
			List<Character> predCal = new ArrayList<Character>();
			predCal.add(PREDICTIVE);
			predCal.add(CALCULATIVE);
			List<Object[]> metricRows = tMtrDAO.getMetricInfoByALBUSTExprType(
					alignmentId, bussUnitId, salesTeamId, salesHierId,predCal,mtrTrgTypeId,
					tenantId);
			if(metricRows != null && !metricRows.isEmpty()){
				map = new HashMap<Integer,String>();
				for(Object[] mtr :metricRows){
					if( mtr[0] != null && mtr[4] != null && ! ((String)mtr[4]).trim().isEmpty() ){
							map.put((Integer) mtr[0], (String) mtr[4]);
					}
				}
			}
			return map;
		}catch (RuntimeException e) {
			LOGGER.error(" RuntimeException in getMtrData - Error while getting metrics data "+e.getMessage());	
			throw new OpservDataAccessException(
					"Error while getting metrics data", e);
		}
	}
}
