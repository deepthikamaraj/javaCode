package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TBussRuleConfigId Pojo. 
 *
 */
@Embeddable
public class TBussRuleConfigId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "org_id", nullable = false, length = 255)
	private Integer orgId;
	@NotNull
	@Column(name = "buss_rule_config_id", nullable = false, length = 255)
	private Integer bussRuleConfigId;

	/**
	 *
	 * @generated
	 */
	public TBussRuleConfigId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setOrgId(final Integer orgId) {
		this.orgId = orgId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getOrgId() {
		return this.orgId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setBussRuleConfigId(final Integer bussRuleConfigId) {
		this.bussRuleConfigId = bussRuleConfigId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getBussRuleConfigId() {
		return this.bussRuleConfigId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TBussRuleConfigId)) {
			return false;
		}
		TBussRuleConfigId castOther = (TBussRuleConfigId) other;
		return (this.orgId.equals(castOther.orgId)) &&

		(this.bussRuleConfigId.equals(castOther.bussRuleConfigId))

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
		result = prime * result + ((orgId == null) ? 0 : orgId.hashCode());
		result = prime * result + ((bussRuleConfigId == null) ? 0 : bussRuleConfigId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("orgId=[").append(orgId).append("] ");
		buffer.append("bussRuleConfigId=[").append(bussRuleConfigId).append("] ");

		return buffer.toString();
	}
}
