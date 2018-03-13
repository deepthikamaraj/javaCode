package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @enum MetricCategoryType 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum MetricExpressionType {

	SIMPLE(1, 'S'), COMPLEX(2, 'C');

	private char value;

	private Integer id;

	private MetricExpressionType(Integer id, char value) {
		this.value = value;
		this.id = id;
	}

	public char getValue() {
		return value;
	}

	public Integer getId() {
		return this.id;
	}
	
	/**
	 * Gets the metric expression type.
	 *
	 * @param id the id
	 * @return the metric expression type
	 */
	public static MetricExpressionType getMetricExpressionType(Integer id) {
		for (MetricExpressionType metricExpressionType : MetricExpressionType
				.values()) {
			if (metricExpressionType.getId() .equals(id)) {
				return metricExpressionType;
			}
		}
		return null;
	}

}
