package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TCommReceipient 
 * The corresponding mapping table is t_comm_receipient 
 */

@Entity
@Audited
@NamedQueries({
	    @NamedQuery(name = "FindTCommReceipientsByCommIdAndStaffId", query = "select myTCommReceipient from TCommReceipient myTCommReceipient WHERE myTCommReceipient.tCommReceipientId.staffId=?1 AND myTCommReceipient.tCommReceipientId.commId=?2 AND myTCommReceipient.tenantId = ?3"),
		@NamedQuery(name = "FindAllTCommReceipients", query = "select myTCommReceipient from TCommReceipient myTCommReceipient"),
		@NamedQuery(name = "CountTCommReceipients", query = "Select Count(c) from TCommReceipient c"),
		@NamedQuery(name = "FindTCommReceipientByTNoteStatus", query = "select myTCommReceipient from TCommReceipient myTCommReceipient where myTCommReceipient.noteStatusId = ?1 "),
		@NamedQuery(name = "CountTCommReceipientsByTNoteStatus", query = "select Count(*) from TCommReceipient myTCommReceipient where myTCommReceipient.noteStatusId = ?1 "),
		@NamedQuery(name = "FindTCommReceipientByTPers", query = "select myTCommReceipient from TCommReceipient myTCommReceipient where myTCommReceipient.tPers = ?1 "),
		@NamedQuery(name = "CountTCommReceipientsByTPers", query = "select Count(*) from TCommReceipient myTCommReceipient where myTCommReceipient.tPers = ?1 "),
		@NamedQuery(name = "FindTCommReceipientByTComm", query = "select myTCommReceipient from TCommReceipient myTCommReceipient where myTCommReceipient.tComm = ?1 "),
		@NamedQuery(name = "CountTCommReceipientsByTComm", query = "select Count(*) from TCommReceipient myTCommReceipient where myTCommReceipient.tComm = ?1 "),
		@NamedQuery(name = "FindTCommReceipientByTAckStatus", query = "select myTCommReceipient from TCommReceipient myTCommReceipient where myTCommReceipient.ackStatusId = ?1 "),
		@NamedQuery(name = "FindTCommsByMonthForLoggedIn", query = "select myTComm.commId, myTComm.commTitle, myTComm.activityStartDt from   TCommReceipient myTCommReceipient, TComm myTComm where myTCommReceipient.tComm.commId = myTComm.commId and myTCommReceipient.tComm.tCommStatus.commStatusId = ?2 " +
				  " AND myTCommReceipient.tCommReceipientId.staffId=?1 AND myTCommReceipient.tComm.validityEndDt>=?3 "+
				  " AND myTCommReceipient.tComm.tCommType.commTypeId=?4 AND myTCommReceipient.tComm.tenantId=?7 AND "+
				  "date_format(myTCommReceipient.tComm.activityStartDt,'%m/%d/%Y') >= ?5 AND date_format(myTCommReceipient.tComm.activityStartDt,'%m/%d/%Y') <= ?6"),
	   @NamedQuery(name = "FindActivityByDate", query = "select myTComm.commId, myTComm.commTitle, myTComm.activityStartDt from TCommReceipient myTCommReceipient, TComm myTComm where myTCommReceipient.tComm.commId = myTComm.commId and myTCommReceipient.tComm.tCommStatus.commStatusId = ?2 " +
				  " AND myTCommReceipient.tCommReceipientId.staffId=?1 AND myTCommReceipient.tComm.validityEndDt>=?6 "+
				  " AND myTCommReceipient.tComm.tCommType.commTypeId=?3 AND myTCommReceipient.tComm.tenantId=?5 AND "+
				  " myTCommReceipient.tComm.activityStartDt=?4"),		  
		@NamedQuery(name = "FindTCommRecipientByStatusDate", query = "select myTCommReceipient from TCommReceipient myTCommReceipient where myTCommReceipient.tComm.tCommStatus.commStatusId = ?1 " +
				  " AND myTCommReceipient.tCommReceipientId.staffId=?2 AND myTCommReceipient.tComm.validityStartDt<=?3 AND myTCommReceipient.tComm.validityEndDt>=?4" +
				  " AND myTCommReceipient.tComm.tCommType.commTypeId=?5 AND myTCommReceipient.tComm.tenantId=?6 ORDER BY  myTCommReceipient.tComm.validityStartDt desc"),
	   @NamedQuery(name = "FindTCommRecipientByStatusPublDate", query = "select myTComm.commId, myTComm.commTitle, myTComm.createDt, myTComm.commDetail, myTComm.publishedDt, myTComm.ackRequired, myTComm.importanceFlag "+ 
							" from TCommReceipient myTCommReceipient, TComm myTComm where myTComm.commId = myTCommReceipient.tComm.commId  and myTCommReceipient.tComm.tCommStatus.commStatusId = ?1 "+
						    " AND myTCommReceipient.tCommReceipientId.staffId=?2 AND myTCommReceipient.tComm.validityStartDt<=?3 AND myTCommReceipient.tComm.validityEndDt>=?4 AND myTCommReceipient.tComm.tCommType.commTypeId=?5 AND myTCommReceipient.tComm.tenantId=?6  "+
							"  ORDER BY  myTCommReceipient.tComm.publishedDt desc , myTCommReceipient.tComm.commId desc"),
		@NamedQuery(name = "CountTCommReceipientsByTAckStatus", query = "select Count(*) from TCommReceipient myTCommReceipient where myTCommReceipient.ackStatusId = ?1 "), 
		@NamedQuery(name = "FindTCommReceipientByAckValue", query = "select myTCommReceipient from TCommReceipient myTCommReceipient where myTCommReceipient.tComm.tCommStatus.commStatusId = ?1 "
				+ " AND myTCommReceipient.tCommReceipientId.staffId=?2 AND myTCommReceipient.tComm.tCommType.commTypeId=?3 "
				+ " AND myTCommReceipient.tComm.tenantId=?4  AND ackStatusId = ?5 AND myTCommReceipient.tComm.ackRequired=?6 AND myTCommReceipient.tComm.validityStartDt<=?7 AND myTCommReceipient.tComm.validityEndDt>=?8"),
		@NamedQuery(name = "FindCountTCommReceipientByAckValue", query = "select Count(*)  from TCommReceipient myTCommReceipient where myTCommReceipient.tComm.tCommStatus.commStatusId = ?1 "
				+ " AND myTCommReceipient.tCommReceipientId.staffId=?2 AND myTCommReceipient.tComm.tCommType.commTypeId=?3 "
				+ " AND myTCommReceipient.tComm.tenantId=?4  AND ackStatusId = ?5 AND myTCommReceipient.tComm.ackRequired=?6 AND myTCommReceipient.tComm.validityStartDt<=?7 AND myTCommReceipient.tComm.validityEndDt>=?8 "),	
				@NamedQuery(name = "FindCountTCommReceipientALL", query = "select Count(*)  from TCommReceipient myTCommReceipient where myTCommReceipient.tComm.tCommStatus.commStatusId = ?1 "
						+ " AND myTCommReceipient.tCommReceipientId.staffId=?2 AND myTCommReceipient.tComm.tCommType.commTypeId=?3 "
						+ " AND myTCommReceipient.tComm.tenantId=?4  AND myTCommReceipient.tComm.validityStartDt<=?5 AND myTCommReceipient.tComm.validityEndDt>=?6 ")	})
