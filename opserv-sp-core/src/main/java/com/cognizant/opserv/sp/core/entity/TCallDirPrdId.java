package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TCallDirPrdId Pojo. 
 *
 */
@Embeddable
public class TCallDirPrdId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "call_dir_id", nullable = false, length = 255)
	private Integer callDirId;
	@NotNull
	@Column(name = "prd_prt_type_id", nullable = false, length = 255)
	private Integer prdPrtTypeId;

	/**
	 *
	 * @generated
	 */
	/*public TCallDirPrdId() {
	}
*/
	/**
	 * 
	 * @generated
	 */
	public void setCallDirId(final Integer callDirId) {
		this.callDirId = callDirId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCallDirId() {
		return this.callDirId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setPrdPrtTypeId(final Integer prdPrtTypeId) {
		this.prdPrtTypeId = prdPrtTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getPrdPrtTypeId() {
		return this.prdPrtTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TCallDirPrdId)) {
			return false;
		}
		TCallDirPrdId castOther = (TCallDirPrdId) other;
		return (this.callDirId.equals(castOther.callDirId)) &&

		(this.prdPrtTypeId.equals(castOther.prdPrtTypeId))

		;

	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		 int prime = 31;
		int result = 1;
		result = prime * result + ((callDirId == null) ? 0 : callDirId.hashCode());
		result = prime * result + ((prdPrtTypeId == null) ? 0 : prdPrtTypeId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("callDirId=["+callDirId+"] ");
		buffer.append("prdPrtTypeId=["+prdPrtTypeId+"] ");

		return buffer.toString();
	}
}
