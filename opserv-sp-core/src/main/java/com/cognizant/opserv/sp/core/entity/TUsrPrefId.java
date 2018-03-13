package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TUsrPrefId Pojo. 
 *
 */
@Embeddable
public class TUsrPrefId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "feature_id", nullable = false, length = 255)
	private Integer featureId;
	@NotNull
	@Column(name = "usr_id", nullable = false, length = 255)
	private Integer usrId;
	@NotNull
	@Column(name = "role_id", nullable = false, length = 255)
	private Integer roleId;

	/**
	 *
	 * @generated
	 */
	public TUsrPrefId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setFeatureId(final Integer featureId) {
		this.featureId = featureId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getFeatureId() {
		return this.featureId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setUsrId(final Integer usrId) {
		this.usrId = usrId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getUsrId() {
		return this.usrId;
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
		if (!(other instanceof TUsrPrefId)) {
			return false;
		}
		TUsrPrefId castOther = (TUsrPrefId) other;
		return (this.featureId.equals(castOther.featureId)) &&

		(this.usrId.equals(castOther.usrId)) &&

		(this.roleId.equals(castOther.roleId))

		;

	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((featureId == null) ? 0 : featureId.hashCode());
		result = prime * result + ((usrId == null) ? 0 : usrId.hashCode());
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

		buffer.append("featureId=[").append(featureId).append("] ");
		buffer.append("usrId=[").append(usrId).append("] ");
		buffer.append("roleId=[").append(roleId).append("] ");

		return buffer.toString();
	}
}
