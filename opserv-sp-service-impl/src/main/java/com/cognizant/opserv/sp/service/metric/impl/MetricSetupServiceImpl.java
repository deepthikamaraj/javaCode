package com.cognizant.opserv.sp.service.metric.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.exception.MetricServiceException;
import com.cognizant.opserv.sp.exception.MetricServiceException.MetricServiceExceptionCode;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.metric.Metric;
import com.cognizant.opserv.sp.model.metric.MetricConfig;
import com.cognizant.opserv.sp.model.metric.MetricExecutionTrigger;
import com.cognizant.opserv.sp.persistence.dao.service.MetricSetupDAOService;
import com.cognizant.opserv.sp.service.metric.MetricSetupService;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class MetricSetupServiceImpl contains all the metrics services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Service("metricSetupService")
@Transactional(rollbackFor = MetricServiceException.class)
public class MetricSetupServiceImpl implements MetricSetupService  {
	
	/*
	 * MetricSetupDAOService has all methods to call DAO and to map the entity
	 */
	/** The MetricSetup dao service. */
	@Autowired
	private MetricSetupDAOService metricSetupDAOService;

	
	/**
	 * 
	 * @method createMetric
	 * @param metric
	 * @param userDetails
	 * @return
	 * @throws MetricServiceException
	 */
	@Override
	public Metric createMetric(Metric metric, UserDetails userDetails)
			throws MetricServiceException {
		if( null ==  metric.getAlignment()  || null == metric.getName() ||null==metric.getCategory() || null == userDetails.getTenantId()){
			String params = "Required Data Missing";
			Object[] args = new Object[]{params};
			throw new  MetricServiceException(args);
		}
		try{
			
			//metricSetupDAOService.updateValMsg(mtr, userDetails);
			return  metricSetupDAOService.createMetric(metric, userDetails);
		}catch(OpservDataAccessException e){
			
			Object[] args = new Object[]{"exception occured while creating a new Metric"};
		    throw new MetricServiceException(MetricServiceExceptionCode.MTR_SER_EX_CD_0010 ,"create_exception",args,e);
		}
		
	}

	
	/**
	 * 
	 * @method updateMetric
	 * @param metric
	 * @param userDetails
	 * @return
	 * @throws MetricServiceException
	 */
	@Override
	public void updateMetric(Metric metric, UserDetails userDetails)
			throws MetricServiceException {
		if(   null ==  metric.getAlignment() || null== metric.getId() || null==metric.getName()  || null == userDetails.getTenantId()){
			String params = "Required Data Missing";
			Object[] args = new Object[]{params};
			throw new  MetricServiceException(args);
		}
		try{
		metricSetupDAOService.updateMetric(metric, userDetails);
		}catch(OpservDataAccessException e){
			Object[] args = new Object[]{"exception occured while updating a  Metric"};
		    throw new MetricServiceException(MetricServiceExceptionCode.MTR_SER_EX_CD_0011 ,"update_exception",args,e);
		}
	}

	/**
	 * 
	 * @method fetchMetricsByAlignment
	 * @param alignment
	 * @param paginInfo
	 * @param userDetails
	 * @return list of Metrics
	 * @throws MetricServiceException
	 */
	
	@Override
	public List<Metric> fetchMetricsByAlignment(Alignment alignment,
			PaginationInfo paginInfo, UserDetails userDetails)
			throws MetricServiceException {
		if(null == alignment || null == alignment.getSalesTeam() || null == alignment.getSalesTeam().getBusinessUnit()  ||
				  null == alignment.getId() || null == alignment.getSalesTeam().getBusinessUnit().getId() || 
				  null == alignment.getSalesTeam().getId() || null == userDetails.getTenantId()){
			String params = "Required Data Missing";
			Object[] args = new Object[]{params};
			throw new  MetricServiceException(args);
		}
		try{
		return metricSetupDAOService.fetchMetricsByAlignment(alignment, paginInfo, userDetails);
	}catch(OpservDataAccessException e){
		Object[] args = new Object[]{"exception occured while fetching  Metrics by alignment"};
	    throw new MetricServiceException(MetricServiceExceptionCode.MTR_SER_EX_CD_0012 ,"fetch_metrics_exception",args,e);
	}
	}

	/**
	 * 
	 * @method configureMetric
	 * @param mConfig
	 * @param userDetails
	 * @return
	 * @throws MetricServiceException
	 */
	@Override
	@Transactional
	public void configureMetric(MetricConfig mConfig, UserDetails userDetails)
			throws MetricServiceException {
		if( null==mConfig.getHierarchy().getId() || null == mConfig.getMetric().getAlignment()||
				null == mConfig.getMetric().getId()|| null == userDetails.getTenantId()){
			String params = "Required Data Missing";
			Object[] args = new Object[]{params};
			throw new  MetricServiceException(args);
		}
		try
		{
		metricSetupDAOService.configureMetric(mConfig, userDetails);
		}catch(OpservDataAccessException e){
			Object[] args = new Object[]{"exception occured while configuring  Metrics "};
		    throw new MetricServiceException(MetricServiceExceptionCode.MTR_SER_EX_CD_0013 ,"configure_metrics_exception",args,e);
		}
	}

	/**
	 * 
	 * @method configureMetricExecutionTrigger
	 * @param mTrigger
	 * @param userDetails
	 * @return
	 * @throws MetricServiceException
	 */
	
	@Override
	public void configureMetricExecutionTrigger(
			MetricExecutionTrigger mTrigger, UserDetails userDetails)
			throws MetricServiceException {
		if( null == mTrigger.getId()
				|| null == userDetails.getTenantId()){
			String params = "Required Data Missing";
			Object[] args = new Object[]{params};
			throw new  MetricServiceException(args);
		}
		try
		{
		metricSetupDAOService.configureMetricExecutionTrigger(mTrigger, userDetails);
		}catch(OpservDataAccessException e){
			Object[] args = new Object[]{"exception occured while configuring  Metricexecution "};
		    throw new MetricServiceException(MetricServiceExceptionCode.MTR_SER_EX_CD_0014 ,"configure_metrics_exception",args,e);
		}
	}

	

	/**
	 * 
	 * @method configureMetricExecutionTrigger
	 * @param mTrigger
	 * @param userDetails
	 * @return
	 * @throws MetricServiceException
	 */
	
	@Override
	public void configureMetricExecutionTrigger(
			List<MetricExecutionTrigger> mTrigger, UserDetails userDetails)
			throws MetricServiceException {
		MetricExecutionTrigger metricTrigger =new MetricExecutionTrigger();
		if( null == metricTrigger.getId()
				||null == userDetails.getTenantId()){
			String params = "Required Data Missing";
			Object[] args = new Object[]{params};
			throw new  MetricServiceException(args);
		}
		try
		{
		metricSetupDAOService.configureMetricExecutionTrigger(mTrigger, userDetails);
	}catch(OpservDataAccessException e){
		Object[] args = new Object[]{"exception occured while configuring list of Metricexecution "};
	    throw new MetricServiceException(MetricServiceExceptionCode.MTR_SER_EX_CD_0015 ,"configure_metrics_exception",args,e);
	}
	}

	

	

}
