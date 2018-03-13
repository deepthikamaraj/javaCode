package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.internal.util.ReflectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.assembler.CustomerAlignmentEntityAssembler;
import com.cognizant.opserv.sp.assembler.CustomerProductAlignmentEntityAssembler;
import com.cognizant.opserv.sp.assembler.CustomerProductAlignmentModelAssembler;
import com.cognizant.opserv.sp.assembler.ProductAlignmentEntityAssembler;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.EntityType;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.common.ApplicationConstant;
import com.cognizant.opserv.sp.core.dao.TCustAlgmntDAO;
import com.cognizant.opserv.sp.core.dao.TCustPrdAlgmntDAO;
import com.cognizant.opserv.sp.core.dao.TCustPrdSalesTeamDAO;
import com.cognizant.opserv.sp.core.dao.TPrdAlgmntDAO;
import com.cognizant.opserv.sp.core.dao.TSalesPosDAO;
import com.cognizant.opserv.sp.core.entity.TCustAlgmnt;
import com.cognizant.opserv.sp.core.entity.TCustPrdAlgmnt;
import com.cognizant.opserv.sp.core.entity.TCustPrdAlgmntId;
import com.cognizant.opserv.sp.core.entity.TCustPrdSalesTeam;
import com.cognizant.opserv.sp.core.entity.TPrdAlgmnt;
import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.EntityTemplate;
import com.cognizant.opserv.sp.model.ProductAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.attrb.AttributeDefinition;
import com.cognizant.opserv.sp.model.attrb.ExtdAttribue;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerProductAlignmentDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.TemplateAlignmentDAOService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************.
 *
 * @class CustomerProductAlignmentDAOServiceImpl contains all the services for Customer product  alignment dts
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Component
public class CustomerProductAlignmentDAOServiceImpl implements CustomerProductAlignmentDAOService {
	
	
	@Autowired
	private TCustPrdAlgmntDAO tCustPrdAlgmntDAO;
	

	@Autowired
	private TemplateAlignmentDAOService templateAlignmentDAOService;
	
	@Autowired
	private  TCustPrdSalesTeamDAO tCustPrdSalesTeamDAO;
	
	@Autowired
	private TSalesPosDAO tSalesPosDAO;
	
	@Autowired
	private TCustAlgmntDAO tCustAlgmntDao;
	
	@Autowired
	private TPrdAlgmntDAO tPrdAlgmntDao;
	
	@Autowired
	private CustomerAlignmentEntityAssembler customerAlignmentEntityAssembler;
	 
	@Autowired
	private CustomerProductAlignmentModelAssembler customerProductAlignmentModelAssembler;
	
	@Autowired
	private CustomerProductAlignmentEntityAssembler customerProductAlignmentEntityAssembler;
	

	
	@Autowired
	private ProductAlignmentEntityAssembler productAlignmentEntityAssembler;
	
	/** The appliedTemplate */
	/*@Value("${opserv.sp.extAttr.appliedTemplate}")
	private Character AppliedTemplate;*/
	
	@Value("#{systemProperties['opserv.sp.extAttr.applyTemplate'] ?: 'N'}")
	private Character ApplyTemplate;
	
	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(CustomerProductAlignmentDAOServiceImpl.class);
	
