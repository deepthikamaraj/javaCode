package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

/** 
 * JPA class representing TAlgmntTmpl 
 * The corresponding mapping table is t_algmnt_tmpl 
 */
@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTAlgmntTmpls", query = "select myTAlgmntTmpl from TAlgmntTmpl myTAlgmntTmpl"),
		@NamedQuery(name = "FindTAlgnmtTmplIdByAlBuSTBussObjId", query = "select myTAlgmntTmpl.tBussObjTmpl.tmplId from TAlgmntTmpl myTAlgmntTmpl,TBussObjTmpl tBussObjTmp where tBussObjTmp.tmplId=myTAlgmntTmpl.tBussObjTmpl.tmplId and myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 " +
				"and myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId =?2 and myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId =?3  " +
				"and myTAlgmntTmpl.tBussObjTmpl.tBussObj.bussObjId = ?4 and myTAlgmntTmpl.tenantId =?5 and tBussObjTmp.activeFlag='Y' and myTAlgmntTmpl.activeFlag='Y'"),
		@NamedQuery(name = "CountTAlgmntTmpls", query = "Select Count(c) from TAlgmntTmpl c"),
		@NamedQuery(name = "FindTAlgmntTmplByTBussObjTmpl", query = "select myTAlgmntTmpl from TAlgmntTmpl myTAlgmntTmpl where myTAlgmntTmpl.tBussObjTmpl = ?1 "),
		@NamedQuery(name = "CountTAlgmntTmplsByTBussObjTmpl", query = "select Count(myTAlgmntTmpl) from TAlgmntTmpl myTAlgmntTmpl where myTAlgmntTmpl.tBussObjTmpl = ?1 "),
		@NamedQuery(name = "FindTAlgmntTmplByTAlgmntSalesTeam", query = "select myTAlgmntTmpl from TAlgmntTmpl myTAlgmntTmpl where myTAlgmntTmpl.tAlgmntSalesTeam = ?1 "),
		@NamedQuery(name = "FindTAlgnmtTemptIdByAlgBuSalesIDs", query = "select myTAlgmntTmpl from TAlgmntTmpl myTAlgmntTmpl where myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?1 and myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId =?2 and myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId =?3  and myTAlgmntTmpl.tenantId =?4 and myTAlgmntTmpl.activeFlag='Y'"),
		@NamedQuery(name = "CountTAlgmntTmplsByTAlgmntSalesTeam", query = "select Count(myTAlgmntTmpl) from TAlgmntTmpl myTAlgmntTmpl where myTAlgmntTmpl.tAlgmntSalesTeam = ?1 "),
		@NamedQuery(name = "FindTAlgmntTmplByTAlgmnt", query = "select myTAlgmntTmpl from TAlgmntTmpl myTAlgmntTmpl where myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmnt = ?1 and myTAlgmntTmpl.tenantId = ?2"),
		@NamedQuery(name = "FindTAlgnmtTmplIdByAlBuSTBussObjTenant", query = "select myTAlgmntTmpl from TAlgmntTmpl myTAlgmntTmpl where myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 " +
				"and myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId =?2 and myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId =?3  " +
				"and myTAlgmntTmpl.tBussObjTmpl.tBussObj.bussObjName = ?4 and myTAlgmntTmpl.tenantId =?5"),
		@NamedQuery(name = "FindAlgmntOfTemplate", query = "select myTAlgmntTmpl.algmntTmplId,myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmnt.algmntStatusId from TAlgmntTmpl myTAlgmntTmpl" +
				" where myTAlgmntTmpl.tBussObjTmpl.tmplId = ?1 and myTAlgmntTmpl.tenantId = ?2 and myTAlgmntTmpl.activeFlag = ?3"),
		@NamedQuery(name = "FindTAlgnmtTmplIdByAlBuSTBussObj", query = "select myTAlgmntTmpl.tBussObjTmpl.tmplId from TAlgmntTmpl myTAlgmntTmpl where myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 " +
				"and myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId =?2 and myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId =?3  " +
				"and myTAlgmntTmpl.tBussObjTmpl.tBussObj.bussObjName = ?4 and myTAlgmntTmpl.tenantId =?5"),
		@NamedQuery(name = "FindActiveAlgmntTmpl", query = "SELECT myTAlgmntTmpl.algmntTmplId, myTAlgmntTmpl.effStartDt, myTAlgmnt.algmntName, " +
				" myTBussUnit.bussUnitName, myTSalesTeam.salesTeamName, myTAlgmnt.algmntId, myTBussUnit.bussUnitId, myTSalesTeam.tSalesTeamId.salesTeamId " +
				" FROM TAlgmntTmpl myTAlgmntTmpl JOIN myTAlgmntTmpl.tAlgmntSalesTeam myTAlgmntSalesTeam " +
				" JOIN myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmnt myTAlgmnt JOIN myTAlgmntTmpl.tAlgmntSalesTeam.tSalesTeam.tBussUnit myTBussUnit " +
				" JOIN myTAlgmntSalesTeam.tSalesTeam myTSalesTeam " +
				" WHERE myTAlgmntTmpl.tBussObjTmpl.tmplId = ?1 AND myTAlgmntTmpl.activeFlag = ?2 " +
				" AND myTAlgmntTmpl.effStartDt <= ?3 AND (myTAlgmntTmpl.effEndDt IS NULL OR myTAlgmntTmpl.effEndDt >= ?4 ) " +
				" AND myTAlgmnt.effStartDt <= ?3 AND (myTAlgmnt.effEndDt IS NULL OR myTAlgmnt.effEndDt > ?4 ) AND myTAlgmntTmpl.tenantId = ?5 " +
				" AND  myTAlgmntTmpl.tenantId = myTAlgmntSalesTeam.tenantId ")
		})
