package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TCvgRuleOptId Pojo. 
 *
 */
@Embeddable
public class TCvgRuleOptId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "order_id", nullable = false, length = 255)
	private Integer orderId;
	@NotNull
	@Column(name = "rule_id", nullable = false, length = 255)
	private Integer ruleId;

	/**
	 *
	 * @generated
	 */
	public TCvgRuleOptId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setOrderId(final Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getOrderId() {
		return this.orderId;
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
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TCvgRuleOptId)) {
			return false;
		}
		TCvgRuleOptId castOther = (TCvgRuleOptId) other;
		return (this.orderId.equals(castOther.orderId)) &&

		(this.ruleId.equals(castOther.ruleId))

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
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((ruleId == null) ? 0 : ruleId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("orderId=[").append(orderId).append("] ");
		buffer.append("ruleId=[").append(ruleId).append("] ");

		return buffer.toString();
	}
}
