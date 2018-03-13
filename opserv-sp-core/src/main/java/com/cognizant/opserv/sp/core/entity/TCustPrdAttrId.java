package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/** 
 * The primary key class for the TCustPrdAttrId Pojo. 
 *
 */
@Embeddable
public class TCustPrdAttrId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(name = "prd_id", nullable = false, length = 50)
	private Long prdId;
	@NotNull
	@Column(name = "cust_id", nullable = false, length = 255)
	private Integer custId;
	@NotNull
	@Column(name = "attr_id", nullable = false, length = 255)
	private Long attrId;

	/**
	 *
	 * @generated
	 */
	/*public TCustPrdAttrId() {
	}*/

	/**
	 * 
	 * @generated
	 */
	public void setPrdId(final Long prdId) {
		this.prdId = prdId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getPrdId() {
		return this.prdId;
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
	public void setAttrId(final Long attrId) {
		this.attrId = attrId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getAttrId() {
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
		if (!(other instanceof TCustPrdAttrId)) {
			return false;
		}
		TCustPrdAttrId castOther = (TCustPrdAttrId) other;
		return (this.prdId.equals(castOther.prdId)) &&

		(this.custId.equals(castOther.custId)) &&

		(this.attrId.equals(castOther.attrId))

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
		result = prime * result + ((prdId == null) ? 0 : prdId.hashCode());
		result = prime * result + ((custId == null) ? 0 : custId.hashCode());
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

		buffer.append("prdId=[").append(prdId).append("] ");
		buffer.append("custId=[").append(custId).append("] ");
		buffer.append("attrId=[").append(attrId).append("] ");

		return buffer.toString();
	}
}
