package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TBussObjAssocId Pojo. 
 *
 */
@Embeddable
public class TBussObjAssocId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "buss_function_id", nullable = false, length = 255)
	private Integer bussFunctionId;
	@NotNull
	@Column(name = "buss_obj_id", nullable = false, length = 255)
	private Integer bussObjId;

	/**
	 *
	 * @generated
	 */
	public TBussObjAssocId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setBussFunctionId(final Integer bussFunctionId) {
		this.bussFunctionId = bussFunctionId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getBussFunctionId() {
		return this.bussFunctionId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setBussObjId(final Integer bussObjId) {
		this.bussObjId = bussObjId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getBussObjId() {
		return this.bussObjId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TBussObjAssocId)) {
			return false;
		}
		TBussObjAssocId castOther = (TBussObjAssocId) other;
		return (this.bussFunctionId.equals(castOther.bussFunctionId)) &&

		(this.bussObjId.equals(castOther.bussObjId))

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
		result = prime * result + ((bussFunctionId == null) ? 0 : bussFunctionId.hashCode());
		result = prime * result + ((bussObjId == null) ? 0 : bussObjId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("bussFunctionId=[").append(bussFunctionId).append("] ");
		buffer.append("bussObjId=[").append(bussObjId).append("] ");

		return buffer.toString();
	}
}
