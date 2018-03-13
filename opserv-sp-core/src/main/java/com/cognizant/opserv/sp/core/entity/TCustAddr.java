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
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.Length;

/**
 * JPA class representing TCustAddr The corresponding mapping table is
 * t_cust_addr
 */
@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTCustAddrs", query = "select myTCustAddr from TCustAddr myTCustAddr"),
		@NamedQuery(name = "CountTCustAddrs", query = "Select Count(c) from TCustAddr c"),
		@NamedQuery(name = "FindPriAddressForCustomer", query = "select myTCustAddr from TCustAddr myTCustAddr where myTCustAddr.custId = ?1 and  myTCustAddr.prAddrFlag =?2 and myTCustAddr.activeFlag =?3 and myTCustAddr.tenantId =?4"),
		@NamedQuery(name = "FindTCustAddrByTCust", query = "select myTCustAddr from TCustAddr myTCustAddr where myTCustAddr.addrId = ?1 "),
		@NamedQuery(name = "CountTCustAddrsByTCust", query = "select Count(*) from TCustAddr myTCustAddr where myTCustAddr.addrId = ?1 "),
		@NamedQuery(name = "FindTCustAddrByTAddrType", query = "select myTCustAddr from TCustAddr myTCustAddr where myTCustAddr.addrId = ?1 "),
		@NamedQuery(name = "CountTCustAddrsByTAddrType", query = "select Count(*) from TCustAddr myTCustAddr where myTCustAddr.addrId = ?1 "),
        @NamedQuery(name = "FindTCustAddrByPostalcode", query = "select  mycust.custId,mycust.custName,myTterr.tSalesPos.salesPosId,myTterr.tSalesPos.tAlgmntSalesHier.salesHierId,myTcustAddr.postalCd,mycust.custCd " +
				                                            " from TCust mycust,TTerrZipAlgmnt myTterr,TCustAlgmnt myTcustAlg,TCustAddr myTcustAddr,TSalesPos tsp"+
                                                            " where myTcustAddr.postalCd=myTterr.postalCd"+
															" and myTterr.postalCd =?5"+
															" and myTcustAlg.tenantId=myTterr.tenantId"+
															" and myTterr.tenantId=mycust.tenantId"+
															" and myTterr.tenantId=?6"+
															" and myTcustAddr.prAddrFlag='Y'"+
															" and myTterr.tSalesPos.salesPosId=myTcustAlg.salesPosId"+
															" and myTterr.tSalesPos.tAlgmntSalesHier.salesHierId=myTcustAlg.salesHierId"+
															" and myTcustAlg.algmntId=?1"+
															" and myTcustAlg.bussUnitId=?2"+
															" and myTcustAlg.salesTeamId=?3"+
															" and tsp.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = myTcustAlg.algmntId"+
															" and tsp.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId =myTcustAlg.bussUnitId"+
															" and tsp.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId =myTcustAlg.salesTeamId"+
															" and tsp.tAlgmntSalesHier.salesHierId = myTcustAlg.salesHierId"+
															" and tsp.salesPosId = myTcustAlg.salesPosId"+
															" and tsp.salesPosId=?4"+
															" and mycust.custId=myTcustAlg.tCust.custId"+
															" and mycust.custId=myTcustAddr.custId"+
															" and mycust.activeFlag='Y'"+
															" and myTcustAlg.activeFlag='Y'"+
															" group by myTcustAlg.salesPosId,myTcustAddr.custId"+
															" order by myTcustAlg.salesPosId,myTcustAddr.postalCd ") ,
															
   @NamedQuery(name = "FindTCustAddrByAssign", query =  "select  mycust.custId,mycust.custName,mycust.custCd, myTterr.tSalesPos.salesPosId,myTterr.tSalesPos.tAlgmntSalesHier.salesHierId,myTcustAddr.postalCd,tsp.posCd,tsp.posName, count(mycust.custId) " +
	        		                                                " from TCust mycust,TTerrZipAlgmnt myTterr,TCustAlgmnt myTcustAlg,TCustAddr myTcustAddr,TSalesPos tsp"+
	                                                                " where myTcustAddr.postalCd=myTterr.postalCd"+
					                                                " and myTterr.postalCd IN ?6"+
																	" and myTcustAlg.tenantId=myTterr.tenantId"+
																	" and myTterr.tenantId=mycust.tenantId"+
																	" and myTterr.tenantId=?7"+
																	" and myTcustAddr.prAddrFlag='Y'"+
																	" and myTcustAddr.activeFlag='Y'"+
																	" and myTterr.tSalesPos.salesPosId=?4"+
																	" and myTterr.tSalesPos.tAlgmntSalesHier.salesHierId=?5"+
																	" and myTterr.tSalesPos.salesPosId=myTcustAlg.salesPosId"+
																	" and myTterr.tSalesPos.tAlgmntSalesHier.salesHierId=myTcustAlg.salesHierId"+
																	" and myTcustAlg.algmntId=?1"+
																	" and myTcustAlg.bussUnitId=?2"+
																	" and myTcustAlg.salesTeamId=?3"+
																	" and tsp.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = myTcustAlg.algmntId"+
																	" and tsp.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId =myTcustAlg.bussUnitId"+
																	" and tsp.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId =myTcustAlg.salesTeamId"+
																	" and tsp.tAlgmntSalesHier.salesHierId = myTcustAlg.salesHierId"+
																	" and tsp.salesPosId = myTcustAlg.salesPosId"+
																	" and mycust.custId=myTcustAlg.tCust.custId"+
																	" and mycust.custId=myTcustAddr.custId"+
																	" and mycust.activeFlag='Y'"+
																	" and myTcustAlg.activeFlag='Y'"+
																	" group by myTcustAlg.salesPosId,myTcustAddr.custId"),

		@NamedQuery(name = "FindTCustAddrForUnAssign", query = " select  mycust.custId,mycust.custName,mycust.custCd,myTcustAddr.postalCd" +
	                                                       " from  TCust mycust,TCustAddr myTcustAddr"+
	                                                       " where myTcustAddr.postalCd IN ?1"+
	                                                       " and   myTcustAddr.prAddrFlag='Y'"+
	                                                       " and   mycust.custId=myTcustAddr.custId"+
	                                                       " and   mycust.activeFlag='Y'"+
	                                                       " and   myTcustAddr.activeFlag='Y'"+
	                                                       " and   myTcustAddr.tenantId=?2 "+
	                                                       " and   myTcustAddr.tenantId =mycust.tenantId"+
	                                                       " group by myTcustAddr.custId"),
		@NamedQuery(name = "FindPriAddressForAllCustomer", query = "select myTCustAddr from TCustAddr myTCustAddr where myTCustAddr.custId IN(?1)" +
				" and  myTCustAddr.prAddrFlag ='Y' and myTCustAddr.activeFlag ='Y' and myTCustAddr.tenantId =?2"),
		@NamedQuery(name = "FindCountryForAllCustomer", query = "select distinct(myTCustAddr.cntry) from TCustAddr myTCustAddr where myTCustAddr.tenantId =?1"),
		@NamedQuery(name = "FindCountryForState", query = "select distinct(myTCustAddr.state) from TCustAddr myTCustAddr where myTCustAddr.tenantId =?1 and myTCustAddr.activeFlag = 'Y' and myTCustAddr.prAddrFlag = 'Y'"),
		@NamedQuery(name = "FindAddrOfAllTCustsByIDS", query = "select myTCustAddr from TCustAddr myTCustAddr where myTCustAddr.tCust.custId IN ?1  " +
				"and myTCustAddr.tenantId = ?2 and myTCustAddr.prAddrFlag = 'Y' and myTCustAddr.activeFlag = 'Y' "),
		@NamedQuery(name = "FindTCustAddrByIds", query = "select myTCustAddr from TCustAddr myTCustAddr where myTCustAddr.addrId IN ?1 and myTCustAddr.tenantId = ?2"),
		@NamedQuery(name = "FindAllActiveAddrByCustIds", query = "select myTCustAddr.tCust.custId, myTCustAddr.addrId, myTCustAddr.addrTypeId, myTCustAddr.city,  myTCustAddr.cntry, myTCustAddr.postalCd," +
				" myTCustAddr.state, myTCustAddr.streetName, myTCustAddr.activeFlag, myTCustAddr.prAddrFlag from TCustAddr myTCustAddr " +
				" where myTCustAddr.tCust.custId IN ?1  and myTCustAddr.tenantId = ?2 and myTCustAddr.activeFlag = 'Y' "),
		@NamedQuery(name = "FindCustFrAssZip", query = "select  count(tCustAddr.custId),tCustAddr.postalCd, tCustAl.salesPosId" +
    		                                                " from TCustAddr tCustAddr, TCustAlgmnt tCustAl,TTerrZipAlgmnt tterrZip,TSalesPos tsp"+
    		                                                " where tCustAddr.postalCd IN ?6" +
    		                                                " and tCustAddr.prAddrFlag='Y'" +
															" and tCustAddr.activeFlag='Y'" +
    		                                                " and tCustAddr.custId =tCustAl.tCust.custId" +
															" and tCustAddr.tenantId =tCustAl.tenantId" +
    		                                                " and tCustAl.activeFlag = 'Y'" +
															" and tCustAl.algmntId = ?1" +
    		                                                " and tCustAl.bussUnitId = ?2" +
															" and tCustAl.salesTeamId = ?3" +
    		                                                " and tCustAl.salesPosId in ?4" +
															" and tCustAl.salesHierId in ?5" +
															" and tCustAl.tenantId = ?7" +
    		                                                " and tCustAddr.postalCd = tterrZip.postalCd" +
															" and tCustAddr.tenantId = tterrZip.tenantId" +
    		                                                " and tterrZip.assignedFlag = 'Y'" +
															" and tterrZip.activeFlag = 'Y'" + 
    		                                                " and tterrZip.tSalesPos.salesPosId = tCustAl.salesPosId" +
															" and tterrZip.tSalesPos.tAlgmntSalesHier.salesHierId = tCustAl.salesHierId" +
    		                                                " and tterrZip.tenantId = tCustAl.tenantId" +
															" and tterrZip.tSalesPos.salesPosId = tsp.salesPosId" +
    		                                                " and tterrZip.tSalesPos.tAlgmntSalesHier.salesHierId = tsp.tAlgmntSalesHier.salesHierId" +
															" and tterrZip.tenantId = tsp.tenantId group by tCustAddr.postalCd, tCustAl.salesPosId"),
		@NamedQuery(name = "FindCustFrUnAssZip", query = "select myTCust.custId, myTCust.custCd, myTCustAddr.postalCd, myTCust.custName from TCustAddr myTCustAddr, TCust myTCust where myTCustAddr.postalCd IN ?1 AND myTCustAddr.custId = myTCust.custId AND myTCustAddr.activeFlag = 'Y' AND myTCustAddr.prAddrFlag = 'Y' "),
		@NamedQuery(name = "FindSearchForState", query = "select distinct(myTCustAddr.state) from TCustAddr myTCustAddr where myTCustAddr.tenantId =?1 and myTCustAddr.state like ?2 and myTCustAddr.activeFlag = 'Y' and myTCustAddr.prAddrFlag = 'Y'"),
		@NamedQuery(name = "FindAllCustomersAddrList", query = "select c.tCust.custId, c.addrId, c.streetName, c.city, c.state, c.cntry, c.postalCd, c.prAddrFlag " +
				"from TCustAddr c where c.tCust.custId IN ?1 AND c.tenantId =?2 AND c.activeFlag = 'Y'"),
		@NamedQuery(name = "FindAllSecAddrOfTCustsByIDS", query = "select c from TCustAddr c where c.tCust.custId IN ?1  " +
				"and c.tenantId = ?2 and c.prAddrFlag = 'N' AND c.activeFlag = 'Y' "),
				// newly added for secondary address validation	
		@NamedQuery(name = "FindAllAddrOfTCustsByIDS", query = "select myTCustAddr from TCustAddr myTCustAddr where myTCustAddr.tCust.custId IN ?1  " +
				"and myTCustAddr.tenantId = ?2"), 
		@NamedQuery(name = "FindAllCustomersPrimaryAddrList", query = "select c.tCust.custId, c.addrId, c.streetName, c.city, c.state, c.cntry, c.postalCd, c.prAddrFlag " +
				"from TCustAddr c where c.tCust.custId IN ?1 AND c.tenantId =?2 AND c.prAddrFlag= 'Y' AND c.activeFlag = 'Y'"),
		@NamedQuery(name = "FindAddrByAddrIds", query = "select c from TCustAddr c where c.addrId IN ?1 AND c.tenantId =?2 "),
		})
