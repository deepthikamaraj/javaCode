package com.cognizant.opserv.sp.rest.ajax;


import java.io.Serializable;
import java.util.List;

/**
 * The Class ServiceResult.
 *
 * @param <T> the generic type
 * @class ServiceResult class defines the service result
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 27/11/2014
 */
public class ServiceResult<T>  implements Serializable{

	/** serialVersionUID. */
	private static final long serialVersionUID = 7170486379459444281L;
	
	/** The detail. */
	private T detail;
	
	/** The list. */
	private List<T> list;
	
	/**
	 * Gets the detail.
	 *
	 * @return the detail
	 * @Method getDetail - getters and setters.
	 */

	public T getDetail() {
		return detail;
	}

	/**
	 * Sets the detail.
	 *
	 * @param detail the new detail
	 * @Method setDetail - Sets the detail.
	 */
	public void setDetail(T detail) {
		this.detail = detail;
	}

	/**
	 * Gets the list.
	 *
	 * @return the list
	 * @Method getList - Gets the list.
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * Sets the list.
	 *
	 * @param list the new list
	 * @Method setList - Sets the list.
	 */
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
