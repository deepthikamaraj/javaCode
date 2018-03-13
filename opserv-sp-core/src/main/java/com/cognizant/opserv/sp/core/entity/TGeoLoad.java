package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TGeoLoad 
 * The corresponding mapping table is t_geo_load 
 */

@Entity
@NamedQueries({
	@NamedQuery(name = "FindAllTGeoLoads", query = "select myTGeoLoad from TGeoLoad myTGeoLoad"),
	@NamedQuery(name = "CountTGeoLoads", query = "Select Count(c) from TGeoLoad c"),
	@NamedQuery(name = "FindTGeoLoadByTLoadStatus", query = "select myTGeoLoad from TGeoLoad myTGeoLoad where myTGeoLoad.tLoadStatus = ?1 "),
	@NamedQuery(name = "CountTGeoLoadsByTLoadStatus", query = "select Count(myTGeoLoad) from TGeoLoad myTGeoLoad where myTGeoLoad.tLoadStatus = ?1 "),
	@NamedQuery(name = "GetStaticHierarchyLevelLoad", query = "select myTGeoLoad.dsId,myTGeoLoad.dsName,myTGeoLoad.extDtTm,myTGeoLoad.fileType,myTGeoLoad.tLoadStatus.statusName,myTGeoLoad.tLoadStatus.statusId,tGeoLog.logFileName,tGeoLog.logLoc,tGeoLog.logId,myTGeoLoad.createdBy from TGeoLoad myTGeoLoad INNER JOIN myTGeoLoad.tGeoLoadLogss tGeoLog where myTGeoLoad.tenantId = ?1 AND myTGeoLoad.createDt BETWEEN ?2 AND ?3 order by myTGeoLoad.extDtTm desc")
})
@Table(name = "t_geo_load", uniqueConstraints = @UniqueConstraint(columnNames = { "ds_id" }))
public class TGeoLoad implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ds_id", nullable = false, length = 255)
	private Integer dsId;

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
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 150)
	@Column(name = "file_name", nullable = true, length = 150)
	private String fileName;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 150)
	@Column(name = "ds_name", nullable = false, length = 150)
	private String dsName;

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
	@Length(max = 150)
	@Column(name = "tbl_name", nullable = true, length = 150)
	private String tblName;

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
	@Column(name = "load_dt_tm", nullable = true, length = 19)
	private Date loadDtTm;

	@ManyToOne
	@JoinColumn(name = "status_id", referencedColumnName = "status_id", nullable = true, insertable = true, updatable = true)
	@Valid
	private TLoadStatus tLoadStatus;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tGeoLoad")
	private Set<TGeoLoadLog> tGeoLoadLogss;

	/**
	 *
	 * @generated
	 */
	public TGeoLoad() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setDsId(final Integer dsId) {
		this.dsId = dsId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getDsId() {
		return this.dsId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setExtDtTm(final Date extDtTm) {
		if (extDtTm != null) {
			this.extDtTm = (Date) extDtTm.clone();
		} else {
			this.extDtTm = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getExtDtTm() {
		if (this.extDtTm != null) {
			return (Date) this.extDtTm.clone();
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

	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDsName(final String dsName) {
		this.dsName = dsName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getDsName() {
		return this.dsName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDsDesc(final String dsDesc) {
		this.dsDesc = dsDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getDsDesc() {
		return this.dsDesc;
	}

	/**
	 * 
	 * @generated
	 */

	public void setTblName(final String tblName) {
		this.tblName = tblName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getTblName() {
		return this.tblName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setFileType(final String fileType) {
		this.fileType = fileType;
	}

	/**
	 * 
	 * @generated
	 */
	public String getFileType() {
		return this.fileType;
	}

	/**
	 * 
	 * @generated
	 */

	public void setLoadDtTm(final Date loadDtTm) {
		if (loadDtTm != null) {
			this.loadDtTm = (Date) loadDtTm.clone();
		} else {
			this.loadDtTm = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getLoadDtTm() {
		if (this.loadDtTm != null) {
			return (Date) this.loadDtTm.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public TLoadStatus getTLoadStatus() {
		return this.tLoadStatus;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTLoadStatus(final TLoadStatus tLoadStatus) {
		this.tLoadStatus = tLoadStatus;

	}

	/**
	 * 
	 * @generated
	 */
	public Set<TGeoLoadLog> getTGeoLoadLogss() {
		return this.tGeoLoadLogss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTGeoLoadLogss(final Set<TGeoLoadLog> tGeoLoadLogss) {
		this.tGeoLoadLogss = tGeoLoadLogss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TGeoLoad that) {
		setDsId(that.getDsId());
		setExtDtTm(that.getExtDtTm());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setTenantId(that.getTenantId());
		setFileName(that.getFileName());
		setDsName(that.getDsName());
		setDsDesc(that.getDsDesc());
		setTblName(that.getTblName());
		setFileType(that.getFileType());
		setLoadDtTm(that.getLoadDtTm());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((dsId == null) ? 0 : dsId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("dsId=[").append(dsId).append("] ");
		buffer.append("extDtTm=[").append(extDtTm).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("fileName=[").append(fileName).append("] ");
		buffer.append("dsName=[").append(dsName).append("] ");
		buffer.append("dsDesc=[").append(dsDesc).append("] ");
		buffer.append("tblName=[").append(tblName).append("] ");
		buffer.append("fileType=[").append(fileType).append("] ");
		buffer.append("loadDtTm=[").append(loadDtTm).append("] ");

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
		final TGeoLoad other = (TGeoLoad) obj;
		if (dsId == null) {
			if (other.dsId != null) {
				return false;
			}
		} else if (!dsId.equals(other.dsId)) {
			return false;
		}
		return true;
	}
}
