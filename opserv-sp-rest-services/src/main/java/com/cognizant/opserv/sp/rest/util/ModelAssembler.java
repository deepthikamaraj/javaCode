package com.cognizant.opserv.sp.rest.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cognizant.opserv.sp.common.AttributeType;
import com.cognizant.opserv.sp.common.MetricTriggerType;
import com.cognizant.opserv.sp.common.RuleType;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.EntityTemplate;
import com.cognizant.opserv.sp.model.GeographyAlignment;
import com.cognizant.opserv.sp.model.PostalCode;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.tenant.common.TenantHolder;
import com.cognizant.opserv.tenant.ctx.TenantContext;
import com.cognizant.opserv.tenant.vo.Tenant;
import com.cognizant.opserv.sp.model.attrb.AttributeDefinition;
import com.cognizant.opserv.sp.model.attrb.AttributeGroup;
import com.cognizant.opserv.sp.model.attrb.AttributeRule;

public class ModelAssembler {

	public static Alignment createEmptyAlignment() {
		Alignment al = new Alignment();
		BusinessUnit bu = new BusinessUnit();
		SalesTeam st = new SalesTeam();
		st.setBusinessUnit(bu);
		al.setSalesTeam(st);
		return al;
	}
	
	public static Alignment getAlignmentModel(Long al, Long bu, Long st) {
		Alignment alg = createEmptyAlignment();
		alg.setId(al);
		alg.getSalesTeam().setId(st);
		alg.getSalesTeam().getBusinessUnit().setId(bu);
		return alg;
	}
	
	public static SalesPosition getSalesPosition(Long salesPosId,Long salesHierId) {
		SalesPosition salespos = new SalesPosition();
		salespos.setId(salesPosId);
		if(salesHierId != null) {
			SalesOrgHierarchy hier = new SalesOrgHierarchy();
			hier.setId(salesHierId);
			salespos.setHierarchy(hier);
		}
		
		return salespos;
	}
	
	public static SalesPosition getSalesPosition(Long salesPosId,Long salesHierId,Long al, Long bu, Long st) {
		SalesPosition salespos = getSalesPosition(salesPosId,salesHierId);
		salespos.setAlignmment(getAlignmentModel(al,bu,st));
		return salespos;
	}	
	
	public static List<MetricTriggerType> getMetricTypes(List<MetricTriggerType> types) {
	
			List<MetricTriggerType> mtypes = new ArrayList<MetricTriggerType>();
			for(MetricTriggerType metricTriggerType: types){
				if(MetricTriggerType.ASSIGN_CUSTOMER == metricTriggerType.ASSIGN_CUSTOMER){
					mtypes.add(metricTriggerType);
				}
			/*	if(MetricTriggerType.UNASSIGN_CUSTOMER == metricTriggerType.CUSTOMER_UPDATE){
					mtypes.add(metricTriggerType);
				}*/
				if(MetricTriggerType.UNASSIGN_CUSTOMER == metricTriggerType.UNASSIGN_CUSTOMER){
					mtypes.add(metricTriggerType);
				}
			}
			
		return mtypes;
	}	
	
	public static UserDetails getUserDetails(short tenantId, String tenantCode) {
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId(tenantId);
		userDetails.setTenantCode(tenantCode);
		return userDetails;
	}
	
	public static UserDetails getUserDetails() {
		UserDetails userDetails = new UserDetails();
		
		Tenant tenant = TenantHolder.getTenant(TenantContext.getTenantKey());
		userDetails.setTenantId(tenant.getTenantID());
		userDetails.setTenantCode(tenant.getTenantCd());
		return userDetails;
	}	
	
	public static UserDetails getDefaultUserDetails() {
		return getUserDetails((short)1,"am");
	}
	
	public static Customer getCustomer(Long custId){
        Customer customer = new Customer();
        customer.setId(custId);
        return customer;
	}	
	
	public static CustomerAlignment getCustomerAlgmnt(Long custId){
		CustomerAlignment custAlgmnt = new CustomerAlignment();
		Customer customer = new Customer();		
		customer.setId(custId);
		custAlgmnt.setCustomer(customer);
        return custAlgmnt;
	}
	
	public static PostalCode getPostalCodeModel(String postalcode) {
		PostalCode postalcd= new PostalCode();
		postalcd.setCode(postalcode);
		return postalcd;
	}
	
	public static GeographyAlignment getGeographyAlignment(SalesPosition sp,PostalCode pc) {
		GeographyAlignment geoAlign = new GeographyAlignment();
		if(sp!=null){
			geoAlign.setSalesPosition(sp);
		}
		
		if(pc!=null){
			geoAlign.setPostalCode(pc);
		}
		
		return geoAlign;
	}
	
