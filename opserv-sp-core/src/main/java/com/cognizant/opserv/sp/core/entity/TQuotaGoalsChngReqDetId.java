package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TQuotaGoalsChngReqDetId Pojo. 
 *
 */
@Embeddable
public class TQuotaGoalsChngReqDetId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "quota_chng_req_id", nullable = false, length = 255)
	private Long quotaChngReqId;
	@NotNull
	@Column(name = "config_id", nullable = false, length = 255)
	private Long confId;

	/**
	 *
	 * @generated
	 */
	public TQuotaGoalsChngReqDetId() {
	}



	/**
	 * 
	 * @generated
	 */
	public void setQuotaChngReqId(final Long quotaChngReqId) {
		this.quotaChngReqId = quotaChngReqId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getQuotaChngReqId() {
		return this.quotaChngReqId;
	}





	public Long getConfId() {
		return confId;
	}



	public void setConfId(Long confId) {
		this.confId = confId;
	}



	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TQuotaGoalsChngReqDetId)) {
			return false;
		}
		TQuotaGoalsChngReqDetId castOther = (TQuotaGoalsChngReqDetId) other;
		return (this.quotaChngReqId.equals(castOther.quotaChngReqId)) &&
           (this.confId.equals(castOther.confId))

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
		result = prime * result + ((quotaChngReqId == null) ? 0 : quotaChngReqId.hashCode());
		result = prime * result + ((confId == null) ? 0 : confId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("quotaChngReqId=[").append(quotaChngReqId).append("] ");
		buffer.append("confId=[").append(confId).append("] ");

		return buffer.toString();
	}
}
