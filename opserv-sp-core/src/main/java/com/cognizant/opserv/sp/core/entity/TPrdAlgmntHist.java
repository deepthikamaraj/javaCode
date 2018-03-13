package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TPrdAlgmntHist 
 * The corresponding mapping table is t_prd_algmnt_hist 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTPrdAlgmntHists", query = "select myTPrdAlgmntHist from TPrdAlgmntHist myTPrdAlgmntHist"),
		@NamedQuery(name = "CountTPrdAlgmntHists", query = "Select Count(c) from TPrdAlgmntHist c"),
		@NamedQuery(name = "FindTPrdAlgmntHistByTPrdAlgmnt", query = "select myTPrdAlgmntHist from TPrdAlgmntHist myTPrdAlgmntHist where myTPrdAlgmntHist.tPrdAlgmnt = ?1 "),
		@NamedQuery(name = "CountTPrdAlgmntHistsByTPrdAlgmnt", query = "select Count(myTPrdAlgmntHist) from TPrdAlgmntHist myTPrdAlgmntHist where myTPrdAlgmntHist.tPrdAlgmnt = ?1 ") })
@Table(name = "t_prd_algmnt_hist", uniqueConstraints = @UniqueConstraint(columnNames = { "prd_algmnt_hist_id" }))
public class TPrdAlgmntHist implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "prd_algmnt_hist_id", nullable = false, length = 255)
	private Long prdAlgmntHistId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "timestamp", nullable = false, length = 19)
	private Date timestamp;

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
	@Column(name = "create_dt", nullable = false, length = 19)
	private Date createDt;

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
	@Column(name = "eff_end_dt", nullable = true, length = 10)
	private Date effEndDt;

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
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

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
	@Column(name = "updated_by", nullable = true, length = 255)
	private Integer updatedBy;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 10)
	@Column(name = "wtg", nullable = true, length = 10)
	private String wtg;

	@ManyToOne
	@JoinColumn(name = "prd_algmnt_id", referencedColumnName = "prd_algmnt_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TPrdAlgmnt tPrdAlgmnt;

	/**
	 *
	 * @generated
	 */
	public TPrdAlgmntHist() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setPrdAlgmntHistId(final Long prdAlgmntHistId) {
		this.prdAlgmntHistId = prdAlgmntHistId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getPrdAlgmntHistId() {
		return this.prdAlgmntHistId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setTimestamp(final Date timestamp) {
		if (timestamp != null) {
			this.timestamp = (Date) timestamp.clone();
		} else {
			this.timestamp = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getTimestamp() {
		if (this.timestamp != null) {
			return (Date) this.timestamp.clone();
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

	public void setWtg(final String wtg) {
		this.wtg = wtg;
	}

	/**
	 * 
	 * @generated
	 */
	public String getWtg() {
		return this.wtg;
	}

	/**
	 * 
	 * @generated
	 */
	public TPrdAlgmnt getTPrdAlgmnt() {
		return this.tPrdAlgmnt;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPrdAlgmnt(final TPrdAlgmnt tPrdAlgmnt) {
		this.tPrdAlgmnt = tPrdAlgmnt;

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TPrdAlgmntHist that) {
		setPrdAlgmntHistId(that.getPrdAlgmntHistId());
		setTimestamp(that.getTimestamp());
		setActiveFlag(that.getActiveFlag());
		setCreateDt(that.getCreateDt());
		setCreatedBy(that.getCreatedBy());
		setEffEndDt(that.getEffEndDt());
		setEffStartDt(that.getEffStartDt());
		setTenantId(that.getTenantId());
		setUpdateDt(that.getUpdateDt());
		setUpdatedBy(that.getUpdatedBy());
		setWtg(that.getWtg());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((prdAlgmntHistId == null) ? 0 : prdAlgmntHistId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("prdAlgmntHistId=[").append(prdAlgmntHistId).append("] ");
		buffer.append("timestamp=[").append(timestamp).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("effEndDt=[").append(effEndDt).append("] ");
		buffer.append("effStartDt=[").append(effStartDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("wtg=[").append(wtg).append("] ");

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
		final TPrdAlgmntHist other = (TPrdAlgmntHist) obj;
		if (prdAlgmntHistId == null) {
			if (other.prdAlgmntHistId != null) {
				return false;
			}
		} else if (!prdAlgmntHistId.equals(other.prdAlgmntHistId)) {
			return false;
		}
		return true;
	}
}
