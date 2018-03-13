package com.cognizant.opserv.sp.service.common;

import java.util.Map;

import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.model.Country;
import com.cognizant.opserv.sp.model.CountryDivision;
import com.cognizant.opserv.sp.model.auth.UserDetails;

/**
 * ****************************************************************************.
 *
 * @class CommonService contains methods to fetch common/static data
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 20/04/2016
 * ***************************************************************************
 */
public interface CommonService {

	

	/**
	 * Gets the all customer types.
	 * 
	 * @method getAllCustomerTypes
	 * @param tenantId the tenant id
	 * @param locale the locale
	 * @return the all customer types
	 * @throws CustomerServiceException the customer service exception
	 */
	public Map<Integer,String>  getAllCustomerTypes(Short tenantId, String locale)throws CustomerServiceException;
	
	/**
	 * Gets the default country.
	 *
	 * @param userDetails the user details
	 * @return the default country
	 */
	public Country getDefaultCountry(UserDetails userDetails); 
	
	/**
	 * Gets the lowest country division.
	 *
	 * @param userDetails the user details
	 * @return the lowest country division
	 */
	public CountryDivision getLowestCountryDivision(UserDetails userDetails); 
		
}
