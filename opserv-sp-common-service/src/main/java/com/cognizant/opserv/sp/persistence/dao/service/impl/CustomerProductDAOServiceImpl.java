package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.assembler.CustomerProductModelAssembler;
import com.cognizant.opserv.sp.core.dao.TCustPrdAlgmntDAO;
import com.cognizant.opserv.sp.core.dao.TCustPrdSalesDAO;
import com.cognizant.opserv.sp.core.dao.TCustPrdSalesTeamDAO;
import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TCustPrdSales;
import com.cognizant.opserv.sp.core.entity.TPrd;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.CustomerProduct;
import com.cognizant.opserv.sp.model.Product;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerProductDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.TemplateAlignmentDAOService;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
/**
 * ****************************************************************************.
 *
 * @class CustomerProductDAOServiceImpl  contains all the DAO services customer product
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Component
public class CustomerProductDAOServiceImpl implements CustomerProductDAOService {
	
	@Autowired
	private TCustPrdSalesDAO tCustProdDao;
	
	@Autowired
	private TCustPrdAlgmntDAO tCustPrdAlgmntDAO;
	
	@Autowired
	private TCustPrdSalesTeamDAO tCustPrdSalesTeamDao;
	
	@Autowired
	private TemplateAlignmentDAOService templateAlignmentDAOService;
	
	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(CustomerProductDAOServiceImpl.class);
	
	@Autowired
	 private CustomerProductModelAssembler customerProductModelAssembler;
	
	
	
	/**
	 * Gets the products of customer.
	 * @method getProductByCustomerId
	 * @param customerId the customer id
	 * @param userDetails the user details
	 * @return the products of customer
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@SuppressWarnings({ "null", "static-access" })
	@Override
	public List<Product> getProductByCustomerId(Long CustomerId, UserDetails userDetails) throws OpservDataAccessException{
		
		
		List<TPrd> tPrdList = null;
		List<Product> productList = null;
		
		try{
			SearchFilter<TCust> searchFilter = new SearchFilter<TCust>();
			TCust tCust = new TCust();
			tCust.setCustId(CustomerId.intValue());
			tCust.setTenantId(userDetails.getTenantId());
			searchFilter.setEntityClass(tCust);
			tPrdList = tCustProdDao.getTPrdsByTCust(searchFilter);
		}
		catch(RuntimeException e)
		{
			LOGGER.error("Error while getting products for given customer Id",e.getMessage());
			throw new OpservDataAccessException("Error while getting products for given customer Id", e);
		}
		
		if(tPrdList != null && !tPrdList.isEmpty()){
		productList =  customerProductModelAssembler.convertTCustPrdToProduct(tPrdList);
		}
		 
		return productList;
	}
	
	/**
	 * Gets the customerproducts of customer.
	 * @method getCustomerProductByCustomerId
	 * @param customerIdList the customer id list
	 * @param userDetails the user details
	 * @return the customerproducts of customer
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@SuppressWarnings("static-access")
	@Override
	public List<CustomerProduct> getCustomerProductByCustomerId(List<Long> customerIdList, UserDetails userDetails) throws OpservDataAccessException{
		List<CustomerProduct> customerProductList = null;
		List<TCust> custList = new ArrayList<TCust>();;
		try{
			for(Long customerId : customerIdList){
				TCust tCust = new TCust();
				tCust.setCustId(customerId.intValue());
				custList.add(tCust);
				}
		   //TODO 
			
			List<TCustPrdSales> tcustPrdList = new ArrayList<TCustPrdSales>();
			for(TCust cust : custList){
				for(TCustPrdSales custPrd : tCustProdDao.getTCustPrdsByCustIdList(cust, userDetails.getTenantId())){
					tcustPrdList.add(custPrd);
				}
			}
			
			if(tcustPrdList != null && !tcustPrdList.isEmpty()){
				customerProductList=  new ArrayList<CustomerProduct>();
				customerProductList = customerProductModelAssembler.convertTCustPrdToCustomerProduct(tcustPrdList);
			}
		}
		catch(RuntimeException e)
		{
			//e.printStackTrace();
			LOGGER.error("Error while getting products for given customer Id",e.getMessage());
			throw new OpservDataAccessException("Error while getting products for given customer Id", e);
		} 
		return customerProductList;
			
	}

	/**
	 * Gets the customerproducts of customer.
	 * @method getCustomerProductBySalesPosition
	 * @param salesPosition the salesPosition
	 * @param userDetails the user details
	 * @return the customerproducts of customer
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public List<CustomerProduct> getCustomerProductBySalesPosition(
			SalesPosition salesPosition, UserDetails userDetails)
					throws OpservDataAccessException {
		Long salesPosId = salesPosition.getId();
		Alignment alignment = salesPosition.getAlignmment();
		List<CustomerProduct> custProdList = null;
		CustomerProduct custProd = null;
		//List<String> extAttrList = null;
		/*try{
			
			List<TCustPrdAlgmnt> custPrdAlignList = tCustPrdAlgmntDAO.findCustomerProductListBySalesPosId(salesPosId,userDetails.getTenantId());

			if (null != custPrdAlignList && !custPrdAlignList.isEmpty()) {
				for(TCustPrdAlgmnt tCustPrdAlgnlist : custPrdAlignList){
				custProdList = new ArrayList<CustomerProduct>();
				custProd=  new CustomerProduct();
				custProd  = CustomerProductModelAssembler
						.convertTCustPrdAlignToCustomerProduct(custPrdAlignList);
				TCustPrdSalesTeam tCustPrdSalesTeam = tCustPrdSalesTeamDao.getSalesTeamByCustIdAlBuSt(custProd.getCustomer().getId().intValue(), alignment.getId(), alignment.getSalesTeam().getId(), alignment.getSalesTeam().getBusinessUnit().getId(),userDetails);

						EntityTemplate entityTemplate = templateAlignmentDAOService
								.getEntityTemplate(EntityType.CUSTOMER, alignment, userDetails);
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
								Method m = ReflectionHelper.getMethod(TCustPrdSalesTeam.class,
										"getAttr" + attrNum);
								String attrValue = (String) ReflectionHelper.getValue(
										m, tCustPrdSalesTeam);
								if (attrValue != null
										&& !attrValue.toString().trim()
												.equals(CommonConstants.EMPTY)) {
									extdAttribue.setId(attributeDefinition.getId());
									extdAttribue.setName(attributeDefinition.getName());
									extdAttribue.setDefinition(attributeDefinition);
									extdAttribue.setValue(attrValue);
									extdAttribuesList.add(extdAttribue);
								}
							}

						}
						
						custProd.setExtdAttributes(extdAttribuesList);
						custProdList.add(custProd);
		
				}
			}
					}
		catch(RuntimeException e){
			throw new OpservDataAccessException("Error while getting products for given salesPosition Id", e);
		}*/
		return custProdList;
	}

}
