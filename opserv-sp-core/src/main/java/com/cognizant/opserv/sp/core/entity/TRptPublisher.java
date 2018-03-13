package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

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

/** 
 * JPA class representing TRptPublisher 
 * The corresponding mapping table is t_rpt_publisher 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTRptPublishers", query = "select myTRptPublisher from TRptPublisher myTRptPublisher"),
		@NamedQuery(name = "CountTRptPublishers", query = "Select Count(c) from TRptPublisher c"),
		@NamedQuery(name = "FindTRptPublisherByTTargetRecipientPref", query = "select myTRptPublisher from TRptPublisher myTRptPublisher where myTRptPublisher.tTargetRecipientPref = ?1 "),
		@NamedQuery(name = "CountTRptPublishersByTTargetRecipientPref", query = "select Count(*) from TRptPublisher myTRptPublisher where myTRptPublisher.tTargetRecipientPref = ?1 "),
		@NamedQuery(name = "FindTRptPublisherByTPers", query = "select myTRptPublisher from TRptPublisher myTRptPublisher where myTRptPublisher.tPers = ?1 "),
		@NamedQuery(name = "CountTRptPublishersByTPers", query = "select Count(*) from TRptPublisher myTRptPublisher where myTRptPublisher.tPers = ?1 "),
		@NamedQuery(name = "FindTRptPublisherByTRpt", query = "select myTRptPublisher from TRptPublisher myTRptPublisher where myTRptPublisher.tRpt = ?1 "),
		@NamedQuery(name = "CountTRptPublishersByTRpt", query = "select Count(*) from TRptPublisher myTRptPublisher where myTRptPublisher.tRpt = ?1 ") })
@Table(name = "t_rpt_publisher")
public class TRptPublisher implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	@Audited
	private TRptPublisherId tRptPublisherId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

	@ManyToOne
	@JoinColumn(name = "recipient_pref_id", referencedColumnName = "recipient_pref_id", nullable = true, insertable = true, updatable = true)
	@Valid
	private TTargetRecipientPref tTargetRecipientPref;

	@ManyToOne
	@JoinColumn(name = "staff_id", referencedColumnName = "staff_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TPers tPers;

	@ManyToOne
	@JoinColumn(name = "rpt_id", referencedColumnName = "rpt_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TRpt tRpt;

	/**
	 *
	 * @generated
	 */
	public TRptPublisher() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTRptPublisherId(final TRptPublisherId tRptPublisherId) {
		this.tRptPublisherId = tRptPublisherId;
	}

	/**
	 * 
	 * @generated
	 */
	public TRptPublisherId getTRptPublisherId() {
		return this.tRptPublisherId;
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
	public TTargetRecipientPref getTTargetRecipientPref() {
		return this.tTargetRecipientPref;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTTargetRecipientPref(final TTargetRecipientPref tTargetRecipientPref) {
		this.tTargetRecipientPref = tTargetRecipientPref;

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

			this.tRptPublisherId.setStaffId(this.tPers.getStaffId());

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

			this.tRptPublisherId.setRptId(this.tRpt.getRptId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TRptPublisher that) {
		setTRptPublisherId(that.getTRptPublisherId());
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
		result = prime * result + ((tRptPublisherId == null) ? 0 : tRptPublisherId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tRptPublisherId=[").append(tRptPublisherId).append("] ");
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
		final TRptPublisher other = (TRptPublisher) obj;
		if (tRptPublisherId == null) {
			if (other.tRptPublisherId != null) {
				return false;
			}
		} else if (!tRptPublisherId.equals(other.tRptPublisherId)) {
			return false;
		}
		return true;
	}
}
