package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

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
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** 
 * JPA class representing TRptTUsrRptPref 
 * The corresponding mapping table is t_rpt_t_usr_rpt_pref 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTRptTUsrRptPrefs", query = "select myTRptTUsrRptPref from TRptTUsrRptPref myTRptTUsrRptPref"),
		@NamedQuery(name = "CountTRptTUsrRptPrefs", query = "Select Count(c) from TRptTUsrRptPref c"),
		@NamedQuery(name = "FindTRptTUsrRptPrefByTUsrRptPref", query = "select myTRptTUsrRptPref from TRptTUsrRptPref myTRptTUsrRptPref where myTRptTUsrRptPref.tUsrRptPref = ?1 "),
		@NamedQuery(name = "CountTRptTUsrRptPrefsByTUsrRptPref", query = "select Count(*) from TRptTUsrRptPref myTRptTUsrRptPref where myTRptTUsrRptPref.tUsrRptPref = ?1 "),
		@NamedQuery(name = "FindTRptTUsrRptPrefByTUsrRptPrefAndTRpt", query = "select myTRptTUsrRptPref from TRptTUsrRptPref myTRptTUsrRptPref where myTRptTUsrRptPref.tUsrRptPref.prefId = ?1 and myTRptTUsrRptPref.tRpt.rptId = ?2 and myTRptTUsrRptPref.tenantId = ?3 and myTRptTUsrRptPref.tSalesPos.salesPosId = ?4 and myTRptTUsrRptPref.tSalesPos.tAlgmntSalesHier.salesHierId =?5 "),
		@NamedQuery(name = "FindTRptTUsrRptPrefByFolderAndRpt", query = "select myTRptTUsrRptPref from TRptTUsrRptPref myTRptTUsrRptPref where myTRptTUsrRptPref.tUsrRptPref.prefId = ?1 and myTRptTUsrRptPref.tRpt.rptId = ?2 and  myTRptTUsrRptPref.tenantId = ?3 "),
		@NamedQuery(name = "FetchFolderReports", query = "select c.tRpt.rptId,c.tRpt.rptTitle,c.tRpt.publishedDt,rptr.ackStatusId, rptc.rptCategoryId," +
				" c.tSalesPos.salesPosId,c.tSalesPos.posName,rptr.tRptRecipientId.staffId,pers.firstName,pers.middleName," +
				" pers.lastName from TRptTUsrRptPref c inner join c.tRpt.tRptRecipientss rptr inner join c.tRpt.tRptSchedExecution rptse " +
				" inner join rptse.tRptSched rpts inner join rpts.tRptConfig rptc inner join rptr.tPers pers " +
				" where c.tUsrRptPref.prefId = ?1 AND c.tenantId = ?2 AND c.activeFlag = 'Y' AND pers.staffId = ?3 order by c.tRpt.updateDate desc "),
		@NamedQuery(name = "CountOfFolderReports", query = "select Count(c) from TRptTUsrRptPref c " +
				"where c.tUsrRptPref.prefId = ?1 and c.tenantId = ?2 and c.activeFlag = 'Y' "),
})
@Table(name = "t_rpt_t_usr_rpt_pref")
public class TRptTUsrRptPref implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @generated
	 */
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "seq_number", nullable = false, length = 255)
	private Integer sequenceNumber;		
	
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
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "pref_id", referencedColumnName = "pref_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TUsrRptPref tUsrRptPref;
		
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "rpt_id", referencedColumnName = "rpt_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TRpt tRpt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "sales_pos_id", referencedColumnName = "sales_pos_id", nullable = true, insertable = true, updatable = true)
	@Valid
	private TSalesPos tSalesPos;
	
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
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * 
	 * @generated
	 */
	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public TRpt getTRpt() {
		return tRpt;
	}

	public void setTRpt(TRpt tRpt) {
		this.tRpt = tRpt;		
	}
	
	public TSalesPos getTSalesPos() {
		return tSalesPos;
	}

	public void setTSalesPos(TSalesPos tSalesPos) {
		this.tSalesPos = tSalesPos;
	}

	/**
	 * 
	 * @generated
	 */
	public TUsrRptPref getTUsrRptPref() {
		return this.tUsrRptPref;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTUsrRptPref(final TUsrRptPref tUsrRptPref) {
		this.tUsrRptPref = tUsrRptPref;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TRptTUsrRptPref that) {
		//setTRptTUsrRptPrefId(that.getTRptTUsrRptPrefId());
		setActiveFlag(that.getActiveFlag());
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
		result = prime * result;// + ((tRptTUsrRptPrefId == null) ? 0 : tRptTUsrRptPrefId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		//buffer.append("tRptTUsrRptPrefId=[").append(tRptTUsrRptPrefId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
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
		final TRptTUsrRptPref other = (TRptTUsrRptPref) obj;
		/*if (tRptTUsrRptPrefId == null) {
			if (other.tRptTUsrRptPrefId != null) {
				return false;
			}
		} else if (!tRptTUsrRptPrefId.equals(other.tRptTUsrRptPrefId)) {
			return false;
		}*/
		return true;
	}
}
