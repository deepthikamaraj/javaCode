package com.cognizant.opserv.sp.cr.process.internal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.common.CallPlanType;
import com.cognizant.opserv.sp.cr.process.dto.CustomerAlignmentDTO;
import com.cognizant.opserv.sp.cr.process.dto.MirrorCustAlgmtDTO;
import com.cognizant.opserv.sp.cr.process.internal.service.CallPlanOfflineService;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.CallPlanServiceException.CallPlanServiceExceptionCode;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerCallPlan;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.CallPlanDAOService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

@Service
public class CallPlanOfflineServiceImpl implements CallPlanOfflineService {

	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(CallPlanOfflineServiceImpl.class);

	/**
	 * CallPlan DAO Service
	 */
	@Autowired
	private CallPlanDAOService callPlanDAOService;

	/**
	 * To create CallPlan
	 * 
	 * @param custCallPlans
	 *            -list of Customer CallPlans
	 * 
	 * @param customerAlignment
	 *            -customer Alignment details
	 * 
	 * @param userDetails
	 * @throws CallPlanServiceException
	 */
	@Override
	public void createCallPlan(List<CustomerCallPlan> custCallPlans, CustomerAlignment customerAlignment, UserDetails userDetails)
			throws CallPlanServiceException {

		try {
			LOGGER.debug("********************Create CallPlan started  ******************" + customerAlignment.getId());
			if (custCallPlans != null && !custCallPlans.isEmpty() && userDetails.getTenantId() != null && customerAlignment.getId() != null
					&& userDetails.getUserId() != null) {
				callPlanDAOService.createCallPlan(custCallPlans, customerAlignment, userDetails);
			} else {
				Object[] args = new Object[] { "customerCallPlanDetails or customerAlignment or tenantId is null" };
				throw new CallPlanServiceException(args);
			}
			LOGGER.debug("********************Create CallPlan Ended  ******************  ");
		} catch (OpservDataAccessException e) {
			LOGGER.error("********************Issue occurred while Creating callPlan  ****************** " + e.getMessage());
			Object[] args = new Object[] { "custCallPlans" };
			throw new CallPlanServiceException(CallPlanServiceExceptionCode.CALLPLAN_SER_EX_CD_002, "This issue occurred while creating callPlan", args, e);
		}
	}

