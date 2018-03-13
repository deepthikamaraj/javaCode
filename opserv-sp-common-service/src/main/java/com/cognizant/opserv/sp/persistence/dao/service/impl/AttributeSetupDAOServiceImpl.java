package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.assembler.AttributeSetupModelAssembler;
import com.cognizant.opserv.sp.common.BusinessObjectType;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.dao.TAttrDataTypeDAO;
import com.cognizant.opserv.sp.core.dao.TAttrDefDAO;
import com.cognizant.opserv.sp.core.dao.TAttrGroupDAO;
import com.cognizant.opserv.sp.core.dao.TAttrGroupMapDAO;
import com.cognizant.opserv.sp.core.dao.TAttrRuleDAO;
import com.cognizant.opserv.sp.core.dao.TBussObjTmplDAO;
import com.cognizant.opserv.sp.core.dao.TCustAttrDAO;
import com.cognizant.opserv.sp.core.dao.TEmpAttrDAO;
import com.cognizant.opserv.sp.core.dao.TPrdAttrDAO;
import com.cognizant.opserv.sp.core.dao.TValMsgDAO;
import com.cognizant.opserv.sp.core.entity.TAlgmntTmpl;
import com.cognizant.opserv.sp.core.entity.TAttrDataType;
import com.cognizant.opserv.sp.core.entity.TAttrDef;
import com.cognizant.opserv.sp.core.entity.TAttrGroup;
import com.cognizant.opserv.sp.core.entity.TAttrGroupMap;
import com.cognizant.opserv.sp.core.entity.TAttrGroupMapId;
import com.cognizant.opserv.sp.core.entity.TAttrRule;
import com.cognizant.opserv.sp.core.entity.TBussEntity;
import com.cognizant.opserv.sp.core.entity.TBussObjTmpl;
import com.cognizant.opserv.sp.core.entity.TCustAttr;
import com.cognizant.opserv.sp.core.entity.TEmpAttr;
import com.cognizant.opserv.sp.core.entity.TPrdAttr;
import com.cognizant.opserv.sp.core.entity.TValMsg;
import com.cognizant.opserv.sp.core.entity.TValMsgId;
import com.cognizant.opserv.sp.exception.AttributeServiceException;
import com.cognizant.opserv.sp.model.EntityTemplate;
import com.cognizant.opserv.sp.model.attrb.AttributeDefinition;
import com.cognizant.opserv.sp.model.attrb.AttributeGroup;
import com.cognizant.opserv.sp.model.attrb.AttributeRule;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.AttributeSetupDAOService;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class AttributeSetupDAOServiceImpl contains all the Attribute Management services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 13/05/2016
 * ***************************************************************************
 */

@Component
public class AttributeSetupDAOServiceImpl implements AttributeSetupDAOService {

	/*
	 * TAttrGroupDAO has methods on attribute group entity
	 */
	@Autowired
	private TAttrGroupDAO tAttrGroupDAO;
	
	/*
	 * TAttrDefDAO has methods on attribute Definition entity
	 */
	@Autowired
	private TAttrDefDAO tAttrDefDAO;
	
	/*
	 * TAttrGroupMapDAO has methods on attribute Definition and group relation 
	 */
	@Autowired
	private TAttrGroupMapDAO tAttrGroupMapDAO;
	
	/*
	 * TCustAttrDAO has methods on Customer Attributes entity
	 */
	@Autowired
	private TCustAttrDAO tCustAttrDAO;
	
	/*
	 * TPrdAttrDAO has methods on Product Attributes entity
	 */
	@Autowired
	private TPrdAttrDAO	tPrdAttrDAO;

	/*
	 * TEmpAttrDAO has methods on Employee Attributes entity
	 */
	@Autowired
	private TEmpAttrDAO	tEmpAttrDAO;
	
	/*
	 * TBussObjTmplDAO has methods on business object template entity
	 */
	@Autowired
	private TBussObjTmplDAO	tBussObjTmplDAO;
	
	/*
	 * TAttrRuleDAO has methods on Attribute rule entity
	 */
	@Autowired
	private TAttrRuleDAO tAttrRuleDAO;
	
	/*
	 * TAttrRuleDAO has methods on Attribute data type entity
	 */
	@Autowired
	private TAttrDataTypeDAO tAttrDataTypeDAO;
	
	/*
	 * TValMsgDAO has methods on Attribute data type entity
	 */
	@Autowired
	private TValMsgDAO	tValMsgDAO;
	
