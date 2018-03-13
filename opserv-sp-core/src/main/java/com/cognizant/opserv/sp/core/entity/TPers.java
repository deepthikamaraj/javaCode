package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.jasypt.hibernate4.type.EncryptedStringType;

/**
 * JPA class representing TPers The corresponding mapping table is t_pers
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTPerss", query = "select myTPers from TPers myTPers"),
		@NamedQuery(name = "CountTPerss", query = "Select Count(c) from TPers c"),						
		@NamedQuery(name = "FindTPersByTPers", query = "select myTPers from TPers myTPers where myTPers.managerId = ?1 "),
		
		@NamedQuery(name = "FindTPersByName", query = "select myTPers from TPers myTPers where myTPers.firstName like ?1 AND myTPers.lastName like ?2 AND myTPers.tenantId = ?3"),
		@NamedQuery(name = "FindTPersByUserId", query = "select myTPers.staffId,myTPers.firstName,myTPers.middleName,myTPers.lastName,myTPers.iattainId,myTPers.birthDtStr,myTPers.gender from TPers myTPers where myTPers.userId = ?1 AND myTPers.tenantId = ?2 "),
		
		@NamedQuery(name = "FindTPersByClientId", query = "select myTPers from TPers myTPers where myTPers.clientId = ?1 AND myTPers.tenantId = ?2 "),
		
		@NamedQuery(name = "getTpersById", query = "select myTPers from TPers myTPers, TPersStatus myTPersStatus where "
				 									+ "myTPers.userId = ?1 AND myTPersStatus.locale = ?2 "
				 									+ "AND myTPersStatus.tPersStatusId.statusId = myTPers.statusId "
				 									+ "AND myTPers.tenantId = ?3 "
				 									+ "AND myTPers.tenantId = myTPersStatus.tenantId "),
		@NamedQuery(name = "FindTPersByUserIdFrComm", query = "select myTPers.empName from TPers myTPers where myTPers.userId = ?1 AND myTPers.tenantId = ?2 "),
		@NamedQuery(name = "FindTPersDetailsByStaffId", query = "select myTPers.staffId,myTPers.firstName,myTPers.lastName,myTPers.birthDtStr,myTPersStatus.statusDesc,myTPers.gender,myTPers.empName,myTPers.clientId from " 
													+ "TPers myTPers,TPersStatus myTPersStatus where myTPers.statusId=myTPersStatus.tPersStatusId.statusId AND myTPers.staffId = ?1 AND " 
													+ "myTPersStatus.tPersStatusId.localeId = ?2 "),
		@NamedQuery(name = "findTPersSingleByUserId", query = "select myTPers from TPers myTPers where myTPers.userId = ?1 AND myTPers.tenantId = ?2 "),
		@NamedQuery(name = "FindTPersDtlsByUserId", query = "select myTPers from TPers myTPers where myTPers.userId = ?1 "),
		
		@NamedQuery(name = "findTPersSingleBystaffId", query = "select myTPers from TPers myTPers where myTPers.staffId = ?1 AND myTPers.tenantId = ?2 "),
		/*
		 * @NamedQuery(name = "CountTPerssByTPers", query =
		 * "select Count(myTPers) from TPers myTPers where myTPers.tPers = ?1 ")
		 */
		@NamedQuery(name = "GetTPersInfoByUserIds", query = "select myTPers.userId,myTPers.staffId,myTPers.firstName,myTPers.middleName,myTPers.lastName,myTPers.iattainId,myTPers.birthDtStr,myTPers.gender from TPers myTPers where myTPers.userId IN ?1 AND myTPers.tenantId = ?2 "),
		@NamedQuery(name = "FindChngReqApprovers", query = "select myTPers from TPers myTPers,TChngReqAppr myTChngReqAppr,TEmpAlgmnt myTEmpAlgmnt WHERE myTEmpAlgmnt.salesPosId=myTChngReqAppr.apprSalesPosId AND myTEmpAlgmnt.tPers.staffId=myTPers.staffId AND myTChngReqAppr.tChngReq.chngReqId=?1 AND myTChngReqAppr.targetApprFlag=?2 AND myTEmpAlgmnt.allocTypeId=?3 AND myTPers.tenantId = ?4 "),
		@NamedQuery(name = "FindTPersBySalesPosId", query = "select myTPers from TPers myTPers,TEmpAlgmnt myTEmpAlgmnt WHERE  myTEmpAlgmnt.salesPosId=?1 AND myTEmpAlgmnt.tPers.staffId=myTPers.staffId  AND myTEmpAlgmnt.allocTypeId=?2 AND myTPers.tenantId = ?3 ")
})

