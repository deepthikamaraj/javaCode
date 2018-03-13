package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TCustPrdId Pojo. 
 *
 */
@Embeddable
public class TCustPrdSalesId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "prd_id", nullable = false, length = 255)
	private Long prdId;
	
	@NotNull
	@Column(name = "cust_id", nullable = false, length = 255)
	private Integer custId;

	/**
	 *
	 * @generated
	 */
/*	public TCustPrdId() {
	}*/



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

	
	public Long getPrdId() {
		return prdId;
	}

	public void setPrdId(Long prdId) {
		this.prdId = prdId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TCustPrdSalesId)) {
			return false;
		}
		TCustPrdSalesId castOther = (TCustPrdSalesId) other;
		return (this.prdId.equals(castOther.prdId)) &&

		(this.custId.equals(castOther.custId))

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

		return buffer.toString();
	}
}