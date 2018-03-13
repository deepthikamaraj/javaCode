package com.cognizant.opserv.sp.common;

/**
 * ****************************************************************************.
 *
 * @enum ProductPriorityType - values from table t_sales_pos_type
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum SalesPositionType {
	/**
	 * values from table - t_sales_pos_type
	 */
	BASE(1),OVERLAY(2);
	
	private Integer id;
	
	private SalesPositionType(Integer id ){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	
	/**
	 * Gets the sales position type.
	 *
	 * @param id the id
	 * @return the sales position type
	 */
	public static SalesPositionType getSalesPositionType(Integer id) {
		for (SalesPositionType salesPositionType : SalesPositionType.values()) {
			if (salesPositionType.getId().equals(id)) {
				return salesPositionType;
			}
		}
		return null;
	}
}

