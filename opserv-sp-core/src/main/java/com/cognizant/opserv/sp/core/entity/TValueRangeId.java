package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TValueRangeId Pojo. 
 *
 */
@Embeddable
public class TValueRangeId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "range_config_id", nullable = false, length = 255)
	private Integer rangeConfigId;
	@NotNull
	@Column(name = "level_id", nullable = false, length = 255)
	private Integer levelId;

	/**
	 *
	 * @generated
	 */
	public TValueRangeId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setRangeConfigId(final Integer rangeConfigId) {
		this.rangeConfigId = rangeConfigId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getRangeConfigId() {
		return this.rangeConfigId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setLevelId(final Integer levelId) {
		this.levelId = levelId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getLevelId() {
		return this.levelId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TValueRangeId)) {
			return false;
		}
		TValueRangeId castOther = (TValueRangeId) other;
		return (this.rangeConfigId.equals(castOther.rangeConfigId)) &&

		(this.levelId.equals(castOther.levelId))

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
		result = prime * result + ((rangeConfigId == null) ? 0 : rangeConfigId.hashCode());
		result = prime * result + ((levelId == null) ? 0 : levelId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("rangeConfigId=[").append(rangeConfigId).append("] ");
		buffer.append("levelId=[").append(levelId).append("] ");

		return buffer.toString();
	}
}
