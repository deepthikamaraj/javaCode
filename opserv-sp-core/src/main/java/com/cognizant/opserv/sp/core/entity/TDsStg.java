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
 * JPA class representing TDsStg 
 * The corresponding mapping table is t_ds_stg 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTDsStgs", query = "select myTDsStg from TDsStg myTDsStg"),
		@NamedQuery(name = "CountTDsStgs", query = "Select Count(c) from TDsStg c"),
		@NamedQuery(name = "TDsStgsAftrCreateDt", query = "select myTDsStg from TDsStg myTDsStg where myTDsStg.tenantId = ?1 AND myTDsStg.createDt BETWEEN ?2 AND ?3 "),
		@NamedQuery(name = "FindTDsStgByTDsStatus", query = "select myTDsStg from TDsStg myTDsStg where myTDsStg.tDsStatus = ?1 "),
		@NamedQuery(name = "FindTDsStgByTDsName", query = "select myTDsStg from TDsStg myTDsStg where myTDsStg.tenantId = ?1 AND myTDsStg.dsName= ?2 "),
		/*@NamedQuery(name = "FindTDsStgByTenantIdAndDSStatus", query = "select myTDsStg from TDsStg myTDsStg where myTDsStg.tenantId = ?1 and myTDsStg.tDsStatus.statusId=?2"),*/
		@NamedQuery(name = "FindTDsStgByTenantIdAndDSStatus", query = "select myTDsStg from TDsStg myTDsStg where myTDsStg.tenantId = ?1 and myTDsStg.tDsStatus.statusId IN ?2"),
		@NamedQuery(name = "CountTDsStgsByTDsStatus", query = "select Count(myTDsStg) from TDsStg myTDsStg where myTDsStg.tDsStatus = ?1 "),
		@NamedQuery(name = "GetActiveDs", query = "select stg.dsId,stg.dataFileName,stg.dsName from TDsStg stg INNER JOIN stg.tTblSchMapss map where map.dynAttrFlag is not null and stg.tenantId = ?1 and stg.tDsStatus.statusId not in (1,2,5) group by stg.dsId"),
		// Added by 407986 to update the buss function for call plan load 
		@NamedQuery(name = "UpadteBussFuntionforCallPlanLoad", query = "Update TDsStg myTDsStg set myTDsStg.bussFunction = ?1  where myTDsStg.tblName  = ?2 and myTDsStg.tenantId = ?3 ")

})
@Table(name = "t_ds_stg", uniqueConstraints = @UniqueConstraint(columnNames = { "ds_id", "ds_name" }))
public class TDsStg implements Serializable {
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
	@Column(name = "appr_dt_tm", nullable = true, length = 19)
	private Date apprDtTm;

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
	@Column(name = "arc_dt_tm", nullable = true, length = 19)
	private Date arcDtTm;

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
	@Column(name = "inc_data_flag", nullable = true, length = 1)
	private Character incDataFlag;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "file_name", nullable = true, length = 50)
	private String fileName;

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
	@Column(name = "data_file_import_dt_tm", nullable = true, length = 19)
	private Date dataFileImportDtTm;

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
	@Column(name = "rpt_ds_flag", nullable = true, length = 1)
	private Character rptDsFlag;

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
	@Column(name = "fmt_type", nullable = true, length = 20)
	private String fmtType;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 3)
	@Column(name = "txt_delim", nullable = true, length = 3)
	private String txtDelim;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "err_action", nullable = true, length = 255)
	private Short errAction;

	@ManyToOne
	@JoinColumn(name = "status_id", referencedColumnName = "status_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TDsStatus tDsStatus;

	/*@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tDsStg")
	private Set<TLoadLog> tLoadLogss;
*/
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tDsStg")
	private Set<TExtLog> tExtLogss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tDsStgByMirrorDsId")
	private Set<TDsMirror> tDsMirrorsForMirrorDsIds;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tDsStg")
	private Set<TTblSchMap> tTblSchMapss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tDsStgByDsId")
	private Set<TDsMirror> tDsMirrorsForDsIds;

	/**
	 *
	 * @generated
	 */
	public TDsStg() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setDsId(final Long dsId) {
		this.dsId = dsId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getDsId() {
		return this.dsId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDataFileName(final String dataFileName) {
		this.dataFileName = dataFileName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getDataFileName() {
		return this.dataFileName;
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

	public void setApprDtTm(final Date apprDtTm) {
		if (apprDtTm != null) {
			this.apprDtTm = (Date) apprDtTm.clone();
		} else {
			this.apprDtTm = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getApprDtTm() {
		if (this.apprDtTm != null) {
			return (Date) this.apprDtTm.clone();
		} else {
			return null;
		}
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

	public void setArcDtTm(final Date arcDtTm) {
		if (arcDtTm != null) {
			this.arcDtTm = (Date) arcDtTm.clone();
		} else {
			this.arcDtTm = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getArcDtTm() {
		if (this.arcDtTm != null) {
			return (Date) this.arcDtTm.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setBussFunction(final String bussFunction) {
		this.bussFunction = bussFunction;
	}

	/**
	 * 
	 * @generated
	 */
	public String getBussFunction() {
		return this.bussFunction;
	}

	/**
	 * 
	 * @generated
	 */

	public void setIncDataFlag(final Character incDataFlag) {
		this.incDataFlag = incDataFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getIncDataFlag() {
		return this.incDataFlag;
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

	public void setDataFileLocation(final String dataFileLocation) {
		this.dataFileLocation = dataFileLocation;
	}

	/**
	 * 
	 * @generated
	 */
	public String getDataFileLocation() {
		return this.dataFileLocation;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDataFileImportDtTm(final Date dataFileImportDtTm) {
		if (dataFileImportDtTm != null) {
			this.dataFileImportDtTm = (Date) dataFileImportDtTm.clone();
		} else {
			this.dataFileImportDtTm = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getDataFileImportDtTm() {
		if (this.dataFileImportDtTm != null) {
			return (Date) this.dataFileImportDtTm.clone();
		} else {
			return null;
		}
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

	public void setRptDsFlag(final Character rptDsFlag) {
		this.rptDsFlag = rptDsFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getRptDsFlag() {
		return this.rptDsFlag;
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

	public void setFmtType(final String fmtType) {
		this.fmtType = fmtType;
	}

	/**
	 * 
	 * @generated
	 */
	public String getFmtType() {
		return this.fmtType;
	}

	/**
	 * 
	 * @generated
	 */

	public void setTxtDelim(final String txtDelim) {
		this.txtDelim = txtDelim;
	}

	/**
	 * 
	 * @generated
	 */
	public String getTxtDelim() {
		return this.txtDelim;
	}

	/**
	 * 
	 * @generated
	 */

	public void setErrAction(final Short errAction) {
		this.errAction = errAction;
	}

	/**
	 * 
	 * @generated
	 */
	public Short getErrAction() {
		return this.errAction;
	}

	/**
	 * 
	 * @generated
	 */
	public TDsStatus getTDsStatus() {
		return this.tDsStatus;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTDsStatus(final TDsStatus tDsStatus) {
		this.tDsStatus = tDsStatus;

	}

	/**
	 * 
	 * @generated
	 */
	/*public Set<TLoadLog> getTLoadLogss() {
		return this.tLoadLogss;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public void setTLoadLogss(final Set<TLoadLog> tLoadLogss) {
		this.tLoadLogss = tLoadLogss;
	}*/

	/**
	 * 
	 * @generated
	 */
	public Set<TExtLog> getTExtLogss() {
		return this.tExtLogss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTExtLogss(final Set<TExtLog> tExtLogss) {
		this.tExtLogss = tExtLogss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TDsMirror> getTDsMirrorsForMirrorDsIds() {
		return this.tDsMirrorsForMirrorDsIds;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTDsMirrorsForMirrorDsIds(final Set<TDsMirror> tDsMirrorsForMirrorDsIds) {
		this.tDsMirrorsForMirrorDsIds = tDsMirrorsForMirrorDsIds;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TTblSchMap> getTTblSchMapss() {
		return this.tTblSchMapss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTTblSchMapss(final Set<TTblSchMap> tTblSchMapss) {
		this.tTblSchMapss = tTblSchMapss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TDsMirror> getTDsMirrorsForDsIds() {
		return this.tDsMirrorsForDsIds;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTDsMirrorsForDsIds(final Set<TDsMirror> tDsMirrorsForDsIds) {
		this.tDsMirrorsForDsIds = tDsMirrorsForDsIds;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TDsStg that) {
		setDsId(that.getDsId());
		setDataFileName(that.getDataFileName());
		setDsDesc(that.getDsDesc());
		setExtDtTm(that.getExtDtTm());
		setApprDtTm(that.getApprDtTm());
		setLoadDtTm(that.getLoadDtTm());
		setArcDtTm(that.getArcDtTm());
		setBussFunction(that.getBussFunction());
		setIncDataFlag(that.getIncDataFlag());
		setFileName(that.getFileName());
		setTblName(that.getTblName());
		setDataFileLocation(that.getDataFileLocation());
		setDataFileImportDtTm(that.getDataFileImportDtTm());
		setDsName(that.getDsName());
		setRptDsFlag(that.getRptDsFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setFileType(that.getFileType());
		setFmtType(that.getFmtType());
		setTxtDelim(that.getTxtDelim());
		setErrAction(that.getErrAction());
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
		buffer.append("dataFileName=[").append(dataFileName).append("] ");
		buffer.append("dsDesc=[").append(dsDesc).append("] ");
		buffer.append("extDtTm=[").append(extDtTm).append("] ");
		buffer.append("apprDtTm=[").append(apprDtTm).append("] ");
		buffer.append("loadDtTm=[").append(loadDtTm).append("] ");
		buffer.append("arcDtTm=[").append(arcDtTm).append("] ");
		buffer.append("bussFunction=[").append(bussFunction).append("] ");
		buffer.append("incDataFlag=[").append(incDataFlag).append("] ");
		buffer.append("fileName=[").append(fileName).append("] ");
		buffer.append("tblName=[").append(tblName).append("] ");
		buffer.append("dataFileLocation=[").append(dataFileLocation).append("] ");
		buffer.append("dataFileImportDtTm=[").append(dataFileImportDtTm).append("] ");
		buffer.append("dsName=[").append(dsName).append("] ");
		buffer.append("rptDsFlag=[").append(rptDsFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("fileType=[").append(fileType).append("] ");
		buffer.append("fmtType=[").append(fmtType).append("] ");
		buffer.append("txtDelim=[").append(txtDelim).append("] ");
		buffer.append("errAction=[").append(errAction).append("] ");

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
		final TDsStg other = (TDsStg) obj;
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