	/**
	 * @method createAttrGrp - This method is to create attribute group and attributes,and update group attributes
	 * @param attributeGroup - Holds Attribute definition and group information
	 * @param template - holds template id
	 * @param userDetails - holds user details
	 * @return AttributeGroup 
	 */
	@Override
	public boolean createAttrGrp(AttributeGroup attributeGroup,
			EntityTemplate template, UserDetails userDetails) {
		try {
			TAttrGroup tAttrGroup = null;
			TAttrGroupMap tAttrGroupMap = null;
			boolean saveFlag = false;
			Integer templateId = template.getId().intValue();
			TBussObjTmpl tBussObjTmpl = tBussObjTmplDAO
					.findTBussObjTmplById(templateId);

			List<Object> attGrplist = tAttrGroupDAO
					.findTAttrGroupByGroupNameAndTBussObjTmpl(
							attributeGroup.getName(), templateId,
							CommonConstants.ACTIVE_Y,
							userDetails.getTenantId());
			if (attGrplist.isEmpty()) {
				tAttrGroup = AttributeSetupModelAssembler.toTAttrGroupEnity(attributeGroup.getName(),
						userDetails.getUserId(), userDetails.getTenantId(),
						tBussObjTmpl);
			} else {
				for (Object grpObj : attGrplist) {
					TAttrGroup tAttrGroupcheck = (TAttrGroup) grpObj;
					if (templateId == tAttrGroupcheck.getTBussObjTmpl()
							.getTmplId()) {
						tAttrGroup = (TAttrGroup) grpObj;
						break;
					} else {
						if (attGrplist.indexOf(grpObj) == attGrplist.size() - 1) {
							tAttrGroup =  AttributeSetupModelAssembler.toTAttrGroupEnity(attributeGroup.getName(),
									 userDetails.getUserId(),
									userDetails.getTenantId(), tBussObjTmpl);
						}
					}
				}
			}

			if (tAttrGroup.getAttrGroupId() != null) {
					if (attributeGroup.getAttrDefinitions()!=null && attributeGroup.getAttrDefinitions().get(0) != null) {
					AttributeDefinition attrbDef = attributeGroup.getAttrDefinitions().get(0);
					if (!attrbDef.getName().isEmpty()) {
						tAttrGroupMap = createOrUpdateAttrDef(tAttrGroup,
								attrbDef, tBussObjTmpl,userDetails);
						TAttrGroupMapId newId = tAttrGroupMap
								.getTAttrGroupMapId();
						newId.setAttrGroupId(tAttrGroup.getAttrGroupId());
						tAttrGroupMap.setTAttrGroupMapId(newId);
						saveFlag = true;
					}
				}
			} else {
				tAttrGroup = tAttrGroupDAO.createTAttrGroup(tAttrGroup);
				if (attributeGroup.getAttrDefinitions()!=null && attributeGroup.getAttrDefinitions().get(0) != null) {
					AttributeDefinition attrbDef = attributeGroup.getAttrDefinitions().get(0);
					if (!attrbDef.getName().isEmpty()) {
						tAttrGroupMap = createOrUpdateAttrDef(tAttrGroup,
								attrbDef, tBussObjTmpl, userDetails);
						TAttrGroupMapId newId = tAttrGroupMap
								.getTAttrGroupMapId();
						newId.setAttrGroupId(tAttrGroup.getAttrGroupId());
						tAttrGroupMap.setTAttrGroupMapId(newId);						
						tAttrGroupMap = tAttrGroupMapDAO.createTAttrGroupMap(tAttrGroupMap);
					}
				}
				saveFlag = true;
			}
			return saveFlag;
		} catch (OpservDataAccessException e) {
			throw new OpservDataAccessException(
					"Error while deleting Attribute group ", e);
		}
	}
	
	/**
	 * @method deleteAttrGrp - This method is to delete Attribute group
	 * @param attributeGroup - Holds Attribute definition and group information
	 * @param userDetails - holds user detail
	 */	
	@Override
	@Transactional(rollbackFor = AttributeServiceException.class)
	public boolean deleteAttrGrp(AttributeGroup attributeGroup,
			UserDetails userDetails) {
		try {
			Integer attrGrpId = attributeGroup.getId().intValue();
			List<TAttrGroupMap> list = tAttrGroupMapDAO
					.findTAttrGroupMapsByAttrgroupId(attrGrpId,
							userDetails.getTenantId());
			boolean result = true;
			boolean flag;
			int attrsMapped = 0;
			if (!list.isEmpty()) {
				for (TAttrGroupMap obj : list) {
					flag = isAttrMapped(obj.getTAttrDef().getAttrId(),
							attrGrpId,userDetails);
					if (flag) {
						result = false;
						attrsMapped = attrsMapped + 1;
					} 
				}
				if (attrsMapped == 0) {
					for (TAttrGroupMap obj : list) {
						TAttrDef tAttrDef = tAttrDefDAO.findTAttrDefById(obj
								.getTAttrDef().getAttrId());
						tAttrDef.setActiveFlag(CommonConstants.ACTIVE_N);
						tAttrDefDAO.updateTAttrDef(tAttrDef);
						obj.setActiveFlag(CommonConstants.ACTIVE_N);
						tAttrGroupMapDAO.updateTAttrGroupMap(obj);
					}
					
					TAttrGroup tAttrGroup = tAttrGroupDAO
							.findTAttrGroupById(attrGrpId);
					if (tAttrGroup != null) {
						tAttrGroup
								.setActiveFlag(CommonConstants.ACTIVE_N);
						tAttrGroupDAO.updateTAttrGroup(tAttrGroup);
					}
				}
			} else {
				TAttrGroup tAttrGroup = tAttrGroupDAO
						.findTAttrGroupById(attrGrpId);
				if (tAttrGroup != null) {
					tAttrGroup.setActiveFlag(CommonConstants.ACTIVE_N);
					tAttrGroupDAO.updateTAttrGroup(tAttrGroup);
				}
			}
			return result;
		} catch (OpservDataAccessException e) {
			throw new OpservDataAccessException(
					"Error while deleting Attribute group ", e);
		}
	}
	
	/**
	 * @method deleteAttributeFromAttributeGroup - This method is to delete Attribute from a group
	 * @param attributeGroup - Holds Attribute definition and group information
	 * @param userDetails -  holds user detail
	 */
	@Override
	public void deleteAttributeFromAttributeGroup(
			AttributeGroup attributeGroup, UserDetails userDetails) {
		try {
			TAttrDef tAttrDef = tAttrDefDAO.findTAttrDefById((long) attributeGroup.getAttrDefinitions().get(0).getId());
			TAttrGroup tAttrGroup = tAttrGroupDAO.findTAttrGroupById(attributeGroup.getId().intValue());
			List<TAttrGroupMap> list = tAttrGroupMapDAO
					.getTAttrGroupMapsByTAttrGroupAndTAttrDef(tAttrGroup,
							tAttrDef, 0, -1);
			if (!list.isEmpty()) {
				for (TAttrGroupMap obj : list) {
					obj.setActiveFlag(CommonConstants.ACTIVE_N);
					tAttrGroupMapDAO.updateTAttrGroupMap(obj);
				}
			}
			deleteAttribute(tAttrDef, attributeGroup.getId().intValue());
		} catch (OpservDataAccessException e) {
			throw new OpservDataAccessException(
					"Error while create Attribute group ", e);
		}		
	}
	
