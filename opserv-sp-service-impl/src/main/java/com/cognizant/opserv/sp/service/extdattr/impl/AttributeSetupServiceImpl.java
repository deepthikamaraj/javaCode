package com.cognizant.opserv.sp.service.extdattr.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.exception.AttributeServiceException;
import com.cognizant.opserv.sp.exception.AttributeServiceException.AttributeServiceExceptionCode;
import com.cognizant.opserv.sp.model.EntityTemplate;
import com.cognizant.opserv.sp.model.attrb.AttributeDefinition;
import com.cognizant.opserv.sp.model.attrb.AttributeGroup;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.AttributeSetupDAOService;
import com.cognizant.opserv.sp.service.extdattr.AttributeSetupService;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class AttributeSetupServiceImpl contains all the Attribute Management services 
 * implementation
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 13/05/2016
 * ***************************************************************************
 */

@Service("attributeSetupService")
@Transactional(rollbackFor = AttributeServiceException.class)
public class AttributeSetupServiceImpl implements AttributeSetupService {

	/**
	 * AttributeSetupDAOService has the services to fetch attribute data.
	 */
	@Autowired
	private AttributeSetupDAOService attributeSetupDAOService;

	/**
	 * @method getTemplateAttributes - This method is to fetch template attributes
	 * @param template - Holds template information
	 * @param userDetails - holds user details
	 * @return list of AttributeDefinition
	 * @throws AttributeServiceException
	 */
	@Override
	public List<AttributeDefinition> getTemplateAttributes(
			EntityTemplate template, UserDetails userDetails)
			throws AttributeServiceException {
		try {
			if( null ==  template.getId()  || null == userDetails.getTenantId())
			{
				String params = "Required Data Missing";
				Object[] args = new Object[]{params};
				throw new  AttributeServiceException(args);
			}
			return attributeSetupDAOService.getTemplateAttributes(template, userDetails);
		} catch (OpservDataAccessException e) {
			Object[] args = new Object[]{"exception occured while fetching attributes by template"};
		    throw new AttributeServiceException(AttributeServiceExceptionCode.ATTR_SER_EX_CD_0010,"fetch_attributes_exception",args,e);
		}
		
	}

	/**
	 * @method saveAttributeDefinition - This method is to save attribute definition
	 * @param attrDefinition - Holds Attribute def 
	 * @param userDetails - holds user details
	 * @return AttributeDefinition 
	 * @throws AttributeServiceException
	 */
	@Override
	public AttributeDefinition saveAttributeDefinition(
			AttributeDefinition attrDefinition, UserDetails userDetails)
			throws AttributeServiceException {
		try {
			if( null ==  attrDefinition.getName()  || null == userDetails.getTenantId())
			{
				String params = "Required Data Missing";
				Object[] args = new Object[]{params};
				throw new  AttributeServiceException(args);
			}
		return attributeSetupDAOService.saveAttributeDefinition(attrDefinition, userDetails);
		} catch (OpservDataAccessException e) {
			Object[] args = new Object[]{"exception occured while saving attribute definition"};
		    throw new AttributeServiceException(AttributeServiceExceptionCode.ATTR_SER_EX_CD_0011,"save_attributedef_exception",args,e);
		}
	}

