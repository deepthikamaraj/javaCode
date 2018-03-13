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
public enum MetricCategoryType {

	CAPACITY_UTILIZATION(1, "CU"), POWER_UTILIZATION(2, "PU");

	private String code;
	private Integer id;

	private MetricCategoryType(Integer id, String code) {
		this.code = code;
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public Integer getId() {
		return this.id;
	}
	
	/**
	 * Gets the metric category type.
	 *
	 * @param id the id
	 * @return the metric category type
	 */
	public static MetricCategoryType getMetricCategoryType(Integer id) {
		for (MetricCategoryType metricCategoryType : MetricCategoryType
				.values()) {
			if (metricCategoryType.getId() .equals(id)) {
				return metricCategoryType;
			}
		}
		return null;
	}

}
