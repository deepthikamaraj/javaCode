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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TAttrDef 
 * The corresponding mapping table is t_attr_def 
 */
@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTAttrDefs", query = "select myTAttrDef from TAttrDef myTAttrDef"),
		@NamedQuery(name = "FindExtendAttrsByTenantId", query = "select myTAttrDef from TAttrDef myTAttrDef where myTAttrDef.tenantId =?1 AND myTAttrDef.activeFlag='Y' AND myTAttrDef.dynAttrFlag='Y' AND myTAttrDef.attrType='M' AND myTAttrDef.cvgFlag='Y' "),
		@NamedQuery(name = "FindAllTAttrDefsByTenantId", query = "select myTAttrDef from TAttrDef myTAttrDef where myTAttrDef.tenantId =?1"),
		@NamedQuery(name = "CountTAttrDefs", query = "Select Count(c) from TAttrDef c"),
		@NamedQuery(name = "FindTAttrDefByAttrName", query = "select myTAttrDef from TAttrDef myTAttrDef where myTAttrDef.attrName like ?1 and myTAttrDef.tenantId =?2 and myTAttrDef.activeFlag='Y' "),
		@NamedQuery(name = "FindTAttrDefByTAttrDataType", query = "select myTAttrDef from TAttrDef myTAttrDef where myTAttrDef.attrDataTypeId = ?1 "),
		@NamedQuery(name = "FindTAttrDefByEntityIdAndTenantId", query = "select myTAttrDef from TAttrDef myTAttrDef where myTAttrDef.tenantId = ?1 and myTAttrDef.entityId = ?2 and myTAttrDef.dynAttrFlag = ?3 and myTAttrDef.elFlag ='Y' "),
		@NamedQuery(name = "CountTAttrDefsByTAttrDataType", query = "select Count(myTAttrDef) from TAttrDef myTAttrDef where myTAttrDef.attrDataTypeId = ?1 "),
		@NamedQuery(name = "FindTAttrDefByattrId", query = "select myTAttrDef from TAttrDef myTAttrDef where myTAttrDef.attrId = ?1 and myTAttrDef.entityId =?2 "),
		@NamedQuery(name = "findAttributeAndEntitiesINQuery", query = " select myTBussEntity.entityId, myTBussEntity.entityName, myTAttrDef.attrId,  myTAttrDef.attrName, myTAttrDef.dynAttrFlag, myTBussEntity.refCond " +
																	  " from TAttrDef myTAttrDef, TBussEntity myTBussEntity "+
																	  " where myTAttrDef.entityId = myTBussEntity.entityId "+
																	  " and myTAttrDef.tenantId = myTBussEntity.tenantId "+
																	  " and myTAttrDef.attrId IN :paramList "
				),
		@NamedQuery(name = "GetAttributeInfoByAlBuStBobjName", query = "select atrdef.attrId,atrdef.attrName,atrdef.attrDesc,atrdef.dynAttrFlag,atrgrp.attrGroupId,atrgrp.groupName from TAttrDef atrdef INNER JOIN atrdef.tAttrGroupMapss atrgrpmap INNER JOIN atrgrpmap.tAttrGroup atrgrp INNER JOIN atrgrp.tBussObjTmpl busobjtmpl INNER JOIN busobjtmpl.tAlgmntTmplss algtmpl INNER JOIN algtmpl.tAlgmntSalesTeam alnst INNER JOIN busobjtmpl.tBussObj bussObj where alnst.tAlgmntSalesTeamId.algmntId = ?1 and alnst.tAlgmntSalesTeamId.bussUnitId = ?2 and alnst.tAlgmntSalesTeamId.salesTeamId = ?3 and bussObj.bussObjName = ?4 and atrdef.tenantId= ?5 and atrdef.activeFlag = atrgrpmap.activeFlag and atrdef.activeFlag = atrgrpmap.visibleFlag and atrdef.activeFlag = atrgrp.activeFlag and atrdef.activeFlag = busobjtmpl.activeFlag and atrdef.activeFlag = algtmpl.activeFlag and atrdef.activeFlag = alnst.activeFlag  and atrdef.activeFlag = 'Y' and atrgrp.defFlag = 'N'"),
		@NamedQuery(name = "GetAttributesByAlBuStBobjName", query = "select atrdef.attrId,atrdef.attrName,atrdef.attrDesc,atrdef.dynAttrFlag,atrgrp.attrGroupId,atrgrp.groupName from TAttrDef atrdef INNER JOIN atrdef.tAttrGroupMapss atrgrpmap INNER JOIN atrgrpmap.tAttrGroup atrgrp INNER JOIN atrgrp.tBussObjTmpl busobjtmpl INNER JOIN busobjtmpl.tAlgmntTmplss algtmpl INNER JOIN algtmpl.tAlgmntSalesTeam alnst INNER JOIN busobjtmpl.tBussObj bussObj where alnst.tAlgmntSalesTeamId.algmntId = ?1 and alnst.tAlgmntSalesTeamId.bussUnitId = ?2 and alnst.tAlgmntSalesTeamId.salesTeamId = ?3 and bussObj.bussObjName = ?4 and atrdef.tenantId= ?5 and atrdef.activeFlag = atrgrpmap.activeFlag and atrdef.activeFlag = atrgrpmap.visibleFlag and atrdef.activeFlag = atrgrp.activeFlag and atrdef.activeFlag = busobjtmpl.activeFlag and atrdef.activeFlag = algtmpl.activeFlag and atrdef.activeFlag = alnst.activeFlag  and atrdef.activeFlag = 'Y' group by atrdef.attrId "),
		@NamedQuery(name = "GetActiveAttributesByAlBuStBobjName", query = "select atrdef.attrId,atrdef.attrName,atrdef.attrDesc,atrdef.dynAttrFlag,atrgrp.attrGroupId,atrgrp.groupName from TAttrDef atrdef INNER JOIN atrdef.tAttrGroupMapss atrgrpmap INNER JOIN atrgrpmap.tAttrGroup atrgrp INNER JOIN atrgrp.tBussObjTmpl busobjtmpl INNER JOIN busobjtmpl.tAlgmntTmplss algtmpl INNER JOIN algtmpl.tAlgmntSalesTeam alnst INNER JOIN busobjtmpl.tBussObj bussObj where alnst.tAlgmntSalesTeamId.algmntId = ?1 and alnst.tAlgmntSalesTeamId.bussUnitId = ?2 and alnst.tAlgmntSalesTeamId.salesTeamId = ?3 and bussObj.bussObjName = ?4 and atrdef.tenantId= ?5 and atrdef.activeFlag = atrgrpmap.activeFlag and atrdef.activeFlag = atrgrp.activeFlag and atrdef.activeFlag = busobjtmpl.activeFlag and atrdef.activeFlag = algtmpl.activeFlag and atrdef.activeFlag = alnst.activeFlag and atrdef.activeFlag = 'Y'"),
		@NamedQuery(name = "GetActiveAttributesByAlBuStEntities", query = "select atrdef.attrId,atrgrpmap.displayName,tbusentity.entityId from TAttrDef atrdef INNER JOIN atrdef.tAttrGroupMapss atrgrpmap INNER JOIN atrgrpmap.tAttrGroup atrgrp INNER JOIN atrgrp.tBussObjTmpl busobjtmpl INNER JOIN busobjtmpl.tAlgmntTmplss algtmpl INNER JOIN algtmpl.tAlgmntSalesTeam alnst INNER JOIN busobjtmpl.tBussObj bussObj INNER JOIN bussObj.tBussEntitiess tbusentity where alnst.tAlgmntSalesTeamId.algmntId = ?1 and alnst.tAlgmntSalesTeamId.bussUnitId = ?2 and alnst.tAlgmntSalesTeamId.salesTeamId = ?3 and tbusentity.entityId IN ?4 and atrdef.tenantId= ?5 and atrdef.tBussEntity.entityId = tbusentity.entityId and atrdef.activeFlag = atrgrpmap.activeFlag and atrdef.activeFlag = atrgrp.activeFlag and atrdef.activeFlag = busobjtmpl.activeFlag and atrdef.activeFlag = algtmpl.activeFlag and atrdef.activeFlag = alnst.activeFlag and atrdef.activeFlag = 'Y' group by atrdef.attrId order by atrgrpmap.displayName"),
		@NamedQuery(name = "GetSearchActiveAttributesByAlBuStBobjName", query = "select atrgrp.attrGroupId,atrgrp.groupName,atrdef.attrId,atrdef.attrName,atrgrpmap.displayName,atrdef.attrDataTypeId,atrdef.attrLen,atrdef.allowedValue,atrgrpmap.editableFlag,atrgrpmap.mandatoryFlag,atrgrpmap.maskValueFlag,atrgrpmap.visibleFlag,atrgrpmap.uniqueFlag,atrgrpmap.orderSeq from TAttrDef atrdef INNER JOIN atrdef.tAttrGroupMapss atrgrpmap INNER JOIN atrgrpmap.tAttrGroup atrgrp INNER JOIN atrgrp.tBussObjTmpl busobjtmpl INNER JOIN busobjtmpl.tAlgmntTmplss algtmpl INNER JOIN algtmpl.tAlgmntSalesTeam alnst INNER JOIN busobjtmpl.tBussObj bussObj INNER JOIN bussObj.tBussEntitiess tbusentity where alnst.tAlgmntSalesTeamId.algmntId = ?1 and alnst.tAlgmntSalesTeamId.bussUnitId = ?2 and alnst.tAlgmntSalesTeamId.salesTeamId = ?3 and bussObj.bussObjName = ?4 and atrdef.tenantId= ?5 and atrdef.tBussEntity.entityId = tbusentity.entityId and atrdef.activeFlag = atrgrpmap.activeFlag and atrdef.activeFlag = atrgrp.activeFlag and atrdef.activeFlag = busobjtmpl.activeFlag and atrdef.activeFlag = algtmpl.activeFlag and atrdef.activeFlag = alnst.activeFlag and atrdef.activeFlag = 'Y' and atrgrpmap.srchFlag = 'Y' and atrgrp.defFlag = 'N' order by atrgrp.groupName,atrdef.attrName"),
		@NamedQuery(name = "GetSearchAttributeRulessByAlBuStBobjName", query = "select atrdef.attrId,rules.ruleId,rules.minValue,rules.maxValue,rules.ruleExpr,valMsgs.errMsg from TAttrDef atrdef INNER JOIN atrdef.tAttrRuless rules INNER JOIN rules.tValMsgss valMsgs INNER JOIN atrdef.tAttrGroupMapss atrgrpmap INNER JOIN atrgrpmap.tAttrGroup atrgrp INNER JOIN atrgrp.tBussObjTmpl busobjtmpl INNER JOIN busobjtmpl.tAlgmntTmplss algtmpl INNER JOIN algtmpl.tAlgmntSalesTeam alnst INNER JOIN busobjtmpl.tBussObj bussObj INNER JOIN bussObj.tBussEntitiess tbusentity where alnst.tAlgmntSalesTeamId.algmntId = ?1 and alnst.tAlgmntSalesTeamId.bussUnitId = ?2 and alnst.tAlgmntSalesTeamId.salesTeamId = ?3 and bussObj.bussObjName = ?4 and atrdef.tenantId= ?5 and atrdef.tBussEntity.entityId = tbusentity.entityId and atrdef.activeFlag = atrgrpmap.activeFlag and atrdef.activeFlag = atrgrp.activeFlag and atrdef.activeFlag = busobjtmpl.activeFlag and atrdef.activeFlag = algtmpl.activeFlag and atrdef.activeFlag = alnst.activeFlag and atrdef.activeFlag = rules.activeFlag and atrdef.activeFlag = 'Y' and atrgrpmap.srchFlag = 'Y' and atrgrp.defFlag = 'N' order by atrgrp.groupName,atrdef.attrName"),
		@NamedQuery(name = "GetSearchActiveAttributesByAlBuStTmpl", query = "select atrgrp.attrGroupId,atrgrp.groupName,atrdef.attrId,atrdef.attrName,atrgrpmap.displayName,atrdef.attrDataTypeId,atrdef.attrLen,atrdef.allowedValue,atrgrpmap.editableFlag,atrgrpmap.mandatoryFlag,atrgrpmap.maskValueFlag,atrgrpmap.visibleFlag,atrgrpmap.uniqueFlag,atrgrpmap.orderSeq from TAttrDef atrdef INNER JOIN atrdef.tAttrGroupMapss atrgrpmap INNER JOIN atrgrpmap.tAttrGroup atrgrp INNER JOIN atrgrp.tBussObjTmpl busobjtmpl INNER JOIN busobjtmpl.tBussObj bussObj INNER JOIN bussObj.tBussEntitiess tbusentity where busobjtmpl.tmplId = ?1 and atrdef.tenantId= ?2 and atrdef.tBussEntity.entityId = tbusentity.entityId and atrdef.activeFlag = atrgrpmap.activeFlag and atrdef.activeFlag = atrgrp.activeFlag and atrdef.activeFlag = busobjtmpl.activeFlag and atrdef.activeFlag = 'Y' and atrgrpmap.srchFlag = 'Y' and atrgrp.defFlag = 'N' and atrgrpmap.visibleFlag = 'Y' order by atrgrp.groupName,atrdef.attrName"),
		@NamedQuery(name = "GetSearchAttributeRulessByAlBuStTmpl", query = "select atrdef.attrId,rules.ruleId,rules.minValue,rules.maxValue,rules.ruleExpr,valMsgs.errMsg from TAttrDef atrdef INNER JOIN atrdef.tAttrRuless rules INNER JOIN rules.tValMsgss valMsgs INNER JOIN atrdef.tAttrGroupMapss atrgrpmap INNER JOIN atrgrpmap.tAttrGroup atrgrp INNER JOIN atrgrp.tBussObjTmpl busobjtmpl INNER JOIN busobjtmpl.tBussObj bussObj INNER JOIN bussObj.tBussEntitiess tbusentity where busobjtmpl.tmplId = ?1 and atrdef.tenantId= ?2 and atrdef.tBussEntity.entityId = tbusentity.entityId and atrdef.activeFlag = atrgrpmap.activeFlag and atrdef.activeFlag = atrgrp.activeFlag and atrdef.activeFlag = busobjtmpl.activeFlag and atrdef.activeFlag = rules.activeFlag and atrdef.activeFlag = 'Y' and atrgrpmap.srchFlag = 'Y' and atrgrp.defFlag = 'N' and atrgrpmap.visibleFlag = 'Y' order by atrgrp.groupName,atrdef.attrName"),
		@NamedQuery(name = "GetActiveAttributesByAlBuStTmpl", query = "select atrgrp.attrGroupId,atrgrp.groupName,atrdef.attrId,atrdef.attrDesc,atrgrpmap.displayName,atrdef.attrDataTypeId,atrdef.attrLen,atrdef.allowedValue,atrgrpmap.editableFlag,atrgrpmap.mandatoryFlag,atrgrpmap.maskValueFlag,atrgrpmap.visibleFlag,atrgrpmap.uniqueFlag,atrgrpmap.orderSeq from TAttrDef atrdef INNER JOIN atrdef.tAttrGroupMapss atrgrpmap INNER JOIN atrgrpmap.tAttrGroup atrgrp INNER JOIN atrgrp.tBussObjTmpl busobjtmpl INNER JOIN busobjtmpl.tBussObj bussObj INNER JOIN bussObj.tBussEntitiess tbusentity where busobjtmpl.tmplId = ?1 and atrdef.tenantId= ?2 and atrdef.tBussEntity.entityId = tbusentity.entityId and atrdef.activeFlag = atrgrpmap.activeFlag and atrdef.activeFlag = atrgrp.activeFlag and atrdef.activeFlag = busobjtmpl.activeFlag and atrdef.activeFlag = 'Y' and atrgrp.defFlag = 'N' and atrgrpmap.visibleFlag = 'Y' order by atrgrp.groupName,atrdef.attrDesc"),
		@NamedQuery(name = "GetAttributeRulessByAlBuStTmpl", query = "select atrdef.attrId,rules.ruleId,rules.minValue,rules.maxValue,rules.ruleExpr,valMsgs.errMsg from TAttrDef atrdef INNER JOIN atrdef.tAttrRuless rules INNER JOIN rules.tValMsgss valMsgs INNER JOIN atrdef.tAttrGroupMapss atrgrpmap INNER JOIN atrgrpmap.tAttrGroup atrgrp INNER JOIN atrgrp.tBussObjTmpl busobjtmpl INNER JOIN busobjtmpl.tBussObj bussObj INNER JOIN bussObj.tBussEntitiess tbusentity where busobjtmpl.tmplId = ?1 and atrdef.tenantId= ?2 and atrdef.tBussEntity.entityId = tbusentity.entityId and atrdef.activeFlag = atrgrpmap.activeFlag and atrdef.activeFlag = atrgrp.activeFlag and atrdef.activeFlag = busobjtmpl.activeFlag and atrdef.activeFlag = rules.activeFlag and atrdef.activeFlag = 'Y' and atrgrp.defFlag = 'N' and atrgrpmap.visibleFlag = 'Y' order by atrgrp.groupName,atrdef.attrName"),
})
@Table(name = "t_attr_def", uniqueConstraints = @UniqueConstraint(columnNames = { "attr_id" }))
	//default serial version id, required for serializable classes.
