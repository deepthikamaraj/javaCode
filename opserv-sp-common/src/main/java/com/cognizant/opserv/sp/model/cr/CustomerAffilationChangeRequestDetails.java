package com.cognizant.opserv.sp.model.cr;

import java.io.Serializable;

import com.cognizant.opserv.sp.model.CustomerAffiliation;
/**
 * ****************************************************************************.
 *
 * @class CustomerAffilationChangeRequestDetails
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class CustomerAffilationChangeRequestDetails 
									extends ChangeRequestLineItem 
									implements Serializable 
{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private CustomerAffiliation oldAffiliation;
	/**
	 * 
	 */
	private CustomerAffiliation newAffiliation;
	
	/**
	 * @return the oldAffiliation
	 */
	public CustomerAffiliation getOldAffiliation() {
		return oldAffiliation;
	}
	/**
	 * @param oldAffiliation the oldAffiliation to set
	 */
	public void setOldAffiliation(CustomerAffiliation oldAffiliation) {
		this.oldAffiliation = oldAffiliation;
	}
	/**
	 * @return the newAffiliation
	 */
	public CustomerAffiliation getNewAffiliation() {
		return newAffiliation;
	}
	/**
	 * @param newAffiliation the newAffiliation to set
	 */
	public void setNewAffiliation(CustomerAffiliation newAffiliation) {
		this.newAffiliation = newAffiliation;
	}

}
