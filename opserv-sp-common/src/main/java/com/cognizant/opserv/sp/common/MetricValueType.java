package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @enum MetricValueType - values from table t_mtr_value_type
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum MetricValueType {
	/**
	 * values from table -t_mtr_value_type
	 */
	INITIAL(1),
	CURRENT(2),
	PROPOSED(3);
	
private Integer id;
	
	private MetricValueType(Integer id){
		this.id =id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	/**
	 * Gets the metric value type.
	 *
	 * @param id the id
	 * @return the metric value type
	 */
	public static MetricValueType getMetricValueType(Integer id) {
		for (MetricValueType metricValueType : MetricValueType
				.values()) {
			if (metricValueType.getId().equals(id)) {
				return metricValueType;
			}
		}
		return null;
	}
}
