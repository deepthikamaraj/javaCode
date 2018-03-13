package com.cognizant.opserv.sp.model.attrb;

import java.io.Serializable;
import java.util.List;

import com.cognizant.opserv.sp.model.BaseModel;
/**
 * ****************************************************************************.
 *
 * @class AttributeGroup
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class AttributeGroup extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8233190293107020230L;
	
	private List<AttributeDefinition> attrDefinitions;
	
	private boolean isDefault;
	
	private int sequence;
	

	/**
	 * @return the attrDefinitions
	 */
	public List<AttributeDefinition> getAttrDefinitions() {
		return attrDefinitions;
	}

	/**
	 * @param attrDefinitions
	 *            the attrDefinitions to set
	 */
	public void setAttrDefinitions(List<AttributeDefinition> attrDefinitions) {
		this.attrDefinitions = attrDefinitions;
	}

	/**
	 * @return the isDefault
	 */
	public boolean isDefault() {
		return isDefault;
	}

	/**
	 * @param isDefault the isDefault to set
	 */
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	/**
	 * @return the sequence
	 */
	public int getSequence() {
		return sequence;
	}

	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}	

}
