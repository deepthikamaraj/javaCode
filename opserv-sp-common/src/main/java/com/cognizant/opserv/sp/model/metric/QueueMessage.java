package com.cognizant.opserv.sp.model.metric;

/******************************************************************************
 * 
 * @class QueueMessage provides message info.
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 10/29/2014
 * COPYRIGHT (C) 2014 Cognizant, all rights reserved.
 *****************************************************************************/

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/**
 * ****************************************************************************.
 *
 * @class QueueMessage
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class QueueMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id - message id
	 */
	private Integer id;

	/**
	 * map - map
	 */
	private Map<String, Object> map;

	/**
	 * @method getId - Gets the message id
	 * @param 
	 * @return id -  message id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @method setId - Sets the message id
	 * @param id - holds id info
	 * @return void
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @method getMap - Gets the map
	 * @param 
	 * @return map -  map
	 */
	public Map<String, Object> getMap() {
		return map;
	}

	/**
	 * @method setMap - Sets the map
	 * @param map - holds map info
	 * @return void
	 */
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	/**
	 * @method addInfo - adds the info
	 * @param key
	 * @param value
	 * @return void
	 */
	public void addInfo(String key,Object value){
		if(map == null){
			map = new HashMap<String,Object>();
		}

		if(key != null){
			map.put(key, value);
		}
	}

	/**
	 * @method getValue - adds the info
	 * @param key	 
	 * @return Object
	 */
	public Object getValue(String key){
		return map != null ? map.get(key) : null;
	}

	/**
	 * @method hashCode - returns hash code
	 * @param 	 
	 * @return int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * @method equals - returns object equals status
	 * @param obj - holds obj info	 
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!getClass().equals(obj.getClass()))
			return false;
		QueueMessage other = (QueueMessage) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * @method toString - returns object info
	 * @param 
	 * @return String
	 */
	@Override
	public String toString() {
		return "QueueMessage [id=" + id + ", map=" + map + "]";
	}



}
