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

/** 
 * JPA class representing TIndiaTaluk 
 * The corresponding mapping table is t_india_taluk 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTIndiaTaluks", query = "select myTIndiaTaluk from TIndiaTaluk myTIndiaTaluk"),
		@NamedQuery(name = "CountTIndiaTaluks", query = "Select Count(c) from TIndiaTaluk c"),
		@NamedQuery(name = "FindTIndiaTalukByTIndiaDistrict", query = "select myTIndiaTaluk from TIndiaTaluk myTIndiaTaluk where myTIndiaTaluk.tIndiaDistrict = ?1 "),
		@NamedQuery(name = "CountTIndiaTaluksByTIndiaDistrict", query = "select Count(myTIndiaTaluk) from TIndiaTaluk myTIndiaTaluk where myTIndiaTaluk.tIndiaDistrict = ?1 ") })
@Table(name = "t_india_taluk", uniqueConstraints = @UniqueConstraint(columnNames = { "taluk_id" }))
public class TIndiaTaluk implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "taluk_id", nullable = false, length = 255)
	private Integer talukId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "taluk_name", nullable = true, length = 200)
	private String talukName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "tehsil", nullable = true, length = 200)
	private String tehsil;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "shape_polygon", nullable = true, length = 255)
	private byte[] shapePolygon;

	@ManyToOne
	@JoinColumn(name = "district_id", referencedColumnName = "district_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TIndiaDistrict tIndiaDistrict;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tIndiaTaluk")
	private Set<TIndiaPin> tIndiaPinss;

	/**
	 *
	 * @generated
	 */
	public TIndiaTaluk() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTalukId(final Integer talukId) {
		this.talukId = talukId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getTalukId() {
		return this.talukId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setTalukName(final String talukName) {
		this.talukName = talukName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getTalukName() {
		return this.talukName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setTehsil(final String tehsil) {
		this.tehsil = tehsil;
	}

	/**
	 * 
	 * @generated
	 */
	public String getTehsil() {
		return this.tehsil;
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
	public TIndiaDistrict getTIndiaDistrict() {
		return this.tIndiaDistrict;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTIndiaDistrict(final TIndiaDistrict tIndiaDistrict) {
		this.tIndiaDistrict = tIndiaDistrict;

	}

	/**
	 * 
	 * @generated
	 */
	public Set<TIndiaPin> getTIndiaPinss() {
		return this.tIndiaPinss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTIndiaPinss(final Set<TIndiaPin> tIndiaPinss) {
		this.tIndiaPinss = tIndiaPinss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TIndiaTaluk that) {
		setTalukId(that.getTalukId());
		setTalukName(that.getTalukName());
		setTehsil(that.getTehsil());
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
		result = prime * result + ((talukId == null) ? 0 : talukId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("talukId=[").append(talukId).append("] ");
		buffer.append("talukName=[").append(talukName).append("] ");
		buffer.append("tehsil=[").append(tehsil).append("] ");
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
		final TIndiaTaluk other = (TIndiaTaluk) obj;
		if (talukId == null) {
			if (other.talukId != null) {
				return false;
			}
		} else if (!talukId.equals(other.talukId)) {
			return false;
		}
		return true;
	}
}
