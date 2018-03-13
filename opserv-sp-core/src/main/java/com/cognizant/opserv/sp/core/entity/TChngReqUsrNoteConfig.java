package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** 
 * JPA class representing TChngReqUsrNoteConfig 
 * The corresponding mapping table is t_chng_req_usr_note_config 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTChngReqUsrNoteConfigs", query = "select myTChngReqUsrNoteConfig from TChngReqUsrNoteConfig myTChngReqUsrNoteConfig"),
		@NamedQuery(name = "CountTChngReqUsrNoteConfigs", query = "Select Count(c) from TChngReqUsrNoteConfig c"),
		@NamedQuery(name = "FetchUserNotificationConfigFlag", query = "select myTChngReqUsrNoteConfig.activeFlag,myTChngReqUsrNoteConfig.tChngReqUsrNoteConfigId.usrTypeId  from TChngReqUsrNoteConfig myTChngReqUsrNoteConfig where myTChngReqUsrNoteConfig.tChngReqUsrNoteConfigId.usrTypeId IN ?1 and myTChngReqUsrNoteConfig.tChngReqUsrNoteConfigId.chngReqConfigId =?2 and myTChngReqUsrNoteConfig.tenantId =?3 "),
        @NamedQuery(name = "getChngReqUsrNoteConfigByCRCofigId", query = "Select obj from TChngReqUsrNoteConfig obj where obj.tChngReqUsrNoteConfigId.chngReqConfigId =?1 and obj.tenantId =?2")
				
})
@Table(name = "t_chng_req_usr_note_config")
public class TChngReqUsrNoteConfig implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TChngReqUsrNoteConfigId tChngReqUsrNoteConfigId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "notify_flag", nullable = false, length = 1)
	private Character notifyFlag;

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
	@Column(name = "algmnt_id", nullable = true, length = 255)
	private Long algmntId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "buss_unit_id", nullable = true, length = 255)
	private Long bussUnitId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "sales_team_id", nullable = true, length = 255)
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

	/**
	 *
	 * @generated
	 */
	public TChngReqUsrNoteConfig() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTChngReqUsrNoteConfigId(final TChngReqUsrNoteConfigId tChngReqUsrNoteConfigId) {
		this.tChngReqUsrNoteConfigId = tChngReqUsrNoteConfigId;
	}

	/**
	 * 
	 * @generated
	 */
	public TChngReqUsrNoteConfigId getTChngReqUsrNoteConfigId() {
		return this.tChngReqUsrNoteConfigId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setNotifyFlag(final Character notifyFlag) {
		this.notifyFlag = notifyFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getNotifyFlag() {
		return this.notifyFlag;
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
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TChngReqUsrNoteConfig that) {
		setTChngReqUsrNoteConfigId(that.getTChngReqUsrNoteConfigId());
		setNotifyFlag(that.getNotifyFlag());
		setActiveFlag(that.getActiveFlag());
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
		result = prime * result + ((tChngReqUsrNoteConfigId == null) ? 0 : tChngReqUsrNoteConfigId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tChngReqUsrNoteConfigId=[").append(tChngReqUsrNoteConfigId).append("] ");
		buffer.append("notifyFlag=[").append(notifyFlag).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
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
		final TChngReqUsrNoteConfig other = (TChngReqUsrNoteConfig) obj;
		if (tChngReqUsrNoteConfigId == null) {
			if (other.tChngReqUsrNoteConfigId != null) {
				return false;
			}
		} else if (!tChngReqUsrNoteConfigId.equals(other.tChngReqUsrNoteConfigId)) {
			return false;
		}
		return true;
	}
}
