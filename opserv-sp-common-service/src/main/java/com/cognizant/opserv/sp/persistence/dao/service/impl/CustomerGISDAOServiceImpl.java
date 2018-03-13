package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.query.AdvanceSearchContext;
import com.cognizant.opserv.query.IExpressionCriteria;
import com.cognizant.opserv.query.IQuery;
import com.cognizant.opserv.query.InvalidQueryException;
import com.cognizant.opserv.query.Query;
import com.cognizant.opserv.query.SearchEntity;
import com.cognizant.opserv.sp.assembler.CustomerAlignmentModelAssembler;
import com.cognizant.opserv.sp.assembler.CustomerModelAssembler;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.core.dao.TCustAddrDAO;
import com.cognizant.opserv.sp.core.dao.TCustAlgmntDAO;
import com.cognizant.opserv.sp.core.dao.TCustDAO;
import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TCustAddr;
import com.cognizant.opserv.sp.core.entity.TCustAlgmnt;
import com.cognizant.opserv.sp.core.entity.TCustContact;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerGISDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.TemplateAlignmentDAOService;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.common.SearchCriteria;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.dao.JPAQueryGenerator;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
/**
 * ****************************************************************************.
 *
 * @class CustomerGISDAOServiceImpl contains implementations for CustomerGISDAOService
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Component
public class CustomerGISDAOServiceImpl implements CustomerGISDAOService{
	
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(CustomerGISDAOServiceImpl.class);
	
	/** The t cust dao. */
	@Autowired
	private TCustDAO tCustDAO;
	
	/** The t cust algmnt dao. */
	@Autowired
	private TCustAlgmntDAO tCustAlgmntDAO;
	
	/** The t cust addr dao. */
	@Autowired
	private TCustAddrDAO tCustAddrDAO;
	
	/** The cust dao service. */
	@Autowired
	private CustomerDAOService custDAOService;
	
	/** The template alignment dao service. */
	@Autowired
	private TemplateAlignmentDAOService templateAlignmentDAOService;
	
	@Autowired
	private CustomerAlignmentModelAssembler customerAlignmentModelAssembler;
	
	@Autowired
	private CustomerModelAssembler customerModelAssembler;
	
	/** The generic dao. */
	@Autowired
	private GenericDAO genericDAO;
	
	/** The jpa qg. */
	@Autowired
	private JPAQueryGenerator jpaQg;
	
	/**
	 * Gets the customers.
	 *
	 * @param criteria the criteria
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return the customers
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public List<CustomerAlignment> getCustomers(ISearchCriteria criteria,
			Alignment alignment, UserDetails userDetails)
					throws OpservDataAccessException {
		try{
			List<CustomerAlignment> customerAlgmntList = null;
			SearchCriteria searchCriteria = (SearchCriteria) criteria;
			String attribute = searchCriteria.getCriteriaObject().getAttribute();

			if(attribute.equals(CommonConstants.SALES_POS_ID) || attribute.equals(CommonConstants.SALES_HIER_ID)){
				List<TCustAlgmnt> algmntList = tCustAlgmntDAO.findCustAlgmntsBySerachCriteria(searchCriteria);
				if(algmntList != null && !algmntList.isEmpty()){
					//EntityTemplate entityTemplate = templateAlignmentDAOService.getEntityTemplate(EntityType.CUSTOMER, alignment, userDetails);

					customerAlgmntList = new ArrayList<CustomerAlignment>();
					for(TCustAlgmnt custAlgmnt : algmntList){
						CustomerAlignment customerAlgmnt = customerAlignmentModelAssembler.convertTCustAlToModelForGIS(custAlgmnt);
						//customerAlgmnt.getCustomer().setExtdAttributes(custDAOService.getExtAttrDetails(entityTemplate, alignment, userDetails, custAlgmnt.getTCust()));
						customerAlgmntList.add(customerAlgmnt);
					}
				}
			}
			else if(attribute.equals(CommonConstants.POSTAL_CD)){
				ISearchCriteria criteriaForPrimaryAddress = new SearchCriteria();
				criteriaForPrimaryAddress.addEqualTo("prAddrFlag", 'Y');
				searchCriteria.addAndCriteria(criteriaForPrimaryAddress);

				List<TCustAddr> custAddrList = tCustAddrDAO.findTCustAddrsBySearchCriteria(searchCriteria);

				if(custAddrList != null && !custAddrList.isEmpty()){
					customerAlgmntList = new ArrayList<CustomerAlignment>();

					//EntityTemplate entityTemplate = templateAlignmentDAOService.getEntityTemplate(EntityType.CUSTOMER, alignment, userDetails);

					for(TCustAddr custAddr : custAddrList){
						CustomerAlignment custAlgmnt = null;
						if(custAddr.getTCust().getTCustAlgmntss() != null && !custAddr.getTCust().getTCustAlgmntss().isEmpty()){
							custAlgmnt = customerAlignmentModelAssembler.convertTCustAlToModelForGIS(custAddr.getTCust().getTCustAlgmntss().iterator().next());
						}
						else{
							custAlgmnt = new CustomerAlignment();
							custAlgmnt.setCustomer(customerModelAssembler.convertTCustToModelWithContactDetails(custAddr.getTCust()));
						}
						//custAlgmnt.getCustomer().setExtdAttributes(custDAOService.getExtAttrDetails(entityTemplate, alignment, userDetails, custAddr.getTCust()));
						customerAlgmntList.add(custAlgmnt);
					}
				}
			}
			else {
				List<TCust> tCustList = tCustDAO.findTCustsBySearchCriteria(criteria);
				if(tCustList != null && !tCustList.isEmpty()){
					customerAlgmntList = new ArrayList<CustomerAlignment>();
					for(TCust tCust : tCustList){
						CustomerAlignment custAlgmnt = null;
						if(tCust.getTCustAlgmntss() != null && !tCust.getTCustAlgmntss().isEmpty()){
							custAlgmnt = customerAlignmentModelAssembler.convertTCustAlToModelForGIS(tCust.getTCustAlgmntss().iterator().next());
						}
						else{
							custAlgmnt = new CustomerAlignment();
							custAlgmnt.setCustomer(customerModelAssembler.convertTCustToModelWithContactDetails(tCust));
						}
						customerAlgmntList.add(custAlgmnt);
					}
				}
			}
			return customerAlgmntList;
		}
		catch(RuntimeException e){
			LOGGER.error("Error during fetching customers by search criteria",e);
			throw new OpservDataAccessException("Error during fetching customers by search criteria",e);
		}
	}

	@Override
	public List<CustomerAlignment> getCustomers(IQuery query,
			Alignment alignment, UserDetails userDetails)
			throws OpservDataAccessException {
		try{
			List<CustomerAlignment> customerAlgmntList = null;
			IExpressionCriteria expressionCriteria = (IExpressionCriteria)query.getCriteria();
			String attribute = expressionCriteria.getAttribute();
			boolean populateChildEntities = false;
			EntityManager em = genericDAO.getEntityManagerFromJPA();
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			
			if(attribute.equals(CommonConstants.SALES_POS_ID) || attribute.equals(CommonConstants.SALES_HIER_ID)){
				List<Long> spIdList = null;
				List<Long> shIdList = null;
				CriteriaQuery<TCustAlgmnt> criteriaQuery = criteriaBuilder.createQuery(TCustAlgmnt.class);
				Root<TCustAlgmnt> custAlignment = criteriaQuery.from(TCustAlgmnt.class);
				if(attribute.equals(CommonConstants.SALES_POS_ID)){
					spIdList = (List<Long>) expressionCriteria.getValue();
					if(null != expressionCriteria.getCriteriaList() && !expressionCriteria.getCriteriaList().isEmpty()){
						shIdList = (List<Long>) expressionCriteria.getCriteriaList().get(0).getValue();
					}
				}
				else if(attribute.equals(CommonConstants.SALES_HIER_ID)){
					shIdList = (List<Long>) expressionCriteria.getValue();
					if(null != expressionCriteria.getCriteriaList() && !expressionCriteria.getCriteriaList().isEmpty()){
						spIdList = (List<Long>) expressionCriteria.getCriteriaList().get(0).getValue();
					}
				}
				Predicate spIds = custAlignment.<Long>get("salesPosId").in(spIdList);
				Predicate shIds = custAlignment.<Long>get("salesHierId").in(shIdList);
				Predicate targetFlag = criteriaBuilder.equal(custAlignment.<String>get("targetFlag"),'Y');
		        Predicate activeFlag = criteriaBuilder.equal(custAlignment.<String>get("activeFlag"),'Y');
		        criteriaQuery.where(criteriaBuilder.and(spIds,shIds,targetFlag,activeFlag));
		        criteriaQuery.select(custAlignment);
				TypedQuery<TCustAlgmnt> typedQuery = em.createQuery(criteriaQuery);
				List<TCustAlgmnt> algmntList = typedQuery.getResultList();

				if(algmntList != null && !algmntList.isEmpty()){
					customerAlgmntList = new ArrayList<CustomerAlignment>();
					for(TCustAlgmnt custAlgmnt : algmntList){
						CustomerAlignment customerAlgmnt = customerAlignmentModelAssembler.convertTCustAlToModel(custAlgmnt, populateChildEntities);
						customerAlgmntList.add(customerAlgmnt);
					}
				}
			}
			else if(attribute.equals(CommonConstants.POSTAL_CD)){
				CriteriaQuery<TCustAddr> criteriaQuery = criteriaBuilder.createQuery(TCustAddr.class);
				Root<TCustAddr> custAddr = criteriaQuery.from(TCustAddr.class);
				
				String postalCd = (String) expressionCriteria.getValue();
				Predicate zipCd = criteriaBuilder.equal(custAddr.<String>get(attribute),postalCd);
		        Predicate prAddrFlag = criteriaBuilder.equal(custAddr.<String>get("prAddrFlag"),'Y');
		        criteriaQuery.where(criteriaBuilder.and(zipCd,prAddrFlag));
		        criteriaQuery.select(custAddr);
				TypedQuery<TCustAddr> typedQuery = em.createQuery(criteriaQuery);
				List<TCustAddr> custAddrList = typedQuery.getResultList();
				
				if(custAddrList != null && !custAddrList.isEmpty()){
					customerAlgmntList = new ArrayList<CustomerAlignment>();

					//EntityTemplate entityTemplate = templateAlignmentDAOService.getEntityTemplate(EntityType.CUSTOMER, alignment, userDetails);

					for(TCustAddr tCustAddr : custAddrList){
						CustomerAlignment custAlgmnt = null;
						if(tCustAddr.getTCust().getTCustAlgmntss() != null && !tCustAddr.getTCust().getTCustAlgmntss().isEmpty()){
							custAlgmnt = customerAlignmentModelAssembler.convertTCustAlToModelForGIS(tCustAddr.getTCust().getTCustAlgmntss().iterator().next());
						}
						else{
							custAlgmnt = new CustomerAlignment();
							custAlgmnt.setCustomer(customerModelAssembler.convertTCustToModelWithContactDetails(tCustAddr.getTCust()));
						}
						//custAlgmnt.getCustomer().setExtdAttributes(custDAOService.getExtAttrDetails(entityTemplate, alignment, userDetails, custAddr.getTCust()));
						customerAlgmntList.add(custAlgmnt);
					}
				}
			}
			else {
				CriteriaQuery<TCust> criteriaQuery = criteriaBuilder.createQuery(TCust.class);
				Root<TCust> cust = criteriaQuery.from(TCust.class);
				
				Predicate predicate = null;
				String value = (String) expressionCriteria.getValue();
				if(attribute.equals(CommonConstants.CUST_NAME)){
					predicate = criteriaBuilder.like(cust.<String>get("custName"),value);
				}
				else if(attribute.equals(CommonConstants.CUST_CD)){
					predicate = criteriaBuilder.like(cust.<String>get("custCd"),value);
				}
		        criteriaQuery.where(criteriaBuilder.and(predicate));
		        criteriaQuery.select(cust);
				TypedQuery<TCust> typedQuery = em.createQuery(criteriaQuery);
		        
//		        AdvanceSearchContext advSrch = new AdvanceSearchContext("AdvanceSearchCustomer");
//				SearchEntity tCustEntity = new SearchEntity("customer",TCust.class);
//				advSrch.addSearchEntity(tCustEntity);
//		        IQuery q = new Query().from(advSrch).criteria((IExpressionCriteria)query.getCriteria());
//				TypedQuery<TCust> typedQuery = (TypedQuery)jpaQg.getQuery(q);
				
				List<TCust> tCustList = typedQuery.getResultList();
				if(tCustList != null && !tCustList.isEmpty()){
					customerAlgmntList = new ArrayList<CustomerAlignment>();
					for(TCust tCust : tCustList){
						CustomerAlignment custAlgmnt = null;
						if(tCust.getTCustAlgmntss() != null && !tCust.getTCustAlgmntss().isEmpty()){
							custAlgmnt = customerAlignmentModelAssembler.convertTCustAlToModelForGIS(tCust.getTCustAlgmntss().iterator().next());
						}
						else{
							custAlgmnt = new CustomerAlignment();
							custAlgmnt.setCustomer(customerModelAssembler.convertTCustToModelWithContactDetails(tCust));
						}
						customerAlgmntList.add(custAlgmnt);
					}
				}
			}
			return customerAlgmntList;
		}
		catch(RuntimeException e){
			e.printStackTrace();
			LOGGER.error("Error during fetching customers by search criteria",e);
			throw new OpservDataAccessException("Error during fetching customers by search criteria",e);
		} 
//		catch (InvalidQueryException e) {
//			e.printStackTrace();
//			LOGGER.error("Error during fetching customers by search criteria",e);
//			throw new OpservDataAccessException("Error during fetching customers by search criteria",e);
//		}		
	}

}
