package com.cognizant.opserv.sp.assembler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.EntityType;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TAlgmntTmpl;
import com.cognizant.opserv.sp.core.entity.TAttrGroup;
import com.cognizant.opserv.sp.core.entity.TAttrGroupMap;
import com.cognizant.opserv.sp.core.entity.TAttrGroupMapId;
import com.cognizant.opserv.sp.core.entity.TBussEntity;
import com.cognizant.opserv.sp.core.entity.TBussObjTmpl;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.EntityTemplate;
import com.cognizant.opserv.sp.model.EntityTemplateAlignment;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;

public class EntityTemplateModelAssembler {

	public static List<EntityTemplateAlignment> convertTAlgmntTmplToEntityTempAlgnmntModel(
			List<TAlgmntTmpl> alignms,TBussObjTmpl baseTemplateDetails,EntityTemplate template) {
		List<EntityTemplateAlignment> list = new ArrayList<EntityTemplateAlignment>();
		EntityTemplateAlignment alignTmpl = new EntityTemplateAlignment();
		if (!(alignms.isEmpty())) {
			for (TAlgmntTmpl tAlgmntTmpl : alignms) {

				Alignment algmnt = new Alignment();
				algmnt.setName(tAlgmntTmpl.getTAlgmntSalesTeam().getTAlgmnt()
						.getAlgmntName());
				algmnt.setId(tAlgmntTmpl.getTAlgmntSalesTeam()
						.getTAlgmntSalesTeamId().getAlgmntId());

				SalesTeam salesTeam = new SalesTeam();
				salesTeam.setId(tAlgmntTmpl.getTAlgmntSalesTeam()
						.getTAlgmntSalesTeamId().getSalesTeamId());
				salesTeam.setName(tAlgmntTmpl.getTAlgmntSalesTeam()
						.getTSalesTeam().getSalesTeamName());
				BusinessUnit bussUnit = new BusinessUnit();
				bussUnit.setId(tAlgmntTmpl.getTAlgmntSalesTeam()
						.getTAlgmntSalesTeamId().getBussUnitId());
				bussUnit.setName(tAlgmntTmpl.getTAlgmntSalesTeam()
						.getTSalesTeam().getTBussUnit().getBussUnitName());
				salesTeam.setBusinessUnit(bussUnit);
				algmnt.setSalesTeam(salesTeam);
				alignTmpl.setStartDate(tAlgmntTmpl.getEffStartDt());
				alignTmpl.setAlignment(algmnt);
				EntityTemplate templ = new EntityTemplate();
				templ.setId(tAlgmntTmpl.getTBussObjTmpl().getTmplId()
						.longValue());
				templ.setName(template.getName());
				templ.setEntity(template.getEntity());
				alignTmpl.setTemplate(templ);
				
				if (tAlgmntTmpl.getActiveFlag() == 'Y') {
					alignTmpl.setActive(true);
				} else {
					alignTmpl.setActive(false);
				}

				alignTmpl.setId(tAlgmntTmpl.getAlgmntTmplId().longValue());

				if (alignTmpl.isActive() == true) {
					list.add(alignTmpl);
				}
			}
		}

		return list;

	}

	public static TBussObjTmpl convertEntityTempModelToTBussObjTmpl(
			TBussObjTmpl baseTemplate, EntityTemplate newTemplate,
			UserDetails userDetails) {

		TBussObjTmpl tBussObjTmpl = new TBussObjTmpl();
		tBussObjTmpl.setTBussObj(baseTemplate.getTBussObj());
		tBussObjTmpl.setTmplName(newTemplate.getName());
		tBussObjTmpl.setTmplDesc(newTemplate.getDescription());
		tBussObjTmpl.setDefFlag(CommonConstants.ACTIVE_Y);
		tBussObjTmpl.setActiveFlag(CommonConstants.ACTIVE_Y);
		tBussObjTmpl.setEffStartDt(DateUtil.getCurrentDate());
		tBussObjTmpl.setCreatedBy(userDetails.getUserId());
		tBussObjTmpl.setCreateDt(DateUtil.getCurrentDate());
		tBussObjTmpl.setTenantId(userDetails.getTenantId());
		return tBussObjTmpl;

	}
	
