package com.cognizant.opserv.sp.assembler;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.core.entity.TCustAddr;
import com.cognizant.opserv.sp.core.entity.TPersAddr;
import com.cognizant.opserv.sp.model.Address;

@Component
public class AddressModelAssembler {
	
	/**
	 * Convert t pers addr to address model.
	 *
	 * @param tPersAddr the t pers addr
	 * @return the address
	 */
	public Address convertTPersAddrToAddressModel(Set<TPersAddr> tPersAddr)
	{
		Address address=new Address();
		
		for (TPersAddr Addr :  tPersAddr) {
			if(null != Addr.getPrAddrFlag() && Addr.getPrAddrFlag().equals(CommonConstants.ACTIVE_FLAG))
			{
			address.setId(Addr.getAddrId().longValue());
			address.setAddressLine(Addr.getAddrLine());
			address.setDoorNumber(Addr.getDoorNumber());
			address.setCity(Addr.getCity());
			address.setCountry(Addr.getCntry());
			address.setPostalCode(Addr.getPostalCd());
			address.setStreetName(Addr.getStreetName());
			address.setState(Addr.getState());
			address.setAddressType(Addr.getAddrTypeId());
			address.setAddressLine2(Addr.getAddrLine2());
			address.setAddressLine3(Addr.getAddrLine3());
			address.setAddressLine4(Addr.getAddrLine4());
			if(Addr.getPrAddrFlag().equals(CommonConstants.ACTIVE_FLAG))
			{
			address.setPrimaryAddr(true);
			}
			}
	    }
		
		return address;
		
	}
	
	/**
	 * Convert t cust addr to address model.
	 *
	 * @param tCustAddr the t cust addr
	 * @return the address
	 */
	public Address convertTCustAddrToAddressModel(TCustAddr tCustAddr){
		Address address = null;
		if(tCustAddr != null){
			address = new Address();
			address.setId(tCustAddr.getAddrId().longValue());
			address.setCode(tCustAddr.getAddrCd());
			address.setCreatedBy(tCustAddr.getCreatedBy());
			address.setCreatedDate(tCustAddr.getCreateDt());
			address.setUpdatedBy(tCustAddr.getUpdatedBy());
			address.setUpdatedDate(tCustAddr.getUpdateDt());
			address.setTenantId(tCustAddr.getTenantId());
			if(null!= tCustAddr.getAddrTypeId()){
			address.setAddressType(tCustAddr.getAddrTypeId().intValue());
			}
			address.setDoorNumber(tCustAddr.getDoorNum());
			address.setStreetName(tCustAddr.getStreetName());
			address.setAddressLine(tCustAddr.getAddrLine());
			address.setAddressLine2(tCustAddr.getAddrLine2());
			address.setAddressLine3(tCustAddr.getAddrLine3());
			address.setAddressLine4(tCustAddr.getAddrLine4());
			address.setCity(tCustAddr.getCity());
			address.setState(tCustAddr.getState());
			address.setCountry(tCustAddr.getCntry());
			address.setPostalCode(tCustAddr.getPostalCd());
			address.setPostalExtension(tCustAddr.getPostalExtn());
			if(tCustAddr.getPrAddrFlag() == CommonConstants.ACTIVE_Y){
				address.setPrimaryAddr(true);
			}
			else{
				address.setPrimaryAddr(false);
			}
			if(tCustAddr.getActiveFlag() == CommonConstants.ACTIVE_Y){
				address.setActive(true);
			}
			else{
				address.setActive(false);
			}
		}
		return address;
	}

}
