package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TIndiaPin 
 * The corresponding mapping table is t_india_pin 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTIndiaPins", query = "select myTIndiaPin from TIndiaPin myTIndiaPin"),
		@NamedQuery(name = "CountTIndiaPins", query = "Select Count(c) from TIndiaPin c"),
		@NamedQuery(name = "FindTIndiaPinByTIndiaTaluk", query = "select myTIndiaPin from TIndiaPin myTIndiaPin where myTIndiaPin.tIndiaTaluk = ?1 "),
		@NamedQuery(name = "CountTIndiaPinsByTIndiaTaluk", query = "select Count(myTIndiaPin) from TIndiaPin myTIndiaPin where myTIndiaPin.tIndiaTaluk = ?1 ") })
@Table(name = "t_india_pin", uniqueConstraints = @UniqueConstraint(columnNames = { "pin_cd" }))
public class TIndiaPin implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@NotEmpty
	@Column(name = "pin_cd", nullable = false, length = 20)
	private String pinCd;

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

	@ManyToOne
	@JoinColumn(name = "taluk_id", referencedColumnName = "taluk_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TIndiaTaluk tIndiaTaluk;

	/**
	 *
	 * @generated
	 */
	public TIndiaPin() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setPinCd(final String pinCd) {
		this.pinCd = pinCd;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPinCd() {
		return this.pinCd;
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
	public TIndiaTaluk getTIndiaTaluk() {
		return this.tIndiaTaluk;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTIndiaTaluk(final TIndiaTaluk tIndiaTaluk) {
		this.tIndiaTaluk = tIndiaTaluk;

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TIndiaPin that) {
		setPinCd(that.getPinCd());
		setShapePolygon(that.getShapePolygon());
		setDataPoint(that.getDataPoint());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((pinCd == null) ? 0 : pinCd.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("pinCd=[").append(pinCd).append("] ");
		buffer.append("shapePolygon=[").append(shapePolygon).append("] ");
		buffer.append("dataPoint=[").append(dataPoint).append("] ");

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
		final TIndiaPin other = (TIndiaPin) obj;
		if (pinCd == null) {
			if (other.pinCd != null) {
				return false;
			}
		} else if (!pinCd.equals(other.pinCd)) {
			return false;
		}
		return true;
	}
}
