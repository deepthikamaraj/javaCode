package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.assembler.MetricSetupEntityAssembler;
import com.cognizant.opserv.sp.core.dao.TAlgmntSalesHierDAO;
import com.cognizant.opserv.sp.core.dao.TMtrCategoryDAO;
import com.cognizant.opserv.sp.core.dao.TMtrConfigDAO;
import com.cognizant.opserv.sp.core.dao.TMtrDAO;
import com.cognizant.opserv.sp.core.dao.TMtrExecConfigDAO;
import com.cognizant.opserv.sp.core.dao.TMtrExprDAO;
import com.cognizant.opserv.sp.core.dao.TMtrTriggerDAO;
import com.cognizant.opserv.sp.core.dao.TMtrValMsgDAO;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesHier;
import com.cognizant.opserv.sp.core.entity.TMtr;
import com.cognizant.opserv.sp.core.entity.TMtrConfig;
import com.cognizant.opserv.sp.core.entity.TMtrConfigId;
import com.cognizant.opserv.sp.core.entity.TMtrExecConfig;
import com.cognizant.opserv.sp.core.entity.TMtrExpr;
import com.cognizant.opserv.sp.core.entity.TMtrTrigger;
import com.cognizant.opserv.sp.core.entity.TMtrValMsg;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.metric.Metric;
import com.cognizant.opserv.sp.model.metric.MetricConfig;
import com.cognizant.opserv.sp.model.metric.MetricExecutionTrigger;
import com.cognizant.opserv.sp.persistence.dao.service.MetricSetupDAOService;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class MetricSetupDAOServiceImpl contains all the DAO metrics services
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 *        ***************************************************************************
 */

@Component
public class MetricSetupDAOServiceImpl implements MetricSetupDAOService {

	/**
	 *  The t mtr  dao. 
	 */
	@Autowired
	private TMtrDAO tMtrDAO;

	/** The t mtr category dao. */
	@Autowired
	private TMtrCategoryDAO tMtrCategoryDAO;

	/** The t mtr valmsg dao. */
	@Autowired
	private TMtrValMsgDAO tMtrValMsgDAO;

	/** The t mtr expr dao. */
	@Autowired
	private TMtrExprDAO tMtrExprDAO;

	/** The t alignment saleshier dao. */
	@Autowired
	private TAlgmntSalesHierDAO tAlgmntSalesHierDAO;

	/** The t mtr config dao. */
	@Autowired
	private TMtrConfigDAO tMtrConfigDAO;

	/** The t mtr trigger dao. */
	@Autowired
	private TMtrTriggerDAO tMtrTriggerDAO;

	/** The t mtr execConfig dao. */
	@Autowired
	private TMtrExecConfigDAO tMtrExecConfigDAO;

	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.MetricSetupDAOService#createMetric(com.cognizant.opserv.sp.model.metric.Metric, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public Metric createMetric(Metric metric, UserDetails userDetails)
			throws OpservDataAccessException {

		try {
			TMtr tMtr = new TMtr();
			tMtr = MetricSetupEntityAssembler.convertMetricModelToEntity(
					metric, userDetails);
			// create new entry in TMtr
			if(null != tMtr)
			{
			tMtr = tMtrDAO.createTMtr(tMtr);
			}
			// mapped
			Metric mappedMtr = MetricSetupEntityAssembler
					.convertMetricEntityToModel(metric,tMtr, userDetails);
			
			/*TMtrValMsg tMtrValMsg = MetricSetupEntityAssembler
					.convertMetricModeltoTMtrValMsgEntity(tMtr, userDetails);
			tMtrValMsg = tMtrValMsgDAO.createTMtrValMsg(tMtrValMsg);*/
			TMtrExpr tMtrExpr = MetricSetupEntityAssembler
					.convertMetricModeltoTMtrExprEntity(tMtr, userDetails);
			tMtrExpr = tMtrExprDAO.createTMtrExpr(tMtrExpr);

			updateValMsg(mappedMtr, userDetails);
			return mappedMtr;
		} catch (RuntimeException e) {
			throw new OpservDataAccessException(
					"Error occurred while creating new Metric", e);
		}

	}

