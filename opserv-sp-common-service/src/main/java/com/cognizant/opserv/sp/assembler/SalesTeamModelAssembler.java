package com.cognizant.opserv.sp.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TBussUnit;
import com.cognizant.opserv.sp.core.entity.TSalesTeam;
import com.cognizant.opserv.sp.core.entity.TSalesTeamId;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.SalesTeam;

/**
 * ***************************************************************************.
 *
 * @class SalesTeamModelAssembler - Mapper class for SalesTeam
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * ***************************************************************************
 */
@Component
public class SalesTeamModelAssembler {

	/** The business unit model assembler. */
	@Autowired
	private BusinessUnitModelAssembler businessUnitModelAssembler;
	
	/**
	 * Map sales team entity to sales team model.
	 *
	 * @param tSalesTeam the t sales team
	 * @param salesTeamId the sales team id
	 * @param tenantId the tenant id
	 * @return the sales team
	 */
	public SalesTeam mapSalesTeamEntityToSalesTeamModel(TSalesTeam tSalesTeam, Long salesTeamId, Short tenantId){
		SalesTeam salesTeam = new SalesTeam();
		if(tSalesTeam != null){
			
			BusinessUnit businessUnit = new BusinessUnit();
			if(tSalesTeam.getTBussUnit()!=null && tSalesTeam.getTBussUnit().getBussUnitId()!=null){
				businessUnit.setId(tSalesTeam.getTBussUnit().getBussUnitId());	
			}
//			if(tSalesTeam.getTSalesTeamId() != null && tSalesTeam.getTSalesTeamId().getSalesTeamId()!=null){
				salesTeam.setId(salesTeamId);				
//			}
			salesTeam.setBusinessUnit(businessUnit);
			salesTeam.setName(tSalesTeam.getSalesTeamName());
			salesTeam.setActive(tSalesTeam.getActiveFlag()=='Y'?true:false);
			if(tSalesTeam.getEffStartDt() != null){
				salesTeam.setStartDate(tSalesTeam.getEffStartDt());	
			}
			if(tSalesTeam.getEffEndDt() != null){
				salesTeam.setEndDate(tSalesTeam.getEffEndDt());	
			}
			// delete flag missing
			salesTeam.setCreatedBy(tSalesTeam.getCreatedBy());
			salesTeam.setCreatedDate(tSalesTeam.getCreateDt());
			
			if(tSalesTeam.getUpdatedBy()!=null && tSalesTeam.getUpdateDt() !=null){
				salesTeam.setUpdatedBy(tSalesTeam.getUpdatedBy());
				salesTeam.setUpdatedDate(tSalesTeam.getUpdateDt());
			}
			salesTeam.setTenantId(tSalesTeam.getTenantId());
		}
		return salesTeam;
	}
	
	/**
	 * Update map sales team model to st entity.
	 *
	 * @param tSalesTeam the t sales team
	 * @param salesTeam the sales team
	 * @param salesTeamId the sales team id
	 * @param tenantId the tenant id
	 * @return the t sales team
	 */
	public TSalesTeam updateMapSalesTeamModelToSTEntity(TSalesTeam tSalesTeam, SalesTeam salesTeam,Long salesTeamId, Short tenantId){
		if(salesTeam != null){
			TSalesTeamId tSalesTeamId = new TSalesTeamId();
			if(salesTeamId !=null ){
				tSalesTeamId.setSalesTeamId(salesTeamId);
			}
			TBussUnit tBussUnit = new TBussUnit();
			if(salesTeam.getBusinessUnit()!=null && salesTeam.getBusinessUnit().getId() !=null ){
				tSalesTeamId.setBussUnitId(salesTeam.getBusinessUnit().getId());
				tBussUnit.setBussUnitId(salesTeam.getBusinessUnit().getId());	
			
			}
			tSalesTeam.setTSalesTeamId(tSalesTeamId);
			tSalesTeam.setTBussUnit(tBussUnit);
			if(tBussUnit!=null){
				tSalesTeam.setTBussUnit(tBussUnit);	
			}
			
			tSalesTeam.setActiveFlag(salesTeam.isActive()==true?'Y':'N');
			tSalesTeam.setTenantId(salesTeam.getTenantId());
			tSalesTeam.setCreatedBy(salesTeam.getCreatedBy());
			tSalesTeam.setCreateDt(salesTeam.getCreatedDate());
//			tSalesTeam.setDeleteFlag(salesTeam.getd); // delete flag is missing
			tSalesTeam.setEffStartDt(salesTeam.getStartDate());
			if(salesTeam.getEndDate()!=null){
				tSalesTeam.setEffEndDt(salesTeam.getEndDate());	
			}
			tSalesTeam.setSalesTeamName(salesTeam.getName());
			if(salesTeam.getUpdatedBy() != null && salesTeam.getUpdatedDate()!= null){
				tSalesTeam.setUpdatedBy(salesTeam.getUpdatedBy());
				tSalesTeam.setUpdateDt(salesTeam.getUpdatedDate());	
			}
		}
		return tSalesTeam;
	}
	
	
	/**
	 * Convert t sales team to sales team model.
	 *
	 * @param tAlgmntSalesTeam the t algmnt sales team
	 * @return the sales team
	 */
	public SalesTeam convertTSalesTeamToSalesTeamModel(TAlgmntSalesTeam tAlgmntSalesTeam) {
		
		SalesTeam salesTeam = new SalesTeam();
		BusinessUnit businessUnit = new BusinessUnit();
		TSalesTeam tSalesTeam=tAlgmntSalesTeam.getTSalesTeam();
		
		if(tSalesTeam!=null)
		{
			salesTeam.setId(tSalesTeam.getTSalesTeamId().getSalesTeamId());
			salesTeam.setStartDate(tSalesTeam.getEffStartDt());
			salesTeam.setEndDate(tSalesTeam.getEffEndDt());
			salesTeam.setName(tSalesTeam.getSalesTeamName());
			if(tSalesTeam.getTBussUnit()!=null)
			{
				businessUnit = businessUnitModelAssembler.convertTBussUnitToBusinessUnitModel(tSalesTeam.getTBussUnit());
			}
			salesTeam.setBusinessUnit(businessUnit);
			
		}
		
		return salesTeam;
		
	}
	
}
