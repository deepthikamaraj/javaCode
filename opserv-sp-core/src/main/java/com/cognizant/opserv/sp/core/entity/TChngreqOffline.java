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
 * JPA class representing TChngreqOffline 
 * The corresponding mapping table is t_temp_chngreq 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTChngreqOfflines", query = "select myTChngreqOffline from TChngreqOffline myTChngreqOffline"),
		@NamedQuery(name = "CountTChngreqOfflines", query = "Select Count(c) from TChngreqOffline c"),
		@NamedQuery(name = "FindTChngreqOfflineByTChngReq", query = "select myTChngreqOffline from TChngreqOffline myTChngreqOffline where myTChngreqOffline.tChngReq = ?1 "),
		@NamedQuery(name = "CountTChngreqOfflinesByTChngReq", query = "select Count(myTChngreqOffline) from TChngreqOffline myTChngreqOffline where myTChngreqOffline.tChngReq = ?1 "),
		@NamedQuery(name = "FetchTChngreqOfflineByOfflineId", query = "select myTChngreqOffline from TChngreqOffline myTChngreqOffline where myTChngreqOffline.offlineId = ?1 and myTChngreqOffline.tenantId = ?2 and myTChngreqOffline.statusId = 1"),
		@NamedQuery(name = "FetchTChngreqOfflineByTChngReq", query = "select myTChngreqOffline from TChngreqOffline myTChngreqOffline where myTChngreqOffline.tChngReq.chngReqId = ?1 and myTChngreqOffline.tenantId = ?2"),
		@NamedQuery(name = "updateTChngreqOfflineByStatusId", query = "Update TChngreqOffline myTChngreqOffline set myTChngreqOffline.statusId=?1 ,myTChngreqOffline.updateDt = ?2 ,myTChngreqOffline.updatedBy = ?3 , myTChngreqOffline.failedReason = ?6" +
				" where myTChngreqOffline.offlineId = ?4 and myTChngreqOffline.tenantId = ?5"),
		@NamedQuery(name = "countOfOfflineCRStatus", query = "select Count(myTChngreqOffline) from TChngreqOffline myTChngreqOffline where myTChngreqOffline.tChngReq.chngReqId = ?1 and myTChngreqOffline.tenantId = ?2 and myTChngreqOffline.statusId IN (1,2)"), 
		@NamedQuery(name = "countOfTChngreqOfflineByStatus", query = "select Count(myTChngreqOffline) from TChngreqOffline myTChngreqOffline where myTChngreqOffline.offlineId = ?1 and myTChngreqOffline.tenantId = ?2 and myTChngreqOffline.statusId = 1"),
		@NamedQuery(name = "FindTChngreqOfflineByStatus", query = "select myTChngreqOffline from TChngreqOffline myTChngreqOffline where myTChngreqOffline.statusId = ?1 and myTChngreqOffline.tenantId = ?2"),
		@NamedQuery(name = "FindStatusOfTChngreqOffline", query = "select myTChngreqOffline.statusId from TChngreqOffline myTChngreqOffline where myTChngreqOffline.offlineId = ?1 and myTChngreqOffline.tenantId = ?2 ")
})
@Table(name = "t_chngreq_offline", uniqueConstraints = @UniqueConstraint(columnNames = { "offline_id" }))
public class TChngreqOffline implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "offline_id", nullable = false, length = 255)
	private Long offlineId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "trigger_id", nullable = false, length = 255)
	private Integer triggerId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "status_id", nullable = true, length = 255)
	private Integer statusId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "req_detail", nullable = true, length = 255)
	private String reqDetail;
	
	
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
	@Length(max = 500)
	@Column(name = "failed_reason", nullable = true, length = 500)
	private String failedReason;

	@ManyToOne
	@JoinColumn(name = "chng_req_id", referencedColumnName = "chng_req_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TChngReq tChngReq;

	/**
	 *
	 * @generated
	 */
	public TChngreqOffline() {
	}

	/**
	 * 
	 * @generated
	 */

	
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

	public void setStatusId(final Integer statusId) {
		this.statusId = statusId;
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
	public Integer getStatusId() {
		return this.statusId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setReqDetail(final String reqDetail) {
		this.reqDetail = reqDetail;
	}

	/**
	 * 
	 * @generated
	 */
	public String getReqDetail() {
		return this.reqDetail;
	}

	/**
	 * 
	 * @generated
	 */

	public void setFailedReason(final String failedReason) {
		this.failedReason = failedReason;
	}

	/**
	 * 
	 * @generated
	 */
	public String getFailedReason() {
		return this.failedReason;
	}

	/**
	 * 
	 * @generated
	 */
	public TChngReq getTChngReq() {
		return this.tChngReq;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTChngReq(final TChngReq tChngReq) {
		this.tChngReq = tChngReq;

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TChngreqOffline that) {
		setOfflineId(that.getOfflineId());
		setTriggerId(that.getTriggerId());
		setStatusId(that.getStatusId());
		setReqDetail(that.getReqDetail());
		setFailedReason(that.getFailedReason());
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
		result = prime * result + ((offlineId == null) ? 0 : offlineId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("offlineId=[").append(offlineId).append("] ");
		buffer.append("triggerId=[").append(triggerId).append("] ");
		buffer.append("statusId=[").append(statusId).append("] ");
		buffer.append("reqDetail=[").append(reqDetail).append("] ");
		buffer.append("failedReason=[").append(failedReason).append("] ");
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
		final TChngreqOffline other = (TChngreqOffline) obj;
		if (offlineId == null) {
			if (other.offlineId != null) {
				return false;
			}
		} else if (!offlineId.equals(other.offlineId)) {
			return false;
		}
		return true;
	}

	public Long getOfflineId() {
		return offlineId;
	}

	public void setOfflineId(Long offlineId) {
		this.offlineId = offlineId;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}
}
