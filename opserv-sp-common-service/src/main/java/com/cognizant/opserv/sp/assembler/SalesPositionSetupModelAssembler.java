package com.cognizant.opserv.sp.assembler;

import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.MirrorSalesPositionType;
import com.cognizant.opserv.sp.common.SalesPositionType;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesHier;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeamId;
import com.cognizant.opserv.sp.core.entity.TMirType;
import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesTeam;

/**
 * The Class SalesPositionSetupModelAssembler.
 */
@Component
public class SalesPositionSetupModelAssembler {

	/**
	 * Map sp model to sales pos entity.
	 *
	 * @param salesPos the sales pos
	 * @param tenantId the tenant id
	 * @return the t sales pos
	 */
	public TSalesPos mapSPModelToSalesPosEntity(SalesPosition salesPos, Short tenantId){
		
		TSalesPos tSalesPos  = new TSalesPos();
		if(null!=salesPos.getId()){
			tSalesPos.setSalesPosId(salesPos.getId());
		}
		tSalesPos.setPosCd(salesPos.getCode());
		tSalesPos.setPosName(salesPos.getName());
		tSalesPos.setActiveFlag(salesPos.isActive()==true?'Y':'N');
//		tSalesPos.setActiveFlag('Y');
		tSalesPos.setEffStartDt(salesPos.getStartDate());
		tSalesPos.setEffEndDt(salesPos.getEndDate());
		tSalesPos.setCreatedBy(salesPos.getCreatedBy());
		tSalesPos.setCreateDt(DateUtil.getCurrentDate());
		tSalesPos.setTenantId(tenantId);
		tSalesPos.setJobShareFlag(salesPos.isJobShared()==true?'Y':'N');
		
		if(salesPos.getSalesPositionType() !=null && salesPos.getSalesPositionType().getId() != null){
			tSalesPos.setSalesPosTypeId(salesPos.getSalesPositionType().getId());
		}
		if(salesPos.getHierarchy() !=null && salesPos.getHierarchy().getParentHierarchy() != null){
			tSalesPos.setPrnSalesHierId(salesPos.getHierarchy().getParentHierarchy().getId()==null?null:salesPos.getHierarchy().getParentHierarchy().getId());
		}
		if(salesPos.getParentSalesPosition() != null){
			tSalesPos.setPrnSalesPosId(salesPos.getParentSalesPosition().getId()==null?null:salesPos.getParentSalesPosition().getId());	
		}
		TMirType tMirType = new TMirType();
		if(salesPos.getMirrorSalesPositionType() != null && salesPos.getMirrorSalesPositionType().getId() != null){
			tMirType.setMirTypeId(salesPos.getMirrorSalesPositionType().getId());
		}
		tSalesPos.setTMirType(tMirType);
		
		TAlgmntSalesHier tAlgmntSalesHier = new TAlgmntSalesHier();
		TAlgmntSalesTeam tAlgmntSalesTeam = new TAlgmntSalesTeam();
		TAlgmntSalesTeamId tAlgmntSalesTeamId = new TAlgmntSalesTeamId();
		if(salesPos.getAlignmment() != null && salesPos.getAlignmment().getSalesTeam() != null 
				&& salesPos.getAlignmment().getSalesTeam().getId() != null && 
				salesPos.getAlignmment().getSalesTeam().getBusinessUnit() != null && 
				salesPos.getAlignmment().getSalesTeam().getBusinessUnit().getId() != null){
			
			tAlgmntSalesTeamId.setAlgmntId(salesPos.getAlignmment().getId());
			tAlgmntSalesTeamId.setBussUnitId(salesPos.getAlignmment().getSalesTeam().getBusinessUnit().getId());
			tAlgmntSalesTeamId.setSalesTeamId(salesPos.getAlignmment().getSalesTeam().getId());
			
			tAlgmntSalesTeam.setTAlgmntSalesTeamId(tAlgmntSalesTeamId);
//			tAlgmntSalesHier.setTAlgmntSalesTeam(tAlgmntSalesTeam);
		}
		
		if(salesPos.getHierarchy() != null && salesPos.getHierarchy().getId() != null && salesPos.getHierarchy().getName() != null 
				&& salesPos.getHierarchy().getCreatedDate() != null && salesPos.getHierarchy().getCreatedBy() != null){
			tAlgmntSalesHier.setSalesHierId(salesPos.getHierarchy().getId());	
			tAlgmntSalesHier.setHierName(salesPos.getHierarchy().getName());
			tAlgmntSalesHier.setActiveFlag(salesPos.getHierarchy().isActive()==true?'Y':'N');
			tAlgmntSalesHier.setHierLevel(salesPos.getHierarchy().getLevel());
			tAlgmntSalesHier.setCreatedBy(salesPos.getHierarchy().getCreatedBy());
			tAlgmntSalesHier.setCreateDt(salesPos.getHierarchy().getCreatedDate());
			if(salesPos.getHierarchy().getUpdatedBy() != null && salesPos.getHierarchy().getUpdatedDate() !=null){
				tAlgmntSalesHier.setUpdatedBy(salesPos.getHierarchy().getUpdatedBy());	
				tAlgmntSalesHier.setUpdateDt(salesPos.getHierarchy().getUpdatedDate());
			}
			tAlgmntSalesHier.setEffStartDt(salesPos.getHierarchy().getStartDate());
			if(salesPos.getHierarchy().getEndDate() != null){
				tAlgmntSalesHier.setEffEndDt(salesPos.getHierarchy().getEndDate());	
			}
			tAlgmntSalesHier.setTenantId(salesPos.getHierarchy().getTenantId());
			tAlgmntSalesHier.setAssignZipFlag(salesPos.getHierarchy().isAssignZipFlag()==true?'Y':'N');
			tAlgmntSalesHier.setMoveSPFlag(salesPos.getHierarchy().isMoveSpFlag()==true?'Y':'N');
			
			if(salesPos.getHierarchy().getParentHierarchy() != null && salesPos.getHierarchy().getParentHierarchy().getId() != null){
				tAlgmntSalesHier.setPrnSaleHierId(salesPos.getHierarchy().getParentHierarchy().getId());
			}
		}
		tSalesPos.setTAlgmntSalesHier(tAlgmntSalesHier);
		tSalesPos.setTAlgmntSalesTeam(tAlgmntSalesTeam);
		return tSalesPos;
	}