@Table(name = "t_cust_addr")
public class TCustAddr implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */	
	@NotNull
	@Column(name = "cust_id", nullable = false, length = 255)
	private Integer custId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "addr_id", nullable = false, length = 255)
    private Integer addrId;


	@NotNull
	@Column(name = "addr_type_id", nullable = false, length = 255)
	private Short addrTypeId;

	/**

	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "door_num", nullable = true, length = 20)
	private String doorNum;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "street_name", nullable = true, length = 50)
	private String streetName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "addr_line", nullable = true, length = 50)
	private String addrLine;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "city", nullable = true, length = 200)
	private String city;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "state", nullable = true, length = 200)
	private String state;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "cntry", nullable = true, length = 200)
	private String cntry;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "pr_addr_flag", nullable = true, length = 1)
	private Character prAddrFlag;

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
	@NotNull
	@Column(name = "created_by", nullable = false, length = 255,  updatable=false)
	private Integer createdBy;

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "create_dt", nullable = false, length = 19, updatable=false)
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
	@Column(name = "postal_cd", nullable = true, length = 20)
	private String postalCd;
	
	 /** 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "postal_extn", nullable = true, length = 20)
	private String postalExtn;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "addr_line_2", nullable = true, length = 100)
	private String addrLine2;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "addr_line_3", nullable = true, length = 100)
	private String addrLine3;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "addr_line_4", nullable = true, length = 100)
	private String addrLine4;
	
	@ManyToOne
	@JoinColumn(name = "cust_id", referencedColumnName = "cust_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@NotAudited
	private TCust tCust;
	
	
	@Length(max = 20)
	@Column(name = "addr_cd", nullable = true, length = 20)
	private String addrCd;
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "addr_type_id", referencedColumnName = "addr_type_id",
	 * nullable = false, insertable = true, updatable = true)
	 * 
	 * @Valid private TAddrType tAddrType;
	 */

	
	public String getAddrCd() {
		return addrCd;
	}

	public void setAddrCd(String addrCd) {
		this.addrCd = addrCd;
	}

	/**
	 * 
	 * @generated
	 */
	/*public TCustAddr() {
	}
*/
/*	*//**
	 * 
	 * @generated
	 *//*

	public void setTCustAddrId(final TCustAddrId tCustAddrId) {
		this.tCustAddrId = tCustAddrId;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public TCustAddrId getTCustAddrId() {
		return this.tCustAddrId;
	}*/

	/**
	 * 
	 * @generated
	 */

	public void setDoorNum(final String doorNum) {
		this.doorNum = doorNum;
	}

	/**
	 * 
	 * @generated
	 */
	public String getDoorNum() {
		return this.doorNum;
	}

	/**
	 * 
	 * @generated
	 */

	public void setStreetName(final String streetName) {
		this.streetName = streetName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getStreetName() {
		return this.streetName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAddrLine(final String addrLine) {
		this.addrLine = addrLine;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAddrLine() {
		return this.addrLine;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCity(final String city) {
		this.city = city;
	}

	/**
	 * 
	 * @generated
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * 
	 * @generated
	 */

	public void setState(final String state) {
		this.state = state;
	}

	/**
	 * 
	 * @generated
	 */
	public String getState() {
		return this.state;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCntry(final String cntry) {
		this.cntry = cntry;
	}

	/**
	 * 
	 * @generated
	 */
	public String getCntry() {
		return this.cntry;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPrAddrFlag(final Character prAddrFlag) {
		this.prAddrFlag = prAddrFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getPrAddrFlag() {
		return this.prAddrFlag;
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

	public void custAddList(final Integer createdBy) {
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
	/*public TCust getTCust() {
		return this.tCust;
	}*/

/*	*//**
	 * 
	 * @generated
	 */
	/*public void setTCust(final TCust tCust) {
		this.tCust = tCust;
		if (this.tCust != null && this.tCust.getCustId() != null) {

			this.tCustAddrId.setCustId(this.tCust.getCustId());

		}

	}*/

	/**
	 * 
	 * @generated
	 */
	/*
	 * public TAddrType getTAddrType() { return this.tAddrType; }
	 *//**
	 * 
	 * @generated
	 */
	/*
	 * public void setTAddrType(final TAddrType tAddrType) { this.tAddrType =
	 * tAddrType;
	 * 
	 * }
	 */

	public Short getAddrTypeId() {
		return addrTypeId;
	}

	public void setAddrTypeId(Short addrTypeId) {
		this.addrTypeId = addrTypeId;
	}
	
	
	
	

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public Integer getAddrId() {
		return addrId;
	}

	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPostalExtn(final String postalExtn) {
		this.postalExtn = postalExtn;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPostalExtn() {
		return this.postalExtn;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAddrLine2(final String addrLine2) {
		this.addrLine2 = addrLine2;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAddrLine2() {
		return this.addrLine2;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAddrLine3(final String addrLine3) {
		this.addrLine3 = addrLine3;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAddrLine3() {
		return this.addrLine3;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAddrLine4(final String addrLine4) {
		this.addrLine4 = addrLine4;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAddrLine4() {
		return this.addrLine4;
	}
	
	/**
	 * 
	 * @generated
	 */
	public TCust getTCust() {
		return tCust;
	}
	
	/**
	 * 
	 * @generated
	 */
	public void setTCust(TCust tCust) {
		this.tCust = tCust;
	}
	
	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCustAddr that) {
		setAddrId(that.getAddrId());
		setCustId(that.getCustId());
		setDoorNum(that.getDoorNum());
		setStreetName(that.getStreetName());
		setAddrLine(that.getAddrLine());
		setCity(that.getCity());
		setState(that.getState());
		setCntry(that.getCntry());
		setPrAddrFlag(that.getPrAddrFlag());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setPostalCd(that.getPostalCd());
		setAddrCd(that.getAddrCd());
		setPostalExtn(that.getPostalExtn());
		setAddrLine2(that.getAddrLine2());
		setAddrLine3(that.getAddrLine3());
		setAddrLine4(that.getAddrLine4());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result
				+ ((addrId == null) ? 0 : addrId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tCustAddrId=[").append(addrId).append("] ");
		buffer.append("doorNum=[").append(doorNum).append("] ");
		buffer.append("streetName=[").append(streetName).append("] ");
		buffer.append("addrLine=[").append(addrLine).append("] ");
		buffer.append("city=[").append(city).append("] ");
		buffer.append("state=[").append(state).append("] ");
		buffer.append("cntry=[").append(cntry).append("] ");
		buffer.append("prAddrFlag=[").append(prAddrFlag).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("postalCd=[").append(postalCd).append("] ");
		buffer.append("addrCd=[").append(addrCd).append("] ");
		buffer.append("postalExtn=[").append(postalExtn).append("] ");
		buffer.append("addrLine2=[").append(addrLine2).append("] ");
		buffer.append("addrLine3=[").append(addrLine3).append("] ");
		buffer.append("addrLine4=[").append(addrLine4).append("] ");

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
		final TCustAddr other = (TCustAddr) obj;
		if (addrId == null) {
			if (other.addrId != null) {
				return false;
			}
		} else if (!addrId.equals(other.addrId)) {
			return false;
		}
		return true;
	}
}
