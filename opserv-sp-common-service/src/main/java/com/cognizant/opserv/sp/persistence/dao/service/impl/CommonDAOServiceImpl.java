package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.assembler.CountryDivisionModelAssembler;
import com.cognizant.opserv.sp.assembler.CountryModelAssembler;
import com.cognizant.opserv.sp.core.dao.TAppConfigDAO;
import com.cognizant.opserv.sp.core.dao.TCountryDAO;
import com.cognizant.opserv.sp.core.dao.TCountryDivDAO;
import com.cognizant.opserv.sp.core.dao.TCustTypeDAO;
import com.cognizant.opserv.sp.core.entity.TAppConfig;
import com.cognizant.opserv.sp.core.entity.TCountryDiv;
import com.cognizant.opserv.sp.core.entity.TCustType;
import com.cognizant.opserv.sp.model.Country;
import com.cognizant.opserv.sp.model.CountryDivision;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.CommonDAOService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
@Component("commonDAOService")
public class CommonDAOServiceImpl implements CommonDAOService{
	
	/**
	 * LOGGER
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(CommonDAOServiceImpl.class);
	
	@Autowired
	private TCustTypeDAO tCustTypeDAO;
	
	@Autowired
	private TAppConfigDAO tAppConfigDAO;
	
	@Autowired
	private TCountryDAO tCountryDAO;
	
	@Autowired
	private TCountryDivDAO tCountryDivDAO;

	/**
	 * Gets the all customer types.
	 *
	 * @param tenantId the tenant id
	 * @param locale the locale
	 * @return the all customer types
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public Map<Integer, String> getAllCustomerTypes(Short tenantId, String locale)
			throws OpservDataAccessException {
		
		Map<Integer, String> custmerTypes = null;
		try {
			List<TCustType> custTypes =tCustTypeDAO.findCustTypeByLocale( tenantId,  locale);
			if(null != custTypes && !custTypes.isEmpty()){
				custmerTypes = new HashMap<Integer, String>();
				for (TCustType tCustType : custTypes) {
					custmerTypes.put(tCustType.gettCustTypeId().getCustTypeId(), tCustType.getTypeDesc());
				}
			}
		} catch (RuntimeException e) {
			LOGGER.error( e.getMessage());
			throw new OpservDataAccessException(e.getMessage());
		}
		
		return custmerTypes;
	}
	
	/**
	 * Gets the default country.
	 *
	 * @param userDetails the user details
	 * @return the default country
	 */
	@Override
	public Country getDefaultCountry(UserDetails userDetails)
			throws OpservDataAccessException {
		try{
			List<TAppConfig> tAppConfigList = tAppConfigDAO.getDefaultCountryID(userDetails.getTenantId());
			if(null != tAppConfigList && !tAppConfigList.isEmpty()){
				return CountryModelAssembler.convertTCountryToModel(tCountryDAO.findTCountryById(tAppConfigList.get(0).getDefCountryId())); 
			}
		}
		catch(RuntimeException e){
			LOGGER.error("exception while fetching default country", e);
			throw new OpservDataAccessException("exception while fetching default country", e);
		}
		return null;
	}
	
	/**
	 * Gets the lowest country division.
	 *
	 * @param userDetails the user details
	 * @return the lowest country division
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public CountryDivision getLowestCountryDivision(UserDetails userDetails)
			throws OpservDataAccessException {
		try{
			Country country = getDefaultCountry(userDetails);
			if(null != country){
				List<TCountryDiv> tCountryDivs = tCountryDivDAO.findLowestCountryDivision(country.getId().intValue());
				if(null != tCountryDivs && !tCountryDivs.isEmpty()){
					return CountryDivisionModelAssembler.convertTCountryDivToModel(tCountryDivs.get(0));
				}
			}
		}
		catch(RuntimeException e){
			LOGGER.error("exception while fetching lowest lookup table", e);
			throw new OpservDataAccessException("exception while fetching lowest lookup table", e);
		}
		return null;
	}
	
}