	public TSalesPos updateMapSalesPosEntityToSPModel(TSalesPos tSalesPos, SalesPosition salesPos, Short tenantId){
		
		if(salesPos != null){
			if(null!=salesPos.getId()){
				tSalesPos.setSalesPosId(salesPos.getId());
			}
			tSalesPos.setPosCd(salesPos.getCode());
			tSalesPos.setPosName(salesPos.getName());
			tSalesPos.setActiveFlag(salesPos.isActive()==true?'Y':'N');
			tSalesPos.setEffStartDt(salesPos.getStartDate());
			if(salesPos.getEndDate() != null){
				tSalesPos.setEffEndDt(salesPos.getEndDate());				
			}
			tSalesPos.setCreatedBy(salesPos.getCreatedBy());
			tSalesPos.setCreateDt(salesPos.getCreatedDate());
			if(salesPos.getUpdatedDate() != null){
				tSalesPos.setUpdateDt(salesPos.getUpdatedDate());
			}
			if(salesPos.getUpdatedBy() != null){
				tSalesPos.setUpdatedBy(salesPos.getUpdatedBy());
			}			
			tSalesPos.setTenantId(salesPos.getTenantId());
			tSalesPos.setJobShareFlag(salesPos.isJobShared()==true?'Y':'N');
			if(salesPos.getParentSalesPosition() != null){
				tSalesPos.setRootHierId(salesPos.getParentSalesPosition().getId()==null?null:salesPos.getParentSalesPosition().getId());				
			}
			if(salesPos.getSalesPositionType() !=null && salesPos.getSalesPositionType().getId() != null){
				tSalesPos.setSalesPosTypeId(salesPos.getSalesPositionType().getId());
			}
			if(salesPos.getHierarchy() != null && salesPos.getHierarchy().getParentHierarchy() != null){
				tSalesPos.setPrnSalesHierId(salesPos.getHierarchy().getParentHierarchy().getId()==null?null:salesPos.getHierarchy().getParentHierarchy().getId());	
			}
			if(salesPos.getParentSalesPosition() != null){
				tSalesPos.setPrnSalesPosId(salesPos.getParentSalesPosition().getId()==null?null:salesPos.getParentSalesPosition().getId());	
			}
			
			TMirType tMirType = new TMirType();
			if(salesPos.getMirrorSalesPositionType() != null && salesPos.getMirrorSalesPositionType().getId() != null){
				tMirType.setMirTypeId(salesPos.getMirrorSalesPositionType().getId());
			}
			tSalesPos.setTMirType(tMirType);
			
		TAlgmntSalesHier tAlgmntSalesHier = new TAlgmntSalesHier();
		TAlgmntSalesTeam tAlgmntSalesTeam = new TAlgmntSalesTeam();
		TAlgmntSalesTeamId tAlgmntSalesTeamId = new TAlgmntSalesTeamId();
		if(salesPos.getAlignmment() != null && salesPos.getAlignmment().getSalesTeam() != null && salesPos.getAlignmment().getSalesTeam().getId() != null && salesPos.getAlignmment().getSalesTeam().getBusinessUnit() != null && salesPos.getAlignmment().getSalesTeam().getBusinessUnit().getId() != null){
			tAlgmntSalesTeamId.setAlgmntId(salesPos.getAlignmment().getId());
			tAlgmntSalesTeamId.setBussUnitId(salesPos.getAlignmment().getSalesTeam().getBusinessUnit().getId());
			tAlgmntSalesTeamId.setSalesTeamId(salesPos.getAlignmment().getSalesTeam().getId());
			
			tAlgmntSalesTeam.setTAlgmntSalesTeamId(tAlgmntSalesTeamId);
		}
		
		if(salesPos.getHierarchy() != null && salesPos.getHierarchy().getId() != null){
			tAlgmntSalesHier.setSalesHierId(salesPos.getHierarchy().getId());	
			if(salesPos.getHierarchy().getParentHierarchy() != null && salesPos.getHierarchy().getParentHierarchy().getId() != null){
				tAlgmntSalesHier.setPrnSaleHierId(salesPos.getHierarchy().getParentHierarchy().getId());
			}
		}
		tSalesPos.setTAlgmntSalesHier(tAlgmntSalesHier);
		tSalesPos.setTAlgmntSalesTeam(tAlgmntSalesTeam);
	}
		return tSalesPos;
	}

	
	
public SalesPosition mapSPEntityDetailsToSalesPos(TSalesPos salesPos, Short tenantId){
		
		SalesPosition salesPosition = new SalesPosition();
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
		}
		salesTeam.setBusinessUnit(businessUnit);
		alignment.setSalesTeam(salesTeam);
		salesPosition.setAlignmment(alignment);
		
