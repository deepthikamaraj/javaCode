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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TComm 
 * The corresponding mapping table is t_comm 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTComms", query = "select myTComm from TComm myTComm"),
		@NamedQuery(name = "CountTComms", query = "Select Count(c) from TComm c"),
		@NamedQuery(name = "FindTCommByTCommType", query = "select myTComm from TComm myTComm where myTComm.tCommType = ?1 "),
		@NamedQuery(name = "CountTCommsByTCommType", query = "select Count(myTComm) from TComm myTComm where myTComm.tCommType = ?1 "),
		@NamedQuery(name = "FindTCommByTCommStatus", query = "select myTComm from TComm myTComm where myTComm.tCommStatus = ?1 "),
		@NamedQuery(name = "CountTCommsByTCommStatus", query = "select Count(myTComm) from TComm myTComm where myTComm.tCommStatus = ?1 ") ,
		//@NamedQuery(name = "FindTCommsAnnByStatusAndType", query = "select myTComm from TComm myTComm where myTComm.tCommStatus=?1 AND myTComm.tCommType.commTypeId=1"),
		@NamedQuery(name = "FindTCommByStatusType", query = "select myTComm from TComm myTComm where myTComm.tCommStatus.commStatusId=?1 AND myTComm.tCommType.commTypeId=?2 AND myTComm.createdBy=?3 AND myTComm.validityEndDt >=?4"),
		@NamedQuery(name = "FindTCommDraftByStatusType", query = "select myTComm from TComm myTComm where myTComm.tCommStatus.commStatusId=?1 AND myTComm.tCommType.commTypeId=?2 AND myTComm.createdBy=?3 AND myTComm.tenantId=?4"),
		@NamedQuery(name = "FindTCommByStatusTypeTitle", query = "select myTComm from TComm myTComm where myTComm.tCommStatus.commStatusId=?1 AND myTComm.tCommType.commTypeId=?2 AND myTComm.createdBy=?3 AND myTComm.activityStartDt=?4 AND myTComm.validityStartDt=?5 AND myTComm.validityEndDt=?6 AND myTComm.commTitle=?7 "),
		@NamedQuery(name = "FindTCommsActivityByStatusAndType", query = "select myTComm from TComm myTComm where myTComm.tCommStatus.commStatusId=?1 AND myTComm.tCommType.commTypeId=3"),
		//@NamedQuery(name = "FindTCommsByMonth", query = "select myTComm from TComm myTComm where myTComm.activityStartDt >=?1 AND myTComm.tCommType.commTypeId=?2 AND myTComm.tCommStatus.commStatusId=?3"),
		@NamedQuery(name = "FindLastPublishedCommId", query = "select Max(myTComm.commId) from TComm myTComm where myTComm.tCommType.commTypeId = ?1 AND myTComm.validityStartDt <= ?2 AND myTComm.tCommStatus.commStatusId=?3 AND myTComm.tenantId=?4"),
	    @NamedQuery(name = "FindTCommsByMonth", query = "select myTComm from TComm myTComm where myTComm.createdBy=?1 AND myTComm.validityEndDt >=?2 AND (myTComm.tCommStatus.commStatusId=?3 OR myTComm.tCommStatus.commStatusId=?4) AND myTComm.tCommType.commTypeId=?5 AND date_format(myTComm.activityStartDt,'%m/%d/%Y') >= ?6 AND date_format(myTComm.activityStartDt,'%m/%d/%Y') <= ?7 AND myTComm.tenantId=?8"),
	    @NamedQuery(name = "FindTCommsByCommDate", query = "select myTComm from TComm myTComm where myTComm.createdBy=?1 AND myTComm.validityEndDt >=?2 AND (myTComm.tCommStatus.commStatusId=?3 OR myTComm.tCommStatus.commStatusId=?4) AND myTComm.tCommType.commTypeId=?5 and myTComm.tenantId=?6"),
	    @NamedQuery(name = "PublishAssignment", query = "Update TComm myTComm set myTComm.publishedDt=?1 , myTComm.updatedBy=?2 ,myTComm.updateDate = CURRENT_DATE,myTComm.tCommStatus.commStatusId=?4 where myTComm.commId = ?3  "),
	    @NamedQuery(name = "GetAnnouncementsByUser", query = "select myTComm from TComm myTComm, TCommReceipient myTCommReceipient, TPers myTPers, TCommNoteType myTCommNoteType where myTComm.commId=myTCommNoteType.tCommNoteTypeId.commId and myTComm.commId=myTCommReceipient.tCommReceipientId.commId and myTCommReceipient.tCommReceipientId.staffId=myTPers.staffId and myTCommNoteType.activeFlag='Y' and " +
	    		"myTCommNoteType.tCommNoteTypeId.noteTypeId not in (1) and ((myTCommReceipient.ackStatusId=4 and myTCommReceipient.commReadDt=null) or (myTCommReceipient.ackStatusId=1 and myTCommReceipient.commAckDt=null)) and myTComm.validityStartDt <= CURRENT_DATE and CURRENT_DATE <= myTComm.validityEndDt and myTComm.tCommStatus.commStatusId=?1 and myTPers.userId=?2 and myTComm.tenantId=?3 "),
		@NamedQuery(name = "GetAnnouncementsByPublisher", query = "select myTComm from TComm myTComm, TCommPublisher myTCommPublisher, TPers myTPers, TCommNoteType myTCommNoteType where myTComm.commId=myTCommNoteType.tCommNoteTypeId.commId and myTComm.commId=myTCommPublisher.tCommPublisherId.commId and myTCommPublisher.tCommPublisherId.staffId=myTPers.staffId and myTCommNoteType.activeFlag='Y' and " +
	    		"myTCommNoteType.tCommNoteTypeId.noteTypeId not in (1) and myTPers.userId=?1 and myTComm.tCommStatus.commStatusId=?2 and myTComm.tenantId=?3 order by myTComm.validityStartDt desc")
		})