	/**
	 * Gets the CallPlan Details for List of Customer Alignment Id's
	 * 
	 * @param CustomerAlignmentDTO
	 *            -the Customer Alignment DTO
	 * @param userDetails
	 *            -the userDetails
	 * @throws CallPlanServiceException
	 *             -the Call Plan Service Exception
	 */
	@Transactional
	public void populateCallPlan(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails) throws CallPlanServiceException {
		try {
			if (custAlgmtDTO != null && userDetails.getTenantId() != null && userDetails != null) {
				LOGGER.info("**************************Execute of populateCallPlan data for Affiliated Customers with Base and Mirror --- started*********************");

				List<Long> custAlIds = new ArrayList<Long>();
				Map<Long, List<CustomerCallPlan>> custAlCPMap = new HashMap<Long, List<CustomerCallPlan>>();

				custAlIds.add(custAlgmtDTO.getSourceBaseCustAlgmt().getId());
				LOGGER.info("**************************Root Customer Alignment with Base is ====*****" + custAlIds);

				if (custAlgmtDTO.getSourceBaseAffCustAlgmts() != null && !custAlgmtDTO.getSourceBaseAffCustAlgmts().isEmpty()) {
					for (CustomerAlignment custAlgnmnt : custAlgmtDTO.getSourceBaseAffCustAlgmts()) {
						custAlIds.add(custAlgnmnt.getId());
					}
				}
				LOGGER.info("**************************Affliated Customer Alignment with Base are ====*****" + custAlIds);

				if (custAlgmtDTO.getMirrors() != null && !custAlgmtDTO.getMirrors().isEmpty()) {
					for (MirrorCustAlgmtDTO custAlgnmntMirrorDTO : custAlgmtDTO.getMirrors()) {
						if (custAlgnmntMirrorDTO.getSourceMirrorCustAlgmts() != null && !custAlgnmntMirrorDTO.getSourceMirrorCustAlgmts().isEmpty()) {
							for (CustomerAlignment sourceMirrorCustAlgmt : custAlgnmntMirrorDTO.getSourceMirrorCustAlgmts()) {
								custAlIds.add(sourceMirrorCustAlgmt.getId());

							}
						}

					}
				}
				LOGGER.info("**************************Affliated Customer Alignment with Mirror are ====*****" + custAlIds);

				LOGGER.info("**************************Fetch Acive CallPlan by CustAlId started******************************");
				custAlCPMap = callPlanDAOService.getActiveCustCallPlanByCustAlId(custAlIds, userDetails);
				LOGGER.info("**************************Fetch Acive CallPlan by CustAlId Ended******************************");

				LOGGER.info("**************************To set the Call Plan details to Root Customer Alignment ---- started******************************");
				if (custAlCPMap != null && !custAlCPMap.isEmpty() && custAlCPMap.entrySet() != null && !custAlCPMap.entrySet().isEmpty()) {
					for (Map.Entry<Long, List<CustomerCallPlan>> entry : custAlCPMap.entrySet()) {
						if (custAlgmtDTO.getSourceBaseCustAlgmt().getId().equals(entry.getKey())) {
							List<CustomerCallPlan> callPlans = entry.getValue();
							if (callPlans != null && !callPlans.isEmpty()) {
								for (CustomerCallPlan customerCallPlan : callPlans) {
									if (customerCallPlan.getType().equals(CallPlanType.BASE)) {
										custAlgmtDTO.getSourceBaseCustAlgmt().setBaseCustomerCallPlan(customerCallPlan);
									} else if (customerCallPlan.getType().equals(CallPlanType.PLANNED)) {
										custAlgmtDTO.getSourceBaseCustAlgmt().setCustomerCallPlan(customerCallPlan);
									}
								}
							}

						}
					}
				}
				LOGGER.info("**************************To set the Call Plan details to Root Customer Alignment ---- ended******************************");

				LOGGER.info("**************************To set the Call Plan details to Affiliated Customer Alignment ---- started******************************");
				if (custAlgmtDTO.getSourceBaseAffCustAlgmts() != null && !custAlgmtDTO.getSourceBaseAffCustAlgmts().isEmpty()) {
					for (CustomerAlignment custAlgnmnt : custAlgmtDTO.getSourceBaseAffCustAlgmts()) {
						if (custAlCPMap != null && !custAlCPMap.isEmpty() && custAlCPMap.entrySet() != null && !custAlCPMap.entrySet().isEmpty()) {
							for (Map.Entry<Long, List<CustomerCallPlan>> entry : custAlCPMap.entrySet()) {
								if (!custAlgmtDTO.getSourceBaseCustAlgmt().getId().equals(entry.getKey())) {
									List<CustomerCallPlan> callPlans = entry.getValue();
									if (callPlans != null && !callPlans.isEmpty()) {
										for (CustomerCallPlan customerCallPlan : callPlans) {

											if (customerCallPlan.getType().equals(CallPlanType.BASE) && (entry.getKey().equals(custAlgnmnt.getId()))) {
												custAlgnmnt.setBaseCustomerCallPlan(customerCallPlan);
											} else if (customerCallPlan.getType().equals(CallPlanType.PLANNED) && (entry.getKey().equals(custAlgnmnt.getId()))) {
												custAlgnmnt.setCustomerCallPlan(customerCallPlan);
											}
										}
									}
								}
							} // break;
						}
					}
				}
				LOGGER.info("**************************To set the Call Plan details to Affiliated Customer Alignments ---- ended******************************");

				LOGGER.info("**************************To set the Call Plan details to Mirror Customer Alignments ---- started******************************");
				if (custAlgmtDTO.getMirrors() != null && !custAlgmtDTO.getMirrors().isEmpty()) {
					for (MirrorCustAlgmtDTO custAlgnmntMirrorDTO : custAlgmtDTO.getMirrors()) {
						if (custAlgnmntMirrorDTO.getSourceMirrorCustAlgmts() != null && !custAlgnmntMirrorDTO.getSourceMirrorCustAlgmts().isEmpty()) {
							for (CustomerAlignment custAlgmntMirror : custAlgnmntMirrorDTO.getSourceMirrorCustAlgmts()) {

								if (custAlCPMap != null && !custAlCPMap.isEmpty() && custAlCPMap.entrySet() != null && !custAlCPMap.entrySet().isEmpty())
									for (Map.Entry<Long, List<CustomerCallPlan>> entry : custAlCPMap.entrySet()) {

										if (custAlgmntMirror.getId().equals(entry.getKey())) {
											List<CustomerCallPlan> callPlans = entry.getValue();
											if (callPlans != null && !callPlans.isEmpty()) {
												for (CustomerCallPlan customerCallPlan : callPlans) {
													if (customerCallPlan.getType().equals(CallPlanType.BASE)
															&& (entry.getKey().equals(custAlgmntMirror.getId()))) {
														custAlgmntMirror.setBaseCustomerCallPlan(customerCallPlan);
													} else if (customerCallPlan.getType().equals(CallPlanType.PLANNED)
															&& (entry.getKey().equals(custAlgmntMirror.getId()))) {
														custAlgmntMirror.setCustomerCallPlan(customerCallPlan);
													}
												}
											}
										}
									}

							}
							// break;
						}
					}
				}

				LOGGER.info("**************************To set the Call Plan details to Mirror Customer Alignments ---- ended******************************");
				LOGGER.info("**************************Execute of populateCallPlan data for Affiliated Customers with Base and Mirror --- end*********************");
			} else {
				Object[] args = new Object[] { "CustomerAlignmentDTO or tenantId or is null" };
				throw new CallPlanServiceException(args);
			}
		} catch (OpservDataAccessException e) {
			LOGGER.error("********************Issue occurred while fetching CallPlan Details from List of Cust Alignment Id's  ******************  ");
			Object[] args = new Object[] { "CustomerAlignmentDTO" };
			throw new CallPlanServiceException(CallPlanServiceExceptionCode.CALLPLAN_SER_EX_CD_010,
					"Issue occurred while fetching CallPlan Details from List of Cust Alignment Id's", args, e);
		}
	}

