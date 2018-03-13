package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @enum ChangeRequestTriggerType holds data from t_mtr_trigger
 * value[feature_id]_value[type_id]
 * `t_mtr_feature`_`t_feature_type`
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum MetricTriggerType {

	/**
	 * values from table -t_mtr_trigger
	 */
	ASSIGN_CUSTOMER(1),
	UNASSIGN_CUSTOMER(3),
	EDIT_ZIP(14),
	ASSIGN_ZIP(15),
	UNASSIGN_ZIP(16),
	EDIT_CUSTOMER(9);
	
private Integer id;
	
	private MetricTriggerType(Integer id){
		this.id =id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	/**
	 * Gets the metric trigger type.
	 *
	 * @param id the id
	 * @return the metric trigger type
	 */
	public static MetricTriggerType getMetricTriggerType(Integer id) {
		for (MetricTriggerType metricTriggerType : MetricTriggerType
				.values()) {
			if (metricTriggerType.getId().equals(id)) {
				return metricTriggerType;
			}
		}
		return null;
	}


}
