package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
	@NamedQuery(name = "FindAllTAlgmntsPub", query = "select mytAlgmntPubStatus from TAlgmntPubStatus mytAlgmntPubStatus where mytAlgmntPubStatus.tenantId = ?1"),	
	@NamedQuery(name = "FindAllTAlgmntsIdAlBuSt", query = "select mytAlgmntPubStatus from TAlgmntPubStatus mytAlgmntPubStatus where mytAlgmntPubStatus.tenantId = ?1" +
			" and mytAlgmntPubStatus.algmntId = ?2 and mytAlgmntPubStatus.bussUnitId = ?3 and mytAlgmntPubStatus.salesTeamId = ?4 and  mytAlgmntPubStatus.category = ?5"),
	@NamedQuery(name = "FindAllTAlgmntsIdAlBuStWithCategory", query = "select mytAlgmntPubStatus from TAlgmntPubStatus mytAlgmntPubStatus where mytAlgmntPubStatus.tenantId = ?1" +
					" and mytAlgmntPubStatus.algmntId = ?2 and mytAlgmntPubStatus.bussUnitId = ?3 and mytAlgmntPubStatus.salesTeamId = ?4")
	
})

@Table(name = "t_algmnt_pub_status", uniqueConstraints = @UniqueConstraint(columnNames = { "status_id", "buss_unit_id" }))
public class TAlgmntPubStatus implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "algmnt_status_id", nullable = false, length = 255)
	private Long algmntStatusId;
	
	@NotNull
	@Column(name = "algmnt_id", nullable = false, length = 255)
	private Long algmntId;
	
	@NotNull
	@Column(name = "status_id", nullable = false, length = 255)
	private Integer statusId;
	
	@NotNull
	@Column(name = "buss_unit_id", nullable = false, length = 255)
	private Long bussUnitId;
	
	@NotNull
	@Column(name = "sales_team_id", nullable = false, length = 255)
	private Long salesTeamId;	
	
	@NotNull
	@Column(name = "created_by", nullable = false, length = 255)
	private Integer createdBy;
	
	@NotNull
	@Column(name = "create_dt", nullable = false, length = 19)
	private Date createDt;
	
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;
	
	@NotNull
	@Column(name = "category", nullable = false, length = 255)
	private String category;
	
	
	@NotNull
	@Column(name = "ds_name", nullable = false, length = 255)
	private String dataSetName;
	
	

	public Long getSalesTeamId() {
		return salesTeamId;
	}

	public void setSalesTeamId(Long salesTeamId) {
		this.salesTeamId = salesTeamId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDataSetName() {
		return dataSetName;
	}

	public void setDataSetName(String dataSetName) {
		this.dataSetName = dataSetName;
	}

	public Long getAlgmntStatusId() {
		return algmntStatusId;
	}

	public void setAlgmntStatusId(Long algmntStatusId) {
		this.algmntStatusId = algmntStatusId;
	}

	public Long getAlgmntId() {
		return algmntId;
	}

	public void setAlgmntId(Long algmntId) {
		this.algmntId = algmntId;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Long getBussUnitId() {
		return bussUnitId;
	}

	public void setBussUnitId(Long bussUnitId) {
		this.bussUnitId = bussUnitId;
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

	public Short getTenantId() {
		return tenantId;
	}

	public void setTenantId(Short tenantId) {
		this.tenantId = tenantId;
	}
	
		
	

	
	
	
	
	public TAlgmntPubStatus(Long algmntStatusId, Long algmntId,
			Integer statusId, Long bussUnitId, Integer createdBy,
			Date createDt, Short tenantId,String category,String dataSetName,Long salesTeamId) {
		super();
		this.algmntStatusId = algmntStatusId;
		this.algmntId = algmntId;
		this.statusId = statusId;
		this.bussUnitId = bussUnitId;
		this.createdBy = createdBy;
		this.createDt = createDt;
		this.tenantId = tenantId;
		this.salesTeamId = salesTeamId;
		this.category = category;
		this.dataSetName = dataSetName;
		
	}
	
	public TAlgmntPubStatus() {
	
	}

	@Override
	public String toString() {
		return "TAlgmntPubStatus [algmntStatusId=" + algmntStatusId
				+ ", algmntId=" + algmntId + ", statusId=" + statusId
				+ ", bussUnitId=" + bussUnitId + ", salesTeamId=" + salesTeamId
				+ ", createdBy=" + createdBy + ", createDt=" + createDt
				+ ", tenantId=" + tenantId + ", category=" + category
				+ ", dataSetName=" + dataSetName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((algmntId == null) ? 0 : algmntId.hashCode());
		result = prime * result
				+ ((algmntStatusId == null) ? 0 : algmntStatusId.hashCode());
		result = prime * result
				+ ((bussUnitId == null) ? 0 : bussUnitId.hashCode());
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result
				+ ((createDt == null) ? 0 : createDt.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((dataSetName == null) ? 0 : dataSetName.hashCode());
		result = prime * result
				+ ((salesTeamId == null) ? 0 : salesTeamId.hashCode());
		result = prime * result
				+ ((statusId == null) ? 0 : statusId.hashCode());
		result = prime * result
				+ ((tenantId == null) ? 0 : tenantId.hashCode());
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
		TAlgmntPubStatus other = (TAlgmntPubStatus) obj;
		if (algmntId == null) {
			if (other.algmntId != null)
				return false;
		} else if (!algmntId.equals(other.algmntId))
			return false;
		if (algmntStatusId == null) {
			if (other.algmntStatusId != null)
				return false;
		} else if (!algmntStatusId.equals(other.algmntStatusId))
			return false;
		if (bussUnitId == null) {
			if (other.bussUnitId != null)
				return false;
		} else if (!bussUnitId.equals(other.bussUnitId))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (createDt == null) {
			if (other.createDt != null)
				return false;
		} else if (!createDt.equals(other.createDt))
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (dataSetName == null) {
			if (other.dataSetName != null)
				return false;
		} else if (!dataSetName.equals(other.dataSetName))
			return false;
		if (salesTeamId == null) {
			if (other.salesTeamId != null)
				return false;
		} else if (!salesTeamId.equals(other.salesTeamId))
			return false;
		if (statusId == null) {
			if (other.statusId != null)
				return false;
		} else if (!statusId.equals(other.statusId))
			return false;
		if (tenantId == null) {
			if (other.tenantId != null)
				return false;
		} else if (!tenantId.equals(other.tenantId))
			return false;
		return true;
	}


	
	

}
