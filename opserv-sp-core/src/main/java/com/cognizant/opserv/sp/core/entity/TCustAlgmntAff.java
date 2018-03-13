package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

/** 
 * JPA class representing TCustAlgmntAff 
 * The corresponding mapping table is t_cust_algmnt_aff 
 */
@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTCustAlgmntAffs", query = "select myTCustAlgmntAff from TCustAlgmntAff myTCustAlgmntAff"),
		@NamedQuery(name = "CountTCustAlgmntAffs", query = "Select Count(c) from TCustAlgmntAff c"),
		@NamedQuery(name = "FindTCustAlgmntAffByTCustType", query = "select myTCustAlgmntAff from TCustAlgmntAff myTCustAlgmntAff where myTCustAlgmntAff.custTypeId = ?1 "),
		@NamedQuery(name = "CountTCustAlgmntAffsByTCustType", query = "select Count(myTCustAlgmntAff) from TCustAlgmntAff myTCustAlgmntAff where myTCustAlgmntAff.custTypeId  = ?1 "),
		@NamedQuery(name = "FindTCustAlgmntAffByTAlgmntSalesTeam", query = "select myTCustAlgmntAff from TCustAlgmntAff myTCustAlgmntAff where myTCustAlgmntAff.tAlgmntSalesTeam = ?1 "),
		@NamedQuery(name = "CountTCustAlgmntAffsByTAlgmntSalesTeam", query = "select Count(myTCustAlgmntAff) from TCustAlgmntAff myTCustAlgmntAff where myTCustAlgmntAff.tAlgmntSalesTeam = ?1 "),
		@NamedQuery(name = "FindTCustAlgmntAffByTAlgmntSalesTeamAndTCustType", query = "select myTCustAlgmntAff from TCustAlgmntAff myTCustAlgmntAff where myTCustAlgmntAff.tAlgmntSalesTeam = ?1 and myTCustAlgmntAff.custTypeId = ?2 and myTCustAlgmntAff.tenantId = ?3 and activeFlag='Y' "),
		@NamedQuery(name = "getLowestCusttype", query = "select tct.typeName from TCustAlgmntAff tcbb, TCustAffHier tcah,TCustType tct where tcbb.affTypeId = tcah.tCustAlgmntAffByAffTypeId.affTypeId and tcbb.tAlgmntSalesTeam.tAlgmnt.algmntId = ?1 and tcbb.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.bussUnitId = ?2 " +
				"     and tcbb.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.salesTeamId = ?3 and tcbb.tenantId = ?4 and tcbb.activeFlag = 'Y' and tcbb.activeFlag = tcah.activeFlag and tct.tCustTypeId.localeId = ?5   and tct.tCustTypeId.custTypeId = tcbb.custTypeId and tcah.tCustAlgmntAffByAffTypeId.affTypeId NOT IN (select tcah.tCustAlgmntAffByPrnAffTypeId.affTypeId from TCustAlgmntAff tcaa, " +
				"     TCustAffHier tcah where tcaa.affTypeId = tcah.tCustAlgmntAffByAffTypeId.affTypeId and tcaa.tAlgmntSalesTeam.tAlgmnt.algmntId = tcbb.tAlgmntSalesTeam.tAlgmnt.algmntId and tcaa.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.bussUnitId = tcbb.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.bussUnitId and tcaa.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.salesTeamId = tcbb.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.salesTeamId and tcaa.tenantId = tcbb.tenantId and tcaa.activeFlag = 'Y' and " +
				"     tcaa.activeFlag = tcah.activeFlag and tcaa.affTypeId <> tcah.tCustAlgmntAffByPrnAffTypeId.affTypeId)")})
