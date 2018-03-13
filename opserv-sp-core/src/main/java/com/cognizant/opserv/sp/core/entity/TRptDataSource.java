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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TRptDataSource 
 * The corresponding mapping table is t_rpt_data_source 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTRptDataSources", query = "select myTRptDataSource from TRptDataSource myTRptDataSource"),
		@NamedQuery(name = "CountTRptDataSources", query = "Select Count(c) from TRptDataSource c"),
		@NamedQuery(name = "FindTRptDataSourceByTRptConfig", query = "select myTRptDataSource from TRptDataSource myTRptDataSource where myTRptDataSource.tRptConfig = ?1 "),
		@NamedQuery(name = "CountTRptDataSourcesByTRptConfig", query = "select Count(*) from TRptDataSource myTRptDataSource where myTRptDataSource.tRptConfig = ?1 ") })
@Table(name = "t_rpt_data_source")
public class TRptDataSource implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "doc_location_id", nullable = false, length = 255)
	private Integer docLocationId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 1024)
	@Column(name = "doc_name", nullable = true, length = 50)
	private String docName;

	/**
	 * 
	 * @generated
	 */	
	@Length(max = 1024)
	@Column(name = "doc_location", nullable = true, length = 1024)
	private String docLocation;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "doc_suffix_format", nullable = true, length = 20)
	private String docSuffixFormat;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 10)
	@Column(name = "doc_extension_format", nullable = true, length = 10)
	private String docExtensionFormat;

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
	@Column(name = "created_by", nullable = true, length = 255)
	private Integer createdBy;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "create_dt", nullable = true, length = 19)
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
	private Date updateDate;

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
	@Column(name = "data_src_design", nullable = true, length = 255)
	private byte[] dataSrcDesign;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "rpt_config_id", referencedColumnName = "rpt_config_id", nullable = false, insertable = true, updatable = true)
	@Valid
	@NotAudited
	private TRptConfig tRptConfig;

	/**
	 *
	 * @generated
	 */
	public TRptDataSource() {
	}

	/**
	 * 
	 * @generated
	 */

	/*public void setTRptDataSourceId(final TRptDataSourceId tRptDataSourceId) {
		this.tRptDataSourceId = tRptDataSourceId;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public TRptDataSourceId getTRptDataSourceId() {
		return this.tRptDataSourceId;
	}*/
	
	/**
	 * 
	 * @generated
	 */
	public Integer getDocLocationId() {
		return docLocationId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setDocLocationId(Integer docLocationId) {
		this.docLocationId = docLocationId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDocName(final String docName) {
		this.docName = docName;
	}	

	/**
	 * 
	 * @generated
	 */
	public String getDocName() {
		return this.docName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDocLocation(final String docLocation) {
		this.docLocation = docLocation;
	}

	/**
	 * 
	 * @generated
	 */
	public String getDocLocation() {
		return this.docLocation;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDocSuffixFormat(final String docSuffixFormat) {
		this.docSuffixFormat = docSuffixFormat;
	}

	/**
	 * 
	 * @generated
	 */
	public String getDocSuffixFormat() {
		return this.docSuffixFormat;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDocExtensionFormat(final String docExtensionFormat) {
		this.docExtensionFormat = docExtensionFormat;
	}

	/**
	 * 
	 * @generated
	 */
	public String getDocExtensionFormat() {
		return this.docExtensionFormat;
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

	public void setUpdateDate(final Date updateDate) {
		if (updateDate != null) {
			this.updateDate = (Date) updateDate.clone();
		} else {
			this.updateDate = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getUpdateDate() {
		if (this.updateDate != null) {
			return (Date) this.updateDate.clone();
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

	public void setDataSrcDesign(final byte[] dataSrcDesign) {
		this.dataSrcDesign = dataSrcDesign;
	}

	/**
	 * 
	 * @generated
	 */
	public byte[] getDataSrcDesign() {
		return this.dataSrcDesign;
	}

	/**
	 * 
	 * @generated
	 */
	public TRptConfig getTRptConfig() {
		return this.tRptConfig;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTRptConfig(final TRptConfig tRptConfig) {
		this.tRptConfig = tRptConfig;
		/*if (this.tRptConfig != null && this.tRptConfig.getRptConfigId() != null) {

			this.tRptDataSourceId.setRptConfigId(this.tRptConfig.getRptConfigId());

		}*/

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TRptDataSource that) {
		//setTRptDataSourceId(that.getTRptDataSourceId());
		setDocLocationId(that.getDocLocationId());
		setDocName(that.getDocName());
		setDocLocation(that.getDocLocation());
		setDocSuffixFormat(that.getDocSuffixFormat());
		setDocExtensionFormat(that.getDocExtensionFormat());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDate(that.getUpdateDate());
		setTenantId(that.getTenantId());
		setDataSrcDesign(that.getDataSrcDesign());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((docLocationId == null) ? 0 : docLocationId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tRptDataSourceId=[").append(docLocationId).append("] ");
		buffer.append("docName=[").append(docName).append("] ");
		buffer.append("docLocation=[").append(docLocation).append("] ");
		buffer.append("docSuffixFormat=[").append(docSuffixFormat).append("] ");
		buffer.append("docExtensionFormat=[").append(docExtensionFormat).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDate=[").append(updateDate).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("dataSrcDesign=[").append(dataSrcDesign).append("] ");

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
		final TRptDataSource other = (TRptDataSource) obj;
		if (docLocationId == null) {
			if (other.docLocationId != null) {
				return false;
			}
		} else if (!docLocationId.equals(other.docLocationId)) {
			return false;
		}
		return true;
	}
}
