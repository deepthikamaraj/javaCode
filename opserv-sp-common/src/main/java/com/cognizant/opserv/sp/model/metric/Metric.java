package com.cognizant.opserv.sp.model.metric;

import java.io.Serializable;

import com.cognizant.opserv.sp.common.MeasureType;
import com.cognizant.opserv.sp.common.MetricCategoryType;
import com.cognizant.opserv.sp.common.MetricUnitType;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BaseModel;
/**
 * ****************************************************************************.
 *
 * @class Metric
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class Metric extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6825017957462535623L;

	private float minValue;

	private float maxValue;

	private boolean isEnforced;

	private boolean isDataDotActive;

	private boolean isVisible;

	private int precision;

	private boolean isCompActive;

	private Alignment alignment;

	private MetricCategoryType category;

	private MeasureType measureType;
	
	private MetricUnitType metricUnitType;
	
	private RangeDetails rangeDetails;
	
	public RangeDetails getRangeDetails() {
		return rangeDetails;
	}

	public void setRangeDetails(RangeDetails rangeDetails) {
		this.rangeDetails = rangeDetails;
	}

	/**
	 * message - metric message
	 */
	private String valmessage;

	/**
	 * @return the minValue
	 */
	public float getMinValue() {
		return minValue;
	}

	/**
	 * @param minValue
	 *            the minValue to set
	 */
	public void setMinValue(float minValue) {
		this.minValue = minValue;
	}

	/**
	 * @return the maxValue
	 */
	public float getMaxValue() {
		return maxValue;
	}

	/**
	 * @param maxValue
	 *            the maxValue to set
	 */
	public void setMaxValue(float maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * @return the isEnforced
	 */
	public boolean isEnforced() {
		return isEnforced;
	}

	/**
	 * @param isEnforced
	 *            the isEnforced to set
	 */
	public void setEnforced(boolean isEnforced) {
		this.isEnforced = isEnforced;
	}

	/**
	 * @return the isDataDotActive
	 */
	public boolean isDataDotActive() {
		return isDataDotActive;
	}

	/**
	 * @param isDataDotActive
	 *            the isDataDotActive to set
	 */
	public void setDataDotActive(boolean isDataDotActive) {
		this.isDataDotActive = isDataDotActive;
	}

	/**
	 * @return the isVisible
	 */
	public boolean isVisible() {
		return isVisible;
	}

	/**
	 * @param isVisible
	 *            the isVisible to set
	 */
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	/**
	 * @return the precision
	 */
	public int getPrecision() {
		return precision;
	}

	/**
	 * @param precision
	 *            the precision to set
	 */
	public void setPrecision(int precision) {
		this.precision = precision;
	}

	/**
	 * @return the isCompActive
	 */
	public boolean isCompActive() {
		return isCompActive;
	}

	/**
	 * @param isCompActive
	 *            the isCompActive to set
	 */
	public void setCompActive(boolean isCompActive) {
		this.isCompActive = isCompActive;
	}

	/**
	 * @return the alignment
	 */
	public Alignment getAlignment() {
		return alignment;
	}

	/**
	 * @param alignment
	 *            the alignment to set
	 */
	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}

	/**
	 * @return the category
	 */
	public MetricCategoryType getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(MetricCategoryType category) {
		this.category = category;
	}

	/**
	 * @return the measureType
	 */
	public MeasureType getMeasureType() {
		return measureType;
	}

	/**
	 * @param measureType
	 *            the measureType to set
	 */
	public void setMeasureType(MeasureType measureType) {
		this.measureType = measureType;
	}
	
	/**
	 * @return the valmessage
	 */
	public String getValmessage() {
		return valmessage;
	}

	/**
	 * @param valmessage the valmessage to set
	 */
	public void setValmessage(String valmessage) {
		this.valmessage = valmessage;
	}

	/**
	 * @return the metricUnitType
	 */
	public MetricUnitType getMetricUnitType() {
		return metricUnitType;
	}

	/**
	 * @param metricUnitType the metricUnitType to set
	 */
	public void setMetricUnitType(MetricUnitType metricUnitType) {
		this.metricUnitType = metricUnitType;
	}


}
