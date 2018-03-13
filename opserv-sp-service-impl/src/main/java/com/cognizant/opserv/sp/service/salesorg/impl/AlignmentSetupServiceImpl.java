package com.cognizant.opserv.sp.service.salesorg.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException.AlignmentServiceExceptionCode;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.AlignmentSetupDAOService;
import com.cognizant.opserv.sp.service.salesorg.AlignmentSetupService;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 * 
 * @class AlignmentSetupServiceImpl contains all the Alignment setup services
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 *        ************************************************************
 *        ***************
 */
@Service("alignmentSetupService")
@Transactional
public class AlignmentSetupServiceImpl implements AlignmentSetupService {

	@Autowired
	private AlignmentSetupDAOService alignmentSetupDAOService;

	/**
	 * Save the alignment .
	 * 
	 * @method createAlignment
	 * @param alignment
	 *            the alignment
	 * @param userDetails
	 *            the user details
	 * @return the alignment
	 * @throws AlignmentServiceException
	 *             the Alignment Setup service exception
	 * @method createAlignment
	 */
	public Alignment createAlignment(Alignment alignment,
			UserDetails userDetails) throws AlignmentServiceException {
		try {
			if (userDetails == null || userDetails.getTenantId() == null
					|| alignment == null) {
				Object[] args = { "alignment", "userDetails" };
				throw new AlignmentServiceException(args);
			}
			alignmentSetupDAOService.createAlignment(alignment, userDetails);
		} catch (OpservDataAccessException e) {
			Object[] args = new Object[] { alignment };
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_300,
					"Fetch exception", args, e);
		}
		return alignment;
	}

	/**
	 * Update the alignment .
	 * 
	 * @method updateAlignment
	 * @param alignment
	 *            the alignment
	 * @param userDetails
	 *            the user details
	 * @throws AlignmentServiceException
	 *             the Alignment Setup service exception
	 * @method updateAlignment
	 */
	public void updateAlignment(Alignment alignment, UserDetails userDetails)
			throws AlignmentServiceException {
		try {
			if (userDetails == null || userDetails.getTenantId() == null
					|| alignment == null || alignment.getId() == null) {
				Object[] args = { "alignment", "userDetails" };
				throw new AlignmentServiceException(args);
			}

			alignmentSetupDAOService.updateAlignment(alignment, userDetails);
		} catch (OpservDataAccessException e) {
			Object[] args = new Object[] { alignment };
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_301,
					"Fetch exception", args, e);
		}

	}

	/**
	 * inactiveAlignment the alignment .
	 * 
	 * @method inactiveAlignment
	 * @param alignment
	 *            the alignment
	 * @param userDetails
	 *            the user details
	 * @throws AlignmentServiceException
	 *             the Alignment Setup service exception
	 * @method inactiveAlignment
	 */
	public void inactivateAlignment(Alignment alignment, UserDetails userDetails)
			throws AlignmentServiceException {
		try {
			if (userDetails == null || userDetails.getTenantId() == null
					|| alignment == null || alignment.getId() == null) {
				Object[] args = { "alignmentId", "userDetails" };
				throw new AlignmentServiceException(args);
			}

			alignmentSetupDAOService.inactiveAlignment(alignment, userDetails);
		} catch (OpservDataAccessException e) {
			Object[] args = new Object[] { alignment };
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_302,
					"Fetch exception", args, e);
		}

	}

	/**
	 * Gets the alignment details .
	 * 
	 * @method getAlignmentDetails
	 * @param alignment
	 *            the alignment
	 * @param userDetails
	 *            the user details
	 * @return the alignment
	 * @throws AlignmentServiceException
	 *             the Alignment Setup service exception
	 * @method getAlignmentDetails
	 */
	public Alignment getAlignmentDetails(long alignmentId,
			UserDetails userDetails) throws AlignmentServiceException {
		Alignment alignment = new Alignment();
		try {
			if (userDetails == null || alignmentId == 0
					|| userDetails.getTenantId() == null) {
				Object[] args = { "alignmentId", "userDetails" };
				throw new AlignmentServiceException(args);
			}
			alignment = alignmentSetupDAOService.getAlignmentDetails(
					alignmentId, userDetails);
		} catch (OpservDataAccessException e) {
			Object[] args = new Object[] { alignmentId };
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_303,
					"Fetch exception", args, e);
		}
		return alignment;
	}

	/**
	 * Gets the alignment List .
	 * 
	 * @method fetchAlignments
	 * @param alignment
	 *            the alignment
	 * @param userDetails
	 *            the user details
	 * @return the List<alignment>
	 * @throws AlignmentServiceException
	 *             the Alignment Setup service exception
	 * @method fetchAlignments
	 */
	public List<Alignment> fetchAlignments(ISearchCriteria criteria,
			UserDetails userDetails) throws AlignmentServiceException {
		try {
			if (criteria == null) {
				throw new AlignmentServiceException(new Object[] { "criteria" });
			}

			return alignmentSetupDAOService.fetchAlignments(criteria,
					userDetails);
		} catch (OpservDataAccessException e) {
			Object[] args = new Object[] { criteria };
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_304,
					"Fetch exception", args, e);
		}
	}

}