	/**
	 * @method updateGroupAttribute - this method is to update group attributes data
	 * @param attributeGroup - Holds Attribute definition and group information
	 * @param userDetails -  holds user detail
	 */
	@Override
	public void updateGroupAttribute(AttributeGroup attributeGroup,
			UserDetails userDetails) {
		try {
			TAttrGroupMap tAttrGroupMap = null;
			List<AttributeDefinition> attrbGrpMapList = attributeGroup.getAttrDefinitions();
			Integer attrGrpId = attributeGroup.getId().intValue();
			for (AttributeDefinition attrDef : attrbGrpMapList) {

				TAttrGroupMapId tAttrGroupMapId = new TAttrGroupMapId();
				tAttrGroupMapId.setAttrGroupId(attrGrpId);
				tAttrGroupMapId.setAttrId(attrDef.getId());

				tAttrGroupMap = tAttrGroupMapDAO
						.findTAttrGroupMapById(tAttrGroupMapId);

				tAttrGroupMap.setTenantId(userDetails.getTenantId());
				tAttrGroupMap.setAttrDesc(attrDef.getName());
				tAttrGroupMap.setTooltip(attrDef.getToolTip());
				tAttrGroupMap.setOrderSeq(attrDef.getSequence());

				if (attrDef.isEditable()){
					tAttrGroupMap
							.setEditableFlag(CommonConstants.ACTIVE_Y);
				}
				else{
					tAttrGroupMap
							.setEditableFlag(CommonConstants.ACTIVE_N);}

				if (attrDef.isVisible()){
					tAttrGroupMap
							.setVisibleFlag(CommonConstants.ACTIVE_Y);}
				else{
					tAttrGroupMap
							.setVisibleFlag(CommonConstants.ACTIVE_N);}

				if (attrDef.isMasked()){
					tAttrGroupMap
							.setMaskValueFlag(CommonConstants.ACTIVE_Y);}
				else{
					tAttrGroupMap
							.setMaskValueFlag(CommonConstants.ACTIVE_N);}

				if (attrDef.isManadatory()){
					tAttrGroupMap
							.setMandatoryFlag(CommonConstants.ACTIVE_Y);}
				else{
					tAttrGroupMap
							.setMandatoryFlag(CommonConstants.ACTIVE_N);}

				if (attrDef.isUnique()){
					tAttrGroupMap
							.setUniqueFlag(CommonConstants.ACTIVE_Y);}
				else{
					tAttrGroupMap
							.setUniqueFlag(CommonConstants.ACTIVE_N);}

				if(attrDef.isSearchable()){
					tAttrGroupMap.setSrchFlag(CommonConstants.ACTIVE_Y);
				}else{
					tAttrGroupMap.setSrchFlag(CommonConstants.ACTIVE_N);
				}
				tAttrGroupMapDAO.updateTAttrGroupMap(tAttrGroupMap);
			}
		} catch (OpservDataAccessException e) {
			throw new OpservDataAccessException(
					"Error while update Attribute ", e);
		}
	}
	
