package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.validator.internal.util.ReflectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.assembler.ChangeRequestAssembler;
import com.cognizant.opserv.sp.assembler.CustomerAlignmentEntityAssembler;
import com.cognizant.opserv.sp.assembler.CustomerAlignmentModelAssembler;
import com.cognizant.opserv.sp.assembler.CustomerEntityAssembler;
import com.cognizant.opserv.sp.assembler.CustomerProductEntityAssembler;
import com.cognizant.opserv.sp.common.ChangeRequestOfflineStatusType;
import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.common.ChangeRequestTriggerType;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.EntityType;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.common.util.JSONUtil;
import com.cognizant.opserv.sp.core.dao.TChngReqDAO;
import com.cognizant.opserv.sp.core.dao.TChngreqOfflineDAO;
import com.cognizant.opserv.sp.core.dao.TCustAddrDAO;
import com.cognizant.opserv.sp.core.dao.TCustAlgmntDAO;
import com.cognizant.opserv.sp.core.dao.TCustDAO;
import com.cognizant.opserv.sp.core.dao.TCustPrdAlgmntDAO;
import com.cognizant.opserv.sp.core.dao.TCustPrdSalesTeamDAO;
import com.cognizant.opserv.sp.core.dao.TCustSalesTeamDAO;
import com.cognizant.opserv.sp.core.dao.TPrdAlgmntDAO;
import com.cognizant.opserv.sp.core.dao.TSalesPosDAO;
import com.cognizant.opserv.sp.core.entity.TChngReq;
import com.cognizant.opserv.sp.core.entity.TChngreqOffline;
import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TCustAlgmnt;
import com.cognizant.opserv.sp.core.entity.TCustPrdSalesTeam;
import com.cognizant.opserv.sp.core.entity.TCustSalesTeam;
import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.messaging.GenericPublisher;
import com.cognizant.opserv.sp.model.Address;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessReason;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerBlob;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.EntityTemplate;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.attrb.AttributeDefinition;
import com.cognizant.opserv.sp.model.attrb.ExtdAttribue;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerAlignmentDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerProductAlignmentDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.TemplateAlignmentDAOService;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;


/**
 * ****************************************************************************.
 * 
 * @class CustomerAlignmentDAOServiceImpl contains all the dao services for
 *        customer affiliation
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 *        ************************************************************
 *        ***************
 */