@Table(name = "t_algmnt_tmpl", uniqueConstraints = @UniqueConstraint(columnNames = { "algmnt_tmpl_id" }))
public class TAlgmntTmpl implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "algmnt_tmpl_id", nullable = false, length = 255)
	private Integer algmntTmplId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "eff_start_dt", nullable = true, length = 10)
	private Date effStartDt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "eff_end_dt", nullable = true, length = 10)
	private Date effEndDt;

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

	@ManyToOne
	@JoinColumn(name = "tmpl_id", referencedColumnName = "tmpl_id", nullable = false, insertable = true, updatable = true)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TBussObjTmpl tBussObjTmpl;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "algmnt_id", referencedColumnName = "algmnt_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "buss_unit_id", referencedColumnName = "buss_unit_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "sales_team_id", referencedColumnName = "sales_team_id", nullable = false, insertable = true, updatable = true) })
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TAlgmntSalesTeam tAlgmntSalesTeam;

	/**
	 *
	 * @generated
	 */
	public TAlgmntTmpl() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setAlgmntTmplId(final Integer algmntTmplId) {
		this.algmntTmplId = algmntTmplId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getAlgmntTmplId() {
		return this.algmntTmplId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setEffStartDt(final Date effStartDt) {
		if (effStartDt != null) {
			this.effStartDt = (Date) effStartDt.clone();
		} else {
			this.effStartDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getEffStartDt() {
		if (this.effStartDt != null) {
			return (Date) this.effStartDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setEffEndDt(final Date effEndDt) {
		if (effEndDt != null) {
			this.effEndDt = (Date) effEndDt.clone();
		} else {
			this.effEndDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getEffEndDt() {
		if (this.effEndDt != null) {
			return (Date) this.effEndDt.clone();
		} else {
			return null;
		}
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
	public TBussObjTmpl getTBussObjTmpl() {
		return this.tBussObjTmpl;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTBussObjTmpl(final TBussObjTmpl tBussObjTmpl) {
		this.tBussObjTmpl = tBussObjTmpl;

	}

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

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TAlgmntTmpl that) {
		setAlgmntTmplId(that.getAlgmntTmplId());
		setEffStartDt(that.getEffStartDt());
		setEffEndDt(that.getEffEndDt());
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
		result = prime * result + ((algmntTmplId == null) ? 0 : algmntTmplId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("algmntTmplId=[").append(algmntTmplId).append("] ");
		buffer.append("effStartDt=[").append(effStartDt).append("] ");
		buffer.append("effEndDt=[").append(effEndDt).append("] ");
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
		final TAlgmntTmpl other = (TAlgmntTmpl) obj;
		if (algmntTmplId == null) {
			if (other.algmntTmplId != null) {
				return false;
			}
		} else if (!algmntTmplId.equals(other.algmntTmplId)) {
			return false;
		}
		return true;
	}
}
