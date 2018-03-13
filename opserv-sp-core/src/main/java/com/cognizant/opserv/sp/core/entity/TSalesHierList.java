package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TSalesHierList 
 * The corresponding mapping table is t_sales_hier_list 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTSalesHierLists", query = "select myTSalesHierList from TSalesHierList myTSalesHierList"),
		@NamedQuery(name = "CountTSalesHierLists", query = "Select Count(c) from TSalesHierList c"),
		@NamedQuery(name = "FindActiveTSalesHierList", query = "select myTSalesHierList from TSalesHierList myTSalesHierList where myTSalesHierList.tenantId=?1 AND myTSalesHierList.effStartDt<=?2 AND myTSalesHierList.effEndDt IS NULL ORDER BY myTSalesHierList.tSalesHierList"),
		@NamedQuery(name = "FindTSalesHierListByTSalesHierList", query = "select myTSalesHierList from TSalesHierList myTSalesHierList where myTSalesHierList.tSalesHierList = ?1 "),
		@NamedQuery(name = "CountTSalesHierListsByTSalesHierList", query = "select Count(myTSalesHierList) from TSalesHierList myTSalesHierList where myTSalesHierList.tSalesHierList = ?1 "),
		@NamedQuery(name = "fetchSequenceNumber", query = "select myTSalesHierList from TSalesHierList myTSalesHierList where myTSalesHierList.tenantId = ?1 ORDER BY salesHierId DESC "),
		
		})
@Table(name = "t_sales_hier_list", uniqueConstraints = @UniqueConstraint(columnNames = { "sales_hier_id" }))
public class TSalesHierList implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sales_hier_id", nullable = false, length = 255)
	private Long salesHierId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 100)
	@Column(name = "hier_name", nullable = false, length = 100)
	private String hierName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 150)
	@Column(name = "hier_desc", nullable = true, length = 150)
	private String hierDesc;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "def_hier_flag", nullable = true, length = 1)
	private Character defHierFlag;

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
	@Column(name = "eff_start_dt", nullable = true, length = 10)
	private Date effStartDt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "eff_end_dt", nullable = true, length = 10)
	private Date effEndDt;

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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "prn_sales_hier_id", referencedColumnName = "sales_hier_id", nullable = true, insertable = true, updatable = true)
	@Valid
	private TSalesHierList tSalesHierList;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tSalesHierList")
	private Set<TOrgSalesHier> tOrgSalesHierss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tSalesHierList")
	private Set<TSalesHierList> tSalesHierListss;

	/**
	 *
	 * @generated
	 */
	public TSalesHierList() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setSalesHierId(final Long salesHierId) {
		this.salesHierId = salesHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getSalesHierId() {
		return this.salesHierId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setHierName(final String hierName) {
		this.hierName = hierName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getHierName() {
		return this.hierName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setHierDesc(final String hierDesc) {
		this.hierDesc = hierDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getHierDesc() {
		return this.hierDesc;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDefHierFlag(final Character defHierFlag) {
		this.defHierFlag = defHierFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getDefHierFlag() {
		return this.defHierFlag;
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

	public void setEffStartDt(final Date effStartDt) {
		if (effStartDt != null) {
			this.effStartDt = (Date) effStartDt.clone();
		} else {
			this.effStartDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getEffStartDt() {
		if (this.effStartDt != null) {
			return (Date) this.effStartDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setEffEndDt(final Date effEndDt) {
		if (effEndDt != null) {
			this.effEndDt = (Date) effEndDt.clone();
		} else {
			this.effEndDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getEffEndDt() {
		if (this.effEndDt != null) {
			return (Date) this.effEndDt.clone();
		} else {
			return null;
		}
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
	public TSalesHierList getTSalesHierList() {
		return this.tSalesHierList;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTSalesHierList(final TSalesHierList tSalesHierList) {
		this.tSalesHierList = tSalesHierList;

	}

	/**
	 * 
	 * @generated
	 */
	public Set<TOrgSalesHier> getTOrgSalesHierss() {
		return this.tOrgSalesHierss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTOrgSalesHierss(final Set<TOrgSalesHier> tOrgSalesHierss) {
		this.tOrgSalesHierss = tOrgSalesHierss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TSalesHierList> getTSalesHierListss() {
		return this.tSalesHierListss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTSalesHierListss(final Set<TSalesHierList> tSalesHierListss) {
		this.tSalesHierListss = tSalesHierListss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TSalesHierList that) {
		setSalesHierId(that.getSalesHierId());
		setHierName(that.getHierName());
		setHierDesc(that.getHierDesc());
		setDefHierFlag(that.getDefHierFlag());
		setActiveFlag(that.getActiveFlag());
		setEffStartDt(that.getEffStartDt());
		setEffEndDt(that.getEffEndDt());
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
		result = prime * result + ((salesHierId == null) ? 0 : salesHierId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("salesHierId=[").append(salesHierId).append("] ");
		buffer.append("hierName=[").append(hierName).append("] ");
		buffer.append("hierDesc=[").append(hierDesc).append("] ");
		buffer.append("defHierFlag=[").append(defHierFlag).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("effStartDt=[").append(effStartDt).append("] ");
		buffer.append("effEndDt=[").append(effEndDt).append("] ");
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
		final TSalesHierList other = (TSalesHierList) obj;
		if (salesHierId == null) {
			if (other.salesHierId != null) {
				return false;
			}
		} else if (!salesHierId.equals(other.salesHierId)) {
			return false;
		}
		return true;
	}
}
