package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TCountryDiv 
 * The corresponding mapping table is t_country_div 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTCountryDivs", query = "select myTCountryDiv from TCountryDiv myTCountryDiv"),
		@NamedQuery(name = "CountTCountryDivs", query = "Select Count(c) from TCountryDiv c"),
		@NamedQuery(name = "findLowestCountryDivision", query = "select myTCountryDiv from TCountryDiv myTCountryDiv where myTCountryDiv.tCountry.countryId = ?1 and myTCountryDiv.lowestDivFlag = 'Y'"),
		@NamedQuery(name = "FindTCountryDivByTCountry", query = "select myTCountryDiv from TCountryDiv myTCountryDiv where myTCountryDiv.tCountry = ?1 "),
		@NamedQuery(name = "CountTCountryDivsByTCountry", query = "select Count(*) from TCountryDiv myTCountryDiv where myTCountryDiv.tCountry = ?1 ") })
@Table(name = "t_country_div")
public class TCountryDiv implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TCountryDivId tCountryDivId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 50)
	@Column(name = "div_name", nullable = false, length = 50)
	private String divName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "div_cd", nullable = true, length = 20)
	private String divCd;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "lowest_div_flag", nullable = true, length = 1)
	private Character lowestDivFlag;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 50)
	@Column(name = "lookup_tbl", nullable = false, length = 50)
	private String lookupTbl;

	@ManyToOne
	@JoinColumn(name = "country_id", referencedColumnName = "country_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TCountry tCountry;

	/**
	 *
	 * @generated
	 */
	public TCountryDiv() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTCountryDivId(final TCountryDivId tCountryDivId) {
		this.tCountryDivId = tCountryDivId;
	}

	/**
	 * 
	 * @generated
	 */
	public TCountryDivId getTCountryDivId() {
		return this.tCountryDivId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDivName(final String divName) {
		this.divName = divName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getDivName() {
		return this.divName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDivCd(final String divCd) {
		this.divCd = divCd;
	}

	/**
	 * 
	 * @generated
	 */
	public String getDivCd() {
		return this.divCd;
	}

	/**
	 * 
	 * @generated
	 */

	public void setLowestDivFlag(final Character lowestDivFlag) {
		this.lowestDivFlag = lowestDivFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getLowestDivFlag() {
		return this.lowestDivFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public void setLookupTbl(final String lookupTbl) {
		this.lookupTbl = lookupTbl;
	}

	/**
	 * 
	 * @generated
	 */
	public String getLookupTbl() {
		return this.lookupTbl;
	}

	/**
	 * 
	 * @generated
	 */
	public TCountry getTCountry() {
		return this.tCountry;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCountry(final TCountry tCountry) {
		this.tCountry = tCountry;
		if (this.tCountry != null && this.tCountry.getCountryId() != null) {

			this.tCountryDivId.setCountryId(this.tCountry.getCountryId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCountryDiv that) {
		setTCountryDivId(that.getTCountryDivId());
		setDivName(that.getDivName());
		setDivCd(that.getDivCd());
		setLowestDivFlag(that.getLowestDivFlag());
		setLookupTbl(that.getLookupTbl());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((tCountryDivId == null) ? 0 : tCountryDivId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tCountryDivId=[").append(tCountryDivId).append("] ");
		buffer.append("divName=[").append(divName).append("] ");
		buffer.append("divCd=[").append(divCd).append("] ");
		buffer.append("lowestDivFlag=[").append(lowestDivFlag).append("] ");
		buffer.append("lookupTbl=[").append(lookupTbl).append("] ");

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
		final TCountryDiv other = (TCountryDiv) obj;
		if (tCountryDivId == null) {
			if (other.tCountryDivId != null) {
				return false;
			}
		} else if (!tCountryDivId.equals(other.tCountryDivId)) {
			return false;
		}
		return true;
	}
}
