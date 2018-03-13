package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * JPA class representing TCustPrdAlgmnt The corresponding mapping table is
 * t_cust_prd_algmnt
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTCustPrdAlgmnts", query = "select myTCustPrdAlgmnt from TCustPrdAlgmnt myTCustPrdAlgmnt"),
		@NamedQuery(name = "CountTCustPrdAlgmnts", query = "Select Count(c) from TCustPrdAlgmnt c"),
		@NamedQuery(name = "FindTCustPrdAlgmntByTCustAlgmnt", query = "select myTCustPrdAlgmnt from TCustPrdAlgmnt myTCustPrdAlgmnt where myTCustPrdAlgmnt.tCustAlgmnt = ?1 "),
		@NamedQuery(name = "GetAllTCustPrdsAlgmntBySPIdCustId", query = "select myTCustPrdAlgmnt"+
	    	            " from TCustPrdAlgmnt myTCustPrdAlgmnt,TCustAlgmnt myTCustAlgmnt"+
	    	            " where myTCustPrdAlgmnt.tCustPrdAlgmntId.custAlgmntId=myTCustAlgmnt.custAlgmntId " +
	    	            "and myTCustAlgmnt.tCust.custId=?1 " +
	    	            "and myTCustAlgmnt.tCust.tenantId=?2 " +
	    	            "and myTCustAlgmnt.salesPosId = ?3 "+
	    	            "and myTCustAlgmnt.tenantId = ?4 "+
	    	            "and (myTCustAlgmnt.effEndDt IS NULL OR myTCustAlgmnt.effEndDt >= ?5)"+

	    	            "and myTCustAlgmnt.activeFlag ='Y'" +
	    	            "and myTCustAlgmnt.tCust.activeFlag='Y'" ),


	     @NamedQuery(name = "FindAllTCustPrdsAlgmntsBySalesPosId", query = "select myTCustPrdAlgmnt"+
	    	            " from TCustPrdAlgmnt myTCustPrdAlgmnt " +
	    	            "JOIN FETCH  myTCustPrdAlgmnt.tCustAlgmnt myTCustAlgmnt " +
	    	            "JOIN FETCH  myTCustPrdAlgmnt.tPrdAlgmnt  myTPrdAlgmnt " +
	    	            "JOIN FETCH  myTCustAlgmnt.tCust tCust " +
	    	            "JOIN FETCH  myTPrdAlgmnt.tPrd tPrd  "+
	    	            " where myTCustPrdAlgmnt.tCustPrdAlgmntId.custAlgmntId=myTCustAlgmnt.custAlgmntId and myTCustPrdAlgmnt.tCustPrdAlgmntId.prdAlgmntId=myTPrdAlgmnt.prdAlgmntId and myTCustAlgmnt.salesPosId  = ?1 "+
	    	            "and myTCustAlgmnt.tenantId = ?2 "+
	    	            "and myTPrdAlgmnt.tenantId = ?3 "+
	    	            "and (myTCustAlgmnt.effEndDt IS NULL OR myTCustAlgmnt.effEndDt >= ?4)"+
	    	            "and (myTPrdAlgmnt.effEndDt IS NULL OR myTPrdAlgmnt.effEndDt >= ?5)"+
	    	            "and myTCustAlgmnt.activeFlag ='Y'" ),




		@NamedQuery(name = "CountTCustPrdAlgmntsByTCustAlgmnt", query = "select Count(*) from TCustPrdAlgmnt myTCustPrdAlgmnt where myTCustPrdAlgmnt.tCustAlgmnt = ?1 "),
        @NamedQuery(name = "FindAllTCustPrdsAlgmntsBySalesPosIdWithoutDate", query = "select myTCustPrdAlgmnt"+
	    	            " from TCustPrdAlgmnt myTCustPrdAlgmnt,TCustAlgmnt myTCustAlgmnt,TPrdAlgmnt myTPrdAlgmnt "+
	    	            " where myTCustPrdAlgmnt.tCustPrdAlgmntId.custAlgmntId=myTCustAlgmnt.custAlgmntId and myTCustPrdAlgmnt.tCustPrdAlgmntId.prdAlgmntId=myTPrdAlgmnt.prdAlgmntId and myTCustAlgmnt.salesPosId  = ?1 "+
	    	            "and myTCustAlgmnt.tenantId = ?2 "+
	    	            "and myTPrdAlgmnt.tenantId = ?3 "+
	    	            "and myTCustAlgmnt.activeFlag ='Y'" ),
	    @NamedQuery(name = "fetchTCustPrdAlgmntByCustALId", query = "select myTCustPrdAlgmnt from TCustPrdAlgmnt myTCustPrdAlgmnt where myTCustPrdAlgmnt.tCustPrdAlgmntId.custAlgmntId = ?1 and myTCustPrdAlgmnt.activeFlag = ?2 "),
	    @NamedQuery(name = "fetchTCustPrdAlgmntByCustAlIds", query = "select myTCustPrdAlgmnt from TCustPrdAlgmnt myTCustPrdAlgmnt where myTCustPrdAlgmnt.tCustPrdAlgmntId.custAlgmntId in (?1) and myTCustPrdAlgmnt.tenantId = ?2 and myTCustPrdAlgmnt.activeFlag = 'Y' ")
		
})
@Table(name = "t_cust_prd_algmnt")
public class TCustPrdAlgmnt implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TCustPrdAlgmntId tCustPrdAlgmntId;

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
	@Column(name = "comp_flag", nullable = true, length = 1)
	private Character compFlag;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "alloc_pct", nullable = true, length = 255)
	private Integer allocPct;

	

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
	@Column(name = "cust_id", nullable = false, length = 255)
	private Integer custId;
	
	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "prd_id", nullable = false, length = 255)
	private Long prdId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "cust_algmnt_id", referencedColumnName = "cust_algmnt_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TCustAlgmnt tCustAlgmnt;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "prd_algmnt_id", referencedColumnName = "prd_algmnt_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TPrdAlgmnt tPrdAlgmnt;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "tCustPrdAlgmnt")
	private Set<TCallPlanPrd> tCallPlanPrdss;
	
	@Column(name = "prd_flag", nullable = true, length = 75)
	private String prdFlag;

	/**
	 * 
	 * @generated
	 */
	/*public TCustPrdAlgmnt() {
	}*/


	/**
	 * 
	 * @generated
	 */

	public void setTCustPrdAlgmntId(final TCustPrdAlgmntId tCustPrdAlgmntId) {
		this.tCustPrdAlgmntId = tCustPrdAlgmntId;
	}

	/**
	 * 
	 * @generated
	 */
	public TCustPrdAlgmntId getTCustPrdAlgmntId() {
		return this.tCustPrdAlgmntId;
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
			this.createDt =x;
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
	public TCustAlgmnt getTCustAlgmnt() {
		return this.tCustAlgmnt;
	}

	public TPrdAlgmnt gettPrdAlgmnt() {
		return tPrdAlgmnt;
	}

	public void settPrdAlgmnt(TPrdAlgmnt tPrdAlgmnt) {
		this.tPrdAlgmnt = tPrdAlgmnt;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCustAlgmnt(final TCustAlgmnt tCustAlgmnt) {
		this.tCustAlgmnt = tCustAlgmnt;
		if (this.tCustAlgmnt != null
				&& this.tCustAlgmnt.getCustAlgmntId() != null) {

			this.tCustPrdAlgmntId.setCustAlgmntId(this.tCustAlgmnt
					.getCustAlgmntId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	public Set<TCallPlanPrd> getTCallPlanPrdss() {
		return this.tCallPlanPrdss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCallPlanPrdss(final Set<TCallPlanPrd> tCallPlanPrdss) {
		this.tCallPlanPrdss = tCallPlanPrdss;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCompFlag(final Character compFlag) {
		this.compFlag = compFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getCompFlag() {
		return this.compFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAllocPct(final Integer allocPct) {
		this.allocPct = allocPct;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getAllocPct() {
		return this.allocPct;
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
	 * Gets the cust id.
	 *
	 * @return the cust id
	 */
	public Integer getCustId() {
		return custId;
	}

	/**
	 * Sets the cust id.
	 *
	 * @param custId the new cust id
	 */
	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	/**
	 * Gets the prd id.
	 *
	 * @return the prd id
	 */
	public Long getPrdId() {
		return prdId;
	}

	/**
	 * Sets the prd id.
	 *
	 * @param prdId the new prd id
	 */
	public void setPrdId(Long prdId) {
		this.prdId = prdId;
	}
	
	/**
	 * Gets the prd flag.
	 * @return the prd flag.
	 */
	public String getPrdFlag() {
		return prdFlag;
	}

	/**
	 * Sets the prd flag.
	 * @return the prd flag.
	 */
	public void setPrdFlag(String prdFlag) {
		this.prdFlag = prdFlag;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCustPrdAlgmnt that) {
		setTCustPrdAlgmntId(that.getTCustPrdAlgmntId());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setCompFlag(that.getCompFlag());
		setAllocPct(that.getAllocPct());
		setEffStartDt(that.getEffStartDt());
		setEffEndDt(that.getEffEndDt());
		setCustId(that.getCustId());
		setPrdId(that.getPrdId());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((tCustPrdAlgmntId == null) ? 0 : tCustPrdAlgmntId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tCustPrdAlgmntId=[").append(tCustPrdAlgmntId)
				.append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("custId=[").append(custId).append("] ");
		buffer.append("prdId=[").append(prdId).append("] ");
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
		final TCustPrdAlgmnt other = (TCustPrdAlgmnt) obj;
		if (tCustPrdAlgmntId == null) {
			if (other.tCustPrdAlgmntId != null) {
				return false;
			}
		} else if (!tCustPrdAlgmntId.equals(other.tCustPrdAlgmntId)) {
			return false;
		}
		return true;
	}
}