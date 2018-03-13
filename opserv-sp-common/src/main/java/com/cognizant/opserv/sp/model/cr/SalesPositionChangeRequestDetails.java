package com.cognizant.opserv.sp.model.cr;

import java.io.Serializable;

import com.cognizant.opserv.sp.model.SalesPosition;
/**
 * ****************************************************************************.
 *
 * @class SalesPositionChangeRequestDetails
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class SalesPositionChangeRequestDetails 
										extends ChangeRequestLineItem 
										implements Serializable 
{
	

	/**
	 * default serial version id, required for serializable classes.
	 */
	private static final long serialVersionUID = -4631244694099027157L;
	
	/**
	 * 
	 */
	private SalesPosition oldSalesPositionObject;
	/**
	 * 
	 */
	private SalesPosition newSalesPositionObject;
	
	/**
	 * @return the oldSalesPositionObject
	 */
	public SalesPosition getOldSalesPositionObject() {
		return oldSalesPositionObject;
	}
	/**
	 * @param oldSalesPositionObject the oldSalesPositionObject to set
	 */
	public void setOldSalesPositionObject(SalesPosition oldSalesPositionObject) {
		this.oldSalesPositionObject = oldSalesPositionObject;
	}
	/**
	 * @return the newSalesPositionObject
	 */
	public SalesPosition getNewSalesPositionObject() {
		return newSalesPositionObject;
	}
	/**
	 * @param newSalesPositionObject the newSalesPositionObject to set
	 */
	public void setNewSalesPositionObject(SalesPosition newSalesPositionObject) {
		this.newSalesPositionObject = newSalesPositionObject;
	}

}
