package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * JPA class representing TEmpAttr The corresponding mapping table is t_emp_attr
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTEmpAttrs", query = "select myTEmpAttr from TEmpAttr myTEmpAttr"),
		@NamedQuery(name = "GetTEmpAttrById", query = "select myTEmpAttr from  TEmpAttr myTEmpAttr where myTEmpAttr.tEmpAttrId.staffId=?1 AND myTEmpAttr.tenantId=?2"),
		@NamedQuery(name = "FindTEmpAttrByTAttGrpMap", query = "select myTAttrGroupMap.displayName, myTEmpAttr.attrValue from TAlgmntTmpl myTAlgmntTmpl, TAttrGroup myTAttrGroup, TAttrGroupMap myTAttrGroupMap, TEmpAttr myTEmpAttr where myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId =?1 AND myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId =?2 AND myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 AND myTAlgmntTmpl.tBussObjTmpl.tmplId = myTAttrGroup.tBussObjTmpl.tmplId AND myTAttrGroup.attrGroupId = myTAttrGroupMap.tAttrGroupMapId.attrGroupId AND myTAttrGroupMap.tAttrGroupMapId.attrId = myTEmpAttr.tEmpAttrId.attrId AND myTEmpAttr.tEmpAttrId.staffId= ?4 AND myTEmpAttr.tenantId=?5 "),
		@NamedQuery(name = "CountTEmpAttrs", query = "Select Count(c) from TEmpAttr c"),
		@NamedQuery(name = "GetTEmpAttrByAttrId", query = "select myTEmpAttr from  TEmpAttr myTEmpAttr where myTEmpAttr.tAttrDef.attrId=?1 AND myTEmpAttr.attrValue=?2 AND myTEmpAttr.tenantId=?3 AND myTEmpAttr.tEmpAttrId.staffId <> ?4"),
		//@NamedQuery(name = "FindTEmpAttrByTAttGrpMap", query = "select myTAttrGroupMap.displayName, myTEmpAttr.attrValue from TAlgmntTmpl myTAlgmntTmpl, TAttrGroup myTAttrGroup, TAttrGroupMap myTAttrGroupMap, TEmpAttr myTEmpAttr where myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId =?1 AND myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId =?2 AND myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 AND myTAlgmntTmpl.tBussObjTmpl.tmplId = myTAttrGroup.tBussObjTmpl.tmplId AND myTAttrGroup.attrGroupId = myTAttrGroupMap.tAttrGroupMapId.attrGroupId AND myTAttrGroupMap.tAttrGroupMapId.attrId = myTEmpAttr.tEmpAttrId.attrId AND myTEmpAttr.tEmpAttrId.staffId= ?4 AND myTEmpAttr.tenantId=?5 "),
		
		/*@NamedQuery(name = "FindTEmpAttrByTAttGrpMapDef", query = "select myTAttrGroupMap.displayName from TAlgmntTmpl myTAlgmntTmpl, TAttrGroup myTAttrGroup, TAttrGroupMap myTAttrGroupMap, TEmpAttr myTEmpAttr where myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId =?1 AND myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId =?2 AND myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 AND myTAlgmntTmpl.tBussObjTmpl.tmplId = myTAttrGroup.tBussObjTmpl.tmplId AND myTAttrGroup.attrGroupId = myTAttrGroupMap.tAttrGroupMapId.attrGroupId AND myTAttrGroupMap.tAttrGroupMapId.attrId = myTEmpAttr.tEmpAttrId.attrId AND myTEmpAttr.tenantId=?4 "),*/
		@NamedQuery(name = "FindTEmpAttrByTAttGrpMapDef", query = " SELECT DISTINCT myTAttrGroupMap.displayName " +
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
		
				@NamedQuery(name = "FindTEmpAttrByTAttGrpMapValue", query = "select myTEmpAttr.tEmpAttrId.staffId ,myAttrDef.displayName,myTEmpAttr.attrValue " +
						"from  TEmpAttr myTEmpAttr LEFT JOIN myTEmpAttr.tAttrDef myAttrDef  " +
						"where myTEmpAttr.tEmpAttrId.staffId IN ?1 " +
						"AND myTEmpAttr.tenantId=?2 " +
						"AND myTEmpAttr.activeFlag = 'Y' "+
						"GROUP BY myTEmpAttr.tEmpAttrId.attrId,myAttrDef.displayName,myTEmpAttr.attrValue " +
						"ORDER BY myTEmpAttr.tEmpAttrId.attrId ")
				
		
		
		
		
		
})
@Table(name = "t_emp_attr")
public class TEmpAttr implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TEmpAttrId tEmpAttrId;

	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;
	
	
	@Column(name = "create_dt", nullable = false, length = 19, updatable = false)
	private Date createDt;
	
	
	@Column(name = "attr_value", nullable = true, length = 3000)
	private String attrValue;
	
	
	@Column(name = "updated_by", nullable = true, length = 255)
	private Integer updatedBy;
	
	@NotNull
	@Column(name = "active_flag", nullable = false, length = 1)
	private Character activeFlag;

	
	@Column(name = "update_dt", nullable = true, length = 19)
	private Date updateDt;

	
	@Column(name = "created_by", nullable = false, length = 255, updatable = false)
	private Integer createdBy;
	
	@ManyToOne
	@JoinColumn(name = "attr_id", referencedColumnName = "attr_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TAttrDef tAttrDef;
	
	

	public TAttrDef gettAttrDef() {
		return tAttrDef;
	}

	public void settAttrDef(TAttrDef tAttrDef) {
		this.tAttrDef = tAttrDef;
	}

	/**
	 * 
	 * @generated
	 */
	public TEmpAttr() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTEmpAttrId(final TEmpAttrId tEmpAttrId) {
		this.tEmpAttrId = tEmpAttrId;
	}

	/**
	 * 
	 * @generated
	 */
	public TEmpAttrId getTEmpAttrId() {
		return this.tEmpAttrId;
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
	public void setCreateDt(final Date createDt) {
		this.createDt = createDt;
	}

	/**
	 * 
	 * @generated
	 */
	public Date getCreateDt() {
		return this.createDt;
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
	public void setUpdateDt(final Date updateDt) {
		this.updateDt = updateDt;
	}

	/**
	 * 
	 * @generated
	 */
	public Date getUpdateDt() {
		return this.updateDt;
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
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((tenantId == null) ? 0 : tenantId.hashCode());
		result = prime * result
				+ ((createDt == null) ? 0 : createDt.hashCode());
		result = prime * result
				+ ((attrValue == null) ? 0 : attrValue.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result
				+ ((activeFlag == null) ? 0 : activeFlag.hashCode());
		result = prime * result
				+ ((updateDt == null) ? 0 : updateDt.hashCode());		
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((tEmpAttrId == null) ? 0 : tEmpAttrId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("attrValue=[").append(attrValue).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");		
		buffer.append("tEmpAttrId=[").append(tEmpAttrId).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");

		return buffer.toString();
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TEmpAttr that) {
		setTEmpAttrId(that.getTEmpAttrId());
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
		final TEmpAttr other = (TEmpAttr) obj;
		if (tEmpAttrId == null) {
			if (other.tEmpAttrId != null) {
				return false;
			}
		} else if (!tEmpAttrId.equals(other.tEmpAttrId)) {
			return false;
		}
		return true;
	}
}
