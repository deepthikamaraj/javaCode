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

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@NamedQueries({
		@NamedQuery(name = "FindtZipLoad", query = "select myTZipLoad from TZipLoad myTZipLoad where myTZipLoad.dsName=?1 and myTZipLoad.tenantId =?2")
		

})

@Table(name = "t_zip_load", uniqueConstraints = @UniqueConstraint(columnNames = { "ds_id", "ds_name" }))
public class TZipLoad implements Serializable{
	
	
	
	//default serial version id, required for serializable classes.
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 * @generated
		 */
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "ds_id", nullable = false, length = 255)
		private Long dsId;

		/**
		 * 
		 * @generated
		 */
		@Length(max = 150)
		@Column(name = "data_file_name", nullable = true, length = 150)
		private String dataFileName;

		/**
		 * 
		 * @generated
		 */
		@Length(max = 300)
		@Column(name = "ds_desc", nullable = true, length = 300)
		private String dsDesc;

		/**
		 * 
		 * @generated
		 */
		@Column(name = "ext_dt_tm", nullable = true, length = 19)
		private Date extDtTm;

		
		/**
		 * 
		 * @generated
		 */
		@Column(name = "load_dt_tm", nullable = true, length = 19)
		private Date loadDtTm;

		
		/**
		 * 
		 * @generated
		 */
		@Length(max = 75)
		@Column(name = "buss_function", nullable = true, length = 75)
		private String bussFunction;

		/**
		 * 
		 * @generated
		 */
		@Length(max = 50)
		@Column(name = "tbl_name", nullable = true, length = 50)
		private String tblName;

		/**
		 * 
		 * @generated
		 */
		@Length(max = 1024)
		@Column(name = "data_file_location", nullable = true, length = 1024)
		private String dataFileLocation;

		
		/**
		 * 
		 * @generated
		 */
		@NotEmpty
		@Length(max = 100)
		@Column(name = "ds_name", nullable = false, length = 100)
		private String dsName;

		
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
		@Length(max = 20)
		@Column(name = "file_type", nullable = true, length = 20)
		private String fileType;
		
		/**
		 * 
		 * @generated
		 */
		@Length(max = 20)
		@Column(name = "log_file_name", nullable = true, length = 20)
		private String logFileName;
		
		/**
		 * 
		 * @generated
		 */
		@Length(max = 20)
		@Column(name = "log_file_location", nullable = true, length = 20)
		private String logFileLocation;
		
		/**
		 * 
		 * @generated
		 */
		@Length(max = 20)
		@Column(name = "status", nullable = true, length = 20)
		private String statusDes;
		
		
		
		
		
		

	

		public Long getDsId() {
			return dsId;
		}

		public void setDsId(Long dsId) {
			this.dsId = dsId;
		}

		public String getDataFileName() {
			return dataFileName;
		}

		public void setDataFileName(String dataFileName) {
			this.dataFileName = dataFileName;
		}

		public String getDsDesc() {
			return dsDesc;
		}

		public void setDsDesc(String dsDesc) {
			this.dsDesc = dsDesc;
		}

		public Date getExtDtTm() {
			return extDtTm;
		}

		public void setExtDtTm(Date extDtTm) {
			this.extDtTm = extDtTm;
		}

		public Date getLoadDtTm() {
			return loadDtTm;
		}

		public void setLoadDtTm(Date loadDtTm) {
			this.loadDtTm = loadDtTm;
		}

		public String getBussFunction() {
			return bussFunction;
		}

		public void setBussFunction(String bussFunction) {
			this.bussFunction = bussFunction;
		}

		public String getTblName() {
			return tblName;
		}

		public void setTblName(String tblName) {
			this.tblName = tblName;
		}

		public String getDataFileLocation() {
			return dataFileLocation;
		}

		public void setDataFileLocation(String dataFileLocation) {
			this.dataFileLocation = dataFileLocation;
		}

		public String getDsName() {
			return dsName;
		}

		public void setDsName(String dsName) {
			this.dsName = dsName;
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

		public String getFileType() {
			return fileType;
		}

		public void setFileType(String fileType) {
			this.fileType = fileType;
		}

		public String getLogFileName() {
			return logFileName;
		}

		public void setLogFileName(String logFileName) {
			this.logFileName = logFileName;
		}

		public String getLogFileLocation() {
			return logFileLocation;
		}

		public void setLogFileLocation(String logFileLocation) {
			this.logFileLocation = logFileLocation;
		}

		public String getStatusDes() {
			return statusDes;
		}

		public void setStatusDes(String statusDes) {
			this.statusDes = statusDes;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		@Override
		public String toString() {
			return "TZipLoad [dsId=" + dsId + ", dataFileName=" + dataFileName
					+ ", dsDesc=" + dsDesc + ", extDtTm=" + extDtTm
					+ ", loadDtTm=" + loadDtTm + ", bussFunction="
					+ bussFunction + ", tblName=" + tblName
					+ ", dataFileLocation=" + dataFileLocation + ", dsName="
					+ dsName + ", createdBy=" + createdBy + ", createDt="
					+ createDt + ", updatedBy=" + updatedBy + ", updateDt="
					+ updateDt + ", tenantId=" + tenantId + ", fileType="
					+ fileType + ", logFileName=" + logFileName
					+ ", logFileLocation=" + logFileLocation + ", statusDes="
					+ statusDes + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((bussFunction == null) ? 0 : bussFunction.hashCode());
			result = prime * result
					+ ((createDt == null) ? 0 : createDt.hashCode());
			result = prime * result
					+ ((createdBy == null) ? 0 : createdBy.hashCode());
			result = prime
					* result
					+ ((dataFileLocation == null) ? 0 : dataFileLocation
							.hashCode());
			result = prime * result
					+ ((dataFileName == null) ? 0 : dataFileName.hashCode());
			result = prime * result
					+ ((dsDesc == null) ? 0 : dsDesc.hashCode());
			result = prime * result + ((dsId == null) ? 0 : dsId.hashCode());
			result = prime * result
					+ ((dsName == null) ? 0 : dsName.hashCode());
			result = prime * result
					+ ((extDtTm == null) ? 0 : extDtTm.hashCode());
			result = prime * result
					+ ((fileType == null) ? 0 : fileType.hashCode());
			result = prime * result
					+ ((loadDtTm == null) ? 0 : loadDtTm.hashCode());
			result = prime
					* result
					+ ((logFileLocation == null) ? 0 : logFileLocation
							.hashCode());
			result = prime * result
					+ ((logFileName == null) ? 0 : logFileName.hashCode());
			result = prime * result
					+ ((statusDes == null) ? 0 : statusDes.hashCode());
			result = prime * result
					+ ((tblName == null) ? 0 : tblName.hashCode());
			result = prime * result
					+ ((tenantId == null) ? 0 : tenantId.hashCode());
			result = prime * result
					+ ((updateDt == null) ? 0 : updateDt.hashCode());
			result = prime * result
					+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
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
			TZipLoad other = (TZipLoad) obj;
			if (bussFunction == null) {
				if (other.bussFunction != null)
					return false;
			} else if (!bussFunction.equals(other.bussFunction))
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
			if (dataFileLocation == null) {
				if (other.dataFileLocation != null)
					return false;
			} else if (!dataFileLocation.equals(other.dataFileLocation))
				return false;
			if (dataFileName == null) {
				if (other.dataFileName != null)
					return false;
			} else if (!dataFileName.equals(other.dataFileName))
				return false;
			if (dsDesc == null) {
				if (other.dsDesc != null)
					return false;
			} else if (!dsDesc.equals(other.dsDesc))
				return false;
			if (dsId == null) {
				if (other.dsId != null)
					return false;
			} else if (!dsId.equals(other.dsId))
				return false;
			if (dsName == null) {
				if (other.dsName != null)
					return false;
			} else if (!dsName.equals(other.dsName))
				return false;
			if (extDtTm == null) {
				if (other.extDtTm != null)
					return false;
			} else if (!extDtTm.equals(other.extDtTm))
				return false;
			if (fileType == null) {
				if (other.fileType != null)
					return false;
			} else if (!fileType.equals(other.fileType))
				return false;
			if (loadDtTm == null) {
				if (other.loadDtTm != null)
					return false;
			} else if (!loadDtTm.equals(other.loadDtTm))
				return false;
			if (logFileLocation == null) {
				if (other.logFileLocation != null)
					return false;
			} else if (!logFileLocation.equals(other.logFileLocation))
				return false;
			if (logFileName == null) {
				if (other.logFileName != null)
					return false;
			} else if (!logFileName.equals(other.logFileName))
				return false;
			if (statusDes == null) {
				if (other.statusDes != null)
					return false;
			} else if (!statusDes.equals(other.statusDes))
				return false;
			if (tblName == null) {
				if (other.tblName != null)
					return false;
			} else if (!tblName.equals(other.tblName))
				return false;
			if (tenantId == null) {
				if (other.tenantId != null)
					return false;
			} else if (!tenantId.equals(other.tenantId))
				return false;
			if (updateDt == null) {
				if (other.updateDt != null)
					return false;
			} else if (!updateDt.equals(other.updateDt))
				return false;
			if (updatedBy == null) {
				if (other.updatedBy != null)
					return false;
			} else if (!updatedBy.equals(other.updatedBy))
				return false;
			return true;
		}
		
		
		
		
		
		
		

		
	
		

		
		
	

}
