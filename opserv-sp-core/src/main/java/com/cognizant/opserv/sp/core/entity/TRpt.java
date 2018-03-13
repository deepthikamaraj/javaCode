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

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TRpt 
 * The corresponding mapping table is t_rpt 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTRpts", query = "select myTRpt from TRpt myTRpt"),
		@NamedQuery(name = "CountTRpts", query = "Select Count(c) from TRpt c"),
		@NamedQuery(name = "FindTRptByTRptSchedExecution", query = "select myTRpt from TRpt myTRpt where myTRpt.tRptSchedExecution = ?1 "),
		@NamedQuery(name = "CountTRptsByTRptSchedExecution", query = "select Count(myTRpt) from TRpt myTRpt where myTRpt.tRptSchedExecution = ?1 "),
		@NamedQuery(name = "FindTRptByTRptStatusType", query = "select myTRpt from TRpt myTRpt where myTRpt.rptStatusTypeId = ?1 "),
		@NamedQuery(name = "CountTRptsByTRptStatusType", query = "select Count(myTRpt) from TRpt myTRpt where myTRpt.rptStatusTypeId = ?1 "),
		@NamedQuery(name = "FindTRptByRecipient", query = "select myTRpt from TRpt myTRpt,TRptRecipient trpRecipient where myTRpt.rptId=trpRecipient.tRptRecipientId.rptId and trpRecipient.tRptRecipientId.staffId = ?1 "),
		@NamedQuery(name = "FindTRptsByMonth", query = "select myTRpt from TRpt myTRpt where MONTH(myTRpt.publishedDt) = ?1 and YEAR(myTRpt.publishedDt) = ?2 and myTRpt.rptStatusTypeId =?3 and myTRpt.tenantId =?4"),
		@NamedQuery(name = "FindTRptByRecipientAndTRptType",query = "select myTRpt from TRpt myTRpt,TRptRecipient trpRecipient where myTRpt.rptId=trpRecipient.tRptRecipientId.rptId and trpRecipient.tRptRecipientId.staffId = ?1 and myTRpt.tRptSchedExecution.tRptSched.tRptConfig.rptTypeId =?2 and myTRpt.rptStatusTypeId =?3 and myTRpt.tenantId =?4 order by myTRpt.publishedDt desc"),
		@NamedQuery(name = "findLastPublishedRptId",query="select max(myTRpt.rptId) from TRpt myTRpt where myTRpt.publishedDt = (select max(myTRpt1.publishedDt) from TRpt myTRpt1,TRptConfig myTRptConfig where myTRpt1.tRptSchedExecution.tRptSched.tRptConfig.rptConfigId=myTRptConfig.rptConfigId and myTRptConfig.rptTypeId = ?1 and myTRpt1.rptStatusTypeId=?2)"),
		@NamedQuery(name = "FindTRptByTRptStatusTypeAndTRptType", query = "select myTRpt from TRpt myTRpt where myTRpt.rptStatusTypeId = ?1 AND myTRpt.tRptSchedExecution.tRptSched.tRptConfig.rptTypeId = ?2 and myTRpt.tenantId= ?3"),
		@NamedQuery(name = "FindTRptsByMonthAndType", query = "select myTRpt from TRpt myTRpt where MONTH(myTRpt.publishedDt) = ?1 and YEAR(myTRpt.publishedDt) = ?2 and myTRpt.rptStatusTypeId =?3 and myTRpt.tRptSchedExecution.tRptSched.tRptConfig.rptTypeId =?4 and myTRpt.tenantId =?5"),
		@NamedQuery(name = "FindOlderTRptsByType", query = "select myTRpt from TRpt myTRpt,TRptRecipient trpRecipient where myTRpt.rptId=trpRecipient.tRptRecipientId.rptId and myTRpt.publishedDt < ?1 and myTRpt.rptStatusTypeId =?2 and myTRpt.tRptSchedExecution.tRptSched.tRptConfig.rptTypeId =?3 and trpRecipient.tRptRecipientId.staffId = ?4 and myTRpt.tenantId =?5"),
		@NamedQuery(name = "FindTRptsByRptType", query = "select myTRpt from TRpt myTRpt,TRptRecipient trpRecipient where myTRpt.rptId=trpRecipient.tRptRecipientId.rptId and trpRecipient.tRptRecipientId.staffId = ?1 and myTRpt.tRptSchedExecution.tRptSched.tRptConfig.rptTypeId =?2 and myTRpt.rptStatusTypeId =?3 and myTRpt.publishedDt >= ?4 and myTRpt.tenantId =?5 order by myTRpt.updateDate desc"),
		@NamedQuery(name = "GetUserRportsByStaff", query = "select rpt.rptId,rpt.rptTitle,rptr.readDt,rptr.ackStatusId,rptc.rptCategoryId,rptr.tRptRecipientId.staffId," +
				" rpt.publishedDt,alg.effStartDt,alg.effEndDt,pers.firstName,pers.middleName,pers.lastName " +
				" from TRpt rpt inner join rpt.tRptRecipientss rptr inner join rpt.tRptSchedExecution rptse inner join rptse.tRptSched rpts " +
				" inner join rpts.tRptConfig rptc inner join rptr.tPers pers inner join pers.tEmpAlgmntss alg " +
				"  where alg.effStartDt <= rpt.publishedDt and alg.effEndDt >= rpt.publishedDt and rptr.tRptRecipientId.staffId = ?1 AND " +
				" rptc.rptTypeId =?2 AND rpt.rptStatusTypeId =?3 AND rpt.tenantId =?4 AND alg.algmntId = ?5 AND alg.activeFlag = 'Y' " +
				" group by rpt.rptId order by rpt.updateDate desc"),
		@NamedQuery(name = "GetUserRportsByMonth", query = "select rpt.rptId,rpt.rptTitle,rptr.readDt,rptr.ackStatusId,rptc.rptCategoryId," +
				" rptr.tRptRecipientId.staffId,rpt.publishedDt,alg.effStartDt,alg.effEndDt," +
				" pers.firstName,pers.middleName,pers.lastName from TRpt rpt inner join rpt.tRptRecipientss" +
				" rptr inner join rpt.tRptSchedExecution rptse inner join rptse.tRptSched rpts inner join rpts.tRptConfig rptc " +
				" inner join rptr.tPers pers inner join pers.tEmpAlgmntss alg  " +
				" where  alg.effStartDt <= rpt.publishedDt and alg.effEndDt >= rpt.publishedDt and MONTH(rpt.publishedDt) = ?1 " +
				" and YEAR(rpt.publishedDt) = ?2 and rptc.rptTypeId =?3 and rpt.rptStatusTypeId =?4 and rpt.tenantId =?5 and" +
				" rptr.tRptRecipientId.staffId = ?6 AND alg.algmntId = ?7 and alg.activeFlag = 'Y' group by rpt.rptId order by rpt.updateDate desc"),
