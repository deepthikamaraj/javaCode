package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TNestChngReqId Pojo. 
 *
 */
@Embeddable
public class TNestChngReqId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "trigger_id", nullable = false, length = 255)
	private Integer triggerId;
	@NotNull
	@Column(name = "nst_trigger_id", nullable = false, length = 255)
	private Integer nstTriggerId;

	/**
	 *
	 * @generated
	 */
	public TNestChngReqId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setTriggerId(final Integer triggerId) {
		this.triggerId = triggerId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getTriggerId() {
		return this.triggerId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setNstTriggerId(final Integer nstTriggerId) {
		this.nstTriggerId = nstTriggerId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getNstTriggerId() {
		return this.nstTriggerId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TNestChngReqId)) {
			return false;
		}
		TNestChngReqId castOther = (TNestChngReqId) other;
		return (this.triggerId.equals(castOther.triggerId)) &&

		(this.nstTriggerId.equals(castOther.nstTriggerId))

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
		result = prime * result + ((triggerId == null) ? 0 : triggerId.hashCode());
		result = prime * result + ((nstTriggerId == null) ? 0 : nstTriggerId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("triggerId=[").append(triggerId).append("] ");
		buffer.append("nstTriggerId=[").append(nstTriggerId).append("] ");

		return buffer.toString();
	}
}
