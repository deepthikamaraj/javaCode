package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/** 
 * The primary key class for the TValMsgId Pojo. 
 *
 */
@Embeddable
public class TValMsgId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "rule_id", nullable = false, length = 255)
	private Integer ruleId;
//	@ManyToOne
//	@JoinColumn(name = "rule_id", referencedColumnName = "rule_id", nullable = false, insertable = true, updatable = true)
//	@Valid
	//private TAttrRule tAttrRule;
	@NotEmpty
	@Column(name = "locale_id", nullable = false, length = 20)
	private String localeId;

	/**
	 *
	 * @generated
	 */
	public TValMsgId() {
	}

	/**
	 * 
	 * @generated
	 */
	/*public void setTAttrRule(final TAttrRule tAttrRule) {
		this.tAttrRule = tAttrRule;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public TAttrRule getTAttrRule() {
		return this.tAttrRule;
	}*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((localeId == null) ? 0 : localeId.hashCode());
		result = prime * result + ((ruleId == null) ? 0 : ruleId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TValMsgId other = (TValMsgId) obj;
		if (localeId == null) {
			if (other.localeId != null)
				return false;
		} else if (!localeId.equals(other.localeId))
			return false;
		if (ruleId == null) {
			if (other.ruleId != null)
				return false;
		} else if (!ruleId.equals(other.ruleId))
			return false;
		return true;
	}

	/**
	 * 
	 * @generated
	 */
	public void setLocaleId(final String localeId) {
		this.localeId = localeId;
	}

	/**
	 * 
	 * @generated
	 */
	public String getLocaleId() {
		return this.localeId;
	}

	public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	/**
	 * 
	 * @generated
	 */
	/*public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TValMsgId)) {
			return false;
		}
		TValMsgId castOther = (TValMsgId) other;
		return (this.tAttrRule.equals(castOther.tAttrRule)) &&

		(this.localeId.equals(castOther.localeId))

		;

	}

	*//**
	 * @generated
	 * 
	 *//*
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tAttrRule == null) ? 0 : tAttrRule.hashCode());
		result = prime * result + ((localeId == null) ? 0 : localeId.hashCode());
		return result;
	}
*/
	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("ruleId=[").append(ruleId).append("] ");
		buffer.append("localeId=[").append(localeId).append("] ");

		return buffer.toString();
	}
}