@Component
public class CustomerAlignmentDAOServiceImpl implements
		CustomerAlignmentDAOService {

	/** The t cust algmnt dao. */
	@Autowired
	private TCustAlgmntDAO tCustAlgmntDAO;

	@Autowired
	private TCustAddrDAO tCustAddrDAO;

	@Autowired
	private TCustDAO tCustDAO;

	@Autowired
	private GeographyAlignmentDAOServiceImpl geographyAlignmentDAOService;

	@Autowired
	private TemplateAlignmentDAOService templateAlignmentDAOService;

	@Autowired
	private TSalesPosDAO tSalesPosDAO;

	@Autowired
	private CustomerProductAlignmentDAOService customerProductAlignmentDAOService;

	@Autowired
	private TCustPrdAlgmntDAO tCustPrdAlgmntDAO;

	@Autowired
	private TPrdAlgmntDAO tPrdAlgmntDAO;
	
	@Autowired
	private GenericPublisher genericPublisher;
	
	@Autowired
	private TChngReqDAO changeRequestDao;

	private static final Integer PENDING_SUBMISSION = 1;

	private static final Integer PENDING_APPROVAL = 2;

	@Autowired
	private TCustSalesTeamDAO tCustSalesTeamDAO;

	@Autowired
	private TCustPrdSalesTeamDAO tCustPrdSalesTeamDAO;

	@Autowired
	private CustomerDAOService customerDAOService;
	
	@Autowired
	private TChngreqOfflineDAO tChngreqOfflineDAO;
	
	@Autowired
	private TChngReqDAO tChngReqDAO;
	
	@Autowired
	private TCustPrdSalesTeamDAO tCustPrdSalesTeamDao;
	
	@Autowired
	private CustomerAlignmentModelAssembler customerAlignmentModelAssembler;
	
	@Autowired
	private CustomerAlignmentEntityAssembler customerAlignmentEntityAssembler;
	
	@Autowired
	private CustomerEntityAssembler customerEntityAssembler;
	
	@Autowired
	private CustomerProductEntityAssembler customerProductEntityAssembler;
	
	@Autowired
	private ChangeRequestAssembler changeRequestAssembler;
	
	/** The appliedTemplate */
	/*@Value("${opserv.sp.extAttr.appliedTemplate}")
	private Character AppliedTemplate;*/
	@Value("#{systemProperties['opserv.sp.extAttr.applyTemplate'] ?: 'N'}")
	private Character ApplyTemplate;
	
	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(CustomerAlignmentDAOServiceImpl.class);
	
	/*
	 * @Autowired private SPAssignmentsService sPAssignmentsService;
	 */
	/**
	 * @method getAllCustomerAlignmentsBySalesPosition - This method will fetch
	 *         Customers of a SP with details
	 * 
	 * @param salesPosition
	 *            - holds selected Sales Position like salesHierId, salesPosId,
	 *            etc.
	 * @param userDetails
	 *            - holds Logged in user's details like userId, staffId,
	 *            tenantId, etc.
	 * @return List<CustomerAlignment> - List of Customers with details to
	 *         display
	 * 
	 * @throws OpservDataAccessException
	 *             - If there are any Exceptions
	 */
	@Override
	public List<CustomerAlignment> getAllCustomerAlignmentsBySalesPosition(
			SalesPosition salesPosition, UserDetails userDetails)
			throws OpservDataAccessException {

		try {
			Map<Integer, List<ExtdAttribue>> custAlgnmtExtdAttribues = null;
			Map<Integer, List<ExtdAttribue>> custExtdAttribues = null;
			LOGGER.info("*****************GET ALL CUSTOMER ALIGNMENTS BY SALESPOSITION STARTED*****************");
			TCustAlgmnt tCustAlgmnt = new TCustAlgmnt();
			SearchFilter<TCustAlgmnt> searchFilter = new SearchFilter<TCustAlgmnt>();
			OperatorInfo operatorInfo = new OperatorInfo();
			operatorInfo.setLogicalOperator(LogicalOperatorEnum.AND);
			PaginationInfo paginationInfo = new PaginationInfo(0, -1);

			searchFilter.setEntityClass(tCustAlgmnt);
			searchFilter.setOperatorInfo(operatorInfo);
			searchFilter.setPaginationInfo(paginationInfo);

			tCustAlgmnt.setSalesHierId(salesPosition.getHierarchy().getId());
			tCustAlgmnt.setSalesPosId(salesPosition.getId());
			tCustAlgmnt.setTenantId(userDetails.getTenantId());
			tCustAlgmnt.setEffEndDt(DateUtil.getCurrentDate());

			List<Integer> crStatusList = new ArrayList<Integer>();
			crStatusList.add(PENDING_SUBMISSION);
			crStatusList.add(PENDING_APPROVAL);

			List<Object[]> objList = null;

			// To get the list of active customer assignments for the given SP
			LOGGER.info("*****************DB CALL TO GET THE LIST OF ACTIVE CUSTOMER ASSIGNMENTS FOR THE GIVEN SP STARTED*****************");
			objList = tCustAlgmntDAO.fetchAssignedActPropCustWithNoAddr(
					searchFilter, userDetails.getLocaleId(),
					userDetails.getLocaleId(), crStatusList);
			LOGGER.info("*****************DB CALL TO GET THE LIST OF ACTIVE CUSTOMER ASSIGNMENTS FOR THE GIVEN SP ENDED*****************");

			// To get the list of inactive customer assignments, but with CR
			// being raised for the given SP
			LOGGER.info("*****************DB CALL TO FETCH INACTIVE CUSTOMER WITH PENDING CR STARTED*****************");
			List<Object[]> inActiveList = tCustAlgmntDAO
					.fetchInactiveCustomerWithPendingCR(searchFilter,
							userDetails.getLocaleId(),
							userDetails.getLocaleId(), crStatusList, 0, -1);
			LOGGER.info("*****************DB CALL TO FETCH INACTIVE CUSTOMER WITH PENDING CR ENDED*****************");

			List<Integer> custIdsList = null;

			if (objList != null && !objList.isEmpty()) {
				custIdsList = new ArrayList<Integer>();
				for (Object[] obj : objList) {
					custIdsList.add((Integer) obj[1]);
				}
			}

			
			if (ApplyTemplate != null
					&& ApplyTemplate.equals(CommonConstants.CHAR_YES)) {
				LOGGER.info("*****************TO FETCH CUSTOMER ALIGNMENTS BY SALESPOSITION STARTED WITH appliedTemplate as 'Y'*****************");
			EntityTemplate entityTemplate = templateAlignmentDAOService.getEntityTemplate(EntityType.SALES_TEAM_CUSTOMER, salesPosition.getAlignmment(), userDetails);
			custAlgnmtExtdAttribues  = getCustomerAlignmentExtAttrDetails(
					entityTemplate, salesPosition.getAlignmment(),
					userDetails, custIdsList);
			// Customer Extended Attribute
						LOGGER.info("*****************TO FETCH CUSTOMER EXTENDED ATTRIBUTES STARTED*****************");
						custExtdAttribues = customerDAOService.getExtAttrDetails(
								EntityType.CUSTOMER, salesPosition.getAlignmment(),
								userDetails, custIdsList);
						LOGGER.info("*****************TO FETCH CUSTOMER EXTENDED ATTRIBUTES ENDED*****************");
			} else {
					LOGGER.info("*****************TO FETCH CUSTOMER ALIGNMENTS BY SALESPOSITION STARTED WITH appliedTemplate as 'N'*****************");
					custAlgnmtExtdAttribues = getCustomerAlignmentExtAttrDetailsWithoutTempl(
							salesPosition.getAlignmment(), userDetails,
							custIdsList);
					custExtdAttribues = customerDAOService.getCustomerExtAttrDetailsWithoutTempl(custIdsList,userDetails);
				}
			

			// Total customers(Active and Inactive)
			objList.addAll(inActiveList);
			SalesPosition salesPos = geographyAlignmentDAOService
					.getSalesPositionById(salesPosition.getId(),
							userDetails.getTenantId());
			List<CustomerAlignment> custAlModelList = customerAlignmentModelAssembler
					.convertEntityObjToModelfrCust(objList, salesPos,
							custExtdAttribues,custAlgnmtExtdAttribues);

			// To get the primary address of the customer
			List<Object[]> custAdrList = tCustAddrDAO
					.findAllCustomersPrimaryAddrList(custIdsList,
							userDetails.getTenantId());
			Map<Integer, Address> custAddrMap = customerAlignmentModelAssembler
					.convertEntityObjToModelfrCustAddr(custAdrList);

			if (custAlModelList != null && !custAlModelList.isEmpty()) {
				for (CustomerAlignment ca : custAlModelList) {
					if (custAddrMap != null && !custAddrMap.isEmpty()) {

						for (Entry<Integer, Address> custIdAddrmap : custAddrMap
								.entrySet()) {

							Long cId = ca.getCustomer().getId();
							Integer mapKey = custIdAddrmap.getKey();
							if (cId != null && cId.equals(Long.valueOf(mapKey))) {
								Customer customer = ca.getCustomer();
								customer.setPrimaryAddress(custIdAddrmap
										.getValue());
								ca.setCustomer(customer);
								break;
							}
						}
					}

				}
			}
			LOGGER.info("*****************GET ALL CUSTOMER ALIGNMENTS BY SALESPOSITION ENDED*****************");
			return custAlModelList;

		} catch (RuntimeException dae) {
			LOGGER.error("Error while fetching customer details for a sales position");
			throw new OpservDataAccessException(
					"Exception while fetching customer details for a sales position",
					dae);
		}

	}

	/**
	 * 
	 * @method getAllCustomerAlignments
	 * @param customerId
	 * @param userDetails
	 * @return
	 * @throws OpservDataAccessException
	 * 
	 */
	@Override
	public List<CustomerAlignment> getCustomerAlignment(long customerId,
			UserDetails userDetails) throws OpservDataAccessException {

		try {
			if (ApplyTemplate != null
					&& ApplyTemplate.equals(CommonConstants.CHAR_YES)) {
				LOGGER.info("*****************TO FETCH CUSTOMER ALIGNMENTS BY CUSTID STARTED WITH appliedTemplate as 'Y'*****************");
			LOGGER.info("*****************TO FETCH CUSTOMER ALIGNMENTS BY CUSTID STARTED*****************");
			
			Map<Integer, List<ExtdAttribue>> custAlgnmtExtdAttributes= new HashMap<Integer, List<ExtdAttribue>>();
			Map<Integer, List<ExtdAttribue>> custExtdAttributes= new HashMap<Integer, List<ExtdAttribue>>();
			List<TCustAlgmnt> tCustAlignmentsList = tCustAlgmntDAO
					.getAllCustAlgmntsFromCId((int) customerId, userDetails.getTenantId());
			List<Integer> cId = new ArrayList<Integer>();
			cId.add((int) customerId);
			Map<String,Alignment> uniqueAlignments = new HashMap<String,Alignment>();
			Map<String,EntityTemplate> uniqueEntityTemplates = new HashMap<String,EntityTemplate>();
			Alignment alignment = null;
			EntityTemplate entityTemplate = null;
			String key = null;
			for(TCustAlgmnt tCustAlgmnt : tCustAlignmentsList ){
				
				 alignment = null;
				 key = tCustAlgmnt.getAlgmntId().toString()+tCustAlgmnt.getBussUnitId().toString()+tCustAlgmnt.getSalesTeamId().toString();
				 if(uniqueAlignments.containsKey(key)) {
					 alignment = uniqueAlignments.get(key);
					 entityTemplate = uniqueEntityTemplates.get(key);
				 } else {
					 alignment = new Alignment();
		             BusinessUnit businessUnit = new BusinessUnit();
		             SalesTeam salesTeam = new SalesTeam();
		             businessUnit.setId(tCustAlgmnt.getBussUnitId());
		             salesTeam.setId(tCustAlgmnt.getSalesTeamId());
		             salesTeam.setBusinessUnit(businessUnit);
		             alignment.setId(tCustAlgmnt.getAlgmntId());
		             alignment.setSalesTeam(salesTeam);
		             uniqueAlignments.put(key, alignment);
		             entityTemplate = templateAlignmentDAOService.getEntityTemplate(EntityType.SALES_TEAM_CUSTOMER, alignment, userDetails);
		             uniqueEntityTemplates.put(key, entityTemplate);
				 }
	             
	            custAlgnmtExtdAttributes = getCustomerAlignmentExtAttrDetails(entityTemplate,alignment,userDetails, cId);
	            custExtdAttributes = customerDAOService.getExtAttrDetails(EntityType.CUSTOMER, alignment,userDetails, cId);
			}
			if (null != tCustAlignmentsList && !tCustAlignmentsList.isEmpty()) {
				List<CustomerAlignment> custAlgnmtList =  customerAlignmentModelAssembler
						.convertTCustAligmentsToModel(tCustAlignmentsList,custAlgnmtExtdAttributes,custExtdAttributes);
				LOGGER.info("*****************TO FETCH CUSTOMER ALIGNMENTS BY CUSTID ENDED*****************");
				return custAlgnmtList;
			}
		} else {
				LOGGER.info("*****************TO FETCH CUSTOMER ALIGNMENTS BY CUSTID STARTED WITH appliedTemplate as 'N'*****************");
				
				Map<Integer, List<ExtdAttribue>> custAlgnmtExtdAttributes = new HashMap<Integer, List<ExtdAttribue>>();
				Map<Integer, List<ExtdAttribue>> custExtdAttributes = new HashMap<Integer, List<ExtdAttribue>>();
				
				List<TCustAlgmnt> tCustAlignmentsList = tCustAlgmntDAO
						.getAllCustAlgmntsFromCId((int) customerId, userDetails.getTenantId());
				
				List<Integer> cId = new ArrayList<Integer>();
				cId.add((int) customerId);

				Alignment alignment = null;
				if (tCustAlignmentsList != null
						&& !tCustAlignmentsList.isEmpty()) {
					for (TCustAlgmnt tCustAlgmnt : tCustAlignmentsList) {
							alignment = new Alignment();
							BusinessUnit businessUnit = new BusinessUnit();
							SalesTeam salesTeam = new SalesTeam();
							businessUnit.setId(tCustAlgmnt.getBussUnitId());
							salesTeam.setId(tCustAlgmnt.getSalesTeamId());
							salesTeam.setBusinessUnit(businessUnit);
							alignment.setId(tCustAlgmnt.getAlgmntId());
							alignment.setSalesTeam(salesTeam);
							custAlgnmtExtdAttributes = getCustomerAlignmentExtAttrDetailsWithoutTempl(alignment, userDetails, cId);
							custExtdAttributes = customerDAOService.getCustomerExtAttrDetailsWithoutTempl(cId, userDetails);
							
						}
						
					}
					List<CustomerAlignment> custAlgnmtList = customerAlignmentModelAssembler
							.convertTCustAligmentsToModel(tCustAlignmentsList,
									custAlgnmtExtdAttributes,custExtdAttributes);
					
					LOGGER.info("*****************TO FETCH CUSTOMER ALIGNMENTS BY CUSTID WITH appliedTemplate as 'N' ENDED*****************");
					return custAlgnmtList;
			}
	} 
			catch (RuntimeException e) {
				LOGGER.error("Error during fetching of alignments by custid");
			throw new OpservDataAccessException(
					"Error during fetching of alignments by custid", e);
		}
		return null;

	}

	/**
	 * 
	 * @method getAllCustomerAlignments
	 * @param customerId
	 * @param buId
	 * @param userDetails
	 * @return
	 * @throws OpservDataAccessException
	 */

	public List<CustomerAlignment> getCustomerAlignmentFrBuid(long customerId,
			long buId, UserDetails userDetails)
			throws OpservDataAccessException {

		try {

			if (ApplyTemplate != null
					&& ApplyTemplate.equals(CommonConstants.CHAR_YES)) {
				LOGGER.info("*****************TO FETCH CUSTOMER ALIGNMENTS BY CUSTID AND BUID STARTED WITH appliedTemplate as 'Y'*****************");
				Map<Integer, List<ExtdAttribue>> custAlgnmtExtdAttributes = new HashMap<Integer, List<ExtdAttribue>>();
				Map<Integer, List<ExtdAttribue>> custExtdAttributes = new HashMap<Integer, List<ExtdAttribue>>();
				List<TCustAlgmnt> tCustAlignmentsList = tCustAlgmntDAO
						.getCustomerAlignmentFrBuid((int) customerId, buId,
								userDetails.getTenantId());
				List<Integer> cId = new ArrayList<Integer>();
				cId.add((int) customerId);
				Map<String, Alignment> uniqueAlignments = new HashMap<String, Alignment>();
				Map<String, EntityTemplate> uniqueEntityTemplates = new HashMap<String, EntityTemplate>();

				Alignment alignment = null;
				EntityTemplate entityTemplate = null;
				String key = null;
				if (tCustAlignmentsList != null
						&& !tCustAlignmentsList.isEmpty()) {
					for (TCustAlgmnt tCustAlgmnt : tCustAlignmentsList) {

						alignment = null;
						key = tCustAlgmnt.getAlgmntId().toString()
								+ tCustAlgmnt.getBussUnitId().toString()
								+ tCustAlgmnt.getSalesTeamId().toString();
						if (uniqueAlignments.containsKey(key)) {
							alignment = uniqueAlignments.get(key);
							entityTemplate = uniqueEntityTemplates.get(key);
						} else {
							alignment = new Alignment();
							BusinessUnit businessUnit = new BusinessUnit();
							SalesTeam salesTeam = new SalesTeam();
							businessUnit.setId(tCustAlgmnt.getBussUnitId());
							salesTeam.setId(tCustAlgmnt.getSalesTeamId());
							salesTeam.setBusinessUnit(businessUnit);
							alignment.setId(tCustAlgmnt.getAlgmntId());
							alignment.setSalesTeam(salesTeam);
							uniqueAlignments.put(key, alignment);
							entityTemplate = templateAlignmentDAOService
									.getEntityTemplate(
											EntityType.SALES_TEAM_CUSTOMER,
											alignment, userDetails);
							uniqueEntityTemplates.put(key, entityTemplate);
						}

						custAlgnmtExtdAttributes = getCustomerAlignmentExtAttrDetails(
								entityTemplate, alignment, userDetails, cId);
						custExtdAttributes = customerDAOService.getExtAttrDetails(EntityType.CUSTOMER, alignment, userDetails, cId);
					}
					List<CustomerAlignment> custAlgnmtList = customerAlignmentModelAssembler
							.convertTCustAligmentsToModel(tCustAlignmentsList,
									custAlgnmtExtdAttributes,custExtdAttributes);
					LOGGER.info("*****************TO FETCH CUSTOMER ALIGNMENTS BY CUSTID AND BUID STARTED WITH appliedTemplate as 'Y'*****************");
					return custAlgnmtList;

				}
			} else {
					LOGGER.info("*****************TO FETCH CUSTOMER ALIGNMENTS BY CUSTID AND BUID STARTED WITH appliedTemplate as 'N'*****************");
					
					Map<Integer, List<ExtdAttribue>> custAlgnmtExtdAttributes = new HashMap<Integer, List<ExtdAttribue>>();
					Map<Integer, List<ExtdAttribue>> custExtdAttributes = new HashMap<Integer, List<ExtdAttribue>>();
					
					List<TCustAlgmnt> tCustAlignmentsList = tCustAlgmntDAO
							.getCustomerAlignmentFrBuid((int) customerId, buId,
									userDetails.getTenantId());
					
					List<Integer> cId = new ArrayList<Integer>();
					cId.add((int) customerId);

					Alignment alignment = null;
					if (tCustAlignmentsList != null
							&& !tCustAlignmentsList.isEmpty()) {
						for (TCustAlgmnt tCustAlgmnt : tCustAlignmentsList) {
								alignment = new Alignment();
								BusinessUnit businessUnit = new BusinessUnit();
								SalesTeam salesTeam = new SalesTeam();
								businessUnit.setId(tCustAlgmnt.getBussUnitId());
								salesTeam.setId(tCustAlgmnt.getSalesTeamId());
								salesTeam.setBusinessUnit(businessUnit);
								alignment.setId(tCustAlgmnt.getAlgmntId());
								alignment.setSalesTeam(salesTeam);
								custAlgnmtExtdAttributes = getCustomerAlignmentExtAttrDetailsWithoutTempl(alignment, userDetails, cId);
							}
							custExtdAttributes = customerDAOService.getCustomerExtAttrDetailsWithoutTempl(cId, userDetails);
						}
						List<CustomerAlignment> custAlgnmtList = customerAlignmentModelAssembler
								.convertTCustAligmentsToModel(tCustAlignmentsList,
										custAlgnmtExtdAttributes,custExtdAttributes);
						
						LOGGER.info("*****************TO FETCH CUSTOMER ALIGNMENTS BY CUSTID AND BUID ENDED WITH appliedTemplate as 'Y'*****************");
						return custAlgnmtList;
				}
		} catch (RuntimeException e) {
			LOGGER.error("Error during fetching of alignments by custid based on BuId");
			throw new OpservDataAccessException(
					"Error during fetching of alignments by custid based on BuId",
					e);
		}
		return null;

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
	public Map<Integer, List<ExtdAttribue>> getCustomerAlignmentExtAttrDetails(EntityTemplate entityTemplate, Alignment alignment,
			UserDetails userDetails, List<Integer> custId)
			throws OpservDataAccessException {
		
		Long alId = alignment.getId() ;
		Long buId = alignment.getSalesTeam().getBusinessUnit().getId();
		Long stId = alignment.getSalesTeam().getId();
		Map<Integer, List<ExtdAttribue>> CustExtAttrList = new HashMap<Integer, List<ExtdAttribue>>();

		try {
			LOGGER.info("*****************TO FETCH CUSTOMER ALIGNMENTS EXTENDED ATTRIBUTES STARTED*****************");
			List<TCustSalesTeam> tcustSalesTeamList = tCustSalesTeamDAO.findTCustSalesTeamByCustId(alId,buId,stId,custId,userDetails.getTenantId());
			for (TCustSalesTeam tcustSalesTeam : tcustSalesTeamList) {
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
									|| Integer.parseInt(attrNum) > 20) {
								continue;
							}
						} catch (NumberFormatException pe) {
							continue;
						}
						ExtdAttribue extdAttribue = new ExtdAttribue();
						Method m = ReflectionHelper.getMethod(TCustSalesTeam.class,
								"getAttr" + attrNum);
						String attrValue = (String) ReflectionHelper.getValue(
								m, tcustSalesTeam);
						if (attrValue != null
								&& !attrValue.toString().trim()
										.equals(CommonConstants.EMPTY)) {
							extdAttribue.setId(attributeDefinition.getId());
							extdAttribue.setName(attributeDefinition.getName());
							extdAttribue.setDefinition(attributeDefinition);
							extdAttribue.setValue(attrValue);
							extdAttribuesList.add(extdAttribue);
						}else{
							extdAttribue.setId(attributeDefinition.getId());
							extdAttribue.setName(attributeDefinition.getName());
							extdAttribue.setDefinition(attributeDefinition);
							extdAttribue.setValue(CommonConstants.NUL);
							extdAttribuesList.add(extdAttribue);
						}
					}

				}
				CustExtAttrList.put(tcustSalesTeam.getTCust().getCustId(), extdAttribuesList);
			}

		} catch (RuntimeException e) {
			LOGGER.error("Error during fetching of Extended Attribute For Customer Alignment by customer Id");
			throw new OpservDataAccessException(
					"Error during fetching of Extended Attribute For Customer Alignment by customer Id",
					e);
		}
		LOGGER.info("*****************TO FETCH CUSTOMER ALIGNMENTS EXTENDED ATTRIBUTES ENDED*****************");
		return CustExtAttrList;

	}

	/**
	  * Save customer alignments.
	  *
	  * @param newCustAlgn the new cust algn
	  * @param userDetails the user details
	  * @param custIdGeoAlgmntFlgMap the cust id geo algmnt flg map
	  * @param orgCustAlgn the org cust algn
	  * @return the customer alignment
	  * @throws OpservDataAccessException the opserv data access exception
	  */
	public CustomerAlignment saveCustomerAlignments(
			CustomerAlignment newCustAlgn, UserDetails userDetails,
			Boolean geoAlgmntFlg, CustomerAlignment orgCustAlgn)
			throws OpservDataAccessException {

		CustomerAlignment customerAl = null;
		try {
			Long alId = newCustAlgn.getSalesPosition().getAlignmment().getId();
			Long buId = newCustAlgn.getSalesPosition().getAlignmment()
					.getSalesTeam().getBusinessUnit().getId();
			Long stId = newCustAlgn.getSalesPosition().getAlignmment()
					.getSalesTeam().getId();
			Long shId = newCustAlgn.getSalesPosition().getHierarchy().getId();
			Long spId = newCustAlgn.getSalesPosition().getId();

			TCustAlgmnt newTCustAlgmnt = new TCustAlgmnt();
			newTCustAlgmnt.setAlgmntId(alId);
			newTCustAlgmnt.setBussUnitId(buId);

			newTCustAlgmnt.setSalesTeamId(stId);
			newTCustAlgmnt.setSalesHierId(shId);
			newTCustAlgmnt.setSalesPosId(spId);
			newTCustAlgmnt.setActiveFlag(CommonConstants.ACTIVE_N); // SET as
																	// 'N'
			newTCustAlgmnt.setCreatedBy(userDetails.getUserId());
			newTCustAlgmnt.setCreateDt(DateUtil.getCurrentDate());
			newTCustAlgmnt.setEffStartDt(newCustAlgn.getStartDate());
			newTCustAlgmnt.setEffEndDt(newCustAlgn.getEndDate());
			newTCustAlgmnt.setTenantId(userDetails.getTenantId());
			
			if(orgCustAlgn != null && orgCustAlgn.getPriority() != null){
			newTCustAlgmnt.setPrtTypeId(orgCustAlgn.getPriority().getId());
			}
			
			newTCustAlgmnt.setImplFlag(orgCustAlgn != null && orgCustAlgn.isImplicitAssignment() == true ? CommonConstants.ACTIVE_Y
					: CommonConstants.ACTIVE_N);
			newTCustAlgmnt.setCompAlgmntFlag(CommonConstants.ACTIVE_N);
			newTCustAlgmnt.setAffFlag(orgCustAlgn != null && orgCustAlgn.isAffBasedAssignment() == true ? CommonConstants.ACTIVE_Y
							: CommonConstants.ACTIVE_N);
	
			newTCustAlgmnt
				.setTargetFlag(orgCustAlgn != null && orgCustAlgn.isCustomerTargeted() == true ? CommonConstants.ACTIVE_Y
						: CommonConstants.ACTIVE_N);
						
			newTCustAlgmnt
			.setGeoAlgmntFlag(geoAlgmntFlg.equals(true) ? CommonConstants.ACTIVE_Y
					: CommonConstants.ACTIVE_N);

			Customer customer = orgCustAlgn.getCustomer();
			TCust tCustEntity = customerEntityAssembler
					.convertCustomerModelToEntity(customer);
			newTCustAlgmnt.setTCust(tCustEntity);

			
			newTCustAlgmnt.setStatusId(ChangeRequestStatusType.PENDING_FOR_APPROVAL.getId()); // SET
																				// as
																				// 'PS'

			TCustAlgmnt createTCustAlgmnt = tCustAlgmntDAO
					.createTCustAlgmnt(newTCustAlgmnt);
			customerAl = customerAlignmentModelAssembler
					.convertTCustAlToModel(createTCustAlgmnt, true);
		} catch (RuntimeException e) {
			LOGGER.error("Error occcurred while Saving Customer Alignments");
			throw new OpservDataAccessException(
					"Error occcurred while Saving Customer Alignments", e);
		}

		return customerAl;

	}

	/**
	 * Update customer alignments.
	 * 
	 * @param orgCustAlgn
	 *            the org cust algn
	 * @param statusId
	 *            the user details
	 * @return the customer alignment
	 * @throws OpservDataAccessException
	 *             the opserv data access exception
	 */
	public CustomerAlignment updateCustomerAlignments(
			CustomerAlignment orgCustAlgn, UserDetails userDetails,Integer statusId)
			throws OpservDataAccessException {
		LOGGER.info("=========Update of lock Customer Alignment  ==============Started" +statusId);
		CustomerAlignment custAlignment = null;
		TCustAlgmnt srcTCustAlgmnt = new TCustAlgmnt();

		try {
			if(orgCustAlgn != null && orgCustAlgn.getId() != null) {
				LOGGER.info("=========Fetch TCustAlignment Details from Cust Alignment Id  ==============Started");
				srcTCustAlgmnt = tCustAlgmntDAO.findTCustAlgmntById(orgCustAlgn
						.getId());

				if (srcTCustAlgmnt != null) {
					TCustAlgmnt updteCustAlgmnt = new TCustAlgmnt();
					srcTCustAlgmnt.setStatusId(statusId);
					srcTCustAlgmnt.setUpdateDt(DateUtil.getCurrentDate());
					// srcTCustAlgmnt.setEffEndDt(newCustAlgn.getStartDate());
					srcTCustAlgmnt.setUpdatedBy(userDetails.getUserId());
					LOGGER.info("=========Updated TCustAlignment table  ==============Started");
					updteCustAlgmnt = tCustAlgmntDAO
							.updateTCustAlgmnt(srcTCustAlgmnt);
					LOGGER.info("=========Updated TCustAlignment table  ==============Ended");
					custAlignment = customerAlignmentModelAssembler
							.convertTCustAlToModel(updteCustAlgmnt, true);
				}
			}
			LOGGER.info("=========Update of lock Customer Alignment  ==============Ended");
		} catch (RuntimeException e) {
			LOGGER.error("Error occcurred while Updating Customer Alignments");
			throw new OpservDataAccessException(
					"Error occcurred while Updating Customer Alignments", e);
		}

		return custAlignment;

	}

	/**
	 * 
	 * @method updateCustomerAlignment
	 * @param customerAlignment
	 * @throws OpservDataAccessException
	 */
	@Override
	public void updateCustomerAlignmentChangeRequest(CustomerAlignment customerAlignment) throws OpservDataAccessException {
		
		try{
			if(null!=customerAlignment){
				TCustAlgmnt tCustAlgmnt = customerAlignmentEntityAssembler.mapCustAlignModelToEntity(customerAlignment);
				tCustAlgmntDAO.updateTCustAlgmnt(tCustAlgmnt);
			}
		}catch(RuntimeException re){
			LOGGER.error("Error occcurred while Updating Customer Alignment Change Request");
			throw new OpservDataAccessException(
					"Error occcurred while Updating Customer Alignment Change Request", re);
		}
		
	}

	/**
	 * Gets the customer alignment fr sp.
	 *
	 * @param customerId the customer id
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return the customer alignment fr sp
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public List<CustomerAlignment> getCustomerAlignmentFrSP(long customerId,
			SalesPosition salesPosition, UserDetails userDetails)
			throws OpservDataAccessException {
			
		try{
			Long shId = salesPosition.getHierarchy().getId();
			Long spId = salesPosition.getId();
			Long buId = salesPosition.getAlignmment().getSalesTeam().getBusinessUnit().getId();
			Long stId = salesPosition.getAlignmment().getSalesTeam().getId();
			Long alId = salesPosition.getAlignmment().getId();
			
			List<TCustAlgmnt> tCustAlgmnt = tCustAlgmntDAO.findTCustAlgmnts(userDetails.getTenantId(), shId, buId, stId, alId, spId, (int) customerId);
			return customerAlignmentModelAssembler.convertTCustAligmentsToModel(tCustAlgmnt);
		}catch(RuntimeException re){
			LOGGER.error("Error occcurred while fetching customer alignment for salesposition");
			throw new OpservDataAccessException(
					"Error occcurred while fetching customer alignment for salesposition", re);
		}
		
	}

	@Override
	public void updateGeoCustAlignmentIdBySalesPostion(
			SalesPosition salesPos, UserDetails userDetails)
			throws OpservDataAccessException {

		try{
			LOGGER.debug("updateGeoCustAlignmentIdBySalesPostion:: salesPos ->"+salesPos.getId());
			Long spId = salesPos.getId();
			short tenantId = userDetails.getTenantId();

			LOGGER.debug("updateGeoCustAlignmentIdBySalesPostion:: updating for source ");
			List<TCustAlgmnt> tNonGeoCustAlgmntList = tCustAlgmntDAO.findNonGeoCustAlignmentIdBySalesPostion(spId,tenantId);
			if (null != tNonGeoCustAlgmntList && tNonGeoCustAlgmntList.size() > 0) {
				for (TCustAlgmnt custAlgmnt : tNonGeoCustAlgmntList) {
					custAlgmnt.setGeoAlgmntFlag(CommonConstants.ACTIVE_N);
				}
				tCustAlgmntDAO.updateTCustAlgmnts(tNonGeoCustAlgmntList);
			}

			LOGGER.debug("updateGeoCustAlignmentIdBySalesPostion:: updating for target ");
			List<TCustAlgmnt> tGeoCustAlgmntList = tCustAlgmntDAO.findGeoCustAlignmentIdBySalesPostion(spId,tenantId);
			if (null != tGeoCustAlgmntList && tGeoCustAlgmntList.size() > 0) {
				for (TCustAlgmnt custAlgmnt : tGeoCustAlgmntList) {
					custAlgmnt.setGeoAlgmntFlag(CommonConstants.ACTIVE_Y);
				}
				tCustAlgmntDAO.updateTCustAlgmnts(tGeoCustAlgmntList);
			}
			//return CustomerAlignmentModelAssembler.convertTCustAligmentsToModel(tCustAlgmntList);
		}catch(RuntimeException re){
			LOGGER.error("Error occcurred while updating custAlignmentId by salesPostion");
			throw new OpservDataAccessException(
					"Error occcurred while updating custAlignmentId by salesPostion", re);
		}
		
	}
	
	
	/**
	 * Gets the Customer count for sales position.
	 *
	 * @param salesPosition the salesPosition
	 * @param userDetails the user details
	 * @return the customer count for sales position
	 * @throws AlignmentServiceException 
	 */
	@Override
	public long getCustomerCountForSalesPosition(SalesPosition salesPosition,
			UserDetails userDetails) throws OpservDataAccessException {
		
		try{
			long spId = salesPosition.getId();
			long shId = salesPosition.getHierarchy().getId();
			
			long custCount = tCustAlgmntDAO.fetchCountOfTCustAlgmnt(spId,shId,userDetails.getTenantId());
			
			return custCount;
		}catch(RuntimeException re){
			LOGGER.error("Error occcurred while getting customer count for salesPostion");
			throw new OpservDataAccessException("Error occcurred while getting customer count for salesPostion", re);
		}
		
	}
	/**
	 * Gets the geo algd customer count for sales position.
	 *
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return the geo algd customer count for sales position
	 * @throws AlignmentServiceException the alignment service exception
	 */
	@Override
	public long getGeoAlgdCustomerCountForSalesPosition(
			SalesPosition salesPosition, UserDetails userDetails)
			throws OpservDataAccessException {
		try{
			long spId = salesPosition.getId();
			long shId = salesPosition.getHierarchy().getId();
			
			long geoAlgdCustCount = tCustAlgmntDAO.getCountOfGeoAlgdCustFrSp(spId, shId, userDetails.getTenantId());
			
			return geoAlgdCustCount;
		}
		catch(RuntimeException re){
			LOGGER.error("Error occcurred while getting count of geo and non-geo aligned customer for SP");
			throw new OpservDataAccessException("Error occcurred while getting count of geo and non-geo aligned customer for SP", re);
		}
	}
	/**
	 * Gets the non geo algd customer count for sales position.
	 *
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return the non geo algd customer count for sales position
	 * @throws AlignmentServiceException the alignment service exception
	 */
	@Override
	public long getNonGeoAlgdCustomerCountForSalesPosition(
			SalesPosition salesPosition, UserDetails userDetails)
			throws OpservDataAccessException {
		try{
			long spId = salesPosition.getId();
			long shId = salesPosition.getHierarchy().getId();
			
			long nonGeoAlgdCustCount = tCustAlgmntDAO.getCountOfNonGeoAlgdCustFrSp(spId, shId, userDetails.getTenantId());
			
			return nonGeoAlgdCustCount;
		}catch(RuntimeException re){
			LOGGER.error("Error occcurred while getting count of non-geo aligned customer for SP");
			throw new OpservDataAccessException("Error occcurred while getting count of non-geo aligned customer for SP", re);
		}
	}
	
	/**
	 * This method is used to save data into TempChngReq
	 * @param orgCustAlgn
	 * @param newCustAlgn
	 * @param changeRequest
	 * @param comments
	 * @param userDetails
	 * @param pushPull
	 * @return TTempChngreq
	 */
	@Override
	public TChngreqOffline saveIntoCROffline(CustomerAlignment orgCustAlgn,
			CustomerAlignment newCustAlgn, ChangeRequest changeRequest,
			String comments, UserDetails userDetails, String funcDesc,
			BusinessReason businessReason, List<CustomerProductAlignment> oldCustomerProductAlignments, List<CustomerProductAlignment> newCustomerProductAlignments ) throws OpservDataAccessException {

		try{
			CustomerBlob customerBlob = new CustomerBlob();
			customerBlob.setSrcCustomerAlignment(orgCustAlgn);
			customerBlob.setTargetCustomerAlignment(newCustAlgn); // newCustAlgn
																	// will be set
																	// to null for
																	// edit customer
			
			customerBlob.setUserDetails(userDetails);
			customerBlob.setBussReason(businessReason);
			customerBlob.setComments(comments);
			
			//For old and new CustomerProductAlignment
			
			customerBlob.setOldCustomerProductAlignments(oldCustomerProductAlignments);
			customerBlob.setNewCustomerProductAlignments(newCustomerProductAlignments);
			
			TChngreqOffline tChngreqOffline = new TChngreqOffline();
			LOGGER.info("****** Save status in t_chng_req_offline table to INITIATED for CR ID===========*****"
					+ changeRequest.getId());
			tChngreqOffline.setStatusId(ChangeRequestOfflineStatusType.INITIATED.getId());
			tChngreqOffline.setFailedReason("");
			
			if(funcDesc.equalsIgnoreCase(CommonConstants.PUSH)){
				tChngreqOffline.setTriggerId(ChangeRequestTriggerType.PUSH_CUSTOMER.getId());
			}else if(funcDesc.equalsIgnoreCase(CommonConstants.PULL)){
				tChngreqOffline.setTriggerId(ChangeRequestTriggerType.PULL_CUSTOMER.getId());
			}else if(funcDesc.equalsIgnoreCase(CommonConstants.EDIT)){
				tChngreqOffline.setTriggerId(ChangeRequestTriggerType.EDIT_CUSTOMER.getId());
			}
			
			TChngReq tChngReq = tChngReqDAO.findTChngReqById(changeRequest.getId());
			tChngreqOffline.setTChngReq(tChngReq);
			ChangeRequest crModelBasicData = changeRequestAssembler.convertTChngReqToBasicChangeRequestModel(tChngReq);
			customerBlob.setChangeRequest(crModelBasicData);
			String json = JSONUtil.toJSON(customerBlob);
			tChngreqOffline.setReqDetail(json);
			tChngreqOffline.setTenantId(userDetails.getTenantId());
			tChngreqOffline.setCreateDt(DateUtil.getCurrentDate());
			tChngreqOffline.setCreatedBy(userDetails.getUserId());
			
			TChngreqOffline tempChngreq = tChngreqOfflineDAO.createTChngreqOffline(tChngreqOffline);
			return tempChngreq;
		}catch(RuntimeException re){
			LOGGER.error("Error occcurred while inserting records into ChngreqOffline");
			throw new OpservDataAccessException("Error occcurred while inserting records into ChngreqOffline", re);
		}
		
				
	}

	/**
	 * Edits the ext attr fr cust sales team.
	 *
	 * @param newCustomerAlignment the new customer alignment
	 * @param userDetails the user details
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public void editExtAttrFrCustSalesTeam(SalesPosition salesPosition,
			CustomerAlignment newCustomerAlignment, UserDetails userDetails)
			throws OpservDataAccessException {
		try{
			
			//Update Extended Attributes for tCustSalesTeam
			Long alId = salesPosition.getAlignmment().getId();
			Long buId = salesPosition.getAlignmment().getSalesTeam().getBusinessUnit().getId();
			Long stId = salesPosition.getAlignmment().getSalesTeam().getId();
			List<ExtdAttribue> custSalesTeamExtdAttr = newCustomerAlignment.getExtdAttributes(); 
			int no_of_columns = 20;
			List<String> listColOfTCust = new ArrayList<String>();
			Map<String, String> attNameMapFrCustST = new HashMap<String, String>(); 
			for (int i = 1; i <= no_of_columns; i++) {
			listColOfTCust.add("attr_" + i);
			}
			
			List<Integer> custIds = new ArrayList<Integer>();
			custIds.add(newCustomerAlignment.getCustomer().getId().intValue());
			
			
			if (listColOfTCust != null && !listColOfTCust.isEmpty()) {
				for (int j = 0; j< custSalesTeamExtdAttr.size(); j++) {
					if (listColOfTCust.contains(custSalesTeamExtdAttr.get(j).getDefinition().getName())) {
						attNameMapFrCustST.put(custSalesTeamExtdAttr.get(j).getDefinition().getName(), custSalesTeamExtdAttr.get(j).getValue());
					}
				}
		}
				 List<TCustSalesTeam> tCustSalesTeams = tCustSalesTeamDAO.findTCustSalesTeamByCustId(alId, buId, stId, custIds, userDetails.getTenantId()); 
					if(!tCustSalesTeams.isEmpty() && !tCustSalesTeams.isEmpty()){
						for(TCustSalesTeam tcSalesTeam : tCustSalesTeams){
							TCustSalesTeam tCustSalesTeam = customerAlignmentEntityAssembler.mapCustomerAlignmentModelToEntity(tcSalesTeam, newCustomerAlignment,attNameMapFrCustST, userDetails);	
							tCustSalesTeamDAO.updateTCustSalesTeam(tCustSalesTeam);
						}
					}
			
	    }catch (RuntimeException e) {
	    	LOGGER.error("Error during editing of Extended Attribute For Customer Sales Team");
			throw new OpservDataAccessException("Error during editing of Extended Attribute For Customer Sales Team",e);
		}
		
	}

	
	/**
	 * Edits the ext attr fr cust prd sales team.
	 *
	 * @param newCustomerProductAlignments the new customer product alignments
	 * @param userDetails the user details
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public void editExtAttrFrCustPrdSalesTeam(List<CustomerProductAlignment> customerProductAlignmentList,UserDetails userDetails)throws OpservDataAccessException {
		
			//Update Extended Attributes for tCustPrdSalesTeam
		try{
			List<ExtdAttribue> custPrdExtdAttributes = null; 
			List<Long> prdIdList = new ArrayList<Long>();
			/*for(CustomerProductAlignment customerProductAlignment: customerProductAlignmentList ){
				Long prdId = customerProductAlignment.getProduct().getId();
				if(!prdIdList.contains(prdId)){
					prdIdList.add(prdId);
				}
			
			}*/
			
			for(CustomerProductAlignment customerProductAlignment : customerProductAlignmentList){
				custPrdExtdAttributes = customerProductAlignment.getExtdAttributes();
				Long alId = customerProductAlignment.getSalesPosition().getAlignmment().getId();
				Long buId = customerProductAlignment.getSalesPosition().getAlignmment().getSalesTeam().getBusinessUnit().getId();
				Long stId = customerProductAlignment.getSalesPosition().getAlignmment().getSalesTeam().getId();
				Integer custId=customerProductAlignment.getCustomer().getId().intValue();
				prdIdList.add(customerProductAlignment.getProduct().getId());
			 	int no_of_columns = 20;
				List<String> listColOfTCust = new ArrayList<String>();
				for (int i = 1; i <= no_of_columns; i++) {
				listColOfTCust.add("attr_" + i);
				}

				Map<String, String> attNameMapFrCustPrd = new HashMap<String, String>(); 
					 if (listColOfTCust != null && !listColOfTCust.isEmpty()) {
							for (int j = 0; j< custPrdExtdAttributes.size(); j++) {
								if (listColOfTCust.contains(custPrdExtdAttributes.get(j).getDefinition().getName())) {
									attNameMapFrCustPrd.put(custPrdExtdAttributes.get(j).getDefinition().getName(), custPrdExtdAttributes.get(j).getValue());
								}
							}
					}
					List<TCustPrdSalesTeam> tCustPrdSalesTeam = tCustPrdSalesTeamDao.getSalesTeamByCustIdAlBuStPrdId(custId, alId, stId, buId , prdIdList, userDetails.getTenantId());
					if(!tCustPrdSalesTeam.isEmpty() && !tCustPrdSalesTeam.isEmpty()){
						for(TCustPrdSalesTeam custPrdSalTeam : tCustPrdSalesTeam){
							
								TCustPrdSalesTeam custPrdSalesTeam = customerProductEntityAssembler.mapCustomerProductModelToEntity(custPrdSalTeam, customerProductAlignment,attNameMapFrCustPrd, userDetails);	
								tCustPrdSalesTeamDao.updateTCustSalesTeam(custPrdSalesTeam);
						}
					}
					
					prdIdList.remove(customerProductAlignment.getProduct().getId());
				}
			
		}catch (RuntimeException e) {
			LOGGER.error("Error during editing of Extended Attribute For Customer Product Sales Team");
			throw new OpservDataAccessException("Error during editing of Extended Attribute For Customer Product Sales Team",e);
		}
		 	
	}

	/**
	 * Fetch cust al fr cust.
	 *
	 * @param salesPosition the sales position
	 * @param affCustId the aff cust id
	 * @param userDetails the user details
	 * @return the list
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public List<CustomerAlignment> fetchCustAlFrCust(SalesPosition salesPosition,List<Integer> custIdList,
			UserDetails userDetails)throws OpservDataAccessException {
		
		try{
			List<CustomerAlignment> customerAlignmentList = null;
			if(null!=salesPosition){
				List<TCustAlgmnt> custAlgmntList = tCustAlgmntDAO
						.findTCustAlgmntFrCustIdList(userDetails.getTenantId(),
								salesPosition.getHierarchy().getId(), salesPosition
										.getAlignmment().getSalesTeam()
										.getBusinessUnit().getId(), salesPosition
										.getAlignmment().getSalesTeam().getId(),
								salesPosition.getAlignmment().getId(),
								salesPosition.getId(), custIdList);
				
			 customerAlignmentList = customerAlignmentModelAssembler.convertTCustAligmentsToModel(custAlgmntList);
						
						
			}
			return customerAlignmentList;
		}catch(RuntimeException re){
			LOGGER.error("Error during fetch Customer Alignment details");
			throw new OpservDataAccessException("Error during fetch Customer Alignment details.",re);
		}
		}
		

	/**
	 * Check status of customer algmnt.
	 *
	 * @param custId the cust id
	 * @param alId the al id
	 * @param buId the bu id
	 * @param stId the st id
	 * @param userDetails the user details
	 * @return true, if successful
	 */
	@Override
	public boolean checkCustCRStatusInAlgmnt(Integer custId, Long alId,
			Long buId, Long stId, UserDetails userDetails) throws OpservDataAccessException{
		try{
			Long countOfCustAlgmntByStatus = tCustAlgmntDAO.countOfCustAlgmntByStatus(custId, alId, buId, stId, userDetails.getTenantId());
			if(countOfCustAlgmntByStatus > 0){
				return true;
			}
			return false;
		}catch(RuntimeException re){
			LOGGER.error("Error occurred while checking customer CR status");
			throw new OpservDataAccessException("Error occurred while checking customer CR status", re);
		}
		
	}

	
	/**
	 * Gets the Customer count for sales position.
	 *
	 * @param salesPosition the salesPositions
	 * @param userDetails the user details
	 * @return the customer count for sales position
	 * @throws AlignmentServiceException 
	 */
	@Override
	public long getCustomerCountForSalesPositions(
			List<SalesPosition> salesPositions, UserDetails userDetails)
			throws OpservDataAccessException {
		List<Long> spIds = new ArrayList<Long>();
		
		try{
			for (SalesPosition salesPosition : salesPositions) {
				if (null != salesPosition.getId()
						&& salesPosition.getId() != 0) {
					spIds.add(salesPosition.getId());
				}
			}
			
			long custCount = tCustAlgmntDAO.findCountOfTCustAlgmntForSpList(spIds, userDetails.getTenantId());
			
			return custCount;
		}catch(RuntimeException re){
			LOGGER.error("Exception occurred while getting count of customers for SP");
			throw new OpservDataAccessException("Exception occurred while getting count of customers for SP", re);
		}
		
		
	}

	/**
	 * Gets the geo algd customer count for sales position.
	 *
	 * @param salesPosition the sales positions
	 * @param userDetails the user details
	 * @return the geo algd customer count for sales position
	 * @throws AlignmentServiceException the alignment service exception
	 */
	
	@Override
	public long getGeoAlgdCustomerCountForSalesPositions(
			List<SalesPosition> salesPositions, UserDetails userDetails)
			throws OpservDataAccessException {
		List<Long> spIds = new ArrayList<Long>();
		try{
			for (SalesPosition salesPosition : salesPositions) {
				if (null != salesPosition.getId()
						&& salesPosition.getId() != 0) {
					spIds.add(salesPosition.getId());
				}
			}
			
			long geoAlgdCustCount = tCustAlgmntDAO.getGeoAlgdCustomerCountForSalesPositions(spIds, userDetails.getTenantId());
			
			return geoAlgdCustCount;
		}catch(RuntimeException re){
			LOGGER.error("Exception occurred while getting count of geo-aligned customers for SP");
			throw new OpservDataAccessException("Exception occurred while getting count of geo-aligned customers for SP", re);
		}
		
	}

	/**
	 * Gets the non geo algd customer count for sales position.
	 *
	 * @param salesPosition the sales positions
	 * @param userDetails the user details
	 * @return the non geo algd customer count for sales position
	 * @throws AlignmentServiceException the alignment service exception
	 */
	
	@Override
	public long getNonGeoAlgdCustomerCountForSalesPositions(
			List<SalesPosition> salesPositions, UserDetails userDetails)
			throws OpservDataAccessException {
		List<Long> spIds = new ArrayList<Long>();
		try{
			for (SalesPosition salesPosition : salesPositions) {
				if (null != salesPosition.getId()
						&& salesPosition.getId() != 0) {
					spIds.add(salesPosition.getId());
				}
			}
			
			long nonGeoAlgdCustCount = tCustAlgmntDAO.getCountOfNonGeoAlgdCustFrSpList(spIds, userDetails.getTenantId());
			
			return nonGeoAlgdCustCount;
		}catch(RuntimeException re){
			LOGGER.error("Exception occurred while getting count of non-geoAligned customers for SP");
			throw new OpservDataAccessException("Exception occurred while getting count of non-geoAligned customers for SP", re);
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
	public Map<Integer, List<ExtdAttribue>> getCustomerAlignmentExtAttrDetailsWithoutTempl(Alignment alignment,
			UserDetails userDetails, List<Integer> custId)
			throws OpservDataAccessException {
		
		Long alId = alignment.getId() ;
		Long buId = alignment.getSalesTeam().getBusinessUnit().getId();
		Long stId = alignment.getSalesTeam().getId();
		Map<Integer, List<ExtdAttribue>> CustExtAttrList = new HashMap<Integer, List<ExtdAttribue>>();

		try {
			LOGGER.info("*****************TO FETCH CUSTOMER ALIGNMENTS EXTENDED ATTRIBUTES STARTED WITHOUT TEMPLATE STARTED FOR CUSTOMER ID== *****************" + custId);
			List<TCustSalesTeam> tcustSalesTeamList = tCustSalesTeamDAO.findTCustSalesTeamByCustId(alId,buId,stId,custId,userDetails.getTenantId());
			
			TCustSalesTeam tCustSalesTeam = null;
			if(tcustSalesTeamList != null && !tcustSalesTeamList.isEmpty()){
				tCustSalesTeam = tcustSalesTeamList.get(0);
			}
			if(tCustSalesTeam != null){
			int no_of_columns = CommonConstants.custSTExtAttrCount;
			
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
						Method m = ReflectionHelper.getMethod(TCustSalesTeam.class,
								"getAttr" + attrNum);
						String attrValue = (String) ReflectionHelper.getValue(
								m, tCustSalesTeam);
						
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

				CustExtAttrList.put(tCustSalesTeam.getTCust().getCustId(), extdAttribuesList);
			}
				LOGGER.info("*****************TO FETCH CUSTOMER ALIGNMENTS EXTENDED ATTRIBUTES WITHOUT TEMPLATE ENDED FOR CUSTOMER ID== *****************" + custId);
		} catch (RuntimeException e) {
			LOGGER.error("Error during fetching of Extended Attribute For Customer Alignment by customer Id");
			throw new OpservDataAccessException(
					"Error during fetching of Extended Attribute For Customer Alignment by customer Id",
					e);
		}
		LOGGER.info("*****************TO FETCH CUSTOMER ALIGNMENTS EXTENDED ATTRIBUTES ENDED*****************");
		return CustExtAttrList;

	}

	/**
	 * Gets the all customer alignments by cust algnmt id.
	 * 
	 * @param custAlgId
	 *            the cust alg id
	 * @param userDetails
	 *            the user details
	 * @return the all customer alignments by cust algnmt id
	 */
	@Override
	@Transactional
	public CustomerAlignment getAllCustomerAlignmentsByCustAlgnmtId(
			long custAlgId, UserDetails userDetails) {
		
		try{

		CustomerAlignment customerAlignment = new CustomerAlignment();
		Map<Integer, List<ExtdAttribue>> custAlgnmtExtdAttributes = new HashMap<Integer, List<ExtdAttribue>>();
		Map<Integer, List<ExtdAttribue>> custExtdAttributes = new HashMap<Integer, List<ExtdAttribue>>();
		EntityTemplate entityTemplate = new EntityTemplate();
		List<Integer> cId = new ArrayList<Integer>();

		TCustAlgmnt tCustAlgnmt = tCustAlgmntDAO.findTCustAlgmntById(custAlgId);
		cId.add(tCustAlgnmt.getTCust().getCustId());

		if (tCustAlgnmt != null) {

			Alignment alignment = new Alignment();
			BusinessUnit businessUnit = new BusinessUnit();
			SalesTeam salesTeam = new SalesTeam();
			businessUnit.setId(tCustAlgnmt.getBussUnitId());
			salesTeam.setId(tCustAlgnmt.getSalesTeamId());
			salesTeam.setBusinessUnit(businessUnit);
			alignment.setId(tCustAlgnmt.getAlgmntId());
			alignment.setSalesTeam(salesTeam);
			
			if (ApplyTemplate != null
					&& ApplyTemplate.equals(CommonConstants.CHAR_YES)) {
			LOGGER.info("*****************TO FETCH CUSTOMER ALIGNMENTS BY CUSTOMER ALIGNMENT STARTED WITH appliedTemplate as 'Y'*****************");

			entityTemplate = templateAlignmentDAOService.getEntityTemplate(
					EntityType.SALES_TEAM_CUSTOMER, alignment, userDetails);
			
			custAlgnmtExtdAttributes = getCustomerAlignmentExtAttrDetails(
					entityTemplate, alignment, userDetails, cId);
			
			custExtdAttributes = customerDAOService.getExtAttrDetails(
					EntityType.CUSTOMER, alignment, userDetails, cId);
			LOGGER.info("*****************TO FETCH CUSTOMER ALIGNMENTS BY CUSTOMER ALIGNMENT ENDED WITH appliedTemplate as 'Y'*****************");
			}else{
				LOGGER.info("*****************TO FETCH CUSTOMER ALIGNMENTS BY CUSTID STARTED WITH appliedTemplate as 'N'*****************");
				custAlgnmtExtdAttributes = getCustomerAlignmentExtAttrDetailsWithoutTempl(alignment, userDetails, cId);
				custExtdAttributes = customerDAOService.getCustomerExtAttrDetailsWithoutTempl(cId, userDetails);
				LOGGER.info("*****************TO FETCH CUSTOMER ALIGNMENTS BY CUSTID ENDED WITH appliedTemplate as 'N'*****************");
			}

			customerAlignment = customerAlignmentModelAssembler
					.convertTCustAlToModel(tCustAlgnmt,
							custAlgnmtExtdAttributes, custExtdAttributes);
		}

		return customerAlignment;
	}
	catch (RuntimeException dae) {
		LOGGER.error("Error while fetching of customer alignments based on customer alignment id");
		throw new OpservDataAccessException(
				"Error while fetching of customer alignments based on customer alignment id",
				dae);
	}
	}
	/**
	 * @param custId
	 * @param spIdList
	 * @param userDetails
	 * @return
	 * @throws OpservDataAccessException
	 */
	@Override
	public List<SalesPosition> fetchAssignedCustForSp(Long custId,
			List<Long> spIdList, UserDetails userDetails)
			throws OpservDataAccessException {
		try {
			List<SalesPosition> salesPositionList = new ArrayList<SalesPosition>();

			if (custId != null && spIdList != null && !spIdList.isEmpty()) {
				SalesPosition salesPosition = new SalesPosition();
				List<TSalesPos> tSalesPosList = tCustAlgmntDAO
						.getAssignedCustomerFrSp(custId, spIdList,
								userDetails.getTenantId());
				if (tSalesPosList != null && !tSalesPosList.isEmpty()) {
					for (TSalesPos tSalesPos : tSalesPosList) {
						salesPosition = customerAlignmentModelAssembler
								.convertTSalesPosToSalesPostion(tSalesPos);
						// salesPosition=salesPositionModelAssembler.mapSPEntityDetailsToSalesPos(tSalesPos,userDetails.getTenantId());
						salesPositionList.add(salesPosition);
					}
				}
			}
			return salesPositionList;
		} catch (RuntimeException dae) {
			LOGGER.error("Error while fetching List of SalesPosition details for Customer");
			throw new OpservDataAccessException(
					"Error while fetching List of SalesPosition details for Customer",
					dae);
		}
	}

	/**
	 * Edit customer alignment.
	 * 
	 * @param newCustomerAlignment
	 *            the new customer alignment
	 * @param userDetails
	 *            the user details
	 */
	public void editCustAlgmnt(CustomerAlignment newCustomerAlignment,UserDetails userDetails) throws OpservDataAccessException{
		
		try{
			newCustomerAlignment.setStatus(ChangeRequestStatusType.APPROVED);
			newCustomerAlignment.setUpdatedBy(userDetails.getUserId());
			newCustomerAlignment.setUpdatedDate(DateUtil.getCurrentDate());
			TCustAlgmnt tCustAlgmnt = customerAlignmentEntityAssembler.mapCustAlignModelToEntity(newCustomerAlignment);
			tCustAlgmntDAO.updateTCustAlgmnt(tCustAlgmnt);
		} catch(RuntimeException rExp){
			LOGGER.error("Error occcurred while Updating Customer Alignments");
			throw new OpservDataAccessException("Error occcurred while Updating Customer Alignments", rExp);
		}
	
	}
	
	/**
	 * @param customerAlgmnt
	 * @param userDetails
	 * @return boolean
	 * @throws OpservDataAccessException
	 */
	@Override
	public boolean lockCustomerAlignment(Long CustAlgmntId,Integer statusId, Integer userId) throws OpservDataAccessException{
		try {
			int lockedCust = tCustAlgmntDAO.updateTCustAlgmntToLock(CustAlgmntId,statusId,userId);
			if(lockedCust > 0){
				return true;
			}
		} catch (RuntimeException e) {
			LOGGER.error("Error occcurred while Updating Customer Alignments");
			throw new OpservDataAccessException(
					"Error occcurred while Updating Customer Alignments", e);
		}
		return false;
	}
	
	/**
	 * @param customerAlgmnt
	 * @param userDetails
	 * @return boolean
	 * @throws OpservDataAccessException
	 */
	@Override
	public boolean unlockCustomerAlignment(Long CustAlgmntId,Integer statusId, Integer userId) throws OpservDataAccessException{
		try {
			List<Long> custAlIds = new ArrayList<Long>();
			custAlIds.add(CustAlgmntId);
			int unlockedCust = tCustAlgmntDAO.updateTCustAlgmntToUnLock(custAlIds,statusId,userId);
			if(unlockedCust > 0){
				return true;
			}
		} catch (RuntimeException e) {
			LOGGER.error("Error occcurred while Updating Customer Alignments");
			throw new OpservDataAccessException(
					"Error occcurred while Updating Customer Alignments", e);
		}
		return false;
	}

	@Override
	public boolean unlockCustomerAlignments(List<Long> CustAlgmntIds,
			Integer statusId, Integer userId) throws OpservDataAccessException {
		try {
			int unlockedCust = tCustAlgmntDAO.updateTCustAlgmntToUnLock(CustAlgmntIds,statusId,userId);
			if(unlockedCust > 0){
				return true;
			}
		} catch (RuntimeException e) {
			LOGGER.error("Error occcurred while Updating Customer Alignments");
			throw new OpservDataAccessException(
					"Error occcurred while Updating Customer Alignments", e);
		}
		return false;
	}
	
	
}