//		@NamedQuery(name = "GetUserRportsBySalesPos", query = "select rpt.rptId,rpt.rptTitle,rptr.readDt,rptr.ackStatusId,rptc.rptCategoryId,rptr.tRptRecipientId.staffId,rpt.publishedDt,alg.effStartDt,alg.effEndDt,pers.firstName,pers.middleName,pers.lastName from TRpt rpt inner join rpt.tRptRecipientss" +
//								" rptr inner join rpt.tRptSchedExecution rptse inner join rptse.tRptSched rpts inner join rpts.tRptConfig rptc inner join rptr.tPers pers inner join pers.tEmpAlgmntss alg   where   and MONTH(rpt.publishedDt) = ?1 and YEAR(rpt.publishedDt) = ?2 and rptc.rptTypeId =?3 and rpt.rptStatusTypeId =?4 and rpt.tenantId =?5 and pos.salesPosId in ?6 group by rpt.rptId order by rpt.updateDate desc"),
		@NamedQuery(name = "GetReportTitleByRptID", query = "select myTRpt.rptTitle from TRpt myTRpt where myTRpt.rptId=?1"),
		@NamedQuery(name = "FetchRptDesignOfConfigReport", query = "select ds.dataSrcDesign from TRpt myTRpt" +
				" inner join myTRpt.tRptSchedExecution.tRptSched.tRptConfig.tRptDataSourcess ds where myTRpt.rptId=?1 and ds.tRptConfig = myTRpt.tRptSchedExecution.tRptSched.tRptConfig "),
		@NamedQuery(name = "GetUserRportsCountByStaff", query = "select rptc.rptTypeId,COUNT(DISTINCT alg.salesPosId), COUNT(rpt.rptId) from TRpt rpt " +
				" inner join rpt.tRptRecipientss rptr inner join rpt.tRptSchedExecution rptse inner join rptse.tRptSched rpts" +
				" inner join rpts.tRptConfig rptc inner join rptr.tPers pers inner join pers.tEmpAlgmntss alg" +
				" where alg.effStartDt <= rpt.publishedDt AND alg.effEndDt >= rpt.publishedDt and" +
				" rptr.tRptRecipientId.staffId = ?1 and rpt.rptStatusTypeId =?2 and rptr.readDt is null  and rpt.tenantId =?3" +
				" and alg.algmntId = ?4 and alg.activeFlag = 'Y' group by rptc.rptTypeId "),
		@NamedQuery(name = "FetchRecptData", query = "SELECT myTRptConfig.tTargetRecipientPref.recipientPrefId,myTRptConfig.ackRequired,myTRptConfig.rptConfigId " +
				" FROM TRpt myTRpt JOIN myTRpt.tRptSchedExecution myTRptSchedExecution JOIN myTRptSchedExecution.tRptSched myTRptSched " +
				" JOIN myTRptSched.tRptConfig myTRptConfig " +
				" WHERE myTRpt.rptId=?1 and myTRpt.tenantId=?2 and myTRpt.tenantId= myTRptConfig.tenantId and myTRptConfig.tenantId= myTRptSched.tenantId " +
				"and myTRptSched.tenantId= myTRptSchedExecution.tenantId "),
		@NamedQuery(name = "GetUserRportsByStaffAlignment", query = "select rpt.rptId,rpt.rptTitle,rptr.readDt,rptr.ackStatusId,rptc.rptCategoryId,rptr.tRptRecipientId.staffId," +
				" rpt.publishedDt from TRpt rpt inner join rpt.tRptRecipientss rptr inner join rpt.tRptSchedExecution rptse inner join rptse.tRptSched rpts " +
				" inner join rpts.tRptConfig rptc inner join rptr.tPers pers inner join pers.tEmpAlgmntss alg " +
				"  where alg.effStartDt <= rpt.publishedDt and alg.effEndDt >= rpt.publishedDt and rptr.tRptRecipientId.staffId = ?1 AND " +
				" rptc.rptTypeId =?2 AND rpt.rptStatusTypeId =?3 AND rpt.tenantId =?4 AND alg.algmntId = ?5 AND alg.activeFlag = 'Y' " +
				" group by rpt.rptId order by rpt.updateDate desc"),
})
@Table(name = "t_rpt", uniqueConstraints = @UniqueConstraint(columnNames = { "rpt_id" }))
public class TRpt implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rpt_id", nullable = false, length = 255)
	private Integer rptId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 75)
	@Column(name = "rpt_title", nullable = false, length = 75)
	private String rptTitle;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "published_dt", nullable = true, length = 10)
	private Date publishedDt;

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
	private Date updateDate;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "sched_execution_id", referencedColumnName = "sched_execution_id", nullable = false, insertable = true, updatable = true)
	@Valid
	@NotAudited
	private TRptSchedExecution tRptSchedExecution;

	@NotNull
	@Column(name = "rpt_status_type_id", nullable = false, length = 255)
	private Integer rptStatusTypeId;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tRpt")
	@NotAudited
	private Set<TRptRecipient> tRptRecipientss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tRpt")
	@NotAudited
	private Set<TRptPublisher> tRptPublisherss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tRpt")
	@NotAudited
	private Set<TRptDoc> tRptDocss;
	/**
	 *
	 * @generated
	 */
	public TRpt() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setRptId(final Integer rptId) {
		this.rptId = rptId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getRptId() {
		return this.rptId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setRptTitle(final String rptTitle) {
		this.rptTitle = rptTitle;
	}

	/**
	 * 
	 * @generated
	 */
	public String getRptTitle() {
		return this.rptTitle;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPublishedDt(final Date publishedDt) {
		if (publishedDt != null) {
			this.publishedDt = (Date) publishedDt.clone();
		} else {
			this.publishedDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getPublishedDt() {
		if (this.publishedDt != null) {
			return (Date) this.publishedDt.clone();
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
	public TRptSchedExecution getTRptSchedExecution() {
		return this.tRptSchedExecution;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTRptSchedExecution(final TRptSchedExecution tRptSchedExecution) {
		this.tRptSchedExecution = tRptSchedExecution;

	}

	/**
	 * 
	 * @generated
	 */
	/*public TRptStatusType getTRptStatusType() {
		return this.tRptStatusType;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTRptStatusType(final TRptStatusType tRptStatusType) {
		this.tRptStatusType = tRptStatusType;

	}*/
	
	/**
	 * 
	 * @generated
	 */
	public Set<TRptRecipient> getTRptRecipientss() {
		return this.tRptRecipientss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTRptRecipientss(final Set<TRptRecipient> tRptRecipientss) {
		this.tRptRecipientss = tRptRecipientss;
	}
	/**
	 * 
	 * @generated
	 */
	public Set<TRptPublisher> getTRptPublisherss() {
		return this.tRptPublisherss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTRptPublisherss(final Set<TRptPublisher> tRptPublisherss) {
		this.tRptPublisherss = tRptPublisherss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TRptDoc> getTRptDocss() {
		return this.tRptDocss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTRptDocss(final Set<TRptDoc> tRptDocss) {
		this.tRptDocss = tRptDocss;
	}

	public Integer getRptStatusTypeId() {
		return rptStatusTypeId;
	}

	public void setRptStatusTypeId(Integer rptStatusTypeId) {
		this.rptStatusTypeId = rptStatusTypeId;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TRpt that) {
		setRptId(that.getRptId());
		setRptTitle(that.getRptTitle());
		setPublishedDt(that.getPublishedDt());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDate(that.getUpdateDate());
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
		result = prime * result + ((rptId == null) ? 0 : rptId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("rptId=[").append(rptId).append("] ");
		buffer.append("rptTitle=[").append(rptTitle).append("] ");
		buffer.append("publishedDt=[").append(publishedDt).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDate=[").append(updateDate).append("] ");
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
		final TRpt other = (TRpt) obj;
		if (rptId == null) {
			if (other.rptId != null) {
				return false;
			}
		} else if (!rptId.equals(other.rptId)) {
			return false;
		}
		return true;
	}
}
