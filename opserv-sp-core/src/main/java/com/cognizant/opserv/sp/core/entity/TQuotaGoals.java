package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * JPA class representing TQuotaGoals The corresponding mapping table is
 * t_quota_goals
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTQuotaGoalss", query = "select myTQuotaGoals from TQuotaGoals myTQuotaGoals"),
		@NamedQuery(name = "CountTQuotaGoalss", query = "Select Count(c) from TQuotaGoals c"),
		@NamedQuery(name = "PreSaveCheck", query = "SELECT tqhc.thresholdValue, tqc.refinementType, tqg.adjustedGoal, "
				+ "(CASE WHEN tqc.quotaType ='Market Share' THEN tqg.baseMrktShare ELSE tqg.baseProdSales END) as baseline_val "
				+ "FROM TQuotaHierConf tqhc, TQuotaConfig tqc, TQuotaGoals tqg "
				+ "WHERE tqhc.tQuotaConfig.confId = ?1 AND tqhc.salesHierId = ?2 AND tqhc.tQuotaConfig.confId = tqc.confId "
				+ "AND tqhc.tQuotaConfig.confId = tqg.confId AND tqg.salesHierId IN ?3 AND tqg.prodCode = ?4 AND (tqg.prnSalesPosCode = ?5 OR tqg.salesPosCode = ?5)"),
		@NamedQuery(name = "SaveRfnmntData", query = "UPDATE TQuotaGoals tqg SET tqg.adjustedGrowthRate = ?1, tqg.adjustedGoal = ?2, "
				+ "tqg.comments = ?7 "
				+ "WHERE tqg.confId = ?3 AND tqg.salesPosCode = ?4 "
				+ "AND tqg.salesHierId = ?5 AND tqg.prodCode = ?6"),
		@NamedQuery(name = "GetRfnmntDataForApprv", query = "SELECT tqg.salesPosCode,tqg.salesHierId, "
				+ "(CASE WHEN tqc.quotaType = 'Market Share' THEN tqg.sugstdMrktShareGoal ELSE tqg.sugstdSalesVolGoal END) AS suggstedGoal, "
				+ "tqg.adjustedGoal,tqg.sugstdGrowthRate,tqg.adjustedGrowthRate, "
				+ "(CASE WHEN tqc.quotaType ='Market Share' THEN tqg.baseMrktShare ELSE tqg.baseProdSales END) as baseline_val, "
				+ "tqc.rolldownOpt "
				+ "FROM TQuotaGoals tqg, TQuotaConfig tqc WHERE tqg.confId = ?1 AND "
				+ "tqg.prnSalesPosCode = ?2 AND tqg.prnSalesHierId = ?3 "
				+ "AND tqg.prodCode = ?4 AND tqc.confId = tqg.confId order by tqg.salesHierId"),
		@NamedQuery(name = "FetchProductList", query = "select distinct tqg.prodCode,tqg.prodName "
				+ "from TQuotaGoals tqg "
				+ "where tqg.prnSalesPosCode = ?1 and tqg.prnSalesHierId = ?2"),
		@NamedQuery(name = "ApprvMarketQtRfnmnt", query = "UPDATE TQuotaGoals tqg "
				+ "SET tqg.sugstdGrowthRate = ?1, tqg.sugstdMrktShareGoal = ?2, tqg.adjustedGrowthRate = ?3, "
				+ "tqg.adjustedGoal = ?4 where tqg.confId = ?5 and tqg.salesPosCode = ?6 and tqg.salesHierId = ?7 "
				+ "and tqg.prodCode = ?8"),
		@NamedQuery(name = "ApprvSalesQtRfnmnt", query = "UPDATE TQuotaGoals tqg "
				+ "SET tqg.sugstdGrowthRate = ?1, tqg.sugstdSalesVolGoal = ?2, tqg.adjustedGrowthRate = ?3, "
				+ "tqg.adjustedGoal = ?4 where tqg.confId = ?5 and tqg.salesPosCode = ?6 and tqg.salesHierId = ?7 "
				+ "and tqg.prodCode = ?8"),
		@NamedQuery(name = "RejectMarketQtRfnmnt", query = "UPDATE TQuotaGoals tqg "
				+ "SET tqg.adjustedGrowthRate = tqg.sugstdGrowthRate, "
				+ "tqg.adjustedGoal = tqg.sugstdMrktShareGoal where tqg.confId = ?1 and tqg.salesPosCode = ?2 and "
				+ "tqg.salesHierId = ?3 and tqg.prodCode = ?4"),
		@NamedQuery(name = "RejectSalesQtRfnmnt", query = "UPDATE TQuotaGoals tqg "
				+ "SET tqg.adjustedGrowthRate = tqg.sugstdGrowthRate, "
				+ "tqg.adjustedGoal = tqg.sugstdSalesVolGoal where tqg.confId = ?1 and tqg.salesPosCode = ?2 "
				+ "and tqg.salesHierId = ?3 and tqg.prodCode = ?4"),
		@NamedQuery(name = "FindSalesHierID", query = "select distinct(tqg.salesHierId) from TQuotaGoals tqg "
				+ "where tqg.prnSalesHierId = ?1 and tqg.prodCode = ?2 and tqg.confId = ?3") })
