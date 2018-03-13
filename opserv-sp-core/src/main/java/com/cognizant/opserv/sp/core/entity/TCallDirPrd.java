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

/** 
 * JPA class representing TCallDirPrd 
 * The corresponding mapping table is t_call_dir_prd 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTCallDirPrds", query = "select myTCallDirPrd from TCallDirPrd myTCallDirPrd"),
		@NamedQuery(name = "CountTCallDirPrds", query = "Select Count(c) from TCallDirPrd c"),
		@NamedQuery(name = "FindTCallDirPrdByTCallDir", query = "select myTCallDirPrd from TCallDirPrd myTCallDirPrd where myTCallDirPrd.tCallDir = ?1 "),
		@NamedQuery(name = "CountTCallDirPrdsByTCallDir", query = "select Count(*) from TCallDirPrd myTCallDirPrd where myTCallDirPrd.tCallDir = ?1 "),
		//@NamedQuery(name = "FindTCallDirPrdByTPrdPrtType", query = "select myTCallDirPrd from TCallDirPrd myTCallDirPrd where myTCallDirPrd.tPrdPrtType = ?1 "),
		//@NamedQuery(name = "CountTCallDirPrdsByTPrdPrtType", query = "select Count(*) from TCallDirPrd myTCallDirPrd where myTCallDirPrd.tCallDirPrdId.tPrdPrtType = ?1 "),
		@NamedQuery(name = "getTCallDirPrdsByPrdId", query = "select myTCallDirPrd from TCallDirPrd myTCallDirPrd where myTCallDirPrd.prdId = ?1 ")
		})
@Table(name = "t_call_dir_prd")
public class TCallDirPrd implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TCallDirPrdId tCallDirPrdId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "prd_id", nullable = false, length = 255)
	private Long prdId;

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
	private Date updateDt;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

	@ManyToOne
	@JoinColumn(name = "call_dir_id", referencedColumnName = "call_dir_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TCallDir tCallDir;

	/*@ManyToOne
	@JoinColumn(name = "prd_prt_type_id", referencedColumnName = "prd_prt_type_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TPrdPrtType tPrdPrtType;*/

	/**
	 *
	 * @generated
	 */
	/*public TCallDirPrd() {
	}*/

	/**
	 * 
	 * @generated
	 */

	public void setTCallDirPrdId(final TCallDirPrdId tCallDirPrdId) {
		this.tCallDirPrdId = tCallDirPrdId;
	}

	/**
	 * 
	 * @generated
	 */
	public TCallDirPrdId getTCallDirPrdId() {
		return this.tCallDirPrdId;
	}


	public void setActiveFlag(final Character activeFlag) {
		this.activeFlag = activeFlag;
	}
	/**
	 * 
	 * @generated
	 */

	public Long getPrdId() {
		return prdId;
	}
	/**
	 * 
	 * @generated
	 */

	public void setPrdId(Long prdId) {
		this.prdId = prdId;
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
		if (createDt == null) {
			Date x=null;
			this.createDt = x;
		} else {
			this.createDt = (Date) createDt.clone();
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getCreateDt() {
		if (this.createDt != null) {
			return null;
		} else {
			
			return (Date) this.createDt.clone();
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

	public void setUpdateDt(final Date updateDt) {
		if (updateDt == null) {
			Date x=null;
			this.updateDt = x;
		} else {
			
			this.updateDt = (Date) updateDt.clone();
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getUpdateDt() {
		if (this.updateDt == null) {
			return null;
			
		} else {
			return (Date) this.updateDt.clone();
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
	public TCallDir getTCallDir() {
		return this.tCallDir;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCallDir(final TCallDir tCallDir) {
		this.tCallDir = tCallDir;
		if (this.tCallDir != null && this.tCallDir.getCallDirId() != null) {

			this.tCallDirPrdId.setCallDirId(this.tCallDir.getCallDirId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	/*public TPrdPrtType getTPrdPrtType() {
		return this.tPrdPrtType;
	}
*/
	/**
	 * 
	 * @generated
	 */
	/*public void setTPrdPrtType(final TPrdPrtType tPrdPrtType) {
		this.tPrdPrtType = tPrdPrtType;
		if (this.tPrdPrtType != null && this.tPrdPrtType.getPrdPrtTypeId() != null) {

			this.tCallDirPrdId.setPrdPrtTypeId(this.tPrdPrtType.getPrdPrtTypeId());

		}

	}
*/
	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCallDirPrd that) {
		setTCallDirPrdId(that.getTCallDirPrdId());
		setPrdId(that.getPrdId());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
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
		result = prime * result + ((tCallDirPrdId == null) ? 0 : tCallDirPrdId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tCallDirPrdId=["+tCallDirPrdId+"] ");
		buffer.append("prdId=["+prdId+"] ");
		buffer.append("activeFlag=["+activeFlag+"] ");
		buffer.append("createdBy=["+createdBy+"] ");
		buffer.append("createDt=["+createDt+"] ");
		buffer.append("updatedBy=["+updatedBy+"] ");
		buffer.append("updateDt=["+updateDt+"] ");
		buffer.append("tenantId=["+tenantId+"] ");

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
		final TCallDirPrd other = (TCallDirPrd) obj;
		if (tCallDirPrdId == null) {
			if (other.tCallDirPrdId != null) {
				return false;
			}
		} else if (!tCallDirPrdId.equals(other.tCallDirPrdId)) {
			return false;
		}
		return true;
	}
}
