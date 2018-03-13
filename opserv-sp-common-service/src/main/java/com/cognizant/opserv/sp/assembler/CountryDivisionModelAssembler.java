package com.cognizant.opserv.sp.assembler;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.core.entity.TCountryDiv;
import com.cognizant.opserv.sp.model.CountryDivision;

public class CountryDivisionModelAssembler {
	
	public static CountryDivision convertTCountryDivToModel(TCountryDiv tCountryDiv){
		CountryDivision countryDivision = null;
		if(null != tCountryDiv){
			countryDivision = new CountryDivision();
			countryDivision.setId(tCountryDiv.getTCountryDivId().getDivId().longValue());
			countryDivision.setName(tCountryDiv.getDivName());
			countryDivision.setCode(tCountryDiv.getDivCd());
			if(null != tCountryDiv.getTCountry()){
				countryDivision.setCountry(CountryModelAssembler.convertTCountryToModel(tCountryDiv.getTCountry()));
			}
			countryDivision.setLookupTable(tCountryDiv.getLookupTbl());
			if(tCountryDiv.getLowestDivFlag() == CommonConstants.ACTIVE_Y){
				countryDivision.setLowestDivFlag(true);
			}
			else{
				countryDivision.setLowestDivFlag(false);
			}
		}
		return countryDivision;
	}
}
