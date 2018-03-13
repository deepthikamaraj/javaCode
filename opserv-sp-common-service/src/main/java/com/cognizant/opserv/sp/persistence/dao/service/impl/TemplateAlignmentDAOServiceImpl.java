package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.EntityType;
import com.cognizant.opserv.sp.core.dao.TAlgmntTmplDAOImpl;
import com.cognizant.opserv.sp.core.dao.TAttrGroupDAO;
import com.cognizant.opserv.sp.core.dao.TAttrGroupMapDAO;
import com.cognizant.opserv.sp.core.dao.TBussEntityDAO;
import com.cognizant.opserv.sp.core.entity.TAlgmntTmpl;
import com.cognizant.opserv.sp.core.entity.TAttrGroup;
import com.cognizant.opserv.sp.core.entity.TAttrGroupMap;
import com.cognizant.opserv.sp.core.entity.TBussEntity;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.EntityTemplate;
import com.cognizant.opserv.sp.model.attrb.AttributeDefinition;
import com.cognizant.opserv.sp.model.attrb.AttributeGroup;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.TemplateAlignmentDAOService;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class TemplateAlignmentDAOServiceImpl contains all the services for TemplateAlignment
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 04/05/2016
 * ***************************************************************************
 */
@Component
public class TemplateAlignmentDAOServiceImpl implements TemplateAlignmentDAOService{

	/**
	 *  TAlgmntTmplDAOImpl has all the services related to tAlgmntTmpl
	 */
	@Autowired
	private TAlgmntTmplDAOImpl tAlgmntTmplDAO;
	
	/**
	 *  TBussEntityDAO has all the services related to tBussEntity
	 */
	@Autowired
	private TBussEntityDAO tBussEntityDAO;
	
	/**
	 *  TAttrGroupDAO has all the services related to tAttrGroup
	 */
	@Autowired
	private TAttrGroupDAO tAttrGroupDAO;
	
	/**
	 *  TAttrGroupMapDAO has all the services related to tAttrGroupMap
	 */
	@Autowired
	private TAttrGroupMapDAO tAttrGroupMapDAO;
	
	/**
	 * @method getEntityTemplate: Gets the entity template.
	 *
	 * @param entity the entity
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return the entity template
	 * @throws OpservDataAccessException the opserv data access exception
	 * @method getEntityTemplate
	 */
	@Override
	public EntityTemplate getEntityTemplate(EntityType entity,Alignment alignment, UserDetails userDetails)
			throws OpservDataAccessException {
		
		EntityTemplate entityTemplate = new EntityTemplate();
		
		try{
		
		Integer entityId = entity.getId();
		
		TBussEntity tBussEntity = tBussEntityDAO.findTBussEntityById(entityId);
		
		Integer bussObjId= null;
		
		if(tBussEntity != null && tBussEntity.getTBussObj().getBussObjId() != null){
			bussObjId = tBussEntity.getTBussObj().getBussObjId();
		}
		
		Long alignmentId = alignment.getId();
		
		Long buId = alignment.getSalesTeam().getBusinessUnit().getId();
				
		Long salesTeamId = alignment.getSalesTeam().getId();
		
		List<TAlgmntTmpl> tAlgmntTmplList = tAlgmntTmplDAO.FindTAlgnmtTemptIdByAlgBuSalesIDs(salesTeamId, alignmentId, buId,userDetails.getTenantId());
	
		List<AttributeGroup> attrgroupsList = new ArrayList<AttributeGroup>();
		
		if (null != tAlgmntTmplList  && !tAlgmntTmplList.isEmpty()) {
		
			for (TAlgmntTmpl obj : tAlgmntTmplList) {
				
				if(obj.getActiveFlag().equals(CommonConstants.ACTIVE_Y)){
					
					if(obj.getTBussObjTmpl().getTBussObj().getBussObjId().equals(bussObjId) && obj.getTBussObjTmpl().getActiveFlag().equals(CommonConstants.ACTIVE_Y)){ 
						
							Integer templateId = obj.getTBussObjTmpl().getTmplId();
							
							attrgroupsList = fetchAttributeGroupsList(templateId,userDetails);
							
						}
					}
				}
			}
		entityTemplate.setGroups(attrgroupsList);
		entityTemplate.setDescription(entity.toString());
		entityTemplate.setEntity(entity);
		entityTemplate.setId(entityId.longValue());
		
		}catch (RuntimeException e) {
			throw new OpservDataAccessException("Error while fetching the entityTempalte details", e);
		} 
		return entityTemplate;
	}

