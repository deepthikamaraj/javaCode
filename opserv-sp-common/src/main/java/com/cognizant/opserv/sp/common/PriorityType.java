package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @enum PriorityType - values from table t_prt_type
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum PriorityType {
	/**
	 * values from table -t_prt_type
	 */
	TIER_1(1),
	TIER_2(2),
	TIER_3(3),
	TIER_4(4);
	
	private Integer id;
	
	private PriorityType(Integer id){
		this.id =id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	/**
	 * Gets the priority type.
	 *
	 * @param id the id
	 * @return the priority type
	 */
	public static PriorityType getPriorityType(Integer id) {
		for (PriorityType priorityType : PriorityType.values()) {
			if (priorityType.getId().equals(id)) {
				return priorityType;
			}
		}
		return null;
	}
}