	public static AttributeGroup getAttributeGroup(Long grpId,Long attrId){
		AttributeGroup attrGrp = new AttributeGroup();
		attrGrp.setId(grpId);
		List<AttributeDefinition> attrDefinitions = new ArrayList<AttributeDefinition>();
		AttributeDefinition attrdef = new AttributeDefinition();
		attrdef.setId(attrId);
		attrDefinitions.add(attrdef );
		attrGrp.setAttrDefinitions(attrDefinitions);
        return attrGrp;
	}
	public static AttributeGroup getAttributeGroupWithAttrdefUpdate(Long grpId,Long attrId,String attrName){
		AttributeGroup attrGrp = new AttributeGroup();
		attrGrp.setId(grpId);
		List<AttributeDefinition> attrDefinitions = new ArrayList<AttributeDefinition>();
		AttributeDefinition attrdef = new AttributeDefinition();
		attrdef.setId(attrId);
		attrdef.setName(attrName);
		attrdef.setDescription(attrName);
		attrdef.setDisplayName(attrName);
		attrdef.setSequence(1);
		attrdef.setToolTip(attrName);
		attrdef.setEditable(false);
		attrdef.setManadatory(false);
		attrdef.setMasked(false);
		attrdef.setUnique(false);
		attrdef.setVisible(false);
		AttributeType type = AttributeType.TEXT;
		attrdef.setType(type);
		attrDefinitions.add(attrdef );
		attrGrp.setAttrDefinitions(attrDefinitions);
        return attrGrp;
	}
	public static AttributeGroup getAttributeGroupWithAttrdefCreate(String grpName,String attrName,Integer templateId,Integer grpid,Integer attrid){
		AttributeGroup attrGrp = new AttributeGroup();
		if(grpid!=1){
		attrGrp.setId(grpid.longValue());
		}
		attrGrp.setName(grpName);
		List<AttributeDefinition> attrDefinitions = new ArrayList<AttributeDefinition>();
		AttributeDefinition attrdef = new AttributeDefinition();
		if(attrid!=1){
			attrdef.setId(attrid.longValue());
		}
		attrdef.setName(attrName);
		attrdef.setDescription(attrName);
		attrdef.setDisplayName(attrName);
		attrdef.setSequence(1);
		attrdef.setToolTip(attrName);
		attrdef.setEditable(true);
		attrdef.setManadatory(true);
		attrdef.setMasked(true);
		attrdef.setUnique(true);
		attrdef.setVisible(true);
		attrdef.setMetricApplicable(true);
		attrdef.setETLApplicable(true);
		attrdef.setLength(50);
		attrdef.setAllowedValues("hello hi h");
		attrdef.setCVGApplicable(true);
		AttributeType type = AttributeType.TEXT;
		attrdef.setType(type);
		List<AttributeRule> ruleList = new ArrayList<AttributeRule>();
		AttributeRule attrRule = new AttributeRule();
		attrRule.setPattern("regex");
		attrRule.setCode("errorMsg");
		attrRule.setRuleType(RuleType.REGULAREXPRESSON);
		ruleList.add(attrRule );
		attrdef.setRuleList(ruleList);
		attrdef.setEntityId(2);
		attrDefinitions.add(attrdef);
		attrGrp.setAttrDefinitions(attrDefinitions);
        return attrGrp;
	}

	public static EntityTemplate getEntityTemplate(Integer templateId) {
		EntityTemplate template = new EntityTemplate();
		template.setId(templateId.longValue());
		return template;
	}
	
	public static AttributeDefinition getAttributeDef(String attrName, Integer entityId) {
	AttributeDefinition attrDefinition=new AttributeDefinition();
	attrDefinition.setDisplayName("last_name");
	attrDefinition.setCreatedBy(1);
	attrDefinition.setCreatedDate(new Date());
	attrDefinition.setName(attrName);
	attrDefinition.setType(AttributeType.CHECKBOX);
	attrDefinition.setTenantId((short) 1);
	attrDefinition.setLength(10);
	
	attrDefinition.setEntityId(entityId);
	attrDefinition.setDescription(attrName);
	
	attrDefinition.setSequence(1);
	attrDefinition.setToolTip(attrName);
	attrDefinition.setEditable(true);
	attrDefinition.setManadatory(true);
	attrDefinition.setMasked(true);
	attrDefinition.setUnique(true);
	attrDefinition.setVisible(true);
	attrDefinition.setMetricApplicable(true);
	attrDefinition.setETLApplicable(true);
	
	attrDefinition.setAllowedValues("hello hi h");
	attrDefinition.setCVGApplicable(true);
	return attrDefinition;
	}

	public static SalesPosition getSalesPositionId(long spId) {
		SalesPosition sp = new SalesPosition();
		sp.setId(spId);
		return sp;
	}
}
