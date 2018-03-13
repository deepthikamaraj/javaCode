package com.cognizant.opserv.sp.service.salesorg;

import java.util.List;

import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.common.ISearchCriteria;

// TODO: Auto-generated Javadoc
/**
 * The Interface SalesPositionSetupService.
 */
public interface SalesPositionSetupService {
		
	/**
	 * Creates the sales position.
	 *
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return the sales position
	 * @throws SalesPositionServiceException the sales position service exception
	 */
	SalesPosition createSalesPosition(SalesPosition salesPosition,UserDetails userDetails) throws SalesPositionServiceException;
	
	/**
	 * Update sales position.
	 *
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @throws SalesPositionServiceException the sales position service exception
	 */
	void updateSalesPosition(SalesPosition salesPosition,UserDetails userDetails) throws SalesPositionServiceException;
	
	/**
	 * Inactive sales position.
	 *
	 * @param salePositionId the sale position id
	 * @param userDetails the user details
	 * @throws SalesPositionServiceException the sales position service exception
	 */
	void inactiveSalesPosition(Long salePositionId,UserDetails userDetails) throws SalesPositionServiceException;
		
	/**
	 * Gets the sales position details.
	 *
	 * @param salePositionId the sale position id
	 * @param userDetails the user details
	 * @return the sales position details
	 * @throws SalesPositionServiceException the sales position service exception
	 */
	SalesPosition getSalesPositionDetails(Long salePositionId,UserDetails userDetails) throws SalesPositionServiceException;
	
	/**
	 * Fetch sales positions.
	 *
	 * @param searchCriteria the search criteria
	 * @param userDetails the user details
	 * @return the list
	 * @throws SalesPositionServiceException the sales position service exception
	 */
	List<SalesPosition> fetchSalesPositions(ISearchCriteria searchCriteria,UserDetails userDetails) throws SalesPositionServiceException;	

}