	/**
	 *  @Method getAllCustomerProducts - This method is to fetch the all
	 *        Customer Products Alignment dts By Sales Position
	 * @param salesPos
	 * @param userDetails
	 * @return List<CustomerProductAlignment> - list of the Customer Product Alignment Details
	 * @throws AlignmentServiceException
	 */
	@Override
	public List<CustomerProductAlignment> getAllCustomerProducts(
			Customer customer, SalesPosition salesPos, UserDetails userDetails)
			throws OpservDataAccessException {
		List<CustomerProductAlignment> custProdAlgModelList = new ArrayList<CustomerProductAlignment>();
		try {
			boolean flag = false;

			if (null != customer && null != customer.getId()) {
				flag = true;
			} else {
				flag = false;
			}
			List<TCustPrdAlgmnt> tCustPrdAlgmntList = tCustPrdAlgmntDAO
					.findCustomerProductsBySalePosId(customer.getId(),
							salesPos.getId(), 0, -1, userDetails.getTenantId(),
							flag);
			

			if (null != tCustPrdAlgmntList && tCustPrdAlgmntList.size() > 0) {
				custProdAlgModelList = customerProductAlignmentModelAssembler
						.convertTCustPrdAlgToCustProdAlgModel(tCustPrdAlgmntList,flag);
			}
			
			if(flag){
				LOGGER.info("*****************TO FETCH CUSTOMER PRODUCT ALIGNMENTS EXTENDED ATTRIBUTES STARTED*****************");
				if(null!=custProdAlgModelList && custProdAlgModelList.size()>0){
					TSalesPos tsalespos = tSalesPosDAO.findSalesPositionBySalesPosId(salesPos.getId(),  userDetails.getTenantId());
					Alignment alignment = new Alignment();
					alignment.setId(tsalespos.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getAlgmntId());
					SalesTeam st = new SalesTeam();
					BusinessUnit bu = new BusinessUnit();
					bu.setId(tsalespos.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getBussUnitId());
					st.setId(tsalespos.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getSalesTeamId());
					st.setBusinessUnit(bu);
					alignment.setSalesTeam(st);
					
					if (ApplyTemplate != null
							&& ApplyTemplate.equals(CommonConstants.CHAR_YES)) {					
				LOGGER.info("*****************FETCHING CUSTOMER ALIGNMENTS EXTENDED ATTRIBUTES WITH TEMPLATE*****************");
					
					EntityTemplate entityTemplate = templateAlignmentDAOService.getEntityTemplate(EntityType.SALES_TEAM_CUSTOMER_PRODUCT, alignment, userDetails);
					
						for (CustomerProductAlignment custPrdAlignment : custProdAlgModelList) {
		
							custPrdAlignment.setExtdAttributes(getExtAttrDetails(
									alignment, entityTemplate, userDetails,
									custPrdAlignment.getCustomer().getId().intValue(),
									custPrdAlignment.getProduct().getId()));
						}
					}
					else if(ApplyTemplate == null || ApplyTemplate.equals(CommonConstants.CHAR_NO)){
						LOGGER.info("*****************FETCHING CUSTOMER ALIGNMENTS EXTENDED ATTRIBUTES WITHOUT TEMPLATE*****************");
						for (CustomerProductAlignment custPrdAlignment : custProdAlgModelList) {
		
							custPrdAlignment.setExtdAttributes(getExtAttrDetails(
									alignment, userDetails,
									custPrdAlignment.getCustomer().getId().intValue(),
									custPrdAlignment.getProduct().getId()));
						}
					}
						
					}
			}
		} catch (RuntimeException re) {
			LOGGER.error("This issue is occurred while Fetching All Customer Products Alignment Dts By Sales Postion.",re.getMessage());
			throw new OpservDataAccessException(
					"This issue is occurred while Fetching All Customer Products Alignment Dts By Sales Postion.",
					re);
		}
		return custProdAlgModelList;
	}

