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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TChngReqTrack 
 * The corresponding mapping table is t_chng_req_track 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTChngReqTracks", query = "select myTChngReqTrack from TChngReqTrack myTChngReqTrack"),
		@NamedQuery(name = "CountTChngReqTracks", query = "Select Count(c) from TChngReqTrack c") })
@Table(name = "t_chng_req_track", uniqueConstraints = @UniqueConstraint(columnNames = { "chng_req_track_id" }))
public class TChngReqTrack implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "chng_req_track_id", nullable = false, length = 255)
	private Integer chngReqTrackId;

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

	/**
	 * 
	 * @generated
	 */
	@Length(max = 10)
	@Column(name = "chng_req_mode", nullable = true, length = 10)
	private String chngReqMode;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "submit_dt_tm", nullable = true, length = 10)
	private Date submitDtTm;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tChngReqTrack")
	private Set<TChngReq> tChngReqss;

	/**
	 *
	 * @generated
	 */
	public TChngReqTrack() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setChngReqTrackId(final Integer chngReqTrackId) {
		this.chngReqTrackId = chngReqTrackId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getChngReqTrackId() {
		return this.chngReqTrackId;
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

	public void setChngReqMode(final String chngReqMode) {
		this.chngReqMode = chngReqMode;
	}

	/**
	 * 
	 * @generated
	 */
	public String getChngReqMode() {
		return this.chngReqMode;
	}

	/**
	 * 
	 * @generated
	 */

	public void setSubmitDtTm(final Date submitDtTm) {
		if (submitDtTm != null) {
			this.submitDtTm = (Date) submitDtTm.clone();
		} else {
			this.submitDtTm = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getSubmitDtTm() {
		if (this.submitDtTm != null) {
			return (Date) this.submitDtTm.clone();
		} else {
			return null;
		}
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

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TChngReqTrack that) {
		setChngReqTrackId(that.getChngReqTrackId());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setChngReqMode(that.getChngReqMode());
		setSubmitDtTm(that.getSubmitDtTm());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((chngReqTrackId == null) ? 0 : chngReqTrackId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("chngReqTrackId=[").append(chngReqTrackId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("chngReqMode=[").append(chngReqMode).append("] ");
		buffer.append("submitDtTm=[").append(submitDtTm).append("] ");

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
		final TChngReqTrack other = (TChngReqTrack) obj;
		if (chngReqTrackId == null) {
			if (other.chngReqTrackId != null) {
				return false;
			}
		} else if (!chngReqTrackId.equals(other.chngReqTrackId)) {
			return false;
		}
		return true;
	}
}
