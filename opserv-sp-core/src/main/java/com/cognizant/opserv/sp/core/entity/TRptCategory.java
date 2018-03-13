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

import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TRptCategory 
 * The corresponding mapping table is t_rpt_category 
 */
/*@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable(value = true)*/
@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTRptCategorys", query = "select myTRptCategory from TRptCategory myTRptCategory"),
		@NamedQuery(name = "CountTRptCategorys", query = "Select Count(c) from TRptCategory c"),
		@NamedQuery(name = "findTRptCategoryByCategoryIds", query = "select myTRptCategory from TRptCategory myTRptCategory where myTRptCategory.tRptCategoryId.rptCategoryId =?1"),
		@NamedQuery(name = "findCategoryName", query = "Select c.rptCategoryName,c.rptCategoryDesc from TRptCategory c where c.tRptCategoryId.rptCategoryId =?1 and c.tRptCategoryId.localeId =?2"),
		@NamedQuery(name = "findAllCategories", query = "Select c.tRptCategoryId.rptCategoryId,c.rptCategoryName,c.rptCategoryDesc from TRptCategory c where c.activeFlag ='Y' and c.tRptCategoryId.localeId =?1 and c.tenantId=?2",
				hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")
	

		}),
		@NamedQuery(name = "findCategoryNameByCategoryId", query = "select myTRptCategory.rptCategoryName from TRptCategory myTRptCategory where myTRptCategory.tRptCategoryId.rptCategoryId =?1"),
})
@Table(name = "t_rpt_category", uniqueConstraints = @UniqueConstraint(columnNames = { "rpt_category_id" }))
public class TRptCategory implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "rpt_category_id", nullable = false, length = 255)
//	private Integer rptCategoryId;
	
	@EmbeddedId
	@Valid
	private TRptCategoryId tRptCategoryId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 50)
	@Column(name = "rpt_category_name", nullable = false, length = 50)
	private String rptCategoryName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 75)
	@Column(name = "rpt_category_desc", nullable = true, length = 75)
	private String rptCategoryDesc;

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
	@Column(name = "created_by", nullable = true, length = 255)
	private Integer createdBy;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "create_dt", nullable = true, length = 19)
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
	private Date updateDate;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

	/*@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tRptCategory")
	@NotAudited
	private Set<TRptConfig> tRptConfigss;*/

	/**
	 *
	 * @generated
	 */
	public TRptCategory() {
	}

	/**
	 * 
	 * @generated
	 */

	/*public void setRptCategoryId(final Integer rptCategoryId) {
		this.rptCategoryId = rptCategoryId;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public Integer getRptCategoryId() {
		return this.rptCategoryId;
	}*/

	public TRptCategoryId getTRptCategoryId() {
		return tRptCategoryId;
	}

	public void setTRptCategoryId(TRptCategoryId tRptCategoryId) {
		this.tRptCategoryId = tRptCategoryId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setRptCategoryName(final String rptCategoryName) {
		this.rptCategoryName = rptCategoryName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getRptCategoryName() {
		return this.rptCategoryName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setRptCategoryDesc(final String rptCategoryDesc) {
		this.rptCategoryDesc = rptCategoryDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getRptCategoryDesc() {
		return this.rptCategoryDesc;
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

	public void setUpdateDate(final Date updateDate) {
		if (updateDate != null) {
			this.updateDate = (Date) updateDate.clone();
		} else {
			this.updateDate = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getUpdateDate() {
		if (this.updateDate != null) {
			return (Date) this.updateDate.clone();
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
	/*public Set<TRptConfig> getTRptConfigss() {
		return this.tRptConfigss;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public void setTRptConfigss(final Set<TRptConfig> tRptConfigss) {
		this.tRptConfigss = tRptConfigss;
	}*/

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TRptCategory that) {
		//setRptCategoryId(that.getRptCategoryId());
		setTRptCategoryId(that.getTRptCategoryId());
		setRptCategoryName(that.getRptCategoryName());
		setRptCategoryDesc(that.getRptCategoryDesc());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDate(that.getUpdateDate());
		setTenantId(that.getTenantId());
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("rptCategoryId=[").append(tRptCategoryId).append("] ");
		buffer.append("rptCategoryName=[").append(rptCategoryName).append("] ");
		buffer.append("rptCategoryDesc=[").append(rptCategoryDesc).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDate=[").append(updateDate).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");

		return buffer.toString();
	}
	
}
