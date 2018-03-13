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
import org.hibernate.validator.constraints.NotEmpty;

/**
 * JPA class representing TTerrZipAlgmntChngReqDet The corresponding mapping
 * table is t_terr_zip_algmnt_chng_req_det
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTTerrZipAlgmntChngReqDets", query = "select myTTerrZipAlgmntChngReqDet from TTerrZipAlgmntChngReqDet myTTerrZipAlgmntChngReqDet"),
		@NamedQuery(name = "FindTTerrZipAlgmntChngReqDetByTChngReqDetail", query = "select myTTerrZipAlgmntChngReqDet from TTerrZipAlgmntChngReqDet myTTerrZipAlgmntChngReqDet where myTTerrZipAlgmntChngReqDet.tChngReq.chngReqId = ?1  AND myTTerrZipAlgmntChngReqDet.postalCd = ?2   AND myTTerrZipAlgmntChngReqDet.tenantId= ?3"),
//		@NamedQuery(name = "CountTTerrZipAlgmntChngReqDetsByTChngReqDetail", query = "select Count(*) from TTerrZipAlgmntChngReqDet myTTerrZipAlgmntChngReqDet where myTTerrZipAlgmntChngReqDet.tChngReqDetail = ?1 "),
//		@NamedQuery(name = "getZipDetailStatusByDetailId", query = "select myTTerrZipAlgmntChngReqDet from TTerrZipAlgmntChngReqDet myTTerrZipAlgmntChngReqDet where myTTerrZipAlgmntChngReqDet.tChngReqDetail.tChngReqDetailId.tChngReq.chngReqId = ?1 AND myTTerrZipAlgmntChngReqDet.tChngReqDetail.tChngReqDetailId.chngReqDetailId = ?2 AND myTTerrZipAlgmntChngReqDet.tenantId= ?3 "),
		@NamedQuery(name = "getZipDetailStatus", query = "select myTTerrZipAlgmntChngReqDet from TTerrZipAlgmntChngReqDet myTTerrZipAlgmntChngReqDet where myTTerrZipAlgmntChngReqDet.tChngReq.chngReqId = ?1  AND myTTerrZipAlgmntChngReqDet.tenantId= ?2 "),

		// @NamedQuery(name = "FindTTerrZipAlgmntChngReqDetByTTerrZipAlgmnt",
		// query =
		// "select myTTerrZipAlgmntChngReqDet from TTerrZipAlgmntChngReqDet myTTerrZipAlgmntChngReqDet where myTTerrZipAlgmntChngReqDet.tTerrZipAlgmnt = ?1 "),
		// @NamedQuery(name = "CountTTerrZipAlgmntChngReqDetsByTTerrZipAlgmnt",
		// query =
		// "select Count(*) from TTerrZipAlgmntChngReqDet myTTerrZipAlgmntChngReqDet where myTTerrZipAlgmntChngReqDet.tTerrZipAlgmnt = ?1 "),
		// @NamedQuery(name = "CountTTerrZipAlgmntChngReqDets", query =
		// "Select Count(c) from TTerrZipAlgmntChngReqDet c")

		/* Starting Of GetZipMovementReqDetails Added BY 407986 */
//		@NamedQuery(name = "GetZipMovementReqDetails", query = "select myTTerrZipAlgmntChngReqDet.reqDetail,myTChngReq.chngReqNum from TTerrZipAlgmntChngReqDet  myTTerrZipAlgmntChngReqDet , TChngReqDetail myTChngReqDetail,TChngReq myTChngReq  "
//				+ "where  myTChngReq.chngReqId=myTTerrZipAlgmntChngReqDet.tChngReq.chngReqId     "
//				+ "and myTTerrZipAlgmntChngReqDet.tChngReq.chngReqId =  myTChngReqDetail.tChngReq.chngReqId "
//				+ "and myTChngReqDetail.tChngReqTrigger.triggerId = ?1 "
//				+ "and myTTerrZipAlgmntChngReqDet.tChngReq.chngReqId = ?2 "
//				+ "and myTTerrZipAlgmntChngReqDet.tenantId = ?3 and myTChngReq.activeFlag='Y'") ,

