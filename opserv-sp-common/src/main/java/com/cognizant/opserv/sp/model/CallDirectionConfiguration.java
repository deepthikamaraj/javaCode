package com.cognizant.opserv.sp.model;

import java.io.Serializable;
/**
 * ****************************************************************************.
 *
 * @class CallDirectionConfiguration
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class CallDirectionConfiguration extends BaseModel implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4986778732909033884L;

	/** product count **/
	private int productCount;

	/** call plan configuration information **//*
	private CallPlanConfiguration callPlanConfig;
*/
	/**
	 * @return the productCount
	 */
	public int getProductCount() {
		return productCount;
	}

	/**
	 * @param productCount
	 *            the productCount to set
	 */
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	/**
	 * @return the callPlanConfig
	 *//*
	public CallPlanConfiguration getCallPlanConfig() {
		return callPlanConfig;
	}

	*//**
	 * @param callPlanConfig
	 *            the callPlanConfig to set
	 *//*
	public void setCallPlanConfig(CallPlanConfiguration callPlanConfig) {
		this.callPlanConfig = callPlanConfig;
	}*/

}
