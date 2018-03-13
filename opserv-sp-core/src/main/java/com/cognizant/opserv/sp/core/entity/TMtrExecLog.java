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

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TMtrExecLog 
 * The corresponding mapping table is t_mtr_exec_log 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTMtrExecLogs", query = "select myTMtrExecLog from TMtrExecLog myTMtrExecLog"),
		@NamedQuery(name = "CountTMtrExecLogs", query = "Select Count(c) from TMtrExecLog c"),
		@NamedQuery(name = "FindTMtrExecLogByTMsgType", query = "select myTMtrExecLog from TMtrExecLog myTMtrExecLog where myTMtrExecLog.tMsgType = ?1 "),
		@NamedQuery(name = "CountTMtrExecLogsByTMsgType", query = "select Count(*) from TMtrExecLog myTMtrExecLog where myTMtrExecLog.tMsgType = ?1 "),
		@NamedQuery(name = "FindTMtrExecLogByTMtrExec", query = "select myTMtrExecLog from TMtrExecLog myTMtrExecLog where myTMtrExecLog.tMtrExec = ?1 "),
		@NamedQuery(name = "CountTMtrExecLogsByTMtrExec", query = "select Count(*) from TMtrExecLog myTMtrExecLog where myTMtrExecLog.tMtrExec = ?1 ") })
@Table(name = "t_mtr_exec_log")
public class TMtrExecLog implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TMtrExecLogId tMtrExecLogId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 3000)
	@Column(name = "msg_text", nullable = true, length = 3000)
	private String msgText;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 20)
	@Column(name = "locale_id", nullable = false, length = 20)
	private String localeId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

	@ManyToOne
	@JoinColumn(name = "msg_type_id", referencedColumnName = "msg_type_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TMsgType tMsgType;

	@ManyToOne
	@JoinColumn(name = "exec_id", referencedColumnName = "exec_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TMtrExec tMtrExec;

	/**
	 *
	 * @generated
	 */
	public TMtrExecLog() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTMtrExecLogId(final TMtrExecLogId tMtrExecLogId) {
		this.tMtrExecLogId = tMtrExecLogId;
	}

	/**
	 * 
	 * @generated
	 */
	public TMtrExecLogId getTMtrExecLogId() {
		return this.tMtrExecLogId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setMsgText(final String msgText) {
		this.msgText = msgText;
	}

	/**
	 * 
	 * @generated
	 */
	public String getMsgText() {
		return this.msgText;
	}

	/**
	 * 
	 * @generated
	 */

	public void setLocaleId(final String localeId) {
		this.localeId = localeId;
	}

	/**
	 * 
	 * @generated
	 */
	public String getLocaleId() {
		return this.localeId;
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
	public TMsgType getTMsgType() {
		return this.tMsgType;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTMsgType(final TMsgType tMsgType) {
		this.tMsgType = tMsgType;

	}

	/**
	 * 
	 * @generated
	 */
	public TMtrExec getTMtrExec() {
		return this.tMtrExec;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTMtrExec(final TMtrExec tMtrExec) {
		this.tMtrExec = tMtrExec;
		if (this.tMtrExec != null && this.tMtrExec.getExecId() != null) {

			this.tMtrExecLogId.setExecId(this.tMtrExec.getExecId());

		}
		if (this.tMtrExec != null && this.tMtrExec.getExecId() != null) {

			this.tMtrExecLogId.setConfigId(this.tMtrExec.getTMtrExecConfig().getConfigId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TMtrExecLog that) {
		setTMtrExecLogId(that.getTMtrExecLogId());
		setMsgText(that.getMsgText());
		setLocaleId(that.getLocaleId());
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
		result = prime * result + ((tMtrExecLogId == null) ? 0 : tMtrExecLogId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tMtrExecLogId=[").append(tMtrExecLogId).append("] ");
		buffer.append("msgText=[").append(msgText).append("] ");
		buffer.append("localeId=[").append(localeId).append("] ");
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
		final TMtrExecLog other = (TMtrExecLog) obj;
		if (tMtrExecLogId == null) {
			if (other.tMtrExecLogId != null) {
				return false;
			}
		} else if (!tMtrExecLogId.equals(other.tMtrExecLogId)) {
			return false;
		}
		return true;
	}
}