	/**
	 * @method checkUniqAttrGrp - This method is to check if group name is unique
	 * @param attGrpName - Holds group Name
	 * @param templateId - holds template Id
	 * @param userDetails -  holds user detail
	 * @return boolean- if unique
	 */
	@Override
	public boolean checkUniqAttrGrp(String attGrpName, Integer templateId,
			UserDetails userDetails){
		try{
			boolean flag = true;
			List<Object> list = tAttrGroupDAO
					.findTAttrGroupByGroupNameAndTBussObjTmpl(attGrpName,
							templateId, CommonConstants.ACTIVE_Y,
							userDetails.getTenantId());
			if (!list.isEmpty()) {
				flag = false;
			}
			return flag;
		}catch (OpservDataAccessException e) {
			throw new OpservDataAccessException(
					"Error while checking unique attr group name ", e);
		}
	}
	/**
	 * @method checkUniqAttrName - This method is to check if group Attribute name is unique
	 * @param attGrpName - Holds group Name
	 * @param attrName - Holds group Attribute Name
	 * @param tmpId - holds template Id
	 * @param userDetails  -  holds user detail
	 * @return String - if attribute Name is new/staticattr/duplicate
	 */
	@Override
	public String checkUniqAttrName(String attGrpName, String attrName,
			Integer tmpId, UserDetails userDetails) {
		
		try {
			String msg = null;
			List<Object> slist = tAttrDefDAO.findTAttrDefByAttrName(attrName,
					userDetails.getTenantId(), 0, -1);
			if (slist != null && !slist.isEmpty()) {
				for (Object map1 : slist) {
					TAttrDef tAttrDef = (TAttrDef) map1;
					if (tAttrDef.getActiveFlag().equals(
							CommonConstants.ACTIVE_Y)) {
						List<TAttrGroupMap> atrsGrpMapsList = tAttrGroupMapDAO
								.findTAttrGroupMapsByAttrId(tAttrDef
										.getAttrId());
						if (atrsGrpMapsList != null
								&& !atrsGrpMapsList.isEmpty()) {
							for (TAttrGroupMap tAttrGroupMap : atrsGrpMapsList) {
								if (tAttrGroupMap.getTAttrGroup()
										.getTBussObjTmpl().getTmplId()
										.intValue()==tmpId.intValue()) {
									msg = "duplicate";
									break;
								}
							}
						}

						if (msg == null && isAttrStatic(tAttrDef.getAttrId())) {
							msg = "Static Attr";
							break;
						}
					}
				}
			}
			if (msg == null) {
				msg = "new";
			}
			return msg;
		}catch (OpservDataAccessException e) {
			throw new OpservDataAccessException(
					"Error while checking unique attr name ", e);
		}
	}
	/**
	 * @Method fetchattrGrp - to fetch AttributeGroup by attGrpName
	 * @param attGrpName - holds attr group name
	 * @param tmpId - holds template id
	 * @return AttributeGroup - returns attribute group data
	 */
	@Override
	public AttributeGroup fetchattrGrp(String attGrpName,
			Integer tmpId, UserDetails userDetails) {
		TAttrGroup	attrgroup =  null;
		AttributeGroup  attributeGroupModel = null;
			List<Object> attGrplistNew = tAttrGroupDAO
					.findTAttrGroupByGroupNameAndTBussObjTmpl(
							attGrpName, tmpId,
							CommonConstants.ACTIVE_Y,
							userDetails.getTenantId());
			if (!attGrplistNew.isEmpty()) {
				for (Object grpObj : attGrplistNew) {
					attrgroup =  new TAttrGroup();
					TAttrGroup tAttrGroupcheck = (TAttrGroup) grpObj;
					if (tmpId == tAttrGroupcheck.getTBussObjTmpl()
							.getTmplId()) {
						attrgroup = (TAttrGroup) grpObj;
						break;
					} 
				}
			}
		if(null != attrgroup){
			if(attrgroup.getActiveFlag().equals(CommonConstants.ACTIVE_Y)){

				attributeGroupModel = new AttributeGroup();

				attributeGroupModel.setId(attrgroup.getAttrGroupId().longValue());

				attributeGroupModel.setName(attrgroup.getGroupName());

				List<AttributeDefinition> attributeDefinitionList =fetchAttributeDefinitionList(attributeGroupModel.getId().intValue(),userDetails);

				attributeGroupModel.setAttrDefinitions(attributeDefinitionList);

				if(attrgroup.getDefFlag().equals(CommonConstants.ACTIVE_Y)){
					attributeGroupModel.setDefault(true);
				}else{
					attributeGroupModel.setDefault(true);
				}
			}
		}
		return attributeGroupModel;
	}
	/**
	 * @method isAttrStatic - This method is to check if attr is static attr
	 * @param attrId - attribute id
	 * @return boolean
	 */
	private boolean isAttrStatic(Long attrId) {
		TAttrDef tAttrDef = tAttrDefDAO.findTAttrDefById(attrId);
		boolean staticAttr = true;
		if (tAttrDef.getDynAttrFlag().equals(CommonConstants.ACTIVE_Y)) {
			staticAttr = false;
		}
		return staticAttr;
	}
	
	/**
	 * This method isAttrMapped
	 * @Method isAttrMapped - This method is to check if attribute is already mapped
	 *  @param attrId - holds attribute id
	 *  @param attrGrpId - holds attribute group id
	 *  @param userDetails - holds user details
	 *  @return boolean
	 */
	
