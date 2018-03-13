package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.Length;
@Entity
@Audited
@NamedQueries({
	@NamedQuery(name = "FindAllTCustPrdSalesTeams", query = "select myTCustPrdSalesTeam from TCustPrdSalesTeam myTCustPrdSalesTeam"),
	@NamedQuery(name = "FindTCustPrdSalesTeamsByCustIdALBuStId", query = "select myTCustPrdSalesTeam from TCustPrdSalesTeam myTCustPrdSalesTeam"
				+ " where myTCustPrdSalesTeam.tCust.custId = ?1 and myTCustPrdSalesTeam.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?2 and myTCustPrdSalesTeam.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?3 and myTCustPrdSalesTeam.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?4 and myTCustPrdSalesTeam.tenantId = ?5 "),
	@NamedQuery(name = "FindTCustPrdSalesTeamsByCustIdALBuStIdPrdId", query = "select myTCustPrdSalesTeam from TCustPrdSalesTeam myTCustPrdSalesTeam"
				+ " where myTCustPrdSalesTeam.tCust.custId = ?1 and myTCustPrdSalesTeam.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?2 and myTCustPrdSalesTeam.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?3 and myTCustPrdSalesTeam.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?4 and myTCustPrdSalesTeam.tPrd.prdId IN ?5 and myTCustPrdSalesTeam.tenantId = ?6 "),
	@NamedQuery(name = "UpdateTCustPrdSalesTeam", query = "Update TCustPrdSalesTeam myTCustPrdSalesTeam set myTCustPrdSalesTeam.attr4 = ?1 ,myTCustPrdSalesTeam.updateDt= ?2, myTCustPrdSalesTeam.updatedBy = ?3 where myTCustPrdSalesTeam.tCust.custId = ?4 and myTCustPrdSalesTeam.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?5 and " +
			"myTCustPrdSalesTeam.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?6 and myTCustPrdSalesTeam.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?7"),
})
@Table(name = "t_cust_prd_sales_team", uniqueConstraints = @UniqueConstraint(columnNames = { "cust_prd_sales_team_id" }))
public class TCustPrdSalesTeam implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The cust sales team id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cust_prd_sales_team_id", nullable = false, length = 255)
	private Long custPrdSalesTeamId;
	
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
	
	/** The eff start dt. */
	@Column(name = "eff_start_dt", nullable = true, length = 19)
	private Date effStartDt;
	
	/** The eff end dt. */
	@Column(name = "eff_end_dt", nullable = true, length = 19)
	private Date effEndDt;
	
	@Column(name = "comp_elg_flag", nullable = true, length = 1)
	private Character compElgFlag;
	
	/** The t algmnt sales team. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "algmnt_id", referencedColumnName = "algmnt_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "buss_unit_id", referencedColumnName = "buss_unit_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "sales_team_id", referencedColumnName = "sales_team_id", nullable = false, insertable = true, updatable = true) })
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TAlgmntSalesTeam tAlgmntSalesTeam;
	       
	/** The t cust. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "prd_id", referencedColumnName = "prd_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TPrd tPrd;
	
	/** The t cust. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "cust_id", referencedColumnName = "cust_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TCust tCust;
	
	/** The t cust. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "prd_sales_team_id", referencedColumnName = "prd_sales_team_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TPrdSalesTeam tPrdSalesTeam;
	
	/** The t cust. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "cust_sales_team_id", referencedColumnName = "cust_sales_team_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TCustSalesTeam tCustSalesTeam;

	public Long getCustPrdSalesTeamId() {
		return custPrdSalesTeamId;
	}

	public void setCustPrdSalesTeamId(Long custPrdSalesTeamId) {
		this.custPrdSalesTeamId = custPrdSalesTeamId;
	}

	public Character getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Character activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public Short getTenantId() {
		return tenantId;
	}

	public void setTenantId(Short tenantId) {
		this.tenantId = tenantId;
	}

	public String getAttr1() {
		return attr1;
	}

	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}

	public String getAttr2() {
		return attr2;
	}

	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}

	public String getAttr3() {
		return attr3;
	}

	public void setAttr3(String attr3) {
		this.attr3 = attr3;
	}

	public String getAttr4() {
		return attr4;
	}

	public void setAttr4(String attr4) {
		this.attr4 = attr4;
	}

	public String getAttr5() {
		return attr5;
	}

	public void setAttr5(String attr5) {
		this.attr5 = attr5;
	}

	public String getAttr6() {
		return attr6;
	}

	public void setAttr6(String attr6) {
		this.attr6 = attr6;
	}

	public String getAttr7() {
		return attr7;
	}

	public void setAttr7(String attr7) {
		this.attr7 = attr7;
	}

	public String getAttr8() {
		return attr8;
	}

	public void setAttr8(String attr8) {
		this.attr8 = attr8;
	}

	public String getAttr9() {
		return attr9;
	}

	public void setAttr9(String attr9) {
		this.attr9 = attr9;
	}

	public String getAttr10() {
		return attr10;
	}

	public void setAttr10(String attr10) {
		this.attr10 = attr10;
	}

	public String getAttr11() {
		return attr11;
	}

	public void setAttr11(String attr11) {
		this.attr11 = attr11;
	}

	public String getAttr12() {
		return attr12;
	}

	public void setAttr12(String attr12) {
		this.attr12 = attr12;
	}

	public String getAttr13() {
		return attr13;
	}

	public void setAttr13(String attr13) {
		this.attr13 = attr13;
	}

	public String getAttr14() {
		return attr14;
	}

	public void setAttr14(String attr14) {
		this.attr14 = attr14;
	}

	public String getAttr15() {
		return attr15;
	}

	public void setAttr15(String attr15) {
		this.attr15 = attr15;
	}

	public String getAttr16() {
		return attr16;
	}

	public void setAttr16(String attr16) {
		this.attr16 = attr16;
	}

	public String getAttr17() {
		return attr17;
	}

	public void setAttr17(String attr17) {
		this.attr17 = attr17;
	}

	public String getAttr18() {
		return attr18;
	}

	public void setAttr18(String attr18) {
		this.attr18 = attr18;
	}

	public String getAttr19() {
		return attr19;
	}

	public void setAttr19(String attr19) {
		this.attr19 = attr19;
	}

	public String getAttr20() {
		return attr20;
	}

	public void setAttr20(String attr20) {
		this.attr20 = attr20;
	}

	public Date getEffStartDt() {
		return effStartDt;
	}

	public void setEffStartDt(Date effStartDt) {
		this.effStartDt = effStartDt;
	}

	public Date getEffEndDt() {
		return effEndDt;
	}

	public void setEffEndDt(Date effEndDt) {
		this.effEndDt = effEndDt;
	}

	public Character getCompElgFlag() {
		return compElgFlag;
	}

	public void setCompElgFlag(Character compElgFlag) {
		this.compElgFlag = compElgFlag;
	}

	public TAlgmntSalesTeam getTAlgmntSalesTeam() {
		return tAlgmntSalesTeam;
	}

	public void setTAlgmntSalesTeam(TAlgmntSalesTeam tAlgmntSalesTeam) {
		this.tAlgmntSalesTeam = tAlgmntSalesTeam;
	}

	public TPrd getTPrd() {
		return tPrd;
	}

	public void setTPrd(TPrd tPrd) {
		this.tPrd = tPrd;
	}

	public TCust getTCust() {
		return tCust;
	}

	public void setTCust(TCust tCust) {
		this.tCust = tCust;
	}

	public TPrdSalesTeam getTPrdSalesTeam() {
		return tPrdSalesTeam;
	}

	public void setTPrdSalesTeam(TPrdSalesTeam tPrdSalesTeam) {
		this.tPrdSalesTeam = tPrdSalesTeam;
	}

	public TCustSalesTeam getTCustSalesTeam() {
		return tCustSalesTeam;
	}

	public void setTCustSalesTeam(TCustSalesTeam tCustSalesTeam) {
		this.tCustSalesTeam = tCustSalesTeam;
	}

	@Override
	public String toString() {
		return "TCustPrdSalesTeam [custPrdSalesTeamId=" + custPrdSalesTeamId
				+ ", activeFlag=" + activeFlag + ", createdBy=" + createdBy
				+ ", createDt=" + createDt + ", updatedBy=" + updatedBy
				+ ", updateDt=" + updateDt + ", tenantId=" + tenantId
				+ ", attr1=" + attr1 + ", attr2=" + attr2 + ", attr3=" + attr3
				+ ", attr4=" + attr4 + ", attr5=" + attr5 + ", attr6=" + attr6
				+ ", attr7=" + attr7 + ", attr8=" + attr8 + ", attr9=" + attr9
				+ ", attr10=" + attr10 + ", attr11=" + attr11 + ", attr12="
				+ attr12 + ", attr13=" + attr13 + ", attr14=" + attr14
				+ ", attr15=" + attr15 + ", attr16=" + attr16 + ", attr17="
				+ attr17 + ", attr18=" + attr18 + ", attr19=" + attr19
				+ ", attr20=" + attr20 + ", effStartDt=" + effStartDt
				+ ", effEndDt=" + effEndDt + ", tAlgmntSalesTeam="
				+ tAlgmntSalesTeam + ", tPrd=" + tPrd + ", tCust=" + tCust
				+ ", tPrdSalesTeam=" + tPrdSalesTeam + ", tCustSalesTeam="
				+ tCustSalesTeam + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((custPrdSalesTeamId == null) ? 0 : custPrdSalesTeamId
						.hashCode());
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
		TCustPrdSalesTeam other = (TCustPrdSalesTeam) obj;
		if (custPrdSalesTeamId == null) {
			if (other.custPrdSalesTeamId != null)
				return false;
		} else if (!custPrdSalesTeamId.equals(other.custPrdSalesTeamId))
			return false;
		return true;
	}
	
}
