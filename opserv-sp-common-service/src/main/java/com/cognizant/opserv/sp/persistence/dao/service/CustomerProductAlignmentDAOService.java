package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.ProductAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class CustomerProductAlignmentDAOService contains all Dao the services for customer and product  alignment
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface CustomerProductAlignmentDAOService {
	
	/**
	 * @Method getAllCustomerProducts - This method is to fetch the
	 *         Products Alignment dts By Sales Position
	 * @param salesPos
	 * @param userDetails
	 * @return List<CustomerProductAlignment> - list of the Customer ProductAlignment Details
	 * @throws AlignmentServiceException
	 */
	List<CustomerProductAlignment> getAllCustomerProducts(
			Customer customer, SalesPosition salesPos, UserDetails userDetails)
			throws OpservDataAccessException;

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
			ProductAlignment productAlignment,CustomerProductAlignment srcCustomerProductAlignment) throws OpservDataAccessException;
	
	/**
	 * Update cust prod algnmt.
	 *
	 * @param customerAlignment the customer alignment
	 * @param userDetails the user details
	 * @return the list
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	List<CustomerProductAlignment> updateCustProdAlgnmt(
			CustomerAlignment customerAlignment, UserDetails userDetails)
			throws OpservDataAccessException;
	
	/**
	 * TO update customer Product Alignment details 
	 * 
	 * @param custPrdAlignList customer product alignment lsit
	 * @param userDetails
	 * @return List<CustomerProductAlignment> : customer product alignment lsit
	 */
	List<CustomerProductAlignment> updateCustProdAlgnmtAllocPerc(List<CustomerProductAlignment> custPrdAlignList, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * TO insert customer Product Alignment details 
	 * 
	 * @param custPrdAlignList customer product alignment lsit
	 * @param userDetails
	 * @return List<CustomerProductAlignment> : customer product alignment lsit
	 */
	List<CustomerProductAlignment> insertCustomerProductAlignmnets(
			List<CustomerProductAlignment> custProdAlignmentList) throws OpservDataAccessException;
	
	/**
	 * update customer Product Alignment For CR
	 * @param custALId
	 * @param prdALId
	 * @param activeFlag
	 * @param userDetails
	 * @throws OpservDataAccessException
	 */
	void updateCustPrdAlgmntForCR(Long custALId, Long prdALId,Character activeFlag, UserDetails userDetails)
			throws OpservDataAccessException;
	
	/**
	 * 
	 * fetch TCustPrdAlgmnt
	 * @param custId
	 * @param userDetails
	 * @return CustomerProductAlignment
	 */
	List<CustomerProductAlignment> fetchTCustPrdAlgmnt(Long custId,UserDetails userDetails,Character activeFlag) throws OpservDataAccessException;
	

	/**
	 * 
	 * fetch TCustPrdAlgmnt by Customer ALignment Id's
	 * @param custIdList
	 * @param tenantId
	 * @return CustomerProductAlignment
	 */
	List<CustomerProductAlignment> populateCustPrdAlgmntByCustAlId(
			List<Long> custIdList, Short tenantId)
			throws OpservDataAccessException;

	
}
