package com.cognizant.opserv.sp.model.metric;

import java.io.Serializable;

import com.cognizant.opserv.sp.model.BaseModel;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
/**
 * ****************************************************************************.
 *
 * @class MetricConfig
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class MetricConfig extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7346210208394948679L;
	
	private Metric metric;

	private boolean isRollupActive;

	private SalesOrgHierarchy hierarchy;

	/**
	 * @return the metric
	 */
	public Metric getMetric() {
		return metric;
	}

	/**
	 * @param metric
	 *            the metric to set
	 */
	public void setMetric(Metric metric) {
		this.metric = metric;
	}

	/**
	 * @return the isRollupActive
	 */
	public boolean isRollupActive() {
		return isRollupActive;
	}

	/**
	 * @param isRollupActive
	 *            the isRollupActive to set
	 */
	public void setRollupActive(boolean isRollupActive) {
		this.isRollupActive = isRollupActive;
	}

	/**
	 * @return the hierarchy
	 */
	public SalesOrgHierarchy getHierarchy() {
		return hierarchy;
	}

	/**
	 * @param hierarchy
	 *            the hierarchy to set
	 */
	public void setHierarchy(SalesOrgHierarchy hierarchy) {
		this.hierarchy = hierarchy;
	}

}