	/**
	 * Save customer product alignmnets.
	 *
	 * @param customerAlignment the customer alignment
	 * @param productAlignment the product alignment
	 * @param spEntityDetailsToSalesPosModel the sp entity details to sales pos model
	 * @return the list
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	public List<CustomerProductAlignment> saveCustomerProductAlignmnets(
			CustomerAlignment customerAlignment,
			ProductAlignment productAlignment,CustomerProductAlignment srcCustomerProductAlignment)
			throws OpservDataAccessException {
		try {
			TCustPrdAlgmntId tCustPrdAlgmntId = new TCustPrdAlgmntId();
			TCustPrdAlgmnt tCustPrdAlgmnt = new TCustPrdAlgmnt();
			List<TCustPrdAlgmnt> custPrdAlgList = new ArrayList<TCustPrdAlgmnt>();
			
			TCustAlgmnt tCustAlgmnt =customerAlignmentEntityAssembler.mapCustAlignModelToEntity(customerAlignment);
			TPrdAlgmnt tPrdAlgmnt = productAlignmentEntityAssembler
					.convertProdAlgmntModeltoEntity(productAlignment);
			
			tCustPrdAlgmnt.setActiveFlag(ApplicationConstant.FLAG_NO);
			tCustPrdAlgmnt.setCreatedBy(tCustAlgmnt.getCreatedBy());
			tCustPrdAlgmnt.setCreateDt(DateUtil.getCurrentDate());
			tCustPrdAlgmnt.setTenantId(tCustAlgmnt.getTenantId());
			tCustPrdAlgmntId.setCustAlgmntId(tCustAlgmnt.getCustAlgmntId());
			tCustPrdAlgmntId.setPrdAlgmntId(tPrdAlgmnt.getPrdAlgmntId());
			tCustPrdAlgmnt.setTCustPrdAlgmntId(tCustPrdAlgmntId);
			tCustPrdAlgmnt.setCustId(tCustAlgmnt.getTCust().getCustId());
			tCustPrdAlgmnt.setPrdId(tPrdAlgmnt.gettPrd().getPrdId());
			tCustPrdAlgmnt.setCompFlag(srcCustomerProductAlignment.isCompAvailable() == true ? CommonConstants.ACTIVE_Y : CommonConstants.ACTIVE_N);
			tCustPrdAlgmnt.setPrdFlag(srcCustomerProductAlignment.getPrdFlag());
			tCustPrdAlgmnt.setAllocPct(srcCustomerProductAlignment.getAllocationPercentage());
			tCustPrdAlgmnt.setEffStartDt(srcCustomerProductAlignment.getStartDate());
			tCustPrdAlgmnt.setEffEndDt(srcCustomerProductAlignment.getEndDate());
			
			TCustPrdAlgmnt tcuPrdAlgmnt = tCustPrdAlgmntDAO
					.createTCustPrdAlgmnt(tCustPrdAlgmnt);
			
			custPrdAlgList.add(tcuPrdAlgmnt);
			List<CustomerProductAlignment> savedCustPrdAlgmnt = customerProductAlignmentModelAssembler
					.convertTCustPrdAlgToCustProdAlgModel(custPrdAlgList,true);
			
			return savedCustPrdAlgmnt;
			
		} catch (RuntimeException rExp){
			LOGGER.error("This issue occured while saving Customer Product Alignments");
			throw new OpservDataAccessException("This issue occured while saving Customer Product Alignments", rExp);
		}
	}
	/**
	 * Update cust prod algnmt.
	 *
	 * @param customerAlignment the customer alignment
	 * @param userDetails the user details
	 * @return the list
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public List<CustomerProductAlignment> updateCustProdAlgnmt(
			CustomerAlignment customerAlignment, UserDetails userDetails)
			throws OpservDataAccessException {

		List<TCustPrdAlgmnt> tCustPrdAlgmntList = new ArrayList<TCustPrdAlgmnt>();
		List<CustomerProductAlignment> custPrdAlgmntList = new ArrayList<CustomerProductAlignment>();
		try {
				List<TCustPrdAlgmnt> tCustPrdAlgmnt = tCustPrdAlgmntDAO.fetchTCustPrdAlgmntByCustALId(customerAlignment.getId(),CommonConstants.ACTIVE_Y);	

				if (null != tCustPrdAlgmnt && !tCustPrdAlgmnt.isEmpty()) {
					for( TCustPrdAlgmnt custPrdAlgmnt : tCustPrdAlgmnt){
//						int statusId = StatusType.PENDING_SUBMISSION.getId();
						// tCustPrdAlgmnt.setStatusId(statusId);
						custPrdAlgmnt.setUpdateDt(DateUtil.getCurrentDate());
						custPrdAlgmnt.setUpdatedBy(userDetails.getUserId());
						custPrdAlgmnt.setEffEndDt(DateUtil.getCurrentDate());
						TCustPrdAlgmnt updateTCustPrdAlgmnt = tCustPrdAlgmntDAO
								.updateTCustPrdAlgmnt(custPrdAlgmnt);
						tCustPrdAlgmntList.add(updateTCustPrdAlgmnt);
					}
				}

			custPrdAlgmntList = customerProductAlignmentModelAssembler
					.convertTCustPrdAlgToCustProdAlgModel(tCustPrdAlgmntList,true);

		} catch (RuntimeException rExp) {
			LOGGER.error("This issue occured while updating Customer Product Alignments");
			throw new OpservDataAccessException(
					"This issue occured while updating Customer Product Alignments",
					rExp);
		}
		return custPrdAlgmntList;
	}

	
	//changed from deepthi's method
	/**
	 * TO get ExtAttr Details
	 * 
	 * @param entityType
	 * @param alignment
	 * @param userDetails
	 * @param custId
	 * @return Map<Integer,List<ExtdAttribue>>
	 */
	public List<ExtdAttribue> getExtAttrDetails(Alignment alignment, EntityTemplate entityTemplate,
			UserDetails userDetails, Integer custId, Long prdId)
			throws OpservDataAccessException {
		
		Long alId = alignment.getId() ;
		Long buId = alignment.getSalesTeam().getBusinessUnit().getId();
		Long stId = alignment.getSalesTeam().getId();
		List<ExtdAttribue> extdAttribuesList = null;
		
		List<Long> prdIdList = new ArrayList<Long>();
		prdIdList.add(prdId);

		try {
			List<TCustPrdSalesTeam> tcustPrdSalesTeamList = tCustPrdSalesTeamDAO.getSalesTeamByCustIdAlBuStPrdId(custId,alId,stId,buId,prdIdList,userDetails.getTenantId());
			for (TCustPrdSalesTeam tCustPrdSalesTeam : tcustPrdSalesTeamList) {
			 extdAttribuesList = new ArrayList<ExtdAttribue>();

				for (int i = 0; i < entityTemplate.getGroups().size(); i++) {
					List<AttributeDefinition> attributeDefinitionsList = entityTemplate
							.getGroups().get(i).getAttrDefinitions();
					for (AttributeDefinition attributeDefinition : attributeDefinitionsList) {
						attributeDefinition.setEntityId(EntityType.SALES_TEAM_CUSTOMER_PRODUCT.getId());
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
						Method m = ReflectionHelper.getMethod(TCustPrdSalesTeam.class,
								"getAttr" + attrNum);
						String attrValue = (String) ReflectionHelper.getValue(
								m, tCustPrdSalesTeam);
						
						//extr attr check changes
							extdAttribue.setId(attributeDefinition.getId());
							extdAttribue.setName(attributeDefinition.getName());
							extdAttribue.setDefinition(attributeDefinition);
							if (attrValue != null
								&& !attrValue.toString().trim()
										.equals(CommonConstants.EMPTY)){
							extdAttribue.setValue(attrValue);
							}
							else{
								extdAttribue.setValue(CommonConstants.NUL);
							}
							if(attributeDefinition.getEntityId() == entityTemplate.getEntity().getId() ){
							extdAttribuesList.add(extdAttribue);
							}
					}

				}
				//CustExtAttrList.put(tcustSalesTeam.gettCust().getCustId(), extdAttribuesList);
				//custPrdExtAttrList.add
		}
				return extdAttribuesList;
		} catch (RuntimeException e) {
			LOGGER.error("Error during fetching of Extended Attribute For Customer Alignment by customer Id");
			throw new OpservDataAccessException(
					"Error during fetching of Extended Attribute For Customer Alignment by customer Id",
					e);
		}
		

	}
	
