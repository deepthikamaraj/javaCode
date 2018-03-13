package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import org.hibernate.validator.internal.util.ReflectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.query.AdvanceSearchContext;
import com.cognizant.opserv.query.ExpressionCriteria;
import com.cognizant.opserv.query.IExpressionCriteria;
import com.cognizant.opserv.query.IQuery;
import com.cognizant.opserv.query.InvalidQueryException;
import com.cognizant.opserv.query.Query;
import com.cognizant.opserv.query.SearchEntity;
import com.cognizant.opserv.sp.assembler.CustomerEntityAssembler;
import com.cognizant.opserv.sp.assembler.CustomerModelAssembler;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.EntityType;
import com.cognizant.opserv.sp.core.dao.TCustDAO;
import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TCustAddr;
import com.cognizant.opserv.sp.core.entity.TCustContact;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.EntityTemplate;
import com.cognizant.opserv.sp.model.attrb.AttributeDefinition;
import com.cognizant.opserv.sp.model.attrb.ExtdAttribue;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.TemplateAlignmentDAOService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.dao.JPAQueryGenerator;

/**
 * ****************************************************************************.
 *
 * @class CustomerDAOServiceImpl contains all the  DAO customer services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Component
public class CustomerDAOServiceImpl implements CustomerDAOService {

	/** The t cust dao. */
	@Autowired
	private TCustDAO tCustDAO;

	/** The template alignment dao service. */
	@Autowired
	private TemplateAlignmentDAOService templateAlignmentDAOService;
	
	/** The jpa qg. */
	@Autowired
	private JPAQueryGenerator jpaQg;
	
	@Autowired
	private CustomerEntityAssembler customerEntityAssembler;
	
	@Autowired
	private CustomerModelAssembler customerModelAssembler;
	
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(CustomerDAOServiceImpl.class);
	
	
	/**
	 * Gets the customer details.
	 *
	 * @param customerId the customer id
	 * @param userDetails the user details
	 * @return the customer details
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public Customer getCustomerDetails(Long customerId, UserDetails userDetails)
			throws OpservDataAccessException {
		try{
			return customerModelAssembler.convertTCustToModelWithChildEntities(tCustDAO.findTCustById(customerId.intValue()));
		}
		catch(RuntimeException e){
			LOGGER.error("Error during fetching customer details by custid",e);
			throw new OpservDataAccessException("Error during fetching customer details by custid",e);
		}
	}
	
	/**
	 * Search customers by criteria.
	 *
	 * @param criteria the criteria
	 * @param userDetails the user details
	 * @return the list
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public List<Customer> searchCustomers(final IQuery query,UserDetails userDetails)
			throws OpservDataAccessException {
		
		List<Customer> customerList = null;
		
		AdvanceSearchContext advSrch = new AdvanceSearchContext("AdvanceSearchCustomer");
		SearchEntity tCust = new SearchEntity("customer",TCust.class);
		SearchEntity tCustAddress = new SearchEntity("customerAddress",TCustAddr.class);
		SearchEntity tCustContact = new SearchEntity("customerContact",TCustContact.class);
		advSrch.addSearchEntity(tCust);
		advSrch.addSearchEntity(tCustAddress);
		advSrch.addSearchEntity(tCustContact);
		
		tCustAddress.joinWith(tCust);
		tCustAddress.joinBy("tCustAddrss");
		tCustAddress.fetchWithParent(true);
		
		tCustContact.joinWith(tCust);
		tCustContact.joinBy("tCustContactss");
		tCustContact.fetchWithParent(true);
		

		IExpressionCriteria controlCrit = ExpressionCriteria.createEqualToCriteria("customer.tenantId", userDetails.getTenantId());
		IExpressionCriteria custActiveFlag = ExpressionCriteria.createEqualToCriteria("customer.activeFlag",'Y');
		IExpressionCriteria pPrimaryAddress = ExpressionCriteria.createEqualToCriteria("customerAddress.prAddrFlag",'Y');
		IExpressionCriteria pPrimaryContact = ExpressionCriteria.createEqualToCriteria("customerContact.prContactFlag",'Y');
		
		controlCrit.and(custActiveFlag,pPrimaryAddress,pPrimaryContact);
		controlCrit.and((IExpressionCriteria)query.getCriteria());
		
		try {
			IQuery q = new Query().from(advSrch).criteria(controlCrit).orderBy(query.getOrderBy());
			TypedQuery<TCust> typedQuery = (TypedQuery)jpaQg.getQuery(q);
			List<TCust> tCusts = typedQuery.getResultList();
			if(null != tCusts && !tCusts.isEmpty()){
				customerList = new ArrayList<Customer>();
				for(TCust cust : tCusts){
					customerList.add(customerModelAssembler.convertTCustToModelWithChildEntities(cust));
				}
			}
			return customerList;
		} catch (InvalidQueryException e) {
			LOGGER.error("Error during fetching customers by search criteria",e);
			throw new OpservDataAccessException("Error during fetching customers by search criteria",e);
		}		
	}

	
	/**
     * getExtAttrDetails - To get Extended Attribute for Customer
     * @param entityType
     * @param alignment
     * @param userDetails
     * @param custId
     * @return  Map<Integer, List<ExtdAttribue>>
     */
	
	public List<ExtdAttribue> getExtAttrDetails(EntityTemplate entityTemplate, Alignment alignment,UserDetails userDetails,TCust tCust)throws OpservDataAccessException{
		List<ExtdAttribue> extdAttribuesList = new ArrayList<ExtdAttribue>();
		try{
			//EntityTemplate entityTemplate = templateAlignmentDAOService.getEntityTemplate(entityType, alignment, userDetails);

			for (int i =0;i < entityTemplate.getGroups().size() ;i++ ){
				List<AttributeDefinition> attributeDefinitionsList = entityTemplate.getGroups().get(i).getAttrDefinitions();
				for(AttributeDefinition attributeDefinition:attributeDefinitionsList){
					if(!attributeDefinition.getName().startsWith("attr_")) {
						continue;
					}

					String attrNum = attributeDefinition.getName().substring(attributeDefinition.getName().indexOf("_")+1);

					try {
						if(attrNum == null || Integer.parseInt(attrNum) <1 || Integer.parseInt(attrNum) >50) {
							continue;	
						}
					} catch(NumberFormatException pe) {
						continue;
					}
					ExtdAttribue extdAttribue = new ExtdAttribue();
					Method m = ReflectionHelper.getMethod(TCust.class,"getAttr"+attrNum);
					String attrValue = (String)ReflectionHelper.getValue(m, tCust);
					if(attrValue != null && !attrValue.toString().trim().equals(CommonConstants.EMPTY)){
						extdAttribue.setDefinition(attributeDefinition);
						extdAttribue.setValue(attrValue);
						extdAttribuesList.add(extdAttribue);
					}
				}

			}
		}catch (RuntimeException e) {
			LOGGER.error("Error while fetching customer extended attribute based on Customer Id",e);
			throw new OpservDataAccessException("Error while fetching customer extended attribute based on Customer Id",e);
		}
		return extdAttribuesList;
	}
	
	/**
	 * Gets the customer count by criteria.
	 *
	 * @param criteria the criteria
	 * @param userDetails the user details
	 * @return the customer count by criteria
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public Long getCustomerCount(final IQuery query,UserDetails userDetails) throws OpservDataAccessException {
		
		AdvanceSearchContext advSrch = new AdvanceSearchContext("AdvanceSearchCustomer");
		SearchEntity tCust = new SearchEntity("customer",TCust.class);
		SearchEntity tCustAddress = new SearchEntity("customerAddress",TCustAddr.class);
		SearchEntity tCustContact = new SearchEntity("customerContact",TCustContact.class);
		advSrch.addSearchEntity(tCust);
		advSrch.addSearchEntity(tCustAddress);
		advSrch.addSearchEntity(tCustContact);
		
		tCustAddress.joinWith(tCust);
		tCustAddress.joinBy("tCustAddrss");
		
		tCustContact.joinWith(tCust);
		tCustContact.joinBy("tCustContactss");
		

		IExpressionCriteria controlCrit = ExpressionCriteria.createEqualToCriteria("customer.tenantId", userDetails.getTenantId());
		IExpressionCriteria custActiveFlag = ExpressionCriteria.createEqualToCriteria("customer.activeFlag",'Y');
		IExpressionCriteria pPrimaryAddress = ExpressionCriteria.createEqualToCriteria("customerAddress.prAddrFlag",'Y');
		IExpressionCriteria pPrimaryContact = ExpressionCriteria.createEqualToCriteria("customerContact.prContactFlag",'Y');
		
		controlCrit.and(custActiveFlag,pPrimaryAddress,pPrimaryContact);
		controlCrit.and((IExpressionCriteria)query.getCriteria());
		
		try {
			IQuery q = new Query().from(advSrch).criteria(controlCrit).isCountQuery(true);
			TypedQuery<Long> typedQuery;
			typedQuery = (TypedQuery)jpaQg.getQuery(q);
			return (Long)typedQuery.getSingleResult();
		} catch (InvalidQueryException e) {
			LOGGER.error("Error during fetching customers by search criteria",e);
			throw new OpservDataAccessException("Error during fetching customers by search criteria",e);
		}		
	}
	
	/**
	 * TO get ExtAttr Details
	 * 
	 * @param entityType
	 * @param alignment
	 * @param userDetails
	 * @param custId
	 * @return Map<Integer,List<ExtdAttribue>>
	 */
	public Map<Integer, List<ExtdAttribue>> getExtAttrDetails(
			EntityType entityType, Alignment alignment,
			UserDetails userDetails, List<Integer> custId)
			throws OpservDataAccessException {

		Map<Integer, List<ExtdAttribue>> CustExtAttrList = new HashMap<Integer, List<ExtdAttribue>>();

		try {

			EntityTemplate entityTemplate = templateAlignmentDAOService
					.getEntityTemplate(entityType, alignment, userDetails);
			if(null != entityTemplate){
				List<TCust> tcustList = tCustDAO.findAllTCustsByIDS(custId);
				for (TCust cust : tcustList) {
					List<ExtdAttribue> extdAttribuesList = new ArrayList<ExtdAttribue>();

					for (int i = 0; i < entityTemplate.getGroups().size(); i++) {
						List<AttributeDefinition> attributeDefinitionsList = entityTemplate
								.getGroups().get(i).getAttrDefinitions();
						for (AttributeDefinition attributeDefinition : attributeDefinitionsList) {
							if (!attributeDefinition.getName().startsWith("attr_")) {
								continue;
							}

							String attrNum = attributeDefinition.getName()
									.substring(
											attributeDefinition.getName().indexOf(
													"_") + 1);

							try {
								if (attrNum == null
										|| Integer.parseInt(attrNum) < 1
										|| Integer.parseInt(attrNum) > 50) {
									continue;
								}
							} catch (NumberFormatException pe) {
								continue;
							}
							ExtdAttribue extdAttribue = new ExtdAttribue();
							Method m = ReflectionHelper.getMethod(TCust.class,
									"getAttr" + attrNum);
							String attrValue = (String) ReflectionHelper.getValue(
									m, cust);
							if (attrValue != null
									&& !attrValue.toString().trim()
											.equals(CommonConstants.EMPTY)) {
								extdAttribue.setDefinition(attributeDefinition);
								extdAttribue.setValue(attrValue);
								extdAttribuesList.add(extdAttribue);
							}
						}

					}
					CustExtAttrList.put(cust.getCustId(), extdAttribuesList);
				}
			}
		} catch (RuntimeException e) {
			LOGGER.error("Error during fetching of Extended Attribute For Customer by customer Id",e);
			throw new OpservDataAccessException("Error during fetching of Extended Attribute For Customer by customer Id", e);
		}
		return CustExtAttrList;
	}

	@Override
	public void editCustomerDetails(Customer customer, UserDetails userDetails)throws OpservDataAccessException {
		
		List<ExtdAttribue> extdAttributes = customer.getExtdAttributes(); 
		try{
		int no_of_columns = 50;
		Map<String, String> attNameMap = new HashMap<String, String>(); 
		List<String> listColOfTCust = new ArrayList<String>();
		for (int i = 1; i <= no_of_columns; i++) {
			listColOfTCust.add("attr_"+i);
		} 
		// get extended attribute (column)names only
		if (listColOfTCust != null && !listColOfTCust.isEmpty()) {
			for (int j = 0; j< extdAttributes.size(); j++) {
				if (listColOfTCust.contains(extdAttributes.get(j).getDefinition().getName())) {
					attNameMap.put(extdAttributes.get(j).getDefinition().getName(), extdAttributes.get(j).getValue());
				}
			}
		}
		TCust tCust = tCustDAO.findTCustById(customer.getId().intValue());
		
		tCust = customerEntityAssembler.mapCustomerModelToEntity(tCust,customer,attNameMap, userDetails);
		tCustDAO.updateTCust(tCust);
	    }catch (RuntimeException e) {
	    	LOGGER.error("Error during editing of Extended Attribute For Customer",e);
			throw new OpservDataAccessException("Error during editing of Extended Attribute For Customer",e);
		}
	}

	@Override
	public List<String> fetchCustNameFrCustIds(List<Integer> custIdList,
			UserDetails userDetails)throws OpservDataAccessException {
		try{
			List<String> custNameList = tCustDAO.fetchCustNameFrCustIds(custIdList,userDetails.getTenantId());
			return custNameList;	
		}catch (RuntimeException e) {
	    	LOGGER.error("Error during fetching customer name",e);
			throw new OpservDataAccessException("Error during fetching customer name",e);
		}
		
}
	/**
	 * TO get ExtAttr Details
	 * 
	 * @param entityType
	 * @param alignment
	 * @param userDetails
	 * @param custId
	 * @return Map<Integer,List<ExtdAttribue>>
	 */
	@Override
	public Map<Integer, List<ExtdAttribue>> getCustomerExtAttrDetailsWithoutTempl(List<Integer> custId,UserDetails userDetails)
			throws OpservDataAccessException {
		
		Map<Integer, List<ExtdAttribue>> CustExtAttrList = new HashMap<Integer, List<ExtdAttribue>>();

		try {
			LOGGER.info("*****************TO FETCH CUSTOMER EXTENDED ATTRIBUTES STARTED WITHOUT TEMPLATE STARTED FOR CUSTOMER ID== *****************" + custId);
			List<TCust> tcustList = tCustDAO.findAllTCustsByIDS(custId);
			
			TCust tCust = null;
			if(tcustList != null && !tcustList.isEmpty()){
				tCust = tcustList.get(0);
			}
			if(tCust != null){
			int no_of_columns = CommonConstants.custExtAttrCount;
			
			List<String> columnList = new ArrayList<String>();
			
			for (int i = 1; i <= no_of_columns; i++) {
				columnList.add("attr_" + i);
			}
			
			
				List<ExtdAttribue> extdAttribuesList = new ArrayList<ExtdAttribue>();
					for (String coulmnName : columnList) {
						if (!coulmnName.startsWith("attr_")) {
							continue;
						}

						String attrNum = coulmnName.substring(5);
					
						ExtdAttribue extdAttribue = new ExtdAttribue();
						Method m = ReflectionHelper.getMethod(TCust.class,
								"getAttr" + attrNum);
						String attrValue = (String) ReflectionHelper.getValue(
								m, tCust);
						
						LOGGER.info("*****************ATTR NAME======*****************" + coulmnName);
						
						LOGGER.info("*****************ATTR VALUE======*****************" + attrValue);
						if (attrValue != null
								&& !attrValue.toString().trim()
										.equals(CommonConstants.EMPTY)) {
							extdAttribue.setName(coulmnName);
							extdAttribue.setValue(attrValue);
							extdAttribuesList.add(extdAttribue);
						}else{
							extdAttribue.setName(coulmnName);
							extdAttribue.setValue(CommonConstants.NUL);
							extdAttribuesList.add(extdAttribue);
						}
					}

				CustExtAttrList.put(tCust.getCustId(), extdAttribuesList);
			}
				LOGGER.info("*****************TO FETCH CUSTOMER EXTENDED ATTRIBUTES WITHOUT TEMPLATE ENDED FOR CUSTOMER ID== *****************" + custId);
		} catch (RuntimeException e) {
			LOGGER.error("Error during fetching of Extended Attribute For Customer by customer Id",e);
			throw new OpservDataAccessException("Error during fetching of Extended Attribute For Customer by customer Id", e);
		}
		LOGGER.info("*****************TO FETCH CUSTOMER EXTENDED ATTRIBUTES ENDED*****************");
		return CustExtAttrList;

	}

	@Override
	public Customer fetchCustDetails(Integer custId) throws OpservDataAccessException{
		try{
			TCust tCust = tCustDAO.findTCustById(custId);
			if(tCust != null){
				Customer customerModel = customerModelAssembler.convertTCustToModelWithBasicDetails(tCust);
				return customerModel;
			}
			return null;
		}catch (RuntimeException e) {
			LOGGER.error("Error during fetching Customer Details",e);
			throw new OpservDataAccessException("Error during fetching Customer Details", e);
		}
		
	}
	
	
}
