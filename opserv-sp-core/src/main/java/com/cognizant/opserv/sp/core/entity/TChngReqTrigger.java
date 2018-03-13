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

/** 
 * JPA class representing TChngReqTrigger 
 * The corresponding mapping table is t_chng_req_trigger 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTChngReqTriggers", query = "select myTChngReqTrigger from TChngReqTrigger myTChngReqTrigger"),
		@NamedQuery(name = "CountTChngReqTriggers", query = "Select Count(c) from TChngReqTrigger c"),
	//	@NamedQuery(name = "FindTChngReqTriggerByTFeature", query = "select myTChngReqTrigger from TChngReqTrigger myTChngReqTrigger where myTChngReqTrigger.tFeature = ?1 "),
	//	@NamedQuery(name = "CountTChngReqTriggersByTFeature", query = "select Count(myTChngReqTrigger) from TChngReqTrigger myTChngReqTrigger where myTChngReqTrigger.tFeature = ?1 "),
		@NamedQuery(name = "getfeatureTypeByTriggerId", query = "select myTChngReqFeature.featureName , myTFeatureType.typeDesc,myTChngReqFeature.featureDesc  from TChngReqTrigger myTChngReqTrigger,TChngReqFeature myTChngReqFeature,TFeatureType myTFeatureType where myTChngReqTrigger.typeId=myTFeatureType.typeId AND myTChngReqTrigger.featureId=myTChngReqFeature.featureId AND myTChngReqTrigger.triggerId=?1 AND  myTChngReqTrigger.tenantId=?2 AND myTFeatureType.tenantId=myTChngReqTrigger.tenantId"),
		@NamedQuery(name = "FetchAllTriggersByTenants", query = "select  myTChngReqTrigger.triggerId ,myTChngReqFeature.featureDesc,myTFeatureType.typeDesc,myTChngReqTrigger.tenantId,myTChngReqFeature.featureName from TChngReqTrigger myTChngReqTrigger,TChngReqFeature myTChngReqFeature,TFeatureType myTFeatureType where myTChngReqTrigger.featureId = myTChngReqFeature.featureId and  myTChngReqTrigger.typeId = myTFeatureType.typeId order by myTChngReqTrigger.triggerId"),
		@NamedQuery(name = "FetchAllTriggersByTenantId", query = "select  myTChngReqTrigger.triggerId ,myTChngReqFeature.featureDesc,myTFeatureType.typeDesc,myTChngReqTrigger.tenantId,myTChngReqFeature.featureName from TChngReqTrigger myTChngReqTrigger,TChngReqFeature myTChngReqFeature,TFeatureType myTFeatureType where myTChngReqTrigger.featureId = myTChngReqFeature.featureId and  myTChngReqTrigger.typeId = myTFeatureType.typeId and myTChngReqTrigger.tenantId =?1"),
		@NamedQuery(name = "GetCRTriggerAndFeature", query = "select myTChngReqTrigger.triggerId, myTChngReqFeature.featureName,concat(myTFeatureType.typeDesc, ' ', myTChngReqFeature.featureName) from TChngReq myTChngReq , TChngReqConfig myTChngReqConfig,TChngReqFeature myTChngReqFeature, TChngReqTrigger myTChngReqTrigger , TFeatureType myTFeatureType  "
				+ " where myTChngReq.tChngReqConfig.chngReqConfigId = myTChngReqConfig.chngReqConfigId and myTChngReqConfig.tChngReqTrigger.triggerId= myTChngReqTrigger.triggerId and myTChngReqTrigger.featureId= myTChngReqFeature.featureId and myTChngReqTrigger.typeId=myTFeatureType.typeId   " 
				+" and myTChngReq.chngReqId= ?1 and  myTChngReq.tenantId = ?2 ")

		
})
@Table(name = "t_chng_req_trigger", uniqueConstraints = @UniqueConstraint(columnNames = { "trigger_id" }))
public class TChngReqTrigger implements Serializable {
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
	@Column(name = "type_id", nullable = false, length = 255)
	private Integer typeId;

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
	@Column(name = "nst_chng_req_flag", nullable = true, length = 1)
	private Character nstChngReqFlag;

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

/*	@ManyToOne
	@JoinColumn(name = "feature_id", referencedColumnName = "feature_id", nullable = false, insertable = true, updatable = true)
	//@Valid
	private TFeature tFeature;*/
	
	@NotNull
	@Column(name = "feature_id", nullable = false, length = 255)
	private Integer featureId;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tChngReqTrigger")
	private Set<TChngReqConfig> tChngReqConfigss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tChngReqTriggerByTriggerId")
	private Set<TNestChngReq> tNestChngReqsForTriggerIds;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tChngReqTriggerByNstTriggerId")
	private Set<TNestChngReq> tNestChngReqsForNstTriggerIds;

	/**
	 *
	 * @generated
	 */
	public TChngReqTrigger() {
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

	public void setTypeId(final Integer typeId) {
		this.typeId = typeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getTypeId() {
		return this.typeId;
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

	public void setNstChngReqFlag(final Character nstChngReqFlag) {
		this.nstChngReqFlag = nstChngReqFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getNstChngReqFlag() {
		return this.nstChngReqFlag;
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
	/*public TFeature getTFeature() {
		return this.tFeature;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public void setTFeature(final TFeature tFeature) {
		this.tFeature = tFeature;

	}*/


	/**
	 * 
	 * @generated
	 */
	public Set<TChngReqConfig> getTChngReqConfigss() {
		return this.tChngReqConfigss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTChngReqConfigss(final Set<TChngReqConfig> tChngReqConfigss) {
		this.tChngReqConfigss = tChngReqConfigss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TNestChngReq> getTNestChngReqsForTriggerIds() {
		return this.tNestChngReqsForTriggerIds;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTNestChngReqsForTriggerIds(final Set<TNestChngReq> tNestChngReqsForTriggerIds) {
		this.tNestChngReqsForTriggerIds = tNestChngReqsForTriggerIds;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TNestChngReq> getTNestChngReqsForNstTriggerIds() {
		return this.tNestChngReqsForNstTriggerIds;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTNestChngReqsForNstTriggerIds(final Set<TNestChngReq> tNestChngReqsForNstTriggerIds) {
		this.tNestChngReqsForNstTriggerIds = tNestChngReqsForNstTriggerIds;
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
	public void copy(final TChngReqTrigger that) {
		setTriggerId(that.getTriggerId());
		setTypeId(that.getTypeId());
		setActiveFlag(that.getActiveFlag());
		setNstChngReqFlag(that.getNstChngReqFlag());
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
		buffer.append("typeId=[").append(typeId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("nstChngReqFlag=[").append(nstChngReqFlag).append("] ");
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
		final TChngReqTrigger other = (TChngReqTrigger) obj;
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
