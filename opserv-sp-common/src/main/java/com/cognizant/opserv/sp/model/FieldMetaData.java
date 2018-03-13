package com.cognizant.opserv.sp.model;

import java.io.Serializable;

import com.cognizant.opserv.sp.common.AttributeType;
/**
 * ****************************************************************************.
 *
 * @class FieldMetaData
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class FieldMetaData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4239223769005718353L;

	private String name;

	private String displayName;

	private AttributeType type;

	private int order;

	private boolean isSortable;

	private boolean isSearchable;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName
	 *            the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the type
	 */
	public AttributeType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(AttributeType type) {
		this.type = type;
	}

	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * @param order
	 *            the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	/**
	 * @return the isSortable
	 */
	public boolean isSortable() {
		return isSortable;
	}

	/**
	 * @param isSortable
	 *            the isSortable to set
	 */
	public void setSortable(boolean isSortable) {
		this.isSortable = isSortable;
	}

	/**
	 * @return the isSearchable
	 */
	public boolean isSearchable() {
		return isSearchable;
	}

	/**
	 * @param isSearchable
	 *            the isSearchable to set
	 */
	public void setSearchable(boolean isSearchable) {
		this.isSearchable = isSearchable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FieldMetaData [name=" + name + ", displayName=" + displayName
				+ ", type=" + type + ", order=" + order + ", isSortable="
				+ isSortable + "]";
	}

}
