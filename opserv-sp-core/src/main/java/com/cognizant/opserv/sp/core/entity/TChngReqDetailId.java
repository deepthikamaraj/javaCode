package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TChngReqDetailId Pojo. 
 *
 */
@Embeddable
public class TChngReqDetailId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/*@NotNull
	@Column(name = "chng_req_id", nullable = false, length = 255)
	private Long chngReqId;*/
	
	@ManyToOne
	@JoinColumn(name = "chng_req_id", referencedColumnName = "chng_req_id", nullable = false)
	@Valid
	private TChngReq tChngReq;
	
	@NotNull
	@Column(name = "chng_req_detail_id", nullable = false, length = 255)
	private Integer chngReqDetailId;

	/**
	 *
	 * @generated
	 */
	public TChngReqDetailId() {
	}

	
	/**
	 * @return the tChngReq
	 */
	public TChngReq gettChngReq() {
		return tChngReq;
	}


	/**
	 * @param tChngReq the tChngReq to set
	 */
	public void settChngReq(TChngReq tChngReq) {
		this.tChngReq = tChngReq;
	}


	/**
	 * 
	 * @generated
	 */
	public void setChngReqDetailId(final Integer chngReqDetailId) {
		this.chngReqDetailId = chngReqDetailId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getChngReqDetailId() {
		return this.chngReqDetailId;
	}

	/**
	 * 
	 * @generated
	 */
	/*public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TChngReqDetailId)) {
			return false;
		}
		TChngReqDetailId castOther = (TChngReqDetailId) other;
		return (this.tChngReq.getChngReqId().equals(castOther.gettChngReq().getChngReqId())) &&

		(this.chngReqDetailId.equals(castOther.chngReqDetailId))

		;

	}*/

	/**
	 * @generated
	 * 
	 */
	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tChngReq.getChngReqId() == null) ? 0 : tChngReq.getChngReqId().hashCode());
		result = prime * result + ((chngReqDetailId == null) ? 0 : chngReqDetailId.hashCode());
		return result;
	}*/

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("chngReqId=[").append(tChngReq.getChngReqId()).append("] ");
		buffer.append("chngReqDetailId=[").append(chngReqDetailId).append("] ");

		return buffer.toString();
	}
}
