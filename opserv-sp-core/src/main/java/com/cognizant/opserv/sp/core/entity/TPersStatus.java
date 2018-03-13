package com.cognizant.opserv.sp.core.entity;


/**************************************************************************************
 **************************************************************************************/
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * This TPersStatus class is a data object class which will be used for data transfer
 *
 *
 */	
/*@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable(value = true)*/
@Entity
@NamedQueries({@NamedQuery(name = "FindAllTStatus", query = "select myTPersStatus from TPersStatus myTPersStatus where myTPersStatus.tenantId=?1 AND myTPersStatus.locale=?2" , 
	hints = {
		@QueryHint(name = "org.hibernate.cacheable", value = "true"),
		@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")

}),
@NamedQuery(name = "FindAllTPersStatusNameAndId", query ="Select myTPersStatus.tPersStatusId.statusId,myTPersStatus.statusDesc from TPersStatus myTPersStatus where myTPersStatus.tenantId=?1 AND myTPersStatus.locale=?2", 
hints = {
	@QueryHint(name = "org.hibernate.cacheable", value = "true"),
	@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")		
})
})
@Table(name = "t_pers_status")
public class TPersStatus implements Serializable {

    /**
     * serialVersionUID to validate serialized object
     */
    private static final long serialVersionUID = 8449947414035676977L;

    /**
     *  statusId - 
     */

	@EmbeddedId
   	@Valid
   	private TPersStatusId tPersStatusId;
     /**
     *  statusId - 
     */


   /* @Id
    @Column(name = "status_id", unique = true, nullable = false)
    private int statusId;*/

    /**
     *  statusCd - 
     */
    @Column(name = "status_cd", nullable = false, length = 20)
    private String statusCd;

    /**
     *  statusDesc - 
     */
    @Column(name = "status_desc", length = 30)
    private String statusDesc;

    /**
     *  activeFlag - 
     */
    @Column(name = "active_flag", nullable = false, length = 1)
    private char activeFlag;

    /**
     *  createdBy - 
     */
    @Column(name = "created_by", nullable = false)
    private int createdBy;

    /**
     *  createDt - 
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_dt", nullable = false, length = 19)
    private Date createDt;

    /**
     *  updatedBy - 
     */
    @Column(name = "updated_by")
    private Integer updatedBy;

    /**
     *  updateDt - 
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_dt", length = 19)
    private Date updateDt;

    /**
     *  tenantId - 
     */
    @Column(name = "tenant_id", nullable = false)
    private short tenantId;

    @NotEmpty
    @Column(name="locale_id", nullable=false, length=20,insertable = false, updatable = false)
    private String locale;
    /**
     *  tPerses - 
     */
   /* @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "tPersStatus")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<TPers> tPerses = new ArrayList<TPers>();*/

    
   /* @OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tPersStatus")
	@NotAudited
	private Set<TPers> tPerss;
    */
    
    
    
   /* public Set<TPers> gettPerss() {
		return tPerss;
	}

	public void settPerss(Set<TPers> tPerss) {
		this.tPerss = tPerss;
	}
*/
	/**
     *  This method is used to get the statusId  
     * @return StatusId 
     */ 
    /*public int getStatusId() {	
        return this.statusId;
    }	

    *//**
     * This method is used to set the statusId 
     * @param statusId StatusId which has to be set
     *//* 
    public void setStatusId(final int statusId) {
        this.statusId = statusId;
    }*/

    /**
     *  This method is used to get the statusCd  
     * @return StatusCd 
     */ 
    public String getStatusCd() {	
        return this.statusCd;
    }	

    /**
     * This method is used to set the statusCd 
     * @param statusCd StatusCd which has to be set
     */ 
    public void setStatusCd(final String statusCd) {
        this.statusCd = statusCd;
    }

    /**
     *  This method is used to get the statusDesc  
     * @return StatusDesc 
     */ 
    public String getStatusDesc() {	
        return this.statusDesc;
    }	

    /**
     * This method is used to set the statusDesc 
     * @param statusDesc StatusDesc which has to be set
     */ 
    public void setStatusDesc(final String statusDesc) {
        this.statusDesc = statusDesc;
    }

    /**
     *  This method is used to get the activeFlag  
     * @return ActiveFlag 
     */ 
    public char getActiveFlag() {	
        return this.activeFlag;
    }	

    /**
     * This method is used to set the activeFlag 
     * @param activeFlag ActiveFlag which has to be set
     */ 
    public void setActiveFlag(final char activeFlag) {
        this.activeFlag = activeFlag;
    }

    /**
     *  This method is used to get the createdBy  
     * @return CreatedBy 
     */ 
    public int getCreatedBy() {	
        return this.createdBy;
    }	

    /**
     * This method is used to set the createdBy 
     * @param createdBy CreatedBy which has to be set
     */ 
    public void setCreatedBy(final int createdBy) {
        this.createdBy = createdBy;
    }

    /**
     *  This method is used to get the createDt  
     * @return CreateDt 
     */ 
    public Date getCreateDt() {	
        return this.createDt;
    }	

    /**
     * This method is used to set the createDt 
     * @param createDt CreateDt which has to be set
     */ 
    public void setCreateDt(final Date createDt) {
        this.createDt = createDt;
    }

    /**
     *  This method is used to get the updatedBy  
     * @return UpdatedBy 
     */ 
    public Integer getUpdatedBy() {	
        return this.updatedBy;
    }	

    /**
     * This method is used to set the updatedBy 
     * @param updatedBy UpdatedBy which has to be set
     */ 
    public void setUpdatedBy(final Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     *  This method is used to get the updateDt  
     * @return UpdateDt 
     */ 
    public Date getUpdateDt() {	
        return this.updateDt;
    }	

    /**
     * This method is used to set the updateDt 
     * @param updateDt UpdateDt which has to be set
     */ 
    public void setUpdateDt(final Date updateDt) {
        this.updateDt = updateDt;
    }

    /**
     *  This method is used to get the tenantId  
     * @return TenantId 
     */ 
    public short getTenantId() {	
        return this.tenantId;
    }	

    /**
     * This method is used to set the tenantId 
     * @param tenantId TenantId which has to be set
     */ 
    public void setTenantId(final short tenantId) {
        this.tenantId = tenantId;
    }

    /**
     *  This method is used to get the tPerses  
     * @return TPerses 
     */ 
    /*public List<TPers> getTPerses() {
        return this.tPerses;
    }		

    *//**
     * This method is used to set the tPerses 
     * @param tPerses TPerses which has to be set
     *//* 
    public void setTPerses(final List<TPers> tPerses) {
        this.tPerses = (ArrayList<TPers>) tPerses;
    }
*/
   @Override
    public String toString() {
        final StringBuilder strBuilder = new StringBuilder();

        strBuilder.append("TPersStatus (").append(super.toString()).append("\t").append("statusId = ").append(this.tPersStatusId).append("\t").append("statusCd = ").append(this.statusCd).append("\t").append("statusDesc = ").append(this.statusDesc).append("\t").append("activeFlag = ").append(this.activeFlag).append("\t").append("createdBy = ").append(this.createdBy).append("\t").append("createDt = ").append(this.createDt).append("\t").append("updatedBy = ").append(this.updatedBy).append("\t").append("updateDt = ").append(this.updateDt).append("\t").append("tenantId = ").append(this.tenantId).append("\t").append(
                " )");
        return strBuilder.toString();
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public TPersStatusId gettPersStatusId() {
		return tPersStatusId;
	}

	public void settPersStatusId(TPersStatusId tPersStatusId) {
		this.tPersStatusId = tPersStatusId;
	}
}
