package com.cognizant.opserv.sp.model.communication;

import java.util.List;


/**
 * ****************************************************************************.
 *
 * @class RecipientsSearchParam
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 07/07/2016
 * ***************************************************************************
 */
	public class RecipientsSearchParam {
	
	/** The search name. */
	private String searchName;
	
	/** The search desc. */
	private String searchDesc;

	/** The pub type id. */
	private Integer pubTypeId;
	
	private List<RecipientsAttribute> recipientsAttributeList;
	
	/**
	 * Gets the search name.
	 *
	 * @return the search name
	 */
	public String getSearchName() {
		return searchName;
	}

	/**
	 * Sets the search name.
	 *
	 * @param searchName the new search name
	 */
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	/**
	 * Gets the search desc.
	 *
	 * @return the search desc
	 */
	public String getSearchDesc() {
		return searchDesc;
	}

	/**
	 * Sets the search desc.
	 *
	 * @param searchDesc the new search desc
	 */
	public void setSearchDesc(String searchDesc) {
		this.searchDesc = searchDesc;
	}

	/**
	 * Gets the pub type id.
	 *
	 * @return the pub type id
	 */
	public Integer getPubTypeId() {
		return pubTypeId;
	}

	/**
	 * Sets the pub type id.
	 *
	 * @param pubTypeId the new pub type id
	 */
	public void setPubTypeId(Integer pubTypeId) {
		this.pubTypeId = pubTypeId;
	}
	
	/**
	 * Gets the recipientsAttributeList
	 *
	 * @return the recipientsAttributeList
	 */
	public List<RecipientsAttribute> getRecipientsAttributeList() {
		return recipientsAttributeList;
	}

	/**
	 * Sets the recipientsAttributeList
	 *
	 * @param recipientsAttributeList
	 */
	public void setRecipientsAttributeList(List<RecipientsAttribute> recipientsAttributeList) {
		this.recipientsAttributeList = recipientsAttributeList;
	}
}