	/** updateValMsg
	 * @param metric
	 * @param userDetails
	 * @throws OpservDataAccessException
	 */
	private void updateValMsg(Metric metric, UserDetails userDetails)
			throws OpservDataAccessException {
		try {
		TMtrValMsg tMtrValMsg = MetricSetupEntityAssembler
				.convertMetricModeltoTMtrValMsgEntity(metric, userDetails);
		
		tMtrValMsg = tMtrValMsgDAO.createTMtrValMsg(tMtrValMsg);
		} catch (RuntimeException e) {
			throw new OpservDataAccessException(
					"Error occurred while creating new Metric Val message", e);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.MetricSetupDAOService#updateMetric(com.cognizant.opserv.sp.model.metric.Metric, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public void updateMetric(Metric metric, UserDetails userDetails)
			throws OpservDataAccessException {
		try {
			TMtr tMtr = tMtrDAO.updateTMtr(MetricSetupEntityAssembler
					.convertMetricModelToEntity(metric, userDetails));

			/*TMtrValMsg tMtrValMsg = MetricSetupEntityAssembler
					.convertMetricModeltoTMtrValMsgEntity(tMtr, userDetails);
			tMtrValMsgDAO.updateTMtrValMsg(tMtrValMsg);
*/
			TMtrExpr tMtrExpr = MetricSetupEntityAssembler
					.convertMetricModeltoTMtrExprEntity(tMtr, userDetails);
			tMtrExprDAO.updateTMtrExpr(tMtrExpr);
		} catch (RuntimeException e) {
			throw new OpservDataAccessException(
					"Error occurred while updating  Metric", e);
		}
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.MetricSetupDAOService#fetchMetricsByAlignment(com.cognizant.opserv.sp.model.Alignment, com.cognizant.peg.core.common.PaginationInfo, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public List<Metric> fetchMetricsByAlignment(Alignment alignment,
			PaginationInfo paginInfo, UserDetails userDetails)
			throws OpservDataAccessException {
		try {
			List<Metric> metricList=new ArrayList<Metric>();
			List<TMtr> tMtrList = tMtrDAO.FindTMtrByABSIds(alignment.getId(),alignment.getSalesTeam().getBusinessUnit().getId(),
					alignment.getSalesTeam().getId()
					);
			if(null !=tMtrList &&  !tMtrList.isEmpty()){
				for(TMtr tMtr : tMtrList){
					
					Metric metric= MetricSetupEntityAssembler.convertMetricEntityToModel(tMtr, userDetails);
					
					
					metricList.add(metric);
				}
			}
			
			return metricList;
		} catch (RuntimeException e) {
			throw new OpservDataAccessException(
					"Error occurred while fetching  Metrics by alignment",
					e);
		}
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.MetricSetupDAOService#configureMetric(com.cognizant.opserv.sp.model.metric.MetricConfig, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public void configureMetric(MetricConfig mConfig, UserDetails userDetails)
			throws OpservDataAccessException {
		try {
			if (null != mConfig.getMetric().getId()
					&& null != mConfig.getHierarchy().getId()
					&& null != userDetails.getTenantId()) {
				int metricId = mConfig.getMetric().getId().intValue();

				TMtr tMtr = tMtrDAO.findTMtrById(metricId);
				TAlgmntSalesHier tAlgmntSalesHier = tAlgmntSalesHierDAO
						.findTAlgmntSalesHierById(mConfig.getHierarchy()
								.getId().longValue());
				TMtrConfigId tMtrConfigId = new TMtrConfigId();
				tMtrConfigId.setMtrId(metricId);
				tMtrConfigId.setSalesHierId(mConfig.getHierarchy().getId());

				TMtrConfig tMtrConfig = MetricSetupEntityAssembler
						.convertMtrConfigModeltoEntity(tMtr,tAlgmntSalesHier,tMtrConfigId, userDetails, mConfig);

				tMtrConfigDAO.createTMtrConfig(tMtrConfig);
			}
		} catch (RuntimeException e) {
			
			throw new OpservDataAccessException(
					"Error occurred while configuring  Metrics ", e);
		}

	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.MetricSetupDAOService#configureMetricExecutionTrigger(com.cognizant.opserv.sp.model.metric.MetricExecutionTrigger, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public void configureMetricExecutionTrigger(
			MetricExecutionTrigger mTrigger, UserDetails userDetails)
			throws OpservDataAccessException {
		try {
			if (null != mTrigger.getId() 
					&& null != mTrigger.getMetricConfig().getMetric().getId()
					&& null != mTrigger.getMetricConfig().getHierarchy()
							.getId() && null != userDetails.getTenantId()) {

				TMtrConfigId tMtrConfigId = new TMtrConfigId();
				tMtrConfigId.setMtrId(mTrigger.getMetricConfig().getMetric()
						.getId().intValue());
				tMtrConfigId.setSalesHierId(mTrigger.getMetricConfig()
						.getHierarchy().getId());

				TMtrConfig tMtrConfig = tMtrConfigDAO
						.findTMtrConfigById(tMtrConfigId);

				TMtrTrigger tMtrTrigger = tMtrTriggerDAO
						.findTMtrTriggerById(mTrigger.getId().intValue());
				TMtrExecConfig tMtrExecConfig = MetricSetupEntityAssembler
						.convertMtrExecConfigModeltoEntity(tMtrConfig,
								userDetails, tMtrTrigger);

				tMtrExecConfigDAO.createTMtrExecConfig(tMtrExecConfig);

			}
		} catch (RuntimeException e) {
			throw new OpservDataAccessException(
					"Error occurred while configuring  Metricexecution ",
					e);
		}

	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.MetricSetupDAOService#configureMetricExecutionTrigger(java.util.List, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public void configureMetricExecutionTrigger(
			List<MetricExecutionTrigger> mTrigger, UserDetails userDetails)
			throws OpservDataAccessException {
		try {
			TMtrConfigId tMtrConfigId = new TMtrConfigId();
			TMtrExecConfig tMtrExecConfig =new TMtrExecConfig();
			if (mTrigger.size() > 0) {
				for (MetricExecutionTrigger mTriggerList : mTrigger) {
					if (null != mTriggerList.getId()
							&& null != mTriggerList.getMetricConfig()
									.getMetric().getId()
							&& null != mTriggerList.getMetricConfig()
									.getHierarchy().getId() && null != userDetails.getTenantId()) {

						
						tMtrConfigId.setMtrId(mTriggerList.getMetricConfig()
								.getMetric().getId().intValue());
						tMtrConfigId.setSalesHierId(mTriggerList
								.getMetricConfig().getHierarchy().getId());

						TMtrConfig tMtrConfig = tMtrConfigDAO
								.findTMtrConfigById(tMtrConfigId);

						TMtrTrigger tMtrTrigger = tMtrTriggerDAO
								.findTMtrTriggerById(mTriggerList.getId()
										.intValue());
						 tMtrExecConfig = MetricSetupEntityAssembler
								.convertMtrExecConfigModeltoEntity(tMtrConfig,
										userDetails, tMtrTrigger);

						tMtrExecConfigDAO.createTMtrExecConfig(tMtrExecConfig);

					}
				}
			}
		} catch (RuntimeException e) {
			throw new OpservDataAccessException(
					"Error occurred while configuring  Metricexecution ",
					e);
		}

	}

}
