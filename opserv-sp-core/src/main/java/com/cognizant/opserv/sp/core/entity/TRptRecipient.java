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
 * JPA class representing TRptRecipient 
 * The corresponding mapping table is t_rpt_recipient 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTRptRecipients", query = "select myTRptRecipient from TRptRecipient myTRptRecipient"),
		@NamedQuery(name = "CountTRptRecipients", query = "Select Count(c) from TRptRecipient c"),
		@NamedQuery(name = "FindTRptRecipientsbyTRptType", query = "select myTRptRecipient from TRptRecipient myTRptRecipient where myTRptRecipient.tRpt.tRptSchedExecution.tRptSched.tRptConfig.rptTypeId= ?1 and myTRptRecipient.tenantId = ?2"),
		@NamedQuery(name = "FindTRptRecipientByTNoteStatus", query = "select myTRptRecipient from TRptRecipient myTRptRecipient where myTRptRecipient.noteStatusId = ?1 "),
		@NamedQuery(name = "CountTRptRecipientsByTNoteStatus", query = "select Count(*) from TRptRecipient myTRptRecipient where myTRptRecipient.noteStatusId = ?1 "),
		@NamedQuery(name = "FindTRptRecipientByTPers", query = "select myTRptRecipient from TRptRecipient myTRptRecipient where myTRptRecipient.tPers = ?1 "),
		@NamedQuery(name = "CountTRptRecipientsByTPers", query = "select Count(*) from TRptRecipient myTRptRecipient where myTRptRecipient.tPers = ?1 "),
		@NamedQuery(name = "FindTRptRecipientByTRpt", query = "select myTRptRecipient from TRptRecipient myTRptRecipient where myTRptRecipient.tRpt = ?1 "),
		@NamedQuery(name = "CountTRptRecipientsByTRpt", query = "select Count(*) from TRptRecipient myTRptRecipient where myTRptRecipient.tRpt = ?1 "),
		@NamedQuery(name = "FindTRptRecipientByTAckStatus", query = "select myTRptRecipient from TRptRecipient myTRptRecipient where myTRptRecipient.ackStatusId = ?1 "),
		@NamedQuery(name = "CountTRptRecipientsByTAckStatus", query = "select Count(*) from TRptRecipient myTRptRecipient where myTRptRecipient.ackStatusId = ?1 "),
		@NamedQuery(name = "FindTRptByRecipientAndMonth", query = "select myTRptRecipient from TRptRecipient myTRptRecipient where MONTH(myTRptRecipient.readDt) = ?1 and YEAR(myTRptRecipient.readDt) = ?2 and myTRptRecipient.tRptRecipientId.staffId = ?3 "),
		@NamedQuery(name = "FindTRptRecipientByTRptAndTPers", query = "select myTRptRecipient from TRptRecipient myTRptRecipient where myTRptRecipient.tRpt = ?1 and myTRptRecipient.tPers = ?2"),
		@NamedQuery(name = "FindRptDesignByTRptAndTPers", query = "select myTRptRecipient.rptDesign from TRptRecipient myTRptRecipient where myTRptRecipient.tRptRecipientId = ?1")
		})
@Table(name = "t_rpt_recipient")
public class TRptRecipient implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	@Audited
	private TRptRecipientId tRptRecipientId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "ack_dt", nullable = true, length = 10)
	private Date ackDt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "reject_dt", nullable = true, length = 10)
	private Date rejectDt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "read_dt", nullable = true, length = 10)
	private Date readDt;

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
	@Length(max = 200)
	@Column(name = "reject_reason", nullable = true, length = 200)
	private String rejectReason;
	
	/**
	 * 
	 * @generated
	 */
	@Column(name = "rpt_design", nullable = true, length = 255)
	private byte[] rptDesign;
	
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
	@JoinColumn(name = "rpt_id", referencedColumnName = "rpt_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@NotAudited
	private TRpt tRpt;

	/*@ManyToOne
	@JoinColumn(name = "reject_reason_id", referencedColumnName = "reject_reason_id", nullable = true, insertable = true, updatable = true)
	@Valid
	private TRejectReason tRejectReason;*/

	/*@ManyToOne
	@JoinColumn(name = "ack_status_id", referencedColumnName = "ack_status_id", nullable = true, insertable = true, updatable = true)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TAckStatus tAckStatus;
*/
	

	@NotNull
	@Column(name = "ack_status_id", nullable = false, length = 255)
	private Integer ackStatusId;
	/**
	 *
	 * @generated
	 */
	public TRptRecipient() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTRptRecipientId(final TRptRecipientId tRptRecipientId) {
		this.tRptRecipientId = tRptRecipientId;
	}

	/**
	 * 
	 * @generated
	 */
	public TRptRecipientId getTRptRecipientId() {
		return this.tRptRecipientId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAckDt(final Date ackDt) {
		if (ackDt != null) {
			this.ackDt = (Date) ackDt.clone();
		} else {
			this.ackDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getAckDt() {
		if (this.ackDt != null) {
			return (Date) this.ackDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setRejectDt(final Date rejectDt) {
		if (rejectDt != null) {
			this.rejectDt = (Date) rejectDt.clone();
		} else {
			this.rejectDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getRejectDt() {
		if (this.rejectDt != null) {
			return (Date) this.rejectDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setReadDt(final Date readDt) {
		if (readDt != null) {
			this.readDt = (Date) readDt.clone();
		} else {
			this.readDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getReadDt() {
		if (this.readDt != null) {
			return (Date) this.readDt.clone();
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

	public byte[] getRptDesign() {
		return rptDesign;
	}

	public void setRptDesign(byte[] rptDesign) {
		this.rptDesign = rptDesign;
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

			this.tRptRecipientId.setStaffId(this.tPers.getStaffId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	public TRpt getTRpt() {
		return this.tRpt;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTRpt(final TRpt tRpt) {
		this.tRpt = tRpt;
		if (this.tRpt != null && this.tRpt.getRptId() != null) {

			this.tRptRecipientId.setRptId(this.tRpt.getRptId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	/*public TRejectReason getTRejectReason() {
		return this.tRejectReason;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public void setTRejectReason(final TRejectReason tRejectReason) {
		this.tRejectReason = tRejectReason;

	}*/

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
	public void copy(final TRptRecipient that) {
		setTRptRecipientId(that.getTRptRecipientId());
		setAckDt(that.getAckDt());
		setRejectDt(that.getRejectDt());
		setReadDt(that.getReadDt());
		setTenantId(that.getTenantId());
		setRptDesign(that.getRptDesign());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((tRptRecipientId == null) ? 0 : tRptRecipientId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tRptRecipientId=[").append(tRptRecipientId).append("] ");
		buffer.append("ackDt=[").append(ackDt).append("] ");
		buffer.append("rejectDt=[").append(rejectDt).append("] ");
		buffer.append("readDt=[").append(readDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("rejectReason=[").append(rejectReason).append("] ");
		buffer.append("rptDesign=[").append(rptDesign).append("] ");

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
		final TRptRecipient other = (TRptRecipient) obj;
		if (tRptRecipientId == null) {
			if (other.tRptRecipientId != null) {
				return false;
			}
		} else if (!tRptRecipientId.equals(other.tRptRecipientId)) {
			return false;
		}
		return true;
	}
}