/* End Of GetZipMovementReqDetails */
				
				/* Starting Of GetZipLocked Added BY 407986 */			
	@NamedQuery(name = "GetZipLocked", query = "select myTTerrZipAlgmntChngReqDet.postalCd,myTChngReq.statusId  from TTerrZipAlgmntChngReqDet  myTTerrZipAlgmntChngReqDet , TChngReq myTChngReq , "
                +"TChngReqConfig myTChngReqConfig where myTTerrZipAlgmntChngReqDet.tChngReq.chngReqId = myTChngReq.chngReqId  " 
			    +"and myTChngReq.statusId in ?1 and myTChngReq.triggerId in ?2 and myTTerrZipAlgmntChngReqDet.postalCd in ?3 and myTChngReq.tenantId = ?4 and myTChngReq.activeFlag='Y' and myTChngReqConfig.chngReqConfigId=myTChngReq.chngReqConfigId  and myTChngReqConfig.alignmentId=?5 and myTChngReqConfig.bussUnitId=?6 and myTChngReqConfig.salesTeamId=?7 ")
		/* End Of GetZipLocked */
})
@Table(name = "t_terr_zip_algmnt_chng_req_det", uniqueConstraints = @UniqueConstraint(columnNames = { "zip_chng_req_id" }))
public class TTerrZipAlgmntChngReqDet implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "zip_chng_req_id", nullable = false, length = 255)
	private Integer zipChngReqId;

	/**
	 * 
	 * @generated
	 * @NotNull
	 * @Column(name = "chng_req_id", nullable = false, length = 255) private
	 *              Long chngReqId;
	 * 
	 *              /**
	 * 
	 * @generated
	 * @NotNull
	 * @Column(name = "chng_req_detail_id", nullable = false, length = 255)
	 *              private Integer chngReqDetailId;
	 */

	/**
	 * 
	 * @generated
	 */
	@Column(name = "status_id", nullable = true, length = 255)
	private Integer statusId;

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
	@Column(name = "req_detail", nullable = true, length = 255)
	private String reqDetail;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "sales_pos_id", nullable = false, length = 255)
	private Long salesPosId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "sales_hier_id", nullable = false, length = 255)
	private Long salesHierId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 20)
	@Column(name = "postal_cd", nullable = false, length = 20)
	private String postalCd;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "geo_id", nullable = false, length = 255)
	private Integer geoId;
	
	/**
	 * 
	 * @generated
	 */
	@Column(name = "comments", nullable = true, length = 400)
	private String comments;

	@Column(name = "algmnt_flag", nullable = false, length = 1)
	private Character algmntFlag;
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumns({
	 * 
	 * @JoinColumn(name = "sales_pos_id", referencedColumnName = "sales_pos_id",
	 * nullable = false, insertable = false, updatable = false),
	 * 
	 * @JoinColumn(name = "sales_hier_id", referencedColumnName =
	 * "sales_hier_id", nullable = false, insertable = false, updatable =
	 * false),
	 * 
	 * @JoinColumn(name = "postal_cd", referencedColumnName = "postal_cd",
	 * nullable = false, insertable = false, updatable = false) })
	 * 
	 * @Valid private TTerrZipAlgmnt tTerrZipAlgmnt;
	 */
