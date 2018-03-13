package com.cognizant.opserv.sp.service.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.opserv.sp.exception.MetricServiceException;
import com.cognizant.opserv.sp.exception.MetricServiceException.MetricServiceExceptionCode;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.metric.RangeDetails;
import com.cognizant.opserv.sp.persistence.dao.service.MetricResultDAOService;
import com.cognizant.peg.core.exception.OpservDataAccessException;

@Service("metricIntService")
public class MetricIntserviceImpl implements MetricIntService{
	
	@Autowired
	MetricResultDAOService metricResultDAOService;
	
	/**
	 * Gets the range details by metric.
	 *
	 * @param mtrId the mtr id
	 * @param userDetails the user details
	 * @return the range details by metric
	 * @throws MetricServiceException the metric service exception
	 */
	@Override
	public List<RangeDetails> getRangeDetailsForSalesPosMetrics(long mtrId,
			UserDetails userDetails) throws MetricServiceException {
		try{
			return metricResultDAOService.getRangeDetailsForSalesPosMetrics(mtrId, userDetails);
		}
		catch(OpservDataAccessException e){
			throw new MetricServiceException(MetricServiceExceptionCode.MTR_SER_EX_CD_001, "exception", null, e);
		}
	}

	/**
	 * Gets the range details for geo metrics.
	 *
	 * @param mtrId the mtr id
	 * @param userDetails the user details
	 * @return the range details for geo metrics
	 * @throws MetricServiceException the metric service exception
	 */
	@Override
	public List<RangeDetails> getRangeDetailsForGeoMetrics(Long mtrId,
			UserDetails userDetails) throws MetricServiceException {
		try{
			return metricResultDAOService.getRangeDetailsForGeoMetrics(mtrId, userDetails);
		}
		catch(OpservDataAccessException e){
			throw new MetricServiceException(MetricServiceExceptionCode.MTR_SER_EX_CD_001, "exception", null, e);
		}
	}

}
