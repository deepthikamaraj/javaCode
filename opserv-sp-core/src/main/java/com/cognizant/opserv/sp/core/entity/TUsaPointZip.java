package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TUsaPointZip 
 * The corresponding mapping table is t_usa_point_zip 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTUsaPointZips", query = "select myTUsaPointZip from TUsaPointZip myTUsaPointZip"),
		@NamedQuery(name = "FindAllTUsaPointZipsByPrnId", query = "select myTUsaPointZip from TUsaPointZip myTUsaPointZip where myTUsaPointZip.prnZipId IN ?1"),
		@NamedQuery(name = "CountTUsaPointZips", query = "Select Count(c) from TUsaPointZip c") })
@Table(name = "t_usa_point_zip", uniqueConstraints = @UniqueConstraint(columnNames = { "zip_id" }))
public class TUsaPointZip implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "zip_id", nullable = false, length = 255)
	private Integer zipId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "prn_zip_id", nullable = false, length = 255)
	private Integer prnZipId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 75)
	@Column(name = "zip_cd", nullable = true, length = 75)
	private String zipCd;

	/**
	 *
	 * @generated
	 */
	public TUsaPointZip() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setZipId(final Integer zipId) {
		this.zipId = zipId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getZipId() {
		return this.zipId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPrnZipId(final Integer prnZipId) {
		this.prnZipId = prnZipId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getPrnZipId() {
		return this.prnZipId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setZipCd(final String zipCd) {
		this.zipCd = zipCd;
	}

	/**
	 * 
	 * @generated
	 */
	public String getZipCd() {
		return this.zipCd;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TUsaPointZip that) {
		setZipId(that.getZipId());
		setPrnZipId(that.getPrnZipId());
		setZipCd(that.getZipCd());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((zipId == null) ? 0 : zipId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("zipId=[").append(zipId).append("] ");
		buffer.append("prnZipId=[").append(prnZipId).append("] ");
		buffer.append("zipCd=[").append(zipCd).append("] ");

		return buffer.toString();
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public boolean equals(final Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final TUsaPointZip other = (TUsaPointZip) obj;
		if (zipId == null) {
			if (other.zipId != null) {
				return false;
			}
		} else if (!zipId.equals(other.zipId)) {
			return false;
		}
		return true;
	}
}