@Table(name = "t_quota_goals", uniqueConstraints = @UniqueConstraint(columnNames = { "goal_id" }))
public class TQuotaGoals implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "goal_id", nullable = false, length = 255)
	private Long goalId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "conf_id", nullable = false, length = 255)
	private Long confId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "sales_pos_code", nullable = true, length = 20)
	private String salesPosCode;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "sales_hier_id", nullable = true, length = 255)
	private Long salesHierId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "prn_sales_pos_code", nullable = true, length = 20)
	private String prnSalesPosCode;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "prn_sales_hier_id", nullable = true, length = 255)
	private Long prnSalesHierId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 75)
	@Column(name = "sales_pos_name", nullable = true, length = 75)
	private String salesPosName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "prod_code", nullable = true, length = 50)
	private String prodCode;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "prod_name", nullable = true, length = 200)
	private String prodName;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "custom_fields", nullable = true, length = 255)
	@Lob
	private byte[] customFields;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "base_prod_sales", nullable = true, length = 255)
	private Double baseProdSales;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "base_mrkt_sales", nullable = true, length = 255)
	private Double baseMrktSales;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "base_mrkt_share", nullable = true, length = 255)
	private Double baseMrktShare;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "sugstd_growth_rate", nullable = true, length = 255)
	private Double sugstdGrowthRate;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "sugstd_mrkt_share_goal", nullable = true, length = 255)
	private Double sugstdMrktShareGoal;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "sugstd_sales_vol_goal", nullable = true, length = 255)
	private Double sugstdSalesVolGoal;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "adjusted_growth_rate", nullable = true, length = 255)
	private Double adjustedGrowthRate;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "adjusted_goal", nullable = true, length = 255)
	private Double adjustedGoal;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "monthly_goal", nullable = true, length = 255)
	private Double monthlyGoal;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 500)
	@Column(name = "comments", nullable = true, length = 500)
	private String comments;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "orig_growth_rate", nullable = true, length = 255)
	private Double origGrowthRate;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "orig_mrkt_share_goal", nullable = true, length = 255)
	private Double origMrktShareGoal;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "orig_sales_vol_goal", nullable = true, length = 255)
	private Double origSalesVolGoal;

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
	@Column(name = "created_dt", nullable = true, length = 19)
	private Date createdDt;

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
	@Column(name = "updated_dt", nullable = true, length = 19)
	private Date updatedDt;

	/**
	 * 
	 * @generated
	 */
	public TQuotaGoals() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setGoalId(final Long goalId) {
		this.goalId = goalId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getGoalId() {
		return this.goalId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setConfId(final Long confId) {
		this.confId = confId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getConfId() {
		return this.confId;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPrnSalesPosCode() {
		return prnSalesPosCode;
	}

	/**
	 * 
	 * @generated
	 */
	public void setPrnSalesPosCode(String prnSalesPosCode) {
		this.prnSalesPosCode = prnSalesPosCode;
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

	public void setPrnSalesHierId(final Long prnSalesHierId) {
		this.prnSalesHierId = prnSalesHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getPrnSalesHierId() {
		return this.prnSalesHierId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setSalesPosName(final String salesPosName) {
		this.salesPosName = salesPosName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getSalesPosName() {
		return this.salesPosName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setSalesPosCode(final String salesPosCode) {
		this.salesPosCode = salesPosCode;
	}

	/**
	 * 
	 * @generated
	 */
	public String getSalesPosCode() {
		return this.salesPosCode;
	}

	/**
	 * 
	 * @generated
	 */

	public void setProdCode(final String prodCode) {
		this.prodCode = prodCode;
	}

	/**
	 * 
	 * @generated
	 */
	public String getProdCode() {
		return this.prodCode;
	}

	/**
	 * 
	 * @generated
	 */

	public void setProdName(final String prodName) {
		this.prodName = prodName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getProdName() {
		return this.prodName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCustomFields(final byte[] customFields) {
		this.customFields = customFields;
	}

	/**
	 * 
	 * @generated
	 */
	public byte[] getCustomFields() {
		return this.customFields;
	}

	/**
	 * 
	 * @generated
	 */

	public void setBaseProdSales(final Double baseProdSales) {
		this.baseProdSales = baseProdSales;
	}

	/**
	 * 
	 * @generated
	 */
	public Double getBaseProdSales() {
		return this.baseProdSales;
	}

	/**
	 * 
	 * @generated
	 */

	public void setBaseMrktSales(final Double baseMrktSales) {
		this.baseMrktSales = baseMrktSales;
	}

	/**
	 * 
	 * @generated
	 */
	public Double getBaseMrktSales() {
		return this.baseMrktSales;
	}

	/**
	 * 
	 * @generated
	 */

	public void setBaseMrktShare(final Double baseMrktShare) {
		this.baseMrktShare = baseMrktShare;
	}

	/**
	 * 
	 * @generated
	 */
	public Double getBaseMrktShare() {
		return this.baseMrktShare;
	}

	/**
	 * 
	 * @generated
	 */

	public void setSugstdGrowthRate(final Double sugstdGrowthRate) {
		this.sugstdGrowthRate = sugstdGrowthRate;
	}

	/**
	 * 
	 * @generated
	 */
	public Double getSugstdGrowthRate() {
		return this.sugstdGrowthRate;
	}

	/**
	 * 
	 * @generated
	 */

	public void setSugstdMrktShareGoal(final Double sugstdMrktShareGoal) {
		this.sugstdMrktShareGoal = sugstdMrktShareGoal;
	}

	/**
	 * 
	 * @generated
	 */
	public Double getSugstdMrktShareGoal() {
		return this.sugstdMrktShareGoal;
	}

	/**
	 * 
	 * @generated
	 */

	public void setSugstdSalesVolGoal(final Double sugstdSalesVolGoal) {
		this.sugstdSalesVolGoal = sugstdSalesVolGoal;
	}

	/**
	 * 
	 * @generated
	 */
	public Double getSugstdSalesVolGoal() {
		return this.sugstdSalesVolGoal;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAdjustedGrowthRate(final Double adjustedGrowthRate) {
		this.adjustedGrowthRate = adjustedGrowthRate;
	}

	/**
	 * 
	 * @generated
	 */
	public Double getAdjustedGrowthRate() {
		return this.adjustedGrowthRate;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAdjustedGoal(final Double adjustedGoal) {
		this.adjustedGoal = adjustedGoal;
	}

	/**
	 * 
	 * @generated
	 */
	public Double getAdjustedGoal() {
		return this.adjustedGoal;
	}

	/**
	 * 
	 * @generated
	 */

	public void setMonthlyGoal(final Double monthlyGoal) {
		this.monthlyGoal = monthlyGoal;
	}

	/**
	 * 
	 * @generated
	 */
	public Double getMonthlyGoal() {
		return this.monthlyGoal;
	}

	/**
	 * 
	 * @generated
	 */

	public void setComments(final String comments) {
		this.comments = comments;
	}

	/**
	 * 
	 * @generated
	 */
	public String getComments() {
		return this.comments;
	}

	/**
	 * 
	 * @generated
	 */

	public void setOrigGrowthRate(final Double origGrowthRate) {
		this.origGrowthRate = origGrowthRate;
	}

	/**
	 * 
	 * @generated
	 */
	public Double getOrigGrowthRate() {
		return this.origGrowthRate;
	}

	/**
	 * 
	 * @generated
	 */

	public void setOrigMrktShareGoal(final Double origMrktShareGoal) {
		this.origMrktShareGoal = origMrktShareGoal;
	}

	/**
	 * 
	 * @generated
	 */
	public Double getOrigMrktShareGoal() {
		return this.origMrktShareGoal;
	}

	/**
	 * 
	 * @generated
	 */

	public void setOrigSalesVolGoal(final Double origSalesVolGoal) {
		this.origSalesVolGoal = origSalesVolGoal;
	}

	/**
	 * 
	 * @generated
	 */
	public Double getOrigSalesVolGoal() {
		return this.origSalesVolGoal;
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

	public void setCreatedDt(final Date createdDt) {
		if (createdDt != null) {
			this.createdDt = (Date) createdDt.clone();
		} else {
			this.createdDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getCreatedDt() {
		if (this.createdDt != null) {
			return (Date) this.createdDt.clone();
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

	public void setUpdatedDt(final Date updatedDt) {
		if (updatedDt != null) {
			this.updatedDt = (Date) updatedDt.clone();
		} else {
			this.updatedDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getUpdatedDt() {
		if (this.updatedDt != null) {
			return (Date) this.updatedDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TQuotaGoals that) {
		setGoalId(that.getGoalId());
		setConfId(that.getConfId());
		setSalesPosCode(that.getSalesPosCode());
		setSalesHierId(that.getSalesHierId());
		setPrnSalesPosCode(that.getPrnSalesPosCode());
		setPrnSalesHierId(that.getPrnSalesHierId());
		setSalesPosName(that.getSalesPosName());
		setSalesPosCode(that.getSalesPosCode());
		setProdCode(that.getProdCode());
		setProdName(that.getProdName());
		setCustomFields(that.getCustomFields());
		setBaseProdSales(that.getBaseProdSales());
		setBaseMrktSales(that.getBaseMrktSales());
		setBaseMrktShare(that.getBaseMrktShare());
		setSugstdGrowthRate(that.getSugstdGrowthRate());
		setSugstdMrktShareGoal(that.getSugstdMrktShareGoal());
		setSugstdSalesVolGoal(that.getSugstdSalesVolGoal());
		setAdjustedGrowthRate(that.getAdjustedGrowthRate());
		setAdjustedGoal(that.getAdjustedGoal());
		setMonthlyGoal(that.getMonthlyGoal());
		setComments(that.getComments());
		setOrigGrowthRate(that.getOrigGrowthRate());
		setOrigMrktShareGoal(that.getOrigMrktShareGoal());
		setOrigSalesVolGoal(that.getOrigSalesVolGoal());
		setCreatedBy(that.getCreatedBy());
		setCreatedDt(that.getCreatedDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdatedDt(that.getUpdatedDt());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((goalId == null) ? 0 : goalId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("goalId=[").append(goalId).append("] ");
		buffer.append("confId=[").append(confId).append("] ");
		buffer.append("salesPosId=[").append(salesPosCode).append("] ");
		buffer.append("salesHierId=[").append(salesHierId).append("] ");
		buffer.append("prnSalesPosId=[").append(prnSalesPosCode).append("] ");
		buffer.append("prnSalesHierId=[").append(prnSalesHierId).append("] ");
		buffer.append("salesPosName=[").append(salesPosName).append("] ");
		buffer.append("salesPosCode=[").append(salesPosCode).append("] ");
		buffer.append("prodCode=[").append(prodCode).append("] ");
		buffer.append("prodName=[").append(prodName).append("] ");
		buffer.append("customFields=[").append(customFields).append("] ");
		buffer.append("baseProdSales=[").append(baseProdSales).append("] ");
		buffer.append("baseMrktSales=[").append(baseMrktSales).append("] ");
		buffer.append("baseMrktShare=[").append(baseMrktShare).append("] ");
		buffer.append("sugstdGrowthRate=[").append(sugstdGrowthRate)
				.append("] ");
		buffer.append("sugstdMrktShareGoal=[").append(sugstdMrktShareGoal)
				.append("] ");
		buffer.append("sugstdSalesVolGoal=[").append(sugstdSalesVolGoal)
				.append("] ");
		buffer.append("adjustedGrowthRate=[").append(adjustedGrowthRate)
				.append("] ");
		buffer.append("adjustedGoal=[").append(adjustedGoal).append("] ");
		buffer.append("monthlyGoal=[").append(monthlyGoal).append("] ");
		buffer.append("comments=[").append(comments).append("] ");
		buffer.append("origGrowthRate=[").append(origGrowthRate).append("] ");
		buffer.append("origMrktShareGoal=[").append(origMrktShareGoal)
				.append("] ");
		buffer.append("origSalesVolGoal=[").append(origSalesVolGoal)
				.append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createdDt=[").append(createdDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updatedDt=[").append(updatedDt).append("] ");

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
		final TQuotaGoals other = (TQuotaGoals) obj;
		if (goalId == null) {
			if (other.goalId != null) {
				return false;
			}
		} else if (!goalId.equals(other.goalId)) {
			return false;
		}
		return true;
	}
}
