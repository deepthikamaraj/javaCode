package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TBussUnit 
 * The corresponding mapping table is t_buss_unit 
 */

@Entity
@NamedQueries({
		
		@NamedQuery(name = "FindAllTBussUnits", query = "select myTBussUnit from TBussUnit myTBussUnit"),
		@NamedQuery(name = "CountTBussUnits", query = "Select Count(c) from TBussUnit c"),
		@NamedQuery(name = "FindTBussUnitByTOrg", query = "select myTBussUnit from TBussUnit myTBussUnit where myTBussUnit.tOrg = ?1 "),
		@NamedQuery(name = "FindTBussUnitByEffDate", query = "select myTBussUnit from TBussUnit myTBussUnit where myTBussUnit.activeFlag = ?1 AND (myTBussUnit.effendDt >= ?2 OR myTBussUnit.effendDt IS NULL) AND myTBussUnit.tenantId = ?3"),
		@NamedQuery(name = "CountTBussUnitsByTOrg", query = "select Count(myTBussUnit) from TBussUnit myTBussUnit where myTBussUnit.tOrg = ?1 "),
		@NamedQuery(name = "FindTBussUnits", query = "select myTBussUnit.bussUnitId ,myTBussUnit.bussUnitName  from TBussUnit myTBussUnit where myTBussUnit.tenantId = ?1 "),
		@NamedQuery(name = "FindTBussUnitNamesById", query = "select myTBussUnit.bussUnitName  from TBussUnit myTBussUnit where myTBussUnit.bussUnitId IN ?1 AND myTBussUnit.tenantId = ?2 ")})
