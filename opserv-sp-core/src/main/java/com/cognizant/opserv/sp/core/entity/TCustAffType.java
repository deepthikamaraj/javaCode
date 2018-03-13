package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

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
 * JPA class representing TCustAffType 
 * The corresponding mapping table is t_cust_aff_type 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTCustAffTypes", query = "select myTCustAffType from TCustAffType myTCustAffType"),
		@NamedQuery(name = "CountTCustAffTypes", query = "Select Count(c) from TCustAffType c")})
//		@NamedQuery(name = "FindTCustAffTypeByTCustAffType", query = "select myTCustAffType from TCustAffType myTCustAffType where myTCustAffType.tCustAffType = ?1 "),
//		@NamedQuery(name = "CountTCustAffTypesByTCustAffType", query = "select Count(myTCustAffType) from TCustAffType myTCustAffType where myTCustAffType.tCustAffType = ?1 ") })
@Table(name = "t_cust_type", uniqueConstraints = @UniqueConstraint(columnNames = { "cust_type_id" }))
public class TCustAffType implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cust_type_id", nullable = false, length = 255)
	private Integer custAffTypeId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "type_name", nullable = true, length = 50)
	private String typeName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 75)
	@Column(name = "type_desc", nullable = true, length = 75)
	private String typeDesc;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "active_flag", nullable = true, length = 1)
	private Character activeFlag;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "created_by", nullable = false, length = 255)
	private Integer createdBy;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "create_dt", nullable = false, length = 19)
	private Date createDt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "updated_by", nullable = true, length = 255)
	private Integer updatedBy;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "update_dt", nullable = true, length = 19)
	private Date updateDt;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

	/*@ManyToOne
	@JoinColumn(name = "prn_aff_type_id", referencedColumnName = "cust_type_id", nullable = true, insertable = true, updatable = true)
	@Valid
	private TCustAffType tCustAffType;*/

	/*@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tCustAffType")
	private Set<TCust> tCustss;*/

	/*@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tCustAffType")
	private Set<TCustAffType> tCustAffTypess;*/

	/**
	 *
	 * @generated
	 */
	public TCustAffType() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setCustAffTypeId(final Integer custAffTypeId) {
		this.custAffTypeId = custAffTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCustAffTypeId() {
		return this.custAffTypeId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setTypeName(final String typeName) {
		this.typeName = typeName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getTypeName() {
		return this.typeName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setTypeDesc(final String typeDesc) {
		this.typeDesc = typeDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getTypeDesc() {
		return this.typeDesc;
	}

	/**
	 * 
	 * @generated
	 */

	public void setActiveFlag(final Character activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getActiveFlag() {
		return this.activeFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCreatedBy(final Integer createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCreateDt(final Date createDt) {
		if (createDt != null) {
			this.createDt = (Date) createDt.clone();
		} else {
			this.createDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getCreateDt() {
		if (this.createDt != null) {
			return (Date) this.createDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setUpdatedBy(final Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	/**
	 * 
	 * @generated
	 */

	public void setUpdateDt(final Date updateDt) {
		if (updateDt != null) {
			this.updateDt = (Date) updateDt.clone();
		} else {
			this.updateDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getUpdateDt() {
		if (this.updateDt != null) {
			return (Date) this.updateDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setTenantId(final Short tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * 
	 * @generated
	 */
	public Short getTenantId() {
		return this.tenantId;
	}

	/**
	 * 
	 * @generated
	 */
	/*public TCustAffType getTCustAffType() {
		return this.tCustAffType;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTCustAffType(final TCustAffType tCustAffType) {
		this.tCustAffType = tCustAffType;

	}*/

	/**
	 * 
	 * @generated
	 */
	/*public Set<TCust> getTCustss() {
		return this.tCustss;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public void setTCustss(final Set<TCust> tCustss) {
		this.tCustss = tCustss;
	}
*/
	/**
	 * 
	 * @generated
	 */
	/*public Set<TCustAffType> getTCustAffTypess() {
		return this.tCustAffTypess;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTCustAffTypess(final Set<TCustAffType> tCustAffTypess) {
		this.tCustAffTypess = tCustAffTypess;
	}*/

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCustAffType that) {
		setCustAffTypeId(that.getCustAffTypeId());
		setTypeName(that.getTypeName());
		setTypeDesc(that.getTypeDesc());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((custAffTypeId == null) ? 0 : custAffTypeId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("custAffTypeId=[").append(custAffTypeId).append("] ");
		buffer.append("typeName=[").append(typeName).append("] ");
		buffer.append("typeDesc=[").append(typeDesc).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");

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
		final TCustAffType other = (TCustAffType) obj;
		if (custAffTypeId == null) {
			if (other.custAffTypeId != null) {
				return false;
			}
		} else if (!custAffTypeId.equals(other.custAffTypeId)) {
			return false;
		}
		return true;
	}
}