	public boolean isAttrMapped(Long attrId,Integer attrGrpId,UserDetails userDetails){
		try {
			boolean mapped = CommonConstants.FALSE;
			TAttrGroup tAttrGroup = tAttrGroupDAO.findTAttrGroupById(attrGrpId);
			TBussObjTmpl bussObjTmpl = tAttrGroup.getTBussObjTmpl();
			Set<TAlgmntTmpl> set=bussObjTmpl.getTAlgmntTmplss();
			for (TAlgmntTmpl tAlignmentTmpl : set) {
				if (tAlignmentTmpl.getActiveFlag()==CommonConstants.ACTIVE_Y) {
					SearchFilter<TAttrDef> searchFilter = new SearchFilter<TAttrDef>();
					TAttrDef tAttrDef = tAttrDefDAO.findTAttrDefById(attrId);
					OperatorInfo operator = new OperatorInfo();
					PaginationInfo pageInfo = new PaginationInfo(0, -1);
					operator.setLogicalOperator(LogicalOperatorEnum.AND);
					searchFilter.setOperatorInfo(operator);
					searchFilter.setPaginationInfo(pageInfo);
					searchFilter.setEntityClass(tAttrDef);
					if (tAttrDef.getEntityId().equals(
							BusinessObjectType.CUSTOMER.getId())) {
						
						List<TCustAttr> custList = tCustAttrDAO
								.getTCustAttrsByTAttrDef(searchFilter);
						if (!custList.isEmpty()) {
							mapped = CommonConstants.TRUE;
						}
					} else if (tAttrDef.getEntityId().equals(
							BusinessObjectType.PRODUCT.getId())) {
						List<TPrdAttr> prdList = tPrdAttrDAO
								.getTPrdAttrsByTAttrDef(searchFilter);
						if (!prdList.isEmpty()) {
							mapped = CommonConstants.TRUE;
						}
					} else if (tAttrDef.getEntityId().equals(
							BusinessObjectType.EMPLOYEE.getId())) {
						List<TEmpAttr> empList = tEmpAttrDAO.getTEmpAttrsByTAttrDef(
								attrId, userDetails.getTenantId());
						if (!empList.isEmpty()) {
							mapped = CommonConstants.TRUE;
						}
					}
				}
			}
			return mapped;
		} catch (OpservDataAccessException e) {
			throw new OpservDataAccessException(
					"Error while deleting Attribute group ", e);
		}
	}
	/**
	 * @method createOrUpdateAttrDef - This method createOrUpdateAttrDef.
	 * @param tAttrGroup the t attr group
	 * @param attrbDef the attrb def
	 * @param tBussObjTmpl the t buss obj tmpl
	 * @param userDetails the user details
	 * @return TAttrGroupMap
	 */
	public TAttrGroupMap createOrUpdateAttrDef(TAttrGroup tAttrGroup,
			AttributeDefinition attrbDef, TBussObjTmpl tBussObjTmpl,
			 UserDetails userDetails)
			{
		try {
			TAttrDef tAttrDef = null;
			List<Object> slist = new ArrayList<Object>();
			slist = tAttrDefDAO.findTAttrDefByAttrName(attrbDef.getName(),
					userDetails.getTenantId(), 0, -1);
			if (slist.isEmpty()) {
				tAttrDef = new TAttrDef();
			} else {
				for (Object obj : slist) {
					TAttrDef pcAtrDef = (TAttrDef) obj;
					List<TAttrGroupMap> atrsGrpMapsList = tAttrGroupMapDAO
							.findTAttrGroupMapsByAttrId(pcAtrDef.getAttrId());
					if (atrsGrpMapsList != null && !atrsGrpMapsList.isEmpty()) {
						for (TAttrGroupMap tAttrGroupMap : atrsGrpMapsList) {
							if (tAttrGroupMap.getTAttrGroup().getTBussObjTmpl()
									.getTmplId().equals(tBussObjTmpl.getTmplId())) {
								tAttrDef = (TAttrDef) obj;
								break;
							}
						}
					}
					if (tAttrDef == null) {
						tAttrDef = new TAttrDef();
					}
				}
			}

			TAttrDataType tAttrDataType = tAttrDataTypeDAO
					.findTAttrDataTypeById(attrbDef.getType().getId(),
							userDetails.getLocaleId());
			tAttrDef.setEntityId(attrbDef.getEntityId()); // ADDED TO GET ENTITY ID
			tAttrDef.setAttrName(attrbDef.getName());
			tAttrDef.setAttrDesc(attrbDef.getDescription());
			tAttrDef.setCreatedBy(userDetails.getUserId());
			tAttrDef.setCreateDt(DateUtil.getCurrentDate());
			tAttrDef.setTenantId(userDetails.getTenantId());
			tAttrDef.setUpdatedBy(userDetails.getUserId());
			tAttrDef.setUpdateDt(DateUtil.getCurrentDate());
			tAttrDef.setDisplayName(attrbDef.getDisplayName());
			tAttrDef.setAttrDataTypeId(tAttrDataType.gettAttrDataTypeId().getAttrDataTypeId());
			tAttrDef.setActiveFlag(CommonConstants.ACTIVE_Y);
			tAttrDef.setGroupAttrFlag(CommonConstants.ACTIVE_Y);
			tAttrDef.setDynAttrFlag(CommonConstants.ACTIVE_Y);
			tAttrDef.setMtrFlag(attrbDef.isMetricApplicable() ? CommonConstants.ACTIVE_Y
					: CommonConstants.ACTIVE_N);
			tAttrDef.setCvgFlag(attrbDef.isCVGApplicable() ? CommonConstants.ACTIVE_Y
					: CommonConstants.ACTIVE_N);
			tAttrDef.setElFlag(attrbDef.isETLApplicable() ? CommonConstants.ACTIVE_Y
					: CommonConstants.ACTIVE_N);

			tAttrDef.setEffStartDt(DateUtil.getCurrentDate());
			if (tAttrDataType.gettAttrDataTypeId().getAttrDataTypeId() == 8
					|| tAttrDataType.gettAttrDataTypeId().getAttrDataTypeId() == 3) {
				tAttrDef.setAttrType(CommonConstants.SINGLE_VALUED);
				if (attrbDef.getLength() != 0) {
				Integer attrlen =	attrbDef.getLength();
					tAttrDef.setAttrLen(attrlen.shortValue());
				}
				tAttrDef.setAllowedValue(null);
			} else if (tAttrDataType.gettAttrDataTypeId().getAttrDataTypeId() == 5
					|| tAttrDataType.gettAttrDataTypeId().getAttrDataTypeId() == 6
					|| tAttrDataType.gettAttrDataTypeId().getAttrDataTypeId() == 7) {
				tAttrDef.setAttrType(CommonConstants.MULTI_VALUED);
				tAttrDef.setAllowedValue(attrbDef.getAllowedValues());
				tAttrDef.setAttrLen(null);
			} else {
				tAttrDef.setAttrType(CommonConstants.SINGLE_VALUED);
				tAttrDef.setAllowedValue(null);
				tAttrDef.setAttrLen(null);
			}
		
			if (attrbDef.getId() == null) {
				if (tAttrDef.getAttrId() == null) {
					tAttrDef = tAttrDefDAO.createTAttrDef(tAttrDef);
				} else {
					tAttrDef = tAttrDefDAO.updateTAttrDef(tAttrDef);
				}
			} else {
				if (attrbDef.getId() != null) {
					tAttrDef.setAttrId(attrbDef.getId());
				}
				if (tAttrDef.getAttrId() != null) {
					tAttrDef = tAttrDefDAO.updateTAttrDef(tAttrDef);
				}
			}
			if (attrbDef.getRuleList() != null) {
				createAttrDefRule(tAttrDef, attrbDef, userDetails);
			} else {
				Set<TAttrRule> attrRules = tAttrDef.getTAttrRuless();
				if (attrRules != null && !attrRules.isEmpty()) {
					for (TAttrRule tattrRule : attrRules) {
						tattrRule
								.setActiveFlag(CommonConstants.ACTIVE_N);
						tAttrRuleDAO.updateTAttrRule(tattrRule);
					}
				}

			}
			TAttrGroupMap tAtroupMap = createAttrgroupMap(tAttrGroup, tAttrDef,
					attrbDef, userDetails);	
			if (!tAtroupMap.getAttrDesc().equals(attrbDef.getDescription())
					&& tAttrDef.getAttrId() != null) {
				updateTGroupMap(tAttrDef.getAttrId(), attrbDef.getDisplayName());
			}
			return tAtroupMap;
		} catch (OpservDataAccessException e) {
			throw new OpservDataAccessException(
					"Error while create Attribute group ", e);
		}
	}
	
