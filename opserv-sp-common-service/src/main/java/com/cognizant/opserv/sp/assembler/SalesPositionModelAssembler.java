package com.cognizant.opserv.sp.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.MirrorSalesPositionType;
import com.cognizant.opserv.sp.common.SalesPositionType;
import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.opserv.sp.core.entity.TTerrZipAlgmnt;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesTeam;


/**
 * ***************************************************************************.
 *
 * @class SalesPositionModelAssembler - Mapper class for SalesPosition
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * ***************************************************************************
 */
@Component
public class SalesPositionModelAssembler {
	 
	/**
	 * Map SalesPosition entity details to sales pos.
	 *
	 * @param salesPos the sales pos
	 * @param tenantId the tenant id
	 * @return the sales position
	 */
	public SalesPosition mapSPEntityDetailsToSalesPos(TSalesPos salesPos, Short tenantId){
		
		SalesPosition salesPosition = new SalesPosition();
		if(salesPos != null){
			if(null!=salesPos.getSalesPosId()){
				salesPosition.setId(salesPos.getSalesPosId());	
			}
			salesPosition.setCode(salesPos.getPosCd());
			salesPosition.setName(salesPos.getPosName());
			Alignment alignment = new Alignment();
			SalesTeam salesTeam = new SalesTeam();
			BusinessUnit businessUnit = new BusinessUnit();
			if(null!=salesPos.getTAlgmntSalesTeam() && null!=salesPos.getTAlgmntSalesTeam().getTAlgmntSalesTeamId()){
				alignment.setId(salesPos.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getAlgmntId());
				
				salesTeam.setId(salesPos.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getSalesTeamId());
				businessUnit.setId(salesPos.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getBussUnitId());
				// Setting the name
				alignment.setName(salesPos.getTAlgmntSalesTeam().getTAlgmnt().getAlgmntName());
				salesTeam.setName(salesPos.getTAlgmntSalesTeam().getTSalesTeam().getSalesTeamName());
				businessUnit.setName(salesPos.getTAlgmntSalesTeam().getTSalesTeam().getTBussUnit().getBussUnitName());
			}
			
			salesTeam.setBusinessUnit(businessUnit);
			alignment.setSalesTeam(salesTeam);
			
			salesPosition.setAlignmment(alignment);
			
			SalesPosition parentSalesPos = new SalesPosition();
			if(salesPos.getPrnSalesPosId()!=null){
				parentSalesPos.setId(salesPos.getPrnSalesPosId());	
			}

			salesPosition.setParentSalesPosition(parentSalesPos);
			
			SalesOrgHierarchy salesOrgHierarchy = new SalesOrgHierarchy();
			if(null!=salesPos.getTAlgmntSalesHier() && salesPos.getTAlgmntSalesHier().getSalesHierId()!=null){
				salesOrgHierarchy.setId(salesPos.getTAlgmntSalesHier().getSalesHierId());
			}
			
			SalesOrgHierarchy parentOrgHier = new SalesOrgHierarchy();
			if(null!=salesPos.getPrnSalesHierId()){
				parentOrgHier.setId(salesPos.getPrnSalesHierId());
				salesOrgHierarchy.setParentHierarchy(parentOrgHier);
			}else{
				salesOrgHierarchy.setParentHierarchy(null);
			}
			salesOrgHierarchy.setName(salesPos.getTAlgmntSalesHier().getHierName());
			salesOrgHierarchy.setActive(salesPos.getTAlgmntSalesHier().getActiveFlag()=='Y'?true:false);
			salesOrgHierarchy.setLevel(salesPos.getTAlgmntSalesHier().getHierLevel());
			salesOrgHierarchy.setCreatedBy(salesPos.getTAlgmntSalesHier().getCreatedBy());
			salesOrgHierarchy.setCreatedDate(salesPos.getTAlgmntSalesHier().getCreateDt());
			if(salesPos.getTAlgmntSalesHier() != null){
				if(salesPos.getTAlgmntSalesHier().getUpdatedBy() != null){
					salesOrgHierarchy.setUpdatedBy(salesPos.getTAlgmntSalesHier().getUpdatedBy());	
				}
				if(salesPos.getTAlgmntSalesHier().getUpdateDt()!=null){
					salesOrgHierarchy.setUpdatedDate(salesPos.getTAlgmntSalesHier().getUpdateDt());
				}
				if(salesPos.getTAlgmntSalesHier().getEffEndDt()!=null){
					salesOrgHierarchy.setEndDate(salesPos.getTAlgmntSalesHier().getEffEndDt());					
				}
			}
			salesOrgHierarchy.setStartDate(salesPos.getTAlgmntSalesHier().getEffStartDt());
			salesOrgHierarchy.setTenantId(salesPos.getTAlgmntSalesHier().getTenantId());
			if(null!=salesPos.getTAlgmntSalesHier().getAssignZipFlag()){
				salesOrgHierarchy.setAssignZipFlag(salesPos.getTAlgmntSalesHier().getAssignZipFlag()=='Y'?true:false);	
			}
			if(null!=salesPos.getTAlgmntSalesHier().getMoveSPFlag()){
				salesOrgHierarchy.setMoveSpFlag(salesPos.getTAlgmntSalesHier().getMoveSPFlag()=='Y'?true:false);
			}
			salesPosition.setHierarchy(salesOrgHierarchy);
			salesPosition.setSalesPositionType( SalesPositionType.getSalesPositionType(salesPos.getSalesPosTypeId()));//Enum
			salesPosition.setActive(salesPos.getActiveFlag()==CommonConstants.ACTIVE_Y?true:false);
			salesPosition.setCreatedDate(salesPos.getCreateDt());
			salesPosition.setCreatedBy(salesPos.getCreatedBy());
			if(salesPos.getUpdatedBy()!=null){
				salesPosition.setUpdatedBy(salesPos.getUpdatedBy());	
			}
			if(salesPos.getUpdateDt()!=null){
				salesPosition.setUpdatedDate(salesPos.getUpdateDt());				
			}
			if(salesPos.getEffEndDt()!=null){
				salesPosition.setEndDate(salesPos.getEffEndDt());
			}
			salesPosition.setStartDate(salesPos.getEffStartDt());
			salesPosition.setTenantId(salesPos.getTenantId());
			
			// isJobShared
			if (null != salesPos.getJobShareFlag()){
				salesPosition.setJobShared(salesPos.getJobShareFlag()==CommonConstants.ACTIVE_Y?true:false);
			}
			if(null!=salesPos.getTMirType()){
				salesPosition.setMirrorSalesPositionType(MirrorSalesPositionType.getMirrorSalesPositionType(salesPos.getTMirType().getMirTypeId()));	
			}
			salesPosition.setRoot(salesPos.getPrnSalesPosId()==null?true:false);
		}
		return salesPosition;
	}
	
