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
 * JPA class representing TCustCategory 
 * The corresponding mapping table is t_cust_category 
 */
/*@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable(value = true)*/
@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTCustCategorys", query = "select myTCustCategory from TCustCategory myTCustCategory"),
		@NamedQuery(name = "CountTCustCategorys", query = "Select Count(c) from TCustCategory c"),
		@NamedQuery(name = "FindAllTCustCategories", query = "Select myTCustCategory.tCustCategoryId.custCategoryId,myTCustCategory.categoryName from TCustCategory myTCustCategory where myTCustCategory.tenantId = ?1 and myTCustCategory.tCustCategoryId.localeId = ?2",
				hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")		
		}),
		@NamedQuery(name = "FindTCustCategorys", query = "Select myTCustCategory from TCustCategory myTCustCategory where myTCustCategory.tenantId = ?1 and myTCustCategory.tCustCategoryId.localeId = ?2",
			hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")		
		}),
		@NamedQuery(name = "FindAllTCustCatNameAndId", query = "Select c.tCustCategoryId.custCategoryId,c.categoryName,c.categoryDesc from TCustCategory c where c.tenantId = ?1 and c.tCustCategoryId.localeId = ?2",
		hints = {
			@QueryHint(name = "org.hibernate.cacheable", value = "true"),
			@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")		
		}),
		})
@Table(name = "t_cust_category", uniqueConstraints = @UniqueConstraint(columnNames = { "category_id" }))
public class TCustCategory implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TCustCategoryId tCustCategoryId;
	/**
	 * 
	 * @generated
	 */
	@Length(max = 75)
	@Column(name = "category_name", nullable = true, length = 75)
	private String categoryName;

	public TCustCategoryId gettCustCategoryId() {
		return tCustCategoryId;
	}

	public void settCustCategoryId(TCustCategoryId tCustCategoryId) {
		this.tCustCategoryId = tCustCategoryId;
	}

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "category_desc", nullable = true, length = 100)
	private String categoryDesc;

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

	/*@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tCustCategory")
	private Set<TCust> tCustss;
*/
	/**
	 *
	 * @generated
	 */
	/*public TCustCategory() {
	}*/

	/**
	 * 
	 * @generated
	 */

	/*public void setCategoryId(final Integer categoryId) {
		this.categoryId = categoryId;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public Integer getCategoryId() {
		return this.categoryId;
	}
*/
	/**
	 * 
	 * @generated
	 */

	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getCategoryName() {
		return this.categoryName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCategoryDesc(final String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getCategoryDesc() {
		return this.categoryDesc;
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
		if (this.createDt == null) {
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
			this.updateDt = null;
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
	/*public Set<TCust> getTCustss() {
		return this.tCustss;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public void setTCustss(final Set<TCust> tCustss) {
		this.tCustss = tCustss;
	}*/

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCustCategory that) {
		//setCategoryId(that.getCategoryId());
		setCategoryName(that.getCategoryName());
		setCategoryDesc(that.getCategoryDesc());
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
		result = prime * result + ((gettCustCategoryId() == null) ? 0 : gettCustCategoryId().hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("categoryId=[").append(gettCustCategoryId()).append("] ");
		buffer.append("categoryName=[").append(categoryName).append("] ");
		buffer.append("categoryDesc=[").append(categoryDesc).append("] ");
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
		final TCustCategory other = (TCustCategory) obj;
		if (gettCustCategoryId() == null) {
			if (other.gettCustCategoryId() != null) {
				return false;
			}
		} else if (!gettCustCategoryId().equals(other.gettCustCategoryId())) {
			return false;
		}
		return true;
	}
}
