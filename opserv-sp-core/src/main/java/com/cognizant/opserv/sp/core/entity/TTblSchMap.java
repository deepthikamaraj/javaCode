package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TTblSchMap 
 * The corresponding mapping table is t_tbl_sch_map 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTTblSchMaps", query = "select myTTblSchMap from TTblSchMap myTTblSchMap"),
		@NamedQuery(name = "CountTTblSchMaps", query = "Select Count(c) from TTblSchMap c"),
		@NamedQuery(name = "FindTTblSchMapByTDsStg", query = "select myTTblSchMap from TTblSchMap myTTblSchMap where myTTblSchMap.tDsStg = ?1 "),
		@NamedQuery(name = "FindTTblSchMapByTDsStgAndKeyCol", query = "select myTTblSchMap from TTblSchMap myTTblSchMap where myTTblSchMap.tDsStg = ?1 and (myTTblSchMap.prdKeyCol = ?2 or myTTblSchMap.prdKeyCol = ?3)"),
		@NamedQuery(name = "CountTTblSchMapsByTDsStg", query = "select Count(myTTblSchMap) from TTblSchMap myTTblSchMap where myTTblSchMap.tDsStg = ?1 "),
		@NamedQuery(name = "GetTTblSchMapsByTDsStgAndTenantAndTableName", query = "select myTTblSchMap from TTblSchMap myTTblSchMap where myTTblSchMap.tDsStg = ?1 and myTTblSchMap.stgTblName=?2 and myTTblSchMap.tenantId=?3")
		})