	/**
	 * Map zip terr entity to sales pos.
	 *
	 * @param tTerrZip the t terr zip
	 * @param tenantId the tenant id
	 * @return the sales position
	 */
	public SalesPosition mapZipTerrEntityToSalesPos(TTerrZipAlgmnt tTerrZip, Short tenantId){
		SalesPosition salesPosition = new SalesPosition();
		
		TSalesPos salesPos = tTerrZip.getTSalesPos();
		if(null!=tTerrZip.getTSalesPos().getSalesPosId()){
			salesPosition.setId(tTerrZip.getTSalesPos().getSalesPosId());	
		}
		salesPosition.setCode(tTerrZip.getTSalesPos().getPosCd());
		salesPosition.setName(tTerrZip.getTSalesPos().getPosName());
		Alignment alignment = new Alignment();
		SalesTeam salesTeam = new SalesTeam();
		BusinessUnit businessUnit = new BusinessUnit();
		if(null!=tTerrZip.getTSalesPos().getTAlgmntSalesTeam() && null!=salesPos.getTAlgmntSalesTeam().getTAlgmntSalesTeamId()){
			alignment.setId(salesPos.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getAlgmntId());
			
			salesTeam.setId(salesPos.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getSalesTeamId());
			businessUnit.setId(salesPos.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getBussUnitId());
			// Setting the name
			alignment.setName(salesPos.getTAlgmntSalesTeam().getTAlgmnt().getAlgmntName());
			salesTeam.setName(salesPos.getTAlgmntSalesTeam().getTSalesTeam().getSalesTeamName());
			businessUnit.setName(salesPos.getTAlgmntSalesTeam().getTSalesTeam().getTBussUnit().getBussUnitName());
		}
		
		salesTeam.setBusinessUnit(businessUnit);
		alignment.setSalesTeam(salesTeam);
		
		salesPosition.setAlignmment(alignment);
		
		SalesPosition parentSalesPos = new SalesPosition();
		parentSalesPos.setId(salesPos.getPrnSalesPosId());

		salesPosition.setParentSalesPosition(parentSalesPos);
		
		SalesOrgHierarchy salesOrgHierarchy = new SalesOrgHierarchy();
		if(null!=salesPos.getTAlgmntSalesHier()){
			salesOrgHierarchy.setId(salesPos.getTAlgmntSalesHier().getSalesHierId());
		}
		
		SalesOrgHierarchy parentOrgHier = new SalesOrgHierarchy();
		if(null!=salesPos.getPrnSalesHierId()){
			parentOrgHier.setId(salesPos.getPrnSalesHierId());
			salesOrgHierarchy.setParentHierarchy(parentOrgHier);
		}else{
			salesOrgHierarchy.setParentHierarchy(null);
		}
		salesOrgHierarchy.setName(salesPos.getTAlgmntSalesHier().getHierName());
		salesOrgHierarchy.setActive(salesPos.getTAlgmntSalesHier().getActiveFlag()=='Y'?true:false);
		salesOrgHierarchy.setLevel(salesPos.getTAlgmntSalesHier().getHierLevel());
		salesOrgHierarchy.setCreatedBy(salesPos.getTAlgmntSalesHier().getCreatedBy());
		salesOrgHierarchy.setCreatedDate(salesPos.getTAlgmntSalesHier().getCreateDt());
		salesOrgHierarchy.setUpdatedBy(salesPos.getTAlgmntSalesHier().getUpdatedBy());
		salesOrgHierarchy.setUpdatedDate(salesPos.getTAlgmntSalesHier().getUpdateDt());
		salesOrgHierarchy.setStartDate(salesPos.getTAlgmntSalesHier().getEffStartDt());
		salesOrgHierarchy.setEndDate(salesPos.getTAlgmntSalesHier().getEffEndDt());
		salesOrgHierarchy.setTenantId(salesPos.getTAlgmntSalesHier().getTenantId());
		if(null!=salesPos.getTAlgmntSalesHier().getAssignZipFlag()){
			salesOrgHierarchy.setAssignZipFlag(salesPos.getTAlgmntSalesHier().getAssignZipFlag()=='Y'?true:false);	
		}
		if(null!=salesPos.getTAlgmntSalesHier().getMoveSPFlag()){
			salesOrgHierarchy.setMoveSpFlag(salesPos.getTAlgmntSalesHier().getMoveSPFlag()=='Y'?true:false);
		}
		
		
		salesPosition.setHierarchy(salesOrgHierarchy);
	
		salesPosition.setSalesPositionType( SalesPositionType.getSalesPositionType(salesPos.getSalesPosTypeId()));//Enum
		salesPosition.setActive(salesPos.getActiveFlag()==CommonConstants.ACTIVE_Y?true:false);
		salesPosition.setUpdatedDate(salesPos.getUpdateDt());
		salesPosition.setCreatedDate(salesPos.getCreateDt());
		salesPosition.setUpdatedBy(salesPos.getUpdatedBy());
		salesPosition.setCreatedBy(salesPos.getCreatedBy());
		salesPosition.setStartDate(salesPos.getEffStartDt());
		salesPosition.setEndDate(salesPos.getEffEndDt());
		salesPosition.setTenantId(salesPos.getTenantId());
		
		// isJobShared
		if (null != salesPos.getJobShareFlag()){
			salesPosition.setJobShared(salesPos.getJobShareFlag()==CommonConstants.ACTIVE_Y?true:false);
		}
		if(null!=salesPos.getTMirType()){
			salesPosition.setMirrorSalesPositionType(MirrorSalesPositionType.getMirrorSalesPositionType(salesPos.getTMirType().getMirTypeId()));	
		}
		

		salesPosition.setRoot(salesPos.getPrnSalesPosId()==null?true:false);
		
		
		return salesPosition;
	}
	
