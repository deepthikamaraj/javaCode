package com.cognizant.opserv.sp.model;

import java.io.Serializable;

import com.cognizant.opserv.sp.common.BusinessFunctionType;

public class BusinessObject extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6907083883815842298L;
	
	BusinessFunctionType type;

	public BusinessFunctionType getType() {
		return type;
	}

	public void setType(BusinessFunctionType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "BusinessObject [type=" + type + ", getId()=" + getId()
				+ ", getName()=" + getName() + "]";
	}

}
