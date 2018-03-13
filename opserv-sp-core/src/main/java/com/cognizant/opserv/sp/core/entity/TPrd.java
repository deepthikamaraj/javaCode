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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TPrd 
 * The corresponding mapping table is t_prd 
 */

@Entity
@Audited
@NamedQueries({ @NamedQuery(name = "FindAllTPrds", query = "select myTPrd from TPrd myTPrd"),
//@NamedQuery(name = "FindRecentlyAddedProductToSP", query = "Select myTPrd.prdId,myTPrd.prdCd,myTPrd.prdName from TPrdAlgmnt myTPrdAlgmnt, TPrd myTPrd where myTPrd.prdId = myTPrdAlgmnt.tPrd.prdId and myTPrdAlgmnt.tenantId = ?1 AND myTPrdAlgmnt.algmntId = ?2 AND myTPrdAlgmnt.bussUnitId = ?3 AND myTPrdAlgmnt.salesTeamId = ?4 AND myTPrdAlgmnt.activeFlag = ?5 AND myTPrdAlgmnt.salesPosId = ?6 AND myTPrdAlgmnt.salesHierId = ?7 AND (myTPrdAlgmnt.effEndDt >= ?8 or myTPrdAlgmnt.effEndDt IS NULL) ORDER By myTPrdAlgmnt.effStartDt DESC"),
	@NamedQuery(name = "FindRecentlyAddedProductToSP", query = "Select myTPrd.prdId,myTPrd.prdCd,myTPrd.prdName from TPrdAlgmnt myTPrdAlgmnt inner join  myTPrdAlgmnt.tPrd myTPrd where myTPrd.prdId = myTPrdAlgmnt.tPrd.prdId and myTPrdAlgmnt.tenantId = ?1 AND myTPrdAlgmnt.algmntId = ?2 AND myTPrdAlgmnt.bussUnitId = ?3 AND myTPrdAlgmnt.salesTeamId = ?4 AND myTPrdAlgmnt.activeFlag = ?5 AND myTPrdAlgmnt.salesPosId = ?6 AND myTPrdAlgmnt.salesHierId = ?7 AND (myTPrdAlgmnt.effEndDt >= ?8 or myTPrdAlgmnt.effEndDt IS NULL) ORDER By myTPrdAlgmnt.effStartDt DESC"),
	@NamedQuery(name = "CountTPrds", query = "Select Count(c) from TPrd c"),
	@NamedQuery(name = "FindTPrdsByPrdIds", query = "Select myTPrd from TPrd myTPrd where myTPrd.activeFlag = 'Y' and  myTPrd.prdId IN ?1 and  myTPrd.tenantId=?2"),
@NamedQuery(name = "getProductsForNonCallDirectionBasedCallPlan", query = " select myTPrd.prdId, myTPrd.prdName,myTPrdAlgmnt.effStartDt,myTPrdAlgmnt.effEndDt  "+
        " from TPrd myTPrd, TPrdAlgmnt myTPrdAlgmnt "+
        " where myTPrdAlgmnt.algmntId = ?1 and myTPrdAlgmnt.bussUnitId = ?2"+
        " and myTPrdAlgmnt.salesTeamId = ?3 and myTPrdAlgmnt.salesHierId = ?4 "+
        " and myTPrdAlgmnt.salesPosId = ?5 and myTPrd.activeFlag = 'Y' and myTPrdAlgmnt.activeFlag = myTPrd.activeFlag "+
        " and myTPrdAlgmnt.tPrd.prdId = myTPrd.prdId and myTPrdAlgmnt.tenantId = ?6")
})
@Table(name = "t_prd", uniqueConstraints = @UniqueConstraint(columnNames = { "prd_id", "prd_cd", "prd_name" }))
public class TPrd implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "prd_id", nullable = false, length = 255)
	private Long prdId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 50)
	@Column(name = "prd_cd", nullable = false, length = 10)
	private String prdCd;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 500)
	@Column(name = "prd_desc", nullable = true, length = 500)
	private String prdDesc;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 200)
	@Column(name = "prd_name", nullable = false, length = 200)
	private String prdName;

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
	@Temporal(TemporalType.DATE)
	@Column(name = "eff_start_dt", nullable = true, length = 10)
	private Date effStartDt;

	/**
	 * 
	 * @generated
	 */
	@Temporal(TemporalType.DATE)
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
	
	
	@Column(name = "ext_attr", nullable = true, length = 255)
	private byte[] extAttr;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tPrd", fetch=FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@NotAudited
	private Set<TPrdHier> tPrdHierss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tPrd", fetch=FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@NotAudited
	private Set<TPrdAlgmnt> tPrdAlgmntss;
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tPrd")
	@Fetch(FetchMode.SUBSELECT)
	@NotAudited
	private Set<TPrdAttr> tPrdAttrss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tPrd", fetch=FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@NotAudited
	private Set<TPrdSalesTeam> tPrdSalesTeams;
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tPrd", fetch=FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@NotAudited
	private Set<TCustPrdSalesTeam> tCustPrdSalesTeams;
	
	/**
	 *
	 * @generated
	 */
	public TPrd() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setPrdId(final Long prdId) {
		this.prdId = prdId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getPrdId() {
		return this.prdId;
	}
	/**
	 * 
	 * @generated
	 */

	public void setPrdCd(final String prdCd) {
		this.prdCd = prdCd;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPrdCd() {
		return this.prdCd;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPrdDesc(final String prdDesc) {
		this.prdDesc = prdDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPrdDesc() {
		return this.prdDesc;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPrdName(final String prdName) {
		this.prdName = prdName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPrdName() {
		return this.prdName;
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
	public Set<TPrdHier> getTPrdHierss() {
		return this.tPrdHierss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPrdHierss(final Set<TPrdHier> tPrdHierss) {
		this.tPrdHierss = tPrdHierss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TPrdAlgmnt> getTPrdAlgmntss() {
		return this.tPrdAlgmntss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPrdAlgmntss(final Set<TPrdAlgmnt> tPrdAlgmntss) {
		this.tPrdAlgmntss = tPrdAlgmntss;
	}
	
	/**
	 * 
	 * @generated
	 */
	public Set<TPrdAttr> getTPrdAttrss() {
		return this.tPrdAttrss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPrdAttrss(final Set<TPrdAttr> tPrdAttrss) {
		this.tPrdAttrss = tPrdAttrss;
	}
	
	
	public byte[] getExtAttr() {
		return extAttr;
	}

	public void setExtAttr(byte[] extAttr) {
		this.extAttr = extAttr;
	}
	
	

	public Set<TPrdSalesTeam> getTPrdSalesTeams() {
		return tPrdSalesTeams;
	}

	public void setTPrdSalesTeams(Set<TPrdSalesTeam> tPrdSalesTeams) {
		this.tPrdSalesTeams = tPrdSalesTeams;
	}

	public Set<TCustPrdSalesTeam> getTCustPrdSalesTeams() {
		return tCustPrdSalesTeams;
	}

	public void setTCustPrdSalesTeams(Set<TCustPrdSalesTeam> tCustPrdSalesTeams) {
		this.tCustPrdSalesTeams = tCustPrdSalesTeams;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TPrd that) {
		setPrdId(that.getPrdId());
		setPrdCd(that.getPrdCd());
		setPrdDesc(that.getPrdDesc());
		setPrdName(that.getPrdName());
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
		result = prime * result + ((prdId == null) ? 0 : prdId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("prdId=[").append(prdId).append("] ");
		buffer.append("prdCd=[").append(prdCd).append("] ");
		buffer.append("prdDesc=[").append(prdDesc).append("] ");
		buffer.append("prdName=[").append(prdName).append("] ");
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
		final TPrd other = (TPrd) obj;
		if (prdId == null) {
			if (other.prdId != null) {
				return false;
			}
		} else if (!prdId.equals(other.prdId)) {
			return false;
		}
		return true;
	}
}