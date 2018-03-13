package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TUsaState 
 * The corresponding mapping table is t_usa_state 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTUsaStates", query = "select myTUsaState from TUsaState myTUsaState"),
		@NamedQuery(name = "CountTUsaStates", query = "Select Count(c) from TUsaState c"),
		/*@NamedQuery(name = "FindTUsaStateByTCountry", query = "select myTUsaState from TUsaState myTUsaState where myTUsaState.tCountry = ?1 "),
		@NamedQuery(name = "CountTUsaStatesByTCountry", query = "select Count(myTUsaState) from TUsaState myTUsaState where myTUsaState.tCountry = ?1 ") */
		})
@Table(name = "t_usa_state", uniqueConstraints = @UniqueConstraint(columnNames = { "state_id" }))
public class TUsaState implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "state_id", nullable = false, length = 255)
	private Integer stateId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "state_name", nullable = true, length = 200)
	private String stateName;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 20)
	@Column(name = "state_cd", nullable = false, length = 20)
	private String stateCd;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "shape_polygon", nullable = false, length = 255)
	private byte[] shapePolygon;

//	@ManyToOne
//	@JoinColumn(name = "country_id", referencedColumnName = "country_id", nullable = false, insertable = true, updatable = true)
//	@Valid
//	private TCountry tCountry;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tUsaState")
	private Set<TUsaCounty> tUsaCountiess;

	/**
	 *
	 * @generated
	 */
	public TUsaState() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setStateId(final Integer stateId) {
		this.stateId = stateId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getStateId() {
		return this.stateId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setStateName(final String stateName) {
		this.stateName = stateName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getStateName() {
		return this.stateName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setStateCd(final String stateCd) {
		this.stateCd = stateCd;
	}

	/**
	 * 
	 * @generated
	 */
	public String getStateCd() {
		return this.stateCd;
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
//	public TCountry getTCountry() {
//		return this.tCountry;
//	}

	/**
	 * 
	 * @generated
	 */
//	public void setTCountry(final TCountry tCountry) {
//		this.tCountry = tCountry;
//
//	}

	/**
	 * 
	 * @generated
	 */
	public Set<TUsaCounty> getTUsaCountiess() {
		return this.tUsaCountiess;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTUsaCountiess(final Set<TUsaCounty> tUsaCountiess) {
		this.tUsaCountiess = tUsaCountiess;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TUsaState that) {
		setStateId(that.getStateId());
		setStateName(that.getStateName());
		setStateCd(that.getStateCd());
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
		result = prime * result + ((stateId == null) ? 0 : stateId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("stateId=[").append(stateId).append("] ");
		buffer.append("stateName=[").append(stateName).append("] ");
		buffer.append("stateCd=[").append(stateCd).append("] ");
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
		final TUsaState other = (TUsaState) obj;
		if (stateId == null) {
			if (other.stateId != null) {
				return false;
			}
		} else if (!stateId.equals(other.stateId)) {
			return false;
		}
		return true;
	}
}