	/**
	 * TO get ExtAttr Details
	 * 
	 * @param alignment
	 * @param userDetails
	 * @param custId
	 * @return Map<Integer,List<ExtdAttribue>>
	 */
	public List<ExtdAttribue> getExtAttrDetails(Alignment alignment,
			UserDetails userDetails, Integer custId, Long prdId)
			throws OpservDataAccessException {
		
		Long alId = alignment.getId() ;
		Long buId = alignment.getSalesTeam().getBusinessUnit().getId();
		Long stId = alignment.getSalesTeam().getId();
		List<ExtdAttribue> extdAttribuesList = null;
		
		List<Long> prdIdList = new ArrayList<Long>();
		prdIdList.add(prdId);

		try {
			List<TCustPrdSalesTeam> tcustPrdSalesTeamList = tCustPrdSalesTeamDAO.getSalesTeamByCustIdAlBuStPrdId(custId,alId,stId,buId,prdIdList,userDetails.getTenantId()); 
				if(tcustPrdSalesTeamList != null && !tcustPrdSalesTeamList.isEmpty()){
					for (TCustPrdSalesTeam tCustPrdSalesTeam : tcustPrdSalesTeamList) {
				
				int no_of_columns = CommonConstants.custSTExtAttrCount;
				
				List<String> columnList = new ArrayList<String>();
				
				for (int i = 1; i <= no_of_columns; i++) {
					columnList.add("attr_" + i);
				}
				
				 extdAttribuesList = new ArrayList<ExtdAttribue>();
						for (String coulmnName : columnList) {
							if (!coulmnName.startsWith("attr_")) {
								continue;
							}

							String attrNum = coulmnName.substring(5);
						
							ExtdAttribue extdAttribue = new ExtdAttribue();
							Method m = ReflectionHelper.getMethod(TCustPrdSalesTeam.class,
									"getAttr" + attrNum);
							String attrValue = (String) ReflectionHelper.getValue(
									m, tCustPrdSalesTeam);
							
							LOGGER.info("*****************ATTR NAME======*****************" + coulmnName);
							
							LOGGER.info("*****************ATTR VALUE======*****************" + attrValue);
							extdAttribue.setName(coulmnName);
						if (attrValue != null
							&& !attrValue.toString().trim()
									.equals(CommonConstants.EMPTY)){
						extdAttribue.setValue(attrValue);
						}
						else{
							extdAttribue.setValue(CommonConstants.NUL);
						}
							extdAttribuesList.add(extdAttribue);
						}	
					}
		} 
				return extdAttribuesList;
		}catch (RuntimeException e) {
			LOGGER.error("Error during fetching of Extended Attribute For Customer Alignment by customer Id");
			throw new OpservDataAccessException(
					"Error during fetching of Extended Attribute For Customer Alignment by customer Id",
					e);
		}
			

	}
     
