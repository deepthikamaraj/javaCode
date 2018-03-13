package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * JPA class representing TOrgSalesHier 
 * The corresponding mapping table is t_org_sales_hier 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTOrgSalesHiers", query = "select myTOrgSalesHier from TOrgSalesHier myTOrgSalesHier"),
		@NamedQuery(name = "CountTOrgSalesHiers", query = "Select Count(c) from TOrgSalesHier c"),
		@NamedQuery(name = "FindTOrgSalesHierByTOrg", query = "select myTOrgSalesHier from TOrgSalesHier myTOrgSalesHier where myTOrgSalesHier.tOrg = ?1 "),
		@NamedQuery(name = "CountTOrgSalesHiersByTOrg", query = "select Count(myTOrgSalesHier) from TOrgSalesHier myTOrgSalesHier where myTOrgSalesHier.tOrg = ?1 "),
		@NamedQuery(name = "FindTOrgSalesHierByTOrgSalesHier", query = "select myTOrgSalesHier from TOrgSalesHier myTOrgSalesHier where myTOrgSalesHier.tOrgSalesHier = ?1 "),
		@NamedQuery(name = "DeleteTOrgSalesHierTenant", query = "delete from TOrgSalesHier myTOrgSalesHier where myTOrgSalesHier.tenantId = ?1 "),
		@NamedQuery(name = "CountTOrgSalesHiersByTOrgSalesHier", query = "select Count(myTOrgSalesHier) from TOrgSalesHier myTOrgSalesHier where myTOrgSalesHier.tOrgSalesHier = ?1 "),
		@NamedQuery(name = "FindActiveTOrgSalesHiers", query = "select myTOrgSalesHier from TOrgSalesHier myTOrgSalesHier where myTOrgSalesHier.tenantId=?1 AND myTOrgSalesHier.effStartDt<=?2 AND myTOrgSalesHier.effEndDt IS NULL ORDER BY myTOrgSalesHier.tOrgSalesHier ASC "),
		@NamedQuery(name = "FindTOrgSalesHierByTSalesHierList", query = "select myTOrgSalesHier from TOrgSalesHier myTOrgSalesHier where myTOrgSalesHier.tSalesHierList = ?1 "),
		@NamedQuery(name = "CountTOrgSalesHiersByTSalesHierList", query = "select Count(myTOrgSalesHier) from TOrgSalesHier myTOrgSalesHier where myTOrgSalesHier.tSalesHierList = ?1 ") ,
		@NamedQuery(name = "fetchSeqNumber", query = "select myTOrgSalesHier from TOrgSalesHier myTOrgSalesHier where myTOrgSalesHier.tenantId = ?1 ORDER BY orgSalesHierId DESC "),
		@NamedQuery(name = "FindTorgSalesHierByPrnOrg", query = "select myTOrgSalesHier from TOrgSalesHier myTOrgSalesHier where myTOrgSalesHier.tOrgSalesHier.orgSalesHierId= ?1 and myTOrgSalesHier.tenantId = ?2 and myTOrgSalesHier.activeFlag='Y' ")
})
@Table(name = "t_org_sales_hier", uniqueConstraints = @UniqueConstraint(columnNames = { "org_sales_hier_id" }))
public class TOrgSalesHier implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "org_sales_hier_id", nullable = false, length = 255)
	private Long orgSalesHierId;

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
	@NotNull
	@Column(name = "active_flag", nullable = false, length = 1)
	private Character activeFlag;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "eff_start_dt", nullable = false, length = 10)
	private Date effStartDt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "eff_end_dt", nullable = true, length = 10)
	private Date effEndDt;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "org_id", referencedColumnName = "org_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TOrg tOrg;

	@ManyToOne
	@JoinColumn(name = "prn_org_sales_hier_id", referencedColumnName = "org_sales_hier_id", nullable = true, insertable = true, updatable = true)
	@Valid
	private TOrgSalesHier tOrgSalesHier;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "org_sales_hier_id", referencedColumnName = "sales_hier_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TSalesHierList tSalesHierList;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tOrgSalesHier")
	private Set<TOrgSalesHier> tOrgSalesHierss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tOrgSalesHier")
	private Set<TAlgmntSalesHier> tAlgmntSalesHierss;

	/**
	 *
	 * @generated
	 */
	public TOrgSalesHier() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setOrgSalesHierId(final Long orgSalesHierId) {
		this.orgSalesHierId = orgSalesHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getOrgSalesHierId() {
		return this.orgSalesHierId;
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
	public TOrg getTOrg() {
		return this.tOrg;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTOrg(final TOrg tOrg) {
		this.tOrg = tOrg;

	}

	/**
	 * 
	 * @generated
	 */
	public TOrgSalesHier getTOrgSalesHier() {
		return this.tOrgSalesHier;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTOrgSalesHier(final TOrgSalesHier tOrgSalesHier) {
		this.tOrgSalesHier = tOrgSalesHier;

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
	public Set<TAlgmntSalesHier> getTAlgmntSalesHierss() {
		return this.tAlgmntSalesHierss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAlgmntSalesHierss(final Set<TAlgmntSalesHier> tAlgmntSalesHierss) {
		this.tAlgmntSalesHierss = tAlgmntSalesHierss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TOrgSalesHier that) {
		setOrgSalesHierId(that.getOrgSalesHierId());
		setHierName(that.getHierName());
		setHierDesc(that.getHierDesc());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setActiveFlag(that.getActiveFlag());
		setEffStartDt(that.getEffStartDt());
		setEffEndDt(that.getEffEndDt());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((orgSalesHierId == null) ? 0 : orgSalesHierId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("orgSalesHierId=[").append(orgSalesHierId).append("] ");
		buffer.append("hierName=[").append(hierName).append("] ");
		buffer.append("hierDesc=[").append(hierDesc).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("effStartDt=[").append(effStartDt).append("] ");
		buffer.append("effEndDt=[").append(effEndDt).append("] ");

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
		final TOrgSalesHier other = (TOrgSalesHier) obj;
		if (orgSalesHierId == null) {
			if (other.orgSalesHierId != null) {
				return false;
			}
		} else if (!orgSalesHierId.equals(other.orgSalesHierId)) {
			return false;
		}
		return true;
	}
}