@Table(name = "t_buss_unit", uniqueConstraints = @UniqueConstraint(columnNames = { "buss_unit_id" }))
public class TBussUnit implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "buss_unit_id", nullable = false, length = 255)
	private Long bussUnitId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 50)
	@Column(name = "buss_unit_name", nullable = false, length = 50)
	private String bussUnitName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 75)
	@Column(name = "buss_unit_desc", nullable = true, length = 75)
	private String bussUnitDesc;

	/**
	 * 
	 * @generated
	 */
	/*@Column(name = "default_buss_unit_flag", nullable = true, length = 1)
	private Character defaultBussUnitFlag;*/

	/**
	 * 
	 * @generated
	 */
	@Column(name = "created_by", nullable = true, length = 255)
	private Integer createdBy;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "create_dt", nullable = true, length = 19)
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
	private Date updateDate;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;
	
	@NotEmpty
	@Length(max = 20)
	@Column(name = "buss_unit_cd", nullable = false, length = 20)
	private String bussUnitCode;
	
	@NotNull
	@Column(name = "active_flag", nullable = false, length = 1)
	private Character activeFlag;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "eff_start_dt", nullable = false, length = 19)
	private Date effstartDt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "eff_end_dt", nullable = true, length = 19)
	private Date effendDt;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "org_id", referencedColumnName = "org_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TOrg tOrg;
	
	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "ext_buss_unit_id", nullable = true, length = 50)
	private String extBussUnitId;

	/*@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tBussUnit")
	private Set<TBussUnitHier> tBussUnitHierss;*/

	/*@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tBussUnit")
	private Set<TBussSubUnit> tBussSubUnitss;*/

	/**
	 *
	 * @generated
	 */
	public TBussUnit() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setBussUnitId(final Long bussUnitId) {
		this.bussUnitId = bussUnitId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getBussUnitId() {
		return this.bussUnitId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setBussUnitName(final String bussUnitName) {
		this.bussUnitName = bussUnitName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getBussUnitName() {
		return this.bussUnitName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setBussUnitDesc(final String bussUnitDesc) {
		this.bussUnitDesc = bussUnitDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getBussUnitDesc() {
		return this.bussUnitDesc;
	}

	/**
	 * 
	 * @generated
	 */

	/*public void setDefaultBussUnitFlag(final Character defaultBussUnitFlag) {
		this.defaultBussUnitFlag = defaultBussUnitFlag;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public Character getDefaultBussUnitFlag() {
		return this.defaultBussUnitFlag;
	}*/

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

	public void setUpdateDate(final Date updateDate) {
		if (updateDate != null) {
			this.updateDate = (Date) updateDate.clone();
		} else {
			this.updateDate = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getUpdateDate() {
		if (this.updateDate != null) {
			return (Date) this.updateDate.clone();
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

	

	public void setBussUnitCode(final String bussUnitCode) {
		this.bussUnitCode = bussUnitCode;
	}

	/**
	 * 
	 * @generated
	 */
	public String getBussUnitCode() {
		return this.bussUnitCode;
	}

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
	
	public void setEffStartDt(final Date effstartDt) {
		if (effstartDt != null) {
			this.effstartDt = (Date) effstartDt.clone();
		} else {
			this.effstartDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getEffStartDt() {
		if (this.effstartDt != null) {
			return (Date) this.effstartDt.clone();
		} else {
			return null;
		}
	}
	
	public void setEffEndDt(final Date effendDt) {
		if (effendDt != null) {
			this.effendDt = (Date) effendDt.clone();
		} else {
			this.effstartDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getEffEndDt() {
		if (this.effendDt != null) {
			return (Date) this.effendDt.clone();
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * @generated
	 */
	public TOrg getTOrg() {
		return this.tOrg;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTOrg(final TOrg tOrg) {
		this.tOrg = tOrg;

	}


	/**
	 * 
	 * @generated
	 */

	public void setExtBussUnitId(final String extBussUnitId) {
		this.extBussUnitId = extBussUnitId;
	}

	/**
	 * 
	 * @generated
	 */
	public String getExtBussUnitId() {
		return this.extBussUnitId;
	}
	/**
	 * 
	 * @generated
	 */
	/*public Set<TBussUnitHier> getTBussUnitHierss() {
		return this.tBussUnitHierss;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTBussUnitHierss(final Set<TBussUnitHier> tBussUnitHierss) {
		this.tBussUnitHierss = tBussUnitHierss;
	}*/

	/**
	 * 
	 * @generated
	 *//*
	public Set<TBussSubUnit> getTBussSubUnitss() {
		return this.tBussSubUnitss;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTBussSubUnitss(final Set<TBussSubUnit> tBussSubUnitss) {
		this.tBussSubUnitss = tBussSubUnitss;
	}*/

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TBussUnit that) {
		setBussUnitId(that.getBussUnitId());
		setBussUnitName(that.getBussUnitName());
		setBussUnitDesc(that.getBussUnitDesc());
		//setDefaultBussUnitFlag(that.getDefaultBussUnitFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDate(that.getUpdateDate());
		setTenantId(that.getTenantId());
		setBussUnitCode(that.getBussUnitCode());
		setActiveFlag(that.getActiveFlag());
		setEffStartDt(that.getEffStartDt());
	    setEffEndDt(that.getEffEndDt());
	    
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((bussUnitId == null) ? 0 : bussUnitId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("bussUnitId=[").append(bussUnitId).append("] ");
		buffer.append("bussUnitName=[").append(bussUnitName).append("] ");
		buffer.append("bussUnitDesc=[").append(bussUnitDesc).append("] ");
		//buffer.append("defaultBussUnitFlag=[").append(defaultBussUnitFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDate=[").append(updateDate).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("bussUnitCode=[").append(bussUnitCode).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("effstartDt=[").append(effstartDt).append("] ");
		buffer.append("effendDt=[").append(effendDt).append("] ");
		

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
		final TBussUnit other = (TBussUnit) obj;
		if (bussUnitId == null) {
			if (other.bussUnitId != null) {
				return false;
			}
		} else if (!bussUnitId.equals(other.bussUnitId)) {
			return false;
		}
		return true;
	}
}
