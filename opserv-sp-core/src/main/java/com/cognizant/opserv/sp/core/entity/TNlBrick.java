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
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TNlBrick 
 * The corresponding mapping table is t_nl_brick 
 */

@Entity
@NamedQueries({ @NamedQuery(name = "FindAllTNlBricks", query = "select myTNlBrick from TNlBrick myTNlBrick"),
		@NamedQuery(name = "CountTNlBricks", query = "Select Count(c) from TNlBrick c") })
@Table(name = "t_nl_brick", uniqueConstraints = @UniqueConstraint(columnNames = { "brick_id" }))
public class TNlBrick implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "brick_id", nullable = false, length = 255)
	private Integer brickId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 20)
	@Column(name = "brick_cd", nullable = false, length = 20)
	private String brickCd;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "brick_name", nullable = true, length = 100)
	private String brickName;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "country_id", nullable = true, length = 255)
	private Integer countryId;

	/**
	 *
	 * @generated
	 */
	public TNlBrick() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setBrickId(final Integer brickId) {
		this.brickId = brickId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getBrickId() {
		return this.brickId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setBrickCd(final String brickCd) {
		this.brickCd = brickCd;
	}

	/**
	 * 
	 * @generated
	 */
	public String getBrickCd() {
		return this.brickCd;
	}

	/**
	 * 
	 * @generated
	 */

	public void setBrickName(final String brickName) {
		this.brickName = brickName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getBrickName() {
		return this.brickName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCountryId(final Integer countryId) {
		this.countryId = countryId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCountryId() {
		return this.countryId;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TNlBrick that) {
		setBrickId(that.getBrickId());
		setBrickCd(that.getBrickCd());
		setBrickName(that.getBrickName());
		setCountryId(that.getCountryId());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((brickId == null) ? 0 : brickId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("brickId=[").append(brickId).append("] ");
		buffer.append("brickCd=[").append(brickCd).append("] ");
		buffer.append("brickName=[").append(brickName).append("] ");
		buffer.append("countryId=[").append(countryId).append("] ");

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
		final TNlBrick other = (TNlBrick) obj;
		if (brickId == null) {
			if (other.brickId != null) {
				return false;
			}
		} else if (!brickId.equals(other.brickId)) {
			return false;
		}
		return true;
	}
}