	/**
	 * @method getAttributesByGroup - This method is to create attribute group and attributes,and update group attributes
	 * @param attributeGroup - Holds Attribute def and group information
	 * @param userDetails - holds user details
	 * @return list of AttributeDefinition
	 * @throws AttributeServiceException
	 */
	@Override
	public List<AttributeDefinition> getAttributesByGroup(
			AttributeGroup attributeGroup, UserDetails userDetails)
			throws AttributeServiceException {
		try {
			if( null ==  attributeGroup.getId() || 
					 null == userDetails.getTenantId())
			{
				String params = "Required Data Missing";
				Object[] args = new Object[]{params};
				throw new  AttributeServiceException(args);
			}
		return attributeSetupDAOService.getAttributesByGroup(attributeGroup, userDetails);
		} catch (OpservDataAccessException e) {
			Object[] args = new Object[]{"exception occured while saving attribute definition"};
		    throw new AttributeServiceException(AttributeServiceExceptionCode.ATTR_SER_EX_CD_0012,"save_attributedef_exception",args,e);
		}
	}
	/**
	 * @method createAttributeGroup - This method is to create attribute group and attributes,and update group attributes
	 * @param attributeGroup - Holds Attribute def and group information
	 * @param template - holds template id
	 * @param userDetails - holds user details
	 * @return AttributeGroup 
	 * @throws AttributeServiceException
	 */
	@Override
	public boolean createAttributeGroup(AttributeGroup attributeGroup,
			EntityTemplate template, UserDetails userDetails)
			throws AttributeServiceException {
		try {
		return attributeSetupDAOService.createAttrGrp(attributeGroup,template,userDetails);
		} catch (OpservDataAccessException e) {
			Object[] args = new Object[] { attributeGroup.getId(), attributeGroup.getName()};
			throw new AttributeServiceException(
					AttributeServiceException.AttributeServiceExceptionCode.ATTR_SER_EX_CD_004,
					"Exception while create/update AttributeGroup", args, e);
		}
		
	}
	/**
	 * @method deleteAttributeGroup - This method is to delete Attribute group
	 * @param attributeGroup - Holds Attribute definition and group information
	 * @param userDetails - holds user detail
	 * @throws AttributeServiceException
	 */
	@Override
	public void deleteAttributeGroup(AttributeGroup attributeGroup,
			UserDetails userDetails) throws AttributeServiceException {
		try {
			attributeSetupDAOService.deleteAttrGrp(attributeGroup, userDetails);
		} catch (OpservDataAccessException e) {
			Object[] args = new Object[] { attributeGroup.getId(),attributeGroup.getName() };
			throw new AttributeServiceException(
					AttributeServiceException.AttributeServiceExceptionCode.ATTR_SER_EX_CD_005,
					"Exception while deleting AttributeGroup", args, e);
		}
	}
	/**
	 * @method deleteAttributesFromGroup - This method is to delete Attribute from a group
	 * @param attributeGroup - Holds Attribute definition and group information
	 * @param userDetails -  holds user detail
	 * @throws AttributeServiceException
	 */
	@Override
	public void deleteAttributesFromGroup(AttributeGroup attributeGroup,
			UserDetails userDetails) throws AttributeServiceException {
		try {
			attributeSetupDAOService.deleteAttributeFromAttributeGroup(attributeGroup,userDetails);
		} catch (OpservDataAccessException e) {
			Object[] args = new Object[] { attributeGroup.getAttrDefinitions()};
			throw new AttributeServiceException(
					AttributeServiceException.AttributeServiceExceptionCode.ATTR_SER_EX_CD_006,
					"Exception in delete attribute", args, e);
		}
	}
	/**
	 * @method updateGroupAttribute - this method is to update group attributes data
	 * @param attributeGroup - Holds Attribute definition and group information
	 * @param userDetails -  holds user detail
	 * @throws AttributeServiceException
	 */
	@Override
	public void updateGroupAttribute(AttributeGroup attributeGroup,
			UserDetails userDetails) throws AttributeServiceException {
		try {
			attributeSetupDAOService.updateGroupAttribute(attributeGroup,userDetails);
		} catch (OpservDataAccessException e) {
			Object[] args = new Object[] { attributeGroup.getAttrDefinitions()};
			throw new AttributeServiceException(
					AttributeServiceException.AttributeServiceExceptionCode.ATTR_SER_EX_CD_007,
					"Exception in update attribute", args, e);
		}
		
	}
	/**
	 * @method uniqAttrGrp - This method is to check if group name is unique
	 * @param attGrpName - Holds group Name
	 * @param templateId - holds template Id
	 * @param userDetails -  holds user detail
	 * @return boolean- if unique
	 * @throws AttributeServiceException
	 */
	@Override
	public boolean uniqAttrGrp(String attGrpName, Integer templateId,
			UserDetails userDetails) throws AttributeServiceException {
		try {
			return attributeSetupDAOService.checkUniqAttrGrp(attGrpName,
					templateId, userDetails);
		} catch (OpservDataAccessException e) {
			Object[] args = new Object[] { attGrpName, templateId };
			throw new AttributeServiceException(
					AttributeServiceException.AttributeServiceExceptionCode.ATTR_SER_EX_CD_008,
					"Exception in Unique attribute group check", args, e);
		}

	}
	/**
	 * @method uniqAttrName - This method is to check if group Attribute name is unique
	 * @param attGrpName - Holds group Name
	 * @param attrName - Holds group Attribute Name
	 * @param tmpId - holds template Id
	 * @param userDetails  -  holds user detail
	 * @return String - if attribute Name is new/staticattr/duplicate
	 * @throws AttributeServiceException
	 */
	@Override
	public String uniqAttrName(String attGrpName, String attrName,
			Integer tmpId, UserDetails userDetails)
			throws AttributeServiceException {
		try {
			return attributeSetupDAOService.checkUniqAttrName(attGrpName,
					attrName, tmpId, userDetails);
		} catch (OpservDataAccessException e) {
			Object[] args = new Object[] { attGrpName, attrName, tmpId };
			throw new AttributeServiceException(
					AttributeServiceException.AttributeServiceExceptionCode.ATTR_SER_EX_CD_009,
					"Exception in Unique attribute Name check", args, e);
		}
	}
	/**
	 * @Method fetchAttributeGroup - to fetch AttributeGroup by group name and template id
	 * @param attGrpName - holds group name
	 * @param tmpId - holds template id
	 * @param userDetails
	 * @return AttributeGroup
	 * @throws AttributeServiceException
	 */
	@Override
	public AttributeGroup fetchAttributeGroup(String attributeGroupName,
			Integer templateId, UserDetails userDetails)
			throws AttributeServiceException {
		try {
			AttributeGroup attrgrp = null;
			attrgrp =	attributeSetupDAOService.fetchattrGrp(attributeGroupName, templateId, userDetails);
		return attrgrp;
		} catch (OpservDataAccessException e) {
			Object[] args = new Object[] {attributeGroupName };
			throw new AttributeServiceException(
					AttributeServiceException.AttributeServiceExceptionCode.ATTR_SER_EX_CD_004,
					"Exception while fetching AttributeGroup", args, e);
		}
	}
}
