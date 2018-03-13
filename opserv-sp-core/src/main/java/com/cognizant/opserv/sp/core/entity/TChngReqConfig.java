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

import org.hibernate.validator.constraints.Length;

/**
 * JPA class representing TChngReqConfig The corresponding mapping table is
 * t_chng_req_config
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTChngReqConfigs", query = "select myTChngReqConfig from TChngReqConfig myTChngReqConfig"),
		@NamedQuery(name = "CountTChngReqConfigs", query = "Select Count(c) from TChngReqConfig c"),
		@NamedQuery(name = "FindTChngReqConfigByTChngReqTrigger", query = "select myTChngReqConfig from TChngReqConfig myTChngReqConfig where myTChngReqConfig.tChngReqTrigger = ?1 "),
		@NamedQuery(name = "CountTChngReqConfigsByTChngReqTrigger", query = "select Count(myTChngReqConfig) from TChngReqConfig myTChngReqConfig where myTChngReqConfig.tChngReqTrigger = ?1 "),
		@NamedQuery(name = "FindTChngReqConfigByTAlgmntSalesTeam", query = "select myTChngReqConfig from TChngReqConfig myTChngReqConfig where myTChngReqConfig.tAlgmntSalesTeam = ?1 "),
		@NamedQuery(name = "CountTChngReqConfigsByTAlgmntSalesTeam", query = "select Count(myTChngReqConfig) from TChngReqConfig myTChngReqConfig where myTChngReqConfig.tAlgmntSalesTeam = ?1 "),


		/* Added by 407986 */
		// @NamedQuery(name = "GetTChngReqConfig", query =
		// "select myTChngReqConfig from TChngReqConfig myTChngReqConfig where myTChngReqConfig.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId= ?1 and myTChngReqConfig.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId= ?2 and myTChngReqConfig.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId= ?3 and myTChngReqConfig.tChngReqTrigger.triggerId= ?4 and tenantId =?5 ")

		@NamedQuery(name = "GetTChngReqConfig", query = "select myTChngReqConfig from TChngReqConfig myTChngReqConfig where myTChngReqConfig.salesTeamId= ?1 and myTChngReqConfig.alignmentId= ?2 and myTChngReqConfig.bussUnitId = ?3 and myTChngReqConfig.tChngReqTrigger.triggerId= ?4 and tenantId =?5 "),
		@NamedQuery(name = "getTChngReqConfigsByTChngReqTriggerId", query = "select myTChngReqConfig.chngReqConfigId,"
				+ "myTChngReqConfig.apprStageCnt,"
				+ "myTChngReqConfig.escApplFlag,"
				+ "myTChngReqConfig.dlgApplFlag,"
				+ "myTChngReqConfig.duration,"
				+ "myTChngReqConfig.apprHierFlag,"
				+ "myTChngReqConfig.autoAction,"
				+ "myTChngReqConfig.prApprTypeId,"
				+ "myTChngReqConfig.secApprTypeId  from TChngReqConfig myTChngReqConfig where myTChngReqConfig.tChngReqTrigger.triggerId= ?1 and  myTChngReqConfig.alignmentId= ?2 and myTChngReqConfig.bussUnitId = ?3 and myTChngReqConfig.salesTeamId= ?4 and tenantId =?5"),
		@NamedQuery(name = "getTChngReqConfigsByTriggerId", query = "select myTChngReqConfig from TChngReqConfig myTChngReqConfig where myTChngReqConfig.tChngReqTrigger.triggerId= ?1 and  myTChngReqConfig.alignmentId= ?2 and myTChngReqConfig.bussUnitId = ?3 and myTChngReqConfig.salesTeamId= ?4 and tenantId =?5 and myTChngReqConfig.activeFlag ='Y'"),
		@NamedQuery(name = "findTChngReqTriggersByAlBuSt", query = "select obj from TChngReqConfig obj where obj.alignmentId =?1 and obj.bussUnitId =?2 and obj.salesTeamId =?3 and obj.tenantId =?4 and obj.activeFlag ='Y'"),
		@NamedQuery(name = "findprmySecdryByAlBuStTrgerId", query = "select obj.chngReqConfigId,obj.prApprTypeId,obj.secApprTypeId from TChngReqConfig obj where obj.tChngReqTrigger.triggerId=?1 and  obj.alignmentId =?2 and obj.bussUnitId =?3 and obj.salesTeamId =?4 and obj.tenantId =?5"), })
