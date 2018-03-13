package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TMtrExecLogId Pojo. 
 *
 */
@Embeddable
public class TMtrExecLogId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "log_id", nullable = false, length = 255)
	private Long logId;
	@NotNull
	@Column(name = "exec_id", nullable = false, length = 255)
	private Long execId;
	@NotNull
	@Column(name = "config_id", nullable = false, length = 255)
	private Integer configId;

	/**
	 *
	 * @generated
	 */
	public TMtrExecLogId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setLogId(final Long logId) {
		this.logId = logId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getLogId() {
		return this.logId;
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
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TMtrExecLogId)) {
			return false;
		}
		TMtrExecLogId castOther = (TMtrExecLogId) other;
		return (this.logId.equals(castOther.logId)) &&

		(this.execId.equals(castOther.execId)) &&

		(this.configId.equals(castOther.configId))

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
		result = prime * result + ((logId == null) ? 0 : logId.hashCode());
		result = prime * result + ((execId == null) ? 0 : execId.hashCode());
		result = prime * result + ((configId == null) ? 0 : configId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("logId=[").append(logId).append("] ");
		buffer.append("execId=[").append(execId).append("] ");
		buffer.append("configId=[").append(configId).append("] ");

		return buffer.toString();
	}
}
