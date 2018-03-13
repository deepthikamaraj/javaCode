package com.cognizant.opserv.sp.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.entity.TAttrDef;
import com.cognizant.opserv.sp.core.entity.TAttrGroup;
import com.cognizant.opserv.sp.core.entity.TBussObjTmpl;
import com.cognizant.opserv.sp.model.attrb.AttributeDefinition;
import com.cognizant.opserv.sp.model.attrb.AttributeGroup;
import com.cognizant.opserv.sp.model.auth.UserDetails;
/**
 * The Class AttributeSetupModelAssembler.
 */
public class AttributeSetupModelAssembler {
	/**
	 * Instantiates a new attribute setup entity assembler.
	 */
	private AttributeSetupModelAssembler(){
	}
	public static TAttrDef convertAttrDefModelToTAttrDefEntity(
			AttributeDefinition attrDefinition,UserDetails userDetails) {
		TAttrDef tAttrDef = new TAttrDef();
		
		tAttrDef.setActiveFlag(CommonConstants.ACTIVE_Y);
		tAttrDef.setAttrDataTypeId(1);
		tAttrDef.setAttrDesc(attrDefinition.getDescription());
		tAttrDef.setAttrLen( (short) attrDefinition.getLength());
		tAttrDef.setAttrName(attrDefinition.getName());
		tAttrDef.setAttrType('S');
		tAttrDef.setCreatedBy(userDetails.getStaffId());
		tAttrDef.setCreateDt(DateUtil.getCurrentDate());
		tAttrDef.setDisplayName(attrDefinition.getDisplayName());
		if(attrDefinition.isExtended())
		{
			tAttrDef.setDynAttrFlag(CommonConstants.ACTIVE_Y);
		}
		else
		{
			tAttrDef.setDynAttrFlag(CommonConstants.ACTIVE_N);
		}
		if(attrDefinition.isCVGApplicable())
		{
			tAttrDef.setCvgFlag(CommonConstants.ACTIVE_Y);
		}
		else
		{
			tAttrDef.setCvgFlag(CommonConstants.ACTIVE_N);
		}
		
		if (attrDefinition.getEndDate() != null) {
			tAttrDef.setEffEndDt(attrDefinition.getEndDate());
		}
		tAttrDef.setEffStartDt(attrDefinition.getStartDate());
		tAttrDef.setAllowedValue(attrDefinition.getAllowedValues());
		if(attrDefinition.isETLApplicable())
		{
			tAttrDef.setElFlag(CommonConstants.ACTIVE_Y);
		}
		else
		{
			tAttrDef.setElFlag(CommonConstants.ACTIVE_N);
		}
		
		tAttrDef.setEntityId(attrDefinition.getEntityId());
		if(attrDefinition.isGroupAttribute())
		{
			tAttrDef.setGroupAttrFlag(CommonConstants.ACTIVE_Y);
		}
		else
		{
			tAttrDef.setGroupAttrFlag(CommonConstants.ACTIVE_N);
		}
		if(attrDefinition.isMetricApplicable())
		{
			tAttrDef.setMtrFlag(CommonConstants.ACTIVE_Y);
		}
		else
		{
			tAttrDef.setMtrFlag(CommonConstants.ACTIVE_N);
		}
		
		
		tAttrDef.setTenantId(userDetails.getTenantId());
		
		return tAttrDef;
	}

