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

/** 
 * JPA class representing TRptConfigTPers 
 * The corresponding mapping table is t_rpt_config_t_pers 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTRptConfigTPerss", query = "select myTRptConfigTPers from TRptConfigTPers myTRptConfigTPers"),
		@NamedQuery(name = "CountTRptConfigTPerss", query = "Select Count(c) from TRptConfigTPers c"),
		@NamedQuery(name = "FindTRptConfigTPersByTPers", query = "select myTRptConfigTPers from TRptConfigTPers myTRptConfigTPers where myTRptConfigTPers.tPers = ?1 "),
		@NamedQuery(name = "CountTRptConfigTPerssByTPers", query = "select Count(*) from TRptConfigTPers myTRptConfigTPers where myTRptConfigTPers.tPers = ?1 "),
		@NamedQuery(name = "FindTRptConfigTPersByTRptConfig", query = "select myTRptConfigTPers from TRptConfigTPers myTRptConfigTPers where myTRptConfigTPers.tRptConfig = ?1 "),
		@NamedQuery(name = "CountTRptConfigTPerssByTRptConfig", query = "select Count(*) from TRptConfigTPers myTRptConfigTPers where myTRptConfigTPers.tRptConfig = ?1 ") })
@Table(name = "t_rpt_config_t_pers")
public class TRptConfigTPers implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TRptConfigTPersId tRptConfigTPersId;

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
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

	@ManyToOne
	@JoinColumn(name = "staff_id", referencedColumnName = "staff_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TPers tPers;

	@ManyToOne
	@JoinColumn(name = "rpt_config_id", referencedColumnName = "rpt_config_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TRptConfig tRptConfig;

	/**
	 *
	 * @generated
	 */
	public TRptConfigTPers() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTRptConfigTPersId(final TRptConfigTPersId tRptConfigTPersId) {
		this.tRptConfigTPersId = tRptConfigTPersId;
	}

	/**
	 * 
	 * @generated
	 */
	public TRptConfigTPersId getTRptConfigTPersId() {
		return this.tRptConfigTPersId;
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

			this.tRptConfigTPersId.setStaffId(this.tPers.getStaffId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	public TRptConfig getTRptConfig() {
		return this.tRptConfig;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTRptConfig(final TRptConfig tRptConfig) {
		this.tRptConfig = tRptConfig;
		if (this.tRptConfig != null && this.tRptConfig.getRptConfigId() != null) {

			this.tRptConfigTPersId.setRptConfigId(this.tRptConfig.getRptConfigId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TRptConfigTPers that) {
		setTRptConfigTPersId(that.getTRptConfigTPersId());
		setActiveFlag(that.getActiveFlag());
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
		result = prime * result + ((tRptConfigTPersId == null) ? 0 : tRptConfigTPersId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tRptConfigTPersId=[").append(tRptConfigTPersId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
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
		final TRptConfigTPers other = (TRptConfigTPers) obj;
		if (tRptConfigTPersId == null) {
			if (other.tRptConfigTPersId != null) {
				return false;
			}
		} else if (!tRptConfigTPersId.equals(other.tRptConfigTPersId)) {
			return false;
		}
		return true;
	}
}
