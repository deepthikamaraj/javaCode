package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @enum MetricUnitType - values from table t_mtr_uom
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum MetricUnitType {
	/**
	 * values from table - t_mtr_uom
	 */
	PERCENTAGE(1),
	UNIT(2),
	CURRENCY(3);
	
private Integer id;
	
	private MetricUnitType(Integer id){
		this.id =id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	/**
	 * Gets the metric uom
	 *
	 * @param id the id
	 * @return the metric uom
	 */
	public static MetricUnitType getMetricUnitType(Integer id) {
		for (MetricUnitType metricUnitType : MetricUnitType.values()) {
			if (metricUnitType.getId().equals(id)) {
				return metricUnitType;
			}
		}
		return null;
	}
}
