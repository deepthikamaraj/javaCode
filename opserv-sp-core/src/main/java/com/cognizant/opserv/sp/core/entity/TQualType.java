package com.cognizant.opserv.sp.core.entity;


/**************************************************************************************
 **************************************************************************************/
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * This TQualType class is a data object class which will be used for data transfer
 *
 *
 */	
/*@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable(value = true)*/
@Entity
@NamedQueries({@NamedQuery(name = "FindAllTQualTypes", query = "select myTQualType from TQualType myTQualType where myTQualType.tenantId=?1 AND myTQualType.tQualTypeId.localeId=?2" , 
hints = {
		@QueryHint(name = "org.hibernate.cacheable", value = "true"),
		@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")

}),
@NamedQuery(name = "FindAllTQualTypeNameAndId", query ="Select myTQualType.tQualTypeId.qualId,myTQualType.qualDesc from TQualType myTQualType where myTQualType.tenantId=?1 AND myTQualType.tQualTypeId.localeId=?2", 
hints = {
	@QueryHint(name = "org.hibernate.cacheable", value = "true"),
	@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")		
})
})
@Table(name = "t_qual_type")
public class TQualType implements Serializable {

    /**
     * serialVersionUID to validate serialized object
     */
    private static final long serialVersionUID = -8691334347262763119L;

    
    @EmbeddedId
	@Valid
	private TQualTypeId tQualTypeId;
    /**
     *  qualId - 
     */
    @Id
    @Column(name = "qual_id", unique = true, nullable = false)
    private int qualId;

    /**
     *  qualCd - 
     */
    @Column(name = "qual_cd", length = 20)
    private String qualCd;

    /**
     *  qualDesc - 
     */
    @Column(name = "qual_desc", nullable = false, length = 40)
    private String qualDesc;

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
    private Short tenantId;
    
    @NotEmpty
    @Column(name="locale_id", nullable=false, length=20)
    private String localeId;

    /**
     *  tPerses - 
     */
   /* @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "tQualType")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<TPers> tPerses = new ArrayList<TPers>();
*/
    /**
     *  This method is used to get the qualId  
     * @return QualId 
     */ 
    public int getQualId() {	
        return this.qualId;
    }	

    /**
     * This method is used to set the qualId 
     * @param qualId QualId which has to be set
     */ 
    public void setQualId(final int qualId) {
        this.qualId = qualId;
    }

    /**
     *  This method is used to get the qualCd  
     * @return QualCd 
     */ 
    public String getQualCd() {	
        return this.qualCd;
    }	

    /**
     * This method is used to set the qualCd 
     * @param qualCd QualCd which has to be set
     */ 
    public void setQualCd(final String qualCd) {
        this.qualCd = qualCd;
    }

    /**
     *  This method is used to get the qualDesc  
     * @return QualDesc 
     */ 
    public String getQualDesc() {	
        return this.qualDesc;
    }	

    /**
     * This method is used to set the qualDesc 
     * @param qualDesc QualDesc which has to be set
     */ 
    public void setQualDesc(final String qualDesc) {
        this.qualDesc = qualDesc;
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
   /* public List<TPers> getTPerses() {
        return this.tPerses;
    }	*/	

    /**
     * This method is used to set the tPerses 
     * @param tPerses TPerses which has to be set
     */ 
   /* public void setTPerses(final List<TPers> tPerses) {
        this.tPerses = (ArrayList<TPers>) tPerses;
    }*/

    @Override
    public String toString() {
        final StringBuilder strBuilder = new StringBuilder();

        strBuilder.append("TQualType (").append(super.toString()).append("\t").append("qualId = ").append(this.qualId).append("\t").append("qualCd = ").append(this.qualCd).append("\t").append("qualDesc = ").append(this.qualDesc).append("\t").append("createdBy = ").append(this.createdBy).append("\t").append("createDt = ").append(this.createDt).append("\t").append("updatedBy = ").append(this.updatedBy).append("\t").append("updateDt = ").append(this.updateDt).append("\t").append("tenantId = ").append(this.tenantId).append("\t").append(
                " )");
        return strBuilder.toString();
    }

    public String getLocaleId() {
		return localeId;
	}

	public void setLocaleId(String localeId) {
		this.localeId = localeId;
	}

	@Override
    public boolean equals(final Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

	public TQualTypeId gettQualTypeId() {
		return tQualTypeId;
	}

	public void settQualTypeId(TQualTypeId tQualTypeId) {
		this.tQualTypeId = tQualTypeId;
	}

	
}
