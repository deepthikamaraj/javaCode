package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TChngReqNotifyId Pojo. 
 *
 */
@Embeddable
public class TChngReqNotifyId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "chng_req_id", nullable = false, length = 255)
	private Long chngReqId;
	@NotNull
	@Column(name = "note_sales_hier_id", nullable = false, length = 255)
	private Long noteSalesHierId;
	@NotNull
	@Column(name = "note_sales_pos_id", nullable = false, length = 255)
	private Long noteSalesPosId;

	/**
	 *
	 * @generated
	 */
	public TChngReqNotifyId() {
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
	public void setNoteSalesHierId(final Long noteSalesHierId) {
		this.noteSalesHierId = noteSalesHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getNoteSalesHierId() {
		return this.noteSalesHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setNoteSalesPosId(final Long noteSalesPosId) {
		this.noteSalesPosId = noteSalesPosId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getNoteSalesPosId() {
		return this.noteSalesPosId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TChngReqNotifyId)) {
			return false;
		}
		TChngReqNotifyId castOther = (TChngReqNotifyId) other;
		return (this.chngReqId.equals(castOther.chngReqId)) &&

		(this.noteSalesHierId.equals(castOther.noteSalesHierId)) &&

		(this.noteSalesPosId.equals(castOther.noteSalesPosId))

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
		result = prime * result + ((noteSalesHierId == null) ? 0 : noteSalesHierId.hashCode());
		result = prime * result + ((noteSalesPosId == null) ? 0 : noteSalesPosId.hashCode());
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
		buffer.append("noteSalesHierId=[").append(noteSalesHierId).append("] ");
		buffer.append("noteSalesPosId=[").append(noteSalesPosId).append("] ");

		return buffer.toString();
	}
}
