package com.cognizant.opserv.sp.model.metric;

import java.io.Serializable;
import java.util.Date;

import com.cognizant.opserv.sp.model.BaseModel;
import com.cognizant.opserv.sp.model.SalesPosition;
/**
 * ****************************************************************************.
 *
 * @class MetricResult
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class MetricResult extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3240292974416179503L;

	private SalesPosition salesPosition;

	private Date baseExecutedDate;
	
	private Date proposedExecutedDate;
	
	private Date actualExecutedDate;

	private float baseValue;
	
	private float proposedValue;
	
	private float actualValue;
	
	private Metric metric;

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
	 * @return the baseExecutedDate
	 */
	public Date getBaseExecutedDate() {
		return baseExecutedDate;
	}

	/**
	 * @param baseExecutedDate
	 *            the baseExecutedDate to set
	 */
	public void setBaseExecutedDate(Date baseExecutedDate) {
		this.baseExecutedDate = baseExecutedDate;
	}

	/**
	 * @return the proposedExecutedDate
	 */
	public Date getProposedExecutedDate() {
		return proposedExecutedDate;
	}
	
	/**
	 * @param proposedExecutedDate
	 *            the proposedExecutedDate to set
	 */
	public void setProposedExecutedDate(Date proposedExecutedDate) {
		this.proposedExecutedDate = proposedExecutedDate;
	}

	/**
	 * @return the actualExecutedDate
	 */
	public Date getActualExecutedDate() {
		return actualExecutedDate;
	}

	/**
	 * @param actualExecutedDate
	 *            the actualExecutedDate to set
	 */
	public void setActualExecutedDate(Date actualExecutedDate) {
		this.actualExecutedDate = actualExecutedDate;
	}

	/**
	 * @return the baseValue
	 */
	public float getBaseValue() {
		return baseValue;
	}

	/**
	 * @param baseValue
	 *            the baseValue to set
	 */
	public void setBaseValue(float baseValue) {
		this.baseValue = baseValue;
	}

	/**
	 * @return the proposedValue
	 */
	public float getProposedValue() {
		return proposedValue;
	}

	/**
	 * @param proposedValue
	 *            the proposedValue to set
	 */
	public void setProposedValue(float proposedValue) {
		this.proposedValue = proposedValue;
	}

	/**
	 * @return the actualValue
	 */
	public float getActualValue() {
		return actualValue;
	}

	/**
	 * @param actualValue
	 *            the actualValue to set
	 */
	public void setActualValue(float actualValue) {
		this.actualValue = actualValue;
	}

	/**
	 * @return the metric
	 */
	public Metric getMetric() {
		return metric;
	}

	/**
	 * @param metric the metric to set
	 */
	public void setMetric(Metric metric) {
		this.metric = metric;
	}	

}
