package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;



/** 
 * JPA class representing TCustAttr 
 * The corresponding mapping table is t_cust_attr 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTCustAttrs", query = "select myTCustAttr from TCustAttr myTCustAttr"),
		@NamedQuery(name = "FindAllTCustAttrsByCustId", query = "select myTCustAttr from TCustAttr myTCustAttr, TCust myTCust where myTCustAttr.tCustAttrId.custId = myTCust.custId and myTCust.custId = ?1 "),
		@NamedQuery(name = "CountTCustAttrs", query = "Select Count(c) from TCustAttr c"),
		@NamedQuery(name = "FindTCustAttrByTCust", query = "select myTCustAttr from TCustAttr myTCustAttr where myTCustAttr.tCust = ?1 "),
		@NamedQuery(name = "CountTCustAttrsByTCust", query = "select Count(*) from TCustAttr myTCustAttr where myTCustAttr.tCust = ?1 "),
		@NamedQuery(name = "FindTCustAttrByTAttrDef", query = "select myTCustAttr from TCustAttr myTCustAttr where myTCustAttr.tAttrDef = ?1 "),
		
		@NamedQuery(name = "FindTCustAttrByTAttGrpMap", query = "select myTAttrGroupMap.displayName, myTCustAttr.attrValue from TAlgmntTmpl myTAlgmntTmpl, TAttrGroup myTAttrGroup, TAttrGroupMap myTAttrGroupMap, TCustAttr myTCustAttr,TBussObjTmpl myTBussObjTmpl,TBussObj myTBussObj where myTBussObj.bussObjName = ?1 AND myTBussObj.bussObjId = myTBussObjTmpl.tBussObj.bussObjId AND myTBussObj.tenantId = myTBussObjTmpl.tenantId AND myTBussObjTmpl.defFlag ='N' AND myTBussObjTmpl.activeFlag = 'Y' AND myTBussObjTmpl.tmplId = myTAlgmntTmpl.tBussObjTmpl.tmplId " +
							"AND myTBussObjTmpl.tenantId = myTAlgmntTmpl.tenantId AND myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId =?2 AND myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId =?3 AND myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?4 AND myTAlgmntTmpl.activeFlag = 'Y' AND myTAlgmntTmpl.tBussObjTmpl.tmplId = myTAttrGroup.tBussObjTmpl.tmplId AND myTAlgmntTmpl.tenantId = myTAttrGroup.tenantId " +
							"AND myTAttrGroup.defFlag ='N' AND myTAttrGroup.activeFlag = 'Y' AND myTAttrGroup.attrGroupId = myTAttrGroupMap.tAttrGroupMapId.attrGroupId AND myTAttrGroup.tenantId = myTAttrGroupMap.tenantId AND myTAttrGroupMap.activeFlag = 'Y' AND myTAttrGroup.tenantId = myTCustAttr.tenantId AND myTAttrGroupMap.tAttrGroupMapId.attrId = myTCustAttr.tAttrDef.attrId AND myTAttrGroupMap.tenantId = myTCustAttr.tenantId AND myTCustAttr.tCustAttrId.custId= ?5 AND myTCustAttr.tenantId=?6 "),
		
		@NamedQuery(name = "GetTCustAttrById", query = "select myTCustAttr from  TCustAttr myTCustAttr where myTCustAttr.tCustAttrId.custId=?1 AND myTCustAttr.tenantId=?2"),
		@NamedQuery(name = "CountTCustAttrsByTAttrDef", query = "select Count(*) from TCustAttr myTCustAttr where myTCustAttr.tAttrDef = ?1 "),
		@NamedQuery(name = "GetTCustAttrByAttrId", query = "select myTCustAttr from  TCustAttr myTCustAttr where myTCustAttr.tAttrDef.attrId=?1 AND myTCustAttr.attrValue=?2 AND myTCustAttr.tenantId=?3 AND myTCustAttr.tCustAttrId.custId <> ?4" ),
		@NamedQuery(name = "GetTCustAttrByAttrIdList", query = "select myTCustAttr from  TCustAttr myTCustAttr where myTCustAttr.tCustAttrId.custId IN ?1 AND myTCustAttr.tenantId=?2"),
		@NamedQuery(name = "GetTCustAttrByAttrIdValCust", query = "select myTCustAttr.tCustAttrId.custId from  TCustAttr myTCustAttr where myTCustAttr.tCustAttrId.custId IN ?1 AND myTCustAttr.tAttrDef.attrId=?2 AND myTCustAttr.attrValue like ?3"),
		
		//@NamedQuery(name = "FindTCustAttrName", query = "select DISTINCT myTAttrGroupMap.displayName from TAlgmntTmpl myTAlgmntTmpl, TAttrGroup myTAttrGroup, TAttrGroupMap myTAttrGroupMap, TCustAttr myTCustAttr where myTAlgmntTmpl.activeFlag = 'Y' and myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId =?1 AND myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId =?2 AND myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 AND myTAlgmntTmpl.tBussObjTmpl.tmplId = myTAttrGroup.tBussObjTmpl.tmplId AND myTAttrGroup.attrGroupId = myTAttrGroupMap.tAttrGroupMapId.attrGroupId AND myTAttrGroupMap.tAttrGroupMapId.attrId = myTCustAttr.tCustAttrId.attrId  AND myTCustAttr.tenantId=?4 AND myTCustAttr.activeFlag = 'Y'"),
		
		@NamedQuery(name = "FindTCustAttrName", query = " SELECT DISTINCT myTAttrGroupMap.displayName " +
				"FROM TAlgmntTmpl myTAlgmntTmpl, TAttrGroup myTAttrGroup, TAttrGroupMap myTAttrGroupMap,TBussObjTmpl myTBussObjTmpl,TBussObj myTBussObj " +
				"WHERE myTBussObj.bussObjName = ?1 " +
				"AND myTBussObj.bussObjId = myTBussObjTmpl.tBussObj.bussObjId " +
				"AND myTBussObj.tenantId = myTBussObjTmpl.tenantId " +
				"AND myTBussObjTmpl.defFlag ='N' " +
				"AND myTBussObjTmpl.activeFlag = 'Y' " +
				"AND myTBussObjTmpl.tmplId = myTAlgmntTmpl.tBussObjTmpl.tmplId " +
				"AND myTBussObjTmpl.tenantId = myTAlgmntTmpl.tenantId " +
				"AND myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId =?2 " +
				"AND myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId =?3 " +
				"AND myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?4 " +
				"AND myTAlgmntTmpl.activeFlag = 'Y' " +
				"AND myTAlgmntTmpl.tBussObjTmpl.tmplId = myTAttrGroup.tBussObjTmpl.tmplId " +
				"AND myTAlgmntTmpl.tenantId = myTAttrGroup.tenantId " +
				"AND myTAttrGroup.defFlag ='N' " +
				"AND myTAttrGroup.activeFlag = 'Y' " +
				"AND myTAttrGroup.attrGroupId = myTAttrGroupMap.tAttrGroupMapId.attrGroupId " +
				"AND myTAttrGroup.tenantId = myTAttrGroupMap.tenantId " +
				"AND myTAttrGroupMap.activeFlag = 'Y' " +
				"AND myTAlgmntTmpl.tenantId = ?5 "),
		
		
		
		@NamedQuery(name = "FindAllAttrsForCust", query = "select myTCustAttr from  TCustAttr myTCustAttr where " +
				"myTCustAttr.tCustAttrId.custId =?1 AND myTCustAttr.tCustAttrId.attrId IN ?2 AND myTCustAttr.activeFlag = 'Y' "),
		@NamedQuery(name = "FindCustAttrsForCust", query = "select myTCustAttr.tCustAttrId.custId, myTCustAttr.tCustAttrId.attrId, myTCustAttr.attrValue" +
				" from TCustAttr myTCustAttr where " +
				" myTCustAttr.tCustAttrId.custId = ?1 AND myTCustAttr.tCustAttrId.attrId IN ?2 " +
				" AND myTCustAttr.activeFlag = ?3 AND myTCustAttr.tenantId = ?4 "),
		@NamedQuery(name = "FindAllAttrsForAllCust", query = "select myTCustAttr from  TCustAttr myTCustAttr where " +
				"myTCustAttr.tCustAttrId.custId IN ?1 AND myTCustAttr.tCustAttrId.attrId IN ?2 AND " +
				"myTCustAttr.tenantId = ?3"),
				/*
				@NamedQuery(name = "FindTCustAttrByTAttGrpMapAndCustID", query = "select  myTCustAttr.tCustAttrId.custId, DISTINCT myTAttrGroupMap.displayName, myTCustAttr.attrValue from " +
						"TCust myTCust , TAlgmntTmpl myTAlgmntTmpl, TAttrGroup myTAttrGroup, TAttrGroupMap myTAttrGroupMap, TCustAttr myTCustAttr" +
						" where  myTCustAttr.tCustAttrId.custId IN ?1 " + 
						"AND myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId =?2 " +
						"AND myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId =?3 " +
						"AND myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?4 " +
						"AND myTAlgmntTmpl.tBussObjTmpl.tmplId = myTAttrGroup.tBussObjTmpl.tmplId " +
						"AND myTAttrGroup.attrGroupId = myTAttrGroupMap.tAttrGroupMapId.attrGroupId " +
						"AND myTAttrGroupMap.tAttrGroupMapId.attrId = myTCustAttr.tCustAttrId.attrId " +
						"AND myTCustAttr.tenantId=?5 " +
						"AND myTCustAttr.activeFlag = 'Y' " +
						"GROUP BY myTAttrGroupMap.displayName,myTCustAttr.tCust.custId,myTCustAttr.attrValue " +
						"ORDER BY myTCustAttr.tCust.custId ")	,*/
				@NamedQuery(name = "FindTCustAttrByTAttGrpMapAndCustID", query = "select  myTCustAttr.tCustAttrId.custId,myAttrDef.displayName, myTCustAttr.attrValue from " +
						"TCustAttr myTCustAttr LEFT JOIN myTCustAttr.tAttrDef myAttrDef" +
						" where  myTCustAttr.tCustAttrId.custId IN ?1 " + 
						"AND myTCustAttr.tenantId=?2 " +
						"AND myTCustAttr.activeFlag = 'Y' " +						
						"GROUP BY myAttrDef.displayName,myTCustAttr.tCust.custId,myTCustAttr.attrValue " +
						"ORDER BY myTCustAttr.tCust.custId "),
				@NamedQuery(name = "FindAllExtAttrsForAllCust", query = "select myTCustAttr from  TCustAttr myTCustAttr where " +
						"myTCustAttr.tCustAttrId.custId IN ?1  AND myTCustAttr.tenantId = ?2"),
	})
