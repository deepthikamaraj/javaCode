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
import org.hibernate.envers.NotAudited;

/** 
 * JPA class representing TCommPublisher 
 * The corresponding mapping table is t_comm_publisher 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTCommPublishers", query = "select myTCommPublisher from TCommPublisher myTCommPublisher"),
		@NamedQuery(name = "CountTCommPublishers", query = "Select Count(c) from TCommPublisher c"),
		@NamedQuery(name = "FindTCommPublisherByTTargetRecipientPref", query = "select myTCommPublisher from TCommPublisher myTCommPublisher where myTCommPublisher.tTargetRecipientPref = ?1 "),
		@NamedQuery(name = "CountTCommPublishersByTTargetRecipientPref", query = "select Count(*) from TCommPublisher myTCommPublisher where myTCommPublisher.tTargetRecipientPref = ?1 "),
		@NamedQuery(name = "FindTCommPublisherByTPers", query = "select myTCommPublisher from TCommPublisher myTCommPublisher where myTCommPublisher.tPers = ?1 "),
		@NamedQuery(name = "CountTCommPublishersByTPers", query = "select Count(*) from TCommPublisher myTCommPublisher where myTCommPublisher.tPers = ?1 "),
		@NamedQuery(name = "FindTCommPublisherByTComm", query = "select myTCommPublisher from TCommPublisher myTCommPublisher where myTCommPublisher.tComm = ?1 "),
		@NamedQuery(name = "CountTCommPublishersByTComm", query = "select Count(*) from TCommPublisher myTCommPublisher where myTCommPublisher.tComm = ?1 ") })
@Table(name = "t_comm_publisher")
public class TCommPublisher implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TCommPublisherId tCommPublisherId;

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
	@Audited
	private TTargetRecipientPref tTargetRecipientPref;

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

	/**
	 *
	 * @generated
	 */
	public TCommPublisher() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTCommPublisherId(final TCommPublisherId tCommPublisherId) {
		this.tCommPublisherId = tCommPublisherId;
	}

	/**
	 * 
	 * @generated
	 */
	public TCommPublisherId getTCommPublisherId() {
		return this.tCommPublisherId;
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

			this.tCommPublisherId.setStaffId(this.tPers.getStaffId());

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

			this.tCommPublisherId.setCommId(this.tComm.getCommId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCommPublisher that) {
		setTCommPublisherId(that.getTCommPublisherId());
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
		result = prime * result + ((tCommPublisherId == null) ? 0 : tCommPublisherId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tCommPublisherId=[").append(tCommPublisherId).append("] ");
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
		final TCommPublisher other = (TCommPublisher) obj;
		if (tCommPublisherId == null) {
			if (other.tCommPublisherId != null) {
				return false;
			}
		} else if (!tCommPublisherId.equals(other.tCommPublisherId)) {
			return false;
		}
		return true;
	}
}
