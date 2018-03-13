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
import javax.persistence.QueryHint;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** 
 * JPA class representing TMtrTrigger 
 * The corresponding mapping table is t_mtr_trigger 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTMtrTriggers", query = "select myTMtrTrigger from TMtrTrigger myTMtrTrigger"),
		@NamedQuery(name = "CountTMtrTriggers", query = "Select Count(c) from TMtrTrigger c"),
		@NamedQuery(name = "FindTMtrTriggerByTFeatureType", query = "select myTMtrTrigger from TMtrTrigger myTMtrTrigger where myTMtrTrigger.tFeatureType = ?1 "),
		@NamedQuery(name = "CountTMtrTriggersByTFeatureType", query = "select Count(myTMtrTrigger) from TMtrTrigger myTMtrTrigger where myTMtrTrigger.tFeatureType = ?1 "),
		//@NamedQuery(name = "FindTMtrTriggerByTFeature", query = "select myTMtrTrigger from TMtrTrigger myTMtrTrigger where myTMtrTrigger.featureId = ?1 "),
		//@NamedQuery(name = "CountTMtrTriggersByTFeature", query = "select Count(myTMtrTrigger) from TMtrTrigger myTMtrTrigger where myTMtrTrigger.featureId = ?1 "),
		@NamedQuery(name = "FindAllTMtrTriggersWithActiveFlag", query = "select myTMtrTrigger from TMtrTrigger myTMtrTrigger where myTMtrTrigger.tenantId=?1 and myTMtrTrigger.activeFlag = 'Y' "),
		@NamedQuery(name = "findMetricTriggerByFeatures",query = "select myTMtrTrigger from TMtrTrigger myTMtrTrigger where myTMtrTrigger.tFeatureType.typeId = ?1 and myTMtrTrigger.featureId = ?2 and myTMtrTrigger.tenantId = ?3 and  myTMtrTrigger.activeFlag = 'Y' "),
		@NamedQuery(name = "FindAllTMtrTrigger", query = "select myTMtrTrigger.triggerId, myTMtrTrigger.featureId, myTMtrTrigger.tFeatureType.typeId from TMtrTrigger myTMtrTrigger where myTMtrTrigger.tenantId = ?1 AND myTMtrTrigger.activeFlag = 'Y' ", 
		hints = {@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")
		})
	})
@Table(name = "t_mtr_trigger", uniqueConstraints = @UniqueConstraint(columnNames = { "trigger_id" }))
public class TMtrTrigger implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "trigger_id", nullable = false, length = 255)
	private Integer triggerId;

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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "type_id", referencedColumnName = "type_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TFeatureType tFeatureType;

/*	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "feature_id", referencedColumnName = "feature_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TFeature tFeature;*/
	@NotNull
	@Column(name = "feature_id", nullable = false, length = 255)
	private Integer featureId;
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tMtrTrigger")
	private Set<TMtrExecConfig> tMtrExecConfigss;

	/**
	 *
	 * @generated
	 */
	public TMtrTrigger() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTriggerId(final Integer triggerId) {
		this.triggerId = triggerId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getTriggerId() {
		return this.triggerId;
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
	public TFeatureType getTFeatureType() {
		return this.tFeatureType;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTFeatureType(final TFeatureType tFeatureType) {
		this.tFeatureType = tFeatureType;

	}

	/**
	 * 
	 * @generated
	 */
	/*public TFeature getTFeature() {
		return this.tFeature;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTFeature(final TFeature tFeature) {
		this.tFeature = tFeature;

	}*/
	public Set<TMtrExecConfig> getTMtrExecConfigss() {
		return this.tMtrExecConfigss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTMtrExecConfigss(final Set<TMtrExecConfig> tMtrExecConfigss) {
		this.tMtrExecConfigss = tMtrExecConfigss;
	}

	public Integer getFeatureId() {
		return featureId;
	}

	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TMtrTrigger that) {
		setTriggerId(that.getTriggerId());
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
		result = prime * result + ((triggerId == null) ? 0 : triggerId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("triggerId=[").append(triggerId).append("] ");
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
		final TMtrTrigger other = (TMtrTrigger) obj;
		if (triggerId == null) {
			if (other.triggerId != null) {
				return false;
			}
		} else if (!triggerId.equals(other.triggerId)) {
			return false;
		}
		return true;
	}
}
