package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TCustChngReqDetId Pojo. 
 *
 */
@Embeddable
public class TCustChngReqDetId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "cust_id", nullable = false, length = 255)
	private Integer custId;
	@NotNull
	@Column(name = "chng_req_id", nullable = false, length = 255)
	private Long chngReqId;

	/**
	 *
	 * @generated
	 */
	public TCustChngReqDetId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setCustId(final Integer custId) {
		this.custId = custId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCustId() {
		return this.custId;
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
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TCustChngReqDetId)) {
			return false;
		}
		TCustChngReqDetId castOther = (TCustChngReqDetId) other;
		return (this.custId.equals(castOther.custId)) &&

		(this.chngReqId.equals(castOther.chngReqId))

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
		result = prime * result + ((custId == null) ? 0 : custId.hashCode());
		result = prime * result + ((chngReqId == null) ? 0 : chngReqId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("custId=[").append(custId).append("] ");
		buffer.append("chngReqId=[").append(chngReqId).append("] ");

		return buffer.toString();
	}
}