	/**
	 * TO update customer Product Alignment details 
	 * 
	 * @param custPrdAlignList customer product alignment lsit
	 * @param userDetails
	 * @return List<CustomerProductAlignment> : customer product alignment lsit
	 */
	@Override
	public List<CustomerProductAlignment> updateCustProdAlgnmtAllocPerc(List<CustomerProductAlignment>
			custPrdAlignList, UserDetails userDetails)
			throws OpservDataAccessException {
		try{
			List<TCustPrdAlgmnt> tCustPrdAlgmntList = new ArrayList<TCustPrdAlgmnt>();
			for(CustomerProductAlignment custPrdAlign : custPrdAlignList){
				//since we cannot get the custalignId and prdAlignId from the customerproductalignment object
				Long tCustAlgmntId = tCustAlgmntDao.fetchCustAlgmntIdfromCustIdAndSPId((Integer)custPrdAlign.getCustomer().getId().intValue(), custPrdAlign.getSalesPosition().getId());
				Long tPrdAlgmntId = tPrdAlgmntDao.findTPrdAlgmntByTPrdAndSPId(custPrdAlign.getProduct().getId(), custPrdAlign.getSalesPosition().getId());
				
				//todo: hit db and get the whole object if the whole object is not given(with the custalignId and PrdASlignId as input)
				TCustPrdAlgmnt tCustPrdAlgmnt =  tCustPrdAlgmntDAO
						.updateTCustPrdAlgmnt(customerProductAlignmentEntityAssembler.converCustProdAlgModelToTCustPrdAlg(custPrdAlign,tCustAlgmntId,tPrdAlgmntId));
				tCustPrdAlgmntList.add(tCustPrdAlgmnt);
			}
				return customerProductAlignmentModelAssembler
						.convertTCustPrdAlgToCustProdAlgModel(tCustPrdAlgmntList,true);
		}catch (RuntimeException re) {
			LOGGER.error("Error while updating Customer product alignment");
			throw new OpservDataAccessException(
					"Error while updating Customer product alignment", re);
		}
	}
    
