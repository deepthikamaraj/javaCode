package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TRptDocId Pojo. 
 *
 */
@Embeddable
public class TRptDocId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "doc_id", nullable = false, length = 255)
	private Integer docId;
	@NotNull
	@Column(name = "rpt_id", nullable = false, length = 255)
	private Integer rptId;

	/**
	 *
	 * @generated
	 */
	public TRptDocId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setDocId(final Integer docId) {
		this.docId = docId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getDocId() {
		return this.docId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setRptId(final Integer rptId) {
		this.rptId = rptId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getRptId() {
		return this.rptId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TRptDocId)) {
			return false;
		}
		TRptDocId castOther = (TRptDocId) other;
		return (this.docId.equals(castOther.docId)) &&

		(this.rptId.equals(castOther.rptId))

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
		result = prime * result + ((docId == null) ? 0 : docId.hashCode());
		result = prime * result + ((rptId == null) ? 0 : rptId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("docId=[").append(docId).append("] ");
		buffer.append("rptId=[").append(rptId).append("] ");

		return buffer.toString();
	}
}