@Table(name = "t_tbl_sch_map", uniqueConstraints = @UniqueConstraint(columnNames = { "mapping_id" }))
public class TTblSchMap implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mapping_id", nullable = false, length = 255)
	private Long mappingId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "stg_ip", nullable = true, length = 20)
	private String stgIp;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "stg_db", nullable = true, length = 100)
	private String stgDb;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "stg_tbl_name", nullable = true, length = 100)
	private String stgTblName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "stg_column_name", nullable = true, length = 100)
	private String stgColumnName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "stg_data_type", nullable = true, length = 100)
	private String stgDataType;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "stg_column_length", nullable = true, length = 255)
	private Short stgColumnLength;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 10)
	@Column(name = "stg_null_option", nullable = true, length = 10)
	private String stgNullOption;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "prd_ip", nullable = true, length = 20)
	private String prdIp;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "prd_db", nullable = true, length = 100)
	private String prdDb;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "prd_tbl_name", nullable = true, length = 100)
	private String prdTblName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "prd_column_name", nullable = true, length = 100)
	private String prdColumnName;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "map_dt_tm", nullable = true, length = 19)
	private Date mapDtTm;

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
	@Length(max = 2000)
	@Column(name = "col_trnsform", nullable = true, length = 2000)
	private String colTrnsform;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "stg_key_col", nullable = true, length = 1)
	private Character stgKeyCol;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "stg_unq_col", nullable = true, length = 1)
	private Character stgUnqCol;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "start_pos", nullable = true, length = 255)
	private Short startPos;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "end_pos", nullable = true, length = 255)
	private Short endPos;

	
	@Column(name = "prd_key_col", nullable = true, length = 1)
	private Character prdKeyCol;

	@Column(name = "dyn_attr_flag", nullable = true, length = 1)
	private Character dynAttrFlag;

	@Column(name = "attr_id", nullable = true, length = 255)
	private Long attrId;
	
	@Column(name = "algmnt_flag", nullable = true, length = 1)
	private Character algmntFlag;
	
	
	@ManyToOne
	@JoinColumn(name = "ds_id", referencedColumnName = "ds_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TDsStg tDsStg;

	/**
	 *
	 * @generated
	 */
	public TTblSchMap() {
	}

	
	
	public Character getAlgmntFlag() {
		return algmntFlag;
	}








	public void setAlgmntFlag(Character algmntFlag) {
		this.algmntFlag = algmntFlag;
	}








	public Character getPrdKeyCol() {
		return prdKeyCol;
	}




	public void setPrdKeyCol(Character prdKeyCol) {
		this.prdKeyCol = prdKeyCol;
	}




	public TDsStg gettDsStg() {
		return tDsStg;
	}




	public void settDsStg(TDsStg tDsStg) {
		this.tDsStg = tDsStg;
	}




	public void setAttrId(final Long attrId) {
		this.attrId = attrId;
	}


	public Long getAttrId() {
		return this.attrId;
	}
	
	public void setDynAttrFlag(final Character dynAttrFlag) {
		this.dynAttrFlag = dynAttrFlag;
	}


	public Character getDynAttrFlag() {
		return this.dynAttrFlag;
	}


	
	/**
	 * 
	 * @generated
	 */

	public void setMappingId(final Long mappingId) {
		this.mappingId = mappingId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getMappingId() {
		return this.mappingId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setStgIp(final String stgIp) {
		this.stgIp = stgIp;
	}

	/**
	 * 
	 * @generated
	 */
	public String getStgIp() {
		return this.stgIp;
	}

	/**
	 * 
	 * @generated
	 */

	public void setStgDb(final String stgDb) {
		this.stgDb = stgDb;
	}

	/**
	 * 
	 * @generated
	 */
	public String getStgDb() {
		return this.stgDb;
	}

	/**
	 * 
	 * @generated
	 */

	public void setStgTblName(final String stgTblName) {
		this.stgTblName = stgTblName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getStgTblName() {
		return this.stgTblName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setStgColumnName(final String stgColumnName) {
		this.stgColumnName = stgColumnName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getStgColumnName() {
		return this.stgColumnName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setStgDataType(final String stgDataType) {
		this.stgDataType = stgDataType;
	}

	/**
	 * 
	 * @generated
	 */
	public String getStgDataType() {
		return this.stgDataType;
	}

	/**
	 * 
	 * @generated
	 */

	public void setStgColumnLength(final Short stgColumnLength) {
		this.stgColumnLength = stgColumnLength;
	}

	/**
	 * 
	 * @generated
	 */
	public Short getStgColumnLength() {
		return this.stgColumnLength;
	}

	/**
	 * 
	 * @generated
	 */

	public void setStgNullOption(final String stgNullOption) {
		this.stgNullOption = stgNullOption;
	}

	/**
	 * 
	 * @generated
	 */
	public String getStgNullOption() {
		return this.stgNullOption;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPrdIp(final String prdIp) {
		this.prdIp = prdIp;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPrdIp() {
		return this.prdIp;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPrdDb(final String prdDb) {
		this.prdDb = prdDb;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPrdDb() {
		return this.prdDb;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPrdTblName(final String prdTblName) {
		this.prdTblName = prdTblName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPrdTblName() {
		return this.prdTblName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPrdColumnName(final String prdColumnName) {
		this.prdColumnName = prdColumnName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPrdColumnName() {
		return this.prdColumnName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setMapDtTm(final Date mapDtTm) {
		if (mapDtTm != null) {
			this.mapDtTm = (Date) mapDtTm.clone();
		} else {
			this.mapDtTm = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getMapDtTm() {
		if (this.mapDtTm != null) {
			return (Date) this.mapDtTm.clone();
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

	public void setColTrnsform(final String colTrnsform) {
		this.colTrnsform = colTrnsform;
	}

	/**
	 * 
	 * @generated
	 */
	public String getColTrnsform() {
		return this.colTrnsform;
	}

	/**
	 * 
	 * @generated
	 */

	public void setStgKeyCol(final Character stgKeyCol) {
		this.stgKeyCol = stgKeyCol;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getStgKeyCol() {
		return this.stgKeyCol;
	}

	/**
	 * 
	 * @generated
	 */

	public void setStgUnqCol(final Character stgUnqCol) {
		this.stgUnqCol = stgUnqCol;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getStgUnqCol() {
		return this.stgUnqCol;
	}

	/**
	 * 
	 * @generated
	 */

	public void setStartPos(final Short startPos) {
		this.startPos = startPos;
	}

	/**
	 * 
	 * @generated
	 */
	public Short getStartPos() {
		return this.startPos;
	}

	/**
	 * 
	 * @generated
	 */

	public void setEndPos(final Short endPos) {
		this.endPos = endPos;
	}

	/**
	 * 
	 * @generated
	 */
	public Short getEndPos() {
		return this.endPos;
	}

	/**
	 * 
	 * @generated
	 */
	public TDsStg getTDsStg() {
		return this.tDsStg;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTDsStg(final TDsStg tDsStg) {
		this.tDsStg = tDsStg;

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TTblSchMap that) {
		setMappingId(that.getMappingId());
		setStgIp(that.getStgIp());
		setStgDb(that.getStgDb());
		setStgTblName(that.getStgTblName());
		setStgColumnName(that.getStgColumnName());
		setStgDataType(that.getStgDataType());
		setStgColumnLength(that.getStgColumnLength());
		setStgNullOption(that.getStgNullOption());
		setPrdIp(that.getPrdIp());
		setPrdDb(that.getPrdDb());
		setPrdTblName(that.getPrdTblName());
		setPrdColumnName(that.getPrdColumnName());
		setMapDtTm(that.getMapDtTm());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setColTrnsform(that.getColTrnsform());
		setStgKeyCol(that.getStgKeyCol());
		setStgUnqCol(that.getStgUnqCol());
		setStartPos(that.getStartPos());
		setEndPos(that.getEndPos());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((mappingId == null) ? 0 : mappingId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("mappingId=[").append(mappingId).append("] ");
		buffer.append("stgIp=[").append(stgIp).append("] ");
		buffer.append("stgDb=[").append(stgDb).append("] ");
		buffer.append("stgTblName=[").append(stgTblName).append("] ");
		buffer.append("stgColumnName=[").append(stgColumnName).append("] ");
		buffer.append("stgDataType=[").append(stgDataType).append("] ");
		buffer.append("stgColumnLength=[").append(stgColumnLength).append("] ");
		buffer.append("stgNullOption=[").append(stgNullOption).append("] ");
		buffer.append("prdIp=[").append(prdIp).append("] ");
		buffer.append("prdDb=[").append(prdDb).append("] ");
		buffer.append("prdTblName=[").append(prdTblName).append("] ");
		buffer.append("prdColumnName=[").append(prdColumnName).append("] ");
		buffer.append("mapDtTm=[").append(mapDtTm).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("colTrnsform=[").append(colTrnsform).append("] ");
		buffer.append("stgKeyCol=[").append(stgKeyCol).append("] ");
		buffer.append("stgUnqCol=[").append(stgUnqCol).append("] ");
		buffer.append("startPos=[").append(startPos).append("] ");
		buffer.append("endPos=[").append(endPos).append("] ");

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
		final TTblSchMap other = (TTblSchMap) obj;
		if (mappingId == null) {
			if (other.mappingId != null) {
				return false;
			}
		} else if (!mappingId.equals(other.mappingId)) {
			return false;
		}
		return true;
	}
}
