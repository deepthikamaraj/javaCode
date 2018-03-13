package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TAddrType 
 * The corresponding mapping table is t_addr_type 
 */
/*@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable(value = true)*/
@Entity
@NamedQueries({ @NamedQuery(name = "FindAllTAddrTypes", query = "select myTAddrType from TAddrType myTAddrType"),
		@NamedQuery(name = "CountTAddrTypes", query = "Select Count(c) from TAddrType c") })
@Table(name = "t_addr_type", uniqueConstraints = @UniqueConstraint(columnNames = { "addr_type_id" }))
public class TAddrType implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TAddrTypeId tAddrTypeId;
	
	/*@Id
	@GenericGenerator(name="helio",strategy="hilo")
	@GeneratedValue(generator="helio")
	@Column(name = "addr_type_id", nullable = false, length = 255)
	private Integer addrTypeId;*/

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "addr_type_desc", nullable = true, length = 50)
	private String addrTypeDesc;

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
	
	/*@NotEmpty
	@Column(name="locale", nullable=false, length=20)
	private String locale;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAddrType")
	private Set<TCustAddr> tCustAddrss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAddrType")
	private Set<TCustContact> tCustContactss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAddrType")
	private Set<TCustContact> tCustContacts_1s;
*/
	/**
	 *
	 * @generated
	 */
	public TAddrType() {
	}

	/**
	 * 
	 * @generated
	 */

	/*public void setAddrTypeId(final Integer addrTypeId) {
		this.addrTypeId = addrTypeId;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public Integer getAddrTypeId() {
		return this.addrTypeId;
	}*/

	/**
	 * 
	 * @generated
	 */

	public void setAddrTypeDesc(final String addrTypeDesc) {
		this.addrTypeDesc = addrTypeDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAddrTypeDesc() {
		return this.addrTypeDesc;
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
	/*public Set<TCustAddr> getTCustAddrss() {
		return this.tCustAddrss;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTCustAddrss(final Set<TCustAddr> tCustAddrss) {
		this.tCustAddrss = tCustAddrss;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public Set<TCustContact> getTCustContactss() {
		return this.tCustContactss;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTCustContactss(final Set<TCustContact> tCustContactss) {
		this.tCustContactss = tCustContactss;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public Set<TCustContact> getTCustContacts_1s() {
		return this.tCustContacts_1s;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public void setTCustContacts_1s(final Set<TCustContact> tCustContacts_1s) {
		this.tCustContacts_1s = tCustContacts_1s;
	}*/

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TAddrType that) {
//		setAddrTypeId(that.getAddrTypeId());
		setAddrTypeDesc(that.getAddrTypeDesc());
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
		result = prime * result + ((gettAddrTypeId() == null) ? 0 : gettAddrTypeId().hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("addrTypeId=[").append(gettAddrTypeId()).append("] ");
		buffer.append("addrTypeDesc=[").append(addrTypeDesc).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
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
		final TAddrType other = (TAddrType) obj;
		if (gettAddrTypeId() == null) {
			if (other.gettAddrTypeId() != null) {
				return false;
			}
		} else if (!gettAddrTypeId().equals(other.gettAddrTypeId())) {
			return false;
		}
		return true;
	}

	public TAddrTypeId gettAddrTypeId() {
		return tAddrTypeId;
	}

	public void settAddrTypeId(TAddrTypeId tAddrTypeId) {
		this.tAddrTypeId = tAddrTypeId;
	}

	/*public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}*/
	
}
