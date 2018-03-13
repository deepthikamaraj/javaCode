package com.cognizant.opserv.sp.service.common;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.model.Country;
import com.cognizant.opserv.sp.model.CountryDivision;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.CommonDAOService;
/**
 * ****************************************************************************.
 *
 * @class CommonServiceImpl contains implementations for CommonService
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 20/04/2016
 * ***************************************************************************
 */
@Service("commonService")
public class CommonServiceImpl implements CommonService{
	
	@Autowired
	private CommonDAOService commonDAOService;
	/**
	 * Gets the all customer types.
	 *
	 * @method getAllCustomerTypes
	 * @param tenantId the tenant id
	 * @param locale the locale
	 * @return the all customer types
	 * @throws CustomerServiceException the customer service exception
	 */
	@Override
	public Map<Integer, String> getAllCustomerTypes(Short tenantId,
			String locale) throws CustomerServiceException {
		return commonDAOService.getAllCustomerTypes(tenantId, locale);
	}
	
	/**
	 * Gets the default country.
	 *
	 * @param userDetails the user details
	 * @return the default country
	 */
	@Override
	public Country getDefaultCountry(UserDetails userDetails) {
		return commonDAOService.getDefaultCountry(userDetails);
	}
	
	/**
	 * Gets the lowest country division.
	 *
	 * @param userDetails the user details
	 * @return the lowest country division
	 */
	@Override
	public CountryDivision getLowestCountryDivision(UserDetails userDetails) {
		return commonDAOService.getLowestCountryDivision(userDetails);
	}

}
