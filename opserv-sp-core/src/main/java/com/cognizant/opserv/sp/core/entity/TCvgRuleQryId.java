package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TCvgRuleQryId Pojo. 
 *
 */
@Embeddable
public class TCvgRuleQryId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "rule_id", nullable = false, length = 255)
	private Integer ruleId;
	@NotNull
	@Column(name = "txn_id", nullable = false, length = 255)
	private Integer txnId;

	/**
	 *
	 * @generated
	 */
	public TCvgRuleQryId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setRuleId(final Integer ruleId) {
		this.ruleId = ruleId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getRuleId() {
		return this.ruleId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTxnId(final Integer txnId) {
		this.txnId = txnId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getTxnId() {
		return this.txnId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TCvgRuleQryId)) {
			return false;
		}
		TCvgRuleQryId castOther = (TCvgRuleQryId) other;
		return (this.ruleId.equals(castOther.ruleId)) &&

		(this.txnId.equals(castOther.txnId))

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
		result = prime * result + ((ruleId == null) ? 0 : ruleId.hashCode());
		result = prime * result + ((txnId == null) ? 0 : txnId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("ruleId=[").append(ruleId).append("] ");
		buffer.append("txnId=[").append(txnId).append("] ");

		return buffer.toString();
	}
}
