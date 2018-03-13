package com.cognizant.opserv.sp.assembler;

import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.core.entity.TBussUnit;
import com.cognizant.opserv.sp.core.entity.TOrg;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.SalesOrg;

/**
 * ***************************************************************************.
 *
 * @class BusinessUnitModelAssembler - Mapper class for BusinessUnit
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * ***************************************************************************
 */
@Component
public class BusinessUnitModelAssembler {

	/**
	 * Map buss unit entity to bu model.
	 *
	 * @param tBussUnit the t buss unit
	 * @param tenantId the tenant id
	 * @return the business unit
	 */
	public BusinessUnit mapBussUnitEntityToBUModel(TBussUnit tBussUnit, Short tenantId){

		BusinessUnit businessUnit = new BusinessUnit();
		if(tBussUnit != null){
			if(tBussUnit.getBussUnitId() != null ){
				businessUnit.setId(tBussUnit.getBussUnitId());
			}
		businessUnit.setActive(tBussUnit.getActiveFlag()=='Y'?true:false);
		businessUnit.setCode(tBussUnit.getBussUnitCode());
		if(tBussUnit.getCreatedBy()!=null && tBussUnit.getCreateDt() != null){
			businessUnit.setCreatedBy(tBussUnit.getCreatedBy());
			businessUnit.setCreatedDate(tBussUnit.getCreateDt());
		}
		if(tBussUnit.getBussUnitDesc() !=null){
			businessUnit.setDescription(tBussUnit.getBussUnitDesc());	
		}
		businessUnit.setStartDate(tBussUnit.getEffStartDt());
		if(tBussUnit.getEffEndDt() !=null){
			businessUnit.setEndDate(tBussUnit.getEffEndDt());
		}
		businessUnit.setTenantId(tBussUnit.getTenantId());
		businessUnit.setName(tBussUnit.getBussUnitName());
		SalesOrg salesOrg = new SalesOrg();
		if(tBussUnit.getTOrg() != null && tBussUnit.getTOrg().getOrgId() != null ){
			salesOrg.setId(tBussUnit.getTOrg().getOrgId().longValue());	
		}
		businessUnit.setSalesOrg(salesOrg);
		if(tBussUnit.getUpdatedBy()!= null && tBussUnit.getUpdateDate() != null){
			businessUnit.setUpdatedBy(tBussUnit.getUpdatedBy());
		    businessUnit.setUpdatedDate(tBussUnit.getUpdateDate()); 
		}
		}
		return businessUnit;
	}

	/**
	 * Update map bu model to buss unit entity.
	 *
	 * @param tBussUnit the t buss unit
	 * @param businessUnit the business unit
	 * @param tenantId the tenant id
	 * @return the t buss unit
	 */
	public TBussUnit updateMapBUModelToBussUnitEntity(TBussUnit tBussUnit, BusinessUnit businessUnit, Short tenantId){
		if(businessUnit != null){
			tBussUnit.setActiveFlag(businessUnit.isActive()==true?'Y':'N');
			tBussUnit.setBussUnitCode(businessUnit.getCode());
			if(businessUnit.getDescription()!=null){
				tBussUnit.setBussUnitDesc(businessUnit.getDescription());	
			}
			tBussUnit.setBussUnitName(businessUnit.getName());
			tBussUnit.setCreatedBy(businessUnit.getCreatedBy());
			tBussUnit.setCreateDt(businessUnit.getCreatedDate());
			tBussUnit.setEffStartDt(businessUnit.getStartDate());
			if(businessUnit.getEndDate()!=null){
				tBussUnit.setEffEndDt(businessUnit.getEndDate());
			}
			tBussUnit.setTenantId(businessUnit.getTenantId());
			TOrg tOrg = new TOrg();
			if(businessUnit.getSalesOrg() != null && businessUnit.getSalesOrg().getId() !=null){
				tOrg.setOrgId(businessUnit.getSalesOrg().getId().intValue());	
			}
			tBussUnit.setTOrg(tOrg);
			if(businessUnit.getUpdatedBy() !=null && businessUnit.getUpdatedDate()!=null){
				tBussUnit.setUpdateDate(businessUnit.getUpdatedDate());
				tBussUnit.setUpdatedBy(businessUnit.getUpdatedBy());	
			}
		}
		return tBussUnit;
	}

	/**
	 * Mapped bu model to buss unit entity.
	 *
	 * @param businessUnit the business unit
	 * @param tenantId the tenant id
	 * @return the t buss unit
	 */
	public TBussUnit mappedBUModelToBussUnitEntity(BusinessUnit businessUnit, Short tenantId){
	
		TBussUnit tBussUnit = new TBussUnit();
		if(businessUnit != null){
			if(businessUnit.getId() != null){
				tBussUnit.setBussUnitId(businessUnit.getId());	
			}
			tBussUnit.setActiveFlag(businessUnit.isActive()==true?'Y':'N');
			tBussUnit.setBussUnitCode(businessUnit.getCode());
			if(businessUnit.getDescription()!=null){
				tBussUnit.setBussUnitDesc(businessUnit.getDescription());	
			}
			tBussUnit.setBussUnitName(businessUnit.getName());
			tBussUnit.setCreatedBy(businessUnit.getCreatedBy());
			tBussUnit.setCreateDt(businessUnit.getCreatedDate());
			tBussUnit.setEffStartDt(businessUnit.getStartDate());
			if(businessUnit.getEndDate()!=null){
				tBussUnit.setEffEndDt(businessUnit.getEndDate());
			}
			tBussUnit.setTenantId(businessUnit.getTenantId());
			TOrg tOrg = new TOrg();
			if(businessUnit.getSalesOrg() != null && businessUnit.getSalesOrg().getId() !=null){
				tOrg.setOrgId(businessUnit.getSalesOrg().getId().intValue());	
			}
			tBussUnit.setTOrg(tOrg);
			if(businessUnit.getUpdatedBy() !=null && businessUnit.getUpdatedDate()!=null){
				tBussUnit.setUpdateDate(businessUnit.getUpdatedDate());
				tBussUnit.setUpdatedBy(businessUnit.getUpdatedBy());	
			}
		}
		return tBussUnit;
	}
	
	
	/**
	 * Convert t buss unit to business unit model.
	 *
	 * @param tBussUnit the t buss unit
	 * @return the business unit
	 */
	public BusinessUnit convertTBussUnitToBusinessUnitModel(TBussUnit tBussUnit) {
		BusinessUnit businessUnit =new BusinessUnit();
		SalesOrg salesOrg = new SalesOrg();
		
		businessUnit.setId(tBussUnit.getBussUnitId());
		businessUnit.setName(tBussUnit.getBussUnitName());
		businessUnit.setCode(tBussUnit.getBussUnitCode());
		businessUnit.setDescription(tBussUnit.getBussUnitDesc());
		businessUnit.setEndDate(tBussUnit.getEffEndDt());
		businessUnit.setStartDate(tBussUnit.getEffStartDt());
		if(tBussUnit.getTOrg() !=null)
		{
			salesOrg=	SalesOrgModelAssembler.convertTOrgToSalesOrgModel(tBussUnit.getTOrg());
		}
		
		businessUnit.setSalesOrg(salesOrg);
	
	
	return businessUnit;
	}

	
	
	
}
