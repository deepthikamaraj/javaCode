package com.cognizant.opserv.sp.model;

import java.io.Serializable;
import java.util.List;

import com.cognizant.opserv.sp.common.EntityType;
import com.cognizant.opserv.sp.model.attrb.AttributeGroup;
/**
 * ****************************************************************************.
 *
 * @class EntityTemplate
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class EntityTemplate extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8440742996389069595L;

	private String description;

	private boolean isDefault;

	private List<AttributeGroup> groups;

	private EntityType entity;

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the isDefault
	 */
	public boolean isDefault() {
		return isDefault;
	}

	/**
	 * @param isDefault
	 *            the isDefault to set
	 */
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	/**
	 * @return the groups
	 */
	public List<AttributeGroup> getGroups() {
		return groups;
	}

	/**
	 * @param groups
	 *            the groups to set
	 */
	public void setGroups(List<AttributeGroup> groups) {
		this.groups = groups;
	}

	/**
	 * @return the entity
	 */
	public EntityType getEntity() {
		return entity;
	}

	/**
	 * @param entity
	 *            the entity to set
	 */
	public void setEntity(EntityType entity) {
		this.entity = entity;
	}

}
