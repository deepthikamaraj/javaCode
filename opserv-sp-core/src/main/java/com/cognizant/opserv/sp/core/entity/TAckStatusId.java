package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TRptRecipientId Pojo. 
 *
 */
@Embeddable
public class TAckStatusId implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Column(name = "ack_status_id", nullable = false, length = 255)
	private Integer ackStatusId;
	
	@NotNull
	@Column(name = "locale_id", nullable = false, length = 255)
	private String localeId;

	public Integer getAckStatusId() {
		return ackStatusId;
	}

	public void setAckStatusId(Integer ackStatusId) {
		this.ackStatusId = ackStatusId;
	}

	public String getLocaleId() {
		return localeId;
	}

	public void setLocaleId(String localeId) {
		this.localeId = localeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ackStatusId == null) ? 0 : ackStatusId.hashCode());
		result = prime * result
				+ ((localeId == null) ? 0 : localeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TAckStatusId other = (TAckStatusId) obj;
		if (ackStatusId == null) {
			if (other.ackStatusId != null)
				return false;
		} else if (!ackStatusId.equals(other.ackStatusId))
			return false;
		if (localeId == null) {
			if (other.localeId != null)
				return false;
		} else if (!localeId.equals(other.localeId))
			return false;
		return true;
	}

}