		SalesPosition parentSalesPos = new SalesPosition();
		if(salesPos.getPrnSalesPosId() != null){
			parentSalesPos.setId(salesPos.getPrnSalesPosId());
		}

		salesPosition.setParentSalesPosition(parentSalesPos);
	
		SalesOrgHierarchy salesOrgHierarchy = new SalesOrgHierarchy();
		if(null!=salesPos.getTAlgmntSalesHier() && salesPos.getTAlgmntSalesHier().getSalesHierId() != null){
			salesOrgHierarchy.setId(salesPos.getTAlgmntSalesHier().getSalesHierId());
		}
		
		SalesOrgHierarchy parentOrgHier = new SalesOrgHierarchy();
		if(null!=salesPos.getPrnSalesHierId()){
			parentOrgHier.setId(salesPos.getPrnSalesHierId());
			salesOrgHierarchy.setParentHierarchy(parentOrgHier);
		}else{
			salesOrgHierarchy.setParentHierarchy(null);
		}
		if(null != salesPos.getTAlgmntSalesHier()){
			salesOrgHierarchy.setName(salesPos.getTAlgmntSalesHier().getHierName());
			salesOrgHierarchy.setActive(salesPos.getTAlgmntSalesHier().getActiveFlag()=='Y'?true:false);
			salesOrgHierarchy.setLevel(salesPos.getTAlgmntSalesHier().getHierLevel());
			salesOrgHierarchy.setCreatedBy(salesPos.getTAlgmntSalesHier().getCreatedBy());
			salesOrgHierarchy.setCreatedDate(salesPos.getTAlgmntSalesHier().getCreateDt());
			if(salesPos.getTAlgmntSalesHier().getUpdatedBy() != null && salesPos.getTAlgmntSalesHier().getUpdateDt() != null){
				salesOrgHierarchy.setUpdatedBy(salesPos.getTAlgmntSalesHier().getUpdatedBy());	
				salesOrgHierarchy.setUpdatedDate(salesPos.getTAlgmntSalesHier().getUpdateDt());
			}
			salesOrgHierarchy.setStartDate(salesPos.getTAlgmntSalesHier().getEffStartDt());
			if(salesPos.getTAlgmntSalesHier().getEffEndDt() != null){
				salesOrgHierarchy.setEndDate(salesPos.getTAlgmntSalesHier().getEffEndDt());			
			}
			salesOrgHierarchy.setTenantId(salesPos.getTAlgmntSalesHier().getTenantId());
			if(null!=salesPos.getTAlgmntSalesHier().getAssignZipFlag()){
				salesOrgHierarchy.setAssignZipFlag(salesPos.getTAlgmntSalesHier().getAssignZipFlag()=='Y'?true:false);	
			}
			if(null!=salesPos.getTAlgmntSalesHier().getMoveSPFlag()){
				salesOrgHierarchy.setMoveSpFlag(salesPos.getTAlgmntSalesHier().getMoveSPFlag()=='Y'?true:false);
			}
		}
		salesPosition.setHierarchy(salesOrgHierarchy);
		
		salesPosition.setSalesPositionType( SalesPositionType.getSalesPositionType(salesPos.getSalesPosTypeId()));//Enum
		salesPosition.setActive(salesPos.getActiveFlag()==CommonConstants.ACTIVE_Y?true:false);
		salesPosition.setCreatedBy(salesPos.getCreatedBy());
		salesPosition.setCreatedDate(salesPos.getCreateDt());
		if(salesPos.getUpdatedBy() != null){
			salesPosition.setUpdatedBy(salesPos.getUpdatedBy());	
		}
		if(salesPos.getUpdateDt() != null){
			salesPosition.setUpdatedDate(salesPos.getUpdateDt());			
		}
		salesPosition.setStartDate(salesPos.getEffStartDt());
		if(salesPos.getEffEndDt() != null){
			salesPosition.setEndDate(salesPos.getEffEndDt());	
		}
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
	
	
	
}
