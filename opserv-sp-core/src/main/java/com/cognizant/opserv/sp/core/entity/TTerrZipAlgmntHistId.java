package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TTerrZipAlgmntHistId Pojo. 
 *
 */
@Embeddable
public class TTerrZipAlgmntHistId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "geo_zip_id", nullable = false, length = 255)
	private Integer geoZipId;
	@NotNull
	@Column(name = "rev_id", nullable = false, length = 255)
	private Integer revId;

	/**
	 *
	 * @generated
	 */
	public TTerrZipAlgmntHistId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setGeoZipId(final Integer geoZipId) {
		this.geoZipId = geoZipId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getGeoZipId() {
		return this.geoZipId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setRevId(final Integer revId) {
		this.revId = revId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getRevId() {
		return this.revId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TTerrZipAlgmntHistId)) {
			return false;
		}
		TTerrZipAlgmntHistId castOther = (TTerrZipAlgmntHistId) other;
		return (this.geoZipId.equals(castOther.geoZipId)) &&

		(this.revId.equals(castOther.revId))

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
		result = prime * result + ((geoZipId == null) ? 0 : geoZipId.hashCode());
		result = prime * result + ((revId == null) ? 0 : revId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("geoZipId=[").append(geoZipId).append("] ");
		buffer.append("revId=[").append(revId).append("] ");

		return buffer.toString();
	}
}
