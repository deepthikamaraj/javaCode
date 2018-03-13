package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TPrdPrtType 
 * The corresponding mapping table is t_prd_prt_type 
 */

@Entity
/*@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable(value = true)*/
@NamedQueries({
		@NamedQuery(name = "FindAllTPrdPrtTypes", query = "select myTPrdPrtType from TPrdPrtType myTPrdPrtType"),
		@NamedQuery(name = "FindTPrdPrtTypesDesc", query = "select p.tPrdPrtTypeId.prdPrtTypeId, p.prdPrtTypeDesc from TPrdPrtType p where p.tPrdPrtTypeId.prdPrtTypeId<=?1 and p.tenantId=?2 order by p.tPrdPrtTypeId.prdPrtTypeId"),
		//@NamedQuery(name = "FindTPrdPrtTypesDesc", query = "select p.prdPrtTypeId, p.prdPrtTypeDesc from TPrdPrtType p where p.tPrdPrtTypeId.prdPrtTypeId<=?1 and p.tenantId=?2 order by p.tPrdPrtTypeId.prdPrtTypeId"),
		@NamedQuery(name = "CountTPrdPrtTypes", query = "Select Count(c) from TPrdPrtType c"),
		@NamedQuery(name = "getTopColNames", query="Select tPrdPrtType.prdPrtTypeDesc from TPrdPrtType tPrdPrtType where tPrdPrtType.tPrdPrtTypeId.prdPrtTypeId <= ?1")
		 })
@Table(name = "t_prd_prt_type")
//, uniqueConstraints = @UniqueConstraint(columnNames = { "prd_prt_type_id" }))
public class TPrdPrtType implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	/*@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "prd_prt_type_id", nullable = false, length = 255)
	private Integer prdPrtTypeId;
*/
	
	@EmbeddedId
	@Valid
	private TPrdPrtTypeId tPrdPrtTypeId;
	
	public TPrdPrtTypeId gettPrdPrtTypeId() {
		return tPrdPrtTypeId;
	}

	public void settPrdPrtTypeId(TPrdPrtTypeId tPrdPrtTypeId) {
		this.tPrdPrtTypeId = tPrdPrtTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 20)
	@Column(name = "prd_prt_type_desc", nullable = false, length = 20)
	private String prdPrtTypeDesc;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "active_flag", nullable = true, length = 1)
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
	/*@Length(max = 20)
	@Column(name = "locale_id", nullable = true, length = 20)
	private String localeId;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tPrdPrtType")
	private Set<TCallDirPrdWt> tCallDirPrdWtss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tPrdPrtType")
	private Set<TCallDirPrd> tCallDirPrdss;*/

	/**
	 *
	 * @generated
	 */
	public TPrdPrtType() {
	}

	/**
	 * 
	 * @generated
	 */

	/*public void setPrdPrtTypeId(final Integer prdPrtTypeId) {
		this.prdPrtTypeId = prdPrtTypeId;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public Integer getPrdPrtTypeId() {
		return this.prdPrtTypeId;
	}
*/
	/**
	 * 
	 * @generated
	 */

	public void setPrdPrtTypeDesc(final String prdPrtTypeDesc) {
		this.prdPrtTypeDesc = prdPrtTypeDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPrdPrtTypeDesc() {
		return this.prdPrtTypeDesc;
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

	/*public void setLocaleId(final String localeId) {
		this.localeId = localeId;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public String getLocaleId() {
		return this.localeId;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public Set<TCallDirPrdWt> getTCallDirPrdWtss() {
		return this.tCallDirPrdWtss;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTCallDirPrdWtss(final Set<TCallDirPrdWt> tCallDirPrdWtss) {
		this.tCallDirPrdWtss = tCallDirPrdWtss;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public Set<TCallDirPrd> getTCallDirPrdss() {
		return this.tCallDirPrdss;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTCallDirPrdss(final Set<TCallDirPrd> tCallDirPrdss) {
		this.tCallDirPrdss = tCallDirPrdss;
	}*/

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TPrdPrtType that) {
		//setPrdPrtTypeId(that.getPrdPrtTypeId());
		setPrdPrtTypeDesc(that.getPrdPrtTypeDesc());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		//setLocaleId(that.getLocaleId());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((gettPrdPrtTypeId() == null) ? 0 : gettPrdPrtTypeId().hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("prdPrtTypeId=[").append(gettPrdPrtTypeId()).append("] ");
		buffer.append("prdPrtTypeDesc=[").append(prdPrtTypeDesc).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		//buffer.append("localeId=[").append(localeId).append("] ");

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
		final TPrdPrtType other = (TPrdPrtType) obj;
		if (gettPrdPrtTypeId() == null) {
			if (other.gettPrdPrtTypeId() != null) {
				return false;
			}
		} else if (!gettPrdPrtTypeId().equals(other.gettPrdPrtTypeId())) {
			return false;
		}
		return true;
	}
}
