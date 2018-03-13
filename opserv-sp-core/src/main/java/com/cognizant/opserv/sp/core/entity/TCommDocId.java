package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TCommDocId Pojo. 
 *
 */
@Embeddable
public class TCommDocId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "doc_id", nullable = false, length = 255)
	private Integer docId;
	@NotNull
	@Column(name = "comm_id", nullable = false, length = 255)
	private Long commId;

	/**
	 *
	 * @generated
	 */
	public TCommDocId() {
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
	public void setCommId(final Long commId) {
		this.commId = commId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getCommId() {
		return this.commId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TCommDocId)) {
			return false;
		}
		TCommDocId castOther = (TCommDocId) other;
		return (this.docId.equals(castOther.docId)) &&

		(this.commId.equals(castOther.commId))

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
		result = prime * result + ((commId == null) ? 0 : commId.hashCode());
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
		buffer.append("commId=[").append(commId).append("] ");

		return buffer.toString();
	}
}