@Table(name = "t_comm_receipient")
public class TCommReceipient implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TCommReceipientId tCommReceipientId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "comm_read_dt", nullable = true, length = 10)
	private Date commReadDt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "comm_ack_dt", nullable = true, length = 10)
	private Date commAckDt;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "reject_reason", nullable = true, length = 200)
	private String rejectReason;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;
	
	@NotNull
	@Column(name = "note_status_id", nullable = false, length = 255)
	private Integer noteStatusId;

	/*@ManyToOne
	@JoinColumn(name = "note_status_id", referencedColumnName = "note_status_id", nullable = false, insertable = true, updatable = true)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TNoteStatus tNoteStatus;*/

	@ManyToOne
	@JoinColumn(name = "staff_id", referencedColumnName = "staff_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@NotAudited
	private TPers tPers;

	@ManyToOne
	@JoinColumn(name = "comm_id", referencedColumnName = "comm_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@NotAudited
	private TComm tComm;

/*	@ManyToOne
	@JoinColumn(name = "ack_status_id", referencedColumnName = "ack_status_id", nullable = true, insertable = true, updatable = true)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TAckStatus tAckStatus;*/
	
	@NotNull
	@Column(name = "ack_status_id", nullable = false, length = 255)
	private Integer ackStatusId;

	
	
	/**
	 * 
	 * @generated
	 */
	@Column(name = "fav_flag", nullable = true, length = 1)
	private Character favFlag;
	/**
	 * 
	 * @generated
	 */

	/**
	 * 
	 * @generated
	 */

	public void setFavFlag(final Character FavFlag) {
		this.favFlag = FavFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getFavFlag() {
		return this.favFlag;
	}

	/**
	 * 
	 * @generated
	 */
	/**
	 *
	 * @generated
	 */
	public TCommReceipient() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTCommReceipientId(final TCommReceipientId tCommReceipientId) {
		this.tCommReceipientId = tCommReceipientId;
	}

	/**
	 * 
	 * @generated
	 */
	public TCommReceipientId getTCommReceipientId() {
		return this.tCommReceipientId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCommReadDt(final Date commReadDt) {
		if (commReadDt != null) {
			this.commReadDt = (Date) commReadDt.clone();
		} else {
			this.commReadDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getCommReadDt() {
		if (this.commReadDt != null) {
			return (Date) this.commReadDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setCommAckDt(final Date commAckDt) {
		if (commAckDt != null) {
			this.commAckDt = (Date) commAckDt.clone();
		} else {
			this.commAckDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getCommAckDt() {
		if (this.commAckDt != null) {
			return (Date) this.commAckDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setRejectReason(final String rejectReason) {
		this.rejectReason = rejectReason;
	}

	/**
	 * 
	 * @generated
	 */
	public String getRejectReason() {
		return this.rejectReason;
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
	/*public TNoteStatus getTNoteStatus() {
		return this.tNoteStatus;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public void setTNoteStatus(final TNoteStatus tNoteStatus) {
		this.tNoteStatus = tNoteStatus;

	}*/

	public Integer getNoteStatusId() {
		return noteStatusId;
	}

	public void setNoteStatusId(Integer noteStatusId) {
		this.noteStatusId = noteStatusId;
	}

	/**
	 * 
	 * @generated
	 */
	public TPers getTPers() {
		return this.tPers;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPers(final TPers tPers) {
		this.tPers = tPers;
		if (this.tPers != null && this.tPers.getStaffId() != null) {

			this.tCommReceipientId.setStaffId(this.tPers.getStaffId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	public TComm getTComm() {
		return this.tComm;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTComm(final TComm tComm) {
		this.tComm = tComm;
		if (this.tComm != null && this.tComm.getCommId() != null) {

			this.tCommReceipientId.setCommId(this.tComm.getCommId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	/*public TAckStatus getTAckStatus() {
		return this.tAckStatus;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTAckStatus(final TAckStatus tAckStatus) {
		this.tAckStatus = tAckStatus;

	}*/

	public Integer getAckStatusId() {
		return ackStatusId;
	}

	public void setAckStatusId(Integer ackStatusId) {
		this.ackStatusId = ackStatusId;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCommReceipient that) {
		setTCommReceipientId(that.getTCommReceipientId());
		setCommReadDt(that.getCommReadDt());
		setCommAckDt(that.getCommAckDt());
		setRejectReason(that.getRejectReason());
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
		result = prime * result + ((tCommReceipientId == null) ? 0 : tCommReceipientId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tCommReceipientId=[").append(tCommReceipientId).append("] ");
		buffer.append("commReadDt=[").append(commReadDt).append("] ");
		buffer.append("commAckDt=[").append(commAckDt).append("] ");
		buffer.append("rejectReason=[").append(rejectReason).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("favFlag=[").append(favFlag).append("] ");
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
		final TCommReceipient other = (TCommReceipient) obj;
		if (tCommReceipientId == null) {
			if (other.tCommReceipientId != null) {
				return false;
			}
		} else if (!tCommReceipientId.equals(other.tCommReceipientId)) {
			return false;
		}
		return true;
	}
}