	/**
	 * Fetch attribute groups list.
	 *
	 * @param templateId the template id
	 * @param userDetails the user details
	 * @return the list
	 */
	private List<AttributeGroup> fetchAttributeGroupsList(Integer templateId, UserDetails userDetails){
		
		List<AttributeGroup> attributeGroupList = new ArrayList<AttributeGroup>();
		
		List<TAttrGroup> tAttrGroupList = tAttrGroupDAO.getTAttrGroupsByTBussObjTmplId(templateId,userDetails.getTenantId());
		
		if(null != tAttrGroupList && !tAttrGroupList.isEmpty()){
			
			for(TAttrGroup attrgroup : tAttrGroupList){
				
				if(attrgroup.getActiveFlag().equals(CommonConstants.ACTIVE_Y)){
	
					   AttributeGroup  attributeGroup = new AttributeGroup();
					   
					   attributeGroup.setId(attrgroup.getAttrGroupId().longValue());
					   
					   attributeGroup.setName(attrgroup.getGroupName());
					   
					   List<AttributeDefinition> attributeDefinitionList =fetchAttributeDefinitionList(attrgroup.getAttrGroupId(),userDetails);
					   
					   attributeGroup.setAttrDefinitions(attributeDefinitionList);
					   
					   if(attrgroup.getDefFlag().equals(CommonConstants.ACTIVE_Y)){
						   attributeGroup.setDefault(true);
					   }else{
					   attributeGroup.setDefault(true);
					   }
					   attributeGroupList.add(attributeGroup);
					}
				}
			}
		return attributeGroupList;
	}

	/**
	 * Fetch attribute definition list.
	 *
	 * @param attrGroupId the attr group id
	 * @param userDetails the user details
	 * @return the list
	 */
	private List<AttributeDefinition> fetchAttributeDefinitionList(Integer attrGroupId, UserDetails userDetails){
		
		List<AttributeDefinition> attributeDefinitionList = new ArrayList<AttributeDefinition>();
	
		List<TAttrGroupMap> attrGroupMap= tAttrGroupMapDAO.findTAttrGroupMapsByAttrgroupId(attrGroupId,userDetails.getTenantId());
		
		if(null != attrGroupMap && !attrGroupMap.isEmpty()){
			
			for(TAttrGroupMap tAttrGroupMap : attrGroupMap){
				
				if(tAttrGroupMap.getActiveFlag().equals(CommonConstants.ACTIVE_Y)){
					
					  AttributeDefinition  attributeDefinition  = new AttributeDefinition();
					  attributeDefinition.setId(tAttrGroupMap.getTAttrGroupMapId().getAttrId());
					  attributeDefinition.setName(tAttrGroupMap.getTAttrDef().getAttrName());
					  attributeDefinition.setDisplayName(tAttrGroupMap.getDisplayName());
					  attributeDefinition.setAllowedValues(tAttrGroupMap.getTAttrDef().getAllowedValue());
										 
					  if(tAttrGroupMap.getEditableFlag().equals(CommonConstants.ACTIVE_Y)){
						  attributeDefinition.setEditable(true);
					  }else{
						  attributeDefinition.setEditable(false);
					  }
					  
					 if(tAttrGroupMap.getMandatoryFlag().equals(CommonConstants.ACTIVE_Y)){
						 attributeDefinition.setManadatory(true);
					 }else{
						 attributeDefinition.setManadatory(false);
					 }
					 
					 if(tAttrGroupMap.getMaskValueFlag().equals(CommonConstants.ACTIVE_Y)){
						 attributeDefinition.setMasked(true);
					 }else{
						 attributeDefinition.setMasked(false);
					 }
						 
					 if(tAttrGroupMap.getSortFlag() != null && tAttrGroupMap.getSortFlag().equals(CommonConstants.ACTIVE_Y)){
						 attributeDefinition.setSortable(true);
					 }else{
						 attributeDefinition.setSortable(false);
					 } 
					 
					 if(tAttrGroupMap.getVisibleFlag().equals(CommonConstants.ACTIVE_Y)){
						 attributeDefinition.setVisible(true);
					 }else{
						 attributeDefinition.setVisible(false);
					 }
					
					 if(tAttrGroupMap.getTAttrDef().getMtrFlag().equals(CommonConstants.ACTIVE_Y)){
						 attributeDefinition.setMetricApplicable(true);
					 }else{
						 attributeDefinition.setMetricApplicable(false);
					 }
						
					 if(tAttrGroupMap.getTAttrDef().getCvgFlag().equals(CommonConstants.ACTIVE_Y)){
						 attributeDefinition.setCVGApplicable(true);
					 }else{
						 attributeDefinition.setCVGApplicable(false);
					 }
					 
					 if(tAttrGroupMap.getTAttrDef().getElFlag().equals(CommonConstants.ACTIVE_Y)){
						 attributeDefinition.setETLApplicable(true);
					 }else{
						 attributeDefinition.setETLApplicable(false);
					 }
					 
					 if(tAttrGroupMap.getTAttrDef().getDynAttrFlag().equals(CommonConstants.ACTIVE_Y)){
						 attributeDefinition.setExtended(true);
					 }else{
						 attributeDefinition.setExtended(false); 
					 }
					 
					 if(tAttrGroupMap.getUniqueFlag().equals(CommonConstants.ACTIVE_Y)){
						 attributeDefinition.setUnique(true);
					 }else{
						 attributeDefinition.setUnique(false); 
					 }
					 
					 if(tAttrGroupMap.getOrderSeq() != null){
						 attributeDefinition.setSequence(tAttrGroupMap.getOrderSeq());  
					 }

					 attributeDefinitionList.add(attributeDefinition);
					}
				}
			}
		
		return attributeDefinitionList;
	}
}