	public static TAttrGroup convertToTAttrGroupToNewTAttrGroup(
			TBussObjTmpl baseTemplateDetails,TBussObjTmpl newTemplate,
			UserDetails userDetails) {
		TAttrGroup newAttrGroup = new TAttrGroup();
		if (baseTemplateDetails.getTAttrGroupss() != null
				&& baseTemplateDetails.getTAttrGroupss().size() > 0) {
			for (TAttrGroup tAttrGroup : baseTemplateDetails
					.getTAttrGroupss()) {
				if(tAttrGroup.getActiveFlag().equals(CommonConstants.ACTIVE_Y)){
					
					newAttrGroup
							.setActiveFlag(CommonConstants.ACTIVE_Y);
					newAttrGroup.setTBussObjTmpl(newTemplate);
					newAttrGroup.setGroupName(tAttrGroup.getGroupName());
					newAttrGroup.setEffStartDt(DateUtil.getCurrentDate());
					newAttrGroup.setEffEndDt(tAttrGroup.getEffEndDt());
					newAttrGroup.setOrderSeq(tAttrGroup.getOrderSeq());
					newAttrGroup.setDefFlag(tAttrGroup.getDefFlag());
					newAttrGroup.setCreatedBy(userDetails.getUserId());
					newAttrGroup.setCreateDt(DateUtil.getCurrentDate());
					newAttrGroup.setTenantId(userDetails.getTenantId());
					
				}
			}
		}
		return newAttrGroup;
	}			
	
	public static TAttrGroupMap convertNewEntityTemplateAttrGroupMap(Set<TAttrGroup> tAttrGroup,
			TAttrGroup newAttrGroup, UserDetails userDetails){
		TAttrGroupMap newTAttrGroupMap = new TAttrGroupMap();
		if (!tAttrGroup.isEmpty()) {
			
			for (TAttrGroup attrGroup : tAttrGroup) {
				Set<TAttrGroupMap> attrGroupMap =attrGroup.getTAttrGroupMapss();
				Iterator<TAttrGroupMap> itr = attrGroupMap.iterator();
				while(itr.hasNext())
				{
				TAttrGroupMap tAttrGroupMap=itr.next();				
				
				TAttrGroupMapId tAttrGroupMapId = new TAttrGroupMapId();
				tAttrGroupMapId.setAttrGroupId(newAttrGroup
						.getAttrGroupId());
				tAttrGroupMapId.setAttrId(tAttrGroupMap.getTAttrDef()
						.getAttrId());			
				newTAttrGroupMap.setActiveFlag(tAttrGroupMap
							.getActiveFlag());				
				newTAttrGroupMap.setAttrDesc(tAttrGroupMap.getAttrDesc());
				newTAttrGroupMap.setCreatedBy(userDetails.getUserId());
				newTAttrGroupMap.setCreateDt(DateUtil.getCurrentDate());
				newTAttrGroupMap.setDisplayName(tAttrGroupMap
						.getDisplayName());
				newTAttrGroupMap.setEditableFlag(tAttrGroupMap
						.getEditableFlag());
				newTAttrGroupMap.setVisibleFlag(tAttrGroupMap
						.getVisibleFlag());
				newTAttrGroupMap.setMandatoryFlag(tAttrGroupMap
						.getMandatoryFlag());
				newTAttrGroupMap.setMaskValueFlag(tAttrGroupMap
						.getMaskValueFlag());
				newTAttrGroupMap.setOrderSeq(tAttrGroupMap.getOrderSeq());
				newTAttrGroupMap.setTAttrGroupMapId(tAttrGroupMapId);
				newTAttrGroupMap.setTenantId(userDetails.getTenantId());
				newTAttrGroupMap.setTooltip(tAttrGroupMap.getTooltip());
				newTAttrGroupMap
						.setUniqueFlag(tAttrGroupMap.getUniqueFlag());
				newTAttrGroupMap.setSortFlag(tAttrGroupMap.getSortFlag());
				}
			}
		}
		return newTAttrGroupMap;
	}
	
	
	public static List<EntityTemplate> convertEntityTempModel(List<TBussObjTmpl> list){
	List<EntityTemplate> templateList = new ArrayList<EntityTemplate>();
	Set<TBussObjTmpl> set = new HashSet<TBussObjTmpl>(list);
	if (set != null && set.size() != 0) {
		for (TBussObjTmpl tBussObjTmpl : set) {
			if (tBussObjTmpl.getActiveFlag() == null
					|| tBussObjTmpl.getActiveFlag().equals(
							CommonConstants.ACTIVE_Y)) {
				EntityTemplate entityDetails = new EntityTemplate();
				entityDetails.setId(tBussObjTmpl.getTmplId()
						.longValue());
				entityDetails.setName(tBussObjTmpl.getTmplName());
				entityDetails
						.setDescription(tBussObjTmpl.getTmplDesc());
				if (tBussObjTmpl.getDefFlag().equals(
						CommonConstants.ACTIVE_Y)
						|| tBussObjTmpl.getDefFlag() == 'y')
				{
					entityDetails.setDefault(true);
				}
				else
				{
					entityDetails.setDefault(false);
				}
				entityDetails
						.setStartDate(tBussObjTmpl.getEffStartDt());
				entityDetails.setEndDate(tBussObjTmpl.getEffEndDt());
				templateList.add(entityDetails);
				
			}
		}
	}
	return templateList;
	}

