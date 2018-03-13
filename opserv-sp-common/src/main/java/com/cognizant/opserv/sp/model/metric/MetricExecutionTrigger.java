package com.cognizant.opserv.sp.model.metric;

import java.io.Serializable;
import java.util.List;

import com.cognizant.opserv.sp.common.MetricTriggerType;
import com.cognizant.opserv.sp.model.BaseModel;
/**
 * ****************************************************************************.
 *
 * @class MetricExecutionTrigger
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class MetricExecutionTrigger extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3361070915497207545L;

	private MetricConfig metricConfig;

	private MetricTriggerType triggerType;

	private List<MetricExecution> metricExecutions;

	/**
	 * @return the metricConfig
	 */
	public MetricConfig getMetricConfig() {
		return metricConfig;
	}

	/**
	 * @param metricConfig
	 *            the metricConfig to set
	 */
	public void setMetricConfig(MetricConfig metricConfig) {
		this.metricConfig = metricConfig;
	}

	/**
	 * @return the triggerType
	 */
	public MetricTriggerType getTriggerType() {
		return triggerType;
	}

	/**
	 * @param triggerType
	 *            the triggerType to set
	 */
	public void setTriggerType(MetricTriggerType triggerType) {
		this.triggerType = triggerType;
	}

	/**
	 * @return the metricExecutions
	 */
	public List<MetricExecution> getMetricExecutions() {
		return metricExecutions;
	}

	/**
	 * @param metricExecutions
	 *            the metricExecutions to set
	 */
	public void setMetricExecutions(List<MetricExecution> metricExecutions) {
		this.metricExecutions = metricExecutions;
	}

}
