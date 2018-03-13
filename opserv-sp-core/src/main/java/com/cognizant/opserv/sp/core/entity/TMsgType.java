package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
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

import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TMsgType 
 * The corresponding mapping table is t_msg_type 
 */

@Entity
@NamedQueries({ @NamedQuery(name = "FindAllTMsgTypes", query = "select myTMsgType from TMsgType myTMsgType"),
		@NamedQuery(name = "CountTMsgTypes", query = "Select Count(c) from TMsgType c") })
@Table(name = "t_msg_type", uniqueConstraints = @UniqueConstraint(columnNames = { "msg_type_id" }))
public class TMsgType implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "msg_type_id", nullable = false, length = 255)
	private Integer msgTypeId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "msg_type_desc", nullable = true, length = 100)
	private String msgTypeDesc;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "active_flag", nullable = true, length = 1)
	private Character activeFlag;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tMsgType")
	private Set<TMtrExecLog> tMtrExecLogss;

	/**
	 *
	 * @generated
	 */
	public TMsgType() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setMsgTypeId(final Integer msgTypeId) {
		this.msgTypeId = msgTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getMsgTypeId() {
		return this.msgTypeId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setMsgTypeDesc(final String msgTypeDesc) {
		this.msgTypeDesc = msgTypeDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getMsgTypeDesc() {
		return this.msgTypeDesc;
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
	public Set<TMtrExecLog> getTMtrExecLogss() {
		return this.tMtrExecLogss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTMtrExecLogss(final Set<TMtrExecLog> tMtrExecLogss) {
		this.tMtrExecLogss = tMtrExecLogss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TMsgType that) {
		setMsgTypeId(that.getMsgTypeId());
		setMsgTypeDesc(that.getMsgTypeDesc());
		setActiveFlag(that.getActiveFlag());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((msgTypeId == null) ? 0 : msgTypeId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("msgTypeId=[").append(msgTypeId).append("] ");
		buffer.append("msgTypeDesc=[").append(msgTypeDesc).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");

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
		final TMsgType other = (TMsgType) obj;
		if (msgTypeId == null) {
			if (other.msgTypeId != null) {
				return false;
			}
		} else if (!msgTypeId.equals(other.msgTypeId)) {
			return false;
		}
		return true;
	}
}
