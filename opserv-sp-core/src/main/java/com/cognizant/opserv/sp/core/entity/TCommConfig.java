package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TCommConfig 
 * The corresponding mapping table is t_comm_config 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTCommConfigs", query = "select myTCommConfig from TCommConfig myTCommConfig"),
		@NamedQuery(name = "CountTCommConfigs", query = "Select Count(c) from TCommConfig c"),
		@NamedQuery(name = "FindTCommConfigByTCommType", query = "select myTCommConfig from TCommConfig myTCommConfig where myTCommConfig.tCommType = ?1 "),
		@NamedQuery(name = "CountTCommConfigsByTCommType", query = "select Count(myTCommConfig) from TCommConfig myTCommConfig where myTCommConfig.tCommType = ?1 ") })
@Table(name = "t_comm_config", uniqueConstraints = @UniqueConstraint(columnNames = { "config_id" }))
public class TCommConfig implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "config_id", nullable = false, length = 255)
	private Integer configId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "item_display_count", nullable = true, length = 255)
	private Integer itemDisplayCount;

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

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "max_attch_sz", nullable = true, length = 20)
	private String maxAttchSz;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 300)
	@Column(name = "file_format", nullable = true, length = 300)
	private String fileFormat;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "max_attch_num", nullable = true, length = 255)
	private Short maxAttchNum;

	@ManyToOne
	@JoinColumn(name = "comm_type_id", referencedColumnName = "comm_type_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TCommType tCommType;

	/**
	 *
	 * @generated
	 */
	public TCommConfig() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setConfigId(final Integer configId) {
		this.configId = configId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getConfigId() {
		return this.configId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setItemDisplayCount(final Integer itemDisplayCount) {
		this.itemDisplayCount = itemDisplayCount;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getItemDisplayCount() {
		return this.itemDisplayCount;
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

	public void setUpdateDt(final Date updateDt) {
		if (updateDt != null) {
			this.updateDt = (Date) updateDt.clone();
		} else {
			this.updateDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getUpdateDt() {
		if (this.updateDt != null) {
			return (Date) this.updateDt.clone();
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

	public void setMaxAttchSz(final String maxAttchSz) {
		this.maxAttchSz = maxAttchSz;
	}

	/**
	 * 
	 * @generated
	 */
	public String getMaxAttchSz() {
		return this.maxAttchSz;
	}

	/**
	 * 
	 * @generated
	 */

	public void setFileFormat(final String fileFormat) {
		this.fileFormat = fileFormat;
	}

	/**
	 * 
	 * @generated
	 */
	public String getFileFormat() {
		return this.fileFormat;
	}

	/**
	 * 
	 * @generated
	 */

	public void setMaxAttchNum(final Short maxAttchNum) {
		this.maxAttchNum = maxAttchNum;
	}

	/**
	 * 
	 * @generated
	 */
	public Short getMaxAttchNum() {
		return this.maxAttchNum;
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
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCommConfig that) {
		setConfigId(that.getConfigId());
		setItemDisplayCount(that.getItemDisplayCount());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setMaxAttchSz(that.getMaxAttchSz());
		setFileFormat(that.getFileFormat());
		setMaxAttchNum(that.getMaxAttchNum());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((configId == null) ? 0 : configId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("configId=[").append(configId).append("] ");
		buffer.append("itemDisplayCount=[").append(itemDisplayCount).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("maxAttchSz=[").append(maxAttchSz).append("] ");
		buffer.append("fileFormat=[").append(fileFormat).append("] ");
		buffer.append("maxAttchNum=[").append(maxAttchNum).append("] ");

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
		final TCommConfig other = (TCommConfig) obj;
		if (configId == null) {
			if (other.configId != null) {
				return false;
			}
		} else if (!configId.equals(other.configId)) {
			return false;
		}
		return true;
	}
}
