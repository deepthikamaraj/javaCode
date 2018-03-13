package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.Map;

import com.cognizant.opserv.sp.model.Country;
import com.cognizant.opserv.sp.model.CountryDivision;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class CommonDAOService contains methods to fetch common/static data
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 20/04/2016
 * ***************************************************************************
 */
public interface CommonDAOService {

	
	/**
	 * Gets the all customer types.
	 *
	 * @param tenantId the tenant id
	 * @param locale the locale
	 * @return the all customer types
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	public Map<Integer,String>  getAllCustomerTypes(Short tenantId, String locale)throws OpservDataAccessException;
	
	/**
	 * Gets the default country.
	 *
	 * @param userDetails the user details
	 * @return the default country
	 */
	public Country getDefaultCountry(UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Gets the lowest country division.
	 *
	 * @param userDetails the user details
	 * @return the lowest country division
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	public CountryDivision getLowestCountryDivision(UserDetails userDetails) throws OpservDataAccessException;
}