	/**
	 * @Method createAttrgroupMap - This method createAttrgroupMap.
	 * @param tAttrGroup the t attr group
	 * @param tAttrDef the t attr def
	 * @param attrbDef the attrb def
	 * @param userDetails the user details
	 * @return TAttrGroupMap
	 */
	public TAttrGroupMap createAttrgroupMap(TAttrGroup tAttrGroup,
			TAttrDef tAttrDef, AttributeDefinition attrbDef, UserDetails userDetails)
			 {
		try {
			TAttrGroupMapId tAttrGroupMapId = null;
			TAttrGroupMap tAttrGroupMap = null;
			if (tAttrGroup.getAttrGroupId() != null) {
				if (tAttrDef.getAttrId() != null) {
					tAttrGroupMapId = new TAttrGroupMapId();
					tAttrGroupMapId.setAttrGroupId(tAttrGroup.getAttrGroupId());
					tAttrGroupMapId.setAttrId(tAttrDef.getAttrId());
					tAttrGroupMap = tAttrGroupMapDAO
							.findTAttrGroupMapById(tAttrGroupMapId);
				}
				if (attrbDef.getId() != null) {
					tAttrGroupMapId = new TAttrGroupMapId();
					tAttrGroupMapId.setAttrGroupId(tAttrGroup.getAttrGroupId());
					tAttrGroupMapId.setAttrId(attrbDef.getId());
					tAttrGroupMap = tAttrGroupMapDAO
							.findTAttrGroupMapById(tAttrGroupMapId);
					
				}
			}
			if (tAttrGroupMap == null) {
				tAttrGroupMapId = new TAttrGroupMapId();
				tAttrGroupMap = new TAttrGroupMap();
				Set<TAttrGroupMap> tAttrGroupMapss = new HashSet<TAttrGroupMap>();
				tAttrGroupMapId.setAttrId(tAttrDef.getAttrId());
				tAttrGroupMap.setActiveFlag(CommonConstants.ACTIVE_Y);
				tAttrGroupMap.setAttrDesc(attrbDef.getDescription());
				tAttrGroupMap.setCreatedBy(userDetails.getUserId());
				tAttrGroupMap.setCreateDt(DateUtil.getCurrentDate());
				tAttrGroupMap.setDisplayName(attrbDef.getDisplayName());
				tAttrGroupMap.setEditableFlag(CommonConstants.ACTIVE_Y);
				tAttrGroupMap
						.setMandatoryFlag(CommonConstants.ACTIVE_N);
				tAttrGroupMap
						.setMaskValueFlag(CommonConstants.ACTIVE_N);
				tAttrGroupMap.setTenantId(userDetails.getTenantId());
				tAttrGroupMap.setTooltip(attrbDef.getToolTip());
				tAttrGroupMap.setUniqueFlag(CommonConstants.ACTIVE_N);
				tAttrGroupMap.setUpdatedBy(userDetails.getUserId());
				tAttrGroupMap.setUpdateDt(DateUtil.getCurrentDate());
				tAttrGroupMap.setVisibleFlag(CommonConstants.ACTIVE_Y);

				tAttrGroupMap.setTAttrGroupMapId(tAttrGroupMapId);
				tAttrGroupMap.setTAttrDef(tAttrDef);
				tAttrGroupMap.setTAttrGroup(tAttrGroup);
				//NOTE DEFAULT SEARCH FLAG IS TRUE
				tAttrGroupMap.setSrchFlag(CommonConstants.ACTIVE_Y);
				tAttrGroupMapss.add(tAttrGroupMap);

				tAttrDef.setTAttrGroupMapss(tAttrGroupMapss);
				tAttrGroup.setTAttrGroupMapss(tAttrGroupMapss);
			}
		return tAttrGroupMap;
		}catch (OpservDataAccessException e) {
			throw new OpservDataAccessException(
					"Error while create Attribute group ", e);
		}
	}
	
