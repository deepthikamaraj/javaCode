package com.cognizant.opserv.sp.assembler;

import com.cognizant.opserv.sp.core.entity.TCountry;
import com.cognizant.opserv.sp.model.Country;

public class CountryModelAssembler {
	
	public static Country convertTCountryToModel(TCountry tCountry){
		if(null != tCountry){
			Country country = new Country();
			country.setId(tCountry.getCountryId().longValue());
			country.setName(tCountry.getCountryName());
			country.setPostalCodeName(tCountry.getPostalCdName());
			return country;
		}
		else{
			return null;
		}
	}

}
