package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TChngReqApprHierId Pojo. 
 *
 */
@Embeddable
public class TChngReqApprHierId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "appr_sales_hier_id", nullable = false, length = 255)
	private Long apprSalesHierId;
	@NotNull
	@Column(name = "chng_req_config_id", nullable = false, length = 255)
	private Integer chngReqConfigId;

	/**
	 *
	 * @generated
	 */
	public TChngReqApprHierId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setApprSalesHierId(final Long apprSalesHierId) {
		this.apprSalesHierId = apprSalesHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getApprSalesHierId() {
		return this.apprSalesHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setChngReqConfigId(final Integer chngReqConfigId) {
		this.chngReqConfigId = chngReqConfigId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getChngReqConfigId() {
		return this.chngReqConfigId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TChngReqApprHierId)) {
			return false;
		}
		TChngReqApprHierId castOther = (TChngReqApprHierId) other;
		return (this.apprSalesHierId.equals(castOther.apprSalesHierId)) &&

		(this.chngReqConfigId.equals(castOther.chngReqConfigId))

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
		result = prime * result + ((apprSalesHierId == null) ? 0 : apprSalesHierId.hashCode());
		result = prime * result + ((chngReqConfigId == null) ? 0 : chngReqConfigId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("apprSalesHierId=[").append(apprSalesHierId).append("] ");
		buffer.append("chngReqConfigId=[").append(chngReqConfigId).append("] ");

		return buffer.toString();
	}
}
