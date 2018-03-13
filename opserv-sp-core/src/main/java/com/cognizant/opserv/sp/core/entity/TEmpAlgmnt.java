package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

/** 
 * JPA class representing TEmpAlgmnt 
 * The corresponding mapping table is t_emp_algmnt 
 */

@Entity
@Audited
@NamedQueries({
	    
		@NamedQuery(name = "FindAllTEmpAlgmntsByStaffId", query = "select myTEmpAlgmnt from TEmpAlgmnt myTEmpAlgmnt where myTEmpAlgmnt.tPers.staffId = ?1 and myTEmpAlgmnt.tenantId = ?2 AND myTEmpAlgmnt.activeFlag = 'Y' "),
		@NamedQuery(name = "FindPrimaryTEmpAlgmnt", query = "select myTEmpAlgmnt.empAlgmntId,myTEmpAlgmnt.tPers.staffId,  " +
				" myTEmpAlgmnt.effStartDt, myTEmpAlgmnt.effEndDt,myTEmpAlgmnt.allocTypeId  " +
				"from TEmpAlgmnt myTEmpAlgmnt where myTEmpAlgmnt.algmntId = ?1 " +
				"AND myTEmpAlgmnt.bussUnitId = ?2 AND myTEmpAlgmnt.salesTeamId = ?3 " +
				"AND myTEmpAlgmnt.salesPosId = ?4 AND myTEmpAlgmnt.salesHierId = ?5 " +
			"AND myTEmpAlgmnt.allocTypeId = ?6 AND myTEmpAlgmnt.tPers.statusId = ?7 AND myTEmpAlgmnt.tenantId = ?8 AND myTEmpAlgmnt.effEndDt >=  myTEmpAlgmnt.effStartDt  AND myTEmpAlgmnt.activeFlag = 'Y'"),
		@NamedQuery(name = "findEmpAlgmntByIds",query = "select myTEmpAlgmnt.empAlgmntId, myTEmpAlgmnt.tPers.staffId , myTEmpAlgmnt.effStartDt, myTEmpAlgmnt.effEndDt " +
				"from TEmpAlgmnt myTEmpAlgmnt where myTEmpAlgmnt.tPers.staffId = ?1 " +
				"AND myTEmpAlgmnt.algmntId = ?2 AND myTEmpAlgmnt.bussUnitId = ?3 " +
				"AND myTEmpAlgmnt.salesTeamId = ?4 AND myTEmpAlgmnt.salesPosId = ?5 " +
				"AND myTEmpAlgmnt.salesHierId = ?6 AND myTEmpAlgmnt.tenantId = ?7  AND myTEmpAlgmnt.activeFlag = 'Y'"),
		@NamedQuery(name = "FindEmpAlgmnt",query = "select myTEmpAlgmnt.empAlgmntId, myTEmpAlgmnt.tPers.staffId , myTEmpAlgmnt.effStartDt, myTEmpAlgmnt.effEndDt " +
				"from TEmpAlgmnt myTEmpAlgmnt where myTEmpAlgmnt.tPers.staffId IN ?1 " +
				"AND myTEmpAlgmnt.algmntId = ?2 AND myTEmpAlgmnt.bussUnitId = ?3 " +
				"AND myTEmpAlgmnt.salesTeamId = ?4 AND myTEmpAlgmnt.salesPosId = ?5 " +
				"AND myTEmpAlgmnt.salesHierId = ?6 AND myTEmpAlgmnt.tenantId = ?7  AND myTEmpAlgmnt.activeFlag = 'Y'"),

		
		@NamedQuery(name = "findTEmpAlgmntForSalesPosId", query = "select myTEmpAlgmnt from TEmpAlgmnt myTEmpAlgmnt where myTEmpAlgmnt.salesPosId =?1 and myTEmpAlgmnt.salesHierId =?2 and myTEmpAlgmnt.algmntId=?3 and myTEmpAlgmnt.bussUnitId=?4 and  myTEmpAlgmnt.salesTeamId=?5 and myTEmpAlgmnt.tenantId=?6 AND myTEmpAlgmnt.activeFlag = 'Y'"),
			
	@NamedQuery(name = "FindPrimaryAllocTEmpAlgmntsForAllSp", query = "select myTEmpAlgmnt"+
					" from TEmpAlgmnt myTEmpAlgmnt LEFT JOIN FETCH myTEmpAlgmnt.tPers myTPers "+
					"WHERE myTEmpAlgmnt.algmntId = ?1 AND myTEmpAlgmnt.bussUnitId = ?2 AND myTEmpAlgmnt.salesTeamId = ?3 "+ 
					"AND myTEmpAlgmnt.allocTypeId = ?4 AND myTEmpAlgmnt.tenantId = ?5 "+ 
					"AND myTEmpAlgmnt.activeFlag = 'Y' " +
					"AND myTPers.statusId = ?6 "),
	@NamedQuery(name = "fetchEmailIdFrEmp", query = "SELECT tPersContact.contactDetail" +
												   " FROM TEmpAlgmnt tEmpAlgmnt, TPers tPers, TPersContact tPersContact,TContactType tContactType" +
												   " where tEmpAlgmnt.tenantId = tPers.tenantId" + 
												   " AND tEmpAlgmnt.tPers.staffId = tPers.staffId" +
												   " AND tPers.staffId = tPersContact.staffId" +
												   " AND tPers.tenantId = tPersContact.tenantId" +
												   " AND tPersContact.contactTypeId = tContactType.contactTypeId" +
												   " AND tPersContact.tenantId = tContactType.tenantId" +
												   " AND tEmpAlgmnt.salesPosId IN ?1" +
												   " AND tEmpAlgmnt.activeFlag = 'Y'" +
												   " AND tEmpAlgmnt.tenantId = ?2"+
												   " AND tPersContact.contactTypeId = 1"),
    @NamedQuery(name = "FindEmpDetails", query = "select myTEmpAlgmnt.empAlgmntId, myTEmpAlgmnt.effStartDt, myTEmpAlgmnt.effEndDt, myTPers.staffId, myTPers.firstName," +
												    " myTPers.middleName, myTPers.lastName, myTPers.empName , myTPersStatus.tPersStatusId.statusId, myTEmpAlgmnt.tPers.clientId, myTPersStatus.statusCd, myTEmpAlgmnt.allocTypeId " +
															" from TEmpAlgmnt myTEmpAlgmnt, TPers myTPers, TPersStatus myTPersStatus " +
															"WHERE myTEmpAlgmnt.tPers.staffId = myTPers.staffId AND myTEmpAlgmnt.tenantId = myTPers.tenantId " +
															"AND myTPersStatus.tPersStatusId.statusId = myTPers.statusId " +
															"AND myTPersStatus.tenantId = myTPers.tenantId " +				
															"AND myTEmpAlgmnt.algmntId = ?1 AND myTEmpAlgmnt.bussUnitId = ?2 AND myTEmpAlgmnt.salesTeamId = ?3 " +
															"AND myTEmpAlgmnt.salesPosId = ?4 AND myTEmpAlgmnt.salesHierId = ?5 " +
															//"AND myTEmpAlgmnt.activeFlag = ?6 " +
															//	"AND ( myTEmpAlgmnt.effEndDt >= ?7 OR myTEmpAlgmnt.effEndDt is null ) " +
															"AND myTEmpAlgmnt.tenantId = ?6 AND myTPersStatus.locale = ?7 "),
	@NamedQuery(name = "fetchStaffIds", query = "SELECT tPersContact.staffId" +
						            " FROM TEmpAlgmnt tEmpAlgmnt, TPers tPers, TPersContact tPersContact,TContactType tContactType" +
						            " where tEmpAlgmnt.tenantId = tPers.tenantId" + 
						            " AND tEmpAlgmnt.tPers.staffId = tPers.staffId" +
						            " AND tPers.staffId = tPersContact.staffId" +
						            " AND tPers.tenantId = tPersContact.tenantId" +
						            " AND tPersContact.contactTypeId = tContactType.contactTypeId" +
						            " AND tPersContact.tenantId = tContactType.tenantId" +
						            " AND tEmpAlgmnt.salesPosId IN ?1" +
						            " AND tEmpAlgmnt.activeFlag = 'Y'" +
						            " AND tEmpAlgmnt.tenantId = ?2"+
						            " AND tPersContact.contactTypeId = 1"),

					
})
@Table(name = "t_emp_algmnt" , uniqueConstraints = @UniqueConstraint(columnNames = { "emp_algmnt_id" }))
public class TEmpAlgmnt implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@GeneratedValue(generator = "uuid")
	//@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "emp_algmnt_id", nullable = false, length = 225)
	private Long empAlgmntId;
	

	/**
	 * 
	 * @generated
	 */
	@Column(name = "reason_id", nullable = true, length = 255)
	private Integer reasonId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "eff_start_dt", nullable = false, length = 10)
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
	@Column(name = "alloc_type_id", nullable = false, length = 255)
	private Integer allocTypeId;
	
	

	public Integer getAllocTypeId() {
		return allocTypeId;
	}

	public void setAllocTypeId(Integer allocTypeId) {
		this.allocTypeId = allocTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	@Column(name = "sys_role_id", nullable = true, length = 255)
	private Integer sysRoleId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "alloc_pct", nullable = true, length = 255)
	private Integer allocPct;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "sales_pos_id", nullable = false, length = 255)
	private Long salesPosId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "sales_hier_id", nullable = false, length = 255)
	private Long salesHierId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "org_role_id", nullable = false, length = 255)
	private Integer orgRoleId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "algmnt_id", nullable = false, length = 255)
	private Long algmntId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "buss_unit_id", nullable = false, length = 255)
	private Long bussUnitId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "sales_team_id", nullable = false, length = 255)
	private Long salesTeamId;

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
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "staff_id", referencedColumnName = "staff_id", nullable = false, insertable = true, updatable = true)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TPers tPers;
	


    
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tEmpAlgmnt",fetch = FetchType.LAZY  )
	@Fetch(FetchMode.SUBSELECT)
	@NotAudited
	private Set<TEmpAlgmntAttr> tEmpAlgmntAttrss;
	
	
	
	/**
	 *
	 * @generated
	 */
	public TEmpAlgmnt() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setEmpAlgmntId(final Long empAlgmntId) {
		this.empAlgmntId = empAlgmntId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getEmpAlgmntId() {
		return this.empAlgmntId;
	}


	/**
	 * 
	 * @generated
	 */

	public void setReasonId(final Integer reasonId) {
		this.reasonId = reasonId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getReasonId() {
		return this.reasonId;
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

	/*public void setTAllocTypeId(final TAllocTypeId tAllocTypeId) {
		this.tAllocTypeId = tAllocTypeId;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public TAllocTypeId getTAllocTypeId() {
		return this.tAllocTypeId;
	}*/
	/**
	 * 
	 * @generated
	 */

	public void setSysRoleId(final Integer sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getSysRoleId() {
		return this.sysRoleId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAllocPct(final Integer allocPct) {
		this.allocPct = allocPct;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getAllocPct() {
		return this.allocPct;
	}

	/**
	 * 
	 * @generated
	 */

	public void setSalesPosId(final Long salesPosId) {
		this.salesPosId = salesPosId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getSalesPosId() {
		return this.salesPosId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setSalesHierId(final Long salesHierId) {
		this.salesHierId = salesHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getSalesHierId() {
		return this.salesHierId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setOrgRoleId(final Integer orgRoleId) {
		this.orgRoleId = orgRoleId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getOrgRoleId() {
		return this.orgRoleId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAlgmntId(final Long algmntId) {
		this.algmntId = algmntId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getAlgmntId() {
		return this.algmntId;
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

	public void setSalesTeamId(final Long salesTeamId) {
		this.salesTeamId = salesTeamId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getSalesTeamId() {
		return this.salesTeamId;
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
	public TPers getTPers() {
		return this.tPers;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPers(final TPers tPers) {
		this.tPers = tPers;

	}

	/**
	 * 
	 * @generated
	 */
	public Set<TEmpAlgmntAttr> getTEmpAlgmntAttrss() {
		return this.tEmpAlgmntAttrss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTEmpAlgmntAttrss(final Set<TEmpAlgmntAttr> tEmpAlgmntAttrss) {
		this.tEmpAlgmntAttrss = tEmpAlgmntAttrss;
	}
	


	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TEmpAlgmnt that) {
		setEmpAlgmntId(that.getEmpAlgmntId());
		setReasonId(that.getReasonId());
		setEffStartDt(that.getEffStartDt());
		setEffEndDt(that.getEffEndDt());
		setActiveFlag(that.getActiveFlag());
		//setAllocTypeId(that.getAllocTypeId());
		setSysRoleId(that.getSysRoleId());
		setAllocPct(that.getAllocPct());
		setSalesPosId(that.getSalesPosId());
		setSalesHierId(that.getSalesHierId());
		setOrgRoleId(that.getOrgRoleId());
		setAlgmntId(that.getAlgmntId());
		setBussUnitId(that.getBussUnitId());
		setSalesTeamId(that.getSalesTeamId());
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
		result = prime * result + ((empAlgmntId == null) ? 0 : empAlgmntId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("empAlgmntId=[").append(empAlgmntId).append("] ");
		buffer.append("reasonId=[").append(reasonId).append("] ");
		buffer.append("effStartDt=[").append(effStartDt).append("] ");
		buffer.append("effEndDt=[").append(effEndDt).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		//buffer.append("allocTypeId=[").append(allocTypeId).append("] ");
		buffer.append("sysRoleId=[").append(sysRoleId).append("] ");
		buffer.append("allocPct=[").append(allocPct).append("] ");
		buffer.append("salesPosId=[").append(salesPosId).append("] ");
		buffer.append("salesHierId=[").append(salesHierId).append("] ");
		buffer.append("orgRoleId=[").append(orgRoleId).append("] ");
		buffer.append("algmntId=[").append(algmntId).append("] ");
		buffer.append("bussUnitId=[").append(bussUnitId).append("] ");
		buffer.append("salesTeamId=[").append(salesTeamId).append("] ");
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
		final TEmpAlgmnt other = (TEmpAlgmnt) obj;
		if (empAlgmntId == null) {
			if (other.empAlgmntId != null) {
				return false;
			}
		} else if (!empAlgmntId.equals(other.empAlgmntId)) {
			return false;
		}
		return true;
	}
}
