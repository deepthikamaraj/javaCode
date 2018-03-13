package com.cognizant.opserv.sp.service.extdattr;

import java.util.List;



import com.cognizant.opserv.sp.exception.AttributeServiceException;
import com.cognizant.opserv.sp.model.EntityTemplate;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.attrb.AttributeDefinition;
import com.cognizant.opserv.sp.model.attrb.AttributeGroup;

/**
 * ****************************************************************************.
 *
 * @class AttributeSetupService contains all the Attribute Management services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 13/05/2016
 * ***************************************************************************
 */
public interface AttributeSetupService {
	/**
	 * @method getTemplateAttributes - This method is to fetch template attributes
	 * @param template - Holds template information
	 * @param userDetails - holds user details
	 * @return list of AttributeDefinition
	 * @throws AttributeServiceException
	 */
	List<AttributeDefinition> getTemplateAttributes(EntityTemplate template,UserDetails userDetails) throws AttributeServiceException;
	/**
	 * @method saveAttributeDefinition - This method is to save attribute definition
	 * @param attrDefinition - Holds Attribute def 
	 * @param userDetails - holds user details
	 * @return AttributeDefinition 
	 * @throws AttributeServiceException
	 */
	AttributeDefinition saveAttributeDefinition(AttributeDefinition attrDefinition,UserDetails userDetails) throws AttributeServiceException;
	/**
	 * @method getAttributesByGroup - This method is to create attribute group and attributes,and update group attributes
	 * @param attributeGroup - Holds Attribute def and group information
	 * @param userDetails - holds user details
	 * @return list of AttributeDefinition
	 * @throws AttributeServiceException
	 */
	List<AttributeDefinition> getAttributesByGroup(AttributeGroup attributeGroup,UserDetails userDetails) throws AttributeServiceException;
	/**
	 * @method createAttributeGroup - This method is to create attribute group and attributes,and update group attributes
	 * @param attributeGroup - Holds Attribute def and group information
	 * @param template - holds template id
	 * @param userDetails - holds user details
	 * @return AttributeGroup 
	 * @throws AttributeServiceException
	 */
	boolean createAttributeGroup(AttributeGroup attributeGroup,EntityTemplate template,UserDetails userDetails) throws AttributeServiceException;
	/**
	 * @method deleteAttributeGroup - This method is to delete Attribute group
	 * @param attributeGroup - Holds Attribute definition and group information
	 * @param userDetails - holds user detail
	 * @throws AttributeServiceException
	 */
	void deleteAttributeGroup(AttributeGroup attributeGroup,UserDetails userDetails) throws AttributeServiceException;
	/**
	 * @method deleteAttributesFromGroup - This method is to delete Attribute from a group
	 * @param attributeGroup - Holds Attribute definition and group information
	 * @param userDetails -  holds user detail
	 * @throws AttributeServiceException
	 */
	void deleteAttributesFromGroup(AttributeGroup attributeGroup,UserDetails userDetails) throws AttributeServiceException;	
	/**
	 * @method updateGroupAttribute - this method is to update group attributes data
	 * @param attributeGroup - Holds Attribute definition and group information
	 * @param userDetails -  holds user detail
	 * @throws AttributeServiceException
	 */
	void updateGroupAttribute(AttributeGroup attributeGroup,UserDetails userDetails) throws AttributeServiceException;
	/**
	 * @method uniqAttrGrp - This method is to check if group name is unique
	 * @param attGrpName - Holds group Name
	 * @param templateId - holds template Id
	 * @param userDetails -  holds user detail
	 * @return boolean- if unique
	 * @throws AttributeServiceException
	 */
	boolean uniqAttrGrp(String attGrpName, Integer templateId,UserDetails userDetails) throws AttributeServiceException;
	/**
	 * @method uniqAttrName - This method is to check if group Attribute name is unique
	 * @param attGrpName - Holds group Name
	 * @param attrName - Holds group Attribute Name
	 * @param tmpId - holds template Id
	 * @param userDetails  -  holds user detail
	 * @return String - if attribute Name is new/staticattr/duplicate
	 * @throws AttributeServiceException
	 */
	String uniqAttrName(String attGrpName,String attrName,Integer tmpId,UserDetails userDetails) throws AttributeServiceException;
	/**
	 * @Method fetchAttributeGroup - to fetch AttributeGroup by group name and template id
	 * @param attGrpName - holds group name
	 * @param tmpId - holds template id
	 * @param userDetails
	 * @return AttributeGroup
	 * @throws AttributeServiceException
	 */
	AttributeGroup fetchAttributeGroup(String attGrpName,
			Integer tmpId, UserDetails userDetails)
			throws AttributeServiceException;
}
