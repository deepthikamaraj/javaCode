package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.MetricTriggerType;
import com.cognizant.opserv.sp.common.MetricUnitType;
import com.cognizant.opserv.sp.common.MetricValueType;
import com.cognizant.opserv.sp.core.dao.TGeoMtrValueDAO;
import com.cognizant.opserv.sp.core.dao.TMtrDAO;
import com.cognizant.opserv.sp.core.dao.TMtrExecConfigDAO;
import com.cognizant.opserv.sp.core.dao.TSalesPosMtrValueDAO;
import com.cognizant.opserv.sp.core.dao.TValueRangeDAO;
import com.cognizant.opserv.sp.core.entity.TValueRange;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.PostalCode;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.metric.Metric;
import com.cognizant.opserv.sp.model.metric.MetricConfig;
import com.cognizant.opserv.sp.model.metric.MetricExecutionTrigger;
import com.cognizant.opserv.sp.model.metric.MetricResult;
import com.cognizant.opserv.sp.model.metric.RangeDetails;
import com.cognizant.opserv.sp.persistence.dao.service.MetricResultDAOService;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
/**
 * ****************************************************************************.
 *
 * @class MetricResultDAOServiceImpl contains all the DAO metric services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Component
public class MetricResultDAOServiceImpl implements MetricResultDAOService {
	
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(MetricResultDAOServiceImpl.class);
	
	/*
	 * TMtrDAO has methods on metric entity
	 */
	@Autowired
	private TMtrDAO tMtrDAO;
	
	/*
	 * TSalesPosMtrValueDAO has methods on sales position metric value entity
	 */
	@Autowired
	private TSalesPosMtrValueDAO tSalesPosMtrValueDAO;

	/*
	 * TMtrExecConfigDAO has methods on metric execution configuration entity 
	 */
	@Autowired
	private TMtrExecConfigDAO tMtrExecConfigDAO;
	
	/*
	 * TGeoMtrValueDAO  has methods on geo metric value entity
	 */
	@Autowired
	private TGeoMtrValueDAO tGeoMtrValueDAO;
	
	/*
	 * TValueRangeDAO  has methods on value range
	 */
	@Autowired
	private TValueRangeDAO tValueRangeDAO;
	
	/**
	 * Gets the all metric results.
	 * 
	 * @method getAllMetricResults
	 * @param salesPosition
	 *            the sales position
	 * @param userDetails
	 *            the user details
	 * @return List<MetricResult> the all metric results
	 * @throws OpservDataAccessException
	 *             the data access exception
	 */
	@Override
	public List<MetricResult> getAllMetricResults(SalesPosition salesPosition,
			UserDetails userDetails) throws OpservDataAccessException {
		List<MetricResult> list = null;
		try{
			if (null != salesPosition && null != salesPosition.getAlignmment()) {
				Long alignmentId = salesPosition.getAlignmment().getId();
				Long bussUnitId = salesPosition.getAlignmment().getSalesTeam().getBusinessUnit().getId();
				Long salesTeamId = salesPosition.getAlignmment().getSalesTeam().getId();
				Long salesHierId = salesPosition.getHierarchy().getId();
				Long salesPosId = salesPosition.getId();

				LOGGER.info(" spId = " + salesPosId + " alignmentId = " + alignmentId 
						+" bussUnitId = " + bussUnitId +" salesTeamId = " + salesTeamId +" salesHierId = " + salesHierId);
				List<Object[]> mrtRes = tMtrDAO.getMetricInfoByALBUST(alignmentId, bussUnitId, salesTeamId, salesHierId, userDetails.getTenantId());	
				
				if(mrtRes!=null && !mrtRes.isEmpty()){
					List<Object[]> mtrValues = new ArrayList<Object[]>();
					List<Integer> mtrIds = new ArrayList<Integer>();
					list = new ArrayList<MetricResult>();
					
					for (Object[] object : mrtRes) {
						mtrIds.add((Integer)object[0]);
					}
					
					LOGGER.info("mtr ids = " + mtrIds);
					List<Integer> mtrValueTypeIds = new ArrayList<Integer>();
					mtrValueTypeIds.add(MetricValueType.INITIAL.getId());
					mtrValueTypeIds.add(MetricValueType.CURRENT.getId());
					mtrValueTypeIds.add(MetricValueType.PROPOSED.getId());
					
					if(mtrIds != null && !mtrIds.isEmpty()){
						mtrValues = tSalesPosMtrValueDAO.getMtrValueByMtrSpAndBaseFlag(mtrIds, salesPosId, mtrValueTypeIds, userDetails.getTenantId());
					}
					
					if(!mrtRes.isEmpty() ){
						for (Object[] mtr : mrtRes) {
							Integer metricId = (Integer) mtr[0];
							String mtrName = (String) mtr[1];
							Float minValue = (Float) mtr[2];
							Float maxValue = (Float) mtr[3];

							MetricResult metricResult = new MetricResult();
							Metric metric = new Metric();

							metricResult.setSalesPosition(salesPosition);

							metric.setId(metricId.longValue());
							metric.setName(mtrName);
							metric.setMaxValue(maxValue);
							metric.setMinValue(minValue);
							metric.setActive(CommonConstants.TRUE);
							metric.setAlignment(salesPosition.getAlignmment());
							if(mtr[4] != null){
								metric.setMetricUnitType(MetricUnitType.getMetricUnitType((Integer) mtr[4]));
							}
							if(mtr[5] != null && ((Character) mtr[5]).equals(CommonConstants.ACTIVE_Y)){
								metric.setDataDotActive(CommonConstants.TRUE);
							}

							//POPULATE VALUE
							if(mtrValues != null && !mtrValues.isEmpty()){
								populateValues(mtrValues,metricResult, metricId);
							}

							metricResult.setMetric(metric);					
							list.add(metricResult);
						}
					}
				}
			}
		} catch (RuntimeException e) {
			LOGGER.error(" RuntimeException at getAllMetricResults - Error while fetching the metrics results  "+e.getMessage());	
			throw new OpservDataAccessException("Error while fetching the metrics results", e);
		} 
		return list;
	}

	/**
	 * Gets the all metric results.
	 *
	 * @method getAllMetricResults
	 * @param salesPosition the sales position
	 * @param types the types
	 * @param userDetails the user details
	 * @return List<MetricResult> the all metric results
	 * @throws OpservDataAccessException the data access exception
	 */
	@Override
	public List<MetricResult> getAllMetricResults(SalesPosition salesPosition,
			List<MetricTriggerType> types, UserDetails userDetails)
			throws OpservDataAccessException {
		List<MetricResult> list = null;
		try{
			if (salesPosition != null && types !=null && !types.isEmpty()) {
				List<Integer> triggerIdList = new ArrayList<Integer>();
				Long alignmentId = salesPosition.getAlignmment().getId();
				Long bussUnitId = salesPosition.getAlignmment().getSalesTeam().getBusinessUnit().getId();
				Long salesTeamId = salesPosition.getAlignmment().getSalesTeam().getId();
				Long salesHierId = salesPosition.getHierarchy().getId();
				Long salesPosId = salesPosition.getId();
				
				LOGGER.info(" spId = " + salesPosId + " alignmentId = " + alignmentId 
						+" bussUnitId = " + bussUnitId +" salesTeamId = " + salesTeamId +" salesHierId = " + salesHierId);
				
				for(MetricTriggerType type : types){
					triggerIdList.add(type.getId());
				}
				
				LOGGER.info(" triggerIdList = " + triggerIdList);
				List<Object> mrtRes = tMtrExecConfigDAO.getMetricExecutionConfig(alignmentId, bussUnitId, salesTeamId, salesHierId, triggerIdList, userDetails.getTenantId());
				List<Object[]> spMtrResults = new ArrayList<Object[]>();
				
				if(mrtRes!=null && !mrtRes.isEmpty()){
					List<Integer> mIds = new ArrayList<Integer>();
					list = new ArrayList<MetricResult>();
					
					for (int i = 0; i<mrtRes.size();i++) {
						Object[] object = (Object[])mrtRes.get(i);
						mIds.add((Integer)object[3]);
					}
					
					LOGGER.info("mtr ids = " + mIds);
					List<Integer> mtrValueTypeIds = new ArrayList<Integer>();
					mtrValueTypeIds.add(MetricValueType.INITIAL.getId());
					mtrValueTypeIds.add(MetricValueType.CURRENT.getId());
					mtrValueTypeIds.add(MetricValueType.PROPOSED.getId());
					
					if(mIds != null && !mIds.isEmpty()){
						spMtrResults = tSalesPosMtrValueDAO.getMtrValueByMtrSpAndBaseFlag(mIds, salesPosId, mtrValueTypeIds, userDetails.getTenantId());
					}
					
					if(!mrtRes.isEmpty() ){
						List<Integer> mtrIds = new ArrayList<Integer>();
						for (int i = 0; i<mrtRes.size();i++) {
							Object[] mtr = (Object[])mrtRes.get(i);
							Integer metricId = (Integer) mtr[3];
							if(!mtrIds.contains(metricId)){
								String mtrName = (String) mtr[6];
								Float minValue = (Float) mtr[7];
								Float maxValue = (Float) mtr[8];

								MetricResult metricResult = new MetricResult();
								Metric metric = new Metric();

								metricResult.setSalesPosition(salesPosition);

								metric.setId(metricId.longValue());
								metric.setName(mtrName);
								metric.setMaxValue(maxValue);
								metric.setMinValue(minValue);
								metric.setActive(CommonConstants.TRUE);
								metric.setAlignment(salesPosition.getAlignmment());
								if(mtr[17] != null){
									metric.setMetricUnitType(MetricUnitType.getMetricUnitType((Integer) mtr[17]));
								}
								if(mtr[15] != null && ((Character) mtr[15]).equals(CommonConstants.ACTIVE_Y)){
									metric.setDataDotActive(CommonConstants.TRUE);
								}

								//POPULATE VALUE
								if(spMtrResults != null && !spMtrResults.isEmpty()){
									populateValues(spMtrResults,metricResult, metricId);
								}

								metricResult.setMetric(metric);					
								list.add(metricResult);
								mtrIds.add(metricId);
							}
						}
					}
				}
			}
		} catch (RuntimeException e) {
			LOGGER.error(" RuntimeException at getAllMetricResults - Error while fetching the metrics results based on trigger types  "+e.getMessage());	
			throw new OpservDataAccessException("Error while fetching the metrics results based on trigger types", e);
		}
		return list;
	}

	/**
	 * Gets the all metric results.
	 * 
	 * @method getAllMetricResults
	 * @param salesPosition
	 *            the sales position
	 * @param type
	 *            the type
	 * @param userDetails
	 *            the user details
	 * @return List<MetricResult> the all metric results
	 * @throws OpservDataAccessException
	 *             the data access exception
	 */
	public List<MetricResult> getAllMetricResults(SalesPosition salesPosition,
			MetricTriggerType type, UserDetails userDetails)
			throws OpservDataAccessException{
		List<MetricResult> list = null;
		try{
			if (salesPosition != null && type !=null) {
				Long alignmentId = salesPosition.getAlignmment().getId();
				Long bussUnitId = salesPosition.getAlignmment().getSalesTeam().getBusinessUnit().getId();
				Long salesTeamId = salesPosition.getAlignmment().getSalesTeam().getId();
				Long salesHierId = salesPosition.getHierarchy().getId();
				Long salesPosId = salesPosition.getId();
				
				List<Integer> triggerIdList = new ArrayList<Integer>();
				triggerIdList.add(type.getId());
				
				LOGGER.info(" spId = " + salesPosId + " alignmentId = " + alignmentId 
						+" bussUnitId = " + bussUnitId +" salesTeamId = " + salesTeamId +" salesHierId = " + salesHierId + 
						" triggerIdList = " + triggerIdList);
				
				List<Object> mrtRes = tMtrExecConfigDAO.getMetricExecutionConfig(alignmentId, bussUnitId, salesTeamId, salesHierId, triggerIdList, userDetails.getTenantId());
				List<Object[]> spMtrResults = new ArrayList<Object[]>();
				
				if(mrtRes!=null && !mrtRes.isEmpty()){
					List<Integer> mIds = new ArrayList<Integer>();
					list = new ArrayList<MetricResult>();
					
					for (int i = 0; i<mrtRes.size();i++) {
						Object[] object = (Object[])mrtRes.get(i);
						mIds.add((Integer)object[3]);
					}
					
					LOGGER.info("mtr ids = " + mIds);
					List<Integer> mtrValueTypeIds = new ArrayList<Integer>();
					mtrValueTypeIds.add(MetricValueType.INITIAL.getId());
					mtrValueTypeIds.add(MetricValueType.CURRENT.getId());
					mtrValueTypeIds.add(MetricValueType.PROPOSED.getId());
					
					if(mIds != null && !mIds.isEmpty()){
						spMtrResults = tSalesPosMtrValueDAO.getMtrValueByMtrSpAndBaseFlag(mIds, salesPosId, mtrValueTypeIds, userDetails.getTenantId());
					}
					
					if(!mrtRes.isEmpty() ){
						List<Integer> mtrIds = new ArrayList<Integer>();
						for (int i = 0; i<mrtRes.size();i++) {
							Object[] mtr = (Object[])mrtRes.get(i);
							Integer metricId = (Integer) mtr[3];
							if(!mtrIds.contains(metricId)){
								String mtrName = (String) mtr[6];
								Float minValue = (Float) mtr[7];
								Float maxValue = (Float) mtr[8];

								MetricResult metricResult = new MetricResult();
								Metric metric = new Metric();

								metricResult.setSalesPosition(salesPosition);

								metric.setId(metricId.longValue());
								metric.setName(mtrName);
								metric.setMaxValue(maxValue);
								metric.setMinValue(minValue);
								metric.setActive(CommonConstants.TRUE);
								metric.setAlignment(salesPosition.getAlignmment());
								if(mtr[17] != null){
									metric.setMetricUnitType(MetricUnitType.getMetricUnitType((Integer) mtr[17]));
								}
								if(mtr[15] != null && ( (Character) mtr[15]).equals(CommonConstants.ACTIVE_Y) ){
									metric.setDataDotActive(CommonConstants.TRUE);
								}

								//POPULATE VALUE
								if(spMtrResults != null && !spMtrResults.isEmpty()){
									populateValues(spMtrResults,metricResult, metricId);
								}

								metricResult.setMetric(metric);					
								list.add(metricResult);
								mtrIds.add(metricId);
							}
						}
					}
				}
			}
		} catch (RuntimeException e) {
			LOGGER.error(" RuntimeException at getAllMetricResults - Error while fetching the metrics results based on trigger types "+e.getMessage());
			throw new OpservDataAccessException("Error while fetching the metrics results based on trigger types", e);
		}
		return list;
	}
	
	/**
	 * @method populateValues - populate the initial,current and proposed values
	 * @param baseVals - metric base values
	 * @param curVals - metric current values
	 * @param propVals - metric proposed values
	 * @param metricResult - metric results
	 * @param mtrId - metric id
	 * @return void
	 */
	private void populateMetricValues(List<Object[]> baseVals,List<Object[]> curVals, List<Object[]> propVals, MetricResult metricResult,Long mtrId){
		for(Object[] metricBaseValRow : baseVals){
			long cmtrId = (long) metricBaseValRow[0];
			if(cmtrId == mtrId){
				if(metricBaseValRow[1] != null){
					metricResult.setBaseValue((Float) metricBaseValRow[1]);
				}
				if(metricBaseValRow[2] != null){
					metricResult.setBaseExecutedDate((Date) metricBaseValRow[2]);
				}
				break;
			}
		}

		for(Object[] metricValRow : curVals){
			long cmtrId = (long) metricValRow[0];
			if(cmtrId == mtrId){
				if(metricValRow[1] != null){
					metricResult.setActualValue((Float) metricValRow[1]);
				}
				if(metricValRow[2] != null){
					metricResult.setActualExecutedDate((Date) metricValRow[2]);
				}
				break;
			}
		}

		for(Object[] metricBaseValRow : propVals){
			long cmtrId = (long) metricBaseValRow[0];
			if(cmtrId == mtrId){
				if(metricBaseValRow[1] != null){
					metricResult.setProposedValue((Float) metricBaseValRow[1]);
				}
				if(metricBaseValRow[2] != null){
					metricResult.setProposedExecutedDate((Date) metricBaseValRow[2]);
				}
				break;
			}
		}
	}
	
	/**
	 * Gets the metric results by sales positions.
	 *
	 * @param mtrId the mtr id
	 * @param salesPositions the sales positions
	 * @param userDetails the user details
	 * @return the metric results by sales positions
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public List<MetricResult> getMetricResultsBySalesPositions(Long mtrId, List<SalesPosition> salesPositions, 
			UserDetails userDetails) throws OpservDataAccessException {
		List<MetricResult> metricResultList = null;
		try{
			List<Long> spIdList = new ArrayList<Long>();
			for(SalesPosition salesPosition : salesPositions){
				spIdList.add(salesPosition.getId());
			}
			List<Object[]> baseVals = tSalesPosMtrValueDAO.getMtrValueBySpListAndValueType(spIdList, 
					MetricValueType.INITIAL.getId(), mtrId.intValue(), userDetails.getTenantId());
			List<Object[]> curVals = tSalesPosMtrValueDAO.getMtrValueBySpListAndValueType(spIdList, 
					MetricValueType.CURRENT.getId(), mtrId.intValue(), userDetails.getTenantId());
			List<Object[]> propVals = tSalesPosMtrValueDAO.getMtrValueBySpListAndValueType(spIdList, 
					MetricValueType.PROPOSED.getId(), mtrId.intValue(), userDetails.getTenantId());
			
			if(null != baseVals && null != curVals && null != propVals){
				metricResultList = new ArrayList<MetricResult>();
				Metric metric = new Metric();
				metric.setId(mtrId);
				for(SalesPosition salesPosition : salesPositions){
					MetricResult metricResult = new MetricResult();
					metricResult.setSalesPosition(salesPosition);
					metricResult.setMetric(metric);
					populateMetricValues(baseVals,curVals,propVals, metricResult, salesPosition.getId());
					metricResultList.add(metricResult);
				}
			}
			return metricResultList;
		} catch (RuntimeException e) {
			LOGGER.error(" RuntimeException at getMetricResultsBySalesPositions - Error while fetching the metrics results "+e.getMessage());
			throw new OpservDataAccessException("Error while fetching the metrics results", e);
		} 
	}
	
	/**
	 * Gets the metric results by postal codes.
	 *
	 * @param mtrId the mtr id
	 * @param postalCodes the postal codes
	 * @param metricValueType the metric value type
	 * @param userDetails the user details
	 * @return the metric results by postal codes
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public Map<PostalCode, MetricResult> getMetricResultsByPostalCodes(Long mtrId,List<PostalCode> postalCodes, 
			Integer metricValueType, UserDetails userDetails) throws OpservDataAccessException {
		Map<PostalCode, MetricResult> metricResults = null;
		try{
			List<String> postalCodeList = new ArrayList<String>();
			for(PostalCode postalCd : postalCodes){
				postalCodeList.add(postalCd.getCode());
			}
			List<Object[]> tGeoMtrValues = tGeoMtrValueDAO.findTGeoMtrValueByIDsForHeatMap(mtrId.intValue(), postalCodeList, 
					userDetails.getTenantId());
			if(null != tGeoMtrValues && !tGeoMtrValues.isEmpty()){
				metricResults = new HashMap<PostalCode, MetricResult>();
				Metric metric = new Metric();
				metric.setId(mtrId);
				for(Object[] obj : tGeoMtrValues){
					MetricResult metricResult = new MetricResult();
					metricResult.setMetric(metric);
					metricResult.setActualValue((float) obj[0]);
					PostalCode postalCode = new PostalCode();
					postalCode.setCode((String) obj[1]);
					metricResults.put(postalCode, metricResult);
				}
			}
		}
		catch(RuntimeException e){
			LOGGER.error(" RuntimeException at getMetricResultsByPostalCodes - Error while fetching metric results based on postal codes  "+e.getMessage());
			throw new OpservDataAccessException("exception while fetching metric results", e);
		}
		return metricResults;
	}
	
	/**
	 * Gets the metric results by postal codes.
	 * @param postalCodes the postal codes
	 * @param userDetails the user details
	 * @return Map<PostalCode, List<MetricResult>> the metric results by postal codes
	 * @throws OpservDataAccessException - the opserv data access exception
	 */
	@Override
	public Map<PostalCode,List<MetricResult>> getAllMetricResultsByPostalCodes(List<PostalCode> postalCodes, UserDetails userDetails)
			throws OpservDataAccessException{
		try{
			Map<PostalCode,List<MetricResult>> resMap = null;
			List<String> postalCodeList = new ArrayList<String>();
			for(PostalCode postalCd : postalCodes){
				postalCodeList.add(postalCd.getCode());
			}
			if(!postalCodeList.isEmpty()){
				List<Object[]> tGeoMtrValues = tGeoMtrValueDAO.findTGeoMtrValueByPostalCd(postalCodeList, userDetails.getTenantId());
				if(tGeoMtrValues != null && !tGeoMtrValues.isEmpty()){
					resMap = new HashMap<PostalCode, List<MetricResult>>();
					Set<Integer> unqMtrIds = new HashSet<Integer>();
					for(Object[] obj :tGeoMtrValues){
						unqMtrIds.add((Integer) obj[0]);
					}
					List<Integer> mtrIds = new ArrayList<Integer>();
					mtrIds.addAll(unqMtrIds);
					
					List<Object[]> mtrValues =tMtrDAO.findTMtrByMtrIds(mtrIds, userDetails.getTenantId());
					for(PostalCode postalCd : postalCodes){
						List<Object[]> geoValues = new ArrayList<Object[]>();
						for(Object[] geoObj :tGeoMtrValues){
							String psCd = (String)geoObj[2];
							if(postalCd.getCode().equals(psCd)){
								geoValues.add(geoObj);
							}
						}
						
						List<MetricResult> metricResults = groupResults(geoValues,mtrValues);
						
						resMap.put(postalCd, metricResults);
					}
				}
			}
			return resMap;
		}catch(RuntimeException e){
			LOGGER.error(" RuntimeException at getAllMetricResultsByPostalCodes - Error while fetching list of metric results based on postal codes  "+e.getMessage());
			throw new OpservDataAccessException("exception while fetching metric results", e);
		}

	}

	/**
	 * @method groupResults - group results by postal code
	 * @param geoValues - geo mtr values
	 * @param mtrValues - mtr values
	 * @return List<MetricResult> - lsit of metric resutls
	 */
	private List<MetricResult> groupResults(List<Object[]> geoValues,
			List<Object[]> mtrValues) {
		List<MetricResult> metricResults = new ArrayList<MetricResult>();
		Set<Integer> mtrIds = new HashSet<Integer>();
		if(mtrValues != null && !mtrValues.isEmpty()){
			for(Object[] obj :mtrValues){
				mtrIds.add((Integer) obj[0]);
			}
			
			for(Integer mtrId : mtrIds){
				List<Object[]> geoMtrList = new ArrayList<Object[]>();
				for(Object[] obj :geoValues){
					Integer mId = (Integer) obj[0];
					if(mtrId.intValue() == mId.intValue()){
						geoMtrList.add(obj);
					}
				}
				metricResults.add(getRes(geoMtrList,mtrValues));
			}
		}
		return metricResults;
	}

	/**
	 * @method getRes - get results for postal code and metric
	 * @param geoMtrList - geo mtr values
	 * @param mtrValues - mtr values
	 * @return MetricResult - metric result
	 */
	private MetricResult getRes(List<Object[]> geoMtrList,
			List<Object[]> mtrValues) {
		MetricResult metricResult = new MetricResult();
		Integer mtrId = null;
		for(Object[] obj :geoMtrList){
			mtrId = (Integer) obj[0];
			Integer valTypeId = (Integer) obj[4];
			if(valTypeId.intValue() == MetricValueType.INITIAL.getId().intValue()){
				metricResult.setBaseValue( (float) obj[1]);
				if( obj[5] != null){
					metricResult.setBaseExecutedDate( (Date) obj[5]);
				}
			}else if(valTypeId.intValue() == MetricValueType.CURRENT.getId().intValue()){
				metricResult.setActualValue( (float) obj[1]);
				if( obj[5] != null){
					metricResult.setActualExecutedDate( (Date) obj[5]);
				}
			}else if(valTypeId.intValue() == MetricValueType.PROPOSED.getId().intValue()){
				metricResult.setProposedValue( (float) obj[1]);
				if( obj[5] != null){
					metricResult.setProposedExecutedDate( (Date) obj[5]);
				}
			}
		}

		for(Object[] mtrObj :mtrValues){
			Integer mId = (Integer) mtrObj[0];
			if(mId.intValue() == mtrId.intValue()){
				Metric mtr = new Metric();
				mtr.setId(mId.longValue());
				mtr.setName((String) mtrObj[1]);
				mtr.setMinValue((float) mtrObj[2]);
				mtr.setMaxValue((float) mtrObj[3]);
				if(mtrObj[4] != null){
					mtr.setMetricUnitType(MetricUnitType.getMetricUnitType((Integer) mtrObj[4]));
				}
				metricResult.setMetric(mtr);
				break;
			}
		}

		return metricResult;
	}

	/**
	 * Gets the metric execution by alignment
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return the metric execution trigger
	 * @throws OpservDataAccessException the metric service exception
	 */
	@Override
	public List<MetricExecutionTrigger> getMetricExecutionConfig(Alignment alignment, UserDetails userDetails) throws OpservDataAccessException {
		try{
			List<MetricExecutionTrigger> list = new ArrayList<MetricExecutionTrigger>();
			List<Object[]> mtrList = tMtrExecConfigDAO.findAllTMtrExecConfigByALBUST(alignment.getId(), alignment.getSalesTeam().getBusinessUnit().getId(), 
					alignment.getSalesTeam().getId(), userDetails.getTenantId());
			if(mtrList != null && !mtrList.isEmpty()){
				for(Object[] obj : mtrList){
					MetricExecutionTrigger mtrExecTrig = new MetricExecutionTrigger();
					SalesOrgHierarchy hierarchy = new SalesOrgHierarchy();
					MetricConfig metricConfig = new MetricConfig();
					Metric metric = new Metric();
					
					metric.setId(((Integer) obj[0]).longValue());
					metric.setName((String) obj[1]);
					metric.setMinValue((float) obj[2]);
					metric.setMaxValue((float) obj[3]);
					metric.setActive(CommonConstants.TRUE);
					if(obj[4] != null){
						metric.setMetricUnitType(MetricUnitType.getMetricUnitType((Integer) obj[4]));
					}
					metric.setAlignment(alignment);
					
					hierarchy.setId((Long) obj[7]);
					
					metricConfig.setHierarchy(hierarchy);
					metricConfig.setMetric(metric);
					mtrExecTrig.setMetricConfig(metricConfig);
					mtrExecTrig.setTriggerType(MetricTriggerType.getMetricTriggerType((Integer) obj[6]));
					list.add(mtrExecTrig);
				}
			}
			return list;
		}catch(RuntimeException e){
			LOGGER.error(" RuntimeException at getMetricExecutionConfig - Error while fetching list of metric execution data for given alignment  "+e.getMessage());
			throw new OpservDataAccessException("exception while fetching metric triggers", e);
		}
		
	}
	
	/**
	 * Gets the range details for salespos metrics.
	 *
	 * @param mtrId the mtr id
	 * @param userDetails the user details
	 * @return the range details by metric
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public List<RangeDetails> getRangeDetailsForSalesPosMetrics(Long mtrId,
			UserDetails userDetails) throws OpservDataAccessException {
		try{
			return getRange(tSalesPosMtrValueDAO.findMinAndMaxValueByMtrId(mtrId, userDetails.getTenantId()));
		}catch(RuntimeException e){
			LOGGER.error(" RuntimeException at getRangeDetailsForSalesPosMetrics - Error while fetching fetching range details for sp metrics  "+e.getMessage());
			throw new OpservDataAccessException("exception while fetching range details for sp metrics", e);
		}
	}
	
	/**
	 * Gets the range details for geo metrics.
	 *
	 * @param mtrId the mtr id
	 * @param userDetails the user details
	 * @return the range details for geo metrics
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public List<RangeDetails> getRangeDetailsForGeoMetrics(Long mtrId,
			UserDetails userDetails) throws OpservDataAccessException {
		try{
			List<Object[]> tGeoMtrValues = tGeoMtrValueDAO.findMinAndMaxValueByMtrId(mtrId, userDetails.getTenantId());
			return getRange(tGeoMtrValues);
		}catch(RuntimeException e){
			LOGGER.error(" RuntimeException at getRangeDetailsForGeoMetrics - Error while fetching fetching range details for zip metrics  "+e.getMessage());
			throw new OpservDataAccessException("exception while fetching range details for zip metrics", e);
		}
	}

	/**
	 * @method getColorCodes
	 * @return Map<Integer,String> - returns color codes
	 */
	private Map<Integer,String> getColorCodes(){
		Map<Integer,String> colorCodes = null; 
		SearchFilter<TValueRange> srchFlt = new SearchFilter<TValueRange>();
		OperatorInfo operatorInfo = new OperatorInfo();
		operatorInfo.setLogicalOperator(LogicalOperatorEnum.AND);
		srchFlt.setOperatorInfo(operatorInfo);
		PaginationInfo pagInfo = new PaginationInfo(0, -1);
		srchFlt.setEntityClass(new TValueRange());
		srchFlt.setPaginationInfo(pagInfo);
		List<TValueRange> tValueRanges = tValueRangeDAO.findTValueRanges(srchFlt);
		if(null != tValueRanges && !tValueRanges.isEmpty()){
			colorCodes = new HashMap<Integer,String>();
			for(TValueRange tValueRange : tValueRanges){
				colorCodes.put(tValueRange.getTValueRangeId().getLevelId(), tValueRange.getColorCd());
			}
		}
		return colorCodes;
	}

	/**
	 * @method getRange - retruns range
	 * @param tMtrValues - holds metric values
	 * @return List<RangeDetails> 
	 */
	private List<RangeDetails> getRange(List<Object[]> tMtrValues){
		List<RangeDetails> rangeDetailsList = null;
		if(null != tMtrValues && !tMtrValues.isEmpty()){
			Map<Integer,String> colorCodes = getColorCodes();
			if(null != colorCodes && !colorCodes.isEmpty()){
				rangeDetailsList = new ArrayList<RangeDetails>();
				if(null != tMtrValues.get(0)[0] && null != tMtrValues.get(0)[1]){
					Float minValue = (Float) tMtrValues.get(0)[0];
					Float maxValue = (Float) tMtrValues.get(0)[1];
					Float offset = (maxValue - minValue)/5;
					int cnt=1;
					while(minValue <= maxValue){
						RangeDetails rangeDetails = new RangeDetails();
						rangeDetails.setLowerLimit(minValue);
						rangeDetails.setUpperLimit(minValue + offset);
						rangeDetails.setColorCode(colorCodes.get(cnt));
						rangeDetailsList.add(rangeDetails);
						cnt++;
						minValue = minValue + offset;
					}
				}
			}
		}
		return rangeDetailsList;
	}
	
	
	/**
	 * @method populateValues - populate the initial,current and proposed values
	 * @param vals - metric values
	 * @param metricResult - metric results
	 * @param mtrId - metric id
	 * @return void
	 */
	private void populateValues(List<Object[]> vals,MetricResult metricResult,Integer mtrId){
		for(Object[] valrow : vals){
			Integer cmtrId = (Integer) valrow[0];
			if(cmtrId.intValue() == mtrId.intValue()){
				Integer mtrValueTypeId = (Integer) valrow[3];
				if(mtrValueTypeId.intValue() == MetricValueType.INITIAL.getId().intValue()){
					if(valrow[1] != null){
						metricResult.setBaseValue((Float) valrow[1]);
					}
					if(valrow[2] != null){
						metricResult.setBaseExecutedDate((Date) valrow[2]);
					}
				}else if(mtrValueTypeId.intValue() == MetricValueType.CURRENT.getId().intValue()){
					if(valrow[1] != null){
						metricResult.setActualValue((Float) valrow[1]);
					}
					if(valrow[2] != null){
						metricResult.setActualExecutedDate((Date) valrow[2]);
					}
				}else if(mtrValueTypeId.intValue() == MetricValueType.PROPOSED.getId().intValue()){
					if(valrow[1] != null){
						metricResult.setProposedValue((Float) valrow[1]);
					}
					if(valrow[2] != null){
						metricResult.setProposedExecutedDate((Date) valrow[2]);
					}
				}
				
				if(metricResult.getBaseExecutedDate() != null && metricResult.getActualExecutedDate() != null && 
						metricResult.getProposedExecutedDate() != null ){
					break;
				}
			}
		}
	}
	
	/**
	 * @method getAllMetricResultsByPostalCodes Gets the metric results by alignmentId and postal code
	 * @param postalCodes the postal codes
	 * @param alignmentId the alignment id
	 * @param userDetails the user details
	 * @return the metric results by postal codes
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public Map<PostalCode,List<MetricResult>> getAllMetricResultsByPostalCodes(List<PostalCode> postalCodes,Long alignmentId, UserDetails userDetails)
			throws OpservDataAccessException{
		try{
			Map<PostalCode,List<MetricResult>> resMap = null;
			LOGGER.info(" alignmentId = " + alignmentId);
			List<Object[]> mtrRes = tMtrDAO.getMetricInfoByAL(alignmentId, userDetails.getTenantId());
			if(mtrRes != null && !mtrRes.isEmpty() && !postalCodes.isEmpty()){
				resMap = new HashMap<PostalCode, List<MetricResult>>();
				List<String> postalCodeList = new ArrayList<String>();
				for(PostalCode postalCd : postalCodes){
					postalCodeList.add(postalCd.getCode());
				}
				List<Object[]> geoMtrValues = tGeoMtrValueDAO.findTGeoMtrValueByPostalCd(postalCodeList, userDetails.getTenantId());
				
				for(PostalCode postalCd : postalCodes){
					List<MetricResult> metricResults = new ArrayList<MetricResult>();
					List<Object[]> grpValues = new ArrayList<Object[]>();
					for(Object[] mtrVals :geoMtrValues){
						String psCd = (String)mtrVals[2];
						if(postalCd.getCode().equals(psCd)){
							grpValues.add(mtrVals);
						}
					}
					
					metricResults = groupMtrData(grpValues,mtrRes);
					resMap.put(postalCd, metricResults);
				}
			}
			return resMap;
		}catch(RuntimeException e){
			LOGGER.error(" RuntimeException at getAllMetricResultsByPostalCodes - Error while fetching list of metric triggers for given alignment and postal codes  "+e.getMessage());
			throw new OpservDataAccessException("Error while fetching list of metric triggers for given alignment and postal codes", e);
		}
	}

	/**
	 * @method groupMtrData 
	 * @param mtrVals - metrics values
	 * @param mtrRes - metrics data
	 * @return List<MetricResult> metric results
	 */
	private List<MetricResult> groupMtrData(List<Object[]> mtrVals,
			List<Object[]> mtrRes) {
		List<MetricResult> metricResults = new ArrayList<MetricResult>();
		for(Object[] obj :mtrRes){
			Integer metricId = (Integer) obj[0];
			String mtrName = (String) obj[1];
			Float minValue = (Float) obj[2];
			Float maxValue = (Float) obj[3];

			MetricResult metricResult = new MetricResult();
			Metric metric = new Metric();

			metric.setId(metricId.longValue());
			metric.setName(mtrName);
			metric.setMaxValue(maxValue);
			metric.setMinValue(minValue);
			metric.setActive(CommonConstants.TRUE);
			if(obj[4] != null){
				metric.setMetricUnitType(MetricUnitType.getMetricUnitType((Integer) obj[4]));
			}
			if(obj[5] != null && ((Character) obj[5]).equals(CommonConstants.ACTIVE_Y)){
				metric.setDataDotActive(CommonConstants.TRUE);
			}

			//POPULATE VALUE
			if(mtrVals != null && !mtrVals.isEmpty()){
				for(Object[] valrow : mtrVals){
					Integer cmtrId = (Integer) valrow[0];
					if(cmtrId.intValue() == metricId.intValue()){
						Integer mtrValueTypeId = (Integer) valrow[4];
						if(mtrValueTypeId.intValue() == MetricValueType.INITIAL.getId().intValue()){
							if(valrow[1] != null){
								metricResult.setBaseValue((Float) valrow[1]);
							}
							if(valrow[5] != null){
								metricResult.setBaseExecutedDate((Date) valrow[5]);
							}
						}else if(mtrValueTypeId.intValue() == MetricValueType.CURRENT.getId().intValue()){
							if(valrow[1] != null){
								metricResult.setActualValue((Float) valrow[1]);
							}
							if(valrow[5] != null){
								metricResult.setActualExecutedDate((Date) valrow[5]);
							}
						}else if(mtrValueTypeId.intValue() == MetricValueType.PROPOSED.getId().intValue()){
							if(valrow[1] != null){
								metricResult.setProposedValue((Float) valrow[1]);
							}
							if(valrow[5] != null){
								metricResult.setProposedExecutedDate((Date) valrow[5]);
							}
						}
						
						if(metricResult.getBaseExecutedDate() != null && metricResult.getActualExecutedDate() != null && 
								metricResult.getProposedExecutedDate() != null ){
							break;
						}
					}
				}
			}

			metricResult.setMetric(metric);					
			metricResults.add(metricResult);
		}
		return metricResults;
	}
}