@TypeDef (name="encryptedString", typeClass= EncryptedStringType.class,
parameters= {
@Parameter(name="encryptorRegisteredName",  value="hibernateStringEncryptor")
})
@Table(name = "t_pers", uniqueConstraints = @UniqueConstraint(columnNames = { "staff_id" }))
public class TPers implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "staff_id", nullable = false, length = 255)
	private Integer staffId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 75)
	@Column(name = "first_name", nullable = false, length = 75)
	private String firstName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 75)
	@Column(name = "middle_name", nullable = true, length = 75)
	private String middleName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 75)
	@Column(name = "last_name", nullable = true, length = 75)
	private String lastName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "title", nullable = true, length = 20)
	private String title;

	/**
	 * 
	 * @generated
	 */
	//@Column(name = "birth_dt", nullable = true, length = 10)
	@Transient
	private Date birthDt;
	
	/**
	 * 
	 * @generated
	 */
	@Column(name = "birth_dt", nullable = true, length = 500)	
	@Type(type="encryptedString")
	private String birthDtStr;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "manager_id", nullable = true, length = 255)
	private Integer managerId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "created_by", nullable = false, length = 255, updatable = false)
	private Integer createdBy;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "create_dt", nullable = false, length = 19, updatable = false)
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
	@Length(max = 30)
	@Column(name = "client_id", nullable = false, length = 30)
	private String clientId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 30)
	@Column(name = "iAttain_id", nullable = false, length = 30)
	private String iattainId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 10)
	@Column(name = "gender", nullable = true, length = 10)
	private String gender;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "qual_id", nullable = true, length = 255)
	private Integer qualId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "org_name", nullable = true, length = 100)
	private String orgName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "div_cd", nullable = true, length = 20)
	private String divCd;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 75)
	@Column(name = "job_title", nullable = true, length = 75)
	private String jobTitle;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 75)
	@Column(name = "role_name", nullable = true, length = 75)
	private String roleName;

	/**
	 * 
	 * @generated
	 */

	@Column(name = "emp_dt", nullable = false, length = 10)
	private Date empDt;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "status_id", nullable = false, length = 255)
	private Integer statusId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "emp_type_id", nullable = false, length = 255)
	private Integer empTypeId;
	/**
	 * 
	 * @generated
	 */
	//@NotEmpty
	@Length(max = 230)
	@Column(name = "emp_name", nullable = true, length = 230)
	private String empName;
	
	
	//@NotNull
	@Column(name = "usr_id", length = 255, nullable = true, insertable = true, updatable = true)
	private Integer userId;
	
	/**
	 * 
	 * @generated
	 */
	@Length(max = 25)
	@Column(name = "name_pfx", nullable = true, length = 25)
	private String namePfx;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 25)
	@Column(name = "name_sfx", nullable = true, length = 25)
	private String nameSfx;
	
	/**
	 * 
	 * @generated
	 */
	@Column(name = "term_dt", nullable = true, length = 10)
	private Date termDt;
	/**
	 * 
	 * @generated
	 */
	public void setEmpName(final String empName) {
		this.empName = empName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getEmpName() {
		return this.empName;
	}
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "tPers", fetch=FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@NotAudited
	private Set<TRptRecipient> tRptRecipientss;

	/*
	 * @OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
	 * CascadeType.MERGE }, mappedBy = "tPers") private Set<TPers> tPersess;
	 */
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "tPers", fetch=FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@NotAudited
	private Set<TUsrRptPref> tUsrRptPrefss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "tPers", fetch=FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@NotAudited
	private Set<TRptTargetRecipient> tRptTargetRecipientss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "tPers", fetch=FetchType.LAZY)
	//@Column(name="staffId")
	//@BatchSize(size=10)
	//@Fetch(FetchMode.SUBSELECT)
	//@Fetch(FetchMode.JOIN)
	@NotAudited
	private Set<TRptPublisher> tRptPublisherss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "tPers", fetch=FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@NotAudited
	private Set<TTargetRecipientPref> tTargetRecipientPrefss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "tPers", fetch=FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@NotAudited
	private Set<TCommReceipient> tCommReceipientss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "tPers", fetch=FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@NotAudited
	private Set<TCommPublisher> tCommPublisherss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "tPers", fetch=FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@NotAudited
	private Set<TCommTargetRecipient> tCommTargetRecipientss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "tPers", fetch=FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@NotAudited
	private Set<TEmpAlgmnt> tEmpAlgmntss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "tPers", fetch=FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	 @NotAudited
	private Set<TPersAddr> tPersAddrss;

	@OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id")
    @Fetch(FetchMode.SUBSELECT)
    @NotAudited
    private Set<TPersContact> tPersContactss;
	

	@OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id")
    @Fetch(FetchMode.SUBSELECT)
    @NotAudited
	private Set<TEmpAttr> tEmpAttrss;
	