	/**
	 * TO insert customer Product Alignment details 
	 * 
	 * @param custPrdAlignList customer product alignment lsit
	 * @param userDetails
	 * @return List<CustomerProductAlignment> : customer product alignment lsit
	 */
	@Override
	public List<CustomerProductAlignment> insertCustomerProductAlignmnets(
			List<CustomerProductAlignment> custProdAlignmentList)
			throws OpservDataAccessException {
		try {
			List<TCustPrdAlgmnt> tCustPrdAlgmntList = new ArrayList<TCustPrdAlgmnt>();
			
			for(CustomerProductAlignment custPrdAlign : custProdAlignmentList){
				//since we cannot get the custalignId and prdAlignId from the CustomerProductAlignment object
				Long tCustAlgmntId = tCustAlgmntDao.fetchCustAlgmntIdfromCustIdAndSPId((Integer)custPrdAlign.getCustomer().getId().intValue(), custPrdAlign.getSalesPosition().getId());
				Long tPrdAlgmntId = tPrdAlgmntDao.findTPrdAlgmntByTPrdAndSPId(custPrdAlign.getProduct().getId(), custPrdAlign.getSalesPosition().getId());
				
				TCustPrdAlgmnt tCustPrdAlgmnt =  tCustPrdAlgmntDAO
						.createTCustPrdAlgmnt(customerProductAlignmentEntityAssembler.converCustProdAlgModelToTCustPrdAlg(custPrdAlign,tCustAlgmntId,tPrdAlgmntId));
				tCustPrdAlgmntList.add(tCustPrdAlgmnt);
			}
				
				return customerProductAlignmentModelAssembler
						.convertTCustPrdAlgToCustProdAlgModel(tCustPrdAlgmntList,true);
		} catch (RuntimeException re) {
			LOGGER.error("Error while inserting records into CustomerProductAlignmnet");
			throw new OpservDataAccessException(
					"Error while inserting records into CustomerProductAlignmnet", re);
		}
	}

	/**
	 * update customer Product Alignment For CR
	 * @param custALId
	 * @param prdALId
	 * @param userDetails
	 */
	@Override
	public void updateCustPrdAlgmntForCR(Long custALId, Long prdALId,Character activeFlag,
			UserDetails userDetails) throws OpservDataAccessException {
		
		try {
			TCustPrdAlgmntId tCustPrdAlgmntId = new TCustPrdAlgmntId();
			tCustPrdAlgmntId.setCustAlgmntId(custALId);
			tCustPrdAlgmntId.setPrdAlgmntId(prdALId);
			TCustPrdAlgmnt tCustPrdAlgmnt = tCustPrdAlgmntDAO
					.findTCustPrdAlgmntById(tCustPrdAlgmntId);
			if(tCustPrdAlgmnt != null){
				
			tCustPrdAlgmnt.setActiveFlag(activeFlag);
			tCustPrdAlgmnt.setUpdateDt(DateUtil.getCurrentDate());
			tCustPrdAlgmnt.setUpdatedBy(userDetails.getUserId());
			tCustPrdAlgmntDAO.updateTCustPrdAlgmnt(tCustPrdAlgmnt);
			}
		} catch (RuntimeException e) {
			throw new OpservDataAccessException(
					"Error while updating TCustPrdAlgmnt", e);
		}
	}

	@Override
	public List<CustomerProductAlignment> fetchTCustPrdAlgmnt(Long custALId,
			UserDetails userDetails,Character activeFlag) throws OpservDataAccessException {
		
		try {
			List<TCustPrdAlgmnt> tCustPrdAlgmnt = tCustPrdAlgmntDAO.fetchTCustPrdAlgmntByCustALId(custALId,activeFlag);

			List<CustomerProductAlignment> customerProductAlignment = null;
			if (tCustPrdAlgmnt != null) {
				customerProductAlignment = customerProductAlignmentModelAssembler
						.convertTCustPrdAlgToCustProdAlgModel(tCustPrdAlgmnt,
								false);
			}

			return customerProductAlignment;
		} catch (RuntimeException e) {
			throw new OpservDataAccessException(
					"Error during fetching Customer Product Alignment by customer Id", e);
		}
	}

	/**
	 * fetch TCustPrdAlgmnt by Customer ALignment Id's
	 * @param custIdList
	 * @param tenantId
	 * @return CustomerProductAlignment
	 */
	@Override
	public List<CustomerProductAlignment> populateCustPrdAlgmntByCustAlId(
			List<Long> custALIdList, Short tenantId) throws OpservDataAccessException{
		try {
			List<TCustPrdAlgmnt> tCustPrdAlgmnts = tCustPrdAlgmntDAO.populateCustPrdAlgmntByCustAlId(custALIdList, tenantId);

			List<CustomerProductAlignment> customerProductAlignment = null;
			if (tCustPrdAlgmnts != null && !tCustPrdAlgmnts.isEmpty()) {
				customerProductAlignment = customerProductAlignmentModelAssembler
						.convertTCustPrdAlgToCustProdAlgModel(tCustPrdAlgmnts,
								false);
			}

			return customerProductAlignment;
		} catch (RuntimeException e) {
			throw new OpservDataAccessException(
					"Error during fetching Customer Product Alignment by customer Id", e);
		}
	}	
}
