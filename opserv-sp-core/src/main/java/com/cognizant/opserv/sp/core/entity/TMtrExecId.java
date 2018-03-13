package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TMtrExecId Pojo. 
 *
 */
@Embeddable
public class TMtrExecId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "config_id", nullable = false, length = 255)
	private Integer configId;
	@NotNull
	@Column(name = "exec_id", nullable = false, length = 255)
	private Long execId;

	/**
	 *
	 * @generated
	 */
	public TMtrExecId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setConfigId(final Integer configId) {
		this.configId = configId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getConfigId() {
		return this.configId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setExecId(final Long execId) {
		this.execId = execId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getExecId() {
		return this.execId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TMtrExecId)) {
			return false;
		}
		TMtrExecId castOther = (TMtrExecId) other;
		return (this.configId.equals(castOther.configId)) &&

		(this.execId.equals(castOther.execId))

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
		result = prime * result + ((configId == null) ? 0 : configId.hashCode());
		result = prime * result + ((execId == null) ? 0 : execId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("configId=[").append(configId).append("] ");
		buffer.append("execId=[").append(execId).append("] ");

		return buffer.toString();
	}
}
