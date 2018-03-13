package com.cognizant.opserv.sp.model;

import java.io.Serializable;
import java.util.List;
/**
 * ****************************************************************************.
 *
 * @class ViewHeader
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class ViewHeader implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4872645292346695253L;

	/** fields - contains field level meta information. */
	List<FieldMetaData> fields;

	/**
	 * Gets the fields.
	 *
	 * @return the fields
	 */
	public List<FieldMetaData> getFields() {
		return fields;
	}

	/**
	 * Sets the fields.
	 *
	 * @param fields the fields to set
	 */
	public void setFields(List<FieldMetaData> fields) {
		this.fields = fields;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HeaderData [fields=" + fields + "]";
	}

}
