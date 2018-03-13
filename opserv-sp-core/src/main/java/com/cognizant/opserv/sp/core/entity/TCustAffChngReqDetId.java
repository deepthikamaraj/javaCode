package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TCustAffChngReqDetId Pojo. 
 *
 */
@Embeddable
public class TCustAffChngReqDetId implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "chng_req_id", nullable = false, length = 255)
	private Long chngReqId;
	@NotNull
	@Column(name = "cust_aff_id", nullable = false, length = 255)
	private Integer custAffId;

	/**
	 *
	 * @generated
	 */
	public TCustAffChngReqDetId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setChngReqId(final Long chngReqId) {
		this.chngReqId = chngReqId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getChngReqId() {
		return this.chngReqId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setCustAffId(final Integer custAffId) {
		this.custAffId = custAffId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCustAffId() {
		return this.custAffId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TCustAffChngReqDetId)) {
			return false;
		}
		TCustAffChngReqDetId castOther = (TCustAffChngReqDetId) other;
		return (this.chngReqId.equals(castOther.chngReqId)) &&

		(this.custAffId.equals(castOther.custAffId))

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
		result = prime * result + ((chngReqId == null) ? 0 : chngReqId.hashCode());
		result = prime * result + ((custAffId == null) ? 0 : custAffId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("chngReqId=[").append(chngReqId).append("] ");
		buffer.append("custAffId=[").append(custAffId).append("] ");

		return buffer.toString();
	}
}
