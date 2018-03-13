package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * The primary key class for the TRoleWdgId Pojo.
 * 
 */
@Embeddable
public class TRoleWdgId implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "wdg_id", nullable = false, length = 255)
	private Integer wdgId;
	@NotNull
	@Column(name = "role_id", nullable = false, length = 255)
	private Integer roleId;

	/**
	 * 
	 * @generated
	 */
	public TRoleWdgId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setWdgId(final Integer wdgId) {
		this.wdgId = wdgId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getWdgId() {
		return this.wdgId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setRoleId(final Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getRoleId() {
		return this.roleId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TRoleWdgId)) {
			return false;
		}
		TRoleWdgId castOther = (TRoleWdgId) other;
				return this.wdgId.equals(castOther.wdgId) && this.roleId.equals(castOther.roleId);
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((wdgId == null) ? 0 : wdgId.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("wdgId=[").append(wdgId).append("] ");
		buffer.append("roleId=[").append(roleId).append("] ");

		return buffer.toString();
	}
}
