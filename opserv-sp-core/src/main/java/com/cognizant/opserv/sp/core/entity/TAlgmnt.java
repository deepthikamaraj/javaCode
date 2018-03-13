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
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TAlgmnt 
 * The corresponding mapping table is t_algmnt 
 */

@NamedQueries({
	@NamedQuery(name = "FindAllTAlgmnts", query = "select myTAlgmnt from TAlgmnt myTAlgmnt  where myTAlgmnt.tenantId=?1"),
	@NamedQuery(name = "FindTAlgmntsforActiveProposed", query = "select myTAlgmnt from TAlgmnt myTAlgmnt  where myTAlgmnt.tenantId=?1 and (myTAlgmnt.algmntStatusId=1 and myTAlgmnt.effEndDt>CURRENT_DATE) OR (myTAlgmnt.algmntStatusId=3 and myTAlgmnt.effStartDt>CURRENT_DATE)"),
		@NamedQuery(name = "CountTAlgmnts", query = "Select Count(c) from TAlgmnt c"),
		@NamedQuery(name = "FindTAlgmntByTBussUnit", query = "select myTAlgmnt from TAlgmnt myTAlgmnt where myTAlgmnt.tBussUnit = ?1 "),
		@NamedQuery(name = "CountTAlgmntsByTBussUnit", query = "select Count(myTAlgmnt) from TAlgmnt myTAlgmnt where myTAlgmnt.tBussUnit = ?1 "),
		@NamedQuery(name = "FindTAlgmntByTAlgmntStatus", query = "select myTAlgmnt from TAlgmnt myTAlgmnt where myTAlgmnt.algmntStatusId = ?1 "),
		@NamedQuery(name = "FindAllTAlgmntsByName", query = "select myTAlgmnt from TAlgmnt myTAlgmnt where myTAlgmnt.algmntName = ?1"),
		@NamedQuery(name = "FindActiveAlgmntByDate", query = "select myTAlgmnt from TAlgmnt myTAlgmnt where  myTAlgmnt.tenantId=?1 AND (myTAlgmnt.effEndDt>=?2 OR myTAlgmnt.effEndDt IS NULL)"),
		@NamedQuery(name = "FindActiveAlgmntByDateAndAlgId", query = "select myTAlgmnt from TAlgmnt myTAlgmnt where myTAlgmnt.algmntId=?1 AND myTAlgmnt.tenantId=?2 AND (myTAlgmnt.effEndDt>=?3 OR myTAlgmnt.effEndDt IS NULL)"),
		@NamedQuery(name = "CountTAlgmntsByTAlgmntStatus", query = "select Count(myTAlgmnt) from TAlgmnt myTAlgmnt where myTAlgmnt.algmntStatusId = ?1 "),
		@NamedQuery(name = "FindAlignments", query = "select myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId,myTSalesTeam.tSalesTeamId.salesTeamId,myTAlgmnt.algmntId,myTAlgmnt.algmntName,myTSalesTeam.salesTeamName,myTAlgmntStatus.statusDesc,myTBussUnit.bussUnitName from TAlgmnt myTAlgmnt,TSalesTeam myTSalesTeam,TAlgmntStatus myTAlgmntStatus,TBussUnit myTBussUnit,TAlgmntSalesTeam myTAlgmntSalesTeam where myTAlgmntSalesTeam.tenantId=myTAlgmnt.tenantId and myTAlgmntSalesTeam.tenantId= myTSalesTeam.tenantId and myTAlgmntSalesTeam.tenantId=myTAlgmntStatus.tenantId and myTAlgmntSalesTeam.tenantId=  myTBussUnit.tenantId and myTAlgmnt.algmntStatusId = myTAlgmntStatus.tAlgmntStatusId.statusId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId=myTSalesTeam.tSalesTeamId.salesTeamId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=myTSalesTeam.tSalesTeamId.bussUnitId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = myTAlgmnt.algmntId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=myTBussUnit.bussUnitId  and myTSalesTeam.salesTeamName like ?1 AND myTAlgmnt.algmntName like ?2 AND myTAlgmntStatus.tAlgmntStatusId.statusId = ?3 AND myTBussUnit.bussUnitName like ?4 AND myTAlgmntSalesTeam.tenantId = ?5"),
		@NamedQuery(name = "FindAllTAlgmntsByStatusIdActive", query = "select myTAlgmnt from TAlgmnt myTAlgmnt  where  myTAlgmnt.tenantId= ?1 and myTAlgmnt.effStartDt <= CURRENT_DATE and (myTAlgmnt.effEndDt >= CURRENT_DATE OR myTAlgmnt.effEndDt is null) "),
		@NamedQuery(name = "FindAllTAlgmntsByStatusIdProposed", query = "select myTAlgmnt from TAlgmnt myTAlgmnt  where  myTAlgmnt.tenantId= ?1 and myTAlgmnt.effStartDt > CURRENT_DATE and (myTAlgmnt.effEndDt >= CURRENT_DATE OR myTAlgmnt.effEndDt is null) "),
		@NamedQuery(name = "FindAllActiveProposedAlgmnts", query = "select myTAlgmnt from TAlgmnt myTAlgmnt  where myTAlgmnt.tenantId= ?1 and (myTAlgmnt.effEndDt >= CURRENT_DATE OR myTAlgmnt.effEndDt is null) "),
		@NamedQuery(name = "FindAlgmntByAlgId", query = "select myTAlgmnt from TAlgmnt myTAlgmnt where myTAlgmnt.algmntId=?1 AND myTAlgmnt.tenantId=?2 ")
})
@Table(name = "t_algmnt", uniqueConstraints = @UniqueConstraint(columnNames = { "algmnt_id", "algmnt_name" }))
@Entity
public class TAlgmnt implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "algmnt_id", nullable = false, length = 255)
	private Long algmntId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 75)
	@Column(name = "algmnt_name", nullable = false, length = 75)
	private String algmntName;

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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "buss_unit_id", referencedColumnName = "buss_unit_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TBussUnit tBussUnit;
	
	@NotNull
	@Column(name = "status_id", nullable = false, length = 255)
	private Integer algmntStatusId;

	/*@ManyToOne
	@JoinColumn(name = "status_id", referencedColumnName = "status_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TAlgmntStatus tAlgmntStatus;*/

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAlgmnt")
	@Fetch(FetchMode.SUBSELECT)
	private Set<TAlgmntSalesTeam> tAlgmntSalesTeamss;

	/**
	 *
	 * @generated
	 */
	public TAlgmnt() {
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

	public void setAlgmntName(final String algmntName) {
		this.algmntName = algmntName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAlgmntName() {
		return this.algmntName;
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
	public TBussUnit getTBussUnit() {
		return this.tBussUnit;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTBussUnit(final TBussUnit tBussUnit) {
		this.tBussUnit = tBussUnit;

	}

	public Integer getAlgmntStatusId() {
		return algmntStatusId;
	}

	public void setAlgmntStatusId(Integer algmntStatusId) {
		this.algmntStatusId = algmntStatusId;
	}
	
	/**
	 * 
	 * @generated
	 */
	/*public TAlgmntStatus getTAlgmntStatus() {
		return this.tAlgmntStatus;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public void setTAlgmntStatus(final TAlgmntStatus tAlgmntStatus) {
		this.tAlgmntStatus = tAlgmntStatus;

	}*/

	/**
	 * 
	 * @generated
	 */
	public Set<TAlgmntSalesTeam> getTAlgmntSalesTeamss() {
		return this.tAlgmntSalesTeamss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAlgmntSalesTeamss(final Set<TAlgmntSalesTeam> tAlgmntSalesTeamss) {
		this.tAlgmntSalesTeamss = tAlgmntSalesTeamss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TAlgmnt that) {
		setAlgmntId(that.getAlgmntId());
		setAlgmntName(that.getAlgmntName());
		setEffStartDt(that.getEffStartDt());
		setEffEndDt(that.getEffEndDt());
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
		result = prime * result + ((algmntId == null) ? 0 : algmntId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("algmntId=[").append(algmntId).append("] ");
		buffer.append("algmntName=[").append(algmntName).append("] ");
		buffer.append("effStartDt=[").append(effStartDt).append("] ");
		buffer.append("effEndDt=[").append(effEndDt).append("] ");
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
		final TAlgmnt other = (TAlgmnt) obj;
		if (algmntId == null) {
			if (other.algmntId != null) {
				return false;
			}
		} else if (!algmntId.equals(other.algmntId)) {
			return false;
		}
		return true;
	}
}
