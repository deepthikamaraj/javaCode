package com.cognizant.opserv.sp.model.metric;

import java.io.Serializable;
import java.util.Date;

import com.cognizant.opserv.sp.common.ExecutionStatusType;
import com.cognizant.opserv.sp.model.BaseModel;
import com.cognizant.opserv.sp.model.SalesPosition;
/**
 * ****************************************************************************.
 *
 * @class MetricExecution
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class MetricExecution extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5093510210093876211L;

	private SalesPosition salesPosition;

	private Date executedOn;

	private ExecutionStatusType status;

	private MetricResult metricResult;

	/**
	 * @return the salesPosition
	 */
	public SalesPosition getSalesPosition() {
		return salesPosition;
	}

	/**
	 * @param salesPosition
	 *            the salesPosition to set
	 */
	public void setSalesPosition(SalesPosition salesPosition) {
		this.salesPosition = salesPosition;
	}

	/**
	 * @return the executedOn
	 */
	public Date getExecutedOn() {
		return executedOn;
	}

	/**
	 * @param executedOn
	 *            the executedOn to set
	 */
	public void setExecutedOn(Date executedOn) {
		this.executedOn = executedOn;
	}

	/**
	 * @return the status
	 */
	public ExecutionStatusType getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(ExecutionStatusType status) {
		this.status = status;
	}

	/**
	 * @return the metricResult
	 */
	public MetricResult getMetricResult() {
		return metricResult;
	}

	/**
	 * @param metricResult
	 *            the metricResult to set
	 */
	public void setMetricResult(MetricResult metricResult) {
		this.metricResult = metricResult;
	}

}
