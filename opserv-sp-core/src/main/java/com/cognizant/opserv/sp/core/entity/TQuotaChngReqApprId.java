package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TQuotaChngReqApprId Pojo. 
 *
 */
@Embeddable
public class TQuotaChngReqApprId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "quota_chng_req_id", nullable = false, length = 255)
	private Long quotaChngReqId;
	@NotNull
	@Column(name = "appr_id", nullable = false, length = 255)
	private Integer apprId;

	/**
	 *
	 * @generated
	 */
	public TQuotaChngReqApprId() {
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

	/**
	 * 
	 * @generated
	 */
	public void setApprId(final Integer apprId) {
		this.apprId = apprId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getApprId() {
		return this.apprId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TQuotaChngReqApprId)) {
			return false;
		}
		TQuotaChngReqApprId castOther = (TQuotaChngReqApprId) other;
		return (this.quotaChngReqId.equals(castOther.quotaChngReqId)) &&

		(this.apprId.equals(castOther.apprId))

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
		result = prime * result + ((apprId == null) ? 0 : apprId.hashCode());
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
		buffer.append("apprId=[").append(apprId).append("] ");

		return buffer.toString();
	}
}