	/**
	 * @Method createAttrDefRule - This method createAttrDefRule.
	 * @param tAttrDef the t attr def
	 * @param attrbDef the attrb def
	 * @param userDetails the user details
	 * @return TAttrDef
	 */
	public TAttrDef createAttrDefRule(TAttrDef tAttrDef,
			AttributeDefinition attrbDef, UserDetails userDetails) {
		try {
			Set<TAttrRule> tAttrRuless = null;
			List<AttributeRule> rlist = attrbDef.getRuleList();
			tAttrRuless = new HashSet<TAttrRule>();
			for (AttributeRule attRule : rlist) {
				Set<TValMsg> tValMsgss = new HashSet<TValMsg>();
				TAttrRule tAttrRule = new TAttrRule();
				TValMsgId tValMsgId = new TValMsgId();
				if (tAttrDef.getAttrId() != null) {
					Set<TAttrRule> rules = tAttrDef.getTAttrRuless();
					if (rules != null) {
						for (TAttrRule rule : rules) {
							tAttrRule.setRuleId(rule.getRuleId());
						}
					}
				}
				tAttrRule.setActiveFlag(CommonConstants.ACTIVE_Y);
				tAttrRule.setValTypeId(attRule.getRuleType().getId());
				tAttrRule.setCreatedBy(userDetails.getUserId());
				tAttrRule.setCreateDt(DateUtil.getCurrentDate());
				tAttrRule.setTenantId(userDetails.getTenantId());
				tAttrRule.setUpdatedBy(userDetails.getUserId());
				tAttrRule.setUpdateDt(DateUtil.getCurrentDate());
				tAttrRule.setMinValue(CommonConstants.EMPTY_STR
						+ attRule.getMinValue());
				tAttrRule.setMaxValue(CommonConstants.EMPTY_STR
						+ attRule.getMaxValue());
				if (!attRule.getPattern().isEmpty()) {
						tAttrRule.setRuleExpr(attRule.getPattern());
				}
				tValMsgId.setLocaleId(userDetails.getLocaleId());
				tValMsgId.setRuleId(tAttrRule.getRuleId());
				TValMsg tValMsg = tValMsgDAO.findTValMsgById(tValMsgId);
				if (tValMsg == null) {
					tValMsg = new TValMsg();
				}
				if (!attRule.getCode().isEmpty()) {
					tValMsg.setErrMsg(attRule.getCode());
				} else {
					tValMsg.setErrMsg(" ");
				}
				tValMsg.setTValMsgId(tValMsgId);
				tValMsg.setCreatedBy(userDetails.getUserId());
				tValMsg.setCreateDt(DateUtil.getCurrentDate());
				tValMsg.setUpdatedBy(userDetails.getUserId());
				tValMsg.setUpdateDt(DateUtil.getCurrentDate());
				tValMsg.setTenantId(userDetails.getTenantId());
				tValMsg.setTAttrRule(tAttrRule);

				if (tAttrDef.getAttrId() != null
						&& tAttrRule.getRuleId() == null) {
					tAttrRule.setTAttrDef(tAttrDef);
					tAttrRule = tAttrRuleDAO.createTAttrRule(tAttrRule);
					tValMsgId.setRuleId(tAttrRule.getRuleId());
					tValMsg.setTValMsgId(tValMsgId);
				} else {
					tAttrRule.setTAttrDef(tAttrDef);
					tValMsgss.add(tValMsg);
					tAttrRule.setTValMsgss(tValMsgss);
					tAttrRule = tAttrRuleDAO.updateTAttrRule(tAttrRule);
					tValMsgId.setRuleId(tAttrRule.getRuleId());
					tValMsg.setTValMsgId(tValMsgId);
				}
				tValMsgss.add(tValMsg);
				tAttrRule.setTValMsgss(tValMsgss);
				tAttrRule.setTAttrDef(tAttrDef);
				tAttrRuless.add(tAttrRule);
			}
			tAttrDef.setTAttrRuless(tAttrRuless);
			return tAttrDef;
		} catch (OpservDataAccessException e) {
			throw new OpservDataAccessException(
					"Error while create Attribute group ", e);
		}
	}
	
	/**
	 * @Method updateTGroupMap - This method updateTGroupMap.
	 * @param attrId the attr id
	 * @param attrDisplayName the attr display name
	 * @return void
	 */
	public void updateTGroupMap(Long attrId, String attrDisplayName) {
		List<TAttrGroupMap> tAttrGroupMapList = tAttrGroupMapDAO
				.findTAttrGroupMapsByAttrId(attrId);
		if (!tAttrGroupMapList.isEmpty()) {
			for (TAttrGroupMap tAttrGpMap : tAttrGroupMapList) {
				if (tAttrGpMap.getActiveFlag().equals(
						CommonConstants.ACTIVE_Y)) {
					tAttrGpMap.setAttrDesc(attrDisplayName);
					tAttrGpMap.setDisplayName(attrDisplayName);
					tAttrGpMap.setTooltip(attrDisplayName);
					tAttrGroupMapDAO.updateTAttrGroupMap(tAttrGpMap);
				}
			}
		}
	}
	/**
	 * @Method - deleteAttribute - deleted attribute
	 * @param tAttrDef - attribute definition
	 * @param attrGrpId - group id
	 * @return boolean
	 */
	public boolean deleteAttribute(TAttrDef tAttrDef, Integer attrGrpId) {
		try {
			boolean atrDel = true;
			List<TAttrGroupMap> attrsList = tAttrGroupMapDAO
					.findTAttrGroupMapsByAttrId(tAttrDef.getAttrId());
			if (!attrsList.isEmpty() &&  ! (attrsList.size() == 1
					&& tAttrDef.getDynAttrFlag().equals(
							CommonConstants.ACTIVE_Y)) ) {
					for (TAttrGroupMap tAttrGrpMap : attrsList) {
						if (!tAttrGrpMap.getTAttrGroup().getAttrGroupId()
								.equals(attrGrpId)) {
							Set<TBussEntity> set = tAttrGrpMap.getTAttrGroup().getTBussObjTmpl().getTBussObj().getTBussEntitiess();
							for (TBussEntity tBussEntity : set) {
								if (tAttrDef.getEntityId() != tBussEntity
										.getEntityId()) {
									tAttrGrpMap.setActiveFlag(CommonConstants.ACTIVE_N);
									tAttrGroupMapDAO.updateTAttrGroupMap(tAttrGrpMap);
								}else{
									atrDel = false;
								}
							}
						}
					}
				//}
			}
			if(atrDel){
				delAtr(tAttrDef);
			}
			return atrDel;
		}  catch (OpservDataAccessException e) {
			throw new OpservDataAccessException(
					"Error while delete Attribute ", e);
		}
	}
	/**
	 * @Method delAtr - to delete Attribute
	 * @param tAttrDef - - attribute definition
	 */
	private void delAtr(TAttrDef tAttrDef){
		tAttrDef.setActiveFlag(CommonConstants.ACTIVE_N);
		tAttrDefDAO.updateTAttrDef(tAttrDef);
	}

