package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TCustType 
 * The corresponding mapping table is t_cust_type 
 */
/*@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable(value = true)*/
@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTCustTypes", query = "select myTCustType from TCustType myTCustType"),
		
		@NamedQuery(name = "FindAllTCustTypesTenantId", query = "select myTCustType from TCustType myTCustType where myTCustType.tenantId = ?1" +
				" AND myTCustType.tCustTypeId.localeId = ?2 and myTCustType.activeFlag = 'Y'", 
		hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")

		}),

		@NamedQuery(name = "CountTCustTypes", query = "Select Count(c) from TCustType c"),
		@NamedQuery(name = "FindTCustTypes", query = "select myTCustType.typeName from TCustType myTCustType where myTCustType.tenantId = ?1"),
		@NamedQuery(name = "FindAllTCustTypesByTenantAndLocale", query = "select myTCustType from TCustType myTCustType where myTCustType.tenantId = ?1 and myTCustType.tCustTypeId.localeId = ?2"),
		@NamedQuery(name = "FindAllTCustTypesByIds", query = "select myTCustType.tCustTypeId.custTypeId,myTCustType.typeName from TCustType myTCustType where myTCustType.tenantId = ?1 and myTCustType.tCustTypeId.localeId = ?2",
		hints = {
			@QueryHint(name = "org.hibernate.cacheable", value = "true"),
			@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")

	}),
		@NamedQuery(name = "FindTCustTypeNameByCustTypeName", query = "select myTCustType from TCustType myTCustType where myTCustType.typeName = ?1 and  myTCustType.tenantId = ?2", hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")
		}),
		@NamedQuery(name = "FindAllTCustTypeIdAndName", query = "select c.tCustTypeId.custTypeId,c.typeDesc from TCustType c where c.tenantId = ?1" +
				" AND c.tCustTypeId.localeId = ?2 and c.activeFlag = 'Y'", 
		hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")

		}),
		@NamedQuery(name = "GetAllCustomerTypeName", query = "select myTCustType.typeName from TCustType myTCustType where myTCustType.tenantId = ?1" +
				" AND myTCustType.tCustTypeId.localeId = ?2 and myTCustType.activeFlag = 'Y' order by myTCustType.typeName", 
		hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")

		}),
	})
@Table(name = "t_cust_type", uniqueConstraints = @UniqueConstraint(columnNames = { "cust_type_id" }))
public class TCustType implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TCustTypeId tCustTypeId;
	/*@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cust_type_id", nullable = false, length = 255)
	private Integer custTypeId;*/

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "type_name", nullable = true, length = 50)
	private String typeName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 75)
	@Column(name = "type_desc", nullable = true, length = 75)
	private String typeDesc;

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
	@Column(name = "icon_cd", nullable = true, length = 255)
	private String iconCd;
	
	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "category_id", nullable = false, length = 255)
	private Integer categoryId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "prs_flag", nullable = true, length = 1)
	private Character prsFlag;


/*	@NotEmpty
	@Column(name="locale_id", nullable=false, length=20)
	private String locale;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tCustType")
	private Set<TCustAlgmntAff> tCustAlgmntAffss;*/

	/**
	 * 
	 * @generated
	 */
	/**
	 * 
	 */
	@Length(max = 30)
	@Column(name = "color_cd", nullable = true, length = 30)
	private String colorCd;
	
	/**
	 *
	 * @generated
	 */
	/*public TCustType() {
	}
*/
	/**
	 * 
	 * @generated
	 */

	/*public void setCustTypeId(final Integer custTypeId) {
		this.custTypeId = custTypeId;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public Integer getCustTypeId() {
		return this.custTypeId;
	}
*/
	/**
	 * 
	 * @generated
	 */

	public void setTypeName(final String typeName) {
		this.typeName = typeName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getTypeName() {
		return this.typeName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setTypeDesc(final String typeDesc) {
		this.typeDesc = typeDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getTypeDesc() {
		return this.typeDesc;
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
	public String getColorCd() {
		return colorCd;
	}

	/**
	 * 
	 * @generated
	 */
	public void setColorCd(String colorCd) {
		this.colorCd = colorCd;
	}
	
	/**
	 * 
	 * @generated
	 */

	public void setCategoryId(final Integer categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCategoryId() {
		return this.categoryId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPrsFlag(final Character prsFlag) {
		this.prsFlag = prsFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getPrsFlag() {
		return this.prsFlag;
	}

	/**
	 * 
	 * @generated
	 */
	/*public Set<TCustAlgmntAff> getTCustAlgmntAffss() {
		return this.tCustAlgmntAffss;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTCustAlgmntAffss(final Set<TCustAlgmntAff> tCustAlgmntAffss) {
		this.tCustAlgmntAffss = tCustAlgmntAffss;
	}*/

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCustType that) {
		//setCustTypeId(that.getCustTypeId());
		setTypeName(that.getTypeName());
		setTypeDesc(that.getTypeDesc());
		setActiveFlag(that.getActiveFlag());
		setColorCd(that.getColorCd());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setCategoryId(that.getCategoryId());
		setPrsFlag(that.getPrsFlag());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((gettCustTypeId() == null) ? 0 : gettCustTypeId().hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("custAffTypeId=[").append(gettCustTypeId()).append("] ");
		buffer.append("typeName=[").append(typeName).append("] ");
		buffer.append("typeDesc=[").append(typeDesc).append("] ");
		buffer.append("colorCd=[").append(colorCd).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("categoryId=[").append(categoryId).append("] ");
		buffer.append("prsFlag=[").append(prsFlag).append("] ");

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
		final TCustType other = (TCustType) obj;
		if (gettCustTypeId() == null) {
			if (other.gettCustTypeId() != null) {
				return false;
			}
		} else if (!gettCustTypeId().equals(other.gettCustTypeId())) {
			return false;
		}
		return true;
	}

/*	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}*/

	public TCustTypeId gettCustTypeId() {
		return tCustTypeId;
	}

	public void settCustTypeId(TCustTypeId tCustTypeId) {
		this.tCustTypeId = tCustTypeId;
	}

	public String getIconCd() {
		return iconCd;
	}

	public void setIconCd(String iconCd) {
		this.iconCd = iconCd;
	}

	
	
	
}
