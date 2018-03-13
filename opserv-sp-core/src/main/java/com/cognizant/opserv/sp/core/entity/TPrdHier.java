package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

/** 
 * JPA class representing TPrdHier 
 * The corresponding mapping table is t_prd_hier 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTPrdHiers", query = "select myTPrdHier from TPrdHier myTPrdHier"),
		@NamedQuery(name = "CountTPrdHiers", query = "Select Count(c) from TPrdHier c"),
		@NamedQuery(name = "FindTPrdHierByTPrdHierGroup", query = "select myTPrdHier from TPrdHier myTPrdHier where myTPrdHier.tPrdHierGroup = ?1 "),
		@NamedQuery(name = "CountTPrdHiersByTPrdHierGroup", query = "select Count(*) from TPrdHier myTPrdHier where myTPrdHier.tPrdHierGroup = ?1 "),
		@NamedQuery(name = "FindTPrdHierByTPrd", query = "select myTPrdHier from TPrdHier myTPrdHier where myTPrdHier.tPrd = ?1 "),
		@NamedQuery(name = "CountTPrdHiersByTPrd", query = "select Count(*) from TPrdHier myTPrdHier where myTPrdHier.tPrd = ?1 "),
		@NamedQuery(name = "FindTPrdHierByTPrdHier", query = "select myTPrdHier from TPrdHier myTPrdHier where myTPrdHier.tPrdHier = ?1 "),
		@NamedQuery(name = "CountTPrdHiersByTPrdHier", query = "select Count(*) from TPrdHier myTPrdHier where myTPrdHier.tPrdHier = ?1 ") })
@Table(name = "t_prd_hier")
public class TPrdHier implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TPrdHierId tPrdHierId;

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
	@JoinColumn(name = "group_id", referencedColumnName = "group_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TPrdHierGroup tPrdHierGroup;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "prd_id", referencedColumnName = "prd_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TPrd tPrd;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "prn_group_id", referencedColumnName = "group_id", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "prd_id", referencedColumnName = "prd_id", nullable = false, insertable = false, updatable = false) })
	@Valid
	@NotAudited
	private TPrdHier tPrdHier;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tPrdHier")
	@NotAudited
	private Set<TPrdHier> tPrdHierss;

	/**
	 *
	 * @generated
	 */
	public TPrdHier() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTPrdHierId(final TPrdHierId tPrdHierId) {
		this.tPrdHierId = tPrdHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public TPrdHierId getTPrdHierId() {
		return this.tPrdHierId;
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
	public TPrdHierGroup getTPrdHierGroup() {
		return this.tPrdHierGroup;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPrdHierGroup(final TPrdHierGroup tPrdHierGroup) {
		this.tPrdHierGroup = tPrdHierGroup;
		if (this.tPrdHierGroup != null && this.tPrdHierGroup.getGroupId() != null) {

			this.tPrdHierId.setGroupId(this.tPrdHierGroup.getGroupId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	public TPrd getTPrd() {
		return this.tPrd;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPrd(final TPrd tPrd) {
		this.tPrd = tPrd;
		if (this.tPrd != null && this.tPrd.getPrdId() != null) {

			this.tPrdHierId.setPrdId(this.tPrd.getPrdId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	public TPrdHier getTPrdHier() {
		return this.tPrdHier;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPrdHier(final TPrdHier tPrdHier) {
		this.tPrdHier = tPrdHier;
		if (this.tPrdHier != null && this.tPrdHier.getTPrdHierId() != null) {

			this.tPrdHierId.setPrdId(this.tPrdHier.getTPrdHierId().getPrdId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	public Set<TPrdHier> getTPrdHierss() {
		return this.tPrdHierss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPrdHierss(final Set<TPrdHier> tPrdHierss) {
		this.tPrdHierss = tPrdHierss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TPrdHier that) {
		setTPrdHierId(that.getTPrdHierId());
		setActiveFlag(that.getActiveFlag());
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
		result = prime * result + ((tPrdHierId == null) ? 0 : tPrdHierId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tPrdHierId=[").append(tPrdHierId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
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
		final TPrdHier other = (TPrdHier) obj;
		if (tPrdHierId == null) {
			if (other.tPrdHierId != null) {
				return false;
			}
		} else if (!tPrdHierId.equals(other.tPrdHierId)) {
			return false;
		}
		return true;
	}
}