@Table(name = "t_cust_algmnt_aff", uniqueConstraints = @UniqueConstraint(columnNames = { "aff_type_id" }))
public class TCustAlgmntAff implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "aff_type_id", nullable = false, length = 255)
	private Integer affTypeId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "active_flag", nullable = false, length = 1)
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
	
	
	@NotNull
	@Column(name = "cust_type_id", nullable = false, length = 255)
	private Integer custTypeId;
	

	/*@ManyToOne
	@JoinColumn(name = "cust_type_id", referencedColumnName = "cust_type_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TCustType tCustType;
	*/
	/*@NotNull
	@Column(name = "algmnt_id", nullable = false, length = 255)
	private Long algmntId;
	
	@NotNull
	@Column(name = "sales_team_id", nullable = false, length = 255)
	private Long salesTeamId;
	
	@NotNull
	@Column(name = "buss_unit_id", nullable = false, length = 255)
	private Long bussUnitId;
*/
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "algmnt_id", referencedColumnName = "algmnt_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "buss_unit_id", referencedColumnName = "buss_unit_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "sales_team_id", referencedColumnName = "sales_team_id", nullable = false, insertable = true, updatable = true) })
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TAlgmntSalesTeam tAlgmntSalesTeam;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tCustAlgmntAffByAffTypeId")
	@Audited
	private Set<TCustAffHier> tCustAffHiersForAffTypeIds;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tCustAlgmntAffByPrnAffTypeId")
	@Audited
	private Set<TCustAffHier> tCustAffHiersForPrnAffTypeIds;

	/**
	 * 
	 * @generated
	 */

	public void setAffTypeId(final Integer affTypeId) {
		this.affTypeId = affTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getAffTypeId() {
		return this.affTypeId;
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
	/*public TCustType getTCustType() {
		return this.tCustType;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTCustType(final TCustType tCustType) {
		this.tCustType = tCustType;

	}
*/
	/**
	 * 
	 * @generated
	 */
	public TAlgmntSalesTeam getTAlgmntSalesTeam() {
		return this.tAlgmntSalesTeam;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAlgmntSalesTeam(final TAlgmntSalesTeam tAlgmntSalesTeam) {
		this.tAlgmntSalesTeam = tAlgmntSalesTeam;

	}

	/*public Long getAlgmntId() {
		return algmntId;
	}

	public void setAlgmntId(Long algmntId) {
		this.algmntId = algmntId;
	}

	public Long getSalesTeamId() {
		return salesTeamId;
	}

	public void setSalesTeamId(Long salesTeamId) {
		this.salesTeamId = salesTeamId;
	}

	public Long getBussUnitId() {
		return bussUnitId;
	}

	public void setBussUnitId(Long bussUnitId) {
		this.bussUnitId = bussUnitId;
	}*/
	


	/**
	 * 
	 * @generated
	 */
	public Integer getCustTypeId() {
		return custTypeId;
	}
	

	/**
	 * 
	 * @generated
	 */
	public void setCustTypeId(Integer custTypeId) {
		this.custTypeId = custTypeId;
	}
	

	/**
	 * 
	 * @generated
	 */
	public Set<TCustAffHier> getTCustAffHiersForAffTypeIds() {
		return this.tCustAffHiersForAffTypeIds;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCustAffHiersForAffTypeIds(final Set<TCustAffHier> tCustAffHiersForAffTypeIds) {
		this.tCustAffHiersForAffTypeIds = tCustAffHiersForAffTypeIds;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TCustAffHier> getTCustAffHiersForPrnAffTypeIds() {
		return this.tCustAffHiersForPrnAffTypeIds;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCustAffHiersForPrnAffTypeIds(final Set<TCustAffHier> tCustAffHiersForPrnAffTypeIds) {
		this.tCustAffHiersForPrnAffTypeIds = tCustAffHiersForPrnAffTypeIds;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCustAlgmntAff that) {
		setAffTypeId(that.getAffTypeId());
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
		result = prime * result + ((affTypeId == null) ? 0 : affTypeId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("affTypeId=[").append(affTypeId).append("] ");
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
		final TCustAlgmntAff other = (TCustAlgmntAff) obj;
		if (affTypeId == null) {
			if (other.affTypeId != null) {
				return false;
			}
		} else if (!affTypeId.equals(other.affTypeId)) {
			return false;
		}
		return true;
	}
}
