package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TRecipientAttrId Pojo. 
 *
 */
@Embeddable
public class TRecipientAttrId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "sequence_id", nullable = false, length = 255)
	private Integer sequenceId;
	@NotNull
	@Column(name = "recipient_pref_id", nullable = false, length = 255)
	private Integer recipientPrefId;
	@NotNull
	@Column(name = "attr_id", nullable = false, length = 255)
	private Integer attrId;

	/**
	 *
	 * @generated
	 */
	public TRecipientAttrId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setSequenceId(final Integer sequenceId) {
		this.sequenceId = sequenceId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getSequenceId() {
		return this.sequenceId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setRecipientPrefId(final Integer recipientPrefId) {
		this.recipientPrefId = recipientPrefId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getRecipientPrefId() {
		return this.recipientPrefId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setAttrId(final Integer attrId) {
		this.attrId = attrId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getAttrId() {
		return this.attrId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TRecipientAttrId)) {
			return false;
		}
		TRecipientAttrId castOther = (TRecipientAttrId) other;
		return (this.sequenceId.equals(castOther.sequenceId)) &&

		(this.recipientPrefId.equals(castOther.recipientPrefId)) &&

		(this.attrId.equals(castOther.attrId))

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
		result = prime * result + ((sequenceId == null) ? 0 : sequenceId.hashCode());
		result = prime * result + ((recipientPrefId == null) ? 0 : recipientPrefId.hashCode());
		result = prime * result + ((attrId == null) ? 0 : attrId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("sequenceId=[").append(sequenceId).append("] ");
		buffer.append("recipientPrefId=[").append(recipientPrefId).append("] ");
		buffer.append("attrId=[").append(attrId).append("] ");

		return buffer.toString();
	}
}
