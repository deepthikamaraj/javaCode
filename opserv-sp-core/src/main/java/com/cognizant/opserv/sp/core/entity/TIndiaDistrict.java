package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TIndiaDistrict 
 * The corresponding mapping table is t_india_district 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTIndiaDistricts", query = "select myTIndiaDistrict from TIndiaDistrict myTIndiaDistrict"),
		@NamedQuery(name = "CountTIndiaDistricts", query = "Select Count(c) from TIndiaDistrict c"),
		@NamedQuery(name = "FindTIndiaDistrictByTIndiaState", query = "select myTIndiaDistrict from TIndiaDistrict myTIndiaDistrict where myTIndiaDistrict.tIndiaState = ?1 "),
		@NamedQuery(name = "CountTIndiaDistrictsByTIndiaState", query = "select Count(myTIndiaDistrict) from TIndiaDistrict myTIndiaDistrict where myTIndiaDistrict.tIndiaState = ?1 ") })
@Table(name = "t_india_district", uniqueConstraints = @UniqueConstraint(columnNames = { "district_id" }))
public class TIndiaDistrict implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "district_id", nullable = false, length = 255)
	private Integer districtId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 200)
	@Column(name = "district_name", nullable = false, length = 200)
	private String districtName;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "shape_polygon", nullable = true, length = 255)
	private byte[] shapePolygon;

	@ManyToOne
	@JoinColumn(name = "state_id", referencedColumnName = "state_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TIndiaState tIndiaState;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tIndiaDistrict")
	private Set<TIndiaTaluk> tIndiaTalukss;

	/**
	 *
	 * @generated
	 */
	public TIndiaDistrict() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setDistrictId(final Integer districtId) {
		this.districtId = districtId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getDistrictId() {
		return this.districtId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDistrictName(final String districtName) {
		this.districtName = districtName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getDistrictName() {
		return this.districtName;
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
	public TIndiaState getTIndiaState() {
		return this.tIndiaState;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTIndiaState(final TIndiaState tIndiaState) {
		this.tIndiaState = tIndiaState;

	}

	/**
	 * 
	 * @generated
	 */
	public Set<TIndiaTaluk> getTIndiaTalukss() {
		return this.tIndiaTalukss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTIndiaTalukss(final Set<TIndiaTaluk> tIndiaTalukss) {
		this.tIndiaTalukss = tIndiaTalukss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TIndiaDistrict that) {
		setDistrictId(that.getDistrictId());
		setDistrictName(that.getDistrictName());
		setShapePolygon(that.getShapePolygon());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((districtId == null) ? 0 : districtId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("districtId=[").append(districtId).append("] ");
		buffer.append("districtName=[").append(districtName).append("] ");
		buffer.append("shapePolygon=[").append(shapePolygon).append("] ");

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
		final TIndiaDistrict other = (TIndiaDistrict) obj;
		if (districtId == null) {
			if (other.districtId != null) {
				return false;
			}
		} else if (!districtId.equals(other.districtId)) {
			return false;
		}
		return true;
	}
}