@Table(name = "t_comm", uniqueConstraints = @UniqueConstraint(columnNames = { "comm_id" }))
public class TComm implements Serializable {
	
	
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "comm_id", nullable = false, length = 255)
	private Long commId;

	/**
	 * 
	 * @generated
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "validity_start_dt", nullable = true, length = 10)
	private Date validityStartDt;

	/**
	 * 
	 * @generated
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "validity_end_dt", nullable = true, length = 10)
	private Date validityEndDt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "published_dt", nullable = true, length = 10)
	private Date publishedDt;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 16777215)
	@Column(name = "comm_detail", nullable = true, length = 16777215)
	private String commDetail;

	/**
	 * 
	 * @generated
	 */
	
	@Column(name = "draft_dt", nullable = true, length = 10)
	private Date draftDt;

	/**
	 * 
	 * @generated
	 */
	
	@Length(max = 400)
	@Column(name = "comm_title", nullable = true, length = 400)
	private String commTitle;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "ack_required", nullable = true, length = 1)
	private Character ackRequired;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "importance_flag", nullable = true, length = 1)
	private Character importanceFlag;

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
	private Date updateDate;

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
	@Column(name = "activity_start_dt", nullable = true, length = 10)
	private Date activityStartDt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "activity_end_dt", nullable = true, length = 10)
	private Date activityEndDt;

	@ManyToOne
	@JoinColumn(name = "comm_type_id", referencedColumnName = "comm_type_id", nullable = false, insertable = true, updatable = true)
	@Valid
	@NotAudited
	private TCommType tCommType;

	@ManyToOne
	@JoinColumn(name = "comm_status_id", referencedColumnName = "comm_status_id", nullable = false, insertable = true, updatable = true)
	@Valid
	@NotAudited
	private TCommStatus tCommStatus;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tComm")
	@NotAudited
	@Fetch(FetchMode.SUBSELECT)
	private Set<TCommDoc> tCommDocss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tComm")
	@NotAudited
	@Fetch(FetchMode.SUBSELECT)
	private Set<TCommNoteType> tCommNoteTypess;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tComm")
	@Fetch(FetchMode.SUBSELECT)
	@NotAudited
	private Set<TCommReceipient> tCommReceipientss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tComm")
	@Fetch(FetchMode.SUBSELECT)
	@NotAudited
	private Set<TCommPublisher> tCommPublisherss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tComm")
	@Fetch(FetchMode.SUBSELECT)
	@NotAudited
	private Set<TCommTargetRecipient> tCommTargetRecipientss;

	/**
	 *
	 * @generated
	 */
	public TComm() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setCommId(final Long commId) {
		this.commId = commId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getCommId() {
		return this.commId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setValidityStartDt(final Date validityStartDt) {
		if (validityStartDt != null) {
			this.validityStartDt = (Date) validityStartDt.clone();
		} else {
			this.validityStartDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getValidityStartDt() {
		if (this.validityStartDt != null) {
			return (Date) this.validityStartDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setValidityEndDt(final Date validityEndDt) {
		if (validityEndDt != null) {
			this.validityEndDt = (Date) validityEndDt.clone();
		} else {
			this.validityEndDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getValidityEndDt() {
		if (this.validityEndDt != null) {
			return (Date) this.validityEndDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setPublishedDt(final Date publishedDt) {
		if (publishedDt != null) {
			this.publishedDt = (Date) publishedDt.clone();
		} else {
			this.publishedDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getPublishedDt() {
		if (this.publishedDt != null) {
			return (Date) this.publishedDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setCommDetail(final String commDetail) {
		this.commDetail = commDetail;
	}

	/**
	 * 
	 * @generated
	 */
	public String getCommDetail() {
		return this.commDetail;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDraftDt(final Date draftDt) {
		if (draftDt != null) {
			this.draftDt = (Date) draftDt.clone();
		} else {
			this.draftDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getDraftDt() {
		if (this.draftDt != null) {
			return (Date) this.draftDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setCommTitle(final String commTitle) {
		this.commTitle = commTitle;
	}

	/**
	 * 
	 * @generated
	 */
	public String getCommTitle() {
		return this.commTitle;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAckRequired(final Character ackRequired) {
		this.ackRequired = ackRequired;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getAckRequired() {
		return this.ackRequired;
	}

	/**
	 * 
	 * @generated
	 */

	public void setImportanceFlag(final Character importanceFlag) {
		this.importanceFlag = importanceFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getImportanceFlag() {
		return this.importanceFlag;
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

	public void setUpdateDate(final Date updateDate) {
		if (updateDate != null) {
			this.updateDate = (Date) updateDate.clone();
		} else {
			this.updateDate = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getUpdateDate() {
		if (this.updateDate != null) {
			return (Date) this.updateDate.clone();
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

	public void setActivityStartDt(final Date activityStartDt) {
		if (activityStartDt != null) {
			this.activityStartDt = (Date) activityStartDt.clone();
		} else {
			this.activityStartDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getActivityStartDt() {
		if (this.activityStartDt != null) {
			return (Date) this.activityStartDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setActivityEndDt(final Date activityEndDt) {
		if (activityEndDt != null) {
			this.activityEndDt = (Date) activityEndDt.clone();
		} else {
			this.activityEndDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getActivityEndDt() {
		if (this.activityEndDt != null) {
			return (Date) this.activityEndDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public TCommType getTCommType() {
		return this.tCommType;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCommType(final TCommType tCommType) {
		this.tCommType = tCommType;

	}

	/**
	 * 
	 * @generated
	 */
	public TCommStatus getTCommStatus() {
		return this.tCommStatus;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCommStatus(final TCommStatus tCommStatus) {
		this.tCommStatus = tCommStatus;

	}

	/**
	 * 
	 * @generated
	 */
	public Set<TCommDoc> getTCommDocss() {
		return this.tCommDocss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCommDocss(final Set<TCommDoc> tCommDocss) {
		this.tCommDocss = tCommDocss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TCommNoteType> getTCommNoteTypess() {
		return this.tCommNoteTypess;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCommNoteTypess(final Set<TCommNoteType> tCommNoteTypess) {
		this.tCommNoteTypess = tCommNoteTypess;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TCommReceipient> getTCommReceipientss() {
		return this.tCommReceipientss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCommReceipientss(final Set<TCommReceipient> tCommReceipientss) {
		this.tCommReceipientss = tCommReceipientss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TCommPublisher> getTCommPublisherss() {
		return this.tCommPublisherss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCommPublisherss(final Set<TCommPublisher> tCommPublisherss) {
		this.tCommPublisherss = tCommPublisherss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TCommTargetRecipient> getTCommTargetRecipientss() {
		return this.tCommTargetRecipientss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCommTargetRecipientss(final Set<TCommTargetRecipient> tCommTargetRecipientss) {
		this.tCommTargetRecipientss = tCommTargetRecipientss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TComm that) {
		setCommId(that.getCommId());
		setValidityStartDt(that.getValidityStartDt());
		setValidityEndDt(that.getValidityEndDt());
		setPublishedDt(that.getPublishedDt());
		setCommDetail(that.getCommDetail());
		setDraftDt(that.getDraftDt());
		setCommTitle(that.getCommTitle());
		setAckRequired(that.getAckRequired());
		setImportanceFlag(that.getImportanceFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDate(that.getUpdateDate());
		setTenantId(that.getTenantId());
		setActivityStartDt(that.getActivityStartDt());
		setActivityEndDt(that.getActivityEndDt());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((commId == null) ? 0 : commId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("commId=[").append(commId).append("] ");
		buffer.append("validityStartDt=[").append(validityStartDt).append("] ");
		buffer.append("validityEndDt=[").append(validityEndDt).append("] ");
		buffer.append("publishedDt=[").append(publishedDt).append("] ");
		buffer.append("commDetail=[").append(commDetail).append("] ");
		buffer.append("draftDt=[").append(draftDt).append("] ");
		buffer.append("commTitle=[").append(commTitle).append("] ");
		buffer.append("ackRequired=[").append(ackRequired).append("] ");
		buffer.append("importanceFlag=[").append(importanceFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDate=[").append(updateDate).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("activityStartDt=[").append(activityStartDt).append("] ");
		buffer.append("activityEndDt=[").append(activityEndDt).append("] ");

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
		final TComm other = (TComm) obj;
		if (commId == null) {
			if (other.commId != null) {
				return false;
			}
		} else if (!commId.equals(other.commId)) {
			return false;
		}
		return true;
	}
}
