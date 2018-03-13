package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TChngReqUsrNoteConfigId Pojo. 
 *
 */
@Embeddable
public class TChngReqUsrNoteConfigId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "usr_type_id", nullable = false, length = 255)
	private Integer usrTypeId;
	@NotNull
	@Column(name = "chng_req_config_id", nullable = false, length = 255)
	private Integer chngReqConfigId;

	/**
	 *
	 * @generated
	 */
	public TChngReqUsrNoteConfigId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setUsrTypeId(final Integer usrTypeId) {
		this.usrTypeId = usrTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getUsrTypeId() {
		return this.usrTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setChngReqConfigId(final Integer chngReqConfigId) {
		this.chngReqConfigId = chngReqConfigId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getChngReqConfigId() {
		return this.chngReqConfigId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TChngReqUsrNoteConfigId)) {
			return false;
		}
		TChngReqUsrNoteConfigId castOther = (TChngReqUsrNoteConfigId) other;
		return (this.usrTypeId.equals(castOther.usrTypeId)) &&

		(this.chngReqConfigId.equals(castOther.chngReqConfigId))

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
		result = prime * result + ((usrTypeId == null) ? 0 : usrTypeId.hashCode());
		result = prime * result + ((chngReqConfigId == null) ? 0 : chngReqConfigId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("usrTypeId=[").append(usrTypeId).append("] ");
		buffer.append("chngReqConfigId=[").append(chngReqConfigId).append("] ");

		return buffer.toString();
	}
}
