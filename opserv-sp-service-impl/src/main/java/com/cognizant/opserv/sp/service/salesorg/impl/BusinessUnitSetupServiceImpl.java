package com.cognizant.opserv.sp.service.salesorg.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.exception.BussUnitServiceException;
import com.cognizant.opserv.sp.exception.BussUnitServiceException.BussUnitServiceExceptionCode;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.BusinessUnitSetupDAOService;
import com.cognizant.opserv.sp.service.salesorg.BusinessUnitSetupService;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.exception.OpservDataAccessException;

// TODO: Auto-generated Javadoc
/**
 * The Class BusinessUnitSetupServiceImpl.
 */
@Service("businessUnitSetupService")
public class BusinessUnitSetupServiceImpl implements BusinessUnitSetupService{

	/** The business unit setup dao service. */
	@Autowired
	private BusinessUnitSetupDAOService businessUnitSetupDAOService;
	
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.service.salesorg.BusinessUnitSetupService#createBusinessUnit(com.cognizant.opserv.sp.model.BusinessUnit, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	@Transactional
	public BusinessUnit createBusinessUnit(BusinessUnit businessUnit,
			UserDetails userDetails) throws BussUnitServiceException {
		try{// buss_unit_cd missing in model
			if(businessUnit == null || businessUnit.getCreatedBy() == null ||  businessUnit.getName() == null ||
					businessUnit.getSalesOrg()==null || businessUnit.getSalesOrg().getId() == null ||businessUnit.getStartDate() == null ||
					businessUnit.getCreatedDate() == null || userDetails == null || userDetails.getTenantId() == null){
				Object[] missingArgs = new Object[]{"Required information is missing"};
				throw new BussUnitServiceException(missingArgs);
			}
			return businessUnitSetupDAOService.createNewBusinessUnit(businessUnit, userDetails);
		}catch(OpservDataAccessException opservDataAccessException){
			Object[] args = new Object[]{"Business unit creation failed!!"};
			throw new BussUnitServiceException(BussUnitServiceExceptionCode.BUSSUNIT_SER_EX_CD_001, "create_exception", args, opservDataAccessException);
		}
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.service.salesorg.BusinessUnitSetupService#updateBusinessUnit(com.cognizant.opserv.sp.model.BusinessUnit, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	@Transactional
	public void updateBusinessUnit(BusinessUnit businessUnit,
			UserDetails userDetails) throws BussUnitServiceException {
	try{// buss_unit_cd missing in model
		if(businessUnit == null || businessUnit.getId() == null || businessUnit.getName() == null
				|| businessUnit.getCreatedBy() == null || businessUnit.getCreatedDate() == null || 
				businessUnit.getSalesOrg() == null || businessUnit.getSalesOrg().getId() == null||
				userDetails == null || userDetails.getTenantId() == null){
			Object[] missingArgs= new Object[]{"Required information is missing"};
				throw new BussUnitServiceException(missingArgs);
			}
		 businessUnitSetupDAOService.updateBusinessUnitInfo(businessUnit, userDetails);
	}catch(OpservDataAccessException dataAccessException){
		Object[] args = new Object[]{"Business unit updation failed!!"};
		throw new BussUnitServiceException(BussUnitServiceExceptionCode.BUSSUNIT_SER_EX_CD_002, "update_exception", args, dataAccessException);
		}
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.service.salesorg.BusinessUnitSetupService#inactiveBussUnit(java.lang.Long, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	@Transactional
	public void inactiveBussUnit(Long businessUnitId, UserDetails userDetails)throws BussUnitServiceException {
		try {
			if(businessUnitId == null || userDetails==null || userDetails.getTenantId() == null){
				Object[] missingArgs = new Object[]{"Required information is missing"};
				throw new BussUnitServiceException(missingArgs);
			}
			businessUnitSetupDAOService.softDeletionOfBusinessUnit(businessUnitId, userDetails);
		} catch (OpservDataAccessException e) {
			Object[] args = new Object[]{businessUnitId};
			throw new BussUnitServiceException(BussUnitServiceExceptionCode.BUSSUNIT_SER_EX_CD_003, "inactive_exception", args, e);
		}
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.service.salesorg.BusinessUnitSetupService#getBussUnitDetails(java.lang.Long, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	@Transactional
	public BusinessUnit getBussUnitDetails(Long businessUnitId,
			UserDetails userDetails) throws BussUnitServiceException {
		try {
			if(businessUnitId == null || userDetails==null || userDetails.getTenantId() == null){
				Object[] missingArgs = new Object[]{"Required information is missing"};
				throw new BussUnitServiceException(missingArgs);
			}
			return businessUnitSetupDAOService.getAllDetailsOfBusinessUnit(businessUnitId, userDetails);
		} catch (OpservDataAccessException e) {
			Object[] args = new Object[]{businessUnitId};
			throw new BussUnitServiceException(BussUnitServiceExceptionCode.BUSSUNIT_SER_EX_CD_004, "fetch_exception", args, e);
		}
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.service.salesorg.BusinessUnitSetupService#fetchBussUnits(com.cognizant.peg.core.common.ISearchCriteria, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	@Transactional
	public List<BusinessUnit> fetchBussUnitsBySearchCriteria(ISearchCriteria searchCriteria,
			UserDetails userDetails) throws BussUnitServiceException {
		try {
			if(searchCriteria == null || userDetails==null || userDetails.getTenantId() == null){
				Object[] missingArgs = new Object[]{"Required information is missing"};
				throw new BussUnitServiceException(missingArgs);
			}
			return businessUnitSetupDAOService.fetchBusinessUnitsByCriteria(searchCriteria, userDetails);
		} catch (OpservDataAccessException e) {
			Object[] args = new Object[]{"criteria is missing!!"};
			throw new BussUnitServiceException(BussUnitServiceExceptionCode.BUSSUNIT_SER_EX_CD_005, "fetch_exception", args, e);
		}
	}

}
