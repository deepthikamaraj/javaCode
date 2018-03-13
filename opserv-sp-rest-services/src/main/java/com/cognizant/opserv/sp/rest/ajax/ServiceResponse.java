package com.cognizant.opserv.sp.rest.ajax;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class ServiceResponse.
 *
 * @param <T> the generic type
 * @class ServiceResponse class defines the service response
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 27/11/2014
 */
public class ServiceResponse<T> implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = -3055772896169262131L;

	/** The status. */
	private ServiceStatus status;

	/** The result. */
	private ServiceResult<T> result;

	/**
	 * Gets the status.
	 *
	 * @return the status
	 * @Method getStatus - getters and setters.
	 */
	
	@JsonProperty("status_text")
	public ServiceStatus getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 * @Method setStatus - Sets the status.
	 */
	public void setStatus(ServiceStatus status) {
		this.status = status;
	}

	/**
	 * Gets the result.
	 *
	 * @return the result
	 * @Method getResult - Gets the result.
	 */
	public ServiceResult<T> getResult() {
		return result;
	}

	/**
	 * Sets the result.
	 *
	 * @param result the new result
	 * @Method setResult - Sets the result.
	 */
	public void setResult(ServiceResult<T> result) {
		this.result = result;
	}

}
