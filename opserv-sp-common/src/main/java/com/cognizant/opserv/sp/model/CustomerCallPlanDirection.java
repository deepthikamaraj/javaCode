package com.cognizant.opserv.sp.model;

import java.io.Serializable;
/**
 * ****************************************************************************.
 *
 * @class CustomerCallPlanDirection
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class CustomerCallPlanDirection extends BaseModel implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3401358750934159591L;

	/** call direction configuration **/
	private CallDirectionConfiguration config;

	/** number of calls **/
	private int numberOfCalls;

	/**
	 * @return the config
	 */
	public CallDirectionConfiguration getConfig() {
		return config;
	}

	/**
	 * @param config
	 *            the config to set
	 */
	public void setConfig(CallDirectionConfiguration config) {
		this.config = config;
	}

	/**
	 * @return the numberOfCalls
	 */
	public int getNumberOfCalls() {
		return numberOfCalls;
	}

	/**
	 * @param numberOfCalls
	 *            the numberOfCalls to set
	 */
	public void setNumberOfCalls(int numberOfCalls) {
		this.numberOfCalls = numberOfCalls;
	}

}
