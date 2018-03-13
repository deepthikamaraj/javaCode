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

import org.hibernate.envers.NotAudited;

/** 
 * JPA class representing TRptConfigTNoteType 
 * The corresponding mapping table is t_rpt_config_t_note_type 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTRptConfigTNoteTypes", query = "select myTRptConfigTNoteType from TRptConfigTNoteType myTRptConfigTNoteType"),
		@NamedQuery(name = "CountTRptConfigTNoteTypes", query = "Select Count(c) from TRptConfigTNoteType c"),
		/*@NamedQuery(name = "FindTRptConfigTNoteTypeByTNoteType", query = "select myTRptConfigTNoteType from TRptConfigTNoteType myTRptConfigTNoteType where myTRptConfigTNoteType.tNoteType = ?1 "),
		@NamedQuery(name = "CountTRptConfigTNoteTypesByTNoteType", query = "select Count(*) from TRptConfigTNoteType myTRptConfigTNoteType where myTRptConfigTNoteType.tNoteType = ?1 "),*/
		@NamedQuery(name = "FindTRptConfigTNoteTypeByTRptConfig", query = "select myTRptConfigTNoteType from TRptConfigTNoteType myTRptConfigTNoteType where myTRptConfigTNoteType.tRptConfig = ?1 "),
		@NamedQuery(name = "CountTRptConfigTNoteTypesByTRptConfig", query = "select Count(*) from TRptConfigTNoteType myTRptConfigTNoteType where myTRptConfigTNoteType.tRptConfig = ?1 ")		})
@Table(name = "t_rpt_config_t_note_type")
public class TRptConfigTNoteType implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	@NotAudited
	private TRptConfigTNoteTypeId tRptConfigTNoteTypeId;

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
	@Column(name = "active_flag", nullable = true, length = 1)
	private Character activeFlag;
	
	/*@ManyToOne
	@JoinColumn(name = "note_type_id", referencedColumnName = "note_type_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@NotAudited
	//@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TNoteType tNoteType;*/

	@ManyToOne
	@JoinColumn(name = "rpt_config_id", referencedColumnName = "rpt_config_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@NotAudited
	private TRptConfig tRptConfig;

	/**
	 *
	 * @generated
	 */
	public TRptConfigTNoteType() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTRptConfigTNoteTypeId(final TRptConfigTNoteTypeId tRptConfigTNoteTypeId) {
		this.tRptConfigTNoteTypeId = tRptConfigTNoteTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public TRptConfigTNoteTypeId getTRptConfigTNoteTypeId() {
		return this.tRptConfigTNoteTypeId;
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
	/*public TNoteType getTNoteType() {
		return this.tNoteType;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTNoteType(final TNoteType tNoteType) {
		this.tNoteType = tNoteType;
		if (this.tNoteType != null && this.tNoteType.getNoteTypeId() != null) {

			this.tRptConfigTNoteTypeId.setNoteTypeId(this.tNoteType.getNoteTypeId());

		}

	}*/

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

			this.tRptConfigTNoteTypeId.setRptConfigId(this.tRptConfig.getRptConfigId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TRptConfigTNoteType that) {
		setTRptConfigTNoteTypeId(that.getTRptConfigTNoteTypeId());
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
		result = prime * result + ((tRptConfigTNoteTypeId == null) ? 0 : tRptConfigTNoteTypeId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tRptConfigTNoteTypeId=[").append(tRptConfigTNoteTypeId).append("] ");
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
		final TRptConfigTNoteType other = (TRptConfigTNoteType) obj;
		if (tRptConfigTNoteTypeId == null) {
			if (other.tRptConfigTNoteTypeId != null) {
				return false;
			}
		} else if (!tRptConfigTNoteTypeId.equals(other.tRptConfigTNoteTypeId)) {
			return false;
		}
		return true;
	}
}