@Column(name = "ext_attr", nullable = true, length = 255)
	private byte[] empExtAttrs;
	
	
	/**
	 * 
	 * @generated
	 */
	public TPers() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setStaffId(final Integer staffId) {
		this.staffId = staffId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getStaffId() {
		return this.staffId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getMiddleName() {
		return this.middleName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * 
	 * @generated
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * 
	 * @generated
	 */

	public void setBirthDt(final Date birthDt) {
		String birthDateStr = "";
		if(birthDt!=null){	
			birthDateStr = birthDt.getYear()+1900+"-";
			if(birthDt.getMonth()<9){
				birthDateStr = birthDateStr+"0"+(birthDt.getMonth()+1)+"-";
			}else{
				birthDateStr = birthDateStr+(birthDt.getMonth()+1)+"-";
			}
			
			if(birthDt.getDate()<9){
				birthDateStr = birthDateStr+"0"+(birthDt.getDate());
			}else{
				birthDateStr = birthDateStr+(birthDt.getDate());
			}
		}	
		this.setBirthDtStr(birthDateStr);
	}
	
	@Transient
	public Date getBirthDt() {
		this.birthDt=convertStringToDate(this.getBirthDtStr());				
		if (this.birthDt != null) {
			return (Date) this.birthDt.clone();
		} else {
			return null;
		}
	}
	
	public void setBirthDtStr(String birthDtStr) {	
			this.birthDtStr = birthDtStr;
	}
	
	/**
	 * 
	 * @generated
	 */
	@Type(type="encryptedString")
	public String getBirthDtStr() {	
		return this.birthDtStr;
	}
	
	private Date convertStringToDate(String dateInString){

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");	
		 
		Date date = null;
		try {	
			if(dateInString!=null && !"".equals(dateInString)){
				date = formatter.parse(dateInString+" 00:00:00");
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
		 
		 
	}
	

	/**
	 * 
	 * @generated
	 */

	public void setManagerId(final Integer managerId) {
		this.managerId = managerId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getManagerId() {
		return this.managerId;
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

	public void setClientId(final String clientId) {
		this.clientId = clientId;
	}

	/**
	 * 
	 * @generated
	 */
	public String getClientId() {
		return this.clientId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setIattainId(final String iattainId) {
		this.iattainId = iattainId;
	}

	/**
	 * 
	 * @generated
	 */
	public String getIattainId() {
		return this.iattainId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setGender(final String gender) {
		this.gender = gender;
	}

	/**
	 * 
	 * @generated
	 */
	public String getGender() {
		return this.gender;
	}

	/**
	 * 
	 * @generated
	 */

	public void setQualId(final Integer qualId) {
		this.qualId = qualId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getQualId() {
		return this.qualId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setOrgName(final String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getOrgName() {
		return this.orgName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDivCd(final String divCd) {
		this.divCd = divCd;
	}

	/**
	 * 
	 * @generated
	 */
	public String getDivCd() {
		return this.divCd;
	}

	/**
	 * 
	 * @generated
	 */

	public void setJobTitle(final String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * 
	 * @generated
	 */
	public String getJobTitle() {
		return this.jobTitle;
	}

	/**
	 * 
	 * @generated
	 */

	public void setRoleName(final String roleName) {
		this.roleName = roleName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getRoleName() {
		return this.roleName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setEmpDt(final Date empDt) {
		if (empDt != null) {
			this.empDt = (Date) empDt.clone();
		} else {
			this.empDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getEmpDt() {
		if (this.empDt != null) {
			return (Date) this.empDt.clone();
		} else {
			return null;
		}
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

	public void setEmpTypeId(final Integer empTypeId) {
		this.empTypeId = empTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getEmpTypeId() {
		return this.empTypeId;
	}
	
	/**
	 * 
	 * @generated
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TEmpAlgmnt> getTEmpAlgmntss() {
		return this.tEmpAlgmntss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTEmpAlgmntss(final Set<TEmpAlgmnt> tEmpAlgmntss) {
		this.tEmpAlgmntss = tEmpAlgmntss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TPersAddr> getTPersAddrss() {
		return this.tPersAddrss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPersAddrss(final Set<TPersAddr> tPersAddrss) {
		this.tPersAddrss = tPersAddrss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TPersContact> getTPersContactss() {
		return this.tPersContactss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPersContactss(final Set<TPersContact> tPersContactss) {
		this.tPersContactss = tPersContactss;
	}

	/**
	 * 
	 * @generated
	 */
	public byte[] getEmpExtAttrs() {
		return empExtAttrs;
	}

	/**
	 * 
	 * @generated
	 */
	public void setEmpExtAttrs(byte[] empExtAttrs) {
		this.empExtAttrs = empExtAttrs;
	}

	/**
	 * 
	 * @generated
	 */

	public void setNamePfx(final String namePfx) {
		this.namePfx = namePfx;
	}

	/**
	 * 
	 * @generated
	 */
	public String getNamePfx() {
		return this.namePfx;
	}

	/**
	 * 
	 * @generated
	 */

	public void setNameSfx(final String nameSfx) {
		this.nameSfx = nameSfx;
	}

	/**
	 * 
	 * @generated
	 */
	public String getNameSfx() {
		return this.nameSfx;
	}

	/**
	 * 
	 * @generated
	 */

	public void setTermDt(final Date termDt) {
		if (termDt != null) {
			this.termDt = (Date) termDt.clone();
		} else {
			this.termDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getTermDt() {
		if (this.termDt != null) {
			return (Date) this.termDt.clone();
		} else {
			return null;
		}
	}
	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TPers that) {
		setStaffId(that.getStaffId());
		setFirstName(that.getFirstName());
		setMiddleName(that.getMiddleName());
		setLastName(that.getLastName());
		setTitle(that.getTitle());
		setBirthDt(that.getBirthDt());
		setManagerId(that.getManagerId());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setClientId(that.getClientId());
		setIattainId(that.getIattainId());
		setGender(that.getGender());
		setQualId(that.getQualId());
		setOrgName(that.getOrgName());
		setDivCd(that.getDivCd());
		setJobTitle(that.getJobTitle());
		setRoleName(that.getRoleName());
		setEmpDt(that.getEmpDt());
		setStatusId(that.getStatusId());
		setEmpTypeId(that.getEmpTypeId());
		setNamePfx(that.getNamePfx());
		setNameSfx(that.getNameSfx());
		setTermDt(that.getTermDt());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((staffId == null) ? 0 : staffId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("staffId=[").append(staffId).append("] ");
		buffer.append("firstName=[").append(firstName).append("] ");
		buffer.append("middleName=[").append(middleName).append("] ");
		buffer.append("lastName=[").append(lastName).append("] ");
		buffer.append("title=[").append(title).append("] ");
		buffer.append("birthDt=[").append(birthDt).append("] ");
		buffer.append("managerId=[").append(managerId).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("clientId=[").append(clientId).append("] ");
		buffer.append("iattainId=[").append(iattainId).append("] ");
		buffer.append("gender=[").append(gender).append("] ");
		buffer.append("qualId=[").append(qualId).append("] ");
		buffer.append("orgName=[").append(orgName).append("] ");
		buffer.append("divCd=[").append(divCd).append("] ");
		buffer.append("jobTitle=[").append(jobTitle).append("] ");
		buffer.append("roleName=[").append(roleName).append("] ");
		buffer.append("empDt=[").append(empDt).append("] ");
		buffer.append("statusId=[").append(statusId).append("] ");
		buffer.append("empTypeId=[").append(empTypeId).append("] ");
		buffer.append("birthDtStr=[").append(birthDtStr).append("] ");
		buffer.append("namePfx=[").append(namePfx).append("] ");
		buffer.append("nameSfx=[").append(nameSfx).append("] ");
		buffer.append("termDt=[").append(termDt).append("] ");

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
		final TPers other = (TPers) obj;
		if (staffId == null) {
			if (other.staffId != null) {
				return false;
			}
		} else if (!staffId.equals(other.staffId)) {
			return false;
		}
		return true;
	}
}