	public static EntityTemplate convertTBussEntityToTempModel(
			TBussEntity tBussEntity,TBussObjTmpl tBussObjTmpl, UserDetails details) {
		EntityTemplate entityTemplate = new EntityTemplate();
		
		
		if(tBussEntity!=null ){
			entityTemplate.setId(tBussObjTmpl.getTmplId().longValue());
			entityTemplate.setName(tBussObjTmpl.getTmplName());
			entityTemplate.setStartDate(tBussObjTmpl.getEffStartDt());
			entityTemplate.setEndDate(tBussObjTmpl.getEffEndDt());
			entityTemplate.setDescription(tBussObjTmpl.getTmplDesc());
			entityTemplate.setCreatedBy(tBussObjTmpl.getCreatedBy());
			entityTemplate.setTenantId(tBussObjTmpl.getTenantId());
			
			if(tBussEntity.getEntityName().equalsIgnoreCase("CUSTOMER")){
				entityTemplate.setEntity(  EntityType.CUSTOMER)	;
				
				}
			else if(tBussEntity.getEntityName().equalsIgnoreCase("PRODUCT")){
				entityTemplate.setEntity(  EntityType.PRODUCT)	;
				
				}
			else if(tBussEntity.getEntityName().equalsIgnoreCase("EMPLOYEE")){
				entityTemplate.setEntity(  EntityType.EMPLOYEE)	;
				
				}
			else if(tBussEntity.getEntityName().equalsIgnoreCase("SALESPOSITION")){
				entityTemplate.setEntity(  EntityType.SALESPOSITION)	;
				
				}
			else if(tBussEntity.getEntityName().equalsIgnoreCase("CALLPLANNING")){
				entityTemplate.setEntity(  EntityType.CALLPLANNING)	;
				
				}
			else if(tBussEntity.getEntityName().equalsIgnoreCase("SALESPOSITION_CUSTOMER")){
				entityTemplate.setEntity(  EntityType.SALESPOSITION_CUSTOMER)	;
				
				}
			else if(tBussEntity.getEntityName().equalsIgnoreCase("SALESPOSITION_PRODUCT")){
				entityTemplate.setEntity(  EntityType.SALESPOSITION_PRODUCT)	;
				
				}
			else if(tBussEntity.getEntityName().equalsIgnoreCase("SALESPOSITION_EMPLOYEE")){
				entityTemplate.setEntity(  EntityType.SALESPOSITION_EMPLOYEE)	;
				
				}
			else if(tBussEntity.getEntityName().equalsIgnoreCase("SALES_TEAM_CUSTOMER")){
				entityTemplate.setEntity(  EntityType.SALES_TEAM_CUSTOMER)	;
				
				}
			else if(tBussEntity.getEntityName().equalsIgnoreCase("SALES_TEAM_PRODUCT")){
				entityTemplate.setEntity(  EntityType.SALES_TEAM_PRODUCT)	;
				
				}
			else if(tBussEntity.getEntityName().equalsIgnoreCase("SALES_TEAM_CUSTOMER_PRODUCT")){
				entityTemplate.setEntity(  EntityType.SALES_TEAM_CUSTOMER_PRODUCT)	;
				
				}
			else if(tBussEntity.getEntityName().equalsIgnoreCase("SALESPOSITION_CUSTOMER_VIEW")){
				entityTemplate.setEntity(  EntityType.SALESPOSITION_CUSTOMER_VIEW)	;
				
				}
			
			
		}
		return entityTemplate;
	}
	
	
	public static TAlgmntTmpl convertEntityTempAlgmntmodelToTalgmntTmplEntity(
			TBussObjTmpl tBussObjTmpl,TAlgmntSalesTeam tAlgmntSalesTeam ,UserDetails details) {
		TAlgmntTmpl tAlgmntTmpl =new TAlgmntTmpl();
		tAlgmntTmpl.setTAlgmntSalesTeam(tAlgmntSalesTeam);
		tAlgmntTmpl.setTBussObjTmpl(tBussObjTmpl);
		tAlgmntTmpl.setCreatedBy(tBussObjTmpl.getCreatedBy());
		tAlgmntTmpl.setCreateDt(new Date());
		tAlgmntTmpl.setEffStartDt(tBussObjTmpl.getEffStartDt());
		tAlgmntTmpl.setEffEndDt(tBussObjTmpl.getEffEndDt());
		tAlgmntTmpl.setTenantId(tBussObjTmpl.getTenantId());
		tAlgmntTmpl.setActiveFlag(tBussObjTmpl.getActiveFlag());
		
		return tAlgmntTmpl;
		
	}
}
	