public class TAttrDef implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "attr_id", nullable = false, length = 255)
	private Long attrId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "attr_name", nullable = true, length = 100)
	private String attrName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "attr_desc", nullable = true, length = 200)
	private String attrDesc;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "attr_type", nullable = true, length = 1)
	private Character attrType;

	
	/**
	 * 
	 * @generated
	 */
	@Length(max = 12000)
	@Column(name = "allowed_value", nullable = true, length = 12000)
	private String allowedValue;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "group_attr_flag", nullable = true, length = 1)
	private Character groupAttrFlag;

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
	@Column(name = "dyn_attr_flag", nullable = true, length = 1)
	private Character dynAttrFlag;

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
	@Length(max = 150)
	@Column(name = "display_name", nullable = true, length = 150)
	private String displayName;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "entity_id", nullable = true, length = 255)
	private Integer entityId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "attr_len", nullable = true, length = 255)
	private Short attrLen;

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
	@Column(name = "mtr_flag", nullable = true, length = 1)
	private Character mtrFlag;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "cvg_flag", nullable = true, length = 1)
	private Character cvgFlag;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "el_flag", nullable = true, length = 1)
	private Character elFlag;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "attr_data_type_id", nullable = true, length = 1)
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Integer attrDataTypeId;
	
	/*@ManyToOne
	@JoinColumn(name = "attr_data_type_id", referencedColumnName = "attr_data_type_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TAttrDataType tAttrDataType;*/

	public Integer getAttrDataTypeId() {
		return attrDataTypeId;
	}

	public void setAttrDataTypeId(Integer attrDataTypeId) {
		this.attrDataTypeId = attrDataTypeId;
	}

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAttrDef")
	@NotAudited
	private Set<TCustAttr> tCustAttrss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAttrDef")
	@Fetch(FetchMode.SUBSELECT)
	@Audited
	private Set<TAttrRule> tAttrRuless;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAttrDef")
	@NotAudited
	private Set<TPrdAttr> tPrdAttrss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAttrDef")
	@Fetch(FetchMode.SUBSELECT)
	@NotAudited
	private Set<TAttrGroupMap> tAttrGroupMapss;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "entity_id", referencedColumnName = "entity_id", nullable = false, insertable = false, updatable = false)
	@NotAudited
	private TBussEntity tBussEntity;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAttrDef")
	@NotAudited
	private Set<TMtrAttr> tMtrAttrss;
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAttrDef")
	@NotAudited
	private Set<TEmpAttr> tEmpAttrss;
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAttrDef")
	@NotAudited
	private Set<TSalesPosAttr> tSalesPosAttrss;
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAttrDef")
	@NotAudited
	private Set<TEmpAlgmntAttr> tEmpAlgmntAttrss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAttrDef")
	@NotAudited
	private Set<TCustAlgmntAttr> tCustAlgmntAttrss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAttrDef")
	@NotAudited
	private Set<TPrdAlgmntAttr> tPrdAlgmntAttrss;
	

	public Set<TEmpAttr> gettEmpAttrss() {
		return tEmpAttrss;
	}

	public void settEmpAttrss(Set<TEmpAttr> tEmpAttrss) {
		this.tEmpAttrss = tEmpAttrss;
	}

	/**
	 *
	 * @generated
	 */
	public TAttrDef() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttrId(final Long attrId) {
		this.attrId = attrId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getAttrId() {
		return this.attrId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttrName(final String attrName) {
		this.attrName = attrName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttrName() {
		return this.attrName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttrDesc(final String attrDesc) {
		this.attrDesc = attrDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttrDesc() {
		return this.attrDesc;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttrType(final Character attrType) {
		this.attrType = attrType;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getAttrType() {
		return this.attrType;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAllowedValue(final String allowedValue) {
		this.allowedValue = allowedValue;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAllowedValue() {
		return this.allowedValue;
	}

	/**
	 * 
	 * @generated
	 */

	public void setGroupAttrFlag(final Character groupAttrFlag) {
		this.groupAttrFlag = groupAttrFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getGroupAttrFlag() {
		return this.groupAttrFlag;
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

	public void setDynAttrFlag(final Character dynAttrFlag) {
		this.dynAttrFlag = dynAttrFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getDynAttrFlag() {
		return this.dynAttrFlag;
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

	public void setDisplayName(final String displayName) {
		this.displayName = displayName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getDisplayName() {
		return this.displayName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setEntityId(final Integer entityId) {
		this.entityId = entityId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getEntityId() {
		return this.entityId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttrLen(final Short attrLen) {
		this.attrLen = attrLen;
	}

	/**
	 * 
	 * @generated
	 */
	public Short getAttrLen() {
		return this.attrLen;
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

	public void setMtrFlag(final Character mtrFlag) {
		this.mtrFlag = mtrFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getMtrFlag() {
		return this.mtrFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCvgFlag(final Character cvgFlag) {
		this.cvgFlag = cvgFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getCvgFlag() {
		return this.cvgFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public void setElFlag(final Character elFlag) {
		this.elFlag = elFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getElFlag() {
		return this.elFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public Set<TMtrAttr> getTMtrAttrss() {
		return this.tMtrAttrss;
	}
	/*public TAttrDataType getTAttrDataType() {
		return this.tAttrDataType;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTAttrDataType(final TAttrDataType tAttrDataType) {
		this.tAttrDataType = tAttrDataType;

	}*/

	/**
	 * 
	 * @generated
	 */
	public Set<TCustAttr> getTCustAttrss() {
		return this.tCustAttrss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCustAttrss(final Set<TCustAttr> tCustAttrss) {
		this.tCustAttrss = tCustAttrss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TAttrRule> getTAttrRuless() {
		return this.tAttrRuless;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAttrRuless(final Set<TAttrRule> tAttrRuless) {
		this.tAttrRuless = tAttrRuless;
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

	/**
	 * 
	 * @generated
	 */
	public Set<TAttrGroupMap> getTAttrGroupMapss() {
		return this.tAttrGroupMapss;
	}
	public void setTMtrAttrss(final Set<TMtrAttr> tMtrAttrss) {
		this.tMtrAttrss = tMtrAttrss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAttrGroupMapss(final Set<TAttrGroupMap> tAttrGroupMapss) {
		this.tAttrGroupMapss = tAttrGroupMapss;
	}
	/**
	 * 
	 * @generated
	 */
	public Set<TSalesPosAttr> getTSalesPosAttrss() {
		return this.tSalesPosAttrss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTSalesPosAttrss(final Set<TSalesPosAttr> tSalesPosAttrss) {
		this.tSalesPosAttrss = tSalesPosAttrss;
	}
	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	/**
	 * 
	 * @generated
	 */
	public Set<TEmpAlgmntAttr> getTEmpAlgmntAttrss() {
		return this.tEmpAlgmntAttrss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTEmpAlgmntAttrss(final Set<TEmpAlgmntAttr> tEmpAlgmntAttrss) {
		this.tEmpAlgmntAttrss = tEmpAlgmntAttrss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TCustAlgmntAttr> getTCustAlgmntAttrss() {
		return this.tCustAlgmntAttrss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCustAlgmntAttrss(final Set<TCustAlgmntAttr> tCustAlgmntAttrss) {
		this.tCustAlgmntAttrss = tCustAlgmntAttrss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TPrdAlgmntAttr> getTPrdAlgmntAttrss() {
		return this.tPrdAlgmntAttrss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPrdAlgmntAttrss(final Set<TPrdAlgmntAttr> tPrdAlgmntAttrss) {
		this.tPrdAlgmntAttrss = tPrdAlgmntAttrss;
	}
	public void copy(final TAttrDef that) {
		setAttrId(that.getAttrId());
		setAttrName(that.getAttrName());
		setAttrDesc(that.getAttrDesc());
		setAttrType(that.getAttrType());
		setAllowedValue(that.getAllowedValue());
		setGroupAttrFlag(that.getGroupAttrFlag());
		setEffStartDt(that.getEffStartDt());
		setEffEndDt(that.getEffEndDt());
		setDynAttrFlag(that.getDynAttrFlag());
		setActiveFlag(that.getActiveFlag());
		setDisplayName(that.getDisplayName());
		setEntityId(that.getEntityId());
		setAttrLen(that.getAttrLen());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setMtrFlag(that.getMtrFlag());
		setCvgFlag(that.getCvgFlag());
		setElFlag(that.getElFlag());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((attrId == null) ? 0 : attrId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("attrId=[").append(attrId).append("] ");
		buffer.append("attrName=[").append(attrName).append("] ");
		buffer.append("attrDesc=[").append(attrDesc).append("] ");
		buffer.append("attrType=[").append(attrType).append("] ");
		buffer.append("allowedValue=[").append(allowedValue).append("] ");
		buffer.append("groupAttrFlag=[").append(groupAttrFlag).append("] ");
		buffer.append("effStartDt=[").append(effStartDt).append("] ");
		buffer.append("effEndDt=[").append(effEndDt).append("] ");
		buffer.append("dynAttrFlag=[").append(dynAttrFlag).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("displayName=[").append(displayName).append("] ");
		buffer.append("entityId=[").append(entityId).append("] ");
		buffer.append("attrLen=[").append(attrLen).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("mtrFlag=[").append(mtrFlag).append("] ");
		buffer.append("cvgFlag=[").append(cvgFlag).append("] ");
		buffer.append("elFlag=[").append(elFlag).append("] ");

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
		final TAttrDef other = (TAttrDef) obj;
		if (attrId == null) {
			if (other.attrId != null) {
				return false;
			}
		} else if (!attrId.equals(other.attrId)) {
			return false;
		}
		return true;
	}

	public TBussEntity gettBussEntity() {
		return tBussEntity;
	}

	public void settBussEntity(TBussEntity tBussEntity) {
		this.tBussEntity = tBussEntity;
	}
}