//
//	@ManyToOne
//	@JoinColumns({
//			@JoinColumn(name = "chng_req_id", referencedColumnName = "chng_req_id", nullable = false),
//			@JoinColumn(name = "chng_req_detail_id", referencedColumnName = "chng_req_detail_id", nullable = false) })
//	@Valid
//	private TChngReqDetail tChngReqDetail;
	
	@ManyToOne
	@JoinColumn(name = "chng_req_id", referencedColumnName = "chng_req_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TChngReq tChngReq;
	
	@ManyToOne
	@JoinColumn(name = "chng_req_br_id", referencedColumnName = "chng_req_br_id", nullable = true, insertable = true, updatable = true)
	@Valid
	private TChngReqBussReason tChngReqBussReason;

	/**
	 * 
	 * @generated
	 */
	public TTerrZipAlgmntChngReqDet() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setZipChngReqId(final Integer zipChngReqId) {
		this.zipChngReqId = zipChngReqId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getZipChngReqId() {
		return this.zipChngReqId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setStatusId(final Integer statusId) {
		this.statusId = statusId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getStatusId() {
		return this.statusId;
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

	public void setReqDetail(final String reqDetail) {
		this.reqDetail = reqDetail;
	}

	/**
	 * 
	 * @generated
	 */
	public String getReqDetail() {
		return this.reqDetail;
	}

	/**
	 * 
	 * @generated
	 */

	public void setSalesPosId(final Long salesPosId) {
		this.salesPosId = salesPosId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getSalesPosId() {
		return this.salesPosId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setSalesHierId(final Long salesHierId) {
		this.salesHierId = salesHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getSalesHierId() {
		return this.salesHierId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPostalCd(final String postalCd) {
		this.postalCd = postalCd;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPostalCd() {
		return this.postalCd;
	}

	/**
	 * 
	 * @generated
	 */

	public void setGeoId(final Integer geoId) {
		this.geoId = geoId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getGeoId() {
		return this.geoId;
	}
	
	

	/**
	 * 
	 * @generated
	 */
	/*
	 * public TTerrZipAlgmnt getTTerrZipAlgmnt() { return this.tTerrZipAlgmnt; }
	 */
	/**
	 * 
	 * @generated
	 */
	/*
	 * public void setTTerrZipAlgmnt(final TTerrZipAlgmnt tTerrZipAlgmnt) {
	 * this.tTerrZipAlgmnt = tTerrZipAlgmnt; if (this.tTerrZipAlgmnt != null &&
	 * this.tTerrZipAlgmnt.getPostalCd()!= null) {
	 * 
	 * this.setPostalCd(this.tTerrZipAlgmnt.getPostalCd());
	 * 
	 * } if (this.tTerrZipAlgmnt != null &&
	 * this.tTerrZipAlgmnt.getTSalesPosGeoUnit
	 * ().getTSalesPosGeoUnitId().getSalesPosId() != null) {
	 * 
	 * this.setSalesPosId(this.tTerrZipAlgmnt.getTSalesPosGeoUnit().
	 * getTSalesPosGeoUnitId().getSalesPosId());
	 * 
	 * } if (this.tTerrZipAlgmnt != null &&
	 * this.tTerrZipAlgmnt.getTSalesPosGeoUnit
	 * ().getTSalesPosGeoUnitId().getSalesHierId() != null) {
	 * 
	 * this.setSalesHierId(this.tTerrZipAlgmnt.getTSalesPosGeoUnit().
	 * getTSalesPosGeoUnitId().getSalesHierId());
	 * 
	 * }
	 * 
	 * }
	 */

	public TChngReq getTChngReq() {
		return tChngReq;
	}

	public void setTChngReq(TChngReq tChngReq) {
		this.tChngReq = tChngReq;
	}
	
	

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	
	public Character getAlgmntFlag() {
		return algmntFlag;
	}

	public void setAlgmntFlag(Character algmntFlag) {
		this.algmntFlag = algmntFlag;
	}

	/**
	 * Gets the t chng req buss reason.
	 *
	 * @return the t chng req buss reason
	 */
	public TChngReqBussReason getTChngReqBussReason() {
		return tChngReqBussReason;
	}

	/**
	 * Sets the t chng req buss reason.
	 *
	 * @param tChngReqBussReason the new t chng req buss reason
	 */
	public void setTChngReqBussReason(TChngReqBussReason tChngReqBussReason) {
		this.tChngReqBussReason = tChngReqBussReason;
	}
	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TTerrZipAlgmntChngReqDet that) {
		setZipChngReqId(that.getZipChngReqId());
		setStatusId(that.getStatusId());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setReqDetail(that.getReqDetail());
		setSalesPosId(that.getSalesPosId());
		setSalesHierId(that.getSalesHierId());
		setPostalCd(that.getPostalCd());
		setGeoId(that.getGeoId());
		setComments(that.getComments());
		setAlgmntFlag(that.getAlgmntFlag());
	}

//	public TChngReqDetail gettChngReqDetail() {
//		return tChngReqDetail;
//	}
//
//	public void settChngReqDetail(TChngReqDetail tChngReqDetail) {
//		this.tChngReqDetail = tChngReqDetail;
//	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result
				+ ((zipChngReqId == null) ? 0 : zipChngReqId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("zipChngReqId=[").append(zipChngReqId).append("] ");
		buffer.append("statusId=[").append(statusId).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("reqDetail=[").append(reqDetail).append("] ");
		buffer.append("salesPosId=[").append(salesPosId).append("] ");
		buffer.append("salesHierId=[").append(salesHierId).append("] ");
		buffer.append("postalCd=[").append(postalCd).append("] ");
		buffer.append("geoId=[").append(geoId).append("] ");
		buffer.append("comments=[").append(comments).append("] ");
		buffer.append("algmntFlag=[").append(algmntFlag).append("] ");

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
		final TTerrZipAlgmntChngReqDet other = (TTerrZipAlgmntChngReqDet) obj;
		if (zipChngReqId == null) {
			if (other.zipChngReqId != null) {
				return false;
			}
		} else if (!zipChngReqId.equals(other.zipChngReqId)) {
			return false;
		}
		return true;
	}
}
