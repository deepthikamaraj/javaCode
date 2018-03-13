package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

/** 
 * JPA class representing TCustAff 
 * The corresponding mapping table is t_cust_aff 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTCustAffs", query = "select myTCustAff from TCustAff myTCustAff"),
		@NamedQuery(name = "CountTCustAffs", query = "Select Count(c) from TCustAff c"),
		@NamedQuery(name = "FindTCustAffByTCustByAffCustId", query = "select myTCustAff from TCustAff myTCustAff where myTCustAff.tCustByAffCustId = ?1 "),
		@NamedQuery(name = "CountTCustAffsByTCustByAffCustId", query = "select Count(myTCustAff) from TCustAff myTCustAff where myTCustAff.tCustByAffCustId = ?1 "),
		@NamedQuery(name = "FindTCustAffByTAlgmntSalesTeam", query = "select myTCustAff from TCustAff myTCustAff where myTCustAff.tAlgmntSalesTeam = ?1 "),
		@NamedQuery(name = "CountTCustAffsByTAlgmntSalesTeam", query = "select Count(myTCustAff) from TCustAff myTCustAff where myTCustAff.tAlgmntSalesTeam = ?1 "),
		/*@NamedQuery(name = "FindTCustAffByTWkflwStatus", query = "select myTCustAff from TCustAff myTCustAff where myTCustAff.tWkflwStatus = ?1 "),
		@NamedQuery(name = "CountTCustAffsByTWkflwStatus", query = "select Count(myTCustAff) from TCustAff myTCustAff where myTCustAff.tWkflwStatus = ?1 "),
		*/@NamedQuery(name = "FindTCustAffByTCustByCustId", query = "select myTCustAff from TCustAff myTCustAff where myTCustAff.tCustByCustId = ?1 "),
		@NamedQuery(name = "CountTCustAffsByTCustByCustId", query = "select Count(myTCustAff) from TCustAff myTCustAff where myTCustAff.tCustByCustId = ?1 "),
		@NamedQuery(name = "FindTCustAffsByTAlgmntSalesTeamAndTCustByCustId", query = "select myTCustAff from TCustAff myTCustAff where myTCustAff.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 and" +
				" myTCustAff.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?2 and myTCustAff.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 and myTCustAff.tCustByCustId.custId = ?4 and myTCustAff.activeFlag = ?5 "),
		@NamedQuery(name = "FindTCustAffsByTAlgmntSalesTeamAndTCustByAffCustId", query = "select myTCustAff from TCustAff myTCustAff where myTCustAff.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 and" +
				" myTCustAff.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?2 and myTCustAff.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 and myTCustAff.tCustByAffCustId.custId = ?4 and myTCustAff.activeFlag = ?5 "),
		@NamedQuery(name = "getAffiliationDtl", query = " select myTCustAff from TCustAff myTCustAff where myTCustAff.tCustByCustId.custId = ?1 " +
														" and myTCustAff.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?2 and myTCustAff.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?3 " +
														" and myTCustAff.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?4 and myTCustAff.tenantId = ?5 "),
		@NamedQuery(name = "getAffiliationDtlCallPlan", query = " select myTCustAff from TCustAff myTCustAff, TCustAlgmnt myTCustAlgmnt, TCust myTCust where myTCustAff.tCustByCustId.custId = ?1 " +
																" and myTCustAff.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?2 and myTCustAff.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?3 " +
																" and myTCustAff.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?4 and myTCustAff.tenantId = ?5 "+
																" and myTCustAff.tCustByCustId.custId = myTCust.custId and myTCust.custId = myTCustAlgmnt.tCust.custId and myTCustAlgmnt.tenantId = myTCustAff.tenantId and myTCustAlgmnt.activeFlag = myTCustAff.activeFlag " +
																" and myTCustAlgmnt.algmntId = myTCustAff.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId "+
																" and myTCustAlgmnt.bussUnitId = myTCustAff.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId "+
																" and myTCustAlgmnt.salesTeamId = myTCustAff.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId " +
																" and myTCustAlgmnt.activeFlag = 'Y' and myTCustAlgmnt.salesHierId = ?6 and myTCustAlgmnt.salesPosId = ?7 and myTCustAlgmnt.effEndDt >= ?8"),
		@NamedQuery(name = "checkTCustAffHeirFrCust", query = " select myTCustAff.tCustByCustId.custId, myTCustAff.tCustByAffCustId.custId ,myTCustAff.activeFlag from TCustAff myTCustAff where (myTCustAff.tCustByCustId.custId IN (?1) OR myTCustAff.tCustByAffCustId.custId IN (?1))" +
				"  and myTCustAff.tenantId = ?2 and myTCustAff.activeFlag = 'Y' "),
		@NamedQuery(name = "CountTCustAffByCustId", query = "select count(myTCustAff) from TCustAff myTCustAff where myTCustAff.tCustByCustId.custId = ?1 and myTCustAff.activeFlag = ?2 " +
				"and myTCustAff.affStartDt <= ?3 and  ( myTCustAff.affEndDt >= ?4 or myTCustAff.affEndDt is null ) and myTCustAff.tenantId = ?5 "),
		@NamedQuery(name = "CountTCustAffByAffCustId", query = "select count(myTCustAff) from TCustAff myTCustAff where myTCustAff.tCustByAffCustId.custId = ?1 and myTCustAff.activeFlag = ?2 " +
				"and myTCustAff.affStartDt <= ?3 and ( myTCustAff.affEndDt >= ?4 or myTCustAff.affEndDt is null ) and myTCustAff.tenantId = ?5 "),
		@NamedQuery(name = "FindTCustAffsByCustId", query = "select myTCustAff from TCustAff myTCustAff where myTCustAff.tCustByCustId.custId = ?1 and myTCustAff.activeFlag = ?2 and myTCustAff.tenantId = ?3 "),
		//TO GET HIGHER AFFILIATIONS DATA
		@NamedQuery(name = "FindTCustDataByCustId", query = "select c.custId, c.custName, c.custCd, c.custFirstName, c.custMiddleName, " +
										" c.custLastName,  c.stateLicId, c.deaId, c.comments, c.custTypeId," +
										" c.prtTypeId, c.categoryId , ct.typeName, ct.colorCd, caf.custAffId, " +
										" caf.tCustByAffCustId.custId " +
										" from TCustAff caf, TCust c , TCustType ct " +
										" WHERE c.custId = caf.tCustByAffCustId.custId AND c.tenantId = caf.tenantId " +
										" AND ct.tCustTypeId.custTypeId = c.custTypeId " +
										" AND ct.tCustTypeId.localeId = ?1 AND c.tenantId = ct.tenantId " +
										" AND caf.tCustByCustId.custId = ?2 and caf.activeFlag = ?3 and caf.tenantId = ?4 "),
		//TO GET LOWER AFFILIATIONS	DATA							
		@NamedQuery(name = "FindTCustDataByAffCustId", query = "select c.custId, c.custName, c.custCd, c.custFirstName, c.custMiddleName, " +
				" c.custLastName,  c.stateLicId, c.deaId, c.comments, c.custTypeId," +
				" c.prtTypeId, c.categoryId , ct.typeName, ct.colorCd,  caf.custAffId, caf.tCustByCustId.custId " +
				" from TCustAff caf , TCust c, TCustType ct " +
				" WHERE c.custId = caf.tCustByCustId.custId AND c.tenantId = caf.tenantId " +
				" AND ct.tCustTypeId.custTypeId  = c.custTypeId " +
				" AND ct.tCustTypeId.localeId = ?1 AND c.tenantId = ct.tenantId " +
				" AND caf.tCustByAffCustId.custId = ?2 and caf.activeFlag = ?3 AND caf.tenantId = ?4 "),
		//FETCH CHILDREN		
		@NamedQuery(name = "FindTCustAffByCustId", query = "select custAff.custAffId, custAff.tCustByCustId.custId, custAff.tCustByAffCustId.custId, " +
				" cust.custName,cust.custFirstName, cust.custLastName, cust.custMiddleName, cust.custCd, cust.custTypeId," +
				" custType.typeName, custType.colorCd, custAff.prAffFlag, custAff.tAlgmntSalesTeam, custAff.relTypeId  " +
				" from TCustAff custAff , TCust cust, TCustType custType " +
				" where custAff.tCustByCustId.custId = ?1 and custAff.activeFlag = ?2 and custAff.tenantId = ?3 " +
				" and custAff.affStartDt <= ?4 AND (custAff.affEndDt > ?4 or custAff.affEndDt is null) " +
				" and custAff.tCustByAffCustId.custId = cust.custId and custAff.tenantId = cust.tenantId " +
				" and cust.custTypeId = custType.tCustTypeId.custTypeId AND custType.tCustTypeId.localeId = ?5 "),
		//FETCH PARENTS
		@NamedQuery(name = "FindTCustAffByAffCustId", query = "select custAff.custAffId, custAff.tCustByCustId.custId, custAff.tCustByAffCustId.custId, " +
				" cust.custName,cust.custFirstName, cust.custLastName, cust.custMiddleName, cust.custCd, cust.custTypeId," +
				" custType.typeName, custType.colorCd, custAff.prAffFlag, custAff.tAlgmntSalesTeam, custAff.relTypeId   " +
				" from TCustAff custAff , TCust cust, TCustType custType " +
				" where custAff.tCustByAffCustId.custId = ?1 and custAff.activeFlag = ?2 and custAff.tenantId = ?3 " +
				" and custAff.affStartDt <= ?4 AND (custAff.affEndDt > ?4 or custAff.affEndDt is null) " +
				" and custAff.tCustByCustId.custId = cust.custId and custAff.tenantId = cust.tenantId " +
				" and cust.custTypeId = custType.tCustTypeId.custTypeId AND custType.tCustTypeId.localeId = ?5 "),		
		//FETCH PARENTS	Affiliations By Alignment	
		/*@NamedQuery(name = "FindTCustAffsByAlignmentAndCustId", query = "select myTCustAff from TCustAff myTCustAff " +
				" where myTCustAff.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 and myTCustAff.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?2 " +
				" and myTCustAff.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 and myTCustAff.tCustByCustId.custId = ?4 and myTCustAff.activeFlag = ?5 " +
				" and myTCustAff.tenantId = ?6 and myTCustAff.affStartDt <= ?7 and (myTCustAff.affEndDt > ?7 or myTCustAff.affEndDt is null) "),*/
		//FETCH CHILD Affiliations By Alignment
		/*@NamedQuery(name = "FindTCustAffsByAlignmentAndAffCustId", query = "select myTCustAff from TCustAff myTCustAff" +
				" where myTCustAff.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 and myTCustAff.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?2 " +
				" and myTCustAff.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 and myTCustAff.tCustByAffCustId.custId = ?4 and myTCustAff.activeFlag = ?5 " +
				" and myTCustAff.tenantId = ?6 and myTCustAff.affStartDt <= ?7 and (myTCustAff.affEndDt > ?7 or myTCustAff.affEndDt is null)"),*/
		//Check if global affiliated customer
		@NamedQuery(name = "FindAccountTCustAffsForTCust", query = " select distinct(myTCustAff.rootAffCustId) from TCustAff myTCustAff where (myTCustAff.tCustByCustId.custId = ?1 OR myTCustAff.tCustByAffCustId.custId =?1)" +
				" and myTCustAff.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = null and myTCustAff.tenantId = ?2 and myTCustAff.activeFlag = 'Y' "),
		//FETCH PARENTS	Affiliations without Alignment
		@NamedQuery(name = "FindTCustAffsForAffCustId", query = "select myTCustAff from TCustAff myTCustAff where myTCustAff.tCustByAffCustId.custId = ?1 " +
				" and myTCustAff.activeFlag = ?2 and myTCustAff.tenantId = ?3 and (myTCustAff.affEndDt > ?4 or myTCustAff.affEndDt is null) "),
		//FETCH CHILD Affiliations without Alignment	
		@NamedQuery(name = "FindTCustAffForCustId", query = "select myTCustAff from TCustAff myTCustAff where myTCustAff.rootAffCustId = ?1 " +
				" and myTCustAff.activeFlag = ?2 and myTCustAff.tenantId = ?3 and (myTCustAff.affEndDt > ?4 or myTCustAff.affEndDt is null) "),
		//To fetch Root aff id's for given custId
		@NamedQuery(name = "FetchRootAffIdsForCustId", query = "select distinct(myTCustAff.rootAffCustId) from TCustAff myTCustAff where (myTCustAff.tCustByCustId.custId = ?1 OR myTCustAff.tCustByAffCustId.custId =?1) and myTCustAff.tenantId = ?2 and myTCustAff.activeFlag = 'Y'"),
		//To fetch Root aff id's for given custId
		@NamedQuery(name = "FetchRootAffIdsForCustIdAndAlignment", query = "select distinct(myTCustAff.rootAffCustId) from TCustAff myTCustAff where (myTCustAff.tCustByCustId.custId = ?1 OR myTCustAff.tCustByAffCustId.custId =?1) " +
				" and myTCustAff.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?2 and myTCustAff.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?3 " +
				" and myTCustAff.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?4 and myTCustAff.tenantId = ?5 and myTCustAff.activeFlag = 'Y'"),
		//To fetch TCustAff for given rootAffId
		@NamedQuery(name = "FetchTCustAffsForRootAffId", query = "select myTCustAff from TCustAff myTCustAff where myTCustAff.rootAffCustId = ?1 and myTCustAff.tenantId = ?2 and myTCustAff.activeFlag = 'Y'"),
		//To fetch Account TCustAff for given customer
		@NamedQuery(name = "FetchAccountTCustAffs", query = "select myTCustAff from TCustAff myTCustAff where myTCustAff.rootAffCustId = ?1 and myTCustAff.tenantId = ?2 " +
				" and (myTCustAff.affEndDt > ?3 or myTCustAff.affEndDt is null) and myTCustAff.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = null and myTCustAff.activeFlag = 'Y'")
		
})
@Table(name = "t_cust_aff", uniqueConstraints = @UniqueConstraint(columnNames = { "cust_aff_id" }))
public class TCustAff implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cust_aff_id", nullable = false, length = 255)
	private Integer custAffId;

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
	@Column(name = "aff_start_dt", nullable = true, length = 10)
	private Date affStartDt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "aff_end_dt", nullable = true, length = 10)
	private Date affEndDt;

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
	@Column(name = "pr_aff_flag", nullable = true, length = 1)
	private Character prAffFlag;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "rel_type_id", nullable = true, length = 255)
	private Integer relTypeId;

	/**
	 * value = cust_id
	 * @generated
	 */
	@Column(name = "root_aff_cust_id", nullable = true, length = 255)
	private Integer rootAffCustId;

	@ManyToOne
	@JoinColumn(name = "aff_cust_id", referencedColumnName = "cust_id", nullable = false, insertable = true, updatable = true)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TCust tCustByAffCustId;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "algmnt_id", referencedColumnName = "algmnt_id", nullable = true, insertable = true, updatable = true),
			@JoinColumn(name = "buss_unit_id", referencedColumnName = "buss_unit_id", nullable = true, insertable = true, updatable = true),
			@JoinColumn(name = "sales_team_id", referencedColumnName = "sales_team_id", nullable = true, insertable = true, updatable = true) })
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TAlgmntSalesTeam tAlgmntSalesTeam;

	/*@ManyToOne
	@JoinColumn(name = "status_id", referencedColumnName = "status_id", nullable = true, insertable = true, updatable = true)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TWkflwStatus tWkflwStatus;*/

	@ManyToOne
	@JoinColumn(name = "cust_id", referencedColumnName = "cust_id", nullable = false, insertable = true, updatable = true)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TCust tCustByCustId;

	/**
	 *
	 * @generated
	 */
	public TCustAff() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setCustAffId(final Integer custAffId) {
		this.custAffId = custAffId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCustAffId() {
		return this.custAffId;
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

	public void setAffStartDt(final Date affStartDt) {
		if (affStartDt != null) {
			this.affStartDt = (Date) affStartDt.clone();
		} else {
			this.affStartDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getAffStartDt() {
		if (this.affStartDt != null) {
			return (Date) this.affStartDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setAffEndDt(final Date affEndDt) {
		if (affEndDt != null) {
			this.affEndDt = (Date) affEndDt.clone();
		} else {
			this.affEndDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getAffEndDt() {
		if (this.affEndDt != null) {
			return (Date) this.affEndDt.clone();
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
	public TCust getTCustByAffCustId() {
		return this.tCustByAffCustId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCustByAffCustId(final TCust tCustByAffCustId) {
		this.tCustByAffCustId = tCustByAffCustId;

	}

	/**
	 * 
	 * @generated
	 */
	public TAlgmntSalesTeam getTAlgmntSalesTeam() {
		return this.tAlgmntSalesTeam;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAlgmntSalesTeam(final TAlgmntSalesTeam tAlgmntSalesTeam) {
		this.tAlgmntSalesTeam = tAlgmntSalesTeam;

	}

/*	*//**
	 * 
	 * @generated
	 *//*
	public TWkflwStatus getTWkflwStatus() {
		return this.tWkflwStatus;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTWkflwStatus(final TWkflwStatus tWkflwStatus) {
		this.tWkflwStatus = tWkflwStatus;

	}*/

	/**
	 * 
	 * @generated
	 */
	public TCust getTCustByCustId() {
		return this.tCustByCustId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCustByCustId(final TCust tCustByCustId) {
		this.tCustByCustId = tCustByCustId;

	}

	/**
	 * 
	 * @generated
	 */

	public void setPrAffFlag(final Character prAffFlag) {
		this.prAffFlag = prAffFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getPrAffFlag() {
		return this.prAffFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public void setRelTypeId(final Integer relTypeId) {
		this.relTypeId = relTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getRelTypeId() {
		return this.relTypeId;
	}
	
	
	/**
	 * Gets the root aff id.
	 *
	 * @return the root aff id
	 */
	public Integer getRootAffCustId() {
		return rootAffCustId;
	}

	/**
	 * Sets the root aff id.
	 *
	 * @param rootAffId the new root aff id
	 */
	public void setRootAffCustId(Integer rootAffCustId) {
		this.rootAffCustId = rootAffCustId;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCustAff that) {
		setCustAffId(that.getCustAffId());
		setActiveFlag(that.getActiveFlag());
		setAffStartDt(that.getAffStartDt());
		setAffEndDt(that.getAffEndDt());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setPrAffFlag(that.getPrAffFlag());
		setRelTypeId(that.getRelTypeId());
		setRootAffCustId(that.getRootAffCustId());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((custAffId == null) ? 0 : custAffId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("custAffId=[").append(custAffId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("affStartDt=[").append(affStartDt).append("] ");
		buffer.append("affEndDt=[").append(affEndDt).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("prAffFlag=[").append(prAffFlag).append("] ");
		buffer.append("relTypeId=[").append(relTypeId).append("] ");
		buffer.append("rootAffCustId=[").append(rootAffCustId).append("] ");
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
		final TCustAff other = (TCustAff) obj;
		if (custAffId == null) {
			if (other.custAffId != null) {
				return false;
			}
		} else if (!custAffId.equals(other.custAffId)) {
			return false;
		}
		return true;
	}
}