	@Override
	public List<AttributeDefinition> getTemplateAttributes(
			EntityTemplate template, UserDetails userDetails) throws OpservDataAccessException{
		try
		{
		List<AttributeDefinition> attrDef =new ArrayList<AttributeDefinition>();
		 List<AttributeGroup> listOfAttrGrp = getAttributeGroupsbyTemplateId(template.getId(), template.getEntity().getId(), userDetails.getTenantId(),userDetails.getLocaleId(), 0,1000);
                 for(AttributeGroup attrGrp :listOfAttrGrp )
                      {
                	 attrDef= getAttributesByGroup(attrGrp,userDetails);
                        }
		
		
		
		return attrDef;
		}
		catch (RuntimeException exception) {
			throw new OpservDataAccessException(
					"Error occurred while fetching attributes by template ", exception);
		}
	}
	private List<AttributeGroup> getAttributeGroupsbyTemplateId(
			Long templateId, Integer entityId, Short tenantId, String localeId,
			Integer startIndex, Integer maxRows) throws OpservDataAccessException{
		try
		{
		Map<Integer, String> map = new HashMap<Integer, String>();
		Set<Integer> set = new HashSet<Integer>();
		List<AttributeGroup> list = new ArrayList<AttributeGroup>();

		List<Object[]> atrTypeList = tAttrDataTypeDAO
				.findTAttrDataTypesByLocaleId(localeId, 'Y');
		if (null!= atrTypeList   && !atrTypeList.isEmpty()) {
			for (Object[] obj : atrTypeList) {
				map.put((Integer) obj[0], (String) obj[1]);
			}
		}

		List<Object[]> attrList = tAttrGroupDAO
				.findActiveTAttrGroupByTBussObjTmpl(templateId.intValue(),
						tenantId, 'Y');
		if (null!= attrList   && !attrList.isEmpty()) {
		list=AttributeSetupModelAssembler.convertAttrGroupToAttrDef(attrList,entityId,list,set);
		}
		
		List<Object[]> attrGrpList = tAttrGroupDAO
				.findActiveGroupsByTBussObjTmpl(templateId.intValue(), 'Y',
						tenantId);
		if (null!= attrGrpList   && !attrGrpList.isEmpty()) {
		list=AttributeSetupModelAssembler.convertAttrGroupListToAttrGroup(attrGrpList,list,set);
		}
		return list;
		}
		catch (RuntimeException e) {
			throw new OpservDataAccessException(
					"Error occurred while fetching attributegroups by template ", e);
		}

	}
	@Override
	public AttributeDefinition saveAttributeDefinition(
			AttributeDefinition attrDefinition, UserDetails userDetails) throws OpservDataAccessException{
		try {
			
			TAttrDef tAttrDef = new TAttrDef();
			
			tAttrDef=AttributeSetupModelAssembler.convertAttrDefModelToTAttrDefEntity(attrDefinition, userDetails);
			tAttrDef = tAttrDefDAO.createTAttrDef(tAttrDef);

			return AttributeSetupModelAssembler.convertTAttrDefEntityToAttrDefModel(tAttrDef, userDetails) ;
			}
			catch (RuntimeException e) {
				throw new OpservDataAccessException(
						"Error occurred while saving attribute definition", e);
			}
	}

	@Override
	public List<AttributeDefinition> getAttributesByGroup(
			AttributeGroup attributeGroup, UserDetails userDetails) throws OpservDataAccessException{
		try
		{
		AttributeDefinition attrDef = new AttributeDefinition();
		TAttrGroup tAttrGroup = tAttrGroupDAO.findTAttrGroupById(attributeGroup
				.getId().intValue());
		
		List<AttributeDefinition> attrdefList = new ArrayList<AttributeDefinition>();

		List<TAttrGroupMap> tAttrGroupMap = tAttrGroupMapDAO
				.findTAttrGroupMapsByAttrgroupId(tAttrGroup.getAttrGroupId(), userDetails.getTenantId());
if(null!=tAttrGroupMap && !tAttrGroupMap.isEmpty()){
		for (TAttrGroupMap attr : tAttrGroupMap) {
			if (attr.getActiveFlag().equals(CommonConstants.ACTIVE_Y)) {

				Long attrId = attr.getTAttrGroupMapId().getAttrId();
				TAttrDef tAttrDef = tAttrDefDAO.findTAttrDefById(attrId);
				
				attrDef=AttributeSetupModelAssembler.convertTAttrDefEntityToAttrDefModel(tAttrDef, userDetails);
				attrdefList.add(attrDef);
			}
		}
}
		return attrdefList;
	}
		catch (RuntimeException e) {
			throw new OpservDataAccessException(
					"Error occurred while fetching attributes by group ", e);
		}
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
					
					AttributeDefinition  attributeDefinition = AttributeSetupModelAssembler.convertTAttrDefEntityToAttrDefModel(tAttrGroupMap.getTAttrDef(), userDetails) ;
					
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
