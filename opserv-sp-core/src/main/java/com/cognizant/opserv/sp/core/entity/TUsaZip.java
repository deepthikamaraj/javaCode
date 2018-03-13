package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TUsaZip 
 * The corresponding mapping table is t_usa_zip 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTUsaZips", query = "select myTUsaZip from TUsaZip myTUsaZip"),
		@NamedQuery(name = "FindAllTUsaZipCodes", query = "select myTUsaZip.zipCd from TUsaZip myTUsaZip"),
		@NamedQuery(name = "CountTUsaZips", query = "Select Count(c) from TUsaZip c"),
		@NamedQuery(name = "FindTUsaZipByTUsaCounty", query = "select myTUsaZip from TUsaZip myTUsaZip where myTUsaZip.tUsaCounty = ?1 "),
		@NamedQuery(name = "CountTUsaZipsByTUsaCounty", query = "select Count(myTUsaZip) from TUsaZip myTUsaZip where myTUsaZip.tUsaCounty = ?1 ") })
@Table(name = "t_usa_zip", uniqueConstraints = @UniqueConstraint(columnNames = { "zip_id" }))
public class TUsaZip implements Serializable {
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
	@Length(max = 75)
	@Column(name = "zip_cd", nullable = true, length = 75)
	private String zipCd;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "shape_polygon", nullable = true, length = 255)
	private byte[] shapePolygon;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "data_point", nullable = true, length = 255)
	private byte[] dataPoint;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "prn_zip_cd", nullable = true, length = 255)
	private Integer prnZipCd;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "point_zip_flag", nullable = true, length = 1)
	private Character pointZipFlag;

	@ManyToOne
	@JoinColumn(name = "county_id", referencedColumnName = "county_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TUsaCounty tUsaCounty;

	/**
	 *
	 * @generated
	 */
	public TUsaZip() {
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
	 * 
	 * @generated
	 */

	public void setShapePolygon(final byte[] shapePolygon) {
		this.shapePolygon = shapePolygon;
	}

	/**
	 * 
	 * @generated
	 */
	public byte[] getShapePolygon() {
		return this.shapePolygon;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDataPoint(final byte[] dataPoint) {
		this.dataPoint = dataPoint;
	}

	/**
	 * 
	 * @generated
	 */
	public byte[] getDataPoint() {
		return this.dataPoint;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPrnZipCd(final Integer prnZipCd) {
		this.prnZipCd = prnZipCd;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getPrnZipCd() {
		return this.prnZipCd;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPointZipFlag(final Character pointZipFlag) {
		this.pointZipFlag = pointZipFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getPointZipFlag() {
		return this.pointZipFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public TUsaCounty getTUsaCounty() {
		return this.tUsaCounty;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTUsaCounty(final TUsaCounty tUsaCounty) {
		this.tUsaCounty = tUsaCounty;

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TUsaZip that) {
		setZipId(that.getZipId());
		setZipCd(that.getZipCd());
		setShapePolygon(that.getShapePolygon());
		setDataPoint(that.getDataPoint());
		setPrnZipCd(that.getPrnZipCd());
		setPointZipFlag(that.getPointZipFlag());
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
		buffer.append("zipCd=[").append(zipCd).append("] ");
		buffer.append("shapePolygon=[").append(shapePolygon).append("] ");
		buffer.append("dataPoint=[").append(dataPoint).append("] ");
		buffer.append("prnZipCd=[").append(prnZipCd).append("] ");
		buffer.append("pointZipFlag=[").append(pointZipFlag).append("] ");

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
		final TUsaZip other = (TUsaZip) obj;
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