	/**
	 * Map zip terr entity to sales pos.
	 *
	 * @param tTerrZipList the t terr zip list
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public List<SalesPosition> mapZipTerrEntityToSalesPos(List<TTerrZipAlgmnt> tTerrZipList, Short tenantId){
		List<SalesPosition> salesPositionList = new ArrayList<SalesPosition>();
		SalesPosition sp;
		for(TTerrZipAlgmnt tTerrZip:tTerrZipList){
			sp = mapZipTerrEntityToSalesPos(tTerrZip,tenantId);
			salesPositionList.add(sp);
			sp = null;
		}
		return salesPositionList;
	}

	/**
	 * Map object to sales position.
	 *
	 * @param tMirSalesPos the t mir sales pos
	 * @param tenantId the tenant id
	 * @return the sales position
	 */
	public SalesPosition mapObjectToSalesPosition(Object[] tMirSalesPos, Short tenantId) {
		
		 SalesPosition sp =  new SalesPosition();
    	 SalesOrgHierarchy salesOrgHierarchy = new SalesOrgHierarchy();
    	 Alignment alignment = new Alignment();
    	 BusinessUnit businessUnit = new BusinessUnit();
    	 SalesTeam salesTeam = new SalesTeam();
    	 
    	 sp.setId((Long)tMirSalesPos[0]);
    	 
    	 salesOrgHierarchy.setId((Long)tMirSalesPos[1]);
    	 alignment.setId((Long) tMirSalesPos[2]);
    	 businessUnit.setId((Long) tMirSalesPos[3]);
    	 salesTeam.setId((Long) tMirSalesPos[4]);
    	 
    	 salesTeam.setBusinessUnit(businessUnit);
    	 alignment.setSalesTeam(salesTeam);
    	 
    	 sp.setAlignmment(alignment);
    	 sp.setHierarchy(salesOrgHierarchy);
    	 sp.setPrimaryMirror(tMirSalesPos[5] != null && (Character)tMirSalesPos[5] =='Y'? true :false);
    	 
    	 return sp;
		
	}
	
	
	
}
