package com.cognizant.opserv.sp.service.salesorg;

import java.util.List;

import com.cognizant.opserv.sp.exception.BussUnitServiceException;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.common.ISearchCriteria;

/**
 * The Interface BusinessUnitSetupService.
 */
public interface BusinessUnitSetupService {

	/**
	 * Creates the business unit.
	 *
	 * @param businessUnit the business unit
	 * @param userDetails the user details
	 * @return the business unit
	 * @throws BussUnitServiceException the buss unit service exception
	 */
	BusinessUnit createBusinessUnit(BusinessUnit businessUnit,UserDetails userDetails) throws BussUnitServiceException;
	
	/**
	 * Update business unit.
	 *
	 * @param businessUnit the business unit
	 * @param userDetails the user details
	 * @throws BussUnitServiceException the buss unit service exception
	 */
	void updateBusinessUnit(BusinessUnit businessUnit,UserDetails userDetails) throws BussUnitServiceException;
	
	/**
	 * Inactive buss unit.
	 *
	 * @param businessUnitId the business unit id
	 * @param userDetails the user details
	 * @throws BussUnitServiceException the buss unit service exception
	 */
	void inactiveBussUnit(Long businessUnitId,UserDetails userDetails) throws BussUnitServiceException;
	

	/**
	 * Gets the buss unit details.
	 *
	 * @param businessUnitId the business unit id
	 * @param userDetails the user details
	 * @return the buss unit details
	 * @throws BussUnitServiceException the buss unit service exception
	 */
	BusinessUnit getBussUnitDetails(Long businessUnitId,UserDetails userDetails) throws BussUnitServiceException;
	
	/**
	 * Fetch buss units.
	 *
	 * @param searchCriteria the search criteria
	 * @param userDetails the user details
	 * @return the list
	 * @throws BussUnitServiceException the buss unit service exception
	 */
	List<BusinessUnit> fetchBussUnitsBySearchCriteria(ISearchCriteria searchCriteria,UserDetails userDetails) throws BussUnitServiceException;	

}