@Table(name = "t_chng_req_config", uniqueConstraints = @UniqueConstraint(columnNames = { "chng_req_config_id" }))
public class TChngReqConfig implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "chng_req_config_id", nullable = false, length = 255)
	private Integer chngReqConfigId;

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
	@Column(name = "appr_stage_cnt", nullable = true, length = 255)
	private Short apprStageCnt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "esc_appl_flag", nullable = true, length = 1)
	private Character escApplFlag;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "dlg_appl_flag", nullable = true, length = 1)
	private Character dlgApplFlag;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "duration", nullable = true, length = 255)
	private Integer duration;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "rem_note_days", nullable = true, length = 255)
	private Short remNoteDays;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "appr_hier_flag", nullable = true, length = 1)
	private Character apprHierFlag;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 30)
	@Column(name = "auto_action", nullable = true, length = 30)
	private String autoAction;

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

	/**
	 * 
	 * @generated
	 */

	@Column(name = "pr_appr_type_id", nullable = true, length = 255)
	private Integer prApprTypeId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "sec_appr_type_id", nullable = true, length = 255)
	private Integer secApprTypeId;

	@ManyToOne
	@JoinColumn(name = "trigger_id", referencedColumnName = "trigger_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TChngReqTrigger tChngReqTrigger;

	@Column(name = "algmnt_id", nullable = false, length = 255)
	private Long alignmentId;

	@Column(name = "buss_unit_id", nullable = false, length = 255)
	private Long bussUnitId;

	@Column(name = "sales_team_id", nullable = false, length = 255)
	private Long salesTeamId;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "algmnt_id", referencedColumnName = "algmnt_id", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "buss_unit_id", referencedColumnName = "buss_unit_id", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "sales_team_id", referencedColumnName = "sales_team_id", nullable = false, insertable = false, updatable = false) })
	// @Valid
	private TAlgmntSalesTeam tAlgmntSalesTeam;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "chngReqConfigId")
	private Set<TChngReq> tChngReqss;

	/**
	 * 
	 * @generated
	 */
	public TChngReqConfig() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setChngReqConfigId(final Integer chngReqConfigId) {
		this.chngReqConfigId = chngReqConfigId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getChngReqConfigId() {
		return this.chngReqConfigId;
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

	public void setApprStageCnt(final Short apprStageCnt) {
		this.apprStageCnt = apprStageCnt;
	}

	/**
	 * 
	 * @generated
	 */
	public Short getApprStageCnt() {
		return this.apprStageCnt;
	}

	/**
	 * 
	 * @generated
	 */

	public void setEscApplFlag(final Character escApplFlag) {
		this.escApplFlag = escApplFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getEscApplFlag() {
		return this.escApplFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDlgApplFlag(final Character dlgApplFlag) {
		this.dlgApplFlag = dlgApplFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getDlgApplFlag() {
		return this.dlgApplFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDuration(final Integer duration) {
		this.duration = duration;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getDuration() {
		return this.duration;
	}

	/**
	 * 
	 * @generated
	 */

	public void setRemNoteDays(final Short remNoteDays) {
		this.remNoteDays = remNoteDays;
	}

	/**
	 * 
	 * @generated
	 */
	public Short getRemNoteDays() {
		return this.remNoteDays;
	}

	/**
	 * 
	 * @generated
	 */

	public void setApprHierFlag(final Character apprHierFlag) {
		this.apprHierFlag = apprHierFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getApprHierFlag() {
		return this.apprHierFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAutoAction(final String autoAction) {
		this.autoAction = autoAction;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAutoAction() {
		return this.autoAction;
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

	public void setPrApprTypeId(final Integer prApprTypeId) {
		this.prApprTypeId = prApprTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getPrApprTypeId() {
		return this.prApprTypeId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setSecApprTypeId(final Integer secApprTypeId) {
		this.secApprTypeId = secApprTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getSecApprTypeId() {
		return this.secApprTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public TChngReqTrigger getTChngReqTrigger() {
		return this.tChngReqTrigger;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTChngReqTrigger(final TChngReqTrigger tChngReqTrigger) {
		this.tChngReqTrigger = tChngReqTrigger;

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
	 * 
	 * @generated
	 */
	public Set<TChngReq> getTChngReqss() {
		return this.tChngReqss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTChngReqss(final Set<TChngReq> tChngReqss) {
		this.tChngReqss = tChngReqss;
	}

	public Long getAlignmentId() {
		return alignmentId;
	}

	public void setAlignmentId(Long alignmentId) {
		this.alignmentId = alignmentId;
	}

	public Long getBussUnitId() {
		return bussUnitId;
	}

	public void setBussUnitId(Long bussUnitId) {
		this.bussUnitId = bussUnitId;
	}

	public Long getSalesTeamId() {
		return salesTeamId;
	}

	public void setSalesTeamId(Long salesTeamId) {
		this.salesTeamId = salesTeamId;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TChngReqConfig that) {
		setChngReqConfigId(that.getChngReqConfigId());
		setActiveFlag(that.getActiveFlag());
		setApprStageCnt(that.getApprStageCnt());
		setEscApplFlag(that.getEscApplFlag());
		setDlgApplFlag(that.getDlgApplFlag());
		setDuration(that.getDuration());
		setRemNoteDays(that.getRemNoteDays());
		setApprHierFlag(that.getApprHierFlag());
		setAutoAction(that.getAutoAction());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setPrApprTypeId(that.getPrApprTypeId());
		setSecApprTypeId(that.getSecApprTypeId());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result
				+ ((chngReqConfigId == null) ? 0 : chngReqConfigId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("chngReqConfigId=[").append(chngReqConfigId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("apprStageCnt=[").append(apprStageCnt).append("] ");
		buffer.append("escApplFlag=[").append(escApplFlag).append("] ");
		buffer.append("dlgApplFlag=[").append(dlgApplFlag).append("] ");
		buffer.append("duration=[").append(duration).append("] ");
		buffer.append("remNoteDays=[").append(remNoteDays).append("] ");
		buffer.append("apprHierFlag=[").append(apprHierFlag).append("] ");
		buffer.append("autoAction=[").append(autoAction).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("prApprTypeId=[").append(prApprTypeId).append("] ");
		buffer.append("secApprTypeId=[").append(secApprTypeId).append("] ");

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
		final TChngReqConfig other = (TChngReqConfig) obj;
		if (chngReqConfigId == null) {
			if (other.chngReqConfigId != null) {
				return false;
			}
		} else if (!chngReqConfigId.equals(other.chngReqConfigId)) {
			return false;
		}
		return true;
	}
}
