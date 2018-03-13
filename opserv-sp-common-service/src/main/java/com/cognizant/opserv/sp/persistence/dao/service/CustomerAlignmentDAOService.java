package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;
import java.util.Map;

import com.cognizant.opserv.sp.core.entity.TChngreqOffline;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessReason;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.EntityTemplate;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.attrb.ExtdAttribue;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class CustomerAlignmentDAOService contains all the Dao services for customer affiliation
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface CustomerAlignmentDAOService {
	
	/**
	 * 
	 * @method getAllCustomerAlignmentsBySalesPosition
	 * @param salesPosition
	 * @param userDetails
	 * @return
	 * @throws CustomerServiceException 
	 * @throws AlignmentServiceException
	 */
	List<CustomerAlignment> getAllCustomerAlignmentsBySalesPosition(
			SalesPosition salesPosition, UserDetails userDetails)
			throws OpservDataAccessException;
	
	/**
	 * Gets the customer alignment.
	 *
	 * @param customerId the customer id
	 * @param userDetails the user details
	 * @return the customer alignment
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	List<CustomerAlignment> getCustomerAlignment(long customerId,UserDetails userDetails) throws OpservDataAccessException;

	/**
	 * Gets the customer alignment fr buid.
	 *
	 * @param customerId the customer id
	 * @param buId the bu id
	 * @param userDetails the user details
	 * @return the customer alignment fr buid
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	List<CustomerAlignment> getCustomerAlignmentFrBuid(long customerId,long buId, UserDetails userDetails)throws OpservDataAccessException;
	
	/**
	 * Update customer alignment change request.
	 *
	 * @param customerAlignment the customer alignment
	 * @throws OpservDataAccessException the opserv data access exception
	 * @method updateCustomerAlignment
	 */
	void updateCustomerAlignmentChangeRequest(CustomerAlignment customerAlignment) throws OpservDataAccessException;


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
	 CustomerAlignment saveCustomerAlignments(
			CustomerAlignment newCustAlgn, UserDetails userDetails,
			Boolean custIdGeoAlgmntFlgMap, CustomerAlignment orgCustAlgn) throws OpservDataAccessException;
	 
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
	 CustomerAlignment updateCustomerAlignments(
				CustomerAlignment orgCustAlgn,UserDetails userDetails,Integer statusId) throws OpservDataAccessException;
 
	 /**
	  * getExtAttrDetails - To get Extended Attribute for Customer Sales Team
	  * @param entityType
	  * @param alignment
	  * @param userDetails
	  * @param custId
	  * @return  Map<Integer, List<ExtdAttribue>>
	  */
	 Map<Integer, List<ExtdAttribue>> getCustomerAlignmentExtAttrDetails(
			 EntityTemplate entityTemplate, Alignment alignment,
				UserDetails userDetails, List<Integer> custId)throws OpservDataAccessException;
				
	/**
	 * Gets the customer alignment fr sp.
	 *
	 * @param customerId the customer id
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return the customer alignment fr sp
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	public List<CustomerAlignment> getCustomerAlignmentFrSP(long customerId,
			SalesPosition salesPosition, UserDetails userDetails)
			throws OpservDataAccessException;
	
	
	void updateGeoCustAlignmentIdBySalesPostion(SalesPosition salesPos,UserDetails userDetails) throws OpservDataAccessException;

	/**
	 * Gets the Customer count for sales position.
	 *
	 * @param salesPosition the salesPosition
	 * @param userDetails the user details
	 * @return the customer count for sales position
	 * @throws AlignmentServiceException 
	 */
	long getCustomerCountForSalesPosition(SalesPosition salesPosition,UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Gets the geo algd customer count for sales position.
	 *
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return the geo algd customer count for sales position
	 * @throws AlignmentServiceException the alignment service exception
	 */
	long getGeoAlgdCustomerCountForSalesPosition(SalesPosition salesPosition,UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Gets the non geo algd customer count for sales position.
	 *
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return the non geo algd customer count for sales position
	 * @throws AlignmentServiceException the alignment service exception
	 */
	long getNonGeoAlgdCustomerCountForSalesPosition(SalesPosition salesPosition,UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * This method is used to save data into TempChngReq
	 * @param orgCustAlgn
	 * @param newCustAlgn
	 * @param changeRequest
	 * @param comments
	 * @param userDetails
	 * @param pushPull
	 * @param businessReason 
	 * @return TTempChngreq
	 */
	TChngreqOffline saveIntoCROffline(CustomerAlignment orgCustAlgn,
			CustomerAlignment newCustAlgn, ChangeRequest changeRequest,
			String comments, UserDetails userDetails, String pushPull,
			BusinessReason businessReason,  List<CustomerProductAlignment> oldCustomerProductAlignments, List<CustomerProductAlignment> newCustomerProductAlignments)throws OpservDataAccessException;
	
	/**
	 * Edits the ext attr fr cust sales team.
	 *
	 * @param newCustomerAlignment the new customer alignment
	 * @param userDetails the user details
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	void editExtAttrFrCustSalesTeam(SalesPosition salesPosition, CustomerAlignment newCustomerAlignment,
			UserDetails userDetails)
			throws OpservDataAccessException;
	
	/**
	 * Edits the ext attr fr cust prd sales team.
	 *
	 * @param newCustomerProductAlignments the new customer product alignments
	 * @param userDetails the user details
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	void editExtAttrFrCustPrdSalesTeam(List<CustomerProductAlignment> newCustomerProductAlignments,
			UserDetails userDetails)
			throws OpservDataAccessException;

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
	public boolean checkCustCRStatusInAlgmnt(Integer custId, Long alId, Long buId, Long stId, UserDetails userDetails)throws OpservDataAccessException;
	
	/**
	 * Gets the Customer count for sales position.
	 *
	 * @param salesPosition the salesPositions
	 * @param userDetails the user details
	 * @return the customer count for sales position
	 * @throws AlignmentServiceException 
	 */
	long getCustomerCountForSalesPositions(List<SalesPosition> salesPositions, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Gets the geo algd customer count for sales position.
	 *
	 * @param salesPosition the sales positions
	 * @param userDetails the user details
	 * @return the geo algd customer count for sales position
	 * @throws AlignmentServiceException the alignment service exception
	 */
	long getGeoAlgdCustomerCountForSalesPositions(List<SalesPosition> salesPositions, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Gets the non geo algd customer count for sales position.
	 *
	 * @param salesPosition the sales positions
	 * @param userDetails the user details
	 * @return the non geo algd customer count for sales position
	 * @throws AlignmentServiceException the alignment service exception
	 */
	long getNonGeoAlgdCustomerCountForSalesPositions(List<SalesPosition> salesPositions, UserDetails userDetails) throws OpservDataAccessException;

	
	/**
	 * Gets the all customer alignments by cust algnmt id.
	 *
	 * @param custAlgId the cust alg id
	 * @param userDetails the user details
	 * @return the all customer alignments by cust algnmt id
	 */
	CustomerAlignment getAllCustomerAlignmentsByCustAlgnmtId(
			long custAlgId, UserDetails userDetails)throws OpservDataAccessException;

	/**
	 * Fetch cust al fr cust.
	 *
	 * @param salesPosition the sales position
	 * @param affCustId the aff cust id
	 * @param userDetails the user details
	 * @return the list
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	List<CustomerAlignment> fetchCustAlFrCust(SalesPosition salesPosition,List<Integer> affCustId,
			UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * @param custId
	 * @param spIdList
	 * @param userDetails
	 * @return
	 * @throws OpservDataAccessException
	 */
	List<SalesPosition> fetchAssignedCustForSp(Long custId,List<Long> spIdList,UserDetails userDetails)throws OpservDataAccessException;
	
	/**
	 * Edit customer alignment.
	 * 
	 * @param newCustomerAlignment
	 *            the new customer alignment
	 * @param userDetails
	 *            the user details
	 */
	void editCustAlgmnt(CustomerAlignment newCustomerAlignment,UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * @param customerAlgmnt
	 * @param userDetails
	 * @return boolean
	 * @throws OpservDataAccessException
	 */
	boolean lockCustomerAlignment(Long CustAlgmntId,Integer statusId, Integer userId) throws OpservDataAccessException;
	
	/**
	 * @param customerAlgmnt
	 * @param userDetails
	 * @return boolean
	 * @throws OpservDataAccessException
	 */
	boolean unlockCustomerAlignment(Long CustAlgmntId,Integer statusId, Integer userId) throws OpservDataAccessException;
	
	/**
	 * @param customerAlgmnt
	 * @param userDetails
	 * @return boolean
	 * @throws OpservDataAccessException
	 */
	boolean unlockCustomerAlignments(List<Long> CustAlgmntId,Integer statusId, Integer userId) throws OpservDataAccessException;
	
}