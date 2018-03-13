package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TCallDirPrdWtId Pojo. 
 *
 */
@Embeddable
public class TCallDirPrdWtId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "call_dir_config_id", referencedColumnName = "call_dir_config_id", nullable = false)
	private TCallDirConfig tCallDirConfig;

	@NotNull
	@Column(name = "prd_prt_type_id", nullable = false, length = 255)
	private Integer prdPrtTypeId;

	/**
	 *
	 * @generated
	 */
	/*public TCallDirPrdWtId() {
	}
*/
	public TCallDirConfig gettCallDirConfig() {
		return tCallDirConfig;
	}

	public void settCallDirConfig(TCallDirConfig tCallDirConfig) {
		this.tCallDirConfig = tCallDirConfig;
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

	// /**
	// *
	// * @generated
	// */
	// public boolean equals(Object other) {
	// if (this == other) {
	// return true;
	// }
	// if (!(other instanceof TCallDirPrdWtId)) {
	// return false;
	// }
	// TCallDirPrdWtId castOther = (TCallDirPrdWtId) other;
	// return (this.tCallDirConfig.equals(castOther.tCallDirConfig)) &&
	//
	// (this.prdPrtTypeId.equals(castOther.prdPrtTypeId))
	//
	// ;
	//
	// }
	//
	// /**
	// * @generated
	// *
	// */
	// @Override
	// public int hashCode() {
	// final int prime = 31;
	// int result = 1;
	// result = prime * result
	// + ((tCallDirConfig == null) ? 0 : tCallDirConfig.hashCode());
	// result = prime * result
	// + ((prdPrtTypeId == null) ? 0 :
	// prdPrtTypeId.hashCode())+(int)Math.random();
	// return result;
	// }
	//
	// /**
	// * Returns a textual representation of a bean.
	// *
	// * @generated
	// */
	// public String toString() {
	//
	// final StringBuilder buffer = new StringBuilder();
	//
	// buffer.append("callDirConfigId=[").append(tCallDirConfig).append("] ");
	// buffer.append("prdPrtTypeId=[").append(prdPrtTypeId).append("] ");
	//
	// return buffer.toString();
	// }
}