@Table(name = "t_cust_attr")
public class TCustAttr implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TCustAttrId tCustAttrId;

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
	@Length(max = 3000)
	@Column(name = "attr_value", nullable = true, length = 3000)
	private String attrValue;

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
	@JoinColumn(name = "cust_id", referencedColumnName = "cust_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TCust tCust;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "attr_id", referencedColumnName = "attr_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TAttrDef tAttrDef;

	/**
	 *
	 * @generated
	 */
	/*public TCustAttr() {
	}*/

	/**
	 * 
	 * @generated
	 */

	public void setTCustAttrId(final TCustAttrId tCustAttrId) {
		this.tCustAttrId = tCustAttrId;
	}

	/**
	 * 
	 * @generated
	 */
	public TCustAttrId getTCustAttrId() {
		return this.tCustAttrId;
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

	public void setAttrValue(final String attrValue) {
		this.attrValue = attrValue;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttrValue() {
		return this.attrValue;
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
			this.createDt = x;
			
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
	public TCust getTCust() {
		return this.tCust;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCust(final TCust tCust) {
		this.tCust = tCust;
		if (this.tCust != null && this.tCust.getCustId() != null) {

			this.tCustAttrId.setCustId(this.tCust.getCustId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	public TAttrDef getTAttrDef() {
		return this.tAttrDef;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAttrDef(final TAttrDef tAttrDef) {
		this.tAttrDef = tAttrDef;
		if (this.tAttrDef != null && this.tAttrDef.getAttrId() != null) {

			this.tCustAttrId.setAttrId(this.tAttrDef.getAttrId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCustAttr that) {
		setTCustAttrId(that.getTCustAttrId());
		setActiveFlag(that.getActiveFlag());
		setAttrValue(that.getAttrValue());
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
		result = prime * result + ((tCustAttrId == null) ? 0 : tCustAttrId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tCustAttrId=[").append(tCustAttrId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("attrValue=[").append(attrValue).append("] ");
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
		final TCustAttr other = (TCustAttr) obj;
		if (tCustAttrId == null) {
			if (other.tCustAttrId != null) {
				return false;
			}
		} else if (!tCustAttrId.equals(other.tCustAttrId)) {
			return false;
		}
		return true;
	}
}
