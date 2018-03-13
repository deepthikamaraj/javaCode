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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.Length;

/**
 * JPA class representing TCustSalesTeam , corresponding mapping table is t_cust_sales_team
 */
@Entity
@Audited
@NamedQueries({
	@NamedQuery(name = "FindAllTCustSalesTeams", query = "select myTCustSalesTeam from TCustSalesTeam myTCustSalesTeam"),
	@NamedQuery(name = "findTCustSalesTeamByCustId", query = "select myTCustSalesTeam from TCustSalesTeam myTCustSalesTeam where myTCustSalesTeam.tAlgmntSalesTeam.tAlgmnt.algmntId = ?1 " +
															 " and myTCustSalesTeam.tAlgmntSalesTeam.tSalesTeam.tBussUnit.bussUnitId = ?2 " +
															 " and myTCustSalesTeam.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.salesTeamId = ?3 " +
															 " and myTCustSalesTeam.tCust.custId IN ?4 " +
															 " and myTCustSalesTeam.tenantId = ?5 " +
															 " and myTCustSalesTeam.activeFlag = 'Y'"),
	@NamedQuery(name = "FindTCustSalesTeamByTCust", query = "select myTCustSalesTeam from TCustSalesTeam myTCustSalesTeam where myTCustSalesTeam.tCust = ?1 "),
	@NamedQuery(name = "CountTCustSalesTeamsByTCust", query = "select Count(myTCustSalesTeam) from TCustSalesTeam myTCustSalesTeam where myTCustSalesTeam.tCust = ?1 ") 
})
@Table(name = "t_cust_sales_team", uniqueConstraints = @UniqueConstraint(columnNames = { "cust_sales_team_id" }))
public class TCustSalesTeam implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1571201953648118620L;

	/** The cust sales team id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cust_sales_team_id", nullable = false, length = 255)
	private Long custSalesTeamId;
	
	/** The active flag. */
	@Column(name = "active_flag", nullable = true, length = 1)
	private Character activeFlag;
	
	/** The created by. */
	@NotNull
	@Column(name = "created_by", nullable = false, length = 255)
	private Integer createdBy;

	
	/** The create dt. */
	@NotNull
	@Column(name = "create_dt", nullable = false, length = 19)
	private Date createDt;

	
	/** The updated by. */
	@Column(name = "updated_by", nullable = true, length = 255)
	private Integer updatedBy;


	/** The update dt. */
	@Column(name = "update_dt", nullable = true, length = 19)
	private Date updateDt;

	/** The tenant id. */
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;
	
	/** The eff start dt. */
	@Column(name = "eff_start_dt", nullable = true, length = 19)
	private Date effStartDt;
	
	/** The eff end dt. */
	@Column(name = "eff_end_dt", nullable = true, length = 19)
	private Date effEndDt;
	
	
	/** The attr1. */
	@Length(max = 1000)
	@Column(name = "attr_1", nullable = true, length = 1000)
	private String attr1;
	
	
	/** The attr2. */
	@Length(max = 1000)
	@Column(name = "attr_2", nullable = true, length = 1000)
	private String attr2;

	
	/** The attr3. */
	@Length(max = 1000)
	@Column(name = "attr_3", nullable = true, length = 1000)
	private String attr3;

	
	/** The attr4. */
	@Length(max = 1000)
	@Column(name = "attr_4", nullable = true, length = 1000)
	private String attr4;

	/** The attr5. */
	@Length(max = 1000)
	@Column(name = "attr_5", nullable = true, length = 1000)
	private String attr5;


	/** The attr6. */
	@Length(max = 1000)
	@Column(name = "attr_6", nullable = true, length = 1000)
	private String attr6;

	
	/** The attr7. */
	@Length(max = 1000)
	@Column(name = "attr_7", nullable = true, length = 1000)
	private String attr7;

	
	/** The attr8. */
	@Length(max = 1000)
	@Column(name = "attr_8", nullable = true, length = 1000)
	private String attr8;

	
	/** The attr9. */
	@Length(max = 1000)
	@Column(name = "attr_9", nullable = true, length = 1000)
	private String attr9;

	
	/** The attr10. */
	@Length(max = 1000)
	@Column(name = "attr_10", nullable = true, length = 1000)
	private String attr10;

	
	/** The attr11. */
	@Length(max = 1000)
	@Column(name = "attr_11", nullable = true, length = 1000)
	private String attr11;

	
	/** The attr12. */
	@Length(max = 1000)
	@Column(name = "attr_12", nullable = true, length = 1000)
	private String attr12;

	
	/** The attr13. */
	@Length(max = 1000)
	@Column(name = "attr_13", nullable = true, length = 1000)
	private String attr13;

	
	/** The attr14. */
	@Length(max = 1000)
	@Column(name = "attr_14", nullable = true, length = 1000)
	private String attr14;

	
	/** The attr15. */
	@Length(max = 1000)
	@Column(name = "attr_15", nullable = true, length = 1000)
	private String attr15;

	
	/** The attr16. */
	@Length(max = 1000)
	@Column(name = "attr_16", nullable = true, length = 1000)
	private String attr16;

	
	/** The attr17. */
	@Length(max = 1000)
	@Column(name = "attr_17", nullable = true, length = 1000)
	private String attr17;

	
	/** The attr18. */
	@Length(max = 1000)
	@Column(name = "attr_18", nullable = true, length = 1000)
	private String attr18;

	/** The attr19. */
	@Length(max = 1000)
	@Column(name = "attr_19", nullable = true, length = 1000)
	private String attr19;

	
	/** The attr20. */
	@Length(max = 1000)
	@Column(name = "attr_20", nullable = true, length = 1000)
	private String attr20;

	/** The t algmnt sales team. */
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "algmnt_id", referencedColumnName = "algmnt_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "buss_unit_id", referencedColumnName = "buss_unit_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "sales_team_id", referencedColumnName = "sales_team_id", nullable = false, insertable = true, updatable = true) })
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TAlgmntSalesTeam tAlgmntSalesTeam;
	       
	/** The t cust. */
	@ManyToOne
	@JoinColumn(name = "cust_id", referencedColumnName = "cust_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TCust tCust;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tCustSalesTeam", fetch=FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@NotAudited
	private Set<TCustPrdSalesTeam> tCustPrdSalesTeams;
	/**
	 * Gets the cust sales team id.
	 *
	 * @return the cust sales team id
	 */
	public Long getCustSalesTeamId() {
		return custSalesTeamId;
	}


	/**
	 * Sets the cust sales team id.
	 *
	 * @param custSalesTeamId the new cust sales team id
	 */
	public void setCustSalesTeamId(Long custSalesTeamId) {
		this.custSalesTeamId = custSalesTeamId;
	}


	/**
	 * Gets the active flag.
	 *
	 * @return the active flag
	 */
	public Character getActiveFlag() {
		return activeFlag;
	}


	/**
	 * Sets the active flag.
	 *
	 * @param activeFlag the new active flag
	 */
	public void setActiveFlag(Character activeFlag) {
		this.activeFlag = activeFlag;
	}


	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public Integer getCreatedBy() {
		return createdBy;
	}


	/**
	 * Sets the created by.
	 *
	 * @param createdBy the new created by
	 */
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}


	/**
	 * Gets the creates the dt.
	 *
	 * @return the creates the dt
	 */
	public Date getCreateDt() {
		return createDt;
	}


	/**
	 * Sets the creates the dt.
	 *
	 * @param createDt the new creates the dt
	 */
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}


	/**
	 * Gets the updated by.
	 *
	 * @return the updated by
	 */
	public Integer getUpdatedBy() {
		return updatedBy;
	}


	/**
	 * Sets the updated by.
	 *
	 * @param updatedBy the new updated by
	 */
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}


	/**
	 * Gets the update dt.
	 *
	 * @return the update dt
	 */
	public Date getUpdateDt() {
		return updateDt;
	}


	/**
	 * Sets the update dt.
	 *
	 * @param updateDt the new update dt
	 */
	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}


	/**
	 * Gets the tenant id.
	 *
	 * @return the tenant id
	 */
	public Short getTenantId() {
		return tenantId;
	}


	/**
	 * Sets the tenant id.
	 *
	 * @param tenantId the new tenant id
	 */
	public void setTenantId(Short tenantId) {
		this.tenantId = tenantId;
	}


	/**
	 * Gets the eff start dt.
	 *
	 * @return the eff start dt
	 */
	public Date getEffStartDt() {
		return effStartDt;
	}


	/**
	 * Sets the eff start dt.
	 *
	 * @param effStartDt the new eff start dt
	 */
	public void setEffStartDt(Date effStartDt) {
		this.effStartDt = effStartDt;
	}


	/**
	 * Gets the eff end dt.
	 *
	 * @return the eff end dt
	 */
	public Date getEffEndDt() {
		return effEndDt;
	}


	/**
	 * Sets the eff end dt.
	 *
	 * @param effEndDt the new eff end dt
	 */
	public void setEffEndDt(Date effEndDt) {
		this.effEndDt = effEndDt;
	}


	/**
	 * Gets the t algmnt sales team.
	 *
	 * @return the t algmnt sales team
	 */
	public TAlgmntSalesTeam getTAlgmntSalesTeam() {
		return tAlgmntSalesTeam;
	}


	/**
	 * Sets the t algmnt sales team.
	 *
	 * @param tAlgmntSalesTeam the new t algmnt sales team
	 */
	public void setTAlgmntSalesTeam(TAlgmntSalesTeam tAlgmntSalesTeam) {
		this.tAlgmntSalesTeam = tAlgmntSalesTeam;
	}


	/**
	 * Gets the t cust.
	 *
	 * @return the t cust
	 */
	public TCust getTCust() {
		return tCust;
	}


	/**
	 * Sets the t cust.
	 *
	 * @param tCust the new t cust
	 */
	public void setTCust(TCust tCust) {
		this.tCust = tCust;
	}


	/**
	 * Gets the attr1.
	 *
	 * @return the attr1
	 */
	public String getAttr1() {
		return attr1;
	}


	/**
	 * Sets the attr1.
	 *
	 * @param attr1 the new attr1
	 */
	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}


	/**
	 * Gets the attr2.
	 *
	 * @return the attr2
	 */
	public String getAttr2() {
		return attr2;
	}


	/**
	 * Sets the attr2.
	 *
	 * @param attr2 the new attr2
	 */
	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}


	/**
	 * Gets the attr3.
	 *
	 * @return the attr3
	 */
	public String getAttr3() {
		return attr3;
	}


	/**
	 * Sets the attr3.
	 *
	 * @param attr3 the new attr3
	 */
	public void setAttr3(String attr3) {
		this.attr3 = attr3;
	}


	/**
	 * Gets the attr4.
	 *
	 * @return the attr4
	 */
	public String getAttr4() {
		return attr4;
	}


	/**
	 * Sets the attr4.
	 *
	 * @param attr4 the new attr4
	 */
	public void setAttr4(String attr4) {
		this.attr4 = attr4;
	}


	/**
	 * Gets the attr5.
	 *
	 * @return the attr5
	 */
	public String getAttr5() {
		return attr5;
	}


	/**
	 * Sets the attr5.
	 *
	 * @param attr5 the new attr5
	 */
	public void setAttr5(String attr5) {
		this.attr5 = attr5;
	}


	/**
	 * Gets the attr6.
	 *
	 * @return the attr6
	 */
	public String getAttr6() {
		return attr6;
	}


	/**
	 * Sets the attr6.
	 *
	 * @param attr6 the new attr6
	 */
	public void setAttr6(String attr6) {
		this.attr6 = attr6;
	}


	/**
	 * Gets the attr7.
	 *
	 * @return the attr7
	 */
	public String getAttr7() {
		return attr7;
	}


	/**
	 * Sets the attr7.
	 *
	 * @param attr7 the new attr7
	 */
	public void setAttr7(String attr7) {
		this.attr7 = attr7;
	}


	/**
	 * Gets the attr8.
	 *
	 * @return the attr8
	 */
	public String getAttr8() {
		return attr8;
	}


	/**
	 * Sets the attr8.
	 *
	 * @param attr8 the new attr8
	 */
	public void setAttr8(String attr8) {
		this.attr8 = attr8;
	}


	/**
	 * Gets the attr9.
	 *
	 * @return the attr9
	 */
	public String getAttr9() {
		return attr9;
	}


	/**
	 * Sets the attr9.
	 *
	 * @param attr9 the new attr9
	 */
	public void setAttr9(String attr9) {
		this.attr9 = attr9;
	}


	/**
	 * Gets the attr10.
	 *
	 * @return the attr10
	 */
	public String getAttr10() {
		return attr10;
	}


	/**
	 * Sets the attr10.
	 *
	 * @param attr10 the new attr10
	 */
	public void setAttr10(String attr10) {
		this.attr10 = attr10;
	}


	/**
	 * Gets the attr11.
	 *
	 * @return the attr11
	 */
	public String getAttr11() {
		return attr11;
	}


	/**
	 * Sets the attr11.
	 *
	 * @param attr11 the new attr11
	 */
	public void setAttr11(String attr11) {
		this.attr11 = attr11;
	}


	/**
	 * Gets the attr12.
	 *
	 * @return the attr12
	 */
	public String getAttr12() {
		return attr12;
	}


	/**
	 * Sets the attr12.
	 *
	 * @param attr12 the new attr12
	 */
	public void setAttr12(String attr12) {
		this.attr12 = attr12;
	}


	/**
	 * Gets the attr13.
	 *
	 * @return the attr13
	 */
	public String getAttr13() {
		return attr13;
	}


	/**
	 * Sets the attr13.
	 *
	 * @param attr13 the new attr13
	 */
	public void setAttr13(String attr13) {
		this.attr13 = attr13;
	}


	/**
	 * Gets the attr14.
	 *
	 * @return the attr14
	 */
	public String getAttr14() {
		return attr14;
	}


	/**
	 * Sets the attr14.
	 *
	 * @param attr14 the new attr14
	 */
	public void setAttr14(String attr14) {
		this.attr14 = attr14;
	}


	/**
	 * Gets the attr15.
	 *
	 * @return the attr15
	 */
	public String getAttr15() {
		return attr15;
	}


	/**
	 * Sets the attr15.
	 *
	 * @param attr15 the new attr15
	 */
	public void setAttr15(String attr15) {
		this.attr15 = attr15;
	}


	/**
	 * Gets the attr16.
	 *
	 * @return the attr16
	 */
	public String getAttr16() {
		return attr16;
	}


	/**
	 * Sets the attr16.
	 *
	 * @param attr16 the new attr16
	 */
	public void setAttr16(String attr16) {
		this.attr16 = attr16;
	}


	/**
	 * Gets the attr17.
	 *
	 * @return the attr17
	 */
	public String getAttr17() {
		return attr17;
	}


	/**
	 * Sets the attr17.
	 *
	 * @param attr17 the new attr17
	 */
	public void setAttr17(String attr17) {
		this.attr17 = attr17;
	}


	/**
	 * Gets the attr18.
	 *
	 * @return the attr18
	 */
	public String getAttr18() {
		return attr18;
	}


	/**
	 * Sets the attr18.
	 *
	 * @param attr18 the new attr18
	 */
	public void setAttr18(String attr18) {
		this.attr18 = attr18;
	}


	/**
	 * Gets the attr19.
	 *
	 * @return the attr19
	 */
	public String getAttr19() {
		return attr19;
	}


	/**
	 * Sets the attr19.
	 *
	 * @param attr19 the new attr19
	 */
	public void setAttr19(String attr19) {
		this.attr19 = attr19;
	}


	/**
	 * Gets the attr20.
	 *
	 * @return the attr20
	 */
	public String getAttr20() {
		return attr20;
	}


	/**
	 * Sets the attr20.
	 *
	 * @param attr20 the new attr20
	 */
	public void setAttr20(String attr20) {
		this.attr20 = attr20;
	}

	public Set<TCustPrdSalesTeam> getTCustPrdSalesTeams() {
		return tCustPrdSalesTeams;
	}

	public void setTCustPrdSalesTeams(Set<TCustPrdSalesTeam> tCustPrdSalesTeams) {
		this.tCustPrdSalesTeams = tCustPrdSalesTeams;
	}

	@Override
	public String toString() {
		return "TCustSalesTeam [custSalesTeamId=" + custSalesTeamId
				+ ", activeFlag=" + activeFlag + ", createdBy=" + createdBy
				+ ", createDt=" + createDt + ", updatedBy=" + updatedBy
				+ ", updateDt=" + updateDt + ", tenantId=" + tenantId
				+ ", effStartDt=" + effStartDt + ", effEndDt=" + effEndDt
				+ ", attr1=" + attr1 + ", attr2=" + attr2 + ", attr3=" + attr3
				+ ", attr4=" + attr4 + ", attr5=" + attr5 + ", attr6=" + attr6
				+ ", attr7=" + attr7 + ", attr8=" + attr8 + ", attr9=" + attr9
				+ ", attr10=" + attr10 + ", attr11=" + attr11 + ", attr12="
				+ attr12 + ", attr13=" + attr13 + ", attr14=" + attr14
				+ ", attr15=" + attr15 + ", attr16=" + attr16 + ", attr17="
				+ attr17 + ", attr18=" + attr18 + ", attr19=" + attr19
				+ ", attr20=" + attr20 + ", tAlgmntSalesTeam="
				+ tAlgmntSalesTeam + ", tCust=" + tCust + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((custSalesTeamId == null) ? 0 : custSalesTeamId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TCustSalesTeam other = (TCustSalesTeam) obj;
		if (custSalesTeamId == null) {
			if (other.custSalesTeamId != null)
				return false;
		} else if (!custSalesTeamId.equals(other.custSalesTeamId))
			return false;
		return true;
	}

}