	public static AttributeDefinition convertTAttrDefEntityToAttrDefModel(
			TAttrDef tAttrDef,UserDetails userDetails) {
		
		AttributeDefinition attrDefCreated = new AttributeDefinition();
		
		if(tAttrDef != null){
			if(tAttrDef.getAttrId()!=null){
				attrDefCreated.setId(tAttrDef.getAttrId());	
			}
			if(tAttrDef.getAttrName()!=null){
				attrDefCreated.setName(tAttrDef.getAttrName());
			}
			if(tAttrDef.getDisplayName()!=null){
				attrDefCreated.setDisplayName(tAttrDef.getDisplayName());
			}
			if(tAttrDef.getAttrDesc()!=null){
				attrDefCreated.setDescription(tAttrDef.getAttrDesc());
			}
			if(tAttrDef.getAllowedValue()!=null){
				attrDefCreated.setAllowedValues(tAttrDef.getAllowedValue());	
			}
			if (tAttrDef.getGroupAttrFlag() != null && tAttrDef.getGroupAttrFlag().equals(CommonConstants.ACTIVE_Y)) {
				attrDefCreated.setGroupAttribute(true);
			}
			if (tAttrDef.getDynAttrFlag() != null && tAttrDef.getDynAttrFlag().equals(CommonConstants.ACTIVE_Y)) {
				attrDefCreated.setExtended(true);
			}
			if (tAttrDef.getActiveFlag() != null && tAttrDef.getActiveFlag().equals(CommonConstants.ACTIVE_Y)) {
				attrDefCreated.setActive(true);
			}
			
			if(tAttrDef.getAttrLen()!=null){
				attrDefCreated.setLength(tAttrDef.getAttrLen());
			}
			if(tAttrDef.getEntityId() !=null){
				attrDefCreated.setEntityId(tAttrDef.getEntityId());
			}
			if(tAttrDef.getTenantId()!=null){
				attrDefCreated.setTenantId(tAttrDef.getTenantId());
			}
			/*if(tAttrDef.getAttrDataTypeId() !=null){
				attrDefCreated.setType(tAttrDef.getAttrDataTypeId());
			}*/
			if (tAttrDef.getCvgFlag() != null && tAttrDef.getCvgFlag().equals(CommonConstants.ACTIVE_Y)) {
				attrDefCreated.setCVGApplicable(true);
			}
			if (tAttrDef.getElFlag() != null && tAttrDef.getElFlag().equals(CommonConstants.ACTIVE_Y)) {
				attrDefCreated.setETLApplicable(true);
			}
			if (tAttrDef.getMtrFlag() != null && tAttrDef.getMtrFlag().equals(CommonConstants.ACTIVE_Y)) {
				attrDefCreated.setMetricApplicable(true);
			}
		}
		return attrDefCreated;
	}
	/**
	 * To enity.
	 *
	 * @param attributeGroup the attribute group
	 * @param tAttrGroup the t attr group
	 * @param staffId the staff id
	 * @param tenantId the tenant id
	 * @param tBussObjTmpl the t buss obj tmpl
	 * @return the t attr group
	 */
	public static TAttrGroup toTAttrGroupEnity(String attributeGroup,
			 Integer staffId, Short tenantId,
			TBussObjTmpl tBussObjTmpl) {
		TAttrGroup tAttrGroup = new TAttrGroup();
		tAttrGroup.setGroupName(attributeGroup);
		tAttrGroup.setEffStartDt(DateUtil.getCurrentDate());
		tAttrGroup.setCreatedBy(staffId);
		tAttrGroup.setCreateDt(DateUtil.getCurrentDate());
		tAttrGroup.setTenantId(tenantId);
		tAttrGroup.setTBussObjTmpl(tBussObjTmpl);
		tAttrGroup.setUpdatedBy(staffId);
		tAttrGroup.setUpdateDt(DateUtil.getCurrentDate());
		tAttrGroup.setDefFlag(CommonConstants.ACTIVE_N);
		tAttrGroup.setActiveFlag(CommonConstants.ACTIVE_Y);
		tAttrGroup.setOrderSeq((byte) 1);
		return tAttrGroup;
	}
	public static List<AttributeGroup> convertAttrGroupToAttrDef(
			List<Object[]> attrList,Integer entityId,List<AttributeGroup> list,Set<Integer> set) {
		AttributeDefinition attribute = new AttributeDefinition();
		List<AttributeDefinition> attrs = new ArrayList<AttributeDefinition>();
		AttributeGroup attrgrp = new AttributeGroup();
		if (attrList != null && !attrList.isEmpty()) {
		for (Object[] atrObj : attrList) {
			Integer grpId = (Integer) atrObj[0];
			if (!set.contains(grpId)) {
				
				
				Integer entID = (Integer) atrObj[8];
				Character defFlag = (Character) atrObj[2];

				attrgrp.setId(grpId.longValue());
				attrgrp.setName((String) atrObj[1]);
				for (Object[] defObj : attrList) {
					Integer grpDefId = (Integer) defObj[0];
					if (grpDefId.intValue() == grpId.intValue()) {
						Character dynFlag = (Character) defObj[6];

					
						attribute.setId((Long) defObj[3]);
						attribute.setName((String) defObj[4]);
						// attribute.setAlias((String) defObj[5]);
						attribute.setExtended(true);
						// attribute.setDateTypeName(map.get((Integer)
						// defObj[7]));
                       if(CommonConstants.ACTIVE_Y.equals(dynFlag))
						 {
							if (entID.intValue() == entityId.intValue()) {

								attribute.setExtended(true);
								 if(CommonConstants.ACTIVE_Y.equals(dynFlag)) {

									attrgrp.setDefault(true);
								} else {

									attrgrp.setDefault(false);
								}
							} else {

								attrgrp.setDefault(true);

								attribute.setExtended(false);
							}
						} else {

							attrgrp.setDefault(true);
							attribute.setExtended(false);
						}
						attrs.add(attribute);
					}
				}

				attrgrp.setAttrDefinitions(attrs);
				list.add(attrgrp);
				set.add(grpId);
			}
		}
		}
		return list;
	}
	
	
	public static List<AttributeGroup> convertAttrGroupListToAttrGroup(
			List<Object[]> attrGrpList,List<AttributeGroup> list,Set<Integer> set) {
		
		AttributeGroup attrgrp = new AttributeGroup();
		if (attrGrpList != null && !attrGrpList.isEmpty()) {
			for (Object[] grpObj : attrGrpList) {
				Integer grpId = (Integer) grpObj[0];
				if (!set.contains(grpId)) {
					Character defFlag = (Character) grpObj[2];
					
					attrgrp.setId(grpId.longValue());
					attrgrp.setName((String) grpObj[1]);
					 if(CommonConstants.ACTIVE_Y.equals(defFlag)){
						attrgrp.setDefault(true);
					} else {
						attrgrp.setDefault(false);
					}
					list.add(attrgrp);
				}
			}
		}

		
				return list;
		
	}
}
