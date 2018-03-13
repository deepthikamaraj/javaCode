package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;

import com.cognizant.opserv.sp.model.EntityTemplate;
import com.cognizant.opserv.sp.model.attrb.AttributeDefinition;
import com.cognizant.opserv.sp.model.attrb.AttributeGroup;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class AttributeSetupDAOService contains all the Attribute Management services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 13/05/2016
 * ***************************************************************************
 */

public interface AttributeSetupDAOService {
	
	/**
	 * @method getTemplateAttributes - This method is to fetch template attributes
	 * @param template - Holds template information
	 * @param userDetails - holds user details
	 * @return list of AttributeDefinition
	
	 */
	List<AttributeDefinition> getTemplateAttributes(EntityTemplate template,
			UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * @method saveAttributeDefinition - This method is to save attribute definition
	 * @param attrDefinition - Holds Attribute def 
	 * @param userDetails - holds user details
	 * @return AttributeDefinition 
	
	 */
	AttributeDefinition saveAttributeDefinition(
			AttributeDefinition attrDefinition, UserDetails userDetails) throws OpservDataAccessException;

	/**
	 * @method getAttributesByGroup - This method is to create attribute group and attributes,and update group attributes
	 * @param attributeGroup - Holds Attribute def and group information
	 * @param userDetails - holds user details
	 * @return list of AttributeDefinition
	
	 */
	List<AttributeDefinition> getAttributesByGroup(
			AttributeGroup attributeGroup, UserDetails userDetails) throws OpservDataAccessException;

	
	

	
	/**
	 * @method createAttrGrp - This method is to create attribute group and attributes,and update group attributes
	 * @param attributeGroup - Holds Attribute definition and group information
	 * @param template - holds template id
	 * @param userDetails - holds user details
	 * @return AttributeGroup 
	 */
	boolean createAttrGrp(AttributeGroup attributeGroup,EntityTemplate template, UserDetails userDetails);
	/**
	 * @method deleteAttrGrp - This method is to delete Attribute group
	 * @param attributeGroup - Holds Attribute definition and group information
	 * @param userDetails - holds user detail
	 */
	boolean deleteAttrGrp(AttributeGroup attributeGroup, UserDetails userDetails);
	/**
	 * @method deleteAttributeFromAttributeGroup - This method is to delete Attribute from a group
	 * @param attributeGroup - Holds Attribute definition and group information
	 * @param userDetails -  holds user detail
	 */
	void deleteAttributeFromAttributeGroup(AttributeGroup attributeGroup,UserDetails userDetails);
	/**
	 * @method updateGroupAttribute - this method is to update group attributes data
	 * @param attributeGroup - Holds Attribute definition and group information
	 * @param userDetails -  holds user detail
	 */
	void updateGroupAttribute(AttributeGroup attributeGroup,UserDetails userDetails);
	/**
	 * @method checkUniqAttrGrp - This method is to check if group name is unique
	 * @param attGrpName - Holds group Name
	 * @param templateId - holds template Id
	 * @param userDetails -  holds user detail
	 * @return boolean- if unique
	 */
	boolean checkUniqAttrGrp(String attGrpName, Integer templateId,UserDetails userDetails);
	/**
	 * @method checkUniqAttrName - This method is to check if group Attribute name is unique
	 * @param attGrpName - Holds group Name
	 * @param attrName - Holds group Attribute Name
	 * @param tmpId - holds template Id
	 * @param userDetails  -  holds user detail
	 * @return String - if attribute Name is new/staticattr/duplicate
	 */
	String checkUniqAttrName(String attGrpName, String attrName, Integer tmpId,UserDetails userDetails);
	/**
	 * @Method fetchattrGrp - to fetch AttributeGroup by group name and template id
	 * @param attGrpName - holds group name
	 * @param tmpId - holds template id
	 * @param userDetails
	 * @return AttributeGroup
	 */
	AttributeGroup fetchattrGrp(String attGrpName, Integer tmpId,
			UserDetails userDetails);

}
