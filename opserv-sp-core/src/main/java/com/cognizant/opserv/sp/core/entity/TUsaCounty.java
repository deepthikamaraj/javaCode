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
 * JPA class representing TUsaCounty 
 * The corresponding mapping table is t_usa_county 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTUsaCountys", query = "select myTUsaCounty from TUsaCounty myTUsaCounty"),
		@NamedQuery(name = "CountTUsaCountys", query = "Select Count(c) from TUsaCounty c"),
		@NamedQuery(name = "FindTUsaCountyByTUsaState", query = "select myTUsaCounty from TUsaCounty myTUsaCounty where myTUsaCounty.tUsaState = ?1 "),
		@NamedQuery(name = "CountTUsaCountysByTUsaState", query = "select Count(myTUsaCounty) from TUsaCounty myTUsaCounty where myTUsaCounty.tUsaState = ?1 ") })
@Table(name = "t_usa_county", uniqueConstraints = @UniqueConstraint(columnNames = { "county_id" }))
public class TUsaCounty implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "county_id", nullable = false, length = 255)
	private Integer countyId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "county_cd", nullable = true, length = 20)
	private String countyCd;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 200)
	@Column(name = "county_name", nullable = false, length = 200)
	private String countyName;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "shape_polygon", nullable = true, length = 255)
	private byte[] shapePolygon;

	@ManyToOne
	@JoinColumn(name = "state_id", referencedColumnName = "state_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TUsaState tUsaState;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tUsaCounty")
	private Set<TUsaZip> tUsaZipss;

	/**
	 *
	 * @generated
	 */
	public TUsaCounty() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setCountyId(final Integer countyId) {
		this.countyId = countyId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCountyId() {
		return this.countyId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCountyCd(final String countyCd) {
		this.countyCd = countyCd;
	}

	/**
	 * 
	 * @generated
	 */
	public String getCountyCd() {
		return this.countyCd;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCountyName(final String countyName) {
		this.countyName = countyName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getCountyName() {
		return this.countyName;
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
	public TUsaState getTUsaState() {
		return this.tUsaState;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTUsaState(final TUsaState tUsaState) {
		this.tUsaState = tUsaState;

	}

	/**
	 * 
	 * @generated
	 */
	public Set<TUsaZip> getTUsaZipss() {
		return this.tUsaZipss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTUsaZipss(final Set<TUsaZip> tUsaZipss) {
		this.tUsaZipss = tUsaZipss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TUsaCounty that) {
		setCountyId(that.getCountyId());
		setCountyCd(that.getCountyCd());
		setCountyName(that.getCountyName());
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
		result = prime * result + ((countyId == null) ? 0 : countyId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("countyId=[").append(countyId).append("] ");
		buffer.append("countyCd=[").append(countyCd).append("] ");
		buffer.append("countyName=[").append(countyName).append("] ");
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
		final TUsaCounty other = (TUsaCounty) obj;
		if (countyId == null) {
			if (other.countyId != null) {
				return false;
			}
		} else if (!countyId.equals(other.countyId)) {
			return false;
		}
		return true;
	}
}