	/**
	 * Edit CallPlan Details for Customer Alignment
	 * 
	 * @param CustomerAlignmentDTO
	 *            -the Customer Alignment DTO
	 * @param userDetails
	 *            -the userDetails
	 * @throws CallPlanServiceException
	 */
	@Override
	public void editCustomerCallPlan(CustomerAlignment targetCustomerAlignment, UserDetails userDetails) throws CallPlanServiceException {
		LOGGER.info("**************************Execute of editCallPlan ---- Started******************************");
		try {
				LOGGER.info("**************************Edit of Planned Call Plan details For Base Customer Alignment ---- Started******************************");
					callPlanDAOService.editCustomerCallPlan(targetCustomerAlignment, userDetails);
				LOGGER.info("**************************Edit of Planned Call Plan details For Base Customer Alignment ---- ended******************************");

				LOGGER.info("**************************Edit of Planned Call Plan details For Mirror Customer Alignment ---- Started******************************");

						/*if (custAlgnmntMirrorDTO.getSourceMirrorCustAlgmts() != null && !custAlgnmntMirrorDTO.getSourceMirrorCustAlgmts().isEmpty()) {

							for (CustomerAlignment custAlgmntMirror : custAlgnmntMirrorDTO.getSourceMirrorCustAlgmts()) {
								if (custAlgmntMirror.getCustomerCallPlan().getPlannedCalls() == 0) {
									custAlgmntMirror.setCustomerCallPlan(custAlgmtDTO.getTargetBaseCustAlgmt().getCustomerCallPlan());
									callPlanDAOService.editCustomerCallPlan(custAlgmntMirror, userDetails);
								}

							}


				}*/
				LOGGER.info("**************************Edit Planned Call Plan details For Mirror Customer Alignment ---- ended******************************");
			
		} catch (OpservDataAccessException e) {
			LOGGER.error("********************Issue occured while editing PlannedCalls  ******************  " + e.getMessage());
			Object[] args = new Object[] { "custCallPlan" };
			throw new CallPlanServiceException(CallPlanServiceExceptionCode.CALLPLAN_SER_EX_CD_005,
					"This issue occurred while editing callPlan for the customer", args, e);
		}
		LOGGER.info("**************************Execute of editCallPlan ---- ended ******************************");

	}

}
