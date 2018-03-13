package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @enum CustomerCategoryType holds data from t_cust_category
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum CustomerCategoryType {
	/**
	 * values from table - t_cust_category
	 */
	ACCOUNT(1), PRESCRIBER(2);
	
	private Integer id;
	
	private CustomerCategoryType(Integer id) {
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	/**
	 * Gets the customer category type.
	 *
	 * @param id the id
	 * @return the customer category type
	 */
	public static CustomerCategoryType getCustomerCategoryType(Integer id) {
		for (CustomerCategoryType customerCategoryType : CustomerCategoryType
				.values()) {
			if (customerCategoryType.getId().equals(id)) {
				return customerCategoryType;
			}
		}
		return null;
	}
}